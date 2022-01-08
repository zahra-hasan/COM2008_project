package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Bedroom {

	//class to handle a bedroom object consisting of at least one bed and at most two beds
	
	private Bed bed1;
	private Bed bed2;
	
	public Bedroom(Bed bed1, Bed bed2) {
		this.setBed1(bed1);
		if (bed2 != null) {
			this.setBed2(bed2);
		}
	}
	
	public int getNumBeds() {
		if (bed1!= null && bed2 != null) {
			return 2;
		} else if (bed1 != null && bed2 == null) {
			return 1;
		} else return 0;
	}
	
	public int getNumSleepers() {
		int total = 0;
		if (bed1!= null && bed2 != null) {
			total += bed1.getNumSleepers() + bed2.getNumSleepers();
		} else if (bed1 != null && bed2 == null) {
			total += bed1.getNumSleepers();
		} else total = 0;
		return total;
	}

	public Bed getBed1() {
		return bed1;
	}

	public void setBed1(Bed bed1) {
		this.bed1 = bed1;
	}

	public Bed getBed2() {
		return bed2;
	}

	public void setBed2(Bed bed2) {
		this.bed2 = bed2;
	}
	
}
