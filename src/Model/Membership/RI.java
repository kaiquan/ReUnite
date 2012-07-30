package Model.Membership;

import Model.Event;
import Model.CGL.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Controller.MySQLController;


public class RI extends Account   {



	private static MySQLController db = new MySQLController();
	
	public String eventName;
	public String eventID;
	public String eventStatus;
	public String totalCost;
	public String amountPending;
	

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
			String handphoneNo, String secretQuestion, String secretAnswer, String closureReason) {
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
		
		
		
	}


	public RI(){}
	
	
	
	
	

	// ___________________________________________________________________Create
	// RI
	// Account___________________________________________________________________

	public boolean createRIAccount(RI account) {

		boolean success = false;
	
		String sql = "INSERT INTO Account (userName, password,type,status, firstName, lastName, nric, school, email, address, telephoneNo, handphoneNo,secretQuestion, secretAnswer)"; 
		sql +="VALUES ('"+ account.getUserName()+ "', '"+ account.getPassword()+"','"+ account.getType()+"','"+ account.getStatus()+"', '"+ account.getFirstName()+ "', '"+ account.getLastName()+ "', '"
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

	
	public ArrayList<RI> GET_Payment_FOR_RI() {
		ResultSet rs = null;
		ArrayList<RI> paymentList = new ArrayList<RI>();
			String dbQuery = "Select totalCost, amountPending FROM Purchase_Summary WHERE purchaseID='12'";
			rs = db.readRequest(dbQuery);
		
			try {
				while(rs.next()){

				
					RI ps = new RI();
				
					ps.setTotalCost(rs.getString("totalCost"));
					ps.setAmountPending(rs.getString("amountPending"));
				
					
			
					
					paymentList.add(ps);
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
				
			return paymentList;
			}

		


	public String[][] getRITableModelPayment() {
		RI riModelPayment= new RI();

		String data[][] = new String[5][13];

		try {

			ArrayList<RI> tempList1 = riModelPayment.GET_Payment_FOR_RI();
			
			for (int i = 0; i < tempList1.size(); i++) {
				data[i][0]= tempList1.get(i).getTotalCost();
				data[i][1]= tempList1.get(i).getAmountPending();
	
				
			}
		} catch (Exception e) {
		}

		return data;

	}
	
	public String[] getRITableColumnNamesPayment() {
		String col[] = { "Total Due", "Total Balance"};
		return col;

	}
	
	
	
	
	
	
	public ArrayList<Event> GET_EVENTS_FOR_RI() {
		ResultSet rs = null;
		ArrayList<Event> eventList = new ArrayList<Event>();
			String dbQuery = "Select eventID, eventStatus, eventName FROM Event WHERE userName='kaiquan88@gmail.com'";
			rs = db.readRequest(dbQuery);
		
			try {
				while(rs.next()){

					Event tempEvent = new Event();
					
				
					tempEvent.setEventStatus(rs.getString("eventStatus"));
					tempEvent.setEventName(rs.getString("eventName"));
					tempEvent.setEventID(rs.getString("eventID"));
					
			
					
					eventList.add(tempEvent);
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
				
			return eventList;
			}

		

	public String[][] getRITableModelEvent() {
		RI riModelEvent = new RI();

		String data[][] = new String[5][13];

		try {

			ArrayList<Event> tempList = riModelEvent.GET_EVENTS_FOR_RI();
			
			for (int i = 0; i < tempList.size(); i++) {
		
				data[i][0] = tempList.get(i).getEventName();
				data[i][1] = tempList.get(i).getEventID();
				data[i][2] = tempList.get(i).getEventStatus();
				
				
				
				
				

				
			}
		} catch (Exception e) {
		}

		return data;

	}
	
	public String[] getRITableColumnNamesEvent() {
		String col[] = {"Event Name", "EventID", "Status"};
		return col;

	}
	

	
	public ArrayList<RI> retrieveUser() {
		ArrayList<RI> riList = new ArrayList<RI>();
		ResultSet rs = null;

		try {
	
			String dbQuery = "Select userName, type, status, firstName, lastName, dateOfBirth, nric, school, email, address, telephoneNo, handphoneNo FROM Account";
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

			ArrayList<RI> tempList = riModel.retrieveUser();
			
			for (int i = 0; i < tempList.size(); i++) {
				data[i][0] = tempList.get(i).getUserName();
				data[i][1] = tempList.get(i).getType();
				data[i][2] = tempList.get(i).getStatus();
				//data[i][1] = tempList.get(i).getPassword();
				data[i][3] = tempList.get(i).getFirstName();
				data[i][4] = tempList.get(i).getLastName();
				//data[i][4] = tempList.get(i).getDateOfBirth();
				data[i][5] = tempList.get(i).getNric();
				data[i][6] = tempList.get(i).getSchool();
				data[i][7] = tempList.get(i).getEmail();
				data[i][8] = tempList.get(i).getTelephoneNo();
				data[i][9] = tempList.get(i).getHandphoneNo();
				
			}
		} catch (Exception e) {
		}

		return data;

	}

	public String[] getRITableColumnNames() {
		String col[] = {"Username", "Type","Status" ,"First Name", "Last Name", "Nric", "School", "Email Address", "Telephone No.","Handphone No."};
		return col;

	}
	

	// ______________________________________________________Delete RI
	// Account(Only GR Can
	// Do)________________________________________________________________________________

	public boolean deleteRIAccount(RI account) {
		boolean success = false;
		String sql = "DELETE FROM Account  where UserName = '" + getUserName()
				+ "'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;

	}

	// _____________________________________________________________________Update
	// RI Account ____________________________________________________________

	public boolean updateRIAccount(Account account) {
	
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
	
	
	public boolean updateClosure(Account account) {
	
		boolean success = false;
		
		String sql = "UPDATE Account SET userName = '"+account.getUserName()+"', closureReason='"+account.getClosureReason()+"'"+
				 " WHERE userName ='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;
	}
	
	
	
	
	
	public boolean disableRIAccount(Account account){
		boolean success = false;
		
		String sql ="UPDATE Account SET status='"+account.getStatus()+"'"+
		" WHERE userName='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;

	}
	





	
	
	public String[][] getRITableModel1() {
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
	
	public static void main(String args[]) throws ParseException {

		ArrayList<Event> list = new ArrayList();
		


	}
	
	
}
