/********************************************************************************************************************************************************
Program Name			:	AdministratePackageForm.java
Description				:	A AdministratePackageForm that is used for Creation, Update & Deletion of Package record
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Update				:	6-2-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	retrieveBallroom() : void
						:	retrieveBallroom(String) : void
						: 	retrieveBallroomByFacility(String) : void
						:	
********************************************************************************************************************************************************/
package View.SOM;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import Controller.MyCalendar;
import Controller.SOM.AdministrateBallroomControl;
import Controller.SOM.AdministrateEntertainmentControl;
import Controller.SOM.AdministrateFacilityControl;
import Controller.SOM.AdministrateMealControl;
import Controller.SOM.AdministratePackageControl;
import Controller.SOM.CSVController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

@SuppressWarnings("unused")
public class AdministratePackageForm {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="336,71"
	private JPanel jPanel = null;
	private JPanel jPanel_header = null;
	private JLabel jLabel_packageID = null;
	private JLabel jLabel_packageAvailability = null;
	private JLabel jLabel_packageTitle = null;
	private JLabel jLabel_packageDescription = null;
	private JTextField jTextField_packageID = null;
	private JCheckBox jCheckBox_packageAvailability = null;
	private JTextField jTextField_packageTitle = null;
	private JScrollPane jScrollPane_packageDescription = null;
	private JTextArea jTextArea_packageDescription = null;
	private JPanel jPanel_body = null;
	private JLabel jLabel_selectComponents = null;
	private JCheckBox jCheckBox_Ballroom = null;
	private JTextField jTextField_Ballroom = null;
	private JButton jButton_ballroom = null;
	private JCheckBox jCheckBox_entertainment = null;
	private JTextField jTextField_entertainment = null;
	private JButton jButton_entertainment = null;
	private JTextField jTextField_mealOption1 = null;
	private JButton jButton_mealOption1 = null;
	private JTextField jTextField_mealOption2 = null;
	private JButton jButton_mealOption2 = null;
	private JTextField jTextField_mealOption3 = null;
	private JButton jButton_mealOption3 = null;
	private JPanel jPanel_summary = null;
	private JPanel jPanel_ballroom = null;
	private JPanel jPanel_entertainment = null;
	private JPanel jPanel_meal = null;
	private JLabel jLabel_discount = null;
	private JTextField jTextField_discount = null;
	private JSlider jSlider_discount = null;
	private JButton jButton_download = null;
	private JButton jButton_upload = null;
	private JButton jButton_delete = null;
	private JButton jButton_update = null;
	private JScrollPane jScrollPane_summary = null;
	private JTextArea jTextArea_summary = null;
	private JCheckBox jCheckBox_mealOption1 = null;
	private JCheckBox jCheckBox_mealOption2 = null;
	private JCheckBox jCheckBox_mealOption3 = null;
	private JFrame jFrame_Ballroom = null;  //  @jve:decl-index=0:visual-constraint="-515,62"
	private JPanel jContentPaneBallroom = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_ballroomSearch = null;
	private JTextField jTextField_ballroomSearch = null;
	private JButton jButton_ballroomSearch = null;
	private JScrollPane jScrollPane_ballroom = null;
	private JTable jTable_ballroomList = null;
	private JToolBar jJToolBarBar_ballroomPrevious = null;
	private JButton jButton_ballroomPrevious = null;
	private JToolBar jJToolBarBar_ballroomNext = null;
	private JButton jButton_ballroomNext = null;
	private JPanel jPanel_ballroomBody = null;
	private JLabel jLabel_ballroomName = null;
	private JTextField jTextField_ballroomName = null;  //  @jve:decl-index=0:visual-constraint="-366,991"
	private JTextField jTextField_ballroomSize = null;
	private JTextField jTextField_ballroomFinalPrice = null;
	private JTextField jTextField_facilityParking = null;
	private JTextField jTextField_facilityWeekendCost = null;
	private JTextField jTextField_facilityName = null;
	private JScrollPane jScrollPane_facilityAddress = null;
	private JTextArea jTextArea_facilityAddress = null;
	private JToolBar jJToolBarBar_selectBallroom = null;
	private JButton jButton_selectBallroom = null;
	private JScrollPane jScrollPaneBallroom = null;
	private JFrame jFrameEntertainment = null;  //  @jve:decl-index=0:visual-constraint="1374,882"
	private JPanel jContentPaneEntertainment = null;
	private JLabel jLabel_ballroomFinalPrice = null;
	private JScrollPane jScrollPaneEntertainment = null;
	private JLabel jLabel_facilityWeekendPrice = null;
	private JLabel jLabel_facilityName = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_entertainmentSearch = null;
	private JTextField jTextField_entertainmentSearch = null;
	private JButton jButton_entertainmentSearch = null;
	private JScrollPane jScrollPane_entertainment = null;
	private JTable jTable_entertainmentList = null;
	private JToolBar jJToolBarBar_entertainmentPrevious = null;
	private JButton jButton_entertainmentPrevious = null;
	private JToolBar jJToolBarBar_entertainmentNext = null;
	private JButton jButton_entertainmentNext = null;
	private JPanel jPanel_entertainmentBody = null;
	private JLabel jLabel_entertainmentName = null;
	private JTextField jTextField_entertainmentName = null;
	private JScrollPane jScrollPane_entertainmentList = null;
	private JTable jTable_entertainmentMenu = null;
	private JLabel jLabel_entertainmentPrice = null;
	private JTextField jTextField_entertainmentPrice = null;
	private JScrollPane jScrollPane_entertainmentDescription = null;
	private JTextArea jTextArea_entertainmentDescription = null;
	private JToolBar jJToolBarBar_selectEntertainment = null;
	private JButton jButton_selectEntertainment = null;
	private JFrame jFrameMeal = null;  //  @jve:decl-index=0:visual-constraint="1371,84"
	private JPanel jContentPaneMeal = null;
	private JScrollPane jScrollPaneMeal = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_mealSearch = null;
	private JTextField jTextField_mealSearch = null;
	private JButton jButton_mealSearch = null;
	private JScrollPane jScrollPane_mealSearch = null;
	private JTable jTable_mealLIst = null;
	private JToolBar jJToolBarBar_mealPrevious = null;
	private JButton jButton_mealPrevious = null;
	private JToolBar jJToolBarBar_mealNext = null;
	private JButton jButton_mealNext = null;
	private JPanel jPanel_mealBody = null;
	private JPopupMenu jPopupMenu_ballroom = null;  //  @jve:decl-index=0:visual-constraint="495,10"
	private JMenuItem jMenuItem_retrive_ballroom = null;
	private JPopupMenu jPopupMenu_entertainment = null;  //  @jve:decl-index=0:visual-constraint="686,14"
	private JMenuItem jMenuItem_retrive_entertainment = null;
	private JPopupMenu jPopupMenu_meal= null;
	private JMenuItem jMenuItem_retrive_meal=null;
	private JLabel jLabel_facilityAddress = null;
	private JLabel jLabel_mealName = null;
	private JTextField jTextField_mealName = null;
	private JLabel jLabel_mealType = null;
	private JTextField jTextField_mealType = null;
	private JLabel jLabel_mealPrice = null;
	private JTextField jTextField_mealPrice = null;
	private JScrollPane jScrollPane_mealList = null;
	private JTable jTable_mealList = null;
	private JScrollPane jScrollPane_mealDescription = null;
	private JToolBar jJToolBarBar_selectMeal = null;
	private JButton jButton_selectMeal = null;
	private JTextArea jTextArea_mealDescription = null;
	private JLabel jLabel_parking = null;
	private JLabel jLabel_ballroomSize = null;
	final JFileChooser fc = new JFileChooser();
	private Thread main=null;
	private Thread progress=null;
	protected String ballroomID;
	protected String entertainmentID;
	protected String mealOption1ID;
	protected String mealOption2ID;
	protected String mealOption3ID;
	/********************************************************
	 *					Start of UI
	 *******************************************************/
	protected JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(1021, 1284));
			jScrollPane.setViewportView(getJPanel());
		}
		return jScrollPane;
	}
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBackground(SystemColor.control);
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(1000, 1270));
			jPanel.add(getJPanel_header(), null);
			jPanel.add(getJPanel_body(), null);
			jPanel.add(getJPanel_summary(), null);
		}
		return jPanel;
	}
	/********************************************************
	 *					Start of header
	 *******************************************************/
	private JPanel getJPanel_header() {
		if (jPanel_header == null) {
			jLabel_packageDescription = new JLabel();
			jLabel_packageDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_packageDescription.setBounds(new Rectangle(50, 120, 81, 30));
			jLabel_packageDescription.setText("Description :");
			jLabel_packageTitle = new JLabel();
			jLabel_packageTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_packageTitle.setBounds(new Rectangle(50, 80, 80, 30));
			jLabel_packageTitle.setText("Title :");
			jLabel_packageAvailability = new JLabel();
			jLabel_packageAvailability.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_packageAvailability.setBounds(new Rectangle(450, 40, 150, 30));
			jLabel_packageAvailability.setText("Availability :");
			jLabel_packageID = new JLabel();
			jLabel_packageID.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_packageID.setBounds(new Rectangle(50, 40, 80, 30));
			jLabel_packageID.setText("ID :");
			jPanel_header = new JPanel();
			jPanel_header.setLayout(null);
			jPanel_header.setBounds(new Rectangle(100, 30, 800, 260));
			jPanel_header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_header.add(jLabel_packageID, null);
			jPanel_header.add(jLabel_packageAvailability, null);
			jPanel_header.add(jLabel_packageTitle, null);
			jPanel_header.add(jLabel_packageDescription, null);
			jPanel_header.add(getJTextField_packageID(), null);
			jPanel_header.add(getJCheckBox_packageAvailability(), null);
			jPanel_header.add(getJTextField_packageTitle(), null);
			jPanel_header.add(getJScrollPane_packageDescription(), null);
		}
		return jPanel_header;
	}
	protected JTextField getJTextField_packageID() {
		if (jTextField_packageID == null) {
			jTextField_packageID = new JTextField();
			jTextField_packageID.setForeground(SystemColor.scrollbar);
			jTextField_packageID.setText("  Generated After Creation");
			jTextField_packageID.setFont(new Font("Segoe UI", Font.ITALIC, 14));
			jTextField_packageID.setEditable(false);
			jTextField_packageID.setBounds(new Rectangle(130, 40, 200, 30));
		}
		return jTextField_packageID;
	}
	protected JCheckBox getJCheckBox_packageAvailability() {
		if (jCheckBox_packageAvailability == null) {
			jCheckBox_packageAvailability = new JCheckBox();
			jCheckBox_packageAvailability.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jCheckBox_packageAvailability.setFocusable(false);
			jCheckBox_packageAvailability.setFocusPainted(false);
			jCheckBox_packageAvailability.setBounds(new Rectangle(550, 40, 200, 30));
			jCheckBox_packageAvailability.setText("Not Available");
			jCheckBox_packageAvailability
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							if(jCheckBox_packageAvailability.isSelected()){
								jCheckBox_packageAvailability.setText("Available");
							}
							else{
								jCheckBox_packageAvailability.setText("Not Available");
							}
						}
					});
		}
		return jCheckBox_packageAvailability;
	}
	protected JTextField getJTextField_packageTitle() {
		if (jTextField_packageTitle == null) {
			jTextField_packageTitle = new JTextField();
			jTextField_packageTitle.setText("                                                                                  Enter a Package Name");
			jTextField_packageTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_packageTitle.setForeground(SystemColor.scrollbar);
			jTextField_packageTitle.setBounds(new Rectangle(130, 80, 620, 30));
			jTextField_packageTitle.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_packageTitle.getText().equals("")){
						jTextField_packageTitle.setText("                                                                                  Enter a Package Name");
						jTextField_packageTitle.setForeground(SystemColor.scrollbar);
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_packageTitle.getText().equals("                                                                                  Enter a Package Name")){
						jTextField_packageTitle.setText("");
						jTextField_packageTitle.setForeground(SystemColor.black);
					}
				}
			});
		}
		return jTextField_packageTitle;
	}
	private JScrollPane getJScrollPane_packageDescription() {
		if (jScrollPane_packageDescription == null) {
			jScrollPane_packageDescription = new JScrollPane();
			jScrollPane_packageDescription.setBounds(new Rectangle(130, 120, 620, 100));
			jScrollPane_packageDescription.setViewportView(getJTextArea_packageDescription());
		}
		return jScrollPane_packageDescription;
	}
	protected JTextArea getJTextArea_packageDescription() {
		if (jTextArea_packageDescription == null) {
			jTextArea_packageDescription = new JTextArea();
			jTextArea_packageDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_packageDescription.setForeground(SystemColor.scrollbar);
			jTextArea_packageDescription.setText("\n\n                                                                                 Enter a Description Here");
			jTextArea_packageDescription.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_packageDescription.getText().equals("")){
						jTextArea_packageDescription.setForeground(SystemColor.scrollbar);
						jTextArea_packageDescription.setText("\n\n                                                                                 Enter a Description Here");
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_packageDescription.getText().equals("\n\n                                                                                 Enter a Description Here")){
								jTextArea_packageDescription.setForeground(SystemColor.black);
								jTextArea_packageDescription.setText("");
							}
						}
					});
		}
		return jTextArea_packageDescription;
	}
	/********************************************************
	 *					Start of body
	 *******************************************************/
	private JPanel getJPanel_body() {
		if (jPanel_body == null) {
			jLabel_selectComponents = new JLabel();
			jLabel_selectComponents.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_selectComponents.setBounds(new Rectangle(30, 40, 250, 30));
			jLabel_selectComponents.setText("Select Package Components :");
			jPanel_body = new JPanel();
			jPanel_body.setLayout(null);
			jPanel_body.setBounds(new Rectangle(100, 320, 800, 630));
			jPanel_body.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_body.add(jLabel_selectComponents, null);
			jPanel_body.add(getJPanel_ballroom(), null);
			jPanel_body.add(getJPanel_entertainment(), null);
			jPanel_body.add(getJPanel_meal(), null);
		}
		return jPanel_body;
	}
	private JPanel getJPanel_ballroom() {
		if (jPanel_ballroom == null) {
			jPanel_ballroom = new JPanel();
			jPanel_ballroom.setLayout(null);
			jPanel_ballroom.setBounds(new Rectangle(30, 90, 750, 100));
			jPanel_ballroom.setBorder(BorderFactory.createTitledBorder(null, "1) Select a Ballroom", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 12), Color.black));
			jPanel_ballroom.add(getJCheckBox_Ballroom(), null);
			jPanel_ballroom.add(getJTextField_Ballroom(), null);
			jPanel_ballroom.add(getJButton_ballroom(), null);
		}
		return jPanel_ballroom;
	}
	protected JCheckBox getJCheckBox_Ballroom() {
		if (jCheckBox_Ballroom == null) {
			jCheckBox_Ballroom = new JCheckBox();
			jCheckBox_Ballroom.setFocusable(false);
			jCheckBox_Ballroom.setEnabled(false);
			jCheckBox_Ballroom.setFocusPainted(false);
			jCheckBox_Ballroom.setSelected(true);
			jCheckBox_Ballroom.setText("");
			jCheckBox_Ballroom.setBounds(new Rectangle(30, 30, 26, 30));
		}
		return jCheckBox_Ballroom;
	}
	protected JTextField getJTextField_Ballroom() {
		if (jTextField_Ballroom == null) {
			jTextField_Ballroom = new JTextField();
			jTextField_Ballroom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_Ballroom.setEditable(false);
			jTextField_Ballroom.setBounds(new Rectangle(57, 32, 504, 30));
		}
		return jTextField_Ballroom;
	}
	private JButton getJButton_ballroom() {
		if (jButton_ballroom == null) {
			jButton_ballroom = new JButton();
			jButton_ballroom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_ballroom.setFocusable(false);
			jButton_ballroom.setFocusPainted(false);
			jButton_ballroom.setEnabled(true);
			jButton_ballroom.setText("Browse");
			jButton_ballroom.setBounds(new Rectangle(571, 32, 150, 30));
			jButton_ballroom.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrame_Ballroom().setVisible(true);
					getJFrame_Ballroom().setSize(new Dimension(820, 287));
					getJContentPaneBallroom().setPreferredSize(new Dimension(770, 240));
					getJTextField_ballroomSearch().setText("");
					DefaultTableModel model= new DefaultTableModel();
					model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
					getJTable_ballroomList().setModel(model);
					getJTable_ballroomList().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
					getJTable_ballroomList().getColumnModel().getColumn(0).setPreferredWidth(50);
					getJTable_ballroomList().getColumnModel().getColumn(1).setPreferredWidth(500);
					getJTable_ballroomList().getColumnModel().getColumn(2).setPreferredWidth(150);
					getJTable_ballroomList().getColumnModel().getColumn(3).setPreferredWidth(135);
					getJTable_ballroomList().getColumnModel().getColumn(4).setPreferredWidth(570);
					getJButton_ballroomPrevious().setEnabled(false);
					getJButton_ballroomPrevious().setVisible(false);
					getJButton_ballroomNext().setEnabled(false);
					getJButton_ballroomNext().setVisible(false);
					getJTextField_ballroomName().setText("");
					getJTextField_ballroomSize().setText("");
					getJTextField_ballroomFinalPrice().setText("");
					getJTextField_facilityParking().setText("");
					getJTextField_facilityWeekendCost().setText("");
					getJTextField_facilityName().setText("");
					getJTextArea_facilityAddress().setText("");
					getJButton_selectBallroom().setEnabled(false);
				}
			});
		}
		return jButton_ballroom;
	}
	private JPanel getJPanel_entertainment() {
		if (jPanel_entertainment == null) {
			jPanel_entertainment = new JPanel();
			jPanel_entertainment.setLayout(null);
			jPanel_entertainment.setBounds(new Rectangle(28, 220, 750, 100));
			jPanel_entertainment.setBorder(BorderFactory.createTitledBorder(null, "2) Select a Entertainment (Optional)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,new Font("Segoe UI", Font.BOLD, 12), Color.black));
			jPanel_entertainment.add(getJCheckBox_entertainment(), null);
			jPanel_entertainment.add(getJTextField_entertainment(), null);
			jPanel_entertainment.add(getJButton_entertainment(), null);
			}
		return jPanel_entertainment;
	}
	protected JCheckBox getJCheckBox_entertainment() {
		if (jCheckBox_entertainment == null) {
			jCheckBox_entertainment = new JCheckBox();
			jCheckBox_entertainment.setFocusable(false);
			jCheckBox_entertainment.setFocusPainted(false);
			jCheckBox_entertainment.setText("");
			jCheckBox_entertainment.setBounds(new Rectangle(30, 30, 26, 30));
			jCheckBox_entertainment.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_entertainment.isSelected()){
						getJButton_entertainment().setEnabled(true);
					}
					else{
						getJButton_entertainment().setEnabled(false);
						getJTextField_entertainment().setText("");
					}
				}
			});
		}
		return jCheckBox_entertainment;
	}
	protected JTextField getJTextField_entertainment() {
		if (jTextField_entertainment == null) {
			jTextField_entertainment = new JTextField();
			jTextField_entertainment.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_entertainment.setEditable(false);
			jTextField_entertainment.setBounds(new Rectangle(57, 30, 504, 30));
		}
		return jTextField_entertainment;
	}
	protected JButton getJButton_entertainment() {
		if (jButton_entertainment == null) {
			jButton_entertainment = new JButton();
			jButton_entertainment.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_entertainment.setFocusable(false);
			jButton_entertainment.setFocusPainted(false);
			jButton_entertainment.setEnabled(false);
			jButton_entertainment.setText("Browse");
			jButton_entertainment.setBounds(new Rectangle(571, 30, 150, 30));
			jButton_entertainment.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrameEntertainment().setVisible(true);
					getJFrameEntertainment().setSize(new Dimension(820, 287));
					getJContentPaneEntertainment().setPreferredSize(new Dimension(770, 240));
					getJTextField_entertainmentSearch().setText("");
					DefaultTableModel model= new DefaultTableModel();
					model.setColumnIdentifiers(new Object[]{"ID","Name","Price","Description"});
					getJTable_entertainmentList().setModel(model);
					getJTable_entertainmentList().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
					getJTable_entertainmentList().getColumnModel().getColumn(0).setPreferredWidth(50);
					getJTable_entertainmentList().getColumnModel().getColumn(1).setPreferredWidth(500);
					getJTable_entertainmentList().getColumnModel().getColumn(2).setPreferredWidth(150);
					getJTable_entertainmentList().getColumnModel().getColumn(3).setPreferredWidth(700);
					getJButton_entertainmentPrevious().setVisible(false);
					getJButton_entertainmentNext().setVisible(false);
					getJTextField_entertainmentName().setText("");
					getJTextField_entertainmentPrice().setText("");	
				}
			});
		}
		return jButton_entertainment;
	}
	private JPanel getJPanel_meal() {
		if (jPanel_meal == null) {
			jPanel_meal = new JPanel();
			jPanel_meal.setBorder(BorderFactory.createTitledBorder(null, "3) Select 1 - 3 Meal Option(s) (Optional)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", Font.BOLD, 12), Color.black));
			jPanel_meal.setLayout(null);
			jPanel_meal.setBounds(new Rectangle(27, 380, 750, 200));
			jPanel_meal.add(getJTextField_mealOption1(), null);
			jPanel_meal.add(getJTextField_mealOption2(), null);
			jPanel_meal.add(getJButton_mealOption1(), null);
			jPanel_meal.add(getJButton_mealOption2(), null);
			jPanel_meal.add(getJTextField_mealOption3(), null);
			jPanel_meal.add(getJButton_mealOption3(), null);
			jPanel_meal.add(getJCheckBox_mealOption1(), null);
			jPanel_meal.add(getJCheckBox_mealOption2(), null);
			jPanel_meal.add(getJCheckBox_mealOption3(), null);
		}
		return jPanel_meal;
	}
	protected JCheckBox getJCheckBox_mealOption1() {
		if (jCheckBox_mealOption1 == null) {
			jCheckBox_mealOption1 = new JCheckBox();
			jCheckBox_mealOption1.setFocusable(false);
			jCheckBox_mealOption1.setFocusPainted(false);
			jCheckBox_mealOption1.setBounds(new Rectangle(30, 30, 26, 30));
			jCheckBox_mealOption1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealOption1.isSelected()){
						getJButton_mealOption1().setEnabled(true);
					}
					else{
						getJButton_mealOption1().setEnabled(false);
						getJTextField_mealOption1().setText("");
					}
				}
			});
		}
		return jCheckBox_mealOption1;
	}
	protected JCheckBox getJCheckBox_mealOption2() {
		if (jCheckBox_mealOption2 == null) {
			jCheckBox_mealOption2 = new JCheckBox();
			jCheckBox_mealOption2.setFocusable(false);
			jCheckBox_mealOption2.setFocusPainted(false);
			jCheckBox_mealOption2.setBounds(new Rectangle(30, 80, 26, 30));
			jCheckBox_mealOption2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealOption2.isSelected()){
						getJButton_mealOption2().setEnabled(true);
					}
					else{
						getJButton_mealOption2().setEnabled(false);
						getJTextField_mealOption2().setText("");
					}
				}
			});
		}
		return jCheckBox_mealOption2;
	}
	protected JCheckBox getJCheckBox_mealOption3() {
		if (jCheckBox_mealOption3 == null) {
			jCheckBox_mealOption3 = new JCheckBox();
			jCheckBox_mealOption3.setFocusable(false);
			jCheckBox_mealOption3.setFocusPainted(false);
			jCheckBox_mealOption3.setBounds(new Rectangle(30, 130, 26, 30));
			jCheckBox_mealOption3.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jCheckBox_mealOption3.isSelected()){
						getJButton_mealOption3().setEnabled(true);
					}
					else{
						getJButton_mealOption3().setEnabled(false);
						getJTextField_mealOption3().setText("");
					}
				}
			});
		}
		return jCheckBox_mealOption3;
	}
	protected JTextField getJTextField_mealOption1() {
		if (jTextField_mealOption1 == null) {
			jTextField_mealOption1 = new JTextField();
			jTextField_mealOption1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealOption1.setEditable(false);
			jTextField_mealOption1.setBounds(new Rectangle(57, 30, 504, 30));
		}
		return jTextField_mealOption1;
	}
	protected JButton getJButton_mealOption1() {
		if (jButton_mealOption1 == null) {
			jButton_mealOption1 = new JButton();
			jButton_mealOption1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_mealOption1.setFocusable(false);
			jButton_mealOption1.setFocusPainted(false);
			jButton_mealOption1.setEnabled(false);
			jButton_mealOption1.setText("Browse");
			jButton_mealOption1.setBounds(new Rectangle(571, 30, 150, 30));
			jButton_mealOption1.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrameMeal().setVisible(true);
					getJFrameMeal().setSize(new Dimension(820, 287));
					getJFrameMeal().setPreferredSize(new Dimension(770, 240));
					getJFrameMeal().setName("1");
					getJContentPaneMeal().setPreferredSize(new Dimension(770, 240));
					getJButton_mealPrevious().setVisible(false);
					getJButton_mealNext().setVisible(false);
					DefaultTableModel mainModel= new DefaultTableModel();
					mainModel.setColumnIdentifiers(new Object[]{"ID","Meal Set Name","Type","Price/head","Description"});
					getJTable_mealLIst().setModel(mainModel);
					getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
					getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
					getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
					getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(4).setPreferredWidth(550);
					
				}
			});
		}
		return jButton_mealOption1;
	}
	protected JTextField getJTextField_mealOption2() {
		if (jTextField_mealOption2 == null) {
			jTextField_mealOption2 = new JTextField();
			jTextField_mealOption2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealOption2.setEditable(false);
			jTextField_mealOption2.setBounds(new Rectangle(57, 80, 504, 30));
		}
		return jTextField_mealOption2;
	}
	protected JButton getJButton_mealOption2() {
		if (jButton_mealOption2 == null) {
			jButton_mealOption2 = new JButton();
			jButton_mealOption2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_mealOption2.setFocusable(false);
			jButton_mealOption2.setFocusPainted(false);
			jButton_mealOption2.setEnabled(false);
			jButton_mealOption2.setText("Browse");
			jButton_mealOption2.setBounds(new Rectangle(571, 80, 150, 30));
			jButton_mealOption2.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrameMeal().setVisible(true);
					getJFrameMeal().setName("2");
					getJFrameMeal().setSize(new Dimension(820, 287));
					getJFrameMeal().setPreferredSize(new Dimension(770, 240));
					getJContentPaneMeal().setPreferredSize(new Dimension(770, 240));
					getJButton_mealPrevious().setVisible(false);
					getJButton_mealNext().setVisible(false);
					DefaultTableModel mainModel= new DefaultTableModel();
					mainModel.setColumnIdentifiers(new Object[]{"ID","Meal Set Name","Type","Price/head","Description"});
					getJTable_mealLIst().setModel(mainModel);
					getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
					getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
					getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
					getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(4).setPreferredWidth(550);
				}
			});
		}
		return jButton_mealOption2;
	}
	protected JTextField getJTextField_mealOption3() {
		if (jTextField_mealOption3 == null) {
			jTextField_mealOption3 = new JTextField();
			jTextField_mealOption3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealOption3.setEditable(false);
			jTextField_mealOption3.setBounds(new Rectangle(57, 130, 504, 30));
		}
		return jTextField_mealOption3;
	}
	protected JButton getJButton_mealOption3() {
		if (jButton_mealOption3 == null) {
			jButton_mealOption3 = new JButton();
			jButton_mealOption3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_mealOption3.setFocusable(false);
			jButton_mealOption3.setFocusPainted(false);
			jButton_mealOption3.setEnabled(false);
			jButton_mealOption3.setText("Browse");
			jButton_mealOption3.setBounds(new Rectangle(571, 130, 150, 30));
			jButton_mealOption3.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJFrameMeal().setVisible(true);
					getJFrameMeal().setName("3");
					getJFrameMeal().setSize(new Dimension(820, 287));
					getJFrameMeal().setPreferredSize(new Dimension(770, 240));
					getJContentPaneMeal().setPreferredSize(new Dimension(770, 240));
					getJButton_mealPrevious().setVisible(false);
					getJButton_mealNext().setVisible(false);
					DefaultTableModel mainModel= new DefaultTableModel();
					mainModel.setColumnIdentifiers(new Object[]{"ID","Meal Set Name","Type","Price/head","Description"});
					getJTable_mealLIst().setModel(mainModel);
					getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
					getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
					getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
					getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(150);
					getJTable_mealLIst().getColumnModel().getColumn(4).setPreferredWidth(550);
				}
			});
		}
		return jButton_mealOption3;
	}
	
	/********************************************************
	 *					Start of summary
	 *******************************************************/
	private JPanel getJPanel_summary() {
		if (jPanel_summary == null) {
			jLabel_discount = new JLabel();
			jLabel_discount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_discount.setBounds(new Rectangle(400, 40, 150, 30));
			jLabel_discount.setText("Entitled Discount :");
			jPanel_summary = new JPanel();
			jPanel_summary.setLayout(null);
			jPanel_summary.setBounds(new Rectangle(100, 970, 800, 250));
			jPanel_summary.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_summary.add(jLabel_discount, null);
			jPanel_summary.add(getJTextField_discount(), null);
			jPanel_summary.add(getJSlider_discount(), null);
			jPanel_summary.add(getJButton_download(), null);
			jPanel_summary.add(getJButton_upload(), null);
			jPanel_summary.add(getJButton_delete(), null);
			jPanel_summary.add(getJButton_update(), null);
			jPanel_summary.add(getJScrollPane_summary(), null);
		}
		return jPanel_summary;
	}
	
	protected JTextField getJTextField_discount() {
		if (jTextField_discount == null) {
			jTextField_discount = new JTextField();
			jTextField_discount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_discount.setEditable(false);
			jTextField_discount.setText("0%");
			jTextField_discount.setBounds(new Rectangle(550, 40, 177, 30));
		}
		return jTextField_discount;
	}
	protected JSlider getJSlider_discount() {
		if (jSlider_discount == null) {
			jSlider_discount = new JSlider();
			jSlider_discount.setFocusable(false);
			jSlider_discount.setValue(0);
			jSlider_discount.setMinimum(0);
			jSlider_discount.setMaximum(100);
			jSlider_discount.setBounds(new Rectangle(400, 80, 337, 30));
			jSlider_discount.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					getJTextField_discount().setText(jSlider_discount.getValue()+"%");
					displaySummary();
				}
			});
		}
		return jSlider_discount;
	}
	protected JButton getJButton_download() {
		if (jButton_download == null) {
			jButton_download = new JButton();
			jButton_download.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_download.setFocusable(false);
			jButton_download.setEnabled(false);
			jButton_download.setFocusPainted(false);
			jButton_download.setBounds(new Rectangle(400, 115, 160, 45));
			jButton_download.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/download.png")));
			jButton_download.setText("Download");
			jButton_download.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setting the file and path name
					fc.setAcceptAllFileFilterUsed(false);
					fc.setFocusable(false);
					fc.setAcceptAllFileFilterUsed(false);
					fc.showSaveDialog(fc);

					final String directory=fc.getSelectedFile().toString();
					 main = new Thread () {
						  @SuppressWarnings("deprecation")
						public void run () {
							  if(validatePackageDetails()){
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
							  if(validatePackageDetails()){
									createPackage();
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
			jButton_delete.setEnabled(false);
			jButton_delete.setBounds(new Rectangle(400, 170, 160, 45));
			jButton_delete.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/delete.png")));
			jButton_delete.setText("Delete");
			jButton_delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final int i=JOptionPane.showConfirmDialog(null, "You are about to delete this record\n Are you sure?", "Delete Record", JOptionPane.YES_NO_OPTION);
					main = new Thread () {
						  public void run () {
								if(i==0){
									deletePackage();
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
								        Thread.sleep(100);
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
			jButton_update.setEnabled(false);
			jButton_update.setBounds(new Rectangle(570, 170, 160, 45));
			jButton_update.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/update.png")));
			jButton_update.setText("Update");
			jButton_update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					main = new Thread () {
						  public void run () {
							  if(validatePackageDetails()){
									updatePackage();
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
	private JScrollPane getJScrollPane_summary() {
		if (jScrollPane_summary == null) {
			jScrollPane_summary = new JScrollPane();
			jScrollPane_summary.setBounds(new Rectangle(50, 40, 321, 174));
			jScrollPane_summary.setViewportView(getJTextArea_summary());
		}
		return jScrollPane_summary;
	}
	private JTextArea getJTextArea_summary() {
		if (jTextArea_summary == null) {
			jTextArea_summary = new JTextArea();
			jTextArea_summary.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_summary.setWrapStyleWord(true);
			jTextArea_summary.setLineWrap(true);
		}
		return jTextArea_summary;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *			Start of Ballroom Selection UI
	 *******************************************************/
	private JFrame getJFrame_Ballroom() {
		if (jFrame_Ballroom == null) {
			jFrame_Ballroom = new JFrame();
			jFrame_Ballroom.setUndecorated(true);
			jFrame_Ballroom.setSize(new Dimension(820, 810));
			jFrame_Ballroom.setResizable(false);
			jFrame_Ballroom.setLocation(323, 66);
			jFrame_Ballroom.setTitle("Select Ballroom");
			jFrame_Ballroom.setContentPane(getJScrollPaneBallroom());
			jFrame_Ballroom.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/SOM/facility.png")));
		}
		return jFrame_Ballroom;
	}
	private JScrollPane getJScrollPaneBallroom() {
		if (jScrollPaneBallroom == null) {
			jScrollPaneBallroom = new JScrollPane();
			jScrollPaneBallroom.setViewportView(getJContentPaneBallroom());
		}
		return jScrollPaneBallroom;
	}
	private JPanel getJContentPaneBallroom() {
		if (jContentPaneBallroom == null) {
			jContentPaneBallroom = new JPanel();
			jContentPaneBallroom.setLayout(null);
			jContentPaneBallroom.setPreferredSize(new Dimension(770, 240));
			jContentPaneBallroom.setBackground(SystemColor.control);
			jContentPaneBallroom.add(getJComboBox_ballroomSearch(), null);
			jContentPaneBallroom.add(getJTextField_ballroomSearch(), null);
			jContentPaneBallroom.add(getJButton_ballroomSearch(), null);
			jContentPaneBallroom.add(getJScrollPane_ballroom(), null);
			jContentPaneBallroom.add(getJJToolBarBar_ballroomPrevious(), null);
			jContentPaneBallroom.add(getJJToolBarBar_ballroomNext(), null);
			jContentPaneBallroom.add(getJPanel_ballroomBody(), null);
		}
		return jContentPaneBallroom;
	}
	@SuppressWarnings("unchecked")
	private JComboBox getJComboBox_ballroomSearch() {
		if (jComboBox_ballroomSearch == null) {
			jComboBox_ballroomSearch = new JComboBox();
			jComboBox_ballroomSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jComboBox_ballroomSearch.addItem("Search By Facility");
			jComboBox_ballroomSearch.addItem("Search By Ballroom");
			jComboBox_ballroomSearch.setFocusable(false);
			jComboBox_ballroomSearch.setBounds(new Rectangle(50, 40, 212, 30));
		}
		return jComboBox_ballroomSearch;
	}
	private JTextField getJTextField_ballroomSearch() {
		if (jTextField_ballroomSearch == null) {
			jTextField_ballroomSearch = new JTextField();
			jTextField_ballroomSearch.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomSearch.setText("");
			jTextField_ballroomSearch.setBounds(new Rectangle(277, 40, 317, 30));

		}
		return jTextField_ballroomSearch;
	}
	private JButton getJButton_ballroomSearch() {
		if (jButton_ballroomSearch == null) {
			jButton_ballroomSearch = new JButton();
			jButton_ballroomSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_ballroomSearch.setFocusable(false);
			jButton_ballroomSearch.setFocusPainted(false);
			jButton_ballroomSearch.setBounds(new Rectangle(600, 40, 150, 30));
			jButton_ballroomSearch.setText("Search");
			jButton_ballroomSearch.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(getJComboBox_ballroomSearch().getSelectedIndex()==0){
						if(getJTextField_ballroomSearch().getText().equals("")){
							retrieveBallroom();
						}
						else{
							retrieveBallroomByFacility(getJTextField_ballroomSearch().getText().toString());
						}
					}
					else{
						if(getJTextField_ballroomSearch().getText().equals("")){
							retrieveBallroom();
						}
						else{
							retrieveBallroom(getJTextField_ballroomSearch().getText().toString());
						}
					}
				}
			});
		}
		return jButton_ballroomSearch;
	}
	private JScrollPane getJScrollPane_ballroom() {
		if (jScrollPane_ballroom == null) {
			jScrollPane_ballroom = new JScrollPane();
			jScrollPane_ballroom.setBounds(new Rectangle(50, 80, 700, 130));
			jScrollPane_ballroom.setViewportView(getJTable_ballroomList());
		}
		return jScrollPane_ballroom;
	}
	@SuppressWarnings("static-access")
	private JTable getJTable_ballroomList() {
		if (jTable_ballroomList == null) {
			DefaultTableModel model= new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
			jTable_ballroomList = new JTable(model);
			jTable_ballroomList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_ballroomList.setOpaque(false);
			jTable_ballroomList.setEnabled(false);
			jTable_ballroomList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_ballroomList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_ballroomList.setIntercellSpacing(new Dimension(5, 5));
			jTable_ballroomList.setRowSelectionAllowed(true);
			jTable_ballroomList.setShowGrid(false);
			jTable_ballroomList.setFocusable(false);
			jTable_ballroomList.setColumnSelectionAllowed(false);
			jTable_ballroomList.setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
			jTable_ballroomList.getColumnModel().getColumn(0).setPreferredWidth(50);
			jTable_ballroomList.getColumnModel().getColumn(1).setPreferredWidth(500);
			jTable_ballroomList.getColumnModel().getColumn(2).setPreferredWidth(150);
			jTable_ballroomList.getColumnModel().getColumn(3).setPreferredWidth(135);
			jTable_ballroomList.getColumnModel().getColumn(4).setPreferredWidth(560);
			jTable_ballroomList.addMouseListener(new MouseAdapter() {   
				public void mouseClicked(java.awt.event.MouseEvent e) {    
					if (e.getClickCount() == 2){
						String ID=getJTable_ballroomList().getValueAt(getJTable_ballroomList().getSelectedRow(), 0).toString();
						retrieveBallroomByID(ID);
					}
				}
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
		                JPopupMenu popup = getJPopupMenu_ballroom();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});
		}
		return jTable_ballroomList;
	}
	private JPopupMenu getJPopupMenu_ballroom() {
		if (jPopupMenu_ballroom == null) {
			jPopupMenu_ballroom = new JPopupMenu();
			jPopupMenu_ballroom.setOpaque(false);
			jPopupMenu_ballroom.setSize(new Dimension(99, 168));
			jPopupMenu_ballroom.add(getJMenuItem_retrive_ballroom());
		}
		return jPopupMenu_ballroom;
	}
	private JMenuItem getJMenuItem_retrive_ballroom() {
		if (jMenuItem_retrive_ballroom == null) {
			jMenuItem_retrive_ballroom = new JMenuItem();
			jMenuItem_retrive_ballroom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jMenuItem_retrive_ballroom.setText("Show Details");
			jMenuItem_retrive_ballroom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String ID=getJTable_ballroomList().getValueAt(getJTable_ballroomList().getSelectedRow(), 0).toString();
					retrieveBallroomByID(ID);
				}
			});
		}
		return jMenuItem_retrive_ballroom;
	}
	private JToolBar getJJToolBarBar_ballroomPrevious() {
		if (jJToolBarBar_ballroomPrevious == null) {
			jJToolBarBar_ballroomPrevious = new JToolBar();
			jJToolBarBar_ballroomPrevious.setFloatable(false);
			jJToolBarBar_ballroomPrevious.setFocusable(false);
			jJToolBarBar_ballroomPrevious.setOpaque(false);
			jJToolBarBar_ballroomPrevious.setBounds(new Rectangle(52, 222, 58, 37));
			jJToolBarBar_ballroomPrevious.add(getJButton_ballroomPrevious());
		}
		return jJToolBarBar_ballroomPrevious;
	}
	private JButton getJButton_ballroomPrevious() {
		if (jButton_ballroomPrevious == null) {
			jButton_ballroomPrevious = new JButton();
			jButton_ballroomPrevious.setFocusable(false);
			jButton_ballroomPrevious.setFocusPainted(false);
			jButton_ballroomPrevious.setEnabled(false);
			jButton_ballroomPrevious.setVisible(false);
			jButton_ballroomPrevious.setComponentOrientation(ComponentOrientation.UNKNOWN);
			jButton_ballroomPrevious.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/previous_button.png")));
			jButton_ballroomPrevious.setHorizontalTextPosition(SwingConstants.RIGHT);
			jButton_ballroomPrevious.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getJTable_ballroomList().getSelectedRow();
					if(row!=0){
						row--;
						getJTable_ballroomList().setSelectionMode(0);
						getJTable_ballroomList().changeSelection(row, 0, false, false);
						String ID=getJTable_ballroomList().getValueAt(getJTable_ballroomList().getSelectedRow(), 0).toString();
						retrieveBallroomByID(ID);
					}
				}
			});
		}
		return jButton_ballroomPrevious;
	}
	private JToolBar getJJToolBarBar_ballroomNext() {
		if (jJToolBarBar_ballroomNext == null) {
			jJToolBarBar_ballroomNext = new JToolBar();
			jJToolBarBar_ballroomNext.setFloatable(false);
			jJToolBarBar_ballroomNext.setFocusable(false);
			jJToolBarBar_ballroomNext.setOpaque(false);
			jJToolBarBar_ballroomNext.setBounds(new Rectangle(700, 225, 57, 32));
			jJToolBarBar_ballroomNext.add(getJButton_ballroomNext());
		}
		return jJToolBarBar_ballroomNext;
	}
	private JButton getJButton_ballroomNext() {
		if (jButton_ballroomNext == null) {
			jButton_ballroomNext = new JButton();
			jButton_ballroomNext.setFocusable(false);
			jButton_ballroomNext.setFocusPainted(false);
			jButton_ballroomNext.setEnabled(false);
			jButton_ballroomNext.setVisible(false);
			jButton_ballroomNext.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/next_button.png")));
			jButton_ballroomNext.setHorizontalTextPosition(SwingConstants.LEFT);
			jButton_ballroomNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getJTable_ballroomList().getSelectedRow();
					if(row!=(getJTable_ballroomList().getRowCount()-1)){
						row++;
						getJTable_ballroomList().setSelectionMode(0);
						getJTable_ballroomList().changeSelection(row, 0, false, false);
						String ID=getJTable_ballroomList().getValueAt(getJTable_ballroomList().getSelectedRow(), 0).toString();
						retrieveBallroomByID(ID);
					}
				}
			});
		}
		return jButton_ballroomNext;
	}
	private JPanel getJPanel_ballroomBody() {
		if (jPanel_ballroomBody == null) {
			jLabel_ballroomSize = new JLabel();
			jLabel_ballroomSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomSize.setBounds(new Rectangle(50, 120, 100, 30));
			jLabel_ballroomSize.setText("Ballroom Size :");
			jLabel_parking = new JLabel();
			jLabel_parking.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_parking.setBounds(new Rectangle(50, 160, 100, 30));
			jLabel_parking.setText("Parking :");
			jLabel_facilityAddress = new JLabel();
			jLabel_facilityAddress.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_facilityAddress.setBounds(new Rectangle(50, 280, 150, 30));
			jLabel_facilityAddress.setText("Address :");
			jLabel_facilityName = new JLabel();
			jLabel_facilityName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_facilityName.setBounds(new Rectangle(50, 200, 200, 30));
			jLabel_facilityName.setText("Facility Name :");
			jLabel_facilityWeekendPrice = new JLabel();
			jLabel_facilityWeekendPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_facilityWeekendPrice.setBounds(new Rectangle(360, 160, 130, 30));
			jLabel_facilityWeekendPrice.setText("Weekend Surcharge :");
			jLabel_ballroomFinalPrice = new JLabel();
			jLabel_ballroomFinalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomFinalPrice.setBounds(new Rectangle(360, 120, 130, 30));
			jLabel_ballroomFinalPrice.setText("Ballroom Rate :");
			jLabel_ballroomName = new JLabel();
			jLabel_ballroomName.setBounds(new Rectangle(50, 50, 150, 30));
			jLabel_ballroomName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomName.setText("Ballroom Name :");
			jPanel_ballroomBody = new JPanel();
			jPanel_ballroomBody.setLayout(null);
			jPanel_ballroomBody.setPreferredSize(new Dimension(700, 445));
			jPanel_ballroomBody.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jPanel_ballroomBody.setBounds(new Rectangle(50, 260, 700, 472));
			jPanel_ballroomBody.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_ballroomBody.add(jLabel_ballroomName, null);
			jPanel_ballroomBody.add(getJTextField_ballroomName());
			jPanel_ballroomBody.add(getJTextField_ballroomSize(), null);
			jPanel_ballroomBody.add(getJTextField_ballroomFinalPrice(), null);
			jPanel_ballroomBody.add(getJTextField_facilityParking(), null);
			jPanel_ballroomBody.add(getJTextField_facilityWeekendCost(), null);
			jPanel_ballroomBody.add(getJTextField_facilityName(), null);
			jPanel_ballroomBody.add(getJScrollPane_facilityAddress(), null);
			jPanel_ballroomBody.add(getJJToolBarBar_selectBallroom(), null);
			jPanel_ballroomBody.add(jLabel_ballroomFinalPrice, null);
			jPanel_ballroomBody.add(jLabel_facilityWeekendPrice, null);
			jPanel_ballroomBody.add(jLabel_facilityName, null);
			jPanel_ballroomBody.add(jLabel_facilityAddress, null);
			jPanel_ballroomBody.add(jLabel_parking, null);
			jPanel_ballroomBody.add(jLabel_ballroomSize, null);
		}
		return jPanel_ballroomBody;
	}
	private JTextField getJTextField_ballroomName() {
		if (jTextField_ballroomName == null) {
			jTextField_ballroomName = new JTextField();
			jTextField_ballroomName.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomName.setBounds(new Rectangle(50, 80, 600, 30));
			jTextField_ballroomName.setEditable(false);
		}
		return jTextField_ballroomName;
	}
	private JTextField getJTextField_ballroomSize() {
		if (jTextField_ballroomSize == null) {
			jTextField_ballroomSize = new JTextField();
			jTextField_ballroomSize.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomSize.setEditable(false);
			jTextField_ballroomSize.setBounds(new Rectangle(150, 120, 150, 30));
		}
		return jTextField_ballroomSize;
	}
	private JTextField getJTextField_ballroomFinalPrice() {
		if (jTextField_ballroomFinalPrice == null) {
			jTextField_ballroomFinalPrice = new JTextField();
			jTextField_ballroomFinalPrice.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomFinalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomFinalPrice.setEditable(false);
			jTextField_ballroomFinalPrice.setBounds(new Rectangle(490, 120, 160, 30));
		}
		return jTextField_ballroomFinalPrice;
	}
	private JTextField getJTextField_facilityParking() {
		if (jTextField_facilityParking == null) {
			jTextField_facilityParking = new JTextField();
			jTextField_facilityParking.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityParking.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_facilityParking.setEditable(false);
			jTextField_facilityParking.setBounds(new Rectangle(150, 160, 150, 30));
		}
		return jTextField_facilityParking;
	}
	private JTextField getJTextField_facilityWeekendCost() {
		if (jTextField_facilityWeekendCost == null) {
			jTextField_facilityWeekendCost = new JTextField();
			jTextField_facilityWeekendCost.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityWeekendCost.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_facilityWeekendCost.setEditable(false);
			jTextField_facilityWeekendCost.setBounds(new Rectangle(490, 160, 160, 30));
		}
		return jTextField_facilityWeekendCost;
	}
	private JTextField getJTextField_facilityName() {
		if (jTextField_facilityName == null) {
			jTextField_facilityName = new JTextField();
			jTextField_facilityName.setHorizontalAlignment(JTextField.CENTER);
			jTextField_facilityName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_facilityName.setEditable(false);
			jTextField_facilityName.setBounds(new Rectangle(49, 230, 600, 30));
		}
		return jTextField_facilityName;
	}
	private JScrollPane getJScrollPane_facilityAddress() {
		if (jScrollPane_facilityAddress == null) {
			jScrollPane_facilityAddress = new JScrollPane();
			jScrollPane_facilityAddress.setBounds(new Rectangle(50, 310, 600, 100));
			jScrollPane_facilityAddress.setViewportView(getJTextArea_facilityAddress());
		}
		return jScrollPane_facilityAddress;
	}
	private JTextArea getJTextArea_facilityAddress() {
		if (jTextArea_facilityAddress == null) {
			jTextArea_facilityAddress = new JTextArea();
			jTextArea_facilityAddress.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_facilityAddress.setEditable(false);
			jTextArea_facilityAddress.setWrapStyleWord(true);
			jTextArea_facilityAddress.setLineWrap(true);
		}
		return jTextArea_facilityAddress;
	}
	private JToolBar getJJToolBarBar_selectBallroom() {
		if (jJToolBarBar_selectBallroom == null) {
			jJToolBarBar_selectBallroom = new JToolBar();
			jJToolBarBar_selectBallroom.setFloatable(false);
			jJToolBarBar_selectBallroom.setOpaque(false);
			jJToolBarBar_selectBallroom.setBounds(new Rectangle(272, 427, 149, 31));
			jJToolBarBar_selectBallroom.add(getJButton_selectBallroom());
		}
		return jJToolBarBar_selectBallroom;
	}
	private JButton getJButton_selectBallroom() {
		if (jButton_selectBallroom == null) {
			jButton_selectBallroom = new JButton();
			jButton_selectBallroom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_selectBallroom.setFocusable(false);
			jButton_selectBallroom.setFocusPainted(false);
			jButton_selectBallroom.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/Select.png")));
			jButton_selectBallroom.setText("Select Ballroom");
			jButton_selectBallroom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJTextField_Ballroom().setText(getJTextField_ballroomName().getText().toString());
					getJTextField_Ballroom().setName(getJTextField_ballroomName().getName().toString());
					ballroomID=getJTextField_ballroomName().getName().toString();
					getJFrame_Ballroom().setVisible(false);
					displaySummary();
				}
			});
		}
		return jButton_selectBallroom;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *			Start of Entertainment Selection
	 *******************************************************/
	private JFrame getJFrameEntertainment() {
		if (jFrameEntertainment == null) {
			jFrameEntertainment = new JFrame();
			jFrameEntertainment.setSize(new Dimension(820, 768));
			jFrameEntertainment.setLocation(323, 66);
			jFrameEntertainment.setResizable(false);
			jFrameEntertainment.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/SOM/entertainment.png")));
			jFrameEntertainment.setContentPane(getJScrollPaneEntertainment());
			jFrameEntertainment.setTitle("Select Entertainment");
		}
		return jFrameEntertainment;
	}	
	private JScrollPane getJScrollPaneEntertainment() {
		if (jScrollPaneEntertainment == null) {
			jScrollPaneEntertainment = new JScrollPane();
			jScrollPaneEntertainment.setViewportView(getJContentPaneEntertainment());
		}
		return jScrollPaneEntertainment;
	}
	private JPanel getJContentPaneEntertainment() {
		if (jContentPaneEntertainment == null) {
			jContentPaneEntertainment = new JPanel();
			jContentPaneEntertainment.setLayout(null);
			jContentPaneEntertainment.setPreferredSize(new Dimension(750, 755));
			jContentPaneEntertainment.setBackground(SystemColor.control);
			jContentPaneEntertainment.add(getJComboBox_entertainmentSearch(), null);
			jContentPaneEntertainment.add(getJTextField_entertainmentSearch(), null);
			jContentPaneEntertainment.add(getJButton_entertainmentSearch(), null);
			jContentPaneEntertainment.add(getJScrollPane_entertainment(), null);
			jContentPaneEntertainment.add(getJJToolBarBar_entertainmentPrevious(), null);
			jContentPaneEntertainment.add(getJJToolBarBar_entertainmentNext(), null);
			jContentPaneEntertainment.add(getJPanel_entertainmentBody(), null);
		}
		return jContentPaneEntertainment;
	}

	@SuppressWarnings("unchecked")
	private JComboBox getJComboBox_entertainmentSearch() {
		if (jComboBox_entertainmentSearch == null) {
			jComboBox_entertainmentSearch = new JComboBox();
			jComboBox_entertainmentSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jComboBox_entertainmentSearch.addItem("Search By Entertainment Set");
			jComboBox_entertainmentSearch.addItem("Search By Programmes");
			jComboBox_entertainmentSearch.setFocusable(false);
			jComboBox_entertainmentSearch.setBounds(new Rectangle(50, 40, 212, 30));
		}
		return jComboBox_entertainmentSearch;
	}
	private JTextField getJTextField_entertainmentSearch() {
		if (jTextField_entertainmentSearch == null) {
			jTextField_entertainmentSearch = new JTextField();
			jTextField_entertainmentSearch.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertainmentSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_entertainmentSearch.setBounds(new Rectangle(277, 40, 317, 30));
		}
		return jTextField_entertainmentSearch;
	}
	private JButton getJButton_entertainmentSearch() {
		if (jButton_entertainmentSearch == null) {
			jButton_entertainmentSearch = new JButton();
			jButton_entertainmentSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_entertainmentSearch.setFocusable(false);
			jButton_entertainmentSearch.setFocusPainted(false);
			jButton_entertainmentSearch.setBounds(new Rectangle(600, 40, 150, 30));
			jButton_entertainmentSearch.setText("Search");
			jButton_entertainmentSearch
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if(getJComboBox_entertainmentSearch().getSelectedIndex()==0){
								if(getJTextField_entertainmentSearch().getText().equals("")){
									retrieveEntertainment();
								}
								else{
									retrieveEntertainment(getJTextField_entertainmentSearch().getText());
								}
							}
							else{
								if(getJTextField_entertainmentSearch().getText().equals("")){
									retrieveEntertainment();
								}
								else{
									retrieveEntertainmentByProgram(getJTextField_entertainmentSearch().getText());
								}
							}
						}
					});
		}
		return jButton_entertainmentSearch;
	}
	private JScrollPane getJScrollPane_entertainment() {
		if (jScrollPane_entertainment == null) {
			jScrollPane_entertainment = new JScrollPane();
			jScrollPane_entertainment.setBounds(new Rectangle(50, 80, 700, 130));
			jScrollPane_entertainment.setViewportView(getJTable_entertainmentList());
		}
		return jScrollPane_entertainment;
	}
	private JTable getJTable_entertainmentList() {
		if (jTable_entertainmentList == null) {
			DefaultTableModel model= new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"ID","Name","Type","Price","Description"});
			jTable_entertainmentList = new JTable(model);
			jTable_entertainmentList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_entertainmentList.setOpaque(false);
			jTable_entertainmentList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_entertainmentList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_entertainmentList.setIntercellSpacing(new Dimension(5, 5));
			jTable_entertainmentList.setRowSelectionAllowed(true);
			jTable_entertainmentList.setShowGrid(false);
			jTable_entertainmentList.setFocusable(true);
			jTable_entertainmentList.setEnabled(false);
			jTable_entertainmentList.addMouseListener(new MouseAdapter() {   
				public void mouseClicked(java.awt.event.MouseEvent e) {    
					if (e.getClickCount() == 2){
						String ID=getJTable_entertainmentList().getValueAt(getJTable_entertainmentList().getSelectedRow(), 0).toString();
						retrieveEntertainmentByID(ID);
					}
				}
		        @Override
		        public void mouseReleased(MouseEvent e) {
		            int r = jTable_entertainmentList.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < jTable_entertainmentList.getRowCount()) {
		            	jTable_entertainmentList.setRowSelectionInterval(r, r);
		            } else {
		            	jTable_entertainmentList.clearSelection();
		            }

		            int rowindex = jTable_entertainmentList.getSelectedRow();
		            if (rowindex < 0)
		                return;
		            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		                JPopupMenu popup = getJPopupMenu_entertainment();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});
		}
		return jTable_entertainmentList;
	}
	private JPopupMenu getJPopupMenu_entertainment() {
		if (jPopupMenu_entertainment == null) {
			jPopupMenu_entertainment = new JPopupMenu();
			jPopupMenu_entertainment.setOpaque(false);
			jPopupMenu_entertainment.setSize(new Dimension(99, 168));
			jPopupMenu_entertainment.add(getJMenuItem_retrive_entertainment());
		}
		return jPopupMenu_entertainment;
	}
	private JMenuItem getJMenuItem_retrive_entertainment() {
		if (jMenuItem_retrive_entertainment == null) {
			jMenuItem_retrive_entertainment = new JMenuItem();
			jMenuItem_retrive_entertainment.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jMenuItem_retrive_entertainment.setText("Show Details");
			jMenuItem_retrive_entertainment.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String ID=getJTable_entertainmentList().getValueAt(getJTable_entertainmentList().getSelectedRow(), 0).toString();
					retrieveEntertainmentByID(ID);
				}
			});
		}
		return jMenuItem_retrive_entertainment;
	}
	private JToolBar getJJToolBarBar_entertainmentPrevious() {
		if (jJToolBarBar_entertainmentPrevious == null) {
			jJToolBarBar_entertainmentPrevious = new JToolBar();
			jJToolBarBar_entertainmentPrevious.setFloatable(false);
			jJToolBarBar_entertainmentPrevious.setFocusable(false);
			jJToolBarBar_entertainmentPrevious.setOpaque(false);
			jJToolBarBar_entertainmentPrevious.setBounds(new Rectangle(52, 222, 58, 37));
			jJToolBarBar_entertainmentPrevious.add(getJButton_entertainmentPrevious());
		}
		return jJToolBarBar_entertainmentPrevious;
	}
	private JButton getJButton_entertainmentPrevious() {
		if (jButton_entertainmentPrevious == null) {
			jButton_entertainmentPrevious = new JButton();
			jButton_entertainmentPrevious.setFocusable(false);
			jButton_entertainmentPrevious.setFocusPainted(false);
			jButton_entertainmentPrevious.setOpaque(false);
			jButton_entertainmentPrevious.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/previous_button.png")));
			jButton_entertainmentPrevious.setText("");
			jButton_entertainmentPrevious
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int row = getJTable_entertainmentList().getSelectedRow();
							if(row!=0){
								row--;
								getJTable_entertainmentList().setSelectionMode(0);
								getJTable_entertainmentList().changeSelection(row, 0, false, false);
								String ID=getJTable_entertainmentList().getValueAt(getJTable_entertainmentList().getSelectedRow(), 0).toString();
								retrieveEntertainmentByID(ID);
						}
					}});
		}
		return jButton_entertainmentPrevious;
	}
	private JToolBar getJJToolBarBar_entertainmentNext() {
		if (jJToolBarBar_entertainmentNext == null) {
			jJToolBarBar_entertainmentNext = new JToolBar();
			jJToolBarBar_entertainmentNext.setFloatable(false);
			jJToolBarBar_entertainmentNext.setFocusable(false);
			jJToolBarBar_entertainmentNext.setOpaque(false);
			jJToolBarBar_entertainmentNext.setBounds(new Rectangle(700, 225, 57, 32));
			jJToolBarBar_entertainmentNext.add(getJButton_entertainmentNext());
		}
		return jJToolBarBar_entertainmentNext;
	}
	private JButton getJButton_entertainmentNext() {
		if (jButton_entertainmentNext == null) {
			jButton_entertainmentNext = new JButton();
			jButton_entertainmentNext.setFocusable(false);
			jButton_entertainmentNext.setEnabled(true);
			jButton_entertainmentNext.setOpaque(false);
			jButton_entertainmentNext.setFocusPainted(false);
			jButton_entertainmentNext.setText("");
			jButton_entertainmentNext.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/next_button.png")));
			jButton_entertainmentNext
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int row = getJTable_entertainmentList().getSelectedRow();
							if(row!=(getJTable_entertainmentList().getRowCount()-1)){
								row++;
								getJTable_entertainmentList().setSelectionMode(0);
								getJTable_entertainmentList().changeSelection(row, 0, false, false);
								String ID=getJTable_entertainmentList().getValueAt(getJTable_entertainmentList().getSelectedRow(), 0).toString();
								retrieveEntertainmentByID(ID);
						}
					}});
		}
		return jButton_entertainmentNext;
	}
	private JPanel getJPanel_entertainmentBody() {
		if (jPanel_entertainmentBody == null) {
			jLabel_entertainmentPrice = new JLabel();
			jLabel_entertainmentPrice.setBounds(new Rectangle(50, 240, 150, 30));
			jLabel_entertainmentPrice.setText("Total Payable Amount :");
			jLabel_entertainmentName = new JLabel();
			jLabel_entertainmentName.setBounds(new Rectangle(50, 50, 150, 30));
			jLabel_entertainmentName.setText("Entertainment Name :");
			jPanel_entertainmentBody = new JPanel();
			jPanel_entertainmentBody.setLayout(null);
			jPanel_entertainmentBody.setBounds(new Rectangle(50, 260, 700, 443));
			jPanel_entertainmentBody.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_entertainmentBody.add(jLabel_entertainmentName, null);
			jPanel_entertainmentBody.add(getJTextField_entertainmentName(), null);
			jPanel_entertainmentBody.add(getJScrollPane_entertainmentList(), null);
			jPanel_entertainmentBody.add(jLabel_entertainmentPrice, null);
			jPanel_entertainmentBody.add(getJTextField_entertainmentPrice(), null);
			jPanel_entertainmentBody.add(getJScrollPane_entertainmentDescription(), null);
			jPanel_entertainmentBody.add(getJJToolBarBar_selectEntertainment(), null);
		}
		return jPanel_entertainmentBody;
	}
	private JTextField getJTextField_entertainmentName() {
		if (jTextField_entertainmentName == null) {
			jTextField_entertainmentName = new JTextField();
			jTextField_entertainmentName.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertainmentName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_entertainmentName.setEditable(false);
			jTextField_entertainmentName.setBounds(new Rectangle(50, 80, 600, 30));
		}
		return jTextField_entertainmentName;
	}
	private JScrollPane getJScrollPane_entertainmentList() {
		if (jScrollPane_entertainmentList == null) {
			jScrollPane_entertainmentList = new JScrollPane();
			jScrollPane_entertainmentList.setBounds(new Rectangle(50, 120, 600, 100));
			jScrollPane_entertainmentList.setViewportView(getJTable_entertainmentMenu());
		}
		return jScrollPane_entertainmentList;
	}
	@SuppressWarnings("static-access")
	private JTable getJTable_entertainmentMenu() {
		if (jTable_entertainmentMenu == null) {
			DefaultTableModel model= new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"Name","Price","Description"});
			jTable_entertainmentMenu = new JTable(model);
			jTable_entertainmentMenu.setEnabled(false);
			jTable_entertainmentMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_entertainmentMenu.setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
			jTable_entertainmentMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
			jTable_entertainmentMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_entertainmentMenu.getColumnModel().getColumn(2).setPreferredWidth(698);
		}
		return jTable_entertainmentMenu;
	}
	private JTextField getJTextField_entertainmentPrice() {
		if (jTextField_entertainmentPrice == null) {
			jTextField_entertainmentPrice = new JTextField();
			jTextField_entertainmentPrice.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertainmentPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_entertainmentPrice.setEditable(false);
			jTextField_entertainmentPrice.setBounds(new Rectangle(200, 240, 250, 30));
		}
		return jTextField_entertainmentPrice;
	}
	private JScrollPane getJScrollPane_entertainmentDescription() {
		if (jScrollPane_entertainmentDescription == null) {
			jScrollPane_entertainmentDescription = new JScrollPane();
			jScrollPane_entertainmentDescription.setBounds(new Rectangle(50, 280, 600, 100));
			jScrollPane_entertainmentDescription.setViewportView(getJTextArea_entertainmentDescription());
		}
		return jScrollPane_entertainmentDescription;
	}
	private JTextArea getJTextArea_entertainmentDescription() {
		if (jTextArea_entertainmentDescription == null) {
			jTextArea_entertainmentDescription = new JTextArea();
			jTextArea_entertainmentDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_entertainmentDescription.setEditable(false);
			jTextArea_entertainmentDescription.setWrapStyleWord(true);
			jTextArea_entertainmentDescription.setLineWrap(true);
		}
		return jTextArea_entertainmentDescription;
	}
	private JToolBar getJJToolBarBar_selectEntertainment() {
		if (jJToolBarBar_selectEntertainment == null) {
			jJToolBarBar_selectEntertainment = new JToolBar();
			jJToolBarBar_selectEntertainment.setFloatable(false);
			jJToolBarBar_selectEntertainment.setOpaque(false);
			jJToolBarBar_selectEntertainment.setBounds(new Rectangle(269, 398, 211, 31));
			jJToolBarBar_selectEntertainment.add(getJButton_selectEntertainment());
		}
		return jJToolBarBar_selectEntertainment;
	}
	private JButton getJButton_selectEntertainment() {
		if (jButton_selectEntertainment == null) {
			jButton_selectEntertainment = new JButton();
			jButton_selectEntertainment.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_selectEntertainment.setOpaque(false);
			jButton_selectEntertainment.setFocusable(false);
			jButton_selectEntertainment.setFocusPainted(false);
			jButton_selectEntertainment.setText("Select Entertainment");
			jButton_selectEntertainment.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/Select.png")));
			jButton_selectEntertainment
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getJTextField_entertainment().setText(getJTextField_entertainmentName().getText());
							getJTextField_entertainment().setName(getJTextField_entertainmentName().getName());
							entertainmentID=getJTextField_entertainmentName().getName();
							getJFrameEntertainment().setVisible(false);
							displaySummary();
						}
					});
		}
		return jButton_selectEntertainment;
	}
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *			Start of Meal Selection
	 *******************************************************/
	private JFrame getJFrameMeal() {
		if (jFrameMeal == null) {
			jFrameMeal = new JFrame();
			jFrameMeal.setSize(new Dimension(820, 857));
			jFrameMeal.setResizable(false);
			jFrameMeal.setLocation(323, 66);
			jFrameMeal.setTitle("Select Meal");
			jFrameMeal.setContentPane(getJScrollPaneMeal());
			jFrameMeal.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/SOM/meal.png")));
		}
		return jFrameMeal;
	}	
	private JScrollPane getJScrollPaneMeal() {
		if (jScrollPaneMeal == null) {
			jScrollPaneMeal = new JScrollPane();
			jScrollPaneMeal.setViewportView(getJContentPaneMeal());
		}
		return jScrollPaneMeal;
	}
	private JPanel getJContentPaneMeal() {
		if (jContentPaneMeal == null) {
			jContentPaneMeal = new JPanel();
			jContentPaneMeal.setLayout(null);
			jContentPaneMeal.setPreferredSize(new Dimension(770, 755));
			jContentPaneMeal.setBackground(SystemColor.control);
			jContentPaneMeal.add(getJComboBox_mealSearch(), null);
			jContentPaneMeal.add(getJTextField_mealSearch(), null);
			jContentPaneMeal.add(getJButton_mealSearch(), null);
			jContentPaneMeal.add(getJScrollPane_mealSearch(), null);
			jContentPaneMeal.add(getJJToolBarBar_mealPrevious(), null);
			jContentPaneMeal.add(getJJToolBarBar_mealNext(), null);
			jContentPaneMeal.add(getJPanel_mealBody(), null);
		}
		return jContentPaneMeal;
	}
	@SuppressWarnings("unchecked")
	private JComboBox getJComboBox_mealSearch() {
		if (jComboBox_mealSearch == null) {
			jComboBox_mealSearch = new JComboBox();
			jComboBox_mealSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jComboBox_mealSearch.setFocusable(false);
			jComboBox_mealSearch.addItem("Search By Meal Set");
			jComboBox_mealSearch.addItem("Search By Meal Dish");
			jComboBox_mealSearch.setBounds(new Rectangle(50, 40, 212, 30));
		}
		return jComboBox_mealSearch;
	}
	private JTextField getJTextField_mealSearch() {
		if (jTextField_mealSearch == null) {
			jTextField_mealSearch = new JTextField();
			jTextField_mealSearch.setHorizontalAlignment(JTextField.CENTER);
			jTextField_mealSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealSearch.setBounds(new Rectangle(277, 40, 317, 30));
		}
		return jTextField_mealSearch;
	}
	private JButton getJButton_mealSearch() {
		if (jButton_mealSearch == null) {
			jButton_mealSearch = new JButton();
			jButton_mealSearch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_mealSearch.setFocusable(false);
			jButton_mealSearch.setFocusPainted(false);
			jButton_mealSearch.setBounds(new Rectangle(600, 40, 150, 30));
			jButton_mealSearch.setText("Search");
			jButton_mealSearch.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(getJComboBox_mealSearch().getSelectedIndex()==0){
						if(getJTextField_mealSearch().getText().equals("")){
							retrieveMeal();
						}
						else{
							retrieveMeal(getJTextField_mealSearch().getText().toString());
						}
					}
					else{
						if(getJTextField_mealSearch().getText().equals("")){
							retrieveMeal();
						}
						else{
							retrieveMealByDish(getJTextField_mealSearch().getText());
						}
					}
				}});
		}
		return jButton_mealSearch;
	}
	private JScrollPane getJScrollPane_mealSearch() {
		if (jScrollPane_mealSearch == null) {
			jScrollPane_mealSearch = new JScrollPane();
			jScrollPane_mealSearch.setBounds(new Rectangle(50, 80, 700, 130));
			jScrollPane_mealSearch.setViewportView(getJTable_mealLIst());
		}
		return jScrollPane_mealSearch;
	}
	@SuppressWarnings("static-access")
	private JTable getJTable_mealLIst() {
		if (jTable_mealLIst == null) {
			DefaultTableModel model= new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"ID","Name","Price","Description"});
			jTable_mealLIst = new JTable(model);
			jTable_mealLIst.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_mealLIst.setOpaque(false);
			jTable_mealLIst.setEnabled(false);
			jTable_mealLIst.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_mealLIst.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_mealLIst.setIntercellSpacing(new Dimension(5, 5));
			jTable_mealLIst.setRowSelectionAllowed(true);
			jTable_mealLIst.setShowGrid(false);
			jTable_mealLIst.setFocusable(false);
			jTable_mealLIst.setColumnSelectionAllowed(false);
			jTable_mealLIst.setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
			jTable_mealLIst.getColumnModel().getColumn(0).setPreferredWidth(50);
			jTable_mealLIst.getColumnModel().getColumn(1).setPreferredWidth(500);
			jTable_mealLIst.getColumnModel().getColumn(2).setPreferredWidth(150);
			jTable_mealLIst.getColumnModel().getColumn(3).setPreferredWidth(700);
			jTable_mealLIst.addMouseListener(new MouseAdapter() {   
				public void mouseClicked(java.awt.event.MouseEvent e) {    
					if (e.getClickCount() == 2){
						String ID=getJTable_mealLIst().getValueAt(getJTable_mealLIst().getSelectedRow(), 0).toString();
						retrieveMealByID(ID);
					}
				}
		        @Override
		        public void mouseReleased(MouseEvent e) {
		            int r = jTable_mealLIst.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < jTable_mealLIst.getRowCount()) {
		            	jTable_mealLIst.setRowSelectionInterval(r, r);
		            } else {
		            	jTable_mealLIst.clearSelection();
		            }

		            int rowindex = jTable_mealLIst.getSelectedRow();
		            if (rowindex < 0)
		                return;
		            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		                JPopupMenu popup = getJPopupMenu_meal();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});
		}
		return jTable_mealLIst;
	}
	private JPopupMenu getJPopupMenu_meal() {
		if (jPopupMenu_meal == null) {
			jPopupMenu_meal = new JPopupMenu();
			jPopupMenu_meal.setOpaque(false);
			jPopupMenu_meal.setSize(new Dimension(99, 168));
			jPopupMenu_meal.add(getJMenuItem_retrive_meal());
		}
		return jPopupMenu_meal;
	}
	private JMenuItem getJMenuItem_retrive_meal() {
		if (jMenuItem_retrive_meal == null) {
			jMenuItem_retrive_meal = new JMenuItem();
			jMenuItem_retrive_meal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jMenuItem_retrive_meal.setText("Show Details");
			jMenuItem_retrive_meal.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String ID=getJTable_mealLIst().getValueAt(getJTable_mealLIst().getSelectedRow(), 0).toString();
					retrieveMealByID(ID);
				}
			});
		}
		return jMenuItem_retrive_meal;
	}

	private JToolBar getJJToolBarBar_mealPrevious() {
		if (jJToolBarBar_mealPrevious == null) {
			jJToolBarBar_mealPrevious = new JToolBar();
			jJToolBarBar_mealPrevious.setOpaque(false);
			jJToolBarBar_mealPrevious.setFloatable(false);
			jJToolBarBar_mealPrevious.setBounds(new Rectangle(52, 222, 58, 37));
			jJToolBarBar_mealPrevious.add(getJButton_mealPrevious());
		}
		return jJToolBarBar_mealPrevious;
	}
	private JButton getJButton_mealPrevious() {
		if (jButton_mealPrevious == null) {
			jButton_mealPrevious = new JButton();
			jButton_mealPrevious.setFocusable(false);
			jButton_mealPrevious.setFocusPainted(false);
			jButton_mealPrevious.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/previous_button.png")));
			jButton_mealPrevious.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getJTable_mealLIst().getSelectedRow();
					if(row!=0){
						row--;
						getJTable_mealLIst().setSelectionMode(0);
						getJTable_mealLIst().changeSelection(row, 0, false, false);
						String ID=getJTable_mealLIst().getValueAt(getJTable_mealLIst().getSelectedRow(), 0).toString();
						retrieveMealByID(ID);
				}
			}});
		}
		return jButton_mealPrevious;
	}
	private JToolBar getJJToolBarBar_mealNext() {
		if (jJToolBarBar_mealNext == null) {
			jJToolBarBar_mealNext = new JToolBar();
			jJToolBarBar_mealNext.setFloatable(false);
			jJToolBarBar_mealNext.setOpaque(false);
			jJToolBarBar_mealNext.setBounds(new Rectangle(700, 225, 57, 32));
			jJToolBarBar_mealNext.add(getJButton_mealNext());
		}
		return jJToolBarBar_mealNext;
	}
	private JButton getJButton_mealNext() {
		if (jButton_mealNext == null) {
			jButton_mealNext = new JButton();
			jButton_mealNext.setFocusable(false);
			jButton_mealNext.setFocusPainted(false);
			jButton_mealNext.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/next_button.png")));
			jButton_mealNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getJTable_mealLIst().getSelectedRow();
					if(row!=(getJTable_mealLIst().getRowCount()-1)){
						row++;
						getJTable_mealLIst().setSelectionMode(0);
						getJTable_mealLIst().changeSelection(row, 0, false, false);
						String ID=getJTable_mealLIst().getValueAt(getJTable_mealLIst().getSelectedRow(), 0).toString();
						retrieveMealByID(ID);
				}
			}});
		}
		return jButton_mealNext;
	}
	private JPanel getJPanel_mealBody() {
		if (jPanel_mealBody == null) {
			jLabel_mealPrice = new JLabel();
			jLabel_mealPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealPrice.setBounds(new Rectangle(416, 120, 84, 30));
			jLabel_mealPrice.setText("Price/head");
			jLabel_mealType = new JLabel();
			jLabel_mealType.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealType.setBounds(new Rectangle(50, 120, 150, 30));
			jLabel_mealType.setText("Type :");
			jLabel_mealName = new JLabel();
			jLabel_mealName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_mealName.setBounds(new Rectangle(50, 50, 150, 30));
			jLabel_mealName.setText("Meal Set Title :");
			jPanel_mealBody = new JPanel();
			jPanel_mealBody.setLayout(null);
			jPanel_mealBody.setBounds(new Rectangle(50, 260, 700, 443));
			jPanel_mealBody.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_mealBody.add(jLabel_mealName, null);
			jPanel_mealBody.add(getJTextField_mealName(), null);
			jPanel_mealBody.add(jLabel_mealType, null);
			jPanel_mealBody.add(getJTextField_mealType(), null);
			jPanel_mealBody.add(jLabel_mealPrice, null);
			jPanel_mealBody.add(getJTextField_mealPrice(), null);
			jPanel_mealBody.add(getJScrollPane_mealList(), null);
			jPanel_mealBody.add(getJScrollPane_mealDescription(), null);
			jPanel_mealBody.add(getJJToolBarBar_selectMeal(), null);
		}
		return jPanel_mealBody;
	}
	private JTextField getJTextField_mealName() {
		if (jTextField_mealName == null) {
			jTextField_mealName = new JTextField();
			jTextField_mealName.setHorizontalAlignment(JTextField.CENTER);
			jTextField_mealName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealName.setEditable(false);
			jTextField_mealName.setBounds(new Rectangle(50, 80, 600, 30));
		}
		return jTextField_mealName;
	}
	private JTextField getJTextField_mealType() {
		if (jTextField_mealType == null) {
			jTextField_mealType = new JTextField();
			jTextField_mealType.setFocusable(false);
			jTextField_mealType.setHorizontalAlignment(JTextField.CENTER);
			jTextField_mealType.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealType.setEditable(false);
			jTextField_mealType.setBounds(new Rectangle(200, 120, 150, 30));
		}
		return jTextField_mealType;
	}
	private JTextField getJTextField_mealPrice() {
		if (jTextField_mealPrice == null) {
			jTextField_mealPrice = new JTextField();
			jTextField_mealPrice.setHorizontalAlignment(JTextField.CENTER);
			jTextField_mealPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_mealPrice.setEditable(false);
			jTextField_mealPrice.setBounds(new Rectangle(500, 120, 150, 30));
		}
		return jTextField_mealPrice;
	}
	private JScrollPane getJScrollPane_mealList() {
		if (jScrollPane_mealList == null) {
			jScrollPane_mealList = new JScrollPane();
			jScrollPane_mealList.setBounds(new Rectangle(50, 160, 600, 100));
			jScrollPane_mealList.setViewportView(getJTable_mealList());
		}
		return jScrollPane_mealList;
	}
	@SuppressWarnings("static-access")
	private JTable getJTable_mealList() {
		if (jTable_mealList == null) {
			DefaultTableModel model= new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"ID","Meal Set Name","Type","Price/head","Description"});
			jTable_mealList = new JTable(model);
			jTable_mealList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTable_mealList.setEnabled(false);
			jTable_mealList.setAutoResizeMode(jTable_mealLIst.AUTO_RESIZE_OFF);
			jTable_mealList.getColumnModel().getColumn(0).setPreferredWidth(450);
			jTable_mealList.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_mealList.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTable_mealList.getColumnModel().getColumn(3).setPreferredWidth(100);
			jTable_mealList.getColumnModel().getColumn(4).setPreferredWidth(500);
		}
		return jTable_mealList;
	}
	private JScrollPane getJScrollPane_mealDescription() {
		if (jScrollPane_mealDescription == null) {
			jScrollPane_mealDescription = new JScrollPane();
			jScrollPane_mealDescription.setBounds(new Rectangle(50, 280, 600, 100));
			jScrollPane_mealDescription.setViewportView(getJTextArea_mealDescription());
		}
		return jScrollPane_mealDescription;
	}
	private JToolBar getJJToolBarBar_selectMeal() {
		if (jJToolBarBar_selectMeal == null) {
			jJToolBarBar_selectMeal = new JToolBar();
			jJToolBarBar_selectMeal.setFloatable(false);
			jJToolBarBar_selectMeal.setFocusable(false);
			jJToolBarBar_selectMeal.setOpaque(false);
			jJToolBarBar_selectMeal.setBounds(new Rectangle(263, 395, 176, 35));
			jJToolBarBar_selectMeal.add(getJButton_selectMeal());
		}
		return jJToolBarBar_selectMeal;
	}
	private JButton getJButton_selectMeal() {
		if (jButton_selectMeal == null) {
			jButton_selectMeal = new JButton();
			jButton_selectMeal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_selectMeal.setFocusable(false);
			jButton_selectMeal.setFocusPainted(false);
			jButton_selectMeal.setText("Select Meal");
			jButton_selectMeal.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/Select.png")));
			jButton_selectMeal.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(getJFrameMeal().getName().equals("1")){
						getJTextField_mealOption1().setText(getJTextField_mealName().getText().toString());
						getJTextField_mealOption1().setName(getJTextField_mealName().getName().toString());
						mealOption1ID=getJTextField_mealName().getName().toString();
					}
					if(getJFrameMeal().getName().equals("2")){
						getJTextField_mealOption2().setText(getJTextField_mealName().getText().toString());
						getJTextField_mealOption2().setName(getJTextField_mealName().getName().toString());
						mealOption2ID=getJTextField_mealName().getName().toString();
					}
					if(getJFrameMeal().getName().equals("3")){
						getJTextField_mealOption3().setText(getJTextField_mealName().getText().toString());
						getJTextField_mealOption3().setName(getJTextField_mealName().getName().toString());
						mealOption3ID=getJTextField_mealName().getName().toString();
					}
					getJFrameMeal().setVisible(false);
					displaySummary();
				}
			});
		}
		return jButton_selectMeal;
	}
	private JTextArea getJTextArea_mealDescription() {
		if (jTextArea_mealDescription == null) {
			jTextArea_mealDescription = new JTextArea();
			jTextArea_mealDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_mealDescription.setEditable(false);
			jTextArea_mealDescription.setLineWrap(true);
			jTextArea_mealDescription.setWrapStyleWord(true);
		}
		return jTextArea_mealDescription;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/********************************************************
	 *				Start of Custom Methods
	 *******************************************************/
	/********************************************************
	  * Method Name 	: retrieveBallroom
	  * Input Parameter : void
	  * Purpose 		: To retrieve all Ballroom record
	  * Return 			: void
	  *******************************************************/
	public void retrieveBallroom(){
		AdministrateBallroomControl control= new AdministrateBallroomControl();
		DefaultTableModel model= new DefaultTableModel();
		model=control.processRetrieveBallroom();
		getJTable_ballroomList().setModel(model);
		getJTable_ballroomList().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_ballroomList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_ballroomList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_ballroomList().getColumnModel().getColumn(3).setPreferredWidth(135);
		getJTable_ballroomList().getColumnModel().getColumn(4).setPreferredWidth(570);
		
		//CHECKS IF THERE IS DATA IN THE RETRIVAL
		if(model.getRowCount()>0){
			getJButton_ballroomPrevious().setEnabled(true);
			getJButton_ballroomNext().setEnabled(true);
		}
		else{
			getJButton_ballroomPrevious().setEnabled(false);
			getJButton_ballroomNext().setEnabled(false);
		}
	}
	
	/********************************************************
	  * Method Name 	: retrieveBallroom
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Ballroom record like paramater
	  * Return 			: void
	  *******************************************************/
	public void retrieveBallroom(String parameter){
		AdministrateBallroomControl control= new AdministrateBallroomControl();
		DefaultTableModel model= new DefaultTableModel();
		model=control.processRetrieveBallroom(parameter);
		getJTable_ballroomList().setModel(model);
		getJTable_ballroomList().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_ballroomList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_ballroomList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_ballroomList().getColumnModel().getColumn(3).setPreferredWidth(135);
		getJTable_ballroomList().getColumnModel().getColumn(4).setPreferredWidth(570);
		
		//CHECKS IF THERE IS DATA IN THE RETRIVAL
		if(model.getRowCount()>0){
			getJButton_ballroomPrevious().setEnabled(true);
			getJButton_ballroomNext().setEnabled(true);
		}
		else{
			getJButton_ballroomPrevious().setEnabled(false);
			getJButton_ballroomNext().setEnabled(false);
		}
	}
	/********************************************************
	  * Method Name 	: retrieveBallroomByFacility
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Ballroom record like paramater from facility
	  * Return 			: void
	  *******************************************************/
	public void retrieveBallroomByFacility(String parameter){
		AdministrateFacilityControl fControl=new AdministrateFacilityControl();
		DefaultTableModel model= new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"ID","Name","Size","Price","Description"});
		DefaultTableModel fModel= new DefaultTableModel();
		DefaultTableModel bModel= new DefaultTableModel();
		fModel=fControl.processRetrieveFacility(parameter);
		
		//THIS METHODS RETRIEVE THE BALLROOM BASE ON LIKE"FACILITY"
		fModel=fControl.processRetrieveFacility(parameter);
		
		for(int i=0;i<fModel.getRowCount();i++){
			AdministrateBallroomControl bControl= new AdministrateBallroomControl();
			bModel=bControl.processRetrieveBallroomByFacility(fModel.getValueAt(i, 0).toString());
			
			for(int n=0;n<bModel.getRowCount();n++){
				model.addRow(new Object[]{bModel.getValueAt(n, 0),bModel.getValueAt(n, 1),bModel.getValueAt(n, 2),bModel.getValueAt(n, 3),bModel.getValueAt(n, 4)});
			}
		}

		getJTable_ballroomList().setModel(model);
		getJTable_ballroomList().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_ballroomList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_ballroomList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_ballroomList().getColumnModel().getColumn(3).setPreferredWidth(135);
		getJTable_ballroomList().getColumnModel().getColumn(4).setPreferredWidth(570);
		
		//CHECKS IF THERE IS DATA IN THE RETRIVAL
		if(model.getRowCount()>0){
			getJButton_ballroomPrevious().setEnabled(true);
			getJButton_ballroomNext().setEnabled(true);
			
		}
		else{
			getJButton_ballroomPrevious().setEnabled(false);
			getJButton_ballroomNext().setEnabled(false);
		}
	}
	/********************************************************
	  * Method Name 	: retrieveBallroomByID
	  * Input Parameter : String
	  * Purpose		 	: To retrieve all Ballroom record by ID
	  * Return 			: void
	  *******************************************************/
	public void retrieveBallroomByID(String ID){
		AdministrateBallroomControl control= new AdministrateBallroomControl();
		control.processRetrieveBallroomByID(ID);

		DecimalFormat fmt = new DecimalFormat("0.00");
		//SETTING THE REVALENT FIELDS
		getJTextField_ballroomName().setText(control.getBallroom().getBallroomName().toString());
		getJTextField_ballroomName().setName(control.getBallroom().getBallroomID().toString());
		getJTextField_ballroomSize().setText(control.getBallroom().getBallroomSize().toString());
		getJTextField_ballroomFinalPrice().setText("$"+fmt.format(control.getBallroom().getBallroomFinalPrice()));
		
		AdministrateFacilityControl fControl= new AdministrateFacilityControl();
		fControl.processRetrieveFacilityByID(control.getBallroom().getFacilityID());
		
		if(fControl.getFacility().isFacilityParking()){
			getJTextField_facilityParking().setText("Free Parking");
		}
		if(!fControl.getFacility().isFacilityParking()){
			getJTextField_facilityParking().setText("narmal Parking Rate");
		}
		getJTextField_facilityWeekendCost().setText("$"+fmt.format(fControl.getFacility().getFacilityWeekendExtraCost()));
		getJTextField_facilityName().setText(fControl.getFacility().getFacilityName().toString());
		getJTextArea_facilityAddress().setText(fControl.getFacility().getFacilityAddress().toString());
		getJButton_selectBallroom().setEnabled(true);
		
		//SETTING THE BUTTONS AND CONTROL FOR THE FORM
		getJButton_ballroomPrevious().setEnabled(true);
		getJButton_ballroomPrevious().setVisible(true);
		getJButton_ballroomNext().setEnabled(true);
		getJButton_ballroomNext().setVisible(true);
		getJContentPaneBallroom().setPreferredSize(new Dimension(770, 749));
		getJFrame_Ballroom().setSize(new Dimension(820,569));
	}
	
	
	
	
	/********************************************************
	  * Method Name 	: retrieveEntertainment
	  * Input Parameter : void
	  * Purpose 		: To retrieve all Entertainment record
	  * Return 			: void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveEntertainment(){
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		getJTable_entertainmentList().setModel(control.processRetrieveEntertainment());
		getJTable_entertainmentList().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_entertainmentList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_entertainmentList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_entertainmentList().getColumnModel().getColumn(3).setPreferredWidth(700);
	}

	/********************************************************
	  * Method Name 	: retrieveEntertainment
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Entertainment record
	  * Return 			: void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveEntertainment(String parameter){
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		getJTable_entertainmentList().setModel(control.processRetrieveEntertainment());
		getJTable_entertainmentList().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_entertainmentList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_entertainmentList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_entertainmentList().getColumnModel().getColumn(3).setPreferredWidth(700);
	}
	/********************************************************
	  * Method Name 	: retrieveEntertainmentByProgram
	  * Input Parameter : String
	  * Purpose 		: To retrieve all Entertainmetn record like paramater from entertainmentmenu
	  * Return 			: void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveEntertainmentByProgram(String parameter){
		DefaultTableModel mModel= new DefaultTableModel();
		DefaultTableModel model= new DefaultTableModel();
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		mModel=control.processRetrieveEntertainmentMenu(parameter);
		
		model.setColumnIdentifiers(new Object[]{"ID","Title","Price/hr","Description"});
		//do a for loop? repeated one how?
		for(int i=0; i<mModel.getRowCount();i++){
			control.processRetrieveEntertainmentByID(mModel.getValueAt(i, 0).toString());
			model.addRow(new Object[]{control.getEntertainment().getEntertainmentID().toString(),control.getEntertainment().getEntertainmentTitle().toString(),control.getEntertainment().getEntertainmentFinalPrice(),control.getEntertainment().getEntertainmentDescription().toString()});
		}
		//remove dupicates
		for(int i=0; i<model.getRowCount();i++){
			String id=model.getValueAt(i, 0).toString();
			for(int n=i+1;n<model.getRowCount();n++){
				if(id.equals(model.getValueAt(n, 0).toString())){
					model.removeRow(n);
				}
			}
		}
		//set the table properties
		getJTable_entertainmentList().setModel(model);
		getJTable_entertainmentList().setAutoResizeMode(getJTable_entertainmentList().AUTO_RESIZE_OFF);
		getJTable_entertainmentList().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_entertainmentList().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_entertainmentList().getColumnModel().getColumn(3).setPreferredWidth(700);
	}
	/********************************************************
	  * Method Name : retrieveEntertainmentByID
	  * Input Parameter : String
	  * Purpose : To retrieve all entertainment record by ID
	  * Return :void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveEntertainmentByID(String ID){
		DecimalFormat fmt = new DecimalFormat("0.00");
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		control.processRetrieveEntertainmentByID(ID);

		getJFrameEntertainment().setSize(new Dimension(820, 569));
		getJContentPaneEntertainment().setPreferredSize(new Dimension(750, 745));
		//setting the fields
		getJTextField_entertainmentName().setText(control.getEntertainment().getEntertainmentTitle().toString());
		getJTextField_entertainmentName().setName(control.getEntertainment().getEntertainmentID().toString());
		getJTextField_entertainmentPrice().setText("$"+fmt.format(control.getEntertainment().getEntertainmentFinalPrice()));
		getJTextArea_entertainmentDescription().setText(control.getEntertainment().getEntertainmentDescription().toString());
		
		getJTable_entertainmentMenu().setModel(control.getModel());
		getJTable_entertainmentMenu().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_entertainmentMenu().getColumnModel().getColumn(0).setPreferredWidth(450);
		getJTable_entertainmentMenu().getColumnModel().getColumn(1).setPreferredWidth(150);
		getJTable_entertainmentMenu().getColumnModel().getColumn(2).setPreferredWidth(610);
		
		getJButton_entertainmentPrevious().setVisible(true);
		getJButton_entertainmentNext().setVisible(true);
		
		
	}
	

	
	
	
	
	
	/********************************************************
	  * Method Name : retrieveMeal
	  * Input Parameter : void
	  * Purpose : To retrieve all Meal record
	  * Return :void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveMeal(){
		AdministrateMealControl control= new AdministrateMealControl();
		getJTable_mealLIst().setModel(control.processRetrieveMeal());
		getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(150);
		getJTable_mealLIst().getColumnModel().getColumn(4).setPreferredWidth(550);
	}


	/********************************************************
	  * Method Name : retrieveMeal
	  * Input Parameter : String
	  * Purpose : To retrieve all Meal record by parameter
	  * Return :void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveMeal(String parameter){
		AdministrateMealControl control= new AdministrateMealControl();
		getJTable_mealLIst().setModel(control.processRetrieveMeal(parameter));
		getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(150);
		getJTable_mealLIst().getColumnModel().getColumn(4).setPreferredWidth(550);
	}

	/********************************************************
	  * Method Name : retrieveMealByDish
	  * Input Parameter : String
	  * Purpose : To retrieve all Meal record by mealDIsh/menu
	  * Return :void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveMealByDish(String parameter){
		DefaultTableModel mModel= new DefaultTableModel();
		DefaultTableModel model= new DefaultTableModel();
		AdministrateMealControl control= new AdministrateMealControl();
		mModel=control.processRetrieveMealMenu(parameter);
		
		model.setColumnIdentifiers(new Object[]{"ID","Name","Price","Description"});
		for(int i=0; i<mModel.getRowCount();i++){
			control.procesRetrieveMealByID(mModel.getValueAt(i, 0).toString());
			model.addRow(new Object[]{control.getMeal().getMealID().toString(),control.getMeal().getMealTitle().toString(),control.getMeal().getMealFinalPrice(),control.getMeal().getMealDescription().toString()});
		}
		//filter
		for(int i=0; i<model.getRowCount();i++){
			String id=model.getValueAt(i, 0).toString();
			for(int n=i+1;n<model.getRowCount();n++){
				if(id.equals(model.getValueAt(n, 0).toString())){
					model.removeRow(n);
				}
			}
		}
		//set
		getJTable_mealLIst().setModel(model);
		getJTable_mealLIst().setAutoResizeMode(jTable_ballroomList.AUTO_RESIZE_OFF);
		getJTable_mealLIst().getColumnModel().getColumn(0).setPreferredWidth(50);
		getJTable_mealLIst().getColumnModel().getColumn(1).setPreferredWidth(500);
		getJTable_mealLIst().getColumnModel().getColumn(2).setPreferredWidth(150);
		getJTable_mealLIst().getColumnModel().getColumn(3).setPreferredWidth(700);
	}
	
	/********************************************************
	  * Method Name : retrieveMealByID
	  * Input Parameter : String
	  * Purpose : To retrieve all Meal record by ID
	  * Return :void
	  *******************************************************/
	@SuppressWarnings("static-access")
	public void retrieveMealByID(String ID){
		DecimalFormat fmt = new DecimalFormat("0.00");
		AdministrateMealControl control= new AdministrateMealControl();
		control.procesRetrieveMealByID(ID);
		
		
		getJFrameMeal().setSize(new Dimension(820, 569));
		getJContentPaneMeal().setPreferredSize(new Dimension(750, 745));
		
		getJTextField_mealName().setText(control.getMeal().getMealTitle());
		getJTextField_mealName().setName(control.getMeal().getMealID());
		getJTextField_mealType().setText(control.getMeal().getMealType());
		getJTextField_mealPrice().setText("$"+fmt.format(control.getMeal().getMealFinalPrice()));
		getJTextArea_mealDescription().setText(control.getMeal().getMealDescription());
		getJTable_mealList().setModel(control.getModel());
		getJTable_mealLIst().setAutoResizeMode(jTable_mealLIst.AUTO_RESIZE_OFF);
		getJTable_mealList().getColumnModel().getColumn(0).setPreferredWidth(450);
		getJTable_mealList().getColumnModel().getColumn(1).setPreferredWidth(135);
		getJTable_mealList().getColumnModel().getColumn(2).setPreferredWidth(100);
		getJTable_mealList().getColumnModel().getColumn(3).setPreferredWidth(100);
		getJTable_mealList().getColumnModel().getColumn(4).setPreferredWidth(500);
		
		getJButton_mealPrevious().setVisible(true);
		getJButton_mealNext().setVisible(true);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	  * Method Name : validate()
	  * Input Parameter : void
	  * Purpose : To validate before basic CRUD
	  * Return :boolean
	  *******************************************************/
	@SuppressWarnings("deprecation")
	public boolean validatePackageDetails(){
		boolean success=true;
		if(getJTextField_packageTitle().getText().equals("")||getJTextField_packageTitle().getText().equals("                                                   Enter Package Name Here")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please enter a Package title", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJTextField_packageTitle().requestFocus();
			main.interrupt();
		}
		else if(getJTextArea_packageDescription().getText().equals("")||getJTextArea_packageDescription().getText().equals("\n\n                                                     Enter a Description Here")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please enter a Package description", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJTextArea_packageDescription().requestFocus();
			main.interrupt();
		}
		else if(getJTextField_Ballroom().getText().equals("")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select a ballroom", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJButton_ballroom().requestFocus();
			main.interrupt();
		}
		else if(getJCheckBox_entertainment().isSelected() && getJTextField_entertainment().getText().equals("")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select a entertainment if you had choose to have one", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJButton_entertainment().requestFocus();
			main.interrupt();
		}
		else if(getJCheckBox_mealOption1().isSelected() && getJTextField_mealOption1().getText().equals("")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select a meal option if you had choose to have one", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJButton_mealOption1().requestFocus();
			main.interrupt();
		}
		else if(getJCheckBox_mealOption2().isSelected() && getJTextField_mealOption2().getText().equals("")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select a meal option if you had choose to have one", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJButton_mealOption2().requestFocus();
			main.interrupt();
		}
		else if(getJCheckBox_mealOption3().isSelected() && getJTextField_mealOption3().getText().equals("")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select a meal option if you had choose to have one", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJButton_mealOption3().requestFocus();
			main.interrupt();
		}
		return success;
	}

	/********************************************************
	 * Method Name 		: displaySummary()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To display the summary of the form
	 *******************************************************/
	public void displaySummary(){
		String header="=======================================\n";
		header+="                                Package Summary List\n";
		header+="=======================================\n\n";
		
		String content="";
		content+="Package Name : "+getJTextField_packageTitle().getText().toString()+"\n";
		content+="Ballroom Name : "+getJTextField_ballroomName().getText().toString()+"\n";
		if(getJCheckBox_entertainment().isSelected())
			content+="Entertainment Name : "+getJTextField_entertainment().getText().toString()+"\n";
		
		 int mealoptions=0;
			if(getJCheckBox_mealOption1().isSelected())mealoptions++;
			if(getJCheckBox_mealOption2().isSelected())mealoptions++;
			if(getJCheckBox_mealOption3().isSelected())mealoptions++;
			if(mealoptions!=0){
				if(mealoptions==1){
					content+=("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()+"\n");
				}
				else if(mealoptions==2){
					content+=("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()+"\n");
					content+=("Meal option 2 Name : "+getJTextField_mealOption2().getText().toString()+"\n");
				}
				else if(mealoptions==3){
					content+=("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()+"\n");
					content+=("Meal option 2 Name : "+getJTextField_mealOption2().getText().toString()+"\n");
					content+=("Meal option 3 Name : "+getJTextField_mealOption3().getText().toString()+"\n");
				}
				
			}
		
		content+="________________\n";
		content+="Discount :"+getJTextField_discount().getText()+"%\n";
		
		
		getJTextArea_summary().setText(header+content);
	}
	
	/********************************************************
	 * Method Name 		: download()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer
	 *******************************************************/
	public void download(String directory)throws MalformedURLException, DocumentException, IOException{
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
				downloadTXT(PDFlink+".csv");
			}
			else if(directory.substring(directory.length()-2).equals(".csv")){
				Scanner sc2= new Scanner(directory);
				String d=".csv";
				sc2.useDelimiter(d);
				while(sc2.hasNext()){
					TXTlink=sc2.next();
				}
				downloadPDF(TXTlink+".pdf");
				downloadTXT(TXTlink+".csv");
			}
			else{
				downloadPDF(directory+".pdf");
				downloadTXT(directory+".csv");
			}
	}
	
	/********************************************************
	 * Method Name 		: downloadPDF()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer in PDF
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void downloadPDF(String path) throws DocumentException, MalformedURLException, IOException{
		String directory=path;
		//writting the pdf
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
		 pdf.add(new Paragraph("Package Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID          : "+getJTextField_packageID().getText().toString()));
		 pdf.add(new Paragraph("Title       : "+getJTextField_packageTitle().getText().toString()));
		 pdf.add(new Paragraph("Description : "+getJTextArea_packageDescription().getText().toString()));
		//etc
		
		 
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Selected Ballroom ID : "+getJTextField_Ballroom().getName().toString()));
		 pdf.add(new Paragraph("Selected Ballroom Name : "+getJTextField_Ballroom().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 if(getJCheckBox_entertainment().isSelected()){
			 pdf.add(new Paragraph("Selected Entertainment ID : "+getJTextField_entertainment().getName().toString()));
			 pdf.add(new Paragraph("Selected Entertainment Name : "+getJTextField_entertainment().getText().toString()));
		 }
		 //do the same for meal
		 int mealoptions=0;
		if(getJCheckBox_mealOption1().isSelected())mealoptions++;
		if(getJCheckBox_mealOption2().isSelected())mealoptions++;
		if(getJCheckBox_mealOption3().isSelected())mealoptions++;
		if(mealoptions!=0){
			if(mealoptions==1){
				pdf.add(new Paragraph("Meal option 1 ID : "+getJTextField_mealOption1().getName().toString()));
				pdf.add(new Paragraph("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()));
			}
			else if(mealoptions==2){
				pdf.add(new Paragraph("Meal option 1 ID : "+getJTextField_mealOption1().getName().toString()));
				pdf.add(new Paragraph("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()));
				pdf.add(new Paragraph("Meal option 2 ID : "+getJTextField_mealOption2().getName().toString()));
				pdf.add(new Paragraph("Meal option 2 Name : "+getJTextField_mealOption2().getText().toString()));
			}
			else if(mealoptions==3){
				pdf.add(new Paragraph("Meal option 1 ID : "+getJTextField_mealOption1().getName().toString()));
				pdf.add(new Paragraph("Meal option 1 Name : "+getJTextField_mealOption1().getText().toString()));
				pdf.add(new Paragraph("Meal option 2 ID : "+getJTextField_mealOption2().getName().toString()));
				pdf.add(new Paragraph("Meal option 2 Name : "+getJTextField_mealOption2().getText().toString()));
				pdf.add(new Paragraph("Meal option 3 ID : "+getJTextField_mealOption3().getName().toString()));
				pdf.add(new Paragraph("Meal option 3 Name : "+getJTextField_mealOption3().getText().toString()));
			}
			
		}
		 pdf.add(new Paragraph("Entitled Discount \t: "+getJTextField_discount().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph("                                                                         End                                               "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph());
		 pdf.add(new Paragraph());
		 
		 pdf.close();
		
		//prompt success
		 progress.interrupt();
		 progress.stop();
		 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
		 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
		 JOptionPane.showMessageDialog(null, "File Downloaded Successfully at "+path, "Downloads", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/********************************************************
	 * Method Name 		: downloadTXT()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To download the details of the form 
	 * 					  into the local computer in TXT
	 * @throws IOException 
	 *******************************************************/
	public void downloadTXT(String path) throws IOException{
		CSVController controller= new CSVController();
		ArrayList<String[]> data = new ArrayList<String[]>();
		System.out.println(getJTextField_ballroomName().getText().toString());
		System.out.println("downloading csv...");
		
		String[]packageHeader=new String[15];
		packageHeader[0]="PACKAGE_ID";
		packageHeader[1]="PACKAGE_TITLE";
		packageHeader[2]="PACKAGE_AVAILABILITY";
		packageHeader[3]="PACKAGE_DESCRIPTION";
		packageHeader[4]="BALLROOM_ID";
		packageHeader[5]="BALLROOM_NAME";
		packageHeader[6]="ENTERTAINMENT_ID";
		packageHeader[7]="ENTERTAINMENT_NAME";
		packageHeader[8]="MEAL_OPTION1_ID";
		packageHeader[9]="MEAL_OPTION1_NAME";
		packageHeader[10]="MEAL_OPTION2_ID";
		packageHeader[11]="MEAL_OPTION2_NAME";
		packageHeader[12]="MEAL_OPTION3_ID";
		packageHeader[13]="MEAL_OPTION3_NAME";
		packageHeader[14]="PACKAGE_DISCOUNT";
		
		String[] packageData= new String[15];
		packageData[0]=getJTextField_packageID().getText().toString();
		packageData[1]=getJTextField_packageTitle().getText().toString();
		if(getJCheckBox_packageAvailability().isSelected())
			packageData[2]="YES";
		if(!getJCheckBox_packageAvailability().isSelected())
			packageData[2]="NO";
		packageData[3]=getJTextArea_packageDescription().getText().toString();
		packageData[4]=ballroomID;
		packageData[5]=getJTextField_Ballroom().getText().toString();
		
		packageData[6]=entertainmentID;
		packageData[7]=getJTextField_entertainment().getText().toString();
		packageData[8]=mealOption1ID;
		packageData[9]=getJTextField_mealOption1().getText().toString();
		packageData[10]=mealOption2ID;
		packageData[11]=getJTextField_mealOption2().getText().toString();
		packageData[12]=mealOption3ID;
		packageData[13]=getJTextField_mealOption3().getText().toString();
		packageData[14]=getJSlider_discount().getValue()+"";
		data.add(packageHeader);
		data.add(packageData);
		controller.WriteFile(data, path);
	}
	
	
	/********************************************************
	 * Method Name : createTabHeader
	 * Input Parameter : nil
	 * Purpose : To create and set the custom Tab Header.
	 * Return : nil
	 * Tested : Success
	 *******************************************************/
	private void createTabHeader(int index){
		CustomTabHeader tab=new CustomTabHeader(AdministrateServiceOptionManagement.getJTabbedPane());
		AdministrateServiceOptionManagement.getJTabbedPane().setTabComponentAt( index,tab );
		
	}
	
	/********************************************************
	 * Method Name : newPackageTab
	 * Input Parameter : nil 
	 * Purpose : To create and set a new Package Tab content.
	 * Return : nil
	 *******************************************************/
	public void newPackageTab(){	
		AdministratePackageForm meal=new AdministratePackageForm();
		if(AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()==1){
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Package Form",null , meal.getJScrollPane(),null , 1); // sets the content
			createTabHeader(1);	//sets the custom tab header
			AdministrateServiceOptionManagement.getJTabbedPane().remove(0);
			AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(0);
		}
		else{
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Package Form",null , meal.getJScrollPane(),null , AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()); // sets the content
			createTabHeader(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);	//sets the custom tab header
			AdministrateServiceOptionManagement.getJTabbedPane().remove(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			if(!(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()==AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()-1)){
				AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);
				}
			}
	}
	
	/********************************************************
	 * Method Name 		: createPackage()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To create a new Package record
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void createPackage(){
		//preparing the data
		String ID=""; 
		String EntertainmentID="";
		if(!getJTextField_entertainment().getText().equals("")){
			EntertainmentID=getJTextField_entertainment().getName().toString();
		}
		String BallroomID="";
		if(!getJTextField_Ballroom().getText().equals("")){
			BallroomID=getJTextField_Ballroom().getName().toString(); 
		}
		String Type="Standard"; 
		String Title=getJTextField_packageTitle().getText().toString(); 
		String Description=getJTextArea_packageDescription().getText().toString(); 
		boolean Availability=getJCheckBox_packageAvailability().isSelected(); 
		int Hits=0; 
		double Discount=getJSlider_discount().getValue(); 
		boolean isRecord=false;
		
		String mealID1="null";
		if(!getJTextField_mealOption1().getText().equals("")){
			mealID1=getJTextField_mealOption1().getName().toString();
		}
		String mealID2="null";
		if(!getJTextField_mealOption2().getText().equals("")){
			mealID2=getJTextField_mealOption2().getName().toString();
		}
		String mealID3="null";
		if(!getJTextField_mealOption3().getText().equals("")){
			mealID3=getJTextField_mealOption3().getName().toString();
		}
		
		AdministratePackageControl control= new AdministratePackageControl(ID, EntertainmentID, BallroomID, Type, Title, Description,Availability, Hits,Discount, isRecord);
		ID=control.processCreatePackage(mealID1, mealID2, mealID3);
		
		if(ID.equals(null)){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Failed to create package record", "Warnning", JOptionPane.ERROR_MESSAGE);
		}
		else{
			getJTextField_packageID().setText(ID);
			getJButton_upload().setEnabled(false);
			getJButton_delete().setEnabled(true);
			getJButton_update().setEnabled(true);
			getJButton_download().setEnabled(true);
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Successfully created a package record", "Warnning", JOptionPane.INFORMATION_MESSAGE);
			displaySummary();
		}
		
	}
	
	/********************************************************
	 * Method Name 		: deletePackage()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To delete the existing  Package record
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void deletePackage(){
		String ID=getJTextField_packageID().getText().toString();
		AdministratePackageControl control= new AdministratePackageControl();
		if(control.processDeletePackage(ID)){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Successfully deleted package record", "Success", JOptionPane.INFORMATION_MESSAGE);
			newPackageTab();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Failed to delete package record", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	/********************************************************
	  * Method Name : updatePackage
	  * Input Parameter : String
	  * Purpose : To update an package record
	  * Return :boolean
	  *******************************************************/
	@SuppressWarnings("deprecation")
	public void updatePackage(){
		//preparing the data
		String ID=getJTextField_packageID().getText().toString();
		String EntertainmentID="";
		if(!getJTextField_entertainment().getText().equals("")){
			EntertainmentID=getJTextField_entertainment().getName().toString();
		}
		String BallroomID="";
		if(!getJTextField_Ballroom().getText().equals("")){
			BallroomID=getJTextField_Ballroom().getName().toString(); 
		}
		String Type="Standard"; 
		String Title=getJTextField_packageTitle().getText().toString(); 
		String Description=getJTextArea_packageDescription().getText().toString(); 
		boolean Availability=getJCheckBox_packageAvailability().isSelected(); 
		int Hits=0; 
		double Discount=getJSlider_discount().getValue(); 
		boolean isRecord=false;
		
		String mealID1="null";
		if(!getJTextField_mealOption1().getText().equals("")){
			mealID1=getJTextField_mealOption1().getName().toString();
		}
		String mealID2="null";
		if(!getJTextField_mealOption2().getText().equals("")){
			mealID2=getJTextField_mealOption2().getName().toString();
		}
		String mealID3="null";
		if(!getJTextField_mealOption3().getText().equals("")){
			mealID3=getJTextField_mealOption3().getName().toString();
		}
		
		AdministratePackageControl control= new AdministratePackageControl(ID, EntertainmentID, BallroomID, Type, Title, Description,Availability, Hits,Discount, isRecord);
		if(control.processUpdatePackage(mealID1, mealID2, mealID3)){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Successfully updated package record", "Success", JOptionPane.INFORMATION_MESSAGE);
			displaySummary();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Failed to update package record", "Error", JOptionPane.ERROR_MESSAGE);
			displaySummary();
		}
		
	}
}

