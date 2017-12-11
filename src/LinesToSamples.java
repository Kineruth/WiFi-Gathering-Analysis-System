package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinesToSamples {
	public LinesToSamples() {
	}

	/**
	 * This function creates WiFiNetworks and a list of samples to be written in the file and shown on google earth's map.
	 * 
	 * @param linesUnited a given list of lines = samples.
	 * @return a list of samples.
	 */
	public List<Sample> getSamplesList(List<String[]> linesUnited) {
		List<Sample> samples = new ArrayList<Sample>();
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
