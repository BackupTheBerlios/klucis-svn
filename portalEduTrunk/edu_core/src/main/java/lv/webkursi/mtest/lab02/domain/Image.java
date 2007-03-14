package lv.webkursi.mtest.lab02.domain;

import java.io.InputStream;

/**
 * Image (e.g. vector or raster graphics resource), which is refered by some
 * Question or Variant
 * 
 * @author kap
 */
public class Image extends ContentItem {

	protected InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
