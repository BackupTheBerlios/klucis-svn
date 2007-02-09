package lv.webkursi.klucis.data;

import java.io.File;

import org.springframework.beans.factory.FactoryBean;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Used by Spring configuration to create RDF Model from its configured path and 
 * filename. 
 *  
 * @author kap
 */
public class FileModelFactory implements FactoryBean {
    
	protected String baseDir = ".";
    
    protected String path = "/";

	protected String fileName = null;
    
	protected Model model = null;
    
    public String getBaseDir() {
        return baseDir;
    }

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public String getFileName() {
        return fileName;
    }

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		
		File f = new File(baseDir + path + fileName);
		if (!f.exists()) {			
			throw new RuntimeException("File '" + baseDir + path + fileName + "' does not exist");
		}
        model = ModelFactory.createFileModelMaker(
					baseDir + path).openModel(
					fileName, false);
		return model;
	}
}
