/********************************************************************************************************************************************************
Program Name			:	Facility.java
Description				:	A Facility Model class that is the Model for Facility record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-30-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Facility()
						:	Facility(String, String, String, String, String, boolean, double)
						:	createFacility() : String
						:	deleteFacility(String) : Boolean
						:	updateFacility(String)	: Boolean
						: 	retrieveFacility() : DefaultTableModel
						:	retrieveFacility(String) : DefaultTableModel
						:	retrieveFacilityByID(String) : Facility
						:	retrieveFacilityNames() : DefaultTableModel
						:	retrieveFacilityByName(String) :  DefaultTableModel
						: 	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class Facility {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Facility data=null;								//stores data from the controler;
	private ResultSet rs=null;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String facilityID=null;
	private String facilityName=null;
	private String facilityDescription=null;
	private String facilityAddress=null;
	private String facilityContact=null;
	private boolean facilityParking=false;
	private double facilityWeekendExtraCost=0.00;

	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public Facility(){}
	public Facility(String ID, String Name, String Description, String Address, String Contact, boolean Parking, double WeekendExtraCost){
		this.facilityID=ID;
		this.facilityName=Name;
		this.facilityDescription=Description;
		this.facilityAddress=Address;
		this.facilityContact=Contact;
		this.facilityParking=Parking;
		this.facilityWeekendExtraCost=WeekendExtraCost;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: createFacility()
	 * Input Parameter 	: void 
	 * Purpose 			: To create a Facility record
	 * Return 			: String
	 *******************************************************/
	public String CREATE_FACILITY(){
		String sqlQuery;
		String facilityID=null;

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Facility` (`facilityName`, `facilityAddress`, `facilityDescription`, `facilityContact`, `facilityParking`, `facilityWeekendExtraCost`)"; 
		sqlQuery +="VALUES ('"+this.facilityName+"', '"+this.facilityAddress+"', '"+this.facilityDescription+"', '"+this.facilityContact+"', "+this.facilityParking+", "+this.facilityWeekendExtraCost+")";
		try{
			DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to create facility new record");
		}
		finally{
		}

		//RETRIEVING THE CREATED FACILITY ID
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				facilityID=rs.getString("facilityID");
				System.out.println(facilityID);
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve ID");
		}
		finally{
		}
		return facilityID;
	}
	
	/********************************************************
	  * Method Name 	: deleteFacility()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an Facility record
	  *******************************************************/
	public boolean DELETE_FACILITY(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Facility` WHERE `facilityID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to delete facility record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: updateFacility()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To update an Facility record
	  *******************************************************/
	public boolean UPDATE_FACILITY(String ID){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "UPDATE `saharp5_adeel_school`.`Facility` SET `facilityName`='"+this.facilityName+"', `facilityAddress`='"+this.facilityAddress+"', `facilityDescription`='"+this.facilityDescription+"', `facilityContact`='"+this.facilityContact+"', `facilityParking`="+this.facilityParking+", `facilityWeekendExtraCost`="+this.facilityWeekendExtraCost+" WHERE `facilityID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to update facility record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: retrieveFacility()
	  * Input Parameter : void
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Facility record
	  *******************************************************/
	public DefaultTableModel RETRIEVE_FACILITY(){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility";
		
		model.setColumnIdentifiers(new Object[]{"ID","Name","Address","Contact","Parking","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				String parking="Free";
				if(rs.getBoolean("facilityParking")){
					parking="Free";
				}
				else{
					parking="Chargeable";
				}
				model.addRow(new Object[]{rs.getString("facilityID"),rs.getString("facilityName"),rs.getString("facilityAddress"),rs.getString("facilityContact"),parking,rs.getString("facilityDescription")});
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
	  * Method Name 	: retrieveFacility()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all facility record 
	  * 				  like parameter
	  *******************************************************/
	public DefaultTableModel RETRIEVE_FACILITY(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility WHERE (facilityID LIKE '"+parameter+"%'";
		sqlQuery+=" OR facilityName LIKE'"+parameter+"%'";
		sqlQuery+=" OR facilityAddress LIKE'"+parameter+"%'";
		sqlQuery+=" OR facilityDescription LIKE'"+parameter+"%'";
		sqlQuery+=" OR facilityContact LIKE'"+parameter+"%'";
		sqlQuery+=" OR facilityParking LIKE'"+parameter+"%'";
		sqlQuery+=" OR facilityWeekendExtraCost LIKE'"+parameter+"%')";
		
		model.setColumnIdentifiers(new Object[]{"ID","Name","Address","Contact","Parking","Description"});
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				String parking="Free";
				if(rs.getBoolean("facilityParking")){
					parking="Free";
				}
				else{
					parking="Chargeable";
				}
				model.addRow(new Object[]{rs.getString("facilityID"),rs.getString("facilityName"),rs.getString("facilityAddress"),rs.getString("facilityContact"),parking,rs.getString("facilityDescription")});
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
	  * Method Name 	: retrieveFacilityByID()
	  * Input Parameter : String
	  * Return 			: Meal
	  * Purpose 		: To retrieve a facility record by ID
	  *******************************************************/
	public Facility RETRIEVE_FACILITY_BY_ID(String ID){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility WHERE `facilityID`='"+ID+"'";
		data= new Facility();
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setFacilityID(rs.getString("facilityID"));
				data.setFacilityName(rs.getString("facilityName"));
				data.setFacilityAddress(rs.getString("facilityAddress"));
				data.setFacilityContact(rs.getString("facilityContact"));
				data.setFacilityDescription(rs.getString("facilityDescription"));
				data.setFacilityParking(rs.getBoolean("facilityParking"));
				data.setFacilityWeekendExtraCost(rs.getDouble("facilityWeekendExtraCost"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve Facility");
		}
		finally{
		}
		return data;
	}
	
	/********************************************************
	  * Method Name 	: retrieveFacilityNames()
	  * Input Parameter : String
	  * Return 			: DefaultComboBoxModel 
	  * Purpose 		: To retrieve all facility record names
	  *******************************************************/
	public DefaultComboBoxModel<String> RETRIEVE_FACILITY_NAMES(){
		DefaultComboBoxModel<String>  cModel = new DefaultComboBoxModel<String>();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility";
		cModel.addElement("Select a Facility");
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				cModel.addElement(rs.getString("facilityName"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		return cModel;
	}
	
	/********************************************************
	  * Method Name 	: retrieveFacilityByName()
	  * Input Parameter : String
	  * Return 			: Facility 
	  * Purpose 		: To retrieve all facility record by name
	  *******************************************************/
	public Facility RETRIEVE_FACILITY_BY_NAME(String name){
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Facility WHERE `facilityName`='"+name+"'";
		data= new Facility();
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				data.setFacilityID(rs.getString("facilityID"));
				data.setFacilityName(rs.getString("facilityName"));
				data.setFacilityAddress(rs.getString("facilityAddress"));
				data.setFacilityContact(rs.getString("facilityContact"));
				data.setFacilityDescription(rs.getString("facilityDescription"));
				data.setFacilityParking(rs.getBoolean("facilityParking"));
				data.setFacilityWeekendExtraCost(rs.getDouble("facilityWeekendExtraCost"));
			}
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve data");
		}
		finally{
		}
		return data;
	}
	
	
	public ArrayList<Facility> GET_FACILITY(String eventName){
		
		
		ArrayList<Facility> e1 = new ArrayList<Facility>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT f.facilityName FROM Event e INNER JOIN Package p On e.packageID=p.packageID INNER JOIN Ballroom b On p.ballroomID=b.ballroomID INNER JOIN Facility f ON f.facilityID=b.facilityID WHERE e.eventName='"+eventName+"'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				Facility f1 = new Facility();
				f1.setFacilityName(rs.getString("f.facilityName"));
				e1.add(f1);
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
	public Facility getData() {
		return data;
	}
	public void setData(Facility data) {
		this.data = data;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getFacilityID() {
		return facilityID;
	}
	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFacilityDescription() {
		return facilityDescription;
	}
	public void setFacilityDescription(String facilityDescription) {
		this.facilityDescription = facilityDescription;
	}
	public String getFacilityAddress() {
		return facilityAddress;
	}
	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}
	public String getFacilityContact() {
		return facilityContact;
	}
	public void setFacilityContact(String facilityContact) {
		this.facilityContact = facilityContact;
	}
	public boolean isFacilityParking() {
		return facilityParking;
	}
	public void setFacilityParking(boolean facilityParking) {
		this.facilityParking = facilityParking;
	}
	public double getFacilityWeekendExtraCost() {
		return facilityWeekendExtraCost;
	}

	public void setFacilityWeekendExtraCost(double facilityWeekendExtraCost) {
		this.facilityWeekendExtraCost = facilityWeekendExtraCost;
	}
	
	public static void main(String args[]){
		Facility f1 = new Facility();
		f1.GET_FACILITY("FAJC REUNION");
	}
}
