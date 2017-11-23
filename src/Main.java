package src;

/**
 * This class is the main class,to run the whole project. The user need to enter
 * the path for a directory - for class MergeCSVFiles, and file's path - for
 * class ConvertToKML
 * 
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Main {

	public static void main(String[] args) {

//		// put a specific path's directory to make CSV file
//		MergeCSVfiles t = new MergeCSVfiles("C:\\Users\\admin\\Desktop\\Task0");
//		t.sortDirFiles();

		// put a specific path to CSV file to make KML file 
		ConvertCSVToKML k =	new ConvertCSVToKML("C:\\Users\\admin\\Desktop\\Task0 - 2017.11.23.04.29.16.csv");
		k.createFile();

	}

}
