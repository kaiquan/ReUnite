package View.RIM.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.explodingpixels.widgets.ImageButton;

import net.miginfocom.swing.MigLayout;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;

@SuppressWarnings("serial")
public class JListGuestImportRenderer extends JPanel implements ListCellRenderer<Guest>
{
	public JListGuestImportRenderer() {}
	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	@Override
	public Component getListCellRendererComponent(JList<? extends Guest> list, Guest value, int index, boolean isSelected, boolean hasFocus) {
		JPanel panel = new JPanel(new MigLayout("", "", "[]"));
		panel.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel();
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
		if(isSelected)
		{
			panel.setBackground(Color.gray);
		}

		panel.add(label);
		
		JLabel firstNameLabel = new JLabel();
		firstNameLabel.setText(value.getFirstName());
		
		panel.add(firstNameLabel);
		
		ImageButton editButton = new ImageButton(ImageHelper.loadImageIcon("pencilIcon.png", "edit", -1, 24, 5));

		
		panel.add(editButton);
		
		return panel;
	}
}
