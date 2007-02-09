package lv.webkursi.klucis.mvc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

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
 * @author kap
 */
public class VelocityMerge {

	private VelocityContext context = new VelocityContext();

	private String templateName;

	private String prefix = "src/main/resources/";

	private String suffix = ".vm";

	private Log log = LogFactory.getLog(VelocityMerge.class);

	public VelocityMerge() {
		try {			
			Velocity.init();
		} catch (Exception e) {
			log.error("VelocityView not initialized.", e);
		}
	}

	public void setContextParams(Map<String, ? extends Object> params) {
		for (String key : params.keySet()) {
			context.put(key, params.get(key));
		}
		context.put("math", new MathTool());
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String render() {
		StringWriter result = new StringWriter();
		Template template = null;
		try {
			template = Velocity.getTemplate(prefix + templateName + suffix);
			template.merge(context, result);
		} catch (ResourceNotFoundException e) {
			log.error("Could not find file " + prefix + templateName + suffix,
					e);
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
			Template template = Velocity.getTemplate(prefix + templateName
					+ suffix);
			template.merge(context, fw);
		} catch (ResourceNotFoundException e) {
			log.error("Could not find file " + prefix + templateName + suffix,
					e);
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
