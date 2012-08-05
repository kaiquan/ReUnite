package View.PRFM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RetrieveFeedbackResult {

	private int colSize = 1, ffCode = 0;
	private DefaultTableModel model = new DefaultTableModel();
	private FeedbackQuestion fq = new FeedbackQuestion();
	private FeedbackForm ff = new FeedbackForm();  //  @jve:decl-index=0:
	private FeedbackResult fr;  //  @jve:decl-index=0:
	private int qnsCount = 0;
	@SuppressWarnings("unused")
	private ArrayList<Integer> sumArr = new ArrayList<Integer>();
	private static final long serialVersionUID = 1L;
	private JFreeChart chart = null;  //  @jve:decl-index=0:
	private ChartPanel chartPanel = null;
	private PiePlot3D plot = null;
	DefaultPieDataset result = new DefaultPieDataset();  //  @jve:decl-index=0:
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="158,10"
	private JPanel jContentPane = null;
	private JLabel codeLabel = null;
	private JLabel dateLabel = null;
	private JLabel thirdLabel = null;
	private JLabel fourthLabel = null;
	private JTextField codeTextField = null;
	private JTextField dateTextField = null;
	private JTextField thirdTextField = null;
	private JTextField fourthTextField = null;
	private JTextArea resultTextArea = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton nextButton = null;
	private JButton prevButton = null;
	@SuppressWarnings("unchecked")
	private JComboBox codeComboBox = null;
	private JButton goButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			
			String title = "Retrieve Feedback Result for Feedback ";
			
			if (ff.getCode().isEmpty()){
				title += "Question #" + fq.getCode().get(0);
			}
			else{
				title += "Form #" + ff.getCode().get(0);
			}
			
			jFrame.setTitle(title);
			jFrame.setSize(new Dimension(500, 470));
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
			
			fourthLabel = new JLabel();
			fourthLabel.setBounds(new Rectangle(25, 110, 120, 15));

			thirdLabel = new JLabel();
			thirdLabel.setBounds(new Rectangle(25, 80, 120, 15));

			if (ff.getCode().isEmpty()){
				thirdLabel.setText("Type: ");
				fourthLabel.setText("Included in form(s): ");
			}
			else{
				thirdLabel.setText("FQ Code (in order): ");
				fourthLabel.setText("Now Showing #: ");
			}
			
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
			jContentPane.add(thirdLabel, null);
			jContentPane.add(fourthLabel, null);
			jContentPane.add(getCodeTextField(), null);
			jContentPane.add(getDateTextField(), null);
			jContentPane.add(getThirdTextField(), null);
			jContentPane.add(getFourthTextField(), null);
			jContentPane.add(getResultTextArea(), null);
			
			if (!ff.getCode().isEmpty()){
				jContentPane.add(getNextButton(), null);
				jContentPane.add(getPrevButton(), null);
				jContentPane.add(getCodeComboBox(), null);
				jContentPane.add(getGoButton(), null);
			}
			
			if (!(fr instanceof OpenEndedResult)){
				jContentPane.add(getChartPanel(), null);
			}
			else{
				jContentPane.add(getJScrollPane(), null);
			}
			
		}
		
		return jContentPane;
	}
	
	private ChartPanel getChartPanel() {
    	if (chartPanel == null){
	        chartPanel = new ChartPanel(getChart());
        }

    	if ( !(fr instanceof OpenEndedResult) ){
	        chartPanel.setBounds(new Rectangle(40, 140, 400, 216));
    	}

        return chartPanel;
    }
    
    /**
     * Creates a chart
     */
    private JFreeChart getChart() {
        if (chart == null){
            chart = ChartFactory.createPieChart3D("", null, true, false, false);
	        chart.setBackgroundPaint(getJFrame().getBackground());
        }
        
        return chart;
        
    }
    
    private PiePlot3D getPlot(){
    	if (plot == null){
    		plot = (PiePlot3D) getChart().getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setMaximumLabelWidth(0.2);
	        plot.setBackgroundPaint(getJFrame().getBackground());
    	}
    	return plot;
    }
    
    public void setChartValues(){		
    	result.clear();
    	
    	if (fr instanceof YesNoResult){
    		YesNoResult ynr = (YesNoResult) fr;
    		YesNoQuestion ynq = new YesNoQuestion();
    		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
    		ynq = fqController.processRetrieveYesNoQuestion();
        	int sum[] = new int[ynq.getChoice().size()];
        	                    
    		for (int a = 0; a < ynr.getFqCode().size(); a++){
    			for (int b = 0; b < ynq.getChoice().size(); b++){
    				if (ynr.getTrueFalse().get(a) == ynq.getTrueFalse().get(b)){
    					sum[b] += 1;
    				}
    			}
    		}
    		
    		for (int i = 0; i < ynq.getChoice().size(); i++){
    			result.setValue(ynq.getChoice().get(i), sum[i]);		
    		}		
    	}
    	else if (fr instanceof RatingResult){
    		RatingResult rr = (RatingResult) fr;
    		RatingQuestion rq = new RatingQuestion();
    		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
    		rq = fqController.processRetrieveRatingQuestion();
        	int sum[] = new int[rq.getChoice().size()];
        	                    
    		for (int a = 0; a < rr.getFqCode().size(); a++){
    			for (int b = 0; b < rq.getChoice().size(); b++){
    				if (rr.getRating().get(a) == rq.getRating().get(b)){
    					sum[b] += 1;
    				}
    			}
    		}
    		
    		for (int i = 0; i < rq.getChoice().size(); i++){
    			result.setValue(rq.getChoice().get(i), sum[i]);
    		}		
    	}

    	getChart().setTitle(fq.getQuestion().get(0));
    	getPlot().setDataset(result);
    }
    
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
	private JTextField getThirdTextField() {
		if (thirdTextField == null) {
			thirdTextField = new JTextField();
			thirdTextField.setBounds(new Rectangle(160, 79, 300, 20));
			thirdTextField.setBackground(null);
			thirdTextField.setBorder(null);
			thirdTextField.setEditable(false);
			
			if (ff.getCode().isEmpty()){
				thirdTextField.setText(fq.getType().get(0));
			}
			else{
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
				
				thirdTextField.setText(fqCode);
			}
		}
		return thirdTextField;
	}

	/**
	 * This method initializes questionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFourthTextField() {
		if (fourthTextField == null) {
			fourthTextField = new JTextField();
			fourthTextField.setBounds(new Rectangle(160, 109, 300, 20));
			fourthTextField.setBackground(null);
			fourthTextField.setBorder(null);
			fourthTextField.setEditable(false);

			if (ff.getCode().isEmpty()){
				String fqFormCode = "NIL";
				
				FeedbackForm tempFf = new FeedbackForm();
				AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
				tempFf = ffController.processSearchTerm(0, null, fq.getCode().get(0));
				
				for (int i = 0; i < tempFf.getCode().size(); i++){
					if (i == 0){
						fqFormCode = tempFf.getCode().get(i).toString();
					}
					else{
						fqFormCode += tempFf.getCode().get(i).toString();
					}
					
					if (i != tempFf.getCode().size() - 1){
						fqFormCode += ", ";
					}
				}
				
				fourthTextField.setText(fqFormCode);
			}
			else{
				fourthTextField.setText(ff.getFqCode().get(qnsCount).toString());
			}
		}
		return fourthTextField;
	}


	/**
	 * This method initializes resultTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getResultTextArea() {
		if (resultTextArea == null) {
			resultTextArea = new JTextArea();
			resultTextArea.setBackground(null);
			resultTextArea.setBorder(null);
			resultTextArea.setEditable(false);
			resultTextArea.setLineWrap(true);
			resultTextArea.setWrapStyleWord(true);
		}
		
		String value = "Total number of respondents: ";
		
		if ( !(fr instanceof OpenEndedResult) ){
			String data = new String();
			int total = 0;
			double percentage = 0.0;

			for (int i = 0; i < result.getItemCount(); i++){
				total += result.getValue(i).intValue();
			}
			
            DecimalFormat df = new DecimalFormat("#.##");        
            
			for (int x = 0; x < result.getItemCount(); x++){
				percentage = (result.getValue(x).doubleValue() / (double) total) * 100.0;
				percentage = Double.valueOf(df.format(percentage));
				data += result.getKey(x) + ": " + result.getValue(x).intValue() + " (" + percentage + "%)" + "     ";
			}
			
			value += total + "\n" + data;
			
			resultTextArea.setBounds(new Rectangle(25, 370, 435, 60));
	
		}
		else{
			OpenEndedResult oer = (OpenEndedResult) fr;
			value += oer.getResult().size() + "\nNote: Duplicate records are removed.";
			resultTextArea.setBounds(new Rectangle(25, 390, 435, 40));
		}
		
		resultTextArea.setText(value);
			
		return resultTextArea;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(25, 139, 435, 240));
		}
		
		if (fr instanceof OpenEndedResult){
			jScrollPane.setViewportView(getJTable());
		}
		
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	JTable getJTable() {
		if (jTable == null) {
			if (fr instanceof OpenEndedResult){
				OpenEndedResult oer = (OpenEndedResult) fr;
				
				for (int a = 0; a < oer.getResult().size(); a++){
					String first = oer.getResult().get(a);
					
					for (int b = a + 1; b < oer.getResult().size(); b++){
						String second = oer.getResult().get(b);
						
						if (first.equals(second)){
							oer.getCode().remove(b);
							oer.getFqCode().remove(b);
							oer.getResult().remove(b);
							b -= 1;
						}
					}
				}
				
				int colWidth = 432;
		        Object[] colNames = {fq.getQuestion().get(0)};
		        Object[][] data = new Object[oer.getResult().size()][colSize];

		        for (int i = 0; i < oer.getResult().size(); i++){
	        		data[i][0] = oer.getResult().get(i);
		        }
		        
				model.setDataVector(data, colNames);
				jTable = new JTable(model);
				
		    	jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			    
	    		jTable.getColumnModel().getColumn(0).setCellRenderer(new Renderer());
	    		jTable.getColumnModel().getColumn(0).setPreferredWidth(colWidth);
	
		    	jTable.setAutoCreateColumnsFromModel(false);
		    	jTable.setEnabled(false);

		    	jTable.addMouseListener(new ResultTableButtonMouseListener(jTable, fq.getCode().get(0)));
		}
	}
		return jTable;
	}

	/**
	 * This method initializes nextButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			
			if (!ff.getCode().isEmpty()){
				nextButton.setText("Next");
				nextButton.setBounds(new Rectangle(360, 440, 100, 20));
			}
			
			nextButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					qnsCount++;
					
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
					
					FeedbackResult tempFr = fr;
					
					setFq(ff.getFqCode().get(qnsCount), ffCode);
					getFourthTextField().setText(ff.getFqCode().get(qnsCount).toString());

					if ( !(tempFr instanceof OpenEndedResult) && (fr instanceof OpenEndedResult) ){
						jContentPane.remove(getChartPanel());
						jContentPane.add(getJScrollPane());
					}
					else if ( (tempFr instanceof OpenEndedResult) && !(fr instanceof OpenEndedResult)){
						jContentPane.remove(getJScrollPane());
						jContentPane.add(getChartPanel());
					}
					
					getResultTextArea();
				}
			});
		}
		return nextButton;
	}

	/**
	 * This method initializes prevButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrevButton() {
		if (prevButton == null) {
			prevButton = new JButton();
			
			if (!ff.getCode().isEmpty()){
				getJFrame().setSize(new Dimension(500, 520));
				prevButton.setText("Previous");
				prevButton.setBounds(new Rectangle(50, 440, 100, 20));
				
				if (qnsCount == 0){
					prevButton.setEnabled(false);
				}
			}
			
			prevButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					qnsCount--;
					
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
					
					FeedbackResult tempFr = fr;
					
					setFq(ff.getFqCode().get(qnsCount), ffCode);
					getFourthTextField().setText(ff.getFqCode().get(qnsCount).toString());

					if ( !(tempFr instanceof OpenEndedResult) && (fr instanceof OpenEndedResult) ){
						jContentPane.remove(getChartPanel());
						jContentPane.add(getJScrollPane());
					}
					else if ( (tempFr instanceof OpenEndedResult) && !(fr instanceof OpenEndedResult)){
						jContentPane.remove(getJScrollPane());
						jContentPane.add(getChartPanel());
					}
					
					getResultTextArea();
				}
			});
		}
		return prevButton;
	}

	/**
	 * This method initializes codeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getCodeComboBox() {
		if (codeComboBox == null) {
			codeComboBox = new JComboBox();
			
			if (!ff.getCode().isEmpty()){
				codeComboBox.setBounds(new Rectangle(173, 440, 100, 20));
				codeComboBox.addItem("FQ Code");
				
				for (int i = 0; i < ff.getFqCode().size(); i++){
					codeComboBox.addItem(ff.getFqCode().get(i));
				}
			}
		}
		return codeComboBox;
	}

	/**
	 * This method initializes goButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoButton() {
		if (goButton == null) {
			goButton = new JButton();
			
			if (!ff.getCode().isEmpty()){
				goButton.setBounds(new Rectangle(278, 440, 50, 20));
				goButton.setText("Go");
			}
			
			goButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (getCodeComboBox().getSelectedIndex() != 0){
						int code = (Integer) getCodeComboBox().getSelectedItem();
						
						boolean valid = true;
						int i = 0;
						
						while (valid && i < ff.getFqCode().size()){
							if (code == ff.getFqCode().get(i)){
								qnsCount = i;
							}
							i++;
						}
						
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
						
						FeedbackResult tempFr = fr;
						
						setFq(ff.getFqCode().get(qnsCount), ffCode);
						getFourthTextField().setText(ff.getFqCode().get(qnsCount).toString());

						if ( !(tempFr instanceof OpenEndedResult) && (fr instanceof OpenEndedResult) ){
							jContentPane.remove(getChartPanel());
							jContentPane.add(getJScrollPane());
						}
						else if ( (tempFr instanceof OpenEndedResult) && !(fr instanceof OpenEndedResult)){
							jContentPane.remove(getJScrollPane());
							jContentPane.add(getChartPanel());
						}
						
						getResultTextArea();
					}
				}
			});
		}
		return goButton;
	}

	public static void main(String[] args){
		RetrieveFeedbackResult retrieve = new RetrieveFeedbackResult();
		retrieve.getJFrame().setVisible(true);
	}

	public RetrieveFeedbackResult(){

	}
	
	public void setFq(int fqCode, int ffCode){
		AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
		AdministrateFeedbackResultController frController = new AdministrateFeedbackResultController();
		fq = fqController.processSearchTerm(fqCode, null, null, null);

		if (ffCode == 0){
			if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
				fr = frController.processYesNoResultSearchTerm(0, fqCode, false, false);
			}
			else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
				fr = frController.processRatingResultSearchTerm(0, fqCode, 0);
			}
			else if (fq.getType().get(0).equals(fq.getTypeOption().get(2))){
				fr = frController.processOpenEndedResultSearchTerm(0, fqCode, null);
			}
		}
		else{
			ArrayList<Integer> frArr = frController.processSearchTerm(0, null, ffCode).getCode();
			
			if (fq.getType().get(0).equals(fq.getTypeOption().get(0))){
				fr = frController.processYesNoResultSearchTerm(frArr, fqCode, false, false);
			}
			else if (fq.getType().get(0).equals(fq.getTypeOption().get(1))){
				fr = frController.processRatingResultSearchTerm(frArr, fqCode, 0);
			}
			else if (fq.getType().get(0).equals(fq.getTypeOption().get(2))){
				fr = frController.processOpenEndedResultSearchTerm(frArr, fqCode, null);
			}
		}
		
		setChartValues();
	}
	
	
	public void setFf(int ffCode){
		this.ffCode = ffCode;
		AdministrateFeedbackFormController ffController = new AdministrateFeedbackFormController();
		ff = ffController.processSearchTerm(ffCode, null, 0);
		setFq(ff.getFqCode().get(qnsCount), ffCode);
	}
}
