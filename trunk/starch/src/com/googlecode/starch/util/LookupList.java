/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch.util;

import java.util.ArrayList;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author moscac
 */
public class LookupList {

    public LookupList() {
    }

    public ObservableList<LookupItem> getList() {
        return list;
    }

    public void setList(ObservableList<LookupItem> list) {
        this.list = list;
    }
    
    public void addLookupItem(int key, String alphaKey, String value) {
        list.add(new LookupItem(key, alphaKey, value));
    }
    
    private ObservableList<LookupItem> list = ObservableCollections.observableList(new ArrayList<LookupItem>(0));
}
