package View.CGL;
//**********************************************************
//Project : OOADPJ (IT2297)
//Admin No: 111942S
//
//Author: A Ameenudeen
//Class Name:CancelEventForm.java
//
//Honor Code: I pledge that this program represents my own
//program code.
//*********************************************************
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Controller.CGL.CancelEventControl;
import Model.Event;
import javax.swing.WindowConstants;

public class CancelEventForm {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="66,8"
	private JPanel jContentPane = null;
	private JScrollPane pne = null;
	private JTree tree = null;
	private JLabel lblEventName = null;
	private JLabel lblEventDescript = null;
	private JLabel lblNoOfGuests = null;
	private JLabel lblEventTime = null;
	private JTextField textField = null;
	private JTextField textField_1 = null;
	private JTextField textField_2 = null;
	private JTextField textField_3 = null;
	private JLabel lblTotalPrice = null;
	private JTextField textField_4 = null;
	private JLabel lblEventDetails = null;
	private JSeparator separator = null;
	private JSeparator separator_1 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JLabel lblNewLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JScrollPane pne1 = null;
	private JTextArea jTextArea = null;
	private JLabel lblBallroom = null;
	private JTextField textField_5 = null;
	private String ballroomPrice="";  //  @jve:decl-index=0:
	private String entertainmentPrice="";
	private String mealPrice="";
	private String packageDiscount="";  //  @jve:decl-index=0:
	private JButton jButton = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(722, 359));
			jFrame.setTitle("Cancel Event");
			jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			jFrame.setResizable(false);
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */


	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblBallroom = new JLabel("Ballroom :");
			lblBallroom.setBounds(new java.awt.Rectangle(437,124,78,14));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(153, 222, 109, 16));
			jLabel1.setText("Event Description :");
			lblNewLabel = new JLabel("Location Date :");
			lblNewLabel.setBounds(new java.awt.Rectangle(154,125,74,14));
			lblNewLabel.setText("Location :");
			lblEventDetails = new JLabel("Event Details");
			lblEventDetails.setBounds(new java.awt.Rectangle(356,11,96,20));
			lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 13));
			lblTotalPrice = new JLabel("Total Price :");
			lblTotalPrice.setBounds(new Rectangle(154, 164, 69, 14));
			lblEventTime = new JLabel("Event Time :");
			lblEventTime.setBounds(new java.awt.Rectangle(435,86,74,14));
			lblNoOfGuests = new JLabel("No Of Guests :");
			lblNoOfGuests.setBounds(new java.awt.Rectangle(435,48,80,14));
			lblEventDescript = new JLabel("Event Date :");
			lblEventDescript.setBounds(new java.awt.Rectangle(154,87,66,14));
			lblEventName = new JLabel("Event Name :");
			lblEventName.setBounds(new java.awt.Rectangle(154,49,76,14));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBounds(new Rectangle(9, 8, 10, 10));
			jContentPane.add(getPne(), getPne().getName());
			jContentPane.add(lblEventName, lblEventName.getName());
			jContentPane.add(lblEventDescript, lblEventDescript.getName());
			jContentPane.add(lblNoOfGuests, lblNoOfGuests.getName());
			jContentPane.add(lblEventTime, lblEventTime.getName());
			jContentPane.add(getTextField(), getTextField().getName());
			jContentPane.add(getTextField_1(), getTextField_1().getName());
			jContentPane.add(getTextField_2(), getTextField_2().getName());
			jContentPane.add(getTextField_3(), getTextField_3().getName());
			jContentPane.add(lblTotalPrice, lblTotalPrice.getName());
			jContentPane.add(getTextField_4(), getTextField_4().getName());
			jContentPane.add(lblEventDetails, lblEventDetails.getName());
			jContentPane.add(getSeparator(), getSeparator().getName());
			jContentPane.add(getSeparator_1(), getSeparator_1().getName());
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(lblNewLabel, lblNewLabel.getName());
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getPne1(), getPne1().getName());
			jContentPane.add(lblBallroom, lblBallroom.getName());
			jContentPane.add(getTextField_5(), getTextField_5().getName());
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTree() {
		if (tree == null) {
			tree = new JTree();
			DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");
			generateEvents(events);
			DefaultTreeModel model = new DefaultTreeModel(events);
			tree.setModel(model);
			tree.setBounds(10, 14, 119, 264);
			tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
				//Once the event is selected,It retrieves the event details for that respective event from the database.
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if(node !=null ){
                    if (node.isLeaf() == true)
                    {	
                    	//Set the event name into a text box and retrieve the details from the controller and splits them up
                    	//using the Scanner class
                    	textField.setText(node.getUserObject().toString());
                    	CancelEventControl c1 = new CancelEventControl();         
                    	String eventName=node.getUserObject().toString();
                    	c1.requestSelectedEventDetails((eventName)).get(0);
                    	Scanner sc = new Scanner(c1.requestSelectedEventDetails((eventName)).get(0));
                    	sc.useDelimiter("~");
                    	String ballroomName=sc.next();
                    	String eventTime=sc.next();
                    	String eventDate=sc.next();
                    	String eventStatus=sc.next();
                    	String eventDescription=sc.next();
                    	String guestCountr=sc.next();
                    	String totalPrice=sc.next();
                    	ballroomPrice=sc.next();
                    	entertainmentPrice=sc.next();
                    	mealPrice=sc.next();
                    	String facilty=sc.next();
                    	packageDiscount=sc.next();
                    	
                    	//Once the items are split using the scanner class it sets them to the
                    	//respective textbox
                 	        	
                    	textField_1.setText(eventDate);
                    	textField_2.setText(guestCountr);
                    	textField_3.setText(eventTime);
                    	textField_5.setText(ballroomName);
                    	textField_4.setText(totalPrice);
                    	jTextArea.setText(eventDescription);
                    	jTextField.setText(facilty);
                    	                   	
                    }
					 
                }
                    
				
			}
			});
		}
		return tree;
	}

	/**
	 * This method initializes pne	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPne()
	{
		if (pne == null) {
			pne = new JScrollPane(getTree(),
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne.setBounds(new java.awt.Rectangle(10,14,119,264));
			pne.setVisible(true);
		}
		return pne;
	}

	/**
	 * This method initializes textField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField() 
	{
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(new java.awt.Rectangle(244,46,137,20));
			textField.setEnabled(true);
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}

	/**
	 * This method initializes textField_1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_1() 
	{
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(new java.awt.Rectangle(244,84,135,20));
			textField_1.setEnabled(true);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	/**
	 * This method initializes textField_2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_2() 
	{
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setBounds(new java.awt.Rectangle(526,45,135,20));
			textField_2.setEnabled(true);
			textField_2.setEditable(false);
			textField_2.setColumns(10);
		}
		return textField_2;
	}

	/**
	 * This method initializes textField_3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_3() 
	{
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setBounds(new java.awt.Rectangle(526,83,135,20));
			textField_3.setEnabled(true);
			textField_3.setEditable(false);
			textField_3.setColumns(10);
		}
		return textField_3;
	}

	/**
	 * This method initializes textField_4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_4() 
	{
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setBounds(new Rectangle(245, 160, 93, 20));
			textField_4.setEnabled(true);
			textField_4.setEditable(false);
			textField_4.setColumns(10);
		}
		return textField_4;
	}

	/**
	 * This method initializes separator	
	 * 	
	 * @return javax.swing.JSeparator	
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(new java.awt.Rectangle(132,11,20,276));
			separator.setOrientation(SwingConstants.VERTICAL);
		}
		return separator;
	}

	/**
	 * This method initializes separator_1	
	 * 	
	 * @return javax.swing.JSeparator	
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(new java.awt.Rectangle(10,285,694,8));
		}
		return separator_1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(344, 263, 144, 15));
			jButton1.setText("Cancel Event");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jTextField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Please select an event");
						return;
					}
					else
					{
						promptsConfirmation();
					}
				}
			});
		}
		return jButton1;
	}
	
	//Prompts whether the event really needs to be cancelled or not
	private void promptsConfirmation() {
		String[] options={"YES","NO"};
		int n = JOptionPane.showOptionDialog(null,"Are you sure you want to cancel this event?","Please Confirm", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE, null,  options,options[1]);
		
		 if(n == 0){
			 
			 	//If the GR Administrator wants to cancel the event,it will prompt the GR administrator to write
			 	//reason for cancellation
	            System.out.println("YES Clicked");
	            String reason="";	            
	            reason=JOptionPane.showInputDialog(null,"Reason for cancellation");	            
	            if(reason.equals(""))
	            {
	            	JOptionPane.showMessageDialog(null, "Please enter the reason for cancellation");	            	
	            	return;
	            }
	            
	            else{
	            		//once the reason is entered
	            		//the program sends an email to the guests regarding the cancellation
	            		//update cancellation table
	            		//set event status to cancelled
	            	
	            	
	            		            	
	            	CancelEventControl c1 = new CancelEventControl();
	            	if( c1.processCancellation(textField.getText(), textField_1.getText(),reason, "Cancelled",jTextField.getText())==true){
	            		JOptionPane.showMessageDialog(null, "Event Cancelled Successfully");
	            	}
	            	
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "Error in processing cancellation");
	            	}
	            	refresh();

	            }
	        }

	       // NO OPTION CLICKED
	        if(n == 1){
	            System.out.println("NO Clicked");
	            
	        }

	       // CROSS CLICKED
	        if(n == -1){
	            System.out.println("Cross Clicked");
	        }

		
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(26, 294, 86, 21));
			jButton2.setText("Refresh");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refresh();
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(244, 122, 137, 20));
			jTextField.setEditable(false);
			jTextField.setEnabled(true);
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(281, 213, 388, 35));
			jTextArea.setWrapStyleWord(true);
			jTextArea.setLineWrap(true);
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}

	/**
	 * This method initializes pne1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPne1() {
		if (pne1 == null) {
			pne1 = new JScrollPane(getJTextArea(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne1.setBounds(new Rectangle(281, 213, 388, 35));
			pne1.setEnabled(false);
			pne1.setVisible(true);
		}
		return pne1;
	}

	/**
	 * This method initializes textField_5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setBounds(new java.awt.Rectangle(526,121,135,20));
			textField_5.setEnabled(true);
			textField_5.setEditable(false);
			textField_5.setColumns(10);
		}
		return textField_5;
	}
	//This method basically refreshes the UI
	//For example when an event is consolidated,cancelled or used to collect payment.
	//Once the objective is completed for the event this method would refresh the whole
	//JFrame
	
	private void refresh(){	
		jContentPane.remove(pne);
		textField.setText("");
		jTextField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_4.setText("");
		textField_5.setText("");
		jTextArea.setText("");
		ballroomPrice="";
		entertainmentPrice="";
		mealPrice="";
		packageDiscount="";
		DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");				
		generateEvents(events);		
		DefaultTreeModel model = new DefaultTreeModel(events);
		tree.setModel(model);		
		pne.setViewportView(tree);		
		jContentPane.add(pne);
		
		pne.updateUI();
		
	}
	//This method gets events from database and make sures that 
	//events with the same date will be put into the same folder in the JTree
	private void generateEvents(DefaultMutableTreeNode tn)
	{
		CancelEventControl c1 = new CancelEventControl();
		ArrayList<Event> eventList = c1.processSelection();
		DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[eventList.size()];
		if(eventList.size()==0)
		{
			JOptionPane.showMessageDialog(null, "No events to display");
		}
		
		else{
		
		
	Outer:
		for (int i=0; i<eventList.size(); i++) {
			for (int j=0; j<tn.getRoot().getChildCount(); j++)
			{
				
				if (eventList.get(i).getEventDate().toString().equals(tn.getRoot().getChildAt(j).toString()))
				{
					
					((DefaultMutableTreeNode)tn.getRoot().getChildAt(j)).add(new DefaultMutableTreeNode(eventList.get(i).getEventName()));
					continue Outer;
				}
			}
			
			nodes[i] = new DefaultMutableTreeNode(eventList.get(i).getEventDate());
			nodes[i].add(new DefaultMutableTreeNode(eventList.get(i).getEventName()));
			tn.add(nodes[i]);
			
		}
		
		}
		
									
			
		}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(339, 160, 44, 21));
			jButton.setText("?");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ballroomPrice.equals("")&& entertainmentPrice.equals("")&&(mealPrice.equals("")&& (packageDiscount.equals("")))){
						JOptionPane.showMessageDialog(null, "To view the breakdown of price,"+"\n"+"Please select an event");
					}
					
					else{
					JOptionPane.showMessageDialog(null, "Ballroom Price is " +ballroomPrice+"\n"+"Entertainment Price: "+entertainmentPrice+"\n"+"Meal Price: "+mealPrice +"\n"+"Package Discount: "+packageDiscount);
					}

				}
			});
		}
		return jButton;
	}

	public static void main(String args[]){
		
		//JTATTOO SET LOOK AND FEEL
		try 
		{
			 UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");   
		}        
		catch (Exception ex) 
		{           
			ex.printStackTrace();           
			System.out.print("Error");
		}

		CancelEventForm c1 = new CancelEventForm();
		c1.getJFrame().setLocationRelativeTo(null);
		c1.getJFrame().setVisible(true);
  			
}
	

}
