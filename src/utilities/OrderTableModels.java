/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Entities.Order;
import utilities.AbstractGuiTableModels;

/**
 *
 * @author Alaa Shafshak
 */
public class OrderTableModels extends AbstractGuiTableModels<Order>{
     private final String[] columnNames = {"ID", "Date"};
    private final Class[] columnClasses = {String.class, String.class};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getId();
            case 1:
                return o.getDate();
             
            default:
                return null;
        }
    }
}
        

