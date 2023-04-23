package Main;
import Helpers.Round;
import Employee.EmployeeData;
import Helpers.Cls;
import Helpers.YesNo;
import Orders.OrderMenu;
import Tables.TableData;

import java.util.Scanner;

import static java.lang.System.out;

public class DailyOperation {
    public static void main(String[] args) {
        int choice;
        TableData tableData = new TableData(); //initialize tableData
        do {
            Cls.cls();
            Scanner input = new Scanner(System.in);
            out.println("What would you like to do");
            out.println();
            out.println("1: New order"); //loads test data while order classes are being made
            out.println("2: Print the bill for a table");
            out.println("3: View employee Assignments");
            out.println("4: Go back to Management Menu");
            out.print("[1,2,3,4]: ");
            choice = input.nextInt();
            input.nextLine(); //clear the leftover linebreak from input.nextInt()
            switch (choice){
                case 1 -> {
                    Cls.cls();
                    OrderMenu.main(new String[]{});
                }
                case 2 -> {
                    Cls.cls();
                    out.println("Enter the table number to total.");
                    out.print("[table number]: ");
                    int tableNumber = input.nextInt();
                    input.nextLine();
                    double total = tableData.getTotal(tableNumber);
                    out.println(tableData.getTableData(tableNumber)); //get table data
                    out.println("TOTAL: $" + Round.round(total, 2));
                    out.println("Ask the customer to enter a tip percentage.");
                    out.print("[15%, 18%, 20%...]: ");
                    String tipString = input.next().replaceAll("[^\\d.]", "");    //get tip percentage and remove all non-numeric characters
                    double tipPercent = Round.round(Double.parseDouble(tipString), 0); //round tip to two decimals));
                    double tipAmount = Round.round(total * (tipPercent / 100), 2); //calculate tip amount
                    out.println();
                    Cls.cls();
                    out.println();
                    out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                    out.println();
                    out.println("Tip: %" + tipPercent + " of $" + total +" = $" + tipAmount);
                    double totalWithTip = Round.round((total + tipAmount), 2);
                    out.println("Total with Tip: $" + totalWithTip);
                    out.println("Would you like to split the bill?");
                    out.print("[(Y)es/(N)o]: ");
                    String yn = input.nextLine();
                    double splitTotal;
                    if(YesNo.yesNo(input.nextLine())){
                        Cls.cls();
                        out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                        out.println("TOTAL: " + total);
                        out.println("How many people are splitting the bill?");
                        out.print("[1,2,3,4]: ");
                        double split = input.nextDouble();
                        splitTotal = Round.round((total / split), 2); //divide the total by the number of people and round to two decimals
                        input.nextLine(); //clear the leftover linebreak from input.nextDouble()
                        out.println();
                        Cls.cls();
                        out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                        out.println("TOTAL: $" + total);
                        out.print("Total split " + Math.round(split) + " ways: $" + splitTotal); //round to whole integer to remove decimal from double data type and print split total
                    }
                    out.println();
                    out.println("| Press enter to return to the menu. |");
                    input.nextLine();
                }
                case 3 -> {
                    EmployeeData employeeData = new EmployeeData();
                    employeeData.addEmployee("Test Server", "Server",1,1 ); //create test employee
                    out.println("Enter the employee's name. (Enter Test Server for testing.");
                    out.print("[name]: ");
                    String name = input.nextLine();
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
                    int lowTable = employeeData.sectionArray(name)[0]; //get the lowest table number
                    int highTable = employeeData.sectionArray(name)[1]; //get the highest table number
                    int[] arr = new int[highTable - lowTable + 1]; //make a new int array from lowTable to highTable
                    for (int i = lowTable; i <= highTable; i++) { //iterate through numbers from lowTable to highTable
                        arr[i - lowTable] = i; //add i (current number iteration) to the arr array
                    }
                    for (int currentValue : arr) { //iterate through number is arr array
                        out.println(tableData.getTableData(currentValue)); //print the table data for the current table number
                    }
                    out.println();
                    out.println("| Press Enter to Continue |");
                    input.nextLine();
                }
                case 4 -> {
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
