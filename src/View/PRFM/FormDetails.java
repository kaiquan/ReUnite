package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import Controller.PRFM.*;
import Model.*;
import Model.PRFM.*;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FormDetails {

	private int code;
	private FeedbackForm ff = new FeedbackForm();  //  @jve:decl-index=0:
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
			jFrame.setTitle("Details of Feedback Form #" + ff.getCode().get(0).toString());
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
			fqCodeTextField.setBounds(new Rectangle(160, 79, 300, 20));
			fqCodeTextField.setBackground(null);
			fqCodeTextField.setBorder(null);
			fqCodeTextField.setEditable(false);
			
			String fqCode = new String();
			
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
	 * This method initializes eventIDTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEventIDTextField() {
		if (eventIDTextField == null) {
			eventIDTextField = new JTextField();
			eventIDTextField.setBounds(new Rectangle(160, 109, 300, 20));
			eventIDTextField.setBackground(null);
			eventIDTextField.setBorder(null);
			eventIDTextField.setEditable(false);
			
			String eventID = "NIL";
			
			for (int i = 0; i < ef.getEventID().size(); i++){
				if (i == 0){
					eventID = ef.getEventID().get(i).toString();
				}
				else{
					eventID += ef.getEventID().get(i).toString();
				}
				
				if (i != ef.getEventID().size() - 1){
					eventID += ", ";
				}
			}
			
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
			viewEventButton.setText("View event(s)");
			
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
			        Object[][] data = new Object[event.getArray_EventID().size()][colSize];

			        for (int i = 0; i < event.getArray_EventID().size(); i++){
		        		data[i][0] = event.getArray_EventID().get(i);
		        		data[i][1] = controller.calendarToString(event.getArray_EventDate().get(i));
		        		data[i][2] = event.getArray_EventTime().get(i);
		        		data[i][3] = event.getArray_EventName().get(i);
		        		data[i][4] = event.getArray_EventStatus().get(i);
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
		FormDetails det = new FormDetails();
		det.getJFrame().setVisible(true);
	}
	
	public FormDetails(){
		
	}
	
	public FormDetails(int code){
		this.code = code;
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(code, null, 0);
		AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
		ef = controller.processEventFormSearchTerm(code, 0);
	}
}
