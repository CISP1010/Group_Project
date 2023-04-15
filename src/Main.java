import Menu.MenuMenu;
import Employee.EmployeeMenu;
import Orders.OrderMenu;
import Tables.TableMenu;
import Helpers.Cls;
import Helpers.YesNo;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        out.println("What would you like to do?\n");
        out.println("1: Management mode");
        out.println("2: Daily Operation mode");
        out.print("[1,2]: ");
        // Get the user's choice
        int choice = input.nextInt();
        input.nextLine();
        // Check which task the user chose
        switch (choice) {
            case 1 -> {
                boolean login;
                String password = "CISP1010";
                do {
                    Cls.cls();
                    out.println("Hint: Password is CISP1010");
                    out.println("[Enter Password] : ");
                    if (input.nextLine().equals(password)) {
                        out.println("Password accepted");
                        login = true;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            out.println("My slumber was interrupted");
                        }
                        Cls.cls();
                        out.println("Management Menu");
                        out.println("---------------\n");
                        out.println("What would you like to do?");
                        out.println("1: Employee Management");
                        out.println("2: Table Management");
                        out.println("3: Order Management");
                        out.println("4: Food Menu Management");
                        int sel = input.nextInt();
                        switch (sel){
                            case 1 ->{
                                EmployeeMenu.main(new String[] {});
                                out.println("we are back");
                            }
                        }
                    } else {
                        out.println("Password denied (It's literally right there???)");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            out.println("My slumber was interrupted");
                        }
                        login = false;
                    }
                } while (!login);

            }

        }
    }
}

