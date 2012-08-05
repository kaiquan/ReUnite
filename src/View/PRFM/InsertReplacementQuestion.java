package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTextField;

import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;

import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class InsertReplacementQuestion {

	private int code;
	private int deleteCode;
	private FeedbackQuestion fq = new FeedbackQuestion();  //  @jve:decl-index=0:
	private FeedbackForm ff = new FeedbackForm();  //  @jve:decl-index=0:
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="142,5"
	private JPanel jContentPane = null;
	private JLabel codeLabel = null;
	private JLabel dateLabel = null;
	private JLabel typeLabel = null;
	private JLabel questionLabel = null;
	private JTextField codeTextField = null;
	private JTextField dateTextField = null;
	private JTextField typeTextField = null;
	private JTextField questionTextField = null;
	private JLabel formLabel = null;
	private JTextField formTextField = null;
	private JButton viewButton = null;
	private JButton replaceButton = null;

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setTitle("Delete Feedback Question #" + deleteCode + " and replace with Feedback Question #" + fq.getCode().get(0).toString());
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
			jContentPane.add(getTypeTextField(), null);
			jContentPane.add(getQuestionTextField(), null);
			jContentPane.add(formLabel, null);
			jContentPane.add(getFormTextField(), null);
			jContentPane.add(getViewButton(), null);
			jContentPane.add(getReplaceButton(), null);
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
	 * This method initializes typeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTypeTextField() {
		if (typeTextField == null) {
			typeTextField = new JTextField();
			typeTextField.setBounds(new Rectangle(160, 79, 300, 20));
			typeTextField.setBackground(null);
			typeTextField.setBorder(null);
			typeTextField.setEditable(false);
			typeTextField.setText(fq.getType().get(0));
		}
		return typeTextField;
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
			questionTextField.setBackground(null);
			questionTextField.setBorder(null);
			questionTextField.setEditable(false);
			questionTextField.setText(fq.getQuestion().get(0));
		}
		return questionTextField;
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
			viewButton.setBounds(new Rectangle(60, 180, 200, 20));
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

	/**
	 * This method initializes replaceButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReplaceButton() {
		if (replaceButton == null) {
			replaceButton = new JButton();
			replaceButton.setBounds(new Rectangle(320, 180, 100, 20));
			replaceButton.setText("Replace");
			replaceButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(getJFrame(), "Do you really want to delete Feedback Question #" + deleteCode + " and replace with Feedback Question #" + fq.getCode().get(0).toString() + "?", "Message", JOptionPane.YES_NO_OPTION);	
					
					if (option == JOptionPane.YES_OPTION){
						AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
						AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();

						if (ffController.processUpdate(deleteCode, fq.getCode().get(0)) && fqController.processDelete(deleteCode)){
								JOptionPane.showMessageDialog(getJFrame(), "Successfully replaced and deleted!");
								getJFrame().dispose();
							}
							else{
								JOptionPane.showMessageDialog(getJFrame(), "Unsuccessful. Please try again.");
							}
						}
				}
			});
		}
		return replaceButton;
	}

	public static void main(String[] args){
		InsertReplacementQuestion insert = new InsertReplacementQuestion();
		insert.getJFrame().setVisible(true);
	}
	
	public InsertReplacementQuestion(){
		
	}
	
	public InsertReplacementQuestion(int code, int deleteCode){
		this.code = code;
		this.deleteCode = deleteCode;
		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
		fq = fqController.processSearchTerm(code, null, null, null);
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(0, null, fq.getCode().get(0));
	}
}
