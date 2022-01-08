package team060.dao;

import team060.core.*;
import java.sql.*;
import java.util.List;

/** data access object for Person/Host table.
 * @author Zahra Hasan A Alhilal
*/

public class HostDao {
	
	private Connection team060;
	private Host host;
	private String email;
	private String password;
	
	//constructor to access host table
	public HostDao(Host host) throws SQLException {
		setConnection();
		this.host = host;
	}
	//constructor to login a host
	public HostDao(String email, String password) throws SQLException {
		setConnection();
		this.email = email;
		this.password = password;
	}
	//method to create host object from a single row using hostID
	public Host convertRowToHost(int id) throws Exception {
		Host host = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs2 = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where personID='"+ id +"'";
			myRs = myStmt.executeQuery(sql);
			if (!myRs.next()) { //checks if a  person exist first
				//getting host address
				int addID = myRs.getInt("addressID");
				AddressDao d = new AddressDao(null);
				Address add = d.getAddress(addID);
				System.out.print("creating address object for host.."+ add);
				//getting person(host) info in an arrayList of Strings
				PersonDao p = new PersonDao(null);
				List<String> person = p.convertRowToPerson(myRs);
				System.out.print("creating person [] object for host.."+ person);
				//getting the rest of host details from host table 
				String sql2 = "select * from Host where hostID='"+ id +"'";
				myRs2 = myStmt.executeQuery(sql2);
				String displayName = myRs2.getString("displayName");
				Boolean superhost = myRs2.getBoolean("superHost");
				//creating the host object using values extracted above
				host = new Host(id, person.get(1), person.get(5), person.get(2),
						person.get(3), person.get(4),add, displayName, superhost);
				System.out.print("creating host object.."+ host);
			}
			return host;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to get hostID using host email
	public int getHostID() throws Exception{
		int hostID = 0;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Person where email='"+ email +"'";
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
				hostID = myRs.getInt("personID");
			}
			return hostID;	
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to check if a host id already exist
	public Boolean hostExist(int id) throws Exception {
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Host where hostID='"+ id +"'";
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
	//method to add new host to host table
	public void addHost() throws Exception {
		PreparedStatement myStmt = null;
		String id = host.idToString();
		String displayName =  host.getDisplayName();
		Boolean isSuperhost = host.getIsSuperhost();
		try {
			myStmt = team060.prepareStatement("insert into Host"+"(hostID,displayName,superHost)"
						+"values(?,?,?)");
			myStmt.setString(1, id);
			myStmt.setString(2,displayName);
			System.out.println("works before bool value..");
			myStmt.setBoolean(3,isSuperhost );
			System.out.println("works after bool value..");
			myStmt.executeUpdate();
		}
		finally {
			myStmt.close();
		}
	}
	//method to register a new host 
	public Boolean registerHost() throws Exception {
		Boolean registered = false;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		//prepare host parameters 
		Address add = host.getAddress();
		String email = host.getEmail();
		String title = host.getTitle();
		String forename = host.getForename();
		String surname = host.getSurname();
		String phone = host.getPhoneNum();
		String encryptedPassword = PasswordUtils.encryptPassword(host.getPassword());
		String displayName = host.getDisplayName();
		Boolean isSuperhost = host.getIsSuperhost();
		
		try {
			//check if a person in the person table have the same email
			String pkEmail = host.getEmail();
			PersonDao personDao = new PersonDao(pkEmail);
			List<String> person = personDao.emailExist();
			 //1- if a person with same email exist do:
			if (person != null){
					//check that this person id exist in the host table	
					int id = Integer.valueOf(person.get(0));
					if (hostExist(id) == true) {//meaning host already exist
						System.out.println("host with same email already registerd, try different email"
								+ "or register as a guest");
					}
					else if (hostExist(id) == false) {//if not, register new host 
						Host temp = new Host(id, host.getDisplayName(), host.getIsSuperhost());
						HostDao tempH = new HostDao(temp);
						tempH.addHost();
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
						PersonDao personDao1 = new PersonDao(host.getEmail());
						System.out.println("host email = "+ host.getEmail() );
						int personId = personDao1.getPersonId();
						//insert to Host table
						Host temp = new Host(personId, displayName, isSuperhost);
						HostDao tempH = new HostDao(temp);
						tempH.addHost();
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
	public Boolean loginHost() throws Exception {
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
				//if yes.. get the person ID and check if it's in the host table
				int id = myRs.getInt("personID");
				String sql2 = "select * from Host where hostID='" + id + "'";
				System.out.println("getting the host id "+ id);
				myRs2 = myStmt.executeQuery(sql2);
				if (myRs2.next()) {
					//host exist 
					//if yes.. get Encrypted password (person id) and authenticate
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
	//setting connection
	public void setConnection() throws SQLException {
		this.team060 = DAOUtils.getConnection();
			System.out.print("DB Connection to team060 successful!");
	}	
}
