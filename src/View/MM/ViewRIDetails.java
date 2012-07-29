package View.MM;
import Images.MM.*;
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
import javax.swing.ImageIcon;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import Model.*;
import Model.Membership.RI;
import Controller.MySQLController;
import Controller.MM.*;
import java.awt.GridBagLayout;

;

public class ViewRIDetails {

	ViewRIDetailsController viewRIDetailsController = new ViewRIDetailsController();  //  @jve:decl-index=0:
	
	public JTable table;
	private JFrame jframe;
	private JPanel panel;
	private JPanel panel2;
	private JPanel wankingPanel ;
	private JPanel infoPanel;
	
	private DefaultTableModel tableModel = viewRIDetailsController.getRITableModel();
	
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
	private JButton confirmUpdateButton;

	private JButton refresh = null;

	
 	
	
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
	statusLabel.setBounds(new Rectangle(650, 300, 400, 30));
	statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	statusLabel.setText("Account Status :");
	
	statusLabel1 = new JLabel();
	statusLabel1.setBounds(new Rectangle(250, 450, 400, 30));
	statusLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
	//color code (active / inactive)
	//statusLabel1.setText("Active / Inactive");

	userNameLabel = new JLabel();
	userNameLabel.setBounds(new Rectangle(0, 50, 400, 30));
	userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	userNameLabel.setText("UserName :");
	
	firstNameLabel = new JLabel();
	firstNameLabel.setBounds(new Rectangle(0, 100, 400, 30));
	firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	firstNameLabel.setText("First Name :");
	
	lastNameLabel = new JLabel();
	lastNameLabel.setBounds(new Rectangle(0, 150, 400, 30));
	lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	lastNameLabel.setText("Last Name :");
	
	
	
	dateOfBirthLabel = new JLabel();
	dateOfBirthLabel.setBounds(new Rectangle(0, 200, 400, 30));
	dateOfBirthLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	dateOfBirthLabel.setText("Date of Birth :");
	
	nricLabel = new JLabel();
	nricLabel.setBounds(new Rectangle(0, 250, 400, 30));
	nricLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	nricLabel.setText("Nric :");
	
	schoolLabel = new JLabel();
	schoolLabel.setBounds(new Rectangle(400, 50, 400, 30));
	schoolLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	schoolLabel.setText("School :");
	
	emailLabel = new JLabel();
	emailLabel.setBounds(new Rectangle(400, 100, 400, 30));
	emailLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	emailLabel.setText("Email :");
	
	telephoneLabel = new JLabel();
	telephoneLabel.setBounds(new Rectangle(400, 150, 400, 30));
	telephoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	telephoneLabel.setText("Telephone :");
	
	handphoneLabel = new JLabel();
	handphoneLabel.setBounds(new Rectangle(400, 200, 400, 30));
	handphoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	handphoneLabel.setText("Handphone :");
	
	typeLabel = new JLabel();
	typeLabel.setBounds(new Rectangle(400, 250, 400, 30));
	typeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	typeLabel.setText("Account Type :");
	
	//TextBox
	userNameTextBox =new JTextField();
	userNameTextBox.setBounds(new Rectangle(100, 50, 150, 25));
	userNameTextBox.setEditable(false);
	
	
	firstNameTextBox=new JTextField();
	firstNameTextBox.setBounds(new Rectangle(100, 100, 150, 25));
	firstNameTextBox.setEditable(false);


	lastNameTextBox=new JTextField();
	lastNameTextBox.setBounds(new Rectangle(100, 150, 150, 25));
	lastNameTextBox.setEditable(false);
	
	
	dateOfBirthTextBox=new JTextField();
	dateOfBirthTextBox.setBounds(new Rectangle(100, 200, 150, 25));
	dateOfBirthTextBox.setEditable(false);
	
	nricTextBox=new JTextField();
	nricTextBox.setBounds(new Rectangle(100, 250, 150, 25));
	nricTextBox.setEditable(false);
	
	schoolTextBox=new JTextField();
	schoolTextBox.setBounds(new Rectangle(500, 50, 150, 25));
	schoolTextBox.setEditable(false);
	
	emailTextBox=new JTextField();
	emailTextBox.setBounds(new Rectangle(500, 100, 150, 25));
	emailTextBox.setEditable(false);
	
	telephoneTextBox=new JTextField();
	telephoneTextBox.setBounds(new Rectangle(500, 150, 150, 25));
	telephoneTextBox.setEditable(false);
	
	handphoneTextBox=new JTextField();
	handphoneTextBox.setBounds(new Rectangle(500, 200, 150, 25));
	handphoneTextBox.setEditable(false);
	
	typeTextBox=new JTextField();
	typeTextBox.setBounds(new Rectangle(500, 250, 150, 25));
	typeTextBox.setEditable(false);
	
	statusTextBox=new JTextField();
	statusTextBox.setBounds(new Rectangle(800, 300, 150, 25));
	statusTextBox.setEditable(false);
	
	
	
	
	
	//Buttons
	
