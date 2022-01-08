package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
import java.util.ArrayList;

public class Sleeping {
	
	//class to handle sleeping facility consists  at least one bedroom 

	private ArrayList<Bedroom> bedrooms;
	private Boolean bedLinen;
	private Boolean towels;
	
	public Sleeping(ArrayList<Bedroom> bedrooms, Boolean bedLinen, Boolean towels) {
		this.bedrooms = bedrooms;
		this.bedLinen = bedLinen;
		this.towels = towels;
	}
	
	//reports how many bedrooms there are
	public int getNumBedrooms() {
		if (bedrooms.size() != 0) {
			return bedrooms.size();
		} else return 0;
	}
	
	//reports how many beds in total there are
	public int getTotalNumBeds() {
		int total =0;
		for (Bedroom b: bedrooms) {
			total += b.getNumBeds();
		} return total;
	}
	
	//reports how many sleepers in total there are
	public int getTotalNumSleepers() {
		int total = 0; 
		for (Bedroom b: bedrooms) {
			total += b.getNumSleepers();
		} return total;
	}
	
	public Boolean haveBedLinen() {
		return bedLinen;
	}
	
	public Boolean haveTowels() {
		return towels;
	}
}


