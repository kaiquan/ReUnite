package rcm.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import rcm.controller.MySQLController;



public class Meal {
	private static MySQLController DB = new MySQLController();

public ArrayList<String> getMealPrice(String eventName){
		
		ArrayList<String> e1 = new ArrayList<String>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT m.mealFinalPrice FROM Meal m INNER JOIN Meal_Options mo On mo.mealID=m.mealID INNER JOIN Package p On p.packageID=mo.packageID INNER JOIN Event e ON e.packageID=p.packageID WHERE e.eventName="+"'"+eventName+"'" ;
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				
			e1.add(rs.getString("mealFinalPrice"));
			
			for(int i=0;i<e1.size();i++){
				System.out.println(e1.get(i));
			}
			
			
			}
			
			
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			return e1;
	}


	
}
