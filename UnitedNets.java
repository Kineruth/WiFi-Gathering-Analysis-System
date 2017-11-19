

import java.util.ArrayList;

public class UnitedNets {
	private ArrayList<CommonNets> united;
public UnitedNets(){
	this.united = new ArrayList<CommonNets>();
}
	public void add(CommonNets common) {
		this.united.add(common);
	}

	public int size() {
		return this.united.size();
	}

	public CommonNets get(int i) {
		return this.united.get(i);
	}

	public ArrayList<CommonNets> getUnitedNets() {
		return this.united;
	}

	public UnitedNets sortBySignal() {
		UnitedNets ans = new UnitedNets();
		CommonNets common = new CommonNets();
		for (int i = 0; i < this.united.size(); i++) {
			this.united.get(i).sort(null);
			for (int j = 0; (j < 10) && (j < this.united.get(i).size()); j++) {
				common.add(this.united.get(i).get(j));
			}
			ans.add(common);
			common = new CommonNets();
		}
		return ans;
	}

}
