package View.RIM.Guest;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import Model.Event;
import Model.Membership.Guest;
import Model.RIM.EventListModel;
import Model.RIM.GuestListModel;
import View.RIM.Components.JListGuestImportRenderer;

import java.awt.BorderLayout;
import java.util.ArrayList;

public class ViewInvitationsView extends JFrame
{
	public ViewInvitationsView() {
		ArrayList<Event> events = new Event().GET_ALL_EVENTS();
		ListModel<Event> listModel = new EventListModel(events);
		
		final JList<Event> list = new JList<Event>(listModel);

		getContentPane().add(list, BorderLayout.CENTER);
		
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String args[])
	{
		new ViewInvitationsView();
	}
}



