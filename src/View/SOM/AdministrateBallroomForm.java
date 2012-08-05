/********************************************************************************************************************************************************
Program Name			:	AdministrateBallroomForm.java
Description				:	A AdministrateBallroomclass that is used for Creation, Update & Deletion of Ballroom record
Done by					:	Lee Kai Quan
Admin No				:	114173S
Module Group			:	IT2297-08
Last Update				:	6-12-2012
=========================================================================================================================================================
USERS USING THIS CLASS 	: 	Lee Kai Quan (114173S)
METHODS LIST 			: 	retrieveFacilityByName() : void
						:	validateBallroomDetails() : Boolean
						:	caculateFinalPrice() : Double
						:	displaySummary() : void
						:	download() : void
						:	downloadPDF(String) : void
						:	downloadCSV(String) : void
						:	createTabHeader() : void
						:	newBallroomTab() : void
						:	createBallroom() : void
						:	deleteBallroom() : Boolean
						:	updateBallroom() : Boolean
********************************************************************************************************************************************************/
package View.SOM;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.SOM.AdministrateBallroomControl;
import Controller.SOM.AdministrateFacilityControl;
import Controller.SOM.CSVController;
import Controller.MyCalendar;

public class AdministrateBallroomForm {
	
	/********************************************************
	 *					The Attributes
	 *******************************************************/
	private JScrollPane jScrollPane = null;  //  @jve:decl-index=0:visual-constraint="280,105"
	private JPanel jPanel = null;
	private JPanel jPanel_header = null;
	private JLabel jLabel_ballroomID = null;
	private JLabel jLabel_ballroomAvailability = null;
	private JLabel jLabel_Facaility = null;
	private JLabel jLabel_facilityContact = null;
	private JLabel jLabel_facilityAddress = null;
	private JPanel jPanel_body = null;
	private JLabel jLabel_ballroomTitle = null;
	private JLabel jLabel_ballroomDescription = null;
	private JLabel jLabel_ballroomSize = null;
	private JLabel jLabel_ballroomPrice = null;
	private JPanel jPanel_summary = null;
	private JLabel jLabel_ballroomFinalPrice = null;
	private JLabel jLabel_ballroomDiscount = null;
	private JTextField jTextField_ballroomFinalPrice = null;
	private JTextField jTextField_ballroomDiscount = null;
	private JScrollPane jScrollPane_summary = null;
	private JButton jButton_download = null;
	private JButton jButton_upload = null;
	private JButton jButton_delete = null;
	private JButton jButton_update = null;
	private JTextArea jTextArea_summary = null;
	private JSlider jSlider_ballroomDiscount = null;
	private JTextField jTextField_ballroomID = null;
	private JCheckBox jCheckBox_ballroomAvailability = null;
	@SuppressWarnings("unchecked")
	private JComboBox jComboBox_facilityName = null;
	private JTextField jTextField_facilityContact = null;
	private JScrollPane jScrollPane_facilityAddress = null;
	private JTextArea jTextArea_facilityAddress = null;
	private JLabel jLabel = null;
	private JTextField jTextField_ballroomTitle = null;
	protected JComboBox<String> jComboBox_ballroomSize = null;
	private JTextField jTextField_ballroomPrice = null;
	private JScrollPane jScrollPane_ballroomDescription = null;
	private JTextArea jTextArea_ballroomDescription = null;
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
		}
		return jScrollPane;
	}
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(1000, 981));
			jPanel.setBackground(SystemColor.control);
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
			jLabel_facilityAddress = new JLabel();
			jLabel_facilityAddress.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_facilityAddress.setBounds(new Rectangle(50, 160, 81, 30));
			jLabel_facilityAddress.setText("Address :");
			jLabel_facilityContact = new JLabel();
			jLabel_facilityContact.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_facilityContact.setBounds(new Rectangle(50, 120, 81, 30));
			jLabel_facilityContact.setText("Contact :");
			jLabel_Facaility = new JLabel();
			jLabel_Facaility.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_Facaility.setBounds(new Rectangle(50, 80, 81, 30));
			jLabel_Facaility.setText("Facility");
			jLabel_ballroomAvailability = new JLabel();
			jLabel_ballroomAvailability.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomAvailability.setBounds(new Rectangle(450, 40, 150, 30));
			jLabel_ballroomAvailability.setText("Availability :");
			jLabel_ballroomID = new JLabel();
			jLabel_ballroomID.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomID.setBounds(new Rectangle(50, 40, 80, 30));
			jLabel_ballroomID.setText("ID");
			jPanel_header = new JPanel();
			jPanel_header.setLayout(null);
			jPanel_header.setBounds(new Rectangle(100, 30, 800, 300));;
			jPanel_header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_header.add(jLabel_ballroomID, null);
			jPanel_header.add(jLabel_ballroomAvailability, null);
			jPanel_header.add(jLabel_Facaility, null);
			jPanel_header.add(jLabel_facilityContact, null);
			jPanel_header.add(jLabel_facilityAddress, null);
			jPanel_header.add(getJTextField_ballroomID(), null);
			jPanel_header.add(getJCheckBox_ballroomAvailability(), null);
			jPanel_header.add(getJComboBox_facilityName(), null);
			jPanel_header.add(getJTextField_facilityContact(), null);
			jPanel_header.add(getJScrollPane_facilityAddress(), null);
		}
		return jPanel_header;
	}
	protected JTextField getJTextField_ballroomID() {
		if (jTextField_ballroomID == null) {
			jTextField_ballroomID = new JTextField();
			jTextField_ballroomID.setHorizontalAlignment(JTextField.CENTER);
			jTextField_ballroomID.setFont(new Font("Segoe UI", Font.ITALIC, 14));
			jTextField_ballroomID.setEnabled(false);
			jTextField_ballroomID.setForeground(SystemColor.scrollbar);
			jTextField_ballroomID.setText("Generate After Creation");
			jTextField_ballroomID.setBounds(new Rectangle(130, 41, 193, 29));
		}
		return jTextField_ballroomID;
	}
	protected JCheckBox getJCheckBox_ballroomAvailability() {
		if (jCheckBox_ballroomAvailability == null) {
			jCheckBox_ballroomAvailability = new JCheckBox();
			jCheckBox_ballroomAvailability.setFocusable(false);
			jCheckBox_ballroomAvailability.setFocusPainted(false);
			jCheckBox_ballroomAvailability.setSelected(true);
			jCheckBox_ballroomAvailability.setBounds(new Rectangle(530, 40, 200, 30));
			jCheckBox_ballroomAvailability.setText("Available");
			jCheckBox_ballroomAvailability
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							if(jCheckBox_ballroomAvailability.isSelected()){
								jCheckBox_ballroomAvailability.setText("Available");
							}
							else{
								jCheckBox_ballroomAvailability.setText("Not Available");
							}
						}
					});
		}
		return jCheckBox_ballroomAvailability;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getJComboBox_facilityName() {
		if (jComboBox_facilityName == null) {
			jComboBox_facilityName = new JComboBox();
			jComboBox_facilityName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jComboBox_facilityName.setFocusable(false);
			jComboBox_facilityName.setBounds(new Rectangle(130, 80, 600, 30));
			jComboBox_facilityName.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					Thread main = new Thread () {
						  public void run () {
							  if(jComboBox_facilityName.getSelectedIndex()!=0){
									retrieveFacilityByName();
								}
								else{
									getJTextField_facilityContact().setText("");
									getJTextArea_facilityAddress().setText("");
								};
						  }
					  };
					  final Thread a=main;
					Thread progress= new Thread(){
						  public void run(){
							  double increment=1;
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      int sleep=300;
								      try {
								        SwingUtilities.invokeLater(new Runnable() {
								         public void run() {
								        	 AdministrateServiceOptionManagement.getJProgressBar().setValue(percent);
								          }
								        });
								        Thread.sleep(sleep);
								        if(!a.isAlive()){
								        	AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
								        	System.out.println( AdministrateServiceOptionManagement.getJProgressBar().getValue());
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
		return jComboBox_facilityName;
	}
	protected JTextField getJTextField_facilityContact() {
		if (jTextField_facilityContact == null) {
			jTextField_facilityContact = new JTextField();
			jTextField_facilityContact.setForeground(SystemColor.scrollbar);;
			jTextField_facilityContact.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_facilityContact.setEnabled(false);
			jTextField_facilityContact.setBounds(new Rectangle(130, 120, 600, 31));
		}
		return jTextField_facilityContact;
	}
	protected JScrollPane getJScrollPane_facilityAddress() {
		if (jScrollPane_facilityAddress == null) {
			jScrollPane_facilityAddress = new JScrollPane();
			jScrollPane_facilityAddress.setBounds(new Rectangle(130, 160, 600, 100));
			jScrollPane_facilityAddress.setViewportView(getJTextArea_facilityAddress());
		}
		return jScrollPane_facilityAddress;
	}
	protected JTextArea getJTextArea_facilityAddress() {
		if (jTextArea_facilityAddress == null) {
			jTextArea_facilityAddress = new JTextArea();
			jTextArea_facilityAddress.setText("\n\n");
			jTextArea_facilityAddress.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_facilityAddress.setLineWrap(true);
			jTextArea_facilityAddress.setTabSize(10);
			jTextArea_facilityAddress.setWrapStyleWord(true);
			jTextArea_facilityAddress.setEnabled(false);
		}
		return jTextArea_facilityAddress;
	}
	
	/********************************************************
	 *					Start of body
	 *******************************************************/
	private JPanel getJPanel_body() {
		if (jPanel_body == null) {
			jLabel = new JLabel();
			jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel.setBounds(new Rectangle(49, 20, 164, 30));
			jLabel.setText("Ballroom Details :");
			jLabel_ballroomPrice = new JLabel();
			jLabel_ballroomPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomPrice.setBounds(new Rectangle(449, 100, 78, 30));
			jLabel_ballroomPrice.setText("Price :");
			jLabel_ballroomSize = new JLabel();
			jLabel_ballroomSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomSize.setBounds(new Rectangle(49, 100, 81, 30));
			jLabel_ballroomSize.setText("Size :");
			jLabel_ballroomDescription = new JLabel();
			jLabel_ballroomDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomDescription.setBounds(new Rectangle(49, 140, 81, 30));
			jLabel_ballroomDescription.setText("Description :");
			jLabel_ballroomTitle = new JLabel();
			jLabel_ballroomTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomTitle.setBounds(new Rectangle(49, 60, 81, 30));
			jLabel_ballroomTitle.setText("Name :");
			jPanel_body = new JPanel();
			jPanel_body.setLayout(null);
			jPanel_body.setBounds(new Rectangle(100, 360, 800, 287));
			jPanel_body.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_body.add(jLabel_ballroomTitle, null);
			jPanel_body.add(jLabel_ballroomDescription, null);
			jPanel_body.add(jLabel_ballroomSize, null);
			jPanel_body.add(jLabel_ballroomPrice, null);
			jPanel_body.add(jLabel, null);
			jPanel_body.add(getJTextField_ballroomTitle(), null);
			jPanel_body.add(getJComboBox_ballroomSize(), null);
			jPanel_body.add(getJTextField_ballroomPrice(), null);
			jPanel_body.add(getJScrollPane_ballroomDescription(), null);
		}
		return jPanel_body;
	}
	protected JTextField getJTextField_ballroomTitle() {
		if (jTextField_ballroomTitle == null) {
			jTextField_ballroomTitle = new JTextField();
			jTextField_ballroomTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomTitle.setForeground(SystemColor.scrollbar);
			jTextField_ballroomTitle.setText("                                                                            Enter a Ballroom Name");
			jTextField_ballroomTitle.setBounds(new Rectangle(129, 60, 600, 30));
			jTextField_ballroomTitle.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_ballroomTitle.getText().equals("")){
						jTextField_ballroomTitle.setForeground(SystemColor.scrollbar);
						jTextField_ballroomTitle.setText("                                                                            Enter a Ballroom Name");
					}
					else{
						displaySummary();
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_ballroomTitle.getText().equals("                                                                            Enter a Ballroom Name")){
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
			jComboBox_ballroomSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jComboBox_ballroomSize.setFocusable(false);
			jComboBox_ballroomSize.addItem("Select a Size");
			jComboBox_ballroomSize.addItem("Small (10 - 25px)");
			jComboBox_ballroomSize.addItem("Medium (26 - 50px)");
			jComboBox_ballroomSize.addItem("Large (50px Above)");
			jComboBox_ballroomSize.setBounds(new Rectangle(129, 100, 250, 31));
			jComboBox_ballroomSize.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(jComboBox_ballroomSize.getSelectedIndex()!=0){
						displaySummary();
					}
				}
			});
		}
		return jComboBox_ballroomSize;
	}
	protected JTextField getJTextField_ballroomPrice() {
		if (jTextField_ballroomPrice == null) {
			jTextField_ballroomPrice = new JTextField();
			jTextField_ballroomPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomPrice.setForeground(SystemColor.scrollbar);
			jTextField_ballroomPrice.setText("0.00");
			jTextField_ballroomPrice.setBounds(new Rectangle(527, 100, 202, 31));
			jTextField_ballroomPrice.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextField_ballroomPrice.getText().equals("")){
						jTextField_ballroomPrice.setForeground(SystemColor.scrollbar);
						jTextField_ballroomPrice.setText("0.00");
					}
					else{
						DecimalFormat fmt = new DecimalFormat("0.00");
						getJTextField_ballroomFinalPrice().setText(fmt.format(caculateFinalPrice()));
						displaySummary();
					}
				}
				public void focusGained(java.awt.event.FocusEvent e) {
					if(jTextField_ballroomPrice.getText().equals("0.00")){
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
			jScrollPane_ballroomDescription.setBounds(new Rectangle(130, 140, 600, 100));
			jScrollPane_ballroomDescription.setViewportView(getJTextArea_ballroomDescription());
		}
		return jScrollPane_ballroomDescription;
	}
	protected JTextArea getJTextArea_ballroomDescription() {
		if (jTextArea_ballroomDescription == null) {
			jTextArea_ballroomDescription = new JTextArea();
			jTextArea_ballroomDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_ballroomDescription.setLineWrap(true);
			jTextArea_ballroomDescription.setWrapStyleWord(true);
			jTextArea_ballroomDescription.setForeground(SystemColor.scrollbar);
			jTextArea_ballroomDescription.setText("\n\n                                                                           Enter a Ballroom Description");
			jTextArea_ballroomDescription.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if(jTextArea_ballroomDescription.getText().equals("")){
						jTextArea_ballroomDescription.setForeground(SystemColor.scrollbar);
						jTextArea_ballroomDescription.setText("\n\n                                                                           Enter a Ballroom Description");
					}
					else{
						displaySummary();
					}
				}
						public void focusGained(java.awt.event.FocusEvent e) {
							if(jTextArea_ballroomDescription.getText().equals("\n\n                                                                           Enter a Ballroom Description")){
								jTextArea_ballroomDescription.setForeground(SystemColor.black);
								jTextArea_ballroomDescription.setText("");
							}
						}
					});
		}
		return jTextArea_ballroomDescription;
	}
	/********************************************************
	 *					Start of summary
	 *******************************************************/
	private JPanel getJPanel_summary() {
		if (jPanel_summary == null) {
			jLabel_ballroomDiscount = new JLabel();
			jLabel_ballroomDiscount.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomDiscount.setBounds(new Rectangle(400, 40, 150, 30));
			jLabel_ballroomDiscount.setText("Entitled Discount :");
			jLabel_ballroomFinalPrice = new JLabel();
			jLabel_ballroomFinalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jLabel_ballroomFinalPrice.setBounds(new Rectangle(50, 40, 79, 30));
			jLabel_ballroomFinalPrice.setText("Final Price :");
			jPanel_summary = new JPanel();
			jPanel_summary.setLayout(null);
			jPanel_summary.setBounds(new Rectangle(100, 680, 800, 250));
			jPanel_summary.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jPanel_summary.add(jLabel_ballroomFinalPrice, null);
			jPanel_summary.add(jLabel_ballroomDiscount, null);
			jPanel_summary.add(getJTextField_ballroomFinalPrice(), null);
			jPanel_summary.add(getJTextField_ballroomDiscount(), null);
			jPanel_summary.add(getJScrollPane_summary(), null);
			jPanel_summary.add(getJButton_download(), null);
			jPanel_summary.add(getJButton_upload(), null);
			jPanel_summary.add(getJButton_delete(), null);
			jPanel_summary.add(getJButton_update(), null);
			jPanel_summary.add(getJSlider_ballroomDiscount(), null);
		}
		return jPanel_summary;
	}
	protected JTextField getJTextField_ballroomFinalPrice() {
		if (jTextField_ballroomFinalPrice == null) {
			jTextField_ballroomFinalPrice = new JTextField();
			jTextField_ballroomFinalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextField_ballroomFinalPrice.setEditable(false);
			jTextField_ballroomFinalPrice.setText("0.00");
			jTextField_ballroomFinalPrice.setBounds(new Rectangle(130, 40, 230, 30));
		}
		return jTextField_ballroomFinalPrice;
	}
	protected JTextField getJTextField_ballroomDiscount() {
		if (jTextField_ballroomDiscount == null) {
			jTextField_ballroomDiscount = new JTextField();
			jTextField_ballroomDiscount.setEditable(false);
			jTextField_ballroomDiscount.setText("0%");
			jTextField_ballroomDiscount.setBounds(new Rectangle(550, 40, 177, 30));
		}
		return jTextField_ballroomDiscount;
	}
	protected JSlider getJSlider_ballroomDiscount() {
		if (jSlider_ballroomDiscount == null) {
			jSlider_ballroomDiscount = new JSlider();
			jSlider_ballroomDiscount.setMaximum(0);
			jSlider_ballroomDiscount.setMaximum(100);
			jSlider_ballroomDiscount.setValue(0);
			jSlider_ballroomDiscount.setFocusable(false);
			jSlider_ballroomDiscount.setBounds(new Rectangle(400, 80, 337, 30));
			jSlider_ballroomDiscount
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							getJTextField_ballroomDiscount().setText(jSlider_ballroomDiscount.getValue()+"%");
							DecimalFormat fmt = new DecimalFormat("0.00");
							getJTextField_ballroomFinalPrice().setText(fmt.format(caculateFinalPrice()));
							displaySummary();
						}
					});
		}
		return jSlider_ballroomDiscount;
	}
	private JScrollPane getJScrollPane_summary() {
		if (jScrollPane_summary == null) {
			jScrollPane_summary = new JScrollPane();
			jScrollPane_summary.setBounds(new Rectangle(50, 79, 312, 138));
			jScrollPane_summary.setViewportView(getJTextArea_summary());
		}
		return jScrollPane_summary;
	}
	protected JTextArea getJTextArea_summary() {
		if (jTextArea_summary == null) {
			jTextArea_summary = new JTextArea();
			jTextArea_summary.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jTextArea_summary.setWrapStyleWord(true);
			jTextArea_summary.setLineWrap(true);
			jTextArea_summary.setFocusable(false);
			jTextArea_summary.setEditable(false);
		}
		return jTextArea_summary;
	}
	protected JButton getJButton_download() {
		if (jButton_download == null) {
			jButton_download = new JButton();
			jButton_download.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			jButton_download.setEnabled(false);
			jButton_download.setFocusable(false);
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
							  if(validateBallroomDetails()){
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
								 for (int i =  0; i <= 100; i+=increment) {
								      final int percent = i;
								      int sleep=300;
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
							  if(validateBallroomDetails()){
									createBallroom();
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
									deleteBallroom();
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
			jButton_update.setEnabled(false);
			jButton_update.setBounds(new Rectangle(570, 170, 160, 45));
			jButton_update.setIcon(new ImageIcon(getClass().getResource("/Images/SOM/update.png")));
			jButton_update.setText("Update");
			jButton_update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					main = new Thread () {
						  public void run () {
							  if(validateBallroomDetails()){
									updateBallroom();
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
	 *				Start of Custom Methods
	 *******************************************************/
	/********************************************************
	  * Method Name 	: retrieveFacilityByName
	  * Input Parameter : String
	  * Purpose 		: To retrieve all facility record by name
	  * Return 			: void 
	  *******************************************************/
	public void retrieveFacilityByName(){
		AdministrateFacilityControl control= new AdministrateFacilityControl();
		control.processRetrieveFacilityByName(getJComboBox_facilityName().getSelectedItem().toString());
		getJTextField_facilityContact().setText(control.getFacility().getFacilityContact());
		getJTextArea_facilityAddress().setText(control.getFacility().getFacilityAddress());
		getJComboBox_facilityName().setName(control.getFacility().getFacilityID());
	}
	
	/********************************************************
	 * Method Name 		: validate
	 * Input Parameter 	: void 
	 * Purpose 			: To validate the details before any CRUD
	 * Return 			: boolean
	 * *******************************************************/
	@SuppressWarnings("deprecation")
	public boolean validateBallroomDetails(){
		boolean success=true;
		Scanner scan = new Scanner(getJTextField_ballroomPrice().getText().toString());
		boolean IsDigit=false;
		if (!scan.hasNextDouble()) {  
			IsDigit=true;   
		} 
		
		if(getJComboBox_facilityName().getSelectedIndex()==0){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please select the facility where this ballroom belongs to", "Warning", JOptionPane.WARNING_MESSAGE);
			getJComboBox_facilityName().requestFocus();
			main.interrupt();
		}
		else if(getJTextField_ballroomTitle().getText().equals("")||getJTextField_ballroomTitle().getText().equals("                                                                            Enter a Ballroom Name")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please Enter a Ballroom Name", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_ballroomTitle().requestFocus();
			main.interrupt();
		}
		else if(getJComboBox_ballroomSize().getSelectedIndex()==0){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please Select a Ballroom Size", "Warning", JOptionPane.WARNING_MESSAGE);
			getJComboBox_ballroomSize().requestFocus();
			main.interrupt();
		}
		else if(getJTextField_ballroomPrice().getText().equals("")||getJTextField_ballroomPrice().getText().equals("0.00")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please Enter a Ballroom Price", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextField_ballroomPrice().requestFocus();
			//main.stop();
			main.interrupt();
		}
		if(IsDigit){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please enter a correct price value", "Warnning", JOptionPane.WARNING_MESSAGE);
			getJTextField_ballroomPrice().requestFocus();
			main.interrupt();
		}
		else if(getJTextArea_ballroomDescription().getText().equals("")||getJTextArea_ballroomDescription().getText().equals("\n\n                                                                           Enter a Ballroom Description")){
			success=false;
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(0);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Please Enter a Ballroom Description", "Warning", JOptionPane.WARNING_MESSAGE);
			getJTextArea_ballroomDescription().requestFocus();
			main.interrupt();
		}
		return success;
	}
	
	/********************************************************
	 * Method Name 		: caculateFinalPrice
	 * Input Parameter 	: void 
	 * Purpose 			: To caculate the Final price after discount
	 * Return 			: double
	 * *******************************************************/
	public double caculateFinalPrice(){
		AdministrateBallroomControl control= new AdministrateBallroomControl();
		return  control.processCalculateFinalPrice(getJSlider_ballroomDiscount().getValue(),Double.parseDouble(getJTextField_ballroomPrice().getText()));
	}
	
	/********************************************************
	 * Method Name 		: displaySummary
	 * Input Parameter 	: void 
	 * Purpose 			: To display the summary of the current Ballroom record
	 * Return 			: void
	 *******************************************************/
	public void displaySummary(){
		String header="====================================\n";
		header+="                         Ballroom Summary List\n";
		header+="====================================\n\n";
		
		String content="";
		if(getJTextField_ballroomTitle().getText().equals("")||getJTextField_ballroomTitle().getText().equals("                                                      Enter a Ballroom Name")){
			content+="Name :";
		}
		else{
			content+="Name :   "+getJTextField_ballroomTitle().getText()+"\n";
		}
		if(getJComboBox_ballroomSize().getSelectedIndex()!=0){
			content+="Size :   "+getJComboBox_ballroomSize().getSelectedItem().toString()+"\n";
		}
		
		
		content+="________________\n";
		content+="Total Price :$"+getJTextField_ballroomPrice().getText().toString()+"\n";
		content+="Discount :"+getJSlider_ballroomDiscount().getValue()+"%\n";
		content+="Final Price :$"+caculateFinalPrice();
		
		
		getJTextArea_summary().setText(header+content);
	}
	
	/********************************************************
	 * Method Name 		: download()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer in PDF & CSV
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
			downloadCSV(PDFlink+".CSV");
		}
		else if(directory.substring(directory.length()-2).equals(".csv")){
			Scanner sc2= new Scanner(directory);
			String d=".r";
			sc2.useDelimiter(d);
			while(sc2.hasNext()){
				TXTlink=sc2.next();
			}
			downloadPDF(TXTlink+".pdf");
			downloadCSV(TXTlink+".CSV");
		}
		else{
			downloadPDF(directory+".pdf");
			downloadCSV(directory+".CSV");
		}
	}
	/********************************************************
	 * Method Name 		: downloadPDF()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer in CSV
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
		 pdf.add(new Paragraph("Ballroom Record"));
		 pdf.add(new Paragraph("Recorded on :"+MyCalendar.formatDate(g)));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("ID : "+getJTextField_ballroomID().getText().toString()));
		 pdf.add(new Paragraph("Facility Name : "+getJComboBox_facilityName().getSelectedItem().toString()));
		 pdf.add(new Paragraph("Contact : "+getJTextField_facilityContact().getText().toString()));
		 pdf.add(new Paragraph("Address : "+getJTextArea_facilityAddress().getText().toString()));
		 pdf.add(new Paragraph("Title : "+getJTextField_ballroomTitle().getText().toString()));
		 pdf.add(new Paragraph("Size : "+getJComboBox_ballroomSize().getSelectedItem().toString()));
		 pdf.add(new Paragraph("Price : $"+getJTextField_ballroomPrice().getText().toString()));
		 pdf.add(new Paragraph("Description : "));
		 pdf.add(new Paragraph(getJTextArea_ballroomDescription().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("Entitled Discount : "+getJTextField_ballroomDiscount().getText().toString()+"%"));
		 pdf.add(new Paragraph("Final Price        : $"+getJTextField_ballroomFinalPrice().getText().toString()));
		 pdf.add(new Paragraph("   "));
		 pdf.add(new Paragraph("****************************************************************************************************************"));
		 pdf.add(new Paragraph("                                                                         End                                               "));
		 pdf.add(new Paragraph());
		 pdf.add(new Paragraph());
		 
		 pdf.close();
		
		
	}
	
	/********************************************************
	 * Method Name 		: downloadCSV()
	 * Input Parameter 	: String 
	 * Return 			: void
	 * Purpose 			: To download the form details in
	 * 					  the local computer
	 * @throws IOException 
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void downloadCSV(String path) throws IOException{
		CSVController controller= new CSVController();
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[]ballroomHeader=new String[11];
		ballroomHeader[0]="BALLROOM_ID";
		ballroomHeader[1]="FACILITY_NAME";
		ballroomHeader[2]="FACILITY_CONTACT";
		ballroomHeader[3]="FACILITY_ADDRESS";
		ballroomHeader[4]="BALLROOM_AVAILABILITY";
		ballroomHeader[5]="BALLROOM_NAME";
		ballroomHeader[6]="BALLROOM_SIZE";
		ballroomHeader[7]="BALLROOM_PRICE";
		ballroomHeader[8]="BALLROOM_DESCRIPTION";
		ballroomHeader[9]="BALLROOM_DISCOUNT";
		ballroomHeader[10]="BALLROOM_FINAL_PRICE";
		
		String[]ballroomData= new String[12];
		ballroomData[0]=getJTextField_ballroomID().getText().toString();
		ballroomData[1]=getJComboBox_facilityName().getSelectedItem().toString();
		ballroomData[2]=getJTextField_facilityContact().getText().toString();
		ballroomData[3]=getJTextArea_facilityAddress().getText().toString();
		if(getJCheckBox_ballroomAvailability().isSelected())
			ballroomData[4]="YES";
		else
			ballroomData[4]="NO";
		ballroomData[5]=getJTextField_ballroomTitle().getText().toString();
		ballroomData[6]=getJComboBox_ballroomSize().getSelectedItem().toString();
		ballroomData[7]=getJTextField_ballroomPrice().getText().toString();
		ballroomData[8]=getJTextArea_ballroomDescription().getText().toString();
		ballroomData[9]=getJSlider_ballroomDiscount().getValue()+"";
		ballroomData[10]=getJTextField_ballroomFinalPrice().getText().toString();
		
		data.add(ballroomHeader);
		data.add(ballroomData);
		
		controller.WriteFile(data, path);
		//prompt success
	
		 progress.interrupt();
		 progress.stop();
		 AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
		 AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
		 JOptionPane.showMessageDialog(null, "File Downloaded Successfully at "+path, "Downloads", JOptionPane.INFORMATION_MESSAGE);
	
	}
	/********************************************************
	 * Method Name		: createTabHeader
	 * Input Parameter 	: int
	 * Purpose 			: To create and set the custom Tab Header.
	 * Return 			: void
	 *******************************************************/
	private void createTabHeader(int index){
		CustomTabHeader tab=new CustomTabHeader(AdministrateServiceOptionManagement.getJTabbedPane());
		AdministrateServiceOptionManagement.getJTabbedPane().setTabComponentAt( index,tab );
		
	}
	
	/********************************************************
	 * Method Name 		: newBallroomTab
	 * Input Parameter 	: void 
	 * Purpose 			: To create and set a new Ballroom Tab content.
	 * Return 			: void
	 *******************************************************/
	@SuppressWarnings("unchecked")
	public void newBallroomTab(){
		AdministrateBallroomForm form=new AdministrateBallroomForm();
		AdministrateFacilityControl control= new AdministrateFacilityControl();
		form.getJComboBox_facilityName().setModel(control.processRetrieveFacilityNames());
		if(AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()==1){
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Ballroom Form",null , form.getJScrollPane(),null , 1); // sets the content
			createTabHeader(1);	//sets the custom tab header
			AdministrateServiceOptionManagement.getJTabbedPane().remove(0);
			AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(0);
		}
		else{
			System.out.println(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			AdministrateServiceOptionManagement.getJTabbedPane().insertTab("Create Ballroom Form",null , form.getJScrollPane(),null , AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()); // sets the content
			createTabHeader(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);	//sets the custom tab header
			AdministrateServiceOptionManagement.getJTabbedPane().remove(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex());
			if(!(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()==AdministrateServiceOptionManagement.getJTabbedPane().getTabCount()-1)){
				AdministrateServiceOptionManagement.getJTabbedPane().setSelectedIndex(AdministrateServiceOptionManagement.getJTabbedPane().getSelectedIndex()-1);
				}
			}
	}
	
	/********************************************************
	 * Method Name 		: createBallroom
	 * Input Parameter 	: void 
	 * Purpose 			: To create a new Ballroom record in the database
	 * Return 			: void
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void createBallroom(){
		//prepares the ballroom object to be pass into the controller
		
		String facilityID=getJComboBox_facilityName().getName().toString();
		String ballroomName=getJTextField_ballroomTitle().getText();
		String ballroomDescription=getJTextArea_ballroomDescription().getText().toString();
		String ballroomSize=getJComboBox_ballroomSize().getSelectedItem().toString();
		double ballroomPrice=Double.parseDouble(getJTextField_ballroomPrice().getText().toString());
		double ballroomDiscount=getJSlider_ballroomDiscount().getValue();
		double ballroomFinalPrice=caculateFinalPrice();
		boolean ballroomAvailability=getJCheckBox_ballroomAvailability().isSelected();
		
		AdministrateBallroomControl control= new AdministrateBallroomControl(facilityID, ballroomName, ballroomDescription, ballroomSize, ballroomDiscount, ballroomPrice, ballroomFinalPrice, 0,ballroomAvailability);
		String ballroomID=control.processCreateBallroom();
		
		if(ballroomID.equals("")||ballroomID.equals(null)){
			//DELETE THE ENTERTAINMENT RECORD IS ANY WAS CREATEDD
			deleteBallroom();
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "There was an unexpected uploading the ballroom record(s)/nTry restarting the application.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		else{
			
			getJTextField_ballroomID().setText(ballroomID);
			getJTextField_ballroomID().setForeground(SystemColor.black);
			getJButton_delete().setEnabled(true);
			getJButton_update().setEnabled(true);
			getJButton_upload().setEnabled(false);
			getJButton_download().setEnabled(true);
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Record has been uploaded successfully", "Success", JOptionPane.PLAIN_MESSAGE);
		}
		displaySummary();
	}
	
	/********************************************************
	 * Method Name 		: deleteBallroom
	 * Input Parameter	: void 
	 * Purpose 			: To Delete the Ballroom record in the database
	 * Return 			: void
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void deleteBallroom(){
		AdministrateBallroomControl control= new AdministrateBallroomControl();
		boolean result= control.processDeleteBallroom(getJTextField_ballroomID().getText().toString());
		
		if(result){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Record has been deleted successfully", "Success", JOptionPane.PLAIN_MESSAGE);
			newBallroomTab();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "There was an unexpected error deleting the record/nTry restarting the application.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/********************************************************
	 * Method Name 		: updateBallroom
	 * Input Parameter 	: void
	 * Purpose 			: To update the Ballroom record in the database
	 * Return 			: void
	 *******************************************************/
	@SuppressWarnings("deprecation")
	public void updateBallroom(){
		
		//prepares the ballroom object to be pass into the controller
		String facilityID=getJComboBox_facilityName().getName().toString();
		String ballroomName=getJTextField_ballroomTitle().getText();
		String ballroomDescription=getJTextArea_ballroomDescription().getText().toString();
		String ballroomSize=getJComboBox_ballroomSize().getSelectedItem().toString();
		double ballroomPrice=Double.parseDouble(getJTextField_ballroomPrice().getText().toString());
		double ballroomDiscount=getJSlider_ballroomDiscount().getValue();
		double ballroomFinalPrice=caculateFinalPrice();
		boolean ballroomAvailability=getJCheckBox_ballroomAvailability().isSelected();
		
		AdministrateBallroomControl control= new AdministrateBallroomControl(facilityID, ballroomName, ballroomDescription, ballroomSize, ballroomDiscount, ballroomPrice, ballroomFinalPrice, 0,ballroomAvailability);
		boolean success=false;
		success=control.processUpdateBallroom(getJTextField_ballroomID().getText().toString());
		
		if(success){
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Record has been updated successfully", "Success", JOptionPane.PLAIN_MESSAGE);
			displaySummary();
		}
		else{
			progress.interrupt();
			progress.stop();
			AdministrateServiceOptionManagement.getJProgressBar().setValue(100);
			AdministrateServiceOptionManagement.getJProgressBar().setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "There was an unexpected error deleting the record/nTry restarting the application.", "Warning", JOptionPane.ERROR_MESSAGE);
			displaySummary();
		}
	}

}
