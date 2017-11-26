package src;
/**
 * Date: 23-11-2017
 * This class represents a point on earth using latitude & longitude.
 * It calculates the distance between two points and returns if a specific point is within a given Km radius.
 * @author Kineret Ruth Nahary & Yakir Amar
 *
 */

public class LocPoint {

	private double Lat, Lon;
/**
 * Default constructor
 */
	public LocPoint(){
		this.Lat=0;
		this.Lon=0;
	}
	/**
	 *  Parameterized constructor
	 *  Converts a string to double. The parameters are already in radians - as the app "Wiggle" exports them.
	 * @param LAT 
	 * @param LON
	 */
	public LocPoint(String LAT, String LON) {
		this.Lat = Double.parseDouble(LAT);
		this.Lon = Double.parseDouble(LON);
	}
/**
 * 
 * @return this latitude.
 */
	public double getLat() {
		return Lat;
	}

/**
 * 
 * @return this longitude.
 */
	public double getLon() {
		return Lon;
	}

/**
 * @return prints the point's parametes.
 */
	public String toString() {
		return "LocPoint [Lat=" + Lat + ", Lon=" + Lon + "]";
	}

	/**
	 * This function checks if a point is within a given radius of this point.
	 * @param Other other given point.
	 * @param radius a given radius in Km.
	 * @return returns true if it's within the radius, false if not.
	 */
	public boolean pointInCircle(LocPoint Other, double radius) {
		return distance(this.Lat, Other.Lat, this.Lon, Other.Lon) <= radius;
	}
/**
 * This function calculate the distance between two points.
 * Taken from: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
 * @param Lat1 this point's latitude.
 * @param Lat2 other point's latitude.
 * @param Lon1 this point's longitude.
 * @param Lon2 other point's longitude.
 * @return the distance between the two points.
 */
	public static double distance(double Lat1, double Lat2, double Lon1, double Lon2) {
		final int R = 6371; // Radius of the earth

		double latDistance = Lat2 - Lat1;
		double lonDistance = Lon2 - Lon1;
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Lat1) * Math.cos(Lat2) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters
		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}

}
