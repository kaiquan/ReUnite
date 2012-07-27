package View.MM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.*;
import Model.Membership.RI;
import Controller.MySQLController;
import Controller.MM.*;

public class ViewRIDetailsRIVIEW {
	
	ViewRIPersonalController viewRIPersonalController = new	ViewRIPersonalController();

	private JTable table;
	private JFrame jframe;  //  @jve:decl-index=0:visual-constraint="10,54"
	private JPanel panel;
	
	
	
	//Labels

	private JLabel title;
	
	
	//TextBoxes
	JTextField userNameTextBox;
	JTextField firstNameTextBox;
	JTextField lastNameTextBox;
	JTextField dateOfBirthTextBox;
	JTextField nricTextBox;
	JTextField schoolTextBox;
	JTextField emailTextBox;
	JTextField telephoneTextBox;
	JTextField handphoneTextBox;
	private  JTextField typeTextBox;
	private  JTextField statusTextBox;
	

	//Buttons
	private JButton  updateAccountButton;
	private JButton requestCloseAccountButton;
	private JButton submitButton;
	
 	
	
	// ***********************JFrame Method****************
	
	private JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(782, 586);
		jframe.setVisible(true);
		jframe.setTitle("View");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setContentPane(getPanel());
		
		
		return jframe;
		
	}
	
	// ***********************JPanel Method****************	
	private JPanel getPanel(){
	panel = new JPanel();	
	panel.setLayout(null);
	panel.setFont(new Font("Dialog", Font.PLAIN, 14));
	
	
	
	//Labels
	 
	title = new JLabel();
	title.setBounds(new Rectangle(10, 10, 300, 100));
	title.setFont(new Font("Dialog", Font.BOLD, 30));
	title.setText("Welcome");
	
//	title.setText("Welcome"+viewRIPersonalController.userName.getText()+"");
	
	
	
	
	
	
	//TextBox
	userNameTextBox =new JTextField();
	userNameTextBox.setBounds(new Rectangle(130, 250, 150, 25));
	userNameTextBox.setVisible(false);
	
	
	firstNameTextBox=new JTextField();
	firstNameTextBox.setBounds(new Rectangle(130, 300, 150, 25));
	firstNameTextBox.setVisible(false);


	lastNameTextBox=new JTextField();
	lastNameTextBox.setBounds(new Rectangle(130, 350, 150, 25));
	lastNameTextBox.setVisible(false);
	
	
	dateOfBirthTextBox=new JTextField();
	dateOfBirthTextBox.setBounds(new Rectangle(130, 400, 150, 25));
	dateOfBirthTextBox.setVisible(false);
	
	nricTextBox=new JTextField();
	nricTextBox.setBounds(new Rectangle(130, 450, 150, 25));
	nricTextBox.setVisible(false);
	
	schoolTextBox=new JTextField();
	schoolTextBox.setBounds(new Rectangle(510, 250, 150, 25));
	schoolTextBox.setVisible(false);
	
	emailTextBox=new JTextField();
	emailTextBox.setBounds(new Rectangle(510, 300, 150, 25));
	emailTextBox.setVisible(false);
	
	telephoneTextBox=new JTextField();
	telephoneTextBox.setBounds(new Rectangle(510, 350, 150, 25));
	telephoneTextBox.setVisible(false);
	
	handphoneTextBox=new JTextField();
	handphoneTextBox.setBounds(new Rectangle(510, 400, 150, 25));
	handphoneTextBox.setVisible(false);
	
	typeTextBox=new JTextField();
	typeTextBox.setBounds(new Rectangle(600, 550, 150, 25));
	typeTextBox.setEditable(false);
	typeTextBox.setVisible(false);
	
	statusTextBox=new JTextField();
	statusTextBox.setBounds(new Rectangle(600, 600, 150, 25));
	statusTextBox.setEditable(false);
	statusTextBox.setVisible(false);
	
	
	
	//Buttons
	
	updateAccountButton = new JButton();
	updateAccountButton.setBounds(500, 50,150,30);
	updateAccountButton.setText("Update Account");
	updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			updateAccountButton.setVisible(false);
			submitButton.setVisible(true);
		
			userNameTextBox.setVisible(true);
			firstNameTextBox.setVisible(true);
			lastNameTextBox.setVisible(true);
			dateOfBirthTextBox.setVisible(true);
			nricTextBox.setVisible(true);
			schoolTextBox.setVisible(true);
			emailTextBox.setVisible(true);
			telephoneTextBox.setVisible(true);
			handphoneTextBox.setVisible(true);
			
			
			
		}
	});
	

	submitButton = new JButton("Submit");
	submitButton.setBounds(500, 50,150,30);
	submitButton.setVisible(false);
	submitButton.addActionListener(new java.awt.event.ActionListener() {
		
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Object[] options = { "OK", "CANCEL" };
			int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure You Want to UPDATE RI DATA?", "Please Confirm",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (confirmUpdateOption==0){
				UpdateRIController updateController = new UpdateRIController();
			
				updateController.updateRIAccount
				
		(userNameTextBox.getText(),
			
			firstNameTextBox.getText(),
			lastNameTextBox.getText(),
			//parseDate(date()), 
			nricTextBox.getText(),
			schoolTextBox.getText(),
			emailTextBox.getText(),
			telephoneTextBox.getText(),
			handphoneTextBox.getText()); 
				
				
				updateAccountButton.setVisible(true);
				submitButton.setVisible(false);
			
				userNameTextBox.setVisible(false);
				firstNameTextBox.setVisible(false);
				lastNameTextBox.setVisible(false);
				dateOfBirthTextBox.setVisible(false);
				nricTextBox.setVisible(false);
				schoolTextBox.setVisible(false);
				emailTextBox.setVisible(false);
				telephoneTextBox.setVisible(false);
				handphoneTextBox.setVisible(false);

				
			}
		}
	});
	
	requestCloseAccountButton = new JButton();
	requestCloseAccountButton.setBounds(515, 463, 150, 30);
	requestCloseAccountButton.setText("Request Account Closure");
	requestCloseAccountButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Object[] options = { "OK", "CANCEL" };
					int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure you want to delete your account?", "Request Account Closure",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if (confirmUpdateOption==0){
						

						JOptionPane.showConfirmDialog(null,"Your request will be sent for verification",
								   "Request Sent", JOptionPane.CLOSED_OPTION);
						
					}
				}
			});
	
	
	
	
	
	// Content Pane
	
	//Table
	
	
	
	
