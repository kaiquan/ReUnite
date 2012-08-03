package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import View.MM.AdministrateRIPersonalDetails;
import View.RIM.InvitationResponseView;
import View.SOM.InitiateEventForm;

@SuppressWarnings("serial")
public class RIDashboard extends JFrame
{

	JPanel mainPanel = new JPanel();

	public RIDashboard()
	{
		setBackground(new Color(0, 128, 128));
		mainPanel.setBackground(new Color(102, 153, 255));
		setContentPane(mainPanel);
		mainPanel.setLayout(new MigLayout("", "[-18.00][391.00,grow][399.00,grow][grow]", "[16.00][211.00][259.00]"));

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				showInitiateEventWindow();
			}
		});
		panel.setBackground(new Color(173, 255, 47));
		mainPanel.add(panel, "cell 0 1 2 1,grow");
		panel.setLayout(new MigLayout("", "[340.00]", "[][][][][][][43.00]"));

		JLabel lblInitiateEvent = new JLabel("Initiate event");
		lblInitiateEvent.setForeground(Color.BLACK);
		lblInitiateEvent.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel.add(lblInitiateEvent, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				showInitiateEventWindow();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/initiateEvent.png")));
		panel.add(lblNewLabel, "cell 0 3,alignx center,aligny center");

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				showViewEventWindow();
			}
		});
		panel_1.setBackground(new Color(220, 20, 60));
		panel_1.setLayout(new MigLayout("", "[344.00]", "[][][][][][][43.00]"));
		mainPanel.add(panel_1, "cell 2 1,grow");

		JLabel lblViewEvents = new JLabel("View events");
		lblViewEvents.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				showViewEventWindow();
			}
		});
		lblViewEvents.setForeground(Color.BLACK);
		lblViewEvents.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_1.add(lblViewEvents, "cell 0 0,alignx left,aligny bottom");

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				showViewEventWindow();
			}
		});
		label_1.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/viewEvent.png")));
		panel_1.add(label_1, "cell 0 3,alignx center,aligny center");

		JLabel profilePicture = new JLabel("");
		profilePicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showProfile();
			}
		});
		profilePicture.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/RIM/userIcon.png")));
		mainPanel.add(profilePicture, "cell 3 1,alignx center");

		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setBackground(new Color(255, 140, 0));
		feedbackPanel.setLayout(new MigLayout("", "[332.00]", "[][][][][][][][43.00][]"));
		mainPanel.add(feedbackPanel, "cell 0 2 2 1,grow");

		JLabel lblFeedbackForm = new JLabel("Feedback form");
		lblFeedbackForm.setForeground(Color.BLACK);
		lblFeedbackForm.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		feedbackPanel.add(lblFeedbackForm, "cell 0 0,alignx left");

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/feedbackIcon.png")));
		feedbackPanel.add(label_2, "cell 0 4,alignx center,aligny center");

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 50, 204));
		panel_2.setLayout(new MigLayout("", "[120.00,grow][8.00,grow][grow]", "[grow][grow][][][][][][43.00][]"));
		mainPanel.add(panel_2, "cell 2 2,grow");

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_4, "cell 1 0 2 9,grow");
		panel_4.setLayout(new MigLayout("", "[][][][]", "[][][][][][]"));

		JLabel lblHelp = new JLabel("User guide");
		lblHelp.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_4.add(lblHelp, "cell 0 0 2 1");

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/helpIcon.png")));
		panel_4.add(label_4, "cell 1 5 3 1,alignx center,aligny center");

		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showAboutWindow();
			}
		});
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_5, "cell 0 0 1 9,grow");
		panel_5.setLayout(new MigLayout("", "[154.00]", "[][][][][][][][][]"));

		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_5.add(lblAbout, "cell 0 0");

		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showAboutWindow();
			}
		});
		label_3.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/aboutIcon.png")));
		panel_5.add(label_3, "cell 0 8,alignx center,aligny bottom");

		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeWindow();
			}
		});
		panel_3.setBackground(new Color(50, 205, 50));
		mainPanel.add(panel_3, "cell 3 2,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[][259.00,grow][]"));

		JLabel lblNewLabel_1 = new JLabel("Logout");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_3.add(lblNewLabel_1, "cell 0 2,alignx right");

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeWindow();
			}
		});
		panel_3.add(label, "cell 0 1,alignx center,aligny center");
		label.setBackground(new Color(50, 205, 50));
		label.setIcon(new ImageIcon(RIDashboard.class.getResource("/Images/logoutIcon.png")));

		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mainPanel.add(lblWelcome, "flowx,cell 1 0 2 1,alignx left");

		// Account.currentUser.getFirstName() + Account.currentUser.getLastName()
		JLabel lblGasdga = new JLabel("Adeel Ateeque");
		lblGasdga.setForeground(Color.WHITE);
		lblGasdga.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mainPanel.add(lblGasdga, "cell 1 0");

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(963, 565);
		setResizable(false);
	}

	protected void disposeWindow()
	{
		this.setState(JFrame.ICONIFIED);;
		
	}

	protected void showAboutWindow()
	{
		new About();
		
	}

	protected void showInitiateEventWindow()
	{
		this.setState(JFrame.ICONIFIED);;
		new InitiateEventForm().getJFrame().setVisible(true);
	}

	protected void showViewEventWindow()
	{
		this.setState(JFrame.ICONIFIED);;
		new InvitationResponseView();
	}
	
	protected void showProfile()
	{
		this.setState(JFrame.ICONIFIED);;
		new AdministrateRIPersonalDetails().getJFrame().setVisible(true);
	}

	public static void main(String args[])
	{
		new RIDashboard();
	}
}
