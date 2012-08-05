package Controller.MM;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

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
	@SuppressWarnings("deprecation")
	public void createRegistration(String userName, String password,
			String firstName, String lastName,String dateOfBirth, String nric, String email, 
			String school, String address, String telephoneNo, String handphoneNo,String secretQuestion, String secretAnswer) throws Exception {
		RI newRI = new RI();
						
		Scanner sc = new Scanner(dateOfBirth);
		sc.useDelimiter("-");
		int year=sc.nextInt();
		int month=sc.nextInt();
		int day=sc.nextInt();
		Date birthDate= new Date(year,month,day);
		newRI.setUserName(userName);
		newRI.setPassword(password);
		 newRI.setType("RI");
		 newRI.setStatus("Active");
		newRI.setConfirmed(true);
		newRI.setFirstName(firstName);
		newRI.setLastName(lastName);
		newRI.setCreationDate(new SimpleDateFormat("YYYY-MM-DD").parse(dateOfBirth));
		newRI.setDateOfBirth(birthDate);
		newRI.setNric(nric);
		newRI.setSchool(school);
		newRI.setEmail(email);
		newRI.setAddress(address);
		newRI.setTelephoneNo(telephoneNo);
		newRI.setHandphoneNo(handphoneNo);
		newRI.setSecretQuestion(secretQuestion);
		 newRI.setSecretAnswer(secretAnswer);
		
	
	
		createRI.CREATE_RI_ACCOUNT(newRI);

	}

	@SuppressWarnings("unused")
	private String secretQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
