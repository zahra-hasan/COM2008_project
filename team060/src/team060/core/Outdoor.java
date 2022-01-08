package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Outdoor {

	private enum Parking {
		FREE,
		ONROAD,
		PAID;
	} 
	
	private Boolean patio;
	private Boolean barbeque;
	
	Parking parking;
	
	public Outdoor(Parking parking, Boolean patio, Boolean barbeque) {
		this.parking = parking;
		this.patio = patio;
		this.barbeque = barbeque;
	}
	
	public Boolean hasPatio() {
		return patio;
	}
	
	public Boolean hasBarbeque() {
		return barbeque;
	}
	
	public Parking getParking() {
		return parking;
	}
	
	public String getParkingType() {
		if (parking.equals(Parking.FREE)) {
			return "free parking";
		} else if (parking.equals(Parking.ONROAD)) {
			return "on-road parking";
		} else return "paid car-park";
	}
}
