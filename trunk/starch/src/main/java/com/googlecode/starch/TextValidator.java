/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
                    if (autoValidate) {
                        JOptionPane.showMessageDialog(null, "Warning: " + fieldName + " exceeds maximum length of " + maxLength);
                    }
                    return new Result(null, "Error: " + fieldName + " exceeded maximum length");
                }
                if (allowEmpty && ((String) value).length() == 0) {
                    return null;
                }
                if (numericCharactersOnly) {
                    try {
                        Integer intValue = new Integer(Integer.parseInt((String) value));
                        if ((intValue.longValue() < minValue) || (intValue.longValue() > maxValue)) {
                            return new Result(null, "Error: " + fieldName + " outside of allowable range");
                        }
                    } catch (NumberFormatException nfe) {
                        return new Result(null, "Error: " + fieldName + " numeric characters only");
                    }
                }
                if ((!allowEmpty) && ((String)value).length() == 0) {
                    return new Result(null, "Error: " + fieldName + " cannot be blank");
                }
                return null;
            } else if (value instanceof Integer) {
                int intValue = ((Integer) value).intValue();
                int len = ((Integer) value).toString().length();
                if ((intValue < minValue) || (intValue > maxValue) || (len > maxLength)) {
                    if (autoValidate) {
                        JOptionPane.showMessageDialog(null, "Warning: " + fieldName + " must be in range of " + minValue + " to " +
                                maxValue + " and no longer than " + maxLength);
                    }
                    return new Result(null, "Error: " + fieldName + " outside of allowable range");
                }
                return null;
            } else if (value instanceof BigDecimal) {
                double v = ((BigDecimal) value).doubleValue();
                String s = ((BigDecimal) value).toString();
                if ((precision > 0) && (s.indexOf(".") >= 0)) {
                    if ((s.indexOf(".") + 1) < (s.length() - precision)) {
                        if (autoValidate) {
                            JOptionPane.showMessageDialog(null, "Warning: " + fieldName + " must not contain more than " + precision +
                                    " decimal places.");
                        }
                        return new Result(null, "Error: " + fieldName + " outside of allowable range");
                    }
                }
                int len = s.length();
                if ((v < minValue) || (v > maxValue) || (len > maxLength)) {
                    if (autoValidate) {
                        JOptionPane.showMessageDialog(null, "Warning: " + fieldName + " must be in range of " + minValue + " to " +
                                maxValue + " and no longer than " + maxLength + " characters");
                    }
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

    public boolean isAutoValidate() {
        return autoValidate;
    }

    public void setAutoValidate(boolean autoValidate) {
        this.autoValidate = autoValidate;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
    private int maxLength;
    private int minValue;
    private int maxValue;
    private boolean allowEmpty;
    private boolean numericCharactersOnly;
    private String fieldName = "";
    private boolean autoValidate;
    private int precision;
}
