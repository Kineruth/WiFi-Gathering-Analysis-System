package GUI_Filter;

import java.util.ArrayList;
import java.util.List;

import Algorithms.Algorithms;
import KML.Coordinate;
import MergedCSV.Sample;
import de.micromata.opengis.kml.v_2_2_0.Point;

public class DataBase {

	private List<Sample> lsp;
	/*
	 * Everytime the user will input a new file name/ folder name we will send it to be read from 
	 * and all the converted lines will be added to our database.
	 * When wanting to filter the database - wi'll copy the current and give them the new filtered one.
	 * need to have a clear option - remove all from database.
	 * need to change the filter option - instead of scanner.
	 * have a function that will call the merging CSV.- need to split the functions there.
	 * also for KML.
	 * Filter time: given max & min - give back only those between. date & time- hour maybe minutes.
	 * Filter Loc - given max & min to lat/lon/alt + radius. 
	 * Filter ID - given string device name - choose between only this or everything except this.
	 * undo filter. thats why we copied it.
	 * save filter in file -like boaz did + upload it.
	 * Filter or/ not /and with current and other filter = meaning when he saved the current filter, 
	 * upload it and we'll deal with this one and other chosen filter.
	 * create the 2 algorithms, when the sec one has 2 options. a given sample/ given up to 3 mac&signals - crate a new sample and send.
	 * when all this time it'll be sync with the current database. THREADS
	 */
	
	
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
