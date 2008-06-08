/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import com.googlecode.starch.util.LookupItem;
import com.googlecode.starch.util.LookupList;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class LookupConverter extends Converter {

    public LookupConverter() {
    }

    @Override
    public Object convertForward(Object value) {
        if (value == null) {
            return "";
        }
        if (useAlphaKey) {
            return getItemUsingAlphaKey((String)value);
        } else {
            return getItem((Integer)value);
        }
    }

    @Override
    public Object convertReverse(Object value) {
        if (value instanceof LookupItem) {
            if (useAlphaKey) {
                return (((LookupItem)value).getAlphaKey());
            } else {
                return (((LookupItem)value).getKey());
            }
        }
        return null;
    }
    
    private LookupItem getItem(int key) {
        for (int i = 0; i < list.getList().size(); i++) {
            if (key == list.getList().get(i).getKey()) {
                return list.getList().get(i);
            }
        }
        return null;
    }
    
    private LookupItem getItemUsingAlphaKey(String alphaKey) {
        for (int i = 0; i < list.getList().size(); i++) {
            if (alphaKey.equals(list.getList().get(i).getAlphaKey())) {
                return list.getList().get(i);
            }
        }
        return null;        
    }

    public LookupList getList() {
        return list;
    }

    public void setList(LookupList list) {
        this.list = list;
    }

    public boolean isUseAlphaKey() {
        return useAlphaKey;
    }

    public void setUseAlphaKey(boolean useAlphaKey) {
        this.useAlphaKey = useAlphaKey;
    } 
    
    private boolean useAlphaKey = false;
    private LookupList list = new LookupList();
}
