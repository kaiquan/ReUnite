/********************************************************************************************************************************************************
Program Name			:	Ballroom.java
Description				:	A Ballroom Model class that is the Model for Ballroom record(s)
Done by					:	Lee Kai Quan,A AMEENUDEEN (111942S)
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S),A Ameenudeen (111942S)
METHODS LIST 			: 	Ballroom();
						: 	Ballroom(String, String, String, String, double, double, double, int, boolean);
						: 	createBallroom() : String
						: 	deleteBallroom(String): Boolean
						: 	deleteBallroomByFID(String) : Boolean
						: 	updateBallroom(String) : Boolean
						: 	retrieveBallroom() : DefaultTableModel
						: 	retrieveBallroom(String) : DefaultTableModel
						: 	retrieveBallroomByID(String) : Balroom
						: 	retrieveBallroomModel(String ID) : DefaultTableModel
						: 	retrieveBallroomByFacility(String) : DefaultTableModel
						: 	checkRelationship(String) : Boolean
						:	GET_BALLROOM_DETAILS(String):	ArrayList<String>
						: 	Accessor Methods()...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

// TODO: Auto-generated Javadoc
/**
 * The Class Ballroom.
 */
public class Ballroom {
	
	/** ****************************************************** The Attributes *****************************************************. */
	
	private Ballroom data=null;								//stores data from the controler;
	
	/** The rs. */
	private ResultSet rs=null;								//result set to ertrive items directly from database
	
	/** The db. */
	private static MySQLController DB = new MySQLController();
	
	/** The ballroom id. */
	private String ballroomID=null;
	
	/** The facility id. */
	private String facilityID=null;
	
	/** The facility. */
	private Facility facility = null;
	
	/** The ballroom name. */
	private String ballroomName=null;
	
	/** The ballroom description. */
	private String ballroomDescription=null;
	
	/** The ballroom size. */
	private String ballroomSize=null;
	
	/** The ballroom discount. */
	private double ballroomDiscount=0.00;
	
	/** The ballroom price. */
	private double ballroomPrice=0.00;
	
	/** The ballroom final price. */
	private double ballroomFinalPrice=0.00;
	
	/** The ballroom hits. */
	private int ballroomHits=0;
	
	/** The ballroom availability. */
	private boolean ballroomAvailability=false;
	
	/**
	 * ******************************************************
	 * The Constructor(s)
	 * *****************************************************.
	 */
	public Ballroom(){}
	
	/**
	 * Instantiates a new ballroom.
	 *
	 * @param ID the id
	 * @param Name the name
	 * @param Description the description
	 * @param Size the size
	 * @param Discount the discount
	 * @param Price the price
	 * @param FinalPrice the final price
	 * @param Hits the hits
	 * @param Availability the availability
	 */
	public Ballroom(String ID, String Name, String Description, String Size, double Discount, double Price, double FinalPrice, int Hits, boolean Availability){
		this.facilityID=ID;
		this.ballroomName=Name;
		this.ballroomDescription=Description;
		this.ballroomSize=Size;
		this.ballroomDiscount=Discount;
		this.ballroomPrice=Price;
		this.ballroomFinalPrice=FinalPrice;
		this.ballroomHits=Hits;
		this.ballroomAvailability=Availability;
	}
	
	/**
	 * ******************************************************
	 * The Method(s)
	 * *****************************************************.
	 *
	 * @return the string
	 */
	
