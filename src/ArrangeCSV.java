package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArrangeCSV {

	private String filePath, fileName, fileOutput;
	private File file1, file2;
	private int num;

	public ArrangeCSV() {
		// this.filePath=filePath+"";
		// this.file = new File(this.filePath);
		// this.num=Integer.parseInt(num);
		this.filePath = null;
		this.file1 = null;
		this.file2 = null;
		this.fileOutput = null;
	}

	public void macStrongestLocation(String filePath, int num) {
		if (num > 0)
			this.num = num;
		else
			System.out.println("Invalid number!"); // then what?
		this.filePath = filePath + "";
		this.file1 = new File(this.filePath);
		checkCSVFile(1);
	}

	public void userLocation(String filePath, String fileOutput, int num) {
		if (num > 0)
			this.num = num;
		else
			System.out.println("Invalid number!"); // then what?
		this.filePath = filePath + "";
		this.fileOutput = fileOutput + "";
		this.file1 = new File(this.filePath);
		this.file2 = new File(this.fileOutput);
		checkCSVFile(1);
	}

	private void checkCSVFile(int algorithm) { // need to be checked
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		LinesToSamples ls = new LinesToSamples();
		Algorithms algo = new Algorithms();
		try {
			// Get only CSV files
			// Add to file's name a timeStamp
			if (algorithm == 1 && this.file1.isFile() && this.file1.getName().endsWith(".csv")) {
				this.fileName = this.filePath.replaceFirst(".csv", (" - " + timeStamp + ".csv"));
				/* read from file and convert lines to Samples */
				SamplesList samples = ls.convertLines(ls.readCSV(this.filePath));
				writeAlgo1File(algo.strongestMacLocation(samples, this.num));
			}
			if (algorithm == 2 && this.file1.isFile() && this.file1.getName().endsWith(".csv") && this.file2.isFile()
					&& this.file2.getName().endsWith(".csv")) {
				this.fileName = this.fileOutput.replaceFirst(".csv", (" - " + timeStamp + ".csv"));
				/* read from file and convert lines to Samples */
				SamplesList s1 = ls.convertLines(ls.readCSV(this.filePath));
				SamplesList s2 = ls.convertLines(ls.readCSV(this.fileOutput));
				SamplesList arrangedSamples = algo.userLocation(s1, s2, this.num);

				writeAlgo2File(arrangedSamples);
			}
			System.out.println("Done writing CSV file!");
		} catch (Exception e) {
			System.out.println("Error running the program!");
		}
	}

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

	private void writeAlgo2File(SamplesList s) { // need to be finished.
		try {
			FileWriter fw = new FileWriter(this.fileName);
			@SuppressWarnings("resource")
			PrintWriter outs = new PrintWriter(fw);
			String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
			for (int i = 1; i < 11; i++) {
				line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
			}
			outs.println(line);
			if (s != null) {
				for (int i = 0; i < s.listSize(); i++) {
					line = s.getSample(i).printSampleInfo();
					// Runs over all networks in the sample and prints their
					// info.
					for (int j = 0; j < s.getSample(i).getCommonNetworks().size(); j++) {
						line += s.getSample(i).getCommonNetworks().get(j).toString();
					}
					outs.println(line);
					line = null;
				}
			}
		}

		catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}

	}
}
