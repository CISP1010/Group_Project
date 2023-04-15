package Orders;

import Menu.MenuItem;

public class Order {
    private double order;
    private int tableNum;
    private int seatNum;
    private String foodItem;
    private MenuItem menuItem;
    public Order(double order, MenuItem menuItem, int tableNum, int seatNum) {
        this.order = order;
        this.tableNum = tableNum;
        this.seatNum = seatNum;
        this.menuItem = menuItem;
        //menuItem is the object created from MenuItem() in MenuItem.java file
    }

    public int getTableNum(){
        return tableNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
}
