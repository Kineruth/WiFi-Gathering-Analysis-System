

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
 * Takes Wifi networks and arranges them by same time and place for every line in the new CSV file.
 * Up to 10 networks for every sample (=time and place).
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class MergeCSVfiles {

	private String dir, dirName;
	private ArrayList<File> files;
	private boolean count = false;
/**
 * Parameterized constructor.
 * @param dir is an input from user for a directory's path.
 */
	public MergeCSVfiles(String dir) {
		this.dir = dir;
		this.dirName = null;
		this.files = new ArrayList<File>();
	}
/**
 * This function gets all the files from a given directory and sends them 
 */
	public void sortDirFiles() {
	try{
		File directory = new File(this.dir);
		this.dirName = directory.getName();

		if (directory.isDirectory()) {
			listf(this.dir, this.files);
			for (int i = 0; i < this.files.size(); i++)
				readFile(this.files.get(i).getPath());
			System.out.println("Done Creating CSV file!");
		}
	}catch (Exception e) {
		System.out.println("Invalid input! Check path/files");
	}

	}

	// ***************************PRIVATE*****************************

	// https://stackoverflow.com/questions/14676407/list-all-files-in-the-folder-and-also-sub-folders
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

	private void readFile(String filePath) {
		try {
			String str, device;
			String[] line;
			WifiNetwork network;
			CommonNets common = new CommonNets();
			UnitedNets united = new UnitedNets();
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			str = br.readLine();
			line = str.split(",");
			device = line[2].split("=")[1];
			str = br.readLine();

			while ((str = br.readLine()) != null) {
				str = device + "," + str;
				line = str.split(",");
				if (line[11].equals("WIFI")) {
					network = new WifiNetwork(line);
					common.add(network);
				}
			}
			united = common.sortListNet();
			br.close();
			fr.close();
			writeFile(united);

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	// https://stackoverflow.com/questions/5797208/java-how-do-i-write-a-file-to-a-specified-directory
	
	private void writeFile(UnitedNets n) {
		try {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			FileWriter fw = new FileWriter((this.dir + "-"+timeStamp+".csv"), true);  //file name +timeStamp & path as the directory
			PrintWriter outs = new PrintWriter(fw);
			String info;
			if (this.count == false) {
				this.count = true;
				String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
				for (int i = 1; i < 11; i++) {
					line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
				}
				outs.println(line);

			}
			if (n != null) {
				for (int i = 0; i < n.size(); i++) {
					info = n.get(i).get(0).printCommonProp() + "," + n.get(i).size();
					for (int j = 0; j < n.get(i).size(); j++) {
						info += "," + n.get(i).get(j).toString();
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

	private String getCommonInfo(UnitedNets united, int i) {
		return united.get(i).get(0).getTime() + "," + united.get(i).get(0).getID() + "," + united.get(i).get(0).getLAT()
				+ "," + united.get(i).get(0).getLON() + "," + united.get(i).get(0).getALT() + ","
				+ united.get(i).size();
	}

}