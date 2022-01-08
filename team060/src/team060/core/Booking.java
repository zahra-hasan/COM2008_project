package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Booking {
	
	//class to handle a booking 
	
	private ChargeBand chargeband;
	private Guest guest;
	private int numNights;
	
	public Booking(Guest guest, Host host, Property property, ChargeBand chargeBand, int numNights) {
		this.setGuest(guest);
		this.setChargeband(chargeBand);
		this.setNumNights(numNights);
		property = this.chargeband.getProperty();
		host = property.getHost();
	}
	
	//gets total cost of a booking according to number of nights spent using related charge band info
	public int totalCost() {
		int numNights = this.getNumNights();
		int ppnight = this.chargeband.getPpnight();
		int cleaningCharge = this.chargeband.getCleaningCharge();
		int serviceCharge = this.chargeband.getServiceCharge();
		int total = (numNights*ppnight) + cleaningCharge + serviceCharge;
		return total;
	}

	public ChargeBand getChargeband() {
		return chargeband;
	}

	public void setChargeband(ChargeBand chargeband) {
		this.chargeband = chargeband;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public int getNumNights() {
		return numNights;
	}

	public void setNumNights(int numNights) {
		this.numNights = numNights;
	}
	
}
