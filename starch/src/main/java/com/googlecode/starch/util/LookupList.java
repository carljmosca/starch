/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

import com.googlecode.starch.util.LookupItem;

/**
 *
 * @author moscac
 */
public class LookupList {

    public LookupList() {
        this(true);
    }
    
    public LookupList(boolean includeEmptyItem) {
        this.includeEmptyItem = includeEmptyItem;
        addEmptyItem();
    }

    public LookupList(String defaultText) {
        this.defaultItemText = defaultText;
        if ((defaultText != null) && defaultText != "") {
            addEmptyItem();
        }
    }

    public ObservableList<LookupItem> getList() {
        return list;
    }

    public void setList(ObservableList<LookupItem> list) {
        this.list = list;
    }

    public void addLookupItem(int key, String alphaKey, String value) {
        addLookupItem(key, alphaKey, value, "");
    }

    public void addLookupItem(int key, String alphaKey, String value,
            String description) {
        list.add(new LookupItem(key, alphaKey, value, description));
    }

    public void removeAll() {
        list.removeAll(list);
        addEmptyItem();
    }

    private void addEmptyItem() {
        if (includeEmptyItem) {
            list.add(new LookupItem(-1, (("".equals(defaultAlphaKeyItemValue)
                    || defaultAlphaKeyItemValue == null) ? "-1" : defaultAlphaKeyItemValue), defaultItemText));
        }
    }

    public void setDefaultAlphaKeyItemValue(String value) {
        this.defaultAlphaKeyItemValue = value;
    }

    public String getDefaultItemText() {
        return this.defaultItemText;
    }

    public void setDefaultItemText(String defaultText) {
        this.defaultItemText = defaultText;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public boolean isIncludeEmptyItem() {
        return includeEmptyItem;
    }

    public void setIncludeEmptyItem(boolean includeEmptyItem) {
        this.includeEmptyItem = includeEmptyItem;
    }
    private boolean includeEmptyItem = true;
    private String defaultAlphaKeyItemValue = "";
    private String defaultItemText = "";
    private ObservableList<LookupItem> list = ObservableCollections
            .observableList(new ArrayList<LookupItem>(0));
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
            this);
}
