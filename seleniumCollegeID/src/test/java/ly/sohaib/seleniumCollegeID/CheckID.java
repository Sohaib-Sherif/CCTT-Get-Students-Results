package ly.sohaib.seleniumCollegeID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckID {
	
	@Before
	public void init() {
		StudentUtils.init();
	}

	@Test
	public void testFirstNumber() {
		Assert.assertEquals("162001", StudentUtils.getID());
	}
}
