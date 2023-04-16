package Employee;
import Helpers.Cls;
import Helpers.YesNo;
import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * This class provides a command-line interface for managing the Employee management system.
 * Users can View, Add, and Edit all employee data.
 */
public class EmployeeMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        EmployeeData employeeData = new EmployeeData(); //Initialize the EmployeeData class
        int choice;
        do {
            Cls.cls(); //clear screen
            out.println("What would you like to do?\n");
            out.println("1: Add an Employee");
            out.println("2: Search for an Employee");
            out.println("3: Edit an Employee");
            out.println("4: List data from all Employees");
            out.println("5: Exit\n");
            out.print("[1,2,3,4,5]: ");
            choice = input.nextInt();
            input.nextLine(); //clear the leftover linebreak from input.nextInt()
            switch (choice) {
                case 1 -> { //Add employee
                    Cls.cls();
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
                        input.nextLine(); //clear the leftover linebreak from input.nextInt()
                        Cls.cls();
                        out.println("Schedules");
                        out.println("---------\n");
                        out.println(employeeData.getScheduleOptions());
                        out.println("Would you like to create a new schedule?");
                        out.print("[(Y)es/(N)o]: ");
                        if (YesNo.yesNo(input.nextLine())) { //calls the YesNo.yesno method from the helpers package to check what the user input
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
                        input.nextLine(); //clears the leftover linebreak from input.nextInt()
                        employeeData.addEmployee(name, position, scheduleOption, sections); //Adds the new employee to the employeeData hashmap
                        Cls.cls();
                        System.out.println("New Employee");
                        System.out.println("------------\n");
                        System.out.println(employeeData.searchEmployee(name)); //print new employee info
                        out.println("Would you like to create another employee?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 2 -> { //search employee
                    boolean restart;
                    Cls.cls();
                    do {
                        out.println("Enter the Employee name.");
                        out.println("------------------------\n");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        Cls.cls();
                        if(!employeeData.isEmployee(name)){ //check if name is not valid
                            out.println("Employee not found!");
                            out.println("-------------------\n");
                        }else{
                            out.println("Employee found!");
                            out.println("-------------------\n");
                            out.println(employeeData.searchEmployee(name)); //print employee data
                        }
                        out.println("Would you like to search again?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                        Cls.cls();
                    } while (restart);
                }
                case 3 -> { //Edit employee
                    boolean restart = false;
                    do {
                        Cls.cls();
                        out.println("Enter the Employee name. (Enter -L to list Employee names or -C to cancel.)");
                        out.print("[name]: ");
                        String name = input.nextLine();
                        if (employeeData.isEmployee(name)) {
                            String backupName = name + "BACKUP"; //saves old name for a failsafe
                            Cls.cls();
                            out.println("Employee found!");
                            out.println("---------------\n");
                            out.print(employeeData.searchEmployee(name) + "\n"); //Display current employee info
                            out.println("Enter the new name (Press enter to leave unchanged)");
                            out.print("[name]: ");
                            String newName = input.nextLine();
                            Cls.cls();
                            out.println("Employee found!"); //keeps info visible after clearscreen
                            out.println("---------------\n"); //keeps info visible after clearscreen
                            out.print(employeeData.searchEmployee(name) + "\n"); //keeps info visible after clearscreen
                            out.println("Enter the new position (Press enter to leave unchanged)");
                            out.print("[position]: ");
                            String newPosition = input.nextLine();
                            Cls.cls();
                            out.println("Employee found!"); //keeps info visible after clearscreen
                            out.println("---------------\n"); //keeps info visible after clearscreen
                            out.print(employeeData.searchEmployee(name) + "\n"); //keeps info visible after clearscreen
                            out.println("Sections");
                            out.println("--------\n");
                            out.println(employeeData.listSection()); //print section options
                            out.println("Enter the new section (Press enter to leave unchanged)");
                            out.print("[section]: ");
                            int newSection;
                            String inputString = input.nextLine();
                            if (inputString != null && !inputString.isEmpty()) {
                                try {
                                    newSection = Integer.parseInt(inputString.trim()); //This if block is for handling blank input,
                                } catch (NumberFormatException e) {                    //when the user does not update the value.
                                    newSection = employeeData.getSection(name);        //int datatypes can't be empty, this assigns the old section value if input was left blank.
                                }
                            } else {
                                newSection = employeeData.getSection(name);            //Just in case something goes wrong in the if block, this falls back on the old section.
                            }
                            Cls.cls();
                            out.println("Employee found!"); //keeps info visible after clearscreen
                            out.println("---------------\n"); //keeps info visible after clearscreen
                            out.print(employeeData.searchEmployee(name) + "\n"); //keeps info visible after clearscreen
                            out.println("Schedules");
                            out.println("---------\n");
                            out.println(employeeData.getScheduleOptions()); //display schedule options
                            out.println("Enter the new schedule option (Press enter to leave unchanged)");
                            out.print("[Schedule]: ");
                            int newSchedule;
                            String inputString2 = input.nextLine();
                            if (inputString2 != null && !inputString2.isEmpty()) {
                                try {
                                    newSchedule = Integer.parseInt(inputString2.trim()); // this if block exists for the same reason as the sections if block
                                } catch (NumberFormatException e) {
                                    newSchedule = employeeData.getSchedule(name);
                                }
                            } else {
                                newSchedule = employeeData.getSchedule(name); //fall back on old schedule
                            }
                            employeeData.editEmployee(name, newName, newPosition, newSchedule, newSection); //edit employee info. This also creates a backup
                            Cls.cls();
                            if (!newName.isEmpty()) { //check if a new name was specified, searches the old name if the name was not edited
                                out.println("New Information");
                                out.println("---------------\n");
                                out.println(employeeData.searchEmployee(newName)); //search for newname if exists
                            } else out.println("New Information:\n" + employeeData.searchEmployee(name)); //otherwise search for old name
                            out.println("Is this correct?"); //check if info is correct
                            out.print("[(Y)es/(N)o]: ");
                            String yn = input.nextLine();
                            if (YesNo.yesNo(String.valueOf(yn))) {  //if user enterd yes, Yes, Y, or y
                                employeeData.remEmployee(backupName); //delete the backup
                                out.println("Employee successfully updated!");
                                out.println("Would you like to edit another employee?");
                                out.print("[(Y)es/(N)o]: ");
                                restart = YesNo.yesNo(String.valueOf(input.nextLine()));
                            } else {
                                employeeData.employeeRestore(backupName, name, newName); //restore the backup
                                out.println("Employee data restored:");
                                out.println(employeeData.searchEmployee(name)); //print restored employee data. This also deletes the backup after it is restored
                                out.println("Press enter to try again.");
                                input.nextLine();
                                restart = true;
                            }
                        } else if (name.equalsIgnoreCase("-L")) { //List employee names
                            Cls.cls();
                            out.println("Employees");
                            out.println("---------\n");
                            out.println(employeeData.listEmployee(1));
                            out.println("| Press enter to continue |");
                            input.nextLine();
                            restart = true;
                        } else if (name.equalsIgnoreCase("-C")) { //return to menu
                            choice = 5;
                        } else {
                            out.println("Employee not found!");
                            restart = true;
                        }
                    } while (restart);
                }
                case 4 -> { //List employee data
                    Cls.cls();
                    out.println("What would you like to list?");
                    out.println("1: Names only");
                    out.println("2: Names, positions, and sections");
                    out.println("3: Names, positions, sections, and schedules");
                    out.print("[1,2,3]: ");
                    int data = input.nextInt();
                    input.nextLine(); //clear leftoever linebreak from input.nextInt()
                    out.println(employeeData.listEmployee(data)); //listEmployee will return a formatted matching the data parameter
                    out.println("| Press enter to continue |");
                    input.nextLine();
                }
                case 5 -> {
                    Cls.cls();
                }
                default -> out.println("Invalid choice \"" + choice + "\" please enter a valid option."); //catch invalid input and prompt to try again
            }
        } while (choice != 5); //end thread if  5 is entered
    }
}


