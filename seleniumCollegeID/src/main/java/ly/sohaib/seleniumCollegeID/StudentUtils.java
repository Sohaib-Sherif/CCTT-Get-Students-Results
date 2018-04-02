package ly.sohaib.seleniumCollegeID;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * This class handles the making of the numerous ID's and birthday's. used to assign values to the
 * student instance variables.<br> it can't be instanitaited and is utilized by the static methods
 * to poll and initialize the ArrayDeque which is used as queue 'FIFO'.
 * @author Sohaib
 * @version 1.0.0
 * @since 1.0.0
 */
public class StudentUtils {
	private static Deque<String> IDs = new ArrayDeque<String>();
	private static String[] birthdays = /*93*/{"1994","1995","1996","1997","1998"};//1999
	
	private StudentUtils() {
	}
	/**
	 * initializes the ArrayDeque using several for methods, from 131 to 172.
	 */
	public static void init() {
		/*for(int id131 = 1; id131 <= 200; id131++ )
			IDs.add("131"+String.format("%03d",id131));
		for(int id132 = 1; id132 <= 200; id132++)
			IDs.add("132"+String.format("%03d",id132));
		for(int id141 = 1; id141 <= 200; id141++)
			IDs.add("141"+String.format("%03d",id141));*/
		/*for(int id142 = 276; id142 <= 300; id142++)
			IDs.add("142"+String.format("%03d",id142)); Done, wonder If I */
		/*for(int id151 = 158; id151 <= 200; id151++)
			IDs.add("151"+String.format("%03d",id151)); Done, probably*/
		/*for(int id152 = 258; id152 <= 300; id152++)
			IDs.add("152"+String.format("%03d",id152)); Done, check with 1998*/
		/*for(int id161 = 301; id161 <= 350; id161++)
			IDs.add("161"+String.format("%03d",id161)); Done */
		/*for(int id162 = 61; id162 <= 200; id162++)
			IDs.add("162"+String.format("%03d", id162)); Done, I think. */
		/*for(int id171 = 1; id171 <= 500; id171++)
			IDs.add("171"+String.format("%03d",id171));
		for(int id172 = 1; id172 <= 500; id172++)
			IDs.add("172"+String.format("%03d",id172));*/
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
	
}
