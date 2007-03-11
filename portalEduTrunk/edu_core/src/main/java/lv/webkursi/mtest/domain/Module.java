package lv.webkursi.mtest.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Module extends ContentItem {
	/**
	 * Size up to 255 bytes. May contain plaintext in Unicode
	 */
	protected String title;

	protected List<Question> questions = new ArrayList<Question>();
	
	protected Set<Image> images = new HashSet<Image>();
	
	public Question createQuestion() {
		Question question = new Question();
		// assign some unique name
		question.setName("q_" + (questions.size() + 1));
		
		questions.add(question);
		return question;
	}
	
	public Image createImage() {
		Image image = new Image();
		image.setName("img_" + (images.size() + 1));
		images.add(image);
		return image;
	}
	
	public void removeImage(Image i) {
		images.remove(i);
	}
	
	public void removeQuestion(Question q) {
		questions.remove(q);
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
}
