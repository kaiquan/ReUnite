package Model.Membership;

public class Guest extends Account
{
	String onlineStatus;
	String profilePicture;
	
	public Guest()
	{
	}
	
	public Guest(String userName)
	{
		super(userName);
	}
	
	public Guest(String name, String onlineStatus, String profilePicture)
	{
		super.setFirstName(name);
		setOnlineStatus(onlineStatus);
		setLastName("Ateeque");
		setProfilePicture(profilePicture);
	}

	public String getOnlineStatus()
	{
		return (onlineStatus);
	}

	public void setOnlineStatus(String onlineStatus)
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

}
