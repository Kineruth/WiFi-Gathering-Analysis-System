package src;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.management.ListenerNotFoundException;

/**
 * This class takes CSV files from a given directory and creates a new CSV file.
 * Takes WiFi networks and arranges them by same time and place for every line
 * in the new CSV file. Up to 10 networks for every sample (=time and place).
 * 
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class MergeCSVfiles {

	private String directoryPath, directoryName, newFileName;
	private ArrayList<File> files;
	private boolean headerCreated = false;

	/**
	 * Parameterized constructor.
	 * 
	 * @param dir
	 *            is an input from user for a directory's path.
	 */
	public MergeCSVfiles(String directory) {
		this.directoryPath = directory;
		this.directoryName = null;
		this.files = new ArrayList<File>();
		this.newFileName=null;
	}

	/**
	 * This function gets all the files from a given directory and sends them
	 * @exception Exception e for any failure .
	 */
	public void sortDirFiles() {
		try {
			File directory = new File(this.directoryPath);
			this.directoryName = directory.getName();

			if (directory.isDirectory()) {
				listf(this.directoryPath, this.files);
				for (int i = 0; i < this.files.size(); i++)
					readFile(this.files.get(i).getPath());
				System.out.println("Done Creating CSV file!");
			}
//			ConvertCSVToKML kmlFile =	new ConvertCSVToKML(this.directoryPath, this.newFileName);
//			kmlFile.createFile();
		} catch (Exception e) {
			System.out.println("Invalid input! Check path/files");
		}

	}

	// ***************************PRIVATE*****************************
/**
 * This function adds all the CSV files from a given directory recursively to an ArrayList of files.
 * Taken from https://stackoverflow.com/questions/14676407/list-all-files-in-the-folder-and-also-sub-folders
 * @param dirPath a given directory's path.
 * @param files a given ArrayList of files.
 */
	private void listf(String dirPath, ArrayList<File> files) {
		File directory = new File(dirPath);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile() && file.getName().endsWith(".csv"))
				files.add(file);
			else if (file.isDirectory())
				listf(file.getAbsolutePath(), files);
		}
	}

	/**
	 * This function reads a given file, takes all the wanted WiFi networks, arranges them to be written in a new file.
	 * @param filePath a given file's path.
	 * @exception IOException if the file cannot be read from.
	 */
	private void readFile(String filePath) {
		try {
			String str, device;
			String[] line;
			WiFiNetwork network;
			Sample sample = new Sample();
			UnitedSamples unitedSamples = new UnitedSamples();
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			str = br.readLine();
			line = str.split(",");
			device = line[2].split("=")[1];
			str = br.readLine();
			str = br.readLine();
			line = str.split(",");
			
			if (line[10].equals("WIFI")) {
				sample = new Sample(device, line[3], line[6], line[7], line[8]);
				network = new WiFiNetwork(line[1], line[0], line[4], line[5]);
				sample.addNetwork(network);
			} 
				while ((str = br.readLine()) != null) { // while line not empty
					line = str.split(",");
					if (line[10].equals("WIFI")) {
						network = new WiFiNetwork(line[1], line[0], line[4], line[5]);
						// add network to sample
						if (sample.checkToAddToSample(line[3], line[6], line[7], line[8])) {
							sample.addNetwork(network);
						} else {
							unitedSamples.add(sample);
							sample = new Sample(device, line[3], line[6], line[7], line[8]);
							sample.addNetwork(network);
						}
					}
				}
			

			br.close();
			fr.close();
			writeFile(unitedSamples);

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	// https://stackoverflow.com/questions/5797208/java-how-do-i-write-a-file-to-a-specified-directory
/**
 * This function writes a new file with the wanted information.
 * Takes all the sorted and arranged WiFiNetworks and writes them in a specific format.
 * Every line in the new file represents a sample of WiFiNetworks up till 10 networks arranges by signals.
 * @param unitedSamples
 */
	private void writeFile(UnitedSamples unitedSamples) {
		try {
			// Gets the timeStamp
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			// file name+timeStamp&path as the directory
			this.newFileName=this.directoryPath + " - " + timeStamp + ".csv";
			FileWriter fw = new FileWriter(this.newFileName, true);
			PrintWriter outs = new PrintWriter(fw);
			String info;
			// adds the title only once
			if (this.headerCreated == false) {
				this.headerCreated = true;
				String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
				for (int i = 1; i < 11; i++) {
					line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
				}
				outs.println(line);

			}
			if (unitedSamples != null) {
				for (int i = 0; i < unitedSamples.size(); i++) {
					info = unitedSamples.get(i).printSampleInfo();
					// Runs over all the networks in the sample and prints their
					// info.
					for (int j = 0; j < unitedSamples.get(i).getCommonNetworks().size(); j++) {
						info += unitedSamples.get(i).getCommonNetworks().get(j).toString();
					}
					outs.println(info);
					info = null;
				}
			}
			outs.close();
			fw.close();

		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}

}
