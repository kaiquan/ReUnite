package View.MM;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Model.*;
import Controller.MySQLController;
import Controller.ViewRIDetailsController;

import Controller.CreateRIController;

public class ViewRIDetailsRIVIEW {

	private JTable table;
	private JFrame jframe;
	private JPanel panel;
	
	
	//Labels

	private JLabel title;
	
	
	//TextBoxes

	//Buttons
	private JButton  updateAccountButton;

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
	updateAccountButton.setBounds(500,400,150,30);
	updateAccountButton.setText("Update Account");
	
	
	
	
	submitButton = new JButton("Submit");
	submitButton.setBounds(300, 400,150,30);

	
	// Content Pane
	
	//Table
	
	
	
	
//..............................add.......................................
	
		panel.add(title);

		panel.add(updateAccountButton);

		panel.add(submitButton);

		panel.add(getTable());
		
		panel.add(getUpdateAccountButton()) ;
			
		return panel;
	}
	
	
	
	public JTable getTable() {

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Type your database query or controller here
			}
		});

		MySQLController db = new MySQLController();
		RI riModel = new RI();

		String col[] = null;
		String data[][] = new String[11][11];
 
		try {
			db.getConnection();

			col = riModel.getRITableColumnNames();

			ArrayList<RI> tempList = riModel.retrieveUser();

			for (int i = 0; i < tempList.size(); i++) {
				data[i][0] = tempList.get(i).getUserName();
				data[i][1] = tempList.get(i).getPassword();
				data[i][2] = tempList.get(i).getFirstName();
				data[i][3] = tempList.get(i).getLastName();
				//data[i][4] = tempList.get(i).getDateOfBirth();
				data[i][5] = tempList.get(i).getNric();
				data[i][6] = tempList.get(i).getSchool();
				data[i][7] = tempList.get(i).getEmail();
				data[i][8] = tempList.get(i).getTelephoneNo();
				data[i][9] = tempList.get(i).getTelephoneNo();
			}
		} catch (Exception e) {
		}


		table = new JTable();
		table.setBounds(50,200,600,200);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);

		
		
		
		return table;

	}

	
	public Object GetData(JTable table, int row_index, int col_index) {
		return table.getModel().getValueAt(row_index, col_index);
	}
// .......................................JTable.........................	
	

	// update Button

	private JButton getUpdateAccountButton() {
		
		updateAccountButton.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e) 
			{
				try {
					updateAccountButton(e);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				} 
			}

			private void updateAccountButton(ActionEvent e) {
			
				new CreateRIForm();
			}
		});

	return updateAccountButton;
}
	public static void main(String a[]){
		
	
		
		ViewRIDetailsRIVIEW viewRiDetails = new ViewRIDetailsRIVIEW();
		viewRiDetails.getJFrame().setVisible(true);
		
			
		//new InsertJTableDatabase();
	
	}

}
