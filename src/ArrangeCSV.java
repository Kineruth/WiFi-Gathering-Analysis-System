package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArrangeCSV {

	
	private  String filePath, fileName,output;
	private File file;
	private int num;
	
	
	public ArrangeCSV(String filePath, String outputPath, String num){
		this.filePath=filePath+"";
		this.file = new File(this.filePath);
		this.num=Integer.parseInt(num);
	}
	
	public void arrangeByStrongMacs(){ 
		checkCSVFile() ;
		Calculate_Mac cm = new Calculate_Mac();
		LinesToSamples ls = new LinesToSamples();
		List<Sample> s = new ArrayList<Sample>();
		s=cm.calcMacLocation(ls.readCSV(this.file.getPath()),this.num);//sample list to be written
	    write(s);
		
	}
	
	public void checkCSVFile() { //need to be checked
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		try {
			// Get only CSV files
			if (this.file.isFile() && this.file.getName().endsWith(".csv")) {
				// Add to file's name timeStamp & change file type to KML
				this.filePath = this.filePath.replaceFirst(".csv", (" - " + timeStamp + ".kml"));
				this.filePath = this.filePath.replaceFirst(".csv", (".kml"));
				System.out.println("Done writing KML file!");
			}
		} catch (Exception e) {
			System.out.println("Error running the program!");
		}
	}
	private void write(List<Sample> s){ //need to be finished.
		try {
			// Gets the timeStamp
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			FileWriter fw = new FileWriter(this.fileName + ".csv");
			PrintWriter outs = new PrintWriter(fw);
			String info;
			// adds the title only once to the file
				String line = "Time,ID,LAT,LON,ALT,#WiFi networks";
				for (int i = 1; i < 11; i++) {
					line += ",SSID" + i + ",MAC" + i + ",Frecuency" + i + ",Signal" + i;
				}
				outs.println(line);

			}
		catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		
			
		}
			
	}

}
