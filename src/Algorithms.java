package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithms {
	private Calculate calc = new Calculate();

	public List<String> strongestMacLocation(SamplesList sampleList, int num) {
		List<String> lines = new ArrayList<String>();

		for (int i = 0; i < sampleList.getSamplesList().size(); i++) {
			for (int j = 0; j < sampleList.getSamplesList().get(i).getCommonNetworks().size(); j++) {
				WiFiNetwork wn = sampleList.getSamplesList().get(i).getCommonNetworks().get(j);
				String mac = wn.getMAC();
				this.calc.modifyPIAlgo1(sampleList, mac, num, lines);
				j--;
			}

		}
		return lines;
	}

	public SamplesList userLocation(SamplesList s1, SamplesList s2, int num) {

		HashMap<String, List<Sample>> hmap = new HashMap<String, List<Sample>>();
		// create hashmap from dataBase samples
		for (int i = 0; i < s1.getSamplesList().size(); i++) {
			for (int j = 0; j < s1.getSamplesList().get(i).getCommonNetworks().size(); j++) {
				WiFiNetwork wn = s1.getSamplesList().get(i).getCommonNetworks().get(j);
				if (hmap.containsKey(wn.getMAC()))
					hmap.get(wn.getMAC()).add(s1.getSamplesList().get(i));
				else {
					List<Sample> temp = new ArrayList<Sample>();
					temp.add(s1.getSamplesList().get(i));
					hmap.put(wn.getMAC(), temp);
				}
			}
		}
		// run over file2 samples:
		for (int i = 0; i < s2.getSamplesList().size(); i++) {
			calcAlgo2(hmap, s2.getSamplesList().get(i), num);

		}

		return s2;
	}

	// ***************************PRIVATE*****************************

	/**
	 * All calculations for algorithm 2:
	 * 
	 * @param hmap
	 *            a given HashMap of macs and samples
	 * @param sample
	 *            a given sample.
	 * @param num
	 *            a given number for filtering.
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
		calc.modifyPI(sample,  list);
		this.calc.sort_RemoveWiFiNetworks(list, num);
		
		Coordinate point = calc.calcCoordinate(list);
		sample.setLAT(point.getLat() + "");
		sample.setLON(point.getLon() + "");
		sample.setALT(point.getAlt() + "");
		return sample;
	}

}
