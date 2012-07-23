/********************************************************************************************************************************************************
Program Name			:	Package.java
Description				:	A Package Model class that is the Model for Package record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-8-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Event()
						: 	Event(String, String, String, String, String, String)
						:	InitiateEvent() : Boolean
						:	retrieveTimings(String, String) : ArrayList<String>
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Controller.MyCalendar;
import Controller.MySQLController;


public class Event {

	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Ballroom data;								//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String eventID;
	private String packageID;
	private String userName;
	private String eventStatus;
	private String eventDate;
	private String eventTime;
	private String eventName;
	private String eventDescription;
	

	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public Event(){}
	public Event(String packageID, String userName, String eventStatus, String eventDate, String eventTime, String eventName){
		this.packageID=packageID;
		this.userName=userName;
		this.eventStatus=eventStatus;
		this.eventDate=eventDate;
		this.eventTime=eventTime;
		this.eventName=eventName;
	}
	

	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	
	/********************************************************
	 * Method Name 		: InitiateEvent()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To create a new event and purchase 
	 * 					  record
	 *******************************************************/
	public boolean InitiateEvent(){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Event` (`packageID`, `userName`, `eventStatus`, `eventDate`, `eventTime`, `eventName`, `eventDescription`)";
		sqlQuery +="VALUES ("+this.packageID+", '"+this.userName+"', 'Pending', '"+this.eventDate+"', '"+this.eventTime+"', '"+this.eventName+"','"+this.eventDescription+"')";
		int i = 0;
		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed To Create Event Record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		//RETRIEVE THE CREATED EVENT ID
		sqlQuery="SELECT * FROM saharp5_adeel_school.Event;";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				this.eventID=rs.getString("eventID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve event Record");
		}
		finally{
		}
		
		
		//CREATE THE PURCHASE SUMMARY
		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Purchase_Summary` (`eventID`,`dateOfPurchase`) VALUES ('"+this.eventID+"','"+MyCalendar.formatDate(new GregorianCalendar())+"')"; 
		i = 0;
		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed To Create Purchase Record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	/********************************************************
	 * Method Name 		: retrieveTimings()
	 * Input Parameter 	: String, String
	 * Return 			: ArrayList<String>
	 * Purpose 			: To retrieve the timings by 
	 * 					  ballroom and date
	 *******************************************************/
	public ArrayList<String> retrieveTimings(String ballroomID, String Date){
		ArrayList<String> packagelist= new ArrayList<String>();
		String sqlQuery;
		
		sqlQuery="SELECT distinct packageID FROM saharp5_adeel_school.Package WHERE ballroomID="+ballroomID+"";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				packagelist.add(rs.getString("packageID"));
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve packageID Record");
		}
		finally{
		}
		

		ArrayList<String> Timelist= new ArrayList<String>();
		for(int i=0; i<packagelist.size();i++){
			sqlQuery = "SELECT * FROM saharp5_adeel_school.Event WHERE (`eventStatus`='Pending' AND `eventDate`='"+Date+"'  AND `packageID`="+packagelist.get(i)+")";
			
			try{
				rs = DB.readRequest(sqlQuery);
				while (rs.next()){
					Timelist.add(rs.getString("eventTime"));
			   }
			}
			catch (Exception e) {
				System.out.println("Failed to Retrieve Entertainment Record");
			}
			finally{
			}
		}
		
		return Timelist;
	}
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Ballroom getData() {
		return data;
	}
	public void setData(Ballroom data) {
		this.data = data;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getPackageID() {
		return packageID;
	}
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventDescription() {
		return eventDescription;
	}
}
