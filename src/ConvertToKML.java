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

public class ConvertToKML {
	private File file;
	private String filePath, fileName;

	public ConvertToKML(String fp) {
		this.filePath = fp;
		this.file = new File(this.filePath);
	}

	public void createFile() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		try {
			if (this.file.isFile() && this.file.getName().endsWith(".csv")) { // only
																				// get
																				// CSV
																				// files.
				this.filePath = this.filePath.replaceFirst(".csv", (" - " + timeStamp + ".kml")); // replace
																									// file
																									// type
																									// to
																									// KML+timeStamp.
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
			// Comparator<String[]> cmp = new Comparator<String[]>() {
			// public int compare(String o1, String o2) {
			// return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
			// }
			// };
			// Collections.sort(linesUnited, cmp);

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
			Kml kml = new Kml();
			Document doc = kml.createAndSetDocument();
			for (int i = 0; i < linesUnited.size(); i++) {
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

	private String getColor(int signal) {
		if (signal >= -70)
			return "#green";
		else if (signal >= -90 && signal < -70)
			return "#yellow";
		return "#red";
	}

}
