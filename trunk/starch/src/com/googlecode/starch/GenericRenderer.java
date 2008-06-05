/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author moscac
 */
public class GenericRenderer implements TableCellRenderer {

    public GenericRenderer() {
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
        if (value instanceof Boolean) {
            checkBox.setText("");
            checkBox.setSelected(((Boolean)value).booleanValue()) ;
            panel.add(checkBox, BorderLayout.CENTER);
            checkBox.invalidate();
        } else {
            label.setText(value.toString());
            panel.add(label, BorderLayout.CENTER);
            label.invalidate();
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
    
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private JCheckBox checkBox = new JCheckBox();
}
