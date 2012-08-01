package entity;

import java.sql.ResultSet;
import java.util.ArrayList;

import controller.MySQLController;

public class RatingResult extends FeedbackResult {
	private static MySQLController db;
	private ArrayList<Integer> fqCode, rating;
	
	public RatingResult(){
		db = new MySQLController();
		fqCode = new ArrayList<Integer>();
		rating = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getFqCode() {
		return fqCode;
	}
	
	public void setFqCode(int fqCode) {
		this.fqCode.add(fqCode);
	}
	
	public ArrayList<Integer> getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating.add(rating);
	}
	
	public boolean createRatingResult(int frCode, int fqCode, int result){
		boolean success = false;

		String sql = "INSERT INTO saharp5_adeel_school.Rating_Result(frCode, fqCode, result) ";
		sql += "VALUES (" + frCode + ", " + fqCode + ", " + result + ")";

		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
	public boolean retrieveRatingResult(String condition){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Rating_Result";
		
		if (condition != null){
			dbQuery += condition;
		}
		
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					setCode(rs.getInt("frCode"));
					fqCode.add(rs.getInt("fqCode"));
					rating.add(rs.getInt("result"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
}
