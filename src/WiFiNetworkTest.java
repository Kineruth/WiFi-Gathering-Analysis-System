package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class WiFiNetworkTest {

	

	@Test
	public void testWiFiNetworkWiFiNetwork() {
		WiFiNetwork A=new WiFiNetwork("MrsKinert","24:79:2a:2c:3d:6c","5520","32");
		String result=A.getSSID()+A.getMAC()+A.getFrecuency()+A.getSignal();
		assertEquals("MrsKinert24:79:2a:2c:3d:6c552032",result);
	}

	@Test
	public void testCompareTo() {
		WiFiNetwork A=new WiFiNetwork("pelephon","ec:8c:a2:08:78:c8","5142","44");
		String result=A.getSignal();
		assertEquals("44",result);
		
	}

//	@Test
//	public void testConvertToFrequency() {
//		WiFiNetwork F=new WiFiNetwork();
//		String result=F.convertToFrequency(44);
//		assertEquals("5220",result);
//	}

}
