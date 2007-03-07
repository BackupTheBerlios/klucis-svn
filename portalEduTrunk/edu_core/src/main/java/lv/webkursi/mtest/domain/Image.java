package lv.webkursi.mtest.domain;

import java.io.OutputStream;

/**
 * Image (e.g. vector or raster graphics resource), which is refered by some
 * Question or Variant
 * 
 * @author kap
 */
public class Image extends ContentItem {

	protected String url;

	protected OutputStream os;

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
