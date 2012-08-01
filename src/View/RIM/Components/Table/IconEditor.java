package View.RIM.Components.Table;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import Controller.RIM.Utils.*;


@SuppressWarnings("serial")
public class IconEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

ImageIcon profilePicture;
JButton button;
JFileChooser fileChooser;
protected static final String EDIT = "edit";

	public IconEditor() 
	{
		//Set up the editor (from the table's point of view),
		//which is a button.
		//This button brings up the color chooser dialog,
		//which is the editor from the user's point of view.
		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);
	
		fileChooser = new JFileChooser("c:\\");
	
	}

	/**
	* Handles events from the editor button and from
	* the dialog's OK button.
	*/
	public void actionPerformed(ActionEvent e) 
	{
		ImageIcon backupIcon = profilePicture;
		if (EDIT.equals(e.getActionCommand())) 
		{
			//The user has clicked the cell, so
			//bring up the dialog.
			Image image = IOHelper.getImageFile();
			if(image !=null)
			{
				profilePicture = new ImageIcon(image);
			}
		
			//Make the renderer reappear.
			fireEditingStopped();
		} 
		else 
		{ 
			profilePicture = backupIcon;
		}
	}
	
	//Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() 
	{
		return profilePicture;
	}
	
	//Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table,
	                        Object value,
	                        boolean isSelected,
	                        int row,
	                        int column) 
	{
		profilePicture = (ImageIcon)value;
		return button;
	}
}