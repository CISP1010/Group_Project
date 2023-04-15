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
        do{
            Cls.cls();
            out.println("What would you like to do?");
            out.println("1: Show the food menu.");
            out.println("2: Edit Menu options.");
            out.print("[1,2]: ");
            sel = input.nextInt();
            input.nextLine(); // consume the end-of-line character
            switch(sel) {
                case 1 -> {
                    Cls.cls();
                    menuData.printMenu();
                    out.println();
                    out.println("| Press enter to continue |");
                    input.nextLine();
                }
                case 2 -> {
                    Cls.cls();
                    out.println("What would you like to do?");
                    out.println("1: Add a menu option.");
                    out.println("2: Remove a menu option.");
                    out.println("3: Edit a menu option.");
                    out.println("4: Change a menu option's availability.");
                    out.print("[1,2,3,4]: ");
                    int choice = input.nextInt();
                    switch (choice){
                        case 1 -> {
                            Cls.cls();
                            // Prompt the user to enter the item's information
                            boolean restart;
                            do {
                                Cls.cls();
                                out.println("Enter the item name.");
                                out.print("[name]: ");
                                String item = input.nextLine();
                                Cls.cls();;
                                out.println("Types");
                                out.println("-----\n");
                                out.println(menuData.getTypes() + "\n");
                                out.println("Enter the item type");
                                out.print("[Type]: ");
                                String type = input.nextLine();
                                Cls.cls();
                                out.println("Enter the price.");
                                out.print("[Price]: ");
                                double price = input.nextInt();
                                input.nextLine();
                                Cls.cls();
                                out.println("Is the item available?");
                                out.print("[(Y)es/(N)o]: ");
                                boolean availability = YesNo.yesNo(input.nextLine());
                                // Add the item to the database
                                menuData.addMenuItem(item, type, price, availability);
                                Cls.cls();
                                System.out.println("New Item");
                                System.out.println("---------\n");
                                System.out.println(menuData.searchItem(item));
                                out.println("Would you like to create another item?");
                                out.print("[(Y)es/(N)o]: ");
                                restart = YesNo.yesNo(input.nextLine());
                            } while (restart);
                        }
                        case 2 -> {
                            boolean restart = false;
                            do {
                                out.println("Enter the item to remove");
                                out.print("[item]: ");
                                String item = input.nextLine();
                                if (menuData.isItem(item)) {
                                    Cls.cls();
                                    menuData.searchItem(item);
                                    out.println("Do you want to delete this item?");
                                    out.print("[(Y)es/(N)o]: ");
                                    if (YesNo.yesNo(input.nextLine())) {
                                        menuData.remItem(item);
                                        out.println(item + "successfully deleted");
                                        out.println("| Press enter to continue |");
                                        input.nextLine();
                                    }else{
                                        Cls.cls();
                                        out.println("Item was NOT removed.");
                                        out.println("| Press Enter to try again. |");
                                        restart = true;
                                        input.nextLine();
                                    }
                                }else {
                                    out.println("Item not found.");
                                    out.println("| Press enter to try again. |");
                                    restart = true;
                                    input.nextLine();
                                }
                            }while (restart);
                        }
                        case 3 ->{
                            boolean restart;
                            do {
                                Cls.cls();
                                // Prompt the user to enter the name of the Item to edit and the new information
                                out.println("Enter the Item name. (Enter -L to list Item names or -C to cancel.)");
                                out.print("[Item]: ");
                                String item = input.nextLine();
                                if (menuData.isItem(item)) {
                                    String backupItem = item + "BACKUP";
                                    Cls.cls();
                                    out.println("Item found!");
                                    out.println("---------------\n");
                                    out.print(menuData.searchItem(item) + "\n");
                                    out.println("Enter the new Item name (Press enter to leave unchanged)");
                                    out.print("[Item Name]: ");
                                    String newItem = input.nextLine();
                                    Cls.cls();
                                    out.println("Item found!");
                                    out.println("---------------\n");
                                    out.print(menuData.searchItem(item) + "\n");
                                    out.println("Enter the new type (Press enter to leave unchanged)");
                                    out.print("[type]: ");
                                    String newType = input.nextLine();
                                    Cls.cls();
                                    out.println("Item found!");
                                    out.println("---------------\n");
                                    out.print(menuData.searchItem(item) + "\n");
                                    out.println("Enter the new price (Press enter to leave unchanged)");
                                    out.print("[price]: ");
                                    double newPrice;
                                    String inputString = input.nextLine();
                                    if (inputString != null && !inputString.isEmpty()) {
                                        try {
                                            newPrice = Integer.parseInt(inputString.trim());
                                        } catch (NumberFormatException e) {
                                            newPrice = menuData.getPrice(item);
                                        }
                                    } else {
                                        newPrice = menuData.getPrice(item);
                                    }
                                    Cls.cls();
                                    out.println("Item found!");
                                    out.println("---------------\n");
                                    out.print(menuData.searchItem(item) + "\n");
                                    out.println("Is the item available? (Press enter to leave unchanged)");
                                    out.print("[(Y)es/(N)o]: ");
                                    String inputString1 = input.nextLine();
                                    boolean newAvailability;
                                    if (inputString != null && !inputString.isEmpty()) {
                                        newAvailability = YesNo.yesNo(inputString1);
                                    } else {
                                        newAvailability = menuData.getAvailability(item);
                                    }
                                    menuData.editItem(item, newItem, newType, newPrice, newAvailability);
                                    Cls.cls();
                                    if (!newItem.isEmpty()) {
                                        out.println("New Information");
                                        out.println("---------------\n");
                                        out.println(menuData.searchItem(newItem));
                                    } else out.println("New Information:\n" + menuData.searchItem(item));
                                    out.println("Is this correct?");
                                    out.print("[(Y)es/(N)o]: ");
                                    String yn = input.nextLine();
                                    if (YesNo.yesNo(String.valueOf(yn))) {
                                        menuData.remItem(backupItem);
                                        out.println("Item successfully updated!");
                                        out.println("Would you like to edit another Item?");
                                        out.print("[(Y)es/(N)o]: ");
                                        restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                                    } else {
                                        menuData.itemRestore(backupItem, item, newItem);
                                        out.println("Employee data restored:");
                                        out.println(menuData.searchItem(item));
                                        out.println("| Press enter to try again. |");
                                        input.nextLine();
                                        restart = true;
                                    }
                                } else if (item.equalsIgnoreCase("-L")) {
                                    Cls.cls();
                                    menuData.printMenu();
                                    out.println("| Press enter to continue |");
                                    input.nextLine();
                                    restart = true;
                                } else if (item.equalsIgnoreCase("-C")) {
                                    break;
                                } else {
                                    out.println("Item not found!");
                                    restart = true;
                                }
                            } while (restart);
                        }
                        default -> out.println("Invalid choice " + choice + " Enter a valid option.");
                    }
                }
                default -> out.println("Invalid choice.");
            }
        }while(sel < 3);
        input.close();
    }
}