

import java.util.ArrayList;
import java.util.Collections;
/**
 * This class represent a WifiNetwork.
 * Gets information from a string and pass them to the WifiNetwork parameters.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class WifiNetwork implements Comparable<WifiNetwork> {
	private String ID, time, LAT, LON, ALT, SSID, MAC, Frecuency, Signal;

	/**
	 * Default constructor
	 */
	public WifiNetwork() {
		this.ID = null;
		this.time = null;
		this.LAT = null;
		this.LON = null;
		this.ALT = null;
		this.SSID = null;
		this.MAC = null;
		this.Frecuency = null;
		this.Signal = null;

	}
/**
 * Parameterized constructor
 * @param line takes info from a line to build a WifiNetwork
 */
	public WifiNetwork(String[] line) {
		this.ID = line[0];
		this.time = line[4];
		this.LAT = line[7];
		this.LON = line[8];
		this.ALT = line[9];
		this.SSID = line[2];
		this.MAC = line[1];
		this.Frecuency = convertToFrequency(Integer.parseInt(line[5]));
		this.Signal = line[6];
	}
/**
 * Copy constructor
 * @param Other copying other WifiNetwork.
 */
	public WifiNetwork(WifiNetwork Other) {
		this.ID = Other.ID;
		this.time = Other.time;
		this.LAT = Other.LAT;
		this.LON = Other.LON;
		this.ALT = Other.ALT;
		this.SSID = Other.SSID;
		this.MAC = Other.MAC;
		this.Frecuency = Other.Frecuency;
		this.Signal = Other.Signal;
	}
/**
 * 
 * @return the WifiNetwork ID.
 */
	public String getID() {
		return ID;
	}
/**
 * 
 * @return the WifiNetwork Time.
 */
	public String getTime() {
		return time;
	}
/**
 * 
 * @return the WifiNetwork latitude.
 */
	public String getLAT() {
		return LAT;
	}

/**
 * 
 * @return the WifiNetwork longitude.
 */
	public String getLON() {
		return LON;
	}

/**
 * 
 * @return the WifiNetwork altitude.
 */
	public String getALT() {
		return ALT;
	}
/**
 * 
 * @return the WifiNetwork SSID.
 */
	public String getSSID() {
		return SSID;
	}

/**
 * 
 * @return the WifiNetwork Mac.
 */
	public String getMAC() {
		return MAC;
	}

/**
 * 
 * @return the WifiNetwork frequency.
 */
	public String getFrecuency() {
		return Frecuency;
	}

/**
 * 
 * @return the WifiNetwork signal.
 */
	public String getSignal() {
		return Signal;
	}

/**
 * 
 * @return prints the common elements in a CSV format.
 */
	public String printCommonProp() {
		return this.time + "," + this.ID + "," + this.LAT + "," + this.LON + "," + this.ALT;
	}

	/**
	 * @return prints the wanted elements in a CSV format.
	 */
	public String toString() {
		return this.SSID + "," + this.MAC + "," + this.Frecuency + "," + this.Signal;
	}

	/**
	 * A function of Comparable.
	 * This function compares signals of the current WifiNetwork and another WifiNetwork.
	 * @return returns 1\0\-1 if the current WifiNetwork bigger\equal\smaller than the other.
	 */
	public int compareTo(WifiNetwork wn) {

		return this.getSignal().compareTo(wn.getSignal());
	}

	// ***************************PRIVATE*****************************
/**
 * This function converts a WifiNetwork channel to frequency.
 * @param channel takes the channel from a WifiNetwork.
 * @return returns the converted frequency. 
 */
	private String convertToFrequency(int channel) {
		int frequency;
		if (channel >= 1 && channel <= 14) {
			frequency = (channel - 1) * 5 + 2412;
			return frequency + "";
		} else if (channel >= 36 && channel <= 165) {
			frequency = (channel - 34) * 5 + 5170;
			return frequency + "";
		}
		return "";
	}

}
