package ca.ottawaparking;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
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
		
		Bundle extras = getIntent().getExtras();
		double longitude;
		double latitude;
		String adjacent;
		System.out.println(extras);
		
		if(extras != null){
			longitude = getIntent().getExtras().getDouble("longitude");
			latitude = getIntent().getExtras().getDouble("latitude");
			adjacent = getIntent().getExtras().getString("location");
		}else{
			longitude = 0;
			latitude = 0;
			adjacent = "error";
		}
		
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(resultCode == ConnectionResult.SUCCESS){
			setContentView(R.layout.mapmarker);
			GoogleMap map = ((MapFragment) getFragmentManager()
	                .findFragmentById(R.id.map)).getMap();

	        LatLng ott = new LatLng(latitude, longitude);
	        
	        if(map != null){
		        map.setMyLocationEnabled(true);
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 13));
		
		        map.addMarker(new MarkerOptions()
		                .title("Destination")
		                .snippet(adjacent + "")
		                .position(ott));
	        }
		}else{
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 69);
			 
			if(dialog != null)
	        {
	            dialog.show();                
	        }
	        else
	        {
	            showOkDialogWithText(this, "Something went wrong. Please make sure that you have the Play Store installed. Contact developer with details if this persists.");
	        }
		}
	}
	
	public static void showOkDialogWithText(Context context, String messageText)
	{
	    Builder builder = new AlertDialog.Builder(context);
	    builder.setMessage(messageText);
	    builder.setCancelable(true);
	    builder.setPositiveButton("OK", null);
	    AlertDialog dialog = builder.create();
	    dialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
