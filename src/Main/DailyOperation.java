package Main;

import Employee.EmployeeData;
import Helpers.Cls;
import Helpers.Round;
import Helpers.YesNo;
import Orders.OrderMenu;
import Tables.TableData;

import java.util.Scanner;

import static java.lang.System.out;


/**
 * @todo DailyOperation.java Proofread comments
 * @body Proofread the code comments and ensure they are still in the correct locations
 * comments are complete
 */
public class DailyOperation {
    public static void main(String[] args) {
        int choice;
        TableData tableData = new TableData(); //initialize tableData
        do {
            Cls.cls();
            Scanner input = new Scanner(System.in); //initialize scanner
            out.println("What would you like to do");
            out.println();
            out.println("1: New order");
            out.println("2: Print the bill for a table");
            out.println("3: View employee Assignments");
            out.println("4: Go back to Management Menu");
            out.print("[1,2,3,4]: ");
            choice = input.nextInt(); //get user input
            input.nextLine(); //clear the leftover linebreak from input.nextInt()
            switch (choice){
                case 1 -> { //New order
                    Cls.cls();
                    OrderMenu.main(new String[]{}); //call OrderMenu
                }
                case 2 -> { //Print the bill for a table
                    Cls.cls();
                    boolean numValid = false;
                    do {
                        out.println("Enter the table number to total. (Enter -L to list tables)"); //add -L option to list data from filled tables
                        out.print("[table number]: ");
                        String tableNumberString = input.nextLine(); //get table number
                        try {
                            int tableNumber = Integer.parseInt(tableNumberString); //get table number
                            double total = tableData.getTotal(tableNumber); //get total
                            out.println(tableData.getTableData(tableNumber)); //get table data
                            out.println("TOTAL: $" + Round.round(total, 2)); //print total rounded to two decimals
                            out.println("Ask the customer to enter a tip percentage.");
                            out.print("[15%, 18%, 20%...]: ");
                            String tipString = input.next().replaceAll("[^\\d.]", "");    //get tip percentage and remove all non-numeric characters
                            double tipPercent = Round.round(Double.parseDouble(tipString), 0); //round tip percent to whole number
                            double tipAmount = Round.round(total * (tipPercent / 100), 2); //calculate tip amount and round to two decimals
                            out.println();
                            Cls.cls();
                            out.println();
                            out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                            out.println();
                            out.println("Tip: %" + tipPercent + " of $" + total + " = $" + tipAmount); //print tip percentage and amount
                            double totalWithTip = Round.round((total + tipAmount), 2); //calculate total with tip and round to two decimals
                            out.println("Total with Tip: $" + totalWithTip); //print total with tip
                            out.println("Would you like to split the bill?");
                            out.print("[(Y)es/(N)o]: ");
                            input.nextLine(); //clear the leftover linebreak from input.nextDouble()
                            double splitTotal;
                            if (YesNo.yesNo(input.nextLine())) { //if user enters yes
                                Cls.cls();
                                out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                                out.println("TOTAL: " + total);
                                out.println("How many people are splitting the bill?");
                                out.print("[1,2,3,4]: ");
                                double split = input.nextDouble(); //get number of people splitting the bill
                                splitTotal = Round.round((total / split), 2); //divide the total by the number of people and round to two decimals
                                input.nextLine(); //clear the leftover linebreak from input.nextDouble()
                                Cls.cls();
                                out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                                out.println("TOTAL: $" + total); //print total before split
                                out.print("Total split " + Math.round(split) + " ways: $" + splitTotal); //round to whole integer to remove empty decimal from double data type and print split total
                            }
                            out.println();
                            numValid = true;
                            out.println("| Press enter to return to the menu. |");
                            input.nextLine();
                        } catch (NumberFormatException e) {
                            Cls.cls();
                            if (tableNumberString.equalsIgnoreCase("-L")) { //if user enters -L
                                out.println("Table Data");
                                out.println("----------\n");
                                out.println(tableData.listTables()); //print data from filled tables
                                out.println();
                                out.println("| Press enter to continue. |");
                                input.nextLine();
                            }
                        }
                    }while (!numValid);
                }
                case 3 -> { //View employee assignments
                    EmployeeData employeeData = new EmployeeData(); //Create new employeeData object
                    Cls.cls();
                    boolean restart;
                    do {
                        out.println("Enter the employee's name. (Enter -L to list employee names"); //add -L option to list all employees
                        out.print("[name]: ");
                        String name = input.nextLine(); //get employee name
                        if(name.equalsIgnoreCase("-L")){
                            out.println(employeeData.listEmployee(1));
                            restart = true;
                        }else {
                            Cls.cls();
                            out.println("Employee Information");
                            out.println("--------------------\n");
                            out.println(employeeData.searchEmployee(name)); //print employee information
                            out.println();
                            out.println("| Press enter to view Table Assignments |");
                            input.nextLine();
                            Cls.cls();
                            out.println("Table Data");
                            out.println("----------\n");
                            int lowTable = employeeData.sectionArray(name)[0]; //get the lowest table number of the section the employee is assigned to
                            int highTable = employeeData.sectionArray(name)[1]; //get the highest table number of the section the employee is assigned to
                            int[] arr = new int[highTable - lowTable + 1]; //make a new int array from lowTable to highTable
                            for (int i = lowTable; i <= highTable; i++) { //iterate through numbers from lowTable to highTable
                                arr[i - lowTable] = i; //add the current number to the array
                            }
                            for (int currentValue : arr) { //iterate through number is arr array
                                out.println(tableData.getTableData(currentValue)); //print the table data for the current table number
                            }
                            out.println();
                            out.println("| Press Enter to Continue |");
                            input.nextLine();
                            restart = false;
                        }
                    } while (restart);
                }
                case 4 -> { //Go back to Management Menu
                    Cls.cls();
                    out.println("Returning to Management Menu...");
                    out.println();
                    out.println("| Press enter to continue |");
                    input.nextLine();
                }
            }
        } while (choice != 4); //exit thread when user enters 4
        Cls.cls();
    }
}
