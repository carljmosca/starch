/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import java.util.logging.Logger;
import org.jdesktop.beansbinding.Validator;
import org.jdesktop.beansbinding.Validator.Result;

/**
 *
 * @author moscac
 */
public class TextValidator extends Validator {

    Logger logger = Logger.getLogger(TextValidator.class.getName());

    public TextValidator() {
        this(-1);
    }

    public TextValidator(int maxLength) {
        this(maxLength, 0, (int) Math.pow(10.0, (double) (maxLength)) - 1);
    }

    public TextValidator(int maxLength, int minValue, int maxValue) {
        setMaxLength(maxLength);
        setAllowEmpty(true);
        setMinValue(minValue);
        setMaxValue(maxValue);
    }

    public Result validate(Object value) {
        try {
            if (value instanceof String) {
                if ((maxLength > 0) && (((String) value).length() > maxLength)) {
                    return new Result(null, "Error: " + fieldName + " exceeded maximum length");
                }
                if (numericCharactersOnly) {
                    try {
                        Integer intValue = new Integer(Integer.parseInt((String)value));
                        if ((intValue.longValue() < minValue) || (intValue.longValue() > maxValue)) {
                            return new Result(null, "Error: " + fieldName + " outside of allowable range");
                        }
                    } catch (NumberFormatException nfe) {
                        return new Result(null, "Error: " + fieldName + " numeric characters only");
                    }
                }
                if (allowEmpty || ((String) value).length() > 0) {
                    return null;
                }
                return null;
            } else if (value instanceof Integer) {
                int intValue = ((Integer) value).intValue();
                if ((intValue < minValue) || (intValue > maxValue)) {
                    return new Result(null, "Error: " + fieldName + " outside of allowable range");
                }
                return null;
            }
        } catch (Exception e) {
            logger.fine(e.getMessage());
        }
        return new Result(value, "Invalid value");
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public boolean isNumericCharactersOnly() {
        return numericCharactersOnly;
    }

    public void setNumericCharactersOnly(boolean numericCharactersOnly) {
        this.numericCharactersOnly = numericCharactersOnly;
    }
        
    private int maxLength;
    private int minValue;
    private int maxValue;
    private boolean allowEmpty;
    private boolean numericCharactersOnly;
    private String fieldName = "";
}
