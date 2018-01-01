package GUI_Filter;

import java.util.List;
import java.util.function.Predicate;

import MergedCSV.Sample;

public class TimeFilter {

	
	/**
	 * This function filter the lines by the user choice of time.
	 * Filters the samples within the stated range,
	 * Filters using Predicate - checks if the user's date and hour is as in the line, if not - removes the line.
	 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * @param linesUnited a given list of lines from the CSV file. 
	 */
		public void withGivenTime(List<Sample> samples, int maxY, int maxM, int maxD, int minY, int minM, int minD, int maxH, int maxMin, int minH, int minMin) {		
			Predicate<Sample> samplePredicate = s -> !(Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])<=maxY && Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])>=minY
						&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])<=maxM && Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])>=minM
						&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])<=maxD && Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])>=minD
						&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])<=maxH &&Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])>= minH
						&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[1]) <= maxMin && Integer.parseInt(s.getTime().split(" ")[1].split(":")[1])>= minMin);
				samples.removeIf(samplePredicate);

		}
		
		/**
		 * This function filter the lines by the user choice of time.
		 * Filters the samples for excluding it the stated range.
		 * Filters using Predicate - checks if the user's date and hour is as in the line, if not - removes the line.
		 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
		 * @param linesUnited a given list of lines from the CSV file. 
		 */
			public void withoutGivenTime(List<Sample> samples,int maxY, int maxM, int maxD, int minY, int minM, int minD, int maxH, int maxMin, int minH, int minMin) {		
						Predicate<Sample> samplePredicate = s -> (Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])<=maxY && Integer.parseInt(s.getTime().split(" ")[0].split("-")[0])>=minY
								&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])<=maxM && Integer.parseInt(s.getTime().split(" ")[0].split("-")[1])>=minM
								&& Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])<=maxD && Integer.parseInt(s.getTime().split(" ")[0].split("-")[2])>=minD
								&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])<=maxH &&Integer.parseInt(s.getTime().split(" ")[1].split(":")[0])>= minH
								&& Integer.parseInt(s.getTime().split(" ")[1].split(":")[1]) <= maxMin && Integer.parseInt(s.getTime().split(" ")[1].split(":")[1])>= minMin);
						samples.removeIf(samplePredicate);
	}
}
