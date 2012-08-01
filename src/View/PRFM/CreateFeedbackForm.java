package form;

import javax.swing.JFrame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AdministrateEventController;
import controller.AdministrateFeedbackFormController;
import controller.AdministrateFeedbackQuestionController;
import entity.Event;
import entity.EventForm;
import entity.FeedbackForm;
import entity.FeedbackQuestion;

public class CreateFeedbackForm {

	private ArrayList<Integer> fqCodeArr = new ArrayList<Integer>();
	private ArrayList<Integer> eventIDArr = new ArrayList<Integer>();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="151,10"
	private JPanel jContentPane = null;
	private JLabel eventLabel = null;
	private JLabel fqCodeLabel = null;
	private JButton submitButton = null;
	private JTextField eventIDTextField = null;
	private JTextField fqCodeTextField = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(500, 200));
			jFrame.setTitle("Create Feedback Form");
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
			fqCodeLabel = new JLabel();
			fqCodeLabel.setBounds(new Rectangle(25, 20, 120, 15));
			fqCodeLabel.setText("FQ Code (in order): ");
			eventLabel = new JLabel();
			eventLabel.setBounds(new Rectangle(25, 50, 120, 15));
			eventLabel.setText("Event Code: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(eventLabel, null);
			jContentPane.add(fqCodeLabel, null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getEventIDTextField(), null);
			jContentPane.add(getFqCodeTextField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setText("Submit");
			submitButton.setBounds(new Rectangle(160, 90, 80, 20));
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean valid = true, secondValid = true;
					String eventID, fqCode;
					eventID = getEventIDTextField().getText();
					ArrayList<Integer> eventIDArr = new ArrayList<Integer>();
					
					fqCode = getFqCodeTextField().getText();
					ArrayList<Integer> fqCodeArr = new ArrayList<Integer>();
						
					if (fqCode.equals("")){
						JOptionPane.showMessageDialog(getJFrame(), "Please select feedback questions for this feedback form.");
						valid = false;
					}
					else{
						Scanner sc = new Scanner(fqCode);
						sc.useDelimiter(",");
						
						while (sc.hasNext()){
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
								boolean[] fqValid = new boolean[fqCodeArr.size()];
								FeedbackQuestion fq = new FeedbackQuestion();
								AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
								fq = fqController.processRetrieve();
								
								for (int a = 0; a < fqCodeArr.size(); a++){
									fqValid[a] = false;
									innerloop:
									for (int b = 0; b < fq.getCode().size(); b++){
										if (fqCodeArr.get(a) == fq.getCode().get(b)){
											fqValid[a] = true;
											break innerloop;
										}
									}
								}
								
								String message = new String();
								
								for (int x = 0; x < fqValid.length; x++){
									if (!fqValid[x]){
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
					
					
					if (!eventID.equals("")){
						Scanner eventSc = new Scanner(eventID);
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
									
									for (int b = 0; b < event.getEventID().size(); b++){
										if (eventIDArr.get(a) == event.getEventID().get(b)){
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
									if (!eventID.equals("")){
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
												
												if (first == second){
													exist += ", " + newEf.getEventID().get(b);
												}
											}
										}
										
										String before = new String();
										String inProcess = new String();
										
										for (int i = 0; i < newEvent.getEventDate().size(); i++){
											int beforeAfter = ffController.compareCurrentDate(newEvent.getEventDate().get(i));
											if (beforeAfter < 0){
												before += ", " + newEvent.getEventID().get(i);
											}
											else if (beforeAfter == 0){
												inProcess += ", " + newEvent.getEventID().get(i);
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
						int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to create this feedback form?", "Message", JOptionPane.YES_NO_OPTION);	
						
						if (option == JOptionPane.YES_OPTION){
							boolean thirdValid = true;
							AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
							int code = ffController.generateCode();
							int x = 1;
							
							while (thirdValid && x <= fqCodeArr.size()){
								thirdValid = ffController.processCreate(code, fqCodeArr.get(x - 1), x);
								x++;
							}
							
							if (!eventID.equals("")){
								int i = 0;
								
								while (thirdValid && i < eventIDArr.size()){
									thirdValid = ffController.processCreateEventForm(code, eventIDArr.get(i));
									i++;
								}
							}
							
							if (thirdValid){
								JOptionPane.showMessageDialog(getJFrame(), "Successfully created!");
								reset();
							}
							else{
								JOptionPane.showMessageDialog(getJFrame(), "Unsuccessful. Please try again.");
							}
						}
					}
				}
			});
		}
		return submitButton;
	}

	/**
	 * This method initializes eventIDTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEventIDTextField() {
		if (eventIDTextField == null) {
			eventIDTextField = new JTextField();
			eventIDTextField.setBounds(new Rectangle(160, 49, 250, 20));
		}
		return eventIDTextField;
	}

	/**
	 * This method initializes fqCodeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFqCodeTextField() {
		if (fqCodeTextField == null) {
			fqCodeTextField = new JTextField();
			fqCodeTextField.setBounds(new Rectangle(160, 19, 250, 20));
		}
		return fqCodeTextField;
	}
	
	private long getDifference (GregorianCalendar d1, GregorianCalendar d2){
					
	long firstDate = d1.getTimeInMillis();
	long secDate = d2.getTimeInMillis();
	
	return (firstDate - secDate)/(24*60*60*1000);
}
	
	private void reset(){
		fqCodeArr = new ArrayList<Integer>();
		getEventIDTextField().setText("");
		getFqCodeTextField().setText("");
		
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
	
	public static void main(String[] args){
		CreateFeedbackForm create = new CreateFeedbackForm();
		create.getJFrame().setVisible(true);
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
}
