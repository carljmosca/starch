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

    public LookupItem(String alphaKey, String value) {
        this();
        setAlphaKey(alphaKey);
        setValue(value);
    }

    public LookupItem(int key, String alphaKey, String value) {
        this(key, alphaKey, value, "");
    }

    public LookupItem(int key, String alphaKey, String value, String description) {
        this();
        setKey(key);
        setAlphaKey(alphaKey);
        setValue(value);
        setDescription(description);
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

    public String getAlphaKey() {
        return alphaKey;
    }

    public void setAlphaKey(String alphaKey) {
        String oldAlphaKey = this.alphaKey;
        this.alphaKey = alphaKey;
        changeSupport.firePropertyChange("alphaKey", oldAlphaKey, alphaKey);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, alphaKey);
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
    private String alphaKey = "";
    private String value = "";
    private String description = "";
}
