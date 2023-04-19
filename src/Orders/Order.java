package Orders;

import Menu.MenuData;

import java.util.ArrayList;

//gp through and change orderItem and orderItems
public class Order {
    private String deliveryAddress;
    private String deliveryPhone;
    private String deliveryNotes;
    private int tableNum;
    private int orderNum = 1111;
    private String custName;
    private ArrayList<Integer> orderItem;
    private String orderType;
    MenuData menuData = new MenuData();

    public Order(int orderNum, String custName, ArrayList<Integer> orderItems, String orderType, int tableNum, String deliveryAddress, String deliveryPhone, String deliveryNotes) {
        this.orderNum = orderNum;;
        this.custName = custName;
        this.orderItem = orderItems;
        this.orderType = orderType;
        this.tableNum = tableNum;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhone = deliveryPhone;
        this.deliveryNotes = deliveryNotes;
    }

    //setters
    public void setNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setOrderItem(ArrayList<Integer> orderItems){
        this.orderItem = orderItems;

    }

    public void setOrderType(String orderType){
        this.orderType = orderType;
    }

    //getters

    public int getOrderNumber(){
        return orderNum;
    }

    public String getDeliveryAddress(){
        return deliveryAddress;
    }

    public String getPhone(){
        return deliveryPhone;
    }

    public String getDeliveryNotes(){
        return deliveryNotes;
    }

    public int getTableNum(){
        return tableNum;
    }

    public String getCustName(){
        return custName;
    }

    public String getOrderItems(){ //this is returning the arraylist of order items as a string
        for (Integer itemNum : orderItem) { //for each item in the orderItem arraylist
            return menuData.getName(itemNum); //print the item name from the menuData hashmap
        }return "Item not found"; //if the item is not found in the menuData hashmap
    }// This is definitely a dirty hack. If the item numbers get changed in the item menu (Like by removing an item from the menu that is not the last item) , this will change every order's value....
    // need to find a way to make permanent item numbers that are not affected by changes to the menu

    public String getOrderType(){
        return orderType;
    }

    public int newOrderNum(){
        orderNum++;
        return orderNum;
    }

}
