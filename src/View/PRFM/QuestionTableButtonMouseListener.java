package View.PRFM;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Controller.PRFM.AdministrateEventController;
import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;
import Controller.PRFM.AdministrateFeedbackResultController;

import Model.Event;
import Model.PRFM.EventForm;
import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;
import Model.PRFM.FeedbackResult;

public class QuestionTableButtonMouseListener extends MouseAdapter {
private JTable table;
private String source = null, selection = null;
private int deleteCode = 0;
private Object crudFf = null;

  public QuestionTableButtonMouseListener(JTable table, String source, int deleteCode, Object crudFf) {
    this.table = table;
    this.source = source;
    this.deleteCode = deleteCode;
    this.crudFf = crudFf;
  }  
  
  public void setSelection(String selection){
	  this.selection = selection;
  }

  public void mouseClicked(MouseEvent e) {
	  int row = table.rowAtPoint(e.getPoint());
	  int code = (Integer)getData(table, row, 0);
	  
	  if (source.equals("Retrieve Feedback Question") || source.equals("Create Feedback Question")){
		  QuestionDetails det = new QuestionDetails(code);
		  det.getJFrame().setVisible(true);
	  }
	  
	  FeedbackForm ff = new FeedbackForm();
	  AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
	  ff = ffController.processSearchTerm(0, null, code);
	  EventForm ef = new EventForm();
	  ArrayList<GregorianCalendar> dateArr = new ArrayList<GregorianCalendar>();
	  ArrayList<GregorianCalendar> temp = new ArrayList<GregorianCalendar>();
	  AdministrateEventController eventController = new AdministrateEventController();
	  
	  for (int i = 0; i < ff.getCode().size(); i++){
		  ef = ffController.processEventFormSearchTerm(ff.getCode().get(i), 0);
		  
		  if (eventController.processEventID(ef.getEventID()) != null){
			  temp = eventController.processEventID(ef.getEventID()).getEventDate_list();
			  
			  for (int x = 0; x < temp.size(); x++){
				  dateArr.add(temp.get(x));
			  }
		  }
	  }
	  
	  if (source.equals("Update Feedback Question")){
		  if (ff.getCode().isEmpty()){
			  UpdateFeedbackQuestion update = new UpdateFeedbackQuestion(code);
			  update.getJFrame().setVisible(true);
		  }
		  else{		
			  int i = 0;
			  boolean valid = true;
			  
			  while (i < dateArr.size() && valid){
				  int option = ffController.compareCurrentDate(dateArr.get(i));
				  
				  if (option < 0){
					JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is included in an already published form and therefore, cannot be updated.");
					valid = false;
				  }
				  else if (option == 0){
					JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is included in an ongoing form and therefore, cannot be updated.");			
					valid = false;
				  }
				  i++;
			  }
			  
			  if (valid){
				Object[] options = {"Update", "Cancel"};
				int choice = JOptionPane.showOptionDialog(null, "Feedback Question #" + code + " is included in a/an unpublished form(s). Update anyway?", "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);	
				
				if (choice == JOptionPane.YES_OPTION){
					  UpdateFeedbackQuestion update = new UpdateFeedbackQuestion(code);
					  update.getJFrame().setVisible(true);
				}
					  
			  }
		  }
	  }
	  
	  if (source.equals("Delete Feedback Question")){
		  if (ff.getCode().isEmpty()){
			  DeleteFeedbackQuestion delete = new DeleteFeedbackQuestion(code);
			  delete.getJFrame().setVisible(true);
		  }
		  else{		
			  int i = 0;
			  boolean valid = true;
			  
			  while (i < dateArr.size() && valid){
				  int option = ffController.compareCurrentDate(dateArr.get(i));
				  
				  if (option < 0){
					JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is included in an already published form and therefore, cannot be deleted.");
					valid = false;
				  }
				  else if (option == 0){
					JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is included in an ongoing form and therefore, cannot be deleted.");			
					valid = false;
				  }
				  i++;
			  }
			  
			  if (valid){
					Object[] options = {"Enter new question before deleting", "Insert replacement question before deleting", "Delete with implicated form(s)", "Cancel"};
					int option = JOptionPane.showOptionDialog(null, "Feedback Question #" + code + " is included in unpublished form(s). Delete anyway?", "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);	
					
					if (option == 0){
						CreateFeedbackQuestion create = new CreateFeedbackQuestion(code);
						create.getJFrame().setVisible(true);
					}
					else if (option == 1){
						RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion("Insert Replacement Feedback Question", code);
						retrieve.getJFrame().setVisible(true);
					}
					else if (option == 2){
						  DeleteFeedbackQuestion delete = new DeleteFeedbackQuestion(code);
						  delete.getJFrame().setVisible(true);
					}
			  }
		  }		  
	  }
	  
	  if (source.equals("Insert Replacement Feedback Question")){
		  ff = ffController.processSearchTerm(ff, deleteCode);
		  
		  if (ff.getCode().isEmpty()){
			  InsertReplacementQuestion insert = new InsertReplacementQuestion(code, deleteCode);
			  insert.getJFrame().setVisible(true);  
		  }
		  else{
			JOptionPane.showMessageDialog(null, "Feedback Question #" + code + " is already included in one or more feedback forms containing the question you want to delete and therefore, cannot be used as a replacement.");			
		  }
	  }
	  
	  if (source.equals("Select Feedback Question to create Feedback Form")){
		  ((CreateFeedbackForm) crudFf).addToFqCode(code);
	  }
	  
	  if (source.equals("Select Feedback Question to update Feedback Form")){
		  ((UpdateFeedbackForm) crudFf).addToFqCode(code);
	  }
	  
	  if (source.equals("Select Feedback Question to View Result")){
		  if (selection.equals("Details")){
			  QuestionDetails det = new QuestionDetails(code);
			  det.getJFrame().setVisible(true);
		  }
		  else if (selection.equals("Result")){
			  FeedbackQuestion fq = new FeedbackQuestion();
			  FeedbackResult fr;
			  AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
			  AdministrateFeedbackResultController frController = new AdministrateFeedbackResultController();
			  
			  fq = fqController.processSearchTerm(code, null, null, null);

			  if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
				  fr = frController.processYesNoResultSearchTerm(0, code, false, false);
			  }
			  else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
				  fr = frController.processRatingResultSearchTerm(0, code, 0);
			  }
			  else{
				  fr = frController.processOpenEndedResultSearchTerm(0, code, null);
			  }
			  
			  if (!fr.getCode().isEmpty()){
				  RetrieveFeedbackResult retrieve = new RetrieveFeedbackResult();
				  retrieve.setFq(code, 0);
				  retrieve.getJFrame().setVisible(true);
			  }
			  else{
				  JOptionPane.showMessageDialog(null, "There are no feedback results for Feedback Question #" + code + " at the moment.");
			  }
		  }
	  }
  }
  
  public Object getData(JTable table, int row_index, int col_index){
	   return table.getModel().getValueAt(row_index, col_index);
	   }  
  
}
