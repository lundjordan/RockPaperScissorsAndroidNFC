package com.example.rockpaperscissors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MidGame extends Activity implements SensorEventListener {
	
	  private long lastUpdate;
	  private String choice;
	  private SensorManager sensorManager;
	  private int shakes = 0;


	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.shake);
		  sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		  lastUpdate = System.currentTimeMillis();
		  Bundle extras = getIntent().getExtras(); 
		  if(extras !=null) {
		      choice = extras.getString("choice");

		  }
	  }


	  public void onSensorChanged(SensorEvent event) {
		  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			  getAccelerometer(event);
		  }

	  }

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdate < 200) {
				return;
			}
			shakes += 1;
			
			if (shakes == 5) {
				lastUpdate = actualTime;
				final Context context = this;
			    Intent intent = new Intent(context, Result.class);
			    intent.putExtra("choice", choice);
		        startActivity(intent); 
			}
			
		}
	}

	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
