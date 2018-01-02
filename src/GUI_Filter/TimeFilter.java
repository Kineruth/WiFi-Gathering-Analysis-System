package GUI_Filter;

import MergedCSV.Sample;

public class TimeFilter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1625053601231834310L;
	private int maxY,  maxM,  maxD,  minY,  minM,  minD,  maxH,  maxMin,  minH,  minMin;
		
			/**
			 * This function filter the lines by the user choice of time.
			 * Filters the samples within the stated range,
			 * Filters using Predicate - checks if the user's date and hour is as in the line, if not - removes the line.
			 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
			 * @param linesUnited a given list of lines from the CSV file. 
			 */
		@Override
		public boolean checkSample(Sample sample) {
			return  !(Integer.parseInt(sample.getTime().split(" ")[0].split("-")[0])<=this.maxY && Integer.parseInt(sample.getTime().split(" ")[0].split("-")[0])>=this.minY
					&& Integer.parseInt(sample.getTime().split(" ")[0].split("-")[1])<=this.maxM && Integer.parseInt(sample.getTime().split(" ")[0].split("-")[1])>=this.minM
					&& Integer.parseInt(sample.getTime().split(" ")[0].split("-")[2])<=this.maxD && Integer.parseInt(sample.getTime().split(" ")[0].split("-")[2])>=this.minD
					&& Integer.parseInt(sample.getTime().split(" ")[1].split(":")[0])<=this.maxH &&Integer.parseInt(sample.getTime().split(" ")[1].split(":")[0])>= this.minH
					&& Integer.parseInt(sample.getTime().split(" ")[1].split(":")[1]) <= this.maxMin && Integer.parseInt(sample.getTime().split(" ")[1].split(":")[1])>= this.minMin);
		}
}
