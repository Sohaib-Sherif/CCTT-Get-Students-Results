package ly.sohaib.seleniumCollegeID;

import java.sql.Connection;

import org.openqa.selenium.UnhandledAlertException;

/**
 * Contains the main method which starts the program.
 * @author Sohaib
 * @version 1.0.0
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
		boolean isFound = false;
		while(StudentUtils.isNotEmpty()) {
			test.getStudent().setID(StudentUtils.getID());//polls the first Id from queue and assign it.
			for(int y = 0; y<=4; y++) {//was 4 before removal of 1999
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
				test.save();
				test.prepareDB();
				test.navigateToHome();
			}
			isFound = false;
		}
		test.close();
	}
}
