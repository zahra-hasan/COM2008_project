package team060.core;

/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
import java.sql.Date;

public class ChargeBand {

	//class to handle a charge band, must be associated with a property
	
	private Date start;
	private Date end;
	private int ppnight;
	private int cleaningCharge;
	private int serviceCharge;
	private Property property;
	
	//constructor
	public ChargeBand(Date start, Date end, int ppnight, int cleaningCharge, int serviceCharge, Property property) {
		this.setStart(start);
		this.setEnd(end);
		this.setPpnight(ppnight);
		this.setCleaningCharge(cleaningCharge);
		this.setServiceCharge(serviceCharge);
		this.setProperty(property);
	}
	

	//getters and setters methods 
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getPpnight() {
		return ppnight;
	}
	public void setPpnight(int ppnight) {
		this.ppnight = ppnight;
	}
	public int getCleaningCharge() {
		return cleaningCharge;
	}
	public void setCleaningCharge(int cleaningCharge) {
		this.cleaningCharge = cleaningCharge;
	}
	public int getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	
}
