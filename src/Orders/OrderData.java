/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Orders;
import Menu.MenuItem;

import java.util.*;

public class OrderData {
    private static HashMap<Double, Order> orderData = new HashMap<>();

    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public OrderData() {
    }

    public void addOrder(double orderNum, MenuItem menuItem,int tableNum,int seatNum) {
        Order order = new Order(orderNum, menuItem, tableNum, seatNum);
        orderData.put(orderNum, order);
    }
}

