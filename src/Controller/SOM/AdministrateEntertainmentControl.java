/********************************************************************************************************************************************************
Program Name			:	AdministrateEntertainmentControl.java
Description				:	A AdministrateEntertainmentControl class that is the Controller for CRUD of Entertainment record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	AdministrateEntertainmentControl();
						: 	AdministrateEntertainmentControl(String, String, boolean, double, double, double, int, boolean)
						:	processCreateEntertainment() : String
						:	processDeleteEntertainment(String) : Boolean
						:	processUpdateEntertainment(String) : Boolean
						:	processRetrieveEntertainment() : DefaultTableModel
						:	processRetrieveEntertainment(String) : DefaultTableModel
						: 	processRetrieveEntertainmentByID(String) : Entertainment
						:	processRetrieveEntertainmentMenu(String) :	DefaultTableModel
						:	processCalculatePrice() : Double
						:	processCalculateFinalPrice(Double) : Double
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import Model.Entertainment;
import Model.EntertainmentMenu;

public class AdministrateEntertainmentControl {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Entertainment entertainment=null;			
	private EntertainmentMenu entertainmentMenu=null;				
	private DefaultTableModel model=null; 
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public AdministrateEntertainmentControl(){}
	public AdministrateEntertainmentControl(String Title, String Description, boolean Availability, double Discount, double Price, double FinalPrice, int Hits, boolean isRecord){
		Entertainment entertainment=new Entertainment();

		entertainment.setEntertainmentTitle(Title);
		entertainment.setEntertainmentDescription(Description);
		entertainment.setEntertainmentAvailability(Availability);
		entertainment.setEntertainmentDiscount(Discount);
		entertainment.setEntertainmentPrice(Price);
		entertainment.setEntertainmentFinalPrice(FinalPrice);
		entertainment.setEntertainmentHits(Hits);
		entertainment.setRecord(isRecord);
		this.entertainment=entertainment;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/

	/********************************************************
	 * Method Name 		: processCreateEntertainment()
	 * Input Parameter 	: void 
	 * Return 			: String
	 * Purpose 			: To create a new Entertainment record
	 * 					  Returns the Created Entertainment ID
	 *******************************************************/
	public String processCreateEntertainment(){
		String ID=null;
		ID=this.entertainment.CREATE_ENTERTAINMENT();
		
		if(ID.equals(null)){
			return null;
		}
		else{
			//CREATES THE ENTERTAINMENT MENU
			boolean success=false;
			for(int i=0;i<model.getRowCount();i++){
				entertainmentMenu=new EntertainmentMenu(ID,model.getValueAt(i, 2).toString(),model.getValueAt(i, 0).toString(),Double.parseDouble((String) model.getValueAt(i, 1)));
				success=entertainmentMenu.CREATE_ENTERTAINMENT_MENU();
			}
			if(!success){
				processDeleteEntertainment(ID);
				ID=null;
			}
		}
		return ID;
	}
	
	/********************************************************
	  * Method Name 	: processDeleteEntertainment()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To delete an Entertainment record
	  *******************************************************/
	public boolean processDeleteEntertainment(String ID){
		boolean success=false;
		Entertainment entertainment=new Entertainment();
		if(entertainment.CHECK_RELATIONSHIP(ID)){
			return false;
		}
		else{
			success=entertainment.DELETE_ENTERTAINMENT(ID);
			EntertainmentMenu entertainmentMenu=new EntertainmentMenu();
			success=entertainmentMenu.DELETE_ENTERTAINMENT_MENU(ID);
		}
		
		return success;
	}
	
	/********************************************************
	  * Method Name 	: processUpdateEntertainment()
	  * Input Parameter : String
	  * Return 			: boolean
	  * Purpose 		: To update an Entertainment record
	  *******************************************************/
	public boolean processUpdateEntertainment(String ID){
		boolean success=true;
		//UPDATES THE ENTERTAINMENT RECORD
		success=this.entertainment.UPDATE_ENTERTAINMENT(ID);
		
		if(success){
			//DELETES THE ENTERTAINMENT MENU
			EntertainmentMenu menu= new EntertainmentMenu();
			success=menu.DELETE_ENTERTAINMENT_MENU(ID);
			if(success){
				//RECREATES THE ENTERTAINMENT MENU
				success=false;
				for(int i=0;i<model.getRowCount();i++){
					entertainmentMenu=new EntertainmentMenu(ID,model.getValueAt(i, 2).toString(),model.getValueAt(i, 0).toString(),Double.parseDouble((String) model.getValueAt(i, 1)));
					success=entertainmentMenu.CREATE_ENTERTAINMENT_MENU();
				}
				if(!success){
					System.out.println("Failed to re-Create EntertainmentMenu Record");
					return false;
				}
			}
			else{
				System.out.println("Failed to Delete EntertainmentMenu Record");
				return false;
			}
		}
		else{
			System.out.println("Failed to Update Entertainment Record");
			return false;
		}
		return success;
	}
	
	 /********************************************************
	  * Method Name 	: processRetrieveEntertainment()
	  * Input Parameter : void
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Entertainment record 
	  * 				  where is not a purchase record
	  *******************************************************/
	public DefaultTableModel processRetrieveEntertainment(){
		Entertainment entertainment= new Entertainment();
		return entertainment.RETRIEVE_ENTERTAINMENT();
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveEntertainment()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve all Entertainment record 
	  * 				  where is not a purchase record
	  * 				  & by input paramater
	  *******************************************************/
	public DefaultTableModel processRetrieveEntertainment(String parameter){
		Entertainment entertainment= new Entertainment();
		return entertainment.RETRIEVE_ENTERTAINMENT(parameter);
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveEntertainmentByID()
	  * Input Parameter : String
	  * Return 			: Entertainment
	  * Purpose 		: To retrieve an Entertainment record 
	  * 				  By entertainment ID
	  *******************************************************/
	public Entertainment processRetrieveEntertainmentByID(String ID){
		Entertainment entertainment= new Entertainment();
		this.entertainment=entertainment.RETRIEVE_ENTERTAINMENT_BY_ID(ID);
		EntertainmentMenu menu= new EntertainmentMenu();
		this.model=menu.RETRIEVE_ENTERTAINMENT_MENU_BY_ID(ID);
		return this.entertainment;
	}
	
	
	/********************************************************
	  * Method Name 	: processRetrieveEntertainmentMenu()
	  * Input Parameter : String
	  * Return 			: DefaultTableModel
	  * Purpose 		: To retrieve an EntertainmentMenu record 
	  * 				  By parameter
	  *******************************************************/
	public DefaultTableModel processRetrieveEntertainmentMenu(String parameter){
		EntertainmentMenu menu= new EntertainmentMenu();
		return menu.RETRIEVE_ENTERTAINMENT_MENU(parameter);
	}
	
	//BUSINESS LOGICS BELOW
	/********************************************************
	 * Method Name		: processCalculatePrice
	 * Input Parameter 	: void 
	 * Return 			: double
	 * Purpose 			: To caculate the price before discount
	 *******************************************************/
	public double processCalculatePrice(){
		double price=0;
		DecimalFormat fmt = new DecimalFormat("0.00");
		for(int i=0;i<model.getRowCount();i++){
			price=price+Double.parseDouble(model.getValueAt(i, 1).toString());
		}
		return Double.parseDouble(fmt.format(price));
	}
	
	/********************************************************
	 * Method Name		: processCalculateFinalPrice
	 * Input Parameter 	: double 
	 * Return 			: double
	 * Purpose 			: To caculate the price after discount
	 * *******************************************************/
	public double processCalculateFinalPrice(double discountValue){
		DecimalFormat fmt = new DecimalFormat("0.00");
		double price=processCalculatePrice();
		double discount=discountValue;
		double finalPrice=0;
		finalPrice=price-((discount/100)*price);
		return  Double.parseDouble(fmt.format(finalPrice));
	}
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Entertainment getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(Entertainment entertainment) {
		this.entertainment = entertainment;
	}

	public EntertainmentMenu getEntertainmentMenu() {
		return entertainmentMenu;
	}

	public void setEntertainmentMenu(EntertainmentMenu entertainmentMenu) {
		this.entertainmentMenu = entertainmentMenu;
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public DefaultTableModel getModel() {
		return model;
	}
	
}
