package lv.webkursi.mtest.lab02.domain;

/**
 * A classifier, which allows to store some small number of various
 * multiple-choice question types with their respective instructions. E.g.
 * "Select one correct answer", "Select 3 correct answers", "Write in a single
 * case-sensitive word, not terminated by any punctuation", etc.
 * 
 * @author kap
 * 
 */
public class QuestionType implements JsonSerializable {

	protected Long id;
	
	/**
	 * A short, unique label for the question type (e.g. "Select 1 of N"). 
	 */
	protected String label;

	/**
	 * An explanation how to answer the question type (e.g. 
	 * "Select one correct answer"
	 */
	protected String instruction;
	
	public QuestionType() {		
	}
	
	public QuestionType(String label, String instruction) {
		this.label = label;
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
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof QuestionType) {
			QuestionType qt = (QuestionType)o;
			return qt.instruction.equals(instruction) && qt.label.equals(label);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return instruction.hashCode() ^ label.hashCode();
	}

	public String[] getParamList() {
		return new String[] { "id", "label", "instruction" };
	}

	public Object[] getValueList() {
		return new Object[] { id, label, instruction };
	}	
	
	/**
	 * String representation in the JSON notation; just for debug messages
	 */
	@Override
	public String toString() {
		return "{\"QuestionType\":{"
		+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";
	}
}
