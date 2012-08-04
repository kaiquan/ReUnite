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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.EmailController;
import Controller.MM.*;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;


public class CreateRIForm extends JFrame {
	
	private JFrame jFrame ;  //  @jve:decl-index=0:visual-constraint="-21,27"
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
	private JTextPane addressTextBox;
	private JTextField telephoneNoTextBox;
	private JTextField handphoneNoTextBox;
	private JTextField secretAnswerTextBox;
	private JLabel cPasswordLabel = null;
	private JComboBox jComboBox = null;
	private JPanel jPanel = null;
	private JLabel SignIn = null;
	private JLabel submitLabel = null;
	private JLabel jLabel = null;
	/**
	 * This method initializes 
	 * 
	 */
	public CreateRIForm() {
		super();
		initialize();
		  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}




	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(406, 125));
        this.setLocationRelativeTo(null);
			
	}




	JFrame getJFrame(){
		jFrame = new JFrame();
		jFrame.setSize(1191, 552);
		jFrame.setVisible(true);
		jFrame.setTitle("Registration");
		jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setContentPane(getPanel());
		
		
		
		return jFrame;
		
	}

	
	
	
	private JPanel getPanel(){
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(637, 421, 193, 91));
		jLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Windows-Close-Program-icon.png")));
		jLabel.setText("Cancel");
		jLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				jFrame.setVisible(false);
				System.out.println("cancel()"); // TODO Auto-generated Event stub mouseClicked()
			}
		});
		submitLabel = new JLabel();
		submitLabel.setBounds(new Rectangle(418, 427, 141, 82));
		submitLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		submitLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/App-noatun-icon.png")));
		submitLabel.setText("Submit");
		submitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				if(validation()){
					System.out.println("validation ok");
				}
				createUser();	
				
		
				System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
			}
		});
		SignIn = new JLabel();
		SignIn.setBounds(new Rectangle(1062, 4, 94, 16));
		SignIn.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Login-in-icon.png")));
		SignIn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		SignIn.setText("SignIn");
		SignIn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
