package Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
/**
 * @todo MenuData.java javadoc
 * @body proofread javadoc info and add missing entries
 * Comments are complete
 */
public class MenuData {
    /**
     * creates a hashmap that maps menu items to an integer key list
     */
    private static final HashMap<Integer, MenuItem> menuData = new HashMap<>();
    /**
     * creates a list to contain categories of dishes
     */
    private static final List<String> categories = Arrays.asList("Appetizers", "Entrees", "Sides", "Desserts");


    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public MenuData() {
        loadDefaultMenuItems(); //call method to load default menu items
    }

    /**
     * Adds a new menu item to the menuData HashMap.
     *
     * @param item         the name of the menu item
     * @param type         the type of the menu item (appetizer, entree, side, or dessert)
     * @param price        the price of the menu item
     * @param availability the availability of the menu item (true if available, false if not available)
     */
    public void addMenuItem(int number, String item, String type, double price, boolean availability) {
        MenuItem menuItem = new MenuItem(number, item, type, price, availability); //create new menu item with the passed parameters
        menuData.put(number, menuItem); //add the new menu item to the menuData HashMap
    }

    /**
     * Gets the current size of the menuData hash map
     * @return the size of the menuData hashmap
     */
    public int getSize() {
        return menuData.size(); //return the size of the menuData HashMap
    }

    /**
     * Loads the default menu items from the default_menu_items.txt file and adds them to the menuData HashMap.
     * The file is located in the Resources directory.
     */
    private void loadDefaultMenuItems() {
        File file = new File("Resources/default_menu_items.txt"); //initialize .txt source
        Scanner input; //initialize scanner outside the try-catch and while loop
        try {
            input = new Scanner(file); //initialize file-source scanner
        } catch (FileNotFoundException e) { //catch exception if file not found
            throw new RuntimeException(e); //throw exception if file not found
        }
        int i = 1;
        while (input.hasNextLine()) { //iterate through each line in file. loops until there are no more lines.
            String line = input.nextLine();
            if (!line.startsWith("#")) { //ignore comment lines
                String[] itemDetails = line.split(","); //split string at commas and load into a String array
                if (itemDetails.length == 4) { //check that the line was formatted correctly
                    String name = itemDetails[0].trim(); //trim any hanging spaces and save name to String name
                    double price = Double.parseDouble(itemDetails[1].trim()); //trim space and parse price to double
                    String type = itemDetails[2].trim(); //trim spaces and save category to String type
                    boolean available = Boolean.parseBoolean(itemDetails[3].trim()); //trim spaces and parse availability to boolean available
                    MenuItem menuItem = new MenuItem(i, name, type, price, available); //create new menu item from extracted data
                    menuData.put(i, menuItem); //add the new item to the menuData hashmap
                    i++; //increment i to keep track of the item number
                }
            }

        }
    }

    /**
     * Checks whether an item with the specified name exists in the menuData map.
     *
     * @param itemNum the number of the item to check
     * @return true if the menuData map contains an entry with the specified item, false otherwise
     */
    public boolean isItem(int itemNum) {
        return menuData.containsKey(itemNum); //return true if the menuData map contains an entry with the specified item, false otherwise
    }

    /**
     * Searches for an item's data in the menuData HashMap.
     *
     * @param item the item to search for
     * @return the item's data if found, otherwise a message that the item was not found
     */
    public String searchItem(int item) {
        if (menuData.containsKey(item)) { //check if the menuData HashMap contains the item
            StringBuilder sb = new StringBuilder();
            MenuItem menuItem = menuData.get(item); //get the MenuItem object from the menuData HashMap
            sb.append("Item: ").append(item).append("\n") //append the item's data to the StringBuilder
                    .append("Type: ").append(menuItem.getType()).append("\n")
                    .append("Price: $").append(menuItem.getPrice()).append("\n")
                    .append("Availability: ").append(menuItem.getAvailability()).append("\n");
            return sb.toString(); //return the String Builder
        } else {
            return "Item not found."; //return message that the item was not found
        }
    }

    /**
     * Returns the item's price.
     *
     * @param item the item to get the price of.
     * @return The item's price.
     */
    public double getPrice(int item) {
        MenuItem menuItem = menuData.get(item); //get the MenuItem object from the menuData HashMap
        return menuItem.getPrice(); //return the item's price
    }

    /**
     * Returns the item's availability.
     *
     * @param item the item to get the availability of.
     * @return The item's availability.
     */
    public boolean getAvailability(int item) {
        MenuItem menuItem = menuData.get(item); //get the MenuItem object from the menuData HashMap
        return menuItem.getAvailability(); //return the item's availability
    }

    /**
     * Sets the availability of an item
     * @param item the item's number
     * @param availability the item's status as available or not
     */
    /**
     * @todo Add code to remove old menu item and add new menu item to the menuData HashMap
     * @body IDK how I missed this
     */
    public void setAvailability(int item, boolean availability) {
        MenuItem menuItem = menuData.get(item);//get the MenuItem object from the menuData HashMap
        menuItem.setAvailability(availability); //set the item's availability

    }

