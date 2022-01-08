package team060.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/** data access object for Person table.
 * @author Zahra Hasan A Alhilal
*/

public class PersonDao {

	private Connection team060;
	private String email;
	
	//constructor to access a person info in person table
	public PersonDao(String email) throws SQLException {
		setConnection();
		this.email = email;
	}
	
	//method to create an array of Strings of a person details from a single row in DB 
	public List<String> convertRowToPerson(ResultSet myRs) throws SQLException {
		List<String> personInfo = new ArrayList<String>();
			int id = myRs.getInt("personID");
			String email = myRs.getString("email");
			String title = myRs.getString("title");
			String forename = myRs.getString("forename");
			String surname = myRs.getString("surname");
			String phone = myRs.getString("phone");
			personInfo.add(String.valueOf(id));
			personInfo.add(email);
			personInfo.add(title);
			personInfo.add(forename);
			personInfo.add(surname);
			personInfo.add(phone);
			return personInfo;	
	}
	//method to get personID using email(PK)
	public int getPersonId() throws Exception {
		int id = 0;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where email='"+ email +"'";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				id += myRs.getInt("personID");
			}
			System.out.println("getting person id works "+ id);
		return id;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to check if a person is already registered with same email
	public List<String> emailExist() throws Exception {
		List<String> list = new ArrayList<String>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where email='"+ email +"'";
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
			list = convertRowToPerson(myRs);
			return list;
			}
			else return null;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//setting connection
	public void setConnection() throws SQLException {
		this.team060 = DAOUtils.getConnection();
			System.out.print("DB Connection to team060 successful!");
	}

}
