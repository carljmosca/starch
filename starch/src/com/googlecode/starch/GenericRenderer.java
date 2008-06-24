/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            checkBox.setText("");
            checkBox.setSelected(((Boolean) value).booleanValue());
            panel.add(checkBox);
            checkBox.invalidate();
        } else if ((value instanceof BigDecimal) || (value instanceof Float) ||
                (value instanceof Integer)) {
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
            label.setText(numberFormat.format(value));
            panel.add(label, BorderLayout.EAST);
            label.invalidate();
        } else {
            if (value == null) {
                label.setText("");
            } else {
                label.setText(value.toString());
            }
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
    
    private int minimumFractionDigits = 2;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private JCheckBox checkBox = new JCheckBox();
    private boolean currencyFormat = false;
    private boolean percentFormat = false;
    private boolean groupingUsed = true;
}
