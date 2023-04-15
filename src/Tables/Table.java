package Tables;
import java.util.HashMap;
import java.util.Map;
/**
 * This class represents a table in a restaurant with a number, seats filled, and dishes
 */
public class Table {
    private int tableNumber;
    private HashMap<Integer, String> seatDishes = new HashMap<>();
    boolean filled;

    /**
     * Constructs a new Tables.Table object with the given table number and initializes the dishes HashMap with empty strings.
     *
     * @param tableNumber the table number
     */
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        for (int i = 1; i <= 4; i++) {
            seatDishes.put(i, "");
        }
    }

    /**
     * Returns the table number.
     *
     * @return the table number
     */
    public int getNumber() {
        return tableNumber;
    }

    /**
     * Sets the table's filled status to the given boolean value.
     *
     * @param filled the filled status of the table
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Returns whether the table is currently filled.
     *
     * @return true if the table is filled, false otherwise
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Returns the number of seats taken at the table.
     *
     * @return the number of seats taken at the table
     */
    public int getSeatsTaken() {
        int count = 0;
        for (Map.Entry<Integer, String> entry : seatDishes.entrySet()) {
            String dish = entry.getValue();
            if(!dish.isEmpty()){
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a dish to the table at the given seat.
     *
     * @param seat the seat number
     * @param dish the name of the dish
     */
    public void addDish(int seat, String dish) {
        seatDishes.put(seat, dish);
    }

    /**
     * Returns a string representation of the dishes at the table.
     *
     * @return a string representation of the dishes at the table
     */
    public String listDishes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : seatDishes.entrySet()) {
            Integer seat = entry.getKey();
            String dish = entry.getValue();
            if (!dish.isEmpty()) {
                sb.append("Seat:").append(seat).append(" | ").append(dish).append("\n");
            } else sb.append("Seat:").append(seat).append(" | ").append("EMPTY").append("\n");
        }
        return sb.toString();
    }
}
