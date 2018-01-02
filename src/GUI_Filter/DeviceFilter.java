package GUI_Filter;

import MergedCSV.Sample;

public class DeviceFilter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7817571438387492159L;
	private String ID;

			/**
			 * This function filters the lines by the user choice of device ID.
			 * Filters the samples - removes all except specific given device.
			 * Filters using Predicate - checks if  the user's device ID is as stated in the line , if not - removes the line.
			 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
			 * @param linesUnited a given list of lines from the CSV file. 
			 */
		@Override
		public boolean checkSample(Sample sample) {
		return  !(sample.getID().equals(this.ID));
		}
}
