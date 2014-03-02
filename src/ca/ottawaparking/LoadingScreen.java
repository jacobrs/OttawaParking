package ca.ottawaparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class LoadingScreen extends Activity {
	private final int WAIT_TIME = 1500;
	public boolean back = false;
	Intent mainIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadingscreen);
		findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//retrieve variable from main
				int buttonClicked = getIntent().getExtras().getInt("buttonClicked");
				//depending on button clicked, open class
				if(buttonClicked == 0) {
					mainIntent = new Intent(LoadingScreen.this, BikeMenu.class);
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				}
				else if(buttonClicked == 1) { 
					mainIntent = new Intent(LoadingScreen.this, CarMenu.class);
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				}
				else if(buttonClicked == 2) { 
					mainIntent = new Intent(LoadingScreen.this, StubMenu.class);
					mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				}
				
				//do not start next activity if back button is pressed
				if(!back){
					LoadingScreen.this.startActivity(mainIntent);
					overridePendingTransition(0,0);
				}
				LoadingScreen.this.finish();
			}
		}, WAIT_TIME);
	}
	
	@Override
	public void onBackPressed() {
        LoadingScreen.super.onBackPressed();
        back = true;
	}
}
