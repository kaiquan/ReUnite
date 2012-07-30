package View.MM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import Controller.MM.UpdateRIController;
import Controller.MM.ViewRIPersonalController;

public class ViewRIDetailsRIVIEW {
	
	ViewRIPersonalController viewRIPersonalController = new	ViewRIPersonalController();

	private JTable table;
	private JTable tableEvent;
	private JTable tablePayment;
	private JFrame jframe;  //  @jve:decl-index=0:visual-constraint="10,54"
	private JPanel panel;
	private JFrame updateFrame;
	private JPanel updatePanel;

	//Labels

	private JLabel title;
	
	private JLabel userNameLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dateOfBirthLabel;
	private JLabel schoolLabel;
	private JLabel emailLabel;
	private JLabel telephoneLabel;
	private JLabel handphoneLabel;
	private JLabel nricLabel;
	
	
	
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
	private JButton requestCloseAccountButton;
	private JButton submitButton;
	private JButton cancelButtonUpdate;

	private JFrame getUpdateFrame(){
		updateFrame = new JFrame();
		updateFrame.setSize(800, 400);
		updateFrame.setVisible(true);
		updateFrame.setTitle("Update RI");
		updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateFrame.setContentPane(getUpdatePanel());
		
		
		return jframe;
		
	}
	
	JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(1000, 600);
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
		
		
	//Label
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setBounds(10, 10, 150, 50);
		welcomeLabel.setText((table.getModel()).getValueAt(0, 0).toString());
		updatePanel.add(welcomeLabel);
		
		userNameLabel = new JLabel();
		userNameLabel.setBounds(new Rectangle(50, 100, 400, 30));
		userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		userNameLabel.setText("UserName :");
		
		firstNameLabel = new JLabel();
		firstNameLabel.setBounds(new Rectangle(50, 150, 400, 30));
		firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		firstNameLabel.setText("First Name :");
		
		lastNameLabel = new JLabel();
		lastNameLabel.setBounds(new Rectangle(50, 200, 400, 30));
		lastNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lastNameLabel.setText("Last Name :");
		
		
		
		dateOfBirthLabel = new JLabel();
		dateOfBirthLabel.setBounds(new Rectangle(50, 250, 400, 30));
		dateOfBirthLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dateOfBirthLabel.setText("Date of Birth :");
		
		nricLabel = new JLabel();
		nricLabel.setBounds(new Rectangle(50, 300, 400, 30));
		nricLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nricLabel.setText("Nric :");
		
		schoolLabel = new JLabel();
		schoolLabel.setBounds(new Rectangle(450, 100, 400, 30));
		schoolLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		schoolLabel.setText("School :");
		
		emailLabel = new JLabel();
		emailLabel.setBounds(new Rectangle(450, 150, 400, 30));
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		emailLabel.setText("Email :");
		
		telephoneLabel = new JLabel();
		telephoneLabel.setBounds(new Rectangle(450, 200, 400, 30));
		telephoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		telephoneLabel.setText("Telephone :");
		
		handphoneLabel = new JLabel();
		handphoneLabel.setBounds(new Rectangle(450, 250, 400, 30));
		handphoneLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		handphoneLabel.setText("Handphone :");
		
	//End of Label	
		
		
		userNameTextBox =new JTextField();
		userNameTextBox.setBounds(new Rectangle(200, 100, 150, 25));
		userNameTextBox.setEditable(false);
		userNameTextBox.setText((table.getModel()).getValueAt(0, 0).toString());
		
		
		firstNameTextBox=new JTextField();
		firstNameTextBox.setBounds(new Rectangle(200, 150, 150, 25));
		firstNameTextBox.setVisible(true);


