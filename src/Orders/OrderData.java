/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Orders;

import java.util.HashMap;
import java.util.Map;

/**
 * @todo Clean up comments and javadoc
 * @body Remove unnecessary code comments and proofread/add javadoc info
 * /

/**
 * This class creates an order data object and provides methods to add, search, list, and remove orders.
 * It also provides methods to get a new order number and check if an order exists. It uses a hashmap to
 * store orders with the order number used as the key.
 */
public class OrderData {


    /**
     * This creates a hashmap to contain the orders
     */
    @SuppressWarnings("FieldMayBeFinal")
    private static HashMap<Integer, Order> orderData = new HashMap<>();
    /**
     * This is a private instance variable initialized to start the order numbers at 1000
     */
    private int orderNumber = 1000;

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
        orderData.put(orderNumber, order); //adds the order to the hashmap
    }

    /**
     * This method returns an order object from the orderData hashmap
     * @param orderNumber the order number to search for
     * @return returns the order object
     */
    public Order getOrder(int orderNumber) {
        return orderData.get(orderNumber);
    }

    /**
     * This method searches the orderData hashmap for an order number and, if found, build a string
     * of its information and returns that string
     * @param orderNumber this is the order number searched for
     * @return returns the built string of order information
     */
    public String searchOrder(int orderNumber) {
        StringBuilder sb = new StringBuilder();
        if (orderData.containsKey(orderNumber)) { //If the order number is found in the hashmap
            Order order = orderData.get(orderNumber); //Get the order object
            sb.append("Order Number: ").append(orderNumber).append("\n") //Build the string of order information
                    .append("Customer Name: ").append(order.getCustName()).append("\n")
                    .append("Order Type: ").append(order.getOrderType()).append("\n");

            if (order.getOrderType().equals("Delivery")) { //If the order is a delivery order
                sb.append("Delivery Address: ").append(order.getDeliveryAddress()).append("\n")
                        .append("Delivery Phone: ").append(order.getPhone()).append("\n")
                        .append("Delivery Notes: ").append(order.getDeliveryNotes()).append("\n")
                        .append("Order Items: ").append(order.getOrderItems()).append("\n");
            }

            if (order.getOrderType().equals("Pick up")){ //If the order is a pickup order
                sb.append("Delivery Phone: ").append(order.getPhone()).append("\n")
                    .append("Order Items: ").append(order.getOrderItems()).append("\n");
            }

            if (order.getOrderType().equals("Dine-in")) { //If the order is a dine-in order
                    sb.append("Table Number: ").append(order.getTableNum()).append("\n")
                        .append("Order Items: ").append(order.getOrderItems()).append("\n");
                }
            } else return '\n' + "Order " + orderNumber + " not found" + '\n'; //If the order number is not found
        return sb.toString();
    }

    /**
     * This method prints out all the information for all the orders in the orderData hashmap
     */
    public String listOrders(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Order> entry : orderData.entrySet()) { //For each order in the hashmap
            Integer key = entry.getKey();     //Get the order number
            Order value = entry.getValue();   //Get the order object

            String tableNum;
            if (value.getTableNum() <= 0) { //If the order is not a dine-in order
                sb.append(key).append(" => ").append(value.getCustName())
                        .append(", ") .append(value.getOrderType()).append(", ")
                        .append(value.getOrderItems()).append("\n"); //Build the string of order information
            } else {
                tableNum = "Table: " + value.getTableNum(); //If the order is a dine-in order
                sb.append(key).append(" => ").append(value.getCustName())
                        .append(", ").append(value.getOrderType()).append(", ")
                        .append(tableNum).append(", ")
                        .append(value.getOrderItems()).append("\n"); //Build the string of order information
            }
        }return sb.toString();
    }

    /**
     * This method checks if an order number exists in the orderData hashmap
     * @param orderNum The order number to be searched for
     * @return returns true if the order number is found
     */
    public boolean isOrder(int orderNum) { //Checks if an order number exists
        return orderData.containsKey(orderNum); //Returns true if the order number is found
    }

    /**
     * Removes the order object with this order number
     * @param orderNumber this is the order number of the order object to be removed
     */
    public void remOrder(int orderNumber) {
        if (isOrder(orderNumber)) { //If the order number exists
            orderData.remove(orderNumber); //Remove the order object
        }else System.out.println("Order " + orderNumber + " not found!"); //If the order number does not exist
    }

    /**
     * This method returns an order number and then increments the order number
     * @return the current value of the order number index
     */
    public int getNewOrderNum(){
        orderNumber++; //Increments the order number
        return orderNumber; //Returns the new order number
    }
}
