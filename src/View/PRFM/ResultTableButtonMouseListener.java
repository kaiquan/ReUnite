package form;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.AdministrateEventController;
import controller.AdministrateFeedbackFormController;
import entity.Event;
import entity.EventForm;
import entity.FeedbackForm;

public class ResultTableButtonMouseListener extends MouseAdapter {
private JTable table;
private int fqCode;

  public ResultTableButtonMouseListener(JTable table, int fqCode) {
    this.table = table;
    this.fqCode = fqCode;
  }  

  public void mouseClicked(MouseEvent e) {
	  int row = table.rowAtPoint(e.getPoint());
	  String result = (String) getData(table, row, 0);
	  
	  ResultDetails det = new ResultDetails(fqCode, result);
	  det.getJFrame().setVisible(true);
  }
  
  public Object getData(JTable table, int row_index, int col_index){
	   return table.getModel().getValueAt(row_index, col_index);
	   }  
  
}
