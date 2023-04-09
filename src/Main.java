import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * The Main class contains the main method for the Restaurant management system.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        input.useDelimiter("[|]");
        EmployeeData employeeData = new EmployeeData();
        Cls cls = new Cls();

        int choice = 0;
        int section;
        do {
            //clear screen
            cls.cls();
            // Display the menu section options
            out.println("Enter a Menu section");
            out.println("1: Employee Management");
            out.println("2: Edit Schedules Options");
            out.print("[1, 2]: ");
            // Get the user's choice
            section = input.nextInt();
            // Check if the user chose employee management
            if (section == 1) {
                //clear scren
                cls.cls();
                // Display the employee management options
                out.println("What would you like to do?");
                out.println("1: Add an Employee");
                out.println("2: Search for an Employee");
                out.println("3: Edit an Employee");
                out.println("4: List data from all Employees");
                out.println("5: Exit");
                out.print("[1,2,3,4,5]: ");
                // Get the user's choice
                choice = input.nextInt();
                input.nextLine();
                // Check which task the user chose
                switch (choice) {
                    case 1 -> {
                    cls.cls();
                    // Prompt the user to enter employee information
                    boolean restart = true;
                        do{
                            cls.cls();
                            out.println("Enter the employee name.");
                            out.print("[name]");
                            String name = input.nextLine();
                            out.println("Enter their position.");
                            out.print("[position]: ");
                            String position = input.nextLine();
                            out.println("Enter their section.");
                            out.print("[section]: ");
                            int sections = input.nextInt();
                            out.println(employeeData.getScheduleOptions());
                            out.println("Would you like to create a new schedule?");
                            out.print("[(Y)es/(N)o]: ");
                            String yn = input.nextLine();
                                if(yn == "Y" || yn == "y" || yn == "Yes" || yn == "yes"){
                                    out.println("Enter the the new schedule. (Eg. Monday-Friday, 9am-5pm)");
                                    out.print("[schedule]: ");
                                    String newSchedule = input.nextLine();
                                    out.println("Would you like to save this schedule to use it in the future?");
                                    out.print("[(Y)es/(N)o]: ");
                                    String save = input.nextLine();
                                    if(save == "Y" || save == "y" || save == "Yes" || save == "yes"){
                                        employeeData.addScheduleOptions(newSchedule);
                                    }
                                }
                            out.println("Enter the number for their schedule.");
                            out.print("[1,2,3...]");
                            int scheduleOption = input.nextInt();
                            // Add the employee to the database
                            employeeData.addEmployee(name, position, scheduleOption, sections);
                            out.println("");
                            out.println("Would you like to create another employee?");
                            out.print("[(Y)es/(N)o]: ");
                            String again = input.nextLine();
                            if(again == "N" || again == "n" || again == "No" || again == "no"){
                                restart = false;
                            }else restart = true;
                        }while (restart == true);
                    }   
                    case 2 -> {
                    boolean restart = true;
                        do{
                            cls.cls();
                            // Prompt the user to enter the name of the employee to search for
                            out.println("Enter the Employee name.");
                            out.print("[name: ]");
                            String name = input.nextLine();
                            // Search for the employee in the database and display their information
                            out.println(employeeData.searchEmployee(name));
                            out.println("Would you like to search again?");
                            out.print("[(Y)es/(N)o]: ");
                            String yn = input.nextLine();
                            if(yn == "N" || yn == "n" || yn == "No" || yn == "no"){
                                restart = false;
                            }else restart = true;
                        }while (restart == true);
                    }
                    case 3 -> {
                    boolean restart = true;
                    do{
                        cls.cls();
                        // Prompt the user to enter the name of the employee to edit and their new information
                        out.println("Enter the Employee name.");
                        String name = input.nextLine();
                        out.println("Enter the new name (Press enter to leave unchanged)");
                        String newName = input.nextLine();
                        out.println("Enter the new position (Press enter to leave unchanged)");
                        String newPosition = input.nextLine();
                        out.println("Enter the new section (Press enter to leave unchanged)");
                        int newSection = input.nextInt();
                        out.println(employeeData.getScheduleOptions());
                        out.println("Enter the new schedule option (Press enter to leave unchanged)");
                        int newScheduleOption = input.nextInt();
                        employeeData.editEmployee(name, newName, newPosition, newScheduleOption, newSection);
                        out.println("Would you like to edit another employee?");
                        out.print("[(Y)es/(N)o]: ");
                        String yn = input.nextLine();
                        if(yn == "N" || yn == "n" || yn == "No" || yn == "no"){
                            restart = false;
                        }else restart = true;
                    }while (restart == true);
                    }
                    case 4 -> {
                        cls.cls();
                        // Prompt the user to choose what data to display
                        out.println("What would you like to list?");
                        out.println("1: Names only");
                        out.println("2: Names, positions, and sections");
                        out.println("3: Names, positions, sections, and schedules");
                        int data = input.nextInt();
                        input.nextLine();
                        // Display the list of employees based on the user's choice
                        out.println(employeeData.listEmployee(data));
                    }
                    case 5 -> {
                        cls.cls();
                        out.println("Goodbye!");
                    }
                    default -> out.println("Invalid choice.");
                }
            } else {
                // Display an error message for invalid menu section options
                out.println("Invalid entry");
            }
        } while (choice != 7);
    input.close();
    }
}
class Cls {
    public void cls() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
} 
