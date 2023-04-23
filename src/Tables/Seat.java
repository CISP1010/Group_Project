package Tables;

import java.util.ArrayList;

/**
 * @todo Seats.java add javadoc info
 * @body add javadoc info
 */
public class Seat {
    private int tableNumber;
    private int seatNumber;
    private ArrayList<String> dishes = new ArrayList<>();
    public Seat(int seatNumber, String dish) {
        this.seatNumber = seatNumber;
        dishes.add(dish);
    }
    public void addDish(int seat, String dish) {
        this.seatNumber = seat;
        dishes.add(dish);
    }

    public String listDishes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seat: ").append(seatNumber).append(" Dishes: ");
        for(String dish : dishes) {
            sb.append(dish).append(", ");
        }
        return sb.toString();
    }

}
