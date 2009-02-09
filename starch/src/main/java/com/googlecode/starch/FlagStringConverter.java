/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class FlagStringConverter extends Converter {

    public FlagStringConverter() {
    }

    @Override
    public Object convertForward(Object value) {
        if (value == null) {
            return false;
        }
        flagsValue.setLength(0);
        flagsValue.append(value);
        return (flagValue.length() > 0) && (flagsValue.indexOf(flagValue) >= 0);
    }

    @Override
    public Object convertReverse(Object value) {
        boolean selected = (Boolean) value;
        if (flagValue.length() == 0) {
            return flagsValue;
        }
        if (selected) {
            if (flagsValue.indexOf(flagValue) <= 0) {
                flagsValue.append(flagValue);
            }
        } else {
            int i = flagsValue.indexOf(flagValue);
            if (i >= 0) {
                flagsValue.deleteCharAt(i);
            }
        }
        return flagsValue.toString();
    }

    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }
    private String flagValue = "";
    private StringBuffer flagsValue = new StringBuffer();

}
