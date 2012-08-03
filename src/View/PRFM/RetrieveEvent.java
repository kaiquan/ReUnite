package View.PRFM;

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
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import Controller.PRFM.*;
import Model.*;
import Model.PRFM.*;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JRadioButton;

public class RetrieveEvent {

	//  @jve:decl-index=0:
	private ButtonGroup bg = new ButtonGroup();
	private String source, selection = "Add";
	private Object crudFf;
	private EventTableButtonMouseListener etbml;
	private final DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel viewModel = null;
	private int colSize = 5;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="157,8"
	private JPanel jContentPane = null;
	private JLabel filterLabel = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JComboBox eventIDComboBox = null;
	private JComboBox dateComboBox = null;
	private JComboBox timeComboBox = null;
	private JButton goButton = null;
	private JComboBox nameComboBox = null;
	private JComboBox statusComboBox = null;
	private JRadioButton viewRadioButton = null;
	private JRadioButton addRadioButton = null;
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
			bg.add(getViewRadioButton());
			bg.add(getAddRadioButton());
			filterLabel = new JLabel();
			filterLabel.setBounds(new Rectangle(25, 20, 80, 15));
			filterLabel.setText("Filter by: ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(filterLabel, null);
			jContentPane.add(getJTable(), null);
			jContentPane.add(getEventIDComboBox(), null);
			jContentPane.add(getDateComboBox(), null);
			jContentPane.add(getTimeComboBox(), null);
			jContentPane.add(getGoButton(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getNameComboBox(), null);
			jContentPane.add(getStatusComboBox(), null);
			jContentPane.add(getViewRadioButton(), null);
			jContentPane.add(getAddRadioButton(), null);
		}
		return jContentPane;
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
			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			int colWidth = 100;
	        Object[] colNames = {"Event ID", "Event Date", "Event Time", "Event Name", "Status"};
	        Object[][] data = new Object[event.getArray_EventID().size()][colSize];

	        for (int i = 0; i < event.getArray_EventID().size(); i++){
        		data[i][0] = event.getArray_EventID().get(i);
        		data[i][1] = controller.calendarToString(event.getArray_EventDate().get(i));
        		data[i][2] = event.getArray_EventTime().get(i);
        		data[i][3] = event.getArray_EventName().get(i);
        		data[i][4] = event.getArray_EventStatus().get(i);
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
	    	
	    	jTable.getColumnModel().getColumn(3).setPreferredWidth(250);
	    	jTable.getColumnModel().getColumn(4).setPreferredWidth(172);
	    	
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
	    	
	    	etbml = new EventTableButtonMouseListener(jTable, source, crudFf);
	    	etbml.setSelection(selection);
	    	jTable.addMouseListener(etbml);
	}
		return jTable;
	}
	
