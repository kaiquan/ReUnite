/********************************************************************************************************************************************************
Program Name			:	MealOptions.java
Description				:	A MealOptions Model class that is the Model for MealOptions record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-30-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	MealOptions()
						: 	createMealOption(String, String) : Boolean
						:	deleteMealOptions(String) : Boolean
						:	retrieveMealOptions(String) :ArrayList<String>
						:	Accessor Methods...
********************************************************************************************************************************************************/

package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import Controller.MySQLController;

public class MealOptions {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private MealOptions mealOptions;							//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from d
	private static MySQLController DB = new MySQLController();
	
	private String MealOptionID;
	private String PackageID;
	private String mealID;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public MealOptions(){}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: createMealOption
	 * Input Parameter 	: String , String
	 * Purpose 			: To create a new MealOption record
	 * Return 			: boolean
	 *******************************************************/
	public boolean CREATE_MEAL_OPTION(String packageID, String mealID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "INSERT INTO Meal_Options (`packageID`, `mealID`) VALUES ("+packageID+", "+mealID+")";

		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to create record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	 * Method Name 		: deleteMealOption
	 * Input Parameter 	: String , String
	 * Purpose 			: To create a new MealOption record
	 * Return 			: boolean
	 *******************************************************/
	public boolean DELETE_MEAL_OPTION(String ID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Meal_Options WHERE `packageID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to delete record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		return success;
	}
	
	/********************************************************
	  * Method Name 	: retrieveMealOptions
	  * Input Parameter : String
	  * Purpose 		: To retrieve all mealoption record by packageID
	  * Return 			: ArrayList<String>
	  *******************************************************/
	public ArrayList<String> RETRIEVE_MEAL_OPTIONS(String ID){
		ArrayList<String> mealID = new ArrayList<String>();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM Meal_Options WHERE `packageID`='"+ID+"'";
		
		
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				mealID.add(rs.getString("mealID"));
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve package Record");
		}
		finally{
		}
		return mealID;
	}

	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public MealOptions getMealOptions() {
		return mealOptions;
	}

	public void setMealOptions(MealOptions mealOptions) {
		this.mealOptions = mealOptions;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getMealOptionID() {
		return MealOptionID;
	}

	public void setMealOptionID(String mealOptionID) {
		MealOptionID = mealOptionID;
	}

	public String getPackageID() {
		return PackageID;
	}

	public void setPackageID(String packageID) {
		PackageID = packageID;
	}

	public String getMealID() {
		return mealID;
	}

	public void setMealID(String mealID) {
		this.mealID = mealID;
	}
}
