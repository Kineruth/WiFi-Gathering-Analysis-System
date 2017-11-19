

import java.util.ArrayList;

public class CommonNets {

	private ArrayList<WifiNetwork> common;

	public CommonNets(){
		this.common = new ArrayList<WifiNetwork>();
	}
	public void add(WifiNetwork net) {
		this.common.add(net); 
	}

	public ArrayList<WifiNetwork> getCommonNets() {
		return this.common;
	}

	public boolean isEmpty() {
		return this.common.isEmpty();
	}

	public int size() {
		return this.common.size();
	}

	public WifiNetwork get(int i) {
		return this.common.get(i);
	}

	public void sort(Object object) {
		this.common.sort(null);

	}
	public UnitedNets sortListNet() {
		if (this.common.isEmpty())
			return null;
		boolean flag = false;
		UnitedNets ans = new UnitedNets();
		CommonNets temp = new CommonNets();

		String time = this.common.get(0).getTime();
		String lat = this.common.get(0).getLAT();
		String lon = this.common.get(0).getLON();
		String alt = this.common.get(0).getALT();
		temp.add(this.common.get(0));

		for (int i = 1; i < this.common.size(); i++) {
			if (checkIfEqual(i, time, lat, lon, alt)) {
				temp.add(this.common.get(i));
			} else {
				ans.add(temp);
				temp = new CommonNets();
				time = this.common.get(i).getTime();
				lat = this.common.get(i).getLAT();
				lon = this.common.get(i).getLON();
				alt = this.common.get(i).getALT();
				temp.add(this.common.get(i));
				flag = true;
			}
		}
		if (flag) {
			ans.add(temp);
		}
		ans = ans.sortBySignal();
		return ans;
	}

	public boolean checkIfEqual(int i, String time, String lat, String lon, String alt) {
		return compTime(i, time) && compLAT(i, lat) && compLON(i, lon) && compALT(i, alt);
	}

	// ***************************PRIVATE*****************************

	private boolean compTime(int i, String time) {
		return this.common.get(i).getTime().equals(time);
	}

	private boolean compLAT(int i, String lat) {
		return this.common.get(i).getLAT().equals(lat);
	}

	private boolean compLON(int i, String lon) {
		return this.common.get(i).getLON().equals(lon);
	}

	private boolean compALT(int i, String alt) {
		return this.common.get(i).getALT().equals(alt);
	}

}
