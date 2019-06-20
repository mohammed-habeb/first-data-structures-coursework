
/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is an implementation of a phone directory using a HashDirectory.
 */

import java.util.*;

public class HashDirectory implements Directory {

	// This is the linkedlist that will store hashed list entries
	private List<List<Entry>> alphaList = new LinkedList<>();

	@Override
	public void addEntry(Entry entry) {
		// if the linked list is empty, then add the entry to the list
		if (alphaList.isEmpty()) {
			List<Entry> firstList = new LinkedList<>();
			firstList.add(entry);
			alphaList.add(firstList);
		} 
		// if it is not empty, check if the entry is a duplicate
		else {
			ListIterator<List<Entry>> iterator = alphaList.listIterator();
			boolean duplicate = false;
			List<Entry> entryList = iterator.next();
			entryList = iterator.previous();
			while (iterator.hasNext()) {
				for (Entry e : entryList) {
					if (e.getPhoneExtension().equals(entry.getPhoneExtension())) {
						duplicate = true;
					}
				}
				entryList = iterator.next();
			}
			// Reset the alphabet list iterator (alphalist) and create an instance of 
			// an iterator for the entries in the hashed alphabet (entryList). 
			iterator = alphaList.listIterator();
			iterator.next();
			entryList = iterator.previous();
			ListIterator<Entry> entryIterator = entryList.listIterator();
			boolean insertedInEntryList = false;
			// If not duplicate then iterate through each list to decide which hashed list to store the entry in
			while (!duplicate) {
				if (entry.getSurname().substring(0, 1).toUpperCase()
						.equals(entryList.get(0).getSurname().substring(0, 1).toUpperCase())) {
					Entry next = entryIterator.next();
					// if appropriate hashlist is found, then add the entry to the list in order of
					// surname
					while (!insertedInEntryList) {
						if (entry.getSurname().toLowerCase().compareTo(next.getSurname().toLowerCase()) > 0) {
							if (entryIterator.hasNext()) {
								next = entryIterator.next();
							} else {
								entryIterator.add(entry);
								insertedInEntryList = true;
								duplicate = true;
							}
						} else {
							entryIterator.previous();
							entryIterator.add(entry);
							duplicate = true;
							insertedInEntryList = true;
						}
					}

				} 
				/* If hashed list is not found, then create a new hash list to insert the element, 
				 * and then insert the hashed list in alphabetical order into the main list.
				 */
				else {
					if (iterator.hasNext()) {
						entryList = iterator.next();
						entryIterator = entryList.listIterator();
					} else {
						List<Entry> newAlphaList = new LinkedList<>();
						newAlphaList.add(entry);
						iterator = alphaList.listIterator();
						List<Entry> list = iterator.next();
						boolean alphaListLocationFound = false;
						while (!alphaListLocationFound) {
							if (entry.getSurname().toUpperCase().substring(0, 1)
									.compareTo(list.get(0).getSurname().toUpperCase().substring(0, 1)) > 0) {
								if (iterator.hasNext()) {
									list = iterator.next();
								} else {
									alphaListLocationFound = true;
								}
							} else {
								list = iterator.previous();
								alphaListLocationFound = true;
							}
						}
						iterator.add(newAlphaList);
						duplicate = true;
						insertedInEntryList = true;
					}
				}
			}
		}

	}

	@Override
	public void deleteEntry(String number) {
		/* Create 2 iterators
		 * 
		 * "alphaIterator" iterates through alphabet lists
		 * 
		 * "entryIterator" iterates through the entries in the alphabet lists.
		 * 
		 */
		boolean deleted = false;
		ListIterator<List<Entry>> alphaIterator = alphaList.listIterator();
		alphaIterator.next();
		List<Entry> listInAlphaList = alphaIterator.previous();
		ListIterator<Entry> entryIterator = listInAlphaList.listIterator();
		Entry entry = entryIterator.next();
		// the while loop will iterate through each entry in every alphabet list until the number 
		// matches the number in the entry. it will then proceed to delete it.
		while (!deleted) {
			if (number.equals(entry.getPhoneExtension())) {
				entryIterator.remove();
				deleted = true;
			} else {
				if (entryIterator.hasNext()) {
					entry = entryIterator.next();
				} else {
					if (alphaIterator.hasNext()) {
						listInAlphaList = alphaIterator.next();
						entryIterator = listInAlphaList.listIterator();
						entry = entryIterator.next();
					} else {
						deleted = true;
					}
				}
			}
		}
		/*
		 * After deleteing the entry, the iterators are reset and a for loop is instantiated 
		 * to delete any empty lists that are a result from the entry deletion.
		 */
		alphaIterator = alphaList.listIterator();
		alphaIterator.next();
		listInAlphaList = alphaIterator.previous();
		for (int i = 0; i < alphaList.size(); i++) {
			if (listInAlphaList.isEmpty()) {
				alphaIterator.remove();
				listInAlphaList = alphaIterator.next();
			} else {
				listInAlphaList = alphaIterator.next();
			}
		}
	}