		lastNameTextBox=new JTextField();
		lastNameTextBox.setBounds(new Rectangle(200, 200, 150, 25));
		lastNameTextBox.setVisible(true);
		
		
		dateOfBirthTextBox=new JTextField();
		dateOfBirthTextBox.setBounds(new Rectangle(200, 250, 150, 25));
		dateOfBirthTextBox.setVisible(true);
		
		
		nricTextBox = new JTextField();
		nricTextBox.setBounds(new Rectangle(200, 300, 150, 25));
		nricTextBox.setVisible(true);
		
		
		schoolTextBox=new JTextField();
		schoolTextBox.setBounds(new Rectangle(550, 100, 150, 25));
		schoolTextBox.setVisible(true);
		
		emailTextBox=new JTextField();
		emailTextBox.setBounds(new Rectangle(550, 150, 150, 25));
		emailTextBox.setVisible(true);
		
		telephoneTextBox=new JTextField();
		telephoneTextBox.setBounds(new Rectangle(550, 200, 150, 25));
		telephoneTextBox.setVisible(true);
		
		handphoneTextBox=new JTextField();
		handphoneTextBox.setBounds(new Rectangle(550, 250, 150, 25));
		handphoneTextBox.setVisible(true);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(400, 300, 150, 30);
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
				}
					else{
					JOptionPane.showConfirmDialog(null,""+userNameTextBox.getText()+"Has Been Successfully updated!",
							   "Account Updated!", JOptionPane.CLOSED_OPTION);
			
						getJFrame().setVisible(true);
					}

					
				
			}
		});
		
		cancelButtonUpdate = new JButton("Cancel");
		cancelButtonUpdate.setBounds(600, 300, 150, 30);
		cancelButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				
				
			}
		});
		
		

		updatePanel.add(userNameTextBox);
		updatePanel.add(firstNameTextBox);
		updatePanel.add(lastNameTextBox);
		updatePanel.add(dateOfBirthTextBox);
		updatePanel.add(nricTextBox);
		updatePanel.add(schoolTextBox);
		updatePanel.add(emailTextBox);
		updatePanel.add(telephoneTextBox);
		updatePanel.add(handphoneTextBox);
	
		
		
		updatePanel.add(userNameLabel);
		updatePanel.add(firstNameLabel);
		updatePanel.add( lastNameLabel);
		updatePanel.add(dateOfBirthLabel);
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
	panel = new JPanel();	
	panel.setLayout(null);
	panel.setFont(new Font("Dialog", Font.PLAIN, 14));
	
	
	
	//Labels
	 
	title = new JLabel();
	title.setBounds(new Rectangle(10, 10, 300, 100));
	title.setFont(new Font("Dialog", Font.BOLD, 30));
	title.setText("Welcome");
	
	
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
	
	
	dateOfBirthTextBox=new JTextField();
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
	eventStatus.setBounds(new Rectangle(600, 650, 150, 25));
	eventStatus.setEditable(true);
	eventStatus.setVisible(true);
	
	amountBalance=new JTextField();
	amountBalance.setBounds(new Rectangle(700, 650, 150, 25));
	amountBalance.setEditable(true);
	amountBalance.setVisible(true);
	
	
	
	//Buttons
	
	updateAccountButton = new JButton();
	updateAccountButton.setBounds(213, 433, 150, 30);
	updateAccountButton.setText("Update Account");
	updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			
			getUpdateFrame().setVisible(true);
			
		}
	});
	


	
	
				requestCloseAccountButton = new JButton();
					requestCloseAccountButton.setBounds(391, 432, 293, 30);
					requestCloseAccountButton.setText("Request Account Closure");
					requestCloseAccountButton
						.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
					Object[] options = { "OK", "CANCEL" };
						int confirmUpdateOption = JOptionPane.showOptionDialog(null, "Are You Sure you want to delete your account?", "Request Account Closure",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
					if (confirmUpdateOption==0)
					{
						if(eventStatus.getText().equalsIgnoreCase("Confirmed")|| eventStatus.getText().equals("Cancelled") && amountBalance.getText().isEmpty()|| amountBalance.getText().equalsIgnoreCase("0")){
							
							
							String closureReason = JOptionPane.showInputDialog(null, "Please enter the reason for closure : ", 
									"Closure request sent!", 1);
							
							UpdateRIController closureReasonUpdate = new UpdateRIController();
								
								closureReasonUpdate.updateClosure(userNameTextBox.getText(), closureReason);
					
						}
						else {
							
						

						JOptionPane.showConfirmDialog(null,"You have an outstanding event or Payment. Please Contact Great Reunion for further details",
								   "Unable to delete", JOptionPane.CLOSED_OPTION);
							
						}
						
					}
			}
							});
					

	
	//Table
	
	
	
	
