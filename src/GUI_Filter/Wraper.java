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
	private DataBase db = new DataBase();
	
	public void folderAdded(String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		this.db.addData(mg.getSamplesFromFiles());
	}
	
	public void mergedFileAdded(String filePath) throws IOException{
		FileFormat fm = new FileFormat() ;
		LinesToSamples ls = new LinesToSamples() ;
		File f = new File(filePath);
		if(fm.checkMergedCSVFormat(f))
		this.db.addData(ls.convertLines(ls.readCSV(filePath)));
	}
	
	public void saveMergedCSV(){
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(this.db.getDataBase());
	}
	
	public void saveAsKML() throws FileNotFoundException, MalformedURLException{
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(this.db.getDataBase());
	}
	
	public void clearance(){
		this.db.deleteAllData();
	}
}
