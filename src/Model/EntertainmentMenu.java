/********************************************************************************************************************************************************
Program Name			:	EntertainmentMenu.java
Description				:	A EntertainmentMenu Model class that is the Model for EntertainmentMenu record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	4-August-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S), Adeel M. Ateeque(112013Z)
METHODS LIST			:	EntertainmentMenu()
						: 	EntertainmentMenu(String, String, String, double)
						:	CREATE_ENTERTAINMENT_MENU() : Boolean
						:	DELETE_ENTERTAINMENT_MENU(String) : Boolean
						:	RETRIEVE_ENTERTAINMENT_MENU_BY_ID(String) : DefaultTableModel
						:	RETRIEVE_ENTERTAINMENT_MENU(String) : DefaultTableModel
						:	GET_ENTERTAINMENT_OPTIONS(int)	:  ArrayList<EntertainmentMenu>
						:	Accessor Methods..
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import Controller.MySQLController;
import Model.RIM.TableNames;

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
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: CREATE_ENTERTAINMENT_MENU()
	 * Input Parameter 	: void 
	 * Return 			: boolean
	 * Purpose 			: To create a new EntertainmentMenu record
	 *******************************************************/
	public boolean CREATE_ENTERTAINMENT_MENU(){
		boolean success=false;
		String sqlQuery;
		
		sqlQuery = "INSERT INTO Entertainment_Menu (`entertainmentID`, `entertainmentMenuName`, `entertainmentMenuPrice`, `entertainmentMenuDescription`)"; 
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
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: DELETE_ENTERTAINMENT_MENU()
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To delete an EntertainmentMenu record
	 *******************************************************/
	public boolean DELETE_ENTERTAINMENT_MENU(String ID){
		boolean success=true;
		String sqlQuery;
		
		sqlQuery = "DELETE FROM Entertainment_Menu WHERE `entertainmentID`='"+ID+"'";
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
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_ENTERTAINMENT_MENU_BY_ID()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve EntertainmentMenu record(s)
	 * 				  	  By entertainmentID
	 *******************************************************/
	public DefaultTableModel RETRIEVE_ENTERTAINMENT_MENU_BY_ID(String ID){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Entertainment_Menu WHERE`entertainmentID`='"+ID+"'";
		
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
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_ENTERTAINMENT_MENU()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve an EntertainmentMenu record 
	 * 				 	  By parameter
	 *******************************************************/
	public DefaultTableModel RETRIEVE_ENTERTAINMENT_MENU(String parameter){
		DefaultTableModel model= new DefaultTableModel();
		String sqlQuery;
		sqlQuery = "SELECT * FROM Entertainment_Menu WHERE (";
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
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: GET_ENTERTAINMENT_OPTIONS()
	 * Input Parameter 	: int
	 * Return 			: ArrayList<EntertainmentMenu>
	 * Purpose 			: To retrieve an EntertainmentMenu record 
	 * 				 	  By parameter
	 *******************************************************/
	public ArrayList<EntertainmentMenu> GET_ENTERTAINMENT_OPTIONS(int entertainmentID)
	{
		ArrayList<EntertainmentMenu> optionList = new ArrayList<EntertainmentMenu>();
		
		try
		{
			ResultSet rs = DB.readRequest("SELECT * FROM " + TableNames.ENTERTAINMENT_MENU_TABLE + " WHERE entertainmentID =" + entertainmentID);
			
			while(rs.next())
			{
				EntertainmentMenu option = new EntertainmentMenu();
				option.setEntertainmentMenuDescription(rs.getString("entertainmentMenuDescription"));
				option.setEntertainmentMenuName(rs.getString("entertainmentMenuName"));
				option.setEntertainmentMenuPrice(rs.getFloat("entertainmentMenuPrice"));
				optionList.add(option);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((EntertainmentID == null) ? 0 : EntertainmentID.hashCode());
		result = prime
				* result
				+ ((EntertainmentMenuDescription == null) ? 0
						: EntertainmentMenuDescription.hashCode());
		result = prime
				* result
				+ ((EntertainmentMenuID == null) ? 0 : EntertainmentMenuID
						.hashCode());
		result = prime
				* result
				+ ((EntertainmentMenuName == null) ? 0 : EntertainmentMenuName
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(EntertainmentMenuPrice);
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
		EntertainmentMenu other = (EntertainmentMenu) obj;
		if (EntertainmentID == null) {
			if (other.EntertainmentID != null)
				return false;
		} else if (!EntertainmentID.equals(other.EntertainmentID))
			return false;
		if (EntertainmentMenuDescription == null) {
			if (other.EntertainmentMenuDescription != null)
				return false;
		} else if (!EntertainmentMenuDescription
				.equals(other.EntertainmentMenuDescription))
			return false;
		if (EntertainmentMenuID == null) {
			if (other.EntertainmentMenuID != null)
				return false;
		} else if (!EntertainmentMenuID.equals(other.EntertainmentMenuID))
			return false;
		if (EntertainmentMenuName == null) {
			if (other.EntertainmentMenuName != null)
				return false;
		} else if (!EntertainmentMenuName.equals(other.EntertainmentMenuName))
			return false;
		if (Double.doubleToLongBits(EntertainmentMenuPrice) != Double
				.doubleToLongBits(other.EntertainmentMenuPrice))
			return false;
		return true;
	}
	
}