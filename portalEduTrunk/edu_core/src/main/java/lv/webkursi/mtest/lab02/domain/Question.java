package lv.webkursi.mtest.lab02.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * There can be non-attached (orphan) questions, but most of them 
 * will be part of modules, and will be created via Module.createQuestion()
 * One question can belong to one module only, but there
 * is no association from Question to Module. 
 * 
 * @author kap
 */
public class Question extends ContentItem implements JsonSerializable {
	protected QuestionType questionType;

	protected List<Variant> variants = new ArrayList<Variant>();

	public Variant createVariant(String label) {
		Variant variant = new Variant();
		variant.setLabel(label);
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
		return new String[] { "id", "description", 
				"questionType" };
	}

	public Object[] getValueList() {
		return new Object[] { id, description, questionType };
	}

	@Override
	public String toString() {
		return "{\"Question\":{"
				+ Utils.jsonParamList(getParamList(), getValueList()) + "}}";
	}
	
	public boolean equals(Object o) {
		if (o instanceof Question) {
			Question q = (Question)o;
			boolean result = true;
			result &= q.description.equals(description);
			result &= q.questionType.equals(questionType);
			return result;
		}
		return false;
	}
	
	public int hashCode() {
		return description.hashCode() ^ questionType.hashCode();
	}

}
