package lv.webkursi.mtest.lab02.data;

public class QuestionTypeForm {
	protected String label;

	protected String instruction;

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

	public QuestionTypeForm() {
		instruction = "[Write a short, generic instruction how to answer the questions of given type]";
	}

}
