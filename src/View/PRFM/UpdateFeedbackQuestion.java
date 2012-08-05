package View.PRFM;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;

import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class UpdateFeedbackQuestion {

	@SuppressWarnings("unused")
	private int code;
	private FeedbackQuestion fq = new FeedbackQuestion();  //  @jve:decl-index=0:
	private FeedbackForm ff = new FeedbackForm();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="127,11"
	private JPanel jContentPane = null;
	private JLabel codeLabel = null;
	private JLabel dateLabel = null;
	private JLabel typeLabel = null;
	private JLabel questionLabel = null;
	private JTextField codeTextField = null;
	private JTextField dateTextField = null;
	private JTextField questionTextField = null;
	private JRadioButton yesNoRadioButton = null;
	private JRadioButton ratingRadioButton = null;
	private JRadioButton openEndedRadioButton = null;
	private JRadioButton secretRadioButton = null;
	private ButtonGroup bg = new ButtonGroup();  //  @jve:decl-index=0:
	private JButton submitButton = null;
	private JButton resetButton = null;
	private JLabel formLabel = null;
	private JTextField formTextField = null;
	private JButton viewButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Update Feedback Question #" + fq.getCode().get(0));
			jFrame.setSize(new Dimension(500, 280));
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
			bg.add(getYesNoRadioButton());
			bg.add(getRatingRadioButton());
			bg.add(getOpenEndedRadioButton());
			setType();
			formLabel = new JLabel();
			formLabel.setBounds(new Rectangle(25, 140, 120, 15));
			formLabel.setText("Included in form(s): ");
			questionLabel = new JLabel();
			questionLabel.setBounds(new Rectangle(25, 110, 120, 15));
			questionLabel.setText("Question: ");
			typeLabel = new JLabel();
			typeLabel.setBounds(new Rectangle(25, 80, 120, 15));
			typeLabel.setText("Type: ");
			dateLabel = new JLabel();
			dateLabel.setBounds(new Rectangle(25, 50, 120, 15));
			dateLabel.setText("Creation Date: ");
			codeLabel = new JLabel();
			codeLabel.setBounds(new Rectangle(25, 20, 120, 15));
			codeLabel.setText("Code: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(codeLabel, null);
			jContentPane.add(dateLabel, null);
			jContentPane.add(typeLabel, null);
			jContentPane.add(questionLabel, null);
			jContentPane.add(getCodeTextField(), null);
			jContentPane.add(getDateTextField(), null);
			jContentPane.add(getQuestionTextField(), null);
			jContentPane.add(getYesNoRadioButton(), null);
			jContentPane.add(getRatingRadioButton(), null);
			jContentPane.add(getOpenEndedRadioButton(), null);
			jContentPane.add(getSecretRadioButton(), null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(formLabel, null);
			jContentPane.add(getFormTextField(), null);
			jContentPane.add(getViewButton(), null);
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
			codeTextField.setText(fq.getCode().get(0).toString());
		}
		return codeTextField;
	}

	/**
	 * This method initializes dateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDateTextField() {
		if (dateTextField == null) {
			dateTextField = new JTextField();
			dateTextField.setBounds(new Rectangle(160, 49, 300, 20));
			dateTextField.setBackground(null);
			dateTextField.setBorder(null);
			dateTextField.setEditable(false);
			dateTextField.setText(fq.getCreationDate().get(0).toString());
		}
		return dateTextField;
	}

	/**
	 * This method initializes questionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQuestionTextField() {
		if (questionTextField == null) {
			questionTextField = new JTextField();
			questionTextField.setBounds(new Rectangle(160, 109, 300, 20));
			questionTextField.setText(fq.getQuestion().get(0));
		}
		return questionTextField;
	}

	/**
	 * This method initializes yesNoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getYesNoRadioButton() {
		if (yesNoRadioButton == null) {
			yesNoRadioButton = new JRadioButton();
			yesNoRadioButton.setBounds(new Rectangle(160, 79, 80, 20));
			yesNoRadioButton.setText("Yes/No");
		}
		return yesNoRadioButton;
	}

	/**
	 * This method initializes ratingRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRatingRadioButton() {
		if (ratingRadioButton == null) {
			ratingRadioButton = new JRadioButton();
			ratingRadioButton.setBounds(new Rectangle(260, 79, 80, 20));
			ratingRadioButton.setText("Rating");
		}
		return ratingRadioButton;
	}

	/**
	 * This method initializes openEndedRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getOpenEndedRadioButton() {
		if (openEndedRadioButton == null) {
			openEndedRadioButton = new JRadioButton();
			openEndedRadioButton.setBounds(new Rectangle(360, 79, 100, 20));
			openEndedRadioButton.setText("Open-ended");
		}
		return openEndedRadioButton;
	}

	/**
	 * This method initializes secretRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSecretRadioButton() {
		if (secretRadioButton == null) {
			secretRadioButton = new JRadioButton();
		}
		return secretRadioButton;
	}
	
	private void reset(){
		bg.setSelected(getSecretRadioButton().getModel(), true);
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setBounds(new Rectangle(260, 180, 80, 20));
			submitButton.setText("Submit");
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String question = getQuestionTextField().getText();
					boolean valid = true;
					
					if (question.equals("")){
						JOptionPane.showMessageDialog(getJFrame(), "Please enter a question.");
						valid = false;
					}
					else if (question.length() > 100){
						JOptionPane.showMessageDialog(getJFrame(), "Question must not be more than 100 characters.");
						valid = false;
					}
					else{
						FeedbackQuestion temp = new FeedbackQuestion();
						AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
						temp = fqController.processRetrieve();
						
						for (int i = 0; i < temp.getQuestion().size(); i++){
							if (temp.getQuestion().get(i).equals(question)){
								JOptionPane.showMessageDialog(getJFrame(), "Duplicate question found. Please enter another question.");
								valid = false;
								break;
							}
						}
					}
					
					if (valid){
						String type = null;
						
						if (getYesNoRadioButton().isSelected()){
							type = getYesNoRadioButton().getText();
						}
						if (getRatingRadioButton().isSelected()){
							type = getRatingRadioButton().getText();
						}
						if (getOpenEndedRadioButton().isSelected()){
							type = getOpenEndedRadioButton().getText();
						}
						
						if ( !(question.equals(fq.getQuestion().get(0)) && type.equals(fq.getType().get(0))) ){
							int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to update this feedback question?", "Message", JOptionPane.YES_NO_OPTION);
							
							if (option == JOptionPane.YES_OPTION){
								AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
								if (controller.processUpdate(fq.getCode().get(0), type, question)){
									JOptionPane.showMessageDialog(getJFrame(), "Successfully updated!");
									reset();
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
			resetButton.setBounds(new Rectangle(370, 180, 80, 20));
			resetButton.setText("Reset");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setType();
					getQuestionTextField().setText(fq.getQuestion().get(0));
				}
			});
		}
		return resetButton;
	}

	private void setType(){
		if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
			bg.setSelected(getYesNoRadioButton().getModel(), true);
		}
		else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
			bg.setSelected(getRatingRadioButton().getModel(), true);
		}
		else if (fq.getType().get(0).equals(fq.getTypeOption().get(2))){
			bg.setSelected(getOpenEndedRadioButton().getModel(), true);
		}
	}
	
	/**
	 * This method initializes formTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFormTextField() {
		if (formTextField == null) {
			formTextField = new JTextField();
			formTextField.setBounds(new Rectangle(160, 139, 300, 20));
			formTextField.setBackground(null);
			formTextField.setBorder(null);
			formTextField.setEditable(false);
			
			String formCode = "NIL";
			for (int i = 0; i < ff.getCode().size(); i++){
				if (i == 0){
					formCode = ff.getCode().get(i).toString();
				}
				else{
					formCode += ff.getCode().get(i).toString();
				}
				
				if (i != ff.getCode().size() - 1){
					formCode += ", ";
				}
			}
			
			formTextField.setText(formCode);
		}
		return formTextField;
	}

	/**
	 * This method initializes viewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewButton() {
		if (viewButton == null) {
			viewButton = new JButton();
			viewButton.setBounds(new Rectangle(30, 180, 200, 20));
			viewButton.setText("View feedback form(s)");
			
			if (getFormTextField().getText().equals("NIL")){
				viewButton.setEnabled(false);
			}
			
			viewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			        int colSize = 4;
			        Object[] colNames = {"Code", "Creation Date", "# of Questions", "# of Events"};
			        Object[][] data = new Object[ff.getCode().size()][colSize];

			        for (int i = 0; i < ff.getCode().size(); i++){
		        		data[i][0] = ff.getCode().get(i);
		        		data[i][1] = ff.getCreationDate().get(i);
		        		data[i][2] = controller.processSearchTerm(ff.getCode().get(i), null, 0).getCode().size();
		        		data[i][3] = controller.processEventFormSearchTerm(ff.getCode().get(i), 0).getEventID().size();
			        }

					DefaultTableModel model = new DefaultTableModel(data, colNames);
					RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm(model);
			        retrieve.getJFrame().setVisible(true);
				}
			});
		}
		return viewButton;
	}

	public static void main(String[] args){
		UpdateFeedbackQuestion update = new UpdateFeedbackQuestion();
		update.getJFrame().setVisible(true);
	}
	
	public UpdateFeedbackQuestion(){
		
	}
	
	public UpdateFeedbackQuestion(int code){
		this.code = code;
		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
		fq = fqController.processSearchTerm(code, null, null, null);
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(0, null, fq.getCode().get(0));
	}
}
