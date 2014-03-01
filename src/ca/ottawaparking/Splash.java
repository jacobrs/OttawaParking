package ca.ottawaparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	boolean paused;
	
	@Override
	protected void onCreate(Bundle createSplash) {
		super.onCreate(createSplash);
		setContentView(R.layout.splash); //reference to xml layout
		Thread timer = new Thread() {
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
					}
				}
			}
		};
		timer.start();	//start thread
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		paused = true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		paused = false;
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);	//pauses thread using milliseconds
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					if(!paused){
						//intent based off of action name
						Intent openMainActivity = new Intent("ca.ottawaparking.MAIN");
						startActivity(openMainActivity);	//will open activity based off of intent
					}
				}
			}
		};
		timer.start();	//start thread
	}
}
