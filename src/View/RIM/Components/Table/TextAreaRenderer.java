package View.RIM.Components.Table;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class TextAreaRenderer extends DefaultTableCellRenderer
{
	JScrollPane scrollPane;
	JTextArea textArea;

	public TextAreaRenderer()
	{
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		textArea.setText((String) value);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(null);

		scrollPane.setBorder(null);
		return scrollPane;
	}
}
