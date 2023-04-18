package Main;
import Employee.EmployeeData;
import Helpers.Cls;
import Helpers.YesNo;
import Tables.TableData;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
            out.println("1: Orders (Loads Test data)"); //loads test data while order classes are being made
            out.println("2: Print the bill for a table");
            out.println("3: View employee Assignments");
            out.println("4: Management Menu");
            out.print("[1,2,3,4]: ");
            choice = input.nextInt();
            input.nextLine(); //clear the leftover linebreak from input.nextInt()
            switch (choice){
                case 1 -> {
                    Cls.cls();
                    //still working on order classes

                    //Fill all tables with test data
                    File file = new File("Resources/default_menu_items.txt");
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    //fill an arraylist with foodMenu options from default data file
                    List <String> foodList = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (!line.startsWith("#")) { //skip comment lines
                            String[] itemDetails = line.split(","); //split lines at commas and only take first split (food name)
                            if (itemDetails.length == 4) { //double-check the line is formatted right
                                foodList.add(itemDetails[0].trim());
                            }else out.println("it broke");
                        }
                    }
                    out.println(foodList);
                    for (int i = 0; i < 21; i++) {
                        for (int s = 1; s < 5; s++) {
                            Collections.shuffle(foodList); //shuffle foodMenu to get a random result at index 0. (Math.random is too annoying to deal with)
                            tableData.addDish(i, s, foodList.get(0));
                        }out.println(tableData.getTableData(i));
                    }
                    scanner.close();
                    out.println("Test data loaded \n \n| Press enter to continue |");
                    input.nextLine();
                }
                case 2 -> {
                    Cls.cls();
                    out.println("Enter the table number to total.");
                    out.print("[table number]: ");
                    int tableNumber = input.nextInt();
                    input.nextLine();
                    double total = tableData.getTotal(tableNumber);
                    out.println(tableData.getTableData(tableNumber)); //get table data
                    out.println("TOTAL: " + total);
                    out.println("Ask the customer to enter a tip percentage.");
                    out.print("[15%, 18%, 20%...]: ");
                    String tipPercent = input.next().replaceAll("[^\\d.]", "");    //get tip percentage and remove all non-numeric characters
                    double tip = Math.round(Double.parseDouble(tipPercent));
                    double tipTotal = (total * (tip / 100)); //calculate tip total
                    out.println();
                    Cls.cls();
                    out.println();
                    out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                    out.println();
                    out.println("Tip: " + "($" + total * (tip / 100) + ") : " + tip + " of $" + total );
                    out.println("TOTAL: " + tipTotal);
                    out.println("Would you like to split the bill?");
                    out.print("[(Y)es/(N)o]: ");
                    double splitTotal;
                    if(YesNo.yesNo(input.nextLine())){
                        Cls.cls();
                        out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                        out.println("TOTAL: " + total);
                        out.println("How many people are splitting the bill?");
                        out.print("[1,2,3,4]: ");
                        double split = input.nextDouble();
                        splitTotal = (total / split); //divide the total by the number of people
                        input.nextLine(); //clear the leftover linebreak from input.nextDouble()
                        out.println();
                        Cls.cls();
                        out.println(tableData.getTableData(tableNumber)); //keeps table data visible after clear screen
                        out.print("Total split " + Math.round(split) + " ways: "); //round to whole integer to remove decimal from double datatype
                        out.println((Math.round(splitTotal * 100.0) / 100.0)); //round to 2 decimal places
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
            }
        } while (choice != 4); //exit thread when user enters 4
        Cls.cls();
    }
}
