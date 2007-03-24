package lv.webkursi.mtest.lab02.domain;


/**
 * This represents any piece of testing system content
 * 
 * @author kap
 */
public abstract class ContentItem {
	
	protected Long id;

	/**
	 * Size up to 64K. Normally it is Textile markup, for Images it is just
	 * plain text (e.g. the ALT attribute)
	 */
	protected String description;
	

	// GET/SET methods
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
