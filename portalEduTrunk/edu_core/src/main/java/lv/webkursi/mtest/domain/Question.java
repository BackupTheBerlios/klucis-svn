package lv.webkursi.mtest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * There can be non-attached (orphan) questions, but most of them 
 * will be part of modules, and will be created via Module.createQuestion()
 * One question can belong to one module only, but there
 * is no association from Question to Module. 
 * 
 * @author kap
 *
 */
public class Question extends ContentItem implements JsonSerializable {
	protected QuestionType questionType;

	protected List<Variant> variants = new ArrayList<Variant>();

	public Variant createVariant(String label) {
		Variant variant = new Variant();
		variant.setName(getName() + "_" + label);
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
