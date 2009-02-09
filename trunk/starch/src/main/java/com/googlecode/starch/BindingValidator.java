/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Validator;

/**
 *
 * @author moscac
 */
public class BindingValidator {
public BindingValidator() {
    }

    public boolean doValidation(boolean silent) {
        if (bindingGroup == null) {
            JOptionPane.showMessageDialog(null, "bindingGroup property has not been set in BindingValidator");
            return true;
        }
        int bindings = bindingGroup.getBindings().size();
        Validator.Result result = null;
        Object target = null;
        Validator validator = null;
        for (int i = 0; i < bindings; i++) {
            target = bindingGroup.getBindings().get(i).getTargetObject();
            validator = bindingGroup.getBindings().get(i).getValidator();
            if (validator != null) {
                if (target instanceof JTextField) {
                    result = validator.validate(((JTextField) target).getText());
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, result.getDescription());
                        ((JTextField) target).requestFocus();
                        return false;
                    }
                } else if (target instanceof JComboBox) {
                    result = validator.validate(((JComboBox)target).getSelectedIndex());
                    if (result != null) {
                        JOptionPane.showMessageDialog(null, result.getDescription());
                        ((JComboBox)target).requestFocus();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public BindingGroup getBindingGroup() {
        return bindingGroup;
    }

    public void setBindingGroup(BindingGroup bindingGroup) {
        this.bindingGroup = bindingGroup;
    }
    
    private BindingGroup bindingGroup;
}
