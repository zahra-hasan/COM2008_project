package team060.core;

import java.util.ArrayList;
/** represents GUEST object
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Guest extends Person {
	
	private String name;
	private ArrayList<Booking> bookings;
	private ArrayList<Review> reviews;
	private String displayName;
	
	//constructor to be used when inserting to DB
	public Guest(String email, String password, String phoneNum, String title, String
			forename, String surname, Address address, String displayName){
		super(email,password,phoneNum,title,forename,surname,address);
		this.setDisplayName(displayName);
	}

	//constructor to be used when pulling from DB
	public Guest(int id, String email, String phoneNum, String title, String
			forename, String surname, Address address, String displayName){
		super(id,email,phoneNum,title,forename,surname,address);
		this.setDisplayName(displayName);
		
	}
	
	public Guest(int id, String displayName) {
		super(id);
		this.setDisplayName(displayName);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

}
