package form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import controller.AdministrateFeedbackFormController;
import controller.AdministrateFeedbackQuestionController;
import entity.FeedbackQuestion;

public class CreateFeedbackQuestion {

	private int deleteCode = 0;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="153,9"
	private JPanel jContentPane = null;
	private JLabel typeLabel = null;
	private JLabel questionLabel = null;
	private JTextField questionTextField = null;
	private JRadioButton yesNoRadioButton = null;
	private JRadioButton ratingRadioButton = null;
	private JRadioButton openEndedRadioButton = null;
	private JButton submitButton = null;
	private ButtonGroup bg = new ButtonGroup();  //  @jve:decl-index=0:
	private JRadioButton secretRadioButton = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			String title = "Create Feedback Question";
			if (deleteCode != 0){
				title += " to replace Feedback Question #" + deleteCode;
			}
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(500, 200));
			jFrame.setTitle(title);
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
			questionLabel = new JLabel();
			questionLabel.setBounds(new Rectangle(25, 60, 80, 15));
			questionLabel.setText("Question: ");
			typeLabel = new JLabel();
			typeLabel.setBounds(new Rectangle(25, 20, 80, 15));
			typeLabel.setText("Type: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(typeLabel, null);
			jContentPane.add(questionLabel, null);
			jContentPane.add(getQuestionTextField(), null);
			jContentPane.add(getYesNoRadioButton(), null);
			jContentPane.add(getRatingRadioButton(), null);
			jContentPane.add(getOpenEndedRadioButton(), null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getSecretRadioButton(), null);
			jContentPane.add(getQuestionTextField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes questionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQuestionTextField() {
		if (questionTextField == null) {
			questionTextField = new JTextField();
			questionTextField.setBounds(new Rectangle(120, 59, 300, 20));
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
			yesNoRadioButton.setBounds(new Rectangle(120, 20, 80, 20));
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
			ratingRadioButton.setBounds(new Rectangle(220, 20, 80, 20));
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
			openEndedRadioButton.setBounds(new Rectangle(320, 20, 100, 20));
			openEndedRadioButton.setText("Open-ended");
		}
		return openEndedRadioButton;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setBounds(new Rectangle(120, 100, 80, 20));
			submitButton.setText("Submit");
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

						AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
						boolean valid = true;
						String question = getQuestionTextField().getText();
						
						if (bg.getSelection() == null || getSecretRadioButton().isSelected()){
							JOptionPane.showMessageDialog(getJFrame(), "Please select a type.");
							valid = false;
						}
						if (question.equals("")){
							JOptionPane.showMessageDialog(getJFrame(), "Please enter a question.");
							valid = false;
						}
						else if (question.length() > 100){
							JOptionPane.showMessageDialog(getJFrame(), "Question must not be more than 100 characters.");
							valid = false;
						}
						else{
							FeedbackQuestion fq = new FeedbackQuestion();
							fq = fqController.processRetrieve();
							
							for (int i = 0; i < fq.getQuestion().size(); i++){
								if (fq.getQuestion().get(i).equals(question)){
									JOptionPane.showMessageDialog(getJFrame(), "Duplicate question found. Please enter another question.");
									valid = false;
									break;
								}
							}
						}
						
						if (valid){
							int option;

							if (deleteCode == 0){
								option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to create this feedback question?", "Message", JOptionPane.YES_NO_OPTION);
							}
							else{
								option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to create this feedback question and delete Feedback Question #" + deleteCode + "?", "Message", JOptionPane.YES_NO_OPTION);	
							}
							
							if (option == JOptionPane.YES_OPTION){
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
								
								boolean secondValid = true;
								
								if (!fqController.processCreate(type, question.replaceAll("'", "\\\\'"))){
									secondValid = false;
								}
								
								if (deleteCode != 0){
									AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
									if (!ffController.processUpdate(deleteCode, fqController.processLastCode())){
										secondValid = false;
									}
									if (!fqController.processDelete(deleteCode)){
										secondValid = false;
									}
								}
								
								if (secondValid){
									String message = "Successfully created";
									if (deleteCode != 0){
										message += " and deleted";
									}
									message += "!";
									
									JOptionPane.showMessageDialog(getJFrame(), message);
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
	
	private void reset(){
		bg.setSelected(getSecretRadioButton().getModel(), true);
		getQuestionTextField().setText("");
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

	public static void main(String[] args){
		CreateFeedbackQuestion create = new CreateFeedbackQuestion();
		create.getJFrame().setVisible(true);
	}
	
	public CreateFeedbackQuestion(){
		
	}
	
	public CreateFeedbackQuestion(int deleteCode){
		this.deleteCode = deleteCode;
	}
}
