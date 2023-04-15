package Employee;

import Helpers.Cls;
import Helpers.YesNo;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * This class provides a command-line interface for managing the  Restaurant Employee management system.
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
            out.println("What would you like to do?\n");
            out.println("1: Add an Employee");
            out.println("2: Search for an Employee");
            out.println("3: Edit an Employee");
            out.println("4: List data from all Employees");
            out.println("5: Exit\n");
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
                        Cls.cls();
                        out.println("Enter their position.");
                        out.print("[position]: ");
                        String position = input.nextLine();
                        Cls.cls();
                        out.println("Sections");
                        out.println("--------\n");
                        out.println(employeeData.listSection());
                        out.println("Enter their section.");
                        out.print("[section]: ");
                        int sections = input.nextInt();
                        input.nextLine();
                        Cls.cls();
                        out.println("Schedules");
                        out.println("---------\n");
                        out.println(employeeData.getScheduleOptions());
                        out.println("Would you like to create a new schedule?");
                        out.print("[(Y)es/(N)o]: ");
                        if (YesNo.yesNo(input.nextLine())) {
                            Cls.cls();
                            out.println("Schedules");
                            out.println("---------\n");
                            out.println(employeeData.getScheduleOptions());
                            out.println("Enter the the new schedule. (Eg. Monday-Friday, 9am-5pm)");
                            out.print("[schedule]: ");
                            String newSchedule = String.valueOf(input.nextLine());
                            employeeData.addScheduleOptions(newSchedule);
                        }
                        Cls.cls();
                        out.println("Schedules");
                        out.println("---------\n");
                        out.println(employeeData.getScheduleOptions());
                        out.println("Enter the number for their schedule.");
                        out.print("[0,1,2...]: ");
                        int scheduleOption = input.nextInt();
                        input.nextLine();
                        // Add the employee to the database
                        employeeData.addEmployee(name, position, scheduleOption, sections);
                        Cls.cls();
                        System.out.println("New Employee");
                        System.out.println("------------\n");
                        System.out.println(employeeData.searchEmployee(name));
                        out.println("Would you like to create another employee?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 2 -> {
                    boolean restart;
                    Cls.cls();
                    do {
                        // Prompt the user to enter the name of the employee to search for
                        out.println("Enter the Employee name.");
                        out.println("------------------------\n");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        // Search for the employee in the database and display their information
                        Cls.cls();
                        if(!employeeData.isEmployee(name)){
                            out.println("Employee not found!");
                            out.println("-------------------\n");
                        }else{
                            out.println("Employee found!");
                            out.println("-------------------\n");
                            out.println(employeeData.searchEmployee(name));
                        }
                        out.println("Would you like to search again?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                        Cls.cls();
                    } while (restart);
                }
                case 3 -> {
                    boolean restart;
                    do {
                        Cls.cls();
                        // Prompt the user to enter the name of the employee to edit and their new information
                        out.println("Enter the Employee name. (Enter -L to list Employee names or -C to cancel.)");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        if (employeeData.isEmployee(name)) {
                            String backupName = name + "BACKUP";
                            Cls.cls();
                            out.println("Employee found!");
                            out.println("---------------\n");
                            out.print(employeeData.searchEmployee(name) + "\n");
                            out.println("Enter the new name (Press enter to leave unchanged)");
                            out.print("[name]: ");
                            String newName = input.nextLine();
                            Cls.cls();
                            out.println("Employee found!");
                            out.println("---------------\n");
                            out.print(employeeData.searchEmployee(name) + "\n");
                            out.println("Enter the new position (Press enter to leave unchanged)");
                            out.print("[position]: ");
                            String newPosition = input.nextLine();
                            Cls.cls();
                            out.println("Employee found!");
                            out.println("---------------\n");
                            out.print(employeeData.searchEmployee(name) + "\n");
                            out.println("Sections");
                            out.println("--------\n");
                            out.println(employeeData.listSection());
                            out.println("Enter the new section (Press enter to leave unchanged)");
                            out.print("[section]: ");
                            int newSection;
                            String inputString = input.nextLine();
                            if (inputString != null && !inputString.isEmpty()) {
                                try {
                                    newSection = Integer.parseInt(inputString.trim());
                                } catch (NumberFormatException e) {
                                    newSection = employeeData.getSection(name);
                                }
                            } else {
                                newSection = employeeData.getSection(name);
                            }
                            Cls.cls();
                            out.println("Employee found!");
                            out.println("---------------\n");
                            out.print(employeeData.searchEmployee(name) + "\n");
                            out.println("Schedules");
                            out.println("---------\n");
                            out.println(employeeData.getScheduleOptions());
                            out.println("Enter the new schedule option (Press enter to leave unchanged)");
                            out.print("[Schedule]: ");
                            int newSchedule;
                            String inputString2 = input.nextLine();
                            if (inputString2 != null && !inputString2.isEmpty()) {
                                try {
                                    newSchedule = Integer.parseInt(inputString2.trim());
                                } catch (NumberFormatException e) {
                                    newSchedule = employeeData.getSchedule(name);
                                }
                            } else {
                                newSchedule = employeeData.getSchedule(name);
                            }
                            employeeData.editEmployee(name, newName, newPosition, newSchedule, newSection);
                            Cls.cls();
                            if (!newName.isEmpty()) {
                                out.println("New Information");
                                out.println("---------------\n");
                                out.println(employeeData.searchEmployee(newName));
                            } else out.println("New Information:\n" + employeeData.searchEmployee(name));
                            out.println("Is this correct?");
                            out.print("[(Y)es/(N)o]: ");
                            String yn = input.nextLine();
                            if (YesNo.yesNo(String.valueOf(yn))) {
                                employeeData.remEmployee(backupName);
                                out.println("Employee successfully updated!");
                                out.println("Would you like to edit another employee?");
                                out.print("[(Y)es/(N)o]: ");
                                restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                            } else {
                                employeeData.employeeRestore(backupName, name, newName);
                                out.println("Employee data restored:");
                                out.println(employeeData.searchEmployee(name));
                                out.println("Press enter to try again.");
                                input.nextLine();
                                restart = true;
                            }
                        } else if (name.equalsIgnoreCase("-L")) {
                            Cls.cls();
                            out.println("Employees");
                            out.println("---------\n");
                            out.println(employeeData.listEmployee(1));
                            out.println("| Press enter to continue |");
                            input.nextLine();
                            restart = true;
                        } else if (name.equalsIgnoreCase("-C")) {
                            break;
                        } else {
                            out.println("Employee not found!");
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
                    out.println("| Press enter to continue |");
                    input.nextLine();
                }
                case 5 -> {
                    Cls.cls();
                    out.println("Goodbye!");
                }
                default -> out.println("Invalid choice.");
            }
        } while (choice < 5);
        input.close();
    }
}


