package ca.ottawaparking;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class IndividualMapMarker extends FragmentActivity{
	//double latitude = getIntent().getExtras().getFloat("latitude");
	//String adjacent = getIntent().getExtras().getString("adjacent");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapmarker);
		
		double longitude = getIntent().getExtras().getFloat("longitude");
		double latitude = getIntent().getExtras().getFloat("latitude");
		String adjacent = getIntent().getExtras().getString("location");
		
		GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng ott = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 13));

        map.addMarker(new MarkerOptions()
                .title("Destination")
                .snippet(adjacent + "")
                .position(ott));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
