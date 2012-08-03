package View.PRFM;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Controller.PRFM.*;
import Model.*;
import Model.PRFM.*;

public class FormTableButtonMouseListener extends MouseAdapter {
private JTable table;
private String source, selection = null;

  public FormTableButtonMouseListener(JTable table, String source) {
    this.table = table;
    this.source = source;
  }  
  
  public void setSelection(String selection){
	  this.selection = selection;
  }

  public void mouseClicked(MouseEvent e) {
	  int row = table.rowAtPoint(e.getPoint());
	  int code = (Integer)getData(table, row, 0);
	  
	  if (source.equals("Retrieve Feedback Form") || source.equals("Create Feedback Form")){
		  FormDetails det = new FormDetails(code);
		  det.getJFrame().setVisible(true);
	  }
	  
	  FeedbackForm ff = new FeedbackForm();
	  AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
	  ff = ffController.processSearchTerm(code, null, 0);
	  EventForm ef = new EventForm();
	  ef = ffController.processEventFormSearchTerm(code, 0);
	  Event event = new Event();
	  AdministrateEventController eventController = new AdministrateEventController();
	  event = eventController.processEventID(ef.getEventID());
	  
	  if (source.equals("Update Feedback Form")){
		  boolean valid = true;
		  int i = 0;
		  
		  if (event != null){
			  while (valid && i < event.getArray_EventID().size()){
				  int option = ffController.compareCurrentDate(event.getArray_EventDate().get(i));
	
				  if (option < 0){
					JOptionPane.showMessageDialog(null, "Feedback Form #" + code + " has already been published and therefore, cannot be updated.");
					valid = false;
				  }
				  else if (option == 0){
					JOptionPane.showMessageDialog(null, "Feedback Form #" + code + " is being published at the moment and therefore, cannot be updated.");			
					valid = false;
				  }		  
				  i++;
		  	 }
	     }
		  
		if (valid){
			UpdateFeedbackForm updateFf = new UpdateFeedbackForm(code);
			updateFf.getJFrame().setVisible(true);
			RetrieveFeedbackQuestion retrieveFq = new RetrieveFeedbackQuestion("Select Feedback Question to update Feedback Form", updateFf);
			retrieveFq.getJFrame().setVisible(true);
			RetrieveEvent retrieveEvent = new RetrieveEvent("Select Event to update Feedback Form", updateFf);
			retrieveEvent.getJFrame().setVisible(true);
		}

	  }
	  
	  if (source.equals("Delete Feedback Form")){
		  boolean valid = true;
		  int i = 0;
		  
		  if (event != null){
			  while (valid && i < event.getArray_EventDate().size()){
				  int option = ffController.compareCurrentDate(event.getArray_EventDate().get(i));
				  
				  if (option < 0){
					JOptionPane.showMessageDialog(null, "Feedback Form #" + code + " has already been published and therefore, cannot be deleted.");
					valid = false;
				  }
				  else if (option == 0){
					JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is being published at the moment and therefore, cannot be deleted.");			
					valid = false;
				  }
				  
				  i++;
			  }
	  	  }
			
		if (valid){
			DeleteFeedbackForm delete = new DeleteFeedbackForm(code);
			delete.getJFrame().setVisible(true);
		}
	  }
	  
	  if (source.equals("Select Feedback Form to View Result")){
		  if (selection.equals("Details")){
			  FormDetails det = new FormDetails(code);
			  det.getJFrame().setVisible(true);
		  }
		  else if (selection.equals("Result")){
			  AdministrateFeedbackResultController frController = new AdministrateFeedbackResultController();
			  FeedbackResult fr = frController.processSearchTerm(0, null, code);
			  
			  if (!fr.getCode().isEmpty()){
				  RetrieveFeedbackResult retrieve = new RetrieveFeedbackResult();
				  retrieve.setFf(code);
				  retrieve.getJFrame().setVisible(true);
			  }
			  else{
				  JOptionPane.showMessageDialog(null, "There are no feedback results for Feedback Form #" + code + " at the moment.");
			  }
		  }
	  }
	  
  }
  
  public Object getData(JTable table, int row_index, int col_index){
	   return table.getModel().getValueAt(row_index, col_index);
	   }  
  
}
