package team060.core;
/** 
 * @author Zahra Hasan A Alhilal
 *  
 */
public abstract class Person {
	
	private int id;
	private String email;
	private String password;
	private String phoneNum;
	private String title;
	private String forename;
	private String surname;
	private Address address;

	//constructor to be used when inserting to DB
	public Person(String email, String password, String phoneNum, String title, String
			forename, String surname, Address address) {
		this.setEmail(email);
		this.setPassword(password);
		this.setPhoneNum(phoneNum);
		this.setTitle(title);
		this.setForename(forename);
		this.setSurname(surname);
		this.setAddress(address);
	}
	//constructor to be used when pulling from DB
	public Person(int id, String email, String phoneNum, String title, String
			forename, String surname, Address address) {
		this.setId(id);
		this.setEmail(email);
		this.setPhoneNum(phoneNum);
		this.setTitle(title);
		this.setForename(forename);
		this.setSurname(surname);
		this.setAddress(address);
	}
	
	
	
	//setters and getters methods 
	
	public Person(int id) {
		this.id = id;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String newName) {
		forename = newName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum2) {
		this.phoneNum = phoneNum2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public String idToString() {
		return String.valueOf(id);
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

