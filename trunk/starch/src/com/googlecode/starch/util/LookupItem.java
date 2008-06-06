/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author moscac
 */
public class LookupItem implements Serializable {

    public LookupItem() {
    }

    public LookupItem(int key, String value) {
        this();
        setKey(key);
        setValue(value);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        int oldKey = this.key;
        this.key = key;
        changeSupport.firePropertyChange("key", oldKey, key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        changeSupport.firePropertyChange("value", oldValue, value);
    }

    @Override
    public String toString() {
        return getValue();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private int key = 0;
    private String value = "";
}
