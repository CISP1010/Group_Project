package Orders;

import java.util.ArrayList;

//gp through and change orderItem and orderItems
public class Order {
    private int orderNum = 0;
    private String custName;
    private ArrayList<Integer> orderItem;

    private String orderType;

    ///add enum or array for delivery, pick up, dine in... dine in needs to trigger a table/seat assignment
   // then choice - 1 in the employee menu thing determines the entry and if 3 -> then enter a table and seat assignment else {}


    // default constructor

    public Order(){

    }

    // overloaded constructor for order with params for number, customer name, items in order, dine-in/delivery


    public Order(int orderNum, String custName, ArrayList orderItems, String orderType) {
        this.orderNum = orderNum;;
        this.custName = custName;
        this.orderItem = orderItems;
        this.orderType = orderType;
    }

    //setters
    public void setNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setOrderItem(String orderItem){
        this orderItem = orderItem;
    }

    public void setOrderType(String orderType){
        this.orderType = orderType;
    }

    //getters

    public int getNum(){
        return orderNum;
    }

    public String getCustName(){
        return custName;
    }

    public String getOrderItems(){ //change this to spit out the arraylist orderitems...
        return orderItem;
    }
    public String getOrderType(){
        return orderType;
    }

    public int assignNum(){
        orderNum++;
        return orderNum;
    }

}
