package lv.webkursi.mtest.domain;

/**
 * For now assume that object equality is not affected
 * by their database ids. 
 * @author kap
 *
 */
public class Utils {
	public static boolean equalId(Long id1, Long id2) {
		return true;
		// return (id1 == null) || (id2 == null) || (id1.equals(id2));
	}
	
	/**
	 * Just for debugging - a helper to log domain object values with JSON 
	 * (http://www.json.org/)
	 * 
	 * @param params an array of parameter names
	 * @param vals an equal length array of their values
	 * @return comma-separated list of quoted paramNames followed by colon(:) and
	 * a quoted value (or another JSON list in curly braces or an array in []).
	 * @throws ArrayIndexOutOfBoundsException if arrays are of different length
	 */
	public static String jsonParamList(String[] params, Object[] vals) {
		if (params == null || params.length == 0) {
			return "null";
		}
		else if (params.length == 1) {
			return "\"" + params[0] + "\":\"" + jsonEncodeAndEscape(vals[0]) + "\""; 
		}
		else {
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < params.length; i++) {
				result.append("\"");
				result.append(params[i]);
				result.append("\":");
				result.append(jsonEncodeAndEscape(vals[i]));
				if (i < params.length-1) {
					result.append(",");
				}
			}
			return result.toString();
		}
	}

	/**
	 * Return string representation, if val is number or date, 
	 * escape special characters, if val is a String, 
	 * transform it into a jsonParamList or an array, if it is a
	 * complex object
	 *  
	 * @param val
	 * @return the trivial implementation without any escapes; would
	 * need to update it.
	 */
	public static String jsonEncodeAndEscape(Object val) {
		if (val == null) {
			return "null";
		}
		else if (val instanceof JsonSerializable) {			
			return "{" + jsonParamList(((JsonSerializable)val).getParamList(),
					((JsonSerializable)val).getValueList()) + "}";
		}
		else if (val instanceof Number) {
			return val.toString();
		}
		else {
			return "\"" + val.toString() + "\"";
		}
	}

}
