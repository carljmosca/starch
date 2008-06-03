/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

/**
 *
 * @author moscac
 */
public class ColumnLookupItem {
    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public int getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(int lookupValue) {
        this.lookupValue = lookupValue;
    }

    private String displayValue;
    private int lookupValue;
}
