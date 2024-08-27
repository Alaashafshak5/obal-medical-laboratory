/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Entities.Analysis;

/**
 *
 * @author lenovo
 */
public class ResultsTableModel extends AbstractGuiTableModels<Analysis> {

    private final String[] columnNames = {"Test Name ", " unit ","reference value","result"};
    private final Class[] columnClasses = {String.class, String.class,String.class,Float.class};
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

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
        Analysis a = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getName();
            case 1:
                return a.getUnit();
            case 2:
                return a.getValue();
            case 3:
                return a.getResult();
            default:
                return null;
        }
    }
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Analysis a = list.get(rowIndex);
       switch (columnIndex) {
            case 3:
                a.setResult(Float.valueOf((String)aValue));
            default:
                return ;
        }
       
   }

}
