package ca.ottawaparking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.*;

public class BikeMenu extends FragmentActivity{
	
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
		
		Context context = this;
		ParseCsv<Bike> parsedBikes = new ParseCsv<Bike>(this, "bikeParking.csv");
		JStack<Bike> ourStack = new JStack<Bike>(1000);
		ourStack = parsedBikes.parseBikeFile();
		
		TabHost th = (TabHost)findViewById(R.id.tabhost);
		//automatically set up the basics
		th.setup();
		//have to do this, not sure of purpose
		TabSpec specs = th.newTabSpec("tag1");
		//this will hold the two buttons and text view in the
		//first layout
		specs.setContent(R.id.tab1);
		//this name will appear on actual tab
		specs.setIndicator("List View");
		th.addTab(specs);
		
		
		specs = th.newTabSpec("tag2");
		//this will hold the two buttons and text view in the
		//first layout
		specs.setContent(R.id.tab2);
		//this name will appear on actual tab
		specs.setIndicator("Nearest");
		th.addTab(specs);
		
		
		specs = th.newTabSpec("tag3");
		//this will hold the two buttons and text view in the
		//first layout
		specs.setContent(R.id.tab3);
		//this name will appear on actual tab
		specs.setIndicator("View All");
		th.addTab(specs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
