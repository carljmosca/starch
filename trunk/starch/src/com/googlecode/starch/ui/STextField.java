/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch.ui;

import com.googlecode.starch.util.LookupList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleState;
import javax.swing.Action;
import javax.swing.JTextField;

/**
 *
 * @author moscac
 */
public class STextField extends JTextField {

    public STextField() {
        addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent event) {
                if (accessibleContext != null) {
                    accessibleContext.firePropertyChange(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            null, AccessibleState.FOCUSED);
                }
            }

            public void focusLost(FocusEvent event) {
                if (accessibleContext != null) {
                    accessibleContext.firePropertyChange(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            AccessibleState.FOCUSED, null);
                }
                updateText();
            }
        });
    }

    @Override
    public void processKeyEvent(KeyEvent ev) {

        char c = ev.getKeyChar();
        super.processKeyEvent(ev);
        if (Character.isLetter(c)) {
            updateToolTip(ev);
        }
    }

    private void updateToolTip(KeyEvent evt) {
        String s = "<html>";
        String text = getText();
        for (int i = 0; i < list.getList().size(); i++) {
            if ((list.getList().get(i).getValue().trim().length() > 0) &&
                    list.getList().get(i).getValue().startsWith(text)) {
                if (s.length() > 0) {
                    s += "<br>";
                }
                s += list.getList().get(i).getValue();
            }
        }
        s += "</html>";
        setToolTipText(s);
        Component c = evt.getComponent();
        Action action = getActionMap().get("postTip");
        //it is also possible to use own Timer to display 
        //ToolTip with custom delay, but here we just 
        //display it immediately
        if (action != null) {
            action.actionPerformed(new ActionEvent(c, ActionEvent.ACTION_PERFORMED, "postTip"));
        }
    }

    private void updateText() {
        String s = getText();
        for (int i = 0; i < list.getList().size(); i++) {
            if (list.getList().get(i).getValue().startsWith(s)) {
                setText(list.getList().get(i).getValue());
                setKey(list.getList().get(i).getKey());
                break;
            }
        }
    }

    private void updateTextByKey(int key) {
        String s = getText();
        for (int i = 0; i < list.getList().size(); i++) {
            if (list.getList().get(i).getKey() == key) {
                setText(list.getList().get(i).getValue());
                setKey(list.getList().get(i).getKey());
                break;
            }
        }
    }

    public LookupList getList() {
        return list;
    }

    public void setList(LookupList list) {
        this.list = list;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        int oldKey = key;
        this.key = key;
        updateTextByKey(key);
        firePropertyChange("key", oldKey, key);
    }
    private int key = 0;
    private LookupList list = new LookupList();
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
}
