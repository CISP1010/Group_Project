/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Orders;
import Menu.MenuItem;

import java.util.*;

public class OrderData {

    // enum for 3 order options delivery, dine out, or dine in, which needs
    // table and seat assign in orderMenu I guess  - not going to do this unless I have time

    //creates a hash map to take however many food items are in the order from the orderMenu

    private static HashMap<int, Order> orderData = new HashMap<>();

    ArrayList<String> orderItems = new ArrayList<>();  /// to store food item choices for entry into order object
                                                    ///need a way to clear this between order entries
    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public OrderData() {
    }

    public void addOrderItem(String orderItem){
        orderItems.add(orderItem);
    }
    // I forgot what I was doing with this public ArrayList<String> getOrderList() {
        //return orderItems;
    }

    }
    public void addOrder(int orderNum, orderItems orderItems, int orderType) {
        Order order = new Order(orderNum, orderItems[], orderType);    //is this how you pass an array list?
        orderData.put(orderNum, order);     ///this should pass order items to the array list, which then gets stored
                                            // in the order item, which then gets put into the hashmap


}

//should list all orders from hashmap and print out their info

    public void listOrders {
             System.out.printf
        orderData.forEach(orderNum, order) -> {
            System.out.printf       ////print out data for each hashmap element under headings

        }

        }
        }

        }
