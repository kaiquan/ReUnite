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

@SuppressWarnings("serial")
public class JListGuestListRenderer extends JPanel implements ListCellRenderer<Guest>
{
	public JListGuestListRenderer()
	{

	}

	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	@Override
	public Component getListCellRendererComponent(JList<? extends Guest> list, Guest value, int index, boolean isSelected, boolean hasFocus)
	{

		BackgroundPanel panel = new BackgroundPanel(ImageHelper.loadImageIcon("userIcon.png", "", 100, 100, 5).getImage(), BackgroundPanel.ACTUAL);
		panel.setLayout(new MigLayout("", "[-14.00][125.00]", "[69.00]"));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setSize(100, 100);

		if (isSelected)
		{
			panel.setBackground(Color.GRAY);
		}

		if (value instanceof Guest)
		{
			JLabel onlineIndicator = new JLabel();

			Guest guest = (Guest) value;
			ImageIcon icon = (ImageIcon) iconTable.get(value);
			if (icon == null)
			{
				if(guest.getProfilePicture()!=null && !guest.getProfilePicture().equals("")){
					icon = ImageHelper.loadImageIcon(guest.getProfilePicture(), "profile picture", 100, 100, 5);
				}
				else
				{
					icon = ImageHelper.loadImageIcon("userIcon.png", "profile picture", 100, 100, 5);
				}
				iconTable.put(guest, icon);
			}
			panel.setImage(icon.getImage());

			panel.setToolTipText(guest.getFirstName() + guest.getLastName() + " (" + guest.getUserName() + ")");

			if (guest.getOnlineStatus())
			{
				onlineIndicator.setIcon(ImageHelper.loadImageIcon("onlineIndicatorIcon.png", "edit", -1, 12, 5));
			}
			else
			{
				onlineIndicator.setIcon(ImageHelper.loadImageIcon("offlineIndicatorIcon.png", "edit", -1, 12, 5));
			}

			panel.add(onlineIndicator, "aligny top");
		}
		return panel;
	}
}
