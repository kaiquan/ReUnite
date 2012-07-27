package Controller.MM;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Membership.*;

public class ViewRIPersonalController {
	RI riModel1 = new RI();
	
	public ViewRIPersonalController(){}
	
	
	
	

	public void retrieveRiDetails(String userName , String password,String firstName, String lastName, String nric, String postalAddress,  String school, String email){
	
		RI singleRetrieve = new RI();
	
		singleRetrieve.setUserName(userName);
		singleRetrieve.setPassword(password);
		singleRetrieve.setFirstName(firstName);
		singleRetrieve.setLastName(lastName); 
		singleRetrieve.setNric(nric);
		singleRetrieve.setSchool(school);
		singleRetrieve.setEmail(email);
		singleRetrieve.setAddress(postalAddress);
	
		
		singleRetrieve.retrieveSingleUser();

	}





	public TableModel getRITableModel1() {
		DefaultTableModel model = new DefaultTableModel(riModel1.getRITableModel1(), riModel1.getRITableColumnNames());
		return model;
	}

}
