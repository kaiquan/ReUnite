/********************************************************************************************************************************************************
Program Name			:	AdministratePackageControl.java
Description				:	A AdministratePackageControl class that is the Controller for CRUD of Package record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	AdministratePcakageControl();
						: 	AdministratePackageControl(String, String, String, String, String, String, boolean, int, double, boolean)
						:	processCreatePackage(String, String, String) : String
						:	processDeletePackage(String) : Boolean
						:	processUpdatePackage(String, String, String) : Boolean
						:	processRetrievePackage() : DefaultTableModel
						:	processRetrievePackage(String) :DefaultTableModel
						:	processRetrievePackageByID(String) : Package
						:	processRetrieveMealOptions(String) : ArrayList<Meal>
						:	
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Ballroom;
import Model.Entertainment;
import Model.Facility;
import Model.Meal;
import Model.MealOptions;
import Model.Package;

public class AdministratePackageControl {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Facility facility=null;			//this stores a entetianment object from the view or model
	private  Ballroom ballroom=null;				
	private Entertainment entertainment= null;
	private Meal meal=null;
	private Package pack=null;
	private MealOptions mealOptions=null;
	private DefaultTableModel model=null; 
	private ArrayList<String> mealIDs=null;
	private ArrayList<Meal> meals=null;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public AdministratePackageControl(){}
	public AdministratePackageControl(String ID, String EntertainmentID, String BallroomID, String Type, String Title, String Description, boolean Availability, int Hits, double Discount, boolean isRecord){
		Package pack= new Package();
		
		pack.setPackageID(ID);
		pack.setEntertainmentID(EntertainmentID);
		pack.setBallroomID(BallroomID);
		pack.setPackageType(Type);
		pack.setPackageTitle(Title);
		pack.setPackageDescription(Description);
		pack.setPackageAvailability(Availability);
		pack.setPackageDiscount(Discount);
		pack.setPackageHits(Hits);
		pack.setRecord(isRecord);
		
		this.pack=pack;
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/

	/********************************************************
	 * Method Name 		: processCreatePackage
	 * Input Parameter 	: NIL 
	 * Purpose 			: To create a new Package 
	 * 					  record in the database
	 * Return 			: String
	 *******************************************************/	
	public String processCreatePackage(String mealID1, String mealID2, String mealID3){
		return this.pack.CREATE_PACKAGE(mealID1, mealID2, mealID3);
	}
	
	/********************************************************
	  * Method Name 	: processDeletePackage
	  * Input Parameter : String
	  * Purpose 		: To delete an package record
	  * Return 			: boolean
	  *******************************************************/
	public boolean processDeletePackage(String ID){
		Package pack= new Package();
		return pack.DELETE_PACKAGE(ID);
	}
	

	/********************************************************
	  * Method Name 	: processUpdatePackage
	  * Input Parameter : String
	  * Purpose 		: To update an package record
	  * Return 			: boolean
	  *******************************************************/
	public boolean processUpdatePackage(String mealID1, String mealID2, String mealID3){
		return this.pack.UPDATE_PACKAGE(mealID1, mealID2, mealID3);
	}
	
	 /********************************************************
	  * Method Name 	: processRetrievePackage
	  * Input Parameter : NIL
	  * Purpose 		: To retrieve all Package record 
	  * 				  where isRecord =0
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel processRetrievePackage(){
		Package pack= new Package();
		return pack.RETRIEVE_PACKAGE();
	}
	 /********************************************************
	  * Method Name 	: processRetrievePackage
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Package record 
						  where isRecord =0
	  * Return 			: DefaultTableModel
	  *******************************************************/
	public DefaultTableModel processRetrievePackage(String parameter){
		Package pack= new Package();
		return pack.RETRIEVE_PACKAGE(parameter);
	}
	 /********************************************************
	  * Method Name 	: processRetrievePackageByID
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Package record by ID
	  * Return 			: Package
	  *******************************************************/
	public Package processRetrievePackageByID(String ID){
		Package pack= new Package();
		this.pack=pack.RETRIEVE_PACKAGE_BY_ID(ID);
		Ballroom ballroom= new Ballroom();
		this.ballroom=ballroom.RETRIEVE_BALLROOM_BY_ID(this.pack.getBallroomID());
		Facility facility= new Facility();
		this.facility=facility.RETRIEVE_FACILITY_BY_ID(this.ballroom.getFacilityID());
		Entertainment entertainment= new Entertainment();
		this.entertainment=entertainment.RETRIEVE_ENTERTAINMENT_BY_ID(this.pack.getEntertainmentID());
		this.mealIDs= new ArrayList<String>();
		MealOptions meal= new MealOptions();
		mealIDs=meal.RETRIEVE_MEAL_OPTIONS(ID);
		Meal m= new Meal();
		this.meals= new ArrayList<Meal>();
		for(int i=0;i<mealIDs.size();i++){
			meals.add(m.RETRIEVE_MEAL_BY_ID(mealIDs.get(i)));
		}
		return this.pack;
	}
	
	 /********************************************************
	  * Method Name 	: processRetrieveMealOptions
	  * Input Parameter : String
	  * Purpose 		: To retrieve all MealOptions 
	  * 				  by PackageID
	  * Return 			: ArrayList<Meal>
	  *******************************************************/
	public ArrayList<Meal> processRetrieveMealOptions(String ID){
		this.mealIDs= new ArrayList<String>();
		MealOptions meal= new MealOptions();
		mealIDs=meal.RETRIEVE_MEAL_OPTIONS(ID);
		Meal m= new Meal();
		this.meals= new ArrayList<Meal>();
		for(int i=0;i<mealIDs.size();i++){
			meals.add(m.RETRIEVE_MEAL_BY_ID(mealIDs.get(i)));
		}
		return this.meals;
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
	public Entertainment getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(Entertainment entertainment) {
		this.entertainment = entertainment;
	}
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public Package getPack() {
		return pack;
	}
	public void setPack(Package pack) {
		this.pack = pack;
	}
	public MealOptions getMealOptions() {
		return mealOptions;
	}
	public void setMealOptions(MealOptions mealOptions) {
		this.mealOptions = mealOptions;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public ArrayList<String> getMealIDs() {
		return mealIDs;
	}
	public void setMealIDs(ArrayList<String> mealIDs) {
		this.mealIDs = mealIDs;
	}
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
}
