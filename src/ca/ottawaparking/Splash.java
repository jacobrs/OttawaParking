package ca.ottawaparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

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
					//intent based off of action name
					Intent openMainActivity = new Intent("ca.ottawaparking.MAIN");
					startActivity(openMainActivity);	//will open activity based off of intent
				}
			}
		};
		timer.start();	//start thread
	}
}
