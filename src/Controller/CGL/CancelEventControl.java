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
import Model.Invitation;
import Model.Meal;
import Model.Package;
import Model.CGL.Event_Cancellation;
import Model.Membership.Guest;

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
		//Creates ArrayList of String 
		//get the ballroom details and adds it to the arrayList
		Ballroom b1 = new Ballroom();
		ArrayList<String> ballroom= new ArrayList<String>();
		ballroom=b1.GET_BALLROOM_DETAILS(eventName);
		
		
		//Creates Entertainment Object
		//Creates ArrayList of String 
		//get the entertainment details and adds it to the arrayList
		ArrayList<String> entertainment = new ArrayList<String>();
		Entertainment e1 = new Entertainment();
		entertainment=e1.GET_ENTERTAINMENT_PRICE(eventName);
		
		//Creates event Object
		//Creates ArrayList of String 
		//get the event details and adds it to the arrayList
		ArrayList<String> event = new ArrayList<String>();
		Event ev1 = new Event();
		event=ev1.GET_EVENT_DETAILS(eventName);
		
		//Creates guest Object
		//Creates ArrayList of String 
		//get the number of guests and adds it to the arrayList
		ArrayList<String> guest = new ArrayList<String>();
		Guest g1= new Guest();
		guest=g1.getNumberOfGuests(eventName);
		
		//Creates meal Object
		//Creates ArrayList of String 
		//get the meal details and adds it to the arrayList
		ArrayList<String> meal = new ArrayList<String>();
		Meal m1 = new Meal();
		
		//If no meal option is selected it add 0 into the arraylist instead
		try
		{
			meal=m1.GET_MEAL_PRICE(eventName);
		}
		
		catch(Exception ex)
		{
			meal.add("0");
		}
		
		
		//get the ballroomFinalPrice & the ballroom name based on the arraylist 
		//we create earlier
		double ballroomFinalPrice;
		String ballroomName;
		
		try 
		{
			Scanner sc = new Scanner(ballroom.get(0));
			sc.useDelimiter(",");
			ballroomName=sc.next();
			ballroomFinalPrice=sc.nextDouble();
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
			Scanner sc1 = new Scanner(entertainment.get(0));
			entertainmentPrice=sc1.nextDouble();
		}
		
		catch(Exception ex)
		{
			 entertainmentPrice=0;
		}
		
		
		//get the EventTime,EventDate,EventStatus,EventDescription based on the arraylist 
		//we create earlier
		Scanner sc2 = new Scanner(event.get(0));
		sc2.useDelimiter(",");
		String eventTime=sc2.next();
		String eventDate=sc2.next();
		String eventStatus=sc2.next();
		String eventDescription=sc2.next();
		
		
		//get the number of guests based on the arraylist 
		//we create earlier
		Scanner sc3 = new Scanner(guest.get(0));
		String guestCount=sc3.next();
		
		//get the total meal price based on the different options selected by guests based on the arraylist 
		//we create earlier
		double mealPrice;
		try
		{
			Scanner sc4 = new Scanner(meal.get(0));
			sc4.useDelimiter(",");
			mealPrice =sc4.nextDouble();
		}
		
		catch(Exception ex)
		{
			mealPrice=0;
		}
		
		
		//get the facility name based on the arraylist 
		//we create earlier
		Facility f1 = new Facility();
		String facility=f1.GET_FACILITY(eventName).get(0);
		
		//get the package discount based on the arraylist 
		//we create earlier
		Package p = new Package();
		String pkgDiscount=p.GET_PACKAGE_DISCOUNT(eventName).get(0);
		double packageDiscount=Double.parseDouble(pkgDiscount);
		
		//Calculates total payable amount
		double totalPrice=ballroomFinalPrice+entertainmentPrice+mealPrice-packageDiscount;
		
		//Create an arrayList to store all the details which will passed to the form
		ArrayList<String> combined = new ArrayList<String>();
		
		
		for(int i=0;i<1;i++){
			String combine=ballroomName+","+eventTime+","+eventDate+","+eventStatus+","+eventDescription+","+guestCount+","+totalPrice+","+ballroomFinalPrice+","+entertainmentPrice+","+mealPrice+","+facility+","+pkgDiscount;
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
			email.sendEmail("TEXT", guestEmail, "RE:Cancellation Of Event: "+eventName, content, null, 1, "Cancellation");
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