//				LogInForm login = new LogInForm();
//					login.getJFrame().setVisible(true);
				System.out.println("SignIn");
			}
		});
		cPasswordLabel = new JLabel();
		cPasswordLabel.setText("Confirm Password");
		cPasswordLabel.setBounds(new Rectangle(340, 79, 129, 16));
		panel = new JPanel();	
		panel.setLayout(null);
		panel.setSize(500,500);
		panel.setBackground(new Color(102, 153, 255));
		panel.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		//Button
		
		cancelButton = new JButton();
		cancelButton.setBounds(new Rectangle(972, 500, 81, 31));
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				jFrame.setVisible(false);
				
			}
		});
		
		
		
		submitRegistrationButton = new JButton();
		submitRegistrationButton.setBounds(new Rectangle(871, 503, 113, 42));
		submitRegistrationButton.setIcon(new ImageIcon(getClass().getResource("/Images/MM/registration_submit_icon.png")));
		submitRegistrationButton.setText("Submit");
		submitRegistrationButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
			
				if(validation()){
					System.out.println("validation ok");
				}
				createUser();	
				
			}});
		
		//combo box
		
		dayCombo = new JComboBox(dayArray());
		dayCombo.setBounds(340, 230, 40, 20);
		
		monthCombo = new JComboBox(monthArray());
		monthCombo.setBounds(390, 230, 100, 20);

		
		yearCombo = new JComboBox(yearArray());
		yearCombo.setBounds(500, 230, 70, 20);
	
		
		
		
		secretQuestionCombo = new JComboBox(secretQuestion());
		secretQuestionCombo.setBounds(975,110,180,20);
		
		
		//Labels
		
		headerText = new JLabel();
		headerText.setBounds(new Rectangle(-6, -10, 913, 153));
		headerText.setFont(new Font("Eras Light ITC", Font.BOLD | Font.ITALIC, 18));
		headerText.setBackground(Color.black);
		headerText.setIcon(new ImageIcon(getClass().getResource("/Images/MM/icon-people.png")));
		headerText.setForeground(Color.black);
		headerText.setText("Great Reunion Member Registration");
		
		
		userNameLabel = new JLabel();
		userNameLabel.setText("User Name");
		userNameLabel.setBounds(new Rectangle(31, 13, 140, 20));
		
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		passwordLabel.setBounds(new Rectangle(30, 76, 140, 20));
		
		firstNameLabel = new JLabel();
		firstNameLabel.setText("First Name");
		firstNameLabel.setBounds(new Rectangle(31, 137, 140, 20));
		
		
		lastNameLabel = new JLabel();
		lastNameLabel.setText("Last Name");
		lastNameLabel.setBounds(new Rectangle(30, 197, 140, 20));
		
		dateOfBirthLabel = new JLabel();//
		dateOfBirthLabel.setText("Date Of Birth");

		dateOfBirthLabel.setBounds(new Rectangle(341, 196, 140, 20));
		
		nricLabel = new JLabel();
		nricLabel.setText("NRIC");
		nricLabel.setBounds(new Rectangle(339, 137, 140, 20));
		
		schoolLabel = new JLabel();
		schoolLabel.setText("School");
		schoolLabel.setBounds(new Rectangle(974, 27, 140, 20));
		
		emailLabel = new JLabel();
		emailLabel.setText("Email");
		emailLabel.setBounds(new Rectangle(340, 15, 140, 20));
		
		addressLabel = new JLabel();
		addressLabel.setText("Address");
		addressLabel.setBounds(new Rectangle(650, 160, 140, 20));
		
		telephoneNoLabel = new JLabel();
		telephoneNoLabel.setText("Tel. No");
		telephoneNoLabel.setBounds(new Rectangle(645, 27, 140, 20));
		
		handphoneNoLabel = new JLabel();
		handphoneNoLabel.setText("H/p No");
		handphoneNoLabel.setBounds(new Rectangle(649, 87, 140, 20));
		

		secretQuestionLabel = new JLabel();
		secretQuestionLabel.setText("Secret Question");
		secretQuestionLabel.setBounds(new Rectangle(975, 89, 140, 20));
		
		secretAnswerLabel = new JLabel();
		secretAnswerLabel.setText("Secret Answer");
		secretAnswerLabel.setBounds(new Rectangle(975, 154, 140, 20));
		
		
		
		
		
		
		// Text Boxes
		firstNameTextBox = new JTextField();
		firstNameTextBox.setBounds(new Rectangle(30, 164, 240, 26));
		firstNameTextBox.setBackground(new Color(219, 225, 224));
		
		lastNameTextBox = new JTextField();
		lastNameTextBox.setBounds(new Rectangle(31, 226, 241, 26));
		lastNameTextBox.setBackground(new Color(219, 225, 224));
		
		userNameTextBox = new JTextField();
		userNameTextBox.setBounds(new Rectangle(31, 45, 240, 27));
		userNameTextBox.setBackground(new Color(219, 225, 224));
		
		passwordTextBox = new JPasswordField();
		passwordTextBox.setBounds(new Rectangle(30, 103, 239, 27));
		passwordTextBox.setBackground(new Color(219, 225, 224));
		
		cPasswordTextBox = new JPasswordField();
		cPasswordTextBox.setBounds(new Rectangle(338, 107, 234, 25));
		cPasswordTextBox.setBackground(new Color(219, 225, 224));
		
		nricTextBox = new JTextField();
		nricTextBox.setBounds(new Rectangle(341, 165, 196, 23));
		nricTextBox.setBackground(new Color(219, 225, 224));
		
		schoolTextBox = new JTextField();
		schoolTextBox.setBounds(new Rectangle(975, 57, 140, 20));
		schoolTextBox.setBackground(new Color(219, 225, 224));
		
		emailTextBox = new JTextField();
		emailTextBox.setBounds(new Rectangle(338, 47, 232, 25));
		emailTextBox.setBackground(new Color(219, 225, 224));
		
		addressTextBox = new JTextPane();
		addressTextBox.setBounds(new Rectangle(648, 181, 209, 67));
		addressTextBox.setBackground(new Color(219, 225, 224));
		
		
		telephoneNoTextBox = new JTextField();
		telephoneNoTextBox.setBounds(new Rectangle(647, 45, 140, 30));
		telephoneNoTextBox.setBackground(new Color(219, 225, 224));
		
		handphoneNoTextBox = new JTextField();
		handphoneNoTextBox.setBounds(new Rectangle(649, 108, 140, 27));
		handphoneNoTextBox.setBackground(new Color(219, 225, 224));
		
		
		secretAnswerTextBox = new JTextField();
		secretAnswerTextBox.setBounds(new Rectangle(974, 195, 201, 32));
		secretAnswerTextBox.setBackground(new Color(219, 225, 224));
		
		
		
		//button
		panel.add(submitRegistrationButton);
		//panel.add(getSubmitRegistrationButton());
		panel.add(cancelButton);
		
		
		//comboBox
		
		//Label
		panel.add(headerText);
		
		
		
		//textBox
		panel.add(getJPanel(), null);
		panel.add(SignIn, null);
		panel.add(submitLabel, null);
		panel.add(jLabel, null);
		
		
		
		
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
		monthArray[1]= "1";monthArray[2]= "2";monthArray[3]= "3";monthArray[4]= "4";monthArray[5]="5";
		monthArray[6]= "6";monthArray[7]= "7";monthArray[8]= "8";monthArray[9]= "9";monthArray[10]= "10";
		monthArray[11]= "11";monthArray[12]= "12";
		
		
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
	
	public String toString(){
		return (yearCombo.getSelectedItem()+"-"+monthCombo.getSelectedItem()+"-"+dayCombo.getSelectedItem());
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
	
				public boolean validation()
				{
					boolean validate = true;
					String number="[\\p{Digit}&&[123456789]]+";
					
					
					if(userNameTextBox.getText().equals("")||passwordTextBox.getText().equals("")||firstNameTextBox.getText().equals("")||lastNameTextBox.getText().equals("")||
					nricTextBox.getText().equals("")||schoolTextBox.getText().equals("")||emailTextBox.getText().equals("")||addressTextBox.getText().equals("")||
					telephoneNoTextBox.getText().equals("")||handphoneNoTextBox.getText().equals("")||//secretQuestionCombo.getSelectedItem().toString(),
					secretAnswerTextBox.getText().equals(""))
					{
						validate = false;
						JOptionPane.showMessageDialog(null, "Please fill up all the fields to continue...");
					}
					else if(!(passwordTextBox.getText().equals(cPasswordTextBox.getText())))
					{
						
						JOptionPane.showMessageDialog(null, "The passwords do not match, please try again to continue");
						validate = false;
					}
					
					 else if(!telephoneNoTextBox.getText().matches(number)&& !handphoneNoTextBox.getText().matches(number))
						 
					 {
						 validate = false;
						 JOptionPane.showMessageDialog(null, "Only Numerical values are allowed for handphone and telephphone please ammend to continue");
					 }
//					String string=nricTextBox.getText(); 
//					String substringNric = string.substring(0,1);
//					String substringNric1 = string.substring(8,9);
//					String substringNric2 = string.substring(2,7); 
					//else if(substringNric.matches(number)&&substringNric1.matches(number) && !substringNric2.matches(number) )
//					 {	
//						 validate = false;
//						 JOptionPane.showMessageDialog(null, "Invalid IC Number");
//					}	
							return validate;
				}
				
				public void createUser(){
					CreateRIController registerController = new CreateRIController();
					try {
						registerController.createRegistration(
											userNameTextBox.getText(),
											passwordTextBox.getText(),
											firstNameTextBox.getText(),
											lastNameTextBox.getText(),
											toString(),
											nricTextBox.getText(),
											schoolTextBox.getText(),
											emailTextBox.getText(),
											addressTextBox.getText(),
											telephoneNoTextBox.getText(),
											handphoneNoTextBox.getText(), 
											secretQuestionCombo.getSelectedItem().toString(),
											secretAnswerTextBox.getText());
						
						
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						EmailController emailController = new EmailController();
						String[] emailArray;               
					      emailArray = new String[1]; 
					      emailArray[0] = emailTextBox.getText();
						try {
							emailController.sendEmail("text", emailArray, "Account Creation with Great Reunion", "Congratulations! You have successfully created a new account with Great Reunion!", null, "Account");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						jComboBox.setBounds(new Rectangle(974, 119, 197, 25));
					}
					return jComboBox;
				}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(-26, 141, 1263, 269));
			jPanel.setBackground(new Color(0, 153, 204));
			jPanel.add(lastNameLabel, null);
			jPanel.add(firstNameTextBox, null);
			jPanel.add(lastNameTextBox, null);
			jPanel.add(userNameLabel, null);
			jPanel.add(userNameTextBox, null);
			jPanel.add(passwordTextBox, null);
			jPanel.add(passwordLabel, null);
			jPanel.add(cPasswordTextBox, null);
			jPanel.add(nricTextBox, null);
			jPanel.add(nricLabel, null);
			jPanel.add(handphoneNoTextBox, null);
			jPanel.add(emailTextBox, null);
			jPanel.add(dateOfBirthLabel, null);
			jPanel.add(emailLabel, null);
			jPanel.add(schoolTextBox, null);
			jPanel.add(schoolLabel, null);
			jPanel.add(cPasswordLabel, null);
			jPanel.add(handphoneNoLabel, null);
			jPanel.add(addressTextBox, null);
			jPanel.add(addressLabel, null);
			jPanel.add(secretAnswerLabel, null);
			jPanel.add(secretAnswerTextBox, null);
			jPanel.add(secretQuestionLabel, null);
			jPanel.add(secretQuestionCombo, null);
			jPanel.add(telephoneNoLabel, null);
			jPanel.add(telephoneNoTextBox, null);
			jPanel.add(firstNameLabel, null);
			jPanel.add(monthCombo);
			jPanel.add(dayCombo);
			jPanel.add(yearCombo);
			jPanel.add(secretQuestionCombo);
			
		}
		return jPanel;
	}




	public static void main(String a[]){
		
		
		CreateRIForm view = new CreateRIForm();
		view.getJFrame().setVisible(true);
	//	System.out.println(view.date());
		
	}
}  //  @jve:decl-index=0:visual-constraint="-9,567"