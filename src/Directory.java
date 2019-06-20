/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is the interface that will be implemented throughout the project.
 */
public interface Directory {

	/*
	 * This method is used to add entries into the directory.
	 */
	public void addEntry(Entry entry);
	
	
	/*
	 * This method is used to delete entries from the directory by entering the telephone number
	 * associated with it.
	 */
	public void deleteEntry(String number);
	
	
	/*
	 * This method is used to look up entries by entering their surname and initials
	 * and will return the telephone number as a string.
	 */
	public String lookUp(String name, String initials);

	/*
	 * This method is used to change the number of an entry by entering the name and the initials 
	 * of the entry you wish to change along with the new number you want to associate it with.
	 */
	public void changeNumber(String name, String initials ,String newNumber);

	
	/*
	 * This method is used to print the directory in a tabulated format.
	 */
	public String printDirectory();
}