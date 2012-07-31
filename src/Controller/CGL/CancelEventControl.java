package Controller.CGL;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.EmailController;
import Model.Ballroom;
import Model.Entertainment;
import Model.Event;
import Model.Facility;
import Model.Invitation;
import Model.Meal;
import Model.Package;
import Model.Membership.Guest;

public class CancelEventControl {
	
	public ArrayList<Event> processSelection(){
				
		Event e1 = new Event();
		return e1.GET_EVENT_RECORDS_FOR_CANCELLATION();
	}
	
	
	public ArrayList<String> requestSelectedEventDetails(String eventName){
		Ballroom b1 = new Ballroom();
		ArrayList<String> ballroom= new ArrayList<String>();
		ballroom=b1.GET_BALLROOM_DETAILS(eventName);
		
		ArrayList<String> entertainment = new ArrayList<String>();
		Entertainment e1 = new Entertainment();
		entertainment=e1.GET_ENTERTAINMENT_PRICE(eventName);
		
		ArrayList<String> event = new ArrayList<String>();
		Event ev1 = new Event();
		event=ev1.GET_EVENT_DETAILS(eventName);
		
		ArrayList<String> guest = new ArrayList<String>();
		Guest g1= new Guest();
		guest=g1.getNumberOfGuests(eventName);
		
		ArrayList<String> meal = new ArrayList<String>();
		Meal m1 = new Meal();
		try{
		meal=m1.GET_MEAL_PRICE(eventName);
		}
		
		catch(Exception ex){
			meal.add("0");
		}
		
		
		
		double ballroomFinalPrice;
		String ballroomName;
		ArrayList<String> combined = new ArrayList<String>();
		try {
		Scanner sc = new Scanner(ballroom.get(0));
		sc.useDelimiter(",");
		ballroomName=sc.next();
		ballroomFinalPrice=sc.nextDouble();
		}
		catch (Exception ex)
		{
			ballroomFinalPrice = 0;
			ballroomName = "None";
		}
		double entertainmentPrice;
		
		try{
		Scanner sc1 = new Scanner(entertainment.get(0));
		entertainmentPrice=sc1.nextDouble();
		
		}
		
		catch(Exception ex){
			 entertainmentPrice=0;
		}
		
		Scanner sc2 = new Scanner(event.get(0));
		sc2.useDelimiter(",");
		String eventTime=sc2.next();
		String eventDate=sc2.next();
		String eventStatus=sc2.next();
		String eventDescription=sc2.next();
		
		Scanner sc3 = new Scanner(guest.get(0));
		
		String guestCount=sc3.next();
		
		double mealPrice;
		try{
		Scanner sc4 = new Scanner(meal.get(0));
		sc4.useDelimiter(",");
		mealPrice =sc4.nextDouble();
		}
		
		catch(Exception ex){
			mealPrice=0;
		}
		
		Facility f1 = new Facility();
		String facility=f1.GET_FACILITY(eventName).get(0);
		
		
		Package p = new Package();
		String pkgDiscount=p.GET_PACKAGE_DISCOUNT(eventName).get(0);
		double packageDiscount=Double.parseDouble(pkgDiscount);
		
		double totalPrice=ballroomFinalPrice+entertainmentPrice+mealPrice-packageDiscount;
		

		
		
		
		
		
		for(int i=0;i<meal.size();i++){
			String combine=ballroomName+","+eventTime+","+eventDate+","+eventStatus+","+eventDescription+","+guestCount+","+totalPrice+","+ballroomFinalPrice+","+entertainmentPrice+","+mealPrice+","+facility+","+pkgDiscount;
			combined.add(combine);
		}
		
		
		
		System.out.println(combined.get(0));
		
		
		return combined;
	}
	
	public boolean processCancellation(String eventName,String eventDate,String status,String reason){
		
		String[] guestEmail=null;
		Invitation invitation = new Invitation();
		ArrayList<String> attendingGuests = new ArrayList<String>();
		
		for(int i=0;i<invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).size();i++){
			guestEmail[i]=invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).get(i).getEmail();
		}
		
		String content="Hi"+"\n"+"The event : "+eventName +"which was supposed to be held on "+eventDate +"has been cancelled due to"+reason;
		
		EmailController email = new EmailController();
		try {
			email.sendEmail("TEXT", guestEmail, "Cancellation Of Event: "+eventName, content, null, 1, "Cancellation");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		//get all attending guests based on invitation id
		//prepare email
		//update date & time
		//updates event record
		
	}


}
