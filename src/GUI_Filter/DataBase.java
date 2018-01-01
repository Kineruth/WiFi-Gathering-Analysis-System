package GUI_Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Algorithms.Algorithms;
import KML.Coordinate;
import MergedCSV.Sample;
import de.micromata.opengis.kml.v_2_2_0.Point;

public class DataBase {

	private List<Sample> lsp;	
	
	
	
	public void addData(List<Sample> samples){
		this.lsp.addAll(samples);
		this.lsp = lsp.stream().distinct().collect(Collectors.toList());
	}
	
	public void deleteAllData(){
		this.lsp.removeAll(null);
	}
	
	public List<Sample> restoreData(){
		return this.lsp;
	}
	/**
	 * This function sends a given sample without coordinates to be calculated and 
	 * returns its calculated coordinate.
	 * @param s a given sample without coordinates to be calculated.
	 * @return the given sample's calculated coordinate.
	 */
	public Coordinate getAlgo2(Sample s){
		List<Sample> smp = new ArrayList<Sample>();
		Algorithms algo = new Algorithms();
		smp.add(s);
		smp = algo.userLocation(this.lsp, smp, 4);
		Sample sample = new Sample(smp.get(0));
		Coordinate p =new Coordinate(sample.getLAT(),sample.getLON(),sample.getALT());
		return  p;
		}
}
