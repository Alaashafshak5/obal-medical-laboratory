/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author lenovo
 */
public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

    
        ImageText tmp=(ImageText)value;
        String label;
        if(tmp.getOrder().isPaid())
         label="<html>"+tmp.getOrder().getClient().getFirstName()+" "+tmp.getOrder().getClient().getLastName()+
                 "<p style='font-size:8px'" +">status: " +"Paid ";
        else
            label="<html>"+tmp.getOrder().getClient().getFirstName()+" "+tmp.getOrder().getClient().getLastName()+
                 "<p style='font-size:8px'" +">status: " +"Not Paid ";
        setIcon(tmp.getImage());
        setText(label);
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(true);
        setFont(list.getFont());
        return this;
        
    }

    
}