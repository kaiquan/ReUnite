package Controller.MM;
import javax.swing.JOptionPane;

import Model.Membership.Account;
import View.MM.AdministrateRIDetails;
import View.MM.AdministrateRIPersonalDetails;
import View.RIM.CreateInvitationView;



public class LoginController {
		
	Account account = new Account();
	
	public void initiateLogin(String userName, String password){
		Account accountSet = new Account();

		accountSet.setUserName(userName);
		accountSet.setPassword(password);
	
		Account account = accountSet.INITIATE_LOGIN(accountSet);
		
		if(account!=null)
		{
			Account.currentUser = account;
			if(Account.currentUser.getType().equalsIgnoreCase("RI"))
			{
				System.out.println("Access Granted");
						new AdministrateRIPersonalDetails().getJFrame().setVisible(true);
			}
			
			else if(Account.currentUser.getType().equalsIgnoreCase("GR")) 
			{
				new AdministrateRIDetails().getJFrame().setVisible(true);
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("Guest")){
				
				new CreateInvitationView();
			}
		}
		else
		{	
			JOptionPane.showMessageDialog(null,"UserName or Password does not exist, Try Again");
		
			System.out.println("Access Denied");
			
		}
		
	}

	
	
}
