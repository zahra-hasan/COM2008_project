package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
import java.util.ArrayList;

public class Bathing {
	
	private int id;
	private ArrayList<Bathroom> bathrooms;
	private Boolean hairDryer;
	private Boolean shampoo;
	private Boolean toiletPaper;
	
	
	public Bathing(int id,ArrayList<Bathroom> bathrooms,Boolean hairDryer,Boolean shampoo, Boolean toiletPaper) {
		this.id = id;
		this.bathrooms =  bathrooms;
		this.hairDryer = hairDryer;
		this.shampoo = shampoo;
		this.toiletPaper = toiletPaper;
	}
	
	
	//reports how many bathrooms there are.
	public int getNumBathrooms() {
		if (bathrooms.size() !=0 ) {
			return bathrooms.size();
		} else return 0;
	}
	
	public Boolean hasHairDryer() {
		return hairDryer;
	}
	
	public Boolean hasShampoo() {
		return shampoo;
	}
	
	public Boolean hasToiletPaper() {
		return toiletPaper;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
