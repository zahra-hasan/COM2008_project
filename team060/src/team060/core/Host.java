package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
import java.util.ArrayList;

public class Host extends Person {
	
	private Boolean isSuperhost;
	private ArrayList<Property> properties;
	private String displayName;
	
	public Host(String email, String password, String phoneNum, String title, String
			forename, String surname, Address address, String displayName, Boolean superhost){
		super(email,password,phoneNum,title,forename,surname,address);
		this.setDisplayName(displayName);
		this.setIsSuperhost(superhost);
	}
	
	public Host(int id, String email, String phoneNum, String title, String
			forename, String surname, Address address, String displayName, Boolean superhost){
		super(id,email,phoneNum,title,forename,surname,address);
		this.setDisplayName(displayName);
		this.setIsSuperhost(superhost);
	}
	
	public Host(int id, String displayName, Boolean superhost) {
		super(id);
		this.setDisplayName(displayName);
		this.setIsSuperhost(superhost);
	}
	
	
	//method to check if a host is a superhost
	public void isSuperhost() {
		
		if (this.properties.size() >0) {
			
			int propertiesScores = 0;
			
			for (Property p :this.properties) {
				int score = p.getAverageScore();
				propertiesScores += score;
			}
			
			int averageHostScore = propertiesScores/(this.properties.size());
			
			if (averageHostScore>= 4.7) {
				this.setIsSuperhost(true);
			}
			else this.setIsSuperhost(false);
		}
		else this.setIsSuperhost(false);
	}
	
	
	//setters and getters methods 
	
	public Boolean getIsSuperhost() {
		return isSuperhost;
	}
	public void setIsSuperhost(Boolean isSuperhost) {
		this.isSuperhost = isSuperhost;
	}
	public ArrayList<Property> getProperties() {
		return properties;
	}
	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	} 

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String toString() {
		return "Host: first name = " + getForename()+", last name =  "+ getSurname()+ ", email address = "+ getAddress();
	}
	

}
