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
public class BooleanConverter extends Converter {

    public BooleanConverter() {
    }

    @Override
    public Object convertForward(Object value) {
        if (value == null) {
            return false;
        }
        if ((Boolean)value) {
           return trueValue; 
        }
        return falseValue;
    }

    @Override
    public Object convertReverse(Object value) {
        if (((String)value).equals(trueValue)) {
            return true;
        }
        return false;
    }

    public String getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }

    public String getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }

    private String trueValue = "Yes";
    private String falseValue = "No";

}
