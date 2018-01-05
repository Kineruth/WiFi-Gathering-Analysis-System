package GUI_Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Algorithms.Algorithms;
import KML.Coordinate;
import MergedCSV.Sample;
import de.micromata.opengis.kml.v_2_2_0.Point;

public abstract class DataBase implements List<Sample> {

	/**
	 * DataBade is the one we will work on, and everytime we want to filter,
	 * we will make a copy of it so when we want to restore the original,
	 * we could set the database to the copy.
	 */
	public static List<Sample> dataBase=new ArrayList<Sample>();	;
	public static List<Sample> copyDataBase;
	private static Filter currentFilter;
	private static String filterChoice;

	/**
	 * Adds new samples to the database.
	 * @param samples a given list of samples.
	 */
	public static void addData(List<Sample> samples){
		DataBase.dataBase.addAll(samples);
		DataBase.dataBase.stream().distinct().collect(Collectors.toList());
		System.out.println("Current samples: "+DataBase.dataBase.size());
	}
	
/**
 * Sets the current database.
 * @param lsp a given list of samples.
 */
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
	public static void restoreData(){
		System.out.println("copy size:"+ DataBase.copyDataBase.size());
		DataBase.dataBase = new ArrayList<Sample>(DataBase.copyDataBase);
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
	/**
	 * 
	 * @return the current Filter.
	 */
	public static Filter getCurrentFilter() {
		return DataBase.currentFilter;
	}
/**
 * 
 * @param currentFilter the current Filter.
 */
	public static void setCurrentFilter(Filter currentFilter) {
		DataBase.currentFilter = currentFilter;
	}
/**
 * 
 * @return the current filter choice.
 */
	public static String getFilterChoice() {
		return DataBase.filterChoice;
	}
/**
 * 
 * @param filterChoice a given filter choice.
 */
	public static void setFilterChoice(String filterChoice) {
		DataBase.filterChoice = filterChoice;
	}
}
