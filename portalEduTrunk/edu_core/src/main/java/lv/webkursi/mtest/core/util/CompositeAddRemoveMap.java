package lv.webkursi.mtest.core.util;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class receives list of addmaps and removesets. Specifically designed to
 * compute the second cascade (i.e. the various scopes successively add new
 * facetstates and then remove some facetstates). The order of additions and
 * removals is controlled by the natural order of the elements in the list
 * "childMapsAndSets". If the current item in the list is a map, then its
 * elements are added to the composite result, but if it is a set, then its
 * elements are removed from the set.
 * 
 * @param <K>
 * @param <V>
 */
public class CompositeAddRemoveMap<K, V> extends AbstractMap {

	private List<Object> childMapsAndSets;

	public List<Object> getChildMapsAndSets() {
		return childMapsAndSets;
	}

	public void setChildMapsAndSets(List<Object> childMapsAndSets) {
		this.childMapsAndSets = childMapsAndSets;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
