/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author moscac
 */
public class BooleanRenderer implements TableCellRenderer {

    public BooleanRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)

        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }

        panel.setLayout(new BorderLayout());
        // Configure the component with the specified value
        boolean booleanValue = false;
        if (value instanceof Boolean) {
            booleanValue = (Boolean) value;
        }
        if (displayText) {
            if (booleanValue) {
                label.setText(trueValue);
            } else {
                label.setText(falseValue);
            }
            panel.add(label, BorderLayout.WEST);
            label.invalidate();
        } else {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            checkBox.setText("");
            checkBox.setSelected(booleanValue);
            panel.add(checkBox);
            checkBox.invalidate();
        }
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        } else {
            panel.setBackground(table.getBackground());
            label.setForeground(table.getForeground());
        }
        // Set tool tip if desired
        //label.setToolTipText((String) value);
        return panel;
    }

    public boolean isDisplayText() {
        return displayText;
    }

    public void setDisplayText(boolean displayText) {
        this.displayText = displayText;
    }

    public String getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }

    public String getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private JCheckBox checkBox = new JCheckBox();
    private String trueValue = "Yes";
    private String falseValue = "No";
    private boolean displayText = false;
}
