package View.MM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.EmailController;
import Controller.MM.DeleteRIController;
import Controller.MM.UpdateRIController;
import Controller.MM.ViewRIDetailsController;
import Model.Membership.RI;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class AdministrateRIDetails {
	
	ViewRIDetailsController viewRIDetailsController = new ViewRIDetailsController(); 
	ViewRIDetailsController viewRIDetailsController2 = new ViewRIDetailsController();//  @jve:decl-index=0:
	
	public JTable table;
	public JTable eventTable;
	private JFrame jframe;  //  @jve:decl-index=0:visual-constraint="20,672"
	private JFrame frames;  //  @jve:decl-index=0:visual-constraint="39,10"
	private JPanel panel;  //  @jve:decl-index=0:visual-constraint="10,1650"
	private JPanel wankingPanel ;
	private JPanel infoPanel;
	private JPanel framesPanel;
	
	private DefaultTableModel tableModel = viewRIDetailsController.getRITableModel();
	private DefaultTableModel tableModel1 = viewRIDetailsController2.getRITableModelEvent();
	
	//TextBoxes
	private JTextField userNameTextBox;
	private  JTextField	firstNameTextBox;
	private  JTextField	lastNameTextBox;
	private  JTextField	dateOfBirthTextBox;
	private  JTextField	nricTextBox;
	private  JTextField	schoolTextBox;
	private  JTextField	emailTextBox;
	private JTextField addressTextBox;	
	private  JTextField	telephoneTextBox;
	private  JTextField handphoneTextBox;
	private  JTextField typeTextBox;
	private  JTextField statusTextBox;
	JTextField closureRequest;
	
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
	private JButton closeButton;
	private JButton  viewEventAndPayment;
	private JLabel refreshLabel = null;
	private JLabel createAccountLabel = null;
	private JLabel updateLabel = null;
	private JLabel deleteLabel = null;
	private JLabel makeDisableLabel = null;
	private JLabel viewPaymentAndEventLabel = null;
	private JLabel addressLabel = null;
	private JLabel jLabel = null;
	private JLabel cancelLabel = null;
	private JLabel activateLabel = null;
	public JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(1118, 771);
		jframe.setVisible(true);
		jframe.setTitle("GR View Of RI");
		jframe.setContentPane(new JScrollPane(panel));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setContentPane(getPanel());
		
		return jframe;
		
	}

	JFrame getFrames(){
		frames = new JFrame();
		frames.setSize(1000, 454);
		frames.setVisible(true);
		frames.setTitle("View of all Event and Purchase");
		
		frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.setResizable(false);
		frames.setContentPane(getFramesPanel());
		
		return frames;
		
	}
	private JPanel getFramesPanel()
	{
		framesPanel = new JPanel();
		framesPanel.setLayout(null);

		JScrollPane tableScrollPane1 = new JScrollPane(getEventTable());
		tableScrollPane1.setBounds(0, 97, 975, 200);
		tableScrollPane1.setViewportView(getEventTable());
		framesPanel.add(tableScrollPane1);
		
		framesPanel.add(closeButton);
		
		return framesPanel;
	}

	
	
	// ***********************JPanel Method****************	
	private JPanel getPanel(){
	createAccountLabel = new JLabel();
	createAccountLabel.setBounds(new Rectangle(934, 2, 175, 40));
	createAccountLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Login-in-icon.png")));
	createAccountLabel.setText("Create New Account");
	createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			CreateRIForm create = new CreateRIForm();
			create.getJFrame().setVisible(true);
		}
	});
	refreshLabel = new JLabel();
	refreshLabel.setBounds(new Rectangle(987, 59, 120, 40));
	refreshLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Refresh-icon.png")));
	refreshLabel.setText("Refresh Table");
	refreshLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			tableModel = viewRIDetailsController.getRITableModel();
			table.setModel(tableModel);
			table.updateUI();
		}
	});
	panel = new JPanel();
	
	
	panel.setLayout(null);
	
	panel.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel.setBounds(new Rectangle(0, 542, 1127, 740));
	panel.setBackground(new Color(102, 153, 255));
	
	
	
	//Labels
	
	title = new JLabel();
	title.setBounds(new Rectangle(-18, 1, 605, 100));
	title.setFont(new Font("Segoe UI", Font.BOLD | Font.PLAIN, 20));
	title.setIcon(new ImageIcon(getClass().getResource("/Images/MM/1343842308_kontact_contacts.png")));
	title.setText("RI ACCOUNTS INFORMATION");
	
	
	
	
	statusLabel = new JLabel();
	statusLabel.setBounds(new Rectangle(650, 300, 400, 30));
	statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	statusLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Status-mail-task-icon.png")));
	statusLabel.setText("Account Status :");
	
	statusLabel1 = new JLabel();
	statusLabel1.setBounds(new Rectangle(250, 450, 400, 30));
	statusLabel1.setFont(new Font("Dialog", Font.BOLD, 14));
	//color code (active / inactive)
	//statusLabel1.setText("Active / Inactive");

	

	
	closeButton = new JButton();
	closeButton.setBounds(346, 347, 150, 30);
	closeButton.setText("close");
	closeButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		
			getFrames().dispose();	
		}
		
	});
	
	
	
	
	viewEventAndPayment =new JButton();
	viewEventAndPayment.setBounds(0,250,150,30);
	viewEventAndPayment.setText("View RI Event and Payment");
	viewEventAndPayment.setVisible(false);
	viewEventAndPayment.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			getFrames().setVisible(true);
		}
		
	});	
	
	
	deleteAccountButton = new JButton();
	deleteAccountButton.setBounds(0, 102, 150, 30);
