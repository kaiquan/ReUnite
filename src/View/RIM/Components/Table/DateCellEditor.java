package View.RIM.Components.Table;

import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTextField;

import View.RIM.Components.DatePicker;

@SuppressWarnings("serial")
public class DateCellEditor extends DefaultCellEditor
{

	// we need this as DefaultCellEditor has no default constructor.
	public DateCellEditor()
	{
		super(new JTextField()); // not really relevant - sets a text field as the editing default.
	}

	// If the cell is editable and it's a mouse event, set up the date picker.
	// If you don't want any keyboard editing, return false if not a MouseEvent.
	public boolean isCellEditable(EventObject anEvent)
	{
		boolean isEditable = super.isCellEditable(anEvent);
		if (isEditable && anEvent instanceof MouseEvent)
		{
			setupDatePicker();
		}
		return isEditable;
	}

	// Set the edit placeholder for the cell, and make the delegate our DatePickerComponent
	// (the component that displays the DatePicker).
	private void setupDatePicker()
	{
		editorComponent = new JLabel("");
		delegate = new DatePickerComponent(this);
	}

	class DatePickerComponent extends EditorDelegate
	{

		CellEditor cellEditor; // reference to cell editor so we can tell it when we're finished.

		
		DateDialog datePicker;
		
		DatePickerComponent(CellEditor cellEditor)
		{
			this.cellEditor = cellEditor;
			createDatePicker();
		}

		// Do whatever you need to create the date picker here.
		private void createDatePicker()
		{
			datePicker = new DateDialog();
		}

		// Set the date to be edited into the date picker and display / edit it.
		public void setValue(Object value)
		{
			datePicker.setValue(value);
			datePicker.startEditing();
		}

		// Get the edited date out of the date picker and return it.
		public Object getCellEditorValue()
		{
			return datePicker.getValue();
		}
		

		// Call this when the date picker edit has finished.
		private void stopEditing()
		{
			cellEditor.stopCellEditing();
		}

		class DateDialog
		{
			DatePicker picker;
			String date;
			
			DateDialog()
			{
				picker = (new DatePicker());
			}

			public void setValue(Object value)
			{
				if (value != null)
				{
					picker.setPickedDate((String) value);
				}
			}

			public void startEditing()
			{
				picker.setVisible(true);
				date = picker.getPickedDate();
				stopEditing();
			}

			public Object getValue()
			{
				return date;
			}
		}
	}
}
