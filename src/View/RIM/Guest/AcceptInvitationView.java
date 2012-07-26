package View.RIM.Guest;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JLabel;

public class AcceptInvitationView extends JFrame {
	public AcceptInvitationView() {
		setTitle("Event invitation");
		getContentPane().setLayout(new MigLayout("", "[19.00][][43.00][39.00][grow][][][grow][][][45.00,grow][]", "[][][][][][][][][58.00]"));
		
		JLabel lblTitle = new JLabel("Title:");
		getContentPane().add(lblTitle, "cell 0 0");
		
		JLabel lblDescription = new JLabel("Description:");
		getContentPane().add(lblDescription, "cell 0 2");
		
		JLabel lblDatetime = new JLabel("Date/Time:");
		getContentPane().add(lblDatetime, "cell 0 4");
		
		JLabel lblInitiator = new JLabel("Initiator:");
		getContentPane().add(lblInitiator, "cell 0 6");
		
		JButton btnGoing = new JButton("Going");
		btnGoing.setPreferredSize(new Dimension(75, 0));
		getContentPane().add(btnGoing, "cell 0 8 3 1,grow");
		
		JButton btnNotGoing = new JButton("Not going");
		getContentPane().add(btnNotGoing, "cell 3 8 5 1,grow");
		
		JButton btnNotSure = new JButton("Not sure");
		getContentPane().add(btnNotSure, "cell 8 8 4 1,grow");
	}

}
