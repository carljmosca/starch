/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import java.util.ArrayList;
import java.util.List;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author adamsl
 */
public class LookupStringConverter extends Converter {

    @Override
    public Object convertForward(Object s) {
        if(s instanceof Integer){
            return getDisplayValue((Integer)s);
        }
        return null;
    }

    @Override
    public Object convertReverse(Object t) {
        return null;
    }

    public void addLookupItem(int lookupValue, String displayValue) {
        items.add(new LookupColumnItem());
        items.get(items.size() - 1).setLookupValue(lookupValue);
        items.get(items.size() - 1).setDisplayValue(displayValue);
    }

    private String getDisplayValue(int lookupValue) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getLookupValue() == lookupValue) {
                return items.get(i).getDisplayValue();
            }
        }
        return "";
    }
    private List<LookupColumnItem> items = new ArrayList<LookupColumnItem>(0);
}
