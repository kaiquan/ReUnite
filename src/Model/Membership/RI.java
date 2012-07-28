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
			String handphoneNo, String secretQuestion, String secretAnswer) {
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
		
		
		
		
	}


	public RI(){}
	
	
	
	
	

	// ___________________________________________________________________Create
	// RI
	// Account___________________________________________________________________

	public boolean createRIAccount(RI account) {

		boolean success = false;
	
		String sql = "INSERT INTO Account (userName, password, firstName, lastName, nric, school, email, address, telephoneNo, handphoneNo, secretAnswer)"; 
		sql +="VALUES ('"+ account.getUserName()+ "', '"+ account.getPassword()+"', '"+ account.getFirstName()+ "', '"+ account.getLastName()+ "', '"
		+ account.getNric()+ "', '"+ account.getSchool()+ "', '"+ account.getEmail()+ "', '"+ account.getAddress()+ "','"+ account.getTelephoneNo()
				+ "','"+ account.getHandphoneNo()
				+ "','"+ account.getSecretAnswer()+"')";

			//	+ account.getSecretQuestion()
			//	+ "','"
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

	
	public ArrayList<Purchase_Summary> GET_Payment_FOR_RI() {
		ResultSet rs = null;
		ArrayList<Purchase_Summary> paymentList = new ArrayList<Purchase_Summary>();
			String dbQuery = "Select purchaseID, totalCost, amountPending FROM Purchase_Summary WHERE eventID='2'";
			rs = db.readRequest(dbQuery);
		
			try {
				while(rs.next()){

				
					Purchase_Summary ps = new Purchase_Summary();
				
					ps.setTotalCost(rs.getString("totalCost"));
					ps.setAmountPending(rs.getString("amountPending"));
					
			
					
					paymentList.add(ps);
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}	
				
			return paymentList;
			}

		
//	ps.setTotalCost(rs.getString("totalCost"));
//	ps.setAmountPending(rs.getString("amountPending"));


	public String[][] getRITableModelPayment() {
		RI riModelPayment= new RI();

		String data[][] = new String[5][2];

		try {

			ArrayList<Purchase_Summary> tempList = riModelPayment.GET_Payment_FOR_RI();
			
			for (int i = 0; i < tempList.size(); i++) {
				data[i][0]= tempList.get(i).getTotalCost();
				data[i][1]= tempList.get(i).getAmountPending();
				
				
				
				
				

				
			}
		} catch (Exception e) {
		}

		return data;

	}
	
	public String[] getRITableColumnNamesPayment() {
		String col[] = {"Purchase ID", "Total Due", "Total Balance"};
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

		
//	ps.setTotalCost(rs.getString("totalCost"));
//	ps.setAmountPending(rs.getString("amountPending"));
//	data[i][3]= tempList.get(i).getTotalCost();
//	data[i][4]= tempList.get(i).getAmountPending();

	
	public String[][] getRITableModelEvent() {
		RI riModelEvent = new RI();

		String data[][] = new String[5][13];

		try {

			ArrayList<Event> tempList = riModelEvent.GET_EVENTS_FOR_RI();
			
			for (int i = 0; i < tempList.size(); i++) {
				//data[i][0] = tempList.get(i).getUserName();
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
	
	
	
	
	
	
	public boolean disableRIAccount(Account account){
		boolean success = false;
		
		String sql ="UPDATE Account SET status='"+account.getStatus()+"'"+
		" WHERE userName='"+account.getUserName()+"'";
		if (db.updateRequest(sql) == 1)
			success = true;
		return success;

	}
	

	public static void main(String args[]) throws ParseException {

		ArrayList<Event> list = new ArrayList();
		
		
		
		

		
		

	}

	public ArrayList<RI> retrieveSingleUser() {
		ArrayList<RI> riList1 = new ArrayList<RI>();
		ResultSet rs = null;

		try {
	
			String dbQuery = "Select userName, type, status, firstName, lastName, dateOfBirth, nric, school, email, address, telephoneNo, handphoneNo FROM Account " +
					"where userName = 'shahrikinalias@gmail.com'";
			rs = db.readRequest(dbQuery);

			while (rs.next()) {
				RI tempRI1 = new RI();
			
				tempRI1.setUserName(rs.getString("userName"));
				tempRI1.setType(rs.getString("type"));
				tempRI1.setStatus(rs.getString("status"));
				tempRI1.setFirstName(rs.getString("firstName"));
				tempRI1.setLastName(rs.getString("lastName"));
				tempRI1.setDateOfBirth(rs.getDate("dateOfBirth"));
				tempRI1.setNric(rs.getString("nric"));
				tempRI1.setSchool(rs.getString("school"));
				tempRI1.setEmail(rs.getString("email"));
				tempRI1.setAddress(rs.getString("address"));
				tempRI1.setTelephoneNo(rs.getString("telephoneNo"));
				tempRI1.setHandphoneNo(rs.getString("handphoneNo"));
				// tempRI1.setSecretQuestion(rs.getString("secretQuestion"));
				// tempRI1.setSecretAnswer(rs.getString("secretAnswer"));
				riList1.add(tempRI1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return riList1;

	}
	
	public String[][] getRITableModel1() {
		RI riModel1 = new RI();

		String data[][] = new String[1][13];

		try {

			ArrayList<RI> tempList = riModel1.retrieveSingleUser();
			
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
	

	
	
}
