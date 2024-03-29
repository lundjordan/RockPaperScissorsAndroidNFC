package com.example.rockpaperscissors;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Multiplayer extends Activity implements CreateNdefMessageCallback,
OnNdefPushCompleteCallback {
	NfcAdapter mNfcAdapter;
	TextView mInfoText;
	  private String choice;
	  
	private static final int MESSAGE_SENT = 1;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplayer);

		//mInfoText = (TextView) findViewById(R.id.textView);
		// Check for available NFC Adapter
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (mNfcAdapter == null) {
			//mInfoText = (TextView) findViewById(R.id.textView);
			//mInfoText.setText("NFC is not available on this device.");
		} else {
			// Register callback to set NDEF message
			mNfcAdapter.setNdefPushMessageCallback(this, this);
			// Register callback to listen for message-sent success
			mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
		}
		Bundle extras = getIntent().getExtras(); 
		  if(extras !=null) {
		      choice = extras.getString("choice");
		  }
	}


	/**
	 * Implementation for the CreateNdefMessageCallback interface
	 */
	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
		Time time = new Time();
		time.setToNow();
		String text = choice;
		NdefMessage msg = new NdefMessage(NdefRecord.createMime(
				"application/com.example.rockpaperscissors", text.getBytes())
				/**
				 * The Android Application Record (AAR) is commented out. When a device
				 * receives a push with an AAR in it, the application specified in the AAR
				 * is guaranteed to run. The AAR overrides the tag dispatch system.
				 * You can add it back in to guarantee that this
				 * activity starts when receiving a beamed message. For now, this code
				 * uses the tag dispatch system.
				 */
				//,NdefRecord.createApplicationRecord("com.example.android.beam")
				);
		return msg;
	}

	/**
	 * Implementation for the OnNdefPushCompleteCallback interface
	 */
	@Override
	public void onNdefPushComplete(NfcEvent arg0) {
		// A handler is needed to send messages to the activity when this
		// callback occurs, because it happens from a binder thread
		mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
	}

	/** This handler receives a message from onNdefPushComplete */
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_SENT:
				Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	public void onResume() {
		super.onResume();
		// Check to see that the Activity started due to an Android Beam
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			processIntent(getIntent());
		}
	}

	@Override
	public void onNewIntent(Intent intent) {
		// onResume gets called after this to handle the intent
		setIntent(intent);
	}

	/**
	 * Parses the NDEF Message from the intent and prints to the TextView
	 */
	void processIntent(Intent intent) {
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
				NfcAdapter.EXTRA_NDEF_MESSAGES);
		// only one message sent during the beam
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		Log.i("sent_message", new String(msg.getRecords()[0].getPayload()));
		final Context context = this;
	    Intent intent2 = new Intent(context, MainActivity.class);
	    intent2.putExtra("opponent", new String(msg.getRecords()[0].getPayload()));
        startActivity(intent2); 
		// record 0 contains the MIME type, record 1 is the AAR, if present
		//mInfoText.setText(new String(msg.getRecords()[0].getPayload()));
	}




}
