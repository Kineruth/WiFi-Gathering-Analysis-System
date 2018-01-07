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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Algorithms.Algorithms;
import KML.ConvertCSVToKML;
import KML.LinesToSamples;
import MergedCSV.FileFormat;
import MergedCSV.MergeCSVfiles;
import MergedCSV.Sample;
import MergedCSV.WiFiNetwork;

public class Wraper {
	/**
	 * Addes new samples from files' folder.
	 * 
	 * @param folderPath
	 *            a given folder path.
	 */
	public static void folderAdded(String folderPath) {
		MergeCSVfiles mg = new MergeCSVfiles(folderPath);
		DataBase.addData(mg.getSamplesFromFiles());
		DataBase.addFolderPath(folderPath);
		JOptionPane.showMessageDialog(new JFrame(), "Folder Added Succesfully!");
	}

	/**
	 * Addes new samples from a given file.
	 * 
	 * @param filePath
	 *            a given file path.
	 * @throws IOException
	 */
	public static void mergedFileAdded(String filePath) throws IOException {
		FileFormat fm = new FileFormat();
		LinesToSamples ls = new LinesToSamples();
		File f = new File(filePath);
		if (fm.checkMergedCSVFormat(f))
			DataBase.addData(ls.convertLines(ls.readCSV(filePath)));
		JOptionPane.showMessageDialog(new JFrame(), "File Added Succesfully !");
	}

	/**
	 * Sends samples to be written to merged CSV.
	 */
	public static void saveMergedCSV() {
		MergeCSVfiles mg = new MergeCSVfiles();
		mg.writeFile(DataBase.dataBase);
		JOptionPane.showMessageDialog(new JFrame(), "File Saved To Desktop!");

	}

	/**
	 * Sends samples to be written to KML file.
	 * 
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	public static void saveAsKML() throws FileNotFoundException, MalformedURLException {
		ConvertCSVToKML kml = new ConvertCSVToKML();
		kml.writeFile(DataBase.dataBase);
		JOptionPane.showMessageDialog(new JFrame(), "File Saved To Desktop!");
	}

	/**
	 * Clears all dataBase.
	 */
	public static void clearance() {
		System.out.println("Samples amount before delete: " + DataBase.dataBase.size());
		DataBase.deleteAllData();
		System.out.println("Samples amount after delete: " + DataBase.dataBase.size());
	}

	/**
	 * Gets mac number.
	 * 
	 * @return
	 */
	public static int getMacNumber() {
		Algorithms a = new Algorithms();
		List<Sample> temp = new ArrayList<Sample>(DataBase.dataBase);
		return a.strongestMacLocation(temp, 4).size();
	}

	/**
	 * Writes a current Filter into a file with bytes.
	 * 
	 * @param f
	 *            a given filter.
	 * @throws IOException
	 */
	public static void writeCurrentFilter(Filter f) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		FileOutputStream fOut = null;
		ObjectOutputStream objOut;
		try {
			fOut = new FileOutputStream("C:\\Users\\admin\\Desktop\\Filter - " + timeStamp + ".txt");
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
	 * This function gets a file path to a File that has a filter object written
	 * in bytes. It reads from the file and retrieves the Filter.
	 * 
	 * @param filterFilePath
	 *            a given file path.
	 * @return the Filter object from given file.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Filter readFilterFile(String filterFilePath) throws IOException, ClassNotFoundException {

		Filter filter;
		FileInputStream fis = new FileInputStream(filterFilePath);
		ObjectInputStream ois = new ObjectInputStream(fis);

		filter = (Filter) ois.readObject();
		ois.close();
		fis.close();
		JOptionPane.showMessageDialog(new JFrame(), "Filter Uploaded Succesfully!");
		return filter;

	}

	public static void createAlgo1(String mac) {
		List<Sample> temp = new ArrayList<Sample>(DataBase.dataBase);
		Algorithms a = new Algorithms();
		List<Sample> s = a.strongestMacLocation(temp, 4);
		for (Sample smp : s) {
			if (smp.getCommonNetworks().get(0).getMAC().equals(mac)) {
				JOptionPane.showMessageDialog(new JFrame(),
						"LAT: " + smp.getLAT() + ", LON: " + smp.getLON() + ", ALT" + smp.getALT());
				break;
			}
		}

	}

	public static void createAlgo2(Sample s) {
		Algorithms a = new Algorithms();
		List<Sample> smpl = new ArrayList<Sample>();
		List<Sample> temp = new ArrayList<Sample>(DataBase.dataBase);
		smpl.add(s);
		Sample sample = new Sample(a.userLocation(temp, smpl, 4).get(0));
		JOptionPane.showMessageDialog(new JFrame(),
				"LAT: " + sample.getLAT() + ", LON: " + sample.getLON() + ", ALT" + sample.getALT());

	}

	/**
	 * Converts line to sample for algo 1.
	 * 
	 * @param text
	 * @return
	 */
	public static Sample convertToSample(String text) {
		Sample s = new Sample();
		String[] str = text.split(",");
		s.setTime(str[0]);
		s.setID(str[1]);
		s.setLAT(str[2]);
		s.setLON(str[3]);
		s.setALT(str[4]);
		for (int i = 6; i < str.length; i = i + 4) {
			WiFiNetwork wn = new WiFiNetwork(str[i], str[i + 1], str[i + 2], str[i + 3]);
			s.addNetwork(wn);
		}
		return s;
	}

	/**
	 * Converts given macs and signals to a sample.
	 * 
	 * @param s1
	 *            given mac 1.
	 * @param s2
	 *            given mac 2.
	 * @param s3
	 *            given mac 3.
	 * @param i1
	 *            given signal 1.
	 * @param i2
	 *            given signal 2.
	 * @param i3
	 *            given signal 3.
	 * @return
	 */
	public static Sample convertMacsToSample(String s1, String s2, String s3, String i1, String i2, String i3) {
		Sample s = new Sample();
		if (!s1.isEmpty()) {
			WiFiNetwork wn = new WiFiNetwork();
			wn.setMAC(s1);
			wn.setSignal(i1);
			s.addNetwork(wn);
		}
		if (!s2.isEmpty()) {
			WiFiNetwork wn = new WiFiNetwork();
			wn.setMAC(s2);
			wn.setSignal(i2);
			s.addNetwork(wn);
		}
		if (!s3.isEmpty()) {
			WiFiNetwork wn = new WiFiNetwork();
			wn.setMAC(s3);
			wn.setSignal(i3);
			s.addNetwork(wn);
		}
		return s;

	}
/**
 * Returns true if the dates the user chose are really max and min.
 * @param max a given max date.
 * @param min a given min date.
 * @return true/false,
 */
	public static boolean checkDateMinMax(Date max, Date min) {
		return (max.after(min) && max.getTime() >= min.getTime()) || max.equals(min);
	}
}
