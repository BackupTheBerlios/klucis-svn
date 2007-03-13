package lv.webkursi.mtest.domain;

/**
 * Answer variant within a Question. 
 * This has the assembly type relationship with a question. 
 * (When deleting a Question, there is always a cascaded
 * delete of its variants). 
 * 
 * @author kap
 * 
 */
public class Variant extends ContentItem implements JsonSerializable {

	public String[] getParamList() {
		return new String[] { "id", "name", "description"};
	}

	public Object[] getValueList() {
		return new Object[] { id, name, description};
	}


	public String toString() {
		return "{\"Variant\":{"
				+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";
	}
}
