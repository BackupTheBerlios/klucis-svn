package lv.webkursi.klucis.mvc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.MathTool;

/**
 * This class is responsible for offline (non-Web) Velocity template processing
 * 
 * @author kap
 */
public class VelocityMerge {

	private VelocityContext context = new VelocityContext();

	private String templateName;

	private Log log = LogFactory.getLog(VelocityMerge.class);

	public VelocityMerge() {
		try {
			Properties props = new Properties();
			props.put("resource.loader", "file, class");

			props.put("file.resource.loader.description",
					"Velocity File Resource Loader");
			props
					.put("file.resource.loader.class",
							"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			props.put("file.resource.loader.path", "user_templates");
			props.put("file.resource.loader.cache", "false");
			props.put("file.resource.loader.modificationCheckInterval", "0");

			props.put("class.resource.loader.description",
					"Velocity Classpath Resource Loader");
			props
					.put("class.resource.loader.class",
							"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			props.put("velocimacro.library", "lv/webkursi/klucis/view/marsmacro.vm");
			Velocity.init(props);
		} catch (Exception e) {
			log.error("VelocityView not initialized.", e);
		}
	}

	public void setContextParams(Map<String, ? extends Object> params) {
		for (String key : params.keySet()) {
			context.put(key, params.get(key));
		}
		context.put("math", new MathTool());
		context.put("_renderContext", new OfflineRenderContext());
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String render() {
		StringWriter result = new StringWriter();
		Template template = null;
		try {
			template = Velocity.getTemplate(templateName);
			template.merge(context, result);
		} catch (ResourceNotFoundException e) {
			log.error("Could not find resource '" + templateName + "'", e);
		} catch (ParseErrorException e) {
			log.error("Syntax error in template " + templateName, e);
		} catch (MethodInvocationException e) {
			log.error("Illegal method call in template " + templateName, e);
		} catch (Exception e) {
			log
					.error("Unexpected error processing template "
							+ templateName, e);
		}
		return result.toString();
	}

	public void write(String fileName) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			Template template = Velocity.getTemplate(templateName);
			template.merge(context, fw);
		} catch (ResourceNotFoundException e) {
			log.error("Could not find resource '" + templateName + "'", e);
		} catch (ParseErrorException e) {
			log.error("Syntax error in template " + templateName, e);
		} catch (MethodInvocationException e) {
			log.error("Illegal method call in template " + templateName, e);
		} catch (IOException e) {
			log.error("Illegal method call in template " + templateName, e);
		} catch (Exception e) {
			log
					.error("Unexpected error processing template "
							+ templateName, e);
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				log.error("Unexpected error processing template "
						+ templateName, e);
			}
		}
	}
}