//..............................add.......................................
	
	panel.add(userNameTextBox);
	panel.add(firstNameTextBox);
	panel.add(lastNameTextBox);
	panel.add(dateOfBirthTextBox);
	panel.add(nricTextBox);
	panel.add(schoolTextBox);
	panel.add(emailTextBox);
	panel.add(telephoneTextBox);
	panel.add(handphoneTextBox);
	panel.add(typeTextBox);
	panel.add(statusTextBox);
	
	
	
		panel.add(title);
		
		panel.add(requestCloseAccountButton);
		
		panel.add(updateAccountButton);

		panel.add(submitButton);

		JScrollPane tableScrollPane = new JScrollPane(getTable());
		tableScrollPane.setBounds(0, 100, 600, 100);
		panel.add(tableScrollPane);
	

		return panel;
	}
	
	
	
	public JTable getTable() {

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Type your database query or controller here
			}
		});
		
		table = new JTable();
	
		table.setBackground(Color.white);
		table.setBorder(null);
		table.setModel(viewRIPersonalController.getRITableModel1());
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getSource() == table.getSelectionModel()
			              && table.getRowSelectionAllowed()) {
			            // Column selection changed
					ListSelectionModel model = table.getSelectionModel();  
		            int lead = model.getLeadSelectionIndex();  
		            shiftData(lead);  

			        }

			        if (e.getValueIsAdjusting()) {
			            // The mouse button has not yet been released
			        }
			}
		});
		

	
		
		
		
		return table;

	}
		

	public void shiftData(int row) {
		//add Date of birth here		
JTextField[] textBoxes = {userNameTextBox,  typeTextBox, statusTextBox,firstNameTextBox, lastNameTextBox,  nricTextBox, schoolTextBox, emailTextBox, telephoneTextBox, handphoneTextBox};
int columns = table.getColumnCount();  



for(int col = 0; col < columns; col++)  
{   textBoxes[col].setText(table.getValueAt(row, col).toString());

}

}  
	
	
// .......................................JTable.........................	
	

	// update Button

		public static void main(String a[]){
		
	
		
		ViewRIDetailsRIVIEW viewRiDetails = new ViewRIDetailsRIVIEW();
		viewRiDetails.getJFrame().setVisible(true);
		
			
		//new InsertJTableDatabase();
	
	}

}
