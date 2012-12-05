package com.example.rockpaperscissors;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends Activity {
	
	  private String choice;
	  private String opponent;
	  private RPSengine game;
	  private Map<String, String> stats;
	  private Map<String, View> correctImages = new HashMap<String, View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null) {
			choice = extras.getString("choice");
			if (extras.getString("opponent") != null) {
				opponent = extras.getString("opponent");
			}
		}
		if (opponent != null) { // single
			stats = new HashMap<String, String>();
			stats.put("userChoice", choice);
			stats.put("opponentChoice", opponent);
			stats.put("result", RPSengine.getResult(stats.get("userChoice"), stats.get("opponentChoice")));
		}
		else { //multiplayer
			stats = RPSengine.play(choice);			
		}
		
		findCorrectImages();
		
		final ImageView userImage = (ImageView) correctImages.get("userImage");
		final ImageView opponentImage = (ImageView) correctImages.get("opponentImage");
		final TextView resultImage = (TextView) correctImages.get("resultView");

		Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
		final Animation animationFadeIn2 = AnimationUtils.loadAnimation(this, R.anim.fadein);

		userImage.startAnimation(animationFadeIn);
		userImage.setVisibility(View.VISIBLE);

		opponentImage.startAnimation(animationFadeIn);
		opponentImage.setVisibility(View.VISIBLE);
		resultImage.startAnimation(animationFadeIn);
		resultImage.setVisibility(View.VISIBLE);

	 	findViewById(R.id.playAgain).setVisibility(View.VISIBLE);

		Log.i("from intent", choice);
		Log.i("stats[userChoice]", stats.get("userChoice"));
		Log.i("stats[opponentChoice]", stats.get("opponentChoice"));
		Log.i("stats[result]", stats.get("result"));
		
		addListenerOnButtons();

	}
	
	public void addListenerOnButtons() {
		final Context context = this;
		findViewById(R.id.playAgain).setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(Result.this, MainMenu.class);
			    startActivity(intent);   
			}
	
		});
	}
	
	public void findCorrectImages() {
		
		// set user image view visible 
		if (stats.get("userChoice").equals("rock")) {
	        ImageView iv = (ImageView) findViewById(R.id.imageView1);
	        correctImages.put("userImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		else if (stats.get("userChoice").equals("paper")) {
	        ImageView iv = (ImageView) findViewById(R.id.imageView2);
	        correctImages.put("userImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		else {
	        ImageView iv = (ImageView) findViewById(R.id.imageView3);
	        correctImages.put("userImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		
		// set opponent image view visible 
		if (stats.get("opponentChoice").equals("rock")) {
	        ImageView iv = (ImageView) findViewById(R.id.ImageView01);
	        correctImages.put("opponentImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		else if (stats.get("opponentChoice").equals("paper")) {
	        ImageView iv = (ImageView) findViewById(R.id.ImageView02);
	        correctImages.put("opponentImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		else {
	        ImageView iv = (ImageView) findViewById(R.id.ImageView03);
	        correctImages.put("opponentImage", iv);
	        //iv.setVisibility(View.VISIBLE);
		}
		
		// set result text view visible 
		if (stats.get("result").equals("win")) {
	        TextView tv = (TextView) findViewById(R.id.textView2);
	        correctImages.put("resultView", tv);
		}
		else if (stats.get("result").equals("lose")) {
			TextView tv = (TextView) findViewById(R.id.TextView02);
	        correctImages.put("resultView", tv);
	        //tv.setVisibility(View.VISIBLE);
		}
		else {
			TextView tv = (TextView) findViewById(R.id.textView3);
	        correctImages.put("resultView", tv);
	        //tv.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
		
	}

}
