package lv.webkursi.mtest.domain;

/**
 * A classifier, which allows to store some small number of various
 * multiple-choice question types with their respective instructions. E.g.
 * "Select one correct answer", "Select 3 correct answers", "Write in a single
 * case-sensitive word, not terminated by any punctuation", etc.
 * 
 * @author kap
 * 
 */
public class QuestionType {

	protected Long id;

	protected String instruction;
	
	public QuestionType() {		
	}
	
	public QuestionType(String instruction) {
		this.instruction = instruction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof QuestionType)) {
			return false;
		}
		else {
			boolean result = true; 
			QuestionType qt = (QuestionType)o;
			result &= instruction.equals(qt.instruction);
			result &= Utils.equalId(qt.id, id); 			
			return result;
		}
	}
	
	@Override
	public int hashCode() {
		return instruction.hashCode();
	}
	
	/**
	 * String representation in the JSON notation; just for debug messages
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("{\"QuestionType\":{");
		result.append("\"instruction\":\"");
		result.append(instruction);
		result.append("\"},{\"id\":\"");
		result.append(id);
		result.append("\"}}");
		return result.toString();
	}
}
