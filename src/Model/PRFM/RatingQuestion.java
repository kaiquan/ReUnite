package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import controller.MySQLController;

public class RatingQuestion extends FeedbackQuestion{
	private static MySQLController db;
	private ArrayList<String> choice;
	private ArrayList<Integer> rating;
	
	public RatingQuestion(){
		db = new MySQLController();
		choice = new ArrayList<String>();
		rating = new ArrayList<Integer>();
	}
	
	public void setChoice(String choice) {
		this.choice.add(choice);
	}
	
	public ArrayList<String> getChoice() {
		return choice;
	}

	public void setRating (int rating) {
		this.rating.add(rating);
	}
	
	public ArrayList<Integer> getRating() {
		return rating;
	}

	public boolean retrieveRatingQuestion(){
		boolean success = false;
		ResultSet rs = null;
		
		String dbQuery = "SELECT * FROM saharp5_adeel_school.Rating_Question";
		rs = db.readRequest(dbQuery);
		
		try {
				while (rs.next())   
				{   
					choice.add(rs.getString("choice"));
				    rating.add(rs.getInt("rating"));
				    success = true;
				}   
	
			}
		catch (Exception e){
			e.printStackTrace();
		}

		return success;
	}
	
	public boolean updateRatingQuestion(String condition){
		boolean success = false;
		
		String sql = "UPDATE saharp5_adeel_school.Rating_Question";
		
		if (condition != null){
			sql += condition;
		}
		
		if (db.updateRequest(sql) == 1)
			success = true;
		
		return success;
	}
	
}
