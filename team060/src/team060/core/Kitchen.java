package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Kitchen {

	private Boolean refrigerator;
	private Boolean microwave;
	private Boolean oven;
	private Boolean stove;
	private Boolean dishwasher;
	private Boolean tableware;
	private Boolean cookware;
	private Boolean provisions;
	
	public Kitchen(Boolean refrigerator,Boolean microwave,Boolean oven,Boolean stove,
			Boolean dishwasher,Boolean tableware,Boolean cookware, Boolean provisions) {
		this.refrigerator = refrigerator;
		this.microwave = microwave;
		this.oven = oven;
		this.stove = stove;
		this.dishwasher = dishwasher;
		this.tableware = tableware;
		this.cookware = cookware;
		this.provisions = provisions;
				
	}
	
	public Boolean hasRefrigerator() {
		return refrigerator;
	}
	public Boolean hasMicrowave() {
		return microwave;
	}
	public Boolean hasOven() {
		return oven;
	}
	public Boolean hasStove() {
		return stove;
	}
	public Boolean hasDishwasher() {
		return dishwasher;
	}
	public Boolean hasTableware() {
		return tableware;
	}
	public Boolean hasCookware() {
		return cookware;
	}
	public Boolean hasProvisions() {
		return provisions;
	}
}
