package Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
public class MenuData {
    private static HashMap<String, MenuItem> menuData = new HashMap<>();
    private static List<String> categories = Arrays.asList("Appetizers", "Entrees", "Sides", "Desserts");

    /**
     * Constructs a new MenuData object and loads the default menu items from the default_menu_items.txt file.
     */
    public MenuData() {
        loadDefaultMenuItems();
    }

    /**
     * Adds a new menu item to the menuData HashMap.
     *
     * @param item         the name of the menu item
     * @param type         the type of the menu item (appetizer, entree, side, or dessert)
     * @param price        the price of the menu item
     * @param availability the availability of the menu item (true if available, false if not available)
     */
    public void addMenuItem(String item, String type, double price, boolean availability) {
        MenuItem menuItem = new MenuItem(item, type, price, availability);
        menuData.put(item, menuItem);
    }

    /**
     * Loads the default menu items from the default_menu_items.txt file and adds them to the menuData HashMap.
     * The file is located in the Resources directory.
     */
    private void loadDefaultMenuItems() {
        File file = new File("Resources/default_menu_items.txt"); //initialize .txt source
        Scanner input = null; //initialize scanner outside of try-catch and while loop
        try {
            input = new Scanner(file); //initialize file-source scanner
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //throw exception if file not found
        }
        while (input.hasNextLine()) { //iterate through each line in file. loops until there are no more lines.
            String line = input.nextLine();
            if (!line.startsWith("#")) { //ignore comment lines
                String[] itemDetails = line.split(","); //split line at commas and load into a String array
                if (itemDetails.length == 4) { //check that the line is formatted correctly
                    String name = itemDetails[0].trim(); //trim any hanging spaces and save name to String name
                    double price = Double.parseDouble(itemDetails[1].trim()); //trim space and parse price to double
                    String type = itemDetails[2].trim(); //timr spaces and save category to String type
                    boolean available = Boolean.parseBoolean(itemDetails[3].trim()); //trim spaces and parse availability to boolean available
                    MenuItem menuItem = new MenuItem(name, type, price, available); //create new menu item from extracted data
                    menuData.put(name, menuItem); //add the new item to the menuData hashmap
                }
            }
        }
        input.close();
    }

    /**
     * Checks whether an item with the specified name exists in the menuData map.
     *
     * @param item the name of the employee to check
     * @return true if the menuData map contains an entry with the specified item, false otherwise
     */
    public boolean isItem(String item) {
        return menuData.containsKey(item);
    }

    /**
     * Searches for an item's data in the menuData HashMap.
     *
     * @param item the item to search for
     * @return the item's data if found, otherwise a message that the item was not found
     */
    public String searchItem(String item) {
        if (menuData.containsKey(item)) {
            StringBuilder sb = new StringBuilder();
            MenuItem menuItem = menuData.get(item);
            sb.append("Item: ").append(item).append("\n")
                    .append("Type: ").append(menuItem.getType()).append("\n")
                    .append("Price: $").append(menuItem.getPrice()).append("\n")
                    .append("Availability: ").append(menuItem.getAvailability()).append("\n");
            return sb.toString();
        } else {
            return "Item not found.";
        }
    }

    /**
     * Returns the item's price.
     *
     * @param item the item to get the price of.
     * @return The item's price.
     */
    public double getPrice(String item) {
        MenuItem menuItem = menuData.get(item);
        return menuItem.getPrice();
    }

    /**
     * Returns the item's availability.
     *
     * @param item the item to get the availability of.
     * @return The item's availability.
     */
    public boolean getAvailability(String item) {
        MenuItem menuItem = menuData.get(item);
        return menuItem.getAvailability();
    }

    /**
     * Sets the item's availability.
     *
     * @param item the item to set the availability of.
     */
    public void setAvailability(String item, boolean availability) {
        MenuItem menuItem = menuData.get(item);
        menuItem.setAvailability(availability);
    }

    /**
     * Prints the menu items in a formatted way, grouped by type (appetizers, entrees, sides, and desserts).
     */
    public void printMenu() {
        // Sort the menu items by type
        List<MenuItem> menuSort = new ArrayList<>(menuData.values());
        menuSort.sort(Comparator.comparing(MenuItem::getType).thenComparing(MenuItem::getItem));

        // Determine the width of the price column based on the longest price
        int priceWidth = 0;
        for (MenuItem menuItem : menuSort) {
            priceWidth = Math.max(priceWidth, String.format("%.2f", menuItem.getPrice()).length() + 1);
        }

        // Determine the width of the menu item column based on the longest menu item name
        int menuItemWidth = 0;
        for (MenuItem menuItem : menuSort) {
            menuItemWidth = Math.max(menuItemWidth, menuItem.getItem().length() + 5);
        }

        // Print the menu items grouped by type and formatted in even columns
        for (String category : categories) {
            System.out.println(category + ":");
            for (MenuItem menuItem : menuSort) {
                if (menuItem.getType().equals(category)) {
                    String price = String.format("$%.2f", menuItem.getPrice());
                    System.out.printf("  %-" + menuItemWidth + "s  %-" + priceWidth + "s\n", menuItem.getItem(), price);
                }
            }
        }
    }

    /**
     * returns a list of menu categories
     * @return string containing menu categories
     */
    public String getTypes(){
        StringBuilder sb = new StringBuilder();
        sb.append("Types").append(categories.toString());
        return sb.toString();
    }

    /**
     * Edits an item in the menuData hashmap
     * @param item The item to edit
     * @param newItem The new item name
     * @param type the new item type
     * @param price the new item price
     * @param availability the new item availability
     */
    public void editItem(String item, String newItem, String type, double price, boolean availability) {
        if (menuData.containsKey(item)) {
            MenuItem menuItem = menuData.get(item);
            MenuItem backup = (MenuItem) menuItem.clone();
            menuData.put(item + "BACKUP", backup);

            if (!newItem.equals("")) {
                menuItem.setItem(newItem);
            }
            if (!type.equals("")) {
                menuItem.setType(type);
            }
            if (price != 0) {
                menuItem.setPrice(price);
            }
            menuItem.setAvailability(availability);
            menuData.remove(item);
            menuData.put(menuItem.getItem(), menuItem);
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Removes an item from the menuData HashMap.
     * @param item  The item to remove
     */
    public void remItem(String item) {
        menuData.remove(item);
    }

    /**
     * Restores an item from backup into the menuData HashMap.
     * @param backupItem The item to restore
     * @param oldItem The old item name
     * @param newItem The edited item to remove
     */
    public void itemRestore(String backupItem, String oldItem, String newItem) {
        if (menuData.containsKey(backupItem)) {
            MenuItem restore = menuData.get(backupItem);
            restore.setItem(oldItem);
            menuData.remove(newItem);
            menuData.put(oldItem, restore);
            menuData.remove(backupItem);
        }
    }
}

