package View.RIM.Components.Table;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import Model.Membership.*;
@SuppressWarnings("serial")
public class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    if(((Boolean)value).booleanValue() == false){
		    if(Account.currentUser.getType().equalsIgnoreCase("RI")){
		    	setText("Send now");
		    }
		    else if(Account.currentUser.getType().equalsIgnoreCase("RI")){
	    	setText("Notify RI");
		    }
	    }else
	    {
	    	JCheckBox checkBox = new JCheckBox("", (Boolean) value);
	    	checkBox.setEnabled(false);
	    	return checkBox;
	    }
	   
	    return this;
	  }
	}