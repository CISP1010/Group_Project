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

/**
 * @todo Clean up comments and javadoc
 * @body Remove unneccesarry code comments and proofread/add javadoc info
 *  // enum for 3 order options delivery, dine out, or dine in, which needs
 *     // table and seat assign in orderMenu I guess  - not going to do this unless I have time - I'm moving this
 *
 *     enum comments here because I don't want to erase it yet, in case we're going to do it...but I'm cleaning up
 *     the body commments  - greg
 */

/**
 * This class creates an order data object and provides methods to add, search, list, and remove orders.
 * It also provides methods to get a new order number and check if an order exists. It uses a hashmap to
 * store orders with the order number used as the key.
 */
public class OrderData {


    /**
     * This creates a hashmap to contain information about available menu items
     */
    MenuData menuData = new MenuData();
    /**
     * This creates a hashmap to container information about table availability
     */
    TableData tableData = new TableData();
    /**
     * This creates a hashmap to contain the orders
     */
    private static HashMap<Integer, Order> orderData = new HashMap<>();
    /**
     * This is a private instance variable initialized to start the order numbers at 1000
     */
    private int orderNumber = 1000;

    /**
     * creates an arraylist to hold orderitems
     */
    ArrayList<String> orderItems = new ArrayList<>();

    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public OrderData() {
    }

    /**
     * This adds an order object to the orderData hashmap
     * @param orderNumber takes the order number key
     * @param order takes the order object
     */
    public void addOrder(int orderNumber, Order order) {
        orderData.put(orderNumber, order);
    }

    /**
     * This method searches the orderData hashmap for an order number and, if found, build a string
     * of its information and returns that string
     * @param orderNumber this is the ordernumber searched for
     * @return returns the built string of order information
     */
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

            if (order.getOrderType().equals("Pick up")){
                sb.append("Delivery Phone: ").append(order.getPhone()).append("\n")
                    .append("Order Items: ").append(order.getOrderItems()).append("\n");
            }

            if (order.getOrderType().equals("Dine-in")) {
                    sb.append("Table Number: ").append(order.getTableNum()).append("\n")
                        .append("Order Items: ").append(order.getOrderItems()).append("\n");
                }
            } else return '\n' + "Order " + orderNumber + " not found" + '\n';
        return sb.toString();
    }

    /**
     * This function prints out all the information for all the orders in the orderData hashmap
     */

    public void listOrders(){
        for(Map.Entry<Integer, Order> entry : orderData.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }

    /**
     * This method checks if an order number exists in the orderData hashmap
     * @param orderNum
     * @return returns true if the order number is found
     */
    public boolean isOrder(int orderNum) {
        boolean loop = true;
        return orderData.containsKey(orderNum);
    }

    /**
     * This method returns the order object with this order number if it exists
     * @param orderNum The order number of the order object searched for
     * @return returns the order object if found, null if not
     */
    public Order getOrder(int orderNum){
        if (isOrder(orderNum)){
            return orderData.get(orderNumber);
        }else return null;
    }

    /**
     * Removes the order object with this order number
     * @param orderNumber this is the order number of the order object to be removed
     */
    public void remOrder(int orderNumber) {
        if (isOrder(orderNumber)) {
            orderData.remove(orderNumber);
        }
    }

    /**
     * This method returns an order number and then increments the order number
     * @return the current value of the order number index
     */
    public int getNewOrderNum(){
        orderNumber++;
        return orderNumber;
    }
}
