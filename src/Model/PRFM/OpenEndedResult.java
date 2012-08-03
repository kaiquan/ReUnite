package Model.PRFM;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;

public class OpenEndedResult extends FeedbackResult {
	private static MySQLController db;
	private ArrayList<Integer> fqCode;
	private ArrayList<String> result;
	
	public OpenEndedResult(){
		db = new MySQLController();
		fqCode = new ArrayList<Integer>();
		result = new ArrayList<String>();
	}
	
	public ArrayList<Integer> getFqCode() {
		return fqCode;
	}
	
	public void setFqCode(int fqCode) {
		this.fqCode.add(fqCode);
	}
	
	public ArrayList<String> getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result.add(result);
	}
	
	public boolean retrieveOpenEndedResult(String condition){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.OpenEnded_Result";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					setCode(rs.getInt("frCode"));
					fqCode.add(rs.getInt("fqCode"));
					result.add(rs.getString("result"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
	public boolean createOpenEndedResult(int frCode, int fqCode, String result){
		boolean success = false;

		String sql = "INSERT INTO saharp5_adeel_school.OpenEnded_Result(frCode, fqCode, result) ";
		sql += "VALUES (" + frCode + ", " + fqCode + ", '" + result + "')";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
}
