package src;

import java.util.Comparator;

public class PI_Comparator implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2){
		Sample s1 = (Sample) obj1;
		Sample s2 = (Sample) obj2;
		return (s1.getPI() + "").compareTo((s2.getPI() + ""));
	}
}
