package View.RIM.Components;

import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.miginfocom.swing.MigLayout;
import Images.RIM.ImageHelper;
import Model.Membership.Guest;

import com.explodingpixels.widgets.ImageButton;

@SuppressWarnings("serial")
public class JListGuestListRenderer extends JPanel implements ListCellRenderer<Guest>
{
	public JListGuestListRenderer() 
	{
		setLayout(new MigLayout("", "[-14.00][121.00]", "[69.00][]"));
		
		
		JLabel label_1 = new JLabel("");
		add(label_1, "cell 0 0 2 1");
		
		JLabel nameLabel = new JLabel("");
		add(nameLabel, "cell 1 1");
		
	}
	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	@Override
	public Component getListCellRendererComponent(JList<? extends Guest> list, Guest value, int index, boolean isSelected, boolean hasFocus) {
		JPanel panel = new JPanel(new MigLayout("", "[-14.00][125.00]", "[69.00]"));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		if(isSelected)
		{
			panel.setBackground(Color.gray);
			panel.setSize(75, 75);
		}

		JLabel label = new JLabel();
		JLabel onlineIndicator = new JLabel();
		label.setSize(75, 75);
		if (value instanceof Guest)
		{
			Guest guest = (Guest) value;
			ImageIcon icon = (ImageIcon) iconTable.get(value);
			if (icon == null)
			{
				icon = ImageHelper.loadImageIcon(guest.getProfilePicture(), "profile picture", 75, 75, 5);
				iconTable.put(guest, icon);
			}
			label.setIcon(icon);
			label.setToolTipText(guest.getFirstName() + guest.getLastName() + "("+guest.getUserName()+")");
			

			
			if(guest.getOnlineStatus()){
				onlineIndicator.setIcon(ImageHelper.loadImageIcon("onlineIndicatorIcon.png", "edit", -1, 12, 5));
			}
			else
			{
				onlineIndicator.setIcon(ImageHelper.loadImageIcon("offlineIndicatorIcon.png", "edit", -1, 12, 5));
			}
		}
		else
		{
			label.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 75, 75, 5));
		}
		panel.add(label, "cell 0 0 2 1");
		
		panel.add(onlineIndicator, "aligny top");
		
		return panel;
	}
}
