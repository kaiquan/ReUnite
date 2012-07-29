package Model.CGL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MyCalendar;
import Controller.MySQLController;
import Model.Event;

public class Event_Cancellation {
	private static MySQLController DB = new MySQLController();
	
public boolean UPDATES_CANCELLATION_DETAILS(String eventName){
		boolean status=false;
		MyCalendar m1 = new MyCalendar();
		ResultSet rs = null;
		
		String dbQuery;
		int i=0;
		
		dbQuery = "INSERT INTO Event_Cancellation VALUES((SELECT eventID FROM Event WHERE eventName='Great Reunion'),'2012-07-06','Waste of time')";
					
		
			try{
				i=DB.updateRequest(dbQuery);
			}
			
			catch(Exception ex){
				System.out.println("Failed to update cancellation");
			}
			
			finally{
				
			}
			
			if(i==1){
				status=true;
			
			}
			
			
			return status;
		
			
		}

	public static void main(String args[]){
		Event_Cancellation e1 = new Event_Cancellation();
		e1.UPDATES_CANCELLATION_DETAILS("Great Reunion");
	}

}
