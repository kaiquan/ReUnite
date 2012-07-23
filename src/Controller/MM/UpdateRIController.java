package Controller.MM;

import Model.Membership.*;

public class UpdateRIController {
	
	RI update = new RI();
	
	public UpdateRIController(){}
	
							// puts in all the method arguments
	public void updateRIAccount(String userName, String password, String firstName, String lastName, String nric, String school, String email, String address){
		
		RI updateRI = new RI();
		updateRI.setUserName(userName);
		updateRI.setPassword(password);
		// updateRI.setType("RI");
		// updateRI.setCreationDate(new
		// SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse("1993-10-07 11:34:33"));
		// updateRI.setStatus("ACTIVE");
		// updateRI.setConfirmed(true);
		updateRI.setFirstName(firstName);
		updateRI.setLastName(lastName);
		// updateRI.setDateOfBirth(new
		// SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse("1993-10-07 11:34:33"));
		updateRI.setNric(nric);
		updateRI.setSchool(school);
		updateRI.setEmail(email);
		updateRI.setAddress(address);
		// updateRI.setTelephoneNo(telephoneNo);
		// updateRI.setHandphoneNo(handphoneNo);
		// updateRI.setSecretQuestion(secretQuestion);
		// newRI.setSecretAnswer(secretAnswer);
		// updateRI.setClosureDate(new
		// SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse("1993-10-07 11:34:33"));
		// updateRI.setClosureRequestDate(new
		// SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse("1993-10-07 11:34:33"));
		// updateRI.setClosureReason(closureReason);

		update.updateRIAccount(updateRI);
		
	}

}
