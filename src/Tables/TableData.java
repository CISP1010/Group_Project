package Tables;

import java.util.HashMap;
import java.util.Map;



/**
 * TableData class represents the data for restaurant tables, including table number,
 * number of seats, number of seats filled, and dishes ordered.
 */

public class TableData {
    /**
     * A hash map that maps table objects to table numbers
     */
    private static HashMap<Integer, Table> tableData = new HashMap<>();

    /**
     * Initializes 20 empty tables to the tableData hashmap.
     */
    public TableData() {
        int i = 1; //This integer increments to create 20 tables
        for (i = 1; i <= 20; i++) { //increments i to create 20 tables
            tableData.put(i, new Table(i)); //Adds a new table to the tableData hashmap
        }
    }

    /**
     * Returns the data for a specified table as a formatted string.
     *
     * @param tableNumber the number of the table to get data for
     * @return a string with the table number, seats filled, and dishes ordered; or returns Table not found if Table doesn't exist.
     */
    public String getTableData(int tableNumber) {
        StringBuilder sb = new StringBuilder();
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            sb.append("Table #: ").append(table.getNumber()).append(" | ") //appends the table data to the string builder
                    .append("Seat Filled: ").append(table.getSeatsFilled()).append(" | ").append("\n")
                    .append(table.getTableDishes()).append("\n");
            return sb.toString(); //returns the string builder as a string
        } else return "Table not found!"; //returns Table not found if the table doesn't exist
    }

    /**
     * Returns a formatted string with data for all tables in the table data.
     *
     * @return a string with the table number, seats filled, and dishes ordered for all tables
     */
    public String listTables() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Table> entry : tableData.entrySet()) { //loops through the table data
            Table table = entry.getValue(); //gets each table
            sb.append("Table #: ").append(table.getNumber()).append(" | ") //appends the table data to the string builder
                    .append("Seat Filled: ").append(table.getSeatsFilled()).append("\n")
                    .append(table.getTableDishes()).append("\n");
        }
        return sb.toString(); //returns the string builder as a string
    }

    /**
     * Returns true if the specified table has 4 or more seats filled, false otherwise.
     *
     * @param tableNumber the number of the table to check for filling
     * @return true if the specified table is filled, false otherwise
     * @throws RuntimeException if the specified table number is not found in the table data
     */
    public boolean isFilled(int tableNumber) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            return table.getSeatsFilled() >= 4; //returns true if the table has 4 or more seats filled
        } else throw new RuntimeException("Table not found."); //throws an exception if the table doesn't exist

    }

    /**
     * Returns the number of empty seats at the specified table.
     *
     * @param tableNumber the number of the table
     * @return the number of empty seats
     * @throws RuntimeException if the table does not exist
     */
    public int getEmptySeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            return 4 - table.getSeatsFilled(); //returns 4 minus the number of seats filled
        } else throw new RuntimeException("Table not found."); //throws an exception if the table doesn't exist
    }

    /**
     * Returns the number of filled seats at the specified table.
     *
     * @param tableNumber the number of the table
     * @return the number of filled seats
     * @throws RuntimeException if the table does not exist
     */
    public int getFilledSeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            return table.getSeatsFilled(); //returns the number of seats filled
        } else throw new RuntimeException("Table not found!"); //throws an exception if the table doesn't exist
    }

    /**
     * Returns a string containing the dishes ordered at the specified table.
     *
     * @param tableNumber the number of the table
     * @return a string containing the dishes ordered or returns Table not found if Table doesn't exist.
     */
    public String getDishes(int tableNumber) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            return table.getTableDishes(); //returns the dishes ordered at the table
        } else return "Table not found!"; //returns Table not found if the table doesn't exist
    }

    /**
     * Adds a dish to the order at the specified seat at the specified table.
     *
     * @param tableNumber the number of the table
     * @param seatNumber  the number of the seat
     * @param dishNum     the number of the dish or returns Table not found if Table doesn't exist.
     */
    public void addDish(int tableNumber, int seatNumber, int dishNum) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            table.addDish(seatNumber, dishNum); //adds the dish to the table object at the specified seat
            tableData.put(tableNumber, table); //adds the table object to the tableData hashmap
        } else System.out.println("Table not found"); //prints Table not found if the table doesn't exist
    }

    /**
     *Clears the dishes at a specified seat at a specified table
     * @param tableNumber the specified table number
     * @param seatNumber the seat number at that table
     */

    /**
     * @todo Check if table object needs removed priot to adding to hashmap
     * @body
     */
    public void clearSeat(int tableNumber, int seatNumber) {
        if (tableData.containsKey(tableNumber)) { //checks if the table exists
            Table table = tableData.get(tableNumber); //gets the table
            table.clearSeat(seatNumber); //clears the seat at the specified table
            tableData.put(tableNumber, table); //adds the table object to the tableData hashmap
        } else System.out.println("Table not found");
    }

    /**
     * Returns the table bill from the tableData hashmap
     *
     * @param tableNumber the number of the table
     * @return The total bill of the table from the tableData hashmap
     */
    public double getTotal(int tableNumber) {
        Table table = tableData.get(tableNumber); //gets the table
        return table.getTotal(); //returns the total bill of the table
    }

    /**
     * Returns a formatted string with the numbers of all empty tables
     * @return a string listing the numbers of all empty tables
     */
    public String getEmptyTables() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Table> entry : tableData.entrySet()) { //loops through the table data
            Table table = entry.getValue(); //gets each table
            if (table.getSeatsFilled() == 0) { //checks if the table is empty
                sb.append("Table #: ").append(table.getNumber()).append(" EMPTY ").append("\n"); //appends the table data to the string builder
            }
        }return sb.toString(); //returns the string builder as a string
    }
}