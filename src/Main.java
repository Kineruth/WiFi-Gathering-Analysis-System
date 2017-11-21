package src;

public class Main {

	public static void main(String[] args) {

		//put a specific path's directory to make CSV file
//		MergeCSVfiles t = new MergeCSVfiles("C:\\Users\\admin\\Desktop\\Task0");
//		t.sortDirFiles();
		
		//put a specific path to CSV file to make KML file
		ConvertToKML k = new ConvertToKML("C:\\Users\\admin\\Desktop\\Task0-2017.11.20.22.55.36.csv");
		k.createFile();

		

	}

}
