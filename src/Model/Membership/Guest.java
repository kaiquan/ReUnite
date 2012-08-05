package Model.Membership;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;
import Controller.RIM.Utils.DateHelper;
import Model.RIM.TableNames;

public class Guest extends Account
{
	private static DateHelper dateHelper = new DateHelper();
	
	boolean onlineStatus;
	String profilePicture;
	private String noOfGuests;

	public String getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(String noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	private MySQLController DB = new MySQLController();

	public Guest()
	{
	}

	public Guest(String userName)
	{
		setUserName(userName);
	}

	public Guest(String name, boolean onlineStatus, String profilePicture, String school)
	{
		super.setFirstName(name);
		setOnlineStatus(onlineStatus);
		setLastName("Ateeque");
		setProfilePicture(profilePicture);
		setSchool(school);
	}

	public boolean getOnlineStatus()
	{
		return (onlineStatus);
	}

	public void setOnlineStatus(boolean onlineStatus)
	{
		this.onlineStatus = onlineStatus;
	}

	public String getProfilePicture()
	{
		return (profilePicture);
	}

	public void setProfilePicture(String profilePicture)
	{
		this.profilePicture = profilePicture;
	}

	public String toString()
	{
		return getFirstName() + " " + getLastName() + " " + getEmail() + " " + getTelephoneNo() + " " + getHandphoneNo();
	}

	// ameen method
	public ArrayList<Guest> getNumberOfGuests(String eventName)
	{

		ArrayList<Guest> e1 = new ArrayList<Guest>();
		ResultSet rs = null;

		String dbQuery;

		dbQuery = "SELECT COUNT(*) FROM Invitation i INNER JOIN Event e On e.eventID=i.eventID INNER JOIN Guest g On i.invitationID=g.invitationID WHERE e.eventName=" + "'" + eventName + "'"
				+ "AND g.response='Attending'";
		try
		{

			rs = DB.readRequest(dbQuery);
			while (rs.next())
			{
				Guest g = new Guest();
				g.setNoOfGuests(rs.getString("Count(*)"));
				e1.add(g);
			

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return e1;
	}

	public String GET_RESPONSE(String userName, int invitationID)
	{
		ResultSet rs = null;
		String response = null;
		String dbQuery = "SELECT response FROM Guest WHERE userName= '"+userName+"' AND invitationID = "+invitationID;
		try
		{
			rs = DB.readRequest(dbQuery);
			while (rs.next())
			{
				response = rs.getString("response");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return response;
	}
	
	public boolean SET_RESPONSE(String userName, int invitationID, String response)
	{
		boolean success = false;
		String dbQuery = "UPDATE Guest SET response = '"+response+"' WHERE userName= '"+userName+"' AND invitationID = "+invitationID;
		try
		{
			int result = DB.updateRequest(dbQuery);
			if(result>0)
			{
				success = true;
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return success;
	}
	
	public ArrayList<Guest> GET_ALL_GUESTS()
	{
		ArrayList<Guest> e1 = new ArrayList<Guest>();
		ResultSet rs = null;

		String dbQuery;

		dbQuery = "SELECT * FROM Account WHERE type='Guest'";
		try
		{

			rs = DB.readRequest(dbQuery);
			while (rs.next())
			{
				Guest guest = new Guest();

				guest.setUserName(rs.getString("userName"));
				guest.setType(rs.getString("type"));
				guest.setStatus(rs.getString("status"));
				guest.setFirstName(rs.getString("firstName"));
				guest.setLastName(rs.getString("lastName"));
				guest.setDateOfBirth(rs.getDate("dateOfBirth"));
				guest.setNric(rs.getString("nric"));
				guest.setSchool(rs.getString("school"));
				guest.setEmail(rs.getString("email"));
				guest.setAddress(rs.getString("address"));
				guest.setTelephoneNo(rs.getString("telephoneNo"));
				guest.setHandphoneNo(rs.getString("handphoneNo"));
				guest.setProfilePicture(rs.getString("profilePicture"));

				e1.add(guest);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return e1;
	}
	
	public void ADD_GUEST_TO_EVENT(String userName, int invitationID)
	{
		String dbQuery = "INSERT INTO Guest (userName, invitationID) VALUES ('"+userName+"', "+invitationID+")";
		try
		{
			DB.updateRequest(dbQuery);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Guest GET_GUEST_ACCOUNT(String userName)
	{
		Guest guest = null;
		ResultSet rs = null;

		String dbQuery;

		dbQuery = "SELECT * FROM Account WHERE userName = '"+userName+"' AND type='Guest'";
		try
		{

			rs = DB.readRequest(dbQuery);
			if (rs.next())
			{

				guest = new Guest();

				guest.setUserName(rs.getString("userName"));
				guest.setType(rs.getString("type"));
				guest.setStatus(rs.getString("status"));
				guest.setFirstName(rs.getString("firstName"));
				guest.setLastName(rs.getString("lastName"));
				guest.setDateOfBirth(rs.getDate("dateOfBirth"));
				guest.setNric(rs.getString("nric"));
				guest.setSchool(rs.getString("school"));
				guest.setEmail(rs.getString("email"));
				guest.setAddress(rs.getString("address"));
				guest.setTelephoneNo(rs.getString("telephoneNo"));
				guest.setHandphoneNo(rs.getString("handphoneNo"));
				guest.setProfilePicture(rs.getString("profilePicture"));

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return guest;
		
	}

	public boolean CREATE_GUEST_ACCOUNT(Guest account)
	{
		boolean success = false;
		
		String sql = "INSERT INTO Account(userName, password, type, status, " +
				"confirmed, firstName, lastName, dateOfBirth, nric, school, email," +
				"profilePicture, address, handphoneNo)"+
				" VALUES ('"+account.getUserName()+"', '"
							+account.getNric()+"', '"
							+"Guest"+"', '"
							+"Active"+"', "
							+1+", '"
							+account.getFirstName()+"', '"
							+account.getLastName()+"', '"
							+dateHelper.dateToString(account.getDateOfBirth(), TableNames.DATE_FORMAT_SIMPLE)+"', '" 
							+account.getNric()+"', '"
							+account.getSchool()+"', '"
							+account.getEmail()+"', '"
							+account.getProfilePicture()+"', '"
							+account.getAddress()+"', '"
							+account.getHandphoneNo()+"'"
				+") " +
				"ON DUPLICATE " +
				"KEY UPDATE " + 
				"password = VALUES(password), "+ 
				"type = VALUES(type), "+
				"status = VALUES(status), "+
				"confirmed = VALUES (confirmed), "+
				"firstName = VALUES(firstName), "+ 
				"lastName = VALUES(lastName), "+
				"dateOfBirth = VALUES(dateOfBirth), "+
				"nric = VALUES(nric), "+
				"school = VALUES(school), "+ 
				"email = VALUES(email),"+
				"profilePicture = VALUES(profilePicture), "+ 
				"address=VALUES(address), "+
				"handphoneNo = VALUES(handphoneNo) ";
		try
		{
			if (DB.updateRequest(sql) > 0)
			{
				System.out.println("Successfully created/updated guest account...");
				success = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return success;
	}

	public static void main(String args[])
	{
		Guest guestModel = new Guest();
		guestModel.setUserName("adeelateeque@hotmail.com");
		guestModel.setPassword("adeel");
		guestModel.setType("Guest");
		guestModel.setStatus("Enabled");
		guestModel.setFirstName("Adeel");
		guestModel.setLastName("Ateeque");
		guestModel.setDateOfBirth(dateHelper.parseDate("1990-10-07", TableNames.DATE_FORMAT_SIMPLE));
		guestModel.setNric("S9375230I");
		guestModel.setSchool("Nanyang Polytechnic");
		guestModel.setEmail("adeelateeque@hotmail.com");
		guestModel.setProfilePicture("myPic.png");
		guestModel.setAddress("I live somewhere");
		guestModel.setTelephoneNo("64974107");
		guestModel.setHandphoneNo("97761013");
		guestModel.CREATE_GUEST_ACCOUNT(guestModel);
	}
}
