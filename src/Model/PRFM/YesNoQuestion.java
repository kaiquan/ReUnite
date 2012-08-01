package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import controller.MySQLController;

public class YesNoQuestion extends FeedbackQuestion{
	private static MySQLController db;
	private ArrayList<String> choice;
	private ArrayList<Boolean> trueFalse;
	
	public YesNoQuestion(){
		db = new MySQLController();
		choice = new ArrayList<String>();
		trueFalse = new ArrayList<Boolean>();
	}
	
	public ArrayList<String> getChoice() {
		return choice;
	}

	public ArrayList<Boolean> getTrueFalse() {
		return trueFalse;
	}

	public boolean retrieveYesNoQuestion(){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.YesNo_Question";
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					choice.add(rs.getString("choice"));
				    trueFalse.add(rs.getBoolean("trueFalse"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
}
