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
import android.widget.Button;
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
	GoogleMap map;
	GoogleMap mapall;
	ArrayList<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carmenu);
		final Context context = this;
		ParseCsv<Car> parsedCars = new ParseCsv<Car>(this, "parking.csv");
		ourStack = parsedCars.parseCarFile();
		Toast.makeText(this, "Ordered by nearest to furthest", Toast.LENGTH_LONG).show();
		// Get location
		LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		// Check if a location provider is available and valid
		if(provider != null){
			Location lastknownloc = locationManager.getLastKnownLocation(provider);
			if(lastknownloc != null){
				// next line makes you Ottawanian
				// lastknownloc.setLatitude((double)45.214);lastknownloc.setLongitude((double)-75.6919);
				ourStack.CarSort(lastknownloc);
				final int points = 4;
				int count = 0;
				
				JStack<Car> tmpStack = new JStack<Car>(ourStack.getTopIndex());
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
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ott, 13));
		        
		        while(!tmpStack.is_empty()){
					Car filler = tmpStack.pop();
		        	LatLng fill = new LatLng(filler.getLat(), filler.getLong());
					map.addMarker(new MarkerOptions()
	                .title(filler.getAddress())
	                .snippet("Parking Lot")
	                //.anchor((float)filler.get_latitude(), (float)filler.get_longitude())
	                .position(fill));
				}
		        JStack<Car> tmpStackAll = new JStack<Car>(ourStack.getTopIndex());
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
				mapall = ((MapFragment) getFragmentManager()
		                .findFragmentById(R.id.map)).getMap();
				LatLng ottall = new LatLng(45.4214, -75.6919);
		        mapall.setMyLocationEnabled(false);
		        mapall.moveCamera(CameraUpdateFactory.newLatLngZoom(ottall, 10));
				
		        while(!tmpStackAll.is_empty()){
					Car filler = tmpStackAll.pop();
		        	LatLng fill = new LatLng(filler.getLat(), filler.getLong());
					mapall.addMarker(new MarkerOptions()
	                .title(filler.getAddress())
	                .snippet("Parking Lot")
	                .position(fill));
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
                  /* 
                  Toast.makeText(getApplicationContext(),
                     "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                     .show();
                  */
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
		th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				System.out.println(tabId);
				if(tabId == "tag2"){
					View tmp = (View)findViewById(R.id.nearest);
					tmp.setVisibility(View.VISIBLE);
					View tmp2 = (View)findViewById(R.id.map);
					tmp2.setVisibility(View.INVISIBLE);
				}
				else if(tabId == "tag3"){
					View tmp = (View)findViewById(R.id.nearest);
					tmp.setVisibility(View.INVISIBLE);
					View tmp2 = (View)findViewById(R.id.map);
					tmp2.setVisibility(View.VISIBLE);
				}
			}
		});
		
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
						//lastknownloc.setLatitude((double)45.214);
						//lastknownloc.setLongitude((double)-75.6919);
						ourStack.CarSort(lastknownloc);
						final int points = 4;
						int count = 0;
						JStack<Car> tmpStack = new JStack<Car>(ourStack.getTopIndex());
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
							Car filler = tmpStack.pop();
				        	LatLng fill = new LatLng(filler.getLat(), filler.getLong());
							map.addMarker(new MarkerOptions()
			                .title(filler.getAddress())
			                .snippet("Parking Lot")
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
				        list = new ArrayList<String>(Arrays.asList(values));
				        adapter = new ArrayAdapter<String>(CarMenu.this,
				                R.layout.listlayout, R.id.txtIcon, list);
				       
				        // Assign adapter to ListView
				        listView.setAdapter(adapter);
				        
				     // ListView Item Click Listener
				        listView.setOnItemClickListener(new OnItemClickListener() {
				        	
				              @Override
				              public void onItemClick(AdapterView<?> parent, View view,
				                 int position, long id) {
				                  // Show Alert 
				                  /* 
				                  Toast.makeText(getApplicationContext(),
				                     "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
				                     .show();
				                  */
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
