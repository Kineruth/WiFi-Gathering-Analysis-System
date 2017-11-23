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
	private String filePath, fileName;

	/**
	 * Parameterized constructor.
	 * 
	 * @param filePath
	 *            a given file's path.
	 */
	public ConvertCSVToKML(String filePath) {
		this.filePath = filePath;
		this.file = new File(this.filePath);
	}

	/**
	 * This function takes only a CSV file and creates a KML file for it.
	 * 
	 * @exception Exception
	 *                e if the program fails running.
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
	 * This function reads from the given file.
	 * 
	 * @exception IOException
	 *                ex if it fails reading from file.
	 */
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

			writeFile(getSamplesList(linesUnited));

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);

		}

	}

	// ***************************PRIVATE*****************************
	/**
	 * This class writes the KML file, it sorts by the user's chosen filter than
	 * writes the file in a KML format.
	 * 
	 * @param list
	 *            a given lists of lines = each line represents a sample.
	 * @throws FileNotFoundException
	 *             if it fails finding the file.
	 * @throws MalformedURLException
	 *             if it fails writing the KML file.
	 */
	private void writeFile(List<Sample> samples) throws FileNotFoundException, MalformedURLException {
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		TimeStamp timeStamp = new TimeStamp();
		for (Sample sample : samples) {
			for (int i = 0; i < sample.getNetworksAmount(); i++) {
				timeStamp.setWhen(sample.getTimeInKML());
				doc.createAndAddPlacemark().withName(sample.getID()).withOpen(Boolean.TRUE).withTimePrimitive(timeStamp)
						.withDescription(KMLDescription(sample.getCommonNetworks().get(i))).createAndSetPoint()
						.addToCoordinates(Double.parseDouble(sample.getLON()), Double.parseDouble(sample.getLAT()));
			}
		}

		/*
		 * List<Sample> samples = getSamplesList(linesUnited); Kml kml = new
		 * Kml(); Document doc = kml.createAndSetDocument();
		 * 
		 * for (Sample sample : samples) { for (int i = 0; i <
		 * sample.getNetworksAmount(); i++) { TimeStamp timeStamp = new
		 * TimeStamp(); timeStamp.setWhen(sample.getTimeInKML());
		 * doc.createAndAddPlacemark().withName(sample.getID()).withOpen(Boolean
		 * .TRUE) .withTimePrimitive(timeStamp)
		 * .withDescription(KMLDescription(sample.getCommonNetworks().get(i))).
		 * createAndSetPoint()
		 * .addToCoordinates(Double.parseDouble(sample.getLON()),
		 * Double.parseDouble(sample.getLAT())); }
		 * 
		 * }
		 */

		kml.marshal(new File(this.filePath));

	}

	/**
	 * This function returns the description for the KML file.
	 * 
	 * @param wn
	 *            a given WiFiNetwork.
	 * @return the KML description.
	 */
	private String KMLDescription(WiFiNetwork wn) {
		return "SSID: " + wn.getSSID() + "\nMac: " + wn.getMAC() + "\nFrequency: " + wn.getFrecuency() + "\nSignal: "
				+ wn.getSignal();
	}

	/**
	 * This function sorts all the strongest Macs to be written in the file and
	 * shown ion google earth's map.
	 * 
	 * @param linesUnited
	 *            a given list of lines = samples.
	 * @return a sorted list of samples with the strongest Macs.
	 */

	private List<Sample> getSamplesList(List<String[]> linesUnited) {
		List<Sample> samples = new ArrayList<Sample>();
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
	/*
	 * private List<Sample> getStrongestMacNetworks(List<Sample> samples) {
	 * ArrayList<String> macsList = new ArrayList<String>(); String macInfo; for
	 * (int i = 0; i < samples.size(); i++) { for (int j = 0; j <
	 * samples.get(i).getNetworksAmount(); j++) { // Saves which
	 * sample&WiFiNetwork this Mac Belongs+the Mac macInfo = i + "-" + j + "-" +
	 * samples.get(i).getCommonNetworks().get(j).getMAC();
	 * macsList.add(macInfo); } } for (int i = 0; i < macsList.size(); i++) { if
	 * (!macsList.get(i).equals("checked")) { String currenStrongtMac =
	 * macsList.get(i); for (int j = i + 1; j < macsList.size() - 2; j++) { if
	 * (!macsList.get(j).equals("checked")) { if (isEqualMacs(macsList.get(i),
	 * macsList.get(j))) { String[] current = currenStrongtMac.split("-");
	 * String[] other = macsList.get(j).split("-"); int currentSample =
	 * Integer.parseInt(current[0]); int otherSample =
	 * Integer.parseInt(other[0]); int currentNetwork =
	 * Integer.parseInt(current[1]); int otherNetwork =
	 * Integer.parseInt(other[1]); // checks if current Mac's signal is weaker
	 * if (isBiggerOrEqual(samples, currentSample, otherSample, currentNetwork,
	 * otherNetwork)) { currenStrongtMac = macsList.get(j); macsList.set(i,
	 * "checked"); // reducing amount of networks by 1
	 * samples.get(currentSample)
	 * .setNetworksAmount(samples.get(currentSample).getNetworksAmount() - 1);
	 * // initializing current network
	 * samples.get(currentSample).getCommonNetworks().set(currentNetwork, new
	 * WiFiNetwork()); } else { macsList.set(j, "checked"); // reducing amount
	 * of networks by 1 samples.get(otherSample)
	 * .setNetworksAmount(samples.get(otherSample).getNetworksAmount() - 1); //
	 * initializing the other network
	 * samples.get(otherSample).getCommonNetworks().set(otherNetwork, new
	 * WiFiNetwork()); } }
	 * 
	 * } } } } return samples; }
	 * 
	 * private boolean isEqualMacs(String currentMac, String otherMac) { return
	 * currentMac.split("-")[2].equals(otherMac.split("-")[2]); }
	 * 
	 * private boolean isBiggerOrEqual(List<Sample> samples, int currentSample,
	 * int otherSample, int currentNetwork, int otherNetwork) { if
	 * ((samples.get(currentSample).getCommonNetworks().get(currentNetwork)
	 * .compareTo(samples.get(otherSample).getCommonNetworks().get(otherNetwork)
	 * )) == -1) return true; else if
	 * ((samples.get(currentSample).getCommonNetworks().get(currentNetwork)
	 * .compareTo(samples.get(otherSample).getCommonNetworks().get(otherNetwork)
	 * )) == 0) return true; return false; }
	 */
	/*
	 * private String getColor(int signal) { if (signal >= -70) return "#green";
	 * else if (signal >= -90 && signal < -70) return "#yellow"; return "#red";
	 * }
	 */

}
