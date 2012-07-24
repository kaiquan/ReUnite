package Controller.CGL;

import java.util.ArrayList;
import java.util.Scanner;

import Model.CGL.Ballroom;
import Model.CGL.Entertainment;
import Model.CGL.Event;
import Model.CGL.Guest;
import Model.CGL.Meal;
import Model.CGL.Purchase_Payment;
import Model.CGL.Purchase_Summary;

public class ConsolidateGuestListControl {
	
public ArrayList<Event> processExpiredInvitation(){
				
		Event e1 = new Event();
		return e1.retrieveEventRecords();
	}
	
public ArrayList<String> requestSelectedEventDetails(String eventName){
	Ballroom b1 = new Ballroom();
	ArrayList<String> ballroom= new ArrayList<String>();
	ballroom=b1.getBallroomDetails(eventName);
	
	ArrayList<String> entertainment = new ArrayList<String>();
	Entertainment e1 = new Entertainment();
	entertainment=e1.getEntertainmentPrice(eventName);
	
	ArrayList<String> event = new ArrayList<String>();
	Event ev1 = new Event();
	event=ev1.getEventDetails(eventName);
	
	ArrayList<String> guest = new ArrayList<String>();
	Guest g1= new Guest();
	guest=g1.getNumberOfGuests(eventName);
	
	ArrayList<String> meal = new ArrayList<String>();
	Meal m1 = new Meal();
	meal=m1.getMealPrice(eventName);
	
	
	ArrayList<String> combined = new ArrayList<String>();
	
	Scanner sc = new Scanner(ballroom.get(0));
	sc.useDelimiter(",");
	String ballroomName=sc.next();
	double ballroomFinalPrice=sc.nextDouble();
	
	Scanner sc1 = new Scanner(entertainment.get(0));
	double entertainmentPrice=sc1.nextDouble();
	
	Scanner sc2 = new Scanner(event.get(0));
	sc2.useDelimiter(",");
	String eventTime=sc2.next();
	String eventDate=sc2.next();
	String eventStatus=sc2.next();
	
	Scanner sc3 = new Scanner(guest.get(0));
	
	String guestCount=sc3.next();
	
	Scanner sc4 = new Scanner(meal.get(0));
	sc4.useDelimiter(",");
	double mealPrice =sc4.nextDouble();
	
	double totalPrice=ballroomFinalPrice+entertainmentPrice+mealPrice;
	
	
	
	
	
	for(int i=0;i<meal.size();i++){
		String combine=ballroomName+","+eventTime+","+eventDate+","+eventStatus+","+guestCount+","+totalPrice+","+ballroomFinalPrice+","+entertainmentPrice+","+mealPrice;
		combined.add(combine);
	}
	
	
	
	System.out.println(totalPrice);
	
	
	return combined;
}

public boolean updateTotalPayableAmount(String amount,String eventName){
	
	Purchase_Summary p1 = new Purchase_Summary();
	
	
	return p1.updatesTotalPayableAmount(amount,eventName);
}

public boolean processUpdateEventStatus(String eventName,String eventStatus){
	Event e1 = new Event();
	return e1.updateEventStatus(eventName,eventStatus);
}



	
	


}
