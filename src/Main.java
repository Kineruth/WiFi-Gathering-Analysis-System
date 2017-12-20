package src;

/**
 * Date: 23-11-2017
 * Updated: 20-12-2017
 * This class is the main class ,to run the whole project. 
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Main {
/**
 * 
 * One: 
 * Create a merged CSV file with CSV files exported from the app WiGLE:
 * Call function sortDirFiles in class MergeCSVfiles.
 * Put in its constructor a specific path to a directory with those CSV files.
 * Two: 
 *  Create a KML file for Google Earth:
 * Call function createFile in class ConvertCSVToKML.
 *  Put in its constructor a specific path to the merged CSV file.
 *  Three: 
 *  Run Algorithm 1 - Create a CSV file with all strongest mac correct location:
 * filePath = the merged CSV file's path.
 * number =  a number of samples to be taken for the calculation.
 * Call function macStrongestLocation in class ArrangeCSV.
 * Four:
 * Run Algorithm 2 - Create a CSV file with the user location for every sample:
 * filePath = the merged CSV file's path.
 * fileOutput = the merged CSV file's path without coordinates.
 *  number =  a number of samples to be taken for the calculation.
 *  Call function userLocation in class ArrangeCSV.
 *  
 *  UNCOMMENT THE WANTED OPTION!
 */
	public static void main(String[] args) {

		/* ONE: */
		
		String diractory_Path = "C:\\Users\\admin\\Documents\\Computer Science\\Second Year\\First Sem\\OOP\\Tasks\\Task2\\Ex2\\data\\BM1\\Wifi_scans";
		MergeCSVfiles t = new MergeCSVfiles(diractory_Path);
		t.sortDirFiles();

		
		
		/* TWO: */
	
		String csv_Path = "C:\\Users\\admin\\git\\Task1\\Wigle Wifi App - Exports - Examples - 2017.12.14.00.04.53.csv";
		ConvertCSVToKML k =	new ConvertCSVToKML(csv_Path);
//		k.createFile();

		
		
		/* THREE & FOUR: */
		
		ArrangeCSV a = new ArrangeCSV();
		String filePath = "C:\\Users\\admin\\Documents\\Computer Science\\Second Year\\First Sem\\OOP\\Tasks\\Task2\\testing\\Boaz_Files\\_comb_all_BM2_.csv";
		String fileOutput="C:\\Users\\admin\\Documents\\Computer Science\\Second Year\\First Sem\\OOP\\Tasks\\Task2\\testing\\Boaz_Files\\_comb_no_gps_ts1.csv";
		int number =4;
		
//		a.macStrongestLocation(filePath, number); //algo 1
//		a.userLocation(filePath, fileOutput, number); //algo 2
	}

}
