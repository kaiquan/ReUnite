package View.RIM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.ContactImporter;
import Controller.RIM.CreateInvitationController;
import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import Model.Membership.Guest;
import View.RIM.Components.ImageUploader;
import View.RIM.Components.NavigationFooter;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.DateCellEditor;
import View.RIM.Components.Table.IconEditor;
import View.RIM.Components.Table.IconRenderer;
import View.RIM.Components.Table.TextAreaEditor;
import View.RIM.Components.Table.TextAreaRenderer;

@SuppressWarnings("serial")
public class CreateInvitationView extends JFrame
{
	// Controller
	private CreateInvitationController controller = new CreateInvitationController();

	// The panel that holds the cards
	private JPanel mainPanel;

	private JTable table;
	
	@SuppressWarnings("unused")
	private int eventID;

	public CreateInvitationView(int eventID)
	{
		initialize();
		this.eventID = eventID;
	}
	
	
	private void initialize()
	{
		setTitle("Create invitation [Step 1 of 2]");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(1000, 600));
		setContentPane(getContentPane());
		setVisible(true);
	}

	public JPanel getContentPane()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());

		// Add the two cards
		mainPanel.add(getStep1());
		mainPanel.add(getStep2());

		return mainPanel;
	}

	public JPanel getStep1()
	{
		JPanel step1 = new JPanel();
		step1.setLayout(new MigLayout("", "[403.00,grow]", "[52.00][3.00][341.00,grow,bottom]"));

		// The heading panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new MigLayout("", "[-20.00px][284px][]", "[125px]"));

		JLabel headingLabel = new JLabel("Please specify your contacts...");
		headingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		headerPanel.add(headingLabel, "cell 1 0,alignx center,aligny center");

		step1.add(headerPanel, "cell 0 0,alignx center,growy");

		JLabel headerIcon = new JLabel("");
		headerIcon.setIcon(ImageHelper.loadImageIcon("selectContacts.png", "background", -1, 50, 5));

		headerPanel.add(headerIcon, "cell 2 0,aligny top");

		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		step1.add(actionPanel, "flowx,cell 0 1,alignx center,aligny bottom");
		actionPanel.setBackground(new Color(255, 204, 51));
		actionPanel.setLayout(new MigLayout("", "[119px]", "[33px][]"));

		JButton plusButton = new JButton("");
		plusButton.setIcon(new ImageIcon(ImageHelper.class.getResource("plus.png")));
		plusButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.addRow();
			}
		});
	
		actionPanel.add(plusButton, "flowx,cell 0 0,alignx center,aligny bottom");

		JButton minusButton = new JButton("");
		minusButton.setIcon(new ImageIcon(ImageHelper.class.getResource("minus.png")));
		minusButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				controller.deleteRow(table.getSelectedRows());
			}
		});
		actionPanel.add(minusButton, "cell 0 0,alignx center,aligny top");

		JButton button = new JButton("");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				controller.searchUsers();
			}
		});
		button.setIcon(ImageHelper.loadImageIcon("browseIcon.png", "", -1, 24, 5));
		actionPanel.add(button, "cell 0 0");

		JLabel lblOrImportFrom = new JLabel("   or import from:   ");
		lblOrImportFrom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		actionPanel.add(lblOrImportFrom, "cell 0 0,alignx left,aligny center");

		JButton hotmailImportButton = new JButton("Hotmail");
		hotmailImportButton.setIcon(ImageHelper.loadImageIcon("hotmailIcon.png", "Import from Hotmail", -1, 24, 5));
		hotmailImportButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ArrayList<Guest> tempList = new ContactImporter("Hotmail").getContacts();
				controller.addRows(tempList);
			}
		});
		actionPanel.add(hotmailImportButton, "cell 0 0,alignx center,aligny top");

		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setViewportView(prepareTable());

		step1.add(tableScrollPane, "cell 0 2,grow");

		NavigationFooter navigationFooter = new NavigationFooter("step1")
		{
			@Override
			public void navigationButtonClicked()
			{
				ArrayList<Integer> invalidRows = controller.validateData();
				if (invalidRows.size() == 0)
				{
					switchPanels();
				}
				else
				{
					String message = "Please fill out all the compulsary fields\nsuch as First name, Last name and Email\n\nPlease check the following rows: \n";
					for(Integer row : invalidRows)
					{
						message +=  "Row " + row + "\n";
					}
					JOptionPane.showMessageDialog(this, message, "Please check your contacts' information", JOptionPane.WARNING_MESSAGE);
				}
			}

		};
		step1.add(navigationFooter, "south,growx,aligny bottom");

		return step1;
	}

	private JTable prepareTable()
	{
		table = new JTable(controller.getTableModel());
		table.setRowHeight(75);
		table.getTableHeader().setReorderingAllowed(false);
		controller.getTableModel().getTableSorter().setTableHeader(table.getTableHeader());
		table.setModel(controller.getTableModel().getTableSorter());

		setRenderers();

		AutoResizeTableColumns resizer = new AutoResizeTableColumns(table, controller.getTableModel(), 32, true, true, new boolean[table.getColumnCount()]);
		controller.getTableModel().addTableModelListener(resizer);

		return table;
	}

	private void setRenderers()
	{
		// Align text to center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 18));
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(new TextAreaRenderer());
		table.getColumnModel().getColumn(7).setCellEditor(new TextAreaEditor());

		// The profile picture cells
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());
		table.getColumnModel().getColumn(0).setCellEditor(new IconEditor());

		table.getColumnModel().getColumn(4).setCellEditor(new DateCellEditor());
	}

	public JPanel getStep2()
	{
		JPanel step2 = new JPanel();

		step2.setLayout(new MigLayout("", "[][609.00,grow][][369.00,grow][]", "[22.00,center][255.00,grow][277.00,grow][-90.00][-15.00][]"));

		JLabel lblAlmostThere = new JLabel("Please confirm your invitation, and upload photos for the event.");
		lblAlmostThere.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		step2.add(lblAlmostThere, "cell 1 0,alignx left,gapx 10 0");

		JLabel lblUploadPhotos = new JLabel("Upload photos");
		lblUploadPhotos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		step2.add(lblUploadPhotos, "flowx,cell 3 0,alignx center,aligny center");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Event details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new MigLayout("", "[][]", "[][][][][]"));

		JLabel lblEventTitle = new JLabel("Title");
		panel.add(lblEventTitle, "cell 0 0");

		JLabel label = new JLabel("");
		panel.add(label, "cell 1 0");

		JLabel lblDescription = new JLabel("Description:");
		panel.add(lblDescription, "cell 0 4");

		ImageUploader uploader = new ImageUploader();
		uploader.setBorder(null);

		step2.add(uploader, "cell 3 1 1 2,gapy 6 0,grow");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panel);
		step2.add(scrollPane, "cell 1 1,grow");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Your message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JEditorPane dtrpnYourMessageHere = new JEditorPane();
		dtrpnYourMessageHere.setPreferredSize(new Dimension(200, 20));
		dtrpnYourMessageHere.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		dtrpnYourMessageHere.setMargin(new Insets(10, 10, 3, 3));
		dtrpnYourMessageHere.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		dtrpnYourMessageHere.setText("I look forward to seeing you at the event, I hope you attend...");
		scrollPane_1.setViewportView(dtrpnYourMessageHere);
		step2.add(scrollPane_1, "cell 1 2,grow");


		NavigationFooter navigationFooter = new NavigationFooter("step2")
		{
			@Override
			public void navigationButtonClicked()
			{
				switchPanels();
			}

			@Override
			public void sendButtonClicked()
			{
				controller.sendInvitation();
			}

		};
		step2.add(navigationFooter, "south,growx,aligny bottom");

		JLabel uploadIcon = new JLabel("");
		uploadIcon.setIcon(ImageHelper.loadImageIcon("uploadImagesIcon.png", "", -1, 50, 5));
		step2.add(uploadIcon, "cell 3 0,aligny bottom,gapy 10 0");

		return step2;
	}

	protected void switchPanels()
	{

		CardLayout cards = (CardLayout) (mainPanel.getLayout());
		cards.next(mainPanel);
	}

	public static void main(String args[])
	{
		LookAndFeelController.setGlobalLookAndFeel();
		new CreateInvitationView(3);
	}
}
