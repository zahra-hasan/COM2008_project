package team060.dao;

import java.sql.*;
import team060.core.Address;

/** data access object for Address table.
 * @author Zahra Hasan A Alhilal
*/
public class AddressDao {

	private Connection team060;
	private Address add;
	
	//constructor
	public AddressDao(Address add) throws SQLException {
		setConnection();
		this.add = add;
	}
	//method to create address object from a single row in DB
	private Address convertRowToAddress(ResultSet myRs) throws SQLException {
		//getting parameters
		int id = myRs.getInt("addressID");
		String house = myRs.getString("houseNameOrNumber");
		String street = myRs.getString("streetName");
		String place = myRs.getString("placeName");
		String postCode = myRs.getString("postCode");
		//creating address object
		Address add = new Address(id,house, street,place,postCode);
		return add;
	}
	//method to get address details from DB using addressID
	public Address getAddress(int addId) throws Exception {
		Statement myStmt = null;
		ResultSet myRs = null;
		Address add = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Address where addressID=" + addId;
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
				add = convertRowToAddress(myRs);
			}
			System.out.println("getting the address details in getAddress()..result = "+ add);
			return add;
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	//method to get address ID from DB using address postcode and house number (unique)
	public Integer getAddressId() throws Exception {
		int id = 0;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Address where houseNameOrNumber='"+ add.getHouse()+
					"' AND postCode= '"+ add.getPostcode()+"'";
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				id += myRs.getInt("addressID");
			}
		System.out.println("getting the address ID in getAddressId()..result = "+ id);
		return id;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to check address exist in Address table (unique combo of house and postcode)
	public Boolean addressExist() throws Exception {
		System.out.println("checking address exist in address table..");
		Boolean exist = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Address where houseNameOrNumber='"+ add.getHouse()+
					"' AND postCode= '"+ add.getPostcode()+"'";
			myRs = myStmt.executeQuery(sql);
			if (myRs.next()) {
				System.out.println("address exist in address table");
				exist = true;
			}
			else System.out.println("checking address exist works..");
			return exist;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to check if addressID already exist in Person table (used to add new person)
	public Boolean addressInPerson() throws Exception {
		Boolean exist = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		if (addressExist()){ //checks if address exist in address table first 
			try {
				int id = getAddressId();
				myStmt = team060.createStatement();
				String sql = "select * from Person where addressID='"+ id +"'";
				myRs = myStmt.executeQuery(sql);
				if (myRs.next()) { //returns true if the result set is not empty meaning address exist.
					exist = true;
					System.out.println("address exist in person table..");
				} 
			}
			finally {
				DAOUtils.close(myStmt,myRs);
			}
		}
		return exist;
	}
	//method to check if addressID already exist in Property table (used to add new property)
	public Boolean addressInProperty() throws Exception {
		Boolean exist = false;
		Statement myStmt = null;
		ResultSet myRs = null;
		if (addressExist()){ //checks if address exist in address table first
			try {
				int id = getAddressId();
				myStmt = team060.createStatement();
				String sql = "select * from Property where addressID='"+ id +"'";
				myRs = myStmt.executeQuery(sql);
				if (myRs.next()) { //returns true if the result set is not empty meaning address exist.
					exist = true;
					System.out.println("address exist in property table..");
				}
			}
			finally {
				DAOUtils.close(myStmt,myRs);
			}
		}
		return exist;
	}
	//method to add new address to DB
	public Boolean addAddress() throws Exception {
		PreparedStatement myStmt = null;
		Boolean registered = false;
		try {
			if (addressExist()) {
				System.out.println("address already registered!");
			}
			else if (!addressExist()) {
				System.out.println("adding the new address >> "+ add);
					myStmt = team060.prepareStatement("insert into Address"
					+ " (houseNameOrNumber,streetName,placeName,postCode)"
					+ " values (?, ?, ?, ?)");
			myStmt.setString(1, add.getHouse());
			myStmt.setString(2, add.getStreetName());
			myStmt.setString(3, add.getPlaceName());
			myStmt.setString(4, add.getPostcode());
			myStmt.executeUpdate();	
			registered = true;
			}
			return registered;		
		}
		finally {
			DAOUtils.close(myStmt);
		}
	}
	//method to update address info 
	public void updateAdd() throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = team060.prepareStatement("update Address"
					+ " set houseNameOrNumber=?, streetName=?, placeName=?, postCode=?"
					+ " where id=?");
			
			myStmt.setString(1, add.getHouse());
			myStmt.setString(2, add.getStreetName());
			myStmt.setString(3, add.getPlaceName());
			myStmt.setString(4, add.getPostcode());
			myStmt.executeUpdate();
		}
		finally {
			DAOUtils.close(myStmt);
		}		
	}
	//setting connection
	public void setConnection() throws SQLException {
		this.team060 = DAOUtils.getConnection();
			System.out.print("DB Connection to team060 successful!");
	}
}
