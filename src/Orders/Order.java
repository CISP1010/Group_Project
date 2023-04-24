package Orders;

import Menu.MenuData;

import java.util.ArrayList;

/**
 *  The Order class creates an object that represents a food order with parameters for information
 *   about the customer, the order itself, and the delivery details.
 */
@SuppressWarnings("FieldMayBeFinal")
public class Order {
    /**
     * These are private instance variable for the delivery address, phone #, notes, table #
     * customer name, order item, and the order type (dine in, delivery, or take away.)
     */
    private String deliveryAddress;
    private String deliveryPhone;
    private String deliveryNotes;
    private int tableNum;
    private String custName;
    private ArrayList<Integer> orderItem;
    private String orderType;
    /**
     * creates a hashmap that contains the offered menu items
     */
    MenuData menuData = new MenuData();

    /**
     * creates an order object with parameters for the order number, customer name, items in the order
     * the type of order, the table number - if dining in - , the delivery address and phone # -
     * if for delivery, and any notes.
     * @param custName contains the customer name
     * @param orderItems contains the order's items
     * @param orderType indicates whether the order is to be served in-house
     * @param tableNum takes the table number, if applicable
     * @param deliveryAddress takes the delivery address if applicable
     * @param deliveryPhone takes the delivery phone number if applicable
     * @param deliveryNotes takes any miscellaneous notes any notes for the delivery (e.g. gate code, special instructions)
     */
    public Order(String custName, ArrayList<Integer> orderItems, String orderType, int tableNum, String deliveryAddress, String deliveryPhone, String deliveryNotes) {
        this.custName = custName; //Stores the passed customer name
        this.orderType = orderType; //Stores the passed order type
        this.orderItem = orderItems; //Stores the passed order items
        this.tableNum = tableNum; //Stores the passed table number
        this.deliveryAddress = deliveryAddress; //Stores the passed delivery address
        this.deliveryPhone = deliveryPhone; //Stores the passed delivery phone number
        this.deliveryNotes = deliveryNotes; //Stores the passed delivery notes
    }

    /**
     * this is a getter for the delivery address
     * @return it returns the delivery address
     */
    public String getDeliveryAddress(){
        return deliveryAddress; //Returns the delivery address
    }

    /**
     * gets the customer phone number
     * @return returns the customer phone number
     */
    public String getPhone(){
        return deliveryPhone; //Returns the delivery phone number
    }

    /**
     * gets the delivery notes
     * @return returns the delivery notes
     */
    public String getDeliveryNotes(){
        return deliveryNotes; //Returns the delivery notes
    }

    /**
     * gets the table number
     * @return returns the table number
     */
    public int getTableNum(){
        return tableNum; //Returns the table number
    }

    /**
     * gets the customer name
     * @return returns the customer name
     */
    public String getCustName(){
        return custName; //Returns the customer name
    }

    /**
     * This builds a list of the ordered item and returns that list as a string
     * @return returns the build string of ordered items from the menu item hashmap of existing menu items
     */
    public String getOrderItems(){
        StringBuilder sb = new StringBuilder();
        for (Integer itemNum : orderItem) { //Iterates through the order items
            sb.append(menuData.getName(itemNum)).append(", "); //Adds the item name to the string builder
        }
        return sb.toString(); //Returns the string builder as a string
    }

    public Integer[] getOrderItemNums(){
        Integer[] itemNums = new Integer[orderItem.size()];
        for (int i = 0; i < orderItem.size(); i++) {
            itemNums[i] = orderItem.get(i);
        }
        return itemNums;
    }

    /**
     * this gets the order-type
     * @return returns the order-type
     */
    public String getOrderType(){
        return orderType; //Returns the order type
    }

}
