package View.MM;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.MM.LoginController;
import Model.Membership.Account;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class LogInForm extends JFrame {

		private JPanel loginPanel = null;
		private JButton loginButton = null;
		private JTextField userNameTextBox = null;
		private JPasswordField passwordTextBox = null;
		private JLabel userNameLabel = null;
		private JLabel passwordLabel = null;
		private JPanel jPanel = null;
		private JLabel jLabel = null;
		private JTextField jTextField = null;  //  @jve:decl-index=0:visual-constraint="760,255"
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
	        this.setSize(new Dimension(568, 322));
	        this.setResizable(false);
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
				passwordLabel.setText("Password");
				passwordLabel.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 18));
				passwordLabel.setBounds(new Rectangle(69, 177, 130, 20));
				userNameLabel = new JLabel();
				userNameLabel.setText("User Name");
				userNameLabel.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 18));
				userNameLabel.setBounds(new Rectangle(69, 93, 153, 38));
				loginPanel = new JPanel();
				loginPanel.setLayout(null);
				loginPanel.add(getJPanel(), null);
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
				loginButton.setRolloverIcon(new ImageIcon(getClass().getResource("/Images/MM/glossy_black_button_icon_079.png")));
				loginButton.setBounds(new Rectangle(224, 246, 101, 30));
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
										new AdministrateRIPersonalDetails().getJFrame().setVisible(true);
							}
							
							else if(Account.currentUser.getType().equalsIgnoreCase("GR")) 
							{
								new AdministrateRIDetails().getJFrame().setVisible(true);
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
				userNameTextBox.setBounds(new Rectangle(86, 133, 389, 33));
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
				passwordTextBox.setBounds(new Rectangle(88, 204, 390, 31));
			}
			return passwordTextBox;
		}
		
		
		
		/**
		 * This method initializes jPanel	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanel() {
			if (jPanel == null) {
				jLabel = new JLabel();
				jLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/folder_black_lock.png")));
				jLabel.setBounds(new Rectangle(209, 11, 223, 105));
				jLabel.setFont(new Font("KaiTi", Font.BOLD | Font.ITALIC, 24));
				jLabel.setText("Login");
				jPanel = new JPanel();
				jPanel.setLayout(null);
				jPanel.setBounds(new Rectangle(-1, 2, 596, 319));
				jPanel.setBackground(new Color(76, 151, 225));
				jPanel.add(getPasswordTextBox(), null);
				jPanel.add(passwordLabel, null);
				jPanel.add(getUserNameTextBox(), null);
				jPanel.add(userNameLabel, null);
				jPanel.add(getLoginButton(), null);
				jPanel.add(jLabel, null);
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
			}
			return jTextField;
		}

		public static void main(String args[]){
				LogInForm n = new LogInForm();
				n.setVisible(true);
		}


}  //  @jve:decl-index=0:visual-constraint="10,10"
