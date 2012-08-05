package Model.PRFM;

import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;

public class FeedbackForm {
	private static MySQLController db;
	private ArrayList<Integer> code, fqCode, fqOrder;
	private ArrayList<Date> creationDate;
	
	public FeedbackForm(){
		db = new MySQLController();
		code = new ArrayList<Integer>();
		creationDate = new ArrayList<Date>();
		fqCode = new ArrayList<Integer>();
		fqOrder = new ArrayList<Integer>();
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
	
	public void setCreationDate(Date creationDate) {
		this.creationDate.add(creationDate);
	}
	
	public ArrayList<Integer> getFqCode() {
		return fqCode;
	}
	
	public void setFqCode(int fqCode) {
		this.fqCode.add(fqCode);
	}
	
	public ArrayList<Integer> getFqOrder() {
		return fqOrder;
	}
	
	public void setFqOrder(int fqOrder) {
		this.fqOrder.add(fqOrder);
	}
	
	public boolean createFeedbackForm(int code, String creationDate, int fqCode, int fqOrder){
		boolean success = false;

		String sql = "INSERT INTO saharp5_adeel_school.Feedback_Form(code, creationDate, fqCode, fqOrder) ";
		sql += "VALUES (" + code + ", '" + creationDate + "', " + fqCode + ", " + fqOrder + ")";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean retrieveFeedbackForm(String condition){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Feedback_Form";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					code.add(rs.getInt("code"));
				    creationDate.add(rs.getDate("creationDate"));
				    fqCode.add(rs.getInt("fqCode"));
				    fqOrder.add(rs.getInt("fqOrder"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
	public boolean deleteFeedbackForm(String condition){
		boolean success = false;
		
		String sql = "DELETE FROM saharp5_adeel_school.Feedback_Form";
		if (condition != null){
			sql += condition;
		}
		
		if (db.updateRequest(sql) >= 1)
			success = true;
		
		return success;
	}
	
	public boolean updateFeedbackForm(String condition){
		boolean success = false;
		
		String sql = "UPDATE saharp5_adeel_school.Feedback_Form";
		if (condition != null){
			sql += condition;
		}
		
		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public int retrieveLastCode(){
		int lastCode = 0;
		ResultSet rs = null;
		String dbQuery = "SELECT MAX(code) FROM saharp5_adeel_school.Feedback_Form";
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
}
