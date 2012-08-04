/********************************************************************************************************************************************************
Program Name			:	Package.java
Description				:	A Package Model class that is the Model for Package record(s)
Done by					:	Lee Kai Quan (114173S), Adeel M. Ateeque(112013Z)
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	4-August-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S), Adeel M. Ateeque(112013Z)
METHODS LIST			:	Pacakge()
						: 	Package(String, String, String, String, String, String, boolean, int, double, boolean)
						:	CREATE_PACKAGE(String, String, String) ; String
						:	DELETE_PACKAGE(String) : Boolean
						:	UPDATE_PACKAGE(String, String, String) : Boolean
						:	RETRIEVE_PACKAGE() : DefaultTableModel
						:	RETRIEVE_PACKAGE(String) : DefaultTableModel
						: 	RETRIEVE_PACKAGE_BY_ID(String) : Package
						:	Accessor Methods...
********************************************************************************************************************************************************/

package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class Package {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Package data;							//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String packageID;
	private String EntertainmentID=null;
	private String BallroomID;
	private Ballroom ballroom;
	private Entertainment entertainment;
	private String packageType;
	private String packageTitle;
	private String packageDescription;
	private boolean packageAvailability;
	private int packageHits;
	private double packageDiscount;
	private boolean isRecord;							//use when customer purchase a package
	private HashSet<Meal> meals = new HashSet<Meal>();
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public Package(){}
	public Package(String ID, String EntertainmentID, String BallroomID, String Type, String Title, String Description, boolean Availability, int Hits, double Discount, boolean isRecord){
		this.packageID=ID;
		this.EntertainmentID=EntertainmentID;
		this.BallroomID=BallroomID;
		this.packageType=Type;
		this.packageTitle=Title;
		this.packageDescription=Description;
		this.packageAvailability=Availability;
		this.packageHits=Hits;
		this.packageDiscount=Discount;
		this.isRecord=isRecord;
	}

	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: CREATE_PACKAGE
	 * Input Parameter 	: String, String, String 
	 * Purpose 			: To create a new Package record
	 * Return 			: String
	 *******************************************************/
	public String CREATE_PACKAGE(String mealID1, String mealID2, String mealID3){
		String sqlQuery;
		String packageID=null;

		
		if(this.EntertainmentID.equals("")){
			sqlQuery = "INSERT INTO Package (`ballroomID`, `packageType`, `packageTitle`, `packageDescription`, `packageAvailability`, `packageHits`, `packageDiscount`,`isRecord` )"; 
			sqlQuery +="VALUES ("+this.BallroomID+", '"+this.packageType+"', '"+this.packageTitle+"', '"+this.packageDescription+"', "+this.packageAvailability+", "+this.packageHits+", "+this.packageDiscount+","+this.isRecord+")";
		}
		else{
			sqlQuery = "INSERT INTO Package (`ballroomID`, `entertainmentID`, `packageType`, `packageTitle`, `packageDescription`, `packageAvailability`, `packageHits`, `packageDiscount`,`isRecord`)"; 
			sqlQuery +="VALUES ("+this.BallroomID+", "+this.EntertainmentID+", '"+this.packageType+"', '"+this.packageTitle+"', '"+this.packageDescription+"', "+this.packageAvailability+", "+this.packageHits+", "+this.packageDiscount+","+this.isRecord+")";
		}
		
		try{
			DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to create new record...");
		}
		finally{
		}
		
		//RETRIEVEING THE CREATED PACKAGE ID
		sqlQuery = "SELECT * FROM Package";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				packageID=rs.getString("packageID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve ID...");
		}
		finally{
		}

		//CREATING THE MEAL OPTION RECORD
		if(!(mealID1.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.CREATE_MEAL_OPTION(packageID, mealID1);
		}
		if(!(mealID2.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.CREATE_MEAL_OPTION(packageID, mealID2);
		}
		if(!(mealID3.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.CREATE_MEAL_OPTION(packageID, mealID3);
		}
		return packageID;
	}
	
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: DELETE_PACKAGE
	 * Input Parameter 	: String
	 * Purpose 			: To delete an package record
	 * Return 			: boolean
	  *******************************************************/
	public boolean DELETE_PACKAGE(String ID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Package WHERE `packageID`='"+ID+"'";
		int i = 0;

		try{
			DB.getConnection();
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to delete record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		//DELETE THE MEAL OPTIONS
		MealOptions meal= new MealOptions();
		meal.DELETE_MEAL_OPTION(ID);
	
		return success;
	}
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: UPDATE_PACKAGE
	 * Input Parameter 	: String, String, String
	 * Purpose			: To update an package record
	 * Return 			: boolean
	  *******************************************************/
	public boolean UPDATE_PACKAGE(String mealID1, String mealID2, String mealID3){
		boolean success=false;
		String sqlQuery;
		if(this.EntertainmentID.equals(null)||this.EntertainmentID.equals("")){
			sqlQuery = "UPDATE Package SET `ballroomID`="+this.BallroomID+",`entertainmentID`=null, `packageType`='Standard', `packageTitle`='"+this.packageTitle+"', `packageDescription`='"+this.packageDescription+"', `packageAvailability`="+this.packageAvailability+", `packageDiscount`="+this.packageDiscount+" WHERE `packageID`='"+this.packageID+"'";

		}
		else{
			sqlQuery = "UPDATE Package SET `ballroomID`="+this.BallroomID+", `entertainmentID`="+this.EntertainmentID+", `packageType`='Standard', `packageTitle`='"+this.packageTitle+"', `packageDescription`='"+this.packageDescription+"', `packageAvailability`="+this.packageAvailability+", `packageDiscount`="+this.packageDiscount+" WHERE `packageID`='"+this.packageID+"'";

		}
		int i = 1;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to update record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		String check="null";
		//	DELETE THE MEAL OPTIONS
		
		MealOptions meal= new MealOptions();
		meal.DELETE_MEAL_OPTION(this.packageID);
		
		//	RECREATE THE MEAL OPTIONS
		if(mealID1!=check){
			meal.CREATE_MEAL_OPTION(packageID, mealID1);
		}
		if(mealID2!=check){
			meal.CREATE_MEAL_OPTION(packageID, mealID2);
		}
		if(mealID3!=check){
			meal.CREATE_MEAL_OPTION(packageID, mealID3);
		}
		
		return success;
	}
	
	 /********************************************************
	  * Author			: Lee Kai Quan(114173S)
	  * Method Name 	: RETRIEVE_PACKAGE
	  * Input Parameter : NIL
	  * Purpose 		: To retrieve all Package record
						  where isRecord =0
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public  DefaultTableModel RETRIEVE_PACKAGE(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM Package WHERE `isRecord`=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Type","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("packageID"),rs.getString("packageTitle"),rs.getString("packageType"),rs.getString("packageDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve package Record");
		}
		finally{
		}
		
		return model;
	}
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_PACKAGE
	 * Input Parameter 	: String
	 * Purpose 			: To retrieve all Package record 
						  where isRecord =0 and like parameter
	 * Return 			: DaultTableModel
	  *******************************************************/
	public DefaultTableModel RETRIEVE_PACKAGE(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM Package WHERE (packageID LIKE '"+parameter+"%'";
		sqlQuery+=" OR ballroomID LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentID LIKE'"+parameter+"%'";
		sqlQuery+=" OR packageType LIKE'"+parameter+"%'";
		sqlQuery+=" OR packageTitle LIKE'"+parameter+"%'";
		sqlQuery+=" OR packageDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR packageAvailability LIKE'"+parameter+"%')";
		sqlQuery+=" AND isRecord=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Type","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("packageID"),rs.getString("packageTitle"),rs.getString("packageType"),rs.getString("packageDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve package Record");
		}
		finally{
		}
		return model;
		
	}
	
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_PACKAGE_BY_ID
	 * Input Parameter 	: String
	 * Purpose 			: To retrieve all Package record by IDr
	 * Return 			: Package
	  *******************************************************/
	public Package RETRIEVE_PACKAGE_BY_ID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM Package WHERE `packageID`='"+ID+"'";
		data= new Package();
		
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setPackageID(rs.getString("packageID"));
				data.setBallroomID(rs.getString("ballroomID"));
				data.setEntertainmentID(rs.getString("entertainmentID"));
				data.setPackageType(rs.getString("packageType"));
				data.setPackageTitle(rs.getString("packageTitle"));
				data.setPackageDescription(rs.getString("packageDescription"));
				data.setPackageAvailability(rs.getBoolean("packageAvailability"));
				data.setPackageHits(rs.getInt("packageHits"));
				data.setPackageDiscount(rs.getDouble("packageDiscount"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		return data;
	}
	
	
	public ArrayList<Package> GET_PACKAGE_DISCOUNT(String eventName){

		ArrayList<Package> e1 = new ArrayList<Package>();
		ResultSet rs = null;

		String dbQuery;


		dbQuery = "SELECT p.packageDiscount FROM Event e INNER JOIN Package p ON e.packageID=p.packageID WHERE e.eventName="+"'"+eventName+"'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				Package p1 = new Package();
				p1.setPackageDiscount(rs.getFloat("p.packageDiscount"));
				e1.add(p1);
				
				for(int i=0;i<e1.size();i++){
					System.out.println(e1.get(i));
				}
				
				
				}
				
				
			}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
				return e1;
		}
	

	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Package getData() {
		return data;
	}
	public void setData(Package data) {
		this.data = data;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public int getID()
	{
		return Integer.parseInt(packageID);
	}
	public void setID(int packageID)
	{
		this.packageID = Integer.toString(packageID);
	}
	public String getPackageID() {
		return packageID;
	}
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
	public String getEntertainmentID() {
		return EntertainmentID;
	}
	public void setEntertainmentID(String entertainmentID) {
		EntertainmentID = entertainmentID;
	}
	public String getBallroomID() {
		return BallroomID;
	}
	public void setBallroomID(String ballroomID) {
		BallroomID = ballroomID;
	}
	public Ballroom getBallroom()
	{
		return ballroom;
	}
	public void setBallroom(Ballroom ballroom)
	{
		this.ballroom = ballroom;
	}
	public Entertainment getEntertainment()
	{
		return entertainment;
	}
	public void setEntertainment(Entertainment entertainment)
	{
		this.entertainment = entertainment;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackageTitle() {
		return packageTitle;
	}
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public boolean isPackageAvailability() {
		return packageAvailability;
	}
	public void setPackageAvailability(boolean packageAvailability) {
		this.packageAvailability = packageAvailability;
	}
	public int getPackageHits() {
		return packageHits;
	}
	public void setPackageHits(int packageHits) {
		this.packageHits = packageHits;
	}
	public double getPackageDiscount() {
		return packageDiscount;
	}
	public void setPackageDiscount(double packageDiscount) {
		this.packageDiscount = packageDiscount;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
	public boolean isRecord() {
		return isRecord;
	}
	
	public void setMeals(Collection<Meal> meals)
	{
		this.meals.addAll(meals);
	}
	
	public void addMeal(Meal meal)
	{
		this.meals.add(meal);
	}
		
}
