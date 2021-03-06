package View.MM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.EmailController;
import Controller.MM.UpdateRIController;
import Controller.MM.ViewRIPersonalController;
import Images.RIM.ImageHelper;
import Model.Membership.Account;
import View.RIDashboard;

import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JPasswordField;

public class AdministrateRIPersonalDetails {
	
	ViewRIPersonalController viewRIPersonalController = new	ViewRIPersonalController();
	private TableModel tableModel = viewRIPersonalController.getRITableModel1();

	private JTable table;
	private JTable tableEvent;
	private JTable tablePayment;
	private JFrame jframe;  //  @jve:decl-index=0:visual-constraint="13,61"
	private JPanel panel;
	private JFrame updateFrame;  //  @jve:decl-index=0:visual-constraint="-7,549"
	private JPanel updatePanel;

	//Labels

	private JLabel title;
	
	private JLabel userNameLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel schoolLabel;
	private JLabel emailLabel;
	private JLabel telephoneLabel;
	private JLabel handphoneLabel;
	private JLabel nricLabel;
	private JLabel name;
	
	
	
	//TextBoxes
	JTextField userNameTextBox;
	JTextField firstNameTextBox;
	JTextField lastNameTextBox;
	JTextField dateOfBirthTextBox;
	JTextField schoolTextBox;
	JTextField emailTextBox;
	JTextField telephoneTextBox;
	JTextField handphoneTextBox;
	JTextField nricTextBox;
	private  JTextField typeTextBox;
	private  JTextField statusTextBox;
	JTextField eventStatus;
	JTextField amountBalance;
	

	
	//Buttons

	private JButton  updateAccountButton;
	private JButton submitButton;
	private JButton cancelButtonUpdate;

	private JLabel updateLabel = null;

	private JLabel requestCloseLabel = null;

	private JLabel jLabel = null;

	private JLabel backToDashBoard = null;

	private JFrame getUpdateFrame(){
		updateFrame = new JFrame();
		updateFrame.setSize(779, 388);
		updateFrame.setVisible(true);
		updateFrame.setTitle("Update RI");
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateFrame.setContentPane(getUpdatePanel());
		
		
		return jframe;
		
	}
	
