package GUI_Filter;

import MergedCSV.Sample;

public class NotFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146958521635475934L;	
	private Filter f1;
	
	public NotFilter() {
		this.f1 = null;
	}
	public NotFilter(Filter f1) {
		this.f1 = f1;
	}
	
	public Filter getFilter() {
		return f1;
	}
	public void setFilter(Filter f1) {
		this.f1 = f1;
	}

	
	@Override
	public boolean checkSample(Sample sample) {
		return !(this.f1.checkSample(sample));
	}
	public String toString() {
		return "Not (" + this.f1 + ")";
	}

}
