package View.RIM.Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.RIM.LookAndFeelController;
import Controller.RIM.Utils.DateHelper;
import Model.Event;
import Model.RIM.TableModels.InvitationTableModel;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.ProgressBarCellRenderer;
import View.RIM.Components.Table.TableSorter;
import View.RIM.Components.Table.TableSorterIcons;

@SuppressWarnings("serial")
public class InvitationReportTable extends JFrame
{
	private static DateHelper dateHelper = new DateHelper();
	
	private JButton button;
	private JCheckBox checkBox;
	private JTextField dateTextField;
	private JTextField firstnameTextField;
	private JTextField floatTextField;
	private JTextField intTextField;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JPanel jPanel1;
	private JTextField lastnameTextField;
	private JTable m_table;
	private JSpinner percentSpinner;

	// The table model
	private InvitationTableModel m_tableModel = new InvitationTableModel(new Event().GET_ALL_EVENTS());

	// The model used for sorting
	private TableSorter m_tableSorter = new TableSorter(m_tableModel);

	public InvitationReportTable()
	{
		initComponents();

		m_table.setModel(m_tableModel);
		
		m_table.getTableHeader().setReorderingAllowed(false);
		m_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// m_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Hiding first column in order to test resize
		// GUIUtils.hideJTableColumn(m_table.getColumnModel().getColumn(0));

		// SORTING CONFIGURATION
		// if you wish to use the same icons regardless of column's sorting
		// priority, then use the two lines below.
		//
		// Icon ups[] = new Icon[] {TableSorterIcons.UP5_ICON};
		// Icon downs[] = new Icon[] {TableSorterIcons.DOWN5_ICON};
		//
		Icon ups[] = new Icon[] {
				TableSorterIcons.UP5_ICON, // this one will be dispayed first
				TableSorterIcons.UP7_ICON, 
				TableSorterIcons.UP6_ICON, 
				TableSorterIcons.UP4_ICON, 
				TableSorterIcons.UP3_ICON,
				TableSorterIcons.UP2_ICON, 
				TableSorterIcons.UP1_ICON };
		
		Icon downs[] = new Icon[] {
				TableSorterIcons.DOWN5_ICON, // this one will be dispayed first
				TableSorterIcons.DOWN7_ICON, 
				TableSorterIcons.DOWN6_ICON, 
				TableSorterIcons.DOWN4_ICON, 
				TableSorterIcons.DOWN3_ICON,
				TableSorterIcons.DOWN2_ICON, 
				TableSorterIcons.DOWN1_ICON };

		m_tableSorter.setCustomIcons(ups, downs);
		m_tableSorter.setTableHeader(m_table.getTableHeader());
		m_table.setModel(m_tableSorter);
		// -----

		// TABLE RESIZING CONFIGURATION
		// resize but don't lock the columns
		AutoResizeTableColumns resizer = new AutoResizeTableColumns(m_table, m_tableModel, 32, true, true, new boolean[m_table.getColumnCount()]);
		m_table.getModel().addTableModelListener(resizer);
		m_table.getColumnModel().getColumn(0).setResizable(false);
		m_table.getColumnModel().getColumn(0).setMaxWidth(30);
		// uncomment for debuging
		// resizer.setDebugMode( true );
		// -----

		// TABLE ACTUAL DATA
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Object[][] values = new Object[][] {
					{ sdf.parse("29/11/2004"), new Integer(999), "Some event", "Adeel", new Float(999.99F), Boolean.FALSE, new Integer(90) },
					{ sdf.parse("14/06/2003"), new Integer(888), "Some other event", "Shahrikin", new Float(888.88F), Boolean.TRUE, new Integer(80) },
					{ sdf.parse("20/07/2001"), new Integer(9), "Very weird event", "Kai Quan", new Float(109.30F), Boolean.TRUE, new Integer(25) },
					{ sdf.parse("30/10/1999"), new Integer(190), "Cheesy event ", "Ameenudeen", new Float(23.27F), Boolean.FALSE, new Integer(50) }, };

			/* instead of addRow()... */
			/*
			 * m_tableModel.addRow(values[0]); m_tableModel.addRow(values[1]);
			 * m_tableModel.addRow(values[2]); m_tableModel.addRow(values[3]);
			 * m_tableModel.addRow(values[4]); m_tableModel.addRow(values[5]);
			 * m_tableModel.addRow(values[6]);
			 */

			/* ...use setDataVector() for performance! */
			// -----
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// TABLE SPECIAL RENDERER FOR PROGRESSBAR
		// configuration for progress bar...
		Hashtable<Integer, Color> limitColors = new Hashtable<Integer, Color>();
		limitColors.put(new Integer(0), Color.red);
		limitColors.put(new Integer(60), Color.yellow);
		limitColors.put(new Integer(80), Color.green);
		// COLUMN 6 contains progress bar!
		TableColumnModel model = m_table.getColumnModel();
		model.getColumn(6).setCellRenderer(new ProgressBarCellRenderer(true, false, 0, 100, limitColors, m_table.getBackground()));
		// -----
	}


