/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import java.awt.BorderLayout;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author moscac
 */
public class GenericEditor implements TableCellEditor, TableCellRenderer {

    public GenericEditor() {
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
            if ((Boolean) value) {
                label.setText("Yes");
            } else {
                label.setText("No");
            }
            panel.add(label, BorderLayout.EAST);
            label.invalidate();
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

    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        if ((value != null) && (value instanceof XMLGregorianCalendar)) {
        }
        this.table = table;
        return textField;
    }

    public void addCellEditorListener(CellEditorListener cellEditorListener) {
        ceListeners.add(cellEditorListener);
    }

    public void removeCellEditorListener(CellEditorListener cellEditorListener) {
        ceListeners.remove(cellEditorListener);
    }

    public void cancelCellEditing() {
        textField.setVisible(false);
        for (int i = 0; i < ceListeners.size(); i++) {
            ceListeners.get(i).editingCanceled(null);
        }
    }

    public boolean stopCellEditing() {
        textField.setVisible(false);
        for (int i = 0; i < ceListeners.size(); i++) {
            ceListeners.get(i).editingStopped(null);
        }
        int column = table.getSelectedColumn();
        if (column < (table.getColumnCount()) - 1) {
            table.editCellAt(table.getSelectedRow(), column + 1);
        }
        return true;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        textField.setVisible(true);
        textField.selectAll();
        return true;
    }

    public boolean isCellEditable(EventObject eventObject) {
        return true;
    }

    public Object getCellEditorValue() {
        return textField.getText();
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
    private boolean currencyFormat = false;
    private boolean percentFormat = false;
    private boolean groupingUsed = true;
    private JTextField textField = new JTextField();
    private List<CellEditorListener> ceListeners = new ArrayList<CellEditorListener>(0);
    private JTable table = null;
}
