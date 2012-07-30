package View.RIM;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import View.RIM.Components.*;

import Model.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
@SuppressWarnings("serial")
public class InvitationDetailsView extends JDialog
{
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	 
	public InvitationDetailsView(Event event)
	{
		initialize();
		
		setTitle(event.getEventName());
		setSize(857, 707);
		setSize(857, 707);
		setSize(857, 707);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "cell 1 1 2 1,grow");
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Meal", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[][]", "[][]"));
		
		lblNewLabel = new JLabel(event.getEventDescription());
		panel_1.add(lblNewLabel, "cell 1 1");
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Entertainment", null, panel_2, null);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Ballroom", null, panel_3, null);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Facility", null, panel_4, null);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("Guests", null, panel_5, null);
		setSize(857, 707);
		
		
		
		setVisible(true);
		
	}
	
	public void initialize()
	{
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[55px][597.00px,grow][690px]", "[307.00px][223.00,grow]"));
		panel.add(new PieChart("Invitation Response:", new Invitation()), "cell 2 0,alignx left,aligny top");
		getContentPane().add(panel);
	}
	

}
