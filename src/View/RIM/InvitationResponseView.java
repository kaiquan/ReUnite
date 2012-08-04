package View.RIM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import Model.Membership.*;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.InvitationResponseController;
import Controller.RIM.LookAndFeelController;
import Controller.RIM.Utils.DateHelper;
import Images.RIM.ImageHelper;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.IconRenderer;
import View.RIM.Components.Table.ProgressBarCellRenderer;

@SuppressWarnings("serial")
public class InvitationResponseView extends JFrame
{
	@SuppressWarnings("unused")
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
	private JTable table;
	private JSpinner percentSpinner;

	// Controller
	private InvitationResponseController controller;

	public InvitationResponseView()
	{
		controller = new InvitationResponseController();

		getContentPane().setSize(800, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Invitations");
		setSize(1068, 546);
		getContentPane().setLayout(new MigLayout("", "[795.00px,grow]", "[427px,grow][60px]"));

		initComponents();

		prepareTable();
	}

	private void initComponents()
	{

		JScrollPane jScrollPane1 = new JScrollPane();

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
		button.setPreferredSize(new Dimension(100, 9));

		jScrollPane1.setViewportView(table);

		getContentPane().add(jScrollPane1, "cell 0 0,grow");

		jPanel1.setMinimumSize(new java.awt.Dimension(500, 60));
		jPanel1.setPreferredSize(new java.awt.Dimension(500, 60));
		jPanel1.setLayout(new MigLayout("", "[70px][30px][90px][90px][30px][20px][60px][][75px]", "[20px][21px]"));

		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("dd/MM/yyyy");
		jPanel1.add(jLabel1, "cell 0 0,grow");
		jPanel1.add(dateTextField, "cell 0 1,grow");

		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setText("Event");
		jPanel1.add(jLabel2, "cell 1 0,grow");
		jPanel1.add(intTextField, "cell 1 1,grow");

		jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel3.setText("Title");
		jPanel1.add(jLabel3, "cell 2 0,grow");
		jPanel1.add(firstnameTextField, "cell 2 1,grow");

		jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel4.setText("Initiator");
		jPanel1.add(jLabel4, "cell 3 0,grow");
		jPanel1.add(lastnameTextField, "cell 3 1,grow");

		jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel5.setText("Total guests");
		jPanel1.add(jLabel5, "cell 4 0,grow");
		jPanel1.add(floatTextField, "cell 4 1,grow");
		jPanel1.add(checkBox, "cell 5 1,alignx center,aligny center");

		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setText("Response");
		jPanel1.add(jLabel6, "cell 6 0,grow");
		jPanel1.add(percentSpinner, "cell 6 1,alignx center,growy");

		button.setText("Search");
		button.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

			}
		});
		jPanel1.add(button, "cell 7 0 2 2,alignx right,growy");

		getContentPane().add(jPanel1, "cell 0 1,alignx center,aligny top");

		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void prepareTable()
	{
		table = new JTable()
		{
			public boolean getScrollableTracksViewportHeight()
			{
				if (getParent() instanceof JViewport) return (((JViewport) getParent()).getHeight() > getPreferredSize().height);

				return super.getScrollableTracksViewportHeight();
			}

			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (getRowCount() == 0)
				{
					Graphics2D g2d = (Graphics2D) g;
					g2d.setColor(Color.BLACK);
					java.awt.Image image = ImageHelper.loadImage("noRecordsFound.jpg", "");
					g2d.drawImage(image, this.getWidth() - image.getWidth(null), this.getHeight() - image.getHeight(null), null);
					g2d.drawString("Sorry, no events found...", 0, 0);
				}
			}

		};
		table.setModel(controller.getTableModel());
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					new InvitationDetailsView(controller.getTableModel().getEvent(table.getSelectedRow()));
				}
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		controller.getTableModel().getTableSorter().setTableHeader(table.getTableHeader());
		table.setModel(controller.getTableModel().getTableSorter());
		table.setRowHeight(75);

		// TABLE RESIZING CONFIGURATION
		// resize but don't lock the columns
		AutoResizeTableColumns resizer = new AutoResizeTableColumns(table, controller.getTableModel(), 32, true, true, new boolean[table.getColumnCount()]);
		table.getModel().addTableModelListener(resizer);

		setRenderers();
	}

	private void setRenderers()
	{
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setMaxWidth(30);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 18));
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(new IconRenderer());
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		table.getColumnModel().getColumn(6).setCellRenderer(new TableCellRenderer()
		{
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean arg3, int row, int col)
					{
						if(Account.currentUser.getType().equalsIgnoreCase("RI"))
						{
							if (((Boolean) value).booleanValue() == true)
							{
								return getTableCellRendererComponent(table, value, isSelected, arg3, row, col);
							}
							return new JButton("Send now");
						}
						else if(Account.currentUser.getType().equalsIgnoreCase("GR"))
						{
							if (((Boolean) value).booleanValue() == true)
							{
								return getTableCellRendererComponent(table, value, isSelected, arg3, row, col);
							}
							return new JButton("Notify RI");
						}
						
						return null;
					}

		});
		
		// configuration for progress bar...
				Hashtable<Integer, Color> limitColors = new Hashtable<Integer, Color>();
				limitColors.put(new Integer(0), Color.red);
				limitColors.put(new Integer(50), Color.yellow);
				limitColors.put(new Integer(75), Color.green);
				// COLUMN 6 contains progress bar!
				TableColumnModel model = table.getColumnModel();

				model.getColumn(7).setCellRenderer(new ProgressBarCellRenderer(true, false, 0, 100, limitColors, table.getBackground()));
	}

	public static void main(String args[])
	{
		try
		{
			LookAndFeelController.setGlobalLookAndFeel();
			java.awt.EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					new InvitationResponseView().setVisible(true);
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
