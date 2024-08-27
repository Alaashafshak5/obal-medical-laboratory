/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Entities.Doctor;

/**
 *
 * @author User
 */
public class DoctorsTableModel extends AbstractGuiTableModels<Doctor> {

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
        Doctor d = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return d.getFirstName() +" "+ d.getLastName();
            case 1:
                return d.getLocation();
            case 2:
                return d.getPhone();
            default:
                return null;
        }
        

    }

}