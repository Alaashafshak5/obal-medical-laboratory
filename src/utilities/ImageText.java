/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import Entities.Order;
import javax.swing.Icon;

/**
 *
 * @author lenovo
 */
public class ImageText {
 
     private Order order;
     private Icon image;

    public ImageText(Order order, Icon image) {
        this.order = order;
        this.image = image;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

   

    

    
     
}