	deleteAccountButton = new JButton();
	deleteAccountButton.setBounds(0, 102, 150, 30);
//	deleteAccountButton.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Trash-Black-Empty-icon.png")));
	deleteAccountButton.setText("Delete Account");
	
	
	
	updateAccountButton = new JButton();
	//updateAccountButton.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Wordpad-icon.png")));
	updateAccountButton.setBounds(0, 29, 150, 30);
	updateAccountButton.setText("Update Account");
	
	
	updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			updateAccount();
		}

		private void updateAccount() {
			Object[] options = { "OK", "CANCEL" };
			int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure You Want to UPDATE  " +userNameTextBox.getText() +"", "Please Confirm",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (confirmUpdateOption == 0){
				
				userNameTextBox.setEditable(true);
				firstNameTextBox.setEditable(true);
				lastNameTextBox.setEditable(true);
				dateOfBirthTextBox.setEditable(true);
				emailTextBox.setEditable(true);
				nricTextBox.setEditable(true);
				schoolTextBox.setEditable(true);
				telephoneTextBox.setEditable(true);
				handphoneTextBox.setEditable(true);
//				typeTextBox.setEditable(true);
//				statusTextBox.setEditable(true);
				
				updateAccountButton.setVisible(false);
				confirmUpdateButton.setVisible(true);
		


				
			}
			
			
			
		}
	});
	
	confirmUpdateButton = new JButton();
	confirmUpdateButton.setBounds(0, 29, 150, 30);
	confirmUpdateButton.setVisible(false);
	confirmUpdateButton.setText("Confirm Update");
	confirmUpdateButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			Object[] options = { "OK", "CANCEL" };
			int confirmUpdateOption = JOptionPane.showOptionDialog(null, "" +userNameTextBox.getText() +" is successfully updated! ", "Account Updated!",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (confirmUpdateOption == 0){
				
				if(userNameTextBox.getText().equals("")
						
						|| firstNameTextBox.getText().equals("")								
						|| lastNameTextBox.getText().equals("")
							|| nricTextBox.getText().equals("")
					|| schoolTextBox.getText().equals("")
							|| emailTextBox.getText().equals("")) {

				JOptionPane.showConfirmDialog(null,"Please Fill up all Field",
							   "Empty Field", JOptionPane.CLOSED_OPTION);
				}
				else{
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
				handphoneTextBox.getText()); //secretQuestion(),
					
					userNameTextBox.setText("");
					firstNameTextBox.setText("");
					lastNameTextBox.setText("");
					//parseDate(date()), 
					dateOfBirthTextBox.setText("");
					nricTextBox.setText("");
					schoolTextBox.setText("");
					emailTextBox.setText("");
					telephoneTextBox.setText("");
					handphoneTextBox.setText("");
					statusTextBox.setText("");
					typeTextBox.setText("");
					//secretQuestion(),
					
					userNameTextBox.setEditable(false);
					firstNameTextBox.setEditable(false);
					lastNameTextBox.setEditable(false);
					dateOfBirthTextBox.setEditable(false);
					emailTextBox.setEditable(false);
					nricTextBox.setEditable(false);
					schoolTextBox.setEditable(false);
					telephoneTextBox.setEditable(false);
					handphoneTextBox.setEditable(false);
					
					updateAccountButton.setVisible(true);
					confirmUpdateButton.setVisible(false);
					
					tableModel = viewRIDetailsController.getRITableModel();
					table.setModel(tableModel);
					table.updateUI();
					
					
			}}
			
	
		}
	});
	
	disableAccountButton = new JButton();
	disableAccountButton.setBounds(1, 190, 150, 30);
	disableAccountButton.setText("Disable Account");
	disableAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			
			if(disableAccountButton.getText()=="Disable Account"){
			
			Object[] options = { "OK", "CANCEL" };
			int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are sure you want to disable  "+userNameTextBox.getText()+"?", "Please Confirm",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (confirmUpdateOption == 0){
			
			statusTextBox.setText("Disable");
			UpdateRIController disableAccount = new UpdateRIController();
			disableAccount.disableRIAccount(userNameTextBox.getText(), statusTextBox.getText());
			}
			
			JOptionPane.showConfirmDialog(null,""+userNameTextBox.getText()+" Has Been Successfully disabled!",
					   "Disable Confirmed!", JOptionPane.CLOSED_OPTION);
			}
			
			
