package View.PRFM;

import View.PRFM.QuestionTableButtonMouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;

import Controller.PRFM.AdministrateFeedbackQuestionController;
import Model.PRFM.FeedbackQuestion;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JRadioButton;

public class RetrieveFeedbackQuestion {

	//  @jve:decl-index=0:
	private String source = null, selection = "Result";
	private final DefaultTableModel model = new DefaultTableModel();
	private QuestionTableButtonMouseListener qtbml;  //  @jve:decl-index=0:
	private DefaultTableModel viewModel = null;
	private Object crudFf = null;
	private int deleteCode = 0;
	private int colSize = 4;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="157,8"
	private JPanel jContentPane = null;
	private JLabel filterLabel = null;
	private final String text = "Keywords (eg. entertainment, food)";
	private JTextArea keywordTextArea = null;
	private JLabel keywordNoteLabel = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	@SuppressWarnings("unchecked")
	private JComboBox codeComboBox = null;
	@SuppressWarnings("unchecked")
	private JComboBox dateComboBox = null;
	@SuppressWarnings("unchecked")
	private JComboBox typeComboBox = null;
	private JButton goButton = null;
	private JButton button = null;
	private JRadioButton detailsRadioButton = null;
	private JRadioButton resultRadioButton = null;
	private ButtonGroup bg = new ButtonGroup();
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(800, 650));
			jFrame.setTitle(source);
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
			AdministrateFeedbackQuestionController fqController = new AdministrateFeedbackQuestionController();
			ArrayList<String> wordArr = fqController.processRetrieveWords();
			@SuppressWarnings("unused")
			Autocomplete ac = new Autocomplete(getKeywordTextArea(), wordArr);
			bg.add(getDetailsRadioButton());
			bg.add(getResultRadioButton());
			keywordNoteLabel = new JLabel();
			keywordNoteLabel.setBounds(new Rectangle(460, 45, 300, 15));
			keywordNoteLabel.setText("*Separate keywords with a comma and a space");
			filterLabel = new JLabel();
			filterLabel.setBounds(new Rectangle(25, 20, 80, 15));
			filterLabel.setText("Filter by: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(filterLabel, null);
			jContentPane.add(getKeywordTextArea(), null);
			jContentPane.add(keywordNoteLabel, null);
			jContentPane.add(getJTable(), null);
			jContentPane.add(getCodeComboBox(), null);
			jContentPane.add(getDateComboBox(), null);
			jContentPane.add(getTypeComboBox(), null);
			jContentPane.add(getGoButton(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getButton(), null);
			jContentPane.add(getDetailsRadioButton(), null);
			jContentPane.add(getResultRadioButton(), null);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes keywordTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextArea getKeywordTextArea() {
		if (keywordTextArea == null) {
			keywordTextArea = new JTextArea();
			keywordTextArea.setBounds(new Rectangle(460, 19, 250, 20));
			keywordTextArea.setText(text);
			keywordTextArea.setBorder(new JTextField().getBorder());
			keywordTextArea.setLineWrap(true);
			keywordTextArea.setWrapStyleWord(true);
			
			keywordTextArea.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseEntered(java.awt.event.MouseEvent e) {    
					if (keywordTextArea.getText().equals(text)){
						keywordTextArea.setText("");
					}
				}
				
				public void mouseExited(java.awt.event.MouseEvent e) {    
					if (keywordTextArea.getText().equals("")){
						keywordTextArea.setText(text);
					}
				}   
			});
		}
		return keywordTextArea;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(25, 90, 740, 450));
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
			FeedbackQuestion fq = new FeedbackQuestion();
			AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
			fq = controller.processRetrieve();
			
			int colWidth = 100;
	        Object[] colNames = {"Code", "Creation Date", "Type", "Question"};
	        Object[][] data = new Object[fq.getCode().size()][colSize];

	        for (int i = 0; i < fq.getCode().size(); i++){
        		data[i][0] = fq.getCode().get(i);
        		data[i][1] = fq.getCreationDate().get(i);
        		data[i][2] = fq.getType().get(i);
        		data[i][3] = fq.getQuestion().get(i);
	        	}
		
			model.setDataVector(data, colNames);
			jTable = new JTable(model);
			
			if (viewModel != null){
				jTable.setModel(viewModel);
				viewModel = null;
			}
			
	    	jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    
	    	for (int i = 0; i < colSize; i++){
	    		jTable.getColumnModel().getColumn(i).setCellRenderer(new Renderer());
	    		jTable.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
	    		
	    		if (i == colSize - 1){
		    		jTable.getColumnModel().getColumn(colSize - 1).setPreferredWidth(422);
	    		}
	    	}
	    	
	    	
	    	jTable.setAutoCreateColumnsFromModel(false);
	    	sortAllRowsBy(model, 0, true);
	    	jTable.setEnabled(false);
	    	
	    	JTableHeader header = jTable.getTableHeader() ;
	    	header.addMouseListener(new MouseAdapter(){
		    	public void mouseClicked(MouseEvent e){
			    	JTableHeader h = (JTableHeader)e.getSource() ;
			    	int nColumn = h.columnAtPoint(e.getPoint());
			    	if (nColumn != -1)
			    	sortAllRowsBy(model, nColumn, true) ;
		    	}
	    	}
	    	) ;
	    	
	    	qtbml = new QuestionTableButtonMouseListener(jTable, source, deleteCode, crudFf);
	    	qtbml.setSelection(selection);
	    	jTable.addMouseListener(qtbml);
	   
	}
		return jTable;
	}
	
	@SuppressWarnings("unchecked")
	public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) {
	    Vector data = model.getDataVector();
	    Collections.sort(data, new ColumnSorter(colIndex, ascending));
	    model.fireTableStructureChanged();
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
			codeComboBox.setBounds(new Rectangle(120, 18, 70, 25));
			codeComboBox.addItem("Code");

			FeedbackQuestion fq = new FeedbackQuestion();
			AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
			fq = controller.processRetrieve();
			
			Collections.sort(fq.getCode());
			
			for (int i = 0; i < fq.getCode().size(); i++){
				codeComboBox.addItem(fq.getCode().get(i));
			}
		}
		return codeComboBox;
	}

	/**
	 * This method initializes dateComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getDateComboBox() {
		if (dateComboBox == null) {
			dateComboBox = new JComboBox();
			dateComboBox.setBounds(new Rectangle(210, 18, 110, 25));
			dateComboBox.addItem("Creation Date");
			
			FeedbackQuestion fq = new FeedbackQuestion();
			AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
			fq = controller.processRetrieve();
			
			Collections.sort(fq.getCreationDate());
			
			for (int a = 0; a < fq.getCreationDate().size(); a++){
				Date first = fq.getCreationDate().get(a);
				for (int b = a + 1; b < fq.getCreationDate().size(); b++){
					Date second = fq.getCreationDate().get(b);
					
					if (first.equals(second)){
						fq.getCreationDate().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < fq.getCreationDate().size(); i++){
				dateComboBox.addItem(fq.getCreationDate().get(i));
			}
		}
		return dateComboBox;
	}

	/**
	 * This method initializes typeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getTypeComboBox() {
		if (typeComboBox == null) {
			typeComboBox = new JComboBox();
			typeComboBox.setBounds(new Rectangle(340, 18, 100, 25));
			typeComboBox.addItem("Type");

			FeedbackQuestion fq = new FeedbackQuestion();
			
			for (int i = 0; i < fq.getTypeOption().size(); i++){
				typeComboBox.addItem(fq.getTypeOption().get(i));
				
			}
		}
		return typeComboBox;
	}

	/**
	 * This method initializes goButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoButton() {
		if (goButton == null) {
			goButton = new JButton();
			goButton.setBounds(new Rectangle(725, 20, 50, 20));
			goButton.setText("Go");
			goButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int code = 0;
					Date date = null;
					String type = null;
					ArrayList<String> keyword = new ArrayList<String>();
					
					if (getCodeComboBox().getSelectedIndex() != 0){
						code = (Integer) getCodeComboBox().getSelectedItem();
					}
					
					if (getDateComboBox().getSelectedIndex() != 0){
						date = (Date) getDateComboBox().getSelectedItem();
					}
					
					if (getTypeComboBox().getSelectedIndex() != 0){
						type = (String) getTypeComboBox().getSelectedItem();
					}
					
					if (!getKeywordTextArea().getText().equals(text)){
						Scanner sc = new Scanner(getKeywordTextArea().getText());
						sc.useDelimiter(",");
						
						while (sc.hasNext()){
							keyword.add(sc.next().replace(" ", ""));
						}			
					}
					else{
						keyword = null;
					}
					
					
					FeedbackQuestion fq = new FeedbackQuestion();
					AdministrateFeedbackQuestionController controller = new AdministrateFeedbackQuestionController();
					fq = controller.processSearchTerm(code, date, type, keyword);
					
					if (fq == null){
						getJTable().setModel(model);
					}
					else{
				        Object[] colNames = {"Code", "Creation Date", "Type", "Question"};
				        Object[][] data = new Object[fq.getCode().size()][colSize];
	
				        for (int i = 0; i < fq.getCode().size(); i++){
			        		data[i][0] = fq.getCode().get(i);
			        		data[i][1] = fq.getCreationDate().get(i);
			        		data[i][2] = fq.getType().get(i);
			        		data[i][3] = fq.getQuestion().get(i);
				        	}
					
						final DefaultTableModel model = new DefaultTableModel(data, colNames);
						getJTable().setModel(model);
						
				    	getJTable().setAutoCreateColumnsFromModel(false);
				    	sortAllRowsBy(model, 0, true);
				    	getJTable().setEnabled(false);
				    	
				    	JTableHeader header = getJTable().getTableHeader() ;
				    	header.addMouseListener(new MouseAdapter(){
					    	public void mouseClicked(MouseEvent e){
						    	JTableHeader h = (JTableHeader)e.getSource() ;
						    	int nColumn = h.columnAtPoint(e.getPoint());
						    	if (nColumn != -1)
						    	sortAllRowsBy(model, nColumn, true) ;
					    	}
				    	}
				    	) ;
						
						
					}
				}
			});
		}
		return goButton;
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton();
			
			if (source.equals("Create Feedback Question")){
				button.setBounds(new Rectangle(25, 560, 250, 20));
				button.setText(source);
			}
			
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (source.equals("Create Feedback Question")){
						CreateFeedbackQuestion create = new CreateFeedbackQuestion();
						create.getJFrame().setVisible(true);
					}
					
					source = null;
					getJFrame().dispose();
				}
			});
		}
		return button;
	}

	/**
	 * This method initializes detailsRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getDetailsRadioButton() {
		if (detailsRadioButton == null) {
			detailsRadioButton = new JRadioButton();

			if (source.equals("Select Feedback Question to View Result")){
				detailsRadioButton.setBounds(new Rectangle(25, 560, 150, 20));
				detailsRadioButton.setText("View details on click");
			}
			
			detailsRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "Details";
					qtbml.setSelection(selection);
				}
			});
		}
		return detailsRadioButton;
	}

	/**
	 * This method initializes resultRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getResultRadioButton() {
		if (resultRadioButton == null) {
			resultRadioButton = new JRadioButton();

			resultRadioButton.setSelected(true);
			
			if (source.equals("Select Feedback Question to View Result")){
				resultRadioButton.setBounds(new Rectangle(195, 560, 200, 20));
				resultRadioButton.setText("View result on click");
			}
			
			resultRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "Result";
					qtbml.setSelection(selection);
				}
			});
		}
		return resultRadioButton;
	}

	public static void main(String[] args){
		RetrieveFeedbackQuestion retrieve = new RetrieveFeedbackQuestion();
		retrieve.getJFrame().setVisible(true);
	}
	
	public RetrieveFeedbackQuestion(String source){
		this.source = source;
	}
	
	public RetrieveFeedbackQuestion(String source, int deleteCode){
		this.source = source;
		this.deleteCode = deleteCode;
	}
	
	public RetrieveFeedbackQuestion(String source, Object crudFf){
		this.source = source;
		this.crudFf = crudFf;
	}
	
	public RetrieveFeedbackQuestion(){
		
	}
	
	public RetrieveFeedbackQuestion(DefaultTableModel viewModel){
		this.viewModel = viewModel;
		this.source = "Retrieve Feedback Question";
	}
	
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */

}
