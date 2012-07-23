package View.RIM.Components;

import java.awt.Component;
import java.util.Hashtable;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;

@SuppressWarnings("serial")
public class JListGuestImportRenderer extends DefaultListCellRenderer
{
	public JListGuestImportRenderer() {
	}
	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus)
	{	JPanel panel = new JPanel(new MigLayout("", "", "[]"));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
		label.setBackground(null);
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
		panel.add(label);
		panel.add(new JButton("edit..."));
		return (panel);
	}
}
