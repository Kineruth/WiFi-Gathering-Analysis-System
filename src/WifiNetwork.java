package src;

import java.util.ArrayList;
import java.util.Collections;
/**
 * This class represent a WifiNetwork.
 * Gets information from a string and pass them to the WifiNetwork parameters.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */
public class WifiNetwork implements Comparable<WifiNetwork> {
	private String SSID, MAC, Frequency, Signal;

	/**
	 * Default constructor
	 */
	public WifiNetwork() {

		this.SSID = null;
		this.MAC = null;
		this.Frequency = null;
		this.Signal = null;

	}
/**
 * Parameterized constructor
 * @param line takes info from a line to build a WifiNetwork
 */
	public WifiNetwork(String SSID, String MAC, String Frequency, String Signal) {
		this.SSID = SSID;
		this.MAC = MAC;
		this.Frequency = convertToFrequency(Integer.parseInt(Frequency));
		this.Signal = Signal;
	}
/**
 * Copy constructor
 * @param Other copying other WifiNetwork.
 */
	public WifiNetwork(WifiNetwork Other) {
;
		this.SSID = Other.SSID;
		this.MAC = Other.MAC;
		this.Frequency = Other.Frequency;
		this.Signal = Other.Signal;
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
		return Frequency;
	}

/**
 * 
 * @return the WifiNetwork signal.
 */
	public String getSignal() {
		return Signal;
	}

	/**
	 * @return prints the wanted elements in a CSV format.
	 */
	public String toString() {
		return ","+this.SSID + "," + this.MAC + "," + this.Frequency + "," + this.Signal;
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
