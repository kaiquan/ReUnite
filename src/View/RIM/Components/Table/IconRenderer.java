package View.RIM.Components.Table;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Images.RIM.ImageHelper;

import Model.Membership.*;

@SuppressWarnings("serial")
public class IconRenderer extends DefaultTableCellRenderer 
{
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		JLabel label = (JLabel) super.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
		
		if(value instanceof ImageIcon) 
		{
			label.setText(null);
			label.setIcon((ImageHelper.getScaledImageIcon((ImageIcon) value, 75, 75, 5)));
		}
		else if(value instanceof Account) 
		{	Account account = (Account) value;
			label.setToolTipText(account.getUserName());
			label.setText(account.getUserName());
			label.setIcon(ImageHelper.loadImageIcon("userIcon.png", "", 75, 75, 5));
		}
		setHorizontalAlignment(JLabel.CENTER);
		return label;
	}
}

