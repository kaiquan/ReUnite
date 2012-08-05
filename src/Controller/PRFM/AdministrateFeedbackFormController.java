package Controller.PRFM;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Model.PRFM.EventForm;
import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;

public class AdministrateFeedbackFormController {

	private String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public FeedbackForm processSearchTerm(int code, Date creationDate, int fqCode){
		FeedbackForm ff = new FeedbackForm();
		String condition = null;
		
		if (code == 0 && creationDate == null && fqCode == 0){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (code != 0){
			searchTerm.add("code = " + code);
		}
		
		if (creationDate != null){
			searchTerm.add("creationDate = '" + dateToString(creationDate) + "'");
		}
		
		if (fqCode != 0){
			searchTerm.add("fqCode = " + fqCode);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		ff.retrieveFeedbackForm(condition);
		return ff;
	}
	
	public FeedbackForm processSearchTerm(FeedbackForm ff, int fqCode){
		FeedbackForm tempFf = new FeedbackForm();
		FeedbackForm newFf = new FeedbackForm();
		
		for (int i = 0; i < ff.getCode().size(); i++){
			tempFf = processSearchTerm(ff.getCode().get(i), null, 0);
			for (int x = 0; x < tempFf.getFqCode().size(); x++){
				if (tempFf.getFqCode().get(x) == fqCode){
					newFf.setCode(tempFf.getCode().get(x));
					newFf.setCreationDate(tempFf.getCreationDate().get(x));
					newFf.setFqCode(tempFf.getFqCode().get(x));
					newFf.setFqOrder(tempFf.getFqOrder().get(x));
				}
			}
		}
		return newFf;
	}
	
	public FeedbackForm processRetrieve(){
		FeedbackForm ff = new FeedbackForm();
		ff.retrieveFeedbackForm(null);
		return ff;
	}
	
	public boolean processDelete(int code, int fqOrder){
		FeedbackForm ff = new FeedbackForm();
		String condition = " WHERE code = " + code;
		
		if (fqOrder != 0){
			condition += " AND fqOrder = " + fqOrder;
		}
		
		return ff.deleteFeedbackForm(condition);
	}
	
	public boolean processUpdate(int fqCode, int replaceFqCode){
		String condition = null;
		FeedbackForm ff = new FeedbackForm();
		condition = " SET fqCode = " + replaceFqCode + " WHERE fqCode = " + fqCode;
		return ff.updateFeedbackForm(condition);
	}
	
	public boolean processUpdate(int code, String creationDate, int replaceFqCode, int fqOrder){
		String condition;
		FeedbackForm ff = new FeedbackForm();
		
		if ( (code == 0 && creationDate == null && replaceFqCode == 0 && fqOrder == 0) || replaceFqCode == 0){
			return false;
		}
		else{
			ArrayList<String> updateTerm = new ArrayList<String>();
			ArrayList<String> searchTerm = new ArrayList<String>();
			condition = " SET ";
			
			if (replaceFqCode != 0){
				updateTerm.add("fqCode = " + replaceFqCode);
			}
			
			for (int i = 0; i < updateTerm.size(); i++){
				condition += updateTerm.get(i);
				
				if (i != updateTerm.size() - 1){
					condition += ", ";
				}
			}
			
			if (code != 0){
				searchTerm.add("code = " + code);
			}
			
			if (creationDate != null){
				searchTerm.add("creationDate = '" + creationDate + "'");
			}
			
			if (fqOrder != 0){
				searchTerm.add("fqOrder = " + fqOrder);
			}
			
			if (!searchTerm.isEmpty()){
				condition += " WHERE ";
			}
			
			for (int x = 0; x < searchTerm.size(); x++){
				condition += searchTerm.get(x);
				
				if (x != searchTerm.size() - 1){
					condition += " AND ";
				}
			}
		}

		return ff.updateFeedbackForm(condition);
	}
	
	public boolean processCreate(int code, int fqCode, int fqOrder){
		FeedbackForm ff = new FeedbackForm();
		return ff.createFeedbackForm(code, dateToString((Date) Calendar.getInstance().getTime()), fqCode, fqOrder);
	}
	
	public int generateCode(){
		FeedbackForm ff = new FeedbackForm();
		int code = ff.retrieveLastCode() + 1;
		return code;
	}
	
	public FeedbackForm processCurrentForm(){
		FeedbackForm ff = new FeedbackForm();
		String currentDate = dateToString((Date) Calendar.getInstance().getTime());
		String condition = " WHERE startDate < '" + currentDate + "' AND endDate > '" + currentDate + "'";
		ff.retrieveFeedbackForm(condition);
		return ff;
	}
	
	public boolean processCreateEventForm(int ffCode, int eventID){
		EventForm ef = new EventForm();
		return ef.createEventForm(ffCode, eventID);
	}
	
	public EventForm processRetrieveEventForm(){
		EventForm ef = new EventForm();
		ef.retrieveEventForm(null);
		return ef;
	}
	
	public EventForm processEventFormSearchTerm(int ffCode, int eventID){
		EventForm ef = new EventForm();
		String condition = null;
		
		if (ffCode == 0 && eventID == 0){
			return null;
		}
		else{
			condition = " WHERE ";
		}
		
		ArrayList<String> searchTerm = new ArrayList<String>();
		
		if (ffCode != 0){
			searchTerm.add("ffCode = " + ffCode);
		}
		
		if (eventID != 0){
			searchTerm.add("eventID = " + eventID);
		}
		
		for (int i = 0; i < searchTerm.size(); i++){
			condition += searchTerm.get(i);
			
			if (i != searchTerm.size() - 1){
				condition += " AND ";
			}
		}
		
		ef.retrieveEventForm(condition);
		return ef;
	}
	
	public boolean processUpdateEventForm(int ffCode, int eventID, int replaceEventID){
		EventForm ef = new EventForm();
		String condition;
		
		if ( (ffCode == 0 && eventID == 0 && replaceEventID == 0) || replaceEventID == 0){
			return false;
		}
		else{
			condition = " SET ";
			ArrayList<String> updateTerm = new ArrayList<String>();
			ArrayList<String> searchTerm = new ArrayList<String>();
			
			if (replaceEventID != 0){
				updateTerm.add("eventID = " + replaceEventID);
			}
			
			for (int a = 0; a < updateTerm.size(); a++){
				condition += updateTerm.get(a);
				
				if (a != updateTerm.size() - 1){
					condition += ", ";
				}
			}
			
			if (ffCode != 0){
				searchTerm.add("ffCode = " + ffCode);
			}
			
			if (eventID != 0){
				searchTerm.add("eventID = " + eventID);
			}
			
			if (!searchTerm.isEmpty()){
				condition += " WHERE ";
			}
			
			for (int b = 0; b < searchTerm.size(); b++){
				condition += searchTerm.get(b);
				
				if (b != searchTerm.size() - 1){
					condition += " AND ";
				}
			}
			
			return ef.updateEventForm(condition);		
		}
	}
	
	public boolean processDeleteEventForm(int ffCode, ArrayList<Integer> notEventID){
		EventForm ef = new EventForm();
		String condition = null;
		
		if ( ffCode == 0 && (notEventID.isEmpty() || notEventID == null) ){
			return false;
		}
		else{
			condition = " WHERE ";
		}
		
		if (ffCode != 0){
			condition += "ffCode = " + ffCode;
		}
		
		if (notEventID != null){
			if (!notEventID.isEmpty()){
				condition += " AND ";
				
				for (int i = 0; i < notEventID.size(); i++){
					condition += "eventID != " + notEventID.get(i);
					
					if (i != notEventID.size() - 1){
						condition += " AND ";
					}
				}
			}
		}
		
		return ef.deleteEventForm(condition);
		
	}
	
	public int compareCurrentDate(GregorianCalendar gc){
		GregorianCalendar now = new GregorianCalendar();
		
		if (gc.get(Calendar.YEAR) == now.get(Calendar.YEAR) && gc.get(Calendar.MONTH) == now.get(Calendar.MONTH) && gc.get(Calendar.DATE) == now.get(Calendar.DATE)){
			return 0;
		}
		
		return gc.compareTo(now);
	}
}
