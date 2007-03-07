package lv.webkursi.mtest.core.intercepting;

import lv.webkursi.mtest.core.util.UnitTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class MaintenanceServiceTest {
	
	private ApplicationContext ctx;
	
	IMaintenanceService service;
	
	@Before
	public void setUp() {
		ctx = new FileSystemXmlApplicationContext(
				UnitTestUtils.getContextFilePath()
//				"../portalTest-ex03/webapp/WEB-INF/portalTest-data.xml"
				);
		service  = (IMaintenanceService)ctx.getBean("maintenanceServiceBean");
	}

	@Test
	public void testF() {
		service.f();
	}	
}
