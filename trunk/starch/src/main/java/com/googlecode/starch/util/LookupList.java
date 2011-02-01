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

/**
 *
 * @author moscac
 */
public class LookupList {

    public LookupList() {
        addEmptyItem();
    }

    public LookupList(String defaultText){
        this.defaultItemText = defaultText;
        addEmptyItem();
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

    public void addLookupItem(int key, String alphaKey, String value, String description) {
        list.add(new LookupItem(key, alphaKey, value, description));
    }

    public void removeAll() {
        list.removeAll(list);
        addEmptyItem();
    }

    private void addEmptyItem() {
        list.add(new LookupItem(0, "0", defaultItemText));
    }
    
    public String getDefaultItemText(){
        return this.defaultItemText;
    }

    public void setDefaultItemText(String defaultText){
        this.defaultItemText = defaultText;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    private String defaultItemText = "";
    private ObservableList<LookupItem> list = ObservableCollections.observableList(new ArrayList<LookupItem>(0));
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
}
