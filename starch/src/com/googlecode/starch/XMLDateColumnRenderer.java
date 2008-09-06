/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.starch;

import com.googlecode.starch.util.DateConverter;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author moscac
 */
public class XMLDateColumnRenderer implements TableCellRenderer {

    public XMLDateColumnRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at
        // (rowIndex, vColIndex)

        panel.removeAll();

        // Configure the component with the specified value
        if (value instanceof XMLGregorianCalendar) {
            label.setText(DateConverter.getDateAsString((XMLGregorianCalendar) value));
            label.invalidate();
        }
        TableCellRenderer rend = table.getDefaultRenderer(value.getClass());
        Component comp = table.prepareRenderer(rend, rowIndex, vColIndex);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        } else {
            panel.setBackground(comp.getBackground());
            label.setForeground(comp.getForeground());
        }
        // Set tool tip if desired
        //label.setToolTipText((String) value);
        // Since the renderer is a component, return itself
        return panel;
    }

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();

}
