/*
 * Date: March 27th 2014
 * Authors: Jacob Gagne, Alex Genio, George Grafos and Ben Barault
 * Main function for the Ottawa Parking App
 * Copyright (C) 2014
 */
package ca.ottawaparking;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	//public JStack<String> newstack;
	public int buttonClicked; //determines which menu to open
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//newstack = new JStack<String>(4);
		//String tmp = newstack.is_full()+"";
		Button bikeActivity = (Button)findViewById(R.id.btnBike);
		Button carActivity = (Button)findViewById(R.id.btnCar);
		Button stubActivity = (Button)findViewById(R.id.btnStub);
		Button about = (Button)findViewById(R.id.btnAbout);
		// Toast.makeText(this, "The Dist is:"+test.getDist(-4, 4), Toast.LENGTH_LONG).show();
		bikeActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent openBikeMenu = new Intent("ca.ottawaparking.BIKE");
				Intent openBikeMenu = new Intent(Main.this, LoadingScreen.class);
				openBikeMenu.putExtra("buttonClicked", 0);
				startActivity(openBikeMenu);
			}
		});
		
		carActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openCarMenu = new Intent(Main.this, LoadingScreen.class);
				openCarMenu.putExtra("buttonClicked", 1);
				startActivity(openCarMenu);
			}
		});

		stubActivity.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openStubMenu = new Intent(Main.this, LoadingScreen.class);
				openStubMenu.putExtra("buttonClicked", 2);
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
	
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	        .setTitle("Really Exit?")
	        .setMessage("Are you sure you want to exit?")
	        .setNegativeButton(android.R.string.no, null)
	        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

	            @Override
				public void onClick(DialogInterface arg0, int arg1) {
	                Main.super.onBackPressed();
	                finish();
	                System.exit(1);
	            }
	        }).create().show();
	}

}
