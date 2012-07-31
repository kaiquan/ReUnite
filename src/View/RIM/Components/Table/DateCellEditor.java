package View.RIM.Components.Table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DateCellEditor extends DefaultCellEditor {

// we need this as DefaultCellEditor has no default constructor.
public DateCellEditor() {
   super(new JTextField()); // not really relevant - sets a text field as the editing default.
}

// If the cell is editable and it's a mouse event, set up the date picker.
// If you don't want any keyboard editing, return false if not a MouseEvent.
public boolean isCellEditable(EventObject anEvent) {
   boolean isEditable = super.isCellEditable(anEvent);
   if (isEditable && anEvent instanceof MouseEvent) 
   {
       setupDatePicker();
   }
   return isEditable;
}

// Set the edit placeholder for the cell, and make the delegate our DatePickerComponent
// (the component that displays the DatePicker).
private void setupDatePicker() {
   editorComponent = new JLabel("");
   delegate = new DatePickerComponent(this);
}

class DatePickerComponent extends EditorDelegate {

   CellEditor cellEditor;  // reference to our cell editor so we can tell it when we're finished.

   // My fake DatePicker.
   DatePicker datePicker;

   // The component constructor - stores the cell editor and creates the date picker.
   DatePickerComponent(CellEditor cellEditor) {
       this.cellEditor = cellEditor;
       createDatePicker();
   }

   // Do whatever you need to create the date picker here.
   private void createDatePicker() {
       datePicker = new DatePicker();
   }

   // Set the date to be edited into the date picker and display / edit it.
   public void setValue(Object value) {
       datePicker.setValue(value);
       datePicker.startEditing();
   }

   // Get the edited date out of the date picker and return it.
   public Object getCellEditorValue() {
       return datePicker.getValue();
   }

   // Call this when the date picker edit has finished.
   private void stopEditing() {
       cellEditor.stopCellEditing();
   }

   // My faked up date picker - just displays a text field  & OK button in a dialog
   // This could be any external component - it just needs to somehow call the 
   // DatePickerComponent.stopEditing() method when it has finished editing, e.g. callback, etc.
   class DatePicker {
       JDialog dialog;
       JTextField textField;

       DatePicker() {

           dialog = new JDialog();
           View.RIM.Components.DatePicker picker = new View.RIM.Components.DatePicker();
           textField = new JTextField();
           dialog.add(textField);
           dialog.add(new JButton(new AbstractAction("OK") {
               public void actionPerformed(ActionEvent e) {
                   dialog.dispose();
                   stopEditing();  // editing is finished, so tell the parent component
               }
           }), BorderLayout.SOUTH);
           dialog.setSize(300, 200);
       }

       public void setValue(Object value) {
           textField.setText((value != null) ? value.toString() : "");
       }

       public void startEditing() {
           dialog.setVisible(true);
       }

       public Object getValue() {
           return textField.getText();
       }
   }
}
}
