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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	//public JStack<String> newstack;
	public int buttonClicked; //determines which menu to open
	Button bikeActivity;
	Button carActivity;
	Button stubActivity;
	Button about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//newstack = new JStack<String>(4);
		//String tmp = newstack.is_full()+"";
		bikeActivity = (Button)findViewById(R.id.btnBike);
		carActivity = (Button)findViewById(R.id.btnCar);
		stubActivity = (Button)findViewById(R.id.btnStub);
		about = (Button)findViewById(R.id.btnAbout);
		// Toast.makeText(this, "The Dist is:"+test.getDist(-4, 4), Toast.LENGTH_LONG).show();
		System.out.println(bikeActivity);
		System.out.println(carActivity);
		System.out.println(stubActivity);
		System.out.println(about);
		
		bikeActivity.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        switch(event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		            bikeActivity.setBackgroundResource(R.drawable.bikebuttonpressed);
		            Intent openBikeMenu = new Intent(Main.this, LoadingScreen.class);
		            openBikeMenu.putExtra("buttonClicked", 0);
		            startActivity(openBikeMenu);
		            return true;
		        case MotionEvent.ACTION_UP:
		        	bikeActivity.setBackgroundResource(R.drawable.bikebutton);
		        	return true;
		        }
		        return false;
		    }
		});
		
		carActivity.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        switch(event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		        	carActivity.setBackgroundResource(R.drawable.carbuttonpressed);
		        	Intent openCarMenu = new Intent(Main.this, LoadingScreen.class);
		            openCarMenu.putExtra("buttonClicked", 1);
		            startActivity(openCarMenu);
		        	return true;
		        case MotionEvent.ACTION_UP:
		        	carActivity.setBackgroundResource(R.drawable.carbutton);
		        	return true;
		        }
		        return false;
		    }
		});

		stubActivity.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        switch(event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		        	stubActivity.setBackgroundResource(R.drawable.hockeybuttonpressed);
		        	Intent openHockeyMenu = new Intent(Main.this, LoadingScreen.class);
		            openHockeyMenu.putExtra("buttonClicked", 2);
		            startActivity(openHockeyMenu);
		        	return true;
		        case MotionEvent.ACTION_UP:
		        	stubActivity.setBackgroundResource(R.drawable.hockeybutton);
		        	return true;
		        }
		        return false;
		    }
		});
		
		about.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        switch(event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		        	about.setBackgroundResource(R.drawable.aboutbuttonpressed);
		        	Intent openAbout = new Intent("ca.ottawaparking.ABOUT");
		            startActivity(openAbout);
		        	return true;
		        case MotionEvent.ACTION_UP:
		        	about.setBackgroundResource(R.drawable.aboutbutton);
		        	return true;
		        }
		        return false;
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
