package Tables;

import Helpers.Cls;
import Helpers.YesNo;
import Menu.MenuData;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * This class provides a command-line interface for managing restaurant tables.
 * Users can view all tables' status, view specific tables' status, or manually edit tables' data.
 */
public class TableMenu {

    /**
     * The main method provides a command line user interface for managing the tables.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        TableData tableData = new TableData(); //Create new TableData object to initialize table data hashmap
        int choice; //initialize choice variable for switch statement
        do {
            Cls.cls();
            out.println("What would you like to do?");
            out.println("1: View all table status");
            out.println("2: View specific table status");
            out.println("3: Manually edit a table");
            out.println("4: Exit");
            out.print("[1,2,3,4]: ");
            choice = input.nextInt(); //get user input
            input.nextLine();
            switch (choice) {
                case 1 -> { //show all table data in formatted string
                    Cls.cls();
                    out.println(tableData.listTables()); //print table data
                    out.println("Press Enter to continue.");
                    input.nextLine();
                }
                case 2 -> { //show specific table data in formatted string
                    boolean restart; //initialize restart variable for do-while loop
                    do {
                        Cls.cls();
                        out.println("Enter the table number.");
                        out.print("[1-20]: ");
                        int table = input.nextInt(); //get table number
                        input.nextLine(); //clear the leftover linebreak from input.nextInt()
                        Cls.cls();
                        out.println(tableData.getTableData(table)); //print table data from tableData hashmap
                        out.println("Would you like to view a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine()); //restart the loop if the user enters yes
                    } while (restart); //loop until the user enters no
                }
                case 3 -> { //edit a table
                    boolean restart; //initialize restart variable for do-while loop
                    do{
                        Cls.cls();
                        out.println("TABLE EDITOR");
                        out.println("-------------");
                        out.println("Enter the table number.");
                        out.print("[1-20]: ");
                        int table = input.nextInt(); //get table number
                        input.nextLine(); //clear the leftover linebreak from input.nextInt()
                        Cls.cls();
                        out.println("Current table data:");
                        out.println("-------------------");
                        out.println(tableData.getTableData(table));  //display current table data
                        for (int i = 1; i <= 4; i++) { //iterate through numbers 1 - 4 and prompt for new dishes for seats 1 - 4
                            boolean restart1 = false; //initialize restart variable for do-while loop
                            do {
                                out.println("Enter the dish number for seat " + i + " (Press Enter to skip, -L to list dishes, -C to clear seat)");
                                out.print("Seat " + i + ": "); //print the seat number and new dish
                                String dishString = input.nextLine(); //get the dish number
                                int nd; //initialize dish number variable
                                switch (dishString) {
                                    case "" -> {  //if the user presses enter, skip the seat
                                    }
                                    case "-L" -> {  //if the user enters -L
                                        Cls.cls();
                                        MenuData menuData = new MenuData(); //create new MenuData object to print the menu
                                        out.println("DISH LIST");
                                        out.println("---------");
                                        menuData.printMenu(); //print the menu
                                        out.println();
                                        restart1 = true;
                                    }
                                    case "-C" -> {
                                        tableData.clearSeat(table, i); //clear the seat
                                        restart1 = false;
                                    }
                                    default -> {
                                        try {
                                            nd = Integer.parseInt(dishString); //Try to parse the dish number to an integer
                                            tableData.addDish(table, i, nd); //add the dish to the table if successful
                                            restart1 = false;
                                        } catch (NumberFormatException e) { //catch invalid dish number
                                            out.println("Invalid dish number."); //print error message
                                            out.println("Press Enter to continue.");
                                            input.nextLine();
                                            restart1 = true;
                                        }
                                    }
                                }
                            }while (restart1); //loop until valid dish number is entered
                        }
                        Cls.cls();
                        out.println("New Table Data:");
                        out.println("---------------");
                        out.println(tableData.getTableData(table)); //display updated table data
                        out.println("Would you like to Edit a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine()); //restart the loop if the user enters yes
                    }while (restart); //loop until the user enters no
                }
                case 4 -> out.println("Goodbye.");

                default -> out.println("Invalid Choice."); //catch invalid choice and prompt for new choice
            }
        } while (choice != 4); //exit thread when user selects 4
    }
}
