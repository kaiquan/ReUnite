package rcm.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import rcm.controller.ConsolidateGuestListControl;
import rcm.controller.MySQLController;

import rcm.controller.MyCalendar;



public class Event {
	
	private static MySQLController DB = new MySQLController();
	
	private String eventDate;
	private String eventTitle;
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return ((eventDate.equals(((Event)obj).eventDate)) && (eventTitle.equals(((Event)obj).eventTitle)));
	}

	public Event(){}
	public Event(String eventTitle, String eventDate){this.eventTitle = eventTitle; this.eventDate = eventDate;}
	
	public ArrayList<Event> retrieveEventRecords(){
		
		MyCalendar m1 = new MyCalendar();
		ArrayList<Event> e1 = new ArrayList<Event>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT e.eventName,e.eventDate FROM Notification n INNER JOIN Invitation i On n.notificationID = i.notificationID INNER JOIN Event e On i.eventID = e.eventID INNER JOIN Package p On e.packageID=p.packageID WHERE i.expiryDate <="+"'"+m1.currentDate()+"'"+  "AND (n.type='Invitation') AND(e.eventStatus='Pending') ORDER BY eventDate";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				e1.add(new Event(rs.getString("e.eventName"), rs.getString("eventDate")));
		}
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			for (int i=0; i<e1.size(); i++)
			{
				System.out.println(e1.get(i).eventTitle);
			}
			
			return e1;
	}
	
	public ArrayList<Event> getEventRecordsDueForPayment(){
			
			MyCalendar m1 = new MyCalendar();
			ArrayList<Event> e1 = new ArrayList<Event>();
			ResultSet rs = null;
			
			String dbQuery;
			
			
			dbQuery = "SELECT eventName,eventDate FROM Event e INNER JOIN Purchase_Summary ps ON ps.eventID=e.eventID WHERE eventStatus='Awaiting Payment' || (eventStatus='Confirmed' && ps.amountPending !=0 && ps.amountPending IS NOT NULL) ORDER BY eventDate";
			try{
				
				rs=DB.readRequest(dbQuery);
				while(rs.next()){
					e1.add(new Event(rs.getString("eventName"), rs.getString("eventDate")));
			}
			}
				catch(Exception e){
					e.printStackTrace();
				}
				
				for (int i=0; i<e1.size(); i++)
				{
					System.out.println(e1.get(i).eventTitle);
				}
				
				return e1;
		}
	
	public ArrayList<String> getEventDetails(String eventName){

ArrayList<String> e1 = new ArrayList<String>();
ResultSet rs = null;

String dbQuery;


dbQuery = "SELECT e.eventTime,e.eventDate,e.eventStatus FROM Event e WHERE e.eventName="+"'"+eventName+"'";
try{
	
	rs=DB.readRequest(dbQuery);
	while(rs.next()){
		
	e1.add(rs.getString("eventTime")+","+rs.getString("eventDate")+","+rs.getString("eventStatus"));
		
		for(int i=0;i<e1.size();i++){
			System.out.println(e1.get(i));
		}
		
		
		}
		
		
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return e1;
}
	
	public boolean updateEventStatus(String eventName,String eventStatus){
		
		boolean status=false;
		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
		
		ResultSet rs = null;
		
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
			this.eventTitle = eventTitle;
		}
}
