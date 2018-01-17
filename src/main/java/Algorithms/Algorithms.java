package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import KML.Coordinate;
import MergedCSV.Sample;
import MergedCSV.WiFiNetwork;

/**
 * This class has all the function for the two algorithms.
 *  It runs over all samples and sends them to the needed functions.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Algorithms {
	
	private Calculate calc = new Calculate();
	
	/**
	 * This function sends every Mac and all the samples to calculate its strongest location,
	 * @param sampleList a given list of samples exported from the merged CSV file.
	 * @param num a given number for the amount of strongest samples to take after sorting them.
	 * @return a list of arranged lines to be written in a new CSV file.
	 */
	public List<Sample> strongestMacLocation(List<Sample> sampleList, int num) {
		List<Sample> lines = new ArrayList<Sample>();

		for (int i = 0; i < sampleList.size(); i++) {
			for (int j = 0; j < sampleList.get(i).getCommonNetworks().size(); j++) {
				WiFiNetwork wn = sampleList.get(i).getCommonNetworks().get(j);
				String mac = wn.getMAC();
				this.calc.modifyPIAlgo1(sampleList, mac, num, lines);
				j--;
			}

		}
		return lines;
	}
/**
 * This class puts all the macs and the samples they were found in them from s1 in a HashMap.
 * Then it sends every sample from s2 to calculate the user location when this sample was scanned.
 * @param s1 a given list of samples exported from the merged CSV file.
 * @param s2 a given list of samples that doesn't have coordinates, exported from the merged CSV file.
 * @param num a given number for the amount of strongest samples to take after sorting them.
 * @return an arranged list of samples with the user locations to be written to a new CSV file.
 */
	public List<Sample> userLocation(List<Sample> s1, List<Sample> s2, int num) {

		HashMap<String, List<Sample>> hmap = new HashMap<String, List<Sample>>();
		// create hashmap from dataBase samples
		for (int i = 0; i < s1.size(); i++) {
			for (int j = 0; j < s1.get(i).getCommonNetworks().size(); j++) {
				WiFiNetwork wn = s1.get(i).getCommonNetworks().get(j);
				if (hmap.containsKey(wn.getMAC()))
					hmap.get(wn.getMAC()).add(s1.get(i));
				else {
					List<Sample> temp = new ArrayList<Sample>();
					temp.add(s1.get(i));
					hmap.put(wn.getMAC(), temp);
				}
			}
		}
		// run over file2 samples:
		for (int i = 0; i < s2.size(); i++) {
			calcAlgo2(hmap, s2.get(i), num);

		}

		return s2;
	}

	// ***************************PRIVATE*****************************

	/**
	 * Algorithm 2: 
	 * Runs over all the WiFiNetworks in the given sample with no coordinates, 
	 * and collects all the needed sample for calculating the user location for this samples.
	 * It gets rid from all the multiple samples for every Mac using Set.
	 * Sends them to a function that calculates their PI.
	 * Sorts all the samples by PI from strongest to the weakest, takes a number of wanted samples - with a given num and removes the others.
	 * Sets the coordinates that were found and returns the updated sample.
	 * @param hmap a given HashMap of macs and samples
	 * @param sample a given sample.
	 * @param num a given number for filtering.
	 * @return the given sample with the user coordinates we found.
	 */
	private Sample calcAlgo2(HashMap<String, List<Sample>> hmap, Sample sample, int num) {
		List<Sample> temp = new ArrayList<Sample>();
		Set<Sample> set = new HashSet<Sample>();
		List<Sample> list = new ArrayList<Sample>();
		// collect all the samples for this sample 
		for (WiFiNetwork wn : sample.getCommonNetworks()) {
			if (hmap.containsKey(wn.getMAC())) {
				temp.addAll(hmap.get(wn.getMAC()));
				set.addAll(temp);
			}
		}
		list.addAll(set);
		calc.modifyPIAlgo2(sample,  list);
		this.calc.sort_RemoveWiFiNetworks(list, num);
		
		Coordinate point = calc.calcCoordinate(list);
		sample.setLAT(point.getLat() + "");
		sample.setLON(point.getLon() + "");
		sample.setALT(point.getAlt() + "");
		return sample;
	}

}
