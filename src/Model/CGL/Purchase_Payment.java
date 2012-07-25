package Model.CGL;

import java.sql.ResultSet;

import Controller.CGL.ConsolidateGuestListControl;
import Controller.MySQLController;

import Controller.MyCalendar;

public class Purchase_Payment {
	private static MySQLController DB = new MySQLController();
	
	public boolean updatesPurchasePayment(String amount,String paymentMethod,String totalCost,String eventName){
		boolean status=false;
		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
		MyCalendar m1 = new MyCalendar();
		ResultSet rs = null;
		
		String dbQuery;
		String dbQuery1;
		
		int i=0;
		
		
		dbQuery = "INSERT INTO `saharp5_adeel_school`.`Purchase_Payment` (`purchaseID`, `paymentMethod`, `paymentDate`, `amountPaid`) VALUES ((SELECT purchaseID FROM Purchase_Summary WHERE totalCost="+totalCost+"),"+"'"+paymentMethod+"'" +"," +"'"+m1.currentDateWithTime()+"'"+","+"'" +amount+"'"+")";
		dbQuery1 ="UPDATE Purchase_Summary SET amountPending=amountPending - "+amount+" WHERE eventID=(SELECT eventID FROM Event WHERE eventName='"+eventName+"'"+")";
		
		try{
			i=DB.updateRequest(dbQuery);
			i=DB.updateRequest(dbQuery1);
		}
		
		catch(Exception ex){
			System.out.println("Failed to Update Purchase Payment Table");
		}
		
		finally{
			
		}
		
		if(i==1){
			status=true;
		
		}
		
		
		return status;
			
	}
	
	
}
