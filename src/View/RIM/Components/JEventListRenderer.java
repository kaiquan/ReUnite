package View.RIM.Components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.miginfocom.swing.MigLayout;

import com.explodingpixels.widgets.ImageButton;

import Images.RIM.ImageHelper;
import Model.Event;

@SuppressWarnings("serial")
public class JEventListRenderer extends JPanel implements ListCellRenderer<Event> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Event> arg0,
			Event value, int index, boolean isSelected, boolean hasFocus) 
	{
		JPanel panel = new JPanel(new MigLayout("", "[][][][][][][][][][][][][][][][][]", "[][][][][][][]"));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		if(isSelected)
		{
			panel.setBackground(Color.gray);
		}

		JLabel label = new JLabel();
	
		label.setText(value.getEventName() + " " + value.getEventDescription() + " " +value.getEventInitiator().getUserName());
			

		panel.add(label, "cell 0 0 4 3");
		
		
		ImageButton editButton = new ImageButton(ImageHelper.loadImageIcon("viewIcon.png", "edit", -1, 16, 5));

		
		panel.add(editButton);
		
		return panel;
	}

}
