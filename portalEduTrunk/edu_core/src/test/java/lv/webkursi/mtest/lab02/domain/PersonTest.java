package lv.webkursi.mtest.lab02.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	Person p1; 
	
	Person p2;
	
	@Before
	public void setUp() {
		p1 = new Person();
		p1.setEmail("email");
		p1.setFirstName("firstname");
		p1.setLastName("lastname");
		p1.setLogin("login");
		p1.setPassword("password");
	}
	
	@Test
	public void equals1() {
		p2 = new Person();
		p2.setEmail("email");
		p2.setFirstName("firstname");
		p2.setLastName("lastname");
		p2.setLogin("login");
		p2.setPassword("password2");
		p2.setModules(new HashSet<Module>());
		assertEquals(p2,p1);
		assertNotSame(p2,p1);
		assertEquals(p2.hashCode(),p1.hashCode());
	}
	
	@Test
	public void equals2() {
		p2 = new Person();
		p2.setEmail("email");
		p2.setFirstName("firstname");
		p2.setLastName("lastname");
		assertTrue(!p2.equals(p1));
	}
	
	

}
