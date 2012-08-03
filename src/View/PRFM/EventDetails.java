package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import Controller.PRFM.*;
import Model.*;
import Model.PRFM.*;

public class EventDetails {

	private int eventID;
	private Event event = new Event();
	private JTextField tf = new JTextField();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="157,7"
	private JPanel jContentPane = null;
	private JLabel eventIDLabel = null;
	private JLabel packageIDLabel = null;
	private JLabel usernameLabel = null;
	private JLabel statusLabel = null;
	private JLabel dateLabel = null;
	private JLabel timeLabel = null;
	private JLabel nameLabel = null;
	private JLabel descLabel = null;
	private JTextField eventIDTextField = null;
	private JTextField packageIDTextField = null;
	private JTextField usernameTextField = null;
	private JTextField statusTextField = null;
	private JTextField dateTextField = null;
	private JTextField timeTextField = null;
	private JTextField nameTextField = null;
	private JTextArea descTextArea = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Details of Event #" + event.getArray_EventID().get(0));
			jFrame.setSize(new Dimension(500, 380));
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
			descLabel = new JLabel();
			descLabel.setBounds(new Rectangle(25, 230, 120, 15));
			descLabel.setText("Event Description: ");
			nameLabel = new JLabel();
			nameLabel.setBounds(new Rectangle(25, 200, 120, 15));
			nameLabel.setText("Event Name: ");
			timeLabel = new JLabel();
			timeLabel.setBounds(new Rectangle(25, 170, 120, 15));
			timeLabel.setText("Time: ");
			dateLabel = new JLabel();
			dateLabel.setBounds(new Rectangle(25, 140, 120, 15));
			dateLabel.setText("Date: ");
			statusLabel = new JLabel();
			statusLabel.setBounds(new Rectangle(25, 110, 120, 15));
			statusLabel.setText("Status: ");
			usernameLabel = new JLabel();
			usernameLabel.setBounds(new Rectangle(25, 80, 120, 15));
			usernameLabel.setText("Username: ");
			packageIDLabel = new JLabel();
			packageIDLabel.setBounds(new Rectangle(25, 50, 120, 15));
			packageIDLabel.setText("Package ID: ");
			eventIDLabel = new JLabel();
			eventIDLabel.setBounds(new Rectangle(25, 20, 120, 15));
			eventIDLabel.setText("Event ID: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(eventIDLabel, null);
			jContentPane.add(packageIDLabel, null);
			jContentPane.add(usernameLabel, null);
			jContentPane.add(statusLabel, null);
			jContentPane.add(dateLabel, null);
			jContentPane.add(timeLabel, null);
			jContentPane.add(nameLabel, null);
			jContentPane.add(descLabel, null);
			jContentPane.add(getEventIDTextField(), null);
			jContentPane.add(getPackageIDTextField(), null);
			jContentPane.add(getUsernameTextField(), null);
			jContentPane.add(getStatusTextField(), null);
			jContentPane.add(getDateTextField(), null);
			jContentPane.add(getTimeTextField(), null);
			jContentPane.add(getNameTextField(), null);
			jContentPane.add(getDescTextArea(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes eventIDTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEventIDTextField() {
		if (eventIDTextField == null) {
			eventIDTextField = new JTextField();
			eventIDTextField.setBounds(new Rectangle(160, 19, 300, 20));
			eventIDTextField.setBackground(null);
			eventIDTextField.setBorder(null);
			eventIDTextField.setEditable(false);
			eventIDTextField.setText(event.getArray_EventID().get(0).toString());
		}
		return eventIDTextField;
	}

	/**
	 * This method initializes packageIDTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPackageIDTextField() {
		if (packageIDTextField == null) {
			packageIDTextField = new JTextField();
			packageIDTextField.setBounds(new Rectangle(160, 49, 300, 20));
			packageIDTextField.setBackground(null);
			packageIDTextField.setBorder(null);
			packageIDTextField.setEditable(false);
			packageIDTextField.setText(event.getArray_PackageID().get(0).toString());
		}
		return packageIDTextField;
	}

	/**
	 * This method initializes usernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setBounds(new Rectangle(160, 79, 300, 20));
			usernameTextField.setBackground(null);
			usernameTextField.setBorder(null);
			usernameTextField.setEditable(false);
			usernameTextField.setText(event.getArray_UserName().get(0));
		}
		return usernameTextField;
	}

	/**
	 * This method initializes statusTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStatusTextField() {
		if (statusTextField == null) {
			statusTextField = new JTextField();
			statusTextField.setBounds(new Rectangle(160, 109, 300, 20));
			statusTextField.setBackground(null);
			statusTextField.setBorder(null);
			statusTextField.setEditable(false);
			statusTextField.setText(event.getArray_EventStatus().get(0));
		}
		return statusTextField;
	}

	/**
	 * This method initializes dateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDateTextField() {
		if (dateTextField == null) {
			dateTextField = new JTextField();
			dateTextField.setBounds(new Rectangle(160, 139, 300, 20));
			dateTextField.setBackground(null);
			dateTextField.setBorder(null);
			dateTextField.setEditable(false);
			AdministrateEventController controller = new AdministrateEventController();
			dateTextField.setText(controller.calendarToString(event.getArray_EventDate().get(0)));
		}
		return dateTextField;
	}

	/**
	 * This method initializes timeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTimeTextField() {
		if (timeTextField == null) {
			timeTextField = new JTextField();
			timeTextField.setBounds(new Rectangle(160, 169, 300, 20));
			timeTextField.setBackground(null);
			timeTextField.setBorder(null);
			timeTextField.setEditable(false);
			timeTextField.setText(event.getArray_EventTime().get(0));
		}
		return timeTextField;
	}

	/**
	 * This method initializes nameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameTextField() {
		if (nameTextField == null) {
			nameTextField = new JTextField();
			nameTextField.setBounds(new Rectangle(160, 199, 300, 20));
			nameTextField.setBackground(null);
			nameTextField.setBorder(null);
			nameTextField.setEditable(false);
			nameTextField.setText(event.getArray_EventName().get(0));
		}
		return nameTextField;
	}

	/**
	 * This method initializes descTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getDescTextArea() {
		if (descTextArea == null) {
			descTextArea = new JTextArea();
			descTextArea.setBounds(new Rectangle(25, 260, 435, 50));
			descTextArea.setLineWrap(true);
			descTextArea.setWrapStyleWord(true);
			descTextArea.setEditable(false);
			descTextArea.setBorder(tf.getBorder());
			descTextArea.setText(event.getArray_EventDescription().get(0));
		}
		return descTextArea;
	}

	public static void main(String[] args){
		EventDetails det = new EventDetails();
		det.getJContentPane().setVisible(true);
	}
	
	public EventDetails(){
		
	}
	
	public EventDetails(int eventID){
		this.eventID = eventID;
		AdministrateEventController controller = new AdministrateEventController();
		event = controller.processSearchTerm(eventID, null, null, null, null);
	}
}
