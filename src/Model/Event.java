/********************************************************************************************************************************************************
Program Name			:	Package.java
Description				:	A Package Model class that is the Model for Package record(s)
Done by					:	Lee Kai Quan(114173S), A Ameenudeen (111942S), Adeel M. Ateeque(112013Z)
Admin No				:	
Module Group			:	IT2297-08
Last Edited				:	4-August-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S), A Ameenudeen(111942S), Adeel M. Ateeque(112013Z)
METHODS LIST			:	Event()
						:	Event(int)
						: 	Event(String, String, String, String, String, String)
						:	Event(String, String)
						:	INITIATE_EVENT() : String
						:	RETRIEVE_TIMINGS(String, String) : ArrayList<String>
						:	RETRIEVE_EVENT_RECORDS (): ArrayList<String>	
						:	GET_EVENT_RECORDS_DUE_FOR_PAYMENT ():ArrayList<String>
						:	GET_EVENT_DETAILS(String) : ArrayList<String>
						:	UPDATE_EVENT_STATUS(String,String) : Boolean
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.RIM.Utils.DateHelper;
import java.util.GregorianCalendar;
import Controller.MyCalendar;
import Controller.MySQLController; 
import Model.Event;
import Model.Membership.Account;
import Model.RIM.TableNames;
import Model.Membership.Account;

@SuppressWarnings("unused")
public class Event {

	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Ballroom data;								//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	private DateHelper dateHelper = new DateHelper();
	
	private String eventID;
	private String packageID;
	private String userName;
	private Package eventPackage;
	private Invitation invitation;
	private EventPhoto photo;
	private Account initiator;
	private String eventStatus;
	private String eventDate;
	private String eventTime;
	private String eventName;
	private String eventDescription;
	
	private ArrayList<Integer> eventID_list, packageID_list;
	private ArrayList<String> userName_list, eventStatus_list, eventName_list, eventTime_list, eventDescription_list;
	private ArrayList<GregorianCalendar> eventDate_list;
	
	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	public Event(int eventID)
	{
		this.setID(eventID);
	}
	public Event(String packageID, String userName, String eventStatus, String eventDate, String eventTime, String eventName){
		this.packageID=packageID;
		this.userName=userName;
		this.eventStatus=eventStatus;
		this.eventDate=eventDate;
		this.eventTime=eventTime;
		this.eventName=eventName;
	}
	public Event(String eventTitle, String eventDate){
		this.eventName = eventTitle; 
		this.eventDate = eventDate;
	}
	public Event(){
		DB = new MySQLController();
		eventID_list = new ArrayList<Integer>();
		packageID_list = new ArrayList<Integer>();
		userName_list = new ArrayList<String>();
		eventStatus_list = new ArrayList<String>();
		eventDate_list = new ArrayList<GregorianCalendar>();
		eventTime_list = new ArrayList<String>();
		eventName_list = new ArrayList<String>();
		eventDescription_list = new ArrayList<String>();
		
	}

	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: INITIATE_EVENT()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To create a new event and purchase 
	 * 					  record
	 *******************************************************/
	public String INITIATE_EVENT(){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "INSERT INTO Event (`packageID`, `userName`, `eventStatus`, `eventDate`, `eventTime`, `eventName`, `eventDescription`)";
		sqlQuery +="VALUES ("+this.packageID+", '"+Account.currentUser.getUserName()+"', 'Pending', '"+this.eventDate+"', '"+this.eventTime+"', '"+this.eventName+"','"+this.eventDescription+"')";
		int i = 0;
		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed To Create Event Record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		//RETRIEVE THE CREATED EVENT ID
		sqlQuery="SELECT * FROM Event;";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				this.eventID=rs.getString("eventID");
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve event Record");
		}
		finally{
		}
		
		
		//CREATE THE PURCHASE SUMMARY
		sqlQuery = "INSERT INTO Purchase_Summary (`eventID`,`dateOfPurchase`) VALUES ('"+this.eventID+"','"+MyCalendar.formatDate(new GregorianCalendar())+"')"; 
		i = 0;
		try{
			i=DB.updateRequest(sqlQuery);
		}
		catch (Exception e) {
		   System.out.println("Failed To Create Purchase Record");
		}
		finally{
		}
		if(i==1)
			success=true;
		
		return this.eventID;
	}
	/********************************************************
	 * Author			: Lee Kai Quan(114173S)
	 * Method Name 		: RETRIEVE_TIMINGS()
	 * Input Parameter 	: String, String
	 * Return 			: ArrayList<String>
	 * Purpose 			: To retrieve the timings by 
	 * 					  ballroom and date
	 *******************************************************/
	public ArrayList<String> RETRIEVE_TIMINGS(String ballroomID, String Date){
		ArrayList<String> packagelist= new ArrayList<String>();
		String sqlQuery;
		
		sqlQuery="SELECT distinct packageID FROM Package WHERE ballroomID="+ballroomID+"";
		try{
			rs = DB.readRequest(sqlQuery);
			while (rs.next()){
				packagelist.add(rs.getString("packageID"));
		   }
		}
		catch (Exception e) {
			System.out.println("Failed to Retrieve packageID Record");
		}
		finally{
		}
		

		ArrayList<String> Timelist= new ArrayList<String>();
		for(int i=0; i<packagelist.size();i++){
			sqlQuery = "SELECT * FROM Event WHERE (`eventStatus`='Pending'OR `eventStatus`='Awaiting Payment') AND `eventDate`='"+Date+"'  AND `packageID`="+packagelist.get(i)+")";
			
			try{
				rs = DB.readRequest(sqlQuery);
				while (rs.next()){
					Timelist.add(rs.getString("eventTime"));
			   }
			}
			catch (Exception e) {
				System.out.println("Failed to Retrieve Entertainment Record");
			}
			finally{
			}
		}
		
		return Timelist;
	}
	
	/********************************************************
	 * Author			: Adeel M. Ateeque(112013Z)
	 * Method Name 		: GET_ALL_EVENTS()
	 * Input Parameter 	: Nil
	 * Return 			: ArrayList<Event>
	 * Purpose 			: To retrieve all the events
	 *******************************************************/
	public ArrayList<Event> GET_ALL_EVENTS()
	{
		ArrayList<Event> eventList = new ArrayList<Event>();
		
		try
		{String dbQuery = null;
			if(Account.currentUser.getType().equalsIgnoreCase("GR"))
			{
				dbQuery = 
					"SELECT * FROM " + TableNames.EVENT_TABLE + " e" 
					+" LEFT JOIN " + TableNames.INVITATION_TABLE + " i ON" + " e.eventID = i.eventID"
					+" INNER JOIN " + TableNames.PACKAGE_TABLE + " p ON e.packageID = p.packageID"
					+" INNER JOIN " + TableNames.BALLROOM_TABLE + " b ON p.ballroomID = b.ballroomID"
					+" INNER JOIN " + TableNames.FACILITY_TABLE + " f ON b.facilityID = f.facilityID" 
					+" INNER JOIN " + TableNames.ENTERTAINMENT_TABLE + " ent on p.entertainmentID = ent.entertainmentID"
					+" INNER JOIN " + TableNames.ENTERTAINMENT_MENU_TABLE + " em ON ent.entertainmentID = em.entertainmentID" 
					+" INNER JOIN " + TableNames.MEAL_OPTIONS_TABLE + " mo ON p.packageID = mo.packageID"
					+" INNER JOIN " + TableNames.MEAL_TABLE + " m ON mo.mealID = m.mealID" 
					+" INNER JOIN " + TableNames.MEAL_MENU_TABLE + " mm ON m.mealID = mm.mealID"
					+" ORDER BY e.eventID";
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("RI"))
			{
				dbQuery = 
					"SELECT * FROM " + TableNames.EVENT_TABLE + " e" 
					+" LEFT JOIN " + TableNames.INVITATION_TABLE + " i ON" + " e.eventID = i.eventID"
					+" INNER JOIN " + TableNames.PACKAGE_TABLE + " p ON e.packageID = p.packageID"
					+" INNER JOIN " + TableNames.BALLROOM_TABLE + " b ON p.ballroomID = b.ballroomID"
					+" INNER JOIN " + TableNames.FACILITY_TABLE + " f ON b.facilityID = f.facilityID" 
					+" INNER JOIN " + TableNames.ENTERTAINMENT_TABLE + " ent on p.entertainmentID = ent.entertainmentID"
					+" INNER JOIN " + TableNames.ENTERTAINMENT_MENU_TABLE + " em ON ent.entertainmentID = em.entertainmentID" 
					+" INNER JOIN " + TableNames.MEAL_OPTIONS_TABLE + " mo ON p.packageID = mo.packageID"
					+" INNER JOIN " + TableNames.MEAL_TABLE + " m ON mo.mealID = m.mealID" 
					+" INNER JOIN " + TableNames.MEAL_MENU_TABLE + " mm ON m.mealID = mm.mealID"
					+" WHERE userName= '"+ Account.currentUser.getUserName()+"'"
					+" ORDER BY e.eventID";
			}
			else if(Account.currentUser.getType().equalsIgnoreCase("Guest"))
			{
				dbQuery = 
					"SELECT * FROM "+TableNames.GUEST_TABLE + " g"
					+" INNER JOIN " + TableNames.INVITATION_TABLE + " i ON" + " g.invitationID = i.invitationID"
					+" INNER JOIN "	+ TableNames.EVENT_TABLE + " e ON i.eventID = e.eventID"   
					+" INNER JOIN " + TableNames.PACKAGE_TABLE + " p ON e.packageID = p.packageID"
					+" INNER JOIN " + TableNames.BALLROOM_TABLE + " b ON p.ballroomID = b.ballroomID"
					+" INNER JOIN " + TableNames.FACILITY_TABLE + " f ON b.facilityID = f.facilityID" 
					+" INNER JOIN " + TableNames.ENTERTAINMENT_TABLE + " ent on p.entertainmentID = ent.entertainmentID"
					+" INNER JOIN " + TableNames.ENTERTAINMENT_MENU_TABLE + " em ON ent.entertainmentID = em.entertainmentID" 
					+" INNER JOIN " + TableNames.MEAL_OPTIONS_TABLE + " mo ON p.packageID = mo.packageID"
					+" INNER JOIN " + TableNames.MEAL_TABLE + " m ON mo.mealID = m.mealID" 
					+" INNER JOIN " + TableNames.MEAL_MENU_TABLE + " mm ON m.mealID = mm.mealID"
					+" WHERE g.userName = '"+ Account.currentUser.getUserName()+"'"
					+" ORDER BY e.eventDate";
			}
			ResultSet rs = DB.readRequest(dbQuery);	
			
		int prev = 0;
		while (rs.next())
		{
			Event event;
			if(rs.getInt("eventID")!=prev){
				prev = rs.getInt("eventID");
				
				event = new Event();

				event.setID(rs.getInt("eventID"));
				event.setEventName(rs.getString("eventName"));
				event.setEventDate(rs.getString("eventDate").trim());
				event.setEventTime(rs.getString("eventTime"));
				event.setEventStatus(rs.getString("eventStatus"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setEventInitiator(new Account(rs.getString("userName")));
				
					Package eventPackage = new Package();
					eventPackage.setID(rs.getInt("packageID"));
					eventPackage.setPackageTitle(rs.getString("packageTitle"));
					eventPackage.setPackageDescription(rs.getString("packageDescription"));
					eventPackage.setPackageType(rs.getString("packageType"));
					
						Ballroom eventBallroom = new Ballroom();
						eventBallroom.setID(rs.getInt("ballroomID"));
						eventBallroom.setBallroomDescription(rs.getString("ballroomDescription"));
						eventBallroom.setBallroomName(rs.getString("ballroomName"));
						eventBallroom.setBallroomSize(rs.getString("ballroomSize"));
							Facility eventFacility = new Facility();
							eventFacility.setFacilityAddress(rs.getString("facilityAddress"));
							eventFacility.setFacilityName(rs.getString("facilityName"));
							eventFacility.setFacilityDescription(rs.getString("facilityDescription"));
							eventFacility.setFacilityContact(rs.getString("facilityContact"));
							eventFacility.setFacilityParking(rs.getBoolean("facilityParking"));
							eventFacility.setFacilityWeekendExtraCost(rs.getFloat("facilityWeekendExtraCost"));
						eventBallroom.setFacility(eventFacility);
						
						
					eventPackage.setBallroom(eventBallroom);
					
						Entertainment eventEntertainment = new Entertainment();
						eventEntertainment.setID(rs.getInt("entertainmentID"));
						eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentDescription"));
						eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentTitle"));
							
							EntertainmentMenu entertainmentOption = new EntertainmentMenu();
							entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
							entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
							entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
							
						eventEntertainment.addEntertainmentOption(entertainmentOption);
						
					eventPackage.setEntertainment(eventEntertainment);
					
						Meal meal = new Meal();
							MealMenu item = new MealMenu();
							item.setMealMenuName(rs.getString("mealMenuName"));
							item.setMealMenuDescription(rs.getString("mealMeuDescription"));
							item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
							item.setMealMenuVegetarian(rs.getBoolean("mealMenuVegetarian"));
							item.setMealMenuHalal(rs.getBoolean("mealMenuHalal"));
						meal.addMealItem(item);
						meal.setMealAvailability(rs.getBoolean("mealAvailability"));
						meal.setMealDescription(rs.getString("mealDescription"));
						meal.setMealDiscount(rs.getFloat("mealDiscount"));
						meal.setMealFinalPrice(rs.getFloat("mealFinalPrice"));
						meal.setMealPricePerHead(rs.getFloat("mealPricePerHead"));
						meal.setMealType(rs.getString("mealType"));
						meal.setMealTitle(rs.getString("mealTitle"));
					
					eventPackage.addMeal(meal);
				
				event.setEventPackage(eventPackage);
					
					Invitation eventInvitation = new Invitation();
					eventInvitation.setInvitationID(rs.getInt("invitationID"));
					eventInvitation.setDateCreated(dateHelper.parseDate(rs.getString("i.dateCreated"), TableNames.DATE_FORMAT));
					eventInvitation.setExpiryDate(dateHelper.parseDate(rs.getString("i.expiryDate"), TableNames.DATE_FORMAT));
				
				event.setEventInvitation(eventInvitation);
				
				eventList.add(event);
			}
			else
			{
				//Take out the previously added Event
				event = eventList.get(eventList.size()-1);
				
				EntertainmentMenu entertainmentOption = new EntertainmentMenu();
				entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
				entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
				entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
				
				event.getEventPackage().getEntertainment().addEntertainmentOption(entertainmentOption);
				
				Meal meal = new Meal();
					MealMenu item = new MealMenu();
					item.setMealMenuName(rs.getString("mealMenuName"));
					item.setMealMenuDescription(rs.getString("mealMeuDescription"));
					item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
					item.setMealMenuVegetarian(rs.getBoolean("mealMenuVegetarian"));
					item.setMealMenuHalal(rs.getBoolean("mealMenuHalal"));
				meal.addMealItem(item);
				meal.setMealAvailability(rs.getBoolean("mealAvailability"));
				meal.setMealDescription(rs.getString("mealDescription"));
				meal.setMealDiscount(rs.getFloat("mealDiscount"));
				meal.setMealFinalPrice(rs.getFloat("mealFinalPrice"));
				meal.setMealPricePerHead(rs.getFloat("mealPricePerHead"));
				meal.setMealType(rs.getString("mealType"));
				meal.setMealTitle(rs.getString("mealTitle"));
				
				event.getEventPackage().addMeal(meal);
			}
		}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return eventList;
	}
	
	/********************************************************
	 * Author			: Adeel M. Ateeque(112013Z)
	 * Method Name 		: GET_EVENT_BY_ID(int eventID)
	 * Input Parameter 	: int
	 * Return 			: Event
	 * Purpose 			: To retrieve a specific Event based
	 * 						on its ID
	 *******************************************************/
	public Event GET_EVENT_BY_ID(int eventID)
	{
		Event event = new Event();
		try
		{
			ResultSet rs = DB.readRequest(	"SELECT * FROM " + TableNames.EVENT_TABLE + " e" 
											+" LEFT JOIN " + TableNames.INVITATION_TABLE + " i ON" + " e.eventID = i.eventID"
											+" INNER JOIN " + TableNames.PACKAGE_TABLE + " p ON e.packageID = p.packageID"
											+" INNER JOIN " + TableNames.BALLROOM_TABLE + " b ON p.ballroomID = b.ballroomID"
											+" INNER JOIN " + TableNames.FACILITY_TABLE + " f ON b.facilityID = f.facilityID" 
											+" INNER JOIN " + TableNames.ENTERTAINMENT_TABLE + " ent on p.entertainmentID = ent.entertainmentID"
											+" INNER JOIN " + TableNames.ENTERTAINMENT_MENU_TABLE + " em ON ent.entertainmentID = em.entertainmentID" 
											+" INNER JOIN " + TableNames.MEAL_OPTIONS_TABLE + " mo ON p.packageID = mo.packageID"
											+" INNER JOIN " + TableNames.MEAL_TABLE + " m ON mo.mealID = m.mealID" 
											+" INNER JOIN " + TableNames.MEAL_MENU_TABLE + " mm ON m.mealID = mm.mealID"
											+" WHERE e.eventID = " + eventID
				);
			
			int prev = 0;
			while (rs.next())
			{
				if(rs.getInt("eventID")!=prev){
					prev = rs.getInt("eventID");
					
					event.setID(rs.getInt("eventID"));
					event.setEventName(rs.getString("eventName"));
					event.setEventDate(rs.getString("eventDate").trim());
					event.setEventTime(rs.getString("eventTime"));
					event.setEventStatus(rs.getString("eventStatus"));
					event.setEventDescription(rs.getString("eventDescription"));
					event.setEventInitiator(new Account(rs.getString("userName")));
					
						Package eventPackage = new Package();
						eventPackage.setID(rs.getInt("packageID"));
						eventPackage.setPackageTitle(rs.getString("packageTitle"));
						eventPackage.setPackageDescription(rs.getString("packageDescription"));
						eventPackage.setPackageType(rs.getString("packageType"));
						
							Ballroom eventBallroom = new Ballroom();
							eventBallroom.setID(rs.getInt("ballroomID"));
							eventBallroom.setBallroomDescription(rs.getString("ballroomDescription"));
							eventBallroom.setBallroomName(rs.getString("ballroomName"));
							eventBallroom.setBallroomSize(rs.getString("ballroomSize"));
								Facility eventFacility = new Facility();
								eventFacility.setFacilityAddress(rs.getString("facilityAddress"));
								eventFacility.setFacilityName(rs.getString("facilityName"));
								eventFacility.setFacilityDescription(rs.getString("facilityDescription"));
								eventFacility.setFacilityContact(rs.getString("facilityContact"));
								eventFacility.setFacilityParking(rs.getBoolean("facilityParking"));
								eventFacility.setFacilityWeekendExtraCost(rs.getFloat("facilityWeekendExtraCost"));
							eventBallroom.setFacility(eventFacility);
						eventPackage.setBallroom(eventBallroom);
						
							Entertainment eventEntertainment = new Entertainment();
							eventEntertainment.setID(rs.getInt("entertainmentID"));
							eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentDescription"));
							eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentTitle"));
								
								EntertainmentMenu entertainmentOption = new EntertainmentMenu();
								entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
								entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
								entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
								
							eventEntertainment.addEntertainmentOption(entertainmentOption);
							
						eventPackage.setEntertainment(eventEntertainment);
						
							Meal meal = new Meal();
								MealMenu item = new MealMenu();
								item.setMealMenuName(rs.getString("mealMenuName"));
								item.setMealMenuDescription(rs.getString("mealMeuDescription"));
								item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
								item.setMealMenuVegetarian(rs.getBoolean("mealMenuVegetarian"));
								item.setMealMenuHalal(rs.getBoolean("mealMenuHalal"));
							meal.addMealItem(item);
							meal.setMealAvailability(rs.getBoolean("mealAvailability"));
							meal.setMealDescription(rs.getString("mealDescription"));
							meal.setMealDiscount(rs.getFloat("mealDiscount"));
							meal.setMealFinalPrice(rs.getFloat("mealFinalPrice"));
							meal.setMealPricePerHead(rs.getFloat("mealPricePerHead"));
							meal.setMealType(rs.getString("mealType"));
							meal.setMealTitle(rs.getString("mealTitle"));
						
						eventPackage.addMeal(meal);
					
					event.setEventPackage(eventPackage);
						
						Invitation eventInvitation = new Invitation();
						eventInvitation.setInvitationID(rs.getInt("invitationID"));
						eventInvitation.setDateCreated(dateHelper.parseDate(rs.getString("i.dateCreated"), TableNames.DATE_FORMAT));
						eventInvitation.setExpiryDate(dateHelper.parseDate(rs.getString("i.expiryDate"), TableNames.DATE_FORMAT));
					
					event.setEventInvitation(eventInvitation);
				}
				else
				{
					EntertainmentMenu entertainmentOption = new EntertainmentMenu();
					entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
					entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
					entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
					
					event.getEventPackage().getEntertainment().addEntertainmentOption(entertainmentOption);
					
					Meal meal = new Meal();
						MealMenu item = new MealMenu();
						item.setMealMenuName(rs.getString("mealMenuName"));
						item.setMealMenuDescription(rs.getString("mealMeuDescription"));
						item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
						item.setMealMenuVegetarian(rs.getBoolean("mealMenuVegetarian"));
						item.setMealMenuHalal(rs.getBoolean("mealMenuHalal"));
					meal.addMealItem(item);
					meal.setMealAvailability(rs.getBoolean("mealAvailability"));
					meal.setMealDescription(rs.getString("mealDescription"));
					meal.setMealDiscount(rs.getFloat("mealDiscount"));
					meal.setMealFinalPrice(rs.getFloat("mealFinalPrice"));
					meal.setMealPricePerHead(rs.getFloat("mealPricePerHead"));
					meal.setMealType(rs.getString("mealType"));
					meal.setMealTitle(rs.getString("mealTitle"));
					
					event.getEventPackage().addMeal(meal);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return event;
	}
	
	/********************************************************
	 * Method Name 		: RETRIEVE_EVENT_RECORDS
	 * Input Parameter 	: void
	 * Return 			: ArrayList<String>
	 * Purpose 			: TO RETRIEVE EVENT RECORDS
	 *******************************************************/
public ArrayList<Event> RETRIEVE_EVENT_RECORDS(){
		
		MyCalendar m1 = new MyCalendar();
		ArrayList<Event> e1 = new ArrayList<Event>();
		ResultSet rs = null;
		
		String dbQuery;
		
		dbQuery = "SELECT * FROM Notification n INNER JOIN Event e ON n.eventID = e.eventID INNER JOIN Package p On e.packageID=p.packageID INNER JOIN Invitation i ON i.eventID = e.eventID WHERE i.expiryDate <="+"'"+m1.currentDate()+"' AND (n.type='Invitation') AND(e.eventStatus='Pending') GROUP BY e.eventID ORDER BY eventDate";
		//dbQuery = "SELECT e.eventName,e.eventDate FROM Notification n INNER JOIN Invitation i On n.notificationID = i.notificationID INNER JOIN Event e On i.eventID = e.eventID INNER JOIN Package p On e.packageID=p.packageID WHERE i.expiryDate <="+"'"+m1.currentDate()+"'"+  "AND (n.type='Invitation') AND(e.eventStatus='Pending') ORDER BY eventDate";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				e1.add(new Event(rs.getString("e.eventName"), rs.getString("eventDate")));
		}
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			for (int i=0; i<e1.size(); i++)
			{
				System.out.println(e1.get(i).eventName);
			}
			
			return e1;
	}

public int GET_INVITATION_ID_FOR_EVENT(int eventID)
{	int id = 0;
	String dbQuery = "SELECT i.invitationID FROM Invitation i WHERE i.eventID = " +eventID;
	try
	{
		rs=DB.readRequest(dbQuery);
		while(rs.next())
		{
			id = rs.getInt("invitationID");
		}
			
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return id;
	
}

/********************************************************
 * Method Name 		: GET_EVENT_RECORS_DUE_FOR_PAYMENT
 * Input Parameter 	: void
 * Return 			: ArrayList<String>
 * Purpose 			: TO GET EVENT RECORDS DUE FOR PAYMENT
 *******************************************************/

public ArrayList<Event> GET_EVENT_RECORDS_DUE_FOR_PAYMENT(){
	
	ArrayList<Event> e1 = new ArrayList<Event>();
	ResultSet rs = null;
	
	String dbQuery;
	
	
	dbQuery = "SELECT eventName,eventDate FROM Event e INNER JOIN Purchase_Summary ps ON ps.eventID=e.eventID WHERE eventStatus='Awaiting Payment' || (eventStatus='Confirmed' && ps.amountPending !=0 && ps.amountPending IS NOT NULL) ORDER BY eventDate";
	try{
		
		rs=DB.readRequest(dbQuery);
		while(rs.next()){
			e1.add(new Event(rs.getString("eventName"), rs.getString("eventDate")));
	}
	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i=0; i<e1.size(); i++)
		{
			System.out.println(e1.get(i).eventName);
		}
		
		return e1;
}

/********************************************************
 * Method Name 		: GET_EVENT_DETAILS
 * Input Parameter 	: STRING
 * Return 			: ArrayList<String>
 * Purpose 			: TO GET EVENT DETAILS BASED ON EVENT RECORD
 *******************************************************/

public ArrayList<Event> GET_EVENT_DETAILS(String eventName){

	ArrayList<Event> e1 = new ArrayList<Event>();
	ResultSet rs = null;

	String dbQuery;


	dbQuery = "SELECT e.eventTime,e.eventDate,e.eventStatus,e.eventDescription FROM Event e WHERE e.eventName="+"'"+eventName+"'";
	try{
		
		rs=DB.readRequest(dbQuery);
		while(rs.next()){
			Event e= new Event();
			e.setEventTime(rs.getString("eventTime"));
			e.setEventDate(rs.getString("eventDate"));
			e.setEventStatus(rs.getString("eventStatus"));
			e.setEventDescription(rs.getString("eventDescription"));
			e1.add(e);	
			for(int i=0;i<e1.size();i++){
				System.out.println(e1.get(i));
			}
			
			
			}
			
			
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			return e1;
	}

/********************************************************
 * Method Name 		: UPDATE_EVENT_STATUS
 * Input Parameter 	: String eventName,String eventStatus
 * Return 			: BOOLEAN
 * Purpose 			: TO UPDATE THE EVENT STATUS BASED
 * 					  ON THE EVENT RECORD
 *******************************************************/
		
		public boolean UPDATE_EVENT_STATUS(String eventName,String eventStatus){
			
			boolean status=false;
			String dbQuery;
			
			int i=0;
			
			
			dbQuery = "UPDATE Event Set eventStatus='"+eventStatus+"'"+"WHERE eventName="+"'"+eventName+"'";

	try{
		i=DB.updateRequest(dbQuery);
	}

	catch(Exception ex){
		System.out.println("Failed to update event status");
				}
				
				finally{
					
				}
				
				if(i==1){
					status=true;
				
				}
				
				
				return status;
				
			}
		
		public ArrayList<Event> GET_EVENT_RECORDS_FOR_CANCELLATION(){
			
			MyCalendar m1 = new MyCalendar();
			ArrayList<Event> e1 = new ArrayList<Event>();
			ResultSet rs = null;
			
			String dbQuery;
			
			
			dbQuery = "SELECT e.eventName,e.eventDate FROM Event e WHERE e.eventStatus='Awaiting Payment' || e.eventStatus='Confirmed'";
			try{
				
				rs=DB.readRequest(dbQuery);
				while(rs.next()){
					e1.add(new Event(rs.getString("e.eventName"), rs.getString("eventDate")));
			}
			}
				catch(Exception e){
					e.printStackTrace();
				}
				
				for (int i=0; i<e1.size(); i++)
				{
					System.out.println(e1.get(i).eventName);
				}
				
				return e1;

			
		}
		
		
		public boolean RETRIEVE_EVENT(String condition){
			boolean success = false;
			ResultSet rs = null;
			String dbQuery = "SELECT * FROM saharp5_adeel_school.Event";
			
			if (condition != null){
				dbQuery += condition;
			}
			
			rs = DB.readRequest(dbQuery);
			
			try {
					while (rs.next())   
					{   
						String date = rs.getString("eventDate");
						Scanner sc = new Scanner(date);
						sc.useDelimiter("-");
						
						ArrayList<Integer> dateArr = new ArrayList<Integer>();
						
						while (sc.hasNext()){
							dateArr.add(sc.nextInt());
						}
						
						eventDate_list.add(new GregorianCalendar(dateArr.get(0), dateArr.get(1) - 1, dateArr.get(2)));
					
						eventID_list.add(rs.getInt("eventID"));
						packageID_list.add(rs.getInt("packageID"));
						userName_list.add(rs.getString("userName"));
						eventStatus_list.add(rs.getString("eventStatus"));
						eventTime_list.add(rs.getString("eventTime"));
						eventName_list.add(rs.getString("eventName"));
						eventDescription_list.add(rs.getString("eventDescription"));
					    success = true;
					}   
		
				}
			catch (Exception e){
				e.printStackTrace();
			}

			return success;
		}
		
	/********************************************************
	 *				The Accessor Methods
	 *******************************************************/
	public int getID()
	{
		return Integer.parseInt(eventID);
	}
	public void setID(int eventID)
	{
		this.eventID = Integer.toString(eventID);
	}
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
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getPackageID() {
		return packageID;
	}
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
	public Account getEventInitiator()
	{
		return initiator;
	}

	public void setEventInitiator(Account initiator)
	{
		this.initiator = initiator;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	
	public Package getEventPackage()
	{
		return eventPackage;
	}

	public void setEventPackage(Package eventPackage)
	{
		this.eventPackage = eventPackage;
	}

	public EventPhoto getPhoto()
	{
		return photo;
	}

	public void setPhoto(EventPhoto photo)
	{
		this.photo = photo;
	}

	public Invitation getEventInvitation()
	{
		return invitation;
	}

	public void setEventInvitation(Invitation invitation)
	{
		this.invitation = invitation;
	}
	
	public ArrayList<Integer> getEventID_list() {
		return eventID_list;
	}

	public void setEventID_list(int eventID) {
		this.eventID_list.add(eventID);
	}

	public ArrayList<Integer> getPackageID_list() {
		return packageID_list;
	}

	public void setPackageID_list(int packageID) {
		this.packageID_list.add(packageID);
	}

	public ArrayList<String> getUserName_list() {
		return userName_list;
	}

	public void setUserName_list(String userName) {
		this.userName_list.add(userName);
	}

	public ArrayList<String> getEventStatus_list() {
		return eventStatus_list;
	}

	public void setEventStatus_list(String eventStatus) {
		this.eventStatus_list.add(eventStatus);
	}

	public ArrayList<String> getEventName_list() {
		return eventName_list;
	}

	public void setEventName_list(String eventName) {
		this.eventName_list.add(eventName);
	}

	public ArrayList<String> getEventTime_list() {
		return eventTime_list;
	}

	public void setEventTime_list(String eventTime) {
		this.eventTime_list.add(eventTime);
	}

	public ArrayList<String> getEventDescription_list() {
		return eventDescription_list;
	}

	public void setEventDescription_list(String eventDescription) {
		this.eventDescription_list.add(eventDescription);
	}

	public ArrayList<GregorianCalendar> getEventDate_list() {
		return eventDate_list;
	}

	public void setEventDate_list(GregorianCalendar eventDate) {
		this.eventDate_list.add(eventDate);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime
				* result
				+ ((eventDescription == null) ? 0 : eventDescription.hashCode());
		result = prime * result + ((eventID == null) ? 0 : eventID.hashCode());
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((eventStatus == null) ? 0 : eventStatus.hashCode());
		result = prime * result
				+ ((eventTime == null) ? 0 : eventTime.hashCode());
		result = prime * result
				+ ((packageID == null) ? 0 : packageID.hashCode());
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
		Event other = (Event) obj;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventDescription == null) {
			if (other.eventDescription != null)
				return false;
		} else if (!eventDescription.equals(other.eventDescription))
			return false;
		if (eventID == null) {
			if (other.eventID != null)
				return false;
		} else if (!eventID.equals(other.eventID))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventStatus == null) {
			if (other.eventStatus != null)
				return false;
		} else if (!eventStatus.equals(other.eventStatus))
			return false;
		if (eventTime == null) {
			if (other.eventTime != null)
				return false;
		} else if (!eventTime.equals(other.eventTime))
			return false;
		if (packageID == null) {
			if (other.packageID != null)
				return false;
		} else if (!packageID.equals(other.packageID))
			return false;
		return true;
	}

}
