package View.RIM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;
import Controller.RIM.SearchUsersController;
import View.RIM.Components.Table.AutoResizeTableColumns;
import View.RIM.Components.Table.IconRenderer;
import View.RIM.Components.Table.*;

@SuppressWarnings("serial")
public class SearchUsersView extends JFrame
{
	SearchUsersController controller = new SearchUsersController();
	public JTable table;
	private JButton btnAddToList;

	public SearchUsersView()
	{
		setPanel();
		initialize();
	}

	private void initialize()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
	}

	private void setPanel()
	{
		getContentPane().setLayout(new MigLayout("", "[198.00,grow][449.00,grow][195.00,grow][21.00]", "[38.00][374.00,grow][]"));
		table = new JTable(controller.getTableModel());
		table.setRowHeight(75);
		table.getTableHeader().setReorderingAllowed(false);
		controller.getTableModel().getTableSorter().setTableHeader(table.getTableHeader());
		table.setModel(controller.getTableModel().getTableSorter());
		setRenderers();

		AutoResizeTableColumns resizer = new AutoResizeTableColumns(table, controller.getTableModel(), 32, true, true, new boolean[table.getColumnCount()]);
		controller.getTableModel().addTableModelListener(resizer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		getContentPane().add(scrollPane, "cell 0 1 3 1,grow");

		btnAddToList = new JButton("Add to list");
		btnAddToList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				controller.addToList(table.getSelectedRows());
				dispose();
			}
		});
		getContentPane().add(btnAddToList, "cell 2 2,alignx right,growy");
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

		// The profile picture cells
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());
	}

	public static void main(String args[])
	{
		new SearchUsersView();
	}
}
