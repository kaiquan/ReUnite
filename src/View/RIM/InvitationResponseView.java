package View.RIM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.InvitationResponseController;
import Controller.RIM.LookAndFeelController;
import Images.RIM.ImageHelper;
import Model.Membership.Account;
import Model.RIM.TableModels.TableSorter;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.ButtonEditor;
import View.RIM.Components.Table.ButtonRenderer;
import View.RIM.Components.Table.IconRenderer;
import View.RIM.Components.Table.ProgressBarCellRenderer;

@SuppressWarnings("serial")
public class InvitationResponseView extends JFrame
{
	private JTable table;
	private TableRowSorter<TableSorter> sorter;

	// Controller
	private InvitationResponseController controller;

	public InvitationResponseView()
	{
		controller = new InvitationResponseController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Invitations");
		getContentPane().setLayout(new MigLayout("", "[795.00px,grow]", "[427px,grow][60px]"));
		initComponents();
		pack();
		setSize(1024, 750);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

	private void initComponents()
	{
		prepareTable();

		JScrollPane jScrollPane1 = new JScrollPane();
		
		getContentPane().add(jScrollPane1, "cell 0 0,grow");

		jScrollPane1.setViewportView(table);
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
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      new InvitationDetailsView(controller.getTableModel().getEvent(row));
			    }
			  }
			});

		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter<TableSorter>(controller.getTableModel().getTableSorter());
		controller.getTableModel().getTableSorter().setTableHeader(table.getTableHeader());
		table.setModel(controller.getTableModel().getTableSorter());
		table.setRowSorter(sorter);

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
		
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
		ButtonRenderer buttonRenderer = new ButtonRenderer();
		buttonRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(6).setCellRenderer(buttonRenderer);

		// configuration for progress bar...
		Hashtable<Integer, Color> limitColors = new Hashtable<Integer, Color>();
		limitColors.put(new Integer(0), Color.red);
		limitColors.put(new Integer(40), Color.yellow);
		limitColors.put(new Integer(75), Color.green);
		// COLUMN 6 contains progress bar!
		TableColumnModel model = table.getColumnModel();
		model.getColumn(7).setCellRenderer(new ProgressBarCellRenderer(true, false, 0, 100, limitColors, table.getBackground()));

		// Hide columns based on user type
		if (Account.currentUser.getType().equalsIgnoreCase("Guest"))
		{
			table.removeColumn(table.getColumnModel().getColumn(6));
		}
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