//	deleteAccountButton.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Trash-Black-Empty-icon.png")));
	deleteAccountButton.setText("Delete Account");
	deleteAccountButton.setVisible(false);
	deleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Object[] options = { "OK", "CANCEL" };
			int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure You Want to Delete  " +userNameTextBox.getText() +"", "Please Confirm",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
			if (confirmUpdateOption == 0)
			{DeleteRIController deleteController = new DeleteRIController();
			deleteController.delteRIAccount(userNameTextBox.getText());
				
			}
		}
	});
	
	
	
	updateAccountButton = new JButton();
	//updateAccountButton.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Wordpad-icon.png")));
	updateAccountButton.setBounds(0, 29, 150, 30);
	updateAccountButton.setText("Update Account");
	updateAccountButton.setVisible(false);
	
	
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
				addressTextBox.setEditable(true);
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
	confirmUpdateButton.setVisible(false);
	confirmUpdateButton.setBounds(new Rectangle(784, 18, 150, 30));
	confirmUpdateButton.setText("Confirm Update");
	confirmUpdateButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			}
			
	
		
	});
	
	disableAccountButton = new JButton();
	disableAccountButton.setBounds(1, 190, 150, 30);
	disableAccountButton.setText("Disable Account");
	disableAccountButton.setVisible(false);
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

			String[] emailArray1;               
		      emailArray1 = new String[1]; 
		      emailArray1[0] = emailTextBox.getText();
			
			EmailController deActiveEmail = new EmailController();
			try {
				deActiveEmail.sendEmail("text",emailArray1, "Account Set to Inactive", "Please be notified that your account is currently deactivated.", null, "Account");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			

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
				
				
				String[] emailArray;               
			      emailArray = new String[1]; 
			      emailArray[0] = emailTextBox.getText();
				
				EmailController activeEmail = new EmailController();
				try {
					activeEmail.sendEmail("text",emailArray, "Account Set to Active", "Please be notified that your account is currently set to active.", null, "Account");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
			addressTextBox.setText("");
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
			addressTextBox.setEditable(false);
			telephoneTextBox.setEditable(false);
			handphoneTextBox.setEditable(false);
			}
		}
	});
	
	createAccountButton = new JButton();
	createAccountButton.setBounds(972, 33, 150, 30);
	createAccountButton.setText("Create Account");
	createAccountButton.setVisible(false);
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
//	panel.add(userNameLabel);
//	panel.add(firstNameLabel);
//	panel.add( lastNameLabel);
//	panel.add(dateOfBirthLabel);
//	panel.add(nricLabel);
//	panel.add(schoolLabel);
//	panel.add(emailLabel);
//	panel.add(telephoneLabel);
//	panel.add(handphoneLabel);
//	panel.add(typeLabel);
//	panel.add(statusLabel);
//	
//	
//	//add textBox
//	panel.add(userNameTextBox);
//	panel.add(firstNameTextBox);
//	panel.add(lastNameTextBox);
//	panel.add(dateOfBirthTextBox);
//	panel.add(nricTextBox);
//	panel.add(schoolTextBox);
//	panel.add(emailTextBox);
//	panel.add(telephoneTextBox);
//	panel.add(handphoneTextBox);
//	panel.add(typeTextBox);
//	panel.add(statusTextBox);
	
	//add Button
	panel.add(deleteAccountButton);
	//panel.add(submitButton);
	panel.add(updateAccountButton);
	panel.add(disableAccountButton);
	panel.add(createAccountButton);
	panel.add(confirmUpdateButton, null);
	
	JScrollPane tableScrollPane = new JScrollPane(getTable());
	tableScrollPane.setBounds(0, 97, 1100, 200);
	tableScrollPane.setViewportView(getTable());
	panel.add(tableScrollPane);
	//panel.add(getInfoPanel(),null);
	panel.add(getWankingPanel(), null);
	panel.add(refreshLabel, null);
	panel.add(createAccountLabel, null);
	panel.add(getInfoPanel(), null);
		return panel;
	}
	
	
