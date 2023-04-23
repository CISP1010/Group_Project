package Main;
import Employee.EmployeeMenu;
import Helpers.Cls;
import Menu.MenuMenu;
import Orders.OrderMenu;
import Tables.TableMenu;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * The main class for the restaurant management system program.
 */
public class Main {
    static Scanner input = new Scanner(System.in);
    /**
     * The main method that runs the program and displays the main menu.
     * @param args The arguments passed into the program
     */
    public static void main(String[] args) {
        int choice;
        int sel;
        do { //loops until the user selects the menu option 3
            out.println("What would you like to do?\n");
            out.println("1: Management mode");
            out.println("2: Daily Operation mode");
            out.println("3: Exit");
            out.print("[1,2,3]: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> { //Management Menu
                    boolean login;
                    String password = "CISP1010";
                    do { //loops until the user inputs the correct password
                        Cls.cls();
                        out.println("Hint: Password is CISP1010");
                        out.print("[Enter Password] : ");
                        if (input.nextLine().equals(password)) {
                            out.println("Password accepted");
                            login = true;
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                out.println("My slumber was interrupted");
                            }
                            do { //loops until the user selects the menu option 5
                                Cls.cls();
                                out.println("Management Menu");
                                out.println("---------------\n");
                                out.println("What would you like to do?");
                                out.println("1: Employee Management");
                                out.println("2: Table Management");
                                out.println("3: Order Management");
                                out.println("4: Food Menu Management");
                                out.println("5: Go back");
                                out.print("[1,2,3,4,5]: ");
                                sel = input.nextInt();
                                switch (sel) {
                                    case 1 -> { //Employee Management
                                        Cls.cls();
                                        EmployeeMenu.main(new String[]{});
                                    }
                                    case 2 -> { //Backend Table Management
                                        Cls.cls();
                                        TableMenu.main(new String[]{});
                                    }
                                    case 3 -> { //Backend Order Management
                                        Cls.cls();
                                        OrderMenu.main(new String[]{});
                                    }
                                    case 4 -> { //Backend Food Menu Management
                                        Cls.cls();
                                        MenuMenu.main(new String[]{});
                                    }
                                    case 5 -> { // exit
                                        Cls.cls();
                                        out.println("Logging Out...");
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            out.println("My slumber was interrupted");
                                        }
                                        Cls.cls();
                                    }
                                }
                            }while (sel != 5) ;
                        } else {
                            out.println("Password denied (It's literally right there???)"); //lol
                            try {
                                Thread.sleep(3000); //wait for 3 seconds to display message
                            } catch (InterruptedException e) {
                                out.println("My slumber was interrupted");
                            }
                            login = false;
                        }
                    } while (!login);
                }
                case 2 -> { // Daily normal user menu
                    DailyOperation.main(new String[]{});
                }
                case 3 -> { // Exit
                    out.println("Goodbye!");
                    out.print(".");
                    for(int i = 0; i < 5; i++){ //for loop to add "." to the line ever half second.... Probably not necessary. But it looks cool
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            out.println("My slumber was interrupted");
                        }
                        out.print(".");
                    }
                    Cls.cls();
                }
                default -> out.println("Invalid choice \"" + choice + "\" please enter a valid option."); //catch invalid choice and prompt for a new choice
            }
        } while (choice != 3); //end thread if choice is 3
    }
}


