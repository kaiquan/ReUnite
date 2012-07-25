/********************************************************************************************************************************************************
Program Name			:	Ballroom.java
Description				:	A Ballroom Model class that is the Model for Ballroom record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
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
						: 	Accessor Methods()...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class Ballroom {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Ballroom data=null;								//stores data from the controler;
	private ResultSet rs=null;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String ballroomID=null;
	private String facilityID=null;
	private Facility facility = null;
	private String ballroomName=null;
	private String ballroomDescription=null;
	private String ballroomSize=null;
	private double ballroomDiscount=0.00;
	private double ballroomPrice=0.00;
	private double ballroomFinalPrice=0.00;
	private int ballroomHits=0;
	private boolean ballroomAvailability=false;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public Ballroom(){}
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
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
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

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Ballroom` (`ballroomName`, `ballroomDescription`, `ballroomSize`, `ballroomDiscount`, `ballroomPrice`, `ballroomFinalPrice`, `ballroomHits`, `facilityID`,`ballroomAvailability` )"; 
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
	
	/********************************************************
	 * Method Name		: deleteBallroom()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an Ballroom record
	 *******************************************************/
	public boolean DELETE_BALLROOM(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Ballroom` WHERE `ballroomID`='"+ID+"';";
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
	 * Method Name		: deleteBallroomByFID()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an Ballroom record 
	 * 					  by facility ID
	 *******************************************************/
	public boolean DELETE_BALLROOM_BY_FID(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Ballroom` WHERE `facilityID`='"+ID+"';";
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
	 * Method Name		: updateBallroom
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To update an Ballroom record
	 *******************************************************/
	public boolean UPDATE_BALLROOM(String ID){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "UPDATE `saharp5_adeel_school`.`Ballroom` SET `ballroomName`='"+this.ballroomName+"', `ballroomDescription`='"+this.ballroomDescription+"', `ballroomSize`='"+this.ballroomSize+"', `ballroomDiscount`="+this.ballroomDiscount+", `ballroomPrice`="+this.ballroomPrice+", `ballroomFinalPrice`="+this.ballroomFinalPrice+", `ballroomAvailability`="+this.ballroomAvailability+" WHERE `ballroomID`='"+ID+"'";
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
	
	/********************************************************
	 * Method Name		: retrieveBallroom
	 * Input Parameter 	: void
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * 					  where is not purchase record
	 *******************************************************/
	public DefaultTableModel RETRIEVE_BALLROOM(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom";
		
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
	
	/********************************************************
	 * Method Name		: retrieveBallroom
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * 					  where is not purchase record
	 * 					  & by input paramater
	 *******************************************************/
	public DefaultTableModel RETRIEVE_BALLROOM(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom WHERE (facilityID LIKE '"+parameter+"%'";
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
	
	/********************************************************
	 * Method Name		: retrieveBallroomByID
	 * Input Parameter 	: String
	 * Return 			: Ballroom
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public Ballroom RETRIEVE_BALLROOM_BY_ID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom WHERE `ballroomID`='"+ID+"'";
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
	
	/********************************************************
	 * Method Name		: retrieveBallroomModel()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public DefaultTableModel RETRIEVE_BALLROOM_MODEL(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom WHERE `facilityID`='"+ID+"'";
	
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
	
	/********************************************************
	 * Method Name		: retrieveBallroomByFacility()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public DefaultTableModel RETRIEVE_BALLROOM_BY_FACILITY(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Ballroom WHERE `facilityID`='"+ID+"'";
	
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
	
	/********************************************************
	  * Method Name 	: checkRelationship()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To check if the ballroom is 
	  * 				  tied to a package
	  *******************************************************/
	public boolean CHECK_RELATIONSHIP(String ID){
		boolean ties=false;
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Package WHERE `ballroomID`='"+ID+"'";
		
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
	
	//ameen method
	public ArrayList<String> getBallroomDetails(String eventName){
		
		
		ArrayList<String> e1 = new ArrayList<String>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT b.ballroomName,b.ballroomFinalPrice FROM Event e INNER JOIN Package p On e.packageID=p.packageID INNER JOIN Ballroom b On p.ballroomID=b.ballroomID  WHERE e.eventName="+"'"+eventName+"'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				e1.add(rs.getString("ballroomName")+","+ rs.getString("ballroomFinalPrice"));
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
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Ballroom getData() {
		return data;
	}
	public void setData(Ballroom data) {
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
		return Integer.parseInt(ballroomID);
	}
	public void setID(int ballroomID)
	{
		this.ballroomID = Integer.toString(ballroomID);
	}
	public String getBallroomID() {
		return ballroomID;
	}
	public void setBallroomID(String ballroomID) {
		this.ballroomID = ballroomID;
	}
	public String getFacilityID() {
		return facilityID;
	}
	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}
	public String getBallroomName() {
		return ballroomName;
	}
	public void setBallroomName(String ballroomName) {
		this.ballroomName = ballroomName;
	}
	public String getBallroomDescription() {
		return ballroomDescription;
	}
	public void setBallroomDescription(String ballroomDescription) {
		this.ballroomDescription = ballroomDescription;
	}
	public String getBallroomSize() {
		return ballroomSize;
	}
	public void setBallroomSize(String ballroomSize) {
		this.ballroomSize = ballroomSize;
	}
	public double getBallroomDiscount() {
		return ballroomDiscount;
	}
	public void setBallroomDiscount(double ballroomDiscount) {
		this.ballroomDiscount = ballroomDiscount;
	}
	public double getBallroomPrice() {
		return ballroomPrice;
	}
	public void setBallroomPrice(double ballroomPrice) {
		this.ballroomPrice = ballroomPrice;
	}
	public double getBallroomFinalPrice() {
		return ballroomFinalPrice;
	}
	public void setBallroomFinalPrice(double ballroomFinalPrice) {
		this.ballroomFinalPrice = ballroomFinalPrice;
	}
	public int getBallroomHits() {
		return ballroomHits;
	}
	public void setBallroomHits(int ballroomHits) {
		this.ballroomHits = ballroomHits;
	}
	public void setBallroomAvailability(boolean ballroomAvailability) {
		this.ballroomAvailability = ballroomAvailability;
	}
	public boolean isBallroomAvailability() {
		return ballroomAvailability;
	}
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
}
