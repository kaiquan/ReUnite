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

import Controller.MyCalendar;

public class Purchase_Summary {
	
	private static MySQLController DB = new MySQLController();
	
	
	/********************************************************
	  * Method Name 	: UPDATES_TOTAL_PAYABLE_AMOUNT
	  * Input Parameter : String,String
	  * Return 			: booleaN
	  * Purpose 		: To Update the TOTAL Payable Amount
	  *******************************************************/
	
public boolean UPDATES_TOTAL_PAYABLE_AMOUNT(String amount,String eventName){
		boolean status=false;
		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
		
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

public ArrayList<String> RETRIEVE_PAYMENT_DETAILS(String eventName){
	
	
	ArrayList<String> e1 = new ArrayList<String>();
	ResultSet rs = null;
	
	String dbQuery;
	
	
	dbQuery = "SELECT totalCost,amountPending From Purchase_Summary WHERE eventID=(SELECT eventID FROM Event WHERE eventName='"+eventName+"')";
	try{
		
		rs=DB.readRequest(dbQuery);
		while(rs.next()){
			e1.add(rs.getString("totalCost")+","+rs.getString("amountPending"));
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
