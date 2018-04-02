package ly.sohaib.seleniumCollegeID;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
/**
 * The class that's responsible for submitting and retrieving the data.
 * @author Sohaib
 * @version 1.0.0
 *
 */
public class GrabEm {
	private static final String URL = "http://cctt.edu.ly/result/index.php";
	private static final String DESKTOP_FOLDER = "/Users/Sohaib/Desktop/Debug Images/";
	private WebDriver driver;
	private static StudentData student = StudentData.getInstance();
	private WebElement birthdayElement;
	private WebElement IDElement;
	/**
	 * normal default constructor with empty body.
	 */
	public GrabEm() {
	}
	/**
	 * Initialize a new driver using RemoteWebDriver and with Chrome capabilities, connects to 
	 * "http://cctt.edu.ly/result/index.php"
	 * @see RemoteWebDriver
	 */
	public void connect() {
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:9515"), new ChromeOptions());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(URL);
	}
	/**
	 * gets the field using id "form-password"
	 * @return the WebElement representing the field.
	 */
	public WebElement getBirthdayField() {
		return driver.findElement(By.id("form-password"));
		
	}
	/**
	 * gets the field using id "form-username"
	 * @return the WebElement representing the field.
	 */
	public WebElement getIDField() {
		return driver.findElement(By.id("form-username"));
	}
	/**
	 * closes the window.
	 */
	public void close() {
		driver.close();
	}
	/**
	 * @deprecated
	 * Takes screenshot of the current ViewPort
	 * @param name name that will be given to the png
	 */
	public void TakeScreenShot(String name) {
		TakesScreenshot screenshotFile = (TakesScreenshot) driver;
		File s = screenshotFile.getScreenshotAs(OutputType.FILE);
		File dest = new File(DESKTOP_FOLDER+name+".png");
		try {
			FileUtils.copyFile(s, dest);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Uses tagName to get grades "tr"
	 * @return List containing grades
	 */
	public List<WebElement> getGrades() {
		return driver.findElements(By.tagName("tr"));
	}
	/**
	 * Uses className to get info "pp"
	 * @return List containing info
	 */
	public List<WebElement> getInfo() {
		return driver.findElements(By.className("pp"));
	}
	/**
	 * makes a txt file in the directory with an ID-birthday combination title.<br>
	 * added a -{@code getName()} to the combination.
	 * @return the file to be written in
	 */
	public File makeFile() {
		return new File(DESKTOP_FOLDER+student.getID()+"-"+student.getBirthDay()+"-"+getName()+".txt"); 
	}
	/**
	 * writes everything to the file
	 * @param soj the file that was made in the directory.
	 * @param info the name and year and specialty of the student
	 * @param grades the grades of the student
	 * @throws IOException
	 */
	public void writeToFile(File soj, List<WebElement> info, List<WebElement> grades) throws IOException {
		for (WebElement element : info) {
			writeText(soj,element);
		}
		for (WebElement element : grades) {
			writeText(soj, element);
		}
	}
	/**
	 * helper method to {@link writeToFile}, it takes every td field in the element and write it in a 
	 * separate line using the {@code System.lineSeparator()} method which is equal to adding \r\n to the
	 * end of the line.
	 * @param soj the file to write to
	 * @param element the current td element
	 * @throws IOException
	 */
	private void writeText(File soj, WebElement element) throws IOException {
		FileUtils.writeStringToFile(soj,element.getText()+System.lineSeparator(),Charset.defaultCharset(),true);
	}
	/**
	 * checks if the submitted ID&Birthday were correct or resulted in an alert
	 * @return true if there is an alert present and accept it or false otherwise.
	 */
	public boolean checkAlert() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, 3);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public StudentData getStudent() {
		return student;
	}
	/**
	 * <b>Note:</b> I made a try/catch statement here to handle the error that's not handled by the
	 * checkError method. why? because the checkError method checks inside an if statement, and when
	 * there's no element.... shit why did I lose focus and forgot what to write
	 * anyways, it handles the last alert from the previous submit.
	 */
	public void start() {
		try{
			birthdayElement = getBirthdayField();
			IDElement = getIDField();
		} catch(Exception e3) {
			WebDriverWait wait = new WebDriverWait(driver, 3);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
		}
	}
	/**
	 * sends the current student ID&Birthday to the fields and submit the form
	 */
	public void send() {
		birthdayElement.sendKeys(getStudent().getBirthDay());
		IDElement.sendKeys(getStudent().getID());
		birthdayElement.submit();
	}
	/**
	 * Navigates to "http://cctt.edu.ly/result/index.php"
	 */
	public void navigateToHome() {
		driver.get(URL);//tried navigate.toback but it caused problems in send method so making a new fresh page is better.
	}
	/**
	 * Save the info&grades to the hard drive.
	 */
	public void save() {
		List<WebElement> info = getInfo();
		List<WebElement> grades = getGrades();
		File soj = makeFile();
		try {
			writeToFile(soj,info,grades);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * removes the ( :إسم الطالب) 
	 * @return the full name of the student which can be used in the file name.
	 */
	public String getName() {
		WebElement element;
		try{
			element = driver.findElement(By.className("pp"));//it's the first encountered element with this class name
		} catch (NoSuchElementException er) {
			return "Unknown";
		}
		String[] name = element.getText().split(": ");
		return name[1];
	}
	/******************************************************************************************************
	 *  everything works. this is for the db thing which was annoying. 
	 ******************************************************************************************************/
	public void prepareDB() {
		List<WebElement> info = getInfo();
		List<WebElement> grades = getGrades();
		/***************
		 * Student Table
		 ***************/
		String[] name = info.get(0).getText().split(": ");
		DBUtils.setNAME(name[1]);//sets the name
		String[] ID = info.get(1).getText().split(": ");
		DBUtils.setID(Integer.parseInt(ID[1]));//sets the ID
		String[] semester = info.get(2).getText().split(": ");
		DBUtils.setSEMESTER(semester[1]);//sets the semester
		String[] department = info.get(3).getText().split(": ");
		DBUtils.setDEPARTMENT(department[1]);//sets the department
		
		DBUtils.updateStudentTable();
		/***************
		 * RESULT Table
		 ***************/
		grades.remove(0);//to remove the th elements
		for (int i = 0; i < grades.size()-1; i++) {//to loop over everything except the last element
			List<WebElement> e = grades.get(i).findElements(By.tagName("td"));
			goOnNormal(e);
		}
		extractPercentages(grades.get(grades.size()-1));//gets the last element
		DBUtils.updateResultTable();
		DBUtils.updatePercentageTable();
	}
		
	private void goOnNormal(List<WebElement> e) {
		DBUtils.addCODE(e.get(0).getText());
		DBUtils.addSubjectName(e.get(1).getText());
		DBUtils.addGRADE(e.get(2).getText());
	}
	/****************************** CAUTION! UGLY CODE AHEAD**********************************************
	 * 
	 * I'm sorry, but this was necessary to crack down that ugly td tag, I'm leaving the sysouts just 
	 * so you can understand
	 * 
	 *****************************************************************************************************/
	private void extractPercentages(WebElement element) {
		String[] firstSplit = element.getText().trim().split(" :");
		String[] secondSplit = firstSplit[1].trim().split(":");
		//System.out.println(secondSplit[0]);
		//System.out.println(secondSplit[1].trim());
		String[] ThirdSplit = secondSplit[0].split("\\s");
		//System.out.println(ThirdSplit[0]);
		String o_percent = secondSplit[1].trim();
		String s_percent = ThirdSplit[0];
		
		DBUtils.setSEMESTER_PERCENTAGE(Double.valueOf(s_percent));
		DBUtils.setOVERALL_PERCENTAGE(Double.parseDouble(o_percent));	
	}
}
