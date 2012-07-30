package Model.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Controller.MySQLController;

public class Account implements Comparable<Account>
{
	public static Account currentUser;
	
	private static MySQLController db = new MySQLController();
	private String userName;
	private String password;
	private String type;
	private Date creationDate;
	private String status;
	private boolean confirmed;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String nric;
	private String school;
	private String email;
	private String address;
	private String telephoneNo;
	private String handphoneNo;
	private String secretQuestion;
	private String secretAnswer;
	private Date closureRequestDate;
	private Date closureDate;
	private String closureReason;

	public Account(){}
	
	public Account(String userName)
	{
		this.userName = userName;
	}
	
	public int compareTo(Account o)
	{
		// Do some comparison here
		return 0;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public boolean isConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getNric()
	{
		return nric;
	}

	public void setNric(String nric)
	{
		this.nric = nric;
	}

	public String getSchool()
	{
		return school;
	}

	public void setSchool(String school)
	{
		this.school = school;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getTelephoneNo()
	{
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo)
	{
		this.telephoneNo = telephoneNo;
	}

	public String getHandphoneNo()
	{
		return handphoneNo;
	}

	public void setHandphoneNo(String handphoneNo)
	{
		this.handphoneNo = handphoneNo;
	}

	public String getSecretQuestion()
	{
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion)
	{
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer()
	{
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer)
	{
		this.secretAnswer = secretAnswer;
	}

	public Date getClosureRequestDate()
	{
		return closureRequestDate;
	}

	public void setClosureRequestDate(Date closureRequestDate)
	{
		this.closureRequestDate = closureRequestDate;
	}

	public Date getClosureDate()
	{
		return closureDate;
	}

	public void setClosureDate(Date closureDate)
	{
		this.closureDate = closureDate;
	}

	public String getClosureReason()
	{
		return closureReason;
	}

	public void setClosureReason(String closureReason)
	{
		this.closureReason = closureReason;
	}
	
	@Override
	public String toString()
	{
		return "Account [userName=" + userName + ", password=" + password + ", type=" + type + ", creationDate=" + creationDate + ", status="
				+ status + ", confirmed=" + confirmed + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", nric=" + nric + ", school=" + school + ", email=" + email + ", address=" + address + ", telephoneNo=" + telephoneNo
				+ ", handphoneNo=" + handphoneNo + ", secretQuestion=" + secretQuestion + ", secretAnswer=" + secretAnswer + ", closureRequestDate="
				+ closureRequestDate + ", closureDate=" + closureDate + ", closureReason=" + closureReason + "]";
	}

	
	
		public boolean INITIATE_LOGIN(Account accountSet) {
			ResultSet rs = null;
			boolean valid = false;
				String dbQuery = "Select userName, password, type FROM Account WHERE userName='"+accountSet.getUserName()+"'AND password = '"+accountSet.getPassword()+"'";
				rs = db.readRequest(dbQuery);
			
				try {
					if(rs.next()){

					
						valid = true;
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}	
					
				return valid;
				}

		public Account GET_ACCOUNT(String userName) {
			Account account = null;
			ResultSet rs = null;
			boolean valid = false;
				String dbQuery = "Select * FROM Account WHERE userName='"+ userName+"'";
				rs = db.readRequest(dbQuery);
				
				try {
					if(rs.next()){
						account = new Account();
						
						account.setUserName(rs.getString("userName"));
						account.setType(rs.getString("type"));
						account.setStatus(rs.getString("status"));
						account.setFirstName(rs.getString("firstName"));
						account.setLastName(rs.getString("lastName"));
						account.setDateOfBirth(rs.getDate("dateOfBirth"));
						account.setNric(rs.getString("nric"));
						account.setSchool(rs.getString("school"));
						account.setEmail(rs.getString("email"));
						account.setAddress(rs.getString("address"));
						account.setTelephoneNo(rs.getString("telephoneNo"));
						account.setHandphoneNo(rs.getString("handphoneNo"));
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}	
					
				return account;
				}
			
		}
		
	
	


	

