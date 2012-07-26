package View.RIM.Guest;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import Model.Event;
import Model.RIM.EventCollection;
import Model.RIM.EventListModel;
import View.RIM.Components.JEventListRenderer;

@SuppressWarnings("serial")
public class ViewInvitationsView extends JFrame
{
	public ViewInvitationsView() {
		ArrayList<Event> events = new Event().GET_ALL_EVENTS();
		EventCollection collection = new EventCollection(events);
		ListModel<Event> listModel = new EventListModel(collection);
		final JList<Event> list = new JList<Event>(listModel);
		list.setCellRenderer(new JEventListRenderer());


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String args[])
	{
		new ViewInvitationsView();
	}
}



