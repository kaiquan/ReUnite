package View.RIM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import Images.RIM.ImageHelper;
import Model.Event;
import Model.Invitation;
import Model.Membership.Guest;
import Model.RIM.GuestCollection;
import Model.RIM.GuestListModel;
import View.RIM.Components.ChatClientGUI;
import View.RIM.Components.JListGuestListRenderer;
import View.RIM.Components.PieChart;

@SuppressWarnings("serial")
public class InvitationDetailsView extends JDialog
{
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	private JPanel entertainmentTabPanel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel guestTabPanel;
	private JLabel lblNewLabel;
	private JLabel lblGuestResponse;
	private JPanel guestResponsePanel;
	private JButton btnGoing;
	private JButton btnNotGoing;
	private JButton btnNotSure;
	private JPanel generalInfoPanel;
	private JLabel lblInitiatedBy;
	private JEditorPane descriptionText;
	private JScrollPane scrollPane;
	private JList<Guest> list;
	private JPanel panel_5;
	private JLabel lblDatetime;
	private JLabel dateTimeText;
	private JLabel initiatedByText;

	private Event event;
	private JLabel lblPackage;
	private JEditorPane packageText;
	private JLabel lblPackageDescription;
	private JEditorPane packageDescriptionText;

	public InvitationDetailsView(Event event)
	{
		this.event = event;
		initialize();
		setResizable(false);
		setTitle(event.getEventName());
		setSize(857, 707);
		setVisible(true);

	}

	public void initialize()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[55px][872.00px,grow][613.00px][690px,grow]", "[][213.00px,grow][][37.00,grow][223.00,grow]"));
		mainPanel.add(getGuestResponsePanel(), "cell 1 2,alignx left,aligny center");
		mainPanel.add(getTabbedPane(), "cell 1 3 3 2,grow");
		
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
		generalInfoPanel.setLayout(new MigLayout("", "[104px,grow][9.00][146.00,grow][64.00][27.00][104px,grow][][][-83.00px][][][][][70.00][65.00]",
				"[18.00][][][][][][][][][][][][][][][][][][][][][][][25.00][][][grow][][12.00,grow][][][][24.00][][16.00][21.00px][26.00,grow]"));

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
		packageText.setBackground(SystemColor.menu);
		packageScrollPane.setViewportView(packageText);
		generalInfoPanel.add(packageScrollPane, "flowx,cell 5 2 10 1,growx");

		JLabel lblTitle = new JLabel("Title:");
		generalInfoPanel.add(lblTitle, "flowx,cell 2 4,aligny top");

		lblPackageDescription = new JLabel("Package description");
		generalInfoPanel.add(lblPackageDescription, "cell 5 4");

		JScrollPane titleScrollPane = new JScrollPane();
		titleScrollPane.setBorder(null);
		titleScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		titleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		JEditorPane titleText = new JEditorPane();
		titleText.setBackground(SystemColor.menu);
		titleText.setText(event.getEventName());
		titleScrollPane.setViewportView(titleText);
		generalInfoPanel.add(titleScrollPane, "cell 2 5 2 9,grow");

		JScrollPane packageDescriptionPane = new JScrollPane();
		packageDescriptionPane.setBorder(null);
		packageDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		packageDescriptionPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		packageDescriptionText = new JEditorPane();
		packageDescriptionText.setBackground(SystemColor.menu);
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
		descriptionText.setBackground(SystemColor.menu);
		descriptionText.setText(event.getEventDescription());
		descriptionText.setEditable(false);
		descriptionTextPane.setViewportView(descriptionText);
		generalInfoPanel.add(descriptionTextPane, "cell 0 15 15 22,alignx left,growy");

		lblDatetime = new JLabel("Date/Time");
		generalInfoPanel.add(lblDatetime, "cell 2 1");

		dateTimeText = new JLabel(event.getEventDate() + " at " + event.getEventTime());
		generalInfoPanel.add(dateTimeText, "cell 2 2,aligny top");

		mainPanel.add(new PieChart(null, new Invitation()), "cell 2 1 2 2,grow");
		getContentPane().add(mainPanel);
	}

	private JPanel getGuestResponsePanel()
	{
		guestResponsePanel = new JPanel();
		guestResponsePanel.setBorder(new TitledBorder(null, "Are you going for this event?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestResponsePanel.setLayout(new MigLayout("", "[96.00px][95.00px][]", "[]"));

		btnGoing = new JButton("Going");
		btnGoing.setPreferredSize(new Dimension(90, 50));
		guestResponsePanel.add(btnGoing, "flowx,cell 0 0 2 1,alignx left,aligny top");

		btnNotGoing = new JButton("Not going");
		btnNotGoing.setPreferredSize(new Dimension(90, 50));
		guestResponsePanel.add(btnNotGoing, "cell 1 0,alignx left,aligny top");

		btnNotSure = new JButton("Not sure");
		btnNotSure.setPreferredSize(new Dimension(90, 50));
		guestResponsePanel.add(btnNotSure, "cell 2 0,alignx left,aligny top");

		return guestResponsePanel;
	}

	private JTabbedPane getTabbedPane()
	{
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		guestTabPanel = new JPanel();
		tabbedPane.addTab("Guests", null, guestTabPanel, null);
		guestTabPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		panel_5 = new JPanel(new MigLayout("", "[1060.00][804px]", "[353px]"));
		guestTabPanel.add(panel_5, "cell 0 0,grow");

		scrollPane = new JScrollPane();
		GuestCollection collection = new GuestCollection();
		ListModel<Guest> listModel = new GuestListModel(collection);
		list = new JList<Guest>(listModel);
		list.setFixedCellWidth(100);
		list.setFixedCellHeight(100);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setCellRenderer(new JListGuestListRenderer());
		scrollPane.setViewportView(list);
		panel_5.add(scrollPane, "cell 0 0,grow");

		ChatClientGUI chatBox = new ChatClientGUI();
		panel_5.add(chatBox, "cell 1 0,grow");

		entertainmentTabPanel = new JPanel();
		tabbedPane.addTab("Entertainment", null, entertainmentTabPanel, null);

		panel_3 = new JPanel();
		tabbedPane.addTab("Ballroom", null, panel_3, null);

		panel_4 = new JPanel();
		tabbedPane.addTab("Facility", null, panel_4, null);

		panel_1 = new JPanel();
		tabbedPane.addTab("Meal", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[][]", "[][]"));

		lblNewLabel = new JLabel(event.getEventDescription());
		panel_1.add(lblNewLabel, "cell 1 1");

		return tabbedPane;
	}
}
