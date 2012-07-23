/********************************************************************************************************************************************************
Program Name			:	Meal.java
Description				:	A Meal Model class that is the Model for Meal record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-30-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Meal()
						:	Meal(String, String, String, String, double, double, double, boolean, int, boolean)
						:	createMeal() : String
						:	deleteMeal(String) : Boolean
						:	updateMeal(String) : Boolean
						:	retrieveMeal() : DefaultTableModel 
						:	retrieveMeal(String) : DefaultTableModel
						:	retrieveMealByID(String) : Meal
						:	updateHits(String) : Boolean
						:	checkRelationship(String) : Boolean
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class Meal {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Meal data;							//stores data from the controler;
	private ResultSet rs;						//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String mealID=null;
	private String mealTitle=null;
	private String mealDescription=null;
	private String mealType=null;
	private double mealPricePerHead=0.00;
	private double mealDiscount=0.00;
	private double mealFinalPrice=0.00;
	private boolean mealAvailability=false;
	private int mealHits=0;
	private boolean isRecord=false;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public Meal(){}
	public Meal(String ID, String Title, String Description, String Type, double PricePerHead, double Discount, double FinalPrice ,boolean Availability, int Hits, boolean isRecord){
		this.mealID=ID;
		this.mealTitle=Title;
		this.mealDescription=Description;
		this.mealType=Type;
		this.mealPricePerHead=PricePerHead;
		this.mealDiscount=Discount;
		this.mealFinalPrice=FinalPrice;
		this.mealAvailability=Availability;
		this.mealHits=Hits;
		this.setRecord(isRecord);
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: createMeal()
	 * Input Parameter 	: NIL 
	 * Return 			: String
	 * Purpose 			: To create a new Meal record
	 *******************************************************/
	public String createMeal(){
		String sqlQuery;
		String MealID=null;

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Meal` (`mealTitle`, `mealDescription`, `mealType`, `mealPricePerHead`, `mealDiscount`, `mealFinalPrice`, `mealAvailability`, `mealHits`, `isRecord`)"; 
		sqlQuery +="VALUES ('"+this.mealTitle+"', '"+this.mealDescription+"', '"+this.mealType+"', "+this.mealPricePerHead+", "+this.mealDiscount+", "+this.mealFinalPrice+", "+this.mealAvailability+", "+this.mealHits+","+this.isRecord+")";
		System.out.println(sqlQuery);
		try{
			DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to create meal record...");
		}
		finally{
		}
		
		//RETRIEVING THE CREATED ID
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				MealID=rs.getString("mealID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve ID...");
		}
		finally{
		}
		return MealID;
	}
	
	/********************************************************
	  * Method Name 	: deleteMeal()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an Meal record
	  *******************************************************/
	public boolean deleteMeal(String ID){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Meal` WHERE `mealID`='"+ID+"'";
		int i = 0;
		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to delete meal record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: updateMeal()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To update an Meal record
	  *******************************************************/
	public boolean updateMeal(String ID){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "UPDATE `saharp5_adeel_school`.`Meal` SET `mealTitle`='"+this.mealTitle+"', `mealDescription`='"+this.mealDescription+"', `mealType`='"+this.mealType+"', `mealPricePerHead`="+this.mealPricePerHead+", `mealDiscount`="+this.mealDiscount+", `mealFinalPrice`="+this.mealFinalPrice+", `mealAvailability`="+this.mealAvailability+" WHERE `mealID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Update Meal record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: retrieveMeal()
	  * Input Parameter : void
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all meal record where
	  * 				  is not a purchase record
	  *******************************************************/
	public DefaultTableModel retrieveMeal(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal WHERE `isRecord`=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Type","PricePerHead","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getDouble(7),rs.getString(3)});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve meal record");
		}
		finally{
		}
		return model;
	}
	
	/********************************************************
	  * Method Name 	: retrieveMeal()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all meal record where 
	  * 				  is not a purchase record & like 
	  * 				  input parameter
	  *******************************************************/
	public DefaultTableModel retrieveMeal(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal WHERE (mealID LIKE '"+parameter+"%'";
		sqlQuery+=" OR mealTitle LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealType LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealFinalPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealDiscount LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealHits LIKE'"+parameter+"%'";
		sqlQuery+=" OR mealPricePerHead LIKE'"+parameter+"%')";
		sqlQuery+=" AND isRecord=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Type","PricePerHead","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getDouble(7),rs.getString(3)});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve meal record");
		}
		finally{
		}
		return model;
	}
	
	/********************************************************
	  * Method Name 	: retrieveMealByID()
	  * Input Parameter : String 
	  * Return 			: Meal
	  * Purpose 		: To retrieve a Meal record by ID
	  *******************************************************/
	public Meal retrieveMealByID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal WHERE `mealID`='"+ID+"'";
		data= new Meal();
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setMealID(rs.getString("mealID"));
				data.setMealTitle(rs.getString("mealTitle"));
				data.setMealDescription(rs.getString("mealDescription"));
				data.setMealType(rs.getString("mealType"));
				data.setMealPricePerHead(rs.getDouble("mealPricePerHead"));
				data.setMealDiscount(rs.getDouble("mealDiscount"));
				data.setMealFinalPrice(rs.getDouble("mealFinalPrice"));
				data.setMealAvailability(rs.getBoolean("mealAvailability"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve Meal record");
		}
		finally{
		}
		return data;
	}
	
	/********************************************************
	  * Method Name 	: updateHits()
	  * Input Parameter : String 
	  * Return 			: boolean
	  * Purpose 		: To update meal record hits
	  *******************************************************/
	public boolean updateHits(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal WHERE `mealID`='"+ID+"'";
		int hits=0;
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				hits=rs.getInt("mealHits");
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve Meal record");
		}
		finally{
		}
		hits++;
		
		//UPDATES THE MEAL HITS
		boolean success=false;

		sqlQuery = "UPDATE `saharp5_adeel_school`.`Meal` SET  `mealHits`="+hits+" WHERE `mealID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Update Meal recordS");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: checkRelationship()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To check if the meal is tied to a 
	  * 				  package
	  *******************************************************/
	public boolean checkRelationship(String ID){
		boolean ties=false;
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Meal_Options WHERE `mealID`='"+ID+"'";
		
		try{
			ResultSet rs;
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				ties=true;
			}
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve meal option record");
		}
		finally{
		}
		return ties;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Meal getData() {
		return data;
	}
	public void setData(Meal data) {
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
	public String getMealTitle() {
		return mealTitle;
	}
	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}
	public String getMealDescription() {
		return mealDescription;
	}
	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public double getMealPricePerHead() {
		return mealPricePerHead;
	}
	public void setMealPricePerHead(double mealPricePerHead) {
		this.mealPricePerHead = mealPricePerHead;
	}
	public double getMealDiscount() {
		return mealDiscount;
	}
	public void setMealDiscount(double mealDiscount) {
		this.mealDiscount = mealDiscount;
	}
	public double getMealFinalPrice() {
		return mealFinalPrice;
	}
	public void setMealFinalPrice(double mealFinalPrice) {
		this.mealFinalPrice = mealFinalPrice;
	}
	public boolean isMealAvailability() {
		return mealAvailability;
	}
	public void setMealAvailability(boolean mealAvailability) {
		this.mealAvailability = mealAvailability;
	}
	public int getMealHits() {
		return mealHits;
	}
	public void setMealHits(int mealHits) {
		this.mealHits = mealHits;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
	public boolean isRecord() {
		return isRecord;
	}
	
}