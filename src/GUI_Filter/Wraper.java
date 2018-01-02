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

	public void folderAdded(DataBase db, String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		db.addData(mg.getSamplesFromFiles());
	}

	public void mergedFileAdded(DataBase db, String filePath) throws IOException {
		FileFormat fm = new FileFormat();
		LinesToSamples ls = new LinesToSamples();
		File f = new File(filePath);
		if (fm.checkMergedCSVFormat(f))
			db.addData(ls.convertLines(ls.readCSV(filePath)));
	}

	public void saveMergedCSV(DataBase db) {
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(db.getDataBase());
	}

	public void saveAsKML(DataBase db) throws FileNotFoundException, MalformedURLException {
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(db.getDataBase());
	}

	public void clearance(DataBase db) {
		System.out.println("Samples amount before delete: " + db.getDataBase().size());
		db.deleteAllData();
		System.out.println("Samples amount after delete: " + db.getDataBase().size());
	}

	public void filterSamples(DataBase db, int choice){
		if
		
	}
}
