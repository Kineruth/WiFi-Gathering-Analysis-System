package GUI_Filter;

import MergedCSV.Sample;

public class AndFilter implements Filter {

	/**
		 * 
		 */
	private static final long serialVersionUID = -8488893060294885154L;
	private Filter f1, f2;

	public AndFilter() {
		this.f1 = null;
		this.f2 = null;
	}

	public AndFilter(Filter f1, Filter f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

	public Filter getFilter1() {
		return f1;
	}

	public void setFilter1(Filter f1) {
		this.f1 = f1;
	}

	public Filter getFilter2() {
		return f2;
	}

	public void setFilter2(Filter f2) {
		this.f2 = f2;
	}

	@Override
	public boolean checkSample(Sample sample) {
		return this.f1.checkSample(sample) && this.f2.checkSample(sample);
	}

	public String toString() {
		return "(" + this.f1 + " and " + this.f2 + ")";
	}
}
