package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;

import Controller.EmailController;
import Controller.MySQLController;
import Controller.RIM.Utils.DateHelper;
import Model.Membership.Guest;
import Model.RIM.TableNames;

public class Invitation
{
	private static MySQLController db = new MySQLController();
	private static DateHelper dateHelper = new DateHelper();
	private Event eventModel = new Event();

	private int invitationID;
	private Event event;
	private Date expiryDate;
	private Date dateCreated;
	private ArrayList<Guest> guestList;

	@Override
	public String toString()
	{
		return "EventInvitation [invitationID=" + invitationID + ", event=" + event + ", expiryDate=" + expiryDate + ", dateCreated=" + dateCreated + ", guestList=" + guestList + "]";
	}

	/**
	 * 
	 * Constructors
	 * 
	 * */
	public Invitation()
	{

	}

	public Invitation(int invitationID)
	{
		this.invitationID = invitationID;
	}

	/**
	 * 
	 * Getters and Setters
	 * 
	 * */

	public int getInvitationID()
	{
		return invitationID;
	}

	public void setInvitationID(int invitationID)
	{
		this.invitationID = invitationID;
	}

	public Date getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public ArrayList<Guest> getGuestList()
	{
		return guestList;
	}

	public void setGuestList(ArrayList<Guest> guestList)
	{
		this.guestList = guestList;
	}

