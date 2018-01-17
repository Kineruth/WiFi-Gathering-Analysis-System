package GUI_Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Algorithms.Algorithms;
import KML.Coordinate;
import MergedCSV.Sample;
import de.micromata.opengis.kml.v_2_2_0.Point;

public abstract class DataBase implements List<Sample> {

	/**
	 * DataBase is the one we will work on, and every time we want to filter,
	 * we will make a copy of it so when we want to restore the original,
	 * we could set the database to the copy.
	 */
	public static List<Sample> dataBase=new ArrayList<Sample>();
	public static List<Sample> copyDataBase=new ArrayList<Sample>();
	private static Filter currentFilter;
	private static String filterChoice;
	private static List<String> folderPaths=new ArrayList<String>();
	private static List<String> filePaths = new ArrayList<String>();
	private static int macs=0;

	/**
	 * Adds new samples to the database.
	 * @param samples a given list of samples.
	 */
	public static void addData(List<Sample> samples){
		Set<Sample> set = new HashSet<Sample>();
		set.addAll(DataBase.dataBase);
		set.addAll(samples);
		
		DataBase.dataBase.clear();
		DataBase.dataBase.addAll(set);		
		DataBase.setCopyDataBase();
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
/**
 * 
 * @return this folderPath list.
 */
public static List<String> getFolderPaths() {
	return DataBase.folderPaths;
}
/**
 * Sets this list to the given one.
 * @param folderPaths a given FolderPath list
 */
public static void setFolderPaths(List<String> folderPaths) {
	DataBase.folderPaths = new ArrayList<String>(folderPaths);
}

public static void addFolderPath(String path){
	DataBase.folderPaths.add(path);
}
public static void removeFolderPath(String path){
	if(DataBase.folderPaths.contains(path)){
		for(String s : DataBase.folderPaths){
			if(s.equals(path)) DataBase.folderPaths.remove(s);
		}
	}
}
	public static void addFilePath(String path){
		DataBase.filePaths.add(path);
	}

	public static List<String> getFilePaths() {
		return DataBase.filePaths;
	}

	public static void setFilePaths(List<String> filePaths) {
		DataBase.filePaths = filePaths;
	}
//	public static void getMacsNumber(){
//		Algorithms a = new Algorithms();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				synchronized (DataBase.dataBase) {
//		
//		List<Sample> temp = new ArrayList<Sample>(DataBase.dataBase);
//		DataBase.macs= a.strongestMacLocation(temp, 4).size();
//		System.out.println("In thread mac");
//				}
//			}
//		}).start();
//	}

	public static int getMacs() {
		Algorithms a = new Algorithms();
		List<Sample> temp = new ArrayList<Sample>(DataBase.dataBase);
		DataBase.macs= a.strongestMacLocation(temp, 4).size();
		return DataBase.macs;
	}

	public static void setMacs(int macs) {
		DataBase.macs = macs;
	}
	
}
