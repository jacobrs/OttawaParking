package ca.ottawaparking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CarMenu extends Activity{
	//public Car[] testCar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carmenu);
		
		/*testCar = new Car[351];
		
		for(int i = 0; i < 350; i++)
			testCar[i] = new Car(this);
		
		testCar[0].setFacId(25);
		System.out.println("Facility Id: " + testCar[0].getFacId());*/
		
		
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
