package GUI_Filter;

import MergedCSV.Sample;

public class OrFilter implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2592421715383696890L;
	private Filter f1,f2;
	
	public OrFilter() {
		this.f1 = null;
		this.f2 = null;
	}
	public OrFilter(Filter f1, Filter f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	public Filter getF1() {
		return f1;
	}
	public void setF1(Filter f1) {
		this.f1 = f1;
	}
	public Filter getF2() {
		return f2;
	}
	public void setF2(Filter f2) {
		this.f2 = f2;
	}
	
	@Override
	public boolean checkSample(Sample sample) {
		return this.f1.checkSample(sample) || this.f2.checkSample(sample);
	}
	public String toString() {
		return "(" + this.f1 + " or " + this.f2 + ")";
	}

}


