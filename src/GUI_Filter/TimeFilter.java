package GUI_Filter;

import java.util.Calendar;
import java.util.Date;

import MergedCSV.Sample;
import MergedCSV.TimeCorrector;

public class TimeFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1625053601231834310L;
	// private Date maxD, minD;
	private Calendar maxC, minC;

	/**
	 * 
	 * @param maxD
	 *            a given max Date.
	 * @param minD
	 *            a given min Date.
	 */
	public TimeFilter(Calendar maxD, Calendar minD) {
		this.maxC = maxD;
		this.minC = minD;
	}

	/**
	 * This function filter the lines by the user choice of time. Filters the
	 * samples within the stated range, Filters using Predicate - checks if the
	 * user's date and hour is as in the line, if not - removes the line.
	 * Predicate removeIf:
	 * https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * 
	 * @param linesUnited
	 *            a given list of lines from the CSV file.
	 */
	@Override
	public boolean checkSample(Sample sample) {
		
//		System.out.println(sample.getTime());
//		TimeCorrector.correctAndSetTime(sample.getTime());
		
		Calendar current = Calendar.getInstance();
		current.set(Integer.parseInt(sample.getTime().split(" ")[0].split("-")[0]),
				Integer.parseInt(sample.getTime().split(" ")[0].split("-")[1]),
				Integer.parseInt(sample.getTime().split(" ")[0].split("-")[2]),
				Integer.parseInt(sample.getTime().split(" ")[1].split(":")[0]),
				Integer.parseInt(sample.getTime().split(" ")[1].split(":")[1]));

		return ((current.before(this.maxC)
				|| current.equals(this.maxC)) && (current.after(this.minC) || current.equals(this.minC)));
	}
}
