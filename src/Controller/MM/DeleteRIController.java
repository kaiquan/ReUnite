package Controller.MM;

import Model.Membership.*;

public class DeleteRIController {
	
	public DeleteRIController(){}
	
	RI delete = new RI();
	
	public void delteRIAccount(String userName){
		
		RI deleteRI = new RI();
		deleteRI.setUserName(userName);
		
		
		delete.DELETE_RI_ACCOUNT(deleteRI);

	}
	

}
