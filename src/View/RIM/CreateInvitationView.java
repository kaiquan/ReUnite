package View.RIM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.ContactImporter;
import Controller.RIM.CreateInvitationViewController;
import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import Model.Membership.Guest;
import View.RIM.Components.NavigationFooter;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.ColorEditor;
import View.RIM.Components.Table.ColorRenderer;
import View.RIM.Components.Table.IconEditor;
import View.RIM.Components.Table.IconRenderer;
import View.RIM.Components.Table.DateCellEditor;


@SuppressWarnings("serial")
public class CreateInvitationView extends JFrame
{
	//Controller
	private CreateInvitationViewController controller = new CreateInvitationViewController();

	// The panel that holds the cards
	private JPanel mainPanel;
	
	private JTable table;

	
	public CreateInvitationView()
	{
		initialize();
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
		step1.setLayout(new MigLayout("", "[403.00,grow]", "[59.00][98.00,grow]"));

			// The heading panel
			JPanel headerPanel = new JPanel();
			headerPanel.setLayout(new MigLayout("", "[-20.00px][284px][][][][][][][][][][][][][][]", "[125px]"));
	
				JLabel headingLabel = new JLabel("Please specify your contacts...");
				headingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
				
			headerPanel.add(headingLabel, "cell 1 0,alignx left,aligny center");
			
				JLabel headerIcon = new JLabel("");
				headerIcon.setIcon(ImageHelper.loadImageIcon("selectContacts.png", "background", -1, 75, 5));
				
			headerPanel.add(headerIcon, "cell 14 0,aligny top");

		step1.add(headerPanel, "cell 0 0, grow");

			// The contact list panel
			JPanel contactListPanel = new JPanel();
			contactListPanel.setLayout(new MigLayout("", "[692.00,grow]", "[28.00px,grow][][][][][][][][][][][]"));
			contactListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contact List", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			
				JScrollPane tableScrollPane = new JScrollPane();
				tableScrollPane.setViewportView(prepareTable());
				contactListPanel.add(tableScrollPane, "cell 0 0 1 11,grow");
				
				JPanel actionPanel = new JPanel();
				actionPanel.setBackground(Color.ORANGE);
				actionPanel.setLayout(new MigLayout("", "[119px]", "[33px]"));

					JButton plusButton = new JButton("");
					plusButton.setIcon(new ImageIcon(ImageHelper.class.getResource("plus.png")));
					plusButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) {
							controller.addRow();
						}
					});
					
				actionPanel.add(plusButton, "flowx,cell 0 0,alignx center,aligny top");


				JButton facebookImportButton = new JButton("");
				facebookImportButton.setIcon(ImageHelper.loadImageIcon("facebookIcon.png", "Import from Facebook", -1, 24, 5));
				facebookImportButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						new ContactImporter("Facebook");
					}
				});

				JButton minusButton = new JButton("");
				minusButton.setIcon(new ImageIcon(ImageHelper.class.getResource("minus.png")));
				minusButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						controller.deleteRow(table.getSelectedRows());
					}
				});
				actionPanel.add(minusButton, "cell 0 0,alignx center,aligny top");

				JLabel lblOrImportFrom = new JLabel("   or import from:   ");
				lblOrImportFrom.setFont(new Font("Tahoma", Font.BOLD, 12));
				actionPanel.add(lblOrImportFrom, "cell 0 0,alignx left,aligny center");
				actionPanel.add(facebookImportButton, "cell 0 0,alignx center,aligny top");

				JButton hotmailImportButton = new JButton("");
				hotmailImportButton.setIcon(ImageHelper.loadImageIcon("hotmailIcon.png", "Import from Hotmail", -1, 24, 5));
				hotmailImportButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0)
					{
						ArrayList<Guest> tempList = new ContactImporter("Hotmail").getContacts();
						controller.addRow(tempList);
					}
				});
				actionPanel.add(hotmailImportButton, "cell 0 0,alignx center,aligny top");

				JButton googleImportButton = new JButton("");
				googleImportButton.setIcon(ImageHelper.loadImageIcon("googleIcon.png", "Import from Google", -1, 24, 5));
				googleImportButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						new ContactImporter("Google");
					}
				});
				actionPanel.add(googleImportButton, "cell 0 0,alignx center,aligny top");
				
			contactListPanel.add(actionPanel, "cell 0 11,alignx center");
			
		step1.add(contactListPanel, "cell 0 1,grow");
	
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
		table.getModel().addTableModelListener(resizer);
		
		return table;
	}
	
	private void setRenderers()
	{	
		//Align text to center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	
		//The profile picture cells
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());
		table.getColumnModel().getColumn(0).setCellEditor(new IconEditor());
		
		table.getColumnModel().getColumn(6).setCellRenderer(new ColorRenderer(true));
		table.getColumnModel().getColumn(6).setCellEditor(new ColorEditor());
		
		// Create our cell editor
		DateCellEditor datePickerCellEditor = new DateCellEditor();

		// Set the number of mouse clicks needed to activate it.
		datePickerCellEditor.setClickCountToStart(1);

		// Set it for the appropriate table column.
		table.getColumnModel().getColumn(4).setCellEditor(datePickerCellEditor);
		
	}
	
	public JPanel getStep2()
	{
		JPanel step2 = new JPanel();

		step2.setLayout(new MigLayout("", "[25.00,grow][371.00,grow][327.00,grow]", "[81.00][277.00,grow][][-15.00]"));

		JLabel lblAlmostTherePlease = new JLabel("Please check your invitation...");
		lblAlmostTherePlease.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		step2.add(lblAlmostTherePlease, "cell 1 0");

		JLabel label = new JLabel("");
		label.setIcon(ImageHelper.loadImageIcon("confirmIcon.png", "", -1, 75, 5));
		step2.add(label, "cell 2 0,alignx right,gapx 0 10");

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Your guests", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		step2.add(panel_2, "cell 0 1 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[168.00,grow]", "[325.00,grow]"));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, "cell 0 0,grow");

		JList<String> list = new JList<String>();
		scrollPane_1.setViewportView(list);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Your message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		step2.add(panel_1, "cell 2 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 0 0,grow");

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ImageHelper.class.getResource("cutShadowBottom.png")));
		step2.add(lblNewLabel, "cell 1 2 2 2");

		step2.add(new NavigationFooter() {

			@Override
			public void nextButtonClicked()
			{
				switchPanels();
			}

		}, "south, alignx center");

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
		new CreateInvitationView();
	}
}





