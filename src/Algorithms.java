package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

public class Algorithms {
	
	public SamplesList strongestMacLocation(SamplesList sampleList, int num) {
		SamplesList newList = new SamplesList();
		Calculate calc = new Calculate();
		// run over every sample and get a sorted one.
		for (int i = 0; i < sampleList.getSamplesList().size(); i++) { 
			//run over all networks in sample
			for (WiFiNetwork net : sampleList.getSamplesList().get(i).getCommonNetworks()) { 
				SamplesList temp = new SamplesList();
				Sample sample = new Sample();
				WiFiNetwork wn = new WiFiNetwork();
				for (int j = 0; j < sampleList.getSamplesList().size(); j++) { 
					calc.modifyPIAlgo1(sampleList, j, net, sample);
					if (sample.getNetworksAmount() > 0)
						temp.add(sample);
					if (sample.getNetworksAmount() > 1)
						sampleList.getSamplesList().get(j).getCommonNetworks().remove(wn); // works?
				}
				/* *
				 * newList has all the samples to be written to file
				 */
				sort_RemoveWiFiNetworks(temp, num);
				calc.setSamplesAlgo1(temp.getSamplesList().get(0), temp, num, newList);
			}
		}
		return newList;
	}
	

public SamplesList userLocation(SamplesList s1, SamplesList s2,int num) {
		
		HashMap<String, SamplesList> hmap = new HashMap<String, SamplesList>();
		//create hashmap from dataBase samples
		for (int i = 0; i < s1.getSamplesList().size(); i++) { 
			for(WiFiNetwork wn : s1.getSamplesList().get(i).getCommonNetworks()){
				if(hmap.containsKey(wn.getMAC()))
					hmap.get(wn.getMAC()).add(s1.getSamplesList().get(i));
				else {
					SamplesList temp = new SamplesList();
					temp.add(s1.getSamplesList().get(i));
					hmap.put(wn.getMAC(), temp);
				}
			}
		}
		//run over file2 samples:
		for (int i = 0; i < s2.getSamplesList().size(); i++) { 
			 calcAlgo2(hmap, s2.getSamplesList().get(i), num); 
			
		}
		
		return s2;
	}

/**
 * All calculations for algorithm 2:
 * @param hmap a given HashMap of macs and samples
 * @param sample a given sample.
 * @param num a given number for filtering.
 * @return the given sample with the user coordinates we found.
 */
@SuppressWarnings("unchecked")
private Sample calcAlgo2(HashMap hmap, Sample sample, int num){
  SamplesList temp = new SamplesList();
  Set<Sample> set = new HashSet<Sample>();
  List<Sample> list = new ArrayList<Sample>();
  Calculate calc = new Calculate();
//collect all the samples for this sample
	for(WiFiNetwork wn : sample.getCommonNetworks()){
		if(hmap.containsKey(wn.getMAC())){
			SamplesList t =(SamplesList) hmap.get(wn.getMAC());
			set.addAll((Collection<? extends Sample>) temp);
	}
	}
	
	list.addAll(set);
	calc.modifyPI( sample,  list,  num);
	sort_RemoveWiFiNetworks((SamplesList) list,  num);
	
	Coordinate point = calc.calcLocation((SamplesList) list,  num);
	sample.setLAT(point.getLat()+"");
	sample.setLON(point.getLon()+"");
	sample.setALT(point.getAlt()+"");
	return sample;
} 

	@SuppressWarnings("unchecked")
	private void sort_RemoveWiFiNetworks(SamplesList sampleList, int num) {
		((List<Sample>) sampleList).sort(null); // sort by IP
		if (sampleList.getSamplesList().size()> num) {
			//remove unwanted sample
			for (int i = num + 1; i < sampleList.getSamplesList().size(); i++)
				sampleList.getSamplesList().remove(i); 
		}
	}
}
