package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithms {
	private Calculate calc = new Calculate();

	
	public List<String> strongestMacLocation(SamplesList sampleList, int num) {
		List<String> lines = new ArrayList<String>();

		for (int i = 0; i < sampleList.getSamplesList().size(); i++) {
			for (WiFiNetwork wn : sampleList.getSamplesList().get(i).getCommonNetworks()) {
				String mac = wn.getMAC();
				this.calc.modifyPIAlgo1(sampleList, mac,num, lines);
			}
		}
		return lines;
	}

	
	public SamplesList userLocation(SamplesList s1, SamplesList s2, int num) {

		HashMap<String, SamplesList> hmap = new HashMap<String, SamplesList>();
		// create hashmap from dataBase samples
		for (int i = 0; i < s1.getSamplesList().size(); i++) {
			for (WiFiNetwork wn : s1.getSamplesList().get(i).getCommonNetworks()) {
				if (hmap.containsKey(wn.getMAC()))
					hmap.get(wn.getMAC()).add(s1.getSamplesList().get(i));
				else {
					SamplesList temp = new SamplesList();
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
	@SuppressWarnings("unchecked")
	private Sample calcAlgo2(HashMap hmap, Sample sample, int num) {
		SamplesList temp = new SamplesList();
		Set<Sample> set = new HashSet<Sample>();
		List<Sample> list = new ArrayList<Sample>();
		// Calculate calc = new Calculate();
		// collect all the samples for this sample
		for (WiFiNetwork wn : sample.getCommonNetworks()) {
			if (hmap.containsKey(wn.getMAC())) {
				SamplesList t = (SamplesList) hmap.get(wn.getMAC());
				set.addAll((Collection<? extends Sample>) temp);
			}
		}

		list.addAll(set);
		this.calc.modifyPI(sample, list, num);
		this.calc.sort_RemoveWiFiNetworks((SamplesList) list, num);

		Coordinate point = calc.calcCoordinate((SamplesList) list, num);
		sample.setLAT(point.getLat() + "");
		sample.setLON(point.getLon() + "");
		sample.setALT(point.getAlt() + "");
		return sample;
	}

	// @SuppressWarnings("unchecked")
	// private void sort_RemoveWiFiNetworks(SamplesList sampleList, int num) {
	// ((List<Sample>) sampleList).sort(null); // sort by IP
	// if (sampleList.getSamplesList().size()> num) {
	// //remove unwanted sample
	// for (int i = num + 1; i < sampleList.getSamplesList().size(); i++)
	// sampleList.getSamplesList().remove(i);
	// }
	// }
}
