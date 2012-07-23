package rcm.model;

import java.sql.ResultSet;

import java.util.ArrayList;

import rcm.controller.ConsolidateGuestListControl;
import rcm.controller.MySQLController;

import rcm.controller.MyCalendar;

public class Purchase_Summary {
	
	private static MySQLController DB = new MySQLController();
	
public boolean updatesTotalPayableAmount(String amount,String eventName){
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

public ArrayList<String> retrievePaymentDetails(String eventName){
	
	
	ArrayList<String> e1 = new ArrayList<String>();
	ResultSet rs = null;
	
	String dbQuery;
	
	
	dbQuery = "SELECT totalCost,amountPending From Purchase_Summary WHERE eventID=(SELECT eventID FROM Event WHERE eventName='"+eventName+"')";
	try{
		
		rs=DB.readRequest(dbQuery);
		while(rs.next()){
			e1.add(rs.getString("totalCost")+","+rs.getString("amountPending"));
	}
	}
		catch(Exception e){
			e.printStackTrace();
		}
								
		
		return e1;
	
}

}
