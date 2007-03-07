package lv.webkursi.mtest.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends ContentItem {
	protected QuestionType questionType;
	
	protected Set<Variant> variants = new HashSet<Variant>();
		
	public Variant createVariant() {
		Variant variant = new Variant();
		variant.setName("var_" + getName() + "_" + variants.size() + 1);
		variants.add(variant);		
		return variant;		
	}
	
	public void addVariant(Variant var) {
		variants.add(var);
	}
	
	public void removeVariant(Variant var) {
		variants.remove(var);
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Set<Variant> getVariants() {
		return variants;
	}

	public void setVariants(Set<Variant> variants) {
		this.variants = variants;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("{\"Question\":{\"name\":\"");
		result.append(name);
		result.append("\"},\"id\":\"");
		result.append(id);
		result.append("\"},\"description\":\"");
		result.append(description);
		result.append("\"},\"questionType\":\"");
		result.append(questionType);
		result.append("\"}}");
		return result.toString();		
	}
}
