/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Menu;

import Employee.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        File file = new File("Resources/default_menu_items.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.startsWith("#")) {
                String[] itemDetails = line.split(",");
                if (itemDetails.length == 4) {
                    String name = itemDetails[0].trim();
                    double price = Double.parseDouble(itemDetails[1].trim());
                    String type = itemDetails[2].trim();
                    boolean available = Boolean.parseBoolean(itemDetails[3].trim());
                    MenuItem menuItem = new MenuItem(name, type, price, available);
                    menuData.put(name, menuItem);
                }
            }
        }
        input.close();
    }

    /**
     * Checks whether an employee with the specified name exists in the employeeData map.
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
    public String searchItem(String item    ) {
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
     * Prints the menu items in a formatted way, grouped by type (appetizers, entrees, sides, and desserts).
     */
    public void printMenu() {
        // Sort the menu items by type
        List<MenuItem> menuSort = new ArrayList<>(menuData.values());
        menuSort.sort(Comparator.comparing(MenuItem::getType).thenComparing(MenuItem::getItem));

        // Print the menu items grouped by type
        for (String category : categories) {
            System.out.println(category + ":");
            for (MenuItem menuItem : menuSort) {
                if (menuItem.getType().equals(category)) {
                    System.out.printf("  %-20s $%.2f\n", menuItem.getItem(), menuItem.getPrice());
                }
            }
        }
    }
    public String getTypes(){
        StringBuilder sb = new StringBuilder();
        sb.append("Types").append(categories.toString());
        return sb.toString();
    }
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

