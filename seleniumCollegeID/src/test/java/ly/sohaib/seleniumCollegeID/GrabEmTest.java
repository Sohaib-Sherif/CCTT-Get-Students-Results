package ly.sohaib.seleniumCollegeID;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GrabEmTest {
	private GrabEm test;
	private StudentData student;
	
	
	@Before
	public void init() {
		test = new GrabEm();
		student = test.getStudent();
		test.connect();
		student.setBirthDay("1996");
		student.setID("162057");
		test.start();
		test.send();
		test.prepareDB();
			
	}
	@Ignore
	@Test
	public void testGetNameMethod() {
		assertEquals("صهيب سالم محمد الشريف", test.getName());
	}
	/*@Test
	public void test() {
		assertEquals(85.833, DBUtils.getSEMESTER_PERCENTAGE(),0.00000000000000000000015);
		System.out.println(DBUtils.getSEMESTER_PERCENTAGE());
		assertEquals(80.4, DBUtils.getOVERALL_PERCENTAGE(),0.00000000000000000000000015);
		System.out.println(DBUtils.getOVERALL_PERCENTAGE());
	}*/
	
	@After
	public void cleanup() {
		test.close();
	}

}
