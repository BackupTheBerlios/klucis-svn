package lv.webkursi.mtest.core.mvc.rule;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class RuleList implements RuleCollection {
    List rules;

    /* (non-Javadoc)
     * @see com.hp.hpl.mars.portal.core.mvc.rule.RuleCollection#ApplyRules()
     */
    public void ApplyRules() {
        Iterator iter = rules.iterator();
        while (iter.hasNext()) {
            ((Rule)iter.next()).run();
        }        
    }

    /**
     * @param rules The rules to set.
     */
    public void setRules(List rules) {
        this.rules = rules;
    }
    
    public List getRules() {
        return rules;
    }
}
