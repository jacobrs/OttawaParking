package ca.ottawaparking;

import android.content.Context;

public class Bike{
	public Bike(Context context){
		   this.context = context;
		   lat = 0;
		   lon = 0;
		}
	
	public void set_post_id(int set){
		post_id = set;
	}
	
	public int get_post_id(){
		if(post_id != -1)
			return post_id;
		return -1;
	}
	
	public void set_mid_block_id(int set){
		mid_block_id = set;
	}
	
	public int get_mid_block_id(){
		if(mid_block_id != -1){
			return mid_block_id;
		}
		return -1;
	}
		
	public void set_street_1(String set){
		street_1 = set;
	}
	
	public String get_street_1(){
		if(street_1 != null)
			return street_1;
		return null;
	}
	
	public void set_street_2(String set){
		street_2 = set;
	}
	
	public String get_street_2(){
		if(street_2 != null)
			return street_2;
		return null;
	}
	
	public void set_street_3(String set){
		street_3 = set;
	}
	
	public String get_street_3(){
		if(street_3 != null)
			return street_3;
		return null;
	}
	
	public void set_side(String set){
		Side = set;
	}
	
	public String get_side(){
		if(Side != null)
			return Side;
		return null;
	}
	
	public void set_adjacent(String set){
		adjacent_to = set;
	}
	
	public String get_adjacent(){
		if(adjacent_to != null)
			return adjacent_to;
		return null;
	}
	
	public void set_latitude(float set){
		lat = set;
	}
	
	public float get_latitude(){
		if(lat != 0)
			return lat;
		return 0;
	}
	
	public void set_longitude(float set){
		lon = set;
	}
	
	public float get_longitude(){
		if(lon != 0)
			return lon;
		return 0;
	}
	
	public void set_core(String set){
		core = set;
	}
	
	public String get_core(){
		if(core != null)
			return core;
		return null;
	}
	
	public void set_notes(String set){
		notes = set;
	}
	
	public String get_notes(){
		if(notes != null)
			return notes;
		return null;
	}
	
	public float getDist(double latin, double longin){
		// Calculates the distance between the current bike stop and another coordinate
		return (float)(Math.sqrt(Math.pow((longin-lon),2) + Math.pow((latin - lat), 2)));
	}
	
	private
		Context context;
		int post_id = -1;
		int mid_block_id = -1;
		String street_1 = null;
		String street_2 = null;
		String street_3 = null;
		String Side = null;
		String adjacent_to = null;
		float lat = 0;
		float lon = 0;
		String core = null;
		String notes = null;
}
