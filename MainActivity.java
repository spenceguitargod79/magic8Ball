package com.spencerhealy.magic8ball;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends Activity implements SensorEventListener{
	
	//Class variables here
	public static int NUM_ANSWERS = 20;
	
	//declare accelerometer variables
	SensorManager sensorManager;
	Sensor accelerometerSensor;
	boolean accelerometerPresent;
	boolean isPlayingUp = false;
	boolean isPlayingDown = false;
	private long lastUpdate;
	
	//declare media player instances
	MediaPlayer sound1, sound2, sound3, sound4, sound5, sound6, sound7, sound8, sound9, sound10,
				sound11, sound12, sound13, sound14, sound15, sound16, sound17, sound18, sound19, 
				sound20, sound21, sound22, sound23, sound24 ;
	
	//SHAKE DETECTION VARIABLES--------------------------------
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Check if the device has an accelerometer and if so, set it up
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();
	    List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	    if(sensorList.size() > 0){
	      accelerometerPresent = true;
	      accelerometerSensor = sensorList.get(0);  
	    }
	    else{
	      accelerometerPresent = false;  
	      sound23.start();//no accelerometer present on the device
	    }
		
		//instantiate all of the media player sounds
		sound1 = MediaPlayer.create(this, R.raw.notshakehard);
		sound2 = MediaPlayer.create(this, R.raw.godsshine);
		sound3 = MediaPlayer.create(this, R.raw.naptime);
		sound4 = MediaPlayer.create(this, R.raw.ofcourse);
		sound5 = MediaPlayer.create(this, R.raw.monk);
		sound6 = MediaPlayer.create(this, R.raw.monkeys);
		sound7 = MediaPlayer.create(this, R.raw.rabbitfoot);
		sound8 = MediaPlayer.create(this, R.raw.pigsfly);
		sound9 = MediaPlayer.create(this, R.raw.yesno);
		sound10 = MediaPlayer.create(this, R.raw.brilliantno);
		sound11 = MediaPlayer.create(this, R.raw.brilliantyes);
		sound12 = MediaPlayer.create(this, R.raw.dreamer);
		sound13 = MediaPlayer.create(this, R.raw.rubbish);
		sound14 = MediaPlayer.create(this, R.raw.badweather);
		sound15 = MediaPlayer.create(this, R.raw.mothballs);
		sound16 = MediaPlayer.create(this, R.raw.lottery);
		sound17 = MediaPlayer.create(this, R.raw.getajob);
		sound18 = MediaPlayer.create(this, R.raw.whythehellnot);
		sound19 = MediaPlayer.create(this, R.raw.informe);
		sound20= MediaPlayer.create(this, R.raw.nickle);
		
		sound21= MediaPlayer.create(this, R.raw.facedownshakeme);
		sound22= MediaPlayer.create(this, R.raw.faceup);
		
		sound23= MediaPlayer.create(this, R.raw.noaccelorometer);
		
		sound24= MediaPlayer.create(this, R.raw.introduction);//the introduction/rules
		
		//Toast.makeText(this, getAnswer(), Toast.LENGTH_LONG).show();
		
		sound24.start();
		
	}
	
	//Randomly play an answer mp3 
	private int getAnswer()
	{
		switch ((int) (NUM_ANSWERS * Math.random() + 1)) 
		{
			case 1: 
				sound1.start();
				return R.string.answer1;
			case 2: 
				sound2.start();
				return R.string.answer2;
			case 3: 
				sound3.start();
				return R.string.answer3;
			case 4: 
				sound4.start();
				return R.string.answer4;
			case 5: 
				sound5.start();
				return R.string.answer5;
			case 6: 
				sound6.start();
				return R.string.answer6;
			case 7: 
				sound7.start();
				return R.string.answer7;
			case 8: 
				sound8.start();
				return R.string.answer8;
			case 9: 
				sound9.start();
				return R.string.answer9;
			case 10: 
				sound10.start();
				return R.string.answer10;
			case 11: 
				sound11.start();
				return R.string.answer11;
			case 12: 
				sound12.start();
				return R.string.answer12;
			case 13: 
				sound13.start();
				return R.string.answer13;
			case 14:
				sound14.start();
				return R.string.answer14;
			case 15: 
				sound15.start();
				return R.string.answer15;
			case 16: 
				sound16.start();
				return R.string.answer16;
			case 17: 
				sound17.start();
				return R.string.answer17;
			case 18: 
				sound18.start();
				return R.string.answer18;
			case 19: 
				sound19.start();
				return R.string.answer19;
				
			default: 
				sound20.start();
				return R.string.answer20;
			
		}
		
	}
	
	@Override
	protected void onResume() {
	 // TODO Auto-generated method stub
	 super.onResume();
	 if(accelerometerPresent){
	   sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	   }
	  }
	
	 @Override
	  protected void onPause() {
	    // unregister listener
	    super.onPause();
	    sensorManager.unregisterListener(this);
	  }

	  @Override
	protected void onStop() {
	 // TODO Auto-generated method stub
	 super.onStop();
	 if(accelerometerPresent){
	   sensorManager.unregisterListener(accelerometerListener);  
	   }
	  }
	  
	private SensorEventListener accelerometerListener = new SensorEventListener()
	{

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	 // TODO Auto-generated method stub
	    
	}

	@Override
	public void onSensorChanged(SensorEvent se) {
	 // TODO Auto-generated method stub
	  float z_value = se.values[2];
	  if (z_value >= 0 && isPlayingUp == false){
		 
		  	//sound22.start();//face up
		  	isPlayingUp = true;//this will keep the audio from looping
	  }
	  else if(z_value <= 0 && isPlayingDown == false){

			sound21.start();//face down
			isPlayingDown = true;//this will keep the audio from looping
	  }
	  
	  //some shake code
	  if (se.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	      getAccelerometer(se);
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
		    long actualTime = event.timestamp;
		    if (accelationSquareRoot >= 2) //
		    {
		      if (actualTime - lastUpdate < 200) {
		        return;
		      }
		      lastUpdate = actualTime;
		      getAnswer();//play a random sound byte
		    }
		  }

	
	};

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	
}
