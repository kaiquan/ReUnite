package entity;

import java.sql.ResultSet;
import java.util.ArrayList;

import controller.MySQLController;

public class YesNoResult extends FeedbackResult {
	private static MySQLController db;
	private ArrayList<Integer> fqCode;
	private ArrayList<Boolean> trueFalse;
	
	public YesNoResult(){
		db = new MySQLController();
		fqCode = new ArrayList<Integer>();
		trueFalse = new ArrayList<Boolean>();
	}
	
	public ArrayList<Integer> getFqCode() {
		return fqCode;
	}
	
	public void setFqCode(int fqCode) {
		this.fqCode.add(fqCode);
	}
	
	public ArrayList<Boolean> getTrueFalse() {
		return trueFalse;
	}
	
	public void setTrueFalse(boolean trueFalse) {
		this.trueFalse.add(trueFalse);
	}
	
	public boolean createYesNoResult(int frCode, int fqCode, boolean result){
		boolean success = false;

		String sql = "INSERT INTO saharp5_adeel_school.YesNo_Result(frCode, fqCode, result) ";
		sql += "VALUES (" + frCode + ", " + fqCode + ", " + result + ")";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean retrieveYesNoResult(String condition){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.YesNo_Result";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					setCode(rs.getInt("frCode"));
					fqCode.add(rs.getInt("fqCode"));
					trueFalse.add(rs.getBoolean("result"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
}