	public JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(1211, 482);
		jframe.setVisible(true);
		jframe.setTitle("View");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setContentPane(getPanel());
		
		
		return jframe;
		
	}
	
	
	


	// ***********************JPanel Method****************	
	
	private JPanel getUpdatePanel(){
		updatePanel = new JPanel();
		updatePanel.setLayout(null);
		updatePanel.setFont(new Font("Dialog", Font.PLAIN, 14));
		updatePanel.setBackground(new Color(102, 153, 255));
		
		
	//Label
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setBounds(18, 18, 530, 64);
		welcomeLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/addressbook-edit-icon.png")));
		welcomeLabel.setText("Update personal Information" );
		updatePanel.add(welcomeLabel);
		
		
		
		userNameLabel = new JLabel();
		userNameLabel.setBounds(new Rectangle(50, 100, 131, 30));
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		userNameLabel.setText("UserName :");
		
		firstNameLabel = new JLabel();
		firstNameLabel.setBounds(new Rectangle(50, 150, 103, 30));
		firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		firstNameLabel.setText("First Name :");
		
		lastNameLabel = new JLabel();
		lastNameLabel.setBounds(new Rectangle(50, 200, 127, 30));
		lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lastNameLabel.setText("Last Name :");
		
		
		
		
		nricLabel = new JLabel();
		nricLabel.setBounds(new Rectangle(50, 300, 400, 30));
		nricLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nricLabel.setText("Nric :");
		
		schoolLabel = new JLabel();
		schoolLabel.setBounds(new Rectangle(462, 202, 92, 30));
		schoolLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		schoolLabel.setText("School :");
		
		emailLabel = new JLabel();
		emailLabel.setBounds(new Rectangle(46, 255, 119, 30));
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		emailLabel.setText("Email :");
		
		telephoneLabel = new JLabel();
		telephoneLabel.setBounds(new Rectangle(462, 102, 112, 30));
		telephoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		telephoneLabel.setText("Telephone :");
		
		handphoneLabel = new JLabel();
		handphoneLabel.setBounds(new Rectangle(462, 149, 112, 30));
		handphoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		handphoneLabel.setText("Handphone :");
		
	//End of Label	
		
		
		userNameTextBox =new JTextField();
		userNameTextBox.setBounds(new Rectangle(200, 100, 237, 25));
		userNameTextBox.setEditable(false);
		userNameTextBox.setText((table.getModel()).getValueAt(0, 0).toString());
		
		
		firstNameTextBox=new JTextField();
		firstNameTextBox.setBounds(new Rectangle(200, 150, 236, 25));
		firstNameTextBox.setVisible(true);


		lastNameTextBox=new JTextField();
		lastNameTextBox.setBounds(new Rectangle(200, 200, 236, 25));
		lastNameTextBox.setVisible(true);
		
		
		
		
		nricTextBox = new JTextField();
		nricTextBox.setBounds(new Rectangle(200, 300, 150, 25));
		nricTextBox.setVisible(true);
		
		
		schoolTextBox=new JTextField();
		schoolTextBox.setBounds(new Rectangle(583, 202, 150, 25));
		schoolTextBox.setVisible(true);
		
		emailTextBox=new JTextField();
		emailTextBox.setBounds(new Rectangle(200, 256, 235, 25));
		emailTextBox.setVisible(true);
		
		telephoneTextBox=new JTextField();
		telephoneTextBox.setBounds(new Rectangle(584, 106, 150, 25));
		telephoneTextBox.setVisible(true);
		
		handphoneTextBox=new JTextField();
		handphoneTextBox.setBounds(new Rectangle(586, 149, 150, 25));
		handphoneTextBox.setVisible(true);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(448, 302, 150, 30);
		submitButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent e) {
				Object[] options = { "OK", "CANCEL" };
				int confirmUpdateOption = JOptionPane.showOptionDialog(null,
						"Are You Sure You Want to UPDATE RI DATA?",
						"Please Confirm", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (confirmUpdateOption == 0) {

					UpdateRIController updateController = new UpdateRIController();

					updateController.updateRIAccount

					(userNameTextBox.getText(),

					firstNameTextBox.getText(),
							lastNameTextBox.getText(),
							// parseDate(date()),
							nricTextBox.getText(), schoolTextBox.getText(),
							emailTextBox.getText(), telephoneTextBox.getText(),
							handphoneTextBox.getText());

					JOptionPane.showConfirmDialog(null, ""
							+ userNameTextBox.getText()
							+ "Has Been Successfully updated!",
							"Account Updated!", JOptionPane.CLOSED_OPTION);
					
					tableModel = viewRIPersonalController.getRITableModel1();
					table.setModel(tableModel);
					table.updateUI();
					

					String[] emailArray1;
					emailArray1 = new String[1];
					emailArray1[0] = Account.currentUser.getEmail();
					
					updateFrame.setVisible(false);

					EmailController updateEmail = new EmailController();
					try {
						updateEmail
								.sendEmail(
										"text",
										emailArray1,
										"Update Reunion Initiator Account",
										"Please be notified that your account has been successfully updated.",
										null, "Account");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

						
						getJFrame().setVisible(true);

					}
				}
			}

		});
		
		cancelButtonUpdate = new JButton("Cancel");
		cancelButtonUpdate.setBounds(605, 302, 150, 30);
		cancelButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				updateFrame.setVisible(false);
			}
		});
		
			
		
		

		updatePanel.add(userNameTextBox);
		updatePanel.add(firstNameTextBox);
		updatePanel.add(lastNameTextBox);
		updatePanel.add(nricTextBox);
		updatePanel.add(schoolTextBox);
		updatePanel.add(emailTextBox);
		updatePanel.add(telephoneTextBox);
		updatePanel.add(handphoneTextBox);
	
		
		
		updatePanel.add(userNameLabel);
		updatePanel.add(firstNameLabel);
		updatePanel.add( lastNameLabel);
		updatePanel.add(nricLabel);
		
		updatePanel.add(schoolLabel);
		updatePanel.add(emailLabel);
		updatePanel.add(telephoneLabel);
		updatePanel.add(handphoneLabel);
		
		
		updatePanel.add(cancelButtonUpdate);
		updatePanel.add(submitButton);
		return updatePanel;
	}
	
	
	private JPanel getPanel(){
	
		
		backToDashBoard = new JLabel();
		backToDashBoard.setBounds(new Rectangle(926, -6, 262, 130));
		backToDashBoard.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Log-Out-icon.png")));
		backToDashBoard.setText("Back to Main Screen");
		backToDashBoard.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				jframe.setVisible(false);
		new RIDashboard();
			
				
			}
		});
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(2, 257, 428, 63));
		jLabel.setText("Current Events and Payment");
		jLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 22));
		requestCloseLabel = new JLabel();
		requestCloseLabel.setBounds(new Rectangle(886, 309, 239, 111));
		requestCloseLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Images/MM/Actions-edit-delete-shred-icon.png")));
		requestCloseLabel.setText("Request Account Closure");
		requestCloseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			
			
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Object[] options = { "OK", "CANCEL" };
				int confirmUpdateOption = JOptionPane.showOptionDialog(null,
						"Are You Sure you want to delete your account?",
						"Request Account Closure", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				
				if (confirmUpdateOption == 0) {
					
						JLabel noRecordsLabel = new JLabel("No records found.");
						noRecordsLabel.setLocation(table.getLocation());

						if(tableEvent.getModel().getValueAt(0, 1).equals(
						"Pending")
						|| tableEvent.getModel().getValueAt(0, 1)
						
						.equals("Awaiting Confirmation")){
							
							System.out.println("Outstanding payment/event");

							JOptionPane
									.showConfirmDialog(
											null,
											"You have an outstanding event or Payment. Please Contact Great Reunion for further details",
											"Unable to delete",
											JOptionPane.CLOSED_OPTION);}
						
					
						String closureReason = null ;
				if (tableEvent.getModel().getValueAt(0, 1).equals(
									"Confirmed")
									|| tableEvent.getModel().getValueAt(0, 1)
									
									.equals("Cancelled")&& tableEvent.getModel().getValueAt(0, 4).equals("0"))
								 closureReason = JOptionPane.showInputDialog(null,
										"Please enter the reason for closure : ",
										"Closure request sent!", 0);

								UpdateRIController closureReasonUpdate = new UpdateRIController();

								closureReasonUpdate.updateClosure(Account.currentUser
										.getUserName(), closureReason);

								String[] emailArray;
								emailArray = new String[1];
								emailArray[0] = Account.currentUser.getEmail();

								EmailController deActiveEmail = new EmailController();
								try {
									deActiveEmail
											.sendEmail(
													"text",
													emailArray,
													"Request for closure Account",
													"Please be notified that your request for account closure is currently being reviewed.",
													null, "Account");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

			
							

									
				}
						
					
						
			
				
			
			}
			
		});

	updateLabel = new JLabel();
	updateLabel.setBounds(new Rectangle(887, 196, 235, 95));
	updateLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Actions-document-edit-icon.png")));
	updateLabel.setText("Update Account");
	updateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			getUpdateFrame().setVisible(true);
		}
	});
	panel = new JPanel();	
	panel.setLayout(null);
	panel.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel.setBackground(new Color(102, 153, 255));
	
	
	
	//Labels
	 
	title = new JLabel();
	title.setBounds(new Rectangle(-1, 3, 750, 113));
	title.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 30));
	title.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Personal-information-128.png")));
	title.setText("Personal details");
	
	
	//TextBox
	updatePanel = new JPanel();
	updatePanel.setLayout(null);
	updatePanel.setFont(new Font("Dialog", Font.PLAIN, 14));
	
	
	userNameTextBox =new JTextField();
	userNameTextBox.setBounds(new Rectangle(130, 450, 150, 25));
	userNameTextBox.setVisible(false);
	
	
	firstNameTextBox=new JTextField();
	firstNameTextBox.setBounds(new Rectangle(130, 500, 150, 25));
	firstNameTextBox.setVisible(false);


	lastNameTextBox=new JTextField();
	lastNameTextBox.setBounds(new Rectangle(130, 550, 150, 25));
	lastNameTextBox.setVisible(false);
	
	
	dateOfBirthTextBox = new JTextField();
	dateOfBirthTextBox.setBounds(new Rectangle(130, 600, 150, 25));
	dateOfBirthTextBox.setVisible(false);
	
	
	schoolTextBox=new JTextField();
	schoolTextBox.setBounds(new Rectangle(510, 400, 150, 25));
	schoolTextBox.setVisible(false);
	
	emailTextBox=new JTextField();
	emailTextBox.setBounds(new Rectangle(510, 450, 150, 25));
	emailTextBox.setVisible(false);
	
	telephoneTextBox=new JTextField();
	telephoneTextBox.setBounds(new Rectangle(510, 500, 150, 25));
	telephoneTextBox.setVisible(false);
	
	handphoneTextBox=new JTextField();
	handphoneTextBox.setBounds(new Rectangle(510, 550, 150, 25));
	handphoneTextBox.setVisible(false);
	
	typeTextBox=new JTextField();
	typeTextBox.setBounds(new Rectangle(600, 600, 150, 25));
	typeTextBox.setEditable(false);
	typeTextBox.setVisible(false);
	
	statusTextBox=new JTextField();
	statusTextBox.setBounds(new Rectangle(600, 650, 150, 25));
	statusTextBox.setEditable(false);
	statusTextBox.setVisible(false);
	
	eventStatus=new JTextField();
	eventStatus.setBounds(new Rectangle(0, 0, 150, 25));
	eventStatus.setEditable(true);
	eventStatus.setVisible(false);
	
	amountBalance=new JTextField();
	amountBalance.setBounds(new Rectangle(100, 0, 150, 25));
	amountBalance.setEditable(true);
	amountBalance.setVisible(false);
	
	
	
	//Buttons
	

	


	
	
			
			panel.add(title);
		//	panel.add(updateAccountButton);
			
			panel.add(userNameTextBox);
			panel.add(firstNameTextBox);
			panel.add(lastNameTextBox);
			panel.add(dateOfBirthTextBox);
			panel.add(schoolTextBox);
			panel.add(emailTextBox);
			panel.add(telephoneTextBox);
			panel.add(handphoneTextBox);
			panel.add(typeTextBox);
			panel.add(statusTextBox);
			panel.add(eventStatus);
			panel.add(amountBalance);
		
			JScrollPane tableScrollPane = new JScrollPane(getTable());
			tableScrollPane.setBounds(0, 120, 1196, 61);
			tableScrollPane.setFont(new Font("Dialog", Font.BOLD, 18));
			tableScrollPane.setEnabled(false);
			panel.add(tableScrollPane);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane tableScrollPaneEvent = new JScrollPane(getTableEvent());
			tableScrollPaneEvent.setBounds(2, 329, 663, 100);
			panel.add(tableScrollPaneEvent);
			panel.add(updateLabel, null);
			panel.add(requestCloseLabel, null);
			panel.add(jLabel, null);
			panel.add(backToDashBoard, null);
				
		
			
		return panel;
	
		
	}
	
	
	public JTable getTableEvent() {

		
		tableEvent = new JTable();


		tableEvent.setBackground(Color.white);
		tableEvent.setBorder(null);
		tableEvent.setModel(viewRIPersonalController.getRITableModelEvent());
		tableEvent.setColumnSelectionAllowed(false);
		tableEvent.setCellSelectionEnabled(false);
		tableEvent.setRowSelectionAllowed(true);
		tableEvent.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);

		tableEvent.getTableHeader().setDefaultRenderer(headerRenderer);

		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));

		
	//	for(int i=0; i<tablePayment.getModel().getRowCount(); i++){
			//amountBalance.setText((tablePayment.getModel()).getValueAt(i, 1).toString());
			
			
	
		return tableEvent;

	}
	
	
	
	public JTable getTable() {

		
		table = new JTable();
	
		table.setBackground(Color.white);
		table.setBorder(null);
		table.setModel(viewRIPersonalController.getRITableModel1());
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
	
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 100));
		
	
	       
			
	

	
	
		
		return table;

	}

	
	 
		public static void main(String a[]){
	
		AdministrateRIPersonalDetails viewRiDetails = new AdministrateRIPersonalDetails();
		viewRiDetails.getJFrame().setVisible(true);
		
		
		}

}
