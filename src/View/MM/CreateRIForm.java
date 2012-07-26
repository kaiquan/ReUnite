package View.MM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.MM.*;


public class CreateRIForm extends JFrame {
	
	private JFrame jFrame ;  
	private JPanel panel;
	private JButton submitRegistrationButton;
	private JButton cancelButton;
	
	private  JComboBox monthCombo;
	private JComboBox dayCombo;
	private JComboBox yearCombo;
	private JComboBox secretQuestionCombo;
	
	
	private JLabel headerText;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dateOfBirthLabel;
	private JLabel nricLabel;
	private JLabel schoolLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	private JLabel telephoneNoLabel;
	private JLabel handphoneNoLabel;
	private JLabel secretQuestionLabel;
	private JLabel secretAnswerLabel;
	
	
	
	private JTextField userNameTextBox;
	private JPasswordField passwordTextBox;
	private JPasswordField cPasswordTextBox;
	private JTextField firstNameTextBox;
	private JTextField lastNameTextBox;
	private JTextField nricTextBox;
	private JTextField schoolTextBox;
	private JTextField emailTextBox;
	private JTextField addressTextBox;
	private JTextField telephoneNoTextBox;
	private JTextField handphoneNoTextBox;
	private JTextField secretAnswerTextBox;
	
	
	JFrame getJFrame(){
		jFrame = new JFrame();
		jFrame.setSize(1000,1000);
		jFrame.setVisible(true);
		jFrame.setTitle("Registration");
		jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jFrame.setContentPane(getPanel());
		
		return jFrame;
		
	}
	
	private JPanel getPanel(){
		panel = new JPanel();	
		panel.setLayout(null);
		panel.setSize(500,500);
		panel.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		//Button
		
		cancelButton = new JButton();
		cancelButton.setBounds(new Rectangle(850, 600, 81, 31));
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				jFrame.setVisible(false);
				
			}
		});
		
		
		submitRegistrationButton = new JButton();
		submitRegistrationButton.setBounds(new Rectangle(750, 600, 81, 31));
		submitRegistrationButton.setText("Submit");
		submitRegistrationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

						if (userNameTextBox.getText().equals("")
								|| passwordTextBox.getText().equals("")
								|| firstNameTextBox.getText().equals("")
								|| lastNameTextBox.getText().equals("")
								|| nricTextBox.getText().equals("")
								|| schoolTextBox.getText().equals("")
								|| emailTextBox.getText().equals("")) {
							System.out.println("Empty field");
							JOptionPane.showConfirmDialog(null,
									"Please Fill up all Field", "Empty Field",
									JOptionPane.CLOSED_OPTION);
						}
							String number = "[\\p{Digit}&&[123456789]]+";
							if (!telephoneNoTextBox.getText().matches(number)) {
								JOptionPane.showMessageDialog(null,
										"Only Numerical Digits allowed");
								System.out.println("Number");
								
								if(!(passwordTextBox.getText().equals(cPasswordTextBox.getText()))){
									
									JOptionPane.showMessageDialog(null,
									"Password Does not Match!");
							System.out.println("password");
									
								}
							}
