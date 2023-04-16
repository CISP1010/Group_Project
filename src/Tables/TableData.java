package Tables;
import java.util.HashMap;
import java.util.Map;

/**
 * TableData class represents the data for restaurant tables, including table number,
 * number of seats, number of seats filled, and dishes ordered.
 */
public class TableData {
    private static HashMap<Integer, Table> tableData = new HashMap<>();

    /**
     * Initializes 20 empty tables to the tableData hashmap.
     */
    public TableData() {
        for (int i = 1; i <= 20; i++) {
            tableData.put(i, new Table(i));
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
        Table table = tableData.get(tableNumber);
        if (tableData.containsKey(tableNumber)) {
            sb.append("Table #: ").append(table.getNumber()).append(" | ")
            .append("Seats Filled: ").append(table.getSeatsTaken()).append(" | ").append("\n")
            .append("Dishes: ").append("\n").append(table.listDishes()).append("\n");
            return sb.toString();
        } else return "Table not found!";
    }

    /**
     * Returns a formatted string with data for all tables in the table data.
     *
     * @return a string with the table number, seats filled, and dishes ordered for all tables
     */
    public String listTables() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Table> entry : tableData.entrySet()) {
            Table table = entry.getValue();
            sb.append("Table #: ").append(table.getNumber()).append(" | ")
                    .append("Seats Filled: ").append(table.getSeatsTaken()).append("\n")
                    .append("Dishes:").append("\n").append(table.listDishes()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if the specified table has 4 or more seats filled, false otherwise.
     *
     * @param tableNumber the number of the table to check for filling
     * @return true if the specified table is filled, false otherwise
     * @throws RuntimeException if the specified table number is not found in the table data
     */
    public boolean isFilled(int tableNumber) {
        Table table = tableData.get(tableNumber);
        if (tableData.containsKey(tableNumber)) {
            return table.getSeatsTaken() >= 4;
        }else throw new RuntimeException("Table not found.");
        
    }

    /**
     * Returns the number of empty seats at the specified table.
     *
     * @param tableNumber the number of the table
     * @return the number of empty seats
     * @throws RuntimeException if the table does not exist
     */
    public int getEmptySeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return 4 - table.getSeatsTaken();
        } else throw new RuntimeException("Table not found.");
    }

    /**
     * Returns the number of filled seats at the specified table.
     *
     * @param tableNumber the number of the table
     * @return the number of filled seats
     * @throws RuntimeException if the table does not exist
     */
    public int getFilledSeats(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return table.getSeatsTaken();
        } else throw new RuntimeException("Table not found!");
    }

    /**
     * Returns a string containing the dishes ordered at the specified table.
     *
     * @param tableNumber the number of the table
     * @return a string containing the dishes ordered or returns Table not found if Table doesn't exist.
     */
    public String getDishes(int tableNumber) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            return table.listDishes();
        }else return "Table not found!";
    }

    /**
     * Adds a dish to the order at the specified seat at the specified table.
     *
     * @param tableNumber the number of the table
     * @param seatNumber  the number of the seat
     * @param dish        the name of the dish or returns Table not found if Table doesn't exist.
     */
    public void addDish(int tableNumber, int seatNumber, String dish) {
        if (tableData.containsKey(tableNumber)) {
            Table table = tableData.get(tableNumber);
            table.addDish(seatNumber, dish);
            tableData.put(tableNumber, table);
        }else System.out.println("Table not found");
    }

    /**
     * Returns the table bill from the tableData hashmap
     *
     * @param tableNumber the number of the table
     * @return The total bill of the table from the tableData hashmap
     */
    public double getTotal(int tableNumber){
        Table table = tableData.get(tableNumber);
        return table.getTotal();
    }
}