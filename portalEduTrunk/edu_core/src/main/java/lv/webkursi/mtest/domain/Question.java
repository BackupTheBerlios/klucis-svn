package lv.webkursi.mtest.domain;

import java.util.ArrayList;
import java.util.List;

public class Question extends ContentItem implements JsonSerializable {
	protected QuestionType questionType;

	protected List<Variant> variants = new ArrayList<Variant>();

	public Variant createVariant() {
		Variant variant = new Variant();
		variant.setName("var_" + getName() + "_" + variants.size() + 1);
		variants.add(variant);
		return variant;
	}

	public boolean removeVariant(Variant var) {
		return variants.remove(var);
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	
	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}


	public String[] getParamList() {
		return new String[] { "id", "name", "description", 
				"questionType" };
	}

	public Object[] getValueList() {
		return new Object[] { id, name, description, questionType };
	}

	public String toString() {
		return "{\"Question\":{"
				+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";
	}

}
