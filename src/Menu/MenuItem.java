package Menu;




/**
 * The MenuItem class represents the menu and contains the MenuItem constructor used to add items to the MenuData hashMap.
 */

/**
* @todo MenuItem.java javadoc
* @body proofread javadoc info and add missing entries
 * Comments are complete
*/
public class MenuItem implements Cloneable{

    private String item; //This string stores the name of the item
    private String type; //This string stores the type of the item
    private int number; //This int stores the number of the item
    private double price; //This double stores the price of the item
    private boolean availability; //This boolean stores the availability of the item
    /**
     * Constructs a new MenuItem object with the specified item, type, price, and availability.
     *
     * @param item the item name
     * @param type the type of menu item
     * @param price the numerical value representing the cost of the item
     * @param availability the representation of if the item is available to order
     */   
    public MenuItem(int number, String item, String type, double price, boolean availability){
        this.number = number; //Stores the passed number of the item
        this.item = item; //Stores the passed name of the item
        this.type = type; //Stores the passed type of the item
        this.price = price; //Stores the passed price of the item
        this.availability = availability; //Stores the passed availability of the item
    }
     /**
     * Returns the name of the item.
     *
     * @return item name
     */
    public int getItemNum(){
        return number; //Returns the number of the item
    }

    /**
     * @todo Needs javadoc info
     * @body
     */
    public String getItem(){
        return item; //Returns the name of the item
    }
    
    /**
     * Sets the type of menu item to the specified value.
     *
     * @param item the new name of the item
     */
    public void setItem(String item){
        this.item = item; //Sets the name of the item to the passed value
    }

    /**
     * @todo Needs javadoc info
     * @body
     */
    public void setItemNum(int number){
        this.number = number; //Sets the number of the item to the passed value
    }
    
    /**
     * Returns the type of item.
     *
     * @return type of item
     */
    public String getType(){
        return type; //Returns the type of the item
    }
     /**
     * Sets the type of menu item to the specified value.
     *
     * @param type the new type of item
     */
    public void setType(String type){
        this.type = type; //Sets the type of the item to the passed value
    }
    
    /**
     * Returns the numerical value that was input when the item was added.
     *
     * @return the price of the menu item
     */
    public double getPrice(){
        return price; //Returns the price of the item
    }
    
    /**
     * Sets the price of the menu item to the specified value.
     *
     * @param price the numerical value entered by the user that represents the cost of the item to customer
     */
    public void setPrice(double price){
        this.price = price; //Sets the price of the item to the passed value
    }
    /**
     * Returns the availability that was input by the user.
     *
     * @return true if the item is available, false if it is not
     */
    public boolean getAvailability(){
        return availability; //Returns the availability of the item
    }
    /**
     * Sets the availability of the menu item to the specified value.
     *
     */
    public void setAvailability(boolean availability){
        this.availability = availability; //Sets the availability of the item to the passed value
    }

    /**
     * Returns a clone of the menuItem for backup.
     *
     * @return a backup clone of the menuItem
     */
    public Object clone() {
        try {
            return super.clone(); //Returns a clone of the menuItem
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); //Throws an error if the menuItem cannot be cloned
        }
    }

    /**
     * Returns a string representation of the menu in the format:
     * "item: [item] | type: [type] | price: [cost] | availability: [availability]"
     *
     * @return a string representation of the menu
     */
    @Override
    public String toString(){
        return "item: " + item + ", type: " + type + ", price: " + price + ", availability: " + availability; //Returns a string representation of the menu
    }
    
}
