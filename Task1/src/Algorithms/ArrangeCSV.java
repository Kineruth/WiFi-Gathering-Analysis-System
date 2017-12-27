package Algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import KML.LinesToSamples;
import MergedCSV.Sample;
/**
 * This class exports CSV files with 2 options: one that has all the Macs' strongest location,
 *  the other with the user location when every sample was scanned.
 *  The program gets a merged CSV file in the format created in class MergeCSVFiles.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class ArrangeCSV {

	private String filePath, fileName, fileOutput;
	private File file1, file2;
	private int num;
/**
 * Default Constructor
 */
	public ArrangeCSV() {
		this.filePath = null;
		this.file1 = null;
		this.file2 = null;
		this.fileOutput = null;
		this.num = 0;
	}
/**
 * Algorithm 1 - finds the strongest location of every Mac from the CSV file.
 * It sends the file to be read from.
 * @param filePath a given path to a merged CSV file.
 * @param num a given number for the amount of strongest samples to take after sorting them.
 */
	public void macStrongestLocation(String filePath, int num) {
		if (num > 0)
			this.num = num;
		this.filePath = filePath + "";
		this.file1 = new File(this.filePath);
		this.fileName = this.filePath.replaceFirst(".csv", (" - Algo1 Output.csv"));
		checkCSVFile(1);
	}
/**
 * Algorithm 2 - finds the user location for every sample scanned in the output file. 
 * This file does'n contain any coordinates, only samples with all the WiFiNetworks that were scanned.
 * It sends the files to be read from.
 * @param filePath a given path to a merged CSV file.
 * @param fileOutput a given path to a merged CSV file that doesn't contain coordinates.
 * @param num a given number for the amount of strongest samples to take after sorting them.
 */
	public void userLocation(String filePath, String fileOutput, int num) {
		if (num > 0)
			this.num = num;
		this.filePath = filePath + "";
		this.fileOutput = fileOutput + "";
		this.file1 = new File(this.filePath);
		this.file2 = new File(this.fileOutput);
		checkCSVFile(2);
	}

	
	// ***************************PRIVATE*****************************
	/**
	 * Checks if the file is in the correct format and sends it to be read from,
	 * according the chosen algorithm and converted into samples.
	 * 
	 * @param algorithm the chosen algorithm.
	 */
	private void checkCSVFile(int algorithm) { 
		LinesToSamples ls = new LinesToSamples();
		Algorithms algo = new Algorithms();
		try {
			// Get only CSV files
			if(algorithm == 1){
//			if (algorithm == 1 && this.file1.isFile() && this.file1.getName().endsWith(".csv")) {
				/* read from file and convert lines to Samples */
				System.out.println(this.filePath);
				List<Sample> samples = ls.convertLines(ls.readCSV(this.filePath));
				writeAlgo1File(algo.strongestMacLocation(samples, this.num));
			}
			if((algorithm == 2)){
//			if (algorithm == 2 && this.file1.isFile() && this.file1.getName().endsWith(".csv") && this.file2.isFile()
//					&& this.file2.getName().endsWith(".csv")) {
				this.fileName = this.fileOutput.replaceFirst(".csv", (" - Algo2 Output.csv"));
				/* read from file and convert lines to Samples */
				List<Sample> s1 = ls.convertLines(ls.readCSV(this.filePath));
				List<Sample> s2 = ls.convertLines(ls.readCSV(this.fileOutput));
				List<Sample> arrangedSamples = algo.userLocation(s1, s2, this.num);

				writeAlgo2File(arrangedSamples);
			}
			System.out.println("Done writing CSV file!");
		} catch (Exception e) {
			System.out.println("Error running the program!");
//			e.printStackTrace();
//			System.out.println(e);
		}
	}
/**
 * Writes a CSV file for algorithm 1.
 * @param s a given list of string containing the correct lines to be printed in the file.
 */
	private void writeAlgo1File(List<String> s) { // need to be finished.
		try {
			FileWriter fw = new FileWriter(this.fileName);
			PrintWriter outs = new PrintWriter(fw);
			int counter = 1;
			String header = "Number,MAC,SSID,Frequency,Signal,LAT,LON,ALT,Time,Aprrox. w-center Algo1";
			outs.println(header);
			if (s != null) {
				for (String l : s) {
					outs.println(counter + "," + l);
					counter++;
				}
			}
			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}
/**
 * Writes a CSV file for algorithm 2.
 * @param s a given list of samples to be written in the file.
 */
	private void writeAlgo2File(List<Sample> s) { 
		try {
			FileWriter fw = new FileWriter(this.fileName);
			PrintWriter outs = new PrintWriter(fw);
			String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
			for (int i = 1; i < 11; i++) {
				line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
			}
			outs.println(line);
			if (s != null) {
				for (int i = 0; i < s.size(); i++) {
					line = s.get(i).printSampleInfo();
					// Runs over all networks in the sample and prints their
					// info.
					for (int j = 0; j < s.get(i).getCommonNetworks().size(); j++) {
						line += s.get(i).getCommonNetworks().get(j).toString();
					}
					outs.println(line);
					line = null;
				}
			}
			outs.close();
			fw.close();
		}
		
		catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}

	}
}
