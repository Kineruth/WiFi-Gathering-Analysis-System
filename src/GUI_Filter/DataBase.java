package GUI_Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Algorithms.Algorithms;
import KML.Coordinate;
import MergedCSV.Sample;
import de.micromata.opengis.kml.v_2_2_0.Point;

public abstract class DataBase implements List<Sample> {

	public static List<Sample> dataBase=new ArrayList<Sample>();	;
	public static List<Sample> copyDataBase;

	/**
	 * Adds new samples to the database.
	 * @param samples a given list of samples.
	 */
	public static void addData(List<Sample> samples){
		DataBase.dataBase.addAll(samples);
		DataBase.dataBase.stream().distinct().collect(Collectors.toList());
	}
	
//	public List<Sample> getDataBase() {
//		return DataBase.dataBase;
//	}

	public static void setDataBase(List<Sample> lsp) {
		DataBase.dataBase = lsp;
	}
/**
 * Deletes all samples from the database.
 */
	public static void deleteAllData(){
		DataBase.dataBase.removeAll(DataBase.dataBase);
	}
	/**
	 * This function saves the database from before the filter was done.
	 * @return untouched database.
	 */
	public static List<Sample> restoreData(){
		return DataBase.dataBase;
	}
	/**
	 * Creates a copy of the current database.
	 */
	public static void setCopyDataBase(){
		DataBase.copyDataBase = new ArrayList<Sample>(DataBase.dataBase);
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
		smp = algo.userLocation(DataBase.dataBase, smp, 4);
		Sample sample = new Sample(smp.get(0));
		Coordinate p =new Coordinate(sample.getLAT(),sample.getLON(),sample.getALT());
		return  p;
		}
}
