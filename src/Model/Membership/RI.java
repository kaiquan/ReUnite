package Model.Membership;



import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.MyCalendar;
import Controller.MySQLController;


public class RI extends Account   {

	


	private static MySQLController db = new MySQLController();
	
	public String eventName;
	public String eventID;
	public String eventStatus;
	public String totalCost;
	public String amountPending;
	
	@SuppressWarnings("unchecked")
	ArrayList eventList = new ArrayList();

	public String getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	public String getAmountPending() {
		return amountPending;
	}


	public void setAmountPending(String amountPending) {
		this.amountPending = amountPending;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public String getEventID() {
		return eventID;
	}


	public void setEventID(String eventID) {
		this.eventID = eventID;
	}


	public String getEventStatus() {
		return eventStatus;
	}


	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}


	
	
	
	
	




	public RI(String userName, String password
			, String type, String status,String firstName,
			String lastName, Date dateOfBirth, String nric, String address,
			String school, String email, String telephoneNo,
			String handphoneNo, String secretQuestion, String secretAnswer, String closureReason, String closureRequest) {
		super();
		super.setUserName(userName);
		super.setPassword(password);
		super.setType(type);
		super.setStatus(status);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setDateOfBirth(dateOfBirth);
		super.setNric(nric);
		super.setSchool(school);
		super.setEmail(email);
		super.setAddress(address);
		super.setTelephoneNo(telephoneNo);
		super.setHandphoneNo(handphoneNo);
		super.setSecretQuestion(secretQuestion);
		super.setSecretAnswer(secretAnswer);
		super.setClosureReason(closureReason);
		super.setClosureRequest(closureRequest);
		
		
		
		
	}


	public RI(){}
	
	
	
	
	

	// ___________________________________________________________________Create
	// RI
	// Account___________________________________________________________________

