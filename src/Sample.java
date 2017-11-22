package src;

import java.util.ArrayList;

public class Sample {

	private String ID, Time, LAT, LON, ALT;
	private ArrayList<WifiNetwork> commonNetworks;

	public Sample() {
		this.ID = null;
		this.Time = null;
		this.LAT = null;
		this.LON = null;
		this.ALT = null;
		this.commonNetworks = new ArrayList<WifiNetwork>();
	}

	public Sample(String ID, String Time, String LAT, String LON, String ALT) {
		this.ID = ID;
		this.Time = Time;
		this.LAT = LAT;
		this.LON = LON;
		this.ALT = ALT;
	}

	public void addNetwork(WifiNetwork network) {
		this.commonNetworks.add(network);
	}

	public String getID() {
		return ID;
	}

	public String getTime() {
		return Time;
	}

	public String getLAT() {
		return LAT;
	}

	public String getLON() {
		return LON;
	}

	public String getALT() {
		return ALT;
	}

	public ArrayList<WifiNetwork> getCommonNetworks() {
		return this.commonNetworks;
	}
	

	public void setCommonNetworks(ArrayList<WifiNetwork> commonNetworks) {
		this.commonNetworks = commonNetworks;
	}

	public int getSampleSize() {
		return this.commonNetworks.size();
	}

	public String printSampleInfo() {
		return this.Time + "," + this.ID + "," + this.LAT + "," + this.LON + "," + this.ALT + "," + getSampleSize();
	}

	public boolean compareTime(String Time) {
		return this.Time.equals(Time);
	}

	public boolean compareLAT(String LAT) {
		return this.LAT.equals(LAT);
	}

	public boolean compareLON(String LON) {
		return this.LON.equals(LON);
	}

	public boolean compareALT(String ALT) {
		return this.ALT.equals(ALT);
	}

}