//..............................add.......................................
			
			panel.add(title);
			panel.add(requestCloseAccountButton);
			panel.add(updateAccountButton);
			
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
			tableScrollPane.setBounds(0, 150, 884, 40);
			tableScrollPane.setFont(new Font("Dialog", Font.BOLD, 18));
			tableScrollPane.setEnabled(false);
			panel.add(tableScrollPane);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane tableScrollPaneEvent = new JScrollPane(getTableEvent());
			tableScrollPaneEvent.setBounds(0, 250, 350, 100);
			panel.add(tableScrollPaneEvent);
				
			JScrollPane tableScrollPanePayment = new JScrollPane(getTablePayment());
			tableScrollPanePayment.setBounds(597, 255, 200, 100);
			panel.add(tableScrollPanePayment);	
			
		return panel;
	}
		

	
	public JTable getTablePayment() {

		

		tablePayment = new JTable();

		tablePayment.setBackground(Color.white);
		tablePayment.setModel(viewRIPersonalController.getRITableModelPayment());
		tablePayment.setColumnSelectionAllowed(false);
		tablePayment.setCellSelectionEnabled(false);
		tablePayment.setRowSelectionAllowed(true);
		tablePayment.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);

		tablePayment.getTableHeader().setDefaultRenderer(headerRenderer);

		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));

		tablePayment.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getSource() == table.getSelectionModel()
								&& table.getRowSelectionAllowed()) {
							// Column selection changed
							ListSelectionModel model = table
									.getSelectionModel();
							int lead = model.getLeadSelectionIndex();
							shiftData(lead);

						}

						if (e.getValueIsAdjusting()) {
							// The mouse button has not yet been released
						}
					}
				});
		amountBalance.setText((tablePayment.getModel()).getValueAt(0, 1).toString());

	
		return tablePayment;

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

		tableEvent.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getSource() == table.getSelectionModel()
								&& table.getRowSelectionAllowed()) {
							// Column selection changed
							ListSelectionModel model = table
									.getSelectionModel();
							int lead = model.getLeadSelectionIndex();
							shiftData(lead);

						}

						if (e.getValueIsAdjusting()) {
							// The mouse button has not yet been released
						}
					}
				});

		
		eventStatus.setText((tableEvent.getModel()).getValueAt(0, 2).toString());
	
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
	
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 100));
		
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
			
			JTextField[] textBoxes = {userNameTextBox,  typeTextBox, statusTextBox,firstNameTextBox, lastNameTextBox,  nricTextBox, schoolTextBox, emailTextBox, telephoneTextBox, handphoneTextBox};
					int columns = table.getColumnCount();  



					for(int col = 0; col < columns; col++)  
					{   	
						textBoxes[col].setText(table.getValueAt(row, col).toString());
					}
	}  
		public static void main(String a[]){
	
		ViewRIDetailsRIVIEW viewRiDetails = new ViewRIDetailsRIVIEW();
		viewRiDetails.getJFrame().setVisible(true);
		
		
	
	
	}
		public void checkforApproval(){
			
			String balanceAmount;
			String statusEvent ;
			
			ViewRIDetails check = new ViewRIDetails();
			
			check.checkIfCanDelete(balanceAmount = amountBalance.getText(),statusEvent = eventStatus.getText());
		
		
		
		}
		

}
