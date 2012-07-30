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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import Images.RIM.ImageHelper;
import Model.Event;
import Model.RIM.*;
import Model.Invitation;
import Model.Membership.Guest;
import Model.RIM.ListModels.SortedListModel;
import View.RIM.Components.JListGuestListRenderer;
import View.RIM.Components.PieChart;

@SuppressWarnings("serial")
public class InvitationDetailsView extends JDialog
{
	private JPanel panel;
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
	private JLabel lblInitiator_1;
	private JLabel lblNewLabel_1;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JList<Guest> list;
	 
	public InvitationDetailsView(Event event)
	{
		initialize();
		
		setTitle(event.getEventName());
		setSize(857, 707);
		setSize(857, 707);
		setSize(857, 707);
		
		guestResponsePanel = new JPanel();
		guestResponsePanel.setBorder(new TitledBorder(null, "Are you going for this event?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(guestResponsePanel, "cell 1 2 2 1,alignx left,aligny center");
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
		panel.add(tabbedPane, "cell 1 3 4 2,grow");
		
		guestTabPanel = new JPanel();
		tabbedPane.addTab("Guests", null, guestTabPanel, null);
		guestTabPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane = new JScrollPane();
		guestTabPanel.add(scrollPane, "cell 0 0,grow");
		
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
		setSize(857, 707);
		
		
		
		setVisible(true);
		
	}
	
	public void initialize()
	{
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[55px][445.00px,grow][311.00px,grow][69.00px][690px]", "[][213.00px,grow][][37.00,grow][223.00,grow]"));
		
		lblInitiator_1 = new JLabel("Initiated by:");
		panel.add(lblInitiator_1, "cell 1 0,gapx 8 0");
		
		lblGuestResponse = new JLabel("Guest response:");
		lblGuestResponse.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(lblGuestResponse, "cell 4 0,alignx center");
		
		generalInfoPanel = new JPanel();
		panel.add(generalInfoPanel, "cell 1 1 2 1,grow");
		generalInfoPanel.setLayout(new MigLayout("", "[104px,grow][1px][][][][70.00][65.00]", "[20.00][4.00][8.00][17.00][33.00][21.00px][grow]"));
		
		JLabel lblDescription = new JLabel("Description:");
		generalInfoPanel.add(lblDescription, "cell 0 5");
		
		final JLabel profilePicture = new JLabel("");
		generalInfoPanel.add(profilePicture, "cell 0 0 1 5,alignx left,aligny top");
		profilePicture.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		profilePicture.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 100, -1, 5));
		
		JLabel lblTitle = new JLabel("Title:");
		generalInfoPanel.add(lblTitle, "flowx,cell 2 1 1 2,aligny top");
		
		lblNewLabel_1 = new JLabel("General info:");
		generalInfoPanel.add(lblNewLabel_1, "cell 2 0 1 5,aligny top");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		generalInfoPanel.add(textArea, "cell 0 6 7 1,grow");
		panel.add(new PieChart(null, new Invitation()), "cell 3 1 2 2,grow");
		getContentPane().add(panel);
	}
	

}
