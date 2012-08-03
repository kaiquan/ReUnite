/********************************************************************************************************************************************************
Program Name			:	InitiateEventControl.java
Description				:	A InitiateEventControl class that is the Controller for CRUD of Event record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-13-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	InitiateEventControl();
						: 	InitiateEventControl(String, String, String, String, String, String)
						:	InitiateEventControl(String, String)
						:	processInitiateEvent() : boolean
						:	processRetrieveTimings(String, String) : ArrayList<String>
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Controller.SOM;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.Ballroom;
import Model.Entertainment;
import Model.Event;
import Model.Facility;
import Model.Meal;
import Model.MealOptions;
import Model.Package;

public class InitiateEventControl {
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
	private Event event=null;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public InitiateEventControl(){}
	public InitiateEventControl(String packageID, String userName, String eventStatus, String eventDate, String eventTime, String eventName, String description){
		this.event= new Event();
		this.event.setPackageID(packageID);
		this.event.setUserName(userName);
		this.event.setEventStatus(eventStatus);
		this.event.setEventDate(eventDate);
		this.event.setEventTime(eventTime);
		this.event.setEventDescription(description);
		this.event.setEventName(eventName);
	}
	public InitiateEventControl(String ballroomID, String Date){
		this.ballroom.setBallroomID(ballroomID);
		this.event.setEventDate(Date);
	}
	
	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: processInitiateEvent()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To create a new Event record
	 *******************************************************/
	public String processInitiateEvent(){
		return this.event.INITIATE_EVENT();
	}
	
	/********************************************************
	 * Method Name 		: processRetrieveTimming()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To get the all the booked time 
	 * 					  for the event base on ballroom 
	 * 					  and date
	 *******************************************************/
	public ArrayList<String> processRetrieveTimings(String ballroomID, String Date){
		Event event= new Event();
		return event.RETRIEVE_TIMINGS(ballroomID, Date);
	}
	
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	public Facility getFacility() {
		return facility;
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
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
