package Controller.MM;
import javax.swing.JOptionPane;

import Model.Membership.Account;
import View.GRDashboard;
import View.RIDashboard;
import View.RIM.GuestProfileView;



public class LoginController {
		
	Account account = new Account();
	//account.getSecretQuestion();
	
	public boolean initiateSQuestion(String userName  ){
		Account secretQn = new Account();
		secretQn.setUserName(userName);
		secretQn.getSecretQuestion();
		
		Account a = secretQn.getSecretQn(secretQn);
		Account.currentUser = account;
		
		Account.currentUser.getSecretQuestion();
		Account.currentUser.getSecretAnswer();
		
		
		
		
		return false;
		
	}
	
	public boolean initiateLogin(String userName, String password){
		boolean valid = false;
		
		Account accountSet = new Account();
		accountSet.setUserName(userName);
		accountSet.setPassword(password);
	
		Account account = accountSet.INITIATE_LOGIN(accountSet);
		
		if(account!=null)
		{
			valid = true;
			Account.currentUser = account;
			if(Account.currentUser.getType().equalsIgnoreCase("RI"))
			{
				new RIDashboard();
			
			}
			
			else if(Account.currentUser.getType().equalsIgnoreCase("GR")) 
			{
				new GRDashboard();
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("Guest"))
			{
				
				new GuestProfileView();
			}
		}
		else
		{	
			JOptionPane.showMessageDialog(null,"User name or password does not exist. Please try again.");
		
			System.out.println("Access Denied");
			
		}
		return valid;
	}

	
	
}
