package Model.RIM;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import Model.Event;

public class EventListModel implements ListModel<Event>
{
	private EventCollection events;
	
	public EventListModel(EventCollection events)
	{
		this.events = events;
	}

	@Override
	public Event getElementAt(int index) {
		return (events.getEvents().get(index));
	}

	@Override
	public int getSize() {
		return (events.getEvents().size());
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeListDataListener(ListDataListener l) {
		
	}
	
}

