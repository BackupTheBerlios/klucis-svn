package lv.webkursi.klucis.web;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.FactoryBean;

/**
 * Return the base directory given a <code>ServletContext</code>.
 */
public class ServletBaseDirFactory implements FactoryBean {
    protected String baseDir = "";

    protected ServletContext servletContext = null; 

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public boolean isSingleton() {
        return true;
    }

    public Class getObjectType() {
        return String.class;
    }

    public Object getObject() {
        return servletContext.getRealPath("");
    }
}
