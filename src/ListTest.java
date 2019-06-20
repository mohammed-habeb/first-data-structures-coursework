/*
 * Name: Mohammed Habeb
 * Student ID: 180631441
 * 
 * Description: This is a class used to test the methods of ListDirectory.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ListTest {

	public static void main(String[] args) throws FileNotFoundException {
		ListDirectory phoneDirectory = new ListDirectory();
		Scanner inFile = new Scanner(new FileReader("C:\\Users\\moham\\Documents\\directory1.txt"));
		StopWatch stopwatch = new StopWatch();
		long addingTime = 0;
		
		
		
		//Adding entries
		while (inFile.hasNextLine()) {
			Scanner line = new Scanner(inFile.nextLine());
			String first = line.next();
			String surname = first;
			String second = line.next();
			String initials = second;
			String third = line.next();
			String number = third;
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.addEntry(new Entry(surname, initials, number));
			stopwatch.stop();
			addingTime += stopwatch.getElapsedTime();
			line.close();
		}
		System.out.println(phoneDirectory.printDirectory());
		System.out.println("Time taken to add all entries: " + addingTime + " Nanoseconds");
		inFile.close();
		stopwatch.reset();
		
		
		
		
		
		
		// Finding entries
		long total = 0;
		for (int i = 0; i <= 10000; i++) {
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.lookUp("Aanerud", "I.G.");
			stopwatch.stop();
			total += stopwatch.getElapsedTime();
		}
		System.out.println("Average time taken to find Aanerud: " + (total / 10000) + " Nanoseconds");
		
		
		stopwatch.reset();
		total = 0;
		for (int i = 0; i <= 10000; i++) {
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.lookUp("Fury", "N.J.");
			stopwatch.stop();
			total += stopwatch.getElapsedTime();
		}
		System.out.println("Average time taken to find Fury: " + (total / 10000) + " Nanoseconds");
		
		
		stopwatch.reset();
		total = 0;
		for (int i = 0; i <= 10000; i++) {
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.lookUp("Meadowcroft", "M.G.Q.");
			stopwatch.stop();
			total += stopwatch.getElapsedTime();
		}
		System.out.println("Average time taken to find Meadowcroft: " + (total / 10000) + " Nanoseconds");
		
		
		stopwatch.reset();
		total = 0;
		for (int i = 0; i <= 10000; i++) {
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.lookUp("Strange", "S.");
			stopwatch.stop();
			total += stopwatch.getElapsedTime();
		}
		System.out.println("Average time taken to find Strange: " + (total / 10000) + " Nanoseconds");
		
		
		stopwatch.reset();
		total = 0;
		for (int i = 0; i <= 10000; i++) {
			stopwatch.reset();
			stopwatch.start();
			phoneDirectory.lookUp("Zoldesy", "J.D.G.");
			stopwatch.stop();
			total += stopwatch.getElapsedTime();
		}
		System.out.println("Average time taken to find Zoldesy: " + (total / 10000) + " Nanoseconds");

		
		
		// Changing numbers of entries
		phoneDirectory.changeNumber("Zoldesy", "J.D.G.", "99999");
		phoneDirectory.changeNumber("Zola", "A.", "88888");
		phoneDirectory.changeNumber("Zenger", "R.S.K.", "77777");
		phoneDirectory.changeNumber("Yurek", "X.E.", "66666");
		System.out.println(phoneDirectory.printDirectory());
		
		
		
		
		// Deleting entries
		phoneDirectory.deleteEntry("10086");
		phoneDirectory.deleteEntry("10072");
		phoneDirectory.deleteEntry("10051");
		phoneDirectory.deleteEntry("10091");
		System.out.println(phoneDirectory.printDirectory());
	}

}
