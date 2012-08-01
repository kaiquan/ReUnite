package form;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.AdministrateFeedbackFormController;
import entity.FeedbackForm;

public class EventTableButtonMouseListener extends MouseAdapter {
private JTable table;
private String source, selection;
private Object crudFf;

  public EventTableButtonMouseListener(JTable table, String source, Object crudFf) {
    this.table = table;
    this.source = source;
    this.crudFf = crudFf;
  }  
  
  public void setSelection(String selection){
	  this.selection = selection;
  }

  public void mouseClicked(MouseEvent e) {
	  int row = table.rowAtPoint(e.getPoint());
	  int code = (Integer)getData(table, row, 0);
	  
	  if (selection.equals("Add")){
		  if (source.equals("Select Event to create Feedback Form")){
			  ((CreateFeedbackForm) crudFf).addToEventID(code);
		  }
		  
		  if (source.equals("Select Event to update Feedback Form")){
			  ((UpdateFeedbackForm) crudFf).addToEventID(code);
		  }
	  }
	  else{
		  EventDetails det = new EventDetails(code);
		  det.getJFrame().setVisible(true);
	  }
  }
  
  public Object getData(JTable table, int row_index, int col_index){
	   return table.getModel().getValueAt(row_index, col_index);
	   }  
  
}
