package View.RIM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import Images.RIM.ImageHelper;
import Model.Event;
import Model.Membership.Account;
import Model.Membership.Guest;
import Model.RIM.Chat.ClientGUI;
import View.RIM.Components.EntertainmentPanel;
import View.RIM.Components.PieChart;

@SuppressWarnings("serial")
public class InvitationDetailsView extends JDialog
{
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private JTabbedPane mealSubTabs;
	private EntertainmentPanel entertainmentTab;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblGuestResponse;
	private JPanel guestResponsePanel;
	private JButton btnGoing;
	private JButton btnNotGoing;
	private JButton btnNotSure;
	private JPanel generalInfoPanel;
	private JLabel lblInitiatedBy;
	private JEditorPane descriptionText;
	private JLabel lblDatetime;
	private JLabel dateTimeText;
	private JLabel initiatedByText;
	private JLabel lblPackage;
	private JEditorPane packageText;
	private JLabel lblPackageDescription;
	private JEditorPane packageDescriptionText;
	
	ClientGUI chatBox;

	private Event event;

	public InvitationDetailsView(Event event)
	{
		this.event = event;
		initialize();
		setResizable(false);
		setTitle(event.getEventName());
		setSize(857, 707);
		setVisible(true);
		addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
				if (chatBox != null)
				{
					chatBox.logout();
				}

			}

