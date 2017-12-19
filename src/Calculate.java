package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {

	private int diffNoSignal = 100, noSignal = -120, minDiff = 3, norm = 10000, power = 2;
	private double sigDiff = 0.4;

	/**
	 * This function checks if a specific mac is in a given sample, if it is it
	 * modify the new sample with the same information of the given sample, adds
	 * a PI ,and the specific network (of the given mac).
	 * 
	 * @param list
	 *            a given SamplesList.
	 * @param index
	 *            a given index.
	 * @param wn
	 *            a given WiFiNetwork.
	 * @param s
	 *            a given new Sample to be modified.
	 */
	@SuppressWarnings("unchecked")
	public void modifyPIAlgo1(SamplesList list, String mac, int num, List<String> lines) {
//		SamplesList temp = new SamplesList();
		List<Sample> temp = new ArrayList<Sample>();
		Sample s = new Sample();
		boolean found = false;
		for (int j = 0; j < list.getSamplesList().size(); j++) { //run over all samples
			
			for (WiFiNetwork n : list.getSamplesList().get(j).getCommonNetworks()) { //run over all wifi in sample
				if (n.getMAC().equals(mac)) {
					int signal = Integer.parseInt(n.getSignal());
					s.setALT(list.getSamplesList().get(j).getALT());
					s.setLAT(list.getSamplesList().get(j).getLAT());
					s.setLON(list.getSamplesList().get(j).getLON());
					s.setID(list.getSamplesList().get(j).getID());
					s.setTime(list.getSamplesList().get(j).getTime());
					s.setPI(1 / Math.pow(signal, 2));
					s.addNetwork(n);
					list.getSamplesList().get(j).getCommonNetworks().remove(n);
					found = true;
				}
				if (found == true) {
					temp.add(s);
					s = new Sample();
					found=false;
					break;
					
				}
			}
		}
		
		if(temp.size()>0){
			sort_RemoveWiFiNetworks(temp, num);
			lines.add(setCoordinatesAlgo1(temp, num, mac));
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void sort_RemoveWiFiNetworks(List<Sample> sampleList, int num) {
		Collections.sort( sampleList, new PI_Comparator()); // sort by IP
		if (sampleList.size()> num) {
			//remove unwanted sample
			for (int i = num + 1; i < sampleList.size(); i++)
				sampleList.remove(i); 
		}
	}
	/**
	 * 
	 * @param s
	 * @param temp
	 * @param num
	 * @param list
	 */

	public String setCoordinatesAlgo1(List<Sample> temp, int num,String mac) {
		Coordinate point = calcCoordinate(temp);
		String ssid=temp.get(0).getCommonNetworks().get(0).getSSID();
		String frequency = temp.get(0).getCommonNetworks().get(0).getFrecuency();
		String signal =temp.get(0).getCommonNetworks().get(0).getSignal();
		String time = temp.get(0).getTime();
		return mac+","+ssid+","+frequency+","+signal+","+point.getLat() + ","+point.getLon() + ","+point.getAlt() + ","+time+",Aprrox. w-center Algo1";

	}
	
	private WiFiNetwork getNet(Sample s, String mac) {
		// run over all the sample's network list
		for (WiFiNetwork wn : s.getCommonNetworks()) {
			if (wn.getMAC() == mac)
				return wn;
		}
		return new WiFiNetwork();
	}

	public Coordinate calcCoordinate(List<Sample> sampleList) {
		double weight = 0, lat = 0, lon = 0, alt = 0;
//		int signal = 0;
		for (int i = 0; i < sampleList.size(); i++) {
			WiFiNetwork wn = new WiFiNetwork(sampleList.get(i).getCommonNetworks().get(0));
//			signal = Integer.parseInt(wn.getSignal());
			double 	signal = Double.parseDouble(wn.getSignal());
			weight += sampleList.get(i).getPI();
			lat += (Double.parseDouble(sampleList.get(i).getLAT()))
					* sampleList.get(i).getPI();
			lon += (Double.parseDouble(sampleList.get(i).getLON()))
					* sampleList.get(i).getPI();
			alt += (Double.parseDouble(sampleList.get(i).getALT()))
					* sampleList.get(i).getPI();
		}
		lat = lat / weight;
		lon = lon / weight;
		alt = alt / weight;

		Coordinate point = new Coordinate(lat + "", lon + "", alt + "");
		return point;
	}

	/**
	 * Algorithm 2 calculation calculates PI for every sample. It modifies the
	 * given samples' PI.
	 * 
	 * @param s
	 *            a given Sample.
	 * @param list
	 *            a given List <Sample>.
	 * @param num
	 *            a given number for filtering.
	 */
	public void modifyPI(Sample s, List<Sample> list, int num) {
		String mac = "";
		double weight = 0, PI = 1;
		int diff;
		for (int i = 0; i < list.size(); i++) {
			for (WiFiNetwork wn : s.getCommonNetworks()) {
				mac = wn.getMAC();
				if (list.get(i).getCommonNetworks().contains(mac)) { // has mac
																		// -
																		// calc
					WiFiNetwork net = new WiFiNetwork(getNet(list.get(i), mac));
					if (Integer.parseInt(net.getSignal()) == this.noSignal)
						diff = this.diffNoSignal;
					else
						diff = Math.max(Math.abs(Integer.parseInt(wn.getSignal()) - Integer.parseInt(net.getSignal())),
								this.minDiff);
					weight = this.norm
							/ (Math.pow(diff, this.sigDiff) * Math.pow(Integer.parseInt(wn.getSignal()), this.power));
					PI *= weight;
				}
			}
			list.get(i).setPI(PI); // do we want to create now the sample
									// that'll be printed?!
		}

	}
}
