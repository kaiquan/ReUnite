package View.RIM.Components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import Images.RIM.ImageHelper;

import java.awt.event.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GuestActionsFooter extends JPanel
{
	public GuestActionsFooter()
	{	super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(255, 204, 51));
		setLayout(new MigLayout("", "[3.00][86.00,grow][103.00][-34.00][105.00]", "[64px]"));
		
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Next button clicked!");
			}
		});
		label.setIcon(new ImageIcon(ImageHelper.loadImage("profileIcon.png", "Testing").getScaledInstance(-1, 75, 5)));
		add(label, "flowy,cell 0 0,alignx left,aligny center");
		
		JLabel lblSettings = new JLabel("Your profile");
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setFont(new Font("Segoe UI", Font.BOLD, 18));
		add(lblSettings, "cell 1 0,alignx left,aligny center");
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(ImageHelper.loadImage("invitationIcon.png", "Testing").getScaledInstance(-1, 75, 5)));
		add(label_2, "flowy,cell 2 0,alignx center,aligny center");
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ImageHelper.loadImage("viewInboxIcon.png", "Testing").getScaledInstance(75, -1, 5)));
		add(label_1, "flowy,cell 4 0,alignx center");
		
		JLabel lblViewInvitations = new JLabel("View invitations");
		add(lblViewInvitations, "cell 2 0,alignx center");
		
		JLabel lblNewLabel = new JLabel("View messages");
		add(lblNewLabel, "cell 4 0,alignx center");
	}
}
