package ly.sohaib.seleniumCollegeID;

import java.util.Objects;
/**
 * model class of the student, applies the singleton pattern.<br>
 * the private constructor calls the {@link StudentUtils}.init() method.
 * UPDATE 19/8/2018 : This is not a typical model class nor is it a bean.
 * @author Sohaib
 * @version 1.0.0
 */
public class StudentData {
	private String ID;
	private String birthDay;
	private static StudentData student = null;


	private StudentData() {
		StudentUtils.init();
	}
	/**
	 * Ensures that there's only one instance of this class.
	 * @return a student object.
	 */
	public static StudentData getInstance() {
		return Objects.isNull(student)? new StudentData():student;
	}
	/**
	 * 
	 * @return the student ID.
	 */
	public String getID() {
		return ID;
	}
	/**
	 * changes the student id usually by the StudentUtils class.
	 * @param iD the ID to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * 
	 * @return the student birthday
	 */
	public String getBirthDay() {
		return birthDay;
	}
	/**
	 * 
	 * @param birthDay the birthday to set which is usually done by the StudentUtils class.
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
}