package View.RIM.Components.Table;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import View.RIM.*;

@SuppressWarnings("serial")
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	protected static final String EDIT = "edit";
	
	boolean value;
	JButton button;
	JTable table;
	int row;
	int column;

	  public ButtonEditor() {
		  button = new JButton();
			button.setActionCommand(EDIT);
			button.addActionListener(this);
			button.setBorderPainted(false);
	  }
	  
	  public void actionPerformed(ActionEvent e) 
		{
			if (EDIT.equals(e.getActionCommand())) 
			{	System.out.println("Send now button clicked");
				new CreateInvitationView((Integer) table.getModel().getValueAt(row, 0));
				for(Window view : InvitationResponseView.getFrames())
				{
					view.dispose();
				}
				
				for(Window view : InvitationDetailsView.getWindows())
				{
					view.dispose();
				}
				//Make the renderer reappear.
				fireEditingStopped();
			} 
		}
	public Object getCellEditorValue() 
	{
		return value;
	}
	
	//Implement the one method defined by TableCellEditor.
	public Component getTableCellEditorComponent(JTable table,
	                        Object value,
	                        boolean isSelected,
	                        int row,
	                        int column) 
	{
		this.value = (boolean) value;
		this.table = table;
		this.row = row;
		this.column = column;
		if(this.value==false){button.setEnabled(false);};
		return button;
	}


	}
