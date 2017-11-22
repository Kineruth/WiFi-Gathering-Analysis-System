package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

public class ConvertCSVToKML {
	private File file;
	private String filePath, fileName;

	public ConvertCSVToKML(String filePath) {
		this.filePath = filePath;
		this.file = new File(this.filePath);
	}

	public void createFile() {
		// String timeStamp = new
		// SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		try {
			// Get only CSV files
			if (this.file.isFile() && this.file.getName().endsWith(".csv")) {
				// Add to file's name timeStamp & change file type to KML
				// this.filePath = this.filePath.replaceFirst(".csv", (" - " +
				// timeStamp + ".kml"));
				this.filePath = this.filePath.replaceFirst(".csv", (".kml"));
				readFile();
			}
		} catch (Exception e) {
			System.out.println("Invalid input! Not a file's path");
		}
	}

	private void readFile() {
		try {

			FileReader fr = new FileReader(this.file.getPath());
			BufferedReader br = new BufferedReader(fr);
			String[] line;
			List<String[]> linesUnited = new ArrayList<String[]>();
			String str = br.readLine();
			Filter f = new Filter();

			str = br.readLine();
			while (str != null) {
				line = str.split(",");
				linesUnited.add(line);
				str = br.readLine();
			}

			f.filterFile(linesUnited);

			br.close();
			fr.close();

			writeFile(linesUnited);

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);

		}

	}

	// ***************************PRIVATE*****************************

	private void writeFile(List<String[]> linesUnited) {

		try {
			List<Sample> samples = getSamplesList(linesUnited);
			Kml kml = new Kml();
			Document doc = kml.createAndSetDocument();
			TimeStamp timeStamp= new TimeStamp();
			/*for (int i = 0; i < linesUnited.size(); i++) {
				doc.createAndAddPlacemark().withName(linesUnited.get(i)[0])
						.withDescription("TimeStamp:" + linesUnited.get(i)[0] + "\nScanned with:"
								+ linesUnited.get(i)[1] + "\nAmount of WiFi networks :" + linesUnited.get(i)[5])
						.createAndSetPoint().addToCoordinates(Double.parseDouble(linesUnited.get(i)[3]),
								Double.parseDouble(linesUnited.get(i)[2]));
			}*/
			
			for (Sample sample : samples) {
				for (int j = 0; j < sample.getNetworksAmount(); j++) {
					timeStamp=sample.
					
				}
				doc.createAndAddPlacemark().withName(linesUnited.get(i)[0])
						.withDescription("TimeStamp:" + linesUnited.get(i)[0] + "\nScanned with:"
								+ linesUnited.get(i)[1] + "\nAmount of WiFi networks :" + linesUnited.get(i)[5])
						.createAndSetPoint().addToCoordinates(Double.parseDouble(linesUnited.get(i)[3]),
								Double.parseDouble(linesUnited.get(i)[2]));
			}
			kml.marshal(new File(this.filePath));
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}

	}

	private List<Sample> getSamplesList(List<String[]> linesUnited) {
		List<Sample> samples = null;
		for (int i = 0; i < linesUnited.size(); i++) {
			String[] line = linesUnited.get(i);
			Sample sample = new Sample(line[1], line[0], line[2], line[3], line[4]);
			for (int j = 6; j < line.length - 3; j = j + 4) {
				WiFiNetwork network = new WiFiNetwork(line[j], line[j + 1], line[j + 2], line[j + 3]);
				sample.addNetwork(network);
			}
			samples.add(sample);
		}
		return samples;
	}

	private String getColor(int signal) {
		if (signal >= -70)
			return "#green";
		else if (signal >= -90 && signal < -70)
			return "#yellow";
		return "#red";
	}

}
