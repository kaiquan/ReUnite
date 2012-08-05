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
import View.CGL.CollectPaymentForm;
import View.CGL.ConsolidateGuestListForm;
import View.MM.AdministrateRIDetails;
import View.MM.LogInForm;
import View.RIM.InvitationResponseView;
import View.SOM.AdministrateServiceOptionManagement;

@SuppressWarnings("serial")
public class GRDashboard extends JFrame
{

	JPanel mainPanel = new JPanel();

	public GRDashboard()
	{
		setBackground(new Color(0, 128, 128));
		mainPanel.setBackground(new Color(102, 153, 255));
		setContentPane(mainPanel);
		mainPanel.setLayout(new MigLayout("", "[-18.00][266.00,grow][385.00,grow][310.00,grow][404.00,grow][404.00,grow][40.00,grow][grow]", "[16.00][211.00,grow][259.00,grow]"));

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				showAdministrateServiceOptionManagement();
			}
		});
		panel.setBackground(new Color(173, 255, 47));
		mainPanel.add(panel, "cell 0 1 3 1,grow");
		panel.setLayout(new MigLayout("", "[517.00]", "[][][][][][][43.00]"));

		JLabel lblInitiateEvent = new JLabel();
		lblInitiateEvent.setText("Administrate Service Options");
		lblInitiateEvent.setForeground(Color.BLACK);
		lblInitiateEvent.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel.add(lblInitiateEvent, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				showAdministrateServiceOptionManagement();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/initiateEvent.png")));
		panel.add(lblNewLabel, "cell 0 3,alignx center,aligny center");

		JLabel profilePicture = new JLabel("");
		profilePicture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showProfile();
			}
		});
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(199, 21, 133));
		mainPanel.add(panel_6, "cell 3 1 4 1,grow");
		panel_6.setLayout(new MigLayout("", "[707.00,grow][707.00,grow][707.00,grow]", "[grow]"));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 215, 0));
		panel_6.add(panel_8, "cell 0 0,grow");
		panel_8.setLayout(new MigLayout("", "[234.00]", "[][][][]"));
		
		JLabel lblConsolidateGuestList = new JLabel("Consolidate Guest List");
		lblConsolidateGuestList.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_8.add(lblConsolidateGuestList, "cell 0 0");
		
		JLabel label_5 = new JLabel("");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showConsolidateGuestListWindow();
			}
		});
		label_5.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/consolidateGuestList.png")));
		panel_8.add(label_5, "cell 0 3,alignx center,aligny center");
		
		JPanel panel_9 = new JPanel();
		panel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCollectPaymentWindow();
			}
		});
		panel_9.setBackground(new Color(255, 215, 0));
		panel_6.add(panel_9, "cell 1 0,grow");
		panel_9.setLayout(new MigLayout("", "[233.00]", "[][][][][][][][]"));
		
		JLabel lblCollect = new JLabel("Collect Payment");
		lblCollect.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_9.add(lblCollect, "cell 0 0");
		
		JLabel label_6 = new JLabel("");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCollectPaymentWindow();
			}
		});
		label_6.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/collectPayment.png")));
		panel_9.add(label_6, "cell 0 7,alignx center,aligny center");
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 215, 0));
		panel_6.add(panel_10, "cell 2 0,grow");
		panel_10.setLayout(new MigLayout("", "[228.00]", "[][][][]"));
		
		JLabel lblCancelEvent = new JLabel("Cancel Event");
		lblCancelEvent.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		panel_10.add(lblCancelEvent, "cell 0 0");
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/cancelEvent.png")));
		panel_10.add(label_7, "cell 0 3,alignx center,aligny center");
		profilePicture.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/RIM/userIcon.png")));
		mainPanel.add(profilePicture, "cell 7 1,alignx left");

		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeWindow();
			}
		});
				
						JPanel panel_1 = new JPanel();
						mainPanel.add(panel_1, "cell 0 2 3 1");
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
										label_1.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/viewEvent.png")));
										panel_1.add(label_1, "cell 0 3,alignx center,aligny center");
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(204, 102, 51));
				mainPanel.add(panel_7, "cell 3 2,grow");
				panel_7.setLayout(new MigLayout("", "[]", "[]"));
				
						JPanel feedbackPanel = new JPanel();
						panel_7.add(feedbackPanel, "cell 0 0");
						feedbackPanel.setBackground(new Color(255, 140, 0));
						feedbackPanel.setLayout(new MigLayout("", "[332.00]", "[][][][][][][][43.00][]"));
						
								JLabel lblFeedbackForm = new JLabel("Feedback form");
								lblFeedbackForm.setForeground(Color.BLACK);
								lblFeedbackForm.setFont(new Font("Segoe UI", Font.PLAIN, 22));
								feedbackPanel.add(lblFeedbackForm, "cell 0 0,alignx left");
								
										JLabel label_2 = new JLabel("");
										label_2.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/feedbackIcon.png")));
										feedbackPanel.add(label_2, "cell 0 4,alignx center,aligny center");
		
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(153, 50, 204));
				panel_2.setLayout(new MigLayout("", "[203.00,grow][8.00,grow]", "[grow][grow][][][][][][43.00][]"));
				mainPanel.add(panel_2, "cell 4 2 3 1,grow");
						
								JPanel panel_5 = new JPanel();
								panel_2.add(panel_5, "cell 0 0 1 9,grow");
								panel_5.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										showAboutWindow();
									}
								});
								panel_5.setBackground(Color.LIGHT_GRAY);
								panel_5.setLayout(new MigLayout("", "[195.00]", "[][][][][][][][][][][][][]"));
								
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
																						label_3.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/aboutIcon.png")));
																						panel_5.add(label_3, "cell 0 11,alignx center,aligny center");
				
						JPanel panel_4 = new JPanel();
						panel_4.setBackground(Color.LIGHT_GRAY);
						panel_2.add(panel_4, "cell 1 0 1 9,grow");
						panel_4.setLayout(new MigLayout("", "[][][][208.00]", "[][][][][][]"));
						
								JLabel lblHelp = new JLabel("User guide");
								lblHelp.setFont(new Font("Segoe UI", Font.PLAIN, 22));
								panel_4.add(lblHelp, "cell 0 0 2 1");
								
										JLabel label_4 = new JLabel("");
										label_4.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/helpIcon.png")));
										panel_4.add(label_4, "cell 1 5 3 1,alignx center,aligny center");
		panel_3.setBackground(new Color(50, 205, 50));
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeWindow();
			}
		});
		mainPanel.add(panel_3, "cell 7 2,grow");
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
		label.setIcon(new ImageIcon(GRDashboard.class.getResource("/Images/logoutIcon.png")));

		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mainPanel.add(lblWelcome, "flowx,cell 1 0 3 1,alignx left");

		// Account.currentUser.getFirstName() + Account.currentUser.getLastName()
		JLabel lblGasdga = new JLabel("Adeel Ateeque");
		lblGasdga.setForeground(Color.WHITE);
		lblGasdga.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mainPanel.add(lblGasdga, "cell 1 0 3 1");

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1300, 600);
		setResizable(false);
	}

	protected void showConsolidateGuestListWindow()
	{
		this.setState(JFrame.ICONIFIED);
		new ConsolidateGuestListForm().getJFrame().setVisible(true);
		
	}
	
	protected void showCollectPaymentWindow()
	{
		this.setState(JFrame.ICONIFIED);
		new CollectPaymentForm().getJFrame().setVisible(true);
		
	}

	protected void disposeWindow()
	{
		new LogInForm().setVisible(true);
		this.dispose();
	}

	protected void showAboutWindow()
	{
		new About();
		
	}

	protected void showAdministrateServiceOptionManagement()
	{
		this.setState(JFrame.ICONIFIED);
		new AdministrateServiceOptionManagement().getJFrame().setVisible(true);
	}

	protected void showViewEventWindow()
	{
		this.setState(JFrame.ICONIFIED);
		new InvitationResponseView();
	}
	
	protected void showProfile()
	{
		this.setState(JFrame.ICONIFIED);
		new AdministrateRIDetails().getJFrame().setVisible(true);
	}

	public static void main(String args[])
	{
		new GRDashboard();
	}
}
