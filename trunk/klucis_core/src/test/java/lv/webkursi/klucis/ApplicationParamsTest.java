package lv.webkursi.klucis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ApplicationParamsTest {
	@Test
	public void arrayConstructor() {
		ApplicationParams params = new ApplicationParams(new String[] {"--p1=v1", "--p2=v2"});
		int count = 0; 
		for (String key: params.getParams().keySet()) {
			// non-system parameter
			if (!key.startsWith("_")) {
				count++;
			}
		}
		assertEquals(2,count);		
		assertEquals("v1", params.getParam("p1"));
		assertEquals("v2", params.getParam("p2"));
		assertNull(params.getParam("p3"));
		
	}
	

}
