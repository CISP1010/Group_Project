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
        this.orderType = orderType;
        this.orderItem = orderItems;
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
        StringBuilder sb = new StringBuilder();
        for (Integer itemNum : orderItem) { //for each item in the orderItem arraylist
            sb.append(menuData.getName(itemNum)).append(", "); //print the item name from the menuData hashmap
        }
        return sb.toString();
    }

    public String getOrderType(){
        return orderType;
    }

    public int newOrderNum(){
        orderNum++;
        return orderNum;
    }

}
