package View.RIM;

import java.awt.*;

import javax.swing.*;

import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SelectInvitationTypeView extends JFrame
{
	public SelectInvitationTypeView()
	{
		JPanel panel = new JPanel(new MigLayout("", "[65.00][154.00px:n,grow][167.00px:n,grow][82.00][172.00][150.00]",
				"[][216.00px,grow][65.00px,grow]"));
		panel.setBackground(UIManager.getColor("ScrollBar.thumb"));

		// Heading
		JLabel headingLabel = new JLabel("Please select an invitation type:");
		headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panel.add(headingLabel, "cell 1 0 2 1,alignx left,gapx 5,aligny top");

		// Description paragraph
		JLabel paragraphLabel = new JLabel(
				"<html><p>You can send invitations to your guests through multiple mediums, make your choice right now, <br />and start inviting your guests for free!\r\n<p></html>");
		paragraphLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		panel.add(paragraphLabel, "flowx, cell 1 1 2 1,gapx 5,aligny top,gapy 80");

		// Background icon
		JLabel invtationBackground = new JLabel("");
		invtationBackground.setIcon(new ImageIcon(ImageHelper.class.getResource("invitationIcon.png")));
		panel.add(invtationBackground, "cell 3 1 3 1,alignx right");

		// 'Email' button
		JButton emailButton = new JButton("Email");
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
				new CreateInvitationView("Email");
			}
		});
		emailButton.setFocusPainted(false);
		emailButton.setToolTipText("Email");
		emailButton.setIcon(ImageHelper.loadImageIcon("emailIcon.png", "Email", 64, -1, 5));
		panel.add(emailButton, "cell 1 2, grow");

		// 'SMS' button
		JButton smsButton = new JButton("SMS");
		smsButton.setIcon(ImageHelper.loadImageIcon("smsIcon.png", "Email", 64, -1, 5));
		smsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
				new CreateInvitationView("SMS");
			}
		});
		panel.add(smsButton, "cell 2 2, grow");

		JLabel lblOr = new JLabel("or");
		panel.add(lblOr, "cell 3 2, alignx center");

		// 'Both' button
		JButton btnBoth = new JButton("Both");
		btnBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
				new CreateInvitationView("Default");
			}
		});
		panel.add(btnBoth, "cell 4 2, grow");

		// JFrame properties
		setTitle("Select an invitation type...");
		setResizable(false);
		setSize(new Dimension(600, 420));
		setPreferredSize(new Dimension(600, 420));
		setContentPane(panel);
		setLocationRelativeTo(null);// To center the frame
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				LookAndFeelController.setGlobalLookAndFeel();
				new SelectInvitationTypeView();
			}
		});
	}
}