	@Override
	public String lookUp(String name, String initials) {
		// "number" is the variable that is returned.
		String number = null;
		ListIterator<List<Entry>> alphaIterator = alphaList.listIterator();
		alphaIterator.next();
		List<Entry> listInAlphaList = alphaIterator.previous();
		boolean found = false;
		// The while loop will find the hashed list that contains all the entries beggining with 
		// the first letter of the surname and then go through each entry to find a successfull
		// match of the initials and surname.
		while (!found) {
			if (name.substring(0, 1).toUpperCase()
					.equals(listInAlphaList.get(0).getSurname().substring(0, 1).toUpperCase())) {
				ListIterator<Entry> entryIterator = listInAlphaList.listIterator();
				Entry nextEntry = entryIterator.next();
				while (!found) {
					if (name.equals(nextEntry.getSurname()) && initials.equals(nextEntry.getInitials())) {
						number = nextEntry.getPhoneExtension();
						found = true;
					} else {
						if (entryIterator.hasNext()) {
							nextEntry = entryIterator.next();
						}
						// if the entry is not found in the hashed list, reutrn "NUMBER NOT FOUND"
						else {
							number = "NUMBER NOT FOUND";
							found = true;
						}
					}
				}
			} 
			// If the hashed alphabet list is not found then return "NUMBER NOT FOUND"
			else {
				if (alphaIterator.hasNext()) {
					listInAlphaList = alphaIterator.next();
				} else {
					number = "NUMBER NOT FOUND";
					found = true;
				}
			}
		}
		return number;
	}

	@Override
	public void changeNumber(String name, String initials, String newNumber) {
		ListIterator<List<Entry>> alphaListIterator = alphaList.listIterator();
		boolean duplicate = false;
		boolean duplicateOperationDone = false;
		List<Entry> entryList = alphaListIterator.next();
		entryList = alphaListIterator.previous();
		
		// Check if the new number exists in the directory.
		while (!duplicateOperationDone) {
			for (Entry e : entryList) {
				if (newNumber.equals(e.getPhoneExtension())) {
					duplicate = true;
					duplicateOperationDone = true;
				}
			}
			if (alphaListIterator.hasNext()) {
				entryList = alphaListIterator.next();
			} else {
				duplicateOperationDone = true;
			}
		}
		// reset the iterators
		alphaListIterator = alphaList.listIterator();
		alphaListIterator.next();
		entryList = alphaListIterator.previous();
		// If the new number doesnt exist in the directory, the while loop will iterate
		// through the hashed lists to find the list of entries with matching surnme initials.  
		// It will then iterate through each entry in the list and compare its name and initials for a match. 
		// Once it finds a match, it will set the entrys telephone number to the new number.
		while (!duplicate) {
			for (Entry e : entryList) {
				if (name.equals(e.getSurname()) && initials.equals(e.getInitials())) {
					e.setPhoneExtension(newNumber);
					duplicate = true;
				}
			}
			if (alphaListIterator.hasNext()) {
				entryList = alphaListIterator.next();
			} else {
				duplicate = true;
			}
		}
	}

	@Override
	public String printDirectory() {
		String directory = "";
		boolean done = false;
		ListIterator<List<Entry>> alphaListIterator = alphaList.listIterator();
		List<Entry> entryList = alphaListIterator.next();
		while (!done) {
			for (Entry e : entryList) {
				directory = directory + e.getSurname() + "\t" + e.getInitials() + "\t" + e.getPhoneExtension() + "\n";
			}
			if (alphaListIterator.hasNext()) {
				entryList = alphaListIterator.next();
			} else {
				done = true;
			}
		}
		return directory;
	}

}
