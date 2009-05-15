/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class TextConverter extends Converter {

    public TextConverter() {
    }

    @Override
    public Object convertForward(Object value) {

        if (value == null) {
            return "";
        }
        if (value instanceof String) {
            return getConvertedString((String) value);
        } else if (value instanceof XMLGregorianCalendar) {
            SimpleDateFormat sdf;
            try {
                sdf = new SimpleDateFormat(getDateFormat());
            } catch (IllegalArgumentException iao) {
                sdf = new SimpleDateFormat("MM/dd/yyyy");
            }
            return sdf.format(((XMLGregorianCalendar) value).toGregorianCalendar().getTime());
        }
        return value;
    }

    public Object convertReverse(Object value) {

        if (value == null) {
            return "";
        }
        if (value instanceof String) {
            return getConvertedString((String) value);
        }
        return value;
    }

    private String getConvertedString(String value) {
        if (isToLower()) {
            return value.toLowerCase();
        }
        if (isToUpper()) {
            return value.toUpperCase();
        }
        return value;
    }

    public boolean isToLower() {
        return toLower;
    }

    public void setToLower(boolean toLower) {
        this.toLower = toLower;
    }

    public boolean isToUpper() {
        return toUpper;
    }

    public void setToUpper(boolean toUpper) {
        this.toUpper = toUpper;
    }

    public String getDateFormat() {
        return dateFormat.toString();
    }

    public void setDateFormat(String dateFormat) {
        String oldDateFormat = this.dateFormat;
        this.dateFormat = dateFormat;
        changeSupport.firePropertyChange("dateFormat", oldDateFormat, this.dateFormat);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private boolean toUpper = false;
    private boolean toLower = false;
    private String dateFormat = "MM/dd/yyyy";
}
