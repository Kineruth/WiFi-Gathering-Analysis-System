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

	@Test
	public void testCorrectAndSetTime() {
		TimeCorrector C=new TimeCorrector();
		String s="29/10/2017  15:02:43";
		C.correctAndSetTime(s);
		assertEquals("29/10/2017  15:02:43",s);
		
		
	}

}
