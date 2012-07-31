package Model.Membership;

import java.sql.ResultSet;
import java.util.ArrayList;

import Controller.MySQLController;

public class Guest extends Account
{
	boolean onlineStatus;
	String profilePicture;
	
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
		return getFirstName() +" "+ getLastName() +" "+ getEmail() +" "+ getTelephoneNo() +" "+ getHandphoneNo();
	}

	//ameen method
public ArrayList<String> getNumberOfGuests(String eventName){
		
		ArrayList<String> e1 = new ArrayList<String>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT COUNT(*) FROM Invitation i INNER JOIN Event e On e.eventID=i.eventID INNER JOIN Guest g On i.invitationID=g.invitationID WHERE e.eventName="+"'"+eventName+"'" +"AND g.response='Attending'";
		try{
			
			rs=DB.readRequest(dbQuery);
			while(rs.next()){
				
			e1.add(rs.getString("Count(*)"));
			
			for(int i=0;i<e1.size();i++){
				System.out.println(e1.get(i));
			}
			
			
			}
			
			
		}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			return e1;
	}
		
}
