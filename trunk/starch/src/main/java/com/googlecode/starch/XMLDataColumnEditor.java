/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import com.googlecode.starch.util.DateConverter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author moscac
 */
public class XMLDataColumnEditor implements TableCellEditor, TableCellRenderer {

    public XMLDataColumnEditor() {

    }

    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        if ((value != null) && (value instanceof XMLGregorianCalendar)) {
            if (DateConverter.isValueRepresentingNull((XMLGregorianCalendar) value)) {
                datePicker.setDate(Calendar.getInstance().getTime());
            } else {
                datePicker.setDate(((XMLGregorianCalendar) value).toGregorianCalendar().getTime());
            }
        }
        this.table = table;
        table.setSurrendersFocusOnKeystroke(true);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatString);
        sdf.setTimeZone(DateConverter.getTimeZone());
        datePicker.setFormats(sdf);
        return datePicker;
    }

    public void addCellEditorListener(CellEditorListener cellEditorListener) {
        ceListeners.add(cellEditorListener);
    }

    public void removeCellEditorListener(CellEditorListener cellEditorListener) {
        ceListeners.remove(cellEditorListener);
    }

    public void cancelCellEditing() {
        datePicker.setVisible(false);
        for (int i = 0; i < ceListeners.size(); i++) {
            ceListeners.get(i).editingCanceled(null);
        }
    }

    public boolean stopCellEditing() {
        datePicker.setVisible(false);
        for (int i = 0; i < ceListeners.size(); i++) {
            ceListeners.get(i).editingStopped(null);
        }
        if (propagateEditMode) {
            int column = table.getSelectedColumn();
            if (column < (table.getColumnCount())) {
                table.editCellAt(table.getSelectedRow(), column + 1);
            }
        }
        return true;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        datePicker.setVisible(true);
        return true;
    }

    public boolean isCellEditable(EventObject eventObject) {
        return true;
    }

    public Object getCellEditorValue() {
        return DateConverter.dateToXML(datePicker.getDate());
    }

    public String getDateFormatString() {
        return dateFormatString;
    }

    public void setDateFormatString(String dateFormatString) {
        this.dateFormatString = dateFormatString;
    }

    public boolean isPropagateEditMode() {
        return propagateEditMode;
    }

    public void setPropagateEditMode(boolean propagateEditMode) {
        this.propagateEditMode = propagateEditMode;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)

        panel.removeAll();
        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }
        // Configure the component with the specified value
        if (value instanceof XMLGregorianCalendar) {
            label.setText(DateConverter.getDateAsString((XMLGregorianCalendar) value, dateFormatString));
            label.invalidate();
        }
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        } else {
            panel.setBackground(table.getBackground());
            label.setForeground(table.getForeground());
        }
        // Set tool tip if desired
        //label.setToolTipText((String) value);
        // Since the renderer is a component, return itself
        panel.invalidate();
        return panel;
    }
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JXDatePicker datePicker = new JXDatePicker();
    private List<CellEditorListener> ceListeners = new ArrayList<CellEditorListener>(0);
    private String dateFormatString = "MM/dd/yy";
    private JTable table = null;
    private boolean propagateEditMode = true;
}
