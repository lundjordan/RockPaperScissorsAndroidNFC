package com.example.rockpaperscissors;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		addListenerOnButtons();

	}
	
	public void addListenerOnButtons() {
		
		final Context context = this;
		Button play_button = (Button) findViewById(R.id.play);
		play_button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, MainActivity.class);
			    intent.putExtra("type", "single");
                startActivity(intent);
			}
 
		});
		
		Button multiplayer_button = (Button) findViewById(R.id.multiplayer);
		multiplayer_button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, MainActivity.class);  
			    intent.putExtra("type", "multiplayer");
                startActivity(intent);
			}
 
		});
	}



}
