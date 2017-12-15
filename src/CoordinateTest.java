package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testLocPoint() {
		
		
	}

	@Test
	public void testPointInCircle() {
		Coordinate A=new Coordinate("3.2","3.1", "3.4");
		Coordinate B=new Coordinate("4.2","2.3","4.3");

		boolean result=A.pointInCircle(B,4);
		assertEquals(false,result);
		

		
		
	}

	@Test
	public void testDistance() {
		Coordinate A=new Coordinate();
		
		double result=A.distance(3.2,3.1,3,4);
		assertEquals(6399842.974597481,result,0.01);
		
	}

}
