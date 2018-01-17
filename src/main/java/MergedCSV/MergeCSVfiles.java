package MergedCSV;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.List;

import javax.management.ListenerNotFoundException;

/**
 * Date: 23-11-2017
 * This class takes CSV files from a given directory and creates a new CSV file.
 * Takes WiFi networks and arranges them by same time and place for every line
 * in the new CSV file. Up to 10 networks for every sample (=time and place).
 * 
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class MergeCSVfiles {

	private ArrayList<File> files;
	private String directoryPath, newFileName;
	private boolean headerCreated = false;
//	private List<Sample> samples = new ArrayList<Sample>();
	private FileFormat fm = new FileFormat();

	/**
	 * Default constructor.
	 */
	public MergeCSVfiles(){
		this.directoryPath = null;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		this.newFileName = "C:\\Users\\admin\\Desktop\\New_Merged_File - "+timeStamp+".csv";
		this.files = new ArrayList<File>();
	}
	/**
	 * Parameterized constructor.
	 * 
	 * @param dir is an input from user for a directory's path.
	 */
	public MergeCSVfiles(String directory) {
		this.directoryPath = directory;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		this.newFileName=this.directoryPath + " -  New_Merged_File -"+timeStamp+".csv";
		this.files = new ArrayList<File>();
	}

	/**
	 * This function gets all the files from a given directory and sends them to be read from..
	 */
	public void sortDirFiles() {
		
		writeFile(getSamplesFromFiles());
		System.out.println("Done Creating CSV file!");
	}
	
	/**
	 * This function sends all the files from a given directory to be converted to samples and added to this class samples list.
	 */
	public List<Sample> getSamplesFromFiles(){
		File directory = new File(this.directoryPath);
		List<Sample> samples = new ArrayList<Sample>();
		try {
			if (directory.isDirectory()) {
				listFiles(this.directoryPath);
				for (int i = 0; i < this.files.size(); i++)
					samples.addAll(readFile(this.files.get(i).getPath()));		
			}
			
		} catch (Exception e) {
			System.out.println("Error reading files!");
		}
			return samples;
	}

	
	// ***************************PRIVATE*****************************
/**
 * This function adds all the CSV files from a given directory recursively to this class ArrayList of files.
 * Taken from https://stackoverflow.com/questions/14676407/list-all-files-in-the-folder-and-also-sub-folders
 * @param dirPath a given directory's path.
 * @throws FileNotFoundException 
 */
	private void listFiles(String dirPath) throws FileNotFoundException {
		File directory = new File(dirPath);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			//check if current file is in right Wigle format
			if(this.fm.checkWigleFormat(file)==true)
				this.files.add(file);
			else if (file.isDirectory())
				listFiles(file.getAbsolutePath());
			else 
				System.out.println("File not in the right format!");
		}
	}
	


	/**
	 * This function reads a given file, takes all the wanted WiFi networks, arranges them to be written in a new file.
	 * @param filePath a given file's path.
	 * @throws FileNotFoundException 
	 */
	private List<Sample> readFile(String filePath) throws FileNotFoundException {
		
		String str, device;
		String[] line;
		WiFiNetwork network;
		Sample sample = new Sample();
		List<Sample> samplesList = new ArrayList<Sample>();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		
		try {
			str = br.readLine();
			line = str.split(",");
			//Get device model
			device = line[2].split("=")[1];
			str = br.readLine();
			str = br.readLine();
			line = str.split(",");
			//get the first sample to compare the times with other networks
			sample = new Sample(device, line[3], line[6], line[7], line[8],0);
			//Check if it's WiFi network
			if (line[10].equals("WIFI")) {
				network = new WiFiNetwork(line[1], line[0], line[4], line[5]);
				sample.addNetwork(network); // add network to sample
			} 
				while ((str = br.readLine()) != null) { // while line not empty
					line = str.split(",");
					if (line[10].equals("WIFI")) {
						network = new WiFiNetwork(line[1], line[0], line[4], line[5]);
						// add network to sample
						if (sample.checkToAddToSample(line[3], line[6], line[7], line[8])) {
							sample.addNetwork(network);
						} else {
							samplesList.add(sample);
							sample = new Sample(device, line[3], line[6], line[7], line[8],0);
							sample.addNetwork(network);
						}
					}
				}
			

			br.close();
			fr.close();
//			writeFile(unitedSamples);

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
		return samplesList;
	}

	// https://stackoverflow.com/questions/5797208/java-how-do-i-write-a-file-to-a-specified-directory
/**
 * This function writes a new file with the wanted information.
 * Takes all the sorted and arranged WiFiNetworks and writes them in a specific format.
 * Every line in the new file represents a sample of WiFiNetworks up till 10 networks arranges by signals.
 * @param list an arrayList of Samples.
 * @exception throws IOException if fails writing to the file.
 */
	public void writeFile(List<Sample> list) {
		try {
			// Gets the timeStamp
			// file name+timeStamp&path as the directory				
			FileWriter fw = new FileWriter(this.newFileName, true);
			PrintWriter outs = new PrintWriter(fw);
			String info;
			// adds the title only once to the file
			if (this.headerCreated == false) {
				this.headerCreated = true;
				String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
				for (int i = 1; i < 11; i++) {
					line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
				}
				outs.println(line);

			}
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					info = list.get(i).printSampleInfo();
					// Runs over all networks in the sample and prints their info.
					for (int j = 0; j < list.get(i).getCommonNetworks().size(); j++) {
						info += list.get(i).getCommonNetworks().get(j).toString();
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
