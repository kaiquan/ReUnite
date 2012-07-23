/*******************************************************************************
Program Name:	MyCalendar.java
Description:	A MyCalendar class for formatting and setting dates
Done by:		Lee Kai Quan
Admin No:		114173S
Module Group:	IT2297-08
*******************************************************************************/
package Controller.SOM;
import java.util.*;

public class MyCalendar {

	//Get the number of days for first date minus second date
	public static long getDifference (GregorianCalendar d1, 
								GregorianCalendar d2){
									
		long firstDate = d1.getTimeInMillis();
		long secDate = d2.getTimeInMillis();
		
		return (firstDate - secDate)/(24*60*60*1000);
	}
	
	public static String formatDate (GregorianCalendar d1){
		int day = d1.get(Calendar.DATE);
		int month = d1.get(Calendar.MONTH)+ 1 ;
		int year = d1.get(Calendar.YEAR);
		return year + "-" + month + "-" + day;
	}
	
	public static GregorianCalendar convertDate(String date){
		Scanner sc = new Scanner (date);
		sc.useDelimiter("/");
		int dd = sc.nextInt();
		int mm = sc.nextInt()+1;
		int yyyy = sc.nextInt();
		
		GregorianCalendar calender = new GregorianCalendar(yyyy,mm,dd);
		return 	calender;
	}
	
	public static void main (String args[]){
	
		GregorianCalendar d1 = new GregorianCalendar(2003, 1, 20);
		GregorianCalendar d2 = new GregorianCalendar(2003, 0, 16);
		System.out.println("First Date = " + MyCalendar.formatDate(d1));
		System.out.println("Second Date = " + MyCalendar.formatDate(d2));
		long days = MyCalendar.getDifference(d1, d2);
		System.out.println("Difference in Days = " + days);

		
	}
}

