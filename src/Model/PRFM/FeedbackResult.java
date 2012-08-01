package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import controller.MySQLController;

public abstract class FeedbackResult {
	private static MySQLController db = new MySQLController();
	protected ArrayList<Integer> code = new ArrayList<Integer>(), ffCode = new ArrayList<Integer>();
	protected ArrayList<Date> creationDate = new ArrayList<Date>();
	
	public FeedbackResult(){
	}
	
	public ArrayList<Integer> getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code.add(code);
	}
	
	public ArrayList<Date> getCreationDate() {
		return creationDate;
	}
	
	public ArrayList<Integer> getFfCode() {
		return ffCode;
	}
	
	public void setFfCode(int ffCode) {
		this.ffCode.add(ffCode);
	}
	
	public boolean createFeedbackResult(String creationDate, int ffCode){
		boolean success = false;

		String sql = "INSERT INTO saharp5_adeel_school.Feedback_Result(creationDate, ffCode) ";
		sql += "VALUES ('" + creationDate + "', " + ffCode + ")";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public int retrieveLastCode(){
		int lastCode = 0;
		ResultSet rs = null;
		String dbQuery = "SELECT MAX(code) FROM saharp5_adeel_school.Feedback_Result";
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					lastCode = rs.getInt("MAX(code)");
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return lastCode;
	}
	
	public boolean retrieveFeedbackResult(String condition){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Feedback_Result";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					code.add(rs.getInt("code"));
					creationDate.add(rs.getDate("creationDate"));
					ffCode.add(rs.getInt("ffCode"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
}
