package GUI_Filter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Algorithms.Algorithms;
import KML.ConvertCSVToKML;
import KML.LinesToSamples;
import MergedCSV.FileFormat;
import MergedCSV.MergeCSVfiles;

public class Wraper {

	public static void folderAdded( String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		 DataBase.addData(mg.getSamplesFromFiles());
		 JOptionPane.showMessageDialog(new JFrame(), "Folder Added Succesfully!");
	}

	public static void mergedFileAdded(String filePath) throws IOException {
		FileFormat fm = new FileFormat();
		LinesToSamples ls = new LinesToSamples();
		File f = new File(filePath);
		if (fm.checkMergedCSVFormat(f))
			 DataBase.addData(ls.convertLines(ls.readCSV(filePath)));
		JOptionPane.showMessageDialog(new JFrame(), "File Added Succesfully !");
	}

	public static void saveMergedCSV() {
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(DataBase.dataBase);
		JOptionPane.showMessageDialog(new JFrame(), "File Saved To Desktop!");

	}

	public static void saveAsKML() throws FileNotFoundException, MalformedURLException {
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(DataBase.dataBase);
		JOptionPane.showMessageDialog(new JFrame(), "File Saved To Desktop!");
	}

	public static void clearance() {
		System.out.println("Samples amount before delete: " +  DataBase.dataBase.size());
		DataBase.deleteAllData();
		System.out.println("Samples amount after delete: " +DataBase.dataBase.size());
	}

public static void writeCurrentFilter(Filter f) throws IOException{
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	FileOutputStream fOut;
	ObjectOutputStream objOut;
	try {
		fOut = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\Filter - "+timeStamp+".txt"));
		objOut = new ObjectOutputStream(fOut);

	// Write objects to file
	objOut.writeObject(f);

	objOut.close();
	fOut.close();
	JOptionPane.showMessageDialog(new JFrame(), "Filter Saved To Desktop!");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * This function gets a file path to a File that has a filter object written in bytes.
 * It reads from the file and retrieves the Filter.
 * @param filterFilePath a given file path.
 * @return the Filter object from given file.
 * @throws IOException
 * @throws ClassNotFoundException
 */
public static Filter readFilterFile(String filterFilePath) throws IOException, ClassNotFoundException{
	
//	 FileInputStream fis = new FileInputStream(filterFilePath);
//     ObjectInputStream ois = new ObjectInputStream(fis);//sends to EOFexception
	
//	ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//	ObjectInputStream ois = new ObjectInputStream(bais);
	
	InputStream fis = new FileInputStream(filterFilePath);
	ObjectInputStream ois = new ObjectInputStream(fis);

     Filter filter = (Filter) ois.readObject();
     System.out.println(filter.toString().toString()); //doesnt reach here
	ois.close();
	fis.close();
	return filter;


}
public static void createAlgo1Map(){
	Algorithms a = new Algorithms();
	a.strongestMacLocation(DataBase.dataBase, 4);
}

}
