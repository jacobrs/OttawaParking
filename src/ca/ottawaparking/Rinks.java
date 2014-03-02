package ca.ottawaparking;

import android.content.Context;

public class Rinks {
	public Rinks(Context context){
		this.context = context;
	}
	
	public void set_park_id(int set) {
		park_id = set;
	}
	
	public int get_park_id() {
		if(park_id != -1)
			return park_id;
		return -1;
	}
	
	public void set_rink_id(int set) {
		rink_id = set;
	}
	
	public int get_rink_id() {
		if(rink_id != -1)
			return rink_id;
		return -1;
	}
	
	public void set_park_name(String set) {
		park_name = set;
	}
	
	public String get_park_name() {
		if(park_name != null)
			return park_name;
		return null;
	}
	
	public void set_address(String set) {
		address = set;
	}
	
	public String get_address() {
		if(address != null)
			return address;
		return null;
	}
	
	public void set_ward(int set) {
		ward = set;
	}
	
	public int get_ward() {
		if(ward != -1)
			return ward;
		return -1;
	}
	
	public void set_alternate_name(String set) {
		alternate_name = set;
	}
	
	public String get_alternate_name() {
		if(alternate_name != null)
			return alternate_name;
		return null;
	}
	
	public void set_cross_street(String set) {
		cross_street = set;
	}
	
	public String get_cross_street() {
		if(cross_street != null)
			return cross_street;
		return null;
	}
	
	public void set_facility(String set) {
		facility = set;
	}
	
	public String get_facility() {
		if(facility != null)
			return facility;
		return null;
	}
	
	public void set_public_change_space(String set) {
		public_change_space = set;
	}
	
	public String get_public_change_space() {
		if(public_change_space != null)
			return public_change_space;
		return null;
	}
	
	public void set_boards(String set) {
		boards = set;
	}
	
	public String get_boards() {
		if(boards != null)
			return boards;
		return null;
	}
	
	public void set_boards_type(String set) {
		boards_type = set;
	}
	
	public String get_boards_type() {
		if(boards_type != null)
			return boards_type;
		return null;
	}
	
	public void set_toilets(String set) {
		toilets = set;
	}
	
	public String get_toilets() {
		if(toilets != null)
			return toilets;
		return null;
	}
	
	public void set_lights_type(String set) {
		lights_type = set;
	}
	
	public String get_lights_type() {
		if(lights_type != null)
			return lights_type;
		return null;
	}
	
	public void set_rink_type(String set) {
		rink_type = set;
	}
	
	public String get_rink_type() {
		if(rink_type != null)
			return rink_type;
		return null;
	}
	
	public void set_supervision(String set) {
		supervision = set;
	}
	
	public String get_supervision() {
		if(supervision != null)
			return supervision;
		return null;
	}
	
	public void set_ice_maintenance(String set) {
		ice_maintenance = set;
	}
	
	public String get_ice_maintenance() {
		if(ice_maintenance != null)
			return ice_maintenance;
		return null;
	}
	
	public void set_latitude(double set){
		lat = set;
	}
	
	public double get_latitude(){
		if(lat != 0)
			return lat;
		return 0;
	}
	
	public void set_longitude(double set){
		lon = set;
	}
	
	public double get_longitude(){
		if(lon != 0)
			return lon;
		return 0;
	}
	
	public double getDist(double latin, double longin){
		// Calculates the distance between the current bike stop and another coordinate
		return (double)(Math.sqrt(Math.pow((longin-lon),2) + Math.pow((latin - lat), 2)));
	}
	
	private
		Context context;
		int park_id = -1;
		int rink_id = -1;
		String park_name = null;
		String address = null;
		int ward = -1;
		String alternate_name = null;
		String cross_street = null;
		String facility = null;
		String public_change_space = null;
		String boards = null;
		String boards_type = null;
		String toilets = null;
		String lights_type = null;
		String rink_type = null;
		String supervision = null;
		String ice_maintenance = null;
		double lat = 0;
		double lon = 0;
}
