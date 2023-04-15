package Menu;
import java.util.Scanner;

public class MenuMenu {
    static MenuData menuData = new MenuData();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sel;
        do{
        System.out.println("What would you like to do?");
        System.out.println("1: Show the food menu.");
        System.out.println("2: Edit Menu options.");
        sel = scanner.nextInt();
        switch(sel){
            case 1 -> {
                System.out.println(menuData.printMenu());
            }
        }
        }while(sel < 3 || sel >0);
        scanner.close();
    }
}