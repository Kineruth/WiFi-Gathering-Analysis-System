package GUI_Filter;

import java.util.List;
import java.util.function.Predicate;

import MergedCSV.Sample;

public class DeviceFilter {

	
	/**
	 * This function filters the lines by the user choice of device ID.
	 * Filters the samples - removes all except specific given device.
	 * Filters using Predicate - checks if  the user's device ID is as stated in the line , if not - removes the line.
	 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * @param linesUnited a given list of lines from the CSV file. 
	 */
		public void filterByID(List<Sample> samples, String device) {
					Predicate<Sample> samplePredicate = s-> !(s.getID().equals(device));
				samples.removeIf(samplePredicate);	
		}
		
		/**
		 * This function filters the lines by the user choice of device ID.
		 * Filters all the samples -removes only specific given device
		 * Filters using Predicate - checks if  the user's device ID is as stated in the line , if not - removes the line.
		 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
		 * @param linesUnited a given list of lines from the CSV file. 
		 */
			public void excludingGivenID(List<Sample> samples, String device) {
						Predicate<Sample> samplePredicate = s-> (s.getID().equals(device));
						samples.removeIf(samplePredicate);	
			}
}
