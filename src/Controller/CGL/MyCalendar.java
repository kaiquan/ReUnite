package rcm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class MyCalendar {
	public String currentDate(){
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
	 return dateFormat.format(cal.getTime());
	}
	
	public String currentDateWithTime(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		Calendar cal = Calendar.getInstance();
		
	 return dateFormat.format(cal.getTime());
	}
	

}
