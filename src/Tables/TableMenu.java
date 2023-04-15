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
     * The main method for running the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(in);
        TableData tableData = new TableData();
        int choice;
        do {
            Cls.cls();
            // Display the employee management options
            out.println("What would you like to do?");
            out.println("1: View all table status");
            out.println("2: View specific table status");
            out.println("3: Manually edit a table");
            out.println("4: Exit");
            out.print("[1,2,3,4]: ");
            // Get the user's choice
            choice = input.nextInt();
            input.nextLine();
            // Check which task the user chose
            switch (choice) {
                case 1 -> {
                    Cls.cls();
                    out.println(tableData.listTables());
                    out.println("Press Enter to continue.");
                    input.nextLine();
                }
                case 2 -> {
                    boolean restart;
                    do {
                        Cls.cls();
                        out.println("Enter the table number.");
                        out.print("[1-20]: ");
                        int table = input.nextInt();
                        input.nextLine();
                        Cls.cls();
                        out.println(tableData.getTableData(table));
                        out.println("Would you like to view a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    } while (restart);
                }
                case 3 -> {
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
                        out.println(tableData.getTableData(table));
                        for (int i = 1; i <= 4; i++) {
                            out.println("Enter the dish for seat " + i +  " (or press Enter to skip.)");
                            out.print("Seat " + i + ": ");
                            String nd = input.nextLine();
                            tableData.addDish(table, i, nd);
                        }
                        Cls.cls();
                        out.println("New Table Data:");
                        out.println("---------------");
                        out.println(tableData.getTableData(table));
                        out.println("Would you like to Edit a different table?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());
                    }while (restart);
                }
                case 4 -> {
                    out.println("Goodbye.");
                }

                default -> out.println("Invalid Choice.");
            }
        } while (choice < 4 && choice > 0);
        input.close();
    }
}
