package GUI_Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import MergedCSV.Sample;

public class SamplesPredicate {

	/**
	 * This function removes all the samples that does not satisfies the given choice of filter using predicate.
	 * Predicate removeIf: https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort .
	 * @param samples a given list of samples.
	 * @param f a given filter.
	 * @return a filtered list of samples.
	 */
	public static List<Sample> filterWithPredicate(List<Sample> samples, Filter f){
		List<Sample> temp = new ArrayList<Sample>(samples);
		Predicate<Sample> samplePredicate = s-> f.checkSample(s);
		temp.removeIf(samplePredicate);
		return temp;
	}
}
