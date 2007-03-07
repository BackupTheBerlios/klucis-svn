package lv.webkursi.mtest.core.mvc.rule;

import org.springframework.beans.BeanWrapper;

/**
 *
 */
public class PortImpl implements Port {
    
    private BeanWrapper source;
    private String propertyName;
    
    public int getInt() {
        Integer val = (Integer) source.getPropertyValue(propertyName);
        return val.intValue(); //@@ todo
    }

    /**
     * @param propertyName The propertyName to set.
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(BeanWrapper source) {
        this.source = source;
    }

}
