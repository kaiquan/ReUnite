package rcm.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import rcm.controller.MySQLController;



public class Entertainment {
	
	private static MySQLController DB = new MySQLController();
	
public ArrayList<String> getEntertainmentPrice(String eventName){
		
		
		ArrayList<String> e1 = new ArrayList<String>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT en.entertainmentPrice FROM Event e INNER JOIN Package p On e.packageID=p.packageID INNER JOIN Entertainment en On p.entertainmentID=en.entertainmentID  WHERE e.eventName="+"'"+eventName+"'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				e1.add(rs.getString("entertainmentPrice"));
		}
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			for (int i=0; i<e1.size(); i++)
			{
				System.out.println(e1.get(i));
			}
			
			return e1;
	}


	

}
