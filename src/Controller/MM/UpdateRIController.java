package Controller.MM;

import Model.Membership.*;

public class UpdateRIController {
	
	RI update = new RI();
	
	public UpdateRIController(){}
	
							// puts in all the method arguments
	
	public void disableRIAccount ( String userName,String status){
		RI disableAccount = new RI();
		
		disableAccount.setUserName(userName);
		disableAccount.setStatus(status);
		
		update.disableRIAccount(disableAccount);
		
	}
	
	public void updateRIAccount(String userName, String firstName, String lastName, String nric, String school, String email,
			String telephoneNo, String handphoneNo){
		
		RI updateRI = new RI();
		updateRI.setUserName(userName);
		//updateRI.setPassword(password);
		updateRI.setFirstName(firstName);
		updateRI.setLastName(lastName);
		updateRI.setNric(nric);
	
		updateRI.setSchool(school);
		updateRI.setEmail(email);
		//updateRI.setAddress(address);
		 updateRI.setTelephoneNo(telephoneNo);
		 updateRI.setHandphoneNo(handphoneNo);
	

		update.updateRIAccount(updateRI);
		
		
		
		
	}

}
