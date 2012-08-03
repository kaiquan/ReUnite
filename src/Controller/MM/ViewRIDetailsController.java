package Controller.MM;



import javax.swing.table.DefaultTableModel;

import Model.Membership.*;

public class ViewRIDetailsController {

	RI riModel = new RI();
	RI eventAndPurchaseModel = new RI();
	
	public ViewRIDetailsController() {}

	public void retrieveRiDetails(String userName, String password,
			String type, String status,
			String firstName, String lastName, String nric,
			String address, String school, String email , String closureRequest) {

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
		retrieveDetails.setAddress(address);
		retrieveDetails.setClosureRequest(closureRequest);

		retrieveDetails.RETRIEVE_USER();

	}

	public DefaultTableModel getRITableModel()
	{
		DefaultTableModel model = new DefaultTableModel(riModel.getRITableModel(), riModel.getRITableColumnNames());
		return model;
	}
	public DefaultTableModel getRITableModelEvent() {
		DefaultTableModel modelEvent = new DefaultTableModel(eventAndPurchaseModel.GET_EVENTS_ALL_FOR_RI(), eventAndPurchaseModel.getRIEventNPurchaseColumnNames());
		return modelEvent;
	}

	
	
	
}
