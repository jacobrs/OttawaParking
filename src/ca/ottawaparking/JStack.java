 /**
 * 
 */
package ca.ottawaparking;

import android.location.Location;

/**
 * @author Jacob Gagne
 * @description This is the stack template to work with different locations
 * 				and also allow the implementation with any objects
 * 
 * 	=========================================
 * 	CATCHES - NOTE
 * 	=========================================
 * 	Most functions manipulating the stack are protected by throwbacks.
 * 	When performing these and to avoid bugs place inside a try and catch.
 * 	The exception to catch is "NegativeArraySizeException"
 */

public class JStack<E>{
	private int top;
	private int size;
	private E[] items;
	
	public String printBikeStack(Location curr){
		String out = "";
		for(int n = 0; n < top-1; n++){
			Bike tmp = (Bike)items[n];
			out += ""+tmp.getDist(curr.getLatitude(), curr.getLongitude())+"\n";
		}
		return out;
	}
	
	public JStack(int insize){
			size = insize;
			top = -1;
			items = (E[]) new Object[size+1];
		}
		// This function returns the element on top of the stack
	public E top(){
			if(top > -1){
				return items[top];
			}else
				throw new NegativeArraySizeException();
		}
		// This function returns the amount of elems in the stack
	public int getTopIndex(){
		return top;
	}
		// This function returns the element on top of the stack and removes it from the stack
	public E pop(){
			if(top > -1){
				return items[top--];
			}else
				throw new NegativeArraySizeException();
		}
		// This function tells the user if the stack is empty
	public boolean is_empty(){
			if(top > -1)
				return false;
			else
				return true;
		}
		// This function tells the user if the stack is full
	public boolean is_full(){
			if(top >= size)
				return true;
			else
				return false;
		}
		// This function adds an element to the stack
	public void push(E item){
			if(top < size){
				items[++top] = item;
			}else
				throw new NegativeArraySizeException();
		}
		// Function returns the size of the stack
	public int getSize(){
		return size;
	}
		// WARNING: ONLY USE IF STACK OF BIKES
	public boolean BikeSort(Location curr){
		int j;
		boolean flag = true;
		E temp;
		while(flag){
			flag = false;
			for(j = 0; j < top; j++){
				Bike low = (Bike)items[j];
				Bike high = (Bike)items[j+1];
				if(low.getDist(curr.getLatitude(), curr.getLongitude()) < high.getDist(curr.getLatitude(), curr.getLongitude())){	
					temp = items[j];
					items[j] = items[j+1];
					items[j+1] = temp;
					flag = true;
				}
			}
		}
		return true;
	}
		// WARNING: ONLY USE IF STACK OF CARS
	public boolean CarSort(Location curr){
		int j;
		boolean flag = true;
		E temp;
		while(flag){
			flag = false;
			for(j = 0; j < top; j++){
				Car low = (Car)items[j];
				Car high = (Car)items[j+1];
				if(low.getDist((float)curr.getLatitude(), (float)curr.getLongitude()) < high.getDist((float)curr.getLatitude(), (float)curr.getLongitude())){	
					temp = items[j];
					items[j] = items[j+1];
					items[j+1] = temp;
					flag = true;
				}
			}
		}
		return true;
	}
		// WARNING: ONLY USE IF STACK OF RINKS
	public boolean RinkSort(Location curr){
		int j;
		boolean flag = true;
		E temp;
		while(flag){
			flag = false;
			for(j = 0; j < top; j++){
				Rinks low = (Rinks)items[j];
				Rinks high = (Rinks)items[j+1];
				if(low.getDist((float)curr.getLatitude(), (float)curr.getLongitude()) < high.getDist((float)curr.getLatitude(), (float)curr.getLongitude())){	
					temp = items[j];
					items[j] = items[j+1];
					items[j+1] = temp;
					flag = true;
				}
			}
		}
		return true;
	}
	
	public E getElement(int location){
		return items[location];
	}
}
