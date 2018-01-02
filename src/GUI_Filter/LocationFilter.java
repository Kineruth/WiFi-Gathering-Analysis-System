package GUI_Filter;

import java.util.List;
import java.util.function.Predicate;

import MergedCSV.Sample;

public class LocationFilter implements Filter{

	private double maxLAT,minLAT,maxLON,minLON,maxALT,minALT;

//	Predicate<Sample> samplePredicate = s-> checkSample(s);
//	samples.removeIf(samplePredicate);
	
	/**
	  * This function filters the lines by the user choice of location. 
	 * Filters using Predicate - checks if  the line's point is within a given range , if not - removes the line.
	 * Meaning it deletes all samples not in the given range.
	 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * @param linesUnited a given list of lines from the CSV file. 
	 * @exception Exception e if the user entered invalid input.
	 */
		@Override
		public boolean checkSample(Sample sample) {
			return !(Double.parseDouble(sample.getLAT())<=this.maxLAT && Double.parseDouble(sample.getLAT())>=this.minLAT
					&&Double.parseDouble(sample.getLON())<= this.maxLON && Double.parseDouble(sample.getLON())>= this.minLON
					&&Double.parseDouble(sample.getALT())<= this.maxALT && Double.parseDouble(sample.getALT())>= this.minALT);
		}
		
		
}
