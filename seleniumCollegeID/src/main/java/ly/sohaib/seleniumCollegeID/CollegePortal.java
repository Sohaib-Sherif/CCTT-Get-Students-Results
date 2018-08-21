package ly.sohaib.seleniumCollegeID;

import java.sql.Connection;

import org.openqa.selenium.UnhandledAlertException;

/**
 * Contains the main method which starts the program.
 * @author Sohaib
 * @version 2.0.0
 */
public class CollegePortal {
	/**
	 * @param args
	 * @throws UnhandledAlertException handled by start method
	 * @see test.start()
	 */
	public static void main(String[] args) throws UnhandledAlertException {
		GrabEm test = new GrabEm();
		Connection con = DB.getConnection();
		test.connect();
		while(StudentUtils.isKnownIDNotEmpty()) {
			test.getStudent().setID(StudentUtils.getKnownID());
			test.getStudent().setBirthDay(StudentUtils.getKnownBirthday());
			test.start();
			test.send();
			if(!test.checkAlert()) {
				test.save();
				test.prepareDB();
				test.navigateToHome();
			}
			else {
				test.updateExpiredFile(test.getStudent().getID());
				System.out.println("Delete this ID "+test.getStudent().getID());
			}
		}
		if(StudentUtils.isNotEmpty()) {//why?becuz there might be none and all ID's are already known.
			boolean isFound = false;
			while(StudentUtils.isNotEmpty()) {
				test.getStudent().setID(StudentUtils.getID());//polls the first Id from queue and assign it.
				for(int y = 0; y<StudentUtils.birthdays.length; y++) {
					test.getStudent().setBirthDay(StudentUtils.getYear(y));//assign the current value of birthday
					System.out.println(test.getStudent().getID());
					test.start();
					test.send();
					//System.out.print(" - before if: "+isFound+" - ");
					if(!test.checkAlert()) {
						isFound = true;
						//System.out.print(" - inside if: "+isFound+"\n");
						break;//break if the combo of id and birthday was correct.
					}
					//System.out.print(" - after if: "+isFound+"\n");
				}
				//System.out.println("After the for loop: "+isFound);
				if(isFound) {
					//since this combo was correct, let's save it to our Known ID's file for future use.
					test.updateKnownFile(test.getStudent().getID(), test.getStudent().getBirthDay());
					test.save();
					test.prepareDB();
					test.navigateToHome();
				}
				isFound = false;
			}
		}
		test.close();
	}
}
