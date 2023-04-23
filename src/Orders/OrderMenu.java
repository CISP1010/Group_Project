package Orders;

import Helpers.Cls;
import Helpers.YesNo;
import Menu.MenuData;
import Tables.TableData;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * @todo Add missing code comments and cleanup
 * @body cleanup messy code and cleanup code comments
 */
public class OrderMenu {
        public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            OrderData orderData = new OrderData();  //initialize orderData
            MenuData menuData = new MenuData();    //initialize menuData
            TableData tableData = new TableData(); //initialize tableData
            int choice;

            //I might have caused an issue by using 'choice' as a var inside loops..maybe not though

            boolean restart = false;
            do {
                //clears screen
                Cls.cls();
                //Displays the order menu options
                System.out.println("What would you like to do?\n");
                System.out.println("Order Options:");
                System.out.println("1 - Create a new order.");
                System.out.println("2 - Modify an existing order.");
                System.out.println("3 - List all orders.");
                System.out.println("4 - Delete an order."); // maybe mark an order as completed too?
                System.out.println("5 - Return to the main menu.\n");
                System.out.print("Enter your selection [1,2,3,4,5]: ");
                //gets the user choice
                choice = input.nextInt();
                input.nextLine();
                //checks and proceeds with the user choice
                switch (choice) {
                    case 1 -> {
                        Cls.cls();
                        //starts the order enterin'
                        String custName;
                        String orderType;
                        int orderChoice;
                        boolean yn;
                        int itemNum;
                        int partySize = 0;
                        int tableNum = 0;
                        int seatNum = 0;
                        int orderNum = orderData.getNewOrderNum();
                        String orderFor;
                        String address = "";
                        String phone = "";
                        String deliveryNotes = "";
                        ArrayList<Integer> itemSel = new ArrayList<>();
                            Cls.cls();
                            out.println("Enter the customer's last name.");
                            out.print("[last name]: ");
                            custName = input.nextLine();
                            Cls.cls();
                            out.println("Is this order for \n 1: Dine-in \n 2: Delivery \n 3: Pick up?");
                            out.print("[order type]: ");
                            // orderChoice takes the number to avoid confusion on my part...
                            orderChoice = input.nextInt();
                        if (orderChoice == 1) {
                                //dine in
                                Cls.cls();
                                orderType = "Dine-in";
                                out.println("How big is the party?");
                                out.print("[party size]: ");
                                partySize = input.nextInt();
                                input.nextLine(); //clears the newline character
                                Cls.cls();
                                boolean tableNumValid = false;
                                do {
                                    out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                    out.print("[table #]: ");
                                    String tableNumString = input.nextLine();
                                    try {
                                        if (!tableData.isFilled(Integer.parseInt(tableNumString))) {
                                            tableNum = Integer.parseInt(tableNumString);
                                            tableNumValid = true;
                                        }
                                    } catch (NumberFormatException e) {
                                        if (tableNumString.equalsIgnoreCase("-L")) {
                                            out.println(tableData.getEmptyTables());
                                            out.println("Press enter to continue.");
                                            input.nextLine();
                                        } else if (tableNumString.equalsIgnoreCase("-C")) { //cancel
                                            Cls.cls();
                                            out.println("Order cancelled.");
                                            out.println("Press enter to continue.");
                                            input.nextLine();
                                            break;
                                        } else {
                                            tableNum = Integer.parseInt(tableNumString);
                                        }
                                    }
                                } while (!tableNumValid);
                                Cls.cls();
                                out.println("Table: " + tableNum);
                                out.println(tableData.getTableData(tableNum));
                                out.println("Enter the seat number where this dish should be served.");
                                out.print("[seat #]: ");
                                seatNum = input.nextInt();
                                input.nextLine(); //clears the newline character
                                orderFor = custName +  " | Table: " + tableNum + " | Seat: " + seatNum;

                            } else if (orderChoice == 2) {          //delivery
                                orderType = "Delivery";
                                out.println("Enter the address.");
                                out.print("[address]: ");
                                address = input.nextLine();
                                Cls.cls();
                                out.println("Enter the phone number.");
                                out.print("[phone]: ");
                                phone = input.nextLine();
                                Cls.cls();
                                out.println("Enter the order notes. (optional)");
                                out.print("[notes]: ");
                                if(!input.nextLine().isEmpty()){
                                    deliveryNotes = input.nextLine();
                                }else deliveryNotes = "";
                                orderFor = "Delivery: " + custName;

                            } else {
                                orderType = "Pick up";                   //pick up
                                out.println("Enter the phone number.");
                                out.print("[phone]: ");
                                phone = input.nextLine();
                                orderFor = "Pick up: " + custName;
                            }

                            //Build orders
                            do {
                                do {
                                    menuData.printMenu();
                                    out.println("Ordering for: " + orderFor);
                                    out.println("Enter the number of the item you would like to add to the order.");
                                    out.print("[menu item(#)]: ");
                                    itemNum = input.nextInt();
                                    if(menuData.isItem(itemNum)) {
                                        input.nextLine(); //clears the newline character
                                        Cls.cls();
                                        out.println("Ordering for: " + orderFor);
                                        out.println("You chose: " + menuData.getName(itemNum));
                                        out.println("Is this correct?");
                                        out.print("[(Y)es/(N)o]: ");
                                        yn = YesNo.yesNo(input.nextLine());
                                    }else{
                                        out.println("Invalid item number. Please try again.");
                                        yn = false;
                                    }
                                } while (!yn);
                                itemSel.add(itemNum);
                                Cls.cls();
                                out.println("Ordering for: " + orderFor);
                                StringBuilder sb = new StringBuilder();
                                for(int i : itemSel){
                                    sb.append(menuData.getName(i)).append(", ");
                                }
                                out.println(sb);
                                out.println("Would you like add another item?");
                                out.print("[(Y)es/(N)o]: ");
                                yn = YesNo.yesNo(input.nextLine());
                            }while (yn);

                        //submit orders
                        if (orderChoice == 1) {
                            //dine in
                            for (int i : itemSel) {
                                tableData.addDish(tableNum, seatNum, i);
                            }
                            out.println("Dishes added to Table: \n" + tableData.getTableData(tableNum));
                            Order order = new Order(orderNum, custName, itemSel, orderType, tableNum, "", "", "");
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n");

                        } else if (orderChoice == 2) {
                            //delivery
                            Order order = new Order(orderNum,custName,itemSel,orderType,-1, address, phone, deliveryNotes);
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n");
                        } else {
                            //pick up
                            Order order = new Order(orderNum, custName, itemSel, orderType, -1, "", phone, "");
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n");
                        }
                        out.println("Would you like to return to the order menu?");
                        out.print("[(Y)es/(N)o]: ");
                        restart = YesNo.yesNo(input.nextLine());

                    }

                    case 2 -> {
                        
                        /**
                        * @todo Debug order editing and cleanup code
                        * @body Debug order editing and cleanup code
                        */
                        Cls.cls();
                        out.println("Enter the number of the order to modify: (-L to list all open orders)");
                        out.println("[order #]: ");
                        String orderNumString = input.nextLine();
                        String custName;
                        String orderType;
                        int orderChoice;
                        boolean yn;
                        boolean retry = false;
                        int itemNum;
                        int partySize = 0;
                        int tableNum = 0;
                        int seatNum = 0;
                        int orderNum = orderData.getNewOrderNum();
                        String orderFor;
                        String address = "";
                        String phone = "";
                        String deliveryNotes = "";
                        out.println("Enter the customer's last name or press enter to skip.");
                        out.print("[last name]: ");
                        custName = input.nextLine();
                        Cls.cls();
                        ArrayList<Integer> itemSel = new ArrayList<>();
                        do {
                            try {
                                if (orderData.isOrder(Integer.parseInt(orderNumString))) {
                                    orderNum = Integer.parseInt(orderNumString);
                                    Cls.cls();
                                    out.println("Order found!");
                                    out.println();
                                    out.println(orderData.searchOrder(orderNum));
                                    out.println("Is this order for \n 1: Dine-in \n 2: Delivery \n 3: Pick up?");
                                    out.print("[order type]: ");
                                    // orderChoice takes the number to avoid confusion on my part...
                                    orderChoice = input.nextInt();
                                    int dishNum = -1;
                                    Cls.cls();
                                    Cls.cls();
                                    if (orderChoice == 1) {
                                        //dine in
                                        Cls.cls();
                                        orderType = "Dine-in";
                                        out.println("How big is the party?");
                                        out.print("[party size]: ");
                                        partySize = input.nextInt();
                                        input.nextLine(); //clears the newline character
                                        Cls.cls();
                                        boolean tableNumValid = false;
                                        do {
                                            out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                            out.print("[table #]: ");
                                            String tableNumString = input.nextLine();
                                            /**
                                            * @todo This try catch block is not functioning correctly
                                            * @body I think it has something to do with the first if block
                                            */
                                            try {
                                                if (!tableData.isFilled(Integer.parseInt(tableNumString))) {
                                                    tableNum = Integer.parseInt(tableNumString);
                                                    tableNumValid = true;
                                                }
                                            } catch (NumberFormatException e) {
                                                if (tableNumString.equalsIgnoreCase("-L")) {
                                                    out.println(tableData.getEmptyTables());
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                } else if (tableNumString.equalsIgnoreCase("-C")) { //cancel
                                                    Cls.cls();
                                                    out.println("Order cancelled.");
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                    break;
                                                } else {
                                                    tableNum = Integer.parseInt(tableNumString);
                                                }
                                            }
                                        } while (!tableNumValid);
                                        Cls.cls();
                                        out.println("Table: " + tableNum);
                                        out.println(tableData.getTableData(tableNum));
                                        out.println("Enter the seat number where this dish should be served.");
                                        out.print("[seat #]: ");
                                        seatNum = input.nextInt();
                                        input.nextLine(); //clears the newline character
                                        orderFor = custName + " | Table: " + tableNum + " | Seat: " + seatNum;
                                    }
                                    if (orderChoice == 1) {
                                        //dine in
                                        Cls.cls();
                                        orderType = "Dine-in";
                                        out.println("How big is the party?");
                                        out.print("[party size]: ");
                                        partySize = input.nextInt();
                                        input.nextLine(); //clears the newline character
                                        Cls.cls();
                                        boolean tableNumValid = false;
                                        do {
                                            out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                            out.print("[table #]: ");
                                            String tableNumString = input.nextLine();
                                            try {
                                                if (!tableData.isFilled(Integer.parseInt(tableNumString))) {
                                                    tableNum = Integer.parseInt(tableNumString);
                                                    tableNumValid = true;
                                                }
                                            } catch (NumberFormatException e) {
                                                if (tableNumString.equalsIgnoreCase("-L")) {
                                                    out.println(tableData.getEmptyTables());
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                } else if (tableNumString.equalsIgnoreCase("-C")) { //cancel
                                                    Cls.cls();
                                                    out.println("Order cancelled.");
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                    break;
                                                } else {
                                                    tableNum = Integer.parseInt(tableNumString);
                                                }
                                            }
                                        } while (!tableNumValid);
                                        Cls.cls();
                                        out.println("Table: " + tableNum);
                                        out.println(tableData.getTableData(tableNum));
                                        out.println("Enter the seat number where this dish should be served.");
                                        out.print("[seat #]: ");
                                        seatNum = input.nextInt();
                                        input.nextLine(); //clears the newline character
                                        orderFor = custName + " | Table: " + tableNum + " | Seat: " + seatNum;

                                    } else if (orderChoice == 2) {          //delivery
                                        orderType = "Delivery";
                                        out.println("Enter the address.");
                                        out.print("[address]: ");
                                        address = input.nextLine();
                                        Cls.cls();
                                        out.println("Enter the phone number.");
                                        out.print("[phone]: ");
                                        phone = input.nextLine();
                                        Cls.cls();
                                        out.println("Enter the order notes. (optional)");
                                        out.print("[notes]: ");
                                        if (!input.nextLine().isEmpty()) {
                                            deliveryNotes = input.nextLine();
                                        } else deliveryNotes = "";
                                        orderFor = "Delivery: " + custName;

                                    } else {
                                        orderType = "Pick up";                   //pick up
                                        out.println("Enter the phone number.");
                                        out.print("[phone]: ");
                                        phone = input.nextLine();
                                        orderFor = "Pick up: " + custName;
                                    }
                                    //Build orders
                                    do {
                                        do {
                                            menuData.printMenu();
                                            out.println("Ordering for: " + orderFor);
                                            out.println("Enter the number of the item you would like to add to the order.");
                                            out.print("[menu item(#)]: ");
                                            itemNum = input.nextInt();
                                            if (menuData.isItem(itemNum)) {
                                                input.nextLine(); //clears the newline character
                                                Cls.cls();
                                                out.println("Ordering for: " + orderFor);
                                                out.println("You chose: " + menuData.getName(itemNum));
                                                out.println("Is this correct?");
                                                out.print("[(Y)es/(N)o]: ");
                                                yn = YesNo.yesNo(input.nextLine());
                                            } else {
                                                out.println("Invalid item number. Please try again.");
                                                yn = false;
                                            }
                                        } while (!yn);
                                        itemSel.add(itemNum);
                                        Cls.cls();
                                        out.println("Ordering for: " + orderFor);
                                        StringBuilder sb = new StringBuilder();
                                        for (int i : itemSel) {
                                            sb.append(menuData.getName(i)).append(", ");
                                        }
                                        out.println(sb);
                                        out.println("Would you like add another item?");
                                        out.print("[(Y)es/(N)o]: ");
                                        yn = YesNo.yesNo(input.nextLine());
                                    } while (yn);

                                    //submit orders
                                    if (orderChoice == 1) {
                                        //dine in
                                        for (int i : itemSel) {
                                            tableData.addDish(tableNum, seatNum, i);
                                        }
                                        out.println("Dishes added to Table: \n" + tableData.getTableData(tableNum));
                                        Order order = new Order(orderNum, custName, itemSel, orderType, tableNum, "", "", "");
                                        orderData.remOrder(orderNum);
                                        orderData.addOrder(orderNum, order);
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n");

                                    } else if (orderChoice == 2) {
                                        //delivery
                                        Order order = new Order(orderNum, custName, itemSel, orderType, -1, address, phone, deliveryNotes);
                                        orderData.remOrder(orderNum);
                                        orderData.addOrder(orderNum, order);
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n");
                                    } else {
                                        //pick up
                                        Order order = new Order(orderNum, custName, itemSel, orderType, -1, "", phone, "");
                                        orderData.remOrder(orderNum);
                                        orderData.addOrder(orderNum, order);
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                if (orderNumString.equalsIgnoreCase("-L")) {
                                    orderData.listOrders();
                                    out.println("Press enter to continue.");
                                    input.nextLine();
                                    retry = true;
                                } else
                                    out.println("Invalid input. Please try again.");
                                retry = true;
                            }
                        }while (retry);
                    }

                    case 3 -> {
                        orderData.listOrders();
                        out.println("| Press enter to continue. |");
                        input.nextLine();
                        Cls.cls();
                    }
                    case 4 -> {
                        Cls.cls();
                        boolean loop = false;
                        do {
                            orderData.listOrders();
                            out.println("Enter the order number you would like to remove.");
                            int orderNum = input.nextInt();
                            if (orderData.isOrder(orderNum)) {
                                Cls.cls();
                                out.println("Order Found!");
                                orderData.searchOrder(orderNum);
                                out.println("Would you like to delete it?");
                                boolean yn = YesNo.yesNo(input.nextLine());
                                if (yn) {
                                    orderData.remOrder(orderNum);
                                    out.println("Order removed");
                                    out.println("| Press enter to return to the menu. |");
                                    input.nextLine();
                                } else loop = true;
                            }
                        }while (loop);
                    }
                    case 5 -> out.println("Returning to the menu");
                    default -> throw new IllegalStateException("Unexpected value: " + choice);

                }
            }while (choice != 5);
        }
}