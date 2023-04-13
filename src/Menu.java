/**
 * The Menu class represents the menu and contains the Menu constructor used to add items to the MenuData hashMap.
 */
public class Menu {
    
    private String item;
    private String type;
    private double price;
    private boolean availability; 
    /**
     * Constructs a new Menu object with the specified item, type, price, and availability.
     *
     * @param item the item name
     * @param type the type of menu item
     * @param price the numerical value representing the cost of the item
     * @param availability the representation of if the item is available to order
     */   
    public Menu(String item, String type, double price, boolean availability){
        this.item = item;
        this.type = type;
        this.price = price;
        this.availability = availability;
    }
     /**
     * Returns the name of the item.
     *
     * @return item name
     */
    public String getItem(){
        return item;
    }
    
    /**
     * Sets the type of menu item to the specified value.
     *
     * @param item the new name of the item
     */
    public void setItem(String item){
        this.item = item;
    }
    
    /**
     * Returns the type of item.
     *
     * @return type of item
     */
    public String getType(){
        return type;
    }
     /**
     * Sets the type of menu item to the specified value.
     *
     * @param type the new type of item
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Returns the numerical value that was input when the item was added.
     *
     * @return the price of the menu item
     */
    public double getPrice(){
        return price;
    }
    
    /**
     * Sets the price of the menu item to the specified value.
     *
     * @param price the numerical value entered by the user that represents the cost of the item to customer
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * Returns the availability that was input by the user.
     *
     * @return true if the item is available, false if it is not
     */
    public boolean getAvailability(){
        return availability;
    }
    /**
     * Sets the availability of the menu item to the specified value.
     *
     */
    public void setAvailability(){
        this.availability = false;
    }
    /**
     * Returns a string representation of the menu in the format:
     * "item: [item] | type: [type] | price: [cost] | availability: [availability]"
     *
     * @return a string representation of the menu
     */
    @Override
    public String toString(){
        return "item: " + item + ", type: " + type + ", price: " + price + ", availability: " + availability;
    }
    
}
