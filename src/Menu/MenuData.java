/**
 * The MenuData class represents a collection of menu items and provides methods to load default menu items, add new menu items,
 * and print the menu in a formatted way. The class uses a HashMap to store MenuItem objects, with the name of the menu item
 * as the key and the MenuItem object as the value.
 */
package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MenuData {
    private static HashMap<String, MenuItem> menuData = new HashMap<>();

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
        System.out.println("File path: " + file.getAbsolutePath()); // add this line
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
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
        scanner.close();
    }

    /**
     * Prints the menu items in a formatted way, grouped by type (appetizers, entrees, sides, and desserts).
     */
    public String printMenu() {
        // Sort the menu items by type
        List<MenuItem> menuSort = new ArrayList<>(menuData.values());
        menuSort.sort(Comparator.comparing(MenuItem::getType).thenComparing(MenuItem::getItem));

        // Print the menu items grouped by type
        List<String> categories = Arrays.asList("Appetizers", "Entrees", "Sides", "Desserts");
        for (String category : categories) {
            System.out.println(category + ":");
            for (MenuItem menuItem : menuSort) {
                if (menuItem.getType().equals(category)) {
                    System.out.printf("  %-20s $%.2f\n", menuItem.getItem(), menuItem.getPrice());
                }
            }
        }

    return null;
    }
}
