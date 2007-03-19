package lv.webkursi.mtest.lab02.domain;

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
	
	/**
	 * true, if this variant is considered correct. 
	 * For certain types of questions (find M correct answers out of N)
	 * there could be several correct answer variants, and 
	 * the user has to find the correct combination. 
	 */
	protected boolean correct;
	
	protected String label;	

	public String[] getParamList() {
		return new String[] { "id", "description", "correct"};
	}

	public Object[] getValueList() {
		return new Object[] { id, description, correct};
	}


	public String toString() {
		return "{\"Variant\":{"
				+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
