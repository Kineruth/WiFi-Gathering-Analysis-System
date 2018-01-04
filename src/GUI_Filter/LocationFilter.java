package GUI_Filter;

import MergedCSV.Sample;

public class LocationFilter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313159370127604726L;
	private double maxLAT,minLAT,maxLON,minLON,maxALT,minALT;

	public LocationFilter( double maxLAT,double minLAT,double maxLON,double minLON,double maxALT,double minALT){
		this.maxLAT = maxLAT;
		this.maxLON = maxLON;
		this.maxALT = maxALT;
		this.minLAT=minLAT;
		this.minLON = minLON;
		this.minALT=minALT;
	}
	/**
	  * This function filters the lines by the user choice of location. 
	 * Filters using Predicate - checks if  the line's point is within a given range , if not - removes the line.
	 * Meaning it deletes all samples not in the given range.
	 * @param linesUnited a given list of lines from the CSV file. 
	 */
		@Override
		public boolean checkSample(Sample sample) {
			return !(Double.parseDouble(sample.getLAT())<=this.maxLAT && Double.parseDouble(sample.getLAT())>=this.minLAT
					&&Double.parseDouble(sample.getLON())<= this.maxLON && Double.parseDouble(sample.getLON())>= this.minLON
					&&Double.parseDouble(sample.getALT())<= this.maxALT && Double.parseDouble(sample.getALT())>= this.minALT);
		}
		
		
}
