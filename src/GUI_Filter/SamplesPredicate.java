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
		// try {
		Predicate<Sample> samplePredicate = s -> !(f.checkSample(s));
		DataBase.dataBase.removeIf(samplePredicate);
//		DataBase.dataBase = new ArrayList<Sample>(
//				DataBase.dataBase.stream().filter(s -> f.checkSample(s)).collect(Collectors.<Sample>toList()));
		System.out.println("Samples after filtering: " + DataBase.dataBase.size());
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(new JFrame(), "Could Not Filter - Given
		// Input Does Not Exist In Database");
		// System.out.println("Could not filter - given input is does not exist
		// in database.");
		//
		// }

	}
}
