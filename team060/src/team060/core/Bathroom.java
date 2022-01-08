package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Bathroom {

	private int id;
	private Boolean toilet;
	private Boolean bath;
	private Boolean shower;
	private Boolean shared;
	
	public Bathroom(int id, Boolean toilet,Boolean bath,Boolean shower,Boolean shared) {
		this.id = id;
		this.toilet = toilet;
		this.bath = bath;
		this.shower = shower;
		this.shared = shared;
	}
	
	public Boolean hasToilet() {
		return toilet;
	}
	
	public Boolean hasBath() {
		return bath;
	}
	
	public Boolean hasShower() {
		return shower;
	}
	
	public Boolean isShared() {
		return shared;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
