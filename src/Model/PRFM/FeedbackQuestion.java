package Model.PRFM;

import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import Controller.MySQLController;

public class FeedbackQuestion {
	private static MySQLController db;
	private ArrayList<Integer> code;
	private ArrayList<Date> creationDate;
	private ArrayList<String> type, question;
	private ArrayList<String> typeOption;

	public FeedbackQuestion(){
		db = new MySQLController();
		code = new ArrayList<Integer>();
		creationDate = new ArrayList<Date>();
		type = new ArrayList<String>();
		question = new ArrayList<String>();
		
		typeOption = new ArrayList<String>();
		typeOption.add("Yes/No");
		typeOption.add("Rating");
		typeOption.add("Open-ended");
	}
	
	public ArrayList<String> getTypeOption(){
		return typeOption;
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

	public ArrayList<String> getType() {
		return type;
	}

	public void setType(String type) {
		this.type.add(type);
	}

	public ArrayList<String> getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question.add(question);
	}
	
	public boolean createFeedbackQuestion(String creationDate, String type, String question){
		boolean success = false;
		
		String sql = "INSERT INTO saharp5_adeel_school.Feedback_Question(creationDate, type, question) ";
		sql += "VALUES ('" + creationDate + "', '" + type + "', '" + question + "')";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean retrieveFeedbackQuestion(String condition){
		boolean success = false;
		ResultSet rs = null;
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Feedback_Question";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					code.add(rs.getInt("code"));
				    creationDate.add(rs.getDate("creationDate"));
				    
					String temp = rs.getString("type");
					
					if (temp.equals(typeOption.get(0))){
						type.add(typeOption.get(0));
					}
					else if (temp.equals(typeOption.get(1))){
						type.add(typeOption.get(1));
					}
					else{
						type.add(typeOption.get(2));
					}
					
				    question.add(rs.getString("question"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
	public int retrieveLastCode(){
		int lastCode = 0;
		ResultSet rs = null;
		String dbQuery = "SELECT MAX(code) FROM saharp5_adeel_school.Feedback_Question";
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
	
	public boolean updateFeedbackQuestion(int code, String type, String question){
		boolean success = false;
		
		String sql = "UPDATE saharp5_adeel_school.Feedback_Question SET type = '" + type + "', question = '" + question + "' WHERE code = " + code;

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean deleteFeedbackQuestion(int code){
		boolean success = false;
		String sql = "DELETE FROM saharp5_adeel_school.Feedback_Question WHERE code = " + code;

		if (db.updateRequest(sql) == 1)
			success = true;

		return success;
	}
}
