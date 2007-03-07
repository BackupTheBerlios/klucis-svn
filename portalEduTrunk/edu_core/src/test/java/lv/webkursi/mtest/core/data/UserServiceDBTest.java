package lv.webkursi.mtest.core.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserServiceDBTest {
	private UserService userService;
	
	@Before
	public void setUp() {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				"edu_core/src/test/resources/testcontext.xml");
		userService = (UserService) ctx.getBean("userService");
		InitUtility.initModel();
	}
	
	@Ignore
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
	
	@Ignore
	@Test
	public void testAddAnotherUser() {
		UserForm form = new UserForm();
		form.setEmail("x@x.x");
		form.setUserName("x");
		form.setName("Xxx");
		form.setPassword("xxx");
		form.setPassword2("xxx");
		userService.addUser(form);
		User some = userService.getUserByName(InitUtility.getModel(), "x");
		assertEquals("xxx",some.getPassword());
	}
}
