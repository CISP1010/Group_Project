package Tables;
import Helpers.Cls;
import Helpers.YesNo;
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
        TableData tableData = new TableData();
        int choice;
        do {
            Cls.cls();
            out.println("What would you like to do?");
            out.println("1: View all table status");
            out.println("2: View specific table status");
            out.println("3: Manually edit a table");
            out.println("4: Exit");
            out.print("[1,2,3,4]: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> { //show all table data in formatted string
                    Cls.cls();
                    out.println(tableData.listTables()); //print table data
                    out.println("Press Enter to continue.");
                    input.nextLine();
                }
                case 2 -> { //show specific table data in formatted string
                    boolean restart;
                    do {
                        Cls.cls();
                        out.println("Enter the table number.");
                        out.print("[1-20]: ");
                        int table = input.nextInt();
                        input.nextLine();
                        Cls.cls();
                        out.println(tableData.getTableData(table)); //print table data from tableData hashmap
                        out.println("Would you like to view a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 3 -> { //edit a table
                    boolean restart;
                    do{
                        Cls.cls();
                        out.println("TABLE EDITOR");
                        out.println("-------------");
                        out.println("Enter the table number.");
                        out.print("[1-20]: ");
                        int table = input.nextInt();
                        input.nextLine();
                        Cls.cls();
                        out.println("Current table data:");
                        out.println("-------------------");
                        out.println(tableData.getTableData(table));  //display current table data
                        for (int i = 1; i <= 4; i++) { //iterate through numbers 1 - 4 and prompt for new dishes for seats 1 - 4
                            out.println("Enter the dish for seat " + i +  " (or press Enter to skip.)");
                            out.print("Seat " + i + ": "); //print the seat number and new dish
                            String nd = input.nextLine();
                            tableData.addDish(table, i, nd);
                        }
                        Cls.cls();
                        out.println("New Table Data:");
                        out.println("---------------");
                        out.println(tableData.getTableData(table)); //display updated table data
                        out.println("Would you like to Edit a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    }while (restart);
                }
                case 4 -> out.println("Goodbye.");

                default -> out.println("Invalid Choice."); //catch invalid choice and prompt for new choice
            }
        } while (choice != 4); //exit thread when user selects 4
    }
}
