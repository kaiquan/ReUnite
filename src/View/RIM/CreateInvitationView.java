package View.RIM;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.ContactImporter;
import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import Model.Membership.Guest;
import Model.RIM.GuestCollection;
import Model.RIM.TableModels.GuestImportTableModel;
import View.RIM.Components.NavigationFooter;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.TableSorter;
import View.RIM.Components.Table.TableSorterIcons;

@SuppressWarnings("serial")
public class CreateInvitationView extends JFrame
{

	// The panel that holds the cards
	private JPanel mainPanel;
	
	//Table elements
	private GuestCollection guests;
	private GuestImportTableModel tableModel;
	private TableSorter tableSorter;
	private JTable table;

	public CreateInvitationView()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImageHelper.class.getResource("emailIcon.png")));
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
						tableModel.addRow(new Guest());
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
						tableModel.deleteRow(table.getSelectedRows());
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
						tableModel.addRow(tempList);
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
		Icon ups[] = new Icon[] 
		{
			TableSorterIcons.UP5_ICON, // this one will be dispayed first
			TableSorterIcons.UP7_ICON, TableSorterIcons.UP6_ICON,
			TableSorterIcons.UP4_ICON, TableSorterIcons.UP3_ICON,
			TableSorterIcons.UP2_ICON, TableSorterIcons.UP1_ICON 
		};

		Icon downs[] = new Icon[] 
		{
			TableSorterIcons.DOWN5_ICON, // this one will be dispayed first
			TableSorterIcons.DOWN7_ICON, TableSorterIcons.DOWN6_ICON,
			TableSorterIcons.DOWN4_ICON, TableSorterIcons.DOWN3_ICON,
			TableSorterIcons.DOWN2_ICON, TableSorterIcons.DOWN1_ICON 
		};
		
		guests = new GuestCollection();
		tableModel = new GuestImportTableModel(guests);
		tableSorter =  new TableSorter(tableModel);
		
		table = new JTable(tableModel)
		{
		    public Component prepareRenderer(TableCellRenderer renderer,
		                                     int rowIndex, int vColIndex) {
		        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
		            c.setBackground(Color.lightGray);
		        }
		        else if(isRowSelected(rowIndex))
		        {
		        	c.setBackground(Color.gray);
		        }
		        else 
		        {
		            // If not shaded, match the table's background
		            c.setBackground(getBackground());
		        }
		        return c;
		    }
		};
		table.setRowHeight(75);
		table.getTableHeader().setReorderingAllowed(false);
		
		tableSorter.setCustomIcons(ups, downs);
		tableSorter.setTableHeader(table.getTableHeader());
		table.setModel(tableSorter);

		setRenderers();
		
		AutoResizeTableColumns resizer = new AutoResizeTableColumns(table, tableModel, 32, true, true, new boolean[table.getColumnCount()]);
		table.getModel().addTableModelListener(resizer);
		
		return table;
	}
	
	private void setRenderers()
	{	
		table.getColumnModel().getColumn(0).setCellRenderer(new TableCellIconRenderer());
	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
		
		String[] values = new String[]{"item1", "item2", "item3"};
		table.getColumnModel().getColumn(4).setCellEditor(new MyComboBoxEditor(values));
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

@SuppressWarnings("serial")
class TableCellIconRenderer extends DefaultTableCellRenderer 
{
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel label = (JLabel) super.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
		
		if (value instanceof ImageIcon) 
		{
			label.setText(null);
			label.setIcon((ImageHelper.getScaledImageIcon((ImageIcon) value, 75, 75, 5)));
		}
		
		return label;
	}
}


@SuppressWarnings({ "serial", "hiding" })
class MyComboBoxRenderer<String> extends JComboBox<String> implements TableCellRenderer {
    public MyComboBoxRenderer(String[] items) {
        super(items);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        // Select the current value
        setSelectedItem(value);
        return this;
    }
}

@SuppressWarnings("serial")
class MyComboBoxEditor extends DefaultCellEditor {
	public MyComboBoxEditor(String[] items) {
        super(new JComboBox<String>(items));
    }
}