	public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) {
	    Vector data = model.getDataVector();
	    Collections.sort(data, new ColumnSorter(colIndex, ascending));
	    model.fireTableStructureChanged();
	}

	/**
	 * This method initializes eventIDComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEventIDComboBox() {
		if (eventIDComboBox == null) {
			eventIDComboBox = new JComboBox();
			eventIDComboBox.setBounds(new Rectangle(120, 18, 70, 25));
			eventIDComboBox.addItem("Event ID");

			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			Collections.sort(event.getArray_EventID());
			
			for (int i = 0; i < event.getArray_EventID().size(); i++){
				eventIDComboBox.addItem(event.getArray_EventID().get(i));
			}
		}
		return eventIDComboBox;
	}

	/**
	 * This method initializes dateComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getDateComboBox() {
		if (dateComboBox == null) {
			dateComboBox = new JComboBox();
			dateComboBox.setBounds(new Rectangle(210, 18, 110, 25));
			dateComboBox.addItem("Event Date");
			
			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			ArrayList<String> dateArr = new ArrayList<String>();
			
			for (int x = 0; x < event.getArray_EventDate().size(); x++){
				dateArr.add(controller.calendarToString(event.getArray_EventDate().get(x)));
			}
			
			Collections.sort(dateArr);
			
			for (int a = 0; a < dateArr.size(); a++){
				String first = dateArr.get(a);
				for (int b = a + 1; b < dateArr.size(); b++){
					String second = dateArr.get(b);
					
					if (first.equals(second)){
						dateArr.remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < dateArr.size(); i++){
				dateComboBox.addItem(dateArr.get(i));
			}
		}
		return dateComboBox;
	}

	/**
	 * This method initializes timeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTimeComboBox() {
		if (timeComboBox == null) {
			timeComboBox = new JComboBox();
			timeComboBox.setBounds(new Rectangle(340, 18, 100, 25));
			timeComboBox.addItem("Event Time");

			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			Collections.sort(event.getArray_EventTime());

			for (int a = 0; a < event.getArray_EventTime().size(); a++){
				String first = event.getArray_EventTime().get(a);
				
				for (int b = a + 1; b < event.getArray_EventTime().size(); b++){
					String second = event.getArray_EventTime().get(b);
					
					if (first.equals(second)){
						event.getArray_EventTime().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < event.getArray_EventTime().size(); i++){
				timeComboBox.addItem(event.getArray_EventTime().get(i));
			}
		}
		return timeComboBox;
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
					int eventID = 0;
					GregorianCalendar date = null;
					String time = null, name = null, status = null;
					
					if (getEventIDComboBox().getSelectedIndex() != 0){
						eventID = (Integer) getEventIDComboBox().getSelectedItem();
					}
					
					if (getDateComboBox().getSelectedIndex() != 0){
						date  = (GregorianCalendar) getDateComboBox().getSelectedItem();
					}
					
					if (getTimeComboBox().getSelectedIndex() != 0){
						time = (String) getTimeComboBox().getSelectedItem();
					}
					
					if (getNameComboBox().getSelectedIndex() != 0){
						name = (String) getNameComboBox().getSelectedItem();
					}
					
					if (getStatusComboBox().getSelectedIndex() != 0){
						status = (String) getStatusComboBox().getSelectedItem();
					}
					
					Event event = new Event();
					AdministrateEventController controller = new AdministrateEventController();
					event = controller.processSearchTerm(eventID, date, time, name, status);
					
					if (event == null){
						getJTable().setModel(model);
					}
					else{
				        Object[] colNames = {"Event ID", "Event Date", "Event Time", "Event Name", "Status"};
				        Object[][] data = new Object[event.getArray_EventID().size()][colSize];

				        for (int i = 0; i < event.getArray_EventID().size(); i++){
			        		data[i][0] = event.getArray_EventID().get(i);
			        		data[i][1] = controller.calendarToString(event.getArray_EventDate().get(i));
			        		data[i][2] = event.getArray_EventTime().get(i);
			        		data[i][3] = event.getArray_EventName().get(i);
			        		data[i][4] = event.getArray_EventStatus().get(i);
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

	/**
	 * This method initializes nameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getNameComboBox() {
		if (nameComboBox == null) {
			nameComboBox = new JComboBox();
			nameComboBox.addItem("Event Name");
			nameComboBox.setBounds(new Rectangle(460, 18, 120, 25));
			
			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			Collections.sort(event.getArray_EventName());
			
			for (int i = 0; i < event.getArray_EventName().size(); i++){
				nameComboBox.addItem(event.getArray_EventName().get(i));
			}
		}
			
		return nameComboBox;
	}

	/**
	 * This method initializes statusComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getStatusComboBox() {
		if (statusComboBox == null) {
			statusComboBox = new JComboBox();
			statusComboBox.addItem("Status");
			statusComboBox.setBounds(new Rectangle(600, 18, 100, 25));
			
			Event event = new Event();
			AdministrateEventController controller = new AdministrateEventController();
			event = controller.processRetrieve();
			
			Collections.sort(event.getArray_EventStatus());

			for (int a = 0; a < event.getArray_EventStatus().size(); a++){
				String first = event.getArray_EventStatus().get(a);
				
				for (int b = a + 1; b < event.getArray_EventStatus().size(); b++){
					String second = event.getArray_EventStatus().get(b);
					
					if (first.equals(second)){
						event.getArray_EventStatus().remove(b);
						b -= 1;
					}
				}
			}
			
			for (int i = 0; i < event.getArray_EventStatus().size(); i++){
				statusComboBox.addItem(event.getArray_EventStatus().get(i));
			}
		}
		return statusComboBox;
	}

	/**
	 * This method initializes viewRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getViewRadioButton() {
		if (viewRadioButton == null) {
			viewRadioButton = new JRadioButton();
			
			if (source.equals("Select Event to create Feedback Form")){
				viewRadioButton.setBounds(new Rectangle(25, 560, 150, 20));
				viewRadioButton.setText("View details on click");
			}
			
			viewRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "View";
					etbml.setSelection(selection);
				}
			});
		}
		return viewRadioButton;
	}

	/**
	 * This method initializes addRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getAddRadioButton() {
		if (addRadioButton == null) {
			addRadioButton = new JRadioButton();

			addRadioButton.setSelected(true);
			
			if (source.equals("Select Event to create Feedback Form")){
				addRadioButton.setBounds(new Rectangle(195, 560, 200, 20));
				addRadioButton.setText("Add to feedback form on click");
			}
			
			addRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selection = "Add";
					etbml.setSelection(selection);
				}
			});
		}
		return addRadioButton;
	}


	public static void main(String[] args){
		RetrieveEvent retrieve = new RetrieveEvent();
		retrieve.getJFrame().setVisible(true);
	}
	
	public RetrieveEvent(){

	}
	
	public RetrieveEvent(String source, Object crudFf){
		this.source = source;
		this.crudFf = crudFf;
	}
	
	public RetrieveEvent(DefaultTableModel viewModel){
		this.viewModel = viewModel;
		this.source = "Retrieve Event";
	}
}
