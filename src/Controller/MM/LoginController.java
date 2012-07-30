package Controller.MM;
import View.MM.*;
import Model.Membership.*;



public class LoginController {
		
	Account account = new Account();
	
	public boolean initiateLogin(String userName, String password){
		Account accountSet = new Account();

		accountSet.setUserName(userName);
		accountSet.setPassword(password);
	
		

	
		boolean isValid = account.INITIATE_LOGIN(accountSet);
		
		if(isValid == true)
		{
			Account.currentUser = account.GET_ACCOUNT(userName);
			if(Account.currentUser.getType().equalsIgnoreCase("RI"))
			{
				System.out.println("RI logs in");
			
				
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("GR"))
			{
				System.out.println("GR logs in");
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("Guest")){
				
				System.out.println("Guest logs in");
			}
			
			else
			{
				isValid = false;
			}
			
			
		}
		return isValid;

		
	}

	
	
}
