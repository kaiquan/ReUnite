package Controller.CGL;
//**********************************************************
//Project : OOADPJ (IT2297)
//Admin No: 111942S
//
//Author: A Ameenudeen
//Class Name:CancelEventControl.java
//
//Honor Code: I pledge that this program represents my own
//program code.
//*********************************************************
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controller.EmailController;
import Model.Ballroom;
import Model.Entertainment;
import Model.Event;
import Model.Facility;
import Model.Invitation;
import Model.Meal;
import Model.Package;
import Model.CGL.Event_Cancellation;
import Model.Membership.Guest;
import View.CGL.ProgressBar;

public class CancelEventControl {
	
	//get events records such as date and eventname for cancellation
	public ArrayList<Event> processSelection()
	{
		Event e1 = new Event();
		return e1.GET_EVENT_RECORDS_FOR_CANCELLATION();
	}
	
	
	public ArrayList<String> requestSelectedEventDetails(String eventName)
	{	
		//Creates Ballroom Object
		//Creates ArrayList of ballroom object 
		//get the ballroom details and adds it to the arrayList
		Ballroom b1 = new Ballroom();
		ArrayList<Ballroom> ballroom= new ArrayList<Ballroom>();
		ballroom=b1.GET_BALLROOM_DETAILS(eventName);
		
		
		//Creates Entertainment Object
		//Creates ArrayList of entertainment objects 
		//get the entertainment details and adds it to the arrayList
		ArrayList<Entertainment> entertainment = new ArrayList<Entertainment>();
		Entertainment e1 = new Entertainment();
		entertainment=e1.GET_ENTERTAINMENT_PRICE(eventName);
		
		//Creates event Object
		//Creates ArrayList of event objects
		//get the event details and adds it to the arrayList
		ArrayList<Event> event = new ArrayList<Event>();
		Event ev1 = new Event();
		event=ev1.GET_EVENT_DETAILS(eventName);
		
		//Creates guest Object
		//Creates ArrayList of guest object
		//get the number of guests and adds it to the arrayList
		ArrayList<Guest> guest = new ArrayList<Guest>();
		Guest g1= new Guest();
		guest=g1.getNumberOfGuests(eventName);
		
		//Creates meal Object
		//Creates ArrayList of meal object
		//get the meal details and adds it to the arrayList Line 89
		ArrayList<Meal> meal = new ArrayList<Meal>();
		Meal m1 = new Meal();
		
		//Creates facility Object
		//Creates ArrayList of facility object 
		//get the facility name
		ArrayList<Facility> facilty = new ArrayList<Facility>();
		Facility f1 = new Facility();
		facilty=f1.GET_FACILITY(eventName);
		
		//Creates Package Object
		//Creates ArrayList of package objects
		//get the package details and adds it to the arrayList
		ArrayList<Package> pkg  = new ArrayList<Package>();
		Package p1 = new Package();
		pkg=p1.GET_PACKAGE_DISCOUNT(eventName);
						
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
			ballroomFinalPrice=Math.round(ballroom.get(0).getBallroomFinalPrice());
		}
		catch (Exception ex)
		{
			ballroomFinalPrice = 0;
			ballroomName="None";
			
		}
		
		//get the entertainment price based on the arraylist 
		//we create earlier		
		double entertainmentPrice;
		
		try
		{
			entertainmentPrice=Math.round(entertainment.get(0).getEntertainmentFinalPrice());
		}
		
		catch(Exception ex)
		{
			 entertainmentPrice=0;
		}
		
		
		//get the EventTime,EventDate,EventStatus,EventDescription based on the arraylist 
		//which we create earlier
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
			mealPrice=Math.round(meal.get(0).getMealFinalPrice());
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
		
		
		
		//Create an arrayList to store all the details which will passed to the form
		ArrayList<String> combined = new ArrayList<String>();
		
		
		//Combine all the attributes which will be passed to the form
		for(int i=0;i<1;i++){
			String combine=ballroomName+"~"+eventTime+"~"+eventDate+"~"+eventStatus+"~"+eventDescription+"~"+guestCount+"~"+totalPrice+"~"+ballroomFinalPrice+"~"+entertainmentPrice+"~"+mealPrice+"~"+facility+"~"+packageDiscount;
			combined.add(combine);
		}
			
		return combined;
	}
	
	// 1)Sends email to registered guests
	// 2)Updates event status to cancelled
	// 3)Updates Cancellation table with cancellation details

	public boolean processCancellation(String eventName,String eventDate,String reason,String eventStatus,String location){
		  	
		boolean success=false;
		
		Invitation invitation = new Invitation();
		String[] guestEmail= new String[invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).size()];
		
		for(int i=0;i<invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).size();i++){
			guestEmail[i]=invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).get(i).getEmail();
		}
		
		String content="Dear Sir/Madam"+"\n"+"\n"+"We are sorry to inform you that the following event : "+eventName +" which was supposed to be held on "+eventDate +" at "+location+" has been cancelled due to "+reason+"."+"\n"+"\n"+"For any clarifications regarding the cancellation please do not hesitate to call us at 67747173. "+"\n"+"\n"+"Shahrikin"+"\n"+"GR Administrator"+"\n"+"\n"+"This is a computer generated letter";
		
		
		//send email to registered guest regarding the cancellation
		
		EmailController email = new EmailController();
		try {
			email.sendEmail("TEXT", guestEmail, "RE:Cancellation Of Event: "+eventName, content, null, "Cancellation");
			success=true;
		} catch (Exception e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Email Failure");
			success=false;
			return success;
		}
		
		Event_Cancellation c1 = new Event_Cancellation();
		
		//updates event status to cancelled
		
		try{
		c1.UPDATE_EVENT_STATUS(eventName, eventStatus);
		success=true;
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to update event Status");
			success=false;
			return success;
		}
		
		//updates cancellation details
		
		try{
			c1.UPDATES_CANCELLATION_DETAILS(eventName, reason);
			success=true;
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to update Cancellation Details");
			success=false;
			return success;
		}
		return success;
		
	}


}
