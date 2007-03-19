package lv.webkursi.mtest.lab02.domain;

import java.io.InputStream;

/**
 * Image (e.g. vector or raster graphics resource), which is refered by some
 * Question or Variant
 * 
 * @author kap
 */
public class Image extends ContentItem {
	
	/**
	 * A name for an image. Is unique within any given module. 
	 * Each image can belong to one module only (see Module.images). 
	 */
	protected String name;

	/**
	 * Input stream to read from a database and also to record an
	 * image to a database. 
	 */
	protected InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Images with identical names are considered equal. 
	 * This is acceptable, iff all the data structures of 
	 * images are built only within a single module. 
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Image) {
			return name.equals(((Image) o).getName());
		} else {
			return false;
		}
	}

	/**
	 * hashCode() matching equals().
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
