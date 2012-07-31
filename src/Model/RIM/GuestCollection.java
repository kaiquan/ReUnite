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
		guests.add(new Guest("Adeel Ateeque", true, "myPic.jpg", "Nanyang Polytechnic"));
		guests.add(new Guest("Shahrikin Alias", false, "userIcon.png", "RP"));
		guests.add(new Guest("Lee Kai Quan", false, "userIcon.png", "Ngee Ann"));
		guests.add(new Guest("A.Ameenudeen", false, "userIcon.png", "SP")); 
		guests.add(new Guest("Jhonny Bravo", true, "johnnyBravo.gif", "National Poly"));
		guests.add(new Guest("Brad Pitt", true, "bradPitt.jpg", "NYP"));
		guests.add(new Guest("Adeel Ateeque", false, "myPic.jpg", "Nanyang Polytechnic"));
		guests.add(new Guest("Shahrikin Alias", false, "userIcon.png", "RP"));
		guests.add(new Guest("Lee Kai Quan", true, "userIcon.png", "Ngee Ann"));
		guests.add(new Guest("A.Ameenudeen", false, "userIcon.png", "SP")); 
		guests.add(new Guest("Jhonny Bravo", false, "johnnyBravo.gif", "National Poly"));
		guests.add(new Guest("Brad Pitt", true, "bradPitt.jpg", "NYP"));
		guests.add(new Guest("Adeel Ateeque", true, "myPic.jpg", "Nanyang Polytechnic"));
		guests.add(new Guest("Shahrikin Alias", true, "userIcon.png", "RP"));
		guests.add(new Guest("Lee Kai Quan", false, "userIcon.png", "Ngee Ann"));
		guests.add(new Guest("A.Ameenudeen", false, "userIcon.png", "SP")); 
		guests.add(new Guest("Jhonny Bravo", false, "johnnyBravo.gif", "National Poly"));
		guests.add(new Guest("Brad Pitt", false, "bradPitt.jpg", "NYP"));
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