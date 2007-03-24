package lv.webkursi.mtest.core.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.EmptyListException;
import com.hp.hpl.jena.rdf.model.Model;

public class UserServiceTest {

	private UserService userService;

	private Model model;

	private static final String userServiceTestData = MockDescriptionFactory.PREFIXES
			+ "portal:Amos"
			+ "    rdf:type mtest:User ;"
			+ "    mtest:userName \"Amos\" ;"
			+ "    mtest:password \"Amos\" . "
			+ "portal:Ezekiel"
			+ "    rdf:type mtest:User ;"
			+ "    mtest:userName \"Ezekiel\" ;"
			+ "    mtest:password \"Ezekiel\" .";

	@Before
	public void setUp() {
		model = MockDescriptionFactory.getInstance().createModel(
				"userServiceTest", userServiceTestData);
		userService = new UserService();
		userService.setModel(model);

	}

	@Test
	public void testUser1() {
		assertNotNull(userService);
		assertNotNull(model);
		User u1 = userService.getUserByName(model, "Amos");
		assertEquals("Amos", u1.getUserName());
		assertEquals("Amos", u1.getPassword());
	}

	@Test
	public void testUser2() {
		User u1 = userService.getUserByName(model, "Ezekiel");
		assertEquals("Ezekiel", u1.getUserName());
		assertEquals("Ezekiel", u1.getPassword());
	}

	@Test
	public void testEmpty() {
		try {
			userService.getUserByName(model, "");
			fail("Should not find anything");
		} catch (EmptyListException e) {
			// this is expected
		}
	}

	@Test
	public void testNull() {
		try {
			userService.getUserByName(model, null);
			fail("Should not find anything");
		} catch (NullPointerException e) {
			// this is expected
		}
	}

	@Test
	public void testWrong() {
		try {
			userService.getUserByName(model, "BlahBlah");
			fail("Should not find anything");
		} catch (EmptyListException e) {
			// this is expected
		}
	}

}
