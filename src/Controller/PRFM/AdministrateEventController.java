package Controller.PRFM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Model.Event;

public class AdministrateEventController {
	
	public String calendarToString(GregorianCalendar cal){
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
	}
	
	public Event processRetrieve(){
		Event event = new Event();
		event.RETRIEVE_EVENTS(null);
		return event;
	}
	
	public Event processSearchTerm(int eventID, GregorianCalendar date, String time, String name, String status){
		Event event = new Event();
		String condition = null;
		
		if (eventID == 0 && date == null && time == null && name == null && status == null){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (eventID != 0){
			searchTerm.add("eventID = " + eventID);
		}
		
		if (date != null){
			searchTerm.add("eventDate = '" + calendarToString(date) + "'");
		}
		
		if (time != null){
			searchTerm.add("eventTime = '" + time + "'");
		}
		
		if (name != null){
			searchTerm.add("eventName = '" + name + "'");
		}
		
		if (status != null){
			searchTerm.add("eventStatus = '" + status + "'");
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += ", ";
			}
		}
		
		event.RETRIEVE_EVENTS(condition);
		return event;
	}
	
	public Event processEventID(ArrayList<Integer> eventArr){
		Event event = new Event();
		String condition = null;
		
		if (eventArr.isEmpty()){
			return null;
		}
		else{
			condition = " WHERE eventID IN (";
		}
		
		for (int i = 0; i < eventArr.size(); i++){
			condition += eventArr.get(i);
			
			if (i != eventArr.size() - 1){
				condition += ", ";
			}
		}
		
		condition += ")";
		event.RETRIEVE_EVENTS(condition);
		return event;
	}

}
