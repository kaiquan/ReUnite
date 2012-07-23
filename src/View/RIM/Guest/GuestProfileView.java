package View.RIM.Guest;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;
import View.RIM.Components.GuestActionsFooter;
import View.RIM.CreateInvitationView;


import net.miginfocom.swing.MigLayout;
import Controller.RIM.LookAndFeelController;

@SuppressWarnings("serial")
public class GuestProfileView extends JFrame
{
	private Guest guest;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public GuestProfileView(Guest guest)
	{
		this.guest = guest;
		initialize();
		setContentPane(getContentPane());
	}
	
	public JPanel getContentPane()
	{
		
		JPanel defaultPanel = new JPanel();
		defaultPanel.setLayout(new MigLayout("", "[28.00][403.00,grow][384.00,grow][384.00,grow][532.00,grow][305.00,grow][302.00,grow][106.00]", "[40.00][20.00][98.00,grow][285.00][grow][][]"));
		
		JLabel lblCreateYourInvitation = new JLabel("Welcome, " +guest.getUserName());
		lblCreateYourInvitation.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		defaultPanel.add(lblCreateYourInvitation, "cell 1 0 6 1");
		
		JPanel form = new JPanel();
		form.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		defaultPanel.add(form, "cell 1 2 2 1,grow");
		form.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblFirstName = new JLabel("First name: ");
		form.add(lblFirstName, "cell 0 0,alignx left");
		
		textField = new JTextField();
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setPreferredSize(new Dimension(100, 20));
		form.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name:");
		form.add(lblLastName, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		form.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblEmailAdress = new JLabel("Email adress:");
		form.add(lblEmailAdress, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		form.add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		final JPanel panel = new JPanel();
		panel.setBorder(null);
		defaultPanel.add(panel, "cell 6 2,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CreateInvitationView.class.getResource("/Images/userIcon.png")));
		panel.add(label, "cell 0 0");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ImageHelper.loadImage("cutShadowTop.png", "testing").getScaledInstance(230, -1, 3)));
		panel.add(lblNewLabel, "cell 0 1,alignx center");
		
		
		JButton btnSelectPhoto = new JButton("Chage profile picture");
		btnSelectPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showDialog(panel, "Select photo...");
			}
		});
		defaultPanel.add(btnSelectPhoto, "cell 6 3,alignx center,aligny top");
		
		JLabel cutShadow = new JLabel("");
		cutShadow.setIcon(new ImageIcon(ImageHelper.loadImage("cutShadowBottom.png", "testing")));
		defaultPanel.add(cutShadow, "cell 1 6 3 1,alignx center");
		
		
		
		defaultPanel.add(new GuestActionsFooter(), "south,alignx center");
		
		
		

		return defaultPanel;
	}
	
	private void initialize()
	{
		setTitle( "Profile of " + guest.getUserName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		setSize(new Dimension(1024, 600));
		
		LookAndFeelController.getWindowsLookAndFeel();
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new GuestProfileView(new Guest("adeelateeque@hotmail.com"));
	}
}
