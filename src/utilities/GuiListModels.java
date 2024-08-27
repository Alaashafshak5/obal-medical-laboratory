/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author hover
 * @param <E>
 */
public class GuiListModels<E> extends AbstractListModel<E> {

    private final List<E> list = new ArrayList();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public E getElementAt(int index) {
        return list.get(index);
    }

    public void add(E a) {
        list.add(a);
        fireIntervalAdded(this, list.size() - 1, list.size() - 1);
    }

    public boolean contains(E a) {
        return list.contains(a);
    }

    public void set(List<E> ls) {
        list.clear();
        list.addAll(ls);
        fireContentsChanged(this, 0, list.size() - 1);
    }

    public List<E> getAll() {
        List<E> ls = new ArrayList();
        ls.addAll(list);
        return ls;
    }
    
    public void removeAll(){
        int size = list.size();
        list.clear();
        fireContentsChanged(this, 0, size-1);
    }
    
    public void remove(E a){
        int index = list.indexOf(a);
        list.remove(index);
        fireContentsChanged(this, index, index);
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
}
