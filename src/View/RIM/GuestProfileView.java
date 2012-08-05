package View.RIM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.LookAndFeelController;
import Controller.RIM.Utils.IOHelper;
import Images.RIM.ImageHelper;
import Model.Membership.Account;
import Model.Membership.Guest;
import View.RIM.Components.GuestActionsFooter;
@SuppressWarnings("serial")
public class GuestProfileView extends JFrame
{
	private Guest guest;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	JTextArea textArea;
	
	public GuestProfileView()
	{
		guest = new Guest();
		guest = guest.GET_GUEST_ACCOUNT(Account.currentUser.getUserName());
		setContentPane(getContentPane());
		initialize();
	}
	
	public JPanel getContentPane()
	{
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.setLayout(new MigLayout("", "[28.00][403.00,grow][][384.00,grow][][384.00,grow][106.00]", "[][40.00][20.00][98.00,grow][285.00][27.00][grow][18.00][]"));
		
		JLabel lblCreateYourInvitation = new JLabel("Welcome, " +guest.getUserName());
		lblCreateYourInvitation.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		defaultPanel.add(lblCreateYourInvitation, "cell 1 1 5 1,alignx center,aligny bottom");
		
		JPanel form = new JPanel();
		form.setFont(new Font("Segoe UI Light", Font.PLAIN, 11));
		form.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Personal Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		defaultPanel.add(form, "cell 1 3 4 5,grow");
		form.setLayout(new MigLayout("", "[][176.00,grow][102.00,grow][grow]", "[][][][][][][][][][128.00,grow][69.00]"));
		
		JLabel lblFirstName = new JLabel("First name: ");
		form.add(lblFirstName, "cell 0 0,alignx left");
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setPreferredSize(new Dimension(100, 20));
		textField.setText(Account.currentUser.getFirstName());
		form.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name:");
		form.add(lblLastName, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		form.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		textField_1.setText(Account.currentUser.getLastName());
		
		JLabel lblSchool = new JLabel("School:");
		form.add(lblSchool, "cell 0 2,alignx left");
		
		textField_4 = new JTextField();
		form.add(textField_4, "cell 1 2,growx");
		textField_4.setColumns(10);
		textField_4.setText(Account.currentUser.getSchool());
		
		JLabel lblEmailAdress = new JLabel("Email adress:");
		form.add(lblEmailAdress, "cell 0 5,alignx left");
		
		textField_2 = new JTextField();
		textField_2.setText(Account.currentUser.getEmail());
		form.add(textField_2, "cell 1 5,growx");
		textField_2.setColumns(10);
		
		JLabel lblHandphoneNumber = new JLabel("Phone number:");
		form.add(lblHandphoneNumber, "cell 0 6,alignx trailing");
		
		textField_5 = new JTextField();
		textField_5.setText(Account.currentUser.getHandphoneNo());
		form.add(textField_5, "cell 1 6,growx");
		textField_5.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		form.add(lblDateOfBirth, "cell 0 3,alignx left");
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		form.add(comboBox, "flowx,cell 1 3,growx");
		
		JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		form.add(comboBox_1, "cell 2 3,growx");
		
		JComboBox<Integer> comboBox_2 = new JComboBox<Integer>();
		form.add(comboBox_2, "cell 1 3,growx");
		
		JLabel lblNric = new JLabel("NRIC:");
		form.add(lblNric, "cell 0 7,alignx left");
		
		textField_3 = new JTextField();
		textField_3.setText(Account.currentUser.getNric());
		form.add(textField_3, "cell 1 7,growx");
		textField_3.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		form.add(lblAddress, "cell 0 9,aligny top");
		
		textArea = new JTextArea();
		textArea.setText(Account.currentUser.getAddress());
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		form.add(textArea, "cell 1 9 2 1,grow");
		
		JButton tglbtnUpdateDetails = new JButton("Update details");
		tglbtnUpdateDetails.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Guest guest = new Guest();
				guest.setUserName(Account.currentUser.getUserName());
				guest.setFirstName(textField.getText());
				guest.setLastName(textField_1.getText());
				guest.setSchool(textField_4.getText());
				guest.setEmail(textField_2.getText());
				guest.setHandphoneNo(textField_5.getText());
				guest.setNric(textField_3.getText());
				guest.setAddress(textArea.getText());
				if(guest.CREATE_GUEST_ACCOUNT(guest))
				{
					JOptionPane.showMessageDialog(null, "Your details have been updated successfully");
				}
			}
			
		});
		tglbtnUpdateDetails.setPreferredSize(new Dimension(101, 40));
		form.add(tglbtnUpdateDetails, "cell 0 10 4 1,alignx center,aligny bottom");
		
		final JLabel profilePicture = new JLabel("");
		profilePicture.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		defaultPanel.add(profilePicture, "flowy,cell 5 3,alignx center,gapy 5 0");
		profilePicture.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 215, -1, 5));
		
		
		JButton btnSelectPhoto = new JButton("Change profile picture");
		btnSelectPhoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ImageIcon backupIcon = (ImageIcon) profilePicture.getIcon();
			
					Image image = IOHelper.getImageFile();
					if(image !=null)
					{
						profilePicture.setIcon(ImageHelper.getScaledImageIcon(new ImageIcon(image), 200, -1, 5));
					}
					else 
					{ 
						profilePicture.setIcon(backupIcon);
					}
			}
		});
		defaultPanel.add(btnSelectPhoto, "cell 5 4,alignx center,aligny top");
		
		JLabel cutShadow = new JLabel("");
		cutShadow.setIcon(new ImageIcon(ImageHelper.loadImage("cutShadowBottom.png", "testing")));
		defaultPanel.add(cutShadow, "cell 1 8 5 1,alignx center");
		
		
		
		defaultPanel.add(new GuestActionsFooter(), "south,alignx center");
		
		JLabel lblNewLabel = new JLabel("");
		defaultPanel.add(lblNewLabel, "cell 5 3");
		lblNewLabel.setIcon(new ImageIcon(ImageHelper.loadImage("cutShadowTop.png", "testing").getScaledInstance(230, -1, 3)));
		
		
		

		return defaultPanel;
	}
	

	private void initialize()
	{
		setTitle( "Profile of " + guest.getUserName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		setSize(new Dimension(677, 600));
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		LookAndFeelController.setGlobalLookAndFeel();
		new GuestProfileView();
	}
}
