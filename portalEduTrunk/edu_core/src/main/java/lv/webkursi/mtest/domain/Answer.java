package lv.webkursi.mtest.domain;

public class Answer {
	protected Question question;
	
	protected String value;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
