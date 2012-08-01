package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import View.RIM.InvitationResponseView;
import View.RIM.InvitationWelcomeView;

import java.awt.SystemColor;

public class RIDashboard extends JFrame{

	JPanel mainPanel = new JPanel();
	
	public RIDashboard() 
	{
		setBackground(new Color(0, 128, 128));
		mainPanel.setBackground(new Color(0, 139, 139));
		setContentPane(mainPanel);
		mainPanel.setLayout(new MigLayout("", "[-18.00][391.00,grow][399.00,grow][grow]", "[16.00][211.00][259.00]"));
		
		JLabel lblWelcome = new JLabel("Welcome,");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mainPanel.add(lblWelcome, "cell 0 0 2 1,alignx left");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 255, 47));
		mainPanel.add(panel, "cell 0 1 2 1,grow");
		panel.setLayout(new MigLayout("", "[301.00]", "[][][][][][][43.00]"));
		
		JLabel lblInitiateEvent = new JLabel("Initiate event");
		lblInitiateEvent.setForeground(Color.BLACK);
		lblInitiateEvent.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		panel.add(lblInitiateEvent, "cell 0 0,alignx center,aligny bottom");
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new InvitationWelcomeView();
			}
		});
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setLayout(new MigLayout("", "[301.00]", "[][][][][][][43.00]"));
		mainPanel.add(panel_1, "cell 2 1,grow");
		
		JLabel lblViewEvents = new JLabel("View events");
		lblViewEvents.setForeground(Color.BLACK);
		lblViewEvents.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		panel_1.add(lblViewEvents, "cell 0 0,alignx center,aligny bottom");
		
		JLabel profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/RIM/userIcon.png")));
		mainPanel.add(profilePicture, "cell 3 1,alignx center");
		
		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setBackground(new Color(255, 140, 0));
		feedbackPanel.setLayout(new MigLayout("", "[301.00]", "[][][][][][][43.00][]"));
		mainPanel.add(feedbackPanel, "cell 0 2 2 1,grow");
		
		JLabel lblFeedbackForm = new JLabel("Feedback form");
		lblFeedbackForm.setForeground(Color.BLACK);
		lblFeedbackForm.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
		feedbackPanel.add(lblFeedbackForm, "cell 0 0,alignx center");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 50, 204));
		panel_2.setLayout(new MigLayout("", "[301.00]", "[][][][][][][43.00][]"));
		mainPanel.add(panel_2, "cell 2 2,grow");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(50, 205, 50));
		mainPanel.add(panel_3, "cell 3 2,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[259.00,grow]"));
		
		JLabel label = new JLabel("");
		panel_3.add(label, "cell 0 0,alignx center,aligny center");
		label.setBackground(new Color(50, 205, 50));
		label.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/logoutIcon.png")));
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(963, 565);
		setResizable(false);
	}
	
	public static void main(String args[])
	{
		new RIDashboard();
	}
}
