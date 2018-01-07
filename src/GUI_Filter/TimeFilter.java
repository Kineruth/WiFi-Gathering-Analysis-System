package GUI_Filter;

import java.util.Date;

import MergedCSV.Sample;

public class TimeFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1625053601231834310L;
	private Date maxD, minD;

	/**
	 * 
	 * @param maxD
	 *            a given max Date.
	 * @param minD
	 *            a given min Date.
	 */
	public TimeFilter(Date maxD, Date minD) {
		this.maxD = maxD;
		this.minD = minD;
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
		@SuppressWarnings("deprecation")
		Date current = new Date(Integer.parseInt(sample.getTime().split(" ")[0].split("-")[0]),
				Integer.parseInt(sample.getTime().split(" ")[0].split("-")[1]),
				Integer.parseInt(sample.getTime().split(" ")[0].split("-")[2]),
				Integer.parseInt(sample.getTime().split(" ")[1].split(":")[0]),
				Integer.parseInt(sample.getTime().split(" ")[1].split(":")[1]));

		return ((current.before(this.maxD) && current.getTime() <= this.maxD.getTime() || current.equals(this.maxD))
				&& ((current.after(this.minD) && current.getTime() >= this.minD.getTime())
						|| current.equals(this.minD)));
	}
}
