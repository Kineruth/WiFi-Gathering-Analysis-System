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

public class ConvertToKML {
	private File file;
	private String filePath, fileName;

	public ConvertToKML(String fp) {
		this.filePath = fp;
		this.file = new File(this.filePath );
	}

	public void createFile() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	try{
		if (this.file.isFile() && this.file.getName().endsWith(".csv")) { //only get CSV files.
			this.filePath = this.filePath.replaceFirst(".csv", (" - "+timeStamp+".kml")); //replace file type to KML+timeStamp.
			readFile();
		} 
	}catch (Exception e) {
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
//			 Comparator<String[]> cmp = new Comparator<String[]>() {
//			      public int compare(String o1, String o2) {
//			        return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
//			      }
//			      };
//			      Collections.sort(linesUnited, cmp);
			    

			br.close();
			fr.close();

			writeFile(linesUnited);
			

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);

		}

	}
	
//  ***************************PRIVATE*****************************

	private void writeFile(List<String[]> linesUnited) {

		try {
			FileWriter fw = new FileWriter(this.filePath );  //file name & path as the given file
			PrintWriter outs = new PrintWriter(fw);
			String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			outs.println(line);
			line = "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
			outs.println(line);
			outs.println();
			for (int i = 0; i < linesUnited.size(); i++) {

				line = "<Placemark>";
				outs.println(line);
				outs.println("<name><![CDATA[" + linesUnited.get(i)[0] + "]]></name>");
				outs.println(
						"<description><![CDATA[<b>TimeStamp: </b>" + linesUnited.get(i)[0] + "<br><b>Scanned with: </b>"
								+ linesUnited.get(i)[1] + "</br><br><b>Amount of WiFi networks : </b>"
								+ linesUnited.get(i)[5] + "<table  border=\"1\" style=\"font-size:12px;\"> <tr>");
				outs.println("<td><b>SSID</b></td>");
				outs.println("<td><b>Mac</b></td>");
				outs.println("<td><b>Frequency</b></td>");
				outs.println("<td><b>Signal</b></td></tr>");
				for (int j = 0; j < (Integer.parseInt(linesUnited.get(i)[5])); j++) {
					outs.println("<tr>");
					outs.println("<td>" + linesUnited.get(i)[j * 4 + 6] + "</td>");
					outs.println("<td>" + linesUnited.get(i)[j * 4 + 7] + "</td>");
					outs.println("<td>" + linesUnited.get(i)[j * 4 + 8] + "</td>");
					outs.println("<td>" + linesUnited.get(i)[j * 4 + 9] + "</td>");
					outs.println("</tr>");

				}
				outs.println("</table>]]></description><styleUrl>" + getColor(Integer.parseInt(linesUnited.get(i)[9]))
						+ "</styleUrl>");
				outs.println("<Point>");
				outs.println(
						"<coordinates>" + linesUnited.get(i)[3] + "," + linesUnited.get(i)[2] + "</coordinates></Point>");
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
	private String getColor(int signal) {
		if (signal >= -70)
			return "#green";
		else if (signal >= -90 && signal < -70)
			return "#yellow";
		return "#red";
	}

}
