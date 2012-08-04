package View.RIM.Components.Table;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class TextAreaEditor implements TableCellEditor
{
	JTextArea textArea;
	JScrollPane scrollPane;

	public TextAreaEditor()
	{
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		textArea.setText((String) value);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(null);
		return scrollPane;
	}

	public void addCellEditorListener(CellEditorListener l)
	{
	}

	public void cancelCellEditing()
	{
	}

	public Object getCellEditorValue()
	{
		return textArea.getText();
	}

	public boolean isCellEditable(EventObject anEvent)
	{
		return true;
	}

	public void removeCellEditorListener(CellEditorListener l)
	{
	}

	public boolean shouldSelectCell(EventObject anEvent)
	{
		return true;
	}

	public boolean stopCellEditing()
	{
		return true;
	}
}
