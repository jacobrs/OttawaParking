/*
 * Date: March 27th 2014
 * Authors: Jacob Gagne, Alex Genio, George Grafos and Ben Barault
 * Main function for the Ottawa Parking App
 * Copyright (C) 2014
 */
package ca.ottawaparking;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bikeActivity = (Button)findViewById(R.id.btnBike);
		Button carActivity = (Button)findViewById(R.id.btnCar);
		Button stubActivity = (Button)findViewById(R.id.btnStub);
		Button about = (Button)findViewById(R.id.btnAbout);
		
		bikeActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openBikeMenu = new Intent("ca.ottawaparking.BIKE");
				startActivity(openBikeMenu);

			}
		});
		
		carActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCarMenu = new Intent("ca.ottawaparking.CAR");
				startActivity(openCarMenu);
				
			}
		});

		stubActivity.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openStubMenu = new Intent("ca.ottawaparking.STUB");
				startActivity(openStubMenu);
				
			}
		});
		
		about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openAbout = new Intent("ca.ottawaparking.ABOUT");
				startActivity(openAbout);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
