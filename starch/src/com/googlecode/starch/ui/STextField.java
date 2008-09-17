/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch.ui;

import com.googlecode.starch.util.LookupList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Action;
import javax.swing.JTextField;

/**
 *
 * @author moscac
 */
public class STextField extends JTextField {

    public STextField() {
        addEventListeners();
    }

    private void addEventListeners() {
        this.setToolTipText("");
        addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                sTextField.updateText();
            }
        });
        addKeyListener(new KeyListener() {

            public void keyReleased(KeyEvent evt) {
            }

            public void keyPressed(KeyEvent evt) {
                sTextField.updateToolTip(evt);
            }

            public void keyTyped(KeyEvent evt) {
            }
        });
    }

    public void updateToolTip(KeyEvent evt) {
        String s = "<html>";
        String text = getText();
        if ((evt.getKeyCode() >= KeyEvent.VK_0) && (evt.getKeyCode() <= KeyEvent.VK_Z)) {
            text += evt.getKeyChar();
        }
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

    public void updateText() {
        String s = getText();
        for (int i = 0; i < list.getList().size(); i++) {
            if (list.getList().get(i).getValue().startsWith(s)) {
                setText(list.getList().get(i).getValue());
                setKey(list.getList().get(i).getKey());
                break;
            }
        }   
    }

    public void updateTextByKey(int key) {
        String s = getText();
        for (int i = 0; i < list.getList().size(); i++) {
            if (list.getList().get(i).getKey() == key) {
                setText(list.getList().get(i).getValue());
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
        changeSupport.firePropertyChange("key", oldKey, key);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    private STextField sTextField = this;
    private int key = 0;
    private LookupList list = new LookupList();
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
}
