/********************************************************************************************************************************************************
Program Name			:	MealMenu.java
Description				:	A MealMenu Model class that is the Model for MealMenu record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	4-August-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	MealMenu()
						: 	MealMenu(String, String, String, double, boolean, boolean)
						:	CREATE_MEAL_MENU() : Boolean
						:	DELETE_MEAL_MENU(String) : Boolean
						:	RETRIEVE_MEAL_MENU_BY_ID(String) : DefaultTableModel
						:	RETRIEVE_MEAL_MENU(String) : DefaultTableModel
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class MealMenu {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Entertainment data;							//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String mealID;
	private String mealMenuID;
	private String mealMenuName;
	private String mealMenuDescription;
	private double mealMenuPrice;
	private boolean mealMenuHalal;
	private boolean MealMenuVegetarian;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public MealMenu(){}
	public MealMenu(String ID, String Name, String Description, double Price, boolean Halal, boolean Vegetarian ){
		this.mealID=ID;
		this.mealMenuName=Name;
		this.mealMenuDescription=Description;
		this.mealMenuPrice=Price;
		this.mealMenuHalal=Halal;
		this.MealMenuVegetarian=Vegetarian;	
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: CREATE_MEAL_MENU()
	 * Input Parameter 	: void 
	 * Return 			: Boolean
	 * Purpose 			: To create a new MealMenu record
	 *******************************************************/
	public boolean CREATE_MEAL_MENU(){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "INSERT INTO Meal_Menu (`mealID`, `mealMenuName`, `mealMeuDescription`, `mealMenuPrice`, `mealMenuHalal`, `mealMenuVegetarian`)"; 
		sqlQuery +="VALUES ("+this.mealID+", '"+this.mealMenuName+"', '"+this.mealMenuDescription+"', "+this.mealMenuPrice+", "+this.mealMenuHalal+", "+this.MealMenuVegetarian+")";

		try{
			DB.updateRequest(sqlQuery);
			success=true;
		}
		catch (Exception e) {
		   System.out.println("Failed to Create new Meal Menu record");
		}
		finally{
		}

		return success;
	}

	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: DELETE_MEAL_MENU()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an MealMenu record
	  *******************************************************/
	public boolean DELETE_MEAL_MENU(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Meal_Menu WHERE `mealID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to delete Meal Menu record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_MEAL_MENU_BY_ID()
	 * Input Parameter 	: String
	 * Purpose 			: To retrieve EntertainmentMenu record 
	 * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel RETRIEVE_MEAL_MENU_BY_ID(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Meal_Menu WHERE`mealID`='"+ID+"'";
		
		model.setColumnIdentifiers(new Object[]{"Meal Name","Price/Hr","Halal","Vegetarian","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				String hala = "";
				String vegetarian="";
				if(rs.getString("mealMenuHalal").equals("1"))
					hala="YES";
				if(rs.getString("mealMenuHalal").equals("0"))
					hala="NO";
				if(rs.getString("mealMenuVegetarian").equals("1"))
					 vegetarian="YES";
				if(rs.getString("mealMenuVegetarian").equals("0"))
					 vegetarian="NO";

				model.addRow(new Object[]{rs.getString("mealMenuName"),rs.getString("mealMenuPrice"),hala,vegetarian,rs.getString("mealMeuDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		return model;
	}
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_MEAL_MENU()
	 * Input Parameter 	: String
	 * Purpose 			: To retrieve EntertainmentMenu record 
	 * Return 			: DefaultTableModel
	 *******************************************************/
	public DefaultTableModel RETRIEVE_MEAL_MENU(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Meal_Menu WHERE ( mealMenuID LIKE '"+parameter+"%'";
		sqlQuery+=" OR mealID LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealMenuName LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealMeuDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealMenuPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealMenuHalal LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealMenuVegetarian LIKE'"+parameter+"%')";
		
		model.setColumnIdentifiers(new Object[]{"ID"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(2)});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		return model;
	}
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Entertainment getData() {
		return data;
	}
	public void setData(Entertainment data) {
		this.data = data;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getMealID() {
		return mealID;
	}
	public void setMealID(String mealID) {
		this.mealID = mealID;
	}
	public String getMealMenuID() {
		return mealMenuID;
	}
	public void setMealMenuID(String mealMenuID) {
		this.mealMenuID = mealMenuID;
	}
	public String getMealMenuName() {
		return mealMenuName;
	}
	public void setMealMenuName(String mealMenuName) {
		this.mealMenuName = mealMenuName;
	}
	public String getMealMenuDescription() {
		return mealMenuDescription;
	}
	public void setMealMenuDescription(String mealMenuDescription) {
		this.mealMenuDescription = mealMenuDescription;
	}
	public double getMealMenuPrice() {
		return mealMenuPrice;
	}
	public void setMealMenuPrice(double mealMenuPrice) {
		this.mealMenuPrice = mealMenuPrice;
	}
	public boolean isMealMenuHalal() {
		return mealMenuHalal;
	}
	public void setMealMenuHalal(boolean mealMenuHalal) {
		this.mealMenuHalal = mealMenuHalal;
	}
	public boolean isMealMenuVegetarian() {
		return MealMenuVegetarian;
	}
	public void setMealMenuVegetarian(boolean mealMenuVegetarian) {
		MealMenuVegetarian = mealMenuVegetarian;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (MealMenuVegetarian ? 1231 : 1237);
		result = prime * result + ((mealID == null) ? 0 : mealID.hashCode());
		result = prime
				* result
				+ ((mealMenuDescription == null) ? 0 : mealMenuDescription
						.hashCode());
		result = prime * result + (mealMenuHalal ? 1231 : 1237);
		result = prime * result
				+ ((mealMenuID == null) ? 0 : mealMenuID.hashCode());
		result = prime * result
				+ ((mealMenuName == null) ? 0 : mealMenuName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(mealMenuPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealMenu other = (MealMenu) obj;
		if (MealMenuVegetarian != other.MealMenuVegetarian)
			return false;
		if (mealID == null) {
			if (other.mealID != null)
				return false;
		} else if (!mealID.equals(other.mealID))
			return false;
		if (mealMenuDescription == null) {
			if (other.mealMenuDescription != null)
				return false;
		} else if (!mealMenuDescription.equals(other.mealMenuDescription))
			return false;
		if (mealMenuHalal != other.mealMenuHalal)
			return false;
		if (mealMenuID == null) {
			if (other.mealMenuID != null)
				return false;
		} else if (!mealMenuID.equals(other.mealMenuID))
			return false;
		if (mealMenuName == null) {
			if (other.mealMenuName != null)
				return false;
		} else if (!mealMenuName.equals(other.mealMenuName))
			return false;
		if (Double.doubleToLongBits(mealMenuPrice) != Double
				.doubleToLongBits(other.mealMenuPrice))
			return false;
		return true;
	}
}
