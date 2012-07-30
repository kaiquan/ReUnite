package View.MM;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import Controller.MM.CreateRIController;
import Controller.MM.LoginController;
import Model.Membership.Account;

public class LogInForm extends JFrame {

	private JPanel loginPanel = null;
	private JButton loginButton = null;
	private JTextField userNameTextBox = null;
	private JPasswordField passwordTextBox = null;
	private JLabel userNameLabel = null;
	private JLabel passwordLabel = null;
	/**
	 * This method initializes 
	 * 
	 */
	public LogInForm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(545, 261));
        this.setContentPane(getLoginPanel());
        this.setTitle("Login");
			
	}

	/**
	 * This method initializes loginPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLoginPanel() {
		if (loginPanel == null) {
			passwordLabel = new JLabel();
			passwordLabel.setBounds(new Rectangle(74, 124, 103, 16));
			passwordLabel.setText("Password");
			userNameLabel = new JLabel();
			userNameLabel.setBounds(new Rectangle(73, 62, 96, 16));
			userNameLabel.setText("User Name");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			loginPanel = new JPanel();
			loginPanel.setLayout(null);
			loginPanel.add(getLoginButton(), gridBagConstraints);
			loginPanel.add(getUserNameTextBox(), null);
			loginPanel.add(getPasswordTextBox(), null);
			loginPanel.add(userNameLabel, null);
			loginPanel.add(passwordLabel, null);
		}
		return loginPanel;
	}

	/**
	 * This method initializes loginButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLoginButton() {
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setText("Login");
			loginButton.setBounds(new Rectangle(344, 168, 97, 26));
			loginButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					LoginController login = new LoginController();
					
					login.initiateLogin(userNameTextBox.getText(), passwordTextBox.getText());
					
					LoginController verify = new LoginController();
					
					if (verify.initiateLogin(getUserNameTextBox().getText(), getPasswordTextBox().getText()) == true)
					{
						if(Account.currentUser.getType().equalsIgnoreCase("RI"))
						{
							System.out.println("Access Granted");
									new ViewRIDetailsRIVIEW().getJFrame().setVisible(true);
						}
						
						else if(Account.currentUser.getType().equalsIgnoreCase("GR")) 
						{
							new ViewRIDetails().getJFrame().setVisible(true);
						}
					}
					else
					{	
						JOptionPane.showMessageDialog(null,"UserName or Password does not exist, Try Again");
					
						System.out.println("Access Denied");
						
					}
					
					
					
					
				}
			});
			
		}
		return loginButton;
	}

	/**
	 * This method initializes UserNameTextBox	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUserNameTextBox() {
		if (userNameTextBox == null) {
			userNameTextBox = new JTextField();
			userNameTextBox.setBounds(new Rectangle(196, 61, 150, 20));
		}
		return userNameTextBox;
	}

	/**
	 * This method initializes passwordTextBox	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPasswordTextBox() {
		if (passwordTextBox == null) {
			passwordTextBox = new JPasswordField();
			passwordTextBox.setBounds(new Rectangle(197, 123, 151, 20));
		}
		return passwordTextBox;
	}
	
	
	
	public static void main(String a[]){
			LogInForm n = new LogInForm();
			n.setVisible(true);
	}

}  
