package lv.webkursi.mtest.core.data;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.FactoryBean;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * A Spring factory bean that returns the Jena model contained in a file.
 * 
 */
public class FileModelFactory implements FactoryBean {
	protected String baseDir = "";

	protected String fileName = null;

	protected ServletContext servletContext = null;

	protected Model model = null;

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public boolean isSingleton() {
		return true;
	}

	public Class getObjectType() {
		return Model.class;
	}

	public Object getObject() {
		if (model != null) {
			return model;
		}
		if (servletContext != null) {
			model = ModelFactory.createFileModelMaker(
					servletContext.getRealPath("") + "/" + baseDir).openModel(
					fileName, false);
		} else {
			model = ModelFactory.createFileModelMaker(baseDir).openModel(
					fileName, false);
		}

		return model;
	}
}
