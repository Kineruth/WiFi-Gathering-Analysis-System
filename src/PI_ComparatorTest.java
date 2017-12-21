package src;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class PI_ComparatorTest {

	@Test
	public void test() {
		
		Sample sam1= new Sample ("LG","03-12-2017 8:37:10 AM","32","34","13243",32.5);//first
		Sample sam2= new Sample ("LG","03-12-2017 8:37:10 AM","32","34","13243",42.5);//second
		ArrayList<Sample> s1= new ArrayList<Sample>();
		s1.add(sam2);
		s1.add(sam1);
		Collections.sort( s1, new PI_Comparator()); // sort by IP
		ArrayList<Sample> result= new ArrayList<Sample>();
		result.add(sam1);
		result.add(sam2);
		assertEquals(result,s1);
	}

}
