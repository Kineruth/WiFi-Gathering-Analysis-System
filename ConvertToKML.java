

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ConvertToKML {
	private File file;
	private String filePath, fileName, input;
	private int choice;

	public ConvertToKML(String fp) {
		this.filePath = fp;
		this.file = new File(this.filePath );
		this.choice = 0;
		this.input = null;
	}

	public void createFile() {
		if (this.file.isFile() && this.file.getName().endsWith(".csv")) { //only get CSV files.
			this.filePath = this.filePath.replaceFirst(".csv", ".kml"); //replace file type from CSV to KML.
			Filter f = new Filter();
			this.input = f.chooseFilter();
			this.choice = f.getChoice();
			readFile();
		} else {
			System.out.println("Invalid Input! Not a file.");
		}
	}

	private void readFile() {
		try {

			FileReader fr = new FileReader(this.file.getPath());
			BufferedReader br = new BufferedReader(fr);
			String[] line;
			ArrayList<String[]> linesUnite = new ArrayList<String[]>();
			String str = br.readLine();

			if (this.choice == 1) {
				str = br.readLine();
				while (str != null) {
					line = str.split(",");
					if (line[0].split(" ")[0].equals(this.input.split(" ")[0])
							&& (line[0].split(" ")[1].split(":")[0].equals(this.input.split(" ")[1].split(":")[0])))
						linesUnite.add(line);
					str = br.readLine();

				}
			} else if (this.choice == 2) {
				str = br.readLine();
				while (str != null) {
					line = str.split(",");
					LocPoint p1 = new LocPoint(this.input.split(",")[0], this.input.split(",")[1]);
					LocPoint p2 = new LocPoint(line[2], line[3]);
					if (p1.pointInCircle(p2, Double.parseDouble(this.input.split(",")[2])))
						linesUnite.add(line);
					str = br.readLine();
				}
			} else {
				str = br.readLine();
				while (str != null) {
					line = str.split(",");
					if (this.input.equals(line[1]))
						linesUnite.add(line);
					str = br.readLine();
				}
			}
			br.close();
			fr.close();

			writeFile(linesUnite);
			System.out.println("Done Creating KML file!");

		} catch (

		IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);

		}

	}

	public void writeFile(ArrayList<String[]> linesUnite) {

		try {
			FileWriter fw = new FileWriter(this.filePath );  //file name & path as the given file
			PrintWriter outs = new PrintWriter(fw);
			String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			outs.println(line);
			line = "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
			outs.println(line);
			outs.println();
			for (int i = 0; i < linesUnite.size(); i++) {

				line = "<Placemark>";
				outs.println(line);
				outs.println("<name><![CDATA[" + linesUnite.get(i)[0] + "]]></name>");
				outs.println(
						"<description><![CDATA[<b>TimeStamp: </b>" + linesUnite.get(i)[0] + "<br><b>Scanned with: </b>"
								+ linesUnite.get(i)[1] + "</br><br><b>Amount of WiFi networks : </b>"
								+ linesUnite.get(i)[5] + "<table  border=\"1\" style=\"font-size:12px;\"> <tr>");
				outs.println("<td><b>SSID</b></td>");
				outs.println("<td><b>Mac</b></td>");
				outs.println("<td><b>Frequency</b></td>");
				outs.println("<td><b>Signal</b></td></tr>");
				for (int j = 0; j < (Integer.parseInt(linesUnite.get(i)[5])); j++) {
					outs.println("<tr>");
					outs.println("<td>" + linesUnite.get(i)[j * 4 + 6] + "</td>");
					outs.println("<td>" + linesUnite.get(i)[j * 4 + 7] + "</td>");
					outs.println("<td>" + linesUnite.get(i)[j * 4 + 8] + "</td>");
					outs.println("<td>" + linesUnite.get(i)[j * 4 + 9] + "</td>");
					outs.println("</tr>");

				}
				outs.println("</table>]]></description><styleUrl>" + getColor(Integer.parseInt(linesUnite.get(i)[9]))
						+ "</styleUrl>");
				outs.println("<Point>");
				outs.println(
						"<coordinates>" + linesUnite.get(i)[3] + "," + linesUnite.get(i)[2] + "</coordinates></Point>");
				outs.println("</Placemark>");
				outs.println();
			}
			outs.println("</Folder></Document></kml>");
			outs.close();
			fw.close();
		}  catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}

	}

//  ***************************PRIVATE*****************************
	
	private String getColor(int signal) {
		if (signal >= -70)
			return "#green";
		else if (signal >= -90 && signal < -70)
			return "#yellow";
		return "#red";
	}

}
