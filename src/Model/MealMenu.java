/********************************************************************************************************************************************************
Program Name			:	MealMenu.java
Description				:	A MealMenu Model class that is the Model for MealMenu record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-30-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	MealMenu()
						: 	MealMenu(String, String, String, double, boolean, boolean)
						:	createMealMenu() : Boolean
						:	deleteMealMenu(String) : Boolean
						:	retrieveMealMenuByID(String) : DefaultTableModel
						:	retrieveMealMenu(String) : DefaultTableModel
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
	 * Method Name 		: createMealMenu()
	 * Input Parameter 	: void 
	 * Return 			: Boolean
	 * Purpose 			: To create a new MealMenu record
	 *******************************************************/
	public boolean createMealMenu(){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Meal_Menu` (`mealID`, `mealMenuName`, `mealMeuDescription`, `mealMenuPrice`, `mealMenuHalal`, `mealMenuVegetarian`)"; 
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
	  * Method Name 	: deleteMealMenu()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an MealMenu record
	  *******************************************************/
	public boolean deleteMealMenu(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Meal_Menu` WHERE `mealID`='"+ID+"'";
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
	  * Method Name 	: retrieveMealMenuByID()
	  * Input Parameter : String
	  * Purpose 		: To retrieve EntertainmentMenu record 
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel retrieveMealMenuByID(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal_Menu WHERE`mealID`='"+ID+"'";
		
		model.setColumnIdentifiers(new Object[]{"Meal Name","Price/Hr","Halal","Vegetarian","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("mealMenuName"),rs.getString("mealMenuPrice"),rs.getString("mealMenuHalal"),rs.getString("mealMenuVegetarian"),rs.getString("mealMeuDescription")});
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
	  * Method Name 	: retrieveMealMenu()
	  * Input Parameter : String
	  * Purpose 		: To retrieve EntertainmentMenu record 
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel retrieveMealMenu(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal_Menu WHERE ( mealMenuID LIKE '"+parameter+"%'";
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
}