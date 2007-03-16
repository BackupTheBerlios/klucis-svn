package lv.webkursi.mtest.core.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class UserServiceDBTest {
	private UserService userService;
	
	private InitUtility initUtility;
	
	@Before
	public void setUp() throws Exception {
		DBModelFactory factory = new DBModelFactory();
		userService = new UserService();
		userService.setModel((Model) factory.getObject());
		initUtility = new InitUtility();
		initUtility.setFactory(new DBModelFactory());
		initUtility.initModel();
	}
	
	@Test
	public void testAddDuplicateUser() {
		UserForm form = new UserForm();
		form.setEmail("admin@admin.com");
		form.setUserName("admin");
		form.setName("Admin");
		form.setPassword("admin");
		form.setPassword2("admin");
		try {
			userService.addUser(form);
			fail("Should not add the second 'admin' user!");
		}
		catch (IllegalArgumentException e) {			
		}
	}
	
	@Test
	public void testAddAnotherUser() throws Exception {
		UserForm form = new UserForm();
		form.setEmail("x@x.x");
		form.setUserName("x");
		form.setName("Xxx");
		form.setPassword("xxx");
		form.setPassword2("xxx");
		userService.addUser(form);
		User some = userService.getUserByName(initUtility.getModel(), "x");
		assertEquals("xxx",some.getPassword());
	}
}