    /**
     * Prints the menu items in a formatted way, grouped by type (appetizers, entrees, sides, and desserts).
     */
    public void printMenu() {
        // Sort the menu items by type
        List<MenuItem> menuSort = new ArrayList<>(menuData.values()); //ArrayList to store the menu items
        menuSort.sort(Comparator.comparing(MenuItem::getType).thenComparing(MenuItem::getItem)); //sort the menu items by type and then by item number

        // Determine the width of the price column based on the longest price
        int priceWidth = 0;
        for (MenuItem menuItem : menuSort) { //loop through each menu item
            priceWidth = Math.max(priceWidth, String.format("%.2f", menuItem.getPrice()).length() + 1); //set the width to the longest price
        }

        // Determine the width of the menu item column based on the longest menu item name
        int menuItemWidth = 0;
        for (MenuItem menuItem : menuSort) { //loop through each menu item
            menuItemWidth = Math.max(menuItemWidth, menuItem.getItem().length() + 5); //set the width to the longest menu item name
        }
        // Print the menu items grouped by type and sorted by item number and formatted in even columns
        for (String category : categories) { //loop through each category
            System.out.println(category + ":"); //print the category
            List<MenuItem> itemsInCategory = new ArrayList<>(); //ArrayList to store the menu items in the category
            for (MenuItem menuItem : menuSort) { //loop through each menu item
                if (menuItem.getType().equals(category)) { //check if the menu item is in the category
                    itemsInCategory.add(menuItem); //add the menu item to the ArrayList if it is in the category
                }
            }
            itemsInCategory.sort(Comparator.comparing(MenuItem::getItemNum)); //sort the menu items in the category by item number
            for (MenuItem menuItem : itemsInCategory) { //loop through each menu item in the category
                String price = String.format("$%.2f", menuItem.getPrice()); //format the price to two decimal places
                System.out.printf(menuItem.getItemNum() + ":" + "  %-" + menuItemWidth + "s  %-" + priceWidth + "s\n", menuItem.getItem(), price); //print the menu item and price in even columns
            }
        }
    }

    /**
     * get the name of an item from menu data
     * @param itemNum the item number
     * @return the name of the item
     */


    public String getName(int itemNum) {
        MenuItem menuItem = menuData.get(itemNum); //get the MenuItem object from the menuData HashMap
        return menuItem.getItem(); //return the item's name
    }

    /**
     * returns a list of menu categories
     *
     * @return string containing menu categories
     */
    public String getTypes() {
        return "Types" + categories;
    }

    /**
     * Edits an item in the menuData hashmap
     *
     * @param itemNum      The item to edit
     * @param newItem      The new item name
     * @param type         the new item type
     * @param price        the new item price
     * @param availability the new item availability
     */
    public void editItem(int itemNum, String newItem, String type, double price, boolean availability) {
        if (menuData.containsKey(itemNum)) { //check if the menuData HashMap contains the item
            MenuItem menuItem = menuData.get(itemNum); //get the MenuItem object from the menuData HashMap
            MenuItem backup = (MenuItem) menuItem.clone(); //create a backup of the MenuItem object
            menuData.put(itemNum + 100, backup); //add the backup to the menuData HashMap

            /**
             * @todo I believe new item needs to be migrated to an integer data type?
             * @body Not sure though. Need to double-check
             */
            if (!newItem.equals("")) { //Set the new item
                menuItem.setItem(newItem);
            }
            if (!type.equals("")) { //set the new item type
                menuItem.setType(type);
            }
            if (price != 0) { //set the new item price
                menuItem.setPrice(price);
            }
            menuItem.setAvailability(availability); //set the new item availability
            menuData.remove(itemNum); //remove the old menu item
            menuData.put(menuItem.getItemNum(), menuItem); //add the new menu item
        } else {
            System.out.println("Item not found"); //print an error message if the item is not found
        }
    }

    /**
     * Removes an item from the menuData HashMap.
     *
     * @param itemNum The item to remove
     */
    public void remItem(int itemNum) {
        menuData.remove(itemNum); //remove the item from the menuData HashMap
    }

    /**
     * Restores an item from backup into the menuData HashMap.
     *
     * @param itemNum The item number to restore
     * @param newItem The edited item to remove
     */
    public void itemRestore(int itemNum, String newItem) {
        if (menuData.containsKey(itemNum + 100)) { //check if the menuData HashMap contains the backup item
            int backupItem = itemNum + 100; //set the backup item number
            MenuItem restore = menuData.get(backupItem); //get the backup MenuItem object from the menuData HashMap
            restore.setItem(newItem); //set the name to the old item name
            menuData.remove(itemNum); //remove the new item
            menuData.put(itemNum, restore); //add the old item
            menuData.remove(backupItem); //remove the backup item
        }
    }

    /**
     * gets the key number of an item
     * @param item the item's name
     * @return the integer place of the item in the menuData hashmap
     */
    public int getItemNum(String item) {
        int num = -1; //set the item number to -1
        for (int i : menuData.keySet()) { //loop through each item number in the menuData HashMap
            if (menuData.containsKey(i)) { //check if the menuData HashMap contains the number of the current iteration
                MenuItem menuItem = menuData.get(i); //get the MenuItem object from the menuData HashMap if it exists
                if (menuItem.getItem().equals(item)) { //check if the menu item name matches the passed item name
                    num = i; //set the item number to the counter number if the item name matches
                }
            }
        }
        return num; //return the item number
    }
}