	private void add_row()
	{
		try
		{
			java.util.Date d = dateHelper.parseDate(dateTextField.getText().trim(), "dd/MM/yyyy");
			Integer i = new Integer(intTextField.getText().trim());
			String firstname = firstnameTextField.getText().trim();
			String lastname = lastnameTextField.getText().trim();
			Float f = new Float(floatTextField.getText().trim());
			Boolean b = new Boolean(checkBox.isSelected());
			Integer percent = (Integer) percentSpinner.getValue();

			m_tableModel.addRow(new Object[] { d, i, firstname, lastname, f, b, percent });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initComponents()
	{

		JScrollPane jScrollPane1 = new JScrollPane();
		m_table = new JTable();
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		dateTextField = new JTextField();
		jLabel2 = new JLabel();
		intTextField = new JTextField();
		jLabel3 = new JLabel();
		firstnameTextField = new JTextField();
		jLabel4 = new JLabel();
		lastnameTextField = new JTextField();
		jLabel5 = new JLabel();
		floatTextField = new JTextField();
		checkBox = new JCheckBox();
		jLabel6 = new JLabel();
		percentSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 10));
		button = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Invitations");

		m_table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7" }));
		m_table.setToolTipText("Press down and hold CTRL or SHIFT while selecting multiple columns' headers for sorting.");
		jScrollPane1.setViewportView(m_table);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanel1.setMinimumSize(new java.awt.Dimension(500, 60));
		jPanel1.setPreferredSize(new java.awt.Dimension(500, 60));
		jPanel1.setLayout(null);

		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("dd/MM/yyyy");
		jPanel1.add(jLabel1);
		jLabel1.setBounds(10, 10, 70, 20);
		jPanel1.add(dateTextField);
		dateTextField.setBounds(10, 30, 70, 21);

		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setText("Event");
		jPanel1.add(jLabel2);
		jLabel2.setBounds(90, 10, 30, 20);
		jPanel1.add(intTextField);
		intTextField.setBounds(90, 30, 30, 21);

		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setText("Title");
		jPanel1.add(jLabel3);
		jLabel3.setBounds(130, 10, 90, 20);
		jPanel1.add(firstnameTextField);
		firstnameTextField.setBounds(130, 30, 90, 21);

		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setText("Initiator");
		jPanel1.add(jLabel4);
		jLabel4.setBounds(230, 10, 90, 20);
		jPanel1.add(lastnameTextField);
		lastnameTextField.setBounds(230, 30, 90, 21);

		jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel5.setText("Total guests");
		jPanel1.add(jLabel5);
		jLabel5.setBounds(330, 10, 30, 20);
		jPanel1.add(floatTextField);
		floatTextField.setBounds(330, 30, 30, 21);
		jPanel1.add(checkBox);
		checkBox.setBounds(370, 30, 20, 17);

		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setText("Response");
		jPanel1.add(jLabel6);
		jLabel6.setBounds(390, 10, 60, 20);
		jPanel1.add(percentSpinner);
		percentSpinner.setBounds(400, 30, 40, 21);

		button.setText("Search");
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				buttonActionPerformed(evt);
			}
		});
		jPanel1.add(button);
		button.setBounds(460, 10, 75, 40);

		getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

		pack();
	}

	private void buttonActionPerformed(ActionEvent evt)
	{
		add_row();
	}
	
	public static void main(String args[])
	{
		try
		{
			LookAndFeelController.setGlobalLookAndFeel();
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run()
				{
					new InvitationReportTable().setVisible(true);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
