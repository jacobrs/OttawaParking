package ca.ottawaparking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class BikeMenu extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bikemenu);
		
		/*Bike[] ourBikes = new Bike[300];     Example code
		
		for(int i = 0; i < 300; i++)
			ourBikes[i] = new Bike(this);
		
		ourBikes[0].set_latitude(75);
		ourBikes[0].set_longitude(75);
		System.out.println("latitude: " + ourBikes[0].get_latitude());
		System.out.println("longitude: " + ourBikes[0].get_longitude());*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
