/*
 * Date: March 27th 2014
 * Authors: Jacob Gagne, Alex Genio, George Grafos and Ben Barault
 * Main function for the Ottawa Parking App
 * Copyright (C) 2014
 */
package ca.ottawaparking;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
