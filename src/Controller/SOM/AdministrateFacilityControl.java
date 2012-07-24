/********************************************************************************************************************************************************
Program Name			:	AdministrateFacilityControl.java
Description				:	A AdministrateFacilityControl class that is the Controller for CRUD of Facility record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	AdministrateFacilityControl();
						: 	AdministrateFacilityControl(String, String, String, String, boolean, double)
						:	processCreateFacility() : String
						:	processDeleteFacility(String) : Boolean
						:	processUpdateFacility(String) : Boolean
						:	processRetrieveFacility() : DefaultTableModel
						:	processRetrieveFacility(String) : DefaultTableModel
						:	processRetrieveFacilityNames() : DefaultComboBoxModel
						:	processRetrieveFacilityByName(String) : Facility
						:	processRetrieveFacilityByID(String) : Facility
						:	processCalculateFinalPrice(int) : Double
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import Model.Ballroom;
import Model.Facility;



public class AdministrateFacilityControl {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Facility facility=null;			//this stores a entetianment object from the view or model
	private  Ballroom ballroom=null;				
	private DefaultTableModel model=null; 
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public AdministrateFacilityControl(){}
	public AdministrateFacilityControl(String Name, String Description, String Address, String Contact, boolean Parking, double WeekendExtraCost){
		Facility facility= new Facility();
		facility.setFacilityName(Name);
		facility.setFacilityDescription(Description);
		facility.setFacilityAddress(Address);
		facility.setFacilityContact(Contact);
		facility.setFacilityParking(Parking);
		facility.setFacilityWeekendExtraCost(WeekendExtraCost);
		this.facility=facility;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/

	/********************************************************
	 * Method Name 		: processCreateFacility
	 * Input Parameter 	: NIL 
	 * Purpose 			: To create a new Facility record
	 * Return			: String 
	 *******************************************************/
	public String processCreateFacility(){
		String ID;
		ID= this.facility.CREATE_FACILITY();
		if(ID.equals(null)){
			return null;
		}
		else{
			//FOR LOOP TO CREATE THE BALLROOM
			for(int i=0;i<model.getRowCount();i++){
				ballroom=new Ballroom(ID,model.getValueAt(i, 0).toString(),model.getValueAt(i, 4).toString(),model.getValueAt(i, 3).toString(),Double.parseDouble(model.getValueAt(i, 2).toString()),Double.parseDouble(model.getValueAt(i, 1).toString()),processCalculateFinalPrice(i),0,true);
				ballroom.CREATE_BALLROOM();
			}
		}
		return ID;
	}
	
	/********************************************************
	  * Method Name 	: processDeleteFacility
	  * Input Parameter : String
	  * Purpose 		: To delete an Facility record 
	  * 				  along with the attached ballrooms
	  * Return 			: boolean
	  *******************************************************/
	public boolean processDeleteFacility(String ID){
		boolean success=true;
		Facility facility= new Facility();
		success=facility.DELETE_FACILITY(ID);
		Ballroom ballroom=new Ballroom();
		success=ballroom.DELETE_BALLROOM_BY_FID(ID);
		return success;
	}
	
	/********************************************************
	  * Method Name 	: processUpdateFacility
	  * Input Parameter : String
	  * Purpose 		: To update an Facility record
	  * Return 			: boolean
	  *******************************************************/
	public boolean processUpdateFacility(String ID){
		boolean success=true;
		//	UPDATE THE FACILITY
		success=this.facility.UPDATE_FACILITY(ID);
		if(success){
			//DELETE THE BALLROOM LIST
			Ballroom ballroom=new Ballroom();
			ballroom.DELETE_BALLROOM_BY_FID(ID);
			//RE-CREATE THE BALLROOM LIST
			for(int i=0;i<model.getRowCount();i++){
				ballroom=new Ballroom(ID,model.getValueAt(i, 0).toString(),model.getValueAt(i, 4).toString(),model.getValueAt(i, 3).toString(),Double.parseDouble(model.getValueAt(i, 2).toString()),Double.parseDouble(model.getValueAt(i, 1).toString()),processCalculateFinalPrice(i),0,true);
				ballroom.CREATE_BALLROOM();
			}
		}
		else{
			return false;
		}
		return success;
	}
	
	
	 /********************************************************
	  * Method Name 	: processRetrieveFacility
	  * Input Parameter : NIL
	  * Purpose 		: To retrieve all facility record
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel processRetrieveFacility(){
		Facility facility= new Facility();
		return facility.RETRIEVE_FACILITY();
	}
	
	/********************************************************
	  * Method Name		: processRetrieveFacility
	  * Input Parameter : String
	  * Purpose 		: To retrieve all facility 
	  * 				  record where like string
	  * Return 			: Void
	  *******************************************************/
	public DefaultTableModel processRetrieveFacility(String parameter){
		Facility facility= new Facility();
		return facility.RETRIEVE_FACILITY(parameter);
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveFacilityNames
	  * Input Parameter : String
	  * Purpose 		: To retrieve all facility record where like string
	  * Return 			: DefaultComboBoxModel 
	  *******************************************************/
	@SuppressWarnings("unchecked")
	public DefaultComboBoxModel  processRetrieveFacilityNames(){
		Facility facility= new Facility();
		return facility.RETRIEVE_FACILITY_NAMES();
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveFacilityByName
	  * Input Parameter : String
	  * Purpose 		: To retrieve all facility 
	  * 				  record by name= string
	  * Return 			: Facility 
	  *******************************************************/
	public Facility processRetrieveFacilityByName(String name){
		Facility facility= new Facility();
		this.facility=facility.RETRIEVE_FACILITY_BY_NAME(name);
		return this.facility;
	}
	
	/********************************************************
	  * Method Name 	: processRetrieveFacilityByID
	  * Input Parameter : String ID
	  * Purpose 		: To retrieve a facility record where id=ID
	  * Return 			: void
	  *******************************************************/
	public Facility processRetrieveFacilityByID(String ID){
		Facility facility= new Facility();
		this.facility=facility.RETRIEVE_FACILITY_BY_ID(ID);
		Ballroom ballroom= new Ballroom();
		this.model=ballroom.RETRIEVE_BALLROOM_MODEL(ID);
		return this.facility;
	}
	

	//business logic
	/********************************************************
	 * Method Name 		: processCalculateFinalPrice
	 * Input Parameter 	: Int 
	 * Purpose 			: To caculate the Final price 
	 * 				  	  after discount
	 * Return 			: double
	 * *******************************************************/
	public double processCalculateFinalPrice(int i){
		DecimalFormat fmt = new DecimalFormat("0.00");
		double price=Double.parseDouble(model.getValueAt(i, 1).toString());
		double discount=Double.parseDouble(model.getValueAt(i, 2).toString());
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
