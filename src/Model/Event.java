/********************************************************************************************************************************************************
Program Name			:	Package.java
Description				:	A Package Model class that is the Model for Package record(s)
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Edited				:	6-8-2012
=========================================================================================================================================================
USERS USING THIS CLASS	:	Lee Kai Quan (114173S)
METHODS LIST			:	Event()
						: 	Event(String, String, String, String, String, String)
						:	InitiateEvent() : Boolean
						:	retrieveTimings(String, String) : ArrayList<String>
						:	Accessor Methods...
********************************************************************************************************************************************************/
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Controller.MyCalendar;
import Controller.MySQLController;
import Model.Membership.Account;
import Model.RIM.TableNames;


public class Event {

	/********************************************************
	 *					The Attributes
	 *******************************************************/
	
	private Ballroom data;								//stores data from the controler;
	private ResultSet rs;								//result set to ertrive items directly from database
	private static MySQLController DB = new MySQLController();
	
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
	

	/********************************************************
	 *				The Constructor(s)
	 *******************************************************/
	
	public Event(){}
	public Event(String packageID, String userName, String eventStatus, String eventDate, String eventTime, String eventName){
		this.packageID=packageID;
		this.userName=userName;
		this.eventStatus=eventStatus;
		this.eventDate=eventDate;
		this.eventTime=eventTime;
		this.eventName=eventName;
	}
	

