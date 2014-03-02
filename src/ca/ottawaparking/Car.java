package ca.ottawaparking;

import android.app.Application;
import android.content.Context;

public class Car extends Application{
	
	public Car(Context context){
		this.context = context;
	}
	
	public void setObjId(int id){
		ObjId = id;		
	}
	
	public void setParkId(int id){
		ParkId = id;		
	}
	
	public void setFacId(int id){
		FacilityId = id;		
	}
	
	public void setAddress(String lineArr){
		address = lineArr;		
	}
	
	public void setSurface(String surf){
		surface = surf;		
	}
	
	public void setCapacity(int cap){
		capacity = cap;		
	}
	
	public void setHandicap(int hand){
		handicap = hand;		
	}
	
	public void setPay(char c){
		pay = c;		
	}
	
	public void setPayType(String ptype){
		payType = ptype;		
	}
	
	public void setLight(char light){
		lighting = light;		
	}
	
	public void setFence(char fence){
		fenced = fence;		
	}
	
	public void setLatitude(double x){
		lat = x;		
	}
	
	public void setLongitude(double y){
		lon = y;		
	}
	
	
	public int getObjId(){
		if(ObjId != -1)
			return ObjId;
		return -1;		
	}
	
	public int getParkId(){
		if(ParkId != -1)
			return ParkId;
		return -1;
	}
	
	public int getFacId(){
		if(FacilityId != -1)
			return FacilityId;
		return -1;
	}
	
	public String getAddress(){
		if(address != null)
			return address;
		return null;
	}
	
	public String getSurface(){
		if(surface != null)
			return surface;
		return null;
	}
	
	public String getPayType(){
		if(payType != null)
			return payType;
		return null;
	}
	
	public int getCapacity(){
		if(capacity != -1)
			return capacity;
		return -1;
	}
	
	public int getHandicap(){
		if(handicap != -1)
			return handicap;
		return -1;
	}
	
	public char getPay(){
		if(pay != 0)
			return pay;
		return 0;
	}
	
	public char getLight(){
		if(lighting != 0)
			return lighting;
		return 0;
	}
	
	public char getFence(){
		if(fenced != 0)
			return fenced;
		return 0;
	}
	
	public double getLat(){
		if(lat != 0)
			return lat;
		return 0;
	}
	
	public double getLong(){
		if(lon != 0)
			return lon;
		return 0;
	}
	
	public double getDist(double latin, double longin){
		// Calculates the distance between the current bike stop and another coordinate
		return (Math.sqrt(Math.pow((longin-lon),2) + Math.pow((latin - lat), 2)));
	}
	
	private
		Context context;
		int ObjId = -1;
		int ParkId = -1;
		int FacilityId = -1;
		String address = null;
		String surface = null;
		int capacity = -1;
		int handicap = -1;
		char pay = 0;
		String payType = null;
		char lighting = 0;
		char fenced = 0;
		double lat = 0;
		double lon = 0;
}
