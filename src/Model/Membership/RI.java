package Model.Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Date;

import View.MM.ViewRIDetails;

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

	public boolean updateRIAccount(RI account) {
		boolean success = false;
		MySQLController db = new MySQLController();
		String sql = "UPDATE Account SET userName, password, firstName,lastName," +
				"dateOfBirth, nric, school, email, address, telephoneNo, handphoneNo, secretQuestion, secretAnswer = '"

				+ "' "
				+ getUserName()
				+ " ''"
				+ getPassword()
				+ "','"
				+ getFirstName()
				+ "','"
				+ getLastName()
				+ "','"
				+ getDateOfBirth()
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
				+ "','"
				+ getSecretQuestion()
				+ "','"
				+ getSecretAnswer()
				+ "''"
				+ " WHERE userName = '" + getUserName() + "'";
		if (db.updateRequest(sql) == 1)
			success = true;
		db.terminate();
		return success;
	}

	public static void main(String args[]) throws ParseException {

		RI riModel = new RI();
		
		riModel.setFirstName("shahrik");

		 riModel.createRIAccount(riModel);

//		ArrayList<RI> riList = riModel.retrieveUser();
//
//		for (int i = 0; i < riList.size(); i++) {
//			System.out.println(riList.get(i).getFirstName());
//			System.out.println(riList.get(i).getLastName());
//		}
//
//		riList = riModel.retrieveSingleUser();

	}

	public ArrayList<RI> retrieveSingleUser() {
		ArrayList<RI> riList = new ArrayList<RI>();
		ResultSet rs = null;
		MySQLController db = new MySQLController();

		try {
			db.getConnection();
			String dbQuery = "Select userName, type, firstName, lastName, dateOfBirth, nric, school, email, address, telephoneNo, handphoneNo FROM Account";
			rs = db.readRequest(dbQuery);

			while (rs.next()) {
				RI tempRI = new RI();

				tempRI.setUserName(rs.getString("userName"));
				tempRI.setType(rs.getString("type"));
				
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
			db.terminate();
		}
		return riList;

	}
}
