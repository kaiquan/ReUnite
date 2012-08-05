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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import Controller.PRFM.AdministrateFeedbackFormController;
import Controller.PRFM.AdministrateFeedbackQuestionController;
import Model.PRFM.EventForm;
import Model.PRFM.FeedbackForm;
import Model.PRFM.FeedbackQuestion;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JRadioButton;

@SuppressWarnings("unused")
public class RetrieveFeedbackForm {

	//  @jve:decl-index=0:
	private String source = null, selection = "Result";
	private final DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel viewModel = null;
	private FormTableButtonMouseListener ftbml;
	private int colSize = 4;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="157,8"
	private JPanel jContentPane = null;
	private JLabel filterLabel = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	@SuppressWarnings("unchecked")
	private JComboBox codeComboBox = null;
	@SuppressWarnings("unchecked")
	private JComboBox creationDateComboBox = null;
	@SuppressWarnings("unchecked")
	private JComboBox fqCodeComboBox = null;
	private JButton goButton = null;
	private JButton button = null;
	@SuppressWarnings("unchecked")
	private JComboBox eventIDComboBox = null;
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
			jFrame.setSize(new Dimension(700, 650));
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
			bg.add(getDetailsRadioButton());
			bg.add(getResultRadioButton());
			filterLabel = new JLabel();
			filterLabel.setBounds(new Rectangle(25, 20, 80, 15));
			filterLabel.setText("Filter by: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(filterLabel, null);
			jContentPane.add(getJTable(), null);
			jContentPane.add(getCodeComboBox(), null);
			jContentPane.add(getCreationDateComboBox(), null);
			jContentPane.add(getFqCodeComboBox(), null);
			jContentPane.add(getGoButton(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getButton(), null);
			jContentPane.add(getEventIDComboBox(), null);
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


	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(25, 90, 430, 450));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}
	
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			FeedbackForm ff = new FeedbackForm();
			AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			ff = controller.processRetrieve();
			
			for (int a = 0; a < ff.getCode().size(); a++){
				int first = ff.getCode().get(a);
				for (int b = a + 1; b < ff.getCode().size(); b++){
					int second = ff.getCode().get(b);
					
					if (first == second){
						ff.getCode().remove(b);
						ff.getCreationDate().remove(b);
						ff.getFqCode().remove(b);
						ff.getFqOrder().remove(b);
						b -= 1;
					}
				}
			}
			
			int colWidth = 100;
	        Object[] colNames = {"Code", "Creation Date", "# of Questions", "# of Events"};
	        Object[][] data = new Object[ff.getCode().size()][colSize];

