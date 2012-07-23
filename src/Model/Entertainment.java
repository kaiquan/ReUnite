/*******************************************************************************************************************************************************
Program Name			:	Entertainment.java
Description				:	A Entertainment Model class that is the Model for Entertainment record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Entertainment()
						: 	Entertainment(String, boolean, String, String, double, double, double, int, boolean)
						:	createEntertainment() : String
						:	deleteEntertainment(String) : Boolean
						:	updateEntertainment(String) : Boolean
						: 	retrieveEntertainment() : DefaultTableModel
						: 	retrieveEntertainment(String) : DefaultTableModel
						: 	retrieveEntertainmentByID(String) : Entertainment
						: 	updateHits(String) : Boolean
						: 	checkRelationship(String) : Boolean
						:	Accessor Methods..
*******************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;


public class Entertainment {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Entertainment data=null;						//stores data from the controler;
	private ResultSet rs=null;								//result set to rertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String entertainmentID=null;					//Primary Key
	private String entertainmentTitle=null;					//Title or Name of the entertainment
	private String entertainmentDescription=null;			//Some description to the RI
	private boolean entertainmentAvailability=false;		//If the entertainment is available to RI for selection/useage
	private double entertainmentDiscount=0.00;				//The discount for the entertainment if any
	private double entertainmentPrice=0.00;					//The price per hour
	private double entertainmentFinalPrice=0.00;			//The total final price per hour after discount,etc
	private int entertainmentHits=0;						//Number of times this entertainment has been used
	private boolean isRecord=false;							//true if is a ourchase record

	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public Entertainment(){}
	public Entertainment(String ID, boolean Availability, String Description,String Title,double Discount,double Price,double FinalPrice,int Hits, boolean isRecord){
		this.entertainmentID=ID;
		this.entertainmentAvailability=Availability;
		this.entertainmentDescription=Description;
		this.entertainmentTitle=Title;
		this.entertainmentDiscount=Discount;
		this.entertainmentPrice=Price;
		this.entertainmentFinalPrice=FinalPrice;
		this.entertainmentHits=Hits;
		this.isRecord=isRecord;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: createEntertainment()
	 * Input Parameter 	: void 
	 * Return 			: String
	 * Purpose 			: To create a new Entertainment record
	 ********************************************************/
	public String createEntertainment(){
		String sqlQuery;
		String entertainmentID=null;

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Entertainment` (`entertainmentAvailability`, `entertainmentDescription`, `entertainmentTitle`, `entertainmentDiscount`, `entertainmentPrice`, `entertainmentFinalPrice`, `entertainmentHits`, `isRecord`)"; 
		sqlQuery +="VALUES ("+this.entertainmentAvailability+", '"+this.entertainmentDescription+"', '"+this.entertainmentTitle+"', "+this.entertainmentDiscount+", "+this.entertainmentPrice+", "+this.entertainmentFinalPrice+", "+this.entertainmentHits+","+this.isRecord+" )";
		try{
			DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Create Entertainent record");
		}
		finally{
		}
	
		//RETRIEVEING THE CREATES RECORD ID
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				entertainmentID=rs.getString("entertainmentID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Entertainment ID");
		}
		finally{
		}
		return entertainmentID;
	}
	
	 /********************************************************
	  * Method Name 	: deleteEntertainment()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an Entertainment record
	  *******************************************************/
	public boolean deleteEntertainment(String ID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Entertainment` WHERE `entertainmentID`='"+ID+"'";
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
	  * Method Name 	: updateEntertainment()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To update an Entertainment record
	  *******************************************************/
	public boolean updateEntertainment(String ID){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "UPDATE `saharp5_adeel_school`.`Entertainment` SET `entertainmentAvailability`="+this.entertainmentAvailability+", `entertainmentDescription`='"+this.entertainmentDescription+"', `entertainmentTitle`='"+this.entertainmentTitle+"', `entertainmentDiscount`="+this.entertainmentDiscount+", `entertainmentPrice`="+this.entertainmentPrice+", `entertainmentFinalPrice`="+this.entertainmentFinalPrice+" WHERE `entertainmentID`='"+ID+"';";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Update Entertainment Record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	 /********************************************************
	  * Method Name 	: retrieveEntertainment()
	  * Input Parameter : void
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Entertainment record
	  * 				  where is not a purchase Record
	  *******************************************************/
	public DefaultTableModel retrieveEntertainment(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment WHERE `isRecord`=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Price/Hr","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("entertainmentID"),rs.getString("entertainmentTitle"),rs.getString("entertainmentFinalPrice"),rs.getString("entertainmentDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Entertainment Record");
		}
		finally{
		}
		
		
		return model;
	}
	
	/********************************************************
	  * Method Name 	: retrieveEntertainment()
	  * Input Parameter : String paramater
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Entertainment record 
	  * 				  where is not a purchase Record & by
	  * 				  input parameter
	  *******************************************************/
	public DefaultTableModel retrieveEntertainment(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment WHERE (entertainmentID LIKE '"+parameter+"%'";
		sqlQuery+=" OR entertainmentDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentTitle LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentDiscount LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentFinalPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentHits LIKE'"+parameter+"%')";
		sqlQuery+=" AND isRecord=false";
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Price/Hr","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(1),rs.getString(4),rs.getString(7),rs.getString(3)});
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
	  * Method Name 	: retrieveEntertainmenByID()
	  * Input Parameter : String ID
	  * Return 			: Entertainment
	  * Purpose 		: To retrieve a Entertainment record 
	  * 				  By entertainmentID
	  *******************************************************/
	public Entertainment retrieveEntertainmentByID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment WHERE `entertainmentID`='"+ID+"'";
		data= new Entertainment();
		
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setEntertainmentID(rs.getString("entertainmentID"));
				data.setEntertainmentTitle(rs.getString("entertainmentTitle"));
				data.setEntertainmentDescription(rs.getString("entertainmentDescription"));
				data.setEntertainmentAvailability(rs.getBoolean("entertainmentAvailability"));
				data.setEntertainmentDiscount(rs.getDouble("entertainmentDiscount"));
				data.setEntertainmentPrice(rs.getDouble("entertainmentPrice"));
				data.setEntertainmentFinalPrice(rs.getDouble("entertainmentFinalPrice"));
				data.setEntertainmentHits(rs.getInt("entertainmentHits"));
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
	  * Method Name 	: updateHits()
	  * Input Parameter : String ID
	  * Return 			: boolean
	  * Purpose 		: To update Entertainment record hits
	  *******************************************************/
	public boolean updateHits(String ID){
		//RETRIEVE THE RECORD TO UPDATE
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment WHERE `entertainmentID`='"+ID+"'";
		int hits=0;
		
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				hits=(rs.getInt("entertainmentHits"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		hits++;
		
		//UPDATES THE ENTERTAINMENT HITS
		boolean success=false;
		sqlQuery = "UPDATE `saharp5_adeel_school`.`Entertainment` SET  `entertainmentHits`="+hits+" WHERE `entertainmentID`='"+ID+"';";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Update Entertainment Record");
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
	  * Purpose 		: To check if the entertainment is 
	  * 				  tied to a package
	  *******************************************************/
	public boolean checkRelationship(String ID){
		boolean ties=false;
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package WHERE `entertainmentID`='"+ID+"'";
		
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				ties=true;
			}
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Package Data");
		}
		finally{
		}
		return ties;
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
	public String getEntertainmentID() {
		return entertainmentID;
	}
	public void setEntertainmentID(String entertainmentID) {
		this.entertainmentID = entertainmentID;
	}
	public String getEntertainmentTitle() {
		return entertainmentTitle;
	}
	public void setEntertainmentTitle(String entertainmentTitle) {
		this.entertainmentTitle = entertainmentTitle;
	}
	public boolean isEntertainmentAvailability() {
		return entertainmentAvailability;
	}
	public void setEntertainmentAvailability(boolean entertainmentAvailability) {
		this.entertainmentAvailability = entertainmentAvailability;
	}
	public String getEntertainmentDescription() {
		return entertainmentDescription;
	}
	public void setEntertainmentDescription(String entertainmentDescription) {
		this.entertainmentDescription = entertainmentDescription;
	}
	public double getEntertainmentDiscount() {
		return entertainmentDiscount;
	}
	public void setEntertainmentDiscount(double entertainmentDiscount) {
		this.entertainmentDiscount = entertainmentDiscount;
	}
	public double getEntertainmentPrice() {
		return entertainmentPrice;
	}
	public void setEntertainmentPrice(double entertainmentPrice) {
		this.entertainmentPrice = entertainmentPrice;
	}
	public double getEntertainmentFinalPrice() {
		return entertainmentFinalPrice;
	}
	public void setEntertainmentFinalPrice(double entertainmentFinalPrice) {
		this.entertainmentFinalPrice = entertainmentFinalPrice;
	}
	public int getEntertainmentHits() {
		return entertainmentHits;
	}
	public void setEntertainmentHits(int entertainmentHits) {
		this.entertainmentHits = entertainmentHits;
	}
	public boolean isRecord() {
		return isRecord;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
}
