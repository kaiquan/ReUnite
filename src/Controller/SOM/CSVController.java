/********************************************************************************************************************************************************
Program Name			:	CSVController.java
Description				:	A CSVController class that is the Controller for read and write CSV files
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	WriteFile(ArrayList<String[]>, String) : void
						: 	ReadFile(String) : ArrayList<String[]>     
********************************************************************************************************************************************************/
package Controller.SOM;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVController {
	/********************************************************
	 * Method Name		: WriteFile
	 * Input Parameter 	: ArrayList<String[]>, String
	 * Purpose 			: To create a csv file  
	 * Return			: void
	 *******************************************************/	
	public void WriteFile(ArrayList<String[]> data,String filePath) throws IOException{
		//this method writes the data into csv format
		CSVWriter writer = new CSVWriter(new FileWriter(filePath));
		//write the header
	    writer.writeNext(data.get(0));
	    //the actual writting
	    for(int i=1;i<data.size();i++){
	   	  writer.writeNext(data.get(i));
	     }
	    writer.close();
	} 
	    
	/********************************************************
	 * Method Name		: ReadFile
	 * Input Parameter 	: String
	 * Purpose 			: To create a csv file  
	 * Return			: ArrayList<String[]>
	 *******************************************************/	
	public ArrayList<String[]> readFile(String filePath) throws IOException{
		//this method reads from a csv file
		CSVReader reader= new CSVReader(new FileReader(filePath));
		ArrayList<String[]>data= new ArrayList<String[]>();
		String [] list= new String[1];
		list=reader.readNext();
		while(list!=null){
			data.add(list);
			list=null;
			list=reader.readNext();
		}
		return data;
	}
	
}