			@Override
			public void windowClosing(WindowEvent arg0)
			{

			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{

			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{

			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{

			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{

			}

		});

	}

	public void initialize()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[55px][872.00px,grow][613.00px][690px,grow]", "[][213.00px,grow][][37.00,grow][223.00,grow]"));
		if (Account.currentUser.getType().equalsIgnoreCase("Guest"))
		{
			mainPanel.add(getGuestResponsePanel(), "cell 1 2,alignx left,aligny center");
		}
		mainPanel.add(getTabbedPane(), "cell 1 3 3 2,grow");

		// ClientGUI chatBox = new ClientGUI(Account.currentUser.getUserName(), event.getID());
		chatBox = new ClientGUI("Adeel", 3);
		tabbedPane.addTab("Guests", chatBox);

		lblInitiatedBy = new JLabel("Initiated by: ");
		mainPanel.add(lblInitiatedBy, "flowx,cell 1 0,gapx 8 0");

		initiatedByText = new JLabel(event.getEventInitiator().getUserName());
		initiatedByText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mainPanel.add(initiatedByText, "cell 1 0,alignx center");

		lblGuestResponse = new JLabel("Guest response:");
		lblGuestResponse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mainPanel.add(lblGuestResponse, "cell 2 0 2 1,alignx center");

		generalInfoPanel = new JPanel();
		generalInfoPanel.setBorder(null);
		mainPanel.add(generalInfoPanel, "cell 1 1,grow");
		generalInfoPanel.setLayout(new MigLayout("", "[104px,grow][9.00][146.00,grow][64.00][27.00][104px,grow][][][-83.00px][][][][][70.00][65.00]", "[18.00][][][][][][][][][][][][][][][][][][][][][][][25.00][][][grow][][12.00,grow][][][][24.00][][16.00][21.00px][26.00,grow]"));

		final JLabel profilePicture = new JLabel("");
		generalInfoPanel.add(profilePicture, "cell 0 0 1 14,alignx left,aligny top");
		profilePicture.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		profilePicture.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 100, -1, 5));

		lblPackage = new JLabel("Package:");
		generalInfoPanel.add(lblPackage, "cell 5 0 1 2,aligny top");

		JScrollPane packageScrollPane = new JScrollPane();
		packageScrollPane.setBorder(null);
		packageScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		packageText = new JEditorPane();
		packageText.setText(event.getEventPackage().getPackageTitle());
		packageText.setBackground(SystemColor.control);
		packageScrollPane.setViewportView(packageText);
		generalInfoPanel.add(packageScrollPane, "flowx,cell 5 2 10 2,growx");

		JLabel lblTitle = new JLabel("Title:");
		generalInfoPanel.add(lblTitle, "flowx,cell 2 4,aligny top");

		lblPackageDescription = new JLabel("Package description");
		generalInfoPanel.add(lblPackageDescription, "cell 5 4");

		JScrollPane titleScrollPane = new JScrollPane();
		titleScrollPane.setBorder(null);
		titleScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		titleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		JEditorPane titleText = new JEditorPane();
		titleText.setBackground(SystemColor.control);
		titleText.setText(event.getEventName());
		titleScrollPane.setViewportView(titleText);
		generalInfoPanel.add(titleScrollPane, "cell 2 5 2 9,grow");

		JScrollPane packageDescriptionPane = new JScrollPane();
		packageDescriptionPane.setBorder(null);
		packageDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		packageDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		packageDescriptionText = new JEditorPane();
		packageDescriptionText.setBackground(SystemColor.control);
		packageDescriptionText.setText(event.getEventPackage().getPackageDescription());
		packageDescriptionPane.setViewportView(packageDescriptionText);
		generalInfoPanel.add(packageDescriptionPane, "cell 5 5 10 10,grow");

		JLabel lblDescription = new JLabel("Description:");
		generalInfoPanel.add(lblDescription, "flowx,cell 0 14");

		JScrollPane descriptionTextPane = new JScrollPane();
		descriptionTextPane.setBorder(null);
		descriptionTextPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionTextPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		descriptionText = new JEditorPane();
		descriptionText.setBackground(SystemColor.control);
		descriptionText.setText(event.getEventDescription());
		descriptionText.setEditable(false);
		descriptionTextPane.setViewportView(descriptionText);
		generalInfoPanel.add(descriptionTextPane, "cell 0 15 15 22,alignx left,growy");

		lblDatetime = new JLabel("Date/Time");
		generalInfoPanel.add(lblDatetime, "cell 2 1");

		dateTimeText = new JLabel(event.getEventDate() + " at " + event.getEventTime());
		generalInfoPanel.add(dateTimeText, "cell 2 2,aligny top");

		int attending = event.getEventInvitation().GET_ATTENDING_GUESTS_COUNT(event.getEventInvitation().getInvitationID());
		int notAttending = event.getEventInvitation().GET_NOT_ATTENDING_GUESTS_COUNT(event.getEventInvitation().getInvitationID());
		int notSure = event.getEventInvitation().GET_NOT_SURE_GUESTS_COUNT(event.getEventInvitation().getInvitationID());
		int totalGuests = event.getEventInvitation().GET_GUESTS_COUNT(event.getEventInvitation().getInvitationID());
		
		mainPanel.add(new PieChart(attending, notAttending, notSure, new Double(totalGuests)), "cell 2 1 2 2,grow");
		getContentPane().add(mainPanel);
	}

	private JPanel getGuestResponsePanel()
	{	
		guestResponsePanel = new JPanel();
		guestResponsePanel.setBorder(new TitledBorder(null, "Are you going for this event?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestResponsePanel.setLayout(new MigLayout("", "[96.00px][95.00px][]", "[]"));

		btnGoing = new JButton("Going");
		btnGoing.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(new Guest().SET_RESPONSE(Account.currentUser.getUserName(), event.getEventInvitation().getInvitationID(), "Attending"))
				{
					JOptionPane.showMessageDialog(null, "Your attendance status has been successfully updated");
				}
				btnGoing.setEnabled(false);
				if(btnNotGoing.isEnabled()==false)
				{
					btnNotGoing.setEnabled(true);
				}
				if(btnNotSure.isEnabled()==false)
				{
					btnNotSure.setEnabled(true);
				}
			}
		});
		
		btnGoing.setPreferredSize(new Dimension(90, 50));
		guestResponsePanel.add(btnGoing, "flowx,cell 0 0 2 1,alignx left,aligny top");

		btnNotGoing = new JButton("Not going");
		btnNotGoing.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(new Guest().SET_RESPONSE(Account.currentUser.getUserName(), event.getEventInvitation().getInvitationID(), "Not Attending"))
				{
					JOptionPane.showMessageDialog(null, "Your attendance status has been successfully updated");
				}
				btnNotGoing.setEnabled(false);
				if(btnGoing.isEnabled()==false)
				{
					btnGoing.setEnabled(true);
				}
				if(btnNotSure.isEnabled()==false)
				{
					btnNotSure.setEnabled(true);
				}
				
			}
		});
		btnNotGoing.setPreferredSize(new Dimension(90, 50));
		guestResponsePanel.add(btnNotGoing, "cell 1 0,alignx left,aligny top");

		btnNotSure = new JButton("Not sure");
		btnNotSure.setPreferredSize(new Dimension(90, 50));
		btnNotSure.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(new Guest().SET_RESPONSE(Account.currentUser.getUserName(), event.getEventInvitation().getInvitationID(), "Not Sure"))
				{
					JOptionPane.showMessageDialog(null, "Your attendance status has been successfully updated");
				}
				btnNotSure.setEnabled(false);
				if(btnGoing.isEnabled()==false)
				{
					btnGoing.setEnabled(true);
				}
				if(btnNotGoing.isEnabled()==false)
				{
					btnNotGoing.setEnabled(true);
				}
				
			}
		});
		guestResponsePanel.add(btnNotSure, "cell 2 0,alignx left,aligny top");

		String currentGuestResponse = new Guest().GET_RESPONSE(Account.currentUser.getUserName(), event.getEventInvitation().getInvitationID());
		
		if(currentGuestResponse.equalsIgnoreCase("Attending"))
		{
			btnGoing.setEnabled(false);
		}
		else if(currentGuestResponse.equalsIgnoreCase("Not Attending"))
		{
			btnNotGoing.setEnabled(false);
		}
		else if(currentGuestResponse.equalsIgnoreCase("Not Sure"))
		{
			btnNotSure.setEnabled(false);
		}
		
		if(!event.getEventStatus().equalsIgnoreCase("Pending"))
		{
			btnGoing.setEnabled(false);
			btnNotGoing.setEnabled(false);
			btnNotSure.setEnabled(false);
		}
		
		
		
		return guestResponsePanel;
	}

	private JTabbedPane getTabbedPane()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		entertainmentTab = new EntertainmentPanel(event.getEventPackage().getEntertainment());
		tabbedPane.addTab("Entertainment", null, entertainmentTab, null);

		panel_3 = new JPanel();
		tabbedPane.addTab("Ballroom", null, panel_3, null);

		panel_4 = new JPanel();
		tabbedPane.addTab("Facility", null, panel_4, null);

		mealSubTabs = new JTabbedPane();
		mealSubTabs.setTabPlacement(JTabbedPane.BOTTOM);
