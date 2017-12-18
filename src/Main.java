package src;

/**
 * Date: 23-11-2017
 * This class is the main class ,to run the whole project. The user needs to enter
 * the path for a directory - for class MergeCSVFiles, and a path to an arranged CSV file - for class ConvertToKML
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Main {

	public static void main(String[] args) {

		// put a specific path's directory to make CSV file
//		MergeCSVfiles t = new MergeCSVfiles("C:\\Users\\admin\\git\\Task1\\Wigle Wifi App - Exports - Examples");
//		t.sortDirFiles();

		
		
		// put a specific path to CSV file to make KML file 
//		ConvertCSVToKML k =	new ConvertCSVToKML("C:\\Users\\admin\\git\\Task1\\Wigle Wifi App - Exports - Examples - 2017.12.14.00.04.53.csv");
//		k.createFile();

		
		//Create a CSV file with all strongest mac correct location
		ArrangeCSV a = new ArrangeCSV();
		String filePath = "C:\\Users\\admin\\Desktop\\CombinedCSV\\_comb_all_BM2_.csv";
		String fileOutput="C:\\Users\\admin\\Desktop\\withoutCoordinates\\_comb_no_gps_ts1.csv";
		int number =4;
		a.macStrongestLocation(filePath, number);
//		a.userLocation(filePath, fileOutput, number);
	}

}
