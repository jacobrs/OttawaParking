package ca.ottawaparking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class ParseCsv<E>{
	public ParseCsv(Context v, String filename){
		    context = v;
			fileName = filename;
	}
	
	public JStack<Bike> parseBikeFile(){
		JStack<Bike> ourArrays = new JStack<Bike>(1000);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("bikeParking.csv")));
			int counter = 0;
			String line;
			String[] lineArr;
			while((line = br.readLine())!=null){
				if(counter != 0){
					lineArr = line.split(",");
					if(!(lineArr.length >= 9))
						break;
					Bike bikeStop = new Bike(context);
					bikeStop.set_post_id(Integer.parseInt(lineArr[0]));
					bikeStop.set_mid_block_id(Integer.parseInt(lineArr[1]));
					bikeStop.set_street_1(lineArr[2]);
					bikeStop.set_street_2(lineArr[3]);
					bikeStop.set_street_3(lineArr[4]);
					bikeStop.set_side(lineArr[5]);
					bikeStop.set_adjacent(lineArr[6]);
					//System.out.println(lineArr[0]+" - "+lineArr[6].length());
					bikeStop.set_latitude(Float.parseFloat(lineArr[7]));
					bikeStop.set_longitude(Float.parseFloat(lineArr[8]));
					
					if(!ourArrays.is_full()){
						ourArrays.push(bikeStop);
					}
				}
				counter++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return ourArrays;
	}
	
	private
		Context context;
		String fileName;
}
