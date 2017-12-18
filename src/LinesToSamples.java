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
			if(str.length()==40 && str.contains("ID")){
			str = br.readLine();//skip header
			}
			while (str != null) {
				line = str.split(",");
				linesUnited.add(line);
				str = br.readLine();
			}
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file! Not the correct CSV format\n" + ex);
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
