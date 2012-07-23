package View.MM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import Model.*;
import Controller.MySQLController;
import Controller.MM.*;

;

public class ViewRIDetails {

	ViewRIDetailsController viewRIDetailsController = new ViewRIDetailsController();
	
	public JTable table;
	private JFrame jframe;
	private JPanel panel;
	private JPanel panel2;
	
	
	//TextBoxes
	private JTextField userNameTextBox;
	private  JTextField	firstNameTextBox;
	private  JTextField	lastNameTextBox;
	private  JTextField	dateOfBirthTextBox;
	private  JTextField	nricTextBox;
	private  JTextField	schoolTextBox;
	private  JTextField	emailTextBox;
	private  JTextField	telephoneTextBox;
	private  JTextField handphoneTextBox;
	private  JTextField typeTextBox;
	private  JTextField statusTextBox;

	
	
	
	//Labels
	private JLabel nameLabel ;
	private JLabel icLabel;
	private JLabel school;
	private JLabel statusLabel;
	private JLabel statusLabel1;
	private JLabel title;
	private JLabel userNameLabel;
	private  JLabel	firstNameLabel;
	private  JLabel	lastNameLabel;
	private  JLabel	dateOfBirthLabel;
	private  JLabel	nricLabel;
	private  JLabel	schoolLabel;
	private  JLabel	emailLabel;
	private  JLabel	telephoneLabel;
	private  JLabel handphoneLabel;
	private JLabel typeLabel;
	
	
	
	
	
	
	//Buttons
	private JButton  deleteAccountButton;
	private JButton	 submitButton;	
	private JButton  updateAccountButton;
	private JButton  disableAccountButton;
	private JButton  createAccountButton;
 	
	
	// ***********************JFrame Method****************
	
	private JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(1000,700);
		jframe.setVisible(true);
		jframe.setTitle("GR View Of RI");
		
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
	title.setText("RI ACCOUNTS");
	
	
	
	
	statusLabel = new JLabel();
	statusLabel.setBounds(new Rectangle(500, 600, 400, 30));
	statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	statusLabel.setText("Account Status :");
	
	statusLabel1 = new JLabel();
	statusLabel1.setBounds(new Rectangle(250, 450, 400, 30));
	statusLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
	//color code (active / inactive)
	//statusLabel1.setText("Active / Inactive");

	userNameLabel = new JLabel();
	userNameLabel.setBounds(new Rectangle(100, 350, 400, 30));
	userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	userNameLabel.setText("UserName :");
	
	firstNameLabel = new JLabel();
	firstNameLabel.setBounds(new Rectangle(100, 400, 400, 30));
	firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	firstNameLabel.setText("First Name :");
	
	lastNameLabel = new JLabel();
	lastNameLabel.setBounds(new Rectangle(100, 450, 400, 30));
	lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	lastNameLabel.setText("Last Name :");
	
	
	
	dateOfBirthLabel = new JLabel();
	dateOfBirthLabel.setBounds(new Rectangle(100, 500, 400, 30));
	dateOfBirthLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	dateOfBirthLabel.setText("Date of Birth :");
	
	nricLabel = new JLabel();
	nricLabel.setBounds(new Rectangle(100, 550, 400, 30));
	nricLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	nricLabel.setText("Nric :");
	
	schoolLabel = new JLabel();
	schoolLabel.setBounds(new Rectangle(500, 350, 400, 30));
	schoolLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	schoolLabel.setText("School :");
	
	emailLabel = new JLabel();
	emailLabel.setBounds(new Rectangle(500, 400, 400, 30));
	emailLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	emailLabel.setText("Email :");
	
	telephoneLabel = new JLabel();
	telephoneLabel.setBounds(new Rectangle(500, 450, 400, 30));
	telephoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	telephoneLabel.setText("Telephone :");
	
	handphoneLabel = new JLabel();
	handphoneLabel.setBounds(new Rectangle(500, 500, 400, 30));
	handphoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	handphoneLabel.setText("Handphone :");
	
	typeLabel = new JLabel();
	typeLabel.setBounds(new Rectangle(500, 550, 400, 30));
	typeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	typeLabel.setText("Account Type :");
	
