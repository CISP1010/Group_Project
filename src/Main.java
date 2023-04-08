import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * The Main class contains the main method for the Restaurant management system.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        EmployeeData employeeData = new EmployeeData();

        int choice = 0;
        int section;
        do {
            // Display the menu section options
            out.println("Enter a Menu section");
            out.println("1: Employee Management");
            // Get the user's choice
            section = input.nextInt();
            // Check if the user chose employee management
            if (section == 1) {
                // Display the employee management options
                out.println("What would you like to do?");
                out.println("1: Add an Employee");
                out.println("2: Search for an Employee");
                out.println("3: Edit an Employee");
                out.println("4: List all Employees");
                out.println("5: Exit");
                // Get the user's choice
                choice = input.nextInt();
                input.nextLine();
                // Check which task the user chose
                switch (choice) {
                    case 1 -> {
                        // Prompt the user to enter employee information
                        out.println("Enter the employee name.");
                        String name = input.nextLine();
                        out.println("Enter their position.");
                        String position = input.nextLine();
                        out.println(employeeData.getScheduleOptions());
                        out.println("Enter their schedule option.");
                        String scheduleOption = input.nextLine();
                        // Add the employee to the database
                        employeeData.addEmployee(name, position, scheduleOption);
                    }
                    case 2 -> {
                        // Prompt the user to enter the name of the employee to search for
                        out.println("Enter the Employee name.");
                        String name = input.nextLine();
                        // Search for the employee in the database and display their information
                        out.println(employeeData.searchEmployee(name));
                    }
                    case 3 -> {
                        // Prompt the user to enter the name of the employee to edit and their new information
                        out.println("Enter the Employee name.");
                        String name = input.nextLine();
                        out.println("Enter the new name (Or re-enter the same name to keep unchanged.)");
                        String newName = input.nextLine();
                        out.println("Enter the new position (Or re-enter the same position to keep unchanged.)");
                        String newPosition = input.nextLine();
                        out.println(employeeData.getScheduleOptions());
                        out.println("Enter the new schedule option (Or re-enter the same option to keep unchanged.)");
                        String newScheduleOption = input.nextLine();
                        // Update the employee information in the database
                        employeeData.editEmployee(name, newName, newPosition, newScheduleOption);
                    }
                    case 4 -> {
                        // Prompt the user to choose whether to display only employee names or all data
                        out.println("What would you like to list?");
                        out.println("1: Names only");
                        out.println("2: All Data");
                        int data = input.nextInt();
                        input.nextLine();
                        boolean i;
                        i = data == 1;
                        // Display the list of employees based on the user's choice
                        out.println(employeeData.listEmployee(i));
                    }
                    case 5 -> out.println("Goodbye!");
                    default -> out.println("Invalid choice.");
                }
            } else {
                // Display an error message for invalid menu section options
                out.println("Invalid entry");
            }
        } while (choice != 5);
    }
}