	/********************************************************
	 * Method Name 		: createBallroom()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To create a new Ballroom record
	 * 					  Returns the Created BallroomID
	 *******************************************************/
	public String CREATE_BALLROOM(){
		String ballroomID="";
		String sqlQuery;

		sqlQuery = "INSERT INTO Ballroom (`ballroomName`, `ballroomDescription`, `ballroomSize`, `ballroomDiscount`, `ballroomPrice`, `ballroomFinalPrice`, `ballroomHits`, `facilityID`,`ballroomAvailability` )"; 
		sqlQuery +="VALUES ('"+this.ballroomName+"', '"+this.ballroomDescription+"', '"+this.ballroomSize+"', "+this.ballroomDiscount+", "+this.ballroomPrice+", "+this.ballroomFinalPrice+", "+this.ballroomHits+", "+this.facilityID+", "+this.ballroomAvailability+")";

		try{
			DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed To Create Ballroom Record");
		}
		finally{
		}

		//RETRIEVING THE CREATED BALLROOM ID
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				ballroomID=rs.getString("ballroomID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed To Retrieve BallroomID");
		}
		finally{
		}
		return ballroomID;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: deleteBallroom()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an Ballroom record
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return true, if successful
	 */
	public boolean DELETE_BALLROOM(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Ballroom WHERE `ballroomID`='"+ID+"';";
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
	
	/**
	 * ******************************************************
	 * Method Name		: deleteBallroomByFID()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an Ballroom record
	 * by facility ID
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return true, if successful
	 */
	public boolean DELETE_BALLROOM_BY_FID(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Ballroom WHERE `facilityID`='"+ID+"';";
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
	
	/**
	 * ******************************************************
	 * Method Name		: updateBallroom
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To update an Ballroom record
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return true, if successful
	 */
	public boolean UPDATE_BALLROOM(String ID){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "UPDATE Ballroom SET `ballroomName`='"+this.ballroomName+"', `ballroomDescription`='"+this.ballroomDescription+"', `ballroomSize`='"+this.ballroomSize+"', `ballroomDiscount`="+this.ballroomDiscount+", `ballroomPrice`="+this.ballroomPrice+", `ballroomFinalPrice`="+this.ballroomFinalPrice+", `ballroomAvailability`="+this.ballroomAvailability+" WHERE `ballroomID`='"+ID+"'";
		int i = 0;

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
		
		return success;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: retrieveBallroom
	 * Input Parameter 	: void
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * where is not purchase record
	 * *****************************************************.
	 *
	 * @return the default table model
	 */
	public DefaultTableModel RETRIEVE_BALLROOM(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Ballroom";
		
		model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("ballroomID"),rs.getString("ballroomName"),rs.getString("ballroomSize"),rs.getString("ballroomFinalPrice"),rs.getString("ballroomDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Ballroom record");
		}
		finally{
		}
		return model;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: retrieveBallroom
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * where is not purchase record
	 * & by input paramater
	 * *****************************************************.
	 *
	 * @param parameter the parameter
	 * @return the default table model
	 */
	public DefaultTableModel RETRIEVE_BALLROOM(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Ballroom WHERE (facilityID LIKE '"+parameter+"%'";
		sqlQuery+=" OR ballroomName LIKE'"+parameter+"%'";
		sqlQuery+=" OR ballroomDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR ballroomSize LIKE'"+parameter+"%'";
		sqlQuery+=" OR ballroomDiscount LIKE'"+parameter+"%'";
		sqlQuery+=" OR ballroomPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR ballroomFinalPrice LIKE'"+parameter+"%')";
		
		model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("ballroomID"),rs.getString("ballroomName"),rs.getString("ballroomSize"),rs.getString("ballroomFinalPrice"),rs.getString("ballroomDescription")});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Ballroom record");
		}
		finally{
		}
		return model;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: retrieveBallroomByID
	 * Input Parameter 	: String
	 * Return 			: Ballroom
	 * Purpose 			: To retrieve a Ballroom record
	 * by ballroomID
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return the ballroom
	 */
	public Ballroom RETRIEVE_BALLROOM_BY_ID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM Ballroom WHERE `ballroomID`='"+ID+"'";
		data= new Ballroom();
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setFacilityID(rs.getString("facilityID"));
				data.setBallroomID(rs.getString("ballroomID"));
				data.setBallroomAvailability(rs.getBoolean("ballroomAvailability"));
				data.setBallroomName(rs.getString("ballroomName"));
				data.setBallroomDescription(rs.getString("ballroomDescription"));
				data.setBallroomSize(rs.getString("ballroomSize"));
				data.setBallroomDiscount(rs.getDouble("ballroomDiscount"));
				data.setBallroomPrice(rs.getDouble("ballroomPrice"));
				data.setBallroomFinalPrice(rs.getDouble("ballroomFinalPrice"));
				data.setBallroomHits(rs.getInt("ballroomHits"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Ballroom record");
		}
		finally{
		}
		return data;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: retrieveBallroomModel()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * by ballroomID
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return the default table model
	 */
	public DefaultTableModel RETRIEVE_BALLROOM_MODEL(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Ballroom WHERE `facilityID`='"+ID+"'";
	
		model.setColumnIdentifiers(new Object[]{"Ballroom Name","Price","Entitled Discount","Size","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(2),rs.getString(6),rs.getString(5),rs.getString(4),rs.getString(3)});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Ballroom record");
		}
		finally{
		}
		return model;
	}
	
	/**
	 * ******************************************************
	 * Method Name		: retrieveBallroomByFacility()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * by ballroomID
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return the default table model
	 */
	public DefaultTableModel RETRIEVE_BALLROOM_BY_FACILITY(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Ballroom WHERE `facilityID`='"+ID+"'";
	
		model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(7),rs.getString(3)});
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve Ballroom record");
		}
		finally{
		}
		return model;
	}
	
