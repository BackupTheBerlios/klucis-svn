package lv.webkursi.mtest.core.mvc;


public interface ServiceFactoryAware {
	public void setServiceFactory(ServiceFactory serviceFactory);
	public ServiceFactory getServiceFactory();
	
//	public HttpServletRequest getRequest();
//	public HttpServletResponse getResponse();
}
