package View.MM;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.MM.LoginController;
import Controller.RIM.LookAndFeelController;
import Model.Membership.Account;

import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class LogInForm extends JFrame {

		private JPanel loginPanel = null;
		private JButton loginButton = null;
		private JTextField userNameTextBox = null;
		private JPasswordField passwordTextBox = null;
		private JLabel userNameLabel = null;
		private JLabel passwordLabel = null;
		private JPanel jPanel = null;
		private JLabel jLabel = null;
		private JLabel forgotPassword = null;
		private JLabel createMember = null;
		private JCheckBox jCheckBox = null;  //  @jve:decl-index=0:visual-constraint="14,178"
		private JLabel jLabel1 = null;
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
		void initialize() {
	        this.setSize(new Dimension(598, 403));
	        this.setResizable(false);
	        this.setContentPane(getLoginPanel());
	        this.setTitle("Login");
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.setLocationRelativeTo(null);
				
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
				passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 20));
				passwordLabel.setBounds(new Rectangle(193, 202, 130, 20));
				userNameLabel = new JLabel();
				userNameLabel.setText("User Name");
				userNameLabel.setFont(new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 20));
				userNameLabel.setBounds(new Rectangle(193, 127, 153, 38));
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
				loginButton.setBounds(new Rectangle(262, 272, 101, 30));
				loginButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						loginUser();
					}
				});
			}
			return loginButton;
		}

		public void loginUser()
		{
			LoginController login = new LoginController();
			
			boolean valid = login.initiateLogin(userNameTextBox.getText(), passwordTextBox.getText());
			
			if(valid==true)
			{
				this.setVisible(false);
				this.dispose();
			}
			else if(valid==false){
				JOptionPane.showMessageDialog(null, "Username or password does not exist, please try again!");
				
				
			}
		}
		/**
		 * This method initializes UserNameTextBox	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getUserNameTextBox() {
			if (userNameTextBox == null) {
				userNameTextBox = new JTextField();
				userNameTextBox.setBounds(new Rectangle(192, 163, 216, 29));
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
				passwordTextBox.setBounds(new Rectangle(197, 232, 215, 31));
				passwordTextBox.addKeyListener(new KeyListener(){

					@Override
					public void keyPressed(KeyEvent e) {
						
					if(e.getKeyCode()==10)
					{
						loginUser();
					}
						
					}
					

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}});
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
				
				jLabel1 = new JLabel();
				jLabel1.setBounds(new Rectangle(1, -43, 591, 416));
				jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Abstract Blue backgrounds 40.jpg")));
				jLabel1.setText("JLabel");
				createMember = new JLabel();
				createMember.setBounds(new Rectangle(450, 9, 268, 16));
				createMember.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
				createMember.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Login-in-icon.png")));
				createMember.setText("SignUp  ");
				createMember.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						CreateRIForm create = new CreateRIForm();
						create.getJFrame().setVisible(true);
					}
				});
				forgotPassword = new JLabel();
				forgotPassword.setBounds(new Rectangle(449, 9, 163, 16));
				forgotPassword.setComponentOrientation(ComponentOrientation.UNKNOWN);
				forgotPassword.setVisible(false);
				forgotPassword.setDisplayedMnemonic(KeyEvent.VK_UNDO);
				forgotPassword.setText("Forgot Password");
				forgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if(userNameTextBox.getText().equals("")){
						
							JOptionPane.showMessageDialog(null, "Please Enter your UserName");
							
						}
						else{
							 
							LoginController security = new LoginController();
							
							boolean valid = security.initiateSQuestion(userNameTextBox.getText());
							
							if(valid==true)
							{
								
							}
							else if(valid==false){
								JOptionPane.showMessageDialog(null, "Username or password does not exist, please try again!");
								
								
							}
									
							JLabel sQuestion = new JLabel();
							String closureReason = JOptionPane.showInputDialog(null,
						Account.currentUser.getSecretQuestion(),
							Account.currentUser.getSecretQuestion(), 0);

						}
					}
				});
				jLabel = new JLabel();
				jLabel.setIcon(new ImageIcon(getClass().getResource("/Images/MM/Lock-icon.png")));
				jLabel.setBounds(new Rectangle(219, 15, 223, 118));
				jLabel.setFont(new Font("KaiTi", Font.BOLD | Font.ITALIC, 24));
				jLabel.setText("");
				jPanel = new JPanel();
				jPanel.setLayout(null);
				jPanel.setBounds(new Rectangle(-1, 2, 596, 375));
				jPanel.setBackground(Color.white);
				jPanel.add(getPasswordTextBox(), null);
				jPanel.add(passwordLabel, null);
				jPanel.add(getUserNameTextBox(), null);
				jPanel.add(userNameLabel, null);
				jPanel.add(getLoginButton(), null);
				jPanel.add(jLabel, null);
				jPanel.add(forgotPassword, null);
				jPanel.add(createMember, null);
				jPanel.add(jLabel1, null);
			}
			return jPanel;
		}

		/**
		 * This method initializes jCheckBox	
		 * 	
		 * @return javax.swing.JCheckBox	
		 */
		private JCheckBox getJCheckBox() {
			if (jCheckBox == null) {
				jCheckBox = new JCheckBox();
			}
			return jCheckBox;
		}

		public static void main(String args[]){
				LogInForm n = new LogInForm();
				n.setVisible(true);
				LookAndFeelController.setGlobalLookAndFeel();
		}

		public JComponent getJFrame() {
			// TODO Auto-generated method stub
			return null;
		}
		

}  //  @jve:decl-index=0:visual-constraint="328,8"
