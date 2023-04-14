package Employee;

import Helpers.Cls;
import Helpers.YesNo;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * This class provides a command-line interface for managing the  Restaurant Employee.Employee management system.
 * Users can View, Add, and Edit all employee data.
 */
public class EmployeeMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        EmployeeData employeeData = new EmployeeData();
        int choice;
        do {
            //clear screen
            Cls.cls();
            // Display the employee management options
            out.println("What would you like to do?");
            out.println("1: Add an Employee.Employee");
            out.println("2: Search for an Employee.Employee");
            out.println("3: Edit an Employee.Employee");
            out.println("4: List data from all Employees");
            out.println("5: Exit");
            out.print("[1,2,3,4,5]: ");
            // Get the user's choice
            choice = input.nextInt();
            input.nextLine();
            // Check which task the user chose
            switch (choice) {
                case 1 -> {
                    Cls.cls();
                    // Prompt the user to enter employee information
                    boolean restart;
                    do {
                        Cls.cls();
                        out.println("Enter the employee name.");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        out.println("Enter their position.");
                        out.print("[position]: ");
                        String position = input.nextLine();
                        out.println(employeeData.listSection());
                        out.println("Enter their section.");
                        out.print("[section]: ");
                        int sections = input.nextInt();
                        input.nextLine();
                        out.println(employeeData.getScheduleOptions());
                        out.println("Would you like to create a new schedule?");
                        out.print("[(Y)es/(N)o]: ");
                        if (YesNo.yesNo(input.nextLine())) {
                            out.println("Enter the the new schedule. (Eg. Monday-Friday, 9am-5pm)");
                            out.print("[schedule]: ");
                            String newSchedule = String.valueOf(input.nextLine());
                            employeeData.addScheduleOptions(newSchedule);
                        }
                        out.println(employeeData.getScheduleOptions());
                        out.println("Enter the number for their schedule.");
                        out.print("[0,1,2...]: ");
                        int scheduleOption = input.nextInt();
                        input.nextLine();
                        // Add the employee to the database
                        employeeData.addEmployee(name, position, scheduleOption, sections);
                        out.println("Would you like to create another employee?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 2 -> {
                    boolean restart;
                    do {
                        Cls.cls();
                        // Prompt the user to enter the name of the employee to search for
                        out.println("Enter the Employee.Employee name.");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        // Search for the employee in the database and display their information
                        out.println(employeeData.searchEmployee(name));
                        out.println("Would you like to search again?");
                        out.print("[(Y)es/(N)o]: ");
                        out.println();
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 3 -> {
                    boolean restart;
                    do {
                        Cls.cls();
                        // Prompt the user to enter the name of the employee to edit and their new information
                        out.println("Enter the Employee.Employee name. (Enter -L to list Employee.Employee names or -C to cancel.)");
                        String name = input.nextLine();
                        if (employeeData.isEmployee(name)) {
                            String backupName = name + "BACKUP";
                            out.println("Employee.Employee found! \n" + employeeData.searchEmployee(name));
                            out.println("Enter the new name (Press enter to leave unchanged)");
                            String newName = input.nextLine();
                            out.println("Enter the new position (Press enter to leave unchanged)");
                            String newPosition = input.nextLine();
                            out.println(employeeData.listSection());
                            out.println("Enter the new section (Press enter to leave unchanged)");
                            int newSection = input.nextInt();
                            out.println(employeeData.getScheduleOptions());
                            out.println("Enter the new schedule option (Press enter to leave unchanged)");
                            int newScheduleOption = input.nextInt();
                            input.nextLine();
                            employeeData.editEmployee(name, newName, newPosition, newScheduleOption, newSection);
                            if (!newName.isEmpty()) {
                                out.println("New Information:\n" + employeeData.searchEmployee(newName));
                            } else out.println("New Information:\n" + employeeData.searchEmployee(name));
                            out.println("Is this correct?");
                            out.print("[(Y)es/(N)o]: ");
                            String yn = input.nextLine();
                            if (YesNo.yesNo(String.valueOf(yn))) {
                                employeeData.remEmployee(backupName);
                                out.println("Employee.Employee successfully updated!");
                                out.println("Would you like to edit another employee?");
                                out.print("[(Y)es/(N)o]: ");
                                restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                            } else {
                                out.println("Employee.Employee data restored:");
                                out.println(employeeData.searchEmployee(name));
                                out.println("Please try again.");
                                restart = true;
                            }
                        } else if (name.equalsIgnoreCase("-L")) {
                            out.println(employeeData.listEmployee(1));
                            restart = true;
                        } else if (name.equalsIgnoreCase("-C")) {
                            break;
                        } else {
                            out.println("Employee.Employee not found!");
                            restart = true;
                        }
                    } while (restart);
                }
                case 4 -> {
                    Cls.cls();
                    // Prompt the user to choose what data to display
                    out.println("What would you like to list?");
                    out.println("1: Names only");
                    out.println("2: Names, positions, and sections");
                    out.println("3: Names, positions, sections, and schedules");
                    out.print("[1,2,3]: ");
                    int data = input.nextInt();
                    input.nextLine();
                    // Display the list of employees based on the user's choice
                    out.println(employeeData.listEmployee(data));
                }
                case 5 -> {
                    Cls.cls();
                    out.println("Goodbye!");
                }
                default -> out.println("Invalid choice.");
            }


        } while (choice != 7);
    }
}


