/********************************************************************************************************************************************************
Program Name			:	AdministrateEntertainmentForm.java
Description				:	A EntertainmentForm class that is used for Creation, Update & Deletion of entertainment record
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Update				:	6-29-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	addEntertainment() : void
						:	validateEntertainmentDetails() : Boolean
						:	calculatePrice() : Double
						:	calculateFinalPrice() : Double
						:	displaySummary() : void
						:	download() : void
						:	downloadPDF(String) : void
						:	downloadTXT(String) : void
						:	createTabHeader() : void
						:	newEntertainmentTab() : void
						:	createEntertainment() : void
						:	deleteEntertainment() : Boolean
						:	updateEntertainment() : void
********************************************************************************************************************************************************/
package View.SOM;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.SOM.AdministrateEntertainmentControl;
import Controller.MyCalendar;
import Controller.SOM.CSVController;

/********************************************************
 *					Start of Class
 *******************************************************/
public class AdministrateEntertainmentForm {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="369,144"
	private JPanel jPanel = null;
	private JPanel jPanel_header = null;
	private JLabel jLabel_entertainmentID = null;
	private JLabel jLabel_entertainmentTitle = null;
	private JLabel jLabel_entertainmentDescription = null;
	private JTextField jTextField_entertaimentID = null;
	private JCheckBox jCheckBox_entertainmentAvailability = null;
	private JTextField jTextField_entertainmentTitle = null;
	private JScrollPane jScrollPane_entertainmentDescription = null;
	private JPanel jPanel_Menu = null;
	private JTextArea jTextArea_entertainmentDescription = null;
	private JLabel jLabel_entertainmentMenu = null;
	private JScrollPane jScrollPane_entertainmentMenu = null;
	private JTable jTable_entertainmentMenu = null;
	private JTextField jTextField_entertainmentMenu = null;
	private JTextField jTextField_entertainmentPrice = null;
	private JButton jButton_Add = null;
	private JPanel jPanel_summary = null;
	private JLabel jLabel_entertainmentDiscount = null;
	private JLabel jLabel_entertainmentTotalPrice = null;
	private JSlider jSlider_entertainmentDiscount = null;
	private JTextField jTextField_entertainmentTotalPrice = null;
	private JTextField jTextField_entertainmentDiscount = null;
	private JLabel jLabel_entertainmentAvailability = null;
	private String []Header={"Entertainment Name","Price/hr","Description"};	//for the jtable
	protected DefaultTableModel model= new DefaultTableModel();//for the jTable
	private JPopupMenu jPopupMenu = null;  //  @jve:decl-index=0:visual-constraint="209,861"
	private JMenuItem jMenuItem_remove = null;
	private JScrollPane jScrollPane_formula = null;
	private JTextArea jTextArea_formula = null;
	private JLabel jLabel_$ = null;
	private JButton jButton_delete = null;
	private JButton jButton_Update = null;
	private JButton jButton_download = null;
	private JButton jButton_createEntertainment = null;
	private JScrollPane jScrollPane_entertainmentMenuDescription = null;
	private JTextArea jTextArea_entertainmentMenuDescription = null;
	final JFileChooser fc = new JFileChooser();
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
			jPanel.setPreferredSize(new Dimension(1000, 1106));
			jPanel.setBackground(SystemColor.control);
			jPanel.add(getJPanel_header(), null);
			jPanel.add(getJPanel_Menu(), null);
			jPanel.add(getJPanel_summary(), null);
		}
		return jPanel;
	}
	/********************************************************
	 *					Start of header
	 *******************************************************/
	private JPanel getJPanel_header() {
		if (jPanel_header == null) {
			jLabel_entertainmentAvailability = new JLabel();
			jLabel_entertainmentAvailability.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentAvailability.setBounds(new Rectangle(450, 40, 150, 30));
			jLabel_entertainmentAvailability.setText("Availability :");
			jLabel_entertainmentDescription = new JLabel();
			jLabel_entertainmentDescription.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentDescription.setBounds(new Rectangle(50, 120, 81, 30));
			jLabel_entertainmentDescription.setText("Description : ");
			jLabel_entertainmentTitle = new JLabel();
			jLabel_entertainmentTitle.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentTitle.setBounds(new Rectangle(50, 80, 81, 30));
			jLabel_entertainmentTitle.setText("Title :");
			jLabel_entertainmentID = new JLabel();
			jLabel_entertainmentID.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentID.setBounds(new Rectangle(50, 40, 80, 30));
			jLabel_entertainmentID.setFont(new Font("Levenim MT", Font.BOLD, 12));
			jLabel_entertainmentID.setText("ID : ");
			jPanel_header = new JPanel();
			jPanel_header.setLayout(null);
			jPanel_header.setLocation(100,30);
			jPanel_header.setPreferredSize(new Dimension(800,260));
			jPanel_header.setBounds(new Rectangle(100, 30, 800, 260));
			jPanel_header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_header.add(jLabel_entertainmentID, null);
			jPanel_header.add(jLabel_entertainmentTitle, null);
			jPanel_header.add(jLabel_entertainmentDescription, null);
			jPanel_header.add(getJTextField_entertaimentID(), null);
			jPanel_header.add(getJCheckBox_entertainmentAvailability(), null);
			jPanel_header.add(getJTextField_entertainmentTitle(), null);
			jPanel_header.add(getJScrollPane_entertainmentDescription(), null);
			jPanel_header.add(jLabel_entertainmentAvailability, null);
		}
		return jPanel_header;
	}
	protected JTextField getJTextField_entertaimentID() {
		if (jTextField_entertaimentID == null) {
			jTextField_entertaimentID = new JTextField();
			jTextField_entertaimentID.setEnabled(false);
			jTextField_entertaimentID.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertaimentID.setForeground(SystemColor.scrollbar);
			jTextField_entertaimentID.setText("  Generated After Creation");
			jTextField_entertaimentID.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_entertaimentID.setBounds(new Rectangle(130, 40, 200, 30));
		}
		return jTextField_entertaimentID;
	}
	protected JCheckBox getJCheckBox_entertainmentAvailability() {
		if (jCheckBox_entertainmentAvailability == null) {
			jCheckBox_entertainmentAvailability = new JCheckBox();
			jCheckBox_entertainmentAvailability.setBounds(new Rectangle(550, 40, 200, 30));
			jCheckBox_entertainmentAvailability.setFocusable(false);
			jCheckBox_entertainmentAvailability.setFocusPainted(false);
			jCheckBox_entertainmentAvailability.setText("Not Available");
			jCheckBox_entertainmentAvailability
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							if(jCheckBox_entertainmentAvailability.isSelected())
								jCheckBox_entertainmentAvailability.setText("Available");
							else
								jCheckBox_entertainmentAvailability.setText("Not Available");
						}
					});
		}
		return jCheckBox_entertainmentAvailability;
	}
	protected JTextField getJTextField_entertainmentTitle() {
		if (jTextField_entertainmentTitle == null) {
			jTextField_entertainmentTitle = new JTextField();
			jTextField_entertainmentTitle.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertainmentTitle.setToolTipText("Enter a title for this entertainment set");
			jTextField_entertainmentTitle.setText("Enter a Title Here...");
			jTextField_entertainmentTitle.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextField_entertainmentTitle.setForeground(SystemColor.scrollbar);
			jTextField_entertainmentTitle.setBounds(new Rectangle(130, 80, 620, 30));
			jTextField_entertainmentTitle
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_entertainmentTitle.getText().equals("")){
						jTextField_entertainmentTitle.setText("Enter a Title Here...");
						jTextField_entertainmentTitle.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextField_entertainmentTitle.getText().equals("                                                           Enter a Title Here...")){
								jTextField_entertainmentTitle.setText("");
								jTextField_entertainmentTitle.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextField_entertainmentTitle;
	}
	private JScrollPane getJScrollPane_entertainmentDescription() {
		if (jScrollPane_entertainmentDescription == null) {
			jScrollPane_entertainmentDescription = new JScrollPane();
			jScrollPane_entertainmentDescription.setBounds(new Rectangle(130, 120, 620, 100));
			jScrollPane_entertainmentDescription.setViewportView(getJTextArea_entertainmentDescription());
		}
		return jScrollPane_entertainmentDescription;
	}
	protected JTextArea getJTextArea_entertainmentDescription() {
		if (jTextArea_entertainmentDescription == null) {
			jTextArea_entertainmentDescription = new JTextArea();
			jTextArea_entertainmentDescription.setWrapStyleWord(true);
			jTextArea_entertainmentDescription.setLineWrap(true);
			jTextArea_entertainmentDescription.setText("\n\n                                                       Enter a Description Here...");
			jTextArea_entertainmentDescription.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextArea_entertainmentDescription.setForeground(SystemColor.scrollbar);
			jTextArea_entertainmentDescription.setToolTipText("Enter a Description here");
			jTextArea_entertainmentDescription
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_entertainmentDescription.getText().equals("")){
						jTextArea_entertainmentDescription.setText("\n\n                                                       Enter a Description Here...");
						jTextArea_entertainmentDescription.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_entertainmentDescription.getText().equals("\n\n                                                       Enter a Description Here...")){
								jTextArea_entertainmentDescription.setText("");
								jTextArea_entertainmentDescription.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextArea_entertainmentDescription;
	}
	/********************************************************
	 *					Start of body
	 *******************************************************/
	private JPanel getJPanel_Menu() {
		if (jPanel_Menu == null) {
			jLabel_$ = new JLabel();
			jLabel_$.setBounds(new Rectangle(423, 240, 15, 30));
			jLabel_$.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			jLabel_$.setText("$");
			jLabel_entertainmentMenu = new JLabel();
			jLabel_entertainmentMenu.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentMenu.setBounds(new Rectangle(50, 40, 150, 30));
			jLabel_entertainmentMenu.setText("Entertainment List : ");
			jPanel_Menu = new JPanel();
			jPanel_Menu.setPreferredSize(new Dimension(423,240));
			jPanel_Menu.setLayout(null);
			jPanel_Menu.setBounds(new Rectangle(100, 320, 800, 413));
			jPanel_Menu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_Menu.add(jLabel_entertainmentMenu, null);
			jPanel_Menu.add(getJScrollPane_entertainmentMenu(), null);
			jPanel_Menu.add(getJTextField_entertainmentMenu(), null);
			jPanel_Menu.add(getJTextField_entertainmentPrice(), null);
			jPanel_Menu.add(getJButton_Add(), null);
			jPanel_Menu.add(jLabel_$, null);
			jPanel_Menu.add(getJScrollPane_entertainmentMenuDescription(), null);
		}
		return jPanel_Menu;
	}
	private JScrollPane getJScrollPane_entertainmentMenu() {
		if (jScrollPane_entertainmentMenu == null) {
			jScrollPane_entertainmentMenu = new JScrollPane();
			jScrollPane_entertainmentMenu.setBounds(new Rectangle(50, 80, 700, 150));
			jScrollPane_entertainmentMenu.setViewportView(getJTable_entertainmentMenu());
		}
		return jScrollPane_entertainmentMenu;
	}
	@SuppressWarnings("static-access")
	protected JTable getJTable_entertainmentMenu() {
		if (jTable_entertainmentMenu == null) {
			model.setColumnIdentifiers(Header);
			jTable_entertainmentMenu = new JTable();
			jTable_entertainmentMenu.setModel(model);
			jTable_entertainmentMenu.setOpaque(false);
			jTable_entertainmentMenu.setEnabled(false);
			jTable_entertainmentMenu.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			jTable_entertainmentMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTable_entertainmentMenu.setIntercellSpacing(new Dimension(5, 5));
			jTable_entertainmentMenu.setRowSelectionAllowed(true);
			jTable_entertainmentMenu.setShowGrid(false);
			jTable_entertainmentMenu.setFocusable(false);
			jTable_entertainmentMenu.setColumnSelectionAllowed(false);
			jTable_entertainmentMenu.setEnabled(false);
			jTable_entertainmentMenu.setAutoResizeMode(jTable_entertainmentMenu.AUTO_RESIZE_OFF);
			jTable_entertainmentMenu.getColumnModel().getColumn(0).setPreferredWidth(565);
			jTable_entertainmentMenu.getColumnModel().getColumn(1).setPreferredWidth(135);
			jTable_entertainmentMenu.getColumnModel().getColumn(2).setPreferredWidth(698);
			jTable_entertainmentMenu.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseReleased(MouseEvent e) {
		            int r = jTable_entertainmentMenu.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < jTable_entertainmentMenu.getRowCount()) {
		            	jTable_entertainmentMenu.setRowSelectionInterval(r, r);
		            } else {
		            	jTable_entertainmentMenu.clearSelection();
		            }

		            int rowindex = jTable_entertainmentMenu.getSelectedRow();
		            if (rowindex < 0)
		                return;
		            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		                JPopupMenu popup = getJPopupMenu();
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        }});

		}
		return jTable_entertainmentMenu;
	}
	private JTextField getJTextField_entertainmentMenu() {
		if (jTextField_entertainmentMenu == null) {
			jTextField_entertainmentMenu = new JTextField();
			jTextField_entertainmentMenu.setHorizontalAlignment(JTextField.CENTER);
			jTextField_entertainmentMenu.setBounds(new Rectangle(50, 240, 370, 30));
			jTextField_entertainmentMenu.setForeground(SystemColor.scrollbar);
			jTextField_entertainmentMenu.setText("Enter Entertainment Name Here...");
			jTextField_entertainmentMenu
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_entertainmentMenu.getText().equals("")){
						jTextField_entertainmentMenu.setText("Enter Entertainment Name Here...");
						jTextField_entertainmentMenu.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextField_entertainmentMenu.getText().equals("                      Enter Entertainment Name Here...")){
								jTextField_entertainmentMenu.setText("");
								jTextField_entertainmentMenu.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextField_entertainmentMenu;
	}
	private JTextField getJTextField_entertainmentPrice() {
		if (jTextField_entertainmentPrice == null) {
			jTextField_entertainmentPrice = new JTextField();
			jTextField_entertainmentPrice.setText("0.00");
			jTextField_entertainmentPrice.setForeground(SystemColor.scrollbar);
			jTextField_entertainmentPrice.setBounds(new Rectangle(440, 240, 150, 30));
			jTextField_entertainmentPrice
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_entertainmentPrice.getText().equals("")){
						jTextField_entertainmentPrice.setText("0.00");
						jTextField_entertainmentPrice.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextField_entertainmentPrice.getText().equals("0.00")){
								jTextField_entertainmentPrice.setText("");
								jTextField_entertainmentPrice.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextField_entertainmentPrice;
	}
	private JButton getJButton_Add() {
		if (jButton_Add == null) {
			jButton_Add = new JButton();
			jButton_Add.setEnabled(true);
			jButton_Add.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_Add.setFocusable(false);
			jButton_Add.setFocusPainted(false);
			jButton_Add.setBounds(new Rectangle(600, 240, 150, 30));
			jButton_Add.setText("Add");
			jButton_Add.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) { 
					
					Scanner scanner = new Scanner(getJTextField_entertainmentPrice().getText().toString());
					boolean isDigit=false;
					if (!scanner.hasNextDouble()) {  
					    isDigit=true;   
					}  
					if(getJTextField_entertainmentMenu().getText().equals("")||getJTextField_entertainmentMenu().getText().equals("                      Enter Entertainment Name Here...")){
						JOptionPane.showMessageDialog(null, "Please enter a entertainment Name.", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextField_entertainmentMenu().requestFocus();
					}
					else if(getJTextField_entertainmentPrice().getText().equals("")||getJTextField_entertainmentPrice().getText().equals("0.00")){
						JOptionPane.showMessageDialog(null, "Please enter the entertainment price.", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextField_entertainmentPrice().requestFocus();
					}
					else if(getJTextArea_entertainmentMenuDescription().getText().equals("")||getJTextArea_entertainmentMenuDescription().getText().equals("\n\n                                                  Enter a short Description of the entertainment")){
						JOptionPane.showMessageDialog(null, "Please enter the entertainment description", "Missing Fields", JOptionPane.WARNING_MESSAGE);
						getJTextArea_entertainmentMenuDescription().requestFocus();
					}
					else if(isDigit){
						JOptionPane.showMessageDialog(null, "Please enter a correct value for price", "Warnning", JOptionPane.WARNING_MESSAGE);
						getJTextField_entertainmentPrice().requestFocus();
					}
					else{
						addEntertainment();
					}
				}
			
			});
		}
		return jButton_Add;
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
					model.removeRow(getJTable_entertainmentMenu().getSelectedRow());
					getJTextField_entertainmentTotalPrice().setText(""+calculatePrice());
				}
			});
		}
		return jMenuItem_remove;
	}
	private JScrollPane getJScrollPane_entertainmentMenuDescription() {
		if (jScrollPane_entertainmentMenuDescription == null) {
			jScrollPane_entertainmentMenuDescription = new JScrollPane();
			jScrollPane_entertainmentMenuDescription.setBounds(new Rectangle(50, 280, 700, 100));
			jScrollPane_entertainmentMenuDescription.setViewportView(getJTextArea_entertainmentMenuDescription());
		}
		return jScrollPane_entertainmentMenuDescription;
	}
	private JTextArea getJTextArea_entertainmentMenuDescription() {
		if (jTextArea_entertainmentMenuDescription == null) {
			jTextArea_entertainmentMenuDescription = new JTextArea();
			jTextArea_entertainmentMenuDescription.setWrapStyleWord(true);
			jTextArea_entertainmentMenuDescription.setLineWrap(true);
			jTextArea_entertainmentMenuDescription.setForeground(SystemColor.scrollbar);
			jTextArea_entertainmentMenuDescription.setFont(new Font("Dialog", Font.ITALIC, 14));
			jTextArea_entertainmentMenuDescription.setText("\n\n                                                  Enter a short Description of the entertainment");
			jTextArea_entertainmentMenuDescription
					.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_entertainmentMenuDescription.getText().equals("")){
						jTextArea_entertainmentMenuDescription.setText("\n\n                                                  Enter a short Description of the entertainment");
						jTextArea_entertainmentMenuDescription.setForeground(SystemColor.scrollbar);
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_entertainmentMenuDescription.getText().equals("\n\n                                                  Enter a short Description of the entertainment")){
								jTextArea_entertainmentMenuDescription.setText("");
								jTextArea_entertainmentMenuDescription.setForeground(SystemColor.black);
							}
						}
					});
		}
		return jTextArea_entertainmentMenuDescription;
	}
	/********************************************************
	 *					Start of summary
	 *******************************************************/
	private JPanel getJPanel_summary() {
		if (jPanel_summary == null) {
			jLabel_entertainmentTotalPrice = new JLabel();
			jLabel_entertainmentTotalPrice.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentTotalPrice.setBounds(new Rectangle(50, 40, 150, 30));
			jLabel_entertainmentTotalPrice.setText("Total Price Per Hour :    $");
			jLabel_entertainmentDiscount = new JLabel();
			jLabel_entertainmentDiscount.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jLabel_entertainmentDiscount.setText("Entitled Discount :");
			jLabel_entertainmentDiscount.setBounds(new Rectangle(400, 40, 150, 30));
			jPanel_summary = new JPanel();
			jPanel_summary.setLayout(null);
			jPanel_summary.setBounds(new Rectangle(100, 760, 800, 250));
			jPanel_summary.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_summary.add(jLabel_entertainmentTotalPrice, null);
			jPanel_summary.add(getJTextField_entertainmentTotalPrice(), null);
			jPanel_summary.add(jLabel_entertainmentDiscount, null);
			jPanel_summary.add(getJTextField_entertainmentDiscount(), null);
			jPanel_summary.add(getJSlider_entertainmentDiscount(), null);
			jPanel_summary.add(getJScrollPane_formula(), null);
			jPanel_summary.add(getJButton_delete(), null);
			jPanel_summary.add(getJButton_Update(), null);
			jPanel_summary.add(getJButton_download(), null);
			jPanel_summary.add(getJButton_createEntertainment(), null);
		}
		return jPanel_summary;
	}
	protected JSlider getJSlider_entertainmentDiscount() {
		if (jSlider_entertainmentDiscount == null) {
			jSlider_entertainmentDiscount = new JSlider();
			jSlider_entertainmentDiscount.setFocusable(false);
			jSlider_entertainmentDiscount.setMaximum(100);
			jSlider_entertainmentDiscount.setMinimum(0);
			jSlider_entertainmentDiscount.setValue(0);
			jSlider_entertainmentDiscount.setBounds(new Rectangle(400, 80, 337, 30));
			jSlider_entertainmentDiscount
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							getJTextField_entertainmentDiscount().setText(jSlider_entertainmentDiscount.getValue()+"%");
							DecimalFormat fmt = new DecimalFormat("0.00");
							getJTextField_entertainmentTotalPrice().setText(fmt.format(calculateFinalPrice()));
							displaySummary();
						}
					});
		}
		return jSlider_entertainmentDiscount;
	}
	protected JTextField getJTextField_entertainmentTotalPrice() {
		if (jTextField_entertainmentTotalPrice == null) {
			jTextField_entertainmentTotalPrice = new JTextField();
			jTextField_entertainmentTotalPrice.setText("0");
			jTextField_entertainmentTotalPrice.setEditable(false);
			jTextField_entertainmentTotalPrice.setBounds(new Rectangle(200, 40, 160, 30));
		}
		return jTextField_entertainmentTotalPrice;
	}
	protected JTextField getJTextField_entertainmentDiscount() {
		if (jTextField_entertainmentDiscount == null) {
			jTextField_entertainmentDiscount = new JTextField();
			jTextField_entertainmentDiscount.setEditable(false);
			jTextField_entertainmentDiscount.setText("0%");
			jTextField_entertainmentDiscount.setBounds(new Rectangle(550, 40, 177, 30));
		}
		return jTextField_entertainmentDiscount;
	}
	private JScrollPane getJScrollPane_formula() {
		if (jScrollPane_formula == null) {
			jScrollPane_formula = new JScrollPane();
			jScrollPane_formula.setBounds(new Rectangle(50, 79, 312, 133));
			jScrollPane_formula.setViewportView(getJTextArea_formula());
		}
		return jScrollPane_formula;
	}
	protected JTextArea getJTextArea_formula() {
		if (jTextArea_formula == null) {
			jTextArea_formula = new JTextArea();
			jTextArea_formula.setLineWrap(true);
			jTextArea_formula.setWrapStyleWord(true);
			jTextArea_formula.setEditable(false);
		}
		return jTextArea_formula;
	}
	protected JButton getJButton_download() {
		if (jButton_download == null) {
			jButton_download = new JButton();
			jButton_download.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_download.setBounds(new Rectangle(400, 115, 160, 45));
			jButton_download.setFocusable(false);
			jButton_download.setFocusPainted(false);
			jButton_download.setEnabled(false);
			jButton_download.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/download.png")));
			jButton_download.setText("Download");
			jButton_download.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(validateEntertainmentDetails()){
						
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
	protected JButton getJButton_createEntertainment() {
		if (jButton_createEntertainment == null) {
			jButton_createEntertainment = new JButton();
			jButton_createEntertainment.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_createEntertainment.setFocusable(false);
			jButton_createEntertainment.setFocusPainted(false);
			jButton_createEntertainment.setBounds(new Rectangle(570, 115, 160, 45));
			jButton_createEntertainment.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/create.png")));
			jButton_createEntertainment.setText("Upload");
			jButton_createEntertainment
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if(validateEntertainmentDetails()){
								createEntertainment();
							}
						}
					});
		}
		return jButton_createEntertainment;
	}
	protected JButton getJButton_Update() {
		if (jButton_Update == null) {
			jButton_Update = new JButton();
			jButton_Update.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_Update.setFocusable(false);
			jButton_Update.setFocusPainted(false);
			jButton_Update.setBounds(new Rectangle(570, 170, 160, 45));
			jButton_Update.setEnabled(false);
			jButton_Update.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/update.png")));
			jButton_Update.setText("Update");
			jButton_Update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(validateEntertainmentDetails()){
						updateEntertainment();
					}
				}
			});
		}
		return jButton_Update;
	}
	protected JButton getJButton_delete() {
		if (jButton_delete == null) {
			jButton_delete = new JButton();
			jButton_delete.setFont(new Font("Century Gothic", Font.BOLD, 12));
			jButton_delete.setFocusable(false);
			jButton_delete.setFocusPainted(false);
			jButton_delete.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/delete.png")));
			jButton_delete.setEnabled(false);
			jButton_delete.setBounds(new Rectangle(400, 170, 160, 45));
			jButton_delete.setText("Delete");
			jButton_delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int i=JOptionPane.showConfirmDialog(null, "You are about to delete this record\n Are you sure?", "Delete Record", JOptionPane.YES_NO_OPTION);
					if(i==0){
						if(deleteEntertainment()){
							JOptionPane.showMessageDialog(null, "Record has been deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
							newEntertainmentTab();
						}
						else{
							JOptionPane.showMessageDialog(null, "An unexpected error occured\n1)Try restarting the application.\n2)This record might be tied to an existing package, tied records cannot be deleted.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			});
		}
		return jButton_delete;
	}
	/********************************************************
	 *					End of UI
	 *******************************************************/


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********************************************************
	 *				Start of Custom Methods
	 *******************************************************/
	
	/********************************************************
	 * Method Name 		: addEntertainment()
	 * Input Parameter 	: void
	 * Return 			: void
	 * Purpose 			: To add an enetrtainmentMenu into 
	 * 					  the entertainment List Table
	 *******************************************************/
	public void addEntertainment(){
		String entertainmentName=getJTextField_entertainmentMenu().getText().toString();
		String entertainmentPrice=getJTextField_entertainmentPrice().getText().toString();
		String entertainmentDescription=getJTextArea_entertainmentMenuDescription().getText().toString();
		//ADD THE ENTERTAINMENT
		model.addRow(new Object[]{entertainmentName,entertainmentPrice,entertainmentDescription});
		getJTable_entertainmentMenu().setModel(model);
		//CLEAR THE TEXTBOX
		getJTextField_entertainmentMenu().setText("                      Enter Entertainment Name Here...");
		getJTextField_entertainmentMenu().setForeground(SystemColor.scrollbar);
		getJTextField_entertainmentPrice().setText("0.00");
		getJTextField_entertainmentPrice().setForeground(SystemColor.scrollbar);
		getJTextArea_entertainmentMenuDescription().setText("\n\n                                                  Enter a short Description of the entertainment");
		getJTextArea_entertainmentMenuDescription().setForeground(SystemColor.scrollbar);
		getJTextField_entertainmentPrice().requestFocus(false);
		getJTextField_entertainmentMenu().requestFocus(false);
		getJTextArea_entertainmentMenuDescription().requestFocus(false);

		DecimalFormat fmt = new DecimalFormat("0.00");
		getJTextField_entertainmentTotalPrice().setText(fmt.format(calculateFinalPrice()));
		displaySummary();
	}
	
	/********************************************************
	 * Method Name		: validateEntertainmentDetails()
	 * Input Parameter 	: void 
	 * Return 			: boolean
	 * Purpose 			: To validate the form before any CRUD
	 * *******************************************************/
	public boolean validateEntertainmentDetails(){
		boolean success=true;
		if(getJTextField_entertainmentTitle().getText().equals("")||getJTextField_entertainmentTitle().getText().equals("                                                           Enter a Title Here...")){
			success=false;
			JOptionPane.showMessageDialog(null, "Please enter an entertainment title", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextField_entertainmentTitle().requestFocus();
		}
		else if(getJTextArea_entertainmentDescription().getText().equals("")||getJTextArea_entertainmentDescription().getText().equals("\n\n                                                       Enter a Description Here...")){
			success=false;
			JOptionPane.showMessageDialog(null, "Please enter an entertainment description", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextArea_entertainmentDescription().requestFocus();
		}
		else if(model.getRowCount()==0){
			success=false;
			JOptionPane.showMessageDialog(null, "There must be an entertainment in the entertainment list", "Missing Fields", JOptionPane.WARNING_MESSAGE);
			getJTextField_entertainmentMenu().requestFocus();
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
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		control.setModel(model);
		return control.processCalculatePrice();
	}
	
	/********************************************************
	 * Method Name 		: calculateFinalPrice()
	 * Input Parameter	: void 
	 * Return 			: double
	 * Purpose 			: To caculate the price after discount
	 * *******************************************************/
	public double calculateFinalPrice(){
		AdministrateEntertainmentControl control= new AdministrateEntertainmentControl();
		control.setModel(model);
		return  control.processCalculateFinalPrice(getJSlider_entertainmentDiscount().getValue());
	}
	
	/********************************************************
	 * Method Name 		: displaySummary()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To display the summary of the form
	 *******************************************************/
	public void displaySummary(){
		String header="====================================\n";
		header+="            Entertainment Summary List\n";
		header+="====================================\n\n";
		
		String content="";
		for(int i=0;i<model.getRowCount();i++){
			content+=(i+1)+") "+model.getValueAt(i, 0).toString()+" - $"+model.getValueAt(i, 1).toString()+"\n";
		}
		content+="________________\n";
		content+="Total Price \t:$"+calculatePrice()+"\n";
		content+="Discount \t:"+getJSlider_entertainmentDiscount().getValue()+"%\n";
		content+="Final Price \t:$"+calculateFinalPrice();
		
		if(model.getRowCount()==0){
			//DOES NOTHING IF THERE IS NO ENTERTAINMENT MENU
		}
		else{
			getJTextArea_formula().setText(header+content);
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
			downloadTXT(PDFlink+".csv");
		}
		else if(directory.substring(directory.length()-2).equals(".r")){
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
		 Image image = Image.getInstance("src\\images\\SOM\\Reunite_Header.png");
		 image.scaleAbsolute(550, 100);
		 pdf.add(image);
		 pdf.add(new Paragraph("  "));
		 pdf.add(new Paragraph("  "));
		 //WRITTING THE CONTENT HERE
		 GregorianCalendar g= new GregorianCalendar();
		 pdf.add(new Paragraph("Entertainment Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID          : "+getJTextField_entertaimentID().getText().toString()));
		 pdf.add(new Paragraph("Title       : "+getJTextField_entertainmentTitle().getText().toString()));
		 pdf.add(new Paragraph("Description : "));
		 pdf.add(new Paragraph(getJTextArea_entertainmentDescription().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		//TABLE FOR THE ENTERTAINMENT MENU
		 float[] colsWidth = {2f, 1f,3f}; // Code 1
		 PdfPTable table = new PdfPTable(colsWidth);
		 table.setWidthPercentage(100); // Code 2
		 PdfPCell cell = new PdfPCell(new Paragraph("Entertainment List"));
		 cell.setColspan(3);
		 table.addCell(cell);
		 table.addCell("Entertainment Name");
		 table.addCell("Price / hr");
		 table.addCell("Description");
		for(int i=0;i<model.getRowCount();i++){
			table.addCell(model.getValueAt(i, 0).toString());
			table.addCell(model.getValueAt(i, 1).toString());
			table.addCell(model.getValueAt(i, 2).toString());
		}
		 pdf.add(table);
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Entitled Discount \t: "+getJTextField_entertainmentDiscount().getText().toString()));
		 pdf.add(new Paragraph("Total Price \t: $"+getJTextField_entertainmentTotalPrice().getText().toString()));
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
	 * @throws IOException 
	 *******************************************************/
	public void downloadTXT(String path) throws IOException{
		CSVController controller= new CSVController();
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[]entertainmentHeader=new String[6];
		entertainmentHeader[0]="ID";
		entertainmentHeader[1]="Title";
		entertainmentHeader[2]="Description";
		entertainmentHeader[3]="Availability";
		entertainmentHeader[4]="Discount";
		entertainmentHeader[5]="TotalPrice";
		
		String[]entertainmentData=new String[6];
		entertainmentData[0]=getJTextField_entertaimentID().getText().toString();
		entertainmentData[1]=getJTextField_entertainmentTitle().getText().toString();
		entertainmentData[2]=getJTextArea_entertainmentDescription().getText().toString();
		if(getJCheckBox_entertainmentAvailability().isSelected())
			entertainmentData[3]="Yes";
		else
			entertainmentData[3]="No";
		entertainmentData[4]=getJTextField_entertainmentDiscount().getText().toString();
		entertainmentData[5]=getJTextField_entertainmentTotalPrice().getText().toString();
		
		String[]entertainmentMenuHeader= new String[3];
		entertainmentMenuHeader[0]="Menu Name";
		entertainmentMenuHeader[1]="Price";
		entertainmentMenuHeader[2]="Description";
		
		data.add(entertainmentHeader);
		data.add(entertainmentData);
		data.add(entertainmentMenuHeader);
		
		for(int i=0;i<model.getRowCount();i++){
			String[] entertainmentMenuData= new String[3];
			entertainmentMenuData[0]=(model.getValueAt(i, 0).toString());
			entertainmentMenuData[1]=(model.getValueAt(i, 1).toString());
			entertainmentMenuData[2]=(model.getValueAt(i, 2).toString());
			data.add(entertainmentMenuData);
		}
		controller.WriteFile(data, path);
	
	}
	
	
	/********************************************************
	 * Method Name 		: createTabHeader()
	 * Input Parameter 	: int
	 * Return 			: void
	 * Purpose 			: To create and set a custom Tab Header.
	 *******************************************************/
	private void createTabHeader(int index){
		CustomTabHeader tab=new CustomTabHeader(AdministrateSystemOptionManagement.getJTabbedPane());
		AdministrateSystemOptionManagement.getJTabbedPane().setTabComponentAt( index,tab );
		
	}
	/********************************************************
	 * Method Name 		: newEntertainmentTab()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To create and set a new 
	 * 					  entertainment form
	 *******************************************************/
	private void newEntertainmentTab(){
		AdministrateEntertainmentForm en= new AdministrateEntertainmentForm();
		if(AdministrateSystemOptionManagement.getJTabbedPane().getTabCount()==1){
			AdministrateSystemOptionManagement.getJTabbedPane().insertTab("New Entertainment",null , en.getJScrollPane(),null , 1); // sets the content
			createTabHeader(1);	//sets the custom tab header
			AdministrateSystemOptionManagement.getJTabbedPane().remove(0);
			AdministrateSystemOptionManagement.getJTabbedPane().setSelectedIndex(0);
		}
		else{
			AdministrateSystemOptionManagement.getJTabbedPane().insertTab("New Entertainment",null , en.getJScrollPane(),null , AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()); // sets the content
			createTabHeader(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()-1);	//sets the custom tab header
			AdministrateSystemOptionManagement.getJTabbedPane().remove(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex());
			if(!(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()==AdministrateSystemOptionManagement.getJTabbedPane().getTabCount()-1)){
				AdministrateSystemOptionManagement.getJTabbedPane().setSelectedIndex(AdministrateSystemOptionManagement.getJTabbedPane().getSelectedIndex()-1);
				}
			}
	}
	
	/********************************************************
	 * Method Name 		: createEntertainment()
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To create a new entertainment 
	 * 					  record in the database
	 *******************************************************/	
	public void createEntertainment(){
		//PREPARING THE OBJECT VARIABLES
		boolean availability=getJCheckBox_entertainmentAvailability().isSelected();
		String title=getJTextField_entertainmentTitle().getText().toString();
		String description=getJTextArea_entertainmentDescription().getText().toString();
		double discount=getJSlider_entertainmentDiscount().getValue();
		double price=calculatePrice();
		double finalPrice=calculateFinalPrice();
		int hits=0;
		
		//CALLING THE CONTROLLER AND PASSING IN THE PREPARED VARIABLES
		AdministrateEntertainmentControl control=new AdministrateEntertainmentControl(title, description, availability, discount, price, finalPrice, hits, false);
		//PASSING IN THE TABLE MODEL TO THE CONTROLLER
		control.setModel(model);
		
		//CREATES THE ENTERTAINMENT RECORD
		String entertainmentID=null;
		entertainmentID=control.processCreateEntertainment();//change the name

			//CHECKS IF ALL ENTERTAINMENTMENU ARE CREATED SUCCESSFULLY
			if(!entertainmentID.equals(null)){
				JOptionPane.showMessageDialog(null, "Record has been uploaded successfully", "Success", JOptionPane.PLAIN_MESSAGE);
				getJTextField_entertaimentID().setText(entertainmentID);
				getJTextField_entertaimentID().setForeground(SystemColor.black);
				//DISABLES THE UPLOAD BUTTON
				getJButton_createEntertainment().setEnabled(false);
				//ENABLES THE UPDATE AND DELETE BUTTONS
				getJButton_Update().setEnabled(true);
				getJButton_delete().setEnabled(true);
				getJButton_download().setEnabled(true);
			}
			else{
				JOptionPane.showMessageDialog(null, "There was an unexpected uploading the entertainment record(s)\n1)Try restarting the application.", "Upload Failure", JOptionPane.ERROR_MESSAGE);
				//DELETE THE ENTERTAINMENT RECORD IS ANY WAS CREATEDD
				deleteEntertainment();
			}
	}
	
	/********************************************************
	 * Method Name 		: deleteEntertainment()
	 * Input Parameter	: VOID 
	 * Return 			: boolean
	 * Purpose 			: To Delete a entertainment record 
	 * 					  in the database
	 *******************************************************/
	public boolean deleteEntertainment(){
		String ID=getJTextField_entertaimentID().getText().toString();
		AdministrateEntertainmentControl entertainment=new AdministrateEntertainmentControl();
		boolean success=entertainment.processDeleteEntertainment(ID);
		return success;
	}
	
	/********************************************************
	 * Method Name 		: updateEntertainment
	 * Input Parameter 	: void 
	 * Return 			: void
	 * Purpose 			: To update a entertainment record 
	 * 					  in the database
	 *******************************************************/
	public void updateEntertainment(){		
		//PREPARING THE OBJECT VARIABLES
		boolean availability=getJCheckBox_entertainmentAvailability().isSelected();
		String title=getJTextField_entertainmentTitle().getText().toString();
		String description=getJTextArea_entertainmentDescription().getText().toString();
		double discount=getJSlider_entertainmentDiscount().getValue();
		double price=calculatePrice();
		double finalPrice=calculateFinalPrice();
		int hits=0;
		
		//CALLING THE CONTROLLER AND PASSING IN THE PREPARED VARIABLES
		AdministrateEntertainmentControl control=new AdministrateEntertainmentControl(title, description, availability, discount, price, finalPrice, hits,false);
		//PASSING IN THE TABLE MODEL TO THE CONTROLLER
		control.setModel(model);
		
		//UPDATES THE ENTERTAINMENT
		String entertainmentID=getJTextField_entertaimentID().getText().toString();
		boolean success=true;
		success=control.processUpdateEntertainment(entertainmentID);
		
		//CHECKS IF THE UPDATE IS SUCCESSFULL
		if(success){
			JOptionPane.showMessageDialog(null, "Record has been Updated successfully", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "There was an unexpected updating the entertainment record(s)\n1)Try restarting the application.", "Update Failure", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
}
/********************************************************
 *					End of Class
 *******************************************************/