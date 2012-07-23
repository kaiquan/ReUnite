package Controller.MM;

import Model.RI;

public class DeleteRIController {
	
	DeleteRIController(){}
	
	RI delete = new RI();
	
	public void delteRIAccount(String userName){
		
		RI deleteRI = new RI();
		deleteRI.setUserName(userName);
		
		
		delete.deleteRIAccount(deleteRI);

	}
	

}
