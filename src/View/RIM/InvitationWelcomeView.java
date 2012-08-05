package View.RIM;

import java.awt.*;

import javax.swing.*;

import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InvitationWelcomeView extends JFrame
{
	private int eventID;
	public InvitationWelcomeView(int eventID)
	{
		this.eventID = eventID;
		JPanel panel = new JPanel(new MigLayout("", "[65.00][170.00px:n,grow][167.00px:n,grow][82.00][172.00][150.00]", "[][216.00px,grow][65.00px,grow]"));
		panel.setBackground(new Color(245, 245, 245));

		// Heading
		JLabel headingLabel = new JLabel("You can send an invitation...");
		headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panel.add(headingLabel, "cell 1 0 2 1,alignx left,gapx 5,aligny top");

		// Description paragraph
		JLabel paragraphLabel = new JLabel(
				"<html><p>You can send an invitation to your guests to notify them about the event and to gather their response (whether they would like to attend) and their preffered meal. Start inviting your guests for free!\r\n<p></html>");
		paragraphLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		panel.add(paragraphLabel, "flowx,cell 1 1 2 1,gapx 5,aligny top,gapy 70");
		

		// Background icon
		JLabel invtationBackground = new JLabel("");
		invtationBackground.setIcon(new ImageIcon(ImageHelper.class.getResource("invitationIcon.png")));
		panel.add(invtationBackground, "cell 3 1 3 1,alignx right");


		// JFrame properties
		setTitle("Would you like to send an invitation to your contacts?");
		setResizable(false);
		setSize(new Dimension(600, 420));
		setPreferredSize(new Dimension(600, 420));
		setContentPane(panel);
		
				// 'Email' button
				JButton emailButton = new JButton("Get started now!");
				emailButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				emailButton.setIconTextGap(10);
				emailButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						showCreateInvitationWindow();
					}
				});
				emailButton.setFocusPainted(false);
				emailButton.setToolTipText("Get started now!");
				emailButton.setIcon(ImageHelper.loadImageIcon("emailIcon.png", "Get started now!", 64, -1, 5));
				panel.add(emailButton, "cell 2 2 2 1,grow");
		setLocationRelativeTo(null);// To center the frame
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void showCreateInvitationWindow()
	{
		new CreateInvitationView(eventID);
		this.dispose();
	}
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				LookAndFeelController.setGlobalLookAndFeel();
				new InvitationWelcomeView(50);
			}
		});
	}
}
