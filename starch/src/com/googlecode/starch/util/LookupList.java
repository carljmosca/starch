/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class LookupList {

    public LookupList() {
    }

    public List<LookupItem> getList() {
        return list;
    }

    public void setList(List<LookupItem> list) {
        this.list = list;
    }
    
    private List<LookupItem> list = new ArrayList<LookupItem>(0);
}
