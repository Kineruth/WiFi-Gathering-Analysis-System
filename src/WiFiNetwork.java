package src;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Date: 23-11-2017
 * This class represent a WifiNetwork.
 * Gets information from a line in the file and passes it to the WifiNetwork's parameters.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class WiFiNetwork implements Comparable<WiFiNetwork> {
	private String SSID, MAC, Frequency, Signal;

	/**
	 * Default constructor
	 */
	public WiFiNetwork() {

		this.SSID = null;
		this.MAC = null;
		this.Frequency = null;
		this.Signal = null;

	}
/**
 * Parameterized constructor
 * @param SSID  
 * @param MAC
 * @param Frequency
 * @param Signal
 */
	public WiFiNetwork(String SSID, String MAC, String Frequency, String Signal) {
		this.SSID = SSID;
		this.MAC = MAC;
		this.Frequency = convertToFrequency(Integer.parseInt(Frequency));
		this.Signal = Signal;
	}
/**
 * Copy constructor
 * @param Other copying other WifiNetwork.
 */
	public WiFiNetwork(WiFiNetwork Other) {
;
		this.SSID = Other.SSID;
		this.MAC = Other.MAC;
		this.Frequency = Other.Frequency;
		this.Signal = Other.Signal;
	}

/**
 * 
 * @return this WifiNetwork's SSID.
 */
	public String getSSID() {
		return this.SSID;
	}

/**
 * 
 * @return this WifiNetwork's Mac.
 */
	public String getMAC() {
		return this.MAC;
	}

/**
 * 
 * @return this WifiNetwork's frequency.
 */
	public String getFrecuency() {
		return this.Frequency;
	}

/**
 * 
 * @return this WifiNetwork's signal.
 */
	public String getSignal() {
		return this.Signal;
	}

	/**
	 * @return prints this WifiNetwork's parameters in a CSV format.
	 */
	public String toString() {
		return ","+this.SSID + "," + this.MAC + "," + this.Frequency + "," + this.Signal;
	}

	/**
	 * A function of Comparable.
	 * This function compares signals of the current WifiNetwork and another WifiNetwork.
	 * @return returns 1\0\-1 if the current WifiNetwork is bigger\equal\smaller than the other.
	 */
	public int compareTo(WiFiNetwork wn) {

		return this.getSignal().compareTo(wn.getSignal());
	}

	// ***************************PRIVATE*****************************
/**
 * This function converts a WifiNetwork channel to frequency. If the given channel is already in frequency numbers then it will skip the convert step.
 * @param channel takes the channel from a WifiNetwork.
 * @return the converted frequency as a string. 
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
		return channel+"";
	}

}
