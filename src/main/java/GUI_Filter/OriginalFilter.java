package GUI_Filter;

import MergedCSV.Sample;

public class OriginalFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8974395765830064719L;
	private Filter f;

	public OriginalFilter() {
		this.f = null;
	}

	public OriginalFilter(Filter f) {
		this.f = f;
	}

	public Filter getFilter() {
		return f;
	}

	public void setFilter(Filter f) {
		this.f = f;
	}

	@Override
	public boolean checkSample(Sample sample) {
		return this.f.checkSample(sample);
	}

	@Override
	public String toString() {
		return "Original (" + this.f + ")";
	}

}
