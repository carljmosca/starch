/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch.ui;

import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author moscac
 */
public class NumericValidator extends AbstractValidator implements Serializable {

    public NumericValidator() {
        setAllowBlank(true);
    }

    protected boolean validationCriteria(JComponent c) {

        String number = ((JTextField) c).getText();
        if (number.trim().length() == 0) {
            if (allowBlank) {
                return true;
            }
            setMessage("Field cannot be blank");
            return false;
        }
        return validate(number);
    }

    public boolean isAllowDecimal() {
        return allowDecimal;
    }

    public void setAllowDecimal(boolean allowDecimal) {
        this.allowDecimal = allowDecimal;
    }

    public boolean isAllowBlank() {
        return allowBlank;
    }

    public void setAllowBlank(boolean allowBlank) {
        this.allowBlank = allowBlank;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean isNegativeOnly() {
        return negativeOnly;
    }

    public void setNegativeOnly(boolean negativeOnly) {
        this.negativeOnly = negativeOnly;
    }

    public boolean isPositiveOnly() {
        return positiveOnly;
    }

    public void setPositiveOnly(boolean positiveOnly) {
        this.positiveOnly = positiveOnly;
    }

    private boolean validate(String number) {
        try {
            if (allowDecimal) {
                float v = Float.parseFloat(number);
                if ((precision > 0) && (number.indexOf(".") >= 0)) {
                    if ((number.indexOf(".") + 1) < (number.length() - precision)) {
                        setMessage("Only " + precision + " decimal places are allowed.");
                        return false;
                    }
                }
                if (positiveOnly && (v < 0.0)) {
                    setMessage("Only positive numbers are allowed.");
                    return false;
                }
                if (negativeOnly && (v > 0.0)) {
                    setMessage("Only negative numbers are allowed.");
                    return false;
                }
            } else {
                int v = Integer.parseInt(number);
                if (positiveOnly && (v < 0)) {
                    setMessage("Only positive numbers are allowed.");
                    return false;
                }
                if (negativeOnly && (v > 0)) {
                    setMessage("Only negative numbers are allowed.");
                    return false;
                }
            }
        } catch (NumberFormatException nfe) {
            setMessage("Field is numeric");
            return false;
        }
        return true;
    }
    private boolean allowDecimal;
    private boolean allowBlank;
    private boolean positiveOnly;
    private boolean negativeOnly;
    private int precision;
}
