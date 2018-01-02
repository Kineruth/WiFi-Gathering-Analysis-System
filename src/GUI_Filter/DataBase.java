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
	private List<Sample> filteredLsp;
	/**
	 * Default constructor.
	 */
	public DataBase(){
		this.lsp = new ArrayList<Sample>();	
		this.filteredLsp = new ArrayList<Sample>();	
	}
	/**
	 * Copy constructor.
	 * @param other other given list of samples.
	 */
	public DataBase(List<Sample> other){
		this.lsp = other;
	}
	/**
	 * Adds new samples to the database.
	 * @param samples a given list of samples.
	 */
	public void addData(List<Sample> samples){
		this.lsp.addAll(samples);
		this.lsp = lsp.stream().distinct().collect(Collectors.toList());
	}
	
	public List<Sample> getDataBase() {
		return this.lsp;
	}

	public void setDataBase(List<Sample> lsp) {
		this.lsp = lsp;
	}
/**
 * Deletes all samples from the database.
 */
	public void deleteAllData(){
		this.lsp.removeAll(lsp);
	}
	/**
	 * This function saves the database from before the filter was done.
	 * @return untouched database.
	 */
	public List<Sample> restoreData(){
		return this.lsp;
	}
	
	public void setFilteredData(List<Sample> s){
		this.filteredLsp = new ArrayList<Sample>(s);
	}
	public List<Sample> getFilteredData(){
		return this.filteredLsp;
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
