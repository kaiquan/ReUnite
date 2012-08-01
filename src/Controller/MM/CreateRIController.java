package Controller.MM;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import Model.Membership.*;

public class CreateRIController {

	public CreateRIController() {

	}

	RI createRI = new RI();

	/**
	 * makes into an new RI object
	 * 
	 * @param password
	 * @param userName
	 */
	
	//date and secret Question Not yet Included
	public void createRegistration(String userName, String password,
			String firstName, String lastName,String dateOfBirth, String nric, String email, 
			String school, String address, String telephoneNo, String handphoneNo,String secretQuestion, String secretAnswer) throws Exception {
		RI newRI = new RI();

		newRI.setUserName(userName);
		newRI.setPassword(password);
		 newRI.setType("RI");
		 //newRI.setCreationDate(new
		// SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse("1993-10-07 11:34:33"));
		 newRI.setStatus("Active");
		// newRI.setConfirmed("1");
		newRI.setFirstName(firstName);
		newRI.setLastName(lastName);
		//newRI.setDateOfBirth(dateOfBirth);//
	
		newRI.setNric(nric);
		newRI.setSchool(school);
		newRI.setEmail(email);
		newRI.setAddress(address);
		newRI.setTelephoneNo(telephoneNo);
		newRI.setHandphoneNo(handphoneNo);
		newRI.setSecretQuestion(secretQuestion);
		 newRI.setSecretAnswer(secretAnswer);
		
	
	
		createRI.createRIAccount(newRI);

	}

	private String secretQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
