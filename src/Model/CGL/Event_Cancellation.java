package Model.CGL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MyCalendar;
import Controller.MySQLController;
import Model.Event;

public class Event_Cancellation {
	private static MySQLController DB = new MySQLController();
	
public boolean UPDATES_CANCELLATION_DETAILS(String eventName,String reason){
		boolean status=false;
		MyCalendar m1 = new MyCalendar();
		ResultSet rs = null;
		
		String dbQuery;
		int i=0;
		
		dbQuery = "INSERT INTO `saharp5_adeel_school`.`Event_Cancellation` (`eventID`, `cancellationDate`, `cancellationReason`) VALUES ((SELECT eventID FROM Event WHERE eventName='"+eventName+"'), '"+m1.currentDate()+"', '"+reason+"')";
					
		
			try{
				i=DB.updateRequest(dbQuery);
				
			}
			
			catch(Exception ex){
				System.out.println("Failed to add record into cancellation");
			}
			
			finally{
				
			}
			
			if(i==1){
				status=true;
			
			}
			
			
			return status;
		
			
		}


/********************************************************
 * Method Name 		: UPDATE_EVENT_STATUS
 * Input Parameter 	: String eventName,String eventStatus
 * Return 			: BOOLEAN
 * Purpose 			: TO UPDATE THE EVENT STATUS BASED
 * 					  ON THE EVENT RECORD
 *******************************************************/
		
		public boolean UPDATE_EVENT_STATUS(String eventName,String eventStatus){
			
			boolean status=false;
			String dbQuery;
			
			int i=0;
			
			
			dbQuery = "UPDATE Event Set eventStatus='"+eventStatus+"'"+"WHERE eventName="+"'"+eventName+"'";

	try{
		i=DB.updateRequest(dbQuery);
	}

	catch(Exception ex){
		System.out.println("Failed to update event status");
				}
				
				finally{
					
				}
				
				if(i==1){
					status=true;
				
				}
				
				
				return status;
				
			}


}
