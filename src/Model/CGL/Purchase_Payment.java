/********************************************************************************************************************************************************
Program Name			:	Purchase_Payment.java
Description				:	A Purchase_Payment class
Done by					:	A AMEENUDEEN (111942S)
Admin No				:	111942S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	A Ameenudeen (111942S)
METHODS LIST 			: 	UPDATES_PURCHASE_PAYMENT(String,String,String,String) : boolean
																		
********************************************************************************************************************************************************/
package Model.CGL;


import java.sql.ResultSet;

import Controller.CGL.ConsolidateGuestListControl;
import Controller.MySQLController;

import Controller.MyCalendar;

public class Purchase_Payment {
	private int paymentID;
	private int purchaseID;
	private String paymentMethod;
	private String paymentDate;
	private String amountPaid;
	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public static MySQLController getDB() {
		return DB;
	}

	public static void setDB(MySQLController dB) {
		DB = dB;
	}

	private static MySQLController DB = new MySQLController();
	
	/********************************************************
	  * Method Name 	: UPDATES_PURCHASE_PAYMENT
	  * Input Parameter : String,String,String,String
	  * Return 			: boolean
	  * Purpose 		: To Update the purchase Payment
	  *******************************************************/
	
	public boolean UPDATES_PURCHASE_PAYMENT(String amount,String paymentMethod,String totalCost,String eventName){
		boolean status=false;
		@SuppressWarnings("unused")
		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
		MyCalendar m1 = new MyCalendar();
		@SuppressWarnings("unused")
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
