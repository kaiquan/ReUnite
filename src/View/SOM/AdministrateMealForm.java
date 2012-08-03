/********************************************************************************************************************************************************
Program Name			:	AdministrateMealForm.java
Description				:	A AdministrateMealForm class that is used for Creation, Update & Deletion of Meal record
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Update				:	6-1-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	addMealMenu() : void
						:	validateMealDetails() : Boolean
						:	calculatePrice() : Double
						:	calculateFinalPrice() : Double
						:	displaySummary() : void
						:	download() : void
						:	downloadPDF(String) : void
						:	downloadCSV(String) : void
						:	createTabHeader() : void
						:	newMealTab() : void
						:	createMeal() : void
						:	deleteMeal() : void
						:	updateMeal() : void
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
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.SOM.AdministrateMealControl;
import Controller.SOM.CSVController;
import Controller.MyCalendar;
/********************************************************
 *					Start of Class
 *******************************************************/
public class AdministrateMealForm {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="201,79"
	private JPanel jPanel = null;
	private JPanel jPanel_header = null;
	private JLabel jLabel_mealID = null;
	private JLabel jLabel_MealAvailability = null;
	private JLabel jLabel_mealTitle = null;
	private JLabel jLabel_mealDescription = null;
	private JTextField jTextField_mealID = null;
	private JCheckBox jCheckBox_mealAvailability = null;
	private JTextField jTextField_mealTitle = null;
	private JScrollPane jScrollPane_mealDescription = null;
	private JTextArea jTextArea_mealDescription = null;
	private JPanel jPanel_mealMenu = null;
	private JLabel jLabel_mealType = null;
	private JLabel jLabel_mealMenu = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_mealType = null;
	private JScrollPane jScrollPane_mealMenu = null;
	private JTable jTable_mealMenu = null;
	private JTextField jTextField_mealMenu = null;
	private JLabel jLabel_mealPrice = null;
	private JTextField jTextField_mealPrice = null;
	private JButton jButton_add = null;
	private JCheckBox jCheckBox_mealHalal = null;
	private JCheckBox jCheckBox_mealVegetarian = null;
	private JScrollPane jScrollPane_mealMenuDescription = null;
	private JTextArea jTextArea_mealMenuDescription = null;
	private JPanel jPanel_mealSummary = null;
	private JLabel jLabel_mealTotalPricePerHead = null;
	private JLabel jLabel_mealDiscount = null;
	private JTextField jTextField_mealTotalPricePerHead = null;
	private JTextField jTextField_mealDiscount = null;
	private JSlider jSlider_mealDiscount = null;
	private JScrollPane jScrollPane_mealSummary = null;
	private JTextArea jTextArea_mealSummary = null;
	private JButton jButton_download = null;
	private JButton jButton_upload = null;
	private JButton jButton_delete = null;
	private JButton jButton_update = null;
	private String []Header={"Meal Name","Price/hr","Halal","Vegetarian","Description"};	//for the jtable
	protected DefaultTableModel model= new DefaultTableModel();//for the jTable
	private JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="209,861"
	private JMenuItem jMenuItem_remove = null;
	final JFileChooser fc = new JFileChooser();
	private Thread main=null;
	private Thread progress=null;
	/********************************************************
	 *					Start of UI
	 *******************************************************/
	protected JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(1021, 303));
			jScrollPane.setViewportView(getJPanel());
			jScrollPane.setPreferredSize(new Dimension(1021, 303));
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
			jPanel.add(getJPanel_mealMenu(), null);
			jPanel.add(getJPanel_mealSummary(), null);
		}
		return jPanel;
	}
	/********************************************************
	 *					Start of header
	 *******************************************************/
	private JPanel getJPanel_header() {
		if (jPanel_header == null) {
			jLabel_mealDescription = new JLabel();
			jLabel_mealDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealDescription.setBounds(new Rectangle(50, 120, 81, 30));
			jLabel_mealDescription.setText("Description :");
			jLabel_mealTitle = new JLabel();
			jLabel_mealTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealTitle.setBounds(new Rectangle(50, 80, 81, 30));
			jLabel_mealTitle.setText("Title :");
			jLabel_MealAvailability = new JLabel();
			jLabel_MealAvailability.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_MealAvailability.setBounds(new Rectangle(450, 40, 150, 30));
			jLabel_MealAvailability.setText("Availability :");
			jLabel_mealID = new JLabel();
			jLabel_mealID.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealID.setBounds(new Rectangle(50, 40, 80, 30));
			jLabel_mealID.setText("ID :");
			jPanel_header = new JPanel();
			jPanel_header.setLayout(null);
			jPanel_header.setPreferredSize(new Dimension(800,260));
			jPanel_header.setBounds(new Rectangle(100, 30, 800, 260));
			jPanel_header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_header.add(jLabel_mealID, null);
			jPanel_header.add(jLabel_MealAvailability, null);
			jPanel_header.add(jLabel_mealTitle, null);
			jPanel_header.add(jLabel_mealDescription, null);
			jPanel_header.add(getJTextField_mealID(), null);
			jPanel_header.add(getJCheckBox_mealAvailability(), null);
			jPanel_header.add(getJTextField_mealTitle(), null);
			jPanel_header.add(getJScrollPane_mealDescription(), null);
		}
		return jPanel_header;
	}
	protected JTextField getJTextField_mealID() {
		if (jTextField_mealID == null) {
			jTextField_mealID = new JTextField();
			jTextField_mealID.setEnabled(false);
			jTextField_mealID.setHorizontalAlignment(JTextField.CENTER);
			jTextField_mealID.setText("  Generated After Creation");
			jTextField_mealID.setFont(new Font("Segoe UI", Font.ITALIC, 14));
			jTextField_mealID.setBounds(new Rectangle(130, 40, 200, 30));
		}
		return jTextField_mealID;
	}
	protected JCheckBox getJCheckBox_mealAvailability() {
		if (jCheckBox_mealAvailability == null) {
			jCheckBox_mealAvailability = new JCheckBox();
			jCheckBox_mealAvailability.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jCheckBox_mealAvailability.setText("Not Available");
			jCheckBox_mealAvailability.setFocusable(false);
			jCheckBox_mealAvailability.setFocusPainted(false);
			jCheckBox_mealAvailability.setSelected(true);
			jCheckBox_mealAvailability.setBounds(new Rectangle(550, 40, 200, 30));
			jCheckBox_mealAvailability.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealAvailability.isSelected()){
						jCheckBox_mealAvailability.setText("Available");
					}
					else{
						jCheckBox_mealAvailability.setText("Not Available");
					}
				}
			});
		}
		return jCheckBox_mealAvailability;
	}
	protected JTextField getJTextField_mealTitle() {
		if (jTextField_mealTitle == null) {
			jTextField_mealTitle = new JTextField();
			jTextField_mealTitle.setBounds(new Rectangle(130, 80, 620, 30));
			jTextField_mealTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealTitle.setText("                                                                                  Enter A Meal Title");
			jTextField_mealTitle.setForeground(SystemColor.scrollbar);
			jTextField_mealTitle.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_mealTitle.getText().equals("")){
						jTextField_mealTitle.setText("                                                                                  Enter A Meal Title");
						jTextField_mealTitle.setForeground(SystemColor.scrollbar);
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_mealTitle.getText().equals("                                                                                  Enter A Meal Title")){
						jTextField_mealTitle.setText("");
						jTextField_mealTitle.setForeground(SystemColor.black);
					}
				}
			});
		}
		return jTextField_mealTitle;
	}
	private JScrollPane getJScrollPane_mealDescription() {
		if (jScrollPane_mealDescription == null) {
			jScrollPane_mealDescription = new JScrollPane();
			jScrollPane_mealDescription.setBounds(new Rectangle(130, 120, 620, 100));
			jScrollPane_mealDescription.setViewportView(getJTextArea_mealDescription());
		}
		return jScrollPane_mealDescription;
	}
	protected JTextArea getJTextArea_mealDescription() {
		if (jTextArea_mealDescription == null) {
			jTextArea_mealDescription = new JTextArea();
			jTextArea_mealDescription.setLineWrap(true);
			jTextArea_mealDescription.setWrapStyleWord(true);
			jTextArea_mealDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_mealDescription.setText("\n\n                                                                                Enter A Description");
			jTextArea_mealDescription.setForeground(SystemColor.scrollbar);
			jTextArea_mealDescription.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_mealDescription.getText().equals("")){
						jTextArea_mealDescription.setText("\n\n                                                                                Enter A Description");
						jTextArea_mealDescription.setForeground(SystemColor.scrollbar);
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextArea_mealDescription.getText().equals("\n\n                                                                                Enter A Description")){
						jTextArea_mealDescription.setForeground(SystemColor.black);
						jTextArea_mealDescription.setText("");
					}
				}
			});
		}
		return jTextArea_mealDescription;
	}
	/********************************************************
	 *					Start of body
	 *******************************************************/
	private JPanel getJPanel_mealMenu() {
		if (jPanel_mealMenu == null) {
			jLabel_mealPrice = new JLabel();
			jLabel_mealPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealPrice.setBounds(new Rectangle(590, 280, 15, 30));
			jLabel_mealPrice.setText("$");
			jLabel_mealMenu = new JLabel();
			jLabel_mealMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealMenu.setBounds(new Rectangle(50, 40, 150, 30));
			jLabel_mealMenu.setText("Meal List :");
			jLabel_mealType = new JLabel();
			jLabel_mealType.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealType.setBounds(new Rectangle(50, 80, 150, 30));
			jLabel_mealType.setText("Type :");
			jPanel_mealMenu = new JPanel();
			jPanel_mealMenu.setLayout(null);
			jPanel_mealMenu.setPreferredSize(new Dimension(423,240));
			jPanel_mealMenu.setBounds(new Rectangle(100, 320, 800, 493));
			jPanel_mealMenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_mealMenu.add(jLabel_mealType, null);
			jPanel_mealMenu.add(jLabel_mealMenu, null);
			jPanel_mealMenu.add(getJComboBox_mealType(), null);
			jPanel_mealMenu.add(getJScrollPane_mealMenu(), null);
			jPanel_mealMenu.add(getJTextField_mealMenu(), null);
			jPanel_mealMenu.add(jLabel_mealPrice, null);
			jPanel_mealMenu.add(getJTextField_mealPrice(), null);
			jPanel_mealMenu.add(getJButton_add(), null);
			jPanel_mealMenu.add(getJCheckBox_mealHalal(), null);
			jPanel_mealMenu.add(getJCheckBox_mealVegetarian(), null);
			jPanel_mealMenu.add(getJScrollPane_mealMenuDescription(), null);
		}
		return jPanel_mealMenu;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getJComboBox_mealType() {
		if (jComboBox_mealType == null) {
			jComboBox_mealType = new JComboBox();
			jComboBox_mealType.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			//meal info @http://wiki.answers.com/Q/What_are_the_types_of_meal_services
			jComboBox_mealType.setFocusable(false);
			jComboBox_mealType.addItem("Select a Meal Genre");
			jComboBox_mealType.addItem("Buffet");
			jComboBox_mealType.addItem("Stations");
			jComboBox_mealType.addItem("Family Style");
			jComboBox_mealType.addItem("American or Plated");
			jComboBox_mealType.addItem("French");
			jComboBox_mealType.addItem("Russian");
			jComboBox_mealType.addItem("Butler Service");
			jComboBox_mealType.setBounds(new Rectangle(100, 80, 250, 30));
		}
		return jComboBox_mealType;
	}
	private JScrollPane getJScrollPane_mealMenu() {
		if (jScrollPane_mealMenu == null) {
			jScrollPane_mealMenu = new JScrollPane();
			jScrollPane_mealMenu.setBounds(new Rectangle(50, 120, 700, 150));
			jScrollPane_mealMenu.setViewportView(getJTable_mealMenu());
		}
		return jScrollPane_mealMenu;
	}
	@SuppressWarnings("static-access")
	protected JTable getJTable_mealMenu() {
		if (jTable_mealMenu == null) {
			model.setColumnIdentifiers(Header);
			jTable_mealMenu = new JTable();
			jTable_mealMenu.setModel(model);
			jTable_mealMenu.setOpaque(false);
			jTable_mealMenu.setEnabled(false);
			jTable_mealMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_mealMenu.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_mealMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_mealMenu.setIntercellSpacing(new Dimension(5, 5));
			jTable_mealMenu.setRowSelectionAllowed(true);
			jTable_mealMenu.setShowGrid(false);
			jTable_mealMenu.setFocusable(false);
			jTable_mealMenu.setColumnSelectionAllowed(false);
			jTable_mealMenu.setEnabled(false);
			jTable_mealMenu.setAutoResizeMode(jTable_mealMenu.AUTO_RESIZE_OFF);
			jTable_mealMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
			jTable_mealMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_mealMenu.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTable_mealMenu.getColumnModel().getColumn(3).setPreferredWidth(100);
			jTable_mealMenu.getColumnModel().getColumn(4).setPreferredWidth(500);
			jTable_mealMenu.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseReleased(MouseEvent e) {
		            int r = jTable_mealMenu.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < jTable_mealMenu.getRowCount()) {
		            	jTable_mealMenu.setRowSelectionInterval(r, r);
		            } else {
		            	jTable_mealMenu.clearSelection();
		            }

		            int rowindex = jTable_mealMenu.getSelectedRow();
		            if (rowindex < 0)
		                return;
		            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		                JPopupMenu popup = getJPopupMenu();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});

		}
		return jTable_mealMenu;
	}
	protected JTextField getJTextField_mealMenu() {
		if (jTextField_mealMenu == null) {
			jTextField_mealMenu = new JTextField();
			jTextField_mealMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealMenu.setText("                                                                          Enter A Meal Name");
			jTextField_mealMenu.setForeground(SystemColor.scrollbar);
			jTextField_mealMenu.setBounds(new Rectangle(50, 280, 527, 30));
			jTextField_mealMenu.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_mealMenu.getText().equals("")){
						jTextField_mealMenu.setText("                                                                          Enter A Meal Name");
						jTextField_mealMenu.setForeground(SystemColor.scrollbar);
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_mealMenu.getText().equals("                                                                          Enter A Meal Name")){
						jTextField_mealMenu.setForeground(SystemColor.black);
						jTextField_mealMenu.setText("");
					}
				}
			});
		}
		return jTextField_mealMenu;
	}
	protected JTextField getJTextField_mealPrice() {
		if (jTextField_mealPrice == null) {
			jTextField_mealPrice = new JTextField();
			jTextField_mealPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealPrice.setText("0.00");
			jTextField_mealPrice.setForeground(SystemColor.scrollbar);
			jTextField_mealPrice.setBounds(new Rectangle(600, 280, 150, 30));
			jTextField_mealPrice.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_mealPrice.getText().equals("")){
						jTextField_mealPrice.setText("0.00");
						jTextField_mealPrice.setForeground(SystemColor.scrollbar);
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_mealPrice.getText().equals("0.00")){
						jTextField_mealPrice.setText("");
						jTextField_mealPrice.setForeground(SystemColor.black);
					}
				}
			});
		}
		return jTextField_mealPrice;
	}
	private JButton getJButton_add() {
		if (jButton_add == null) {
			jButton_add = new JButton();
			jButton_add.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_add.setFocusable(false);
			jButton_add.setFocusPainted(false);
			jButton_add.setBounds(new Rectangle(596, 425, 150, 30));
			jButton_add.setText("Add");
			jButton_add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Scanner scanner = new Scanner(getJTextField_mealPrice().getText().toString());
					boolean isDigit=false;
					if (!scanner.hasNextDouble()) {  
					    isDigit=true;   
					}  
					if(getJTextField_mealMenu().getText().equals("")||getJTextField_mealMenu().getText().equals("Enter a Meal Name")){
						JOptionPane.showMessageDialog(null, "Please Enter An Meal Name", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextField_mealMenu().requestFocus();
					}
					else if(getJTextField_mealPrice().getText().equals("")||getJTextField_mealPrice().getText().equals("0.00")){
						JOptionPane.showMessageDialog(null, "Please Enter An Meal Price", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextField_mealPrice().requestFocus();
					}
					else if(getJTextArea_mealMenuDescription().getText().equals("")||getJTextArea_mealMenuDescription().getText().equals("\n\n\n                                                         Enter a Short Description of the Meal")){
						JOptionPane.showMessageDialog(null, "Please Enter An Meal Description", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextArea_mealMenuDescription().requestFocus();
					}
					else if(isDigit){
						JOptionPane.showMessageDialog(null, "Please enter a correct value for price", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_mealPrice().requestFocus();
					}
					else{
						addMeaMenu();
						DecimalFormat fmt = new DecimalFormat("0.00");
						getJTextField_mealTotalPricePerHead().setText(fmt.format(calculateFinalPrice()));
						displaySummary();
					}
				}
			});
		}
		return jButton_add;
	}
	private JCheckBox getJCheckBox_mealHalal() {
		if (jCheckBox_mealHalal == null) {
			jCheckBox_mealHalal = new JCheckBox();
			jCheckBox_mealHalal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jCheckBox_mealHalal.setBounds(new Rectangle(597, 370, 150, 30));
			jCheckBox_mealHalal.setFocusable(false);
			jCheckBox_mealHalal.setFocusPainted(false);
			jCheckBox_mealHalal.setText("Non Halal");
			jCheckBox_mealHalal.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealHalal.isSelected()){
						jCheckBox_mealHalal.setText("Halal");
					}
					else{
						jCheckBox_mealHalal.setText("Non Halal");
					}
				}
			});
		}
		return jCheckBox_mealHalal;
	}
	private JCheckBox getJCheckBox_mealVegetarian() {
		if (jCheckBox_mealVegetarian == null) {
			jCheckBox_mealVegetarian = new JCheckBox();
			jCheckBox_mealVegetarian.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jCheckBox_mealVegetarian.setFocusable(false);
			jCheckBox_mealVegetarian.setFocusPainted(false);
			jCheckBox_mealVegetarian.setBounds(new Rectangle(598, 330, 150, 30));
			jCheckBox_mealVegetarian.setText("Non Vegetarian");
			jCheckBox_mealVegetarian.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealVegetarian.isSelected()){
						jCheckBox_mealVegetarian.setText("Vegetarian");
					}
					else{
						jCheckBox_mealVegetarian.setText("Non Vegetarian");
					}
				}
			});
		}
		return jCheckBox_mealVegetarian;
	}
	private JScrollPane getJScrollPane_mealMenuDescription() {
		if (jScrollPane_mealMenuDescription == null) {
			jScrollPane_mealMenuDescription = new JScrollPane();
			jScrollPane_mealMenuDescription.setBounds(new Rectangle(50, 330, 530, 130));
			jScrollPane_mealMenuDescription.setViewportView(getJTextArea_mealMenuDescription());
		}
		return jScrollPane_mealMenuDescription;	}

	private JTextArea getJTextArea_mealMenuDescription() {
		if (jTextArea_mealMenuDescription == null) {
			jTextArea_mealMenuDescription = new JTextArea();
			jTextArea_mealMenuDescription.setWrapStyleWord(true);
			jTextArea_mealMenuDescription.setLineWrap(true);
			jTextArea_mealMenuDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_mealMenuDescription.setText("\n\n\n                                                         Enter a Short Description of the Meal");
			jTextArea_mealMenuDescription.setForeground(SystemColor.scrollbar);
			jTextArea_mealMenuDescription
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_mealMenuDescription.getText().equals("")){
						jTextArea_mealMenuDescription.setText("\n\n\n                                                         Enter a Short Description of the Meal");
						jTextArea_mealMenuDescription.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_mealMenuDescription.getText().equals("\n\n\n                                                         Enter a Short Description of the Meal")){
								jTextArea_mealMenuDescription.setText("");
								jTextArea_mealMenuDescription.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextArea_mealMenuDescription;
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
			jMenuItem_remove.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jMenuItem_remove.setOpaque(false);
			jMenuItem_remove.setText("Remove");
			jMenuItem_remove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					model.removeRow(getJTable_mealMenu().getSelectedRow());
					DecimalFormat fmt = new DecimalFormat("0.00");
					getJTextField_mealTotalPricePerHead().setText(fmt.format(calculateFinalPrice()));
					displaySummary();
				}
			});
		}
		return jMenuItem_remove;
	}
	/********************************************************
	 *					Start of summary
	 *******************************************************/
	private JPanel getJPanel_mealSummary() {
		if (jPanel_mealSummary == null) {
			jLabel_mealDiscount = new JLabel();
			jLabel_mealDiscount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealDiscount.setBounds(new Rectangle(400, 40, 150, 30));
			jLabel_mealDiscount.setText("Entitled Discount :");
			jLabel_mealTotalPricePerHead = new JLabel();
			jLabel_mealTotalPricePerHead.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealTotalPricePerHead.setBounds(new Rectangle(50, 40, 150, 30));
			jLabel_mealTotalPricePerHead.setText("Total Price Per Head :");
			jPanel_mealSummary = new JPanel();
			jPanel_mealSummary.setLayout(null);
			jPanel_mealSummary.setBounds(new Rectangle(100, 840, 800, 249));
			jPanel_mealSummary.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_mealSummary.add(jLabel_mealTotalPricePerHead, null);
			jPanel_mealSummary.add(jLabel_mealDiscount, null);
			jPanel_mealSummary.add(getJTextField_mealTotalPricePerHead(), null);
			jPanel_mealSummary.add(getJTextField_mealDiscount(), null);
			jPanel_mealSummary.add(getJSlider_mealDiscount(), null);
			jPanel_mealSummary.add(getJScrollPane_mealSummary(), null);
			jPanel_mealSummary.add(getJButton_download(), null);
			jPanel_mealSummary.add(getJButton_upload(), null);
			jPanel_mealSummary.add(getJButton_delete(), null);
			jPanel_mealSummary.add(getJButton_update(), null);
		}
		return jPanel_mealSummary;
	}
	protected JTextField getJTextField_mealTotalPricePerHead() {
		if (jTextField_mealTotalPricePerHead == null) {
			jTextField_mealTotalPricePerHead = new JTextField();
			jTextField_mealTotalPricePerHead.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealTotalPricePerHead.setText("0.00");
			jTextField_mealTotalPricePerHead.setEditable(false);
			jTextField_mealTotalPricePerHead.setBounds(new Rectangle(200, 40, 160, 30));
		}
		return jTextField_mealTotalPricePerHead;
	}
	protected JTextField getJTextField_mealDiscount() {
		if (jTextField_mealDiscount == null) {
			jTextField_mealDiscount = new JTextField();
			jTextField_mealDiscount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealDiscount.setText("0%");
			jTextField_mealDiscount.setEditable(false);
			jTextField_mealDiscount.setBounds(new Rectangle(550, 40, 177, 30));
		}
		return jTextField_mealDiscount;
	}
	protected JSlider getJSlider_mealDiscount() {
		if (jSlider_mealDiscount == null) {
			jSlider_mealDiscount = new JSlider();
			jSlider_mealDiscount.setValue(0);
			jSlider_mealDiscount.setFocusable(false);
			jSlider_mealDiscount.setMaximum(100);
			jSlider_mealDiscount.setMinimum(0);
			jSlider_mealDiscount.setBounds(new Rectangle(400, 80, 337, 30));
			jSlider_mealDiscount.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					getJTextField_mealDiscount().setText(jSlider_mealDiscount.getValue()+"%");
					DecimalFormat fmt = new DecimalFormat("0.00");
					getJTextField_mealTotalPricePerHead().setText(fmt.format(calculateFinalPrice()));
					displaySummary();
				}
			});
		}
		return jSlider_mealDiscount;
	}
	private JScrollPane getJScrollPane_mealSummary() {
		if (jScrollPane_mealSummary == null) {
			jScrollPane_mealSummary = new JScrollPane();
			jScrollPane_mealSummary.setBounds(new Rectangle(50, 79, 312, 133));
			jScrollPane_mealSummary.setViewportView(getJTextArea_mealSummary());
		}
		return jScrollPane_mealSummary;
	}
	private JTextArea getJTextArea_mealSummary() {
		if (jTextArea_mealSummary == null) {
			jTextArea_mealSummary = new JTextArea();
			jTextArea_mealSummary.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_mealSummary.setEditable(false);
			jTextArea_mealSummary.setWrapStyleWord(true);
			jTextArea_mealSummary.setLineWrap(true);
		}
		return jTextArea_mealSummary;
	}
	protected JButton getJButton_download() {
		if (jButton_download == null) {
			jButton_download = new JButton();
			jButton_download.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_download.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/download.png")));
			jButton_download.setBounds(new Rectangle(400, 115, 160, 45));
			jButton_download.setFocusable(false);
			jButton_download.setEnabled(false);
			jButton_download.setFocusPainted(false);
			jButton_download.setText("Download");
			jButton_download.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//SETS THE PATH AND FILE NAME
					fc.setAcceptAllFileFilterUsed(false);
					fc.setFocusable(false);
					fc.setAcceptAllFileFilterUsed(false);
					fc.showSaveDialog(fc);

					final String directory=fc.getSelectedFile().toString();
					 main = new Thread () {
						  @SuppressWarnings("deprecation")
						public void run () {
							  if(validateMealDetails()){
									try {
										download(directory);
									} catch (MalformedURLException e1) {
										e1.printStackTrace();
									} catch (DocumentException e1) {
										e1.printStackTrace();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									displaySummary();
								}
							  else{
									main.interrupt();
									main.stop();
									AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
							  }
						  }
					  };
					progress= new Thread(){
						  @SuppressWarnings("deprecation")
						public void run(){
							  double increment=1;
							  int sleep=300;
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      try {
								        SwingUtilities.invokeLater(new Runnable() {
								         public void run() {
								        	 AdministrateServiceOptionManagement.getJProgressBar().setValue(percent);
								        	 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								          }
								        });
								        Thread.sleep(sleep);
								        if(!main.isAlive()){
								        	AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								        	AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								        	break;
										 }
								        sleep+=100;
								      } catch (InterruptedException e) {
								    	  AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(true);
								      }
								    } 
								 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								 this.stop();
								 this.interrupt();
						  }
					  };
					  progress.start();
					  main.start();
				}
			});
		}
		return jButton_download;
	}
	protected JButton getJButton_upload() {
		if (jButton_upload == null) {
			jButton_upload = new JButton();
			jButton_upload.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_upload.setFocusable(false);
			jButton_upload.setFocusPainted(false);
			jButton_upload.setBounds(new Rectangle(570, 115, 160, 45));
			jButton_upload.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/create.png")));
			jButton_upload.setText("Upload");
			jButton_upload.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					main = new Thread () {
						  public void run () {
							  if(validateMealDetails()){
									createMeal();
								}
							  else{
									main.interrupt();
									AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
									AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
							  }
						  }
					  };
					progress= new Thread(){
						  public void run(){
							  double increment=1;
							  int sleep=300;
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      try {
								        SwingUtilities.invokeLater(new Runnable() {
								         public void run() {
								        	 AdministrateServiceOptionManagement.getJProgressBar().setValue(percent);
								        	 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								          }
								        });
								        Thread.sleep(sleep);
								        if(!main.isAlive()){
								        	AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								        	AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								        	break;
										 }
								        sleep+=100;
								      } catch (InterruptedException e) {
								    	  AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(true);
								      }
								    } 
								 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								 this.interrupt();
						  }
					  };
					  progress.start();
					  main.start();
				}
			});
		}
		return jButton_upload;
	}
	protected JButton getJButton_delete() {
		if (jButton_delete == null) {
			jButton_delete = new JButton();
			jButton_delete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_delete.setFocusable(false);
			jButton_delete.setFocusPainted(false);
			jButton_delete.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/delete.png")));
			jButton_delete.setBounds(new Rectangle(400, 170, 160, 45));
			jButton_delete.setText("Delete");
			jButton_delete.setEnabled(false);
			jButton_delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final int i=JOptionPane.showConfirmDialog(null, "You are about to delete this record\n Are you sure?", "Delete Record", JOptionPane.YES_NO_OPTION);
					main = new Thread () {
						  public void run () {
								if(i==0){
									deleteMeal();
								}
						  }
					  };
					  final Thread a=main;
					progress= new Thread(){
						  public void run(){
							  double increment=1;
							  int sleep=300;
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      try {
								        SwingUtilities.invokeLater(new Runnable() {
								         public void run() {
								        	 AdministrateServiceOptionManagement.getJProgressBar().setValue(percent);
								        	 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								          }
								        });
								        Thread.sleep(sleep);
								        if(!a.isAlive()){
								        	AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								        	break;
										 }
								        sleep+=100;
								      } catch (InterruptedException e) {
								    	  AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(true);
								      }
								    } 
								 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								 this.interrupt();
								 
						  }
					  };
					  progress.start();
					  main.start();
				}
			});
		}
		return jButton_delete;
	}
	protected JButton getJButton_update() {
		if (jButton_update == null) {
			jButton_update = new JButton();
			jButton_update.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_update.setFocusable(false);
			jButton_update.setFocusPainted(false);
			jButton_update.setBounds(new Rectangle(570, 170, 160, 45));
			jButton_update.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/update.png")));
			jButton_update.setText("Update");
			jButton_update.setEnabled(false);
			jButton_update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					main = new Thread () {
						  public void run () {
							  if(validateMealDetails()){
									updateMeal();
								}
							  else{
									main.interrupt();
									AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
									AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
							  }
						  }
					  };
					  final Thread a=main;
					progress= new Thread(){
						  public void run(){
							  double increment=1;
							  int sleep=300;
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      try {
								        SwingUtilities.invokeLater(new Runnable() {
								         public void run() {
								        	 AdministrateServiceOptionManagement.getJProgressBar().setValue(percent);
								        	 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								          }
								        });
								        Thread.sleep(sleep);
								        if(!a.isAlive()){
								        	AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								        	break;
										 }
								        sleep+=100;
								      } catch (InterruptedException e) {
								    	  AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(true);
								      }
								    } 
								 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
								 this.interrupt();
								 
						  }
					  };
					  progress.start();
					  main.start();
				}
			});
		}
		return jButton_update;
	}
	/********************************************************
	 *					End of UI
	 *******************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *					Start of Custom Methods
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: addMealMenu()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To add an mealMenu into the meal 
	 * 					  List Table
	 *******************************************************/
	public void addMeaMenu(){
		String MealMenuName=getJTextField_mealMenu().getText().toString();
		String MealMenuPrice=getJTextField_mealPrice().getText().toString();
		String MealMenuDescription=getJTextArea_mealMenuDescription().getText().toString();
		String MealMenuHalal="NO";
		if(getJCheckBox_mealHalal().isSelected())MealMenuHalal="YES";
		String MealMenuVegetarian="NO";
		if(getJCheckBox_mealVegetarian().isSelected())MealMenuVegetarian="YES";
		
		//ADD THE ENTERTAINMENT
		model.addRow(new Object[]{MealMenuName,MealMenuPrice,MealMenuHalal,MealMenuVegetarian, MealMenuDescription});
		getJTable_mealMenu().setModel(model);
		
		//CLEAR THE TEXTBOX
		getJTextField_mealMenu().setText("                                                                          Enter A Meal Name");
		getJTextField_mealMenu().setForeground(SystemColor.scrollbar);
		getJTextField_mealMenu().requestFocus(false);
		getJTextField_mealPrice().setText("0.00");
		getJTextField_mealPrice().setForeground(SystemColor.scrollbar);
		getJTextField_mealPrice().requestFocus(false);
		getJCheckBox_mealHalal().setSelected(false);
		getJCheckBox_mealVegetarian().setSelected(false);
		getJTextArea_mealMenuDescription().setText("\n\n\n                                                         Enter a Short Description of the Meal");
		getJTextArea_mealMenuDescription().setForeground(SystemColor.scrollbar);
		getJTextArea_mealMenuDescription().requestFocus(false);
		
	}
	
	/********************************************************
	 * Method Name 		: validateMealDetails()
	 * Input Parameter 	: void 
	 * Return 			: boolean
	 * Purpose			: To validate the details before any CRUD
	 * *******************************************************/
	@SuppressWarnings("deprecation")
	public boolean validateMealDetails(){
		boolean success=true;
		
		if(getJTextField_mealTitle().getText().equals("")||getJTextField_mealTitle().getText().equals("                                                                                  Enter A Meal Title")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Title Must Not Be Empty", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextField_mealTitle().requestFocus();
			main.interrupt();
		}
		else if(getJTextArea_mealDescription().getText().equals("")||getJTextArea_mealDescription().getText().equals("\n\n                                                                                Enter A Description")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Description Must Not Be Empty", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextArea_mealDescription().requestFocus();
			main.interrupt();
		}
		else if(getJComboBox_mealType().getSelectedIndex()==0){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please Select a Meal Type", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJComboBox_mealType().requestFocus();
			main.interrupt();
		}
		else if(model.getRowCount()==0){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "There Must Be Dish in the Meal Menu List", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextField_mealMenu().requestFocus();
			main.interrupt();
		}
		
		return success;
	}
	
	/********************************************************
	 * Method Name 		: calculatePrice()
	 * Input Parameter 	: void 
	 * Return 			: double
	 * Purpose 			: To caculate the price before discount
	 *******************************************************/
	public double calculatePrice(){
		AdministrateMealControl control= new AdministrateMealControl();
		control.setModel(model);
		return control.processCalculatePrice();
	}
	
	/********************************************************
	 * Method Name 		: calculateFinalPrice()
	 * Input Parameter 	: void 
	 * Return 			: double
	 * Purpose 			: To caculate the price after discount
	 * *******************************************************/
	public double calculateFinalPrice(){
		AdministrateMealControl control= new AdministrateMealControl();
		control.setModel(model);
		return  control.processCalculateFinalPrice(getJSlider_mealDiscount().getValue());
	}

	/********************************************************
	 * Method Name 		: displaySummary()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To display the summary of the 
	 * 					  current meal record
	 *******************************************************/
	public void displaySummary(){
		String header="====================================\n";
		header+="                                Meal Summary List\n";
		header+="====================================\n\n";
		
		String content="";
		for(int i=0;i<model.getRowCount();i++){
			content+=(i+1)+") "+model.getValueAt(i, 0).toString()+" - $"+model.getValueAt(i, 1).toString()+"\n";
		}
		content+="________________\n";
		content+="Total Price \t:$"+calculatePrice()+"\n";
		content+="Discount \t:"+getJSlider_mealDiscount().getValue()+"%\n";
		content+="Final Price \t:$"+calculateFinalPrice();
		
		
		getJTextArea_mealSummary().setText(header+content);
	}

	/********************************************************
	 * Method Name 		: download()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer
	 *******************************************************/
	public void download(String directory) throws MalformedURLException, DocumentException, IOException{
		
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
			downloadCSV(PDFlink+".csv");
		}
		else if(directory.substring(directory.length()-2).equals(".csv")){
			Scanner sc2= new Scanner(directory);
			String d=".csv";
			sc2.useDelimiter(d);
			while(sc2.hasNext()){
				TXTlink=sc2.next();
			}
			downloadPDF(TXTlink+".pdf");
			downloadCSV(TXTlink+".csv");
		}
		else{
			downloadPDF(directory+".pdf");
			downloadCSV(directory+".csv");
		}
	}
	
	/********************************************************
	 * Method Name 		: downloadPDF()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer in PDF
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void downloadPDF(String path) throws DocumentException, MalformedURLException, IOException{
		String directory=path;
		
		//WRITTING THE PDF
		 Document pdf = new Document (PageSize.A4);
		 PdfWriter.getInstance(pdf, new FileOutputStream(directory));
		 pdf.open ();
		 //SETTING THE HEADER
		 pdf.addCreator("Lee Kai Quan(114173S)");
		 Image image = Image.getInstance("src\\images\\SOM\\Reunite_Header.png");
		 image.scaleAbsolute(550, 100);
		 pdf.add(image);
		 pdf.add(new Paragraph("  "));
		 pdf.add(new Paragraph("  "));
		 //WRITTING THE CONTENT HERE
		 GregorianCalendar g= new GregorianCalendar();
		 pdf.add(new Paragraph("Meal Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID          : "+getJTextField_mealID().getText().toString()));
		 pdf.add(new Paragraph("Title       : "+getJTextField_mealTitle().getText().toString()));
		 pdf.add(new Paragraph("Description : "));
		 pdf.add(new Paragraph(getJTextArea_mealDescription().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		//TABLE FOR THE ENTERTAINMENT MENU
		 float[] colsWidth = {2f, 1f,1f,1f,3f}; //THIS SETS THE COL WIDTH
		 PdfPTable table = new PdfPTable(colsWidth);
		 table.setWidthPercentage(100); 
		 PdfPCell cell1 = new PdfPCell(new Paragraph("Meal Menu"));
		 cell1.setColspan(5);
		 table.addCell(cell1);
		 PdfPCell cell2 = new PdfPCell(new Paragraph("Type : "+getJComboBox_mealType().getSelectedItem().toString()));
		 cell2.setColspan(5);
		 table.addCell(cell2);
		 table.addCell("Meal Name");
		 table.addCell("Price / hr");
		 table.addCell("Hala");
		 table.addCell("Vegetarian");
		 table.addCell("Description");
		 for(int i=0;i<model.getRowCount();i++){
			table.addCell(model.getValueAt(i, 0).toString());
			table.addCell(model.getValueAt(i, 1).toString());
			if(model.getValueAt(i, 2).equals("YES")||model.getValueAt(i, 2).equals("YES")){
				table.addCell("YES");
			}
			if(model.getValueAt(i, 2).equals("NO")||model.getValueAt(i, 2).equals("NO")){
				table.addCell("NO");
			}
			if(model.getValueAt(i, 3).equals("YES")||model.getValueAt(i, 2).equals("YES")){
				table.addCell("YES");
			}
			if(model.getValueAt(i, 3).equals("NO")||model.getValueAt(i, 2).equals("NO")){
				table.addCell("NO");
			}
			table.addCell(model.getValueAt(i, 4).toString());
		}
		 pdf.add(table);
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Entitled Discount \t: "+getJTextField_mealDiscount().getText().toString()));
		 pdf.add(new Paragraph("Total Price \t: $"+getJTextField_mealTotalPricePerHead().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph("                                                                         End                                               "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph());
		 pdf.add(new Paragraph());
		 
		 pdf.close();
		
		//PROMPT SUCCESS
		 progress.interrupt();
		 progress.stop();
		 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
		 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
		 JOptionPane.showMessageDialog(null, "File Downloaded Successfully at "+path, "Downloads", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/********************************************************
	 * Method Name 		: downloadCSV()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer in TXT
	 * @throws IOException 
	 *******************************************************/
	public void downloadCSV(String path) throws IOException{
		CSVController controller= new CSVController();
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		String[]mealHeader=new String[7];
		mealHeader[0]="MEAL_ID";
		mealHeader[1]="MEAL_TITLE";
		mealHeader[2]="MEAL_AVAILABILITY";
		mealHeader[3]="MEAL_DESCRIPTION";
		mealHeader[4]="MEAL_TYPE";
		mealHeader[5]="MEAL_DISCOUNT";
		mealHeader[6]="MEAL_FINAL_PRICE";
		
		String[] mealData= new String[7];
		mealData[0]=getJTextField_mealID().getText().toString();
		mealData[1]=getJTextField_mealTitle().getText().toString();
		if(getJCheckBox_mealAvailability().isSelected())
			mealData[2]="YES";
		else
			mealData[2]="NO";
		mealData[3]=getJTextArea_mealDescription().getText().toString();
		mealData[4]=getJComboBox_mealType().getSelectedItem().toString();
		mealData[5]=getJSlider_mealDiscount().getValue()+"";
		mealData[6]=getJTextField_mealTotalPricePerHead().getText().toString();
		
		String[] mealMenuHeader= new String[5];
		mealMenuHeader[0]="MEAL_MENU_NAME";
		mealMenuHeader[1]="MEAL_MENU_PRICE";
		mealMenuHeader[2]="MEAL_MENU_IS_HALA";
		mealMenuHeader[3]="MEAL_MENU_IS_VEGETARIAN";
		mealMenuHeader[4]="MEAL_MENU_DESCRIPTION";
		
		data.add(mealHeader);
		data.add(mealData);
		data.add(mealMenuHeader);
		
		String[] mealMenuData= new String[5];
		for(int i=0;i<model.getRowCount();i++){
			mealMenuData= new String[5];
			mealMenuData[0]=(model.getValueAt(i, 0).toString());
			mealMenuData[1]=(model.getValueAt(i, 1).toString());
			if(model.getValueAt(i, 2).equals("YES")||model.getValueAt(i, 2).equals("YES")){
				mealMenuData[2]="YES";
			}
			if(model.getValueAt(i, 2).equals("NO")||model.getValueAt(i, 2).equals("NO")){
				mealMenuData[2]="NO";
			}
			if(model.getValueAt(i, 3).equals("YES")||model.getValueAt(i, 3).equals("YES")){
				mealMenuData[3]="YES";
			}
			if(model.getValueAt(i, 3).equals("NO")||model.getValueAt(i, 3).equals("NO")){
				mealMenuData[3]="NO";;
			}
			mealMenuData[4]=(model.getValueAt(i, 4).toString());
			data.add(mealMenuData);
		}

		controller.WriteFile(data, path);
	}
	
	/********************************************************
	 * Method Name 		: createTabHeader()
	 * Input Parameter 	: int
	 * Return 			: void
	 * Purpose 			: To create and set the custom Tab Header.
	 *******************************************************/
	private void createTabHeader(int index){
		CustomTabHeader tab=new CustomTabHeader(AdministrateServiceOptionManagement.getJTabbedPane());
		AdministrateServiceOptionManagement.getJTabbedPane().setTabComponentAt( index,tab );
		
	}
	
	/********************************************************
	 * Method Name		: newMealTab()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To create and set a new Meal Tab content.
	 *******************************************************/
	public void newMealTab(){
		AdministrateMealForm meal=new AdministrateMealForm();
		if(AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()==1){
			System.out.println(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Meal Form",null , meal.getJScrollPane(),null , 1); // SETS THE CONTENT
			createTabHeader(1);	//SETS THE CUSTOM TAB HEADER
			AdministrateServiceOptionManagement.getJTabbedPane().remove(0);
			AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(0);
		}
		else{
			System.out.println(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Meal Form",null , meal.getJScrollPane(),null , AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()); // SETS THE CONTENT
			createTabHeader(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);	//SETS THE CUSTOM TAB HEADER
			AdministrateServiceOptionManagement.getJTabbedPane().remove(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			if(!(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()==AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()-1)){
				AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);
				}
			}
	}
	
	/********************************************************
	 * Method Name		: createMeal()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To create a new Meal 
	 * 					  record in the database
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void createMeal(){
		//PREPARING THE OBJECT VARIABLES
		String title=getJTextField_mealTitle().getText().toString();
		boolean availability=getJCheckBox_mealAvailability().isSelected();
		String description=getJTextArea_mealDescription().getText().toString();
		String type=getJComboBox_mealType().getSelectedItem().toString();
		double price=calculatePrice();
		double discount=getJSlider_mealDiscount().getValue();
		double finalPrice=Double.parseDouble(getJTextField_mealTotalPricePerHead().getText().toString());
		
		//CALLING THE CONTROLLER AND PASSING IN THE PREPARED VARIABLES
		AdministrateMealControl control=new AdministrateMealControl(title, description, type,price,discount,finalPrice,availability,0,false);
		//PASSING IN THE TABLE MODEL TO THE CONTROLLER
		control.setModel(model);
		
		//CREATES THE MEAL RECORD
		String mealID=null;
		mealID=control.processCreateMeal();

			//CHECKS IF ALL MEAL MENU ARE CREATED SUCCESSFULLY
			if(!mealID.equals(null)){
				getJTextField_mealID().setText(mealID);
				getJTextField_mealID().setForeground(SystemColor.black);
				//DISABLES THE UPLOAD BUTTON
				getJButton_upload().setEnabled(false);
				//ENABLES THE UPDATE AND DELETE BUTTON
				getJButton_update().setEnabled(true);
				getJButton_delete().setEnabled(true);
				getJButton_download().setEnabled(true);
				progress.interrupt();
				progress.stop();
				AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
				AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
				JOptionPane.showMessageDialog(null, "Record has been uploaded successfully", "Success", JOptionPane.PLAIN_MESSAGE);
				
			}
			else{
				progress.interrupt();
				progress.stop();
				AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
				AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
				JOptionPane.showMessageDialog(null, "There was an unexpected uploading the Meal record(s)/n1)Try restarting the application.", "Error", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/********************************************************
	 * Method Name 		: deleteMeal()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To delete a new Meal 
	 * 					  record in the database 
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void deleteMeal(){
		String ID=getJTextField_mealID().getText().toString();
		AdministrateMealControl meal=new AdministrateMealControl();
		boolean success=meal.processDeleteMeal(ID);
		if(success){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Record has been deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			//RESETS THE TAB
			newMealTab();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "An unexpected error occured\n1)Try restarting the application.\n2)This record might be tied to an existing package, tied records cannot be deleted.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/********************************************************
	 * Method Name 		: updateMeal()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To update a meal record 
	 * 					  in the database
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void updateMeal(){
		//PREPARING THE OBJECT VARIABLES
		String title=getJTextField_mealTitle().getText().toString();
		String description=getJTextArea_mealDescription().getText().toString();
		String type=getJComboBox_mealType().getSelectedItem().toString();
		boolean availability=getJCheckBox_mealAvailability().isSelected();
		double pricePerHead=calculatePrice();
		double finalPrice=Double.parseDouble(getJTextField_mealTotalPricePerHead().getText().toString());
		double discount=getJSlider_mealDiscount().getValue();
		int hits=0;
		
		//CALLING THE CONTROLLER AND PASSING IN THE PREPARED VARIABLES
		AdministrateMealControl control= new AdministrateMealControl(title, description, type,pricePerHead,discount,finalPrice,availability,hits,false);
		//PASSING IN THE TABLE MODEL TO THE CONTROLLER
		control.setModel(model);
		
		//UPDATE THE MEAL RECORD
		String mealID=getJTextField_mealID().getText().toString();
		boolean success=true;
		success=control.processUpdateMeal(mealID);
		
		//CHECKS FOR UPDATE SUCCESS
		if(success){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Record has been Updated successfully", "Success", JOptionPane.PLAIN_MESSAGE);
			displaySummary();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "There was an unexpected uploading the entertainment record(s)/n1)Try restarting the application.", "Update Failure", JOptionPane.ERROR_MESSAGE);
			displaySummary();
		}
	}
	
}
/********************************************************
 *					End of Class
 *******************************************************/