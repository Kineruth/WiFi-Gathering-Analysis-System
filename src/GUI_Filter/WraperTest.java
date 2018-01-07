package GUI_Filter;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class WraperTest {

//	@Test
//	public void testFolderAdded() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testMergedFileAdded() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSaveMergedCSV() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSaveAsKML() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testClearance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetMacNumber() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testWriteCurrentFilter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReadFilterFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateAlgo1() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateAlgo2() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertToSample() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertMacsToSample() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testCheckDateMinMax() {
		Date max = new Date(2017, 12, 3, 8, 50);
		Date min = new Date(2017, 12, 3, 7, 10);
		boolean result = true;
		assertEquals(result,Wraper.checkDateMinMax(max, min));

	}

}
