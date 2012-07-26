package Model.RIM;

import java.util.ArrayList;

import Model.*;
import Model.Membership.*;

public class GuestCollection
{
	private Invitation invitationModel = new Invitation();
	
	private ArrayList<Guest> guests;

	public GuestCollection(ArrayList<Guest> guests)
	{
		this.guests = guests;
		this.numOfGuests = countProfiles(guests);
	}
	
	public GuestCollection(int eventID)
	{
		invitationModel.getGuestList();
	}

	public GuestCollection()
	{
		guests = new ArrayList<Guest>();
		guests.add(new Guest("Adeel Ateeque", "Online", "myPic.jpg"));
		guests.add(new Guest("Shahrikin Alias", "Online", "userIcon.png"));
		guests.add(new Guest("Lee Kai Quan", "Busy", "userIcon.png"));
		guests.add(new Guest("A.Ameenudeen", "Offline", "userIcon.png")); 
		guests.add(new Guest("Jhonny Bravo", "Away", "johnnyBravo.gif"));
		guests.add(new Guest("Brad Pitt", "Sleeping", "bradPitt.jpg"));
	}


	private int numOfGuests;

	
	public ArrayList<Guest> getProfiles()
	{
		return (guests);
	}

	public int getNumOfProfiles()
	{
		return (numOfGuests);
	}

	// Assumes the list is sorted by user name
	private int countProfiles(ArrayList<Guest> profiles)
	{
		int n = 0;
		String currentProfile, previousProfile = "None";
		for (int i = 0; i < profiles.size(); i++)
		{
			currentProfile = profiles.get(i).getFirstName();
			if (!previousProfile.equals(currentProfile))
			{
				n++;
			}
			currentProfile = previousProfile;
		}
		return (n);
	}
}