/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author moscac
 */
public class LookupColumnRenderer implements TableCellRenderer {

    public LookupColumnRenderer() {
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
        if (value instanceof Integer) {
            label.setText(getDisplayValue((Integer)value));
        }

        // Set tool tip if desired
        //label.setToolTipText((String) value);

        // Since the renderer is a component, return itself
        return label;
    }
    
    private String getDisplayValue(int lookupValue) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getLookupValue() == lookupValue) {
                return items.get(i).getDisplayValue();
            }
        }
        return "";
    }
    
    public void addLookupItem(int lookupValue, String displayValue) {
        items.add(new LookupColumnItem());
        items.get(items.size() - 1).setLookupValue(lookupValue);
        items.get(items.size() - 1).setDisplayValue(displayValue);
    }
    
    private JLabel label = new JLabel();
    private List<LookupColumnItem> items = new ArrayList<LookupColumnItem>(0);

}