//
//		if (event.getEventPackage().getMeals().size() > 0)
//		{
//			Iterator<Meal> mealIterator = event.getEventPackage().getMeals().iterator();
//			int i = 1;
//			while (mealIterator.hasNext())
//			{
//				Meal meal = mealIterator.next();
//				mealSubTabs.addTab("Meal Option "+i, null, getMealChoicePanel(meal), null);
//				i++;
//			}
//		}
		
		tabbedPane.addTab("Meal", null, mealSubTabs, null);
		return tabbedPane;
	}

//	public JPanel getMealChoicePanel(Meal meal)
//	{
//		JPanel mealPanel = new JPanel();
//
//		mealPanel.setLayout(new FlowLayout());
//		
//		JLabel mealPricePerHead = new JLabel(Double.toString(meal.getMealPricePerHead()));
//		mainPanel.add(mealPricePerHead);
//		JLabel mealDescription = new JLabel(meal.getMealDescription());
//		mainPanel.add(mealDescription);
//		JLabel mealTitle = new JLabel(meal.getMealTitle());
//		mainPanel.add(mealTitle);
//		JLabel mealType = new JLabel(meal.getMealType());
//		mainPanel.add(mealType);
//		
//		for(MealMenu mealItems:meal.getMealItems())
//		{
//			System.out.println(mealItems.getMealMenuName());
//			System.out.println(mealItems.getMealMenuDescription());
//			System.out.println(mealItems.getMealMenuPrice());
//		}
//
//		return mealPanel;
//	}
	
}
