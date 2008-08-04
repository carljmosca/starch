/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.math.BigDecimal;
import java.text.NumberFormat;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class NumericConverter extends Converter {

    public NumericConverter() {
    }

    @Override
    public Object convertForward(Object value) {
        if (value == null) {
            return "";
        }
        if ((value instanceof BigDecimal) || (value instanceof Float) ||
                (value instanceof Integer)) {
            if (blankWhenZero && isZero(value)) {
                return "";
            } else {
                NumberFormat numberFormat;
                if (currencyFormat) {
                    numberFormat = NumberFormat.getCurrencyInstance();
                } else if (percentFormat) {
                    numberFormat = NumberFormat.getPercentInstance();
                } else {
                    numberFormat = NumberFormat.getInstance();
                    numberFormat.setMinimumFractionDigits(minimumFractionDigits);
                }
                numberFormat.setGroupingUsed(groupingUsed);
                return numberFormat.format(value);
            }
        }
        return value;
    }
        
    @Override
    public Object convertReverse(Object value) {
        if (((String) value).length() == 0) {
            if (minimumFractionDigits == 0) {
                return new Integer(0);
            } else {
                return new Float(0);
            }
        }
        switch (minimumFractionDigits) {
            case 0:
                int i = 0;
                try {
                    i = Integer.parseInt((String) value);
                } catch (NumberFormatException nfe) {
                    return i;
                }
                break;
            default :
                float f = 0;
                try {
                    f = Float.parseFloat((String) value);
                } catch (NumberFormatException nfe) {
                    return f;
                }
        }
        return 0;
    }

     boolean isBlankWhenZero() {
        return blankWhenZero;
    }

    public void setBlankWhenZero(boolean blankWhenZero) {
        this.blankWhenZero = blankWhenZero;
    }

    public boolean isCurrencyFormat() {
        return currencyFormat;
    }

    public void setCurrencyFormat(boolean currencyFormat) {
        this.currencyFormat = currencyFormat;
    }

    public boolean isPercentFormat() {
        return percentFormat;
    }

    public void setPercentFormat(boolean percentFormat) {
        this.percentFormat = percentFormat;
    }

    public int getMinimumFractionDigits() {
        return minimumFractionDigits;
    }

    public void setMinimumFractionDigits(int minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
    }

    public boolean isGroupingUsed() {
        return groupingUsed;
    }

    public void setGroupingUsed(boolean groupingUsed) {
        this.groupingUsed = groupingUsed;
    }

    private boolean isZero(Object value) {
        if ((value instanceof BigDecimal) && (((BigDecimal) value).floatValue() == 0)) {
            return true;
        }
        if ((value instanceof Float) && (((Float) value).floatValue() == 0)) {
            return true;
        }
        if ((value instanceof Integer) && (((Integer) value).intValue() == 0)) {
            return true;
        }
        return false;
    }
    
    private boolean blankWhenZero;
    private boolean groupingUsed = true;
    private int minimumFractionDigits = 2;
    private boolean currencyFormat = false;
    private boolean percentFormat = false;
}
