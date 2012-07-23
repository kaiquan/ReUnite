package Controller.RIM.Utils;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: DateHelper
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose: A utility class with helper methods for Date related tasks
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
import hirondelle.date4j.DateTime;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public final class DateHelper {

	/**
     * Gets the current date.
     * @return  The current date in java.util.Date type
     */
    public Date getCurrentDate()
    {
        return( Calendar.getInstance().getTime() );
    }
    
	/**
     * Gets the current date.
     * @return  The current date in hirondelle.date4j.DateTime type
     */
	public DateTime getCurrentDateTime()
	{
		DateTime now = DateTime.now(TimeZone.getDefault());
		return now;
	}
    
	/**
     * Gets the current date.
     * @return  The current date in String type
     */
	public String getCurrentDateString()
	{
		DateTime now = DateTime.now(TimeZone.getDefault());
		String result = now.format("YYYY-MM-DD hh:mm:ss");
		return result;
	}
	
    /**
     * Gets the current date in long number format.
     * @return  The current date in long number format.
     */
    public long getCurrentDateLong()
    {
        return( Calendar.getInstance().getTimeInMillis() );
    }


    /**
     * Gets the current year, e.g. 2004.
     * @return  The current year.
     */
    public int getCurrentYear()
    {
        return( Calendar.getInstance().get(Calendar.YEAR) );
    }


    /**
     * Gets the current month, JANUARY = 1,......,DECEMBER = 12.
     * @return  The current month.
     */
    public int getCurrentMonth()
    {
        return( Calendar.getInstance().get(Calendar.MONTH) + 1 );
    }


    /**
     * Gets the current day in the month (1...31).
     * @return  The current day in a month.
     */
    public int getCurrentDay()
    {
        return( Calendar.getInstance().get(Calendar.DAY_OF_MONTH) );
    }


    /**
     * Gets the current hour in 24 hours format.
     * @return  The current hour in 24 hours format.
     */
    public int getCurrentHour()
    {
        return( Calendar.getInstance().get(Calendar.HOUR_OF_DAY) );
    }


    /**
     * Gets the current minutes in hour.
     * @return  The current minutes in hour.
     */
    public int getCurrentMinutes()
    {
        return( Calendar.getInstance().get(Calendar.MINUTE) );
    }


    /**
     * Gets the current seconds in hour.
     * @return  The current seconds in hour.
     */
    public int getCurrentSeconds()
    {
        return( Calendar.getInstance().get(Calendar.SECOND) );
    }
    

    //Calculation methods
    

	/** What's the age of someone born May 16, 1995? */
	public int ageIfBornOnDate(DateTime date)
	{
		DateTime today = DateTime.today(TimeZone.getDefault());
		DateTime birthdate = date;
		int age = today.getYear() - birthdate.getYear();
		if (today.getDayOfYear() < birthdate.getDayOfYear())
		{
			age = age - 1;
		}
		return age;
	}

	/** How many days till the next December 25? */
	public int daysTillDate(DateTime date)
	{
		DateTime today = DateTime.today(TimeZone.getDefault());
		DateTime toDate = date;
		int result = 0;
		if (today.isSameDayAs(toDate))
		{
			// do nothing
		}
		else if (today.lt(toDate))
		{
			result = today.numDaysFrom(toDate);
		}
		else if (today.gt(toDate))
		{
			DateTime christmasNextYear = DateTime.forDateOnly(today.getYear() + 1, 12, 25);
			result = today.numDaysFrom(christmasNextYear);
		}
		return result;
	}

	/** What day is 90 days from today? */
	public DateTime whenIsDaysFromToday(int days)
	{
		DateTime today = DateTime.today(TimeZone.getDefault());

		today.plusDays(days);

		return today;
	}

	public String addDaysToDate(int days, DateTime date)
	{
		return date.plusDays(days).format("YYYY-MM-DD hh:mm:ss");
	}
	
	public Date addDaysToDate(int days, Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);  // number of days to add
		date = c.getTime();
		return date;
	}


	/** How many weeks is it since Sep 6, 2010? */
	public int weeksSinceDate(DateTime date)
	{
		DateTime today = DateTime.today(TimeZone.getDefault());
		DateTime startOfProject = date;
		int result = today.getWeekIndex() - startOfProject.getWeekIndex();
		return result;
	}

	/** How many weeks is it since Sep 6, 2010? */
	public int daysSinceDate(DateTime date)
	{
		DateTime today = DateTime.today(TimeZone.getDefault());
		DateTime startDate = date;
		int result = (today.getWeekIndex() - startDate.getWeekIndex()) * 7;
		return result;
	}

	/** How much time till midnight? */
	public long timeTillMidnight()
	{
		DateTime now = DateTime.now(TimeZone.getDefault());
		DateTime midnight = now.plusDays(1).getStartOfDay();
		long result = now.numSecondsFrom(midnight);
		return result;
	}
	
    /**
     * Subtracts 'days' from a calendar taking into consideration
     * the month, year and leap years change as well.
     * @param cal Calendar type
     * @param days The number of days in int.
     */
    public void subtractDaysFromDate(Calendar cal, int days)
    {
        if( cal == null )
            return;

        while( days > 0 )
        {
            int day_of_year = cal.get(Calendar.DAY_OF_YEAR);
            if( day_of_year==1 )
            {
                cal.roll(Calendar.YEAR, false);
            }

            cal.roll(Calendar.DAY_OF_YEAR, false);
            days--;
        }
    }


    /**
     * Adds 'days' to a calendar taking into consideration
     * the month, year and leap years change as well.
     * <p>
     * @param cal   The <code>Calendar</code>.
     * @param days  The number of days.
     */
    public void addDaysToDate(Calendar cal, int days)
    {
        if( cal == null )
            return;

        while( days > 0 )
        {
            int day_of_year = cal.get(Calendar.DAY_OF_YEAR);

            int days_in_year = 365;
            if( isLeapYear(cal.get(Calendar.YEAR)) )
            {
                days_in_year = 366;
            }

            if( day_of_year == days_in_year )
            {
                cal.roll(Calendar.YEAR, true);
            }

            cal.roll(Calendar.DAY_OF_YEAR, true);
            days--;
        }
    }


    /**
     * Leap years occur in years exactly divisible by four,
     * <i>except</i> those years ending in 00 are leap years
     * only if they are divisible by 400.
     * <p>
     * @param year  The year number.
     * <p>
     * @return      <code>true</code> if it is a leap year.
     */
    public boolean isLeapYear(int year)
    {
        boolean isLeapYear = false;

        if( year > 0 && (year % 4) == 0 )
        {
            isLeapYear = true;

            if( (year % 1000) == 0 ) // year is '00
            {
                if( (year % 400) != 0 )
                {
                    isLeapYear = false;
                }
            }
        }

        return( isLeapYear );
    }


    /**
     * Parses a string into a date. String should be in
     * <code>SimpleDateFormat</code> format.
     * e.g. <code>java.util.Date d = parseDate(myDate, "dd/MM/yyyy");</code>
     * <p>
     * @param myDate    The date string.
     * @param pattern   The pattern to use.
     * <p>
     * @return          The <code>Date</code>.
     * <p>
     * @throws ParseException
     */
    public Date parseDate(String myDate, String pattern)
    {
    	java.util.Date uDate = null;
    	
    	try{
	        if( myDate != null && pattern != null )
	        {
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( pattern );
		        uDate = simpleDateFormat.parse( myDate );
	        }
    	}
    	catch(ParseException e)
    	{
    		e.printStackTrace();
    	}

        return( uDate );
    }


    /**
     * Converts a date to a string based on a
     * <code>SimpleDateFormat</code> pattern.
     * e.g. <code>String s = dateToString(uDate, "dd/MM/yyyy");</code>
     * <p>
     * @param uDate     The date string.
     * @param pattern   The pattern to use.
     * <p>
     * @return          The string of the date or <code>null</code> on error.
     */
    public String dateToString(Date uDate, String pattern)
    {
        if( uDate==null || pattern==null )
          return( null );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( pattern );
        String myDate = simpleDateFormat.format( uDate );

        return( myDate );
    }


    /**
     * Checks a string to see if it contains a valid date in
     * <code>SimpleDateFormat</code>.
     * <p>
     * @param dateToCheck   The date string to check.
     * @param pattern       The pattern to use.
     * <p>
     * @return              <code>true</code> if it contains a valid date in
     *                      <code>SimpleDateFormat</code>.
     */
    public boolean isDateValid(String dateToCheck, String pattern)
    {
        if( dateToCheck==null || pattern==null )
          return( false );

        boolean result = false;
        try
        {
            java.util.Date in = parseDate(dateToCheck, pattern);
            String out = dateToString(in, pattern);
            if( dateToCheck.compareTo(out) == 0 )
            {
                result = true;
            }
        }
        catch(Exception e)
        {
            // result is already false
        }

        return( result );
    }


    /**
     * Parses a string into a date. String should be in
     * <code>SimpleDateFormat</code> format. Returns only the year of the date
     * or -1 on error.
     * <p>
     * <b>NOTE:</b> only 'yyyy' is supported!
     * <p>
     * @param dateToCheck   The date string to check.
     * @param pattern the   The pattern to use.
     * <p>
     * @return              The date or -1 on error.
     */
    public int getYearForDate(String dateToCheck, String pattern)
    {
        if( isDateValid(dateToCheck, pattern) &&
            dateToCheck.length()==pattern.length() )
        {
            int index = pattern.indexOf("yyyy"); // only "yyyy" is supported
            if( index==-1 )
            {
                return( -1 );
            }

            String year_str = dateToCheck.substring(index, index+4);
            int year = -1;
            try
            {
                Integer i = new Integer( year_str );
                year = i.intValue();
            }
            catch(Exception e)
            {
                // year is already -1
            }

            return( year );
        }
        else
        {
            return( -1 );
        }
    }


    /**
     * Parses a string into a date. String should be in
     * <code>SimpleDateFormat</code> format. Returns only the month of the date
     * or -1 on error.
     * <p>
     * <b>NOTE:</b> only 'MM' is supported!
     * <p>
     * @param dateToCheck   The date string to check.
     * @param pattern       The pattern to use.
     * <p>
     * @return              The date or -1 on error.
     */
    public int getMonthForDate(String dateToCheck, String pattern)
    {
        if( isDateValid(dateToCheck, pattern) &&
            dateToCheck.length()==pattern.length() )
        {
            int index = pattern.indexOf("MM"); // only "MM" is supported
            if( index==-1 )
            {
                return( -1 );
            }

            String month_str = dateToCheck.substring(index, index+2);
            int month = -1;
            try
            {
                Integer i = new Integer( month_str );
                month = i.intValue();

                // month starts from 0...11
                month--;
            }
            catch(Exception e)
            {
                // month is already -1
            }

            return( month );
        }
        else
        {
            return( -1 );
        }
    }


    /**
     * Parses a string into a date. String should be in
     * <code>SimpleDateFormat</code> format. Returns only the day of the date
     * or -1 on error.
     * <p>
     * <b>NOTE:</b> only 'dd' is supported!
     * <p>
     * @param dateToCheck   The date string to check.
     * @param pattern       The pattern to use.
     * <p>
     * @return              The day for the date or -1 on error.
     */
    public int getDayForDate(String dateToCheck, String pattern)
    {
        if( isDateValid(dateToCheck, pattern) &&
            dateToCheck.length()==pattern.length() )
        {
            int index = pattern.indexOf("dd"); // only "dd" is supported
            if( index==-1 )
            {
                return( -1 );
            }

            String day_str = dateToCheck.substring(index, index+2);
            int day = -1;
            try
            {
                Integer i = new Integer( day_str );
                day = i.intValue();
            }
            catch(Exception e)
            {
                // day is already -1
            }

            return( day );
        }
        else
        {
            return( -1 );
        }
    }


    /**
     * Gets the name of a day based on a date and current locale.
     * <p>
     * @param dt        The date.
     * @param fullname  Fetch complete day's name or the short one.
     * <p>
     * @return          A string with the name of the day.
     */
    public String getDayNameForDate(java.util.Date dt, boolean fullname)
    {
        // For formatting, if the number of pattern letters is 4 or more,
        // the full form is used; otherwise a short or abbreviated form is used
        // if available. (extracted from Sun's Javadoc)
        final String fullFormat = "EEEE";
        final String smallFormat = "EEE";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                fullname ? fullFormat : smallFormat );

        String dayName = simpleDateFormat.format( dt );

        return( dayName );
    }

}
