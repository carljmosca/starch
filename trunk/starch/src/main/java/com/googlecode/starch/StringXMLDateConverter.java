/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import com.googlecode.starch.util.DateConverter;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author moscac
 */
public class StringXMLDateConverter extends Converter  {

    public StringXMLDateConverter() {
    }

    @Override
    public Object convertForward(Object value) {
        if (DateConverter.isValueRepresentingNull((XMLGregorianCalendar)value))
            return null;
//        if (value == null)
//            return new Date();
        return ((XMLGregorianCalendar)value).toGregorianCalendar().getTime();
    }
    
    @Override
    public Object convertReverse(Object value) {
        if (value == null)
            return DateConverter.dateValueRepresentingNull();
        return DateConverter.dateToXML((Date)value);
    }

}
