package Orders;

import Menu.MenuData;

import java.util.ArrayList;

/**
 *  The Order class creates an object that represents a food order with parameters for information
 *   about the customer, the order itself, and the delivery details.
 */


public class Order {
    /**
     * These are private instance variable for the delivery address, phone #, notes, table #
     * customer name, order item, and the order type (dine in, delivery, or take away.
     */
    private String deliveryAddress;
    private String deliveryPhone;
    private String deliveryNotes;
    private int tableNum;
    private int orderNum = 1111;
    private String custName;
    private ArrayList<Integer> orderItem;
    private String orderType;
    /**
     * creates a hashmap that contains the offered menu items
     */
    MenuData menuData = new MenuData();

    /**
     * creates an order object with parameters for the order number, customer name, items in the order
     * the type of arder, the table number - if dining in - , the delivery address and phone # -
     * if for delivery, and any notes.
     * @param orderNum contains the order number
     * @param custName contains the customer name
     * @param orderItems contains the order's items
     * @param orderType indicates whether the order is to be served in-house
     * @param tableNum takes the table number, if applicable
     * @param deliveryAddress takes the deliver address if applicable
     * @param deliveryPhone takes the deliver phone number if applicable
     * @param deliveryNotes takes any miscellaneous notes any notes for the delivery (e.g. gate code, special instructions)
     */
    public Order(int orderNum, String custName, ArrayList<Integer> orderItems, String orderType, int tableNum, String deliveryAddress, String deliveryPhone, String deliveryNotes) {
        this.orderNum = orderNum;;
        this.custName = custName;
        this.orderType = orderType;
        this.orderItem = orderItems;
        this.tableNum = tableNum;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhone = deliveryPhone;
        this.deliveryNotes = deliveryNotes;
    }

    /**
     * this is a setter for the order number
     * @param orderNum takes the order number
     */
    public void setNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * this is a setter for the customer name
     * @param custName takes the customer name
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * this is a setter for the order items arraylist
     * @param orderItems this is an array list that takes ordered items
     */
    public void setOrderItem(ArrayList<Integer> orderItems){
        this.orderItem = orderItems;

    }

    /**
     * this is a setter for the order type
     * @param orderType contains information about whether the meal is to be eaten in, delivered, or picked up
     */
    public void setOrderType(String orderType){
        this.orderType = orderType;
    }

    /**
     * this is a getter for the order number
     * @return it returns the order number
     */

    public int getOrderNumber(){
        return orderNum;
    }

    /**
     * this is a getter for the delivery address
     * @return it returns the delivery address
     */
    public String getDeliveryAddress(){
        return deliveryAddress;
    }

    /**
     * gets the customer phone number
     * @return returns the customer phone number
     */
    public String getPhone(){
        return deliveryPhone;
    }

    /**
     * gets the delivery notes
     * @return returns the deliver notes
     */
    public String getDeliveryNotes(){
        return deliveryNotes;
    }

    /**
     * gets the table number
     * @return returns the table number
     */
    public int getTableNum(){
        return tableNum;
    }

    /**
     * gets the customer name
     * @return returns the customer name
     */
    public String getCustName(){
        return custName;
    }

    /**
     * This builds a list of the ordered item and returns that list as a string
     * @return returns the build string of ordered items from the menu item hashmap of existing menu items
     */
    public String getOrderItems(){
        StringBuilder sb = new StringBuilder();
        for (Integer itemNum : orderItem) {
            sb.append(menuData.getName(itemNum)).append(", ");
        }
        return sb.toString();
    }

    /**
     * this gets the ordertype
     * @return returns the ordertype
     */
    public String getOrderType(){
        return orderType;
    }

    /**
     * this is a utility function to automatically increment the order number
     * @return returns the order number
     */
    public int newOrderNum(){
        orderNum++;
        return orderNum;
    }

}
