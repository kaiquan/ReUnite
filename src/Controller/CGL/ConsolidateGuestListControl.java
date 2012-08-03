package Controller.CGL;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controller.EmailController;
import Model.Ballroom;
import Model.Entertainment;
import Model.Event;
import Model.Facility;
import Model.Membership.Guest;
import Model.Membership.RI;
import Model.Meal;
import Model.CGL.Purchase_Summary;
import Model.Package;

public class ConsolidateGuestListControl {
	
public ArrayList<Event> processExpiredInvitation(){
				
		Event e1 = new Event();
		return e1.RETRIEVE_EVENT_RECORDS();
	}
	
public ArrayList<String> requestSelectedEventDetails(String eventName)
{	
	//Creates Ballroom Object
	//Creates ArrayList of String 
	//get the ballroom details and adds it to the arrayList
	Ballroom b1 = new Ballroom();
	ArrayList<Ballroom> ballroom= new ArrayList<Ballroom>();
	ballroom=b1.GET_BALLROOM_DETAILS(eventName);
	
	
	//Creates Entertainment Object
	//Creates ArrayList of String 
	//get the entertainment details and adds it to the arrayList
	ArrayList<Entertainment> entertainment = new ArrayList<Entertainment>();
	Entertainment e1 = new Entertainment();
	entertainment=e1.GET_ENTERTAINMENT_PRICE(eventName);
	
	//Creates event Object
	//Creates ArrayList of String 
	//get the event details and adds it to the arrayList
	ArrayList<Event> event = new ArrayList<Event>();
	Event ev1 = new Event();
	event=ev1.GET_EVENT_DETAILS(eventName);
	
	//Creates guest Object
	//Creates ArrayList of String 
	//get the number of guests and adds it to the arrayList
	ArrayList<Guest> guest = new ArrayList<Guest>();
	Guest g1= new Guest();
	guest=g1.getNumberOfGuests(eventName);
	
	//Creates meal Object
	//Creates ArrayList of String 
	//get the meal details and adds it to the arrayList
	ArrayList<Meal> meal = new ArrayList<Meal>();
	Meal m1 = new Meal();
	
	//Creates facility Object
	//Creates ArrayList of String 
	//get the facilty name
	ArrayList<Facility> facilty = new ArrayList<Facility>();
	Facility f1 = new Facility();
	facilty=f1.GET_FACILITY(eventName);
	
	//Creates meal Object
	//Creates ArrayList of String 
	//get the meal details and adds it to the arrayList
	ArrayList<Package> pkg  = new ArrayList<Package>();
	Package p1 = new Package();
	pkg=p1.GET_PACKAGE_DISCOUNT(eventName);
	
	ArrayList<RI> email = new ArrayList<RI>();
	RI ri = new RI();
	email=ri.GET_RI_EMAIL(eventName);
	
	//If no meal option is selected it add 0 into the arraylist instead
	try
	{
		meal=m1.GET_MEAL_PRICE(eventName);
	}
	
	catch(Exception ex)
	{
		meal.get(0).setMealFinalPrice(0);
	}
	
	
	//get the ballroomFinalPrice & the ballroom name based on the arraylist 
	//we create earlier
	double ballroomFinalPrice;
	String ballroomName;
	
	try 
	{
		ballroomName=ballroom.get(0).getBallroomName();
		ballroomFinalPrice=(ballroom.get(0).getBallroomFinalPrice());
	}
	catch (Exception ex)
	{
		ballroomFinalPrice = 0;
		ballroomName="None";
		
	}
	
	//get the entertainment based on the arraylist 
	//we create earlier		
	double entertainmentPrice;
	
	try
	{
		
		entertainmentPrice=entertainment.get(0).getEntertainmentFinalPrice();
	}
	
	catch(Exception ex)
	{
		 entertainmentPrice=0;
	}
	
	
	//get the EventTime,EventDate,EventStatus,EventDescription based on the arraylist 
	//we create earlier
	String eventTime=event.get(0).getEventTime();
	System.out.println(event.get(0).getEventTime());
	String eventDate=event.get(0).getEventDate();
	String eventStatus=event.get(0).getEventStatus();
	String eventDescription=event.get(0).getEventDescription();
	
	
	//get the number of guests based on the arraylist 
	//we create earlier
	
	String guestCount=guest.get(0).getNoOfGuests();
	
	//get the total meal price based on the different options selected by guests based on the arraylist 
	//we create earlier
	double mealPrice;
	try
	{
		
		mealPrice =meal.get(0).getMealFinalPrice();
	}
	
	catch(Exception ex)
	{
		mealPrice=0;
	}
	
	
	//get the facility name based on the arraylist 
	//we create earlier
	String facility=facilty.get(0).getFacilityName();
	
	//get the package discount based on the arraylist 
	//we create earlier
	Package p = new Package();
	
	double packageDiscount=pkg.get(0).getPackageDiscount();
	
	//Calculates total payable amount
	double totalPrice=ballroomFinalPrice+entertainmentPrice+mealPrice-packageDiscount;
	
	String emailAddress=email.get(0).getEmail();
	
	//Create an arrayList to store all the details which will passed to the form
	ArrayList<String> combined = new ArrayList<String>();
	
	
	
	for(int i=0;i<1;i++){
		String combine=ballroomName+","+eventTime+","+eventDate+","+eventStatus+","+eventDescription+","+guestCount+","+totalPrice+","+ballroomFinalPrice+","+entertainmentPrice+","+mealPrice+","+facility+","+packageDiscount+","+emailAddress;
		combined.add(combine);
	}
		
	return combined;
}


public boolean processEventConsolidation(String amount,String eventName,String recipient,File pdf,String eventStatus){
	
	boolean success=false;
	//updates total payable amount
	try
	{
		Purchase_Summary p1 = new Purchase_Summary();
		p1.UPDATES_TOTAL_PAYABLE_AMOUNT(amount, eventName);
		success=true;
	}
	catch(Exception ex){
		JOptionPane.showMessageDialog(null, "Failed to update total payable amount");
		success=false;
		return success;
	}
	
	try{
	String content="Dear Sir/Madam"+"\n"+"\n"+"Kindly make your first payment for the event "+eventName +"\n"+"For more details regarding the payment, kindly refer to the attachment attached with this email or you can contact us at 67747173."+"\n"+"\n"+"Shahrikin"+"\n"+"GR Administrator";
	//prepares email
	String[] emailAddress= new String[1];
	emailAddress[0]=recipient;
	EmailController email = new EmailController();
	email.sendEmail("TEXT", emailAddress, "RE : Payment Notification for event "+eventName, content, pdf, "Payment");
	success=true;
	}
	
		
	catch(Exception ex){
		JOptionPane.showMessageDialog(null, "Failed to send email to RI");
		success=false;
		return success;
	}
	
	try{
		
		Event e1 = new Event();
		e1.UPDATE_EVENT_STATUS(eventName, eventStatus);
		success=true;
	}
	
	catch(Exception ex){
		JOptionPane.showMessageDialog(null, "Failed to update event status");
		success=false;
	}
	
	return success;
}

}
