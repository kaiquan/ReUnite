package View.SOM;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SystemApp {
	

	public static void main(String args[]) throws IOException{
		 try {
	        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {  
	       // handle exception
	    } 
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	
	   // calls the AdministrateSystemOptionManagement GUI
	    AdministrateServiceOptionManagement GA=new AdministrateServiceOptionManagement();
	    GA.getJFrame().setVisible(true);
	 

	    InitiateEventForm form1= new InitiateEventForm();
	    form1.getJFrame().setVisible(true);
	    

		
	}
}
