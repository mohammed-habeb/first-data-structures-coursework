/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is the class that is used to store the surname, initials, and number of an
 * entry in an entry object. The object will then be stored in the telephone directory.
 */
public class Entry {

	private String surname;
	private String initials;
	private String phoneExtension;

	public Entry(String surname, String initials, String phoneExtension) {
		this.surname = surname;
		this.initials = initials;
		this.phoneExtension = phoneExtension;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

}