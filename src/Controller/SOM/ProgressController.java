package Controller.SOM;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import View.SOM.AdministrateSystemOptionManagement;;


public class ProgressController {
	
	static int min = 0;
	static int max = 100;
	JProgressBar pbar;
	public void updateProgress(int x){
		AdministrateSystemOptionManagement.getJProgressBar().setMinimum(0);
		 for (int i =  0; i <= 100; i+=x) {
		      final int percent = i;
		      try {
		        SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		        	 update(percent);
		        	 //System.out.println(AdministrateSystemOptionManagement.getJProgressBar().getValue());
		        	 System.out.println(percent+"%");
		        	 //pbar.setIndeterminate(true);
		          }
		        });
		        Thread.sleep(100);
		      } catch (InterruptedException e) {
		      }
		    } 
	}
	public void update(int value){
		AdministrateSystemOptionManagement.getJProgressBar().setValue(value);
	}
	public void endProgress(){
		 for (int i =  0; i <= max; i+=10) {
		      final int percent = i;
		      try {
		        SwingUtilities.invokeLater(new Runnable() {
		         public void run() {
		        	 update(percent);
		        	  //pbar.setIndeterminate(true);
		          }
		        });
		        Thread.sleep(100);
		      } catch (InterruptedException e) {
		      }
		    } 
	}
	
}
