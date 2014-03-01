package ca.ottawaparking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import android.content.*;
import android.view.View;

import android.app.Activity;
import android.app.Application;

public class Parse extends Activity {
	public static void main(String[] args) {
		String csvFile = "/Downloads/parking.csv";
		String line = "";
		String csvSplitBy = ",";
	 
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("parking.csv")));
			while ((line = br.readLine()) != null) {
				String[] storage = line.split(csvSplitBy);	 
				System.out.println("Parking [latitude= " + storage[11] 
	                                 + ", longitude=" + storage[12] + "]");
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
	}	
}
