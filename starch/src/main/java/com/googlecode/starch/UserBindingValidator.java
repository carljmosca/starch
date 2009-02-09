/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.AbstractBindingListener;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Binding.SyncFailure;

/**
 *
 * @author moscac
 */
public class UserBindingValidator extends AbstractBindingListener {

    @Override
    public void syncFailed(Binding binding, SyncFailure fail) {
        String description;
        if ((fail != null) && (fail.getType() == Binding.SyncFailureType.VALIDATION_FAILED)) {
            description = fail.getValidationResult().getDescription();
            JOptionPane.showMessageDialog(null, description);
            if (binding.getTargetObject() instanceof JTextField) {
                ((JTextField)binding.getTargetObject()).selectAll();
            }
        }
    } 

}
