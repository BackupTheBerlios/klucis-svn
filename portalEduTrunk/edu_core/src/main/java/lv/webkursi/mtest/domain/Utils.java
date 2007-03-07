package lv.webkursi.mtest.domain;

/**
 * For now assume that object equality is not affected
 * by their database ids. 
 * @author kap
 *
 */
public class Utils {
	public static boolean equalId(Long id1, Long id2) {
		return true;
		// return (id1 == null) || (id2 == null) || (id1.equals(id2));
	}

}
