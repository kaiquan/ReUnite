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
	private JFrame jframe;
	private JPanel panel;
	
	
	
	//Labels

	private JLabel title;
	
	
	//TextBoxes
	JButton userNameTextBox;
	JButton firstNameTextBox;
	JButton lastNameTextBox;
	JButton nricTextBox;
	JButton schoolTextBox;
	JButton emailTextBox;
	JButton telephoneTextBox;
	JButton handphoneTextBox;
	

	//Buttons
	private JButton  updateAccountButton;
	private JButton requestDeleteButton;
	private JButton submitButton;
 	
	
	// ***********************JFrame Method****************
	
	private JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(700,500);
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
	title.setBounds(new Rectangle(10, 30, 300, 100));
	title.setFont(new Font("Dialog", Font.BOLD, 30));
	title.setText("RI ACCOUNTS");
	
	
	
	
	
	//TextBox
	
	
	
	//Buttons
	
	updateAccountButton = new JButton();
	updateAccountButton.setBounds(500,30,150,30);
	updateAccountButton.setText("Update Account");
	updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
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
			handphoneTextBox.getText()); //secretQuestion(),
//				updateAccountButton.setVisible(true);
//				confirmUpdateButton.setVisible(false);

				
			}
		}
	});
	
	requestDeleteButton = new JButton();
	requestDeleteButton.setBounds(500,400,150,30);
	requestDeleteButton.setText("Request Delete");
	
	
	
	
	submitButton = new JButton("Submit");
	submitButton.setBounds(300, 30,150,30);

	
	// Content Pane
	
	//Table
	
	
	
	
//..............................add.......................................
	
		panel.add(title);
		
		panel.add(requestDeleteButton);
		
		panel.add(updateAccountButton);

		panel.add(submitButton);

		panel.add(getTable());

	

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
		
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(Color.GRAY);
		
		
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		
		DefaultTableCellRenderer usernameRenderer = new DefaultTableCellRenderer();
		usernameRenderer.setFont(new Font("Dialog", Font.BOLD, 50));
		
		
	
		
		
		
		return table;

	}
	
	
// .......................................JTable.........................	
	

	// update Button

		public static void main(String a[]){
		
	
		
		ViewRIDetailsRIVIEW viewRiDetails = new ViewRIDetailsRIVIEW();
		viewRiDetails.getJFrame().setVisible(true);
		
			
		//new InsertJTableDatabase();
	
	}

}
