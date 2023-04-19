/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Orders;

import Menu.MenuData;
import Tables.TableData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderData {

    // enum for 3 order options delivery, dine out, or dine in, which needs
    // table and seat assign in orderMenu I guess  - not going to do this unless I have time

    //creates a hash map to take however many food items are in the order from the orderMenu
    MenuData menuData = new MenuData();
    TableData tableData = new TableData();

    private static HashMap<Integer, Order> orderData = new HashMap<>();

    ArrayList<String> orderItems = new ArrayList<>();  /// to store food item choices for entry into order object
    ///need a way to clear this between order entries

    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public OrderData() {
    }

    public void addOrder(int orderNumber, Order order) {
        orderData.put(orderNumber, order);
    }

    public String searchOrder(int orderNumber) {
        StringBuilder sb = new StringBuilder();
        if (orderData.containsKey(orderNumber)) {
            Order order = orderData.get(orderNumber);
            sb.append("Order Number: ").append(orderNumber).append("\n")
                    .append("Customer Name: ").append(order.getCustName()).append("\n")
                    .append("Order Type: ").append(order.getOrderType()).append("\n");

            if (order.getOrderType().equals("Delivery")) {
                sb.append("Delivery Address: ").append(order.getDeliveryAddress()).append("\n")
                        .append("Delivery Phone: ").append(order.getPhone()).append("\n")
                        .append("Delivery Notes: ").append(order.getDeliveryNotes()).append("\n")
                        .append("Order Items: ").append(order.getOrderItems()).append("\n");
            }

            if (order.getOrderType().equals("Dine In")) {
                sb.append("Order Items: \n")
                        .append("Table Number: ").append(order.getTableNum()).append("\n")
                        .append("Order Items: ");
                for (String item : orderItems) { //for each item in the orderItem arraylist
                    sb.append(tableData.getDishes(order.getTableNum())).append("\n");
                    sb.append(item).append(", ");
                }
            } else return '\n' + "Order " + orderNumber + " not found" + '\n';
        }return sb.toString();
    }
//should list all orders from hashmap and print out their info

    public void listOrders(){
        for(Map.Entry<Integer, Order> entry : orderData.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }
}
