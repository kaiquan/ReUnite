package Controller.RIM;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LookAndFeelController {
	public static void setGlobalLookAndFeel()
	{
		try 
		{
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            System.out.println("Setting Graphite Look and Feel");
		}
            catch (UnsupportedLookAndFeelException ex) 
        	{
	            ex.printStackTrace();
	            getWindowsLookAndFeel();
	        } 
        	catch (IllegalAccessException ex) 
        	{
	            ex.printStackTrace();
	            getWindowsLookAndFeel();
	        } 
        	catch (InstantiationException ex) 
        	{
	            ex.printStackTrace();
	            getWindowsLookAndFeel();
	        } 
        	catch (ClassNotFoundException ex) 
        	{
	            ex.printStackTrace();
	            getWindowsLookAndFeel();
			}
        finally
        {
	    	 /* Turn off metal's use of bold fonts */
	        UIManager.put("swing.boldMetal", Boolean.FALSE);	
        }
		
		
	}
	
	public static void getWindowsLookAndFeel()
	{
		try 
    	{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			System.out.println("Setting Windows Look and Feel");
		} 
    	catch (UnsupportedLookAndFeelException ex) 
    	{
            ex.printStackTrace();
        } 
    	catch (IllegalAccessException ex) 
    	{
            ex.printStackTrace();
        } 
    	catch (InstantiationException ex) 
    	{
            ex.printStackTrace();
        } 
    	catch (ClassNotFoundException ex) 
    	{
            ex.printStackTrace();
		}	
	}
}
