package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeCorrectorTest {

	@Test
	public void testSetTimeKMLFormat() {
		TimeCorrector A=new TimeCorrector();
		String result=A.setTimeKMLFormat("2017-5-23 14:45:23");
		assertEquals("2017-5-23T14:45:23",result);
	}



}
