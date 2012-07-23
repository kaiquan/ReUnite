package View.RIM.Components.Chat;

import javax.swing.*;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;

import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class JListChatUserRenderer extends DefaultListCellRenderer
{
	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus)
	{
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);

		if (value instanceof Guest)
		{
			Guest guest = (Guest) value;
			ImageIcon icon = (ImageIcon) iconTable.get(value);
			if (icon == null)
			{
				icon = ImageHelper.loadImageIcon(guest.getProfilePicture(), "profile picture", 50, 50, 5);
				iconTable.put(guest, icon);
			}
			label.setIcon(icon);
		}
		else
		{
			// Clear old icon; needed in 1st release of JDK 1.2
			label.setIcon(null);
			label.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 50, 50, 5));
		}
		return (label);
	}
}