package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocPointTest {

	@Test
	public void testLocPoint() {
		
		
	}

	@Test
	public void testPointInCircle() {
		LocPoint A=new LocPoint("3.2","3.1", "3.4");
		LocPoint B=new LocPoint("4.2","2.3","4.3");

		boolean result=A.pointInCircle(B,4);
		assertEquals(false,result);
		

		
		
	}

	@Test
	public void testDistance() {
		LocPoint A=new LocPoint();
		
		double result=A.distance(3.2,3.1,3,4);
		assertEquals(6399842.974597481,result,0.01);
		
	}

}
