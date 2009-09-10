/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import com.googlecode.starch.util.LookupItem;
import com.googlecode.starch.util.LookupList;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.Validator;

/**
 *
 * @author moscac
 */
public class LookupValidator extends Validator {

    Logger logger = Logger.getLogger(LookupValidator.class.getName());
    
    public LookupValidator() {
        
    }

    public Result validate(Object value) {
        try {
            if (value instanceof Integer) {
                int intValue = ((Integer) value).intValue();
                // NOTE WELL: This logic assumes the use of the starch library lookup list
                // which adds a "null" element which is indexed with the value of 0
                // therefore we are testing for a value between 1 and the size of the list
                // instead of 0
                for (LookupItem item : lookupList.getList()) {
                    if (intValue > 0) {
                        return null;
                    }
                }
                return new Result(null, "A value must be selected for " + fieldName);
            }
        } catch (Exception e) {
            logger.fine(e.getMessage());
        }
        return new Result(value, "A value must be selected for " + fieldName);
    }

    public LookupList getLookupList() {
        return lookupList;
    }

    public void setLookupList(LookupList lookupList) {
        this.lookupList = lookupList;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    private String fieldName = "";
    private LookupList lookupList = new LookupList();
}
