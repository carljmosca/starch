/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class DateConverter extends Converter {

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private String dateFormat = "MM/dd/yyyy";

    public DateConverter() {
    }

    public String getDateFormat() {
        return dateFormat.toString();
    }

    public void setDateFormat(String dateFormat) {
        if (dateFormat == null) {
            return;
        }
        String oldDateFormat = this.dateFormat;
        this.dateFormat = dateFormat;
        changeSupport.firePropertyChange("dateFormat", oldDateFormat, this.dateFormat);
    }

    @Override
    public Object convertReverse(Object value) {
        Date result = null;
        if (value != null && value instanceof String) {
            try {
                result = getSDF().parse((String)value);
            } catch (ParseException ex) {
            }
        }
        return result;
    }

    @Override
    public Object convertForward(Object value) {
        String result = "";
        if (value != null && value instanceof Date) {
            try {
                result = getSDF().format(value);
            } catch (Exception e) {
            }
        }
        return result;
    }

    private SimpleDateFormat getSDF() {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(getDateFormat());
        } catch (IllegalArgumentException iao) {
            sdf = new SimpleDateFormat("MM/dd/yyyy");
        }
        return sdf;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
