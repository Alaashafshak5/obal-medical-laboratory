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
public class AnalysisTableModel extends AbstractGuiTableModels<Analysis> {

    private final String[] columnNames = {"Test Name ", " Price "};
    private final Class[] columnClasses = {String.class, float.class};

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
                return a.getDefaultPrice();
            default:
                return null;
        }
    }

}
