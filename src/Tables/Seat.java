package Tables;

import java.util.ArrayList;

/**
 * @todo Seats.java add javadoc info
 * @body add javadoc info
 * comments are complete
 */
public class Seat {
    private int tableNumber; //This int stores the table number
    private int seatNumber; //This int stores the seat number
    private ArrayList<String> dishes = new ArrayList<>(); //This array list stores the dishes ordered for the seat
    public Seat(int seatNumber, String dish) {
        this.seatNumber = seatNumber; //Sets the seat number to the passed value
        dishes.add(dish); //Adds the passed dish to the dishes array list
    }
    public void addDish(int seat, String dish) {
        this.seatNumber = seat; //Sets the seat number to the passed value
        dishes.add(dish); //Adds the passed dish to the dishes array list
    }

    public String listDishes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seat: ").append(seatNumber).append(" Dishes: "); //Adds the seat number and the dishes to the string builder
        for(String dish : dishes) { //Iterates through the dishes
            sb.append(dish).append(", "); //Adds the dish to the string builder
        }
        return sb.toString(); //Returns the string builder as a string
    }

}
