package GUI_Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MergedCSV.Sample;

public class SamplesPredicate {

	/**
	 * This function removes all the samples that does not satisfies the given
	 * choice of filter using predicate. Predicate removeIf:
	 * https://www.concretepage.com/java/jdk-8/java-8-list-example-with-foreach-removeif-replaceall-and-sort
	 * .
	 * 
	 * @param samples
	 *            a given list of samples.
	 * @param f
	 *            a given filter.
	 */
	public static void filterWithPredicate(Filter f) {
		System.out.println("Samples before filtering: " + DataBase.dataBase.size());
		DataBase.setCopyDataBase();
		Predicate<Sample> samplePredicate = s -> !(f.checkSample(s));
		DataBase.dataBase.removeIf(samplePredicate);
		System.out.println("Samples after filtering: " + DataBase.dataBase.size());
	}
}
