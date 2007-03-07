package lv.webkursi.mtest.core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

/**
 * Helper object to display composite trees of ModelAndView-s
 * 
 */
public class RenderContext {

    protected final Logger logger = Logger.getLogger(getClass());
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ViewResolver viewResolver;
    
    public RenderContext(HttpServletRequest request, 
                  HttpServletResponse response, 
                  ViewResolver viewResolver) {
        this.request = request;
        this.response = response;
        this.viewResolver = viewResolver;
    }
    
    @SuppressWarnings("unchecked")
	public void render(ModelAndView mv) throws Exception {
        // view has to be initialized explicitly, since this does not 
        // go through the normal Spring MVC rendering loop
        String viewName = mv.getViewName();
        if (mv.getView() == null) {
            mv.setView(viewResolver.resolveViewName(viewName, null));//@@TODO fix locale            
        }
        
        mv.getModel().put("_renderContext", this);
        mv.getView().render(mv.getModel(), request,  response);
    }

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public ViewResolver getViewResolver() {
		return viewResolver;
	}    
}