//			ViewRIDetailsController verifyDelete = new ViewRIDetailsController();
//			
//			if(verifyDelete.ableToDelete()==false){
//			
//			JOptionPane.showConfirmDialog(null,""+userNameTextBox.getText()+" You have an Outstanding Event",
//					   "Disable Confirmed!", JOptionPane.CLOSED_OPTION);
//			
//		}
//			
//		else{
//		
//			JOptionPane.showConfirmDialog(null,""+userNameTextBox.getText()+" Has Been Successfully DiSa l!",
//					   "Disable Confirmed!", JOptionPane.CLOSED_OPTION);
//		}
			
			if(disableAccountButton.getText()=="Enable Account"){
				Object[] options = { "OK", "CANCEL" };
				int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are Sure You want to Enable  "+userNameTextBox.getText()+"?", "Please Confirm",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				if (confirmUpdateOption == 0){
				
				statusTextBox.setText("Active");
				UpdateRIController disableAccount = new UpdateRIController();
				disableAccount.disableRIAccount(userNameTextBox.getText(), statusTextBox.getText());
				}
				
			
			
			
			
			tableModel = viewRIDetailsController.getRITableModel();
			table.setModel(tableModel);
			table.updateUI();
			
			userNameTextBox.setText("");
			firstNameTextBox.setText("");
			lastNameTextBox.setText("");
			//parseDate(date()), 
			dateOfBirthTextBox.setText("");
			nricTextBox.setText("");
			schoolTextBox.setText("");
			emailTextBox.setText("");
			telephoneTextBox.setText("");
			handphoneTextBox.setText("");
			statusTextBox.setText("");
			typeTextBox.setText("");
			//secretQuestion(),
			
			userNameTextBox.setEditable(false);
			firstNameTextBox.setEditable(false);
			lastNameTextBox.setEditable(false);
			dateOfBirthTextBox.setEditable(false);
			emailTextBox.setEditable(false);
			nricTextBox.setEditable(false);
			schoolTextBox.setEditable(false);
			telephoneTextBox.setEditable(false);
			handphoneTextBox.setEditable(false);
			}
		}
	});
	
	createAccountButton = new JButton();
	createAccountButton.setBounds(810, 46, 150, 30);
	createAccountButton.setText("Create Account");
	createAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			CreateRIForm createRiForm = new CreateRIForm();
			createRiForm.getJFrame().setVisible(true);
		}
	});
	
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
	panel.add(confirmUpdateButton);
	
	
	JScrollPane tableScrollPane = new JScrollPane(getTable());
	tableScrollPane.setBounds(-8, 97, 975, 200);
	panel.add(tableScrollPane);
	panel.add(getWankingPanel(), null);
	panel.add(getRefresh(), null);
	panel.add(getInfoPanel(),null);
		return panel;
	}
	
	
// .......................................JTable.........................	

	public JTable getTable() {

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Type your database query or controller here
			}
		});
		RI riModel = new RI();
		table = new JTable();	
		table.setBackground(Color.white);
		table.setBorder(null);
		table.setModel(tableModel);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		


		
		
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
	            if(col == 2)
	            {
	            	if(table.getValueAt(row, col).toString().equals("Disable"))
	            	{
	            		
	            		disableAccountButton.setText("Enable Account");
	            	}
	            	else
	            	{
	            		disableAccountButton.setText("Disable Account");
	            		
	            		 

	            	}
	            }
	        }  
	
	     
	  }  
	
	private JPanel getInfoPanel(){
		
		if(infoPanel == null) {
			infoPanel = new JPanel();
			infoPanel.setLayout(null);
			infoPanel.setBounds(new Rectangle(0, 328, 780, 300));
			infoPanel.add(userNameLabel);
			infoPanel.add(firstNameLabel);
			infoPanel.add( lastNameLabel);
			infoPanel.add(dateOfBirthLabel);
			infoPanel.add(nricLabel);
			infoPanel.add(schoolLabel);
			infoPanel.add(emailLabel);
			infoPanel.add(telephoneLabel);
			infoPanel.add(handphoneLabel);
			infoPanel.add(typeLabel);
			
			
			//add textBox
			infoPanel.add(userNameTextBox);
			infoPanel.add(firstNameTextBox);
			infoPanel.add(lastNameTextBox);
			infoPanel.add(dateOfBirthTextBox);
			infoPanel.add(nricTextBox);
			infoPanel.add(schoolTextBox);
			infoPanel.add(emailTextBox);
			infoPanel.add(telephoneTextBox);
			infoPanel.add(handphoneTextBox);
			infoPanel.add(typeTextBox);
			
		
			infoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RI Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return infoPanel;
	
	
	}
	
	

	private JPanel getWankingPanel() {
		if (wankingPanel == null) {
			wankingPanel = new JPanel();
			wankingPanel.setLayout(null);
			wankingPanel.setBounds(new Rectangle(783, 328, 178, 298));
			wankingPanel.add(updateAccountButton);
			wankingPanel.add(deleteAccountButton);
			wankingPanel.add(disableAccountButton);
			wankingPanel.add(confirmUpdateButton );
			wankingPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return wankingPanel;
	}

	/**
	 * This method initializes refresh	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefresh() {
		if (refresh == null) {
			refresh = new JButton("Refresh");
			refresh.setBounds(new Rectangle(712, 77, 262, 18));
			refresh.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					tableModel = viewRIDetailsController.getRITableModel();
					table.setModel(tableModel);
					table.updateUI();
				}
				
			});
		}
		return refresh;
	}

	public static void main(String a[]){
		
		
		
		
		ViewRIDetails viewRiDetails = new ViewRIDetails();
		viewRiDetails.getJFrame().setVisible(true);
		

		
	
	}
	
	

}
//String addFirstName=rs.getString("firstName");
//userNameTextBox.setText(addFirstName);