//							String string = nricTextBox.getText();
//							String substringNirc = string.substring(0, 1);
//							String substringNirc1 = string.substring(8, 9);
//
//							if (!(substringNirc.matches(number)
//									&& substringNirc1.matches(number))) {
//								JOptionPane.showMessageDialog(null,
//							
//								"Invalid IC Number");
//								System.out.println("bwrb");
//							}
					
			
			}});
		
		//combo box
		monthCombo = new JComboBox(monthArray());
		monthCombo.setBounds(600, 300, 100, 20);
		
		dayCombo = new JComboBox(dayArray());
		dayCombo.setBounds(550, 300, 40, 20);
		
		yearCombo = new JComboBox(yearArray());
		yearCombo.setBounds(710, 300, 70, 20);
		
		secretQuestionCombo = new JComboBox(secretQuestion());
		secretQuestionCombo.setBounds(240,550,140,20);
		
		
		//Labels
		
		headerText = new JLabel();
		headerText.setBounds(new Rectangle(44, 15, 205, 69));
		headerText.setFont(new Font("Eras Light ITC", Font.BOLD | Font.ITALIC, 20));
		headerText.setText("Member Registration");
		
		
		userNameLabel = new JLabel();
		userNameLabel.setBounds(new Rectangle(120, 200, 140, 20));
		userNameLabel.setText("User Name");
		
		passwordLabel = new JLabel();
		passwordLabel.setBounds(new Rectangle(450, 200, 140, 20));
		passwordLabel.setText("Password");
		
		firstNameLabel = new JLabel();
		firstNameLabel.setBounds(new Rectangle(120, 100, 140, 20));
		firstNameLabel.setText("First Name");
		
		
		lastNameLabel = new JLabel();
		lastNameLabel.setBounds(new Rectangle(450, 100, 140, 20));
		lastNameLabel.setText("Last Name");
		
		dateOfBirthLabel = new JLabel();//
		dateOfBirthLabel.setBounds(new Rectangle(450, 300, 140, 20));
		dateOfBirthLabel.setText("Date Of Birth");

		
		nricLabel = new JLabel();
		nricLabel.setBounds(new Rectangle(120, 300, 140, 20));
		nricLabel.setText("NRIC");
		
		schoolLabel = new JLabel();
		schoolLabel.setBounds(new Rectangle(120, 400, 140, 20));
		schoolLabel.setText("School");
		
		emailLabel = new JLabel();
		emailLabel.setBounds(new Rectangle(450, 400, 140, 20));
		emailLabel.setText("Email");
		
		addressLabel = new JLabel();
		addressLabel.setBounds(new Rectangle(120, 450, 140, 20));
		addressLabel.setText("Address");
		
		telephoneNoLabel = new JLabel();
		telephoneNoLabel.setBounds(new Rectangle(120, 500, 140, 20));
		telephoneNoLabel.setText("Tel. No");
		
		handphoneNoLabel = new JLabel();
		handphoneNoLabel.setBounds(new Rectangle(450, 500, 140, 20));
		handphoneNoLabel.setText("H/p No");
		

		secretQuestionLabel = new JLabel();
		secretQuestionLabel.setBounds(new Rectangle(120, 550, 140, 20));
		secretQuestionLabel.setText("Secret Question");
		
		secretAnswerLabel = new JLabel();
		secretAnswerLabel.setBounds(new Rectangle(450, 550, 140, 20));
		secretAnswerLabel.setText("Secret Answer");
		
		
		
		
		
		
		// Text Boxes
		firstNameTextBox = new JTextField();
		firstNameTextBox.setBounds(new Rectangle(200, 100, 140, 20));
		
		lastNameTextBox = new JTextField();
		lastNameTextBox.setBounds(new Rectangle(550, 100, 140, 20));
		
		userNameTextBox = new JTextField();
		userNameTextBox.setBounds(new Rectangle(200, 200, 140, 20));
		
		passwordTextBox = new JPasswordField();
		passwordTextBox.setBounds(new Rectangle(550, 200, 140, 20));
		
		cPasswordTextBox = new JPasswordField();
		cPasswordTextBox.setBounds(new Rectangle(650, 200, 140, 20));
		
		nricTextBox = new JTextField();
		nricTextBox.setBounds(new Rectangle(200, 300, 140, 20));
		
		schoolTextBox = new JTextField();
		schoolTextBox.setBounds(new Rectangle(200, 400, 140, 20));
		
		emailTextBox = new JTextField();
		emailTextBox.setBounds(new Rectangle(550, 400, 140, 20));
		
		addressTextBox = new JTextField();
		addressTextBox.setBounds(new Rectangle(200, 450, 140, 20));
		
		telephoneNoTextBox = new JTextField();
		telephoneNoTextBox.setBounds(new Rectangle(200, 500, 140, 20));
		
		handphoneNoTextBox = new JTextField();
		handphoneNoTextBox.setBounds(new Rectangle(550, 500, 140, 20));
		
		
		secretAnswerTextBox = new JTextField();
		secretAnswerTextBox.setBounds(new Rectangle(550,550,140,20));
		
		
		
		//button
		panel.add(submitRegistrationButton);
		//panel.add(getSubmitRegistrationButton());
		panel.add(cancelButton);
		
		
		//comboBox
		panel.add(monthCombo);
		panel.add(dayCombo);
		panel.add(yearCombo);
		panel.add(secretQuestionCombo);
		
		//Label
		panel.add(headerText);
		panel.add(firstNameLabel);
		panel.add(lastNameLabel);
		panel.add(userNameLabel);
		panel.add(passwordLabel);
		panel.add(dateOfBirthLabel);
		panel.add(nricLabel);
		panel.add(schoolLabel);
		panel.add(emailLabel);
		panel.add(addressLabel);
		
		
		
		//textBox
		panel.add(firstNameTextBox);
		panel.add(lastNameTextBox);
		panel.add(userNameTextBox);
		panel.add(passwordTextBox);
		panel.add(nricTextBox);
		panel.add(schoolTextBox);
		panel.add(emailTextBox);
		panel.add(addressTextBox);
		panel.add(telephoneNoLabel);
		panel.add(handphoneNoLabel);
		panel.add(secretQuestionLabel);
		panel.add(secretAnswerLabel);
		panel.add(secretAnswerTextBox);
		panel.add(telephoneNoTextBox);
		panel.add(handphoneNoTextBox);
		panel.add(cPasswordTextBox);
		
		
		
		
		return panel;
		}
	
	
	
	//Array for Date ComboBox
	
	public String[] dayArray(){
		String [] dayArray = new String[32];
		dayArray[0]="Day";
	    dayArray[1] = "1"; dayArray[2] = "2";  dayArray[3] = "3";  dayArray[4] = "4"; dayArray[5] = "5"; dayArray[6] = "6";
	    dayArray[7] = "7"; dayArray[8] = "8";  dayArray[9] = "9";  dayArray[10] = "10";  dayArray[11] = "11";  dayArray[12] = "12";
	    dayArray[13] = "13"; dayArray[14] = "14";  dayArray[15] = "15";   dayArray[16] = "16";  dayArray[17] = "17"; dayArray[17] = "18";
	    dayArray[19] = "19"; dayArray[20] = "20"; dayArray[21] = "21";dayArray[22] = "22";dayArray[23] = "23";dayArray[24] = "24";
	    dayArray[25] = "25";dayArray[26] = "26";dayArray[27] = "27";dayArray[28] = "28";dayArray[29] = "29";dayArray[30] = "30";dayArray[31] = "31";
	    
		return dayArray;
		
	}
	public String [] monthArray(){
		String[] monthArray = new String[13];
		monthArray[0]="Month";
		monthArray[1]= "January";monthArray[2]= "February";monthArray[3]= "March";monthArray[4]= "April";monthArray[5]="May";
		monthArray[6]= "June";monthArray[7]= "July";monthArray[8]= "August";monthArray[9]= "September";monthArray[10]= "October";
		monthArray[11]= "November";monthArray[12]= "December";
		
		
		return monthArray;
		
	}
	
	
	
	
	public String [] yearArray(){
		String [] yearArray = new String[41];
		yearArray[0] ="Year";yearArray[1] ="1961";yearArray[2] ="1962";yearArray[3] ="1963";yearArray[4] ="1964";yearArray[5] ="1965";yearArray[6] ="1966";yearArray[7] ="1967";yearArray[8] ="1968";
		yearArray[9] ="1969";yearArray[10] ="1970";yearArray[11] ="1971";yearArray[12] ="1972";yearArray[13] ="1973";yearArray[14] ="1974";yearArray[15] ="1975";yearArray[16] ="1976";yearArray[17] ="1977";
		yearArray[18] ="1978";yearArray[19] ="1979";yearArray[20] ="1980";yearArray[21] ="1981";yearArray[22] ="1982";yearArray[23] ="1983";yearArray[24] ="1984";yearArray[25] ="1985";yearArray[26] ="1986";
		yearArray[27] ="1987";yearArray[28] ="1988";yearArray[29] ="1989";yearArray[30] ="1990";yearArray[31] ="1991";yearArray[32] ="1992";yearArray[33] ="1993";yearArray[34] ="1994";
		yearArray[35] ="1995";yearArray[36] ="1996";yearArray[37] ="1997";yearArray[38] ="1998";yearArray[39] ="1999";yearArray[40] ="2000";
		return yearArray;
		
	}
	
	public String date(){
		return (yearCombo+"-"+monthCombo+"-"+dayCombo);
	}
	
	
	public Date parseDate(String date) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		return formatter.parse(date());
	}
	
	
	
	//Array for Secret Question
	public String [] secretQuestion(){
	String[] question = new String[5];
	question[0]="Select one";
	question[1]="What is Primary School Name";
	question[2]="What is your girlfiend name";
	question[3]= "What is your favourite local dish";
	question[4]= "What is your Proudest moment";
	
	return question;
	}
	
	
	
	//Button Action Listener and Button Method

//	private JButton getSubmitRegistrationButton() {
//
//		submitRegistrationButton.addActionListener(new java.awt.event.ActionListener() {
//					public void actionPerformed(java.awt.event.ActionEvent e) {
//
//						try {
//						
//									
//									}
//							
//						} catch (Exception e1) {
//							
//							
//							
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}}
//				});
//						
//						return submitRegistrationButton;
//					}

					private void submitRegistration(ActionEvent e)throws Exception {
						
						CreateRIController registerController = new CreateRIController();
			registerController.createRegistration
				(userNameTextBox.getText(),
					passwordTextBox.getText(),
					firstNameTextBox.getText(),
					lastNameTextBox.getText(),
					//parseDate(date()), 
					nricTextBox.getText(),
					schoolTextBox.getText(),
					emailTextBox.getText(),
					addressTextBox.getText(),
					telephoneNoTextBox.getText(),
					handphoneNoTextBox.getText(), //secretQuestion(),
					secretAnswerTextBox.getText());
					
		
					}

			
	
	
	
	
	public static void main(String a[]){
		
		
		CreateRIForm view = new CreateRIForm();
		view.getJFrame().setVisible(true);
		
	}
}