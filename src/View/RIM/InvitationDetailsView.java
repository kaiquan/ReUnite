package View.RIM;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
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
	private JPanel panel_2;
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
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JList<Guest> list;
	private JPanel panel_5;
	private JLabel lblDatetime;
	private JLabel titleText;
	private JLabel dateTimeText;
	private JLabel initiatedByText;
	 
	public InvitationDetailsView(Event event)
	{
		setResizable(false);
		initialize();
		
		setTitle(event.getEventName());
		setSize(857, 707);
		setSize(857, 707);
		setSize(857, 707);
		
		guestResponsePanel = new JPanel();
		guestResponsePanel.setBorder(new TitledBorder(null, "Are you going for this event?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPanel.add(guestResponsePanel, "cell 1 2,alignx left,aligny center");
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPanel.add(tabbedPane, "cell 1 3 3 2,grow");
		
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
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Entertainment", null, panel_2, null);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Ballroom", null, panel_3, null);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Facility", null, panel_4, null);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Meal", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[][]", "[][]"));
		
		lblNewLabel = new JLabel(event.getEventDescription());
		panel_1.add(lblNewLabel, "cell 1 1");
		
		initiatedByText = new JLabel(event.getEventInitiator().getUserName());
		initiatedByText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mainPanel.add(initiatedByText, "cell 1 0,alignx center");
		setSize(857, 707);
		
		
		setVisible(true);
		
	}
	
	public void initialize()
	{
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[55px][872.00px,grow][613.00px][690px,grow]", "[][213.00px,grow][][37.00,grow][223.00,grow]"));
		
		lblInitiatedBy = new JLabel("Initiated by: ");
		mainPanel.add(lblInitiatedBy, "flowx,cell 1 0,gapx 8 0");
		
		lblGuestResponse = new JLabel("Guest response:");
		lblGuestResponse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mainPanel.add(lblGuestResponse, "cell 2 0 2 1,alignx center");
		
		generalInfoPanel = new JPanel();
		mainPanel.add(generalInfoPanel, "cell 1 1,grow");
		generalInfoPanel.setLayout(new MigLayout("", "[104px,grow][9.00][grow][][104px,grow][-83.00px][][][][70.00][65.00]", "[18.00][][25.00][][grow][][12.00,grow][][][][24.00][][16.00][21.00px][grow]"));
		
		JLabel lblTitle = new JLabel("Title:");
		generalInfoPanel.add(lblTitle, "flowx,cell 2 1,aligny top");
		
		titleText = new JLabel("");
		generalInfoPanel.add(titleText, "cell 2 3 9 1");
		
		lblDatetime = new JLabel("Date/Time");
		generalInfoPanel.add(lblDatetime, "cell 2 5");
		
		dateTimeText = new JLabel("");
		generalInfoPanel.add(dateTimeText, "cell 2 7 9 2");
		
		final JLabel profilePicture = new JLabel("");
		generalInfoPanel.add(profilePicture, "flowx,cell 0 0 1 9,alignx left,aligny top");
		profilePicture.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		profilePicture.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 100, -1, 5));
		
		JLabel lblDescription = new JLabel("Description:");
		generalInfoPanel.add(lblDescription, "cell 0 9");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		generalInfoPanel.add(textArea, "cell 0 10 11 5,grow");
		mainPanel.add(new PieChart(null, new Invitation()), "cell 2 1 2 2,grow");
		getContentPane().add(mainPanel);
	}
}
