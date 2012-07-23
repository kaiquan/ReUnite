package Controller.MM;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.RI;
import Model.Account;

public class ViewRIDetailsController {

	RI riModel = new RI();
	
	public ViewRIDetailsController() {
	}

	public void retrieveRiDetails(String userName, String password,
			String type, String status,
			String firstName, String lastName, String nric,
			String postalAddress, String school, String email) {

		RI retrieveDetails = new RI();

		retrieveDetails.setUserName(userName);
		retrieveDetails.setPassword(password);
		retrieveDetails.setType(type);
		retrieveDetails.setStatus(status);
		retrieveDetails.setFirstName(firstName);
		retrieveDetails.setLastName(lastName);
		retrieveDetails.setNric(nric);
		retrieveDetails.setSchool(school);
		retrieveDetails.setEmail(email);
		retrieveDetails.setAddress(postalAddress);

		retrieveDetails.retrieveUser();

	}

	public DefaultTableModel getRITableModel()
	{
		DefaultTableModel model = new DefaultTableModel(riModel.getRITableModel(), riModel.getRITableColumnNames());
		return model;
	}

}
