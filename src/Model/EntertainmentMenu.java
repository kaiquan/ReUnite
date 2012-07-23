/********************************************************************************************************************************************************
Program Name			:	EntertainmentMenu.java
Description				:	A EntertainmentMenu Model class that is the Model for EntertainmentMenu record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-24-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	EntertainmentMenu()
						: 	EntertainmentMenu(String, String, String, double)
						:	createEntertainmentMenu() : Boolean
						:	deleteEntertainmentMenu(String) : Boolean
						:	retrieveEntertainmentMenuByID(String) : DefaultTableModel
						:	retrieveEntertainmentMenu(String) : DefaultTableModel
						:	Accessor Methods..
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;

public class EntertainmentMenu {

	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private EntertainmentMenu data=null;						//stores data from the controler;
	private ResultSet rs=null;								//result set to rertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
	private String EntertainmentMenuID=null;					//The primary key
	private String EntertainmentID=null;						//the foreign key linking to the entertainment it belongs to
	private String EntertainmentMenuName=null;				//name of this entertainment
	private double EntertainmentMenuPrice=0.00;				//the price of this entertainment (before discount)
	private String EntertainmentMenuDescription=null;		//the description of this entertainment
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public EntertainmentMenu(){}
	public EntertainmentMenu(String entertainmentID, String Description, String Name, double Price){
		this.EntertainmentID=entertainmentID;
		this.EntertainmentMenuDescription=Description;
		this.EntertainmentMenuName=Name;
		this.EntertainmentMenuPrice=Price;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: createEntertainmentMenu()
	 * Input Parameter 	: void 
	 * Return 			: boolean
	 * Purpose 			: To create a new EntertainmentMenu record
	 *******************************************************/
	public boolean createEntertainmentMenu(){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Entertainment_Menu` (`entertainmentID`, `entertainmentMenuName`, `entertainmentMenuPrice`, `entertainmentMenuDescription`)"; 
		sqlQuery +="VALUES ('"+EntertainmentID+"', '"+EntertainmentMenuName+"', "+EntertainmentMenuPrice+", '"+EntertainmentMenuDescription+"')";

		try{
			DB.updateRequest(sqlQuery);
			success=true;
		}
		catch (Exception e) {
		   System.out.println("Failed to Create Entertainment Menu");
		}
		finally{
		}
		return success;
	}
	
	/********************************************************
	  * Method Name 	: deleteEntertainmentMenu()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an EntertainmentMenu record
	  *******************************************************/
	public boolean deleteEntertainmentMenu(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM `saharp5_adeel_school`.`Entertainment_Menu` WHERE `entertainmentID`='"+ID+"'";
		int i = 0;

		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed to Delete EntertainmentMenu record...");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: retrieveEntertainmenMenuByID()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve EntertainmentMenu record(s)
	  * 				  By entertainmentID
	  *******************************************************/
	public DefaultTableModel retrieveEntertainmentMenuByID(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment_Menu WHERE`entertainmentID`='"+ID+"'";
		
		model.setColumnIdentifiers(new Object[]{"Entertinment Name","Price/Hr","Description"});

		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				model.addRow(new Object[]{rs.getString("entertainmentMenuName"),rs.getString("entertainmentMenuPrice"),rs.getString("entertainmentMenuDescription")});
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
	  * Method Name 	: retrieveEntertainmentMenu()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve an EntertainmentMenu record 
	  * 				  By parameter
	  *******************************************************/
	public DefaultTableModel retrieveEntertainmentMenu(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM saharp5_adeel_school.Entertainment_Menu WHERE (";
		sqlQuery+=" entertainmentMenuName LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentMenuPrice LIKE'"+parameter+"%'";
		sqlQuery+=" OR entertainmentMenuDescription LIKE'"+parameter+"%')";
		
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
	public EntertainmentMenu(EntertainmentMenu data){
		this.data=data;
	}
	public EntertainmentMenu getData() {
		return data;
	}
	public void setData(EntertainmentMenu data) {
		this.data = data;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getEntertainmentMenuID() {
		return EntertainmentMenuID;
	}
	public void setEntertainmentMenuID(String entertainmentMenuID) {
		EntertainmentMenuID = entertainmentMenuID;
	}
	public String getEntertainmentID() {
		return EntertainmentID;
	}
	public void setEntertainmentID(String entertainmentID) {
		EntertainmentID = entertainmentID;
	}
	public String getEntertainmentMenuName() {
		return EntertainmentMenuName;
	}
	public void setEntertainmentMenuName(String entertainmentMenuName) {
		EntertainmentMenuName = entertainmentMenuName;
	}
	public double getEntertainmentMenuPrice() {
		return EntertainmentMenuPrice;
	}
	public void setEntertainmentMenuPrice(double entertainmentMenuPrice) {
		EntertainmentMenuPrice = entertainmentMenuPrice;
	}
	public String getEntertainmentMenuDescription() {
		return EntertainmentMenuDescription;
	}
	public void setEntertainmentMenuDescription(String entertainmentMenuDescription) {
		EntertainmentMenuDescription = entertainmentMenuDescription;
	}
}