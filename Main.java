

public class Main {

	public static void main(String[] args) {

		//put path to a specific directory
//		MergeCSVfiles t = new MergeCSVfiles("C:\\Users\\admin\\Documents\\Computer Science\\Second Year\\First Sem\\מונחה עצמים\\Tasks\\Task0\\27.10");
//		t.sortDirFiles();
		
		//put a path to a specific CSV file
		ConvertToKML k = new ConvertToKML("C:\\Users\\admin\\Documents\\Computer Science\\Second Year\\First Sem\\מונחה עצמים\\Tasks\\Task0\\27.10.csv");
		k.createFile();

		

	}

}
