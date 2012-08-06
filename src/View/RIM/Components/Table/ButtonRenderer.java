package View.RIM.Components.Table;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import Images.RIM.ImageHelper;
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
		    	setIcon(ImageHelper.loadImageIcon("sendNow.png", "", 30, -1, 5));
		    }
		   
	    }else
	    {
	    	JCheckBox checkBox = new JCheckBox("", (boolean) value);
	    	checkBox.setEnabled(false);
	    	checkBox.setIcon(ImageHelper.loadImageIcon("sentAlready.png", "", 30, -1, 5));
	    	checkBox.setText("Thank you.");
	    	return checkBox;
	    }
	   
	    return this;
	  }
	}