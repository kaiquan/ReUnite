package form;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

	public class Renderer extends DefaultTableCellRenderer { 
	      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JLabel lbl;
	      ImageIcon icon = null; 
	       
	      
	      public Renderer(){
	      
	      }
	       
	      public Component getTableCellRendererComponent(JTable table, Object value, 
	                                                     boolean isSelected, boolean hasFocus,  
                                                     int row, int column) {            

	    	String s = value.toString();
	    	lbl = new JLabel(s, icon, JLabel.CENTER);
	        lbl.setIcon(icon);  
	        lbl.setVerticalTextPosition(JLabel.BOTTOM);
	        lbl.setHorizontalTextPosition(JLabel.CENTER);
	        lbl.setAlignmentX(CENTER_ALIGNMENT);
	        lbl.setAlignmentY(CENTER_ALIGNMENT);        
	        table.setRowHeight(row, 30);
	        return lbl; 
	      }
	      
	}