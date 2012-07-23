package Controller.MM;

import Model.RI;

public class ViewRIPersonalController {
	
	ViewRIPersonalController(){}
	
	

	

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

}
