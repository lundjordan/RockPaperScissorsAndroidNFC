package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	ImageButton rock_button;
	ImageButton paper_button;
	ImageButton scissors_button;
	private String opponent = null;

	String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle extras = getIntent().getExtras(); 
		  if(extras !=null) {
		      type = extras.getString("type");
				if (extras.getString("opponent") != null) {
					opponent = extras.getString("opponent");
				}
		  }
		addListenerOnButtons();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void addListenerOnButtons() {
		
		final Context context = this;
		rock_button = (ImageButton) findViewById(R.id.rock_button);
		rock_button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent;
				if (opponent != null) {
				    intent = new Intent(context, Result.class);
					intent.putExtra("opponent", opponent);
				}
				else {
					if (type.equals("single")) {
					    intent = new Intent(context, MidGame.class);
					}
					else {
					    intent = new Intent(context, Multiplayer.class);
					}
				}
			    intent.putExtra("choice", "rock");
             	startActivity(intent);   
			}
 
		});
		paper_button = (ImageButton) findViewById(R.id.paper_button);
		paper_button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent;
				if (opponent != null) {
				    intent = new Intent(context, Result.class);
					intent.putExtra("opponent", opponent);
				}
				else {
					if (type.equals("single")) {
					    intent = new Intent(context, MidGame.class);
					}
					else {
					    intent = new Intent(context, Multiplayer.class);
					}
				}
				intent.putExtra("choice", "paper");
                startActivity(intent);   
			}
 
		});	
		scissors_button = (ImageButton) findViewById(R.id.scissors_button);
		scissors_button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent;
				if (opponent != null) {
				    intent = new Intent(context, Result.class);
					intent.putExtra("opponent", opponent);
				}
				else {
					if (type.equals("single")) {
					    intent = new Intent(context, MidGame.class);
					}
					else {
					    intent = new Intent(context, Multiplayer.class);
					}
				}		    
				intent.putExtra("choice", "scissors");
             	startActivity(intent);   
			}
 
		});
	}

}
