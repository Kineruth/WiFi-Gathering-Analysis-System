package GUI_Filter;

import static org.junit.Assert.*;

import org.junit.Test;

import MergedCSV.Sample;

public class TimeFilterTest {


	@Test
	public void testCheckSample() {
		TimeFilter t = new TimeFilter(2017, 10, 3, 2017, 10, 3, 8, 40, 7, 40);
		String time  = "2017-10-27 16:35:00";
		Sample s = new Sample("device", time, "32.2", "34.2", "143", 0);
		
		boolean result = false;
		assertEquals(result,t.checkSample(s));
	}

}
