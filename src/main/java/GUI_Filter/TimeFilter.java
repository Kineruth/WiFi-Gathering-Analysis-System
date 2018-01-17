package GUI_Filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import MergedCSV.Sample;
import MergedCSV.TimeCorrector;

public class TimeFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1625053601231834310L;
	private Date max, min;
	// private Date maxD, minD;
	// private Calendar min;

	/**
	 * 
	 * @param maxD
	 *            a given max Date.
	 * @param minD
	 *            a given min Date.
	 */
	public TimeFilter(Date maxD, Date minD) {
		this.max = maxD;
		this.min = minD;
	}

	/**
	 * This function filter the lines by the user choice of time. Filters the
	 * samples within the stated range, Filters using Predicate - checks if the
	 * user's date and hour is as in the line, if not - removes the line.
	 * 
	 * @param sample a given sample.
	 */
	@Override
	public boolean checkSample(Sample sample) {
		String s = sample.getTime().split(" ")[0].split("-")[0] + "-" + sample.getTime().split(" ")[0].split("-")[1] 
				+ "-" + sample.getTime().split(" ")[0].split("-")[2] + " "
				+ (Integer.parseInt(sample.getTime().split(" ")[1].split(":")[0]) + 2) + ":"
				+ sample.getTime().split(" ")[1].split(":")[1] + ":00";
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
		Date current = new Date();
		try {
			current = dt.parse(s);
			current.setMonth( Integer.parseInt(sample.getTime().split(" ")[0].split("-")[1] )-1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return current.after(this.min) && this.max.getTime() >= current.getTime()&& current.before(this.max) && this.min.getTime() <= current.getTime();
	}
}
