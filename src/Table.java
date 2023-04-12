import java.util.HashMap;
import java.util.Map;

public class Table {
    private int tableNumber;
    private int seatsFilled;
    private HashMap<Integer, String> dishes = new HashMap<>();
    boolean filled;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        for (int i = 1; i <= 4; i++) {
            dishes.put(i, "");
        }
    }

    public int getNumber() {
        return tableNumber;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setSeatsFilled(int seatsFilled) {
        this.seatsFilled = seatsFilled;
    }

    public int getSeatsTaken() {
        int count = 0;
        for (Map.Entry<Integer, String> entry : dishes.entrySet()) {
            Integer seat = entry.getKey();
            String dish = entry.getValue();
            if(!dish.isEmpty()){
                count++;
            }
        }
        return count;
    }

    public void addDish(int seat, String dish) {
        dishes.put(seat, dish);
    }

    public String listDishes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : dishes.entrySet()) {
            Integer seat = entry.getKey();
            String dish = entry.getValue();
            if (!dish.isEmpty()) {
                sb.append("Seat:").append(seat).append(" | ").append(dish).append("\n");
            } else sb.append("Seat:").append(seat).append(" | ").append("EMPTY").append("\n");
        }
    return sb.toString();
    }
}
