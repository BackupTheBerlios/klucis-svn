package lv.webkursi.mtest.core.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A read-only map providing iterator capabilities, e.g. for scoped filters
 * 
 * @param <K> keys 
 * @param <V>
 */
public class CompositeMap<K, V> extends AbstractMap<K, V> {

	private List<Map<K, V>> childMaps;

	public List<Map<K, V>> getChildMaps() {
		return childMaps;
	}
	
	/**
	 * 
	 * @param childMaps participant maps with a narrowing scope and increasing priority 
	 */
	public void setChildMaps(List<Map<K, V>> childMaps) {
		List<Map<K, V>> temp = new ArrayList<Map<K, V>>();
		for (Map<K, V> child: childMaps) {
			if (child != null) {
				temp.add(child);
			}
			else {
				temp.add(new HashMap<K,V>());
			}
		}		
		this.childMaps = temp;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		
		return new AbstractSet<Map.Entry<K, V>>() {
			private int size = -1;
			private int hashCode = childMaps.hashCode();

			@Override
			public Iterator<Map.Entry<K, V>> iterator() {
				
				return new Iterator<Map.Entry<K, V>>() {
					private int mapCount = -1;
					private Iterator<Map.Entry<K, V>> currentIterator = null;

					public boolean hasNext() {
						if (currentIterator != null && currentIterator.hasNext()) {
							return true;
						}
						else if (mapCount < childMaps.size()-1) {
							mapCount++;
							currentIterator = childMaps.get(mapCount).entrySet().iterator();
							return hasNext(); // recursive call
						}
						else {
							return false;
						}
					}

					public Map.Entry<K, V> next() {
						if (!hasNext()) {
							throw new NoSuchElementException();
						}
						Map.Entry<K, V> candidate = currentIterator.next();
						K candidateKey = candidate.getKey();
						for (int i = mapCount+1; i < childMaps.size(); i++) {
							if (childMaps.get(i).containsKey(candidateKey)) {
								// skip this candidate
								return next();
							}
						}
						return candidate;
					}

					public void remove() {
						throw new UnsupportedOperationException();						
					}					
				};
			}

			@Override
			public int size() {
				// not properly initialized; go through iterator
				if (size == -1 || hashCode != childMaps.hashCode()) {
					size = 0;
					for (Iterator<Map.Entry<K, V>> i = iterator(); i.hasNext(); i.next()) {
						size++;
					}
				}
				return size;
			}			
		};
	}	
}
