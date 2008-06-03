/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import com.googlecode.starch.util.DateConverter;
import java.awt.Component;
import javax.swing.JLabel;
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

        if (isSelected) {
            // cell (and perhaps other cells) are selected
        }

        if (hasFocus) {
            // this cell is the anchor and the table has the focus
        }        

        // Configure the component with the specified value
        if (value instanceof XMLGregorianCalendar) {
            label.setText(DateConverter.getDateAsString((XMLGregorianCalendar)value));
        }
        if (copyColumnBackgroundColor >= 0) {
            label.setBackground(table.getBackground());
        }
        // Set tool tip if desired
        //label.setToolTipText((String) value);

        // Since the renderer is a component, return itself
        return label;
    }

    public int getCopyColumnBackgroundColor() {
        return copyColumnBackgroundColor;
    }

    public void setCopyColumnBackgroundColor(int copyColumnBackgroundColor) {
        this.copyColumnBackgroundColor = copyColumnBackgroundColor;
    }
   
    private int copyColumnBackgroundColor;
    private JLabel label = new JLabel();

}
