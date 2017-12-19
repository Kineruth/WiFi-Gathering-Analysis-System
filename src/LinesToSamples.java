package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LinesToSamples {
	public LinesToSamples() {
	}

	public List<String[]> readCSV(String filePath) {
		List<String[]> linesUnited = new ArrayList<String[]>();
		try {

			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String[] line;
			String str = br.readLine(); 
			line = str.split(",");
			if(line.length==46 && line[1].equals("ID")){
			str = br.readLine();//skip header
			}
			boolean flag = true;
			while (str != null&& flag) {
				line = str.split(",");
				if(line.length>4){
				linesUnited.add(line);
				str = br.readLine();
				}
				else flag=false;
			}
			br.close();
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file! Not the correct CSV format\n" + ex);
//			e.printStackTrace();
//			System.out.println(e);
			System.exit(2);

		}
		return linesUnited;
	}
	/**
	 * This function creates WiFiNetworks and a list of samples to be written in the file and shown on google earth's map.
	 * 
	 * @param linesUnited a given list of lines = samples.
	 * @return a list of samples.
	 */
	public SamplesList convertLines(List<String[]> linesUnited) {
		SamplesList samples = new SamplesList();
		for (int i = 0; i < linesUnited.size(); i++) {
			String[] line = linesUnited.get(i);
			Sample sample = new Sample(line[1], line[0], line[2], line[3], line[4],0);
			for (int j = 6; j < line.length - 3; j = j + 4) {
				WiFiNetwork network = new WiFiNetwork(line[j], line[j + 1], line[j + 2], line[j + 3]);
				sample.addNetwork(network);
			}
			samples.add(sample);
		}
		return samples;
	}

	

}