	/**
	 * 
	 * Database methods
	 * 
	 * */
	public int GET_INVITATION_COUNT()
	{
		ResultSet rs = null;
		int count = 0;
		String dbQuery = "SELECT COUNT(*) FROM " + TableNames.INVITATION_TABLE;
		try
		{
			rs = db.readRequest(dbQuery);
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<Invitation> GET_ALL_INVITATIONS()
	{
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();

		ArrayList<Event> eventList = eventModel.GET_ALL_EVENTS();

		for (int i = 0; i < eventList.size(); i++)
		{
			Invitation invitation;
			invitation = eventList.get(i).getEventInvitation();
			invitation.setEvent(eventList.get(i));
			invitationList.add(invitation);
		}

		return invitationList;
	}

	public int GET_INVITATION_RESPONSE()
	{
		ResultSet rs = null;
		int response = 0;

		String dbQuery = "SELECT ROUND(((SELECT COUNT(*) FROM Guest WHERE invitationID = " + this.invitationID + " AND response = 'ATTENDING')/(SELECT COUNT(*) FROM Guest WHERE invitationID = "
				+ this.invitationID + ")*100), 0) AS Response";
		try
		{
			rs = db.readRequest(dbQuery);
			if (rs.next())
			{
				response = rs.getInt("Response");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return response;

	}

	public Event GET_INVITATION_EVENT(int invitationID)
	{
		ResultSet rs = null;
		Event event = null;
		String dbQuery = "SELECT e.* FROM Event e INNER JOIN Invitation i ON e.eventID = i.eventID WHERE i.invitationID = " + invitationID;
		try
		{
			rs = db.readRequest(dbQuery);
			if (rs.next())
			{
				event = new Event(rs.getInt("eventID"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return event;
	}

	public Invitation GET_INVITATION_BY_ID(int invitationID)
	{
		ResultSet rs = null;
		Invitation invitation = null;

		String dbQuery = "SELECT * FROM " + TableNames.INVITATION_TABLE + " i  INNER JOIN " + TableNames.EVENT_TABLE + " e ON i.eventID = e.eventID" + " INNER JOIN " + TableNames.ACCOUNT_TABLE
				+ " a ON e.userName = a.userName WHERE invitationID = " + invitationID;

		try
		{
			rs = db.readRequest(dbQuery);
			if (rs.next())
			{
				invitation = new Invitation(rs.getInt("invitationID"));
				invitation.setDateCreated(dateHelper.parseDate(rs.getString("dateCreated"), "yyyy-MM-dd HH:mm:ss"));
				invitation.setExpiryDate(dateHelper.parseDate(rs.getString("expiryDate"), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return invitation;
	}

	public int GET_GUEST_COUNT()
	{
		ResultSet rs = null;
		int count = 0;
		String dbQuery = "SELECT COUNT(*) FROM " + TableNames.GUEST_TABLE + " WHERE invitationID = " + this.invitationID;
		try
		{

			rs = db.readRequest(dbQuery);
			while (rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<Guest> GET_ALL_GUESTS()
	{
		ResultSet rs = null;
		ArrayList<Guest> guestList = new ArrayList<Guest>();
		String dbQuery = "SELECT * FROM " + TableNames.GUEST_TABLE + " i INNER JOIN  " + TableNames.ACCOUNT_TABLE + " a ON i.userName = a.userName WHERE invitationID = " + invitationID;
		try
		{
			rs = db.readRequest(dbQuery);
			while (rs.next())
			{
				Guest guest = new Guest();
				guest.setUserName(rs.getString("userName"));
				guest.setFirstName(rs.getString("firstName"));
				guest.setLastName(rs.getString("lastName"));
				guest.setEmail(rs.getString("email"));
				guest.setHandphoneNo(rs.getString("handPhoneNo"));
				guest.setTelephoneNo(rs.getString("telephoneNo"));
				guest.setNric(rs.getString("nric"));

				guestList.add(guest);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return guestList;
	}

	public ArrayList<Guest> GET_ALL_ATTENDING_GUESTS(String eventName, String eventDate)
	{
		ResultSet rs = null;
		ArrayList<Guest> guestList = new ArrayList<Guest>();
		String dbQuery = "SELECT * FROM Invitation i INNER JOIN Guest g ON g.invitationID=i.invitationID INNER JOIN Account a ON a.userName=g.userName WHERE i.eventID =(SELECT eventID FROM Event WHERE eventName='"
				+ eventName + "'" + " AND eventDate='" + eventDate + "')" + " AND g.response='Attending'";
		try
		{
			rs = db.readRequest(dbQuery);
			while (rs.next())
			{
				Guest guest = new Guest();
				guest.setUserName(rs.getString("userName"));
				guest.setFirstName(rs.getString("firstName"));
				guest.setLastName(rs.getString("lastName"));
				guest.setEmail(rs.getString("email"));
				guest.setHandphoneNo(rs.getString("handPhoneNo"));
				guest.setTelephoneNo(rs.getString("telephoneNo"));
				guest.setNric(rs.getString("nric"));

				guestList.add(guest);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return guestList;
	}

	public boolean CREATE_INVITATION(Invitation invitation, int eventID)
	{
		int success = 0;
		String dbQuery = "INSERT INTO " + TableNames.INVITATION_TABLE + " (eventID,dateCreated,expiryDate)" + " VALUES (" + invitation.event.getEventID() + ", " + "'"
				+ dateHelper.dateToString(invitation.getDateCreated(), "yyyy-MM-dd HH:mm:ss") + "'" + ", " + "'" + dateHelper.dateToString(invitation.getExpiryDate(), "yyyy-MM-dd HH:mm:ss") + "'"
				+ ")";
		try
		{
			success = db.updateRequest(dbQuery);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (success == 1)
		{
			int invitationID = new Event().GET_INVITATION_ID_FOR_EVENT(eventID);
			System.out.println("Successfully inserted new invitation, sending out emails");
			ArrayList<String> emailList = new ArrayList<String>();
			for (Guest guest : invitation.getGuestList())
			{
				emailList.add(guest.getEmail());
				Guest guestModel = new Guest();
				guestModel.setUserName(guest.getEmail() != null ? guest.getEmail() : "");
				guestModel.setPassword(guest.getNric() != null ? guest.getNric() : "");
				guestModel.setType("Guest");
				guestModel.setStatus("Enabled");
				guestModel.setFirstName(guest.getFirstName() != null ? guest.getFirstName() : "");
				guestModel.setLastName(guest.getLastName() != null ? guest.getLastName() : "");
				guestModel.setDateOfBirth(guest.getDateOfBirth() != null ? guest.getDateOfBirth() : dateHelper.parseDate("1990-10-07", TableNames.DATE_FORMAT_SIMPLE));
				guestModel.setNric(guest.getNric() != null ? guest.getNric() : "");
				guestModel.setSchool(guest.getSchool() != null ? StringEscapeUtils.escapeSql(guest.getSchool()) : "");
				guestModel.setEmail(guest.getEmail() != null ? guest.getEmail() : "");
				guestModel.setProfilePicture(guest.getProfilePicture() != null ? StringEscapeUtils.escapeSql(guest.getProfilePicture()) : "");
				guestModel.setAddress(guest.getAddress() != null ? StringEscapeUtils.escapeSql(guest.getAddress()) : "");
				guestModel.setTelephoneNo(guest.getTelephoneNo() != null ? guest.getTelephoneNo() : "");
				guestModel.setHandphoneNo(guest.getHandphoneNo() != null ? guest.getHandphoneNo() : "");
				guestModel.CREATE_GUEST_ACCOUNT(guestModel);
				guestModel.ADD_GUEST_TO_EVENT(guestModel.getUserName(), invitationID);
			}
			
			String[] emails = new String[emailList.size()];
			emailList.toArray(emails);
			EmailController emailController = new EmailController();
			try
			{
				emailController.sendEmail("text", emails, "You are invited", "I hope you come", null, "Invitation");
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean DELETE_INVITATION(int invitationID)
	{
		int success = 0;
		String dbQuery = "DELETE FROM " + TableNames.INVITATION_TABLE + " WHERE invitationID = " + invitationID;

		try
		{
			success = db.updateRequest(dbQuery);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (success == 1)
		{
			System.out.println("Successfully deleted invitation with ID = " + invitationID);
			return true;
		}
		return false;
	}

	public static void main(String args[])
	{
		// Invitation invitation = new Invitation();
		// ArrayList<Invitation> invitationList = invitation.GET_ALL_INVITATIONS();
		//
		// for(int i = 0; i < invitationList.size(); i++)
		// {
		// System.out.println(invitationList.get(i));
		// }

		// Invitation test= new Invitation();
		// ArrayList<Guest> invitationList = test.GET_ALL_ATTENDING_GUESTS("e1","2012-07-28");
		// String[] email= new String[invitationList.size()];
		//
		// for(int i=0;i<invitationList.size();i++)
		// {
		//
		// email[i]=invitationList.get(i).getEmail();
		// }
		//
		// for(int i=0;i<email.length;i++){
		// System.out.println(email[i]);
		// }
		//
	}

}
