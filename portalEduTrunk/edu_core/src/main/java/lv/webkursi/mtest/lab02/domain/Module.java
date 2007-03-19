package lv.webkursi.mtest.lab02.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Module extends ContentItem {
	
	/**
	 * Name (globally unique; module names are simple, other item names contain
	 * two parts - module and item, separated with a dot (.). Legal characters
	 * within parts are upper/lower case Latin letters, digits and underscores.
	 * Must NOT be null for any initialized ContentItem and does not change
	 * over the lifetime 
	 */
	protected String name;

	/**
	 * Size up to 255 bytes. May contain plaintext in Unicode
	 */
	protected String title;

	protected List<Question> questions = new ArrayList<Question>();
	
	protected Set<Image> images = new HashSet<Image>();
	
	public Question createQuestion() {
		Question question = new Question();
		questions.add(question);
		return question;
	}
	
	public boolean removeQuestion(Question q) {
		return questions.remove(q);
	}
		
	public Image createImage(String name) {
		Image image = new Image();
		image.setName(name);		
		images.add(image);
		return image;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Module) {
			Module m = (Module)o;
			boolean result = true;
			result &= m.name.equals(name);
			result &= m.title.equals(title);
			result &= m.description.equals(description);
			return result;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() ^ title.hashCode() ^ description.hashCode();
	}
	
	
}
