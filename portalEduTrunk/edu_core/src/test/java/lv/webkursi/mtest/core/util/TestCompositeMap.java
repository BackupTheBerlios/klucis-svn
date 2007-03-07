package lv.webkursi.mtest.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class TestCompositeMap {
	private CompositeMap<String, String> theMap;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		theMap = new CompositeMap<String, String>();
		Map<String, String> child1 = new TreeMap<String, String>();
		child1.put("a", "A1"); // shadowed by child3
		child1.put("b", "B1"); // shadowed by child2
		child1.put("c", "C1"); // shadowed by child3
		child1.put("d", "D1");
		Map<String, String> child2 = new TreeMap<String, String>();
		child2.put("a", "A2"); // shadowed by child3
		child2.put("b", "B2");
		child2.put("e", "E2"); // shadowed by child3
		child2.put("f", "F2");
		Map<String, String> child3 = new TreeMap<String, String>();
		child3.put("a", "A3");
		child3.put("c", "C3");
		child3.put("e", "E3");
		theMap.setChildMaps(Arrays.asList(child1, child2, child3));
		child3.put("g", "G3");
	}

	@Test
	public void testSize() {
		assertEquals(7, theMap.size());
	}

	@Test
	public void testIterator() {
//		Set<String> keys = theMap.keySet();
		Set<String> values = new HashSet<String>(theMap.values());
		Set<String> expectedValues = new HashSet<String>(Arrays.asList("D1",
				"B2", "F2", "A3", "C3", "E3", "G3"));
		assertTrue(values.equals(expectedValues));

	}
	
	@Test
	public void testPut() {
		try {
			theMap.put("x", "X");
			fail("The composite map should not support insertions");
		}
		catch (UnsupportedOperationException e) {}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNullChild() {
		List childMaps = new ArrayList(); 
		childMaps.addAll(theMap.getChildMaps());
		childMaps.add(null);
		theMap.setChildMaps(childMaps);		
		assertEquals(7, theMap.size());
	}
}
