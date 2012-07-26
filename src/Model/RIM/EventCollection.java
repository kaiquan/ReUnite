package Model.RIM;

import java.util.ArrayList;

import Model.*;

public class EventCollection
{
	private int numOfEvents;
	
	private Invitation invitationModel = new Invitation();
	
	private ArrayList<Event> events;

	public EventCollection(ArrayList<Event> events)
	{
		this.events = events;
		this.numOfEvents = countEvents(events);
	}
	
	public EventCollection(int eventID)
	{
		invitationModel.getGuestList();
	}

	public EventCollection()
	{
		events = new ArrayList<Event>();
	}

	public ArrayList<Event> getEvents()
	{
		return (events);
	}

	public int getNumOfEvents()
	{
		return numOfEvents;
	}

	// Assumes the list is sorted by user name
	private int countEvents(ArrayList<Event> events)
	{
		int n = 0;
		String currentProfile;
		String previousProfile = "None";
		for (int i = 0; i < events.size(); i++)
		{
			currentProfile = events.get(i).getEventID();
			if (!previousProfile.equals(currentProfile))
			{
				n++;
			}
			currentProfile = previousProfile;
		}
		return (n);
	}
}