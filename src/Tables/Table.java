package Tables;

import Helpers.Round;
import Menu.MenuData;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a table in a restaurant with information
 * about the table's number, seats, dishes, and bill.
*/
@SuppressWarnings("FieldMayBeFinal")
public class Table {

    /**
     * creates a new hashmap for table seats and the dishes ordered at those seats
     */
    private HashMap<Integer, String> tableSeats = new HashMap<>();
    /**
     * creates a hash map to hold existing menu items
     */
    private final int tableNumber; //This integer holds the table number
    private MenuData menuData = new MenuData(); //Creates a new Menu.MenuData object

    /**
     * Constructs a new table object with a table number
     *
     * @param tableNumber the table number
     */
    public Table(int tableNumber) {
        this.tableNumber = tableNumber; //Sets the table number to the given table number
    }

    /**
     * Returns the table number.
     *
     * @return the table number
     */
    public int getNumber() {
        return tableNumber; //returns the table number
    }

    /**
     * Adds a dish to the table at the given seat.
     *
     * @param seat the seat number
     * @param dishNumber the name of the dish
     */
    public void addDish(int seat, int dishNumber) {
        StringBuilder sb = new StringBuilder();
        if(tableSeats.get(seat) != null){ //if the seat is not empty
            sb.append(tableSeats.get(seat)).append(", "); //append the dishes already ordered at the seat
        }
        sb.append(menuData.getName(dishNumber)); //append the new dish
        tableSeats.remove(seat); //remove the old dish object
        tableSeats.put(seat, sb.toString()); //add the new dish object
    }

    /**
     * clears the dishes at a given seat
     * @param seat the seat number
     */
    public void clearSeat(int seat) {
        StringBuilder sb = new StringBuilder();
        if (tableSeats.get(seat) != null) { //if the seat is not empty
            sb.append(" "); //set the dish to empty
            tableSeats.remove(seat); //remove the old dish object
            tableSeats.put(seat, sb.toString()); //add the new dish object
        }
    }

    /**
     * Boolean method to check if a seat is empty
     * @param seat the seat number
     * @return returns true if the seat is empty
     */
    public boolean isSeatEmpty(int seat){
        return tableSeats.get(seat) == null;
    }

    /**
     * Returns a string representation of the dishes at the table.
     *
     * @return returns a list of dishes ordered at the table
     */
    public String getTableDishes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : tableSeats.entrySet()) { //loops through the table seats
            int seat = entry.getKey(); //gets the seat number
            String dish = entry.getValue(); //gets the dish ordered at the seat
            sb.append("Seat ").append(seat).append(": ").append(dish).append("\n"); //appends the seat number and dish to the string builder
        }
        return sb.toString(); //returns the string builder as a string
    }

    /**
     * Returns a string representation of the dishes at a seat.
     * @param seat the seat number
     * @return returns a list of dishes ordered at the seat
     */
    public String getSeatDishes(int seat){
        return tableSeats.get(seat);
    }

    /**
     *Returned the number of seats filled at a table
     * @return the number of seats filled
     */
    public int getSeatsFilled(){
        return tableSeats.size(); //returns the number of seats filled
    }


    /**
     * Returns the total bill due for the table.
     *
     * @return the total bill for the table
     */
    public double getTotal(){
        MenuData menuData = new MenuData(); //Creates a new Menu.MenuData object
        double price = 0; //This double holds the total price of the bill
        for (Map.Entry<Integer, String> entry : tableSeats.entrySet()){ //loops through the table seats
            for(String dish : entry.getValue().split(", ")){ //loops through the dishes ordered at the seat
                int itemNum = menuData.getItemNum(dish); //gets the item number of the dish
                price = price + menuData.getPrice(itemNum); //adds the price of the dish to the total price
            }
        }return Round.round(price,2); //returns the total price rounded to 2 decimal places
    }
}
