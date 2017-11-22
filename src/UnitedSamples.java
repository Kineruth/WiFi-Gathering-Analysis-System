package src;

import java.util.ArrayList;

public class UnitedSamples {
	private ArrayList<Sample> united;

	public UnitedSamples() {
		this.united = new ArrayList<Sample>();
	}

	public void add(Sample sample) {
		ArrayList<WifiNetwork> sortedNetworks =sortBySignal(sample.getCommonNetworks()); 
		sample.setCommonNetworks(sortedNetworks);
		this.united.add(sample);
	}

	public int size() {
		return this.united.size();
	}

	public Sample get(int i) {
		return this.united.get(i);
	}

	public ArrayList<Sample> getUnitedNets() {
		return this.united;
	}

	public ArrayList<WifiNetwork> sortBySignal(ArrayList <WifiNetwork>sampleNetworks) {
		ArrayList<WifiNetwork> networks = new ArrayList<WifiNetwork>();
		sampleNetworks.sort(null);
		for (int i = 0; (i < 10) &&( i <sampleNetworks.size()); i++) {
			networks.add(sampleNetworks.get(i));
		}
		return networks;

	}

}
