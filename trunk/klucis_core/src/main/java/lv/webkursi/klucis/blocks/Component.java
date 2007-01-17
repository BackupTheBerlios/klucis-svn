package lv.webkursi.klucis.blocks;

public interface Component {
	public void addObject(String key, Object value);
	
	public String render();
}
