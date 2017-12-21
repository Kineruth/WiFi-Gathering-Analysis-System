package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
 * This class converts a given CSV file into a KML file that can be uploaded on
 * google earth website: https://earth.google.com/web/
 * 
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class ConvertCSVToKML {
	private File file;
	private String filePath;

	/**
	 * Parameterized constructor.
	 * 
	 * @param filePath a given file's path.
	 */
	public ConvertCSVToKML(String filePath) {
		this.filePath = filePath;
		this.file = new File(this.filePath);
	}

	/**
	 * This function takes only a CSV file and creates a KML Google earth file from it.
	 */
	public void createFile() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		try {
			// Get only CSV files
			if (this.file.isFile() && this.file.getName().endsWith(".csv")) {
				// Add to file's name timeStamp & change file type to KML
				this.filePath = this.filePath.replaceFirst(".csv", (" - " + timeStamp + ".kml"));
				this.filePath = this.filePath.replaceFirst(".csv", (".kml"));
				readFile();
				System.out.println("Done writing KML file!");
			}
		} catch (Exception e) {
			System.out.println("Error running the program!");
		}
	}

	/**
	 * This function reads from the given CSV file.
	 * @throws MalformedURLException 
	 * @throws FileNotFoundException 

	 */
	private void readFile() throws FileNotFoundException, MalformedURLException {

		LinesToSamples ls = new LinesToSamples();
		Filter f = new Filter();
		List<String[]> linesUnited = ls.readCSV(this.file.getPath());

		f.filterFile(linesUnited);
		writeFile(ls.convertLines(linesUnited));

	}

	// ***************************PRIVATE*****************************

	/**
	 * This class writes the KML file, it sorts by the user's chosen filter than writes the file in a KML format.
	 * @param s a given list of samples.
	 * @throws FileNotFoundException  if it fails finding the file.
	 * @throws MalformedURLException  if it fails writing the KML file.
	 */
	private void writeFile(List<Sample> s) throws FileNotFoundException, MalformedURLException {
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		int counter =0; //counting points on map.
		for (int i = 0; i < s.size(); i++) {
			for (int j = 0; j < s.get(i).getNetworksAmount(); j++) {
				TimeStamp timeStamp = new TimeStamp();
				timeStamp.setWhen(s.get(i).getTimeInKML());
				doc.createAndAddPlacemark().withName(s.get(i).getID()).withOpen(Boolean.TRUE).withTimePrimitive(timeStamp)
						.withDescription(KMLDescription(s.get(i).getCommonNetworks().get(j))).createAndSetPoint()
						.addToCoordinates(Double.parseDouble(s.get(i).getLON()), Double.parseDouble(s.get(i).getLAT()));
				counter++;
			}
		}
		System.out.println("count points: "+counter);
		kml.marshal(new File(this.filePath));

	}
	
	/**
	 * This function returns a WiFiNetwork's description for the KML file.
	 * 
	 * @param wn a given WiFiNetwork.
	 * @return the KML description.
	 */
	private String KMLDescription(WiFiNetwork wn) {
		return "SSID: " + wn.getSSID() + "\nMac: " + wn.getMAC() + "\nFrequency: " + wn.getFrecuency() + "\nSignal: "
				+ wn.getSignal();
	}

}
