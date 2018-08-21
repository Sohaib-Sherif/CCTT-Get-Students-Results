package ly.sohaib.seleniumCollegeID;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
/**
 * This class handles the making of the numerous ID's and birthday's. used to assign values to the
 * student instance variables.<br> it can't be instantiated and is utilized by the static methods
 * to poll and initialize the ArrayDeque which is used as queue 'FIFO'.
 * @author Sohaib
 * @version 2.0.0
 * @since 1.0.0
 */
public class StudentUtils {
	private static Deque<String> IDs = new ArrayDeque<String>();
	private static Deque<String> knownIDs = new ArrayDeque<>();
	private static Deque<String> knownBirthdays = new ArrayDeque<>();
	private static Deque<String> expiredIDs = new ArrayDeque<>();
	public static String[] birthdays = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001"};

	private StudentUtils() {
	}
	/**
	 * initializes the known ID and Birthday ArrayDeque and generate the rest of IDs that are not already known
	 * or expired(loads them both from corresponding files)
	 * This is called in StudentData private constructor.
	 */
	public static void init() {
		loadIDsAndBirthdays();
		getExpiredIDsFromFile();
		int term = 172001;//put the number of the semester you want to start from
		int limit = term+499;
		for(int loop = 1; loop <= 2 ; loop++) {//put the number of semesters untill the last one
			for(; term <= limit; term++) {
				String id = String.valueOf(term);
				if(!(knownIDs.contains(id)||expiredIDs.contains(id)))
					IDs.add(id);
			}
			if(loop % 2 == 0) {// depends, change it to 1 if you at a ربيع or 2 if خريف
				term+=501;
			}
			else {
				term+=8501;
			}
			limit = term+499;
		}
	}
	
	private static void loadIDsAndBirthdays() {
		File storedIDsAndBirthdays = new File("C:/Users/Sohaib/Desktop/Debug Images/Birthdays&IDs.txt");
		try {
			Scanner sc = new Scanner(storedIDsAndBirthdays);
			while(sc.hasNext()) {
				knownIDs.add(sc.next());
				knownBirthdays.add(sc.next());
				sc.nextLine();
			}
		sc.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void getExpiredIDsFromFile() {
		File expiredFile = new File("C:/Users/Sohaib/Desktop/Debug Images/ExpiredIDs.txt");
		try {
			Scanner sc = new Scanner(expiredFile);
			while(sc.hasNextLine()) {
				expiredIDs.add(sc.nextLine());
			}
		sc.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * gets and removes the ID from the ArrayDeque
	 * @return
	 */
	public static String getID() {
		return IDs.poll();
	}
	/**
	 * gets a year using an index
	 * @param index int representing the index of the year wanted
	 * @return the birthday year
	 */
	public static String getYear(int index) {
		return birthdays[index];
	}
	/**
	 * check if the dequeue is not empty
	 * @return <b>true if there are still more ID's
	 */
	public static boolean isNotEmpty() {
		return !IDs.isEmpty();
	}
	
	/**
	 * gets and removes the ID from the ArrayDeque
	 * @return
	 * @since 2.0.0
	 */
	public static String getKnownID() {
		return knownIDs.poll();
	}
	/**
	 * gets and removes the birthday from the ArrayDeque
	 * @return
	 * @since 2.0.0
	 */
	public static String getKnownBirthday() {
		return knownBirthdays.poll();
	}
	/**
	 * check if the dequeue is not empty
	 * @return <b>true if there are still more ID's
	 * @since 2.0.0
	 */
	public static boolean isKnownIDNotEmpty() {
		return !knownIDs.isEmpty();
	}
}
