package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import Algorithms.Calculate;
import KML.Coordinate;
import MergedCSV.Sample;
import MergedCSV.WiFiNetwork;

public class CalculateTest {

	@Test
	public void testModifyPIAlgo1() {
	
	}

	@Test
	public void testSort_RemoveWiFiNetworks() {
		Calculate c = new Calculate();
		Sample sam1= new Sample ("LG","29/10/2017  15:02:43","32","34","13243",32.5);//first
		Sample sam2= new Sample ("LG","29/10/2017  15:02:43","32","34","13243",42.5);//second
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
		Calculate c = new Calculate();
		WiFiNetwork wn=new WiFiNetwork("ariel", "f:c:3", "1", "-70");
		WiFiNetwork wn2=new WiFiNetwork("ariel", "f:c:3", "1", "-50");
		Sample sam1= new Sample ("LG","03-12-2017 8:37:10 AM","2","4","8",32.5);//first
		Sample sam2= new Sample ("LG","03-12-2017 8:37:10 AM","3","6","13",42.5);//second
		sam1.addNetwork(wn);
		sam2.addNetwork(wn2);
		ArrayList<Sample> s1= new ArrayList<Sample>();
		s1.add(sam2);
		s1.add(sam1);
		Coordinate point = c.calcCoordinate(s1);
		Sample result = new Sample();
		result.setALT(point.getAlt()+"");
		result.setLAT(point.getLat()+"");
		result.setLON(point.getLon()+"");
		result.setID("LG");
		result.setPI(42.5);
		result.setTime("03-12-2017 8:37:10 AM");
		result.addNetwork(wn2);
		
		
//		String result = "f:c:3,ariel,2412,-50,"+point.getLat()+","+point.getLon()+","+point.getAlt()+",03-12-2017 8:37:10 AM,Aprrox. w-center Algo1";
		Sample s = c.setCoordinatesAlgo1(s1, "f:c:3");
		assertEquals(result,s);
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
		
	}

}
