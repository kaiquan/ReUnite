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
public class JListGuestImportRenderer extends JPanel implements ListCellRenderer<Guest>
{
	public JListGuestImportRenderer() 
	{
		
	}
	private Hashtable<Guest, ImageIcon> iconTable = new Hashtable<Guest, ImageIcon>();

	@Override
	public Component getListCellRendererComponent(JList<? extends Guest> list, Guest value, int index, boolean isSelected, boolean hasFocus) {
		JPanel panel = new JPanel(new MigLayout("", "[][][][][][][][][][][][][][][][][]", "[][][]"));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		if(isSelected)
		{
			panel.setBackground(Color.gray);
		}

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
			label.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 50, 50, 5));
		}
		panel.add(label, "cell 0 0 4 3");
		
		JLabel nameLabel = new JLabel("Name");
		panel.add(nameLabel, "cell 4 0");
		
		JLabel lblEmail = new JLabel("Email");
		panel.add(lblEmail, "cell 8 0");
		
		JLabel lblAddress = new JLabel("Adress");
		panel.add(lblEmail, "cell 12 0");
		
		ImageButton editButton = new ImageButton(ImageHelper.loadImageIcon("pencilIcon.png", "edit", -1, 24, 5));

		
		panel.add(editButton);
		
		return panel;
	}
}
