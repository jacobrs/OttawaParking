/*
 * Date: March 27th 2014
 * Authors: Jacob Gagne, Alex Genio, George Grafos and Ben Barault
 * Main function for the Ottawa Parking App
 * Copyright (C) 2014
 */
package ca.ottawaparking;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends Activity {
	//public JStack<String> newstack;
	public int buttonClicked; //determines which menu to open
	Button bikeActivity;
	Button carActivity;
	Button stubActivity;
	Button about;
	AdView ad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bikeActivity = (Button)findViewById(R.id.btnBike);
		carActivity = (Button)findViewById(R.id.btnCar);
		stubActivity = (Button)findViewById(R.id.btnStub);
		about = (Button)findViewById(R.id.btnAbout);
		ad = (AdView)findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
	    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	    .addTestDevice("ca-app-pub-2762904834767478/7975367349")
	    .build();
		
		ad.loadAd(adRequest);
		
		if(!isNetworkAvailable()){
			new AlertDialog.Builder(this)
	        .setTitle("No Internet Connection")
	        .setMessage("Internet Connection is a core part of this application, "
	        		+ "Ottawa Navigator was unable to connect to the internet. "
	        		+ "Please review your settings or use a limited version "
	        		+ "of this application")
	        .setNegativeButton(android.R.string.ok, null).create().show();
		}
		bikeActivity.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        switch(event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		            bikeActivity.setBackgroundResource(R.drawable.bikebuttonpressed);
		            if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
		            	Intent openBikeMenu = new Intent(Main.this, LoadingScreen.class);
		            	openBikeMenu.putExtra("buttonClicked", 0);
		            	startActivity(openBikeMenu);
		            }else{
		            	Intent openBikeMenu = new Intent(Main.this, LoadingScreen.class);
		            	openBikeMenu.putExtra("buttonClicked", 3);
		            	Toast.makeText(Main.this, "Android version less than 4.1.2 therefore functionality will be limited", Toast.LENGTH_LONG).show();
		            	startActivity(openBikeMenu);
		            }
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
		        	if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
			        	Intent openCarMenu = new Intent(Main.this, LoadingScreen.class);
			            openCarMenu.putExtra("buttonClicked", 1);
			            startActivity(openCarMenu);
		        	}else{
		        		Intent openCarMenu = new Intent(Main.this, LoadingScreen.class);
		        		openCarMenu.putExtra("buttonClicked", 4);
		        		Toast.makeText(Main.this, "Android version less than 4.1.2 therefore functionality will be limited", Toast.LENGTH_LONG).show();
		            	startActivity(openCarMenu);
		        	}
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
		        	if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
			        	Intent openHockeyMenu = new Intent(Main.this, LoadingScreen.class);
			            openHockeyMenu.putExtra("buttonClicked", 2);
			            startActivity(openHockeyMenu);
		        	}else{
		        		Intent openStubMenu = new Intent(Main.this, LoadingScreen.class);
		        		openStubMenu.putExtra("buttonClicked", 5);
		        		Toast.makeText(Main.this, "Android version less than 4.1.2 therefore functionality will be limited", Toast.LENGTH_LONG).show();
		            	startActivity(openStubMenu);
		        	}
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
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

}