	/********************************************************
	 *				The Method(s)
	 *******************************************************/
	
	
	/********************************************************
	 * Method Name 		: InitiateEvent()
	 * Input Parameter 	: void
	 * Return 			: String
	 * Purpose 			: To create a new event and purchase 
	 * 					  record
	 *******************************************************/
	public boolean InitiateEvent(){
		boolean success=false;
		String sqlQuery;

		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Event` (`packageID`, `userName`, `eventStatus`, `eventDate`, `eventTime`, `eventName`, `eventDescription`)";
		sqlQuery +="VALUES ("+this.packageID+", '"+this.userName+"', 'Pending', '"+this.eventDate+"', '"+this.eventTime+"', '"+this.eventName+"','"+this.eventDescription+"')";
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
		sqlQuery="SELECT * FROM saharp5_adeel_school.Event;";
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
		sqlQuery = "INSERT INTO `saharp5_adeel_school`.`Purchase_Summary` (`eventID`,`dateOfPurchase`) VALUES ('"+this.eventID+"','"+MyCalendar.formatDate(new GregorianCalendar())+"')"; 
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
		
		return success;
	}
	/********************************************************
	 * Method Name 		: retrieveTimings()
	 * Input Parameter 	: String, String
	 * Return 			: ArrayList<String>
	 * Purpose 			: To retrieve the timings by 
	 * 					  ballroom and date
	 *******************************************************/
	public ArrayList<String> retrieveTimings(String ballroomID, String Date){
		ArrayList<String> packagelist= new ArrayList<String>();
		String sqlQuery;
		
		sqlQuery="SELECT distinct packageID FROM saharp5_adeel_school.Package WHERE ballroomID="+ballroomID+"";
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
			sqlQuery = "SELECT * FROM saharp5_adeel_school.Event WHERE (`eventStatus`='Pending' AND `eventDate`='"+Date+"'  AND `packageID`="+packagelist.get(i)+")";
			
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
	
	public ArrayList<Event> GET_ALL_EVENTS()
	{
		ArrayList<Event> eventList = new ArrayList<Event>();
		
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
											+" ORDER BY e.eventID"
										);
			
		int prev = 0;
		while (rs.next())
		{
			Event event;
			if(rs.getInt("eventID")!=prev){
				prev = rs.getInt("eventID");
				
				event = new Event();

				event.setID(rs.getInt("eventID"));
				event.setEventName(rs.getString("eventName"));
				event.setEventDate(rs.getString("eventDate").trim() + " 00:00:00");
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
						eventBallroom.setFacilityAddress(rs.getString("facilityAddress"));
						eventBallroom.setFacilityName(rs.getString("facilityName"));
						eventBallroom.setFacilityDescription(rs.getString("facilityDescription"));
						eventBallroom.setFacilityContact(rs.getString("facilityContact"));
						eventBallroom.setFacilityParking(rs.getBoolean("facilityParking"));
						eventBallroom.setFacilityWeekendExtraCost(rs.getFloat("facilityWeekendExtraCost"));
						
					eventPackage.setBallroom(eventBallroom);
					
						Entertainment eventEntertainment = new Entertainment();
						eventEntertainment.setEntertainmentID(rs.getInt("entertainmentID"));
						eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentDescription"));
						eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentTitle"));
							
							EventPackageEntertainmentOption entertainmentOption = new EventPackageEntertainmentOption();
							entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
							entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
							entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
							
						eventEntertainment.addEntertainmentOption(entertainmentOption);
						
					eventPackage.setEntertainment(eventEntertainment);
					
						EventPackageMeal meal = new EventPackageMeal();
							EventPackageMealItem item = new EventPackageMealItem();
							item.setMealMenuName(rs.getString("mealMenuName"));
							item.setMealMenuDescription(rs.getString("mealMeuDescription"));
							item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
							item.setMealMenuVegeterian(rs.getBoolean("mealMenuVegetarian"));
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
					
					EventInvitation eventInvitation = new EventInvitation();
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
				
				EventPackageEntertainmentOption entertainmentOption = new EventPackageEntertainmentOption();
				entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
				entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
				entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
				
				event.getEventPackage().getEntertainment().addEntertainmentOption(entertainmentOption);
				
				EventPackageMeal meal = new EventPackageMeal();
					EventPackageMealItem item = new EventPackageMealItem();
					item.setMealMenuName(rs.getString("mealMenuName"));
					item.setMealMenuDescription(rs.getString("mealMeuDescription"));
					item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
					item.setMealMenuVegeterian(rs.getBoolean("mealMenuVegetarian"));
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
	

	public Event GET_EVENT_BY_ID(int eventID)
	{
		Event event = new Event();
		try
		{
			ResultSet rs = db.readRequest(	"SELECT * FROM " + TableNames.EVENT_TABLE + " e" 
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
											+" ORDER BY e.eventID "
				);
			
			int prev = 0;
			while (rs.next())
			{
				if(rs.getInt("eventID")!=prev){
					prev = rs.getInt("eventID");
					
					event.setEventID(rs.getInt("eventID"));
					event.setEventName(rs.getString("eventName"));
					event.setEventDate(rs.getString("eventDate").trim() + " 00:00:00");
					event.setEventTime(rs.getString("eventTime"));
					event.setEventStatus(rs.getString("eventStatus"));
					event.setEventDescription(rs.getString("eventDescription"));
					event.setEventInitiator(new Account(rs.getString("userName")));
					
						EventPackage eventPackage = new EventPackage();
						eventPackage.setPackageID(rs.getInt("packageID"));
						eventPackage.setPackageTitle(rs.getString("packageTitle"));
						eventPackage.setPackageDescription(rs.getString("packageDescription"));
						eventPackage.setPackageType(rs.getString("packageType"));
						
							EventPackageBallroom eventBallroom = new EventPackageBallroom();
							eventBallroom.setBallroomID(rs.getInt("ballroomID"));
							eventBallroom.setBallroomDescription(rs.getString("ballroomDescription"));
							eventBallroom.setBallroomName(rs.getString("ballroomName"));
							eventBallroom.setBallroomSize(rs.getString("ballroomSize"));
							eventBallroom.setFacilityAddress(rs.getString("facilityAddress"));
							eventBallroom.setFacilityName(rs.getString("facilityName"));
							eventBallroom.setFacilityDescription(rs.getString("facilityDescription"));
							eventBallroom.setFacilityContact(rs.getString("facilityContact"));
							eventBallroom.setFacilityParking(rs.getBoolean("facilityParking"));
							eventBallroom.setFacilityWeekendExtraCost(rs.getFloat("facilityWeekendExtraCost"));
							
						eventPackage.setBallroom(eventBallroom);
						
							EventPackageEntertainment eventEntertainment = new EventPackageEntertainment();
							eventEntertainment.setEntertainmentID(rs.getInt("entertainmentID"));
							eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentDescription"));
							eventEntertainment.setEntertainmentDescription(rs.getString("entertainmentTitle"));
								
								EventPackageEntertainmentOption entertainmentOption = new EventPackageEntertainmentOption();
								entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
								entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
								entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
								
							eventEntertainment.addEntertainmentOption(entertainmentOption);
							
						eventPackage.setEntertainment(eventEntertainment);
						
							EventPackageMeal meal = new EventPackageMeal();
								EventPackageMealItem item = new EventPackageMealItem();
								item.setMealMenuName(rs.getString("mealMenuName"));
								item.setMealMenuDescription(rs.getString("mealMeuDescription"));
								item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
								item.setMealMenuVegeterian(rs.getBoolean("mealMenuVegetarian"));
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
					
					EventPackageEntertainmentOption entertainmentOption = new EventPackageEntertainmentOption();
					entertainmentOption.setEntertainmentMenuDescription(rs.getString("em.entertainmentMenuDescription"));
					entertainmentOption.setEntertainmentMenuName(rs.getString("em.entertainmentMenuName"));
					entertainmentOption.setEntertainmentMenuPrice(rs.getFloat("em.entertainmentMenuPrice"));
					
					event.getEventPackage().getEntertainment().addEntertainmentOption(entertainmentOption);
					
					EventPackageMeal meal = new EventPackageMeal();
						EventPackageMealItem item = new EventPackageMealItem();
						item.setMealMenuName(rs.getString("mealMenuName"));
						item.setMealMenuDescription(rs.getString("mealMeuDescription"));
						item.setMealMenuPrice(rs.getFloat("mealMenuPrice"));
						item.setMealMenuVegeterian(rs.getBoolean("mealMenuVegetarian"));
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
}
