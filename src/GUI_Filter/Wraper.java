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
	
	public void mergedFileAdded(File f) throws IOException{
		FileFormat fm = new FileFormat() ;
		LinesToSamples ls = new LinesToSamples() ;
		if(fm.checkMergedCSVFormat(f))
		this.db.addData(ls.convertLines(ls.readCSV(f.getPath())));
	}
	
	public void saveMergedCSV(){
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(this.db.getDataBase());
	}
	
	public void saveAsKML() throws FileNotFoundException, MalformedURLException{
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(this.db.getDataBase());
	}
}
