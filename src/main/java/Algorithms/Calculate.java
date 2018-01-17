package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import KML.Coordinate;
import MergedCSV.Sample;
import MergedCSV.WiFiNetwork;
/**
 * This class contains all the calculations needed for both algorithm 1 & 2.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class Calculate {

	private int diffNoSignal = 100, noSignal = -120, minDiff = 3, norm = 10000, power = 2;
	private double sigDiff = 0.4;

	/**
	 * This function checks if a specific mac is in a given sample, if it is it
	 * modify the new sample with the same information of the given sample, adds
	 * a PI ,and the specific network (of the given mac).
	 * 
	 * @param list a given SamplesList.
	 * @param index a given index.
	 * @param wn a given WiFiNetwork.
	 * @param s a given new Sample to be modified.
	 */
	public void modifyPIAlgo1(List<Sample> list, String mac, int num, List<Sample> lines) {
		List<Sample> temp = new ArrayList<Sample>();
		Sample s = new Sample();
		boolean found = false;
		for (int j = 0; j < list.size(); j++) { //run over all samples
			
			for (WiFiNetwork n : list.get(j).getCommonNetworks()) { //run over all wifi in sample
				if (n.getMAC().equals(mac)) {
					double signal = Double.parseDouble(n.getSignal());
					s.setALT(list.get(j).getALT());
					s.setLAT(list.get(j).getLAT());
					s.setLON(list.get(j).getLON());
					s.setID(list.get(j).getID());
					s.setTime(list.get(j).getTime());
					s.setPI(1 / Math.pow(signal, 2));
					s.addNetwork(n);
					list.get(j).getCommonNetworks().remove(n);
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
			lines.add(setCoordinatesAlgo1(temp, mac));
		}
	}
	
	/**
	 * 
	 * @param sampleList a given list of samples.
	 * @param num a given number for the amount of strongest samples to take after sorting them.
	 */
	@SuppressWarnings("unchecked")
	public void sort_RemoveWiFiNetworks(List<Sample> sampleList, int num) {
		Collections.sort( sampleList, new PI_Comparator()); // sort by IP
		if (sampleList.size()> num) {
			//remove unwanted samples
			for (int i = num; i < sampleList.size(); i++)
				sampleList.remove(i); 
		}
	}
	/**
	 * This function sends the given samples and mac to a function that will calculate the mac's strongest location.
	 * Then it will create and return a line with all the information needed to be written in the new file.
	 * @param s a given list of samples.
	 * @param mac a given mac.
	 * @return return a line with all the updated sample's information.
	 */

	public Sample setCoordinatesAlgo1(List<Sample> s,String mac) {
		Coordinate point = calcCoordinate(s);
		Sample newS = new Sample();
		newS.addNetwork(s.get(0).getCommonNetworks().get(0));
		newS.setLAT(point.getLat()+"");
		newS.setLON(point.getLon()+"");
		newS.setALT(point.getAlt()+"");
		return newS;
//		String ssid=s.get(0).getCommonNetworks().get(0).getSSID();
//		String frequency = s.get(0).getCommonNetworks().get(0).getFrecuency();
//		String signal =s.get(0).getCommonNetworks().get(0).getSignal();
//		String time = s.get(0).getTime();
//		return mac+","+ssid+","+frequency+","+signal+","+point.getLat() + ","+point.getLon() + ","+point.getAlt() + ","+time+",Aprrox. w-center Algo1";
		
	}
	/**
	 * This function calculate the coordinates parameters by summing all the parameters and divides them by the weight.
	 * @param sampleList a given list of samples.
	 * @return a coordinate with the calculated parameters.
	 */
	public Coordinate calcCoordinate(List<Sample> sampleList) {
		double weight = 0, lat = 0, lon = 0, alt = 0;
		double signal;
		for (int i = 0; i < sampleList.size(); i++) {
			WiFiNetwork wn = new WiFiNetwork(sampleList.get(i).getCommonNetworks().get(0));
			 signal = Double.parseDouble(wn.getSignal());
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
	 * Algorithm 2 calculation: calculates the PI for every sample and modifies it. 
	 * given samples' PI.
	 * @param s a given Sample.
	 * @param list a given List <Sample>.
	 * @param num a given number for filtering.
	 */
	public void modifyPIAlgo2(Sample s, List<Sample> list) {
		String mac = "";
		double weight = 0, PI = 1;
		double diff;
		for (int i = 0; i < list.size(); i++) { //run over all samples
			for (WiFiNetwork wn : s.getCommonNetworks()) { //run over all wifi's in given sample
				mac = wn.getMAC();
				for(WiFiNetwork w : list.get(i).getCommonNetworks()){//wifi's list
					if(mac.equals(w.getMAC())){
						if (Double.parseDouble(w.getSignal()) == this.noSignal)
						diff = this.diffNoSignal;
					else
						diff = Math.max(Math.abs(Double.parseDouble(wn.getSignal()) - Double.parseDouble(w.getSignal())),
								this.minDiff);
					weight = this.norm
							/ (Math.pow(diff, this.sigDiff) * Math.pow(Double.parseDouble(wn.getSignal()), this.power));
					PI *= weight;
				}
				}
			}
			list.get(i).setPI(PI); 
		}

	}
}
