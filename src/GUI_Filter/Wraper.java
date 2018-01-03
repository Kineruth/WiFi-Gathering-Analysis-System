package GUI_Filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

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

public static void writeCurrentFilter(Filter f) throws IOException{
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	FileOutputStream fOut;
	try {
		fOut = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\Filter - "+timeStamp+".txt"));
		ObjectOutputStream objOut = new ObjectOutputStream(fOut);

	// Write objects to file
	objOut.writeObject(f);

	objOut.close();
	fOut.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//public static void readFilterFile(File f){
//	FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
//	ObjectInputStream oi = new ObjectInputStream(fi);
//
//	// Read objects
//	Person pr1 = (Person) oi.readObject();
//	Person pr2 = (Person) oi.readObject();
//
//	System.out.println(pr1.toString());
//	System.out.println(pr2.toString());
//
//	oi.close();
//	fi.close();
//}

}
