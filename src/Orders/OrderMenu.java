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
                System.out.println("4 - Delete an order.");
                System.out.println("5 - Return to the main menu.\n");
                System.out.print("Enter your selection [1,2,3,4,5]: ");
                //gets the user choice
                choice = input.nextInt();
                input.nextLine();
                //checks and proceeds with the user choice
                switch (choice) {
                    case 1 -> {
                        Cls.cls();
                        //starts the order entry process
                        String custName;
                        String orderType;
                        int orderChoice; //this is the number the user enters to select the order type
                        boolean yn; //boolean for yes or no questions
                        int itemNum; //this is the number the user enters to select the menu item
                        int partySize = 0; //this is the number of people in the party. **Not currently implemented**
                        int tableNum = 0; //this is the table number the party is seated at.
                        int seatNum = 0; //this is the seat number the order should be served to.
                        int orderNum = orderData.getNewOrderNum(); //this gets a new order number for the order.
                        String orderFor; //A string to hold the customer name and order type
                        String address = ""; //this is the address for delivery orders
                        String phone = ""; //this is the phone number for delivery and pickup orders
                        String deliveryNotes = ""; //this is the delivery notes for delivery orders
                        ArrayList<Integer> itemSel = new ArrayList<>(); //this is an arraylist to hold the menu item numbers for the order
                            Cls.cls();
                            out.println("Enter the customer's last name.");
                            out.print("[last name]: ");
                            custName = input.nextLine(); //gets the customer's last name
                            Cls.cls();
                            out.println("Is this order for \n 1: Dine-in \n 2: Delivery \n 3: Pick up?");
                            out.print("[order type]: ");
                            orderChoice = input.nextInt(); //gets the order type
                        if (orderChoice == 1) { //dine in
                                //dine in
                                Cls.cls();
                                orderType = "Dine-in";
                                out.println("How big is the party?");
                                out.print("[party size]: ");
                                partySize = input.nextInt();
                                input.nextLine(); //clears the leftover newline character
                                Cls.cls();
                                boolean tableNumValid = false; //this is a boolean to continue the do while loop if the table number is valid
                                do {
                                    out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                    out.print("[table #]: ");
                                    String tableNumString = input.nextLine(); //gets the table number
                                    try {
                                        if (!tableData.isFilled(Integer.parseInt(tableNumString))) { //checks if the table is filled
                                            tableNum = Integer.parseInt(tableNumString); //trys to parse the user input to an int
                                            tableNumValid = true; //sets the boolean to true to exit the do while loop if the table number is valid
                                        }
                                    } catch (NumberFormatException e) { //catches the exception if the user input is not an int (-L / -C)
                                        if (tableNumString.equalsIgnoreCase("-L")) {
                                            out.println(tableData.getEmptyTables()); //lists the empty tables
                                            out.println("Press enter to continue.");
                                            input.nextLine();
                                        } else if (tableNumString.equalsIgnoreCase("-C")) { //cancels the order
                                            Cls.cls();
                                            out.println("Order cancelled.");
                                            out.println("Press enter to continue.");
                                            input.nextLine();
                                            break;
                                        } else {
                                            tableNum = Integer.parseInt(tableNumString); //trys to parse the user input to an int
                                        }
                                    }
                                } while (!tableNumValid); //continues the do while loop if the table number is not valid
                                Cls.cls();
                                out.println("Table: " + tableNum);  //displays the table number
                                out.println(tableData.getTableData(tableNum)); //displays the table data
                                out.println("Enter the seat number where this dish should be served.");
                                out.print("[seat #]: ");
                                seatNum = input.nextInt(); //gets the seat number
                                input.nextLine(); //clears the newline character
                                orderFor = custName +  " | Table: " + tableNum + " | Seat: " + seatNum; //sets the orderFor string

                            } else if (orderChoice == 2) {          //delivery
                                orderType = "Delivery";
                                out.println("Enter the address.");
                                out.print("[address]: ");
                                address = input.nextLine(); //gets the address
                                Cls.cls();
                                out.println("Enter the phone number.");
                                out.print("[phone]: ");
                                phone = input.nextLine(); //gets the phone number
                                Cls.cls();
                                out.println("Enter the order notes. (optional)");
                                out.print("[notes]: ");
                             /**
                              * @todo OrderMenu.java. Line 127 freezes after entering order notes.
                              * @body Extra input.nextLine() somewhere??
                              */
                                if(!input.nextLine().isEmpty()){ //if delivery notes is not empty
                                    deliveryNotes = input.nextLine(); //gets the delivery notes
                                }else deliveryNotes = ""; //if delivery notes is empty
                                orderFor = "Delivery: " + custName; //sets the orderFor string

                            } else {
                                orderType = "Pick up";                   //pick up
                                out.println("Enter the phone number.");
                                out.print("[phone]: ");
                                phone = input.nextLine(); //gets the phone number
                                orderFor = "Pick up: " + custName; //sets the orderFor string
                            }

                            //Build orders
                            do {
                                do {
                                    menuData.printMenu(); //prints the menu
                                    out.println("Ordering for: " + orderFor); //displays the orderFor string
                                    out.println("Enter the number of the item you would like to add to the order.");
                                    out.print("[menu item(#)]: ");
                                    itemNum = input.nextInt(); //gets the menu item number
                                    if(menuData.isItem(itemNum)) { //checks if the menu item number is valid
                                        input.nextLine(); //clears the newline character
                                        Cls.cls();
                                        out.println("Ordering for: " + orderFor); //displays the orderFor string
                                        out.println("You chose: " + menuData.getName(itemNum)); //displays the menu item name
                                        out.println("Is this correct?");
                                        out.print("[(Y)es/(N)o]: ");
                                        yn = YesNo.yesNo(input.nextLine()); //gets yes or no for do while loop
                                    }else{
                                        out.println("Invalid item number. Please try again."); //displays if the menu item number is invalid
                                        yn = false; //sets yn to false to continue the do while loop
                                    }
                                } while (!yn); //continues the do while loop if the menu item number is invalid
                                itemSel.add(itemNum); //adds the menu item number to the itemSel arraylist
                                Cls.cls();
                                out.println("Ordering for: " + orderFor); //displays the orderFor string
                                StringBuilder sb = new StringBuilder();
                                for(int i : itemSel){ //loops through the itemSel arraylist
                                    sb.append(menuData.getName(i)).append(", "); //appends the menu item name to the string builder
                                }
                                out.println(sb);
                                out.println("Would you like add another item?");
                                out.print("[(Y)es/(N)o]: ");
                                yn = YesNo.yesNo(input.nextLine()); //gets yes or no for do while loop
                            }while (yn); //continues the do while loop if the user wants to add another item

                        //submit orders
                        if (orderChoice == 1) { //dine in
                            for (int i : itemSel) { //loops through the itemSel arraylist
                                tableData.addDish(tableNum, seatNum, i); //adds the menu item number to the table
                            }
                            out.println("Dishes added to Table: \n" + tableData.getTableData(tableNum)); //displays the table data
                            Order order = new Order(orderNum, custName, itemSel, orderType, tableNum, "", "", ""); //Creates the order object
                            orderData.addOrder(orderNum, order); //adds the order to the orderData hashmap
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n"); //displays the order

                        } else if (orderChoice == 2) {
                            //delivery
                            Order order = new Order(orderNum,custName,itemSel,orderType,-1, address, phone, deliveryNotes); //Creates the order object
                            orderData.addOrder(orderNum, order); //adds the order to the orderData hashmap
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n"); //displays the order
                        } else {
                            //pick up
                            Order order = new Order(orderNum, custName, itemSel, orderType, -1, "", phone, ""); //Creates the order object
                            orderData.addOrder(orderNum, order);//adds the order to the orderData hashmap
                            out.println("Order submitted.");
                            out.println(orderData.searchOrder(orderNum) + "\n"); //displays the order
                        }
                        out.println("| Press enter to continue. |");
                        input.nextLine();
                    }

                    case 2 -> {
                        //Edit order
                        /**
                        * @todo Debug order editing and cleanup code
                        * @body Debug order editing and cleanup code
                        */
                        Cls.cls();
                        out.println("Enter the number of the order to modify: (-L to list all open orders)");
                        out.println("[order #]: ");
                        String orderNumString = input.nextLine(); //gets the order number to edit
                        String custName; //This string is used to store the customer's name
                        String orderType; //This string is used to store the order type
                        int orderChoice; //This int is used to store the order choice for the switch statement
                        boolean yn; //This boolean is used to store yes or no for the do while loop
                        boolean retry = false; //This boolean is used to retry the do while loop
                        int itemNum; //this int is used to store the menu item number
                        int partySize = 0; //This int is used to store the party size **currently unused**
                        int tableNum = 0; //This int is used to store the table number
                        int seatNum = 0; //This int is used to store the seat number
                        int orderNum; //This int is used to get a new order number
                        String orderFor; //This string is used to store the order for string
                        String address = ""; //This string is used to store the address
                        String phone = ""; //This string is used to store the phone number
                        String deliveryNotes = ""; //This string is used to store the delivery notes
                        ArrayList<Integer> itemSel = new ArrayList<>(); //creates an arraylist to store the menu item numbers
                        do {
                            out.println("Enter the customer's last name or press enter to skip.");
                            out.print("[last name]: ");
                            custName = input.nextLine(); //gets the customer's last name
                            Cls.cls();
                            try { //try catch to catch the NumberFormatException
                                if (orderData.isOrder(Integer.parseInt(orderNumString))) { //tries to parse the orderNumString to an int and checks if the order number is valid
                                    orderNum = Integer.parseInt(orderNumString); //parses the orderNumString to an int
                                    Cls.cls();
                                    out.println("Order found!");
                                    out.println();
                                    out.println(orderData.searchOrder(orderNum)); //displays the order
                                    out.println("Is this order for \n 1: Dine-in \n 2: Delivery \n 3: Pick up?"); //asks the user for the order type
                                    out.print("[order type]: ");
                                    orderChoice = input.nextInt(); //gets the order type
                                    int dishNum = -1; //This int is used to store the dish number
                                    Cls.cls();
                                    if (orderChoice == 1) { //dine in
                                        Cls.cls();
                                        orderType = "Dine-in";
                                        out.println("How big is the party?");
                                        out.print("[party size]: ");
                                        partySize = input.nextInt(); //gets the party size
                                        input.nextLine(); //clears the newline character
                                        Cls.cls();
                                        boolean tableNumValid = false; //boolean for the do while loop to continue looping if the table number is invalid
                                        do {
                                            out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                            out.print("[table #]: ");
                                            String tableNumString = input.nextLine(); //gets the table number
                                            /**
                                             * @todo This try catch block is not functioning correctly
                                             * @body I think it has something to do with the first if block
                                             */
                                            try { //try catch to catch the NumberFormatException
                                                if (!tableData.isFilled(Integer.parseInt(tableNumString))) { //tries to parse the tableNumString to an int and checks if the table is filled
                                                    tableNum = Integer.parseInt(tableNumString); //parses the tableNumString to an int
                                                    tableNumValid = true; //sets the boolean to true to exit the do while loop
                                                }
                                            } catch (NumberFormatException e) {
                                                if (tableNumString.equalsIgnoreCase("-L")) { //catch for the -L command
                                                    out.println(tableData.getEmptyTables()); //displays the empty tables
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                } else if (tableNumString.equalsIgnoreCase("-C")) { //catch for the -C command
                                                    Cls.cls();
                                                    out.println("Order cancelled.");
                                                    out.println("Press enter to continue.");
                                                    input.nextLine();
                                                    break; //breaks out of the do while loop
                                                } else {
                                                    tableNum = Integer.parseInt(tableNumString); //parses the tableNumString to an int
                                                }
                                            }
                                        } while (!tableNumValid); //continues looping if the table number is invalid
                                        Cls.cls();
                                        out.println("Table: " + tableNum); //displays the table number
                                        out.println(tableData.getTableData(tableNum)); //displays the table data
                                        out.println("Enter the seat number where this dish should be served.");
                                        out.print("[seat #]: ");
                                        seatNum = input.nextInt(); //gets the seat number
                                        input.nextLine(); //clears the newline character
                                        orderFor = custName + " | Table: " + tableNum + " | Seat: " + seatNum; //sets the orderFor string

                                    } else if (orderChoice == 2) {          //delivery
                                        orderType = "Delivery"; //sets the order type
                                        out.println("Enter the address.");
                                        out.print("[address]: ");
                                        address = input.nextLine(); //gets the address
                                        Cls.cls();
                                        out.println("Enter the phone number.");
                                        out.print("[phone]: ");
                                        phone = input.nextLine(); //gets the phone number
                                        Cls.cls();
                                        out.println("Enter the order notes. (optional)");
                                        out.print("[notes]: ");
                                        /**
                                         * @todo remove if else and set directly to string value
                                         * @body Why did i even do this?? Possible in the order creation block as well
                                         */
                                        if (!input.nextLine().isEmpty()) {
                                            deliveryNotes = input.nextLine(); //gets the delivery notes if not empty
                                        } else deliveryNotes = ""; //sets the delivery notes to empty if empty (Why did i make an if else for this??)
                                        orderFor = "Delivery: " + custName; //sets the orderFor string

                                    } else { //pick up
                                        orderType = "Pick up";
                                        out.println("Enter the phone number.");
                                        out.print("[phone]: ");
                                        phone = input.nextLine(); //gets the phone number
                                        orderFor = "Pick up: " + custName; //sets the orderFor string
                                    }
                                    //Build orders
                                    do {
                                        do {
                                            menuData.printMenu(); //prints the menu
                                            out.println("Ordering for: " + orderFor); //displays the orderFor string
                                            out.println("Enter the number of the item you would like to add to the order.");
                                            out.print("[menu item(#)]: ");
                                            itemNum = input.nextInt(); //gets the item number
                                            if (menuData.isItem(itemNum)) { //checks if the item number is valid
                                                input.nextLine(); //clears the newline character
                                                Cls.cls();
                                                out.println("Ordering for: " + orderFor); //displays the orderFor string
                                                out.println("You chose: " + menuData.getName(itemNum)); //displays the item name
                                                out.println("Is this correct?");
                                                out.print("[(Y)es/(N)o]: ");
                                                yn = YesNo.yesNo(input.nextLine()); //Restarts the loop if the user says no
                                            } else {
                                                out.println("Invalid item number. Please try again.");
                                                yn = false; //sets the boolean to false if the item number is invalid
                                            }
                                        } while (!yn); //continues looping until item number is valid and user confirms the item
                                        itemSel.add(itemNum); //adds the item number to the itemSel arraylist
                                        Cls.cls();
                                        out.println("Ordering for: " + orderFor); //displays the orderFor string
                                        StringBuilder sb = new StringBuilder();
                                        for (int i : itemSel) { //loops through the itemSel arraylist
                                            sb.append(menuData.getName(i)).append(", "); //appends the item name to the string builder
                                        }
                                        out.println(sb); //prints the item names
                                        out.println("Would you like add another item?");
                                        out.print("[(Y)es/(N)o]: ");
                                        yn = YesNo.yesNo(input.nextLine()); //Restarts the loop if the user says yes
                                    } while (yn); //continues looping until user is finished adding items

                                    //submit orders
                                    /**
                                     * @todo need to remove old table data before adding new order data
                                     * @body This is causing the table data to be duplicated
                                     */
                                    if (orderChoice == 1) { //dine in
                                        for (int i : itemSel) { //loops through the itemSel arraylist
                                            tableData.addDish(tableNum, seatNum, i); //adds the item to the table
                                        }
                                        out.println("Dishes added to Table: \n" + tableData.getTableData(tableNum)); //displays the table data
                                        Order order = new Order(orderNum, custName, itemSel, orderType, tableNum, "", "", ""); //creates the new order object
                                        orderData.remOrder(orderNum); //removes the old order from the orderData arraylist
                                        orderData.addOrder(orderNum, order); //adds the new order to the orderData arraylist
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n"); //displays the new order data

                                    } else if (orderChoice == 2) { //delivery
                                        Order order = new Order(orderNum, custName, itemSel, orderType, -1, address, phone, deliveryNotes); //creates the new order object
                                        orderData.remOrder(orderNum); //removes the old order from the orderData arraylist
                                        orderData.addOrder(orderNum, order); //adds the new order to the orderData arraylist
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n"); //displays the new order data
                                    } else { //pick up
                                        Order order = new Order(orderNum, custName, itemSel, orderType, -1, "", phone, ""); //creates the order object
                                        orderData.remOrder(orderNum); //removes the old order from the orderData arraylist
                                        orderData.addOrder(orderNum, order); //adds the new order to the orderData arraylist
                                        out.println("Order edited.");
                                        out.println(orderData.searchOrder(orderNum) + "\n"); //displays the new order data
                                    }
                                }
                            } catch (NumberFormatException e) { //catches the exception if the user enters a non integer
                                if (orderNumString.equalsIgnoreCase("-L")) { //checks if the user entered -L
                                    /**
                                     * @todo OrderMenu.java line 446 outputs order object instead of string
                                     * @body probably the wrong method is being called
                                     */
                                    orderData.listOrders(); //lists the orders ***THIS IS WHERE THE ERROR IS***
                                    out.println("Press enter to continue.");
                                    input.nextLine();
                                    retry = true; //sets the retry boolean to true to restart the loop
                                } else //if input is invalid
                                    out.println("Invalid input. Please try again.");
                                retry = true; //sets the retry boolean to true to restart the loop
                            }
                        }while (retry); //continues looping until the user enters a valid order number
                    }

                    case 3 -> {
                        /**
                         * @todo OrderMenu.java line 446 outputs order object instead of string
                         * @body probably the wrong method is being called
                         */
                        orderData.listOrders(); //lists the orders ***THIS IS WHERE THE ERROR IS***
                        out.println("| Press enter to continue. |");
                        input.nextLine();
                        Cls.cls();
                    }
                    case 4 -> {
                        Cls.cls();
                        boolean loop = false; //loop boolean for the do while loop
                        do {
                            /**
                             * @todo OrderMenu.java line 459 also outputs order object instead of string
                             * @body Really need to get my methods in order lmao
                             */
                            orderData.listOrders(); //lists the orders ***THIS IS WHERE THE ERROR IS***
                            out.println("Enter the order number you would like to remove.");
                            int orderNum = input.nextInt(); //gets the order number
                            input.nextLine(); //clears the newline character
                            if (orderData.isOrder(orderNum)) { //checks if the order number is valid
                                Cls.cls();
                                out.println("Order Found!");
                                orderData.searchOrder(orderNum); //displays the order data
                                /**
                                 * @todo Skips user input here. Also loops without exiting.
                                 * @body probably an input.nextInt() that left the nextline character
                                 * Maybe fixed now??
                                 */
                                out.println("Would you like to delete it?");
                                boolean yn = YesNo.yesNo(input.nextLine()); //gets the users yes or no input
                                if (yn) {
                                    orderData.remOrder(orderNum); //removes the order from the orderData arraylist if the user says yes
                                    out.println("Order removed");
                                    out.println("| Press enter to return to the menu. |");
                                    input.nextLine();
                                } else loop = true; //sets the loop boolean to true if the user says no to restart the loop
                            }else {
                                out.println("Order not found.");
                                loop = true; //restarts the loop if the order number is invalid
                            }
                        }while (loop); //continues looping until the user enters a valid order number or confirms the removal
                    }
                    case 5 -> out.println("Returning to the menu"); //returns to the menu
                    default -> throw new IllegalStateException("Unexpected value: " + choice); //throws an exception if the user enters an invalid number

                }
            }while (choice != 5); //continues looping until the user enters 5
        }
}