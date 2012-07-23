package Model.RIM;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;
import Model.*;
import Model.Membership.Account;
import Model.Membership.Guest;

public class InvitationGuestResponse extends Guest
{
	MySQLController db = new MySQLController();
	
	private Account guest;
	private Meal mealOption;
	private Invitation invitation;

	public InvitationGuestResponse(String userName, Meal mealOption, Invitation invitation)
	{
		super(userName);
		this.mealOption = mealOption;
		this.invitation = invitation;
	}

	/**
	 * 
	 * Getters and Setters
	 * 
	 * */
	public Meal getMealOption()
	{
		return mealOption;
	}

	public void setMealOption(Meal mealOption)
	{
		this.mealOption = mealOption;
	}

	public Invitation getInvitation()
	{
		return invitation;
	}

	public void setInvitation(Invitation invitation)
	{
		this.invitation = invitation;
	}

	/**
	 * 
	 * Database functions
	 * 
	 * */
	public InvitationGuestResponse GET_GUEST_RESPONSE(int invitationID)
	{
		InvitationGuestResponse response = null;
		ResultSet rs = null;
		String dbQuery = "SELECT * FROM " + TableNames.GUEST_TABLE + " WHERE invitationID=" +
		invitationID + "AND userName="+this.guest.getUserName();
		try
		{
			rs = db.readRequest(dbQuery);
			if (rs.next())
			{
				response = new InvitationGuestResponse(rs.getString("userName"), new Meal(rs.getInt("mealOptionID")), new Invitation(rs.getInt(invitationID)));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}

	public Meal GET_GUEST_MEAL_CHOICE()
	{
		return null;
	}

	/**
	 * Fetches and returns a list of Event(s) the Guest is attending, or has
	 * attended in the past.
	 * */
	public ArrayList<Event> GET_GUEST_EVENTS()
	{
		ArrayList<Event> eventList = null;
		return eventList;
	}
}
