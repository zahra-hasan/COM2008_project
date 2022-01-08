package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
import java.util.ArrayList;

public class Property {

	private int id;
	private String name;
	private String description;
	private String location;
	private Boolean breakfast;
	private Address address;
	private int addID;
	private ArrayList<ChargeBand> chargebands;
	private Host host;
	private int hostID;
	private ArrayList<Review> reviews;
	private Sleeping sleep;
	private Bathing bath;
	private Kitchen kitchen;
	private Living living;
	private Outdoor outdoor;
	private Utility utility;
	
	
	////constructor to be used when pulling from DB
	public Property(String name, String description, Boolean breakfast,
			String location, Address address,Sleeping sleep, Bathing bath, Kitchen kitchen,
				Living living, Outdoor outdoor, Utility utility){
		this.setName(name);
		this.setDescription(description);
		this.setBreakfast(breakfast);
		this.setAddress(address);
		this.setHost(host);
		this.sleep = sleep;
		this.bath = bath;
		this.kitchen = kitchen;
		this.living = living;
		this.outdoor = outdoor;
		this.utility = utility;
		this.location = location;
	}
	
	
	//constructor to be used when pulling from DB
	public Property(int id, Host host, Address add,String name, String description,
			String location, Boolean breakfast) {
		this.setId(id);
		this.host = host;
		this.address = add;
		this.name = name;
		this.description = description;
		this.location = location;
		this.breakfast = breakfast;
	}
	
	
	//constructor to be used when pulling from DB
	public Property(int hostID, int addID, String name, String description, String location, Boolean breakfast) {
		this.hostID = hostID;
		this.addID = addID;
		this.name = name;
		this.description = description;
		this.location = location;
		this.breakfast = breakfast;
	}
	
	//methods to get average score over all reviews over a certain category
	public int getCleanlinessScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getCleanliness();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	
	}
	public int getCommunicationScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getCommunication();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	}
	public int getCheckInScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getCheckIn();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	}
	public int getAccuracyScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getAccuracy();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	}
	
	public int getLocationScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getLocation();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	}
	public int getValueScore() {
		if (this.reviews.size() > 0) {
			int total = 0;
			for (Review r:this.reviews) {
				total += r.getValue();
			}
			int average = total/(this.reviews.size());
			return average;
		}
		else return 0;
	}
	//method to get average score overall
	public int getAverageScore() {
		
		if (this.reviews.size() > 0) {
			int total = this.getCleanlinessScore()+this.getCommunicationScore()+
					this.getCheckInScore()+this.getLocationScore()+this.getAccuracyScore()+
					this.getValueScore();
			int average = total/6;
			return average;
		}
		else return 0;
	}
	
	//setters and getters methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Boolean breakfast) {
		this.breakfast = breakfast;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ArrayList<ChargeBand> getChargebands() {
		return chargebands;
	}
	public void setChargebands(ArrayList<ChargeBand> chargebands) {
		this.chargebands = chargebands;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	public Sleeping getSleepingfac() {
		return sleep;	
	}
	public Bathing getBathingfac() {
		return bath;
	}
	public Kitchen getKitchenfac() {
		return kitchen;
	}
	public Living getLivingfac() {
		return living;
	}
	public Outdoor getOutdoorfac() {
		return outdoor;
	}
	public void setOutdoor(Outdoor outdoor) {
		this.outdoor = outdoor;
	}
	public Utility getUtilityfac() {
		return utility;
	}
	public void setUtility(Utility utility) {
		this.utility = utility;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	

	public String toString() {
		return "Property name : " + name;
	}
	public int getHostID() {
		return hostID;
	}
	public void setHostID(int hostID) {
		this.hostID = hostID;
	}
	public int getAddID() {
		return addID;
	}
	public void setAddID(int addID) {
		this.addID = addID;
	}

}
