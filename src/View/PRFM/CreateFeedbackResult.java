package View.PRFM;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;
import Controller.PRFM.AdministrateFeedbackResultController;

import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;
import Model.PRFM.FeedbackResult;
import Model.PRFM.OpenEndedResult;
import Model.PRFM.RatingQuestion;
import Model.PRFM.RatingResult;
import Model.PRFM.YesNoQuestion;
import Model.PRFM.YesNoResult;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class CreateFeedbackResult {

	private JTextField tf = new JTextField();
	private Object[] result;
	private String[] resultType;
	private YesNoResult ynr = new YesNoResult();  //  @jve:decl-index=0:
	private RatingResult rr = new RatingResult();  //  @jve:decl-index=0:
	private OpenEndedResult oer = new OpenEndedResult();  //  @jve:decl-index=0:
	private FeedbackQuestion fq = new FeedbackQuestion();  //  @jve:decl-index=0:
	private YesNoQuestion ynq = new YesNoQuestion();
	private RatingQuestion rq = new RatingQuestion();  //  @jve:decl-index=0:
	private FeedbackForm ff = new FeedbackForm();  //  @jve:decl-index=0:
	private ArrayList<String> pageWords = new ArrayList<String>();
	private ArrayList<String> ansWords = new ArrayList<String>();  //  @jve:decl-index=0:
	private int qnsCount;
	private ArrayList<JRadioButton> yesNoArr = new ArrayList<JRadioButton>();
	private ArrayList<JRadioButton> ratingArr = new ArrayList<JRadioButton>();
	private ButtonGroup yesNoBg = new ButtonGroup();  //  @jve:decl-index=0:
	private ButtonGroup ratingBg = new ButtonGroup();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="151,6"
	private JPanel jContentPane = null;
	private JTextArea questionTextArea = null;
	private JTextArea answerTextArea = null;
	private JScrollPane jScrollPane = null;
	private JButton prevButton = null;
	private JButton nextButton = null;
	private JRadioButton secretYesNoRadioButton = null;
	private JRadioButton secretRatingRadioButton = null;
	private JButton goButton = null;
	private JLabel pageLabel = null;
	private JTextArea pageTextArea = null;
	private JButton submitButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Take Survey");
			jFrame.setSize(new Dimension(520, 350));
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
			pageLabel = new JLabel();
			pageLabel.setBounds(new Rectangle(180, 245, 135, 15));
			pageLabel.setText("*Type first/last/qns #");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getQuestionTextArea(), null);
			jContentPane.add(getAnswerTextArea(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getPrevButton(), null);
			jContentPane.add(getNextButton(), null);
			jContentPane.add(getSecretYesNoRadioButton(), null);
			jContentPane.add(getSecretRatingRadioButton(), null);
			jContentPane.add(getGoButton(), null);
			jContentPane.add(pageLabel, null);
			jContentPane.add(getPageTextArea(), null);
			jContentPane.add(getSubmitButton(), null);
			getYesNoRadioButton();
			getRatingRadioButton();
		}
		return jContentPane;
	}

	/**
	 * This method initializes questionTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getQuestionTextArea() {
		if (questionTextArea == null) {
			questionTextArea = new JTextArea();
			questionTextArea.setBackground(null);
			questionTextArea.setLineWrap(true);
			questionTextArea.setWrapStyleWord(true);
			questionTextArea.setBounds(new Rectangle(50, 30, 400, 40));
				
			String qnsNum = String.valueOf(qnsCount + 1) + ". ";
			questionTextArea.setText(qnsNum + fq.getQuestion().get(0));
		}
		return questionTextArea;
	}

	/**
	 * This method initializes answerTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getAnswerTextArea() {
		if (answerTextArea == null) {
			answerTextArea = new JTextArea();
			answerTextArea.setLineWrap(true);
			answerTextArea.setWrapStyleWord(true);
		}
		return answerTextArea;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		jScrollPane = new JScrollPane(getAnswerTextArea(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBounds(new Rectangle(50, 80, 400, 120));

		if (!fq.getType().get(0).equals(fq.getTypeOption().get(2))){
			jScrollPane.setVisible(false);
		}
		
		return jScrollPane;
	}

	/**
	 * This method initializes prevButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrevButton() {
		if (prevButton == null) {
			prevButton = new JButton();
			prevButton.setBounds(new Rectangle(50, 220, 90, 20));
			prevButton.setText("Previous");
			
			if (qnsCount == 0){
				prevButton.setEnabled(false);
			}
			
				prevButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						storeInput();
						
						qnsCount--;
						String qnsNum = String.valueOf(qnsCount + 1) + ". ";
						
						AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
						fq = fqController.processSearchTerm(ff.getFqCode().get(qnsCount), null, null, null);
						
						if (qnsCount == 0){
							prevButton.setEnabled(false);
						}
						else{
							prevButton.setEnabled(true);
						}
						
						if (qnsCount == ff.getCode().size() - 1){
							nextButton.setEnabled(false);
						}
						else{
							nextButton.setEnabled(true);
						}
						
						getQuestionTextArea().setText(qnsNum + fq.getQuestion().get(0));
						resetAnswerOption();
						displayInput();
						getSubmitButton().setEnabled(validation());

					}
				});
	
		}
		return prevButton;
	}

	/**
	 * This method initializes nextButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setBounds(new Rectangle(360, 220, 90, 20));
			nextButton.setText("Next");
		
			nextButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					
					storeInput();
					
					qnsCount++;
					String qnsNum = String.valueOf(qnsCount + 1) + ". ";
					
					AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
					fq = fqController.processSearchTerm(ff.getFqCode().get(qnsCount), null, null, null);
					
					if (qnsCount == 0){
						prevButton.setEnabled(false);
					}
					else{
						prevButton.setEnabled(true);
					}
					
					if (qnsCount == ff.getCode().size() - 1){
						nextButton.setEnabled(false);
					}
					else{
						nextButton.setEnabled(true);
					}
					
					getQuestionTextArea().setText(qnsNum + fq.getQuestion().get(0));
					resetAnswerOption();
					displayInput();
					getSubmitButton().setEnabled(validation());
				}
			});
			
		}
		return nextButton;
	}

	private ArrayList<JRadioButton> getYesNoRadioButton() {
		JRadioButton yesRadioButton = new JRadioButton();
		JRadioButton noRadioButton = new JRadioButton();

		yesRadioButton.setText(ynq.getChoice().get(0));
		noRadioButton.setText(ynq.getChoice().get(1));
		yesRadioButton.setBounds(new Rectangle(50, 80, 60, 20));
		noRadioButton.setBounds(new Rectangle(50, 110, 60, 20));
	
		yesNoArr.add(yesRadioButton);
		yesNoArr.add(noRadioButton);
		yesNoBg.add(yesRadioButton);
		yesNoBg.add(noRadioButton);
		jContentPane.add(yesRadioButton, null);
		jContentPane.add(noRadioButton, null);
		
		if (!fq.getType().get(0).equals(fq.getTypeOption().get(0))){
			yesRadioButton.setVisible(false);
			noRadioButton.setVisible(false);
		}

		return yesNoArr;
	}
	
	/**
	 * This method initializes ratingRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private ArrayList<JRadioButton> getRatingRadioButton() {
		int lDistance = 80;
		
		for (int i = 0; i < rq.getChoice().size(); i++){
			JRadioButton ratingRadioButton = new JRadioButton();
			ratingRadioButton.setText(rq.getChoice().get(i));
			
			ratingRadioButton.setBounds(new Rectangle(50, lDistance, 200, 20));
			lDistance += 30;
			
			ratingArr.add(ratingRadioButton);
			ratingBg.add(ratingRadioButton);
			jContentPane.add(ratingRadioButton, null);
			
			if (!fq.getType().get(0).equals(fq.getTypeOption().get(1))){
				ratingRadioButton.setVisible(false);
			}
			
		}
		
		if (ratingArr.size() >= 4 && fq.getType().get(0).equals(fq.getTypeOption().get(1))){
			int loop = ratingArr.size() - 4;
			int increase = 30 * loop;
			
			getJFrame().setSize(520, 350 + increase);
			getPrevButton().setBounds(new Rectangle(50, 220 + increase, 90, 20));
			getNextButton().setBounds(new Rectangle(360, 220 + increase, 90, 20));
			getPageTextArea().setBounds(new Rectangle(180, 220 + increase, 80, 20));
			getGoButton().setBounds(new Rectangle(265, 220 + increase, 50, 20));
			pageLabel.setBounds(new Rectangle(180, 245 + increase, 135, 15));
			getSubmitButton().setBounds(new Rectangle(205, 280 + increase, 80, 20));
		}
		
		return ratingArr;
	}

	private void resetAnswer(){
		yesNoBg.setSelected(getSecretYesNoRadioButton().getModel(), true);
		ratingBg.setSelected(getSecretRatingRadioButton().getModel(), true);
		getAnswerTextArea().setText("");
	}
	/**
	 * This method initializes secretYesNoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSecretYesNoRadioButton() {
		if (secretYesNoRadioButton == null) {
			secretYesNoRadioButton = new JRadioButton();
		}
		return secretYesNoRadioButton;
	}

	/**
	 * This method initializes secretRatingRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSecretRatingRadioButton() {
		if (secretRatingRadioButton == null) {
			secretRatingRadioButton = new JRadioButton();
		}
		return secretRatingRadioButton;
	}
	
	private void resetAnswerOption(){
		getPageTextArea().setText("");
		
		if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
			for (int a = 0; a < yesNoArr.size(); a++){
				yesNoArr.get(a).setVisible(true);
			}
			
			for (int b = 0; b < ratingArr.size(); b++){
				ratingArr.get(b).setVisible(false);
			}
			
			jScrollPane.setVisible(false);
			
			getJFrame().setSize(520, 350);
			getPrevButton().setBounds(new Rectangle(50, 220, 90, 20));
			getNextButton().setBounds(new Rectangle(360, 220, 90, 20));
			getPageTextArea().setBounds(new Rectangle(180, 220, 80, 20));
			getGoButton().setBounds(new Rectangle(265, 220, 50, 20));
			pageLabel.setBounds(new Rectangle(180, 245, 135, 15));
			getSubmitButton().setBounds(new Rectangle(205, 280, 80, 20));
		}
		
		else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
			if (ratingArr.size() >= 4){
				int loop = ratingArr.size() - 4;
				int increase = 30 * loop;
				
				getJFrame().setSize(520, 350 + increase);
				getPrevButton().setBounds(new Rectangle(50, 220 + increase, 90, 20));
				getNextButton().setBounds(new Rectangle(360, 220 + increase, 90, 20));
				getPageTextArea().setBounds(new Rectangle(180, 220 + increase, 80, 20));
				getGoButton().setBounds(new Rectangle(265, 220 + increase, 50, 20));
				pageLabel.setBounds(new Rectangle(180, 245 + increase, 135, 15));
				getSubmitButton().setBounds(new Rectangle(205, 280 + increase, 80, 20));
			}
			
			for (int a = 0; a < yesNoArr.size(); a++){
				yesNoArr.get(a).setVisible(false);
			}
			
			for (int b = 0; b < ratingArr.size(); b++){
				ratingArr.get(b).setVisible(true);
			}
			
			jScrollPane.setVisible(false);	
		}
		else{
			for (int a = 0; a < yesNoArr.size(); a++){
				yesNoArr.get(a).setVisible(false);
			}
			
			for (int b = 0; b < ratingArr.size(); b++){
				ratingArr.get(b).setVisible(false);
			}
			
			jScrollPane.setVisible(true);
			
			getJFrame().setSize(520, 350);
			getPrevButton().setBounds(new Rectangle(50, 220, 90, 20));
			getNextButton().setBounds(new Rectangle(360, 220, 90, 20));
			getPageTextArea().setBounds(new Rectangle(180, 220, 80, 20));
			getGoButton().setBounds(new Rectangle(265, 220, 50, 20));
			pageLabel.setBounds(new Rectangle(180, 245, 135, 15));
			getSubmitButton().setBounds(new Rectangle(205, 280, 80, 20));
		}
	}
	
	private void storeInput(){
		if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
			if ( !(yesNoBg.getSelection() == null || getSecretYesNoRadioButton().isSelected()) ){
				outerloop:
					for (int i = 0; i < yesNoArr.size(); i++){
						if (yesNoArr.get(i).isSelected()){
							for (int x = 0; x < ynq.getChoice().size(); x++){
								if (yesNoArr.get(i).getText().equals(ynq.getChoice().get(x))){
									result[qnsCount] = ynq.getTrueFalse().get(x);
									resultType[qnsCount] = fq.getType().get(0);
									break outerloop;
								}
							}
						}
					}
			}
		}
		else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
			if ( !(ratingBg.getSelection() == null || getSecretRatingRadioButton().isSelected()) ){
				outerloop:
					for (int i = 0; i < ratingArr.size(); i++){
						if (ratingArr.get(i).isSelected()){
							for (int x = 0; x < rq.getChoice().size(); x++){
								if (ratingArr.get(i).getText().equals(rq.getChoice().get(x))){
									result[qnsCount] = rq.getRating().get(x);
									resultType[qnsCount] = fq.getType().get(0);
									break outerloop;
								}
							}
						}
					}
			}
		}
		else{
			String ans = getAnswerTextArea().getText();
			if (!ans.equals("")){
				result[qnsCount] = ans;
				resultType[qnsCount] = fq.getType().get(0);
			}
		}
	}
	
	private void displayInput(){
		if (result[qnsCount] == null){
			resetAnswer();
		}
		else{
			if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
				outerloop:
					for (int i = 0; i < ynq.getTrueFalse().size(); i++){
						if (result[qnsCount] == ynq.getTrueFalse().get(i)){
							for (int x = 0; x < yesNoArr.size(); x++){
								if (ynq.getChoice().get(i).equals(yesNoArr.get(x).getText())){
									yesNoBg.setSelected(yesNoArr.get(x).getModel(), true);
									break outerloop;
								}
							}
						}
					}
			}
			else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
				outerloop:
					for (int i = 0; i < rq.getRating().size(); i++){
						if (result[qnsCount] == rq.getRating().get(i)){
							for (int x = 0; x < ratingArr.size(); x++){
								if (rq.getChoice().get(i).equals(ratingArr.get(x).getText())){
									ratingBg.setSelected(ratingArr.get(x).getModel(), true);
									break outerloop;
								}
							}
						}
					}
			}
			else{
				getAnswerTextArea().setText((String) result[qnsCount]);
			}
		}
	}

	/**
	 * This method initializes goButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoButton() {
		if (goButton == null) {
			goButton = new JButton();
			goButton.setText("Go");
			goButton.setBounds(new Rectangle(265, 220, 50, 20));
			goButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String pageText = getPageTextArea().getText();
					
					if (!pageText.equals("")){				
						storeInput();
						
						pageText = pageText.replace(" ", "");
						if (pageText.equals(pageWords.get(0))){
							qnsCount = 0;
						}
						else if (pageText.equals(pageWords.get(1))){
							qnsCount = ff.getCode().size() - 1;
						}
						else{
							try{
								int num = Integer.parseInt(pageText);
								if (num <= 0 || num >= ff.getCode().size()){
									JOptionPane.showMessageDialog(getJFrame(), "There are only questions 1 to " + ff.getCode().size() + ". Please enter again.");
								}
								else{
									qnsCount = num - 1;
								}
							}
							catch (InputMismatchException ex){
								JOptionPane.showMessageDialog(getJFrame(), "Please enter first, last or a legal question number.");
							}
						}
						
						String qnsNum = String.valueOf(qnsCount + 1) + ". ";
						
						AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
						fq = fqController.processSearchTerm(ff.getFqCode().get(qnsCount), null, null, null);
						
						if (qnsCount == 0){
							prevButton.setEnabled(false);
						}
						else{
							prevButton.setEnabled(true);
						}
						
						if (qnsCount == ff.getCode().size() - 1){
							nextButton.setEnabled(false);
						}
						else{
							nextButton.setEnabled(true);
						}
						
						getQuestionTextArea().setText(qnsNum + fq.getQuestion().get(0));
						resetAnswerOption();
						displayInput();
						getSubmitButton().setEnabled(validation());
					
					}
				}
			});
		}
		return goButton;
	}

	/**
	 * This method initializes pageTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getPageTextArea() {
		if (pageTextArea == null) {
			pageTextArea = new JTextArea();
			pageTextArea.setBorder(tf.getBorder());
			pageTextArea.setBounds(new Rectangle(180, 220, 80, 20));
		}
		return pageTextArea;
	}

	private boolean validation(){
		boolean valid = true;
		int i = 0;
		
		while (valid && i < result.length){
			if (result[i] == null){
				valid = false;
			}
			i++;
		}
		
		return valid;
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
			submitButton.setBounds(new Rectangle(205, 280, 80, 20));
			submitButton.setEnabled(false);
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to submit?", "Message", JOptionPane.YES_NO_OPTION);
					
					if (option == JOptionPane.YES_OPTION){
						for (int i = 0; i < resultType.length; i++){
							if (resultType[i].equals(fq.getTypeOption().get(0))){
								ynr.setFqCode(ff.getFqCode().get(i));
								ynr.setTrueFalse(Boolean.valueOf(result[i].toString()));
							}
							else if (resultType[i].equals(fq.getTypeOption().get(1))){
								rr.setFqCode(ff.getFqCode().get(i));
								rr.setRating(Integer.valueOf(result[i].toString()));
							}
							else{
								oer.setFqCode(ff.getFqCode().get(i));
								oer.setResult(result[i].toString());
							}
						}
						
						AdministrateFeedbackResultController frController = new AdministrateFeedbackResultController();
						
						if (frController.processCreate(ff.getCode().get(0))){
							boolean ynValid = true, rValid = true, oeValid = true;
							int frCode = frController.processLastCode();
							
							if (!ynr.getFqCode().isEmpty()){
								int i = 0;
								
								while (ynValid && i < ynr.getFqCode().size()){
									ynValid = frController.processCreateYesNoResult(frCode, ynr.getFqCode().get(i), ynr.getTrueFalse().get(i));
									i++;
								}
							}
							
							if (!rr.getFqCode().isEmpty()){
								int i = 0;
								
								while (rValid && i < rr.getFqCode().size()){
									rValid = frController.processCreateRatingResult(frCode, rr.getFqCode().get(i), rr.getRating().get(i));
									i++;
								}
							}
							
							if (!oer.getFqCode().isEmpty()){
								int i = 0;
								
								while (oeValid && i < oer.getFqCode().size()){
									oeValid = frController.processCreateOpenEndedResult(frCode, oer.getFqCode().get(i), oer.getResult().get(i).replaceAll("'", "\\\\'"));
									i++;
								}
							}
							
							if (ynValid && rValid && oeValid){
								JOptionPane.showMessageDialog(getJFrame(), "Successfully submitted!");
								
								for (int i = 0; i < result.length; i++){
									result[i] = null;
								}
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

	public static void main(String[] args){
		CreateFeedbackResult create = new CreateFeedbackResult();
		create.getJFrame().setVisible(true);
	}
	
	
	public CreateFeedbackResult(){
		
	}
	
	public CreateFeedbackResult(int ffCode){
		qnsCount = 0;
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(ffCode, null, 0);
		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
		fq = fqController.processSearchTerm(ff.getFqCode().get(qnsCount), null, null, null);
		ynq = fqController.processRetrieveYesNoQuestion();
		rq = fqController.processRetrieveRatingQuestion();
		result = new Object[ff.getCode().size()];
		resultType = new String[ff.getCode().size()];
		pageWords.add("first");
		pageWords.add("last");
		Autocomplete pageComplete = new Autocomplete(getPageTextArea(), pageWords);
		AdministrateFeedbackResultController frController = new AdministrateFeedbackResultController();
		
		ansWords = frController.processRetrieveWords();
		
		Autocomplete answerComplete = new Autocomplete(getAnswerTextArea(), ansWords);
	}
}
