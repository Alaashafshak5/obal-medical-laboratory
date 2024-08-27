/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lenovo
 */
public abstract class AbstractGuiTableModels<E> extends AbstractTableModel {

    protected final List<E> list = new ArrayList();

    @Override
    public int getRowCount() {
        return list.size();
    }

    public void add(int row, E e) {
        list.add(row, e);
        fireTableRowsInserted(row, row);
    }

    public void add(E e) {
        add(getRowCount(), e);
    }
     public boolean contains(E a) {
        return list.contains(a);
    }

    public void remove(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public int remove(E e) {
        int index = list.indexOf(e);
        remove(index);
        return index;
    }

    public E get(int row) {
        return list.get(row);
    }

    public void add(List<E> ls) {
        for (E e : ls) {
            add(e);
        }
    }

    public void set(List<E> ls) {
        list.clear();
        list.addAll(ls);
        fireTableDataChanged();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public void removeAll(){
        list.clear();
        fireTableDataChanged();
    }
   
    public List<E> getAll() {
        List<E> ls = new ArrayList();
        ls.addAll(list);
        return ls;
    }
   
    
    public void update(E e){
        int index = list.indexOf(e);
        fireTableRowsUpdated(index, index);
    }
}