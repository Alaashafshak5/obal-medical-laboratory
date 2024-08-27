/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Entities.Client;

/**
 *
 * @author lenovo
 */
public class ClientsTableModel extends AbstractGuiTableModels<Client> {

    private final String[] columnNames = {"Name", "Location", "Number"};
    private final Class[] columnClasses = {String.class, String.class, String.class};

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
        Client c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFirstName() +" "+ c.getLastName();
            case 1:
                return c.getLocation();
            case 2:
                return c.getPhone();
            default:
                return null;
        }
        

    }

}
