package team060.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import team060.core.Address;
import team060.core.Guest;


/** data access object for Person/Guest table.
 * @author Zahra Hasan A Alhilal
*/

public class GuestDao {

	private Connection team060;
	private Guest guest;
	private String email;
	private String password;

	//constructor to access guest table
	public GuestDao(Guest guest) throws SQLException {
		setConnection();
		this.guest = guest;
	}
	
	//constructor to login a guest
	public GuestDao(String email, String password) throws SQLException {
		setConnection();
		this.email = email;
		this.password = password;
	}
	
	//method to create guest object from a single row using guestID
	public Guest convertRowToGuest(int id) throws Exception {
		Guest guest = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs2 = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where personID='"+ id +"'";
			myRs = myStmt.executeQuery(sql);
			if (!myRs.next()) { //checks if a  person exist first
				//getting guest address
				int addID = myRs.getInt("addressID");
				AddressDao d = new AddressDao(null);
				Address add = d.getAddress(addID);
				System.out.print("creating address object for guest.."+ add);
				//getting person(guest) info in an arrayList of Strings
				PersonDao p = new PersonDao(null);
				List<String> person = p.convertRowToPerson(myRs);
				System.out.print("creating person [] object for guest.."+ person);
				//getting the rest of guest details from guest table 
				String sql2 = "select * from Guest where guestID='"+ id +"'";
				myRs2 = myStmt.executeQuery(sql2);
				String displayName = myRs2.getString("displayName");
				//creating the guest object using values extracted above
				guest = new Guest(id, person.get(1), person.get(5), person.get(2),
						person.get(3), person.get(4),add, displayName);
				System.out.print("creating host object.."+ guest);
			}
			return guest;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}	
	//method to get hostID using host email
	public int getGuestID() throws Exception{
		int guestID = 0;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where email='"+ email +"'";
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
				guestID = myRs.getInt("personID");
			}
			return guestID;	
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to add new guest to guest table
	public void addGuest() throws Exception {
		PreparedStatement myStmt = null;
		String id = guest.idToString();
		String displayName =  guest.getDisplayName();
		try {
			myStmt = team060.prepareStatement("insert into Guest"+"(guestID,displayName)"
						+"values(?,?)");
			myStmt.setString(1, id);
			myStmt.setString(2, displayName);
			myStmt.executeUpdate();
		}
		finally {
			myStmt.close();	
		}
	}
	//method to check if a guest id already exist 
	public Boolean guestExist(int id) throws Exception {
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Guest where guestID='"+ id +"'";
			myRs = myStmt.executeQuery(sql);
			if (!myRs.next()) {
				return false;
			}
			else return true;	
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to register new guest 
	public Boolean registerGuest() throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		//prepare host parameters 
		Boolean registered = false;
		Address add = guest.getAddress();
		String email = guest.getEmail();
		String title = guest.getTitle();
		String forename = guest.getForename();
		String surname = guest.getSurname();
		String phone = guest.getPhoneNum();
		String encryptedPassword = PasswordUtils.encryptPassword(guest.getPassword());
		String displayName = guest.getDisplayName();
		
		try {
			//check if a person in the person table have the same email
			String pkEmail = guest.getEmail();
			PersonDao personDao = new PersonDao(pkEmail);
			List<String> person = personDao.emailExist();
			 //1- if a person with same email exist do:
			if (person != null){
					//check that this person id exist in the guest table	
					int id = Integer.valueOf(person.get(0));
					if (guestExist(id) == true) {//meaning host already exist
						System.out.println("guest with same email already registerd, try different email"
								+ "or register as a host");
					}
					else if (guestExist(id) == false) {//if not, register new guest 
						Guest temp = new Guest(id, guest.getDisplayName());
						GuestDao tempG = new GuestDao(temp);
						tempG.addGuest();
						registered = true;
						
					}
					System.out.println("checking person with same email works..");
			}
			//2- if no, register this new person
			else if (person == null) {
					//insert to address table 
					AddressDao addressDao = new AddressDao(add);
					System.out.println("declaring AddressDao works.."+ add);
					if (addressDao.addAddress() == true) {
						//Retrieve the address ID again to use in Person table
						int addId = addressDao.getAddressId();
						//insert to Person table
						myStmt = team060.prepareStatement("insert into Person"+
								"(addressID,email,title,forename,surname,phone,password)"
								+"values(?,?,?,?,?,?,?)");
						myStmt.setInt(1, addId);
						myStmt.setString(2, email);
						myStmt.setString(3, title);
						myStmt.setString(4, forename);
						myStmt.setString(5, surname);
						myStmt.setString(6, phone);
						System.out.println("works fine before encrypt.");
						myStmt.setString(7, encryptedPassword);
						System.out.println("encryption works..");
						myStmt.executeUpdate();
						//Retrieve the person ID again to use in Host table
						PersonDao personDao1 = new PersonDao(guest.getEmail());
						System.out.println("guest email = "+ guest.getEmail() );
						int personId = personDao1.getPersonId();
						//insert to Host table
						Guest temp = new Guest(personId, displayName);
						GuestDao tempG = new GuestDao(temp);
						tempG.addGuest();
						registered = true;
						}
					}
			return registered;
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	//method to log in a host using authenticate(int id) and getEncrpytedPassword(int id) below.
	public Boolean loginGuest() throws Exception {
		Statement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs2 = null;
		Boolean loggedin = false;
		//get email
		try {
			//use it to check if person exist
			myStmt = team060.createStatement();
			String sql = "select * from Person where email='"+ email +"'";
			System.out.println("getting the person email "+ email);
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
				//if yes.. get the person ID and check if it's in the guest table
				int id = myRs.getInt("personID");
				String sql2 = "select * from Guest where guestID='" + id + "'";
				System.out.println("getting the guest id "+ id);
				myRs2 = myStmt.executeQuery(sql2);
				if (myRs2.next()) {
					//guest exist 
					//get Encrypted password (person id) and authenticate
					loggedin = authenticate(id);
				}
				myRs2.close();
			}
			return loggedin;
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	public boolean authenticate(int id) throws Exception {
		boolean result = false;
		
		// get the encrypted password from database for this user
		String encryptedPasswordFromDatabase = getEncrpytedPassword(id);
		
		// compare the passwords
		result = PasswordUtils.checkPassword(password, encryptedPasswordFromDatabase);
		
		return result;
	}
	private String getEncrpytedPassword(int id) throws Exception {
		String encryptedPassword = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = team060.createStatement();
			System.out.println("creating statement");
			myRs = myStmt.executeQuery("select * from Person where personID='" + id + "'");
			
			if (myRs.next()) {
				encryptedPassword = myRs.getString("password");
				System.out.println("getting the password from database" + encryptedPassword);
			}
			else {
				throw new Exception("Host id not found: " + id);
			}

			return encryptedPassword;		
		}
		finally {
			myStmt.close();
			myRs.close();
		}		
	}
	public void setConnection() throws SQLException {
		this.team060 = DAOUtils.getConnection();
			System.out.print("DB Connection to team060 successful!");
	}
}