	//TextBox
	userNameTextBox =new JTextField();
	userNameTextBox.setBounds(new Rectangle(200, 350, 150, 25));
	
	
	firstNameTextBox=new JTextField();
	firstNameTextBox.setBounds(new Rectangle(200, 400, 150, 25));
	
	
	lastNameTextBox=new JTextField();
	lastNameTextBox.setBounds(new Rectangle(200, 450, 150, 25));
	
	dateOfBirthTextBox=new JTextField();
	dateOfBirthTextBox.setBounds(new Rectangle(200, 500, 150, 25));
	
	nricTextBox=new JTextField();
	nricTextBox.setBounds(new Rectangle(200, 550, 150, 25));
	
	schoolTextBox=new JTextField();
	schoolTextBox.setBounds(new Rectangle(600, 350, 150, 25));
	
	emailTextBox=new JTextField();
	emailTextBox.setBounds(new Rectangle(600, 400, 150, 25));
	
	telephoneTextBox=new JTextField();
	telephoneTextBox.setBounds(new Rectangle(600, 450, 150, 25));
	
	handphoneTextBox=new JTextField();
	handphoneTextBox.setBounds(new Rectangle(600, 500, 150, 25));
	
	typeTextBox=new JTextField();
	typeTextBox.setBounds(new Rectangle(600, 550, 150, 25));
	
	statusTextBox=new JTextField();
	statusTextBox.setBounds(new Rectangle(600, 600, 150, 25));
	statusTextBox.setEditable(false);
	
	
	
	
	//Buttons
	
	deleteAccountButton = new JButton();
	deleteAccountButton.setBounds(800,350,150,30);
	deleteAccountButton.setText("Delete Account");
	
	updateAccountButton = new JButton();
	updateAccountButton.setBounds(800,450,150,30);
	updateAccountButton.setText("Update Account");
	
	disableAccountButton = new JButton();
	disableAccountButton.setBounds(800,550,150,30);
	disableAccountButton.setText("Disable Account");
	
	createAccountButton = new JButton();
	createAccountButton.setBounds(800,70,150,30);
	createAccountButton.setText("Create Account");
	
	submitButton  = new JButton();
	submitButton.setBounds(700, 110,700,30);
	submitButton.setText("submit");
	
	
	// Content Pane
	
	//Table
	
//..............................add.......................................
	
	//add label
	panel.add(title);
	
	
//	panel.add(statusLabel1);
//	
	panel.add(userNameLabel);
	panel.add(firstNameLabel);
	panel.add( lastNameLabel);
	panel.add(dateOfBirthLabel);
	panel.add(nricLabel);
	panel.add(schoolLabel);
	panel.add(emailLabel);
	panel.add(telephoneLabel);
	panel.add(handphoneLabel);
	panel.add(typeLabel);
	panel.add(statusLabel);
	
	
	//add textBox
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
	
	//add Button
	panel.add(deleteAccountButton);
	//panel.add(submitButton);
	panel.add(updateAccountButton);
	panel.add(disableAccountButton);
	panel.add(createAccountButton);
	
	
	
	JScrollPane tableScrollPane = new JScrollPane(getTable());
	tableScrollPane.setBounds(0,100,975,200);
	panel.add(tableScrollPane);
	
	
		return panel;
	}
	
	
	private JPanel getPanel2(){
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(200, 100);
		
		return panel2; 
		}
	
	
// .......................................JTable.........................	

	public JTable getTable() {

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Type your database query or controller here
			}
		});
		table = new JTable();
	
		table.setBackground(Color.white);
		table.setBorder(null);
		table.setModel(viewRIDetailsController.getRITableModel());
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));
		
		
		//Add row action listener
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
			JTextField[] textBoxes = {userNameTextBox, typeTextBox, statusTextBox, firstNameTextBox, lastNameTextBox,  nricTextBox, schoolTextBox, emailTextBox, telephoneTextBox, handphoneTextBox};
	        int columns = table.getColumnCount();  
	        for(int col = 0; col < columns; col++)  
	        {  
	            textBoxes[col].setText(table.getValueAt(row, col).toString());
	        }  
	     
	  }  
	
	
	
	public static void main(String a[]){
		
		
		
		
		ViewRIDetails viewRiDetails = new ViewRIDetails();
		viewRiDetails.getJFrame().setVisible(true);
		

		
	
	}
	
	

}
//String addFirstName=rs.getString("firstName");
//userNameTextBox.setText(addFirstName);

