package View.CGL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;



import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.JTextArea;



import Controller.CGL.ConsolidateGuestListControl;
import Model.Event;

import java.util.Enumeration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ConsolidateGuestListForm {

	final JTree tree = new JTree();
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="167,6"
	private JPanel jContentPane = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel jLabel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JTextArea jTextArea = null;
	private JTextField textField_5;
	private JLabel jLabel2 = null;
	private String ballroomPrice;
	private String entertainmentPrice;
	private String mealPrice;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(722, 359));
			jFrame.setTitle("Consolidate Guest List");
			jFrame.setResizable(true);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(150, 162, 12, 15));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel2.setText("+");
			jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					
					
					JOptionPane.showMessageDialog(null, "Ballroom Price is " +ballroomPrice+"\n"+"Entertainment Price: "+entertainmentPrice+"\n"+"Meal Price: "+mealPrice);
				
				}
			});
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(153, 222, 109, 16));
			jLabel1.setText("Event Description :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			//final JTree tree = new JTree();
			DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");
			generateEvents(events);
			DefaultTreeModel model = new DefaultTreeModel(events);
		
			
			tree.setModel(model);
			tree.setBounds(10, 14, 119, 264);
			tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if(node !=null ){
                    if (node.isLeaf() == true)
                    {
                    	textField.setText(node.getUserObject().toString());
                    	
                    	ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();         
                    	String eventName=node.getUserObject().toString();
                    	c1.requestSelectedEventDetails((eventName)).get(0);
                    	
                    	Scanner sc = new Scanner(c1.requestSelectedEventDetails((eventName)).get(0));
                    	sc.useDelimiter(",");
                    	String ballroomName=sc.next();
                    	String eventTime=sc.next();
                    	String eventDate=sc.next();
                    	String eventStatus=sc.next();
                    	String guestCountr=sc.next();
                    	String totalPrice=sc.next();
                    	ballroomPrice=sc.next();
                    	entertainmentPrice=sc.next();
                    	mealPrice=sc.next();
                    	
                    	
                    	
                    	
                    	textField_1.setText(eventDate);
                    	textField_2.setText(guestCountr);
                    	textField_3.setText(eventTime);
                    	textField_5.setText(ballroomName);
                    	textField_4.setText(totalPrice);
                    	
                    	
                    }
					 
                    }

				
				}
			});
			//jContentPane.add(tree);
			JScrollPane pne = new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne.setVisible(true);
			pne.setBounds(10, 14, 119, 264);
			jContentPane.add(pne);
			
			JLabel lblEventName = new JLabel("Event Name :");
			lblEventName.setBounds(154, 49, 76, 14);
			jContentPane.add(lblEventName);
			
			JLabel lblEventDescript = new JLabel("Event Date :");
			lblEventDescript.setBounds(154, 87, 66, 14);
			jContentPane.add(lblEventDescript);
			
			JLabel lblNoOfGuests = new JLabel("No Of Guests :");
			lblNoOfGuests.setBounds(435, 48, 80, 14);
			jContentPane.add(lblNoOfGuests);
			
			JLabel lblEventTime = new JLabel("Event Time :");
			lblEventTime.setBounds(435, 86, 74, 14);
			jContentPane.add(lblEventTime);
			
			textField = new JTextField();
			textField.setBounds(244, 46, 137, 20);
			jContentPane.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setBounds(244, 84, 135, 20);
			jContentPane.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setBounds(526, 45, 135, 20);
			jContentPane.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setBounds(526, 83, 135, 20);
			jContentPane.add(textField_3);
			textField_3.setColumns(10);
			
			JLabel lblTotalPrice = new JLabel("Total Price :");
			lblTotalPrice.setBounds(161, 163, 69, 14);
			jContentPane.add(lblTotalPrice);
			
			textField_4 = new JTextField();
			textField_4.setBounds(245, 160, 135, 20);
			jContentPane.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel lblEventDetails = new JLabel("Event Details");
			lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 13));
			lblEventDetails.setBounds(356, 11, 96, 20);
			jContentPane.add(lblEventDetails);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(132, 11, 20, 276);
			jContentPane.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 285, 694, 8);
			jContentPane.add(separator_1);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			
			JLabel lblNewLabel = new JLabel("Location Date :");
			lblNewLabel.setBounds(154, 125, 74, 14);
			lblNewLabel.setText("Location :");
			jContentPane.add(lblNewLabel);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			
			JScrollPane pne1 = new JScrollPane(getJTextArea(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne1.setVisible(true);
			pne1.setBounds(new Rectangle(281, 213, 388, 35));
			jContentPane.add(pne1);
			
			JLabel lblBallroom = new JLabel("Ballroom :");
			lblBallroom.setBounds(437, 124, 78, 14);
			jContentPane.add(lblBallroom);
			
			textField_5 = new JTextField();
			textField_5.setBounds(526, 121, 135, 20);
			jContentPane.add(textField_5);
			jContentPane.add(jLabel2, null);
			textField_5.setColumns(10);
			
		}
		return jContentPane;
	}
	private void generateEvents(DefaultMutableTreeNode tn)
	{
	

		ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
	
		ArrayList<Event> eventList = c1.processExpiredInvitation();
		
		DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[eventList.size()];
		
		
	Outer:
		for (int i=0; i<eventList.size(); i++) {
			for (int j=0; j<tn.getRoot().getChildCount(); j++)
			{
				//System.out.println(tn.getRoot().getChildAt(j).toString() + eventList.get(i).getEventDate().toString());
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
		
//		for (int i = 0; i<eventList.size(); i++)
//		{
//				for (int k = 0; k < nodes.length; k++)
//				{
//					if(nodes[k]==null)
//					{
//						nodes[i] = new DefaultMutableTreeNode(eventList.get(i).getEventDate());
//						
//						for(int j = 0; j<eventList.size(); j++)
//						{
//							if(eventList.get(j).getEventDate().equals(eventList.get(i).getEventDate())){
//								nodes[i].add(new DefaultMutableTreeNode(eventList.get(j).getEventTitle()));
//							}
//						}
//					}
//					else
//					{
//						if(!eventList.get(i).getEventDate().equals(nodes[k].getUserObject().toString()))
//						{
//							nodes[i] = new DefaultMutableTreeNode(eventList.get(i).getEventDate());
//							
//							for(int j = 0; j<eventList.size(); j++)
//							{
//								if(eventList.get(j).getEventDate().equals(eventList.get(i).getEventDate())){
//									nodes[i].add(new DefaultMutableTreeNode(eventList.get(j).getEventTitle()));
//								}
//							}
//						}
//					}
//				}
//			if(nodes[i]!=null){tn.add(nodes[i]);}	
//			
//		}
							
			
		}
		/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String eventStatus="Awaiting Payment";
					
					ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
					if((c1.updateTotalPayableAmount((textField_4.getText()),textField.getText())==true)){
						JOptionPane.showMessageDialog(null, "Payment Amount Updated");
					}
					
					if((c1.processUpdateEventStatus(textField.getText(),eventStatus)==true)){
						JOptionPane.showMessageDialog(null, "Event Status Changed to Awaiting Payment");
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_4.setText("");
						textField_5.setText("");
						jTextArea.setText("");
						jContentPane.remove(tree);
						//jContentPane.add(tree);
						DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");
						generateEvents(events);
						DefaultTreeModel model = new DefaultTreeModel(events);
					
						
						tree.setModel(model);
						tree.setBounds(10, 14, 119, 264);
						tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
							public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
								DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			                    if(node !=null ){
			                    if (node.isLeaf() == true)
			                    {
			                    	textField.setText(node.getUserObject().toString());
			                    	
			                    	ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();         
			                    	String eventName=node.getUserObject().toString();
			                    	c1.requestSelectedEventDetails((eventName)).get(0);
			                    	
			                    	Scanner sc = new Scanner(c1.requestSelectedEventDetails((eventName)).get(0));
			                    	sc.useDelimiter(",");
			                    	String ballroomName=sc.next();
			                    	String eventTime=sc.next();
			                    	String eventDate=sc.next();
			                    	String guestCountr=sc.next();
			                    	String totalPrice=sc.next();
			                    	ballroomPrice=sc.next();
			                    	entertainmentPrice=sc.next();
			                    	mealPrice=sc.next();
			                    	
			                    	
			                    	
			                    	
			                    	textField_1.setText(eventDate);
			                    	textField_2.setText(guestCountr);
			                    	textField_3.setText(eventTime);
			                    	textField_5.setText(ballroomName);
			                    	textField_4.setText(totalPrice);
			                    	
			                    	
			                    }
								 
			                    }

							
							}
						});
						jContentPane.add(tree);
						
						
					}
					
					
					
				}
			});
			jButton1.setBounds(new Rectangle(344, 263, 144, 15));
			jButton1.setText("Send Email To RI");
		}
		return jButton1;
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
			jTextArea.setLineWrap(true);
			
			jTextArea.setWrapStyleWord(true);
			jTextArea.setBounds(new Rectangle(281, 213, 388, 35));
			 
		}
		return jTextArea;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	

	public static void main(String args[]){
				
	try {
                     UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");          // start application       // select Look and Feel
			
				
        }        catch (Exception ex) {            ex.printStackTrace();            System.out.print("Error");
        }
		
		ConsolidateGuestListForm c1 = new ConsolidateGuestListForm();
		c1.getJFrame().setVisible(true);
	       
			
		
			
		}
}
