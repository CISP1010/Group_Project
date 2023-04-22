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
    private static HashMap<Integer, MenuItem> menuData = new HashMap<>();
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
    public void addMenuItem(int number, String item, String type, double price, boolean availability) {
        MenuItem menuItem = new MenuItem(number, item, type, price, availability);
        menuData.put(number, menuItem);
    }

    public int getSize() {
        return menuData.size();
    }

    /**
     * Loads the default menu items from the default_menu_items.txt file and adds them to the menuData HashMap.
     * The file is located in the Resources directory.
     */
    private void loadDefaultMenuItems() {
        File file = new File("Resources/default_menu_items.txt"); //initialize .txt source
        Scanner input = null; //initialize scanner outside the try-catch and while loop
        try {
            input = new Scanner(file); //initialize file-source scanner
        } catch (FileNotFoundException e) { //catch exception if file not found
            throw new RuntimeException(e); //throw exception if file not found
        }
        int i = 1;
        while (input.hasNextLine()) { //iterate through each line in file. loops until there are no more lines.
            String line = input.nextLine();
            if (!line.startsWith("#")) { //ignore comment lines
                String[] itemDetails = line.split(","); //split line at commas and load into a String array
                if (itemDetails.length == 4) { //check that the line is formatted correctly
                    String name = itemDetails[0].trim(); //trim any hanging spaces and save name to String name
                    double price = Double.parseDouble(itemDetails[1].trim()); //trim space and parse price to double
                    String type = itemDetails[2].trim(); //trim spaces and save category to String type
                    boolean available = Boolean.parseBoolean(itemDetails[3].trim()); //trim spaces and parse availability to boolean available
                    MenuItem menuItem = new MenuItem(i, name, type, price, available); //create new menu item from extracted data
                    menuData.put(i, menuItem); //add the new item to the menuData hashmap
                    i++;
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
        return menuData.containsKey(itemNum);
    }

    /**
     * Searches for an item's data in the menuData HashMap.
     *
     * @param item the item to search for
     * @return the item's data if found, otherwise a message that the item was not found
     */
    public String searchItem(int item) {
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
    public double getPrice(int item) {
        MenuItem menuItem = menuData.get(item);
        return menuItem.getPrice();
    }

    /**
     * Returns the item's availability.
     *
     * @param item the item to get the availability of.
     * @return The item's availability.
     */
    public boolean getAvailability(int item) {
        MenuItem menuItem = menuData.get(item);
        return menuItem.getAvailability();
    }

    /**
     * Sets the item's availability.
     *
     * @param item the item to set the availability of.
     */
    public void setAvailability(int item, boolean availability) {
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
        int i = 1;
        // Print the menu items grouped by type and sorted by item number and formatted in even columns
        for (String category : categories) {
            System.out.println(category + ":");
            List<MenuItem> itemsInCategory = new ArrayList<>();
            for (MenuItem menuItem : menuSort) {
                if (menuItem.getType().equals(category)) {
                    itemsInCategory.add(menuItem);
                }
            }
            Collections.sort(itemsInCategory, Comparator.comparing(MenuItem::getItemNum));
            for (MenuItem menuItem : itemsInCategory) {
                String price = String.format("$%.2f", menuItem.getPrice());
                System.out.printf(menuItem.getItemNum() + ":" + "  %-" + menuItemWidth + "s  %-" + priceWidth + "s\n", menuItem.getItem(), price);
            }
        }
    }

    public String getName(int itemNum) {
        MenuItem menuItem = menuData.get(itemNum);
        return menuItem.getItem();
    }

    /**
     * returns a list of menu categories
     *
     * @return string containing menu categories
     */
    public String getTypes() {
        return "Types" + categories.toString();
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
        if (menuData.containsKey(itemNum)) {
            MenuItem menuItem = menuData.get(itemNum);
            MenuItem backup = (MenuItem) menuItem.clone();
            menuData.put(itemNum + 100, backup);

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
            menuData.remove(itemNum);
            menuData.put(menuItem.getItemNum(), menuItem);
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Removes an item from the menuData HashMap.
     *
     * @param itemNum The item to remove
     */
    public void remItem(int itemNum) {
        menuData.remove(itemNum);
    }

    /**
     * Restores an item from backup into the menuData HashMap.
     *
     * @param itemNum The item number to restore
     * @param newItem The edited item to remove
     */
    public void itemRestore(int itemNum, String newItem) {
        if (menuData.containsKey(itemNum + 100)) {
            int backupItem = itemNum + 100;
            MenuItem restore = menuData.get(backupItem);
            restore.setItem(newItem);
            menuData.remove(itemNum);
            menuData.put(itemNum, restore);
            menuData.remove(backupItem);
        }
    }

    public int getItemNum(String item) {
        int num = -1;
        for (int i : menuData.keySet()) {
            if (menuData.containsKey(i)) {
                MenuItem menuItem = menuData.get(i);
                if (menuItem.getItem().equals(item)) {
                    num = i;
                }
            }
        }
        return num;
    }
}

