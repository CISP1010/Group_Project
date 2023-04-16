package Menu;
import Helpers.Cls;
import Helpers.YesNo;
import java.util.Scanner;
import static java.lang.System.*;

public class MenuMenu {
    static MenuData menuData = new MenuData();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sel;
        do {
            Cls.cls();
            out.println("What would you like to do?");
            out.println("1: Show the food menu.");
            out.println("2: Edit Menu options.");
            out.println("3: Go Back");
            out.print("[1,2,3]: ");
            sel = input.nextInt();
            input.nextLine(); //clear the leftover linebreak from input.nextInt()
            switch (sel) {
                case 1 -> { //Print food menu
                    Cls.cls();
                    menuData.printMenu();
                    out.println();
                    out.println("| Press enter to continue |");
                    input.nextLine();
                }
                case 2 -> { //edit food menu
                    Cls.cls();
                    out.println("What would you like to do?");
                    out.println("1: Add a menu option.");
                    out.println("2: Remove a menu option.");
                    out.println("3: Edit a menu option.");
                    out.println("4: Change a menu option's availability.");
                    out.println("5: Go back");
                    out.print("[1,2,3,4,5]: ");
                    int choice = input.nextInt();
                    input.nextLine();
                    while (choice < 5) {
                        switch (choice) {
                            case 1 -> { //add new menu item
                                Cls.cls();
                                boolean restart;
                                do {
                                    Cls.cls();
                                    out.println("Enter the item name.");
                                    out.print("[name]: ");
                                    String item = input.nextLine();
                                    Cls.cls();
                                    out.println("Types");
                                    out.println("-----\n");
                                    out.println(menuData.getTypes() + "\n"); //Print categories
                                    out.println("Enter the item type");
                                    out.print("[Type]: ");
                                    String type = input.nextLine();
                                    Cls.cls();
                                    out.println("Enter the price.");
                                    out.print("[Price]: ");
                                    double price = input.nextDouble();
                                    input.nextLine();  //clear the leftover linebreak from input.nextDouble()
                                    Cls.cls();
                                    out.println("Is the item available?");
                                    out.print("[(Y)es/(N)o]: ");
                                    boolean availability = YesNo.yesNo(input.nextLine());
                                    menuData.addMenuItem(item, type, price, availability); //add item to menuData hashmap
                                    Cls.cls();
                                    System.out.println("New Item");
                                    System.out.println("---------\n");
                                    System.out.println(menuData.searchItem(item));  //display new menu item
                                    out.println("Would you like to create another item?");
                                    out.print("[(Y)es/(N)o]: ");
                                    restart = YesNo.yesNo(input.nextLine());
                                } while (restart);
                            }
                            case 2 -> { //remove menu item
                                boolean restart = false;
                                do {
                                    out.println("Enter the Item to remove. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String item = input.nextLine();
                                    if (menuData.isItem(item)) { //check if menu item exists
                                        Cls.cls();
                                        out.println("Item Info");
                                        out.println("---------\n");
                                        out.println(menuData.searchItem(item)); //print current info
                                        out.println("Do you want to delete this item?"); //confirm delete
                                        out.print("[(Y)es/(N)o]: ");
                                        if (YesNo.yesNo(input.nextLine())) { //if yes delete, if no prompt restart
                                            menuData.remItem(item);
                                            out.println(item + "successfully deleted");
                                            out.println("Would you like to delete another item?");
                                            out.print("[(Y)es/(N)o]: ");
                                            restart = YesNo.yesNo(input.nextLine());
                                        } else {
                                            Cls.cls();
                                            out.println("Item was NOT removed.");
                                            out.println("Would you like to try again?");
                                            out.print("[(Y)es/(N)o]: ");
                                            restart = YesNo.yesNo(input.nextLine());
                                        }
                                    } else if (item.equalsIgnoreCase("-L")) { //Print food menu
                                        menuData.printMenu();
                                        out.println();
                                        restart = true;
                                    } else if (item.equalsIgnoreCase("-C")) { //return to previous menu
                                        restart = false;
                                        Cls.cls();
                                    } else {
                                        out.println("Item not found.");
                                        out.println("| Press enter to try again. |");
                                        restart = true;
                                        input.nextLine();
                                        Cls.cls();
                                    }
                                } while (restart);
                            }
                            case 3 -> { //Edit menu option
                                boolean restart;
                                do {
                                    out.println("Enter the Item name. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String item = input.nextLine();
                                    if (menuData.isItem(item)) {
                                        String backupItem = item + "BACKUP"; //failsafe backup of item name
                                        Cls.cls();
                                        out.println("Item found!");
                                        out.println("---------------\n");
                                        out.print(menuData.searchItem(item) + "\n"); //print current data
                                        out.println("Enter the new Item name (Press enter to leave unchanged)");
                                        out.print("[Item Name]: ");
                                        String newItem = input.nextLine();
                                        Cls.cls();
                                        out.println("Item found!"); //keeps item data visible after clearscreen
                                        out.println("---------------\n"); //keeps item data visible after clearscreen
                                        out.print(menuData.searchItem(item) + "\n"); //keeps item data visible after clearscreen
                                        out.println("Enter the new type (Press enter to leave unchanged)");
                                        out.print("[type]: ");
                                        String newType = input.nextLine();
                                        Cls.cls();
                                        out.println("Item found!"); //keeps item data visible after clearscreen
                                        out.println("---------------\n"); //keeps item data visible after clearscreen
                                        out.print(menuData.searchItem(item) + "\n"); //keeps item data visible after clearscreen
                                        out.println("Enter the new price (Press enter to leave unchanged)");
                                        out.print("[price]: ");
                                        double newPrice;
                                        String inputString = input.nextLine();
                                        if (inputString != null && !inputString.isEmpty()) { //check if user entered a new price, if not, sets value to old price to handle null double datatype exception
                                            try {
                                                newPrice = Double.parseDouble(inputString); //set to user input price if exists
                                            } catch (NumberFormatException e) {
                                                out.println("Number Format Exception.\n Please enter a price in the format 1,000.00");
                                                newPrice = menuData.getPrice(item); //otherwise set to old price
                                            }
                                        } else {
                                            newPrice = menuData.getPrice(item); //failsafe sets to old price
                                        }
                                        Cls.cls();
                                        out.println("Item found!"); //keeps item data visible after clearscreen
                                        out.println("---------------\n"); //keeps item data visible after clearscreen
                                        out.print(menuData.searchItem(item) + "\n"); //keeps item data visible after clearscreen
                                        out.println("Is the item available? (Press enter to leave unchanged)");
                                        out.print("[(Y)es/(N)o]: ");
                                        String inputString1 = input.nextLine();
                                        boolean newAvailability;
                                        if (inputString != null && !inputString.isEmpty()) { //same if loop as price but handles empty boolean exception
                                            newAvailability = YesNo.yesNo(inputString1); //set to user input availability if exists
                                        } else {
                                            newAvailability = menuData.getAvailability(item); //otherwise get old price and set to that
                                        }
                                        menuData.editItem(item, newItem, newType, newPrice, newAvailability); //edit menu item. This also performs a backup
                                        Cls.cls();
                                        if (!newItem.isEmpty()) { //check if the user entered a new name.
                                            out.println("New Information");
                                            out.println("---------------\n");
                                            out.println(menuData.searchItem(newItem)); //if new name exists, display item with the new name
                                        } else
                                            out.println("New Information:\n" + menuData.searchItem(item)); //otherwise display updated item with unchanged name
                                        out.println("Is this correct?"); //check if correct
                                        out.print("[(Y)es/(N)o]: ");
                                        String yn = input.nextLine();
                                        if (YesNo.yesNo(String.valueOf(yn))) { //check if the user entered Yes, yes, Y, or y
                                            menuData.remItem(backupItem); //remove the backup
                                            out.println("Item successfully updated!");
                                            out.println("Would you like to edit another Item?");
                                            out.print("[(Y)es/(N)o]: ");
                                            restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                                        } else {
                                            menuData.itemRestore(backupItem, item, newItem); //restore the item from backup. This also deletes the backup after restoring
                                            Cls.cls();
                                            out.println("Item data restored:");
                                            out.println(menuData.searchItem(item)); //print restored item data
                                            out.println("| Press enter to try again. |");
                                            input.nextLine();
                                            restart = true; //restart
                                        }
                                    } else if (item.equalsIgnoreCase("-L")) { // Print Food Menu
                                        Cls.cls();
                                        menuData.printMenu();
                                        out.println();
                                        restart = true;
                                    } else if (item.equalsIgnoreCase("-C")) { //Return to the previous menu
                                        restart = false;
                                    } else {
                                        out.println("Item not found!");
                                        restart = true;
                                    }
                                } while (restart);
                            }
                            case 4 -> {
                                boolean restart = false;
                                Cls.cls();
                                do {
                                    out.println("Enter the Item. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String item = input.nextLine();
                                    if (menuData.isItem(item)) {
                                        Cls.cls();
                                        out.println("Item Found");
                                        out.println("----------\n");
                                        out.println(menuData.searchItem(item));
                                        out.println();
                                        out.println("Is this item available?");
                                        out.print("[(Y)es/(N)o]: ");
                                        boolean avail = YesNo.yesNo(input.nextLine());
                                        menuData.setAvailability(item, avail);
                                        Cls.cls();
                                        String display;
                                        if (avail) {
                                            display = "available.";
                                        } else display = "not available";
                                        out.println("Successfully set " + item + " as " + display);
                                        out.println();
                                        out.println(menuData.searchItem(item));
                                        out.println();
                                        out.println("Would you like to edit another Item?");
                                        out.print("[(Y)es/(N)o]: ");
                                        restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                                        Cls.cls();
                                    } else if (item.equalsIgnoreCase("-L")) {
                                        Cls.cls();
                                        menuData.printMenu();
                                        out.println();
                                        restart = true;
                                    } else if (item.equalsIgnoreCase("-C")) {
                                        restart = false;
                                    }
                                } while (restart);
                            }
                            default -> out.println("Invalid choice \"" + choice + "\" Enter a valid option."); //second switch case for edit menu options
                        }
                        choice = 5; //set choice to 5 to end main do while loop
                    }
                }
                default -> out.println("Invalid choice \"" + sel + "\" Enter a valid option."); //first switch case for show menu or edit menu
            }
        } while (sel != 3); //main do while loop, exit thread if sel = 3
    }
}