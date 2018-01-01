package GUI_Filter;

import java.io.Serializable;
import MergedCSV.Sample;
/**
 * This class represents a filter interface.
 * @author  Kineret Ruth Nahary && Yakir Amar.
 *
 */
public interface Filter extends Serializable {
	/**
	 * This function checks if the sample is of a specific standards.
	 * @param sample a given sample to check.
	 * @return true if it is, false if not.
	 */
	public boolean checkSample(Sample sample);

}