	/**
	 * ******************************************************
	 * Method Name 	: checkRelationship()
	 * Input Parameter : String
	 * Return 			: boolean
	 * Purpose 		: To check if the ballroom is
	 * tied to a package
	 * *****************************************************.
	 *
	 * @param ID the id
	 * @return true, if successful
	 */
	public boolean CHECK_RELATIONSHIP(String ID){
		boolean ties=false;
		String sqlQuery;
		sqlQuery = "SELECT * FROM Package WHERE `ballroomID`='"+ID+"'";
		
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
	
	
	/**
	 * ******************************************************
	 * Author			: A Ameenudeen(111942S)
	 * Method Name		: GET_BALLROOM_DETAILS
	 * Input Parameter 	: String eventName
	 * Return 			: ArrayList<String>
	 * Purpose 			: To get the ballroom details
	 * *****************************************************.
	 *
	 * @param eventName the event name
	 * @return the array list
	 */
	public ArrayList<Ballroom> GET_BALLROOM_DETAILS(String eventName){
		
		
		ArrayList<Ballroom> e1 = new ArrayList<Ballroom>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT b.ballroomName,b.ballroomFinalPrice FROM Event e INNER JOIN Package p On e.packageID=p.packageID INNER JOIN Ballroom b On p.ballroomID=b.ballroomID  WHERE e.eventName="+"'"+eventName+"'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				Ballroom b= new Ballroom();
				b.setBallroomName(rs.getString("ballroomName"));
				b.setBallroomFinalPrice(rs.getFloat("ballroomFinalPrice"));
				e1.add(b);
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
			
			for (int i=0; i<e1.size(); i++)
			{
				System.out.println(e1.get(i));
			}
			
			return e1;
		}
	
	
	/**
	 * ******************************************************
	 * The Accessor Methods
	 * *****************************************************.
	 *
	 * @return the data
	 */
	public Ballroom getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Ballroom data) {
		this.data = data;
	}
	
	/**
	 * Gets the rs.
	 *
	 * @return the rs
	 */
	public ResultSet getRs() {
		return rs;
	}
	
	/**
	 * Sets the rs.
	 *
	 * @param rs the new rs
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID()
	{
		return Integer.parseInt(ballroomID);
	}
	
	/**
	 * Sets the id.
	 *
	 * @param ballroomID the new id
	 */
	public void setID(int ballroomID)
	{
		this.ballroomID = Integer.toString(ballroomID);
	}
	
	/**
	 * Gets the ballroom id.
	 *
	 * @return the ballroom id
	 */
	public String getBallroomID() {
		return ballroomID;
	}
	
