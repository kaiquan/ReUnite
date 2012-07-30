package Controller.MM;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Event;
import Model.Membership.*;

public class ViewRIPersonalController {
	RI riModel1 = new RI();
	RI riModelEvent = new RI();
	RI riModelPayment = new RI();
	LoginController login = new LoginController();  //  @jve:decl-index=0:
	
	
	
public ViewRIPersonalController(){}
public Account GET_ACCOUNT(Account userName) {
	
	return userName;
	
}

	public void retrieveRiDetails(String userName , String password,String firstName, String lastName, String nric, String postalAddress,  String school, String email){
	
	
		Account.currentUser.setUserName(userName);
		Account.currentUser.setPassword(password);
		Account.currentUser.setFirstName(firstName);
		Account.currentUser.setLastName(lastName); 
		Account.currentUser.setNric(nric);
		Account.currentUser.setSchool(school);
		Account.currentUser.setEmail(email);
		Account.currentUser.setAddress(postalAddress);
		
	
		


	}
	
	public void getEvent(String userName, String eventName, String eventStatus, String eventID){
			RI retrieveEvent = new RI();
			
			retrieveEvent.setEventName(eventName);
			retrieveEvent.setEventStatus(eventStatus);
			retrieveEvent.setEventID(eventID);
			
			retrieveEvent.GET_EVENTS_FOR_RI();
	
	}




	public TableModel getRITableModel1() {
		DefaultTableModel model = new DefaultTableModel(riModel1.getRITableModel1(), riModel1.getRITableColumnNames());
		return model;
	}
	
	public TableModel getRITableModelEvent() {
		DefaultTableModel modelEvent = new DefaultTableModel(riModelEvent.getRITableModelEvent(), riModelEvent.getRITableColumnNamesEvent());
		return modelEvent;
	}
	
	public TableModel getRITableModelPayment() {
		DefaultTableModel modelPayment = new DefaultTableModel(riModelPayment.getRITableModelPayment(), riModelPayment.getRITableColumnNamesPayment());
		return modelPayment;
	}
	
	

	

	

	
	

}
