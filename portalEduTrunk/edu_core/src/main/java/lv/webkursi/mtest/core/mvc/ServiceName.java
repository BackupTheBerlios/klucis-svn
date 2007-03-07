package lv.webkursi.mtest.core.mvc;

/**
 * A list of hard-coded service names available for all portalCore components.
 * See their implementations in classes with the corresponding names.
 */
public enum ServiceName {
//	ComponentFactoryImpl,  // this should retire 
	ComponentManager, // an implementation of component manager
	FixedController, // the universal controller
	PortalDescription, // the RDF description of portal and its components
	ProxyComponentFactory, // through this all components are initialized
	RequestHolder, // adapter to the State management (scope = request)
	UserService, // can find users by their name and check their passwords
	UserSettings, // session stuff - persistent and non-persistent user data
	ViewResolver; // the viewresolver used by composite view subsystem

	/**
	 * Naming convention for Spring beans - replace the first capital letter of
	 * the service with a lowercase letter
	 * 
	 * @param serviceName
	 * @return
	 */
	public static String getBeanName(ServiceName serviceName) {
		String s = serviceName.name();
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
}
