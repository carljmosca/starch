/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.awt.BorderLayout;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
        label.setFont(table.getFont());
        Component comp = null;
        // Configure the component with the specified value
        if (value != null) {
            TableCellRenderer rend = table.getDefaultRenderer(value.getClass());
            if (rend != null) {
                comp = table.prepareRenderer(rend, rowIndex, vColIndex);
            }
        }
        if (value instanceof Boolean) {
            if ((Boolean)value) {
                label.setText("Yes");
            } else {
                label.setText("No");
            }
            panel.add(label, BorderLayout.EAST);
            label.invalidate();
        } else if ((value instanceof BigDecimal) || (value instanceof Float) ||
                (value instanceof Integer)) {
            if (blankWhenZero && isZero(value)) {
                label.setText("");
            } else {
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
            }
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
            if (comp != null) {
                panel.setBackground(comp.getBackground());
                label.setForeground(comp.getForeground());
            } else {
                panel.setBackground(table.getBackground());
                label.setForeground(table.getForeground());                
            }
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

    public boolean isBlankWhenZero() {
        return blankWhenZero;
    }

    public void setBlankWhenZero(boolean blankWhenZero) {
        this.blankWhenZero = blankWhenZero;
    }
    
    private boolean isZero(Object value) {
        if ((value instanceof BigDecimal) && (((BigDecimal)value).floatValue() == 0))  {
            return true;
        }
        if ((value instanceof Float) && (((Float)value).floatValue() == 0))  {
            return true;
        }
        if ((value instanceof Integer) && (((Integer)value).intValue() == 0))  {
            return true;
        }
        return false;
    }
    
    private int minimumFractionDigits = 2;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private boolean currencyFormat = false;
    private boolean percentFormat = false;
    private boolean groupingUsed = true;
    private boolean blankWhenZero = false;
}
