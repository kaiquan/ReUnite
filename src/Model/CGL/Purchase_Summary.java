package Model.CGL;
/********************************************************************************************************************************************************
Program Name			:	Purchase_Summary.java
Description				:	A Purchase_Summary class
Done by					:	A AMEENUDEEN (111942S)
Admin No				:	111942S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	A Ameenudeen (111942S)
METHODS LIST 			: 	UPDATES_TOTAL_PAYABLE_AMOUNT(String,String) : Boolean
						:	RETREIVE_PAYMENT_DETAILS(String) : ArrayList<String>
																		
********************************************************************************************************************************************************/



import java.sql.ResultSet;
import java.util.ArrayList;
import Controller.CGL.ConsolidateGuestListControl;
import Controller.MySQLController;

public class Purchase_Summary {
	private int purchaseID;
	private int eventID;
	@SuppressWarnings("unused")
	private String totalCost;
	@SuppressWarnings("unused")
	private String amountPending;
	private String dateOfPurchase;
	
	public int getPurchaseID() {
		return purchaseID;
	}


	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}


	public int getEventID() {
		return eventID;
	}


	public void setEventID(int eventID) {
		this.eventID = eventID;
	}


	public String getDateOfPurchase() {
		return dateOfPurchase;
	}


	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}


	public static MySQLController getDB() {
		return DB;
	}


	public static void setDB(MySQLController dB) {
		DB = dB;
	}


	private static MySQLController DB = new MySQLController();
	
	
	/********************************************************
	  * Method Name 	: UPDATES_TOTAL_PAYABLE_AMOUNT
	  * Input Parameter : String,String
	  * Return 			: booleaN
	  * Purpose 		: To Update the TOTAL Payable Amount
	  *******************************************************/
	
public boolean UPDATES_TOTAL_PAYABLE_AMOUNT(String amount,String eventName){
		boolean status=false;
		@SuppressWarnings("unused")
		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
		
		@SuppressWarnings("unused")
		ResultSet rs = null;
		
		String dbQuery;
		
		int i=0;
		
		dbQuery = "UPDATE Purchase_Summary SET totalCost="+amount+ ",amountPending="+amount+ " WHERE eventID=(SELECT eventID FROM Event WHERE eventName="+"'"+eventName+"'"+")";
		
		try{
			i=DB.updateRequest(dbQuery);
		}
		
		catch(Exception ex){
			System.out.println("Failed to gr");
		}
		
		finally{
			
		}
		
		if(i==1){
			status=true;
		
		}
		
		
		return status;
			
	}


/********************************************************
  * Method Name 	: RETREIVE_PAYMENT_DETAILS
  * Input Parameter : String
  * Return 			: ArrayList<String>
  * Purpose 		: To RETRIEVE THE PAYMENT DETAILS
  *******************************************************/

public ArrayList<Purchase_Summary> RETRIEVE_PAYMENT_DETAILS(String eventName){
	
	
	ArrayList<Purchase_Summary> e1 = new ArrayList<Purchase_Summary>();
	ResultSet rs = null;
	
	String dbQuery;
	
	
	dbQuery = "SELECT totalCost,amountPending From Purchase_Summary WHERE eventID=(SELECT eventID FROM Event WHERE eventName='"+eventName+"')";
	try{
		
		rs=DB.readRequest(dbQuery);
		while(rs.next()){
			Purchase_Summary p1 = new Purchase_Summary();
			p1.setTotalCost(rs.getString("totalCost"));
			p1.setAmountPending(rs.getString("amountPending"));
			e1.add(p1);
	}
		
		for(int i=0;i<e1.size();i++){
			System.out.println(e1.get(i));
		}
	}
		catch(Exception e){
			e.printStackTrace();
		}
								
		
		return e1;
	
}


public void setTotalCost(String string) {
	// TODO Auto-generated method stub
	
}


public void setAmountPending(String string) {
	// TODO Auto-generated method stub
	
}


public String getTotalCost() {
	// TODO Auto-generated method stub
	return null;
}


public String getAmountPending() {
	// TODO Auto-generated method stub
	return null;
}


}
