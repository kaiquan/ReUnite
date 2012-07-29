package View.CGL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Controller.CGL.CollectPaymentControl;

import Model.Event;
import javax.swing.JRadioButtonMenuItem;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CollectPaymentForm extends Fonts {

	

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="99,17"
	private JPanel jContentPane = null;
	private JSeparator separator = null;
	private JSeparator separator_1 = null;
	private JButton jButton2 = null;
	private JTextArea jTextArea = null;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="408,386"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JLabel jLabel9 = null;
	private JTextArea jTextArea1 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JRadioButton jRadioButton = null;
	private JRadioButton jRadioButton1 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JTextField jTextField7 = null;
	private JLabel jLabel14 = null;
	private JTextField jTextField8 = null;
	private JButton jButton = null;
	private JLabel jLabel15 = null;
	private JComboBox jComboBox = null;
	final JTree tree = new JTree();
	private String ballroomPrice="";
	private String entertainmentPrice="";  //  @jve:decl-index=0:
	private String mealPrice="";
	private String packageDiscount="";  //  @jve:decl-index=0:
	private JScrollPane pne,pne1;
	private String eventStatus;
	private String status;
	private static String FILE = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			tree.setBounds(new Rectangle(12, 14, 78, 72));
			tree.setBounds(new Rectangle(4, 8, 132, 269));
			jFrame = new JFrame();
			jFrame.setResizable(true);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(new Dimension(722, 359));
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Collect Payment");
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSeparator(), getSeparator().getName());
			jContentPane.add(getSeparator_1(), getSeparator_1().getName());
			jContentPane.add(getJButton2(), null);
			pne = new JScrollPane(getJPanel(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne.setBounds(135, 8, 553, 268);
			pne.setPreferredSize(new Dimension(553, 268));
			jContentPane.add(pne);			
			jContentPane.add(tree, null);
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
                    	jTextField.setText(node.getUserObject().toString());
                    	
                    	CollectPaymentControl c2 = new CollectPaymentControl();     
                    	String eventName=node.getUserObject().toString();
                    	c2.requestSelectedEventDetails((eventName)).get(0);
                    	
                    	Scanner sc = new Scanner(c2.requestSelectedEventDetails((eventName)).get(0));
                    	sc.useDelimiter(",");
                    	String ballroomName=sc.next();
                    	String eventTime=sc.next();
                    	String eventDate=sc.next();
                    	eventStatus=sc.next();
                    	String eventDescription=sc.next();
                    	String guestCountr=sc.next();
                    	String totalPrice=sc.next();
                    	ballroomPrice=sc.next();
                    	entertainmentPrice=sc.next();
                    	mealPrice=sc.next();
                    	String facilty=sc.next();
                    	packageDiscount=sc.next();
                    	
                    	                    	
                    	
                    	
                    	jTextField1.setText(eventDate);
                    	jTextField3.setText(guestCountr);
                    	jTextField4.setText(eventTime);
                    	jTextField5.setText(ballroomName);
                    	jTextField6.setText(totalPrice);
                    	jTextArea1.setText(eventDescription);
                    	jTextField2.setText(facilty);
                    	
                    	
                    	
                    	
                    	if(eventStatus.equals("Awaiting Payment")){
                    		jRadioButton.setSelected(true);
                    		jRadioButton1.setEnabled(false);
                    		jLabel12.setEnabled(false);
                    		
                    		Scanner sc1 = new Scanner(c2.processPaymentDetails(jTextField.getText()).get(0));
                        	sc1.useDelimiter(",");
                        	String totalCost=sc1.next();
                        	String amountPending=sc1.next();
                        	double newAmountPending= (Double.parseDouble(amountPending)/2);
                        	System.out.println(newAmountPending);
                    		jTextField7.setText(String.valueOf(newAmountPending));
                    		status="Awaiting Payment";
                    		
                    		
                    		
                    		
                    	}
                    	
                    	if(eventStatus.equals("Confirmed")){
                    		jRadioButton1.setSelected(true);
                    		jRadioButton.setEnabled(false);
                    		jLabel11.setEnabled(false);
                    		jLabel12.setEnabled(true);
                    		Scanner sc1 = new Scanner(c2.processPaymentDetails(jTextField.getText()).get(0));
                        	sc1.useDelimiter(",");
                        	String totalCost=sc1.next();
                        	String amountPending=sc1.next();
                        	jTextField7.setText(String.valueOf(amountPending));
                        	status="Confirmed";
                    	}
                    	
                    	
                    }
					 
                    }

				
				}
			});
			pne1 = new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne1.setVisible(true);
			pne1.setBounds(10, 14, 119, 264);
			jContentPane.add(pne1);
			}
		return jContentPane;
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
					 // TODO Auto-generated Event stub actionPerformed()
					jButton2.setEnabled(false);
					refresh();
					jButton2.setEnabled(true);
				}
			});
		}
		return jButton2;
	}
	
	private void refresh(){	
		jContentPane.remove(pne1);
		jTextField.setText("");
		jTextField.setText("");
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
		jTextField4.setText("");
		jTextField5.setText("");
		jTextField6.setText("");
		jTextArea1.setText("");
		
		mealPrice="";
		ballroomPrice="";
		entertainmentPrice="";
		packageDiscount="";
		jTextField7.setText("");
		jTextField8.setText("");
		jRadioButton.setSelected(false);
		jRadioButton1.setSelected(false);
		jLabel12.setEnabled(false);
		status="";
		FILE="";
		
		//getJTextArea.setText("");
		DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");				
		generateEvents(events);		
		DefaultTreeModel model = new DefaultTreeModel(events);
		tree.setModel(model);		
		pne1.setViewportView(tree);		
		jContentPane.add(pne1);
		
		pne1.updateUI();
		
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
		}
		return jTextArea;
	}
	
	/**
	 * This method which is called by the jTree
	 * gets the event dates from the controller 
	 * 	class
	 * @return javax.swing.JPanel	
	 */
	
	private void generateEvents(DefaultMutableTreeNode tn)
	{
			
			CollectPaymentControl c2 = new CollectPaymentControl();
			ArrayList<Event> eventList = c2.processSelection();
			DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[eventList.size()];
				
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
	/**
	 * This method initializes jPanel 
	 * contain jPane with all event details	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(129, 401, 110, 16));
			jLabel15.setText("Payment Method :");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(128, 373, 87, 16));
			jLabel14.setText("Amount Paid :");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(127, 344, 119, 16));
			jLabel13.setText("Amount to be Paid :");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(230, 319, 104, 16));
			jLabel12.setText("Second Payment ");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(231, 295, 92, 17));
			jLabel11.setText("First Payment ");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(222, 272, 112, 16));
			jLabel10.setFont(new Font("Dialog", Font.BOLD, 13));
			jLabel10.setText("Payment Details");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(19, 172, 13, 16));
			jLabel9.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel9.setText("+");
			jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(ballroomPrice.equals("")&& entertainmentPrice.equals("")&&(mealPrice.equals("")&& (packageDiscount.equals("")))){
						JOptionPane.showMessageDialog(null, "To view the breakdown of price,"+"\n"+"Please select an event");
					}
					
					else{
					JOptionPane.showMessageDialog(null, "Ballroom Price is " +ballroomPrice+"\n"+"Entertainment Price: "+entertainmentPrice+"\n"+"Meal Price: "+mealPrice +"\n"+"Package Discount: "+packageDiscount);
					}
				}
			});
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(20, 217, 108, 16));
			jLabel8.setText("Event Description :");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(31, 172, 80, 16));
			jLabel7.setText("Total Price :");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(315, 129, 73, 16));
			jLabel6.setText("Ballroom :");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(28, 130, 62, 16));
			jLabel5.setText("Location :");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(312, 89, 85, 16));
			jLabel4.setText("Event Time :");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(312, 49, 85, 16));
			jLabel3.setText("No Of Guests :");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(29, 90, 81, 16));
			jLabel2.setText("Event Date :");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(29, 49, 82, 16));
			jLabel1.setText("Event Name :");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(227, 12, 89, 16));
			jLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
			jLabel.setText("Event Details");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(135, 8, 540, 197));
			jPanel.setPreferredSize(new Dimension(540,480));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(jLabel8, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(getJTextField6(), null);
			jPanel.add(jLabel9, null);
			JScrollPane pne1 = new JScrollPane(getJTextArea1(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			pne1.setVisible(true);
			pne1.setBounds(new Rectangle(139, 209, 371, 36));
			jPanel.add(pne1);
			//jPanel.add(getJTextArea1(), null);
			jPanel.add(jLabel10, null);
			jPanel.add(jLabel11, null);
			jPanel.add(getJRadioButton(), null);
			jPanel.add(getJRadioButton1(), null);
			jPanel.add(jLabel12, null);
			jPanel.add(jLabel13, null);
			jPanel.add(getJTextField7(), null);
			jPanel.add(jLabel14, null);
			jPanel.add(getJTextField8(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(jLabel15, null);
			jPanel.add(getJComboBox(), null);			
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(126, 47, 115, 20));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(126, 90, 112, 20));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(128, 133, 109, 20));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(428, 45, 89, 20));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(429, 87, 87, 20));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(430, 129, 82, 20));
		}
		return jTextField5;
	}

	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(127, 170, 110, 20));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jTextArea1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setBounds(new Rectangle(139, 209, 371, 36));
			jTextArea1.setWrapStyleWord(true);
			jTextArea1.setLineWrap(true);
		}
		return jTextArea1;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setBounds(new Rectangle(202, 293, 21, 21));
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setBounds(new Rectangle(203, 316, 21, 21));
		}
		return jRadioButton1;
	}

	/**
	 * This method initializes jTextField7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBounds(new Rectangle(258, 342, 99, 20));
		}
		return jTextField7;
	}

	/**
	 * This method initializes jTextField8	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setBounds(new Rectangle(260, 371, 96, 20));
		}
		return jTextField8;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(195, 436, 160, 17));
			jButton.setText("Send Email to Guests");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					submitDetails(); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}
	
	private void submitDetails() {
		
		CollectPaymentControl c2 = new CollectPaymentControl();
		
		if(jTextField3.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please Select an event");
			return;
		}
		if(jTextField8.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Please enter the amount paid");
			return;
		}		
		if(!(jTextField8.getText().equals(jTextField7.getText()))){
			JOptionPane.showMessageDialog(null,"The amount paid does not match the "+"Amount to be paid");
			return;
		}		
		
		JOptionPane.showMessageDialog(null, "Update the purchase payment");
			
		if(c2.processUpdatePurchasePayment(jTextField8.getText(), jComboBox.getSelectedItem().toString(), jTextField6.getText(),jTextField.getText())){
			JOptionPane.showMessageDialog(null, "Successfully Updated Purchase Summary");				
		}
		else {
			JOptionPane.showMessageDialog(null, "Failure");
			return;
		}			
		
		
			
		//update eventStatus
		if(status.equals("Awaiting Payment")){
			String eventStatus="Confirmed";
			if(c2.processUpdateEventStatus(jTextField.getText(), eventStatus)==true){
				JOptionPane.showMessageDialog(null, "Successfully Changed Status to confirmed");
				CollectPaymentForm g1 = new CollectPaymentForm();
				FILE=jTextField.getText()+" details"+".pdf"; 
				g1.pdfCreator(FILE,jTextField.getText(),jTextField3.getText(),jTextField1.getText(),jTextField4.getText(),jTextField2.getText(),jTextField5.getText(),jTextField6.getText(),getJTextArea().getText(),ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
				//send email
				JOptionPane.showMessageDialog(null, "Successfully Created Pdf and sent an email*");
				refresh();
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Failure");
				return;
			}
			
			
		}
		
		
		if(status.equals("Confirmed")){
			String eventStatus="Confirmed";
			if(c2.processUpdateEventStatus(jTextField.getText(), eventStatus)==true){
				JOptionPane.showMessageDialog(null, "Successfully Changed Status to confirmed");
				//CollectPaymentForm g1 = new CollectPaymentForm();
				//FILE=jTextField.getText()+" payment_receipt"+".pdf"; 
				//g1.pdfCreator(FILE,jTextField.getText(),jTextField3.getText(),jTextField1.getText(),jTextField4.getText(),jTextField2.getText(),jTextField5.getText(),jTextField6.getText(),getJTextArea().getText(),ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
				//send email
				JOptionPane.showMessageDialog(null, "Successfully sent an email* for payment 2");
				refresh();
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Failure");
				return;
			}
			
			
		}
				
		
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.addItem("Cash");
			jComboBox.addItem("Cheque");
			jComboBox.setBounds(new Rectangle(261, 400, 94, 21));
		}
		return jComboBox;
	}
	
	public void pdfCreator(String file,String eventName, String eventDate, String noOfGuests, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice, String entertainmentPrice, String mealPrice, String packageDiscount) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document,eventName,eventDate,noOfGuests,eventTime,location,ballroom,totalPrice,eventDesc,ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
			addTitlePage(document,eventName,eventDate,noOfGuests,eventTime,location,ballroom,totalPrice,eventDesc,ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
			
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void addMetaData(Document document, String eventName, String noOfGuests, String eventDate, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice2, String entertainmentPrice2, String mealPrice2, String packageDiscount2) {
		document.addTitle(eventName);
		document.addSubject("Event Confirmation");
		document.addKeywords("Event Confirmation,GR Administrator");
		document.addAuthor("GR Administrator");
		document.addCreator("GR Administrator");
	}

	private void addTitlePage(Document document, String eventName, String noOfGuests, String eventDate, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice2, String entertainmentPrice2, String mealPrice2, String packageDiscount2)
			throws DocumentException, MalformedURLException, IOException {
		
		Image image = Image.getInstance("src\\images\\CGL\\Reunion.jpg");
		image.setAbsolutePosition(80f, 700f);
		image.scaleAbsolute(400f, 150f);
		document.add(image);
		Paragraph preface = new Paragraph();
		// We add 6 empty line
		addEmptyLine(preface, 6);
		// Lets write a big header
		preface.add(new Paragraph("****************************************************************************************************************"));
		Paragraph paragraph = new Paragraph("Event Confirmation",Garamond);
		paragraph.setIndentationLeft(150f);
		preface.add(paragraph);
		addEmptyLine(preface, 1);
		preface.add(new Chunk("Event Name:"+eventName));
        preface.add(new Chunk("                         No Of Guests:"+noOfGuests));        
        addEmptyLine(preface, 1);
        preface.add(new Chunk("Event Date:"+eventDate));
        preface.add(new Chunk("                             Event Time: "+eventTime));
        addEmptyLine(preface, 1);
        preface.add(new Chunk("Location:"+location));
        preface.add(new Chunk("                             Ballroom: "+ballroom));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Event Description: "+eventDesc));
 		addEmptyLine(preface,1);
        addEmptyLine(preface, 1);
		preface.add(new Paragraph("****************************************************************************************************************"));
	 	// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Event Confirmation generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		smallBold));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Note: Please be punctual for the event",
		redFont));
		document.add(preface);
		// Start a new page
		document.newPage();
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public static void main(String args[]){
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");          // start application       // select Look and Feel
			}
		catch (Exception ex) {           
		ex.printStackTrace();           
		System.out.print("Error");
		}
		
		CollectPaymentForm c1 = new CollectPaymentForm();
		c1.getJFrame().setVisible(true);
		
	}

}
