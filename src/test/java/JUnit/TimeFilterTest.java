package JUnit;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import GUI_Filter.Filter;
import GUI_Filter.TimeFilter;
import MergedCSV.Sample;

public class TimeFilterTest {

	@Test
	public void testCheckSample() {
		Date max = new Date(), min = new Date();

		String s = "2017-12-3 20:40:00";
		String s2 = "2017-12-3 20:10:00";
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

		try {
			max = dt.parse(s);
			max.setMonth(11);
			max.setHours(22);
			min = dt.parse(s2);
			min.setMonth(11);
			min.setHours(22);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(max.toGMTString());
		System.out.println(min.toGMTString());
		
		TimeFilter t = new TimeFilter(max, min);
		String time = "2017-12-3 20:35:00";
		Sample sample = new Sample("device", time, "32.2", "34.2", "143", 0);

		boolean result = true;
		assertEquals(result, t.checkSample(sample));
	}

}
