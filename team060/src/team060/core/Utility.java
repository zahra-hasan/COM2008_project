package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public class Utility {

	private Boolean heating;
	private Boolean washing;
	private Boolean drying;
	private Boolean fireEx;
	private Boolean smokeAlarm;
	private Boolean faKit;
	
	public Utility(Boolean heating, Boolean washing, Boolean drying, 
			Boolean fireEx ,Boolean smokeAlarm, Boolean faKit) {
		this.heating = heating;
		this.washing = washing;
		this.drying = drying;
		this.fireEx = fireEx;
		this.smokeAlarm = smokeAlarm;
		this.faKit = faKit;
	}
	
	public Boolean hasHeating() {
		return heating;
	}
	public Boolean haswashing() {
		return washing;
	}
	public Boolean hasDrying() {
		return drying;
	}
	public Boolean hasFireEx() {
		return fireEx;
	}
	public Boolean hasSmokeAlarm() {
		return smokeAlarm;
	}
	public Boolean hasFaKit() {
		return faKit;
	}
	
}
