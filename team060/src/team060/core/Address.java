package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Address {
	
	private int id;
	private String house;
	private String streetName;
	private String placeName;
	private String postcode;
	
	//constructor to be used when pulling address from DB 
	public Address(int id,String house, String streetName, String placeName, String postcode) {
		this.id = id;
		this.setHouse(String.valueOf(house));
		this.setStreetName(streetName);
		this.setPlaceName(placeName);
		this.setPostcode(postcode);
		
	}
	//constructor to be used when inserting address to DB
	public Address(String house, String streetName, String placeName, String postcode) {
		this.setHouse(String.valueOf(house));
		this.setStreetName(streetName);
		this.setPlaceName(placeName);
		this.setPostcode(postcode);
		
	}
	
	//setters and getters
	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPlaceName() {
		return placeName;
	
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Address: ID="+this.getId()+", house name or number = "+ this.getHouse()
			+ ", street name = "+ this.getStreetName()+ ", place name = "+ this.getPlaceName()+
			" ,postcode = "+ this.getPostcode();
	}
}
