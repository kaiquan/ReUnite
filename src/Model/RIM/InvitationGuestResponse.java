package Model.RIM;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;
import Model.Meal;
import Model.Membership.Guest;

public class InvitationGuestResponse extends Guest
{
	MySQLController db = new MySQLController();
	private Meal mealOption;
	private String response;

	public InvitationGuestResponse(String userName, Meal mealOption, String response)
	{
		super(userName);
		this.mealOption = mealOption;
		this.response = response;
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


	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * 
	 * Database functions
	 * 
	 * */
	public ArrayList<InvitationGuestResponse> GET_ALL_GUEST_RESPONSE(int invitationID)
	{
		InvitationGuestResponse response = null;
		ArrayList<InvitationGuestResponse> responseList = new ArrayList<InvitationGuestResponse>();
		ResultSet rs = null;
		String dbQuery = "SELECT * FROM " + TableNames.GUEST_TABLE + " WHERE invitationID=" +
		invitationID;
		try
		{
			rs = db.readRequest(dbQuery);
			while (rs.next())
			{
				response = new InvitationGuestResponse(rs.getString("userName"), new Meal(rs.getInt("mealOptionID")), rs.getString("response"));
				responseList.add(response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return responseList;
	}
	
	public InvitationGuestResponse GET_GUEST_RESPONSE(String userName, int invitationID)
	{
		InvitationGuestResponse response = null;
		ResultSet rs = null;
		String dbQuery = "SELECT * FROM " + TableNames.GUEST_TABLE + " WHERE invitationID=" +
		invitationID + "AND userName="+userName;
		try
		{
			rs = db.readRequest(dbQuery);
			if (rs.next())
			{
				response = new InvitationGuestResponse(rs.getString("userName"), new Meal(rs.getInt("mealOptionID")), rs.getString("response"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
	
	public boolean SET_GUEST_MEAL_OPTION(int invitationID, String userName, int mealOptionID)
	{
		String dbQuery = "UPDATE Guest SET mealOptionID="+mealOptionID+" WHERE invitationID= "+invitationID+" AND userName= "+userName;
		boolean success = false;
			try
			{
				int result = db.updateRequest(dbQuery);
				if(result>0)
				{
					success=true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		return success;
	}
}
