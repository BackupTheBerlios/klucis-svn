package lv.webkursi.mtest.core.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class provides a controlled access to some hard-coded services in 
 * portalCore (e.g. authorization, customization, view resolving, etc.). 
 * The singleton ServiceFactory object is injected in all controllers
 * and possibly other MVC layer classes as well. 
 * Other, custom services should be configured to be accessible through 
 * BeanFactoryFactory - a class, which provides access to arbitrarily 
 * configurable factories producing all kinds of components.   
 * 
 *
 */
public class ServiceFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * Finds a service in ApplicationContext by its name from some predefined/enumerated
	 * vocabulary.  
	 * @param serviceName name of the service from some enumeration
	 * @return service itself from the Spring's config file. 
	 */
	public Object getService(ServiceName serviceName) {
		return applicationContext.getBean(ServiceName.getBeanName(serviceName));
	}

}