	/**
	 * Sets the ballroom id.
	 *
	 * @param ballroomID the new ballroom id
	 */
	public void setBallroomID(String ballroomID) {
		this.ballroomID = ballroomID;
	}
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public String getFacilityID() {
		return facilityID;
	}
	
	/**
	 * Sets the facility id.
	 *
	 * @param facilityID the new facility id
	 */
	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}
	
	/**
	 * Gets the ballroom name.
	 *
	 * @return the ballroom name
	 */
	public String getBallroomName() {
		return ballroomName;
	}
	
	/**
	 * Sets the ballroom name.
	 *
	 * @param ballroomName the new ballroom name
	 */
	public void setBallroomName(String ballroomName) {
		this.ballroomName = ballroomName;
	}
	
	/**
	 * Gets the ballroom description.
	 *
	 * @return the ballroom description
	 */
	public String getBallroomDescription() {
		return ballroomDescription;
	}
	
	/**
	 * Sets the ballroom description.
	 *
	 * @param ballroomDescription the new ballroom description
	 */
	public void setBallroomDescription(String ballroomDescription) {
		this.ballroomDescription = ballroomDescription;
	}
	
	/**
	 * Gets the ballroom size.
	 *
	 * @return the ballroom size
	 */
	public String getBallroomSize() {
		return ballroomSize;
	}
	
	/**
	 * Sets the ballroom size.
	 *
	 * @param ballroomSize the new ballroom size
	 */
	public void setBallroomSize(String ballroomSize) {
		this.ballroomSize = ballroomSize;
	}
	
	/**
	 * Gets the ballroom discount.
	 *
	 * @return the ballroom discount
	 */
	public double getBallroomDiscount() {
		return ballroomDiscount;
	}
	
	/**
	 * Sets the ballroom discount.
	 *
	 * @param ballroomDiscount the new ballroom discount
	 */
	public void setBallroomDiscount(double ballroomDiscount) {
		this.ballroomDiscount = ballroomDiscount;
	}
	
	/**
	 * Gets the ballroom price.
	 *
	 * @return the ballroom price
	 */
	public double getBallroomPrice() {
		return ballroomPrice;
	}
	
	/**
	 * Sets the ballroom price.
	 *
	 * @param ballroomPrice the new ballroom price
	 */
	public void setBallroomPrice(double ballroomPrice) {
		this.ballroomPrice = ballroomPrice;
	}
	
	/**
	 * Gets the ballroom final price.
	 *
	 * @return the ballroom final price
	 */
	public double getBallroomFinalPrice() {
		return ballroomFinalPrice;
	}
	
	/**
	 * Sets the ballroom final price.
	 *
	 * @param ballroomFinalPrice the new ballroom final price
	 */
	public void setBallroomFinalPrice(double ballroomFinalPrice) {
		this.ballroomFinalPrice = ballroomFinalPrice;
	}
	
	/**
	 * Gets the ballroom hits.
	 *
	 * @return the ballroom hits
	 */
	public int getBallroomHits() {
		return ballroomHits;
	}
	
	/**
	 * Sets the ballroom hits.
	 *
	 * @param ballroomHits the new ballroom hits
	 */
	public void setBallroomHits(int ballroomHits) {
		this.ballroomHits = ballroomHits;
	}
	
	/**
	 * Sets the ballroom availability.
	 *
	 * @param ballroomAvailability the new ballroom availability
	 */
	public void setBallroomAvailability(boolean ballroomAvailability) {
		this.ballroomAvailability = ballroomAvailability;
	}
	
	/**
	 * Checks if is ballroom availability.
	 *
	 * @return true, if is ballroom availability
	 */
	public boolean isBallroomAvailability() {
		return ballroomAvailability;
	}
	
	/**
	 * Gets the facility.
	 *
	 * @return the facility
	 */
	public Facility getFacility() {
		return facility;
	}
	
	/**
	 * Sets the facility.
	 *
	 * @param facility the new facility
	 */
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
}
