package JUnit;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import GUI_Filter.TimeFilter;
import MergedCSV.Sample;

public class TimeFilterTest {


	@Test
	public void testCheckSample() {
		Calendar max = Calendar.getInstance();
		Calendar min = Calendar.getInstance();
		max.set(2017, 12, 3, 8, 40); 
		min.set(2017, 12, 3, 7, 10);
		
		TimeFilter t = new TimeFilter(max, min);
		String time  = "2017-12-3 8:35:00";
		Sample s = new Sample("device", time, "32.2", "34.2", "143", 0);
		
		boolean result = true;
		assertEquals(result,t.checkSample(s));
	}

}
