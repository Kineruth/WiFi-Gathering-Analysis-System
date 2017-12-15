package src;

import java.util.List;

public class Calculate {
	
private int diffNoSignal=100, noSignal=-120, minDiff=3,norm=10000,power=2;
private double sigDiff=0.4;
	
	public void modifyPIAlgo1(SamplesList list, int index, WiFiNetwork wn, Sample s){
		String mac=wn.getMAC();
		int signal = 0;
		// if contain this mac
		if (list.getSamplesList().get(index).getCommonNetworks().contains(mac)) {
			// get network with the same mac
			wn = new WiFiNetwork(getNet(list.getSamplesList().get(index), mac));
			signal = Integer.parseInt(wn.getSignal());
			list.getSamplesList().get(index).setPI(1 / Math.pow(signal, 2));
			s = new Sample(list.getSamplesList().get(index));
			s.setCommonNetworks(null);
			s.addNetwork(wn);
		}
	}
	public void setSamplesAlgo1(Sample s,SamplesList temp, int num, SamplesList list){
		Coordinate point = calcLocation(temp, num);
		s.setALT(point.getAlt() + "");
		s.setLAT(point.getLat() + "");
		s.setLON(point.getLon() + "");
		/* add sample with strongest mac coordinate, and strongest signal */
		list.add(s);
	}
	
	private WiFiNetwork getNet(Sample s, String mac) {
		// run over all the sample's network list
		for (WiFiNetwork wn : s.getCommonNetworks()) {
			if (wn.getMAC() == mac)
				return wn;
		}
		return new WiFiNetwork();
	}
	
	public Coordinate calcLocation(SamplesList sampleList, int num) {
		double weight = 0, lat = 0, lon = 0, alt = 0;
		int signal = 0;
		for (int i = 0; i < sampleList.getSamplesList().size(); i++) { //run over all samples
			WiFiNetwork wn = new WiFiNetwork(sampleList.getSamplesList().get(i).getCommonNetworks().get(0));
			signal = Integer.parseInt(wn.getSignal());
			weight += sampleList.getSamplesList().get(i).getPI();
			lat += (Double.parseDouble(sampleList.getSamplesList().get(i).getLAT())) * sampleList.getSamplesList().get(i).getPI();
			lon += (Double.parseDouble(sampleList.getSamplesList().get(i).getLON())) * sampleList.getSamplesList().get(i).getPI();
			alt += (Double.parseDouble(sampleList.getSamplesList().get(i).getALT())) * sampleList.getSamplesList().get(i).getPI();
		}
		lat = lat / weight;
		lon = lon / weight;
		alt = alt / weight;

		Coordinate point = new Coordinate(lat+"",lon+"",alt+"");
		return point;	
	}
	

	/**
	 * Algorithm 2 calculation
	 * calculates PI for every sample. It modifies the given samples' PI. 
	 * @param s a given Sample.
	 * @param list a given List <Sample>.
	 * @param num a given number for filtering.
	 */
	public void modifyPI(Sample s, List<Sample> list, int num){
		String mac="";
		double weight=0, PI=1;
		int diff;
		for (int i = 0; i < list.size(); i++) {
			for(WiFiNetwork wn : s.getCommonNetworks()){
				mac= wn.getMAC();
				if(list.get(i).getCommonNetworks().contains(mac)){ //has mac - calc
					WiFiNetwork net = new WiFiNetwork(getNet(list.get(i), mac));
					if(Integer.parseInt(net.getSignal())==this.noSignal) diff=this.diffNoSignal;
					else diff =Math.max(Math.abs(Integer.parseInt(wn.getSignal())-Integer.parseInt(net.getSignal())), this.minDiff);
						weight= this.norm/(Math.pow(diff, this.sigDiff)*Math.pow(Integer.parseInt(wn.getSignal()), this.power));
						PI*=weight;
				}
			}
			list.get(i).setPI(PI); //do we want to create now the sample that'll be printed?!
		}
		
	}
}
