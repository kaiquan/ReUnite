package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import controller.MySQLController;

public class EventForm extends FeedbackForm{
	private static MySQLController db;
	private ArrayList<Integer> eventID;
	
	public EventForm(){
		db = new MySQLController();
		eventID = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID.add(eventID);
	}
	
	public boolean createEventForm(int ffCode, int eventID){
		boolean success = false;
		
		String sql = "INSERT INTO saharp5_adeel_school.Event_Form(ffCode, eventID) ";
		sql += "VALUES (" + ffCode + ", " + eventID + ")";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean retrieveEventForm(String condition){
		boolean success = false;
		ResultSet rs = null;
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Event_Form";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					setCode(rs.getInt("ffCode"));
					eventID.add(rs.getInt("eventID"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
	public boolean updateEventForm(String condition){
		boolean success = false;
		
		String sql = "UPDATE saharp5_adeel_school.Event_Form";
		if (condition != null){
			sql += condition;
		}
		
		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean deleteEventForm(String condition){
		boolean success = false;
		
		String sql = "DELETE FROM saharp5_adeel_school.Event_Form";
		if (condition != null){
			sql += condition;
		}
		
		if (db.updateRequest(sql) >= 1)
			success = true;
		
		return success;
	}
	
}
