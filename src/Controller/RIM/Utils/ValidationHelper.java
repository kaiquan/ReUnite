package Controller.RIM.Utils;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationHelper {


    public ValidationHelper(){}

    /*************************### String Validation ###**************************/
    
    /**
     * Checks if the given string has at least the
     * specified minimum length.
     * Strings that are null or contain only blanks have length 0.
     *
     * @param str   the string to check
     * @param min   the minimum length
     * @return {@code true} if the length is greater or equal to the minimum,
     *     {@code false} otherwise
     */
    public static boolean hasMinimumLength(String str, int min) {
        int length = str == null ? 0 : str.trim().length();
        return min <= length;
    }


    /**
     * Checks if the given string is shorter than
     * the specified maximum length.
     * Strings that are null or contain only blanks have length 0.
     * @param str   the string to check
     * @param max   the maximum length
     * @return {@code true} if the length is less than or equal to the minimum,
     *     {@code false} otherwise
     */
    public static boolean hasMaximumLength(String str, int max) {
        int length = str == null ? 0 : str.trim().length();
        return length <= max;
    }


    /**
     * Checks if the length of the given string is in the
     * bounds as specified by the interval [min, max].
     * Strings that are {@code null} or contain only blanks have length 0.
     *
     * @param str   the string to check
     * @param min   the minimum length
     * @param max   the maximum length
     * @return {@code true} if the length is in the interval,
     *     {@code false} otherwise
     * @throws IllegalArgumentException if min > max
     */
    public static boolean hasBoundedLength(String str, int min, int max) {
        int length = str == null ? 0 : str.trim().length();
        return min <= length && length <= max;
    }


    /*************************### Character Validation ###****************************/

    /**
     * Checks if the given string contains only unicode letters.
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.
     * 
     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode letters,
     *     and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if the given string contains only unicode letters
     * and space (' ').
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.

     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode letters
     *     and space, and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isAlphaSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only
     * unicode letters or digits.
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.
     * 
     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode letters
     *     or digits, and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only
     * unicode letters or digits or space (' ').
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.
     *
     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode letters,
     *     digits or space (' '), and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isAlphanumericSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only unicode digits.
     * A decimal point is not a unicode digit and returns {@code false}.
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.
     *
     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode digits,
     *     and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks and answers if the given string contains only unicode digits
     * or space (' '). A decimal point is not a unicode digit and
     * returns {@code false}.
     * {@code null} returns false,
     * an empty string ("") returns {@code true}.
     *
     * @param str   the string to check, may be {@code null}
     * @return {@code true} if the string contains only unicode digits
     *     or space, and is non-{@code null}
     *
     * @since 1.2
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }


    // Date Validations *******************************************************

    /**
     * Determines and answers if the day of the given {@code Date}
     * is in the past.
     *
     * @param date   the date to check
     * @return {@code true} if in the past, {@code false} otherwise
     */
    public static boolean isPastDay(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar today = getRelativeCalendar(0);
        return in.before(today);
    }


    /**
     * Determines and answers if the given {@code Date} is yesterday.
     *
     * @param date   the date to check
     * @return {@code true} if yesterday, {@code false} otherwise
     */
    public static boolean isYesterday(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar yesterday = getRelativeCalendar(-1);
        Calendar today     = getRelativeCalendar( 0);
        return !in.before(yesterday)
             && in.before(today);
    }


    /**
     * Determines and answers if the given {@code Date} is today.
     *
     * @param date   the date to check
     * @return {@code true} if today, {@code false} otherwise
     */
    public static boolean isToday(Date date) {
        GregorianCalendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar today    = getRelativeCalendar( 0);
        Calendar tomorrow = getRelativeCalendar(+1);
        return !in.before(today)
             && in.before(tomorrow);
    }


    /**
     * Determines and answers if the given {@code Date} is tomorrow.
     *
     * @param date   the date to check
     * @return {@code true} if tomorrow, {@code false} otherwise
     */
    public static boolean isTomorrow(Date date) {
        GregorianCalendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar tomorrow = getRelativeCalendar(+1);
        Calendar dayAfter = getRelativeCalendar(+2);
        return !in.before(tomorrow)
             && in.before(dayAfter);
    }


    /**
     * Determines and answers if the day of the given {@code Date}
     * is in the future.
     *
     * @param date   the date to check
     * @return {@code true} if in the future, {@code false} otherwise
     */
    public static boolean isFutureDay(Date date) {
        Calendar in = new GregorianCalendar();
        in.setTime(date);
        Calendar tomorrow = getRelativeCalendar(+1);
        return !in.before(tomorrow);
    }


    /**
     * Computes the day that has the given offset in days to today
     * and returns it as an instance of  {@code Date}.
     *
     * @param offsetDays   the offset in day relative to today
     * @return the {@code Date} that is the begin of the day
     *     with the specified offset
     */
    public static Date getRelativeDate(int offsetDays) {
        return getRelativeCalendar(offsetDays).getTime();
    }


    /**
     * Computes the day that has the given offset in days to today
     * and returns it as an instance of {@code Calendar}.
     *
     * @param offsetDays   the offset in day relative to today
     * @return a {@code Calendar} instance that is the begin of the day
     *     with the specified offset
     */
    public static Calendar getRelativeCalendar(int offsetDays) {
        Calendar today = new GregorianCalendar();
        return getRelativeCalendar(today, offsetDays);
    }


    /**
     * Computes the day that has the given offset in days from the specified
     * <em>from</em> date and returns it as an instance of {@code Calendar}.
     *
     * @param from         the base date as {@code Calendar} instance
     * @param offsetDays   the offset in day relative to today
     * @return a {@code Calendar} instance that is the begin of the day
     *     with the specified offset from the given day
     */
    public static Calendar getRelativeCalendar(Calendar from, int offsetDays) {
        Calendar temp =
            new GregorianCalendar(
                from.get(Calendar.YEAR),
                from.get(Calendar.MONTH),
                from.get(Calendar.DATE),
                0,
                0,
                0);
        temp.add(Calendar.DATE, offsetDays);
        return temp;
    }
    
    public static boolean validateEmail(String hex)
    {
    	Pattern pattern;
  	  	Matcher matcher;
   
  	    String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   
 
  		pattern = Pattern.compile(EMAIL_PATTERN);
  	 
  		matcher = pattern.matcher(hex);
  		  
  		return matcher.matches();

    }

}
