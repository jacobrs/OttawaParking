package ca.ottawaparking;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
import android.content.*;

public class BikeMenuLower extends FragmentActivity{
	
	ListView listView;
	// handles the ListView data
	ArrayAdapter<String> adapter;
	JStack<Bike> ourStack = new JStack<Bike>(1000);
	int amtIteration = 0;
	final int AMOUNT_OF_VIEWS = 15;
	GoogleMap map;
	ArrayList<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bikemenulower);
		
		
		final Context context = this;
		ParseCsv<Bike> parsedBikes = new ParseCsv<Bike>(this, "bikeParking.csv");
		ourStack = parsedBikes.parseBikeFile();
		Toast.makeText(this, "Ordered by nearest to furthest", Toast.LENGTH_LONG).show();
		// Get location
		LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		// Check if a location provider is available and valid
		if(provider != null){
			Location lastknownloc = locationManager.getLastKnownLocation(provider);
			if(lastknownloc != null){
				ourStack.BikeSort(lastknownloc);
				//Toast.makeText(this, ourStack.printBikeStack(lastknownloc), Toast.LENGTH_LONG).show();
				
				//final double radius = 0.1;
				final int points = 4;
				int count = 0;
				JStack<Bike> tmpStack = new JStack<Bike>(ourStack.getTopIndex());
				int pointer = ourStack.getTopIndex();
				while((count < points) && (pointer > 0)){
					try{
						System.out.println(pointer);
						tmpStack.push(ourStack.getElement(pointer));
						pointer--;
						count++;
					}catch(NegativeArraySizeException e){
						pointer = -1;
						System.out.println("Stack Overflow");
					}
				}
				map = ((MapFragment) getFragmentManager()
		                .findFragmentById(R.id.nearest)).getMap();
		        LatLng ott = new LatLng(lastknownloc.getLatitude(), lastknownloc.getLongitude());
		        map.setMyLocationEnabled(true);
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 16));
				
		        while(!tmpStack.is_empty()){
					Bike filler = tmpStack.pop();
		        	LatLng fill = new LatLng(filler.get_latitude(), filler.get_longitude());
					map.addMarker(new MarkerOptions()
	                .title(filler.get_street_1())
	                .snippet(filler.get_notes())
	                //.anchor((float)filler.get_latitude(), (float)filler.get_longitude())
	                .position(fill));
				}
		        JStack<Bike> tmpStackAll = new JStack<Bike>(ourStack.getTopIndex());
		        int pointerAll = ourStack.getTopIndex();
				while(pointerAll >= 0){
					System.out.println(pointerAll);
					try{
						tmpStackAll.push(ourStack.getElement(pointerAll));
						pointerAll--;
					}catch(NegativeArraySizeException e){
						pointerAll = -1;
						System.out.println("Stack Overflow");
					}
				}
			}else {if(provider == "gps"){Toast.makeText(context, "Data is required to use GPS/Location", Toast.LENGTH_SHORT).show();
			}else{
			Toast.makeText(context, "Location Unavailable, Data is required to use GPS/Location", Toast.LENGTH_SHORT).show();}
		}
	}else
	{
		Toast.makeText(context, "Unable to find suitable Provider", Toast.LENGTH_SHORT).show();
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
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        // Defined Array values to show in ListView
        String[] values = new String[AMOUNT_OF_VIEWS+1];
        
        int counter = 0;
        
        for(; counter < AMOUNT_OF_VIEWS; counter++){
        	values[counter] = ourStack.getElement(ourStack.getTopIndex() - counter).get_adjacent();
        }
        amtIteration++;
        
        values[AMOUNT_OF_VIEWS] = "View More";
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        list = new ArrayList<String>(Arrays.asList(values));
        adapter = new ArrayAdapter<String>(this,
          R.layout.listlayout, R.id.txtIcon, list);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {
        	
              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                if(position%(AMOUNT_OF_VIEWS*amtIteration) == 0 && position != 0 && position < ourStack.getTopIndex()){
                	list.remove(position);
                	adapter.notifyDataSetChanged();
                	
                	String[] newValues = new String[AMOUNT_OF_VIEWS+1];
                	int newCounter = 0;
                	for(; newCounter < AMOUNT_OF_VIEWS && (position + newCounter) < ourStack.getTopIndex(); newCounter++){
                		newValues[newCounter] = ourStack.getElement(ourStack.getTopIndex() - ((amtIteration*AMOUNT_OF_VIEWS) + newCounter)).get_adjacent();
                		
                		if(newValues[newCounter].length() == 0){
                			newValues[newCounter] = "Around " + ourStack.getElement(ourStack.getTopIndex() - ((amtIteration*AMOUNT_OF_VIEWS) + newCounter)).get_street_1();
                		}
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
                	Intent openMapMarker = new Intent(BikeMenuLower.this, IndividualMapMarker.class);
    				openMapMarker.putExtra("longitude", ourStack.getElement(ourStack.getTopIndex() - (position)).get_longitude());
    				openMapMarker.putExtra("latitude", ourStack.getElement(ourStack.getTopIndex() - (position)).get_latitude());
    				openMapMarker.putExtra("location", ourStack.getElement(ourStack.getTopIndex() - (position)).get_adjacent());
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
		//=======================================================
		// RECAL LOCATION QUERYING TO FIND UP TO DATE ON CLICK ||
		//=======================================================
		
		Button ref = (Button)findViewById(R.id.nearestRefresh);
		ref.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Get location
				LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				String provider = locationManager.getBestProvider(criteria, true);
				// Check if a location provider is available and valid
				if(provider != null){
					Location lastknownloc = locationManager.getLastKnownLocation(provider);
					if(lastknownloc != null){
						map.clear();
						// next line makes you Ottawanian
						// lastknownloc.setLatitude((double)45.214);lastknownloc.setLongitude((double)-75.6919);
						ourStack.BikeSort(lastknownloc);
						final int points = 4;
						int count = 0;
						JStack<Bike> tmpStack = new JStack<Bike>(ourStack.getTopIndex());
						int pointer = ourStack.getTopIndex();
						while((count < points) && (pointer > 0)){
							try{
								tmpStack.push(ourStack.getElement(pointer));
								pointer--;
								count++;
							}catch(NegativeArraySizeException e){
								pointer = -1;
								System.out.println("Stack Overflow");
							}
						}
						map = ((MapFragment) getFragmentManager()
				                .findFragmentById(R.id.nearest)).getMap();
						//float ottawalat = (float) 45.4214;
						//float ottawalon = (float) -75.6919;
				        LatLng ott = new LatLng(lastknownloc.getLatitude(), lastknownloc.getLongitude());
						//LatLng ott = new LatLng(ottawalat, ottawalon);
				        map.setMyLocationEnabled(true);
				        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 13));
				        while(!tmpStack.is_empty()){
							Bike filler = tmpStack.pop();
				        	LatLng fill = new LatLng(filler.get_latitude(), filler.get_longitude());
							map.addMarker(new MarkerOptions()
			                .title(filler.get_street_1())
			                .snippet(filler.get_adjacent())
			                //.anchor((float)filler.get_latitude(), (float)filler.get_longitude())
			                .position(fill));
						}
				        amtIteration = 0;
						// Get ListView object from xml
				        listView = (ListView) findViewById(R.id.list);
				        // Defined Array values to show in ListView
				        String[] values = new String[AMOUNT_OF_VIEWS+1];
				        
				        int counter = 0;
				        
				        for(; counter < AMOUNT_OF_VIEWS; counter++){
				        	values[counter] = ourStack.getElement(ourStack.getTopIndex() - counter).get_adjacent();
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
				        adapter = new ArrayAdapter<String>(BikeMenuLower.this,
				                R.layout.listlayout, R.id.txtIcon, list);
				       
				        // Assign adapter to ListView
				        listView.setAdapter(adapter);
				        
				        // ListView Item Click Listener
				        listView.setOnItemClickListener(new OnItemClickListener() {
				        	
				              @Override
				              public void onItemClick(AdapterView<?> parent, View view,
				                 int position, long id) {
					              if(position%(AMOUNT_OF_VIEWS*amtIteration) == 0 && position != 0 && position < ourStack.getTopIndex()){
				                   	list.remove(position);
				                   	adapter.notifyDataSetChanged();
				                   	
				                   	String[] newValues = new String[AMOUNT_OF_VIEWS+1];
				                   	int newCounter = 0;
				                   	
				                   	for(; newCounter < AMOUNT_OF_VIEWS && (position + newCounter) < ourStack.getTopIndex(); newCounter++){
				                		newValues[newCounter] = ourStack.getElement(ourStack.getTopIndex() - ((amtIteration*AMOUNT_OF_VIEWS) + newCounter)).get_adjacent();
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
				                	Intent openMapMarker = new Intent(BikeMenuLower.this, IndividualMapMarker.class);
				    				openMapMarker.putExtra("longitude", ourStack.getElement(ourStack.getTopIndex() - (position)).get_longitude());
				    				openMapMarker.putExtra("latitude", ourStack.getElement(ourStack.getTopIndex() - (position)).get_latitude());
				    				openMapMarker.putExtra("location", ourStack.getElement(ourStack.getTopIndex() - (position)).get_adjacent());
				    				startActivity(openMapMarker);
				                }
				             }
				         });
					}else {if(provider == "gps"){Toast.makeText(context, "Data is required to use GPS/Location", Toast.LENGTH_SHORT).show();
					}else{
					Toast.makeText(context, "Location Unavailable, Data is required to use GPS/Location", Toast.LENGTH_SHORT).show();}
				}
			}else
			{
				Toast.makeText(context, "Unable to find suitable Provider", Toast.LENGTH_SHORT).show();
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}