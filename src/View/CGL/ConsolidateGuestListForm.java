package View.CGL;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Controller.EmailController;
import Controller.MyCalendar;
import Controller.MySQLController;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;




import Controller.CGL.ConsolidateGuestListControl;
import Model.Event;

import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class ConsolidateGuestListForm extends Fonts{

	
	private JTree tree = new JTree();
	JScrollPane pne;
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
	private String ballroomPrice="";  //  @jve:decl-index=0:
	private String entertainmentPrice="";
	private String mealPrice="";
	private String packageDiscount="";
	private static String FILE = null;

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
				//+ Label Message Dialog
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
					if(ballroomPrice.equals("")&& entertainmentPrice.equals("")&&(mealPrice.equals("")&& (packageDiscount.equals("")))){
						JOptionPane.showMessageDialog(null, "To view the breakdown of price,"+"\n"+"Please select an event");
					}
					
					else{
				JOptionPane.showMessageDialog(null, "Ballroom Price is " +ballroomPrice+"\n"+"Entertainment Price: "+entertainmentPrice+"\n"+"Meal Price: "+mealPrice +"\n"+"Package Discount: "+packageDiscount);
				}
				}
			});
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(153, 222, 109, 16));
			jLabel1.setText("Event Description :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			DefaultMutableTreeNode events = new DefaultMutableTreeNode("Events");
			generateEvents(events);
			DefaultTreeModel model = new DefaultTreeModel(events);
			tree.setModel(model);
			tree.setBounds(10, 14, 119, 264);
			tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
				//Once the event is selected,It retreives the event details for that respective event from the database.
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if(node !=null ){
                    if (node.isLeaf() == true)
                    {	
                    	//Set the event name into a text box and retreive the details from the controller and splits them up
                    	//using the Scanner class
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
			pne = new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		
		if(eventList.size()==0){
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
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(jTextField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Please Select an event");
						return;
					}
					
					String eventStatus="Awaiting Payment";
					
					ConsolidateGuestListControl c1 = new ConsolidateGuestListControl();
					if((c1.updateTotalPayableAmount((textField_4.getText()),textField.getText())==true)){
						System.out.println("Payment Amount Updated");
                    	FILE=textField.getText()+".pdf";                    	
                    	ConsolidateGuestListForm g1 = new ConsolidateGuestListForm();
                    	g1.pdfCreator(FILE,textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),jTextField.getText(),textField_5.getText(),textField_4.getText(),getJTextArea().getText(),ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);                    	
                    	System.out.println("PDF CREATED SUCCESSFULLY");                    	
                    	EmailController email = new EmailController();                    	
                    	File pdf = new File(FILE);
                    	String[] test={"anniyan123456789@hotmail.com"};                    	
                    	try {
							email.sendEmail("TEXT", test, "TEST EMAIL", "TESTING EMAIL", pdf, "Payment");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if((c1.processUpdateEventStatus(textField.getText(),eventStatus)==true)){
							System.out.println("Event Status Changed to Awaiting Payment");
							refresh();						
						}
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
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jButton2.setEnabled(false);
					refresh();
					jButton2.setEnabled(true);
				}
			});
		}
		return jButton2;
	}
	
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
	 * @param packageDiscount 
	 * @param mealPrice 
	 * @param entertainmentPrice 
	 * @param ballroomPrice 
	 * @param eventDesc 
	 * @param totalPrice 
	 * @param ballroom 
	 * @param location 
	 * @param eventTime 
	 * @param noOfGuests 
	 * @param eventDate 
	 * @param eventName 
	 * @param packageDiscount2 
	 * @param File 
	 * @param packageDiscount2 
	 * @param mealPrice2 
	 * @param entertainmentPrice2 
	 * @param ballroomPrice2 
	 * @param string7 
	 * @param string6 
	 * @param string5 
	 * @param string4 
	 * @param string3 
	 * @param string2 
	 * @param string 
	 * @param packageDiscount3 
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	public void pdfCreator(String file,String eventName, String eventDate, String noOfGuests, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice, String entertainmentPrice, String mealPrice, String packageDiscount) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(eventName+".pdf"));
			document.open();
			addMetaData(document,eventName,eventDate,noOfGuests,eventTime,location,ballroom,totalPrice,eventDesc,ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
			addTitlePage(document,eventName,eventDate,noOfGuests,eventTime,location,ballroom,totalPrice,eventDesc,ballroomPrice,entertainmentPrice,mealPrice,packageDiscount);
			
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void addMetaData(Document document, String eventName, String eventDate, String noOfGuests, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice2, String entertainmentPrice2, String mealPrice2, String packageDiscount2) {
		document.addTitle(eventName);
		document.addSubject("Payment Notification");
		document.addKeywords("Payment,GR Administrator");
		document.addAuthor("GR Administrator");
		document.addCreator("GR Administrator");
	}

	private void addTitlePage(Document document, String eventName, String eventDate, String noOfGuests, String eventTime, String location, String ballroom, String totalPrice, String eventDesc, String ballroomPrice2, String entertainmentPrice2, String mealPrice2, String packageDiscount2)
			throws DocumentException, MalformedURLException, IOException {
		
		Image image = Image.getInstance("src\\images\\CGL\\Reunion.jpg");
		image.setAbsolutePosition(80f, 700f);
		image.scaleAbsolute(400f, 150f);
		document.add(image);
		Paragraph preface = new Paragraph();
		// We add 6 empty line
		addEmptyLine(preface, 6);
		// Lets write a big header
		Paragraph paragraph = new Paragraph("Payment Notification",Garamond);
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
		Paragraph paragraph1 = new Paragraph("Payment Details",Garamond);
		paragraph1.setIndentationLeft(170f);
		preface.add(paragraph1);
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Total Ballroom Price : $"+ballroomPrice2));
		addEmptyLine(preface,1);
		preface.add(new Paragraph("Total Entertainment Price : $"+entertainmentPrice2));
		addEmptyLine(preface,1);
		preface.add(new Paragraph("Total Meal Price : $"+mealPrice2));
		addEmptyLine(preface,1);
		preface.add(new Paragraph("Package Discount: $"+packageDiscount2));
		addEmptyLine(preface,1);
		preface.add(new Paragraph("Total Price :$"+totalPrice));
		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Please make your first payment(50% of total Price) Of $"+(Double.parseDouble(totalPrice)/2)+" as soon as possible :",smallBold));
		addEmptyLine(preface,1);
		preface.add(new Paragraph("****************************************************************************************************************"));
	 	// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Payment Notification generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		smallBold));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Terms & Conditions: Once payment is made,there will be no refund .",
		redFont));
		document.add(preface);
		// Start a new page
		document.newPage();
	}

	private  void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	

	public static void main(String args[]){
				
	try {
                     UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");          // start application       // select Look and Feel
			
				
        }        catch (Exception ex) {            ex.printStackTrace();            System.out.print("Error");
        }
		
		ConsolidateGuestListForm c1 = new ConsolidateGuestListForm();
		c1.getJFrame().setVisible(true);
	       
			
		
			
		}
}
