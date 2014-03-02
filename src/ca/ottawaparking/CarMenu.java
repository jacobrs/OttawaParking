package ca.ottawaparking;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class CarMenu extends FragmentActivity{
	ListView listView;
	// handles the ListView data
	ArrayAdapter<String> adapter;
	JStack<Car> ourStack = new JStack<Car>(1000);
	int amtIteration = 0;
	final int AMOUNT_OF_VIEWS = 15;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carmenu);
		Context context = this;
		ParseCsv<Car> parsedCars = new ParseCsv<Car>(this, "parking.csv");
		ourStack = parsedCars.parseCarFile();
		
		// Get location
		LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		// Check if a location provider is available and valid
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
			}else Toast.makeText(this, "Location Unavailable, check settings", Toast.LENGTH_LONG).show();
		}else Toast.makeText(this, "Unable to find suitable Provider", Toast.LENGTH_LONG).show();
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
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
     // Defined Array values to show in ListView
        String[] values = new String[AMOUNT_OF_VIEWS+1];
        
        int counter = 0;
        
        for(; counter < AMOUNT_OF_VIEWS; counter++){
        	values[counter] = ourStack.getElement(ourStack.getTopIndex() - counter).getAddress();
        	System.out.println(values[counter]);
        }
        amtIteration++;
        
        values[AMOUNT_OF_VIEWS] = "View More";
        
     // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
       
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {
        	
              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
            	// ListView Clicked item index
                  int itemPosition     = position;
                  
                  // ListView Clicked item value
                  String  itemValue    = (String) listView.getItemAtPosition(position);
                     
                   // Show Alert 
                   Toast.makeText(getApplicationContext(),
                     "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                     .show();
                   
                   if(position%(AMOUNT_OF_VIEWS*amtIteration) == 0 && position != 0 && position < ourStack.getTopIndex()){
                   	list.remove(position);
                   	adapter.notifyDataSetChanged();
                   	
                   	String[] newValues = new String[AMOUNT_OF_VIEWS+1];
                   	int newCounter = 0;
                   	
                   	for(; newCounter < AMOUNT_OF_VIEWS && (position + newCounter) < ourStack.getTopIndex(); newCounter++){
                		newValues[newCounter] = ourStack.getElement(ourStack.getTopIndex() - ((amtIteration*AMOUNT_OF_VIEWS) + newCounter)).getAddress();
                		list.add((amtIteration*AMOUNT_OF_VIEWS)+newCounter, newValues[newCounter]);
                	}
                	
                   	if(!(position+newCounter >= ourStack.getTopIndex())){
                		newValues[newCounter] = "View More";
                		list.add((amtIteration*AMOUNT_OF_VIEWS)+newCounter, newValues[newCounter]);
                	}
                	amtIteration++;
                	adapter.notifyDataSetChanged();
                }else if(!(position%(AMOUNT_OF_VIEWS*amtIteration) == 0) || position == 0){
                	//start new fragment activity
                	Intent openMapMarker = new Intent(CarMenu.this, IndividualMapMarker.class);
    				openMapMarker.putExtra("longitude", ourStack.getElement(ourStack.getTopIndex() - (position)).getLong());
    				openMapMarker.putExtra("latitude", ourStack.getElement(ourStack.getTopIndex() - (position)).getLat());
    				openMapMarker.putExtra("location", ourStack.getElement(ourStack.getTopIndex() - (position)).getAddress());
    				startActivity(openMapMarker);
                }
             }
         }); 
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
