package Algorithms;

import java.util.Comparator;

import MergedCSV.Sample;
/**
 * This class is of type comparator.
 * It compares every two samples by their PI value,
 * needed in order to sort a list of samples by their PI.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class PI_Comparator implements Comparator {
	@Override
	public int compare(Object obj1, Object obj2){
		Sample s1 = (Sample) obj1;
		Sample s2 = (Sample) obj2;
		return (s1.getPI() + "").compareTo((s2.getPI() + ""));
	}
}
