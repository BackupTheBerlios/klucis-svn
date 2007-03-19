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
	

	/**
	 * Just compare the "name" attribute, since they are unique
	 */
	/*
	@Override
	public boolean equals(Object o) {
		if (o instanceof ContentItem) {
			return name.equals(((ContentItem) o).getName());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	*/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
