package ca.ottawaparking;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CarMenu extends FragmentActivity{
	//public Car[] testCar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carmenu);
		
		// Get location
		LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		if(provider != null){
			Location lastknownloc = locationManager.getLastKnownLocation(provider);
			if(lastknownloc != null){
				GoogleMap map = ((MapFragment) getFragmentManager()
		                .findFragmentById(R.id.map)).getMap();
		
		        LatLng ott = new LatLng(lastknownloc.getLatitude(), lastknownloc.getLongitude());
		        map.setMyLocationEnabled(true);
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 13));
		
		        map.addMarker(new MarkerOptions()
		                .title("You")
		                .snippet("You are here ;)")
		                .position(ott));
			}
		}
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
