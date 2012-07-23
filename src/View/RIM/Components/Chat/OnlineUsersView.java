package View.RIM.Components.Chat;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Model.Membership.Guest;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import com.explodingpixels.macwidgets.HudWindow;

@SuppressWarnings("serial")
public class OnlineUsersView extends JFrame {

  public OnlineUsersView() {
    super("Online Friends");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    Container content = getContentPane();

    GuestCollection collection = new GuestCollection();
    ListModel<Guest> listModel = new GuestListModel(collection);
    JList<Guest> guestJList = new JList<Guest>(listModel);
    guestJList.setCellRenderer(new JListChatUserRenderer());
    guestJList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    guestJList.addMouseListener(new MouseListener()
	{
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			HudWindow hud = new HudWindow("Window");
			hud.getJDialog().setSize(300, 350);
			hud.getJDialog().setLocationRelativeTo(null);
			hud.getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox comboBox = HudWidgetFactory.createHudComboBox(new DefaultComboBoxModel(new String[]{"Item One", "Item Two", "Item Three"}));
			
			hud.getJDialog().add(comboBox);
			hud.getJDialog().add(HudWidgetFactory.createHudLabel("Testing"));
			hud.getJDialog().setVisible(true);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
	  
	});
    
    content.add(guestJList);
    pack();
    setVisible(true);
  }
  
  public static void main(String[] args) {
	    new OnlineUsersView();
	  }
}
