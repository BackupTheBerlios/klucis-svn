package lv.webkursi.mtest.core.mvc.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is needed to encode all parameters into UTF-8. SupressWarnings helps
 * to avoid warnings caused by a deprecated method in the javax.servlet.ServletRequest class. 
 */
@SuppressWarnings("deprecation") 
public final class CharsetRequestWrapper extends HttpServletRequestWrapper {
	
	private Log log = LogFactory.getLog(getClass());
	
	public CharsetRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	
	@Override
	public String[] getParameterValues(String parameter) {
		return normalizeValues(parameter,super.getParameterValues(parameter));
	}
	
	@Override
	public String getParameter(String parameter) {
		String result = super.getParameter(parameter);
		String encResult = normalizeValue(parameter,result);
		return encResult;		
	}
	
	@Override
	public Map getParameterMap() {
		Map oldMap = super.getParameterMap();
		Map<String,String[]> newMap = new HashMap<String,String[]>();
		for (Iterator i = oldMap.keySet().iterator(); i.hasNext(); ) {
			String paramName = (String)i.next();
			String[] paramValue = (String[])oldMap.get(paramName);
			newMap.put(paramName, normalizeValues(paramName,paramValue));
		}
		return newMap;
	}
	
	private String normalizeValue(String parameter, String value) {
		if (value == null) { 
			return null; 
		}
		String encValue = value.trim();			
		try {
			encValue = new String(encValue.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Could not encode parameter '" + parameter + "' in UTF-8, "
					+ "returning original value '" + encValue+ "'",e);
		}
		return encValue;	
	}
	
	private String[] normalizeValues(String parameter, String[] values) {
//		log.info("Values is " + Arrays.asList(values));
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] results = new String[count];
		for (int i = 0; i < count; i++) {			
			results[i] = normalizeValue(parameter,values[i]);
		}
//		log.info("Results is " + Arrays.asList(results));
		return results;		
	}
}
