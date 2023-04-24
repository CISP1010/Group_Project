package Menu;

import Helpers.Cls;
import Helpers.YesNo;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * @todo proofread MenuMenu.java code comments
 * @body check code comments for accuracy and ensure they are in the correct locations
 * Comments are complete
 */
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
                    int choice = input.nextInt(); //get user input
                    input.nextLine();
                    do{
                        switch (choice) {
                            case 1 -> { //add new menu item
                                Cls.cls();
                                boolean restart; //boolean to restart the do-while loop
                                do {
                                    Cls.cls();
                                    out.println("Enter the item name.");
                                    out.print("[name]: ");
                                    String item = input.nextLine(); //get item name
                                    Cls.cls();
                                    out.println("Types");
                                    out.println("-----\n");
                                    out.println(menuData.getTypes() + "\n"); //Print categories
                                    out.println("Enter the item type");
                                    out.print("[Type]: ");
                                    String type = input.nextLine(); //get item type
                                    Cls.cls();
                                    out.println("Enter the price.");
                                    out.print("[Price]: ");
                                    double price = input.nextDouble(); //get item price
                                    input.nextLine();  //clear the leftover linebreak from input.nextDouble()
                                    Cls.cls();
                                    out.println("Is the item available?");
                                    out.print("[(Y)es/(N)o]: ");
                                    boolean availability = YesNo.yesNo(input.nextLine()); //get item availability
                                    int number = menuData.getSize() + 1; //get new item number
                                    menuData.addMenuItem(number, item, type, price, availability); //add item to menuData hashmap
                                    Cls.cls();
                                    System.out.println("New Item");
                                    System.out.println("---------\n");
                                    System.out.println(menuData.searchItem(number));  //display new menu item
                                    out.println("Would you like to create another item?");
                                    out.print("[(Y)es/(N)o]: ");
                                    restart = YesNo.yesNo(input.nextLine()); //get user input to restart loop
                                } while (restart); //restart loop if user wants to add another item
                            }
                            case 2 -> { //remove menu item
                                boolean restart = false; //boolean to restart the do-while loop
                                Cls.cls();
                                do {
                                    out.println("Enter the Item to remove. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String itemString = input.nextLine();
                                    boolean itemNumValid = false; //boolean to check if item number is valid
                                    int itemNum; //This int stores the item number
                                        try {
                                            if (menuData.isItem((Integer.parseInt(itemString)))) { //try to parse user input to integer and check if it is a valid item
                                                itemNum = Integer.parseInt(itemString); //Try to parse user input to integer
                                                itemNumValid = true; //set itemNumValid to true if successful
                                                String name = menuData.getName(itemNum); //get item name
                                                input.nextLine();
                                                Cls.cls();
                                                out.println("Item Info");
                                                out.println("---------\n");
                                                out.println(menuData.searchItem(itemNum)); //print current info
                                                out.println("Do you want to delete this item?"); //confirm delete
                                                out.print("[(Y)es/(N)o]: ");

                                                if (YesNo.yesNo(input.nextLine())) { //if yes delete, if no prompt restart
                                                    menuData.remItem(itemNum); //delete item
                                                    out.println(name + "successfully deleted");
                                                    out.println("Would you like to delete another item?");
                                                    out.print("[(Y)es/(N)o]: ");
                                                    restart = YesNo.yesNo(input.nextLine()); //get user input to restart loop
                                                } else {
                                                    Cls.cls();
                                                    out.println("Item was NOT removed.");
                                                    out.println("Would you like to try again?");
                                                    out.print("[(Y)es/(N)o]: ");
                                                    restart = YesNo.yesNo(input.nextLine()); //get user input to restart loop
                                                }
                                            }
                                        } catch (NumberFormatException e) { //catch if user input is not an integer

                                            if (itemString.equalsIgnoreCase("-L")) { //list menu items if user enters -L
                                                menuData.printMenu(); //print menu
                                                out.println();
                                                restart = true; //restart loop
                                            } else if (itemString.equalsIgnoreCase("-C")) { //cancel if user enters -C
                                                restart = false; //don't restart loop
                                                Cls.cls();
                                            } else { //If the user input is not a valid item and is not -L or -C
                                                out.println("Item not found.");
                                                out.println("| Press enter to try again. |");
                                                restart = true; //restart loop
                                                input.nextLine();
                                                Cls.cls();
                                            }
                                        }
                                } while (restart); //restart loop if user wants to delete another item or if user input is invalid
                            }
                            case 3 -> { //Edit menu option
                                boolean restart = false; //boolean to restart the do-while loop
                                do {
                                    out.println("Enter the Item number. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String item = input.nextLine(); //get item number
                                    try {
                                        if (menuData.isItem(Integer.parseInt(item))) { //try to parse user input to integer and check if item exists
                                            int itemNum = Integer.parseInt(item); //parse user input to integer if successful
                                            int backupItem = itemNum + 100; //failsafe backup of item name
                                            Cls.cls();
                                            out.println("Item found!");
                                            out.println("---------------\n");
                                            out.print(menuData.searchItem(itemNum) + "\n"); //print current item info
                                            out.println("Enter the new Item name (Press enter to leave unchanged)");
                                            out.print("[Item Name]: ");
                                            String newItem = input.nextLine(); //get new item name
                                            Cls.cls();
                                            out.println("Item found!"); //keeps item data visible after clearscreen
                                            out.println("---------------\n"); //keeps item data visible after clearscreen
                                            out.print(menuData.searchItem(itemNum) + "\n"); //keeps item data visible after clearscreen
                                            out.println("Enter the new type (Press enter to leave unchanged)");
                                            out.print("[type]: ");
                                            String newType = input.nextLine(); //get new item type
                                            Cls.cls();
                                            out.println("Item found!"); //keeps item data visible after clearscreen
                                            out.println("---------------\n"); //keeps item data visible after clearscreen
                                            out.print(menuData.searchItem(itemNum) + "\n"); //keeps item data visible after clearscreen
                                            out.println("Enter the new price (Press enter to leave unchanged)");
                                            out.print("[price]: ");
                                            double newPrice;
                                            String inputString = input.nextLine(); //get new item price
                                            if (inputString != null && !inputString.isEmpty()) { //check if user entered a new price, if not, sets value to old price to handle null double datatype exception
                                                try {
                                                    newPrice = Double.parseDouble(inputString); //set to user input price if exists
                                                } catch (NumberFormatException e) { //catch if user input is not a double
                                                    out.println("Number Format Exception.\n Please enter a price in the format 1,000.00 \n!Price was not changed!");
                                                    newPrice = menuData.getPrice(itemNum); //Fail-safe sets to old price and displays error message
                                                }
                                            } else {
                                                out.println("Price was not changed!");
                                                newPrice = menuData.getPrice(itemNum); //failsafe sets to old price
                                            }
                                            Cls.cls();
                                            out.println("Item found!"); //keeps item data visible after clearscreen
                                            out.println("---------------\n"); //keeps item data visible after clearscreen
                                            out.print(menuData.searchItem(itemNum) + "\n"); //keeps item data visible after clearscreen
                                            out.println("Is the item available? (Press enter to leave unchanged)");
                                            out.print("[(Y)es/(N)o]: ");
                                            String inputString1 = input.nextLine(); //get new item availability
                                            boolean newAvailability;
                                            if (inputString != null && !inputString.isEmpty()) { //same if loop as price but handles empty boolean exception
                                                newAvailability = YesNo.yesNo(inputString1); //set to user input availability if exists
                                            } else {
                                                newAvailability = menuData.getAvailability(itemNum); //otherwise get old availability and set to that
                                            }
                                            menuData.editItem(itemNum, newItem, newType, newPrice, newAvailability); //edit menu item. This also performs a backup
                                            Cls.cls();
                                            if (!newItem.isEmpty()) { //check if the user entered a new name.
                                                out.println("New Information");
                                                out.println("---------------\n");
                                                out.println(menuData.searchItem(itemNum)); //if new name exists, display item with the new name
                                            } else
                                                out.println("New Information:\n" + menuData.searchItem(itemNum)); //otherwise display updated item with unchanged name
                                            out.println("Is this correct?"); //check if correct
                                            out.print("[(Y)es/(N)o]: ");
                                            String yn = input.nextLine();
                                            if (YesNo.yesNo(String.valueOf(yn))) { //check if the user entered Yes, yes, Y, or y
                                                menuData.remItem(backupItem); //remove the backup if the user entered Yes
                                                out.println("Item successfully updated!");
                                                out.println("Would you like to edit another Item?");
                                                out.print("[(Y)es/(N)o]: ");
                                                restart = YesNo.yesNo(String.valueOf(input.nextLine())); //check if the user wants to edit another item
                                            } else {
                                                menuData.itemRestore(backupItem, newItem); //restore the item from backup. This also deletes the backup after restoring
                                                Cls.cls();
                                                out.println("Item data restored:");
                                                out.println(menuData.searchItem(itemNum)); //print restored item data
                                                out.println("Would you like to try again??");
                                                out.print("[(Y)es/(N)o]: ");
                                                restart = YesNo.yesNo(String.valueOf(input.nextLine())); //check if the user wants to try again
                                            }
                                        }
                                    }catch (NumberFormatException e) { //catch if user input is not an integer
                                        if (item.equalsIgnoreCase("-L")) { //list item names if user enters -L
                                            Cls.cls();
                                            menuData.printMenu(); //print menu
                                            out.println();
                                            restart = true; //restart the loop
                                        } else if (item.equalsIgnoreCase("-C")) { //cancel if user enters -C
                                            restart = false; //exit the loop
                                        } else {
                                            out.println("Item not found!"); //if user input is not a valid item number, -L, or -C, display error message
                                            restart = true; //restart the loop
                                        }
                                    }
                                } while (restart); //loop until user enters -C or a valid item number
                            }
                            case 4 -> { //delete item
                                boolean restart = false; //boolean to restart the loop
                                Cls.cls();
                                do {
                                    out.println("Enter the Item number. (Enter -L to list Item names or -C to cancel.)");
                                    out.print("[Item]: ");
                                    String item = input.nextLine(); //get item number
                                    try {
                                        if (menuData.isItem(Integer.parseInt(item))) { //try to parse the user input as an integer and check if it is a valid item number
                                            int itemNum = Integer.parseInt(item); //parse the user input as an integer if it is a valid item number and set it to itemNum
                                            Cls.cls();
                                            out.println("Item Found");
                                            out.println("----------\n");
                                            out.println(menuData.searchItem(itemNum)); //print item data
                                            out.println();
                                            out.println("Is this item available?");
                                            out.print("[(Y)es/(N)o]: ");
                                            boolean avail = YesNo.yesNo(input.nextLine()); //get availability
                                            menuData.setAvailability(itemNum, avail); //set availability
                                            Cls.cls();
                                            String display; //string to display availability as "available" or "not available"
                                            if (avail) {
                                                display = "available."; //set display to "available" if avail is true
                                            } else display = "not available"; //set display to "not available" if avail is false
                                            out.println("Successfully set " + item + " as " + display);
                                            out.println();
                                            out.println(menuData.searchItem(itemNum)); //print item data
                                            out.println();
                                            out.println("Would you like to edit another Item?");
                                            out.print("[(Y)es/(N)o]: ");
                                            restart = YesNo.yesNo(String.valueOf(input.nextLine())); //check if the user wants to edit another item
                                            Cls.cls();
                                        }
                                    }catch (NumberFormatException e) { //catch if user input is not an integer
                                        if (item.equalsIgnoreCase("-L")) { //list item names if user enters -L
                                            Cls.cls();
                                            menuData.printMenu(); //print menu
                                            out.println();
                                            restart = true; //restart the loop
                                        } else if (item.equalsIgnoreCase("-C")) { //cancel if user enters -C
                                            restart = false; //exit the loop
                                        }
                                    }
                                } while (restart); //loop until user enters -C or a valid item number
                            }
                            default -> out.println("Invalid choice \"" + choice + "\" Enter a valid option."); //first switch case for show menu or edit menu. Display error message if user enters an invalid option
                        }

                    }while (choice != 5); //second do while loop, exit loop if choice = 5
                }
                default -> out.println("Invalid choice \"" + sel + "\" Enter a valid option."); //first switch case for show menu or edit menu
            }
        } while (sel != 3); //main do while loop, exit thread if sel = 3
    }
}