	public boolean CREATE_RI_ACCOUNT(RI account) {

		boolean success = false;
		
		String sql = "INSERT INTO Account (userName, password,type,status, firstName, lastName, nric, school, email, address, telephoneNo, handphoneNo,secretQuestion, secretAnswer)"; 
		sql +="VALUES ('"+ account.getUserName()+ "', '"+ account.getPassword()+"','"+ account.getType()+"','"+ account.getStatus()+"', '"+ account.getFirstName()+ 
		"', '"+ account.getLastName()+ "', '"
		+ account.getNric()+ "', '"+ account.getSchool()+ "', '"+ account.getEmail()+ "', '"+ account.getAddress()+ "','"+ account.getTelephoneNo()
				+ "','"+ account.getHandphoneNo()+ "','"+ account.getSecretQuestion()
	
					+ "','"+ account.getSecretAnswer()+"')";
		try {
		
	
			if (db.updateRequest(sql) == 1)
				success = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return success;

	}

	// _______________________________________________________________Retrieve
	// All RI Account (GR
	// View)___________________________________________________
	
	
	public Object[][] GET_EVENTS_ALL_FOR_RI() {
		ArrayList<Object[]> tempList = new ArrayList<Object[]>();
		Object[][] records = null;
		ResultSet rs = null;
			String dbQuery = "SELECT * FROM Event ";
			rs = db.readRequest(dbQuery);
			try {
				while(rs.next())
				{
					Object[] row = new Object[8];
					
					row[0] = rs.getString("eventID");
					row[1] =  rs.getString("packageID");
					row[2] = rs.getString("userName");
					row[3] = rs.getString("eventStatus");
					row[4] = rs.getString("eventDate");
					row[5] = rs.getString("eventTime");
					row[6] = rs.getString("eventName");
					row[7] = rs.getString("eventDescription");
			
					
					
		
					tempList.add(row);
				}
				records = new Object[tempList.size()][8];
				for(int i = 0; i<tempList.size(); i++)
				{
					records[i][0] = tempList.get(i)[0];
					records[i][1] = tempList.get(i)[1];
					records[i][2] = tempList.get(i)[2];
					records[i][3] = tempList.get(i)[3];
					records[i][4] = tempList.get(i)[4];
					records[i][5] = tempList.get(i)[5];
					records[i][6] = tempList.get(i)[6];
					records[i][7] = tempList.get(i)[7];
			
					
					
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
				
			return records;
			}


	

	
	public String[] getPurchaseColumnNames() {
		String col[] = {"Purchase ID", "Event ID","total Cost" ,"Amount Pending"};
		return col;

	}
	
	
	public String[] getRIEventNPurchaseColumnNames() {
		String col[] = {"Event ID", "packageID","userName" ,"eventStatus", "Event Date", "Event Time", "Event Name", "Event Description"};
		return col;

	}
	
	
	
	public Object[][] GET_EVENTS_FOR_RI() {
		ArrayList<Object[]> tempList = new ArrayList<Object[]>();
		Object[][] records = null;
		ResultSet rs = null;
			String dbQuery = "SELECT * FROM Event e INNER JOIN Purchase_Summary p ON e.eventID = p.eventID WHERE e.eventID IN (SELECT ev.eventID FROM Event ev WHERE ev.userName ='"+Account.currentUser.getUserName()+"') Order by eventStatus , amountPending ";
			rs = db.readRequest(dbQuery);
			try {
				while(rs.next())
				{
					Object[] row = new Object[5];
					
					row[0] = rs.getString("eventID");
					row[1] =  rs.getString("eventStatus");
					row[2] = rs.getString("eventName");
					row[3] = rs.getString("totalCost");
					row[4] = rs.getString("amountPending");
		
					tempList.add(row);
				}
				records = new Object[tempList.size()][5];
				for(int i = 0; i<tempList.size(); i++)
				{
					records[i][0] = tempList.get(i)[0];
					records[i][1] = tempList.get(i)[1];
					records[i][2] = tempList.get(i)[2];
					records[i][3] = tempList.get(i)[3];
					records[i][4] = tempList.get(i)[4];
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
				
			return records;
			}

		

	public String[] getRIEventColumnNames() {
		String col[] = {"Event ID", "Event Status","Event Name" ,"Total Due", "Total Balance"};
		return col;

	}

	
	public ArrayList<RI> RETRIEVE_USER() {
		ArrayList<RI> riList = new ArrayList<RI>();
		ResultSet rs = null;

		try {
	
			String dbQuery = "Select userName, type, status, firstName, lastName, dateOfBirth, nric, school, email, address, telephoneNo, handphoneNo,closureReason, closureRequest FROM Account Where type='RI' OR type = 'GR'  Order by type   ";
			rs = db.readRequest(dbQuery);

			while (rs.next()) {
				RI tempRI = new RI();
			
				tempRI.setUserName(rs.getString("userName"));
				tempRI.setType(rs.getString("type"));
				tempRI.setStatus(rs.getString("status"));
				tempRI.setFirstName(rs.getString("firstName"));
				tempRI.setLastName(rs.getString("lastName"));
				tempRI.setDateOfBirth(rs.getDate("dateOfBirth"));
				tempRI.setNric(rs.getString("nric"));
				tempRI.setSchool(rs.getString("school"));
				tempRI.setEmail(rs.getString("email"));
				tempRI.setAddress(rs.getString("address"));
				tempRI.setTelephoneNo(rs.getString("telephoneNo"));
				tempRI.setHandphoneNo(rs.getString("handphoneNo"));
				tempRI.setClosureReason(rs.getString("closureReason"));
				tempRI.setClosureRequest(rs.getString("closureRequest"));
				// tempRI.setSecretQuestion(rs.getString("secretQuestion"));
				// tempRI.setSecretAnswer(rs.getString("secretAnswer"));
				riList.add(tempRI);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return riList;

	}
	





	
	public String[][] getRITableModel() {
		RI riModel = new RI();

		String data[][] = new String[13][13];

		try {

			ArrayList<RI> tempList = riModel.RETRIEVE_USER();
			
			for (int i = 0; i < tempList.size(); i++) {
				data[i][0] = tempList.get(i).getUserName();
				data[i][1] = tempList.get(i).getType();
				data[i][2] = tempList.get(i).getStatus();
				//data[i][1] = tempList.get(i).getPassword();
				data[i][3] = tempList.get(i).getFirstName();
				data[i][4] = tempList.get(i).getLastName();
				//data[i[4] = tempList.get(i).getDateOfBirth();
				data[i][5] = tempList.get(i).getNric();
				data[i][6] = tempList.get(i).getSchool();
				data[i][7] = tempList.get(i).getEmail();
				data[i][8] = tempList.get(i).getAddress();
				data[i][9] = tempList.get(i).getTelephoneNo();
				data[i][10] = tempList.get(i).getHandphoneNo();
				data[i][11] = tempList.get(i).getClosureReason();
				data[i][12] = tempList.get(i).getClosureRequest();
				
				
			}
		} catch (Exception e) {
		}

		return data;
	}
	

	public String[] getRITableColumnNames() {
		String col[] = {"Username", "Type","Status" ,"First Name", "Last Name", "Nric", "School", "Email Address","Residential Address", "Telephone No.","Handphone No.","Closure Request Reason","closure Request"};
		return col;

	}
	

	public String[] getRITableColumnNames1() {
		String col[] = {"Username", "Type","Status" ,"First Name", "Last Name", "Nric", "School", "Email Address","Residential Address", "Telephone No.","Handphone No."};
		return col;

	}
	

	// ______________________________________________________Delete RI
	// Account(Only GR Can
	// Do)________________________________________________________________________________

	public boolean DELETE_RI_ACCOUNT(RI account) {
		boolean success = false;
		String sql = "DELETE * FROM Account  where userName = '" + account.getUserName()+ "'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;

	}

	// _____________________________________________________________________Update
	// RI Account ____________________________________________________________

	public boolean UPDATE_RI_ACCOUNT(Account account) {
	
		@SuppressWarnings("unused")
		RI updateRi = new RI();
		
		boolean success = false;
		
		String sql = "UPDATE Account SET userName = '"+account.getUserName()+"', firstName= '"+account.getFirstName()+"', lastName= '"+account.getLastName()+"'," +
				" nric= '"+account.getNric()+"', school= '"+account.getSchool()+"', email='"+ account.getEmail()+"', telephoneNo='"+account.getTelephoneNo()+"'," +
				" handphoneNo='"+account.getHandphoneNo()+"'"+
				 " WHERE userName ='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;
	}
	
	
	public boolean UPDATE_CLOSURE(Account account) {
	
		boolean success = false;
		
		String sql = "UPDATE Account SET userName = '"+Account.currentUser.getUserName()+"', closureReason='"+account.getClosureReason()+"', closureRequest='Request Closure+'"+
				 " WHERE userName ='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;
	}
	
	
	
	
	
	public boolean DISABLE_ACCOUNT(Account account){
		boolean success = false;
		
		String sql ="UPDATE Account SET status='"+account.getStatus()+"'"+
		" WHERE userName='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;

	}
	





	
	
	public String[][] getRITableModel1() {
		@SuppressWarnings("unused")
		RI riModel1 = new RI();

		String data[][] = new String[1][13];

		try {

			
			for (int i = 0; i < 1; i++) {
				data[i][0] = Account.currentUser.getUserName();
				data[i][1] =Account.currentUser.getType();
				data[i][2] = Account.currentUser.getStatus();
				//data[i][1] = tempList.get(i).getPassword();
				data[i][3] = Account.currentUser.getFirstName();
				data[i][4] = Account.currentUser.getLastName();
				//data[i][4] = tempList.get(i).getDateOfBirth();
				data[i][5] = Account.currentUser.getNric();
				data[i][6] = Account.currentUser.getSchool();
				data[i][7] = Account.currentUser.getEmail();
				data[i][8] = Account.currentUser.getTelephoneNo();
				data[i][9] = Account.currentUser.getHandphoneNo();
				
			}
		} catch (Exception e) {
		}

		return data;

	}
	
	
public ArrayList<RI> GET_RI_EMAIL(String eventName){
		
		
		ArrayList<RI> e1 = new ArrayList<RI>();
		ResultSet rs = null;
		
		String dbQuery;
		
		
		dbQuery = "SELECT a.email FROM Account a INNER JOIN Event e ON e.userName=a.userName WHERE e.eventName='"+eventName+"'";
		try{
			
			rs=db.readRequest(dbQuery);
			while(rs.next()){
				RI ri = new RI();
				ri.setEmail(rs.getString("a.email"));
				e1.add(ri);
				
			}
			
						
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
			
						
			return e1;
		}
		
	
}
