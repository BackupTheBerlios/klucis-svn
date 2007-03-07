package lv.webkursi.mtest.core.mvc.rule;

/**
 *
 */
public class IntegerCompare implements Predicate {
    private Port x;
    private Port y;
    
    public boolean isTrue() {
        return x.getInt() < y.getInt();
    }

}
