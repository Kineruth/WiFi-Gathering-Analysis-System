package GUI_Filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import KML.ConvertCSVToKML;
import KML.LinesToSamples;
import MergedCSV.FileFormat;
import MergedCSV.MergeCSVfiles;

public class Wraper {

	public static void folderAdded( String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		 DataBase.addData(mg.getSamplesFromFiles());
	}

	public static void mergedFileAdded(String filePath) throws IOException {
		FileFormat fm = new FileFormat();
		LinesToSamples ls = new LinesToSamples();
		File f = new File(filePath);
		if (fm.checkMergedCSVFormat(f))
			 DataBase.addData(ls.convertLines(ls.readCSV(filePath)));
	}

	public static void saveMergedCSV() {
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(DataBase.dataBase);
	}

	public static void saveAsKML() throws FileNotFoundException, MalformedURLException {
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(DataBase.dataBase);
	}

	public static void clearance() {
		System.out.println("Samples amount before delete: " +  DataBase.dataBase.size());
		DataBase.deleteAllData();
		System.out.println("Samples amount after delete: " +DataBase.dataBase.size());
	}

	public static void oneFilter(int choice, Filter f){
		if(choice==0){//not Filter
			OriginalFilter f1 = new OriginalFilter(f);
			SamplesPredicate p = new SamplesPredicate();
			DataBase.setCopyDataBase();
			p.filterWithPredicate(DataBase.copyDataBase, f1);
		}
		if(choice==1){//original Filter
			
		}
		
	}
	
//	public void twoFilters(int choice1, int choice2){
//		if(choice1==0){//Filter by add
//			
//		}
//		if(choice==1){
//			
//		}
//		if(choice==2){
//			
//		}
//		
//	} 
}
