/********************************************************************************************************************************************************
Program Name			:	AdministrateBallroomControl.java
Description				:	A AdministrateBallroomControl class that is the Controller for CRUD of Ballroom record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	AdministrateBallroomControl();
						: 	AdministrateBallroomControl(String, String, String, String, double, double, double, int, boolean)
						:	processCreateBallroom() : String
						:	processDeleteBallroom(String) : Boolean
						:	processUpdateBallroom(String) : Boolean
						: 	processRetrieveBallroom() : DefaultTableModel
						: 	processRetrieveBallroom(String) : DefaultTableModel
						: 	processRetrieveBallroomByID(String) : Ballroom
						:	processRetrieveBallroomModel(String) : DefaultTableModel
						: 	processRetrieveBallroomByFacility(String) : DefaultTableModel
						: 	processCalculateFinalPrice(int, double) : Double
						:  	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

import Model.Ballroom;
import Model.Facility;



public class AdministrateBallroomControl {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Facility facility=null;			//this stores a entetianment object from the view or model
	private  Ballroom ballroom=null;				
	private DefaultTableModel model=null; 
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public AdministrateBallroomControl(){}
	public AdministrateBallroomControl(String ID, String Name, String Description, String Size, double Discount, double Price, double FinalPrice, int Hits, boolean Availability){
		Ballroom ballroom =new Ballroom();
		ballroom.setFacilityID(ID);
		ballroom.setBallroomName(Name);
		ballroom.setBallroomDescription(Description);
		ballroom.setBallroomSize(Size);
		ballroom.setBallroomDiscount(Discount);
		ballroom.setBallroomPrice(Price);
		ballroom.setBallroomFinalPrice(FinalPrice);
		ballroom.setBallroomHits(Hits);
		ballroom.setBallroomAvailability(Availability);
		
		this.ballroom=ballroom;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/

	/********************************************************
	 * Method Name		: processCreateBallroom
	 * Input Parameter 	: NIL 
	 * Purpose 			: To create a new Ballroom 
	 * 					  record in the database
	 * Return			: String
	 *******************************************************/	
	public String processCreateBallroom(){
		return this.ballroom.CREATE_BALLROOM();
	}
	
	/********************************************************
	 * Method Name 		: processDeleteBallroom
	 * Input Parameter 	: String
	 * Purpose 			: To Delete the Ballroom
	 * 					  record in the database
	 * Return 			: boolean
	 *******************************************************/
	public boolean processDeleteBallroom(String ID){
		boolean success=false;
		Ballroom ballroom = new Ballroom();
		if(ballroom.CHECK_RELATIONSHIP(ID)){
			return false;
		}
		else{
			success= ballroom.DELETE_BALLROOM(ID);
		}
		return success;
	}
	
	/********************************************************
	 * Method Name		: processUpdateBallroom
	 * Input Parameter 	: String
	 * Return 			: boolean
	 * Purpose 			: To update an Ballroom record
	  *******************************************************/
	public boolean processUpdateBallroom(String ID){
		return this.ballroom.UPDATE_BALLROOM(ID);
	}
	
	/********************************************************
	 * Method Name		: processRetrieveBallroom
	 * Input Parameter 	: void
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * 					  where is not purchase record
	 *******************************************************/
	public DefaultTableModel processRetrieveBallroom(){
		Ballroom ballroom= new Ballroom();
		return ballroom.RETRIEVE_BALLROOM();
	}
	
	/********************************************************
	 * Method Name		: processRetrieveBallroom
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve all Ballroom record
	 * 					  where is not purchase record
	 * 					  & by input paramater
	 *******************************************************/
	public DefaultTableModel processRetrieveBallroom(String parameter){
		Ballroom ballroom= new Ballroom();
		return ballroom.RETRIEVE_BALLROOM(parameter);
	}
	
	/********************************************************
	 * Method Name		: processRetrieveBallroomByID
	 * Input Parameter 	: String
	 * Return 			: void
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public Ballroom processRetrieveBallroomByID(String ID){
		Ballroom ballroom= new Ballroom();
		this.ballroom=ballroom.RETRIEVE_BALLROOM_BY_ID(ID);
		return this.ballroom;
	}
	
	/********************************************************
	 * Method Name		: processRetrieveBallroomModel
	 * Input Parameter 	: String
	 *  Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public DefaultTableModel processRetrieveBallroomModel(String ID){
		Ballroom ballroom= new Ballroom();
		return ballroom.RETRIEVE_BALLROOM_MODEL(ID);
	}
	
	/********************************************************
	 * Method Name		: processRetrieveBallroomByFacility()
	 * Input Parameter 	: String
	 * Return 			: DefaultTableModel
	 * Purpose 			: To retrieve a Ballroom record
	 * 					  by ballroomID
	 *******************************************************/
	public DefaultTableModel processRetrieveBallroomByFacility(String ID){
		Ballroom ballroom= new Ballroom();
		return ballroom.RETRIEVE_BALLROOM_BY_FACILITY(ID);
	}
	
	
	//business logic
	/********************************************************
	 * Method Name : processCalculateFinalPrice
	 * Input Parameter : Int 
	 * Purpose : To caculate the Final price after discount
	 * Return :double
	 * Tested : Success
	 * *******************************************************/
	public double processCalculateFinalPrice(int i, double p){
		DecimalFormat fmt = new DecimalFormat("0.00");
		double price=p;
		double discount=i;
		double finalPrice=0;
		finalPrice=price-((discount/100)*price);
		return  Double.parseDouble(fmt.format(finalPrice));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	public Ballroom getBallroom() {
		return ballroom;
	}
	public void setBallroom(Ballroom ballroom) {
		this.ballroom = ballroom;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}
