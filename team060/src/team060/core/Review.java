package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Review {
	
	//class to handle a single review related to a booking 
	
	private String review;
	private Booking booking;
	private int cleanliness;
	private int communication;
	private int checkIn;
	private int accuracy;
	private int location;
	private int value;
	
	public Review(String review, int cleanliness,int communication, int checkIn,
	int accuracy, int location, int value, Booking booking, Guest guest, Property property) {
		this.setReview(review);
		this.setCleanliness(cleanliness);
		this.setCommunication(communication);
		this.setCheckIn(checkIn);
		this.setAccuracy(accuracy);
		this.setLocation(location);
		this.setValue(value);
		this.setBooking(booking);
		guest = this.booking.getGuest();
		property = this.booking.getChargeband().getProperty();
	}
	
	
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public int getCleanliness() {
		return cleanliness;
	}
	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}
	public int getCommunication() {
		return communication;
	}
	public void setCommunication(int communication) {
		this.communication = communication;
	}
	public int getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(int checkIn) {
		this.checkIn = checkIn;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