// .......................................JTable.........................	

	public JTable getEventTable() {

		RI riModel = new RI();
		eventTable = new JTable();	
		eventTable.setBorder(null);
		eventTable.setModel(tableModel1);
		eventTable.setColumnSelectionAllowed(false);
		eventTable.setCellSelectionEnabled(false);
		eventTable.setRowSelectionAllowed(true);
		eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		


		
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
		table.setSelectionBackground(null);
		
		
		
		eventTable.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));
		return eventTable;
	
	
	}
		
		
	public JTable getTable() {

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Type your database query or controller here
			}
		});
		RI riModel = new RI();
		table = new JTable();	
		table.setBackground(Color.white);
		table.setBounds(0, 0, 1000, 300);
		table.setBorder(null);
		table.setModel(tableModel);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowHeight(50);
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
			JTextField[] textBoxes = {userNameTextBox, typeTextBox, statusTextBox,firstNameTextBox, lastNameTextBox,  nricTextBox, schoolTextBox, emailTextBox, addressTextBox, telephoneTextBox, handphoneTextBox,closureRequest};
	        int columns = table.getColumnCount();  
	       
	        
	      
	        for(int col = 0; col < columns; col++)  
	        {  
	            textBoxes[col].setText(table.getValueAt(row, col).toString());
	            if(col == 2)
	            {
	            	if(table.getValueAt(row, col).toString().equals("Active"))
	            	{
	            		makeDisableLabel.setVisible(true);
	            		activateLabel.setVisible(false);
	            	
	            		
	            	}
	            	else
	            	{
	            		activateLabel.setVisible(true);
	            		makeDisableLabel.setVisible(false);
	            		
	            		 

	            	}
	            }
	        }  
	
	     
	  }  
	
	private JPanel getInfoPanel(){
		
		if(infoPanel == null) {
			
			
			cancelLabel = new JLabel();
			cancelLabel.setBounds(new Rectangle(915, 203, 133, 58));
			cancelLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/haha.png")));
			cancelLabel.setText("Cancel");
			cancelLabel.setVisible(false);
			cancelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					userNameTextBox.setText("");
					firstNameTextBox.setText("");
					lastNameTextBox.setText("");
					//parseDate(date()), 
					dateOfBirthTextBox.setText("");
					nricTextBox.setText("");
					schoolTextBox.setText("");
					emailTextBox.setText("");
					telephoneTextBox.setText("");
					addressTextBox.setText("");
					handphoneTextBox.setText("");
					statusTextBox.setText("");
					typeTextBox.setText("");
					updateLabel.setVisible(true);
					cancelLabel.setVisible(false);
					jLabel.setVisible(false);
					
				}
			});
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(911, 155, 136, 52));
			jLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Status-mail-task-icon.png")));
			jLabel.setText("Update now");
			jLabel.setVisible(false);
			jLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
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
									|| emailTextBox.getText().equals("")||
									addressTextBox.getText().equals("")) {

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
							addressTextBox.setText("");
							handphoneTextBox.setText("");
							statusTextBox.setText("");
							typeTextBox.setText("");
							
							
							userNameTextBox.setEditable(false);
							firstNameTextBox.setEditable(false);
							lastNameTextBox.setEditable(false);
							dateOfBirthTextBox.setEditable(false);
							emailTextBox.setEditable(false);
							nricTextBox.setEditable(false);
							schoolTextBox.setEditable(false);
							addressTextBox.setEditable(false);
							telephoneTextBox.setEditable(false);
							handphoneTextBox.setEditable(false);
							
							updateAccountButton.setVisible(true);
							confirmUpdateButton.setVisible(false);
							
							tableModel = viewRIDetailsController.getRITableModel();
							table.setModel(tableModel);
							table.updateUI();
							
						
							String[] emailArray;               
						      emailArray = new String[1]; 
						      emailArray[0] = emailTextBox.getText();
								
								EmailController updateEmail = new EmailController();
								try {
									updateEmail.sendEmail("text",emailArray, "Update Reunion Initiator Account with Great Reunion ", "Please be notified that you have updated an account successfully with Great Reunion.", null, "Account");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							
						}
						
							
					}
					
				}
			});
			addressLabel = new JLabel();
			addressLabel.setBounds(new Rectangle(493, 77, 87, 29));
			addressLabel.setText("Address :");
			addressLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			
			userNameLabel = new JLabel();
			userNameLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			userNameLabel.setBounds(new Rectangle(1, 28, 104, 30));
			userNameLabel.setText("UserName :");
			
			firstNameLabel = new JLabel();
			firstNameLabel.setBounds(new Rectangle(2, 74, 91, 30));
			firstNameLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			firstNameLabel.setText("First Name :");
			
			lastNameLabel = new JLabel();
			lastNameLabel.setBounds(new Rectangle(2, 121, 90, 30));
			lastNameLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			lastNameLabel.setText("Last Name :");
			
			
			
			dateOfBirthLabel = new JLabel();
			dateOfBirthLabel.setBounds(new Rectangle(1, 209, 99, 30));
			dateOfBirthLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			dateOfBirthLabel.setText("Date of Birth :");
			
			nricLabel = new JLabel();
			nricLabel.setBounds(new Rectangle(2, 164, 65, 30));
			nricLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			nricLabel.setText("Nric :");
			
			schoolLabel = new JLabel();
			schoolLabel.setBounds(new Rectangle(496, 238, 89, 30));
			schoolLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			schoolLabel.setText("School :");
			
			emailLabel = new JLabel();
			emailLabel.setBounds(new Rectangle(495, 26, 83, 30));
			emailLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			emailLabel.setText("Email  :");
			
			telephoneLabel = new JLabel();
			telephoneLabel.setBounds(new Rectangle(495, 133, 98, 30));
			telephoneLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			telephoneLabel.setText("Telephone :");
			
			handphoneLabel = new JLabel();
			handphoneLabel.setBounds(new Rectangle(496, 179, 105, 30));
			handphoneLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 16));
			handphoneLabel.setText("Handphone :");
			
			typeLabel = new JLabel();
			typeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			typeLabel.setBounds(new Rectangle(892, 22, 400, 30));
			typeLabel.setText("Account Type :");
			typeLabel.setVisible(false);
			
			//TextBox
			userNameTextBox =new JTextField();
			userNameTextBox.setBounds(new Rectangle(105, 30, 269, 25));
			userNameTextBox.setEditable(false);
			
			
			firstNameTextBox=new JTextField();
			firstNameTextBox.setBounds(new Rectangle(104, 75, 271, 25));
			firstNameTextBox.setEditable(false);


			lastNameTextBox=new JTextField();
			lastNameTextBox.setBounds(new Rectangle(105, 120, 271, 25));
			lastNameTextBox.setEditable(false);
			
			
			dateOfBirthTextBox=new JTextField();
			dateOfBirthTextBox.setBounds(new Rectangle(103, 210, 150, 25));
			dateOfBirthTextBox.setEditable(false);
			
			nricTextBox=new JTextField();
			nricTextBox.setBounds(new Rectangle(104, 166, 150, 25));
			nricTextBox.setEditable(false);
			
			schoolTextBox=new JTextField();
			schoolTextBox.setBounds(new Rectangle(600, 237, 257, 25));
			schoolTextBox.setEditable(false);
			
			emailTextBox=new JTextField();
			emailTextBox.setBounds(new Rectangle(597, 30, 230, 25));
			emailTextBox.setEditable(false);
			
			addressTextBox=new JTextField();
			addressTextBox.setEditable(false);
			addressTextBox.setBounds(new Rectangle(597, 76, 362, 29));
			
			telephoneTextBox=new JTextField();
			telephoneTextBox.setBounds(new Rectangle(598, 138, 150, 25));
			telephoneTextBox.setEditable(false);
			
			handphoneTextBox=new JTextField();
			handphoneTextBox.setBounds(new Rectangle(599, 177, 150, 25));
			handphoneTextBox.setEditable(false);
			
			typeTextBox=new JTextField();
			typeTextBox.setEditable(false);
			typeTextBox.setBounds(new Rectangle(1018, 26, 72, 25));
			typeTextBox.setVisible(false);
			
			statusTextBox=new JTextField();
			statusTextBox.setBounds(new Rectangle(0, 300, 150, 25));
			statusTextBox.setEditable(false);
			statusTextBox.setVisible(true);
			
			
			
			closureRequest = new JTextField();
			closureRequest.setVisible(false);

			
			infoPanel = new JPanel();
			infoPanel.setLayout(null);
			infoPanel.setBackground(new Color(102, 153, 255));
			infoPanel.setBounds(new Rectangle(0, 297, 1106, 275));
			infoPanel.add(userNameLabel);
			infoPanel.add(firstNameLabel);
			infoPanel.add( lastNameLabel);
			infoPanel.add(dateOfBirthLabel);
			infoPanel.add(nricLabel);
			infoPanel.add(schoolLabel);
			infoPanel.add(emailLabel);
			infoPanel.add(telephoneLabel);
			infoPanel.add(handphoneLabel);
			infoPanel.add(closureRequest);
			infoPanel.add(statusLabel);
			infoPanel.add(statusTextBox);
			
			
			//add textBox
			infoPanel.add(userNameTextBox);
			infoPanel.add(firstNameTextBox);
			infoPanel.add(lastNameTextBox);
			infoPanel.add(dateOfBirthTextBox);
			infoPanel.add(nricTextBox);
			infoPanel.add(schoolTextBox);
			infoPanel.add(emailTextBox);
			infoPanel.add(addressTextBox);
			infoPanel.add(telephoneTextBox);
			infoPanel.add(handphoneTextBox);
			
		
			infoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RI Personal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			infoPanel.add(addressLabel, null);
			infoPanel.add(typeLabel, null);
			infoPanel.add(typeTextBox, null);
			infoPanel.add(jLabel, null);
			infoPanel.add(cancelLabel, null);
			
			
			
			infoPanel.add(addressTextBox, null);
		}
		return infoPanel;
	
	
	}
	
	

	private JPanel getWankingPanel() {
		if (wankingPanel == null) {
			
			activateLabel = new JLabel();
			activateLabel.setBounds(new Rectangle(874, 29, 231, 101));
			activateLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/80_140____button-add-icon_28.png")));
			activateLabel.setText("Activate ");
			activateLabel.setVisible(false);
			activateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {	
					Object[] options = { "OK", "CANCEL" };
					int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are Sure You want to Enable  "+userNameTextBox.getText()+"?", "Please Confirm",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if (confirmUpdateOption == 0){
					
					statusTextBox.setText("Active");
					UpdateRIController disableAccount = new UpdateRIController();
					disableAccount.disableRIAccount(userNameTextBox.getText(), statusTextBox.getText());
					}
					
					
					String[] emailArray;               
				      emailArray = new String[1]; 
				      emailArray[0] = emailTextBox.getText();
					
					EmailController activeEmail = new EmailController();
					try {
						activeEmail.sendEmail("text",emailArray, "Account Set to Active", "Please be notified that your account is currently set to active.", null, "Account");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
					
				}
				});
			
			updateLabel = new JLabel();
			updateLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Actions-document-edit-icon.png")));
			updateLabel.setBounds(new Rectangle(47, 11, 267, 116));
			updateLabel.setText("Update This Account");
			updateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					updateAccount();
					
				}
				private void updateAccount() {
					Object[] options = { "OK", "CANCEL" };
					int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure You Want to UPDATE  " +userNameTextBox.getText() +"", "Please Confirm",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
					if (confirmUpdateOption == 0){
						jLabel.setVisible(true);
						cancelLabel.setVisible(true);
						
						userNameTextBox.setEditable(true);
						firstNameTextBox.setEditable(true);
						lastNameTextBox.setEditable(true);
						dateOfBirthTextBox.setEditable(true);
						emailTextBox.setEditable(true);
				
						nricTextBox.setEditable(true);
						schoolTextBox.setEditable(true);
						addressTextBox.setEditable(true);
						telephoneTextBox.setEditable(true);
						handphoneTextBox.setEditable(true);
//						typeTextBox.setEditable(true);
//						statusTextBox.setEditable(true);
					}
				}
			});
			viewPaymentAndEventLabel = new JLabel();
			viewPaymentAndEventLabel.setBounds(new Rectangle(608, 24, 259, 102));
			viewPaymentAndEventLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/data-management-icon.png")));
			viewPaymentAndEventLabel.setText("View Payments and Events");
			viewPaymentAndEventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					getFrames().setVisible(true);
					
				}
			});
			makeDisableLabel = new JLabel();
			makeDisableLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Windows-Close-Program-icon.png")));
			makeDisableLabel.setBounds(new Rectangle(871, 27, 257, 105));
			makeDisableLabel.setText("Disable This Account");
			makeDisableLabel.setVisible(false);
			makeDisableLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					
				
					
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
					

					String[] emailArray1;               
				      emailArray1 = new String[1]; 
				      emailArray1[0] = emailTextBox.getText();
					
					EmailController deActiveEmail = new EmailController();
					try {
						deActiveEmail.sendEmail("text",emailArray1, "Account Set to Inactive", "Please be notified that your account is currently deactivated.", null, "Account");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
					addressTextBox.setText("");
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
					addressTextBox.setEditable(false);
					telephoneTextBox.setEditable(false);
					handphoneTextBox.setEditable(false);
					

					
				}
			});
			deleteLabel = new JLabel();
			deleteLabel.setBounds(new Rectangle(345, 13, 254, 115));
			deleteLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			deleteLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Trash-Black-Empty-icon.png")));
			deleteLabel.setText("Delete This Account");
			wankingPanel = new JPanel();
			wankingPanel.setLayout(null);
			wankingPanel.setBounds(new Rectangle(2, 573, 1161, 166));
			wankingPanel.setBackground(new Color(102, 153, 255));
			wankingPanel.add(updateAccountButton);
			wankingPanel.add(deleteAccountButton);
			wankingPanel.add(disableAccountButton);
			wankingPanel.add(confirmUpdateButton );
			wankingPanel.add(viewEventAndPayment);
			wankingPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			wankingPanel.add(updateLabel, null);
			wankingPanel.add(deleteLabel, null);
			wankingPanel.add(viewPaymentAndEventLabel, null);
			wankingPanel.add(makeDisableLabel, null);
			wankingPanel.add(activateLabel, null);
		}
		return wankingPanel;
	}

	public void checkIfCanDelete(String balanceAmount, String statusEvent ){
		ViewRIDetailsRIVIEW check =new ViewRIDetailsRIVIEW();
		
		
		
	}
	
	public static void main(String a[]){
		
		
		
		
		AdministrateRIDetails viewRiDetails = new AdministrateRIDetails();
		viewRiDetails.getJFrame().setVisible(true);
		

		
	
	}
	
	
	

}
