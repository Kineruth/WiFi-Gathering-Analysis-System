package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class CalculateTest {

	@Test
	public void testModifyPIAlgo1() {
		fail("Not yet implemented");
	}

	@Test
	public void testSort_RemoveWiFiNetworks() {
		Calculate c = new Calculate();
		Sample sam1= new Sample ("LG","03-12-2017 8:37:10 AM","32","34","13243",32.5);//first
		Sample sam2= new Sample ("LG","03-12-2017 8:37:10 AM","32","34","13243",42.5);//second
		ArrayList<Sample> s1= new ArrayList<Sample>();
		s1.add(sam2);
		s1.add(sam1);
		
		ArrayList<Sample> result= new ArrayList<Sample>();
		result.add(sam1);
		
		c.sort_RemoveWiFiNetworks(s1, 1);
		
		assertEquals(result,s1);
	}

	@Test
	public void testSetCoordinatesAlgo1() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcCoordinate() {
		Calculate c = new Calculate();
		WiFiNetwork wn=new WiFiNetwork("ariel", "f:c:3", "100", "-70");
		WiFiNetwork wn2=new WiFiNetwork("ariel", "f:c:3", "100", "-50");
		Sample sam1= new Sample ("LG","03-12-2017 8:37:10 AM","2","4","8",32.5);//first
		Sample sam2= new Sample ("LG","03-12-2017 8:37:10 AM","3","6","13",42.5);//second
		sam1.addNetwork(wn);
		sam2.addNetwork(wn2);
		ArrayList<Sample> s1= new ArrayList<Sample>();
		s1.add(sam2);
		s1.add(sam1);
		Coordinate point = c.calcCoordinate(s1);
		
		Coordinate result = new Coordinate("2.662162162","5.324324324","11.31081081");
		assertEquals(result.getLat(),point.getLat(),0.1);
		assertEquals(result.getLon(),point.getLon(),0.2);
		assertEquals(result.getAlt(),point.getAlt(),0.5);
	}

	@Test
	public void testModifyPI() {
		fail("Not yet implemented");
	}

}
