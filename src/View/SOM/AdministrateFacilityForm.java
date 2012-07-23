/********************************************************************************************************************************************************
Program Name			:	AdministrateFacilityForm.java
Description				:	A AdministrateFacilityForm class that is used for Creation, Update & Deletion of Facility record
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Update				:	6-1-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	addBallroom() : void
						:	validateFacilityDetails() : Boolean
						:	displaySummary() : void
						:	download() : void
						:	downloadPDF(String) : void
						:	downloadTXT(String) : void
						:	createTabHeader() : void
						:	newFacilityTab() : void
						:	createFacility() : void
						:	deleteFacility() : Boolean
						:	updateFacility() : void
********************************************************************************************************************************************************/
package View.SOM;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.SOM.AdministrateFacilityControl;
import Controller.MyCalendar;

public class AdministrateFacilityForm {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="250,12"
	private JPanel jPanel = null;
	private JPanel jPanel_header = null;
	private JLabel jLabel_facilityName = null;
	private JLabel jLabel_facilityID = null;
	private JLabel jLabel_facilityDescription = null;
	private JLabel jLabel_facilityAddress = null;
	private JTextField jTextField_facilityID = null;
	private JTextField jTextField_facilityName = null;
	private JTextField jTextField_facilityContact = null;
	private JTextField jTextField_facilityAddress = null;
	private JScrollPane jScrollPane_facilityDescription = null;
	private JLabel jLabel_facilityContact = null;
	private JTextArea jTextArea_facilityDescription = null;
	private JPanel jPanel_ballrooms = null;
	private JLabel jLabel_ballroomList = null;
	private JScrollPane jScrollPane_ballroomList = null;
	private JTable jTable_ballroomList = null;
	private JTextField jTextField_ballroomTitle = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_ballroomSize = null;
	private JTextField jTextField_ballroomPrice = null;
	private JScrollPane jScrollPane_ballroomDescription = null;
	private JTextField jTextField_ballroomDiscount = null;
	private JButton jButton_add = null;
	private JTextArea jTextArea_ballroomDescription = null;
	private JPanel jPanel_ballroomSummary = null;
	private JLabel jLabel_facilityParking = null;
	private JCheckBox jCheckBox_facilityParking = null;
	private JScrollPane jScrollPane_facilitySummary = null;
	private JButton jButton_download = null;
	private JButton jButton_update = null;
	private JButton jButton_delete = null;
	private JButton jButton_upload = null;
	private JLabel jLabel_facilityWeekendCost = null;
	private JTextField jTextField_facilityWeekendCost = null;
	private JTextArea jTextArea_facilitySummary = null;
	private String []Header={"Ballroom Name","Price","Entitled Discount","Size","Description"};	//for the jtable
	protected DefaultTableModel model= new DefaultTableModel();//for the jTable
	private JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="209,861"
	private JMenuItem jMenuItem_remove = null;
	final JFileChooser fc = new JFileChooser();
	/********************************************************
	 *					Start of UI
	 *******************************************************/
	protected JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(1021, 303));
			jScrollPane.setViewportView(getJPanel());
		}
		return jScrollPane;
	}
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(1000, 1145));
			jPanel.setBackground(SystemColor.control);
			jPanel.add(getJPanel_header(), null);
			jPanel.add(getJPanel_ballrooms(), null);
			jPanel.add(getJPanel_ballroomSummary(), null);
		}
		return jPanel;
	}
	/********************************************************
	 *					Start of header
	 *******************************************************/
	private JPanel getJPanel_header() {
		if (jPanel_header == null) {
			jLabel_facilityContact = new JLabel();
			jLabel_facilityContact.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityContact.setBounds(new Rectangle(450, 40, 150, 30));
			jLabel_facilityContact.setText("Contact No :");
			jLabel_facilityAddress = new JLabel();
			jLabel_facilityAddress.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityAddress.setBounds(new Rectangle(50, 120, 81, 30));
			jLabel_facilityAddress.setText("Address :");
			jLabel_facilityDescription = new JLabel();
			jLabel_facilityDescription.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityDescription.setBounds(new Rectangle(50, 160, 80, 30));
			jLabel_facilityDescription.setText("Description :");
			jLabel_facilityID = new JLabel();
			jLabel_facilityID.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityID.setBounds(new Rectangle(50, 40, 80, 30));
			jLabel_facilityID.setText("ID :");
			jLabel_facilityName = new JLabel();
			jLabel_facilityName.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityName.setBounds(new Rectangle(50, 80, 80, 30));
			jLabel_facilityName.setText("Name :");
			jPanel_header = new JPanel();
			jPanel_header.setLayout(null);
			jPanel_header.setPreferredSize(new Dimension(800,260));
			jPanel_header.setBounds(new Rectangle(100, 30, 800, 300));
			jPanel_header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_header.add(jLabel_facilityName, null);
			jPanel_header.add(jLabel_facilityID, null);
			jPanel_header.add(jLabel_facilityDescription, null);
			jPanel_header.add(jLabel_facilityAddress, null);
			jPanel_header.add(getJTextField_facilityID(), null);
			jPanel_header.add(getJTextField_facilityName(), null);
			jPanel_header.add(getJTextField_facilityContact(), null);
			jPanel_header.add(getJTextField_facilityAddress(), null);
			jPanel_header.add(getJScrollPane_facilityDescription(), null);
			jPanel_header.add(jLabel_facilityContact, null);
		}
		return jPanel_header;
	}
	protected JTextField getJTextField_facilityID() {
		if (jTextField_facilityID == null) {
			jTextField_facilityID = new JTextField();
			jTextField_facilityID.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityID.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_facilityID.setEnabled(false);
			jTextField_facilityID.setForeground(SystemColor.scrollbar);
			jTextField_facilityID.setText("Generate After Creation");
			jTextField_facilityID.setBounds(new Rectangle(130, 41, 193, 29));
			jTextField_facilityID.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_facilityID.getText().equals("")){
						jTextField_facilityID.setForeground(SystemColor.scrollbar);
						jTextField_facilityID.setText("Generate After Creation");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_facilityID.getText().equals("   Generate After Creation")){
						jTextField_facilityID.setText("");
						jTextField_facilityID.setForeground(SystemColor.black);
					}
				}
			});
		}
		return jTextField_facilityID;
	}
	protected JTextField getJTextField_facilityName() {
		if (jTextField_facilityName == null) {
			jTextField_facilityName = new JTextField();
			jTextField_facilityName.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_facilityName.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityName.setForeground(SystemColor.scrollbar);
			jTextField_facilityName.setText("Enter Facility Name Here");
			jTextField_facilityName.setBounds(new Rectangle(130, 80, 600, 30));
			jTextField_facilityName.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_facilityName.getText().equals("")){
						jTextField_facilityName.setForeground(SystemColor.scrollbar);
						jTextField_facilityName.setText("Enter Facility Name Here");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_facilityName.getText().equals("Enter Facility Name Here")){
						jTextField_facilityName.setText("");
						jTextField_facilityName.setForeground(SystemColor.black);
					}
				}
			});
		}
		return jTextField_facilityName;
	}
	protected JTextField getJTextField_facilityContact() {
		if (jTextField_facilityContact == null) {
			jTextField_facilityContact = new JTextField();
			jTextField_facilityContact.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityContact.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_facilityContact.setForeground(SystemColor.scrollbar);
			jTextField_facilityContact.setText("Enter Contact No");
			jTextField_facilityContact.setBounds(new Rectangle(530, 40, 200, 30));
			jTextField_facilityContact.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_facilityContact.getText().equals("")){
						jTextField_facilityContact.setForeground(SystemColor.scrollbar);
						jTextField_facilityContact.setText("Enter Contact No");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_facilityContact.getText().equals("Enter Contact No")){
						jTextField_facilityContact.setText("");
						jTextField_facilityContact.setForeground(SystemColor.black);
						
					}
				}
			});
		}
		return jTextField_facilityContact;
	}
	protected JTextField getJTextField_facilityAddress() {
		if (jTextField_facilityAddress == null) {
			jTextField_facilityAddress = new JTextField();
			jTextField_facilityAddress.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityAddress.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_facilityAddress.setForeground(SystemColor.scrollbar);
			jTextField_facilityAddress.setText("Enter Facility Adress Here");
			jTextField_facilityAddress.setBounds(new Rectangle(130, 120, 600, 31));
			jTextField_facilityAddress.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_facilityAddress.getText().equals("")){
						jTextField_facilityAddress.setForeground(SystemColor.scrollbar);
						jTextField_facilityAddress.setText("Enter Facility Adress Here");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_facilityAddress.getText().equals("Enter Facility Adress Here")){
						jTextField_facilityAddress.setForeground(SystemColor.black);
						jTextField_facilityAddress.setText("");
					}
				}
			});
		}
		return jTextField_facilityAddress;
	}
	private JScrollPane getJScrollPane_facilityDescription() {
		if (jScrollPane_facilityDescription == null) {
			jScrollPane_facilityDescription = new JScrollPane();
			jScrollPane_facilityDescription.setBounds(new Rectangle(130, 160, 600, 100));
			jScrollPane_facilityDescription.setViewportView(getJTextArea_facilityDescription());
		}
		return jScrollPane_facilityDescription;
	}
	protected JTextArea getJTextArea_facilityDescription() {
		if (jTextArea_facilityDescription == null) {
			jTextArea_facilityDescription = new JTextArea();
			jTextArea_facilityDescription.setWrapStyleWord(true);
			jTextArea_facilityDescription.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextArea_facilityDescription.setForeground(SystemColor.scrollbar);
			jTextArea_facilityDescription.setText("\n\n                                                  Enter a Description Here");
			jTextArea_facilityDescription.setAutoscrolls(true);
			jTextArea_facilityDescription.setLineWrap(true);
			jTextArea_facilityDescription
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_facilityDescription.getText().equals("")){
						jTextArea_facilityDescription.setForeground(SystemColor.scrollbar);
						jTextArea_facilityDescription.setText("\n\n                                                  Enter a Description Here");
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_facilityDescription.getText().equals("\n\n                                                  Enter a Description Here")){
								jTextArea_facilityDescription.setText("");
								jTextArea_facilityDescription.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextArea_facilityDescription;
	}
	/********************************************************
	 *					Start of body
	 *******************************************************/
	private JPanel getJPanel_ballrooms() {
		if (jPanel_ballrooms == null) {
			jLabel_ballroomList = new JLabel();
			jLabel_ballroomList.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_ballroomList.setBounds(new Rectangle(50, 40, 150, 30));
			jLabel_ballroomList.setText("Ballrroom List :");
			jPanel_ballrooms = new JPanel();
			jPanel_ballrooms.setLayout(null);
			jPanel_ballrooms.setBounds(new Rectangle(100, 360, 800, 453));
			jPanel_ballrooms.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_ballrooms.add(jLabel_ballroomList, null);
			jPanel_ballrooms.add(getJScrollPane_ballroomList(), null);
			jPanel_ballrooms.add(getJTextField_ballroomTitle(), null);
			jPanel_ballrooms.add(getJComboBox_ballroomSize(), null);
			jPanel_ballrooms.add(getJTextField_ballroomPrice(), null);
			jPanel_ballrooms.add(getJScrollPane_ballroomDescription(), null);
			jPanel_ballrooms.add(getJTextField_ballroomDiscount(), null);
			jPanel_ballrooms.add(getJButton_add(), null);
		}
		return jPanel_ballrooms;
	}
	private JScrollPane getJScrollPane_ballroomList() {
		if (jScrollPane_ballroomList == null) {
			jScrollPane_ballroomList = new JScrollPane();
			jScrollPane_ballroomList.setBounds(new Rectangle(50, 80, 700, 150));
			jScrollPane_ballroomList.setViewportView(getJTable_ballroomList());
		}
		return jScrollPane_ballroomList;
	}
	@SuppressWarnings("static-access")
	protected JTable getJTable_ballroomList() {
		if (jTable_ballroomList == null) {
			model.setColumnIdentifiers(Header);
			jTable_ballroomList = new JTable(model);
			jTable_ballroomList.setOpaque(false);
			jTable_ballroomList.setEnabled(false);
			jTable_ballroomList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_ballroomList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_ballroomList.setIntercellSpacing(new Dimension(5, 5));
			jTable_ballroomList.setRowSelectionAllowed(true);
			jTable_ballroomList.setShowGrid(false);
			jTable_ballroomList.setFocusable(false);
			jTable_ballroomList.setColumnSelectionAllowed(false);
			jTable_ballroomList.setEnabled(false);
			jTable_ballroomList.setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
			jTable_ballroomList.getColumnModel().getColumn(0).setPreferredWidth(440);
			jTable_ballroomList.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_ballroomList.getColumnModel().getColumn(2).setPreferredWidth(135);
			jTable_ballroomList.getColumnModel().getColumn(3).setPreferredWidth(135);
			jTable_ballroomList.getColumnModel().getColumn(4).setPreferredWidth(563);
			jTable_ballroomList.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseReleased(MouseEvent e) {
		            int r = jTable_ballroomList.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < jTable_ballroomList.getRowCount()) {
		            	jTable_ballroomList.setRowSelectionInterval(r, r);
		            } else {
		            	jTable_ballroomList.clearSelection();
		            }

		            int rowindex = jTable_ballroomList.getSelectedRow();
		            if (rowindex < 0)
		                return;
		            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		                JPopupMenu popup = getJPopupMenu();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});
		}
		return jTable_ballroomList;
	}
	private JPopupMenu getJPopupMenu() {
		if (jPopupMenu == null) {
			jPopupMenu = new JPopupMenu();
			jPopupMenu.setOpaque(false);
			jPopupMenu.setSize(new Dimension(99, 168));
			jPopupMenu.add(getJMenuItem_remove());
			
		}
		return jPopupMenu;
	}
	private JMenuItem getJMenuItem_remove() {
		if (jMenuItem_remove == null) {
			jMenuItem_remove = new JMenuItem();
			jMenuItem_remove.setOpaque(false);
			jMenuItem_remove.setText("Remove");
			jMenuItem_remove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					model.removeRow(getJTable_ballroomList().getSelectedRow());
					displaySummary();
				}
			});
		}
		return jMenuItem_remove;
	}
	protected JTextField getJTextField_ballroomTitle() {
		if (jTextField_ballroomTitle == null) {
			jTextField_ballroomTitle = new JTextField();
			jTextField_ballroomTitle.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomTitle.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_ballroomTitle.setForeground(SystemColor.scrollbar);
			jTextField_ballroomTitle.setText("Enter a Ballroom Name");
			jTextField_ballroomTitle.setBounds(new Rectangle(50, 240, 500, 30));
			jTextField_ballroomTitle.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_ballroomTitle.getText().equals("")){
						jTextField_ballroomTitle.setForeground(SystemColor.scrollbar);
						jTextField_ballroomTitle.setText("Enter a Ballroom Name");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_ballroomTitle.getText().equals("Enter a Ballroom Name")){
						jTextField_ballroomTitle.setForeground(SystemColor.black);
						jTextField_ballroomTitle.setText("");
					}
				}
			});
		}
		return jTextField_ballroomTitle;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getJComboBox_ballroomSize() {
		if (jComboBox_ballroomSize == null) {
			jComboBox_ballroomSize = new JComboBox();
			jComboBox_ballroomSize.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jComboBox_ballroomSize.addItem("Select a Size");
			jComboBox_ballroomSize.addItem("Small (10 - 25px)");
			jComboBox_ballroomSize.addItem("Medium (26 - 50px)");
			jComboBox_ballroomSize.addItem("Large (50px Above)");
			jComboBox_ballroomSize.setFocusable(false);
			jComboBox_ballroomSize.setBounds(new Rectangle(563, 240, 187, 30));
		}
		return jComboBox_ballroomSize;
	}
	protected JTextField getJTextField_ballroomPrice() {
		if (jTextField_ballroomPrice == null) {
			jTextField_ballroomPrice = new JTextField();
			jTextField_ballroomPrice.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomPrice.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_ballroomPrice.setForeground(SystemColor.scrollbar);
			jTextField_ballroomPrice.setText("Enter Price");
			jTextField_ballroomPrice.setBounds(new Rectangle(563, 290, 187, 30));
			jTextField_ballroomPrice.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_ballroomPrice.getText().equals("")){
						jTextField_ballroomPrice.setForeground(SystemColor.scrollbar);
						jTextField_ballroomPrice.setText("Enter Price");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_ballroomPrice.getText().equals("Enter Price")){
						jTextField_ballroomPrice.setForeground(SystemColor.black);
						jTextField_ballroomPrice.setText("");
					}
				}
			});
		}
		return jTextField_ballroomPrice;
	}
	private JScrollPane getJScrollPane_ballroomDescription() {
		if (jScrollPane_ballroomDescription == null) {
			jScrollPane_ballroomDescription = new JScrollPane();
			jScrollPane_ballroomDescription.setBounds(new Rectangle(50, 290, 500, 130));
			jScrollPane_ballroomDescription.setViewportView(getJTextArea_ballroomDescription());
		}
		return jScrollPane_ballroomDescription;
	}
	protected JTextArea getJTextArea_ballroomDescription() {
		if (jTextArea_ballroomDescription == null) {
			jTextArea_ballroomDescription = new JTextArea();
			jTextArea_ballroomDescription.setLineWrap(true);
			jTextArea_ballroomDescription.setWrapStyleWord(true);
			jTextArea_ballroomDescription.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextArea_ballroomDescription.setForeground(SystemColor.scrollbar);
			jTextArea_ballroomDescription.setText("\n\n                          Enter a Short Description of the Ballroom");
			jTextArea_ballroomDescription
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_ballroomDescription.getText().equals("")){
						jTextArea_ballroomDescription.setForeground(SystemColor.scrollbar);
						jTextArea_ballroomDescription.setText("\n\n                          Enter a Short Description of the Ballroom");
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_ballroomDescription.getText().equals("\n\n                          Enter a Short Description of the Ballroom")){
								jTextArea_ballroomDescription.setForeground(SystemColor.black);
								jTextArea_ballroomDescription.setText("");
							}
						}
					});
		}
		return jTextArea_ballroomDescription;
	}
	protected JTextField getJTextField_ballroomDiscount() {
		if (jTextField_ballroomDiscount == null) {
			jTextField_ballroomDiscount = new JTextField();
			jTextField_ballroomDiscount.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomDiscount.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_ballroomDiscount.setForeground(SystemColor.scrollbar);
			jTextField_ballroomDiscount.setText("Enter Entitled Discount");
			jTextField_ballroomDiscount.setBounds(new Rectangle(563, 335, 187, 30));
			jTextField_ballroomDiscount.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_ballroomDiscount.getText().equals("")){
						jTextField_ballroomDiscount.setForeground(SystemColor.scrollbar);
						jTextField_ballroomDiscount.setText("Enter Entitled Discount");
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_ballroomDiscount.getText().equals("Enter Entitled Discount")){
						jTextField_ballroomDiscount.setForeground(SystemColor.black);
						jTextField_ballroomDiscount.setText("");
					}
				}
			});
		}
		return jTextField_ballroomDiscount;
	}
	private JButton getJButton_add() {
		if (jButton_add == null) {
			jButton_add = new JButton();
			jButton_add.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_add.setFocusable(false);
			jButton_add.setFocusPainted(false);
			jButton_add.setBounds(new Rectangle(563, 390, 187, 30));
			jButton_add.setText("Add");
			jButton_add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Scanner scanPrice = new Scanner(getJTextField_ballroomPrice().getText().toString());
					boolean priceIsDigit=false;
					if (!scanPrice.hasNextDouble()) {  
						priceIsDigit=true;   
					}  
					Scanner scanDiscount = new Scanner(getJTextField_ballroomDiscount().getText().toString());
					boolean discountIsDigit=false;
					if (!scanDiscount.hasNextDouble()) {  
						discountIsDigit=true;   
					}  
					if(getJTextField_ballroomTitle().getText().equals("")||getJTextField_ballroomTitle().getText().equals("                                      Enter a Ballroom Name")){
						JOptionPane.showMessageDialog(null, "Please Enter An Ballroom Name", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_ballroomTitle().requestFocus();
					}
					else if(getJTextArea_ballroomDescription().getText().equals("")||getJTextArea_ballroomDescription().getText().equals("\n\n                          Enter a Short Description of the Ballroom")){
						JOptionPane.showMessageDialog(null, "Please Enter An Ballroom Description", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextArea_ballroomDescription().requestFocus();
					}
					else if(getJComboBox_ballroomSize().getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Please Select An Ballroom Size", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJComboBox_ballroomSize().requestFocus();
					}
					else if(getJTextField_ballroomPrice().getText().equals("")||getJTextField_ballroomPrice().getText().equals("            Enter Price")){
						JOptionPane.showMessageDialog(null, "Please Enter A Price", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_ballroomPrice().requestFocus();
					}
					else if(discountIsDigit){
						JOptionPane.showMessageDialog(null, "Please enter a correct value for discount", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_ballroomDiscount().requestFocus();
					}
					else if(getJTextField_ballroomDiscount().getText().equals("")||getJTextField_ballroomDiscount().getText().equals("     Enter Entitled Discount")){
						JOptionPane.showMessageDialog(null, "Please Enter A Discount Percantage", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_ballroomDiscount().requestFocus();
					}
					else if(priceIsDigit){
						JOptionPane.showMessageDialog(null, "Please enter a correct value for price", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_ballroomPrice().requestFocus();
					}
					else{
						addBallroom();
						displaySummary();
					}
				}
			});
		}
		return jButton_add;
	}
	/********************************************************
	 *					Start of summary
	 *******************************************************/
	private JPanel getJPanel_ballroomSummary() {
		if (jPanel_ballroomSummary == null) {
			jLabel_facilityWeekendCost = new JLabel();
			jLabel_facilityWeekendCost.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityWeekendCost.setBounds(new Rectangle(400, 40, 150, 30));
			jLabel_facilityWeekendCost.setText("Weekend Extra Cost :     $");
			jLabel_facilityParking = new JLabel();
			jLabel_facilityParking.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_facilityParking.setBounds(new Rectangle(50, 40, 79, 30));
			jLabel_facilityParking.setText("Parking :");
			jPanel_ballroomSummary = new JPanel();
			jPanel_ballroomSummary.setLayout(null);
			jPanel_ballroomSummary.setBounds(new Rectangle(101, 854, 801, 255));
			jPanel_ballroomSummary.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_ballroomSummary.add(jLabel_facilityParking, null);
			jPanel_ballroomSummary.add(getJCheckBox_facilityParking(), null);
			jPanel_ballroomSummary.add(getJScrollPane_facilitySummary(), null);
			jPanel_ballroomSummary.add(getJButton_download(), null);
			jPanel_ballroomSummary.add(getJButton_update(), null);
			jPanel_ballroomSummary.add(getJButton_delete(), null);
			jPanel_ballroomSummary.add(getJButton_upload(), null);
			jPanel_ballroomSummary.add(jLabel_facilityWeekendCost, null);
			jPanel_ballroomSummary.add(getJTextField_facilityWeekendCost(), null);
		}
		return jPanel_ballroomSummary;
	}
	protected JCheckBox getJCheckBox_facilityParking() {
		if (jCheckBox_facilityParking == null) {
			jCheckBox_facilityParking = new JCheckBox();
			jCheckBox_facilityParking.setBounds(new Rectangle(150, 40, 160, 30));
			jCheckBox_facilityParking.setFocusable(false);
			jCheckBox_facilityParking.setFocusPainted(false);
			jCheckBox_facilityParking.setText("As Per Usual");
			jCheckBox_facilityParking.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_facilityParking.isSelected()){
						jCheckBox_facilityParking.setText("Free Parking");
					}
					else{
						jCheckBox_facilityParking.setText("As Per Usual");
					}
				}
			});
		}
		return jCheckBox_facilityParking;
	}
	protected JTextField getJTextField_facilityWeekendCost() {
		if (jTextField_facilityWeekendCost == null) {
			jTextField_facilityWeekendCost = new JTextField();
			jTextField_facilityWeekendCost.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_facilityWeekendCost.setForeground(SystemColor.scrollbar);
			jTextField_facilityWeekendCost.setText("0.00");
			jTextField_facilityWeekendCost.setBounds(new Rectangle(550, 40, 177, 30));
			jTextField_facilityWeekendCost
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_facilityWeekendCost.getText().equals("")){
						jTextField_facilityWeekendCost.setForeground(SystemColor.scrollbar);
						jTextField_facilityWeekendCost.setText("0.00");
					}
					else{
						Scanner scan = new Scanner(jTextField_facilityWeekendCost.getText().toString());
						boolean IsDigit=false;
						if (!scan.hasNextDouble()) {  
							IsDigit=true;   
						} 
						if(IsDigit){
							JOptionPane.showMessageDialog(null, "Please enter a correct value", "Warnning", JOptionPane.WARNING_MESSAGE);
							jTextField_facilityWeekendCost.requestFocus();
						}
						else{
							displaySummary();
						}
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextField_facilityWeekendCost.getText().equals("0.00")){
								jTextField_facilityWeekendCost.setForeground(SystemColor.black);
								jTextField_facilityWeekendCost.setText("");
							}
						}
					});
		}
		return jTextField_facilityWeekendCost;
	}
	private JScrollPane getJScrollPane_facilitySummary() {
		if (jScrollPane_facilitySummary == null) {
			jScrollPane_facilitySummary = new JScrollPane();
			jScrollPane_facilitySummary.setBounds(new Rectangle(50, 79, 312, 133));
			jScrollPane_facilitySummary.setViewportView(getJTextArea_facilitySummary());
		}
		return jScrollPane_facilitySummary;
	}
	protected JTextArea getJTextArea_facilitySummary() {
		if (jTextArea_facilitySummary == null) {
			jTextArea_facilitySummary = new JTextArea();
			jTextArea_facilitySummary.setWrapStyleWord(true);
			jTextArea_facilitySummary.setLineWrap(true);
			jTextArea_facilitySummary.setEditable(false);
		}
		return jTextArea_facilitySummary;
	}
	protected JButton getJButton_download() {
		if (jButton_download == null) {
			jButton_download = new JButton();
			jButton_download.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_download.setIcon(new ImageIcon(getClass().getResource("/images/download.png")));
			jButton_download.setText("Download");
			jButton_download.setEnabled(false);
			jButton_download.setFocusable(false);
			jButton_download.setFocusPainted(false);
			jButton_download.setBounds(new Rectangle(400, 100, 160, 45));
			jButton_download.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(validateFacilityDetails()){
						try {
							download();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (DocumentException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return jButton_download;
	}
	protected JButton getJButton_update() {
		if (jButton_update == null) {
			jButton_update = new JButton();
			jButton_update.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_update.setBounds(new Rectangle(570, 170, 160, 45));
			jButton_update.setEnabled(false);
			jButton_update.setFocusable(false);
			jButton_update.setFocusPainted(false);
			jButton_update.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
			jButton_update.setText("Update");
			jButton_update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(validateFacilityDetails()){
						updateBallroom();
						displaySummary();
					}
				}
			});
		}
		return jButton_update;
	}
	protected JButton getJButton_delete() {
		if (jButton_delete == null) {
			jButton_delete = new JButton();
			jButton_delete.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_delete.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
			jButton_delete.setFocusable(false);
			jButton_delete.setFocusPainted(false);
			jButton_delete.setEnabled(false);
			jButton_delete.setBounds(new Rectangle(400, 170, 160, 45));
			jButton_delete.setText("Delete");
			jButton_delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int i=JOptionPane.showConfirmDialog(null, "You are about to delete this record\n Are you sure?", "Delete Record", JOptionPane.YES_NO_OPTION);
					if(i==0){
						if(deleteBallroom()){
							JOptionPane.showMessageDialog(null, "Record has been deleted successfully", "Success", JOptionPane.PLAIN_MESSAGE);
							newFacilityTab();
						}
						else{
							JOptionPane.showMessageDialog(null, "There was an unexpected error deleting the recor\nnTry restarting the application.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
		return jButton_delete;
	}
	protected JButton getJButton_upload() {
		if (jButton_upload == null) {
			jButton_upload = new JButton();
			jButton_upload.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_upload.setBounds(new Rectangle(570, 100, 160, 45));
			jButton_upload.setFocusable(false);
			jButton_upload.setFocusPainted(false);
			jButton_upload.setIcon(new ImageIcon(getClass().getResource("/images/create.png")));
			jButton_upload.setText("Upload");
			jButton_upload.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(validateFacilityDetails()){
						createBallroom();
						displaySummary();
					}
				}
			});
		}
		return jButton_upload;
	}
	/********************************************************
	 *					End of UI
	 *******************************************************/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	/********************************************************
	 *				Start of Custom Methods
	 *******************************************************/
	
	/********************************************************
	 * Method Name : addBallroom
	 * Input Parameter : NIL 
	 * Purpose : To add an Ballroom into the Ballroom List Table
	 * Return :void
	 * Tested : Success
	 *******************************************************/
	public void addBallroom(){
		//prepare the data
		String name=getJTextField_ballroomTitle().getText().toString();
		String price = getJTextField_ballroomPrice().getText().toString();
		String discount = getJTextField_ballroomDiscount().getText().toString();
		String size=getJComboBox_ballroomSize().getSelectedItem().toString();
		String description=getJTextArea_ballroomDescription().getText().toString();
		//add the data
		model.addRow(new Object[]{name,price,discount,size,description});
		getJTable_ballroomList().setModel(model);
		//reset the fields
		getJTextField_ballroomTitle().setForeground(SystemColor.scrollbar);
		getJTextField_ballroomTitle().setText("                                      Enter a Ballroom Name");
		getJTextField_ballroomPrice().setForeground(SystemColor.scrollbar);
		getJTextField_ballroomPrice().setText("            Enter Price");
		getJTextField_ballroomDiscount().setForeground(SystemColor.scrollbar);
		getJTextField_ballroomDiscount().setText("     Enter Entitled Discount");
		getJTextArea_ballroomDescription().setForeground(SystemColor.scrollbar);
		getJTextArea_ballroomDescription().setText("\n\n                          Enter a Short Description of the Ballroom");
		getJComboBox_ballroomSize().setSelectedIndex(0);
		
		displaySummary();
	}
	/********************************************************
	 * Method Name : validateFacilityDetails
	 * Input Parameter : NIL 
	 * Purpose : To validate the details before any CRUD
	 * Return :boolean
	 * Tested : 
	 * *******************************************************/
	public boolean validateFacilityDetails(){
		boolean validate=true;
		Scanner scan = new Scanner(jTextField_facilityWeekendCost.getText().toString());
		boolean IsDigit=false;
		if (!scan.hasNextDouble()) {  
			IsDigit=true;   
		} 
		if(IsDigit){
			validate=false;
			JOptionPane.showMessageDialog(null, "Please enter a correct weekend cost value", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJTextField_facilityWeekendCost().requestFocus();
		}
		else if(getJTextField_facilityContact().getText().equals("")||getJTextField_facilityContact().getText().equals("          Enter Contact No")){
			validate=false;
			JOptionPane.showMessageDialog(null, "Contact Number Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_facilityContact().requestFocus();
		}
		else if(getJTextField_facilityName().getText().equals("")||getJTextField_facilityName().getText().equals("                                                 Enter Facility Name Here")){
			JOptionPane.showMessageDialog(null, "Facility Name Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_facilityName().requestFocus();
			validate=false;
		}
		else if(getJTextField_facilityAddress().getText().equals("")||getJTextField_facilityAddress().getText().equals("                                                Enter Facility Adress Here")){
			JOptionPane.showMessageDialog(null, "Facility Address Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_facilityAddress().requestFocus();
			validate=false;
		}
		else if(getJTextArea_facilityDescription().getText().equals("")||getJTextArea_facilityDescription().getText().equals("\n\n                                                  Enter a Description Here")){
			JOptionPane.showMessageDialog(null, "Facility Description Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextArea_facilityDescription().requestFocus();
			validate=false;
		}
		else if(model.getRowCount()==0){
			JOptionPane.showMessageDialog(null, "Facility's Ballroom Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_ballroomTitle().requestFocus();
			validate=false;
		}
		return validate;
	}
	
	/********************************************************
	 * Method Name 		: displaySummary()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To display the summary of the form
	 *******************************************************/
	public void displaySummary(){
		String header="====================================\n";
		header+="            Ballroom Summary List\n";
		header+="====================================\n\n";
		
		String content="";
		for(int i=0;i<model.getRowCount();i++){
			content+=(i+1)+") "+model.getValueAt(i, 0).toString()+" - $"+model.getValueAt(i, 1).toString()+"\n";
		}
		content+="________________\n";
		content+="Total Ballrooms \t:$"+model.getRowCount()+"\n";
		content+="Weekend Extra Cost \t:$"+getJTextField_facilityWeekendCost().getText().toString();

		
		if(model.getRowCount()==0){
			//DOES NOTHING IF THERE IS NO BALLROOM
		}
		else{
			getJTextArea_facilitySummary().setText(header+content);
		}
	}
	
	/********************************************************
	 * Method Name 		: download()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer in PDF & TXT
	 *******************************************************/
	public void download() throws MalformedURLException, DocumentException, IOException{
		//setting the file and path name
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFocusable(false);
		fc.setAcceptAllFileFilterUsed(false);
		fc.showSaveDialog(fc);

		String directory=null;
		directory=fc.getSelectedFile().toString();
		String PDFlink="";
		String TXTlink="";
		if(directory.substring(directory.length()-4).equals(".pdf")){
			Scanner sc1= new Scanner(directory);
			String d=".pdf";
			sc1.useDelimiter(d);
			while(sc1.hasNext()){
				PDFlink=sc1.next();
			}
			downloadPDF(PDFlink+".pdf");
			downloadTXT(PDFlink+".r");
		}
		else if(directory.substring(directory.length()-2).equals(".r")){
			Scanner sc2= new Scanner(directory);
			String d=".r";
			sc2.useDelimiter(d);
			while(sc2.hasNext()){
				TXTlink=sc2.next();
			}
			downloadPDF(TXTlink+".pdf");
			downloadTXT(TXTlink+".r");
		}
		else{
			downloadPDF(directory+".pdf");
			downloadTXT(directory+".r");
		}
	}
	/********************************************************
	 * Method Name 		: downloadPDF()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer in PDF
	 *******************************************************/
	public void downloadPDF(String path) throws MalformedURLException, IOException, DocumentException{
		String directory=path;
		
		//writting the pdf
		 Document pdf = new Document (PageSize.A4);
		 PdfWriter.getInstance(pdf, new FileOutputStream(directory));
		 pdf.open ();
		 //SETTING THE HEADER
		 pdf.addCreator("Lee Kai Quan(114173S)");
		 Image image = Image.getInstance("src\\images\\Reunite_Header.png");
		 image.scaleAbsolute(550, 100);
		 pdf.add(image);
		 pdf.add(new Paragraph("  "));
		 pdf.add(new Paragraph("  "));
		 //WRITTING THE CONTENT HERE
		 GregorianCalendar g= new GregorianCalendar();
		 pdf.add(new Paragraph("Facility Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID          : "+getJTextField_facilityID().getText().toString()));
		 pdf.add(new Paragraph("Title       : "+getJTextField_facilityName().getText().toString()));
		 pdf.add(new Paragraph("Address     : "+getJTextField_facilityAddress().getText().toString()));
		 pdf.add(new Paragraph("Contact     : "+getJTextField_facilityContact().getText().toString()));
		 pdf.add(new Paragraph("Description : "));
		 pdf.add(new Paragraph(getJTextArea_facilityDescription().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		//TABLE FOR THE BALLROOM
		 float[] colsWidth = {2f, 1f,1f,3f}; // Code 1
		 PdfPTable table = new PdfPTable(colsWidth);
		 table.setWidthPercentage(100); // Code 2
		 PdfPCell cell = new PdfPCell(new Paragraph("Ballroom List"));
		 cell.setColspan(4);
		 table.addCell(cell);
		 table.addCell("Ballroom Name");
		 table.addCell("Price / hr");
		 table.addCell("Size");
		 table.addCell("Description");
		for(int i=0;i<model.getRowCount();i++){
			table.addCell(model.getValueAt(i, 0).toString());
			table.addCell("$"+model.getValueAt(i, 1).toString());
			table.addCell(model.getValueAt(i, 3).toString());
			table.addCell(model.getValueAt(i, 4).toString());
		}
		 pdf.add(table);
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Parking : "+getJCheckBox_facilityParking().getText().toString()));
		 pdf.add(new Paragraph("Weekwend Sur Charge   : $"+getJTextField_facilityWeekendCost().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph("                                                                         End                                               "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph());
		 pdf.add(new Paragraph());
		 
		 pdf.close();
		
		//prompt success
		 JOptionPane.showMessageDialog(null, "File Downloaded Successfully at "+path, "Downloads", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/********************************************************
	 * Method Name 		: downloadTXT()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer
	 *******************************************************/
	public void downloadTXT(String path){
		//one line for set information wit';'
		//multiple line for menu with';'
	}
	
	/********************************************************
	 * Method Name : createTabHeader
	 * Input Parameter : nil
	 * Purpose : To create and set the custom Tab Header.
	 * Return : nil
	 * Tested : Success
	 *******************************************************/
	private void createTabHeader(int index){
		CustomTabHeader tab=new CustomTabHeader(AdministrateSystemOptionManagement.getJTabbedPane());
		AdministrateSystemOptionManagement.getJTabbedPane().setTabComponentAt( index,tab );
		
	}
	
	/********************************************************
	 * Method Name : newFacilityTab
	 * Input Parameter : nil 
	 * Purpose : To create and set a new Ballroom Tab content.
	 * Return : nil
	 *******************************************************/
	public void newFacilityTab(){
		
		AdministrateFacilityForm meal=new AdministrateFacilityForm();
		if(AdministrateSystemOptionManagement.getJTabbedPane().getTabCount()==1){
			System.out.println(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex());
			AdministrateSystemOptionManagement.getJTabbedPane().insertTab("New Facility",null , meal.getJScrollPane(),null , 1); // sets the content
			createTabHeader(1);	//sets the custom tab header
			AdministrateSystemOptionManagement.getJTabbedPane().remove(0);
			AdministrateSystemOptionManagement.getJTabbedPane().setSelectedIndex(0);
		}
		else{
			System.out.println(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex());
			AdministrateSystemOptionManagement.getJTabbedPane().insertTab("New Facility",null , meal.getJScrollPane(),null , AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()); // sets the content
			createTabHeader(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()-1);	//sets the custom tab header
			AdministrateSystemOptionManagement.getJTabbedPane().remove(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex());
			if(!(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()==AdministrateSystemOptionManagement.getJTabbedPane().getTabCount()-1)){
				AdministrateSystemOptionManagement.getJTabbedPane().setSelectedIndex(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()-1);
				}
			}
	}
	
	/********************************************************
	 * Method Name : createBallroom
	 * Input Parameter : NIL 
	 * Purpose : To create a new Ballroom record in the database
	 * Return :void
	 * Tested : Success
	 *******************************************************/
	public void createBallroom(){
		//preparing the facility details
		String contact=getJTextField_facilityContact().getText().toString();
		String name=getJTextField_facilityName().getText().toString();
		String address=getJTextField_facilityAddress().getText().toString();
		String description=getJTextArea_facilityDescription().getText().toString();
		boolean parking=getJCheckBox_facilityParking().isSelected();
		double weekendCost=Double.parseDouble(getJTextField_facilityWeekendCost().getText().toString());
		
		//passing in the facility details to control
		AdministrateFacilityControl control= new AdministrateFacilityControl(name, description, address,contact, parking, weekendCost);
		control.setModel(model);
		
		//creates the facility
		String facilityID=null;
		facilityID=control.processCreateFacility();

			//check if all ballroom are created successfully
			if(!facilityID.equals(null)){
				JOptionPane.showMessageDialog(null, "Record has been uploaded successfully", "Success", JOptionPane.PLAIN_MESSAGE);
				getJTextField_facilityID().setText(facilityID);
				getJTextField_facilityID().setForeground(SystemColor.black);
				//disable the create button?
				getJButton_upload().setEnabled(false);
				//enable delete and update button
				getJButton_update().setEnabled(true);
				getJButton_delete().setEnabled(true);
				getJButton_download().setEnabled(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "There was an unexpected uploading the Ballroom record(s)/nTry restarting the application.", "Warning", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/********************************************************
	 * Method Name : deleteBallroom
	 * Input Parameter : NIL 
	 * Purpose : To Delete the ballroom record in the database
	 * Return :boolean
	 * Tested : Success
	 *******************************************************/
	public boolean deleteBallroom(){
		String ID=getJTextField_facilityID().getText().toString();
		AdministrateFacilityControl control=new AdministrateFacilityControl();
		boolean success=control.processDeleteFacility(ID);
		return success;
	}
	
	/********************************************************
	 * Method Name : updateBallroom
	 * Input Parameter : NIL 
	 * Purpose : To update the ballroom record in the database
	 * Return :void
	 *******************************************************/
	public void updateBallroom(){
		//prepares the facility object to be pass into the controller
		String name=getJTextField_facilityName().getText().toString();
		String contact=getJTextField_facilityContact().getText().toString();
		String address=getJTextField_facilityAddress().getText().toString();
		String description=getJTextArea_facilityDescription().getText().toString();
		boolean parking=getJCheckBox_facilityParking().isSelected();
		double extraCost=Double.parseDouble(getJTextField_facilityWeekendCost().getText().toString());
		
		//pass in the details into the controller
		AdministrateFacilityControl control= new AdministrateFacilityControl(name, description, address, contact, parking, extraCost);
		control.setModel(model);
		
		String facilityID=getJTextField_facilityID().getText().toString();
		boolean success=true;
		success=control.processUpdateFacility(facilityID);
		
		//check update success
		if(success){
			JOptionPane.showMessageDialog(null, "Record has been Updated successfully", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "There was an unexpected uploading the entertainment record(s)/nTry restarting the application.", "Update Failure", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
