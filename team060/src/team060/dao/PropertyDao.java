package team060.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import team060.core.*;
/** 
 * data access object for Property table
 * @author Zahra Hasan A Alhilal
 *  
 */
public class PropertyDao {

	private Connection team060;
	private Property property;
	private Host host;
	private Address add;
	private int hostID;
	
	//constructor to use when creating new property
	public PropertyDao(Host host, Address add) throws Exception {
		setConnection();
		this.setHost(host);
	}
	//constructor to use when accessing property details in DB
	public PropertyDao(Property p) throws Exception{
		setConnection();
		this.property = p;
	}
	//constructor to use when accessing associated properties with a host
	public PropertyDao(int hostID) throws Exception{
		setConnection();
		this.hostID  = hostID;
	}	
	//method to create a property object from a single row in DB
	public List<Property> convertRowToProperty(ResultSet myRs) throws Exception{
		List<Property> properties = new ArrayList<Property>();
		while (myRs.next()) {
			int id = myRs.getInt("propertyID");
			int hostID = myRs.getInt("hostID");
			int addID = myRs.getInt("addressID");
			String name = myRs.getString("name");
			String description = myRs.getString("description");
			String location = myRs.getString("location");
			Boolean breakfast = myRs.getBoolean("breakfast");
			HostDao h = new HostDao(null);
			Host host = h.convertRowToHost(hostID);
			AddressDao d = new AddressDao(null);
			Address add = d.getAddress(addID);
			Property p = new Property(id, host, add, name, description, location, breakfast);
			properties.add(p);
		}
		return properties;
	}
	//method to get all properties details from DB
	public List<Property> getAllProperties() throws Exception {
		List<Property> properties = new ArrayList<Property>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Property";
			myRs = myStmt.executeQuery(sql);
			properties = convertRowToProperty(myRs); 
			return properties;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to get all properties associated with a host using hostID from DB
	public List<Property> getAllPropertiesForHost() throws Exception {
		List<Property> properties = new ArrayList<Property>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = team060.createStatement();
			String sql = "select * from Property where hostID='"+ hostID+ "'";
			myRs = myStmt.executeQuery(sql);
			properties = convertRowToProperty(myRs); 
			return properties;
		}
		finally {
			DAOUtils.close(myStmt,myRs);
		}
	}
	//method to create the new property
	public Boolean addProperty() throws Exception {
		PreparedStatement myStmt = null;
		Boolean added = false;
		try {
			myStmt = team060.prepareStatement("insert into Property (hostID,addressID,name,description"
					+ ",location,breakfast) values (?, ?, ?, ?, ? ,?)");
			myStmt.setInt(1, property.getHostID());
			myStmt.setInt(2, property.getAddID());
			myStmt.setString(3, property.getName());
			myStmt.setString(4, property.getDescription());
			myStmt.setString(5,  property.getLocation());
			myStmt.setBoolean(6, property.getBreakfast());
			myStmt.executeUpdate();	
			added = true;
			return added;
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
	//setters and getters
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public Address getAddress() {
		return add;
	}
	public void setAddress(Address add) {
		this.add = add;
	}
	
}

