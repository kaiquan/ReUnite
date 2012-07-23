package View.MM;
import java.awt.*;

import javax.swing.*;



public class DeleteRIForm extends JFrame {
	

	JTable table;
	private JFrame jframe;
	private JPanel panel;
	
	
	
	
	
	//Labels
	private JLabel nameLabel ;
	private JLabel icLabel;
	private JLabel school;
	
	//TextBoxes
	private JTextField nameTextBox;
	private JTextField icTextBox;
	private JTextField schoolTextBox;
	
	//Buttons
	private JButton  deleteAccountButton;
	
	
	private JFrame getJFrame(){
		jframe = new JFrame();
		jframe.setSize(800,800);
		jframe.setVisible(true);
		jframe.setTitle("Delete");
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setContentPane(getPanel());
		return jframe;
		
	}
	
	
	private JPanel getPanel(){
	panel = new JPanel();	
	panel.setLayout(null);
	panel.setFont(new Font("Dialog", Font.PLAIN, 14));
	
	//Labels
	nameLabel = new JLabel();
	nameLabel.setBounds(new Rectangle(200, 100, 100, 50));
	nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	nameLabel.setText("Enter Name :");
	
	//TextBox
	nameTextBox = new JTextField();
	nameTextBox.setBounds(new Rectangle(300,110,100,30));
	
	
	//Buttons
	
	deleteAccountButton = new JButton();
	deleteAccountButton.setBounds(600,400,150,30);
	deleteAccountButton.setText("Delete Account");
	
	
	
	//Table
	

	
	
	
	
	//.add
	
	//add label
	panel.add(nameLabel);
	
	//add textBox
	panel.add(nameTextBox);
	
	
	//add Button
	panel.add(deleteAccountButton);
	panel.add(getTable());
	
	
	
	
		
		return panel;
		
		
		
	}
	public JTable getTable(){
		
		
		Object[][] riData = 
		{
				{"shahrikin","S885131D","school of it"},
				{"man","IC number","School of school"},
				{"woman","ss","dd"},
				{"woman","ss","dd"},
						
		
		};
		Object [] columnNames = {"Name", "Ic Number","School"};
		table = new JTable(riData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,5));
		table.setBounds(200,170,500,200);
		table.setFillsViewportHeight(true);

		JScrollPane  scrollPane = new JScrollPane(table);
		add(scrollPane);
		return table;
		
		}
	public static void main(String a[]){
		
		
		
	
		DeleteRIForm d = new DeleteRIForm();
		d.getJFrame().setVisible(true);
		

		
	
	}

	
	

}
