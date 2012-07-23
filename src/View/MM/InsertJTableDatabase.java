package View.MM;

import javax.swing.*;
import javax.swing.table.*;

import Controller.MySQLController;
import Model.RI;

import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class InsertJTableDatabase {
	JTable table;
	JButton button;

	public static void main(String[] args) {
		new InsertJTableDatabase();
	}

	public InsertJTableDatabase() {
		JFrame frame = new JFrame("Getting Cell Values in JTable");
		JPanel panel = new JPanel();
		table = new JTable();
		JScrollPane pane = new JScrollPane(table);
		button = new JButton("Submit");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Type your database query or controller here
			}});
		
		MySQLController db = new MySQLController();
		RI riModel = new RI();
	
	String col[] = null;
	String data[][] = new String[25][25];
		
		try 
		{
			db.getConnection();
			
			col = riModel.getRITableColumnNames();
			
			ArrayList<RI> tempList = riModel.retrieveUser();
			
			for (int i=0;i<tempList.size();i++) 
			{
				data[i][0] =  tempList.get(i).getFirstName();
				data[i][1] =  tempList.get(i).getLastName();
				data[i][2] =  tempList.get(i).getEmail();
				data[i][3] =  tempList.get(i).getNric();
				data[i][4] =  tempList.get(i).getPassword();
				data[i][5] =  tempList.get(i).getFirstName();
				data[i][6] =  tempList.get(i).getFirstName();
				data[i][7] =  tempList.get(i).getFirstName();
			}
		} 
		catch (Exception e) 
		{
		}
	

		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
	
		
		panel.add(pane);
		panel.add(button);
		frame.add(panel);
		frame.setSize(500, 250);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public Object GetData(JTable table, int row_index, int col_index) {
		return table.getModel().getValueAt(row_index, col_index);
	}
}