	        for (int i = 0; i < ff.getCode().size(); i++){
        		data[i][0] = ff.getCode().get(i);
        		data[i][1] = ff.getCreationDate().get(i);
        		data[i][2] = controller.processSearchTerm(ff.getCode().get(i), null, 0).getCode().size();
        		data[i][3] = controller.processEventFormSearchTerm(ff.getCode().get(i), 0).getEventID().size();
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
	    	
	    	ftbml = new FormTableButtonMouseListener(jTable, source);
	    	ftbml.setSelection(selection);
	    	jTable.addMouseListener(ftbml);
	   
	}
		return jTable;
	}
	
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
	private JComboBox getCodeComboBox() {
		if (codeComboBox == null) {
			codeComboBox = new JComboBox();
			codeComboBox.setBounds(new Rectangle(120, 18, 70, 25));
			codeComboBox.addItem("Code");

			FeedbackForm ff = new FeedbackForm();
			AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			ff = controller.processRetrieve();
			
			Collections.sort(ff.getCode());
			
			for (int a = 0; a < ff.getCode().size(); a++){
				int first = ff.getCode().get(a);
				for (int b = a + 1; b < ff.getCode().size(); b++){
					int second = ff.getCode().get(b);
					
					if (first == second){
						ff.getCode().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < ff.getCode().size(); i++){
				codeComboBox.addItem(ff.getCode().get(i));
			}
		}
		return codeComboBox;
	}

	/**
	 * This method initializes creationDateComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCreationDateComboBox() {
		if (creationDateComboBox == null) {
			creationDateComboBox = new JComboBox();
			creationDateComboBox.setBounds(new Rectangle(210, 18, 110, 25));
			creationDateComboBox.addItem("Creation Date");
			
			FeedbackForm ff = new FeedbackForm();
			AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			ff = controller.processRetrieve();
			
			Collections.sort(ff.getCreationDate());
			
			for (int a = 0; a < ff.getCreationDate().size(); a++){
				Date first = ff.getCreationDate().get(a);
				for (int b = a + 1; b < ff.getCreationDate().size(); b++){
					Date second = ff.getCreationDate().get(b);
					
					if (first.equals(second)){
						ff.getCreationDate().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < ff.getCreationDate().size(); i++){
				creationDateComboBox.addItem(ff.getCreationDate().get(i));
			}
		}
		return creationDateComboBox;
	}
	
	private JComboBox getFqCodeComboBox() {
		if (fqCodeComboBox == null) {
			fqCodeComboBox = new JComboBox();
			fqCodeComboBox.setBounds(new Rectangle(340, 18, 100, 25));
			fqCodeComboBox.addItem("FQ Code");

			FeedbackForm ff = new FeedbackForm();
			AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			ff = controller.processRetrieve();
			
			Collections.sort(ff.getFqCode());
			
			for (int a = 0; a < ff.getFqCode().size(); a++){
				int first = ff.getFqCode().get(a);
				for (int b = a + 1; b < ff.getFqCode().size(); b++){
					int second = ff.getFqCode().get(b);
					
					if (first == second){
						ff.getFqCode().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < ff.getFqCode().size(); i++){
				fqCodeComboBox.addItem(ff.getFqCode().get(i));
			}
		}
		return fqCodeComboBox;
	}
	/**
	 * This method initializes goButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoButton() {
		if (goButton == null) {
			goButton = new JButton();
			goButton.setBounds(new Rectangle(560, 20, 50, 20));
			goButton.setText("Go");
			goButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int code = 0, fqCode = 0, eventID = 0;
					Date creationDate = null, startDate = null, endDate = null;
					
					if (getCodeComboBox().getSelectedIndex() != 0){
						code = (Integer) getCodeComboBox().getSelectedItem();
					}
					
					if (getCreationDateComboBox().getSelectedIndex() != 0){
						creationDate = (Date) getCreationDateComboBox().getSelectedItem();
					}
					
					if (getFqCodeComboBox().getSelectedIndex() != 0){
						fqCode = (Integer) getFqCodeComboBox().getSelectedItem();
					}
					
					if (getEventIDComboBox().getSelectedIndex() != 0){
						eventID = (Integer) getEventIDComboBox().getSelectedItem();
					}
								
					FeedbackForm ff = new FeedbackForm();
					FeedbackForm newFf = new FeedbackForm();
					AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
					ff = controller.processSearchTerm(code, creationDate, fqCode);
					EventForm ef = new EventForm();
					ef = controller.processEventFormSearchTerm(code, eventID);
					
					if (ff != null){
						for (int a = 0; a < ff.getCode().size(); a++){
							int first = ff.getCode().get(a);
							for (int b = a + 1; b < ff.getCode().size(); b++){
								int second = ff.getCode().get(b);
								
								if (first == second){
									ff.getCode().remove(b);
									ff.getCreationDate().remove(b);
									ff.getFqCode().remove(b);
									ff.getFqOrder().remove(b);
									b -= 1;
								}
							}
						}
						
						for (int i = 0; i < ff.getCode().size(); i++){
							newFf.setCode(ff.getCode().get(i));
							newFf.setCreationDate(ff.getCreationDate().get(i));
							newFf.setFqCode(ff.getFqCode().get(i));
							newFf.setFqOrder(ff.getFqOrder().get(i));
						}
						
						if (ef != null){
							if (!ef.getCode().isEmpty()){
								for (int x = 0; x < newFf.getCode().size(); x++){
									if (newFf.getCode().get(x) != ef.getCode().get(0)){
										newFf.getCode().remove(x);
										newFf.getCreationDate().remove(x);
										newFf.getFqCode().remove(x);
										newFf.getFqOrder().remove(x);
									}
								}
							}
							else{
								newFf = new FeedbackForm();
							}
						}
					}
					else{
						if (ef != null && !ef.getCode().isEmpty()){
							newFf = controller.processSearchTerm(ef.getCode().get(0), null, 0);
							
							for (int a = 0; a < newFf.getCode().size(); a++){
								int first = newFf.getCode().get(a);
								for (int b = a + 1; b < newFf.getCode().size(); b++){
									int second = newFf.getCode().get(b);
									
									if (first == second){
										newFf.getCode().remove(b);
										newFf.getCreationDate().remove(b);
										newFf.getFqCode().remove(b);
										newFf.getFqOrder().remove(b);
										b -= 1;
									}
								}
							}
						}
					}
					
						
					
					if (ff == null && ef == null){
						getJTable().setModel(model);
					}
					else{
				        Object[] colNames = {"Code", "Creation Date", "# of Questions", "# of Events"};
				        Object[][] data = new Object[newFf.getCode().size()][colSize];

				        for (int i = 0; i < newFf.getCode().size(); i++){
			        		data[i][0] = newFf.getCode().get(i);
			        		data[i][1] = newFf.getCreationDate().get(i);
			        		data[i][2] = controller.processSearchTerm(newFf.getCode().get(i), null, 0).getCode().size();
			        		data[i][3] = controller.processEventFormSearchTerm(newFf.getCode().get(i), 0).getEventID().size();
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
			
			if (source.equals("Create Feedback Form")){
				button.setBounds(new Rectangle(25, 560, 250, 20));
				button.setText(source);
			}
			
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (source.equals("Create Feedback Form")){
						CreateFeedbackForm createFf = new CreateFeedbackForm();
						createFf.getJFrame().setVisible(true);
						RetrieveFeedbackQuestion retrieveFq = new RetrieveFeedbackQuestion("Select Feedback Question to create Feedback Form", createFf);
						retrieveFq.getJFrame().setVisible(true);
						RetrieveEvent retrieveEvent = new RetrieveEvent("Select Event to create Feedback Form", createFf);
						retrieveEvent.getJFrame().setVisible(true);
					}
					
					source = null;
					getJFrame().dispose();
				}
			});
		}
		return button;
	}
	
	/**
	 * This method initializes eventIDComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEventIDComboBox() {
		if (eventIDComboBox == null) {
			eventIDComboBox = new JComboBox();
			eventIDComboBox.setBounds(new Rectangle(460, 18, 80, 25));
			eventIDComboBox.addItem("Event ID");
			
			EventForm ef = new EventForm();
			AdministrateFeedbackFormController controller = new AdministrateFeedbackFormController();
			ef = controller.processRetrieveEventForm();
			
			Collections.sort(ef.getEventID());
			
			for (int a = 0; a < ef.getEventID().size(); a++){
				int first = ef.getEventID().get(a);
				
				for (int b = a + 1; b < ef.getEventID().size(); b++){
					int second = ef.getEventID().get(b);
					
					if (first == second){
						ef.getEventID().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < ef.getEventID().size(); i++){
				eventIDComboBox.addItem(ef.getEventID().get(i));
			}
			
		}
		return eventIDComboBox;
	}

	/**
	 * This method initializes detailsRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getDetailsRadioButton() {
		if (detailsRadioButton == null) {
			detailsRadioButton = new JRadioButton();
			
			if (source.equals("Select Feedback Form to View Result")){
				detailsRadioButton.setBounds(new Rectangle(25, 560, 150, 20));
				detailsRadioButton.setText("View details on click");
			}
			
			detailsRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "Details";
					ftbml.setSelection(selection);
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
			
			if (source.equals("Select Feedback Form to View Result")){
				resultRadioButton.setBounds(new Rectangle(195, 560, 200, 20));
				resultRadioButton.setText("View result on click");
			}
			
			resultRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "Result";
					ftbml.setSelection(selection);
				}
			});
		}
		return resultRadioButton;
	}

	public static void main(String[] args){
		RetrieveFeedbackForm retrieve = new RetrieveFeedbackForm();
		retrieve.getJFrame().setVisible(true);
	}
	
	public String getSource(){
		return source;
	}
	
	public RetrieveFeedbackForm(String source){
		this.source = source;
	}
	
	public RetrieveFeedbackForm(){
		
	}
	
	public RetrieveFeedbackForm(DefaultTableModel viewModel){
		this.viewModel = viewModel;
		source = "Retrieve Feedback Form";
	}

}
