package Model.Membership;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

import Controller.MySQLController;

public class RI extends Account {

	private static MySQLController db = new MySQLController();

	public RI(String userName, String password, String type, String status,String firstName,
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

	public RI() {
		// TODO Auto-generated constructor stub
	}

	

	// ___________________________________________________________________Create
	// RI
	// Account___________________________________________________________________

	public boolean createRIAccount(RI account) {

		boolean success = false;

		String sql = "INSERT INTO Account (userName, password, firstName, lastName, nric, school, email, address, telephoneNo, handphoneNo, secretAnswer)"+ 
		"VALUES " + "('"
				+ account.getUserName()
				+ "', '"
				+ account.getPassword()
				+ "', '"
				+ account.getFirstName()
				+ "', '"
				+ account.getLastName()
				+ "', '"
			//	+ account.getDateOfBirth()
			//	+ "','"
				+ account.getNric()
				+ "', '"
				+ account.getSchool()
				+ "', '"
				+ account.getEmail()
				+ "', '"
				+ account.getAddress()
				+ "''"
				+ account.getTelephoneNo()
				+ "','"
				+ account.getHandphoneNo()
				+ "','"
			//	+ account.getSecretQuestion()
			//	+ "','"
				+ account.getSecretAnswer()+"')";

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
		MySQLController db = new MySQLController();
		String sql = "DELETE FROM Account  where UserName = '" + getUserName()
				+ "'";
		if (db.updateRequest(sql) == 1)
			success = true;
		db.terminate();
		return success;

	}

	// _____________________________________________________________________Update
	// RI Account ____________________________________________________________

	public boolean updateRIAccount(Account account) {
		boolean success = false;
		MySQLController db = new MySQLController();
		String sql = "UPDATE Account SET userName, firstName, lastName, nric, school, email, address, telephoneNo, handphoneNo,  secretAnswer = '"

				+ "' "
				+ getUserName()
				
				+ "','"
				+ getFirstName()
				+ "','"
				+ getLastName()
			//	+ "','"
			//	+ getDateOfBirth()
				+ "','"
				+ getNric()
				+ "', '"
				+ getSchool()
				+ "','"
				+ getEmail()
				+ "','"
				+ getAddress()
				+ "', "
				+ getTelephoneNo()
				+ "','"
				+ getHandphoneNo()
//				+ "','"
//				+ getSecretQuestion()
				+ "','"
				+ getSecretAnswer()
				+ "''"
				+ " WHERE userName ='shahrikinalias@gmail.com'";
		if (db.updateRequest(sql) == 1)
			success = true;
		db.terminate();
		return success;
	}

	public static void main(String args[]) throws ParseException {

		RI riModel = new RI();
		
	riModel.setFirstName("shahrik");
	riModel.setLastName("alias");
	riModel.setUserName("kino");

	 riModel.updateRIAccount(riModel);
		

		

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

		String data[][] = new String[13][13];

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
