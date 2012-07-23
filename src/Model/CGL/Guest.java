package Model.CGL;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;



public class Guest {
	
	private static MySQLController DB = new MySQLController();
	
		public ArrayList<String> getNumberOfGuests(String eventName){
		
		ArrayList<String> e1 = new ArrayList<String>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT COUNT(*) FROM Invitation i INNER JOIN Event e On e.eventID=i.eventID INNER JOIN Guest g On i.invitationID=g.invitationID WHERE e.eventName="+"'"+eventName+"'" +"AND g.response='Attending'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				
			e1.add(rs.getString("Count(*)"));
			
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
