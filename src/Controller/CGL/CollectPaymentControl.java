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
import Model.Package;
import Model.Membership.Guest;
import Model.Meal;
import Model.CGL.Purchase_Payment;
import Model.CGL.Purchase_Summary;

public class CollectPaymentControl {
	
	public ArrayList<Event> processSelection(){
		
		Event e1 = new Event();
		return e1.GET_EVENT_RECORDS_DUE_FOR_PAYMENT();
	}

	public ArrayList<String> processPaymentDetails(String eventName){
		Purchase_Summary p1 = new Purchase_Summary();
		
		return p1.RETRIEVE_PAYMENT_DETAILS(eventName);
	}

	public boolean processUpdatePurchasePayment(String amount,String paymentMethod,String totalCost,String eventName){
		Purchase_Payment p1 = new Purchase_Payment();
		
		return p1.UPDATES_PURCHASE_PAYMENT(amount, paymentMethod, totalCost, eventName);
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
		meal=m1.GET_MEAL_PRICE(eventName);
		
		
		ArrayList<String> combined = new ArrayList<String>();
		
		double ballroomFinalPrice;
		String ballroomName;
		
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
	
	public boolean processAmountEntered(String amount,String paymentMethod,String totalCost,String eventName,String eventDate,String location,String eventStatus,File pdf,String content,String subject,String type){
		
		boolean success=false;
		//updates amount paid in purchase summary
		//prepare email/
		//update date and time notification table
		try
		{
			Purchase_Payment p1 = new Purchase_Payment();
			success= p1.UPDATES_PURCHASE_PAYMENT(amount, paymentMethod, totalCost, eventName);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Failed to update purchase payment");
			return success=false;
		}
		

		
		Invitation invitation = new Invitation();
		String[] guestEmail= new String[invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).size()];
		
		for(int i=0;i<invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).size();i++){
			guestEmail[i]=invitation.GET_ALL_ATTENDING_GUESTS(eventName, eventDate).get(i).getEmail();
		}
		
		
		
		
		//send email to registered guest regarding the cancellation
		
		EmailController email = new EmailController();
		try {

			email.sendEmail("TEXT", guestEmail, subject, content, pdf, type);
			success=true;
		} catch (Exception e) {
			
			
			JOptionPane.showMessageDialog(null, "Email Failure");
			success=false;
			return success;
		}
		
		try{
			Event e1 = new Event();
			success=e1.UPDATE_EVENT_STATUS(eventName, eventStatus);
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Failed to Update event Status");
			success=false;
		}
		
		return success;
	}

}
