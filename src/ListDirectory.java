
/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is an implementation of a phone directory using LinkedLists.
 */
import java.util.*;

public class ListDirectory implements Directory {

	// This is the linked list that will store entries.
	private List<Entry> entries = new LinkedList<>();

	@Override
	public void addEntry(Entry entry) {
		// if the linked list is empty, then add the entry to the list
		if (entries.isEmpty()) {
			entries.add(entry);
		}
		// if it is not empty, check if the entry is a duplicate
		else {
			ListIterator<Entry> iterator = entries.listIterator();
			boolean duplicate = false;
			while (iterator.hasNext()) {
				if (iterator.next().getPhoneExtension().equals(entry.getPhoneExtension())) {
					duplicate = true;
				}
			}

			// If it is not a duplicate, then itirate through the elements in the list
			// until the entrys surname is alphabetically less than the elements surname and
			// then
			// add it.
			while (!duplicate) {
				iterator = entries.listIterator();
				boolean done = false;
				Entry next = iterator.next();
				while (!done) {
					if (entry.getSurname().toLowerCase().compareTo(next.getSurname().toLowerCase()) > 0) {
						if (iterator.hasNext()) {
							next = iterator.next();
						} else {
							iterator.add(entry);
							done = true;
							duplicate = true;
						}
					} else {
						iterator.previous();
						iterator.add(entry);
						done = true;
						duplicate = true;
					}
				}
			}
		}
	}

	@Override
	public void deleteEntry(String number) {
		// This iterator will iterate through the elements in the list.
		ListIterator<Entry> iterator = entries.listIterator();
		boolean deleted = false;
		Entry next = iterator.next();

		// This while loop will go through each element and check if its number is the
		// same
		// as the number in the parameter. Once the number is found, it will remove the
		// entry
		// from the linked list.
		while (!deleted) {
			if (number.equals(next.getPhoneExtension())) {
				iterator.remove();
				deleted = true;
			} else {
				if (iterator.hasNext()) {
					next = iterator.next();
				} else {
					deleted = true;
				}
			}
		}

	}

	@Override
	public String lookUp(String name, String initials) {
		ListIterator<Entry> iterator = entries.listIterator();
		boolean found = false;
		// extensionNumber is the variable that the method will return and is initially
		// and is initially set to null.
		String extensionNumber = null;
		Entry next = iterator.next();
		// The while loop will iterate over all the entries and compare their surname
		// and
		// initials to the ones entered. Once the comparison is successful, it will then
		// assign
		// the elements telephone number to extensionNumber then terminate the loop.
		while (!found) {
			if (name.equals(next.getSurname()) && initials.equals(next.getInitials())) {
				extensionNumber = next.getPhoneExtension();
				found = true;
			} else {
				if (iterator.hasNext()) {
					next = iterator.next();
				} else {
					// if the number is not found, it will assign "NUMBER NOT FOUND" to
					// extensionNumber and terminate the while loop.
					extensionNumber = "NUMBER NOT FOUND";
					found = true;
				}
			}
		}
		return extensionNumber;
	}

	@Override
	public void changeNumber(String name, String initials, String newNumber) {
		// This iterator will iterate through the elements in the list.
		ListIterator<Entry> iterator = entries.listIterator();
		boolean duplicate = false;

		// Check if the new number exists in the directory.
		while (iterator.hasNext()) {
			if (newNumber.equals(iterator.next().getPhoneExtension())) {
				duplicate = true;
			}
		}
		// reset the iterator.
		iterator = entries.listIterator();
		Entry next = iterator.next();
		boolean changed = false;

		// If the new number doesnt exist in the directory, the while loop will iterate
		// through each entry in the list and compare its name and initials for a match. 
		// Once it finds a match, it will set the entrys telephone number to the new number.
		while (!duplicate && !changed) {
			if (name.equals(next.getSurname()) && initials.equals(next.getInitials())) {
				next.setPhoneExtension(newNumber);
				changed = true;
			} else {
				if (iterator.hasNext()) {
					next = iterator.next();
				} else {
					duplicate = true;
				}
			}
		}
	}

	@Override
	public String printDirectory() {
		String directory = "";
		for (int i = 0; i < entries.size(); i++) {
			directory = directory + entries.get(i).getSurname() + "\t" + entries.get(i).getInitials() + "\t"
					+ entries.get(i).getPhoneExtension() + "\n";
		}
		return directory;
	}
}
