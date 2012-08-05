package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JTextField;

import Controller.PRFM.AdministrateEventController;
import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;

import Model.Event;
import Model.PRFM.EventForm;
import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class UpdateFeedbackForm {

	private int code;
	private ArrayList<Integer> fqCodeArr = new ArrayList<Integer>();
	private ArrayList<Integer> eventIDArr = new ArrayList<Integer>();  //  @jve:decl-index=0:
	private String fqCode, eventID;
	private FeedbackForm ff = new FeedbackForm();
	private EventForm ef = new EventForm();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="142,5"
	private JPanel jContentPane = null;
	private JLabel codeLabel = null;
	private JLabel creationDateLabel = null;
	private JTextField codeTextField = null;
	private JTextField creationDateTextField = null;
	private JLabel fqCodeLabel = null;
	private JTextField fqCodeTextField = null;
	private JButton viewFqButton = null;
	private JButton submitButton = null;
	private JButton resetButton = null;
	private JLabel eventIDLabel = null;
	private JTextField eventIDTextField = null;
	private JButton viewEventButton = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Update Feedback Form #" + ff.getCode().get(0).toString());
			jFrame.setSize(new Dimension(500, 250));
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			eventIDLabel = new JLabel();
			eventIDLabel.setBounds(new Rectangle(25, 110, 120, 15));
			eventIDLabel.setText("Event ID: ");
			fqCodeLabel = new JLabel();
			fqCodeLabel.setBounds(new Rectangle(25, 80, 120, 15));
			fqCodeLabel.setText("FQ Code (in order): ");
			creationDateLabel = new JLabel();
			creationDateLabel.setBounds(new Rectangle(25, 50, 120, 15));
			creationDateLabel.setText("Creation Date: ");
			codeLabel = new JLabel();
			codeLabel.setBounds(new Rectangle(25, 20, 120, 15));
			codeLabel.setText("Code: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(codeLabel, null);
			jContentPane.add(creationDateLabel, null);
			jContentPane.add(getCodeTextField(), null);
			jContentPane.add(getCreationDateTextField(), null);
			jContentPane.add(fqCodeLabel, null);
			jContentPane.add(getFqCodeTextField(), null);
			jContentPane.add(getViewFqButton(), null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(eventIDLabel, null);
			jContentPane.add(getEventIDTextField(), null);
			jContentPane.add(getViewEventButton(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes codeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCodeTextField() {
		if (codeTextField == null) {
			codeTextField = new JTextField();
			codeTextField.setBounds(new Rectangle(160, 19, 300, 20));
			codeTextField.setBackground(null);
			codeTextField.setBorder(null);
			codeTextField.setEditable(false);
			codeTextField.setText(ff.getCode().get(0).toString());
		}
		return codeTextField;
	}

	/**
	 * This method initializes creationDateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCreationDateTextField() {
		if (creationDateTextField == null) {
			creationDateTextField = new JTextField();
			creationDateTextField.setBounds(new Rectangle(160, 49, 300, 20));
			creationDateTextField.setBackground(null);
			creationDateTextField.setBorder(null);
			creationDateTextField.setEditable(false);
			creationDateTextField.setText(ff.getCreationDate().get(0).toString());
		}
		return creationDateTextField;
	}

	/**
	 * This method initializes fqCodeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFqCodeTextField() {
		if (fqCodeTextField == null) {
			fqCodeTextField = new JTextField();
			fqCodeTextField.setBounds(new Rectangle(160, 79, 250, 20));
			fqCodeTextField.setText(fqCode);
		}
		return fqCodeTextField;
	}

	/**
	 * This method initializes viewFqButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewFqButton() {
		if (viewFqButton == null) {
			viewFqButton = new JButton();
			viewFqButton.setBounds(new Rectangle(85, 150, 180, 20));
			viewFqButton.setText("View feedback questions");
			viewFqButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			        ArrayList<FeedbackQuestion> fqArr = new ArrayList<FeedbackQuestion>();
			        FeedbackQuestion fq = new FeedbackQuestion();
			        AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
			        
			        for (int x = 0; x < ff.getCode().size(); x++){
			        	fq = controller.processSearchTerm(ff.getFqCode().get(x), null, null, null);
			        	fqArr.add(fq);
			        }
			        
			        int colSize = 4;
					Object[] colNames = {"Code", "Creation Date", "Type", "Question"};
			        Object[][] data = new Object[fqArr.size()][colSize];

			        for (int i = 0; i < fqArr.size(); i++){
		        		data[i][0] = fqArr.get(i).getCode().get(0);
		        		data[i][1] = fqArr.get(i).getCreationDate().get(0);
		        		data[i][2] = fqArr.get(i).getType().get(0);
		        		data[i][3] = fqArr.get(i).getQuestion().get(0);
			        	}
			        
					DefaultTableModel model = new DefaultTableModel(data, colNames);
					RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion(model);
			        retrieve.getJFrame().setVisible(true);
				}
			});
		}
		return viewFqButton;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setBounds(new Rectangle(185, 180, 80, 20));
			submitButton.setText("Submit");
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ( !(getFqCodeTextField().getText().equals(fqCode) && getEventIDTextField().getText().equals(eventID)) ){
						
					boolean valid = true, secondValid = true;							
					ArrayList<Integer> fqCodeArr = new ArrayList<Integer>();
					ArrayList<Integer> eventIDArr = new ArrayList<Integer>();
					
					if (fqCode.equals("")){
						JOptionPane.showMessageDialog(getJFrame(), "Please select feedback questions for this feedback form.");
						valid = false;
					}
					else{
						if (!getFqCodeTextField().getText().equals(fqCode)){
							Scanner sc = new Scanner(getFqCodeTextField().getText());
							sc.useDelimiter(",");
							
							while (sc.hasNext() && valid){
								try{
									fqCodeArr.add(Integer.valueOf(sc.next().replace(" ", "")));
								}
								catch(Exception ex){
									JOptionPane.showMessageDialog(getJFrame(), "Please enter integer value only.");
									valid = false;
								}
							}
							
							if (valid){
								if (removeDuplicate(fqCodeArr)){
									JOptionPane.showMessageDialog(getJFrame(), "Duplicate feedback question code found. Please ensure there're no duplicates.");
									valid = false;
								}
								else if (fqCodeArr.size() < 5){
									JOptionPane.showMessageDialog(getJFrame(), "Number of feedback questions must be equal to or exceed 5.");
									valid = false;
								}
								else if (fqCodeArr.size() > 15){
									JOptionPane.showMessageDialog(getJFrame(), "Number of feedback questions must not exceed 15.");
									valid = false;
								}
								else{
									boolean[] codeArr = new boolean[fqCodeArr.size()];
									FeedbackQuestion fq = new FeedbackQuestion();
									AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
									fq = fqController.processRetrieve();
									
									for (int a = 0; a < fqCodeArr.size(); a++){
										codeArr[a] = false;
										for (int b = 0; b < fq.getCode().size(); b++){
											if (fqCodeArr.get(a) == fq.getCode().get(b)){
												codeArr[a] = true;
												break;
											}
										}
									}
									
									String message = new String();
									
									for (int x = 0; x < codeArr.length; x++){
										if (!codeArr[x]){
											message += ", " + fqCodeArr.get(x);
										}
									}
									
									if (!message.equals("")){
										JOptionPane.showMessageDialog(getJFrame(), "Feedback question code(s) " + message.substring(2) + " does/do not exist. Please enter codes that already exist.");
										valid = false;
									}
								}
							}
						}
					}
					
					if (!eventID.equals("") && !getEventIDTextField().getText().equals(eventID)){
						Scanner eventSc = new Scanner(getEventIDTextField().getText());
						eventSc.useDelimiter(",");
						
						while (eventSc.hasNext() && secondValid){
							try{
								eventIDArr.add(Integer.valueOf(eventSc.next().replace(" ", "")));
							}
							catch(Exception ex){
								JOptionPane.showMessageDialog(getJFrame(), "Please enter integer value only.");
								secondValid = false;
							}
						}
						
						if (secondValid){
							if (removeDuplicate(eventIDArr)){
								JOptionPane.showMessageDialog(getJFrame(), "Duplicate event ID found. Please ensure there're no duplicates.");
								secondValid = false;
							}
							else{
								boolean[] eventValid = new boolean[eventIDArr.size()];
								Event event = new Event();
								AdministrateEventController controller = new AdministrateEventController();
								event = controller.processRetrieve();
								
								for (int a = 0; a < eventIDArr.size(); a++){
									eventValid[a] = false;
									
									for (int b = 0; b < event.getEventID_list().size(); b++){
										if (eventIDArr.get(a) == event.getEventID_list().get(b)){
											eventValid[a] = true;
											break;
										}
									}
								}
								
								String message = new String();
								
								for (int x = 0; x < eventValid.length; x++){
									if (!eventValid[x]){
										message += ", " + eventIDArr.get(x);
									}
								}
								
								if (!message.equals("")){
									JOptionPane.showMessageDialog(getJFrame(), "Event ID(s) " + message.substring(2) + " does/do not exist. Please enter IDs that already exist.");
									secondValid = false;
								}
								else{
									if (!eventIDArr.isEmpty() && !getEventIDTextField().getText().equals(eventID)){
										Event newEvent = new Event();
										newEvent = controller.processEventID(eventIDArr);
										AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
										EventForm newEf = new EventForm();
										newEf = ffController.processRetrieveEventForm();
										
										String exist = new String();
										
										for (int a = 0; a < eventIDArr.size(); a++){
											int first = eventIDArr.get(a);
											
											for (int b = 0; b < newEf.getEventID().size(); b++){
												int second = newEf.getEventID().get(b);
												
												if (first == second && newEf.getCode().get(b) != ff.getCode().get(0)){
													exist += ", " + newEf.getEventID().get(b);
												}
											}
										}
										
										String before = new String();
										String inProcess = new String();
										
										for (int i = 0; i < newEvent.getEventDate_list().size(); i++){
											int beforeAfter = ffController.compareCurrentDate(newEvent.getEventDate_list().get(i));
											if (beforeAfter < 0){
												before += ", " + newEvent.getEventID_list().get(i);
											}
											else if (beforeAfter == 0){
												inProcess += ", " + newEvent.getEventID_list().get(i);
											}
										}
										
										if (!exist.equals("")){
											
											JOptionPane.showMessageDialog(getJFrame(), "Event ID(s) " + exist.substring(2) + " has/have already been assigned with a feedback form. Please enter other event IDs.");
											secondValid = false;
										}				
										if (!before.equals("")){
											JOptionPane.showMessageDialog(getJFrame(), "Event ID(s) " + before.substring(2) + " has/have already been held. Please enter other event IDs.");
											secondValid = false;
										}
										if (!inProcess.equals("")){
											JOptionPane.showMessageDialog(getJFrame(), "Event ID(s) " + inProcess.substring(2) + " is/are being held today. Please enter other event IDs.");
											secondValid = false;
										}
									}
								}
							}
						}
					}
					
					if (valid && secondValid){
						
						int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to update this feedback form?", "Message", JOptionPane.YES_NO_OPTION);	
						
						if (option == JOptionPane.YES_OPTION){
							boolean thirdValid = true, fourthValid = true;
							AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
							
							if (!getFqCodeTextField().getText().equals(fqCode)){
								int x = 1;
								int size;
								
								if (fqCodeArr.size() <= ff.getCode().size()){
									size = fqCodeArr.size();
								}
								else{
									size = ff.getCode().size();
								}
								
								while (thirdValid && x <= size){
									thirdValid = ffController.processUpdate(code, null, fqCodeArr.get(x - 1), x);
									x++;
								}
								
								if (thirdValid){
									if (fqCodeArr.size() < ff.getCode().size()){
										int a = fqCodeArr.size() + 1;
										
										while (fourthValid && a <= ff.getCode().size()){
											fourthValid = ffController.processDelete(code, a);
											a++;
										}
									}
									else if (fqCodeArr.size() > ff.getCode().size()){
										int a = ff.getCode().size() + 1;
										
										while (fourthValid && a <= fqCodeArr.size()){
											fourthValid = ffController.processCreate(code, fqCodeArr.get(a - 1), a);
											a++;
										}
									}
								}
							}
							
								boolean fifthValid = true;
								
								if (!getEventIDTextField().getText().equals(eventID)){
									if (!ef.getEventID().isEmpty()){
										fifthValid = ffController.processDeleteEventForm(code, eventIDArr);
									}
									
									if (fifthValid){
										if (!eventIDArr.isEmpty()){
											ArrayList<Integer> temp = new ArrayList<Integer>();
											temp = eventIDArr;
											
											for (int a = 0; a < ef.getEventID().size(); a++){
												int first = ef.getEventID().get(a);
												
												for (int b = 0; b < temp.size(); b++){
													int second = temp.get(b);
													
													if (first == second){
														temp.remove(b);
														b -= 1;
													}
												}
											}
											
											if (!temp.isEmpty()){
												int i = 0;
												while (fifthValid && i < temp.size()){
													fifthValid = ffController.processCreateEventForm(code, temp.get(i));
													i++;
												}
											}
										}
									}
								}

								
								if (fourthValid && fifthValid){
									JOptionPane.showMessageDialog(getJFrame(), "Successfully updated!");
								}
								else{
									JOptionPane.showMessageDialog(getJFrame(), "Unsuccessful. Please try again.");
								}
									
									
								}

							}
						}
					
					}
			});
		}
		return submitButton;
	}

	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(295, 180, 80, 20));
			resetButton.setText("Reset");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					reset();
				}
			});
		}
		return resetButton;
	}

	public void reset(){
		getFqCodeTextField().setText(fqCode);
		getEventIDTextField().setText(eventID);
	}
	
	@SuppressWarnings("unused")
	private long getDifference (GregorianCalendar d1, GregorianCalendar d2){
		
		long firstDate = d1.getTimeInMillis();
		long secDate = d2.getTimeInMillis();
		
		return (firstDate - secDate)/(24*60*60*1000);
	}
	
	private boolean removeDuplicate(ArrayList<Integer> intArr){
		boolean removed = false;
		
		for (int a = 0; a < intArr.size(); a++){
			int first = intArr.get(a);
			
			for (int b = a + 1; b < intArr.size(); b++){
				int second = intArr.get(b);
				
				if (first == second){
					removed = true;
					intArr.remove(b);
					b -= 1;
				}
			}
		}
		
		return removed;
	}
	
	public void addToFqCode(int code){
		fqCodeArr.clear();
		String fqCode = getFqCodeTextField().getText();
		
		if (!fqCode.equals("")){
			boolean valid = true;
			fqCode = fqCode.replaceAll("[^\\d]", " ");
			fqCode = fqCode.replaceAll("\\s+", " ");
			Scanner sc = new Scanner(fqCode);
			sc.useDelimiter(" ");
			
			while (sc.hasNext() && valid){
				fqCodeArr.add(Integer.valueOf(sc.next()));
			}
		}
		
		fqCodeArr.add(code);
		removeDuplicate(fqCodeArr);
		
		for (int i = 0; i < fqCodeArr.size(); i++){
			if (i == 0){
				fqCode = fqCodeArr.get(i).toString();
			}
			else{
				fqCode += ", " + fqCodeArr.get(i).toString();
			}
		}
		
		getFqCodeTextField().setText(fqCode);
	}
	
	public void addToEventID(int code){	
		eventIDArr.clear();
		String eventID = getEventIDTextField().getText();
		
		if (!eventID.equals("")){
			boolean valid = true;
			eventID = eventID.replaceAll("[^\\d]", " ");
			eventID = eventID.replaceAll("\\s+", " ");
			Scanner eventSc = new Scanner(eventID);
			eventSc.useDelimiter(" ");
			
			while (eventSc.hasNext() && valid){
				eventIDArr.add(Integer.valueOf(eventSc.next()));
			}
		}
		
		eventIDArr.add(code);
		removeDuplicate(eventIDArr);
		
		for (int i = 0; i < eventIDArr.size(); i++){
			if (i == 0){
				eventID = eventIDArr.get(i).toString();
			}
			else{
				eventID += ", " + eventIDArr.get(i).toString();
			}
		}
		
		getEventIDTextField().setText(eventID);
	}

	/**
	 * This method initializes eventIDTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEventIDTextField() {
		if (eventIDTextField == null) {
			eventIDTextField = new JTextField();
			eventIDTextField.setBounds(new Rectangle(160, 109, 250, 20));
			eventIDTextField.setText(eventID);
		}
		return eventIDTextField;
	}

	/**
	 * This method initializes viewEventButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewEventButton() {
		if (viewEventButton == null) {
			viewEventButton = new JButton();
			viewEventButton.setBounds(new Rectangle(295, 150, 120, 20));
			viewEventButton.setText("View Event(s)");
			
			if (getEventIDTextField().getText().equals("NIL")){
				viewEventButton.setEnabled(false);
			}
			
			viewEventButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Event event = new Event();
					AdministrateEventController controller = new AdministrateEventController();
					event = controller.processEventID(ef.getEventID());
					
					int colSize = 5;
			        Object[] colNames = {"Event ID", "Event Date", "Event Time", "Event Name", "Status"};
			        Object[][] data = new Object[event.getEventID_list().size()][colSize];

			        for (int i = 0; i < event.getEventID_list().size(); i++){
		        		data[i][0] = event.getEventID_list().get(i);
		        		data[i][1] = controller.calendarToString(event.getEventDate_list().get(i));
		        		data[i][2] = event.getEventTime_list().get(i);
		        		data[i][3] = event.getEventName_list().get(i);
		        		data[i][4] = event.getEventStatus_list().get(i);
			        }
			        
					DefaultTableModel model = new DefaultTableModel(data, colNames);
					RetrieveEvent retrieve = new RetrieveEvent(model);
			        retrieve.getJFrame().setVisible(true);
				}
			});
		}
		return viewEventButton;
	}

	public static void main(String[] args){
		UpdateFeedbackForm update = new UpdateFeedbackForm();
		update.getJFrame().setVisible(true);
	}
	
	public UpdateFeedbackForm(){
		
	}
	
	public UpdateFeedbackForm(int code){
		this.code = code;
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(code, null, 0);
		ef = ffController.processEventFormSearchTerm(code, 0);
		  
		fqCode = new String();
		eventID = new String();
		
		for (int i = 1; i <= ff.getCode().size(); i++){
			for (int x = 0; x < ff.getCode().size(); x++){
				if (ff.getFqOrder().get(x) == i){
					if (i == 1){
						fqCode = ff.getFqCode().get(x).toString();
					}
					else{
						fqCode += ff.getFqCode().get(x).toString();
					}
					
					if (i != ff.getCode().size()){
						fqCode += ", ";
					}
					
					break;
				}
			}
		}
		
		for (int a = 0; a < ef.getEventID().size(); a++){
			if (a == 0){
				eventID = ef.getEventID().get(a).toString();
			}
			else{
				eventID += ef.getEventID().get(a).toString();
			}
			
			if (a != ef.getEventID().size() - 1){
				eventID += ", ";
			}
		}
	}
}
