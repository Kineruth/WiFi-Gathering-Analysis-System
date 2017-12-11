package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate_Mac {
	/**
	 * Algorithm 1:
	 * 
	 * @param sampleList
	 * @param mac
	 * @return
	 */
	public LocPoint calcMacLocation(List<Sample> sampleList, String mac) {
		List<Sample> newSampleList = arrangeSampleListForMac(sampleList, mac);
		return calculateLocation(newSampleList);
	}

	/**
	 * Algorithm 2:
	 * 
	 * @param sampleList
	 * @param macList
	 * @return
	 */
	public LocPoint calcUserLocation(List<Sample> sampleList, List<MacInput> macList) {
		// return a sorted samples list with only the wanted macs
		List<Sample> newSampleList = arrangeSampleListByMacs(sampleList, macList);
		return calculateLocation(newSampleList);
	}

	// ***************************PRIVATE*****************************
	/**
	 * Algorithm 1:
	 * 
	 * @param sampleList
	 * @param mac
	 * @return
	 */
	private List<Sample> arrangeSampleListForMac(List<Sample> sampleList, String mac) {
		List<Sample> newSampleList = new ArrayList<Sample>();
		int signal = 0;
		// run over every sample and get a sorted one.
		for (Sample s : sampleList) {
			Sample sample = new Sample();
			// if contain this mac
			if (s.getCommonNetworks().contains(mac)) {
				// get network with the same mac
				WiFiNetwork wn = new WiFiNetwork(getMacNetwork(s, mac));
				signal = Integer.parseInt(wn.getSignal());
				s.setPI(1 / Math.pow(signal, 2));
			}
			if (sample.getNetworksAmount() != 0)
				newSampleList.add(sample);
		}
		sort_RemoveWiFiNetworks(newSampleList);
		return newSampleList;

	}

	/**
	 * Algorithm 2: run over all samples and search for all wanted macs. Create
	 * a list of samples with only those macs.
	 * 
	 * @param sampleList
	 * @param macList
	 * @return
	 */
	private List<Sample> arrangeSampleListByMacs(List<Sample> sampleList, List<MacInput> macList) {

		List<Sample> newSampleList = new ArrayList<Sample>();
		int diff;
		double weight, PI;
		// run over every sample and get a sorted one.
		for (Sample s : sampleList) {
			Sample sample = new Sample();
			PI = 1;
			diff = 0;
			weight = 0;
			for (MacInput m : macList) {
				// if contain this mac
				if (s.getCommonNetworks().contains(m.getMac())) {
					// get network with the same mac
					WiFiNetwork wn = new WiFiNetwork(getMacNetwork(s, m.getMac()));
					sample.addNetwork(wn);
					int wnSignl = Integer.parseInt(wn.getSignal());
					if (wnSignl == -120)
						diff = 100;
					else
						diff = Math.max(Math.abs(m.getSignal() - wnSignl), 3);
				} else
					diff = 100;
				weight = 10000 / (Math.pow(diff, 0.4) * Math.pow(m.getSignal(), 2)) + 3;
				PI *= weight;
			}
			sample.setPI(PI);
			if (sample.getNetworksAmount() != 0)
				newSampleList.add(sample);
		}
		sort_RemoveWiFiNetworks(newSampleList);
		return newSampleList;

	}

	/**
	 * search for the network with the same mac and return it. else will return
	 * a null network
	 * 
	 * @param s
	 * @param mac
	 * @return
	 */
	private WiFiNetwork getMacNetwork(Sample s, String mac) {
		// run over all the sample's network list
		for (WiFiNetwork wn : s.getCommonNetworks()) {
			if (wn.getMAC() == mac)
				return wn;
		}
		return new WiFiNetwork();
	}

	/**
	 * 
	 * @param sampleList
	 */
	private void sort_RemoveWiFiNetworks(List<Sample> sampleList) {
		sampleList.sort(null); // sort by IP
		if (sampleList.size() > 3) {
			for (int i = 4; i < sampleList.size(); i++)
				sampleList.remove(i);
		}
	}

	/**
	 * Algorithm 1 in task2:
	 * 
	 * @param sampleList
	 * @return
	 */
	private LocPoint calculateLocation(List<Sample> sampleList) {
		double weight = 0, lat = 0, lon = 0, alt = 0;
		int signal = 0;
		for (Sample s : sampleList) {
			WiFiNetwork wn = new WiFiNetwork(s.getCommonNetworks().get(0));
			signal = Integer.parseInt(wn.getSignal());
			weight += s.getPI();
			lat += Double.parseDouble(s.getLAT());
			lon += Double.parseDouble(s.getLON());
			alt += Double.parseDouble(s.getALT());
		}
		lat = lat / weight;
		lon = lon / weight;
		alt = alt / weight;
		return new LocPoint(lat + "", lon + "", alt + "");
	}
}
