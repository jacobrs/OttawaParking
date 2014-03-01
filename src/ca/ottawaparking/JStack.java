 /**
 * 
 */
package ca.ottawaparking;
import java.lang.Throwable;

/**
 * @author Jacob Gagne
 * @description This is the stack template to work with different locations
 * 				and also allow the implementation with any objects
 */

public class JStack<E>{
	private int top;
	private int size;
	private E[] items;
	
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
			}
		}
	public int getSize(){
		return size;
	}
}
