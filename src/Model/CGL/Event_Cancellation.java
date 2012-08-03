package Model.CGL;

import java.sql.ResultSet;
import Controller.MyCalendar;
import Controller.MySQLController;

public class Event_Cancellation {
	private int cancellationID;
	private int eventID;
	private String cancellationDate;
	private String cancellationReason;
	
	
	
	public int getCancellationID() {
		return cancellationID;
	}


	public void setCancellationID(int cancellationID) {
		this.cancellationID = cancellationID;
	}


	public int getEventID() {
		return eventID;
	}


	public void setEventID(int eventID) {
		this.eventID = eventID;
	}


	public String getCancellationDate() {
		return cancellationDate;
	}


	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}


	public String getCancellationReason() {
		return cancellationReason;
	}


	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}


	public static MySQLController getDB() {
		return DB;
	}


	public static void setDB(MySQLController dB) {
		DB = dB;
	}


	private static MySQLController DB = new MySQLController();
	
	/********************************************************
	 * Method Name 		: UPDATE_CANCELLATION_DETAILS
	 * Input Parameter 	: String eventName,String reason
	 * Return 			: BOOLEAN
	 * Purpose 			: TO ADD CANCELLATION DETAILS FOR
	 * 					  THE PARTICULAR EVENT	
	 *******************************************************/
	
public boolean UPDATES_CANCELLATION_DETAILS(String eventName,String reason){
		boolean status=false;
		MyCalendar m1 = new MyCalendar();
		@SuppressWarnings("unused")
		ResultSet rs = null;
		
		String dbQuery;
		int i=0;
		dbQuery = "INSERT INTO `saharp5_adeel_school`.`Event_Cancellation` (`eventID`, `cancellationDate`, `cancellationReason`) VALUES ((SELECT eventID FROM Event WHERE eventName='"+eventName+"'), '"+m1.currentDate()+"', '"+reason+"')";
		try
		{
			i=DB.updateRequest(dbQuery);
 		}
			
		catch(Exception ex)
		{
			System.out.println("Failed to add record into cancellation");
		}
					
		if(i==1)
		{
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
			try
			{
				i=DB.updateRequest(dbQuery);
			}

			catch(Exception ex)
			{
				System.out.println("Failed to update event status");
			}
				
			if(i==1)
			{
				status=true;
			}
								
				return status;
			}


}
