/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is an implementation of a phone directory using Arrays.
 */
import java.util.*;

public class ArrayDirectory implements Directory {

// This is the array that will store entries. The size is initially set to 0, but will change when each entry is added.
	private Entry[] entries = new Entry[0];

	@Override
	public void addEntry(Entry entry) {
		// This array is 1 element greater than the main array used. it will be manipulated throughout the method.
		Entry[] entries1 = new Entry[entries.length + 1];
		// If there are no entries in the array add first entry.
		if (entries1.length == 1) {
			entries1[0] = entry;
			entries = entries1;
		}
		// If there are entries, then check if it is duplicate.	
		else {
			int i = 0;
			boolean less = true;
			boolean nonDuplicate = true;
			for (int f = 0; f < entries.length; f++) {
				if (entry.getPhoneExtension().equals(entries[f].getPhoneExtension())) {
					nonDuplicate = false;
				}
			}

			// If it is not a duplicate then add it by the aplhabetical order of surname.
			while (nonDuplicate) {
				while (i < entries.length && less) {
					Entry entry1 = entries[i];
					if (entry.getSurname().toLowerCase().compareTo(entry1.getSurname().toLowerCase()) > 0) {
						entries1[i] = entry1;
						i++;
						entries1[i] = entry;
					} else {
						entries1[i] = entry;
						i++;
						for (int j = i; j < entries1.length; j++) {
							entries1[j] = entries[j - 1];
						}
						less = false;
					}
				}
				// After adding the entry, assign the manipulated array to the variable used by the initial array.
				entries = entries1;
				nonDuplicate = false;
			}
		}
	}

	public void deleteEntry(String number) {
		// This array is an element less in size than the main array used throughout the program.
		// It will be manipulated and eventually be assigned to the variable of the main array.
		Entry[] entries1 = new Entry[entries.length - 1];
		boolean notDeleted = true;

		// This for loop will go through each entry to look for the entry to delete.
		for (int i = 0; i < entries.length && notDeleted; i++) {
			// If found, then skip the index that the entry is in, then add all the entries following it
			// to the new array at an index 1 less than it is in the main array.
			if (number.equals(entries[i].getPhoneExtension())) {
				int skipIndex = i + 1;
				for (int n = skipIndex; n < entries.length; n++) {
					entries1[n - 1] = entries[n];
				}
				notDeleted = false;
			}
			// if not found, assign the entry to the same index as the main array.
			else {
				entries1[i] = entries[i];
			}
		}
		// finally assign the new array to the main array variable.
		entries = entries1;
	}

	public String lookUp(String name, String initials) {
		// This is an implementation of the binarySearch method.
		int left = 0, right = entries.length - 1;
		String extensionNumber2 = null;
		while (left <= right) {
			int middle = left + (right - left) / 2;

			// Check if entry is present at mid
			if (name.equals(entries[middle].getSurname()) && initials.equals(entries[middle].getInitials())) {
				extensionNumber2 = entries[middle].getPhoneExtension();
				return extensionNumber2;
			}
			// If entry surname is greater, ignore left half
			else if (name.toLowerCase().compareTo(entries[middle].getSurname().toLowerCase()) > 0) {
				left = middle + 1;
			}
			// If entry surname is smaller, ignore right half
			else {
				right = middle - 1;
			}
		}

		// if we reach here, then the entry is
		// not present
		return "NOT FOUND";
	}

	public void changeNumber(String name, String initials, String newNumber) {
		boolean notChanged = true;
		
		// Check if the new number exists in the directory.
		for (int i = 0; i < entries.length; i++) {
			if (newNumber.equals(entries[i].getPhoneExtension())) {
				notChanged = false;
			}
		}
		//if it doesnt, use a for loop to go through each entry to look for the name. When
		//found, change the telephone number to the new number and terminate the for loop.
		for (int i = 0; i < entries.length && notChanged; i++) {
			if (name.equals(entries[i].getSurname()) && !newNumber.equals(entries[i].getPhoneExtension())
					&& initials.equals(entries[i].getInitials())) {
				entries[i].setPhoneExtension(newNumber);
				notChanged = false;
			}
		}
	}

	

	public String printDirectory() {
		String directory = "";
		for (int i = 0; i < entries.length; i++) {
			directory = directory + entries[i].getSurname() + "\t" + entries[i].getInitials() + "\t"
					+ entries[i].getPhoneExtension() + "\n";
		}
		return directory;
	}

}