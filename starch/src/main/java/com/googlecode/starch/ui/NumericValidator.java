/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch.ui;

import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JDialog;
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

    private boolean validate(String number) {
        try {
            if (allowDecimal) {
                Float.parseFloat(number);
            } else {
                Integer.parseInt(number);
            }
        } catch (NumberFormatException nfe) {
            setMessage("Field is numeric");
            return false;
        }
        return true;
    }
    private boolean allowDecimal;
    private boolean allowBlank;
}
