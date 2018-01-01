package GUI_Filter;

import KML.LinesToSamples;
import MergedCSV.MergeCSVfiles;

public class Wraper {
	private DataBase db = new DataBase();

	public void folderAdded(String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		mg.getSamplesFromFiles();
		this.db.addData(mg.getSamples());
	}
	
	public void mergedFileAdded(String filePath){
		
		LinesToSamples ls = new LinesToSamples() ;
		this.db.addData(ls.convertLines(ls.readCSV(filePath)));
	}
}
