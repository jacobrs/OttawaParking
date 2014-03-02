package ca.ottawaparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	boolean paused = false;
	
	@Override
	protected void onCreate(Bundle createSplash) {
		super.onCreate(createSplash);
		setContentView(R.layout.splash); //reference to xml layout
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(2000);	//pauses thread using milliseconds
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					if(!paused){ //Make sure person didn't click home button
						//intent based off of action name
						Intent openMainActivity = new Intent("ca.ottawaparking.MAIN");
						startActivity(openMainActivity);	//will open activity based off of intent
						finish();
					}
				}
			}
		};
		timer.start();	//start thread
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		paused = true;
	}
	
	

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(paused){
			Intent openMainActivity = new Intent("ca.ottawaparking.MAIN");
			startActivity(openMainActivity);	//will open activity based off of intent
			finish();
		}
	}
}
