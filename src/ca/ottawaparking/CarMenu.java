package ca.ottawaparking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
