package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
	
	//This function will read data from a file and return as a list
	public static List<String[]> read(String file){
		List<String[]> data = new ArrayList<String[]>();
		
		String dataRow;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((dataRow = br.readLine()) != null) {
				String[] dataRecord = dataRow.split(",");
				data.add(dataRecord);
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read file");
			e.printStackTrace();
		}
		
		return data;
	}

}
