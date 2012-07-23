/********************************************************************************************************************************************************
Program Name			:	Package.java
Description				:	A Package Model class that is the Model for Package record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-8-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Pacakge()
						: 	Package(String, String, String, String, String, String, boolean, int, double, boolean)
						:	createPackage(String, String, String) ; String
						:	deletePackage(String) : Boolean
						:	updatePackage(String, String, String) : Boolean
						:	retrievePackage() : DefaultTableModel
						:	retrievePackage(String) : DefaultTableModel
						: 	retrievePackageByID(String) : Package
						:	Accessor Methods...
********************************************************************************************************************************************************/

package Model;

import java.sql.ResultSet;

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
	private String packageType;
	private String packageTitle;
	private String packageDescription;
	private boolean packageAvailability;
	private int packageHits;
	private double packageDiscount;
	private boolean isRecord;							//use when customer purchase a package
	
	
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
	 * Method Name 		: createPackage
	 * Input Parameter 	: NIL 
	 * Purpose 			: To create a new Package record
	 * Return 			: String
	 *******************************************************/
	public String createPackage(String mealID1, String mealID2, String mealID3){
		String sqlQuery;
		String packageID=null;

		
		if(this.EntertainmentID.equals("")){
			sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Package` (`ballroomID`, `packageType`, `packageTitle`, `packageDescription`, `packageAvailability`, `packageHits`, `packageDiscount`,`isRecord` )"; 
			sqlQuery +="VALUES ("+this.BallroomID+", '"+this.packageType+"', '"+this.packageTitle+"', '"+this.packageDescription+"', "+this.packageAvailability+", "+this.packageHits+", "+this.packageDiscount+","+this.isRecord+")";
		}
		else{
			sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Package` (`ballroomID`, `entertainmentID`, `packageType`, `packageTitle`, `packageDescription`, `packageAvailability`, `packageHits`, `packageDiscount`,`isRecord`)"; 
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
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package";
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

		//create the meal option
		if(!(mealID1.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.createMealOption(packageID, mealID1);
		}
		if(!(mealID2.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.createMealOption(packageID, mealID2);
		}
		if(!(mealID3.equals("null"))){
			MealOptions meal= new MealOptions();
			meal.createMealOption(packageID, mealID3);
		}
		return packageID;
	}
	
	
	/********************************************************
	  * Method Name 	: deletePackage
	  * Input Parameter : String
	  * Purpose 		: To delete an package record
	  * Return 			: boolean
	  *******************************************************/
	public boolean deletePackage(String ID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Package` WHERE `packageID`='"+ID+"'";
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
		meal.deleteMealOptions(ID);
	
		return success;
	}
	
	/********************************************************
	  * Method Name 	: updatePackage
	  * Input Parameter : String
	  * Purpose			: To update an package record
	  * Return 			: boolean
	  *******************************************************/
	public boolean updatePackage(String mealID1, String mealID2, String mealID3){
		boolean success=false;
		String sqlQuery;
		if(this.EntertainmentID.equals(null)||this.EntertainmentID.equals("")){
			sqlQuery = "UPDATE `saharp5_adeel_school`.`Package` SET `ballroomID`="+this.BallroomID+",`entertainmentID`=null, `packageType`='Standard', `packageTitle`='"+this.packageTitle+"', `packageDescription`='"+this.packageDescription+"', `packageAvailability`="+this.packageAvailability+", `packageDiscount`="+this.packageDiscount+" WHERE `packageID`='"+this.packageID+"'";

		}
		else{
			sqlQuery = "UPDATE `saharp5_adeel_school`.`Package` SET `ballroomID`="+this.BallroomID+", `entertainmentID`="+this.EntertainmentID+", `packageType`='Standard', `packageTitle`='"+this.packageTitle+"', `packageDescription`='"+this.packageDescription+"', `packageAvailability`="+this.packageAvailability+", `packageDiscount`="+this.packageDiscount+" WHERE `packageID`='"+this.packageID+"'";

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
		meal.deleteMealOptions(this.packageID);
		
		//	RECREATE THE MEAL OPTIONS
		if(mealID1!=check){
			meal.createMealOption(packageID, mealID1);
		}
		if(mealID2!=check){
			meal.createMealOption(packageID, mealID2);
		}
		if(mealID3!=check){
			meal.createMealOption(packageID, mealID3);
		}
		
		return success;
	}
	
	 /********************************************************
	  * Method Name 	: retrievePackage
	  * Input Parameter : NIL
	  * Purpose 		: To retrieve all Package record
						  where isRecord =0
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public  DefaultTableModel retrievePackage(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package WHERE `isRecord`=false";
		
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
	  * Method Name 	: retrievePackage
	  * Input Parameter : String paramater
	  * Purpose 		: To retrieve all Package record 
						  where isRecord =0 and like parameter
	  * Return 			: DaultTableModel
	  *******************************************************/
	public DefaultTableModel retrievePackage(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package WHERE (packageID LIKE '"+parameter+"%'";
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
	  * Method Name 	: retrievePackageByID
	  * Input Parameter : String paramater
	  * Purpose 		: To retrieve all Package record by IDr
	  * Return 			: Package
	  *******************************************************/
	public Package retrievePackageByID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package WHERE `packageID`='"+ID+"'";
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
}
