package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Bed {
	
	//class to handle a bed object
	
	private enum Beds {SINGLE,DOUBLE,KING,BUNK};
	
	Beds bed;
	
	public Bed(Beds bed) {
		this.bed = bed;
	}
	
	public int getNumSleepers() {
		if (bed.equals(Beds.SINGLE)) {
			return 1;
		} else return 2;
	}
	
	public Beds getBed() {
		return bed;
	}
	
	public String getBedType() {
		if (bed.equals(Beds.SINGLE)) {
			return "single bed";
		} else if (bed.equals(Beds.DOUBLE)) {
			return "double bed";
		} else if (bed.equals(Beds.KING)) {
			return "kingsize bed";
		} else return "bunk bed";
	}
	
	

}
