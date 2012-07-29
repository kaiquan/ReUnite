package View.RIM.Components.Table;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Images.RIM.ImageHelper;

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
		
		return label;
	}
}

