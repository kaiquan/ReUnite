package form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;

import controller.AdministrateFeedbackQuestionController;

import entity.RatingQuestion;
import javax.swing.JButton;

public class UpdateRatingQuestion {

	private RatingQuestion rq = new RatingQuestion();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="161,8"
	private JPanel jContentPane = null;
	private JLabel noteLabel = null;
	private JLabel scaleLabel = null;
	private JLabel optionLabel = null;
	private JTextField firstScaleTextField = null;
	private JTextField secondScaleTextField = null;
	private JTextField thirdScaleTextField = null;
	private JTextField fourthScaleTextField = null;
	private JTextField firstOptionTextField = null;
	private JTextField secondOptionTextField = null;
	private JTextField thirdOptionTextField = null;
	private JTextField fourthOptionTextField = null;
	private JButton submitButton = null;
	private JButton resetButton = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Update Rating Options");
			jFrame.setSize(new Dimension(450, 300));
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
			optionLabel = new JLabel();
			optionLabel.setBounds(new Rectangle(150, 50, 60, 15));
			optionLabel.setText("Option");
			scaleLabel = new JLabel();
			scaleLabel.setBounds(new Rectangle(25, 50, 50, 15));
			scaleLabel.setText("Scale");
			noteLabel = new JLabel();
			noteLabel.setBounds(new Rectangle(25, 20, 400, 15));
			noteLabel.setText("Note: Least satisfactory to most satisfactory on a scale of " + rq.getRating().get(0) + "-" + rq.getRating().get(rq.getRating().size() - 1) + ".");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(noteLabel, null);
			jContentPane.add(scaleLabel, null);
			jContentPane.add(optionLabel, null);
			jContentPane.add(getFirstScaleTextField(), null);
			jContentPane.add(getSecondScaleTextField(), null);
			jContentPane.add(getThirdScaleTextField(), null);
			jContentPane.add(getFourthScaleTextField(), null);
			jContentPane.add(getFirstOptionTextField(), null);
			jContentPane.add(getSecondOptionTextField(), null);
			jContentPane.add(getThirdOptionTextField(), null);
			jContentPane.add(getFourthOptionTextField(), null);
			jContentPane.add(getSubmitButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes firstScaleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFirstScaleTextField() {
		if (firstScaleTextField == null) {
			firstScaleTextField = new JTextField();
			firstScaleTextField.setBounds(new Rectangle(25, 80, 50, 20));
			firstScaleTextField.setBackground(null);
			firstScaleTextField.setBorder(null);
			firstScaleTextField.setEditable(false);
			firstScaleTextField.setText(rq.getRating().get(0).toString());
		}
		return firstScaleTextField;
	}

	/**
	 * This method initializes secondScaleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSecondScaleTextField() {
		if (secondScaleTextField == null) {
			secondScaleTextField = new JTextField();
			secondScaleTextField.setBounds(new Rectangle(25, 110, 50, 20));
			secondScaleTextField.setBackground(null);
			secondScaleTextField.setBorder(null);
			secondScaleTextField.setEditable(false);
			secondScaleTextField.setText(rq.getRating().get(1).toString());
		}
		return secondScaleTextField;
	}

	/**
	 * This method initializes thirdScaleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThirdScaleTextField() {
		if (thirdScaleTextField == null) {
			thirdScaleTextField = new JTextField();
			thirdScaleTextField.setBounds(new Rectangle(25, 140, 50, 20));
			thirdScaleTextField.setBackground(null);
			thirdScaleTextField.setBorder(null);
			thirdScaleTextField.setEditable(false);
			thirdScaleTextField.setText(rq.getRating().get(2).toString());
		}
		return thirdScaleTextField;
	}

	/**
	 * This method initializes fourthScaleTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFourthScaleTextField() {
		if (fourthScaleTextField == null) {
			fourthScaleTextField = new JTextField();
			fourthScaleTextField.setBounds(new Rectangle(25, 170, 50, 20));
			fourthScaleTextField.setBackground(null);
			fourthScaleTextField.setBorder(null);
			fourthScaleTextField.setEditable(false);
			fourthScaleTextField.setText(rq.getRating().get(3).toString());
		}
		return fourthScaleTextField;
	}

	/**
	 * This method initializes firstOptionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFirstOptionTextField() {
		if (firstOptionTextField == null) {
			firstOptionTextField = new JTextField();
			firstOptionTextField.setBounds(new Rectangle(150, 80, 200, 20));
			firstOptionTextField.setText(rq.getChoice().get(0));
		}
		return firstOptionTextField;
	}

	/**
	 * This method initializes secondOptionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSecondOptionTextField() {
		if (secondOptionTextField == null) {
			secondOptionTextField = new JTextField();
			secondOptionTextField.setBounds(new Rectangle(150, 110, 200, 20));
			secondOptionTextField.setText(rq.getChoice().get(1));
		}
		return secondOptionTextField;
	}

	/**
	 * This method initializes thirdOptionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThirdOptionTextField() {
		if (thirdOptionTextField == null) {
			thirdOptionTextField = new JTextField();
			thirdOptionTextField.setBounds(new Rectangle(150, 140, 200, 20));
			thirdOptionTextField.setText(rq.getChoice().get(2));
		}
		return thirdOptionTextField;
	}

	/**
	 * This method initializes fourthOptionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFourthOptionTextField() {
		if (fourthOptionTextField == null) {
			fourthOptionTextField = new JTextField();
			fourthOptionTextField.setBounds(new Rectangle(150, 170, 200, 20));
			fourthOptionTextField.setText(rq.getChoice().get(3));
		}
		return fourthOptionTextField;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setBounds(new Rectangle(100, 210, 80, 20));
			submitButton.setText("Submit");
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String choiceArr[] = new String[rq.getRating().size()];
					choiceArr[0] = getFirstOptionTextField().getText();
					choiceArr[1] = getSecondOptionTextField().getText();
					choiceArr[2] = getThirdOptionTextField().getText();
					choiceArr[3] = getFourthOptionTextField().getText();
					
					if ( !(choiceArr[0].equals(rq.getChoice().get(0)) &&
					choiceArr[1].equals(rq.getChoice().get(1)) &&
					choiceArr[2].equals(rq.getChoice().get(2)) &&
					choiceArr[3].equals(rq.getChoice().get(3)) ) ){
						
						String message = new String();
						
						
						for (int i = 0; i < rq.getRating().size(); i++){
							if (choiceArr[i].equals("")){
								message += ", " + rq.getRating().get(i);
							}
						}
						
						if (!message.equals("")){
							JOptionPane.showMessageDialog(getJFrame(), "Please enter options for scale(s) " + message.substring(2) + ".");
						}
						else{
							boolean valid = true;
							int x = 0;
							
							while (valid && x < choiceArr.length){
								String temp = choiceArr[x];
								temp = temp.replaceAll("\\s+", "");
								valid = temp.matches("^[a-zA-Z]+$");
								x++;
							}
							
							if (!valid){
								JOptionPane.showMessageDialog(getJFrame(), "No symbols allowed. Please enter again.");
							}
							else{
								String msg = new String();
								for (int z = 0; z < choiceArr.length; z++){
									if (choiceArr[z].length() > 20){
										msg += ", " + z;
									}
								}
								
								if (!msg.equals("")){
									JOptionPane.showMessageDialog(getJFrame(), "Maximum characters of 20 has been exceeded by option(s) for scale(s) " + msg.substring(2) + ". Please enter again.");
									valid = false;
								}
							}
							
							if (valid) {		
								int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to update the rating options?", "Message", JOptionPane.YES_NO_OPTION);
								
								if (option == JOptionPane.YES_OPTION){
									AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
									boolean secondValid = true;
									int i = 0;
									
									while (secondValid && i < rq.getRating().size()){
										secondValid = fqController.processUpdateRatingQuestion(rq.getRating().get(i), choiceArr[i]);
										i++;
									}
									
									if (secondValid){
										JOptionPane.showMessageDialog(getJFrame(), "Successfully updated!");
									}
									else{
										JOptionPane.showMessageDialog(getJFrame(), "Unsuccessful. Please try again.");
									}
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
			resetButton.setBounds(new Rectangle(270, 210, 80, 20));
			resetButton.setText("Reset");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getFirstOptionTextField().setText(rq.getChoice().get(0));
					getSecondOptionTextField().setText(rq.getChoice().get(1));
					getThirdOptionTextField().setText(rq.getChoice().get(2));
					getFourthOptionTextField().setText(rq.getChoice().get(3));
				}
			});
		}
		return resetButton;
	}

	public static void main(String[] args){
		UpdateRatingQuestion update = new UpdateRatingQuestion();
		update.getJFrame().setVisible(true);
	}
	
	public UpdateRatingQuestion(){
		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
		rq = fqController.processRetrieveRatingQuestion();
	}
}
