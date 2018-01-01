package GUI_Filter;

import java.util.List;
import java.util.function.Predicate;

import MergedCSV.Sample;

public class LocationFilter {

	
	/**
	  * This function filters the lines by the user choice of location. 
	 * Filters using Predicate - checks if  the line's point is within a given range , if not - removes the line.
	 * Meaning it deletes all samples not in the given range.
	 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * @param linesUnited a given list of lines from the CSV file. 
	 * @exception Exception e if the user entered invalid input.
	 */
		public void withGivenPlace(List<Sample> samples, double maxLAT,double maxLON, double maxALT, double minLAT, double minLON, double minALT) {
//				Coordinate p1 = new Coordinate(userLat, userLon,0+""); 
//				Predicate<Sample> samplePredicate = s-> !(p1.pointInCircle(new Coordinate(s.getLAT(), s.getLON(),s.getALT()),Double.parseDouble(userRadius)));
		
			/* do we need a radius?!  */
			Predicate<Sample> samplePredicate = s-> !(Double.parseDouble(s.getLAT())<=maxLAT && Double.parseDouble(s.getLAT())>=minLAT
					&&Double.parseDouble(s.getLON())<= maxLON && Double.parseDouble(s.getLON())>= minLON
					&&Double.parseDouble(s.getALT())<= maxALT && Double.parseDouble(s.getALT())>= minALT);
				samples.removeIf(samplePredicate);
		}
		
		/**
		  * This function filters the lines by the user choice of location.
		 * Filters using Predicate - checks if  the line's point is within a given range , if not - removes the line.
		 * Meaning it deletes all samples found in the given range.
		 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
		 * @param linesUnited a given list of lines from the CSV file. 
		 * @exception Exception e if the user entered invalid input.
		 */
			public void withoutGivenPlace(List<Sample> samples, double maxLAT,double maxLON, double maxALT, double minLAT, double minLON, double minALT) {
//					Coordinate p1 = new Coordinate(userLat, userLon,0+""); 
//					Predicate<Sample> samplePredicate = s-> !(p1.pointInCircle(new Coordinate(s.getLAT(), s.getLON(),s.getALT()),Double.parseDouble(userRadius)));
			
				/* do we need a radius?!  */
				Predicate<Sample> samplePredicate = s-> !(Double.parseDouble(s.getLAT())<=maxLAT && Double.parseDouble(s.getLAT())>=minLAT
						&&Double.parseDouble(s.getLON())<= maxLON && Double.parseDouble(s.getLON())>= minLON
						&&Double.parseDouble(s.getALT())<= maxALT && Double.parseDouble(s.getALT())>= minALT);
					samples.removeIf(samplePredicate);
			}
		
		
}
