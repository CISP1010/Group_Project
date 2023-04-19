package Orders;

import Helpers.Cls;
import Helpers.YesNo;
import Menu.MenuData;
import Tables.TableData;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;
public class OrderMenu {
        public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            OrderData orderData = new OrderData();  //initialize orderData
            MenuData menuData = new MenuData();    //initialize menuData
            TableData tableData = new TableData(); //initialize tableData
            int choice = 0;
            int orderNum = 1;

            String noSeat = "No seat"; //I might have caused an issue by using 'choice' as a var inside loops..maybe not though
            String noTable = "No table";

            Boolean continueEntry = true;
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
                System.out.println("Enter your selection [1,2,3,4,5]: ");
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
                        boolean yn = false;
                        int itemNum;
                        int partySize = 0;
                        int tableNum = 0;
                        int seatNum = 0;
                        String address = "";
                        String phone = "";
                        String deliveryNotes = "";
                        ArrayList<Integer> itemSel = new ArrayList<Integer>();
                        do {
                            Cls.cls();
                            out.println("Enter the customer's last name.");
                            out.print("[last name]: ");
                            custName = input.nextLine();
                            Cls.cls();
                            out.println("Is this order for (1) Dine-in, (2) Delivery, or (3) Pick up?");
                            out.print("[order type]: ");
                            // orderChoice takes the number to avoid confusion on my part...
                            orderChoice = input.nextInt();
                            int dishNum = -1;
                            if (orderChoice == 1) {
                                //dine in
                                Cls.cls();
                                orderType = "Dine-in";
                                out.println("How big is the party?");
                                out.print("[party size]: ");
                                partySize = input.nextInt();
                                input.nextLine(); //clears the newline character
                                Cls.cls();
                                out.println("Enter the table number where the party will be seated. (-L to list empty tables)");
                                out.print("[table #]: ");
                                String tableNumString = input.nextLine();
                                if (tableNumString.equalsIgnoreCase("-L")) {
                                    out.println(tableData.getEmptyTables());
                                    out.println("Enter the table number where the party will be seated.");
                                    out.print("[table #]: ");
                                    tableNumString = input.nextLine();
                                }
                                tableNum = Integer.parseInt(tableNumString); //parse to int for tableData
                                Cls.cls();
                                out.println("Table: " + tableNum);
                                out.println(tableData.getTableData(tableNum));
                                out.println("Enter the seat number where this dish should be served.");
                                out.print("[seat #]: ");
                                seatNum = input.nextInt();
                                input.nextLine(); //clears the newline character
                                String orderFor = custName +  " | Table: " + tableNum + " | Seat: " + seatNum;

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
                                String orderFor = "Delivery: " + custName;

                            } else {
                                orderType = "Pick up";                   //pick up
                                out.println("Enter the phone number.");
                                out.print("[phone]: ");
                                phone = input.nextLine();
                                String orderFor = "Pick up: " + custName;
                            }

                            //Build orders
                            do {
                                menuData.printMenu();
                                out.println("Enter the number of the item you would like to add to the order.");
                                out.print("[menu item(#)]: ");
                                itemNum = input.nextInt();
                                input.nextLine(); //clears the newline character
                                Cls.cls();
                                out.println("You chose: " + menuData.getName(itemNum));
                                out.println("Is this correct?");
                                out.println("[(Y)es/(N)o]: ");
                                yn = YesNo.yesNo(input.nextLine());
                            }while(!yn);
                            itemSel.add(itemNum);
                            input.nextLine();
                            Cls.cls();
                            if(orderChoice == 1){
                                tableData.addDish(tableNum, seatNum, dishNum);
                                out.println("Dish added to Table: \n" + tableData.getTableData(tableNum));
                            }else {
                                out.println("Item added to order: \n" + "Items: " + itemSel);
                            }
                            out.println("Would you like add another item?");
                            out.print("[(Y)es/(N)o]: ");
                            restart = YesNo.yesNo(input.nextLine());
                        }while (restart);

                        //submit orders
                        if (orderChoice == 1) {
                            //dine in
                            Order order = new Order(orderNum, custName, itemSel, orderType, tableNum, "", "","" );
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println("Order number: " + orderNum);
                            out.println("Order: " + order.toString() + "\n");

                        } else if (orderChoice == 2) {
                            //delivery
                            Order order = new Order(orderNum,custName,itemSel,orderType,-1, address, phone, deliveryNotes);
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println("Order number: " + orderNum);
                            out.println("Order: " + order.toString() + "\n");
                        } else {
                            //pick up
                            Order order = new Order(orderNum, custName, itemSel, orderType, -1, "", phone, "");
                            orderData.addOrder(orderNum, order);
                            out.println("Order submitted.");
                            out.println("Order number: " + orderNum);
                            out.println("Order: " + order.toString() + "\n");
                        }
                            Cls.cls();
                        ///wait I got confused here...need to use function to pass all order items to orderItem list or hashmap
                        // then create object, then pass it to the hashmap
                        //this *should* add order object to the existing order hashmap
                        //augments the menu entry item that precedes [menu item(#)
                        orderNum++; //advances orderNum - this variable is outside the loop and should keep advancing
                        restart = YesNo.yesNo(input.nextLine());

                    }

                    case 2 -> {

                        Cls.cls();
                        out.println("Enter the number of the order to modify: ");
                        //need option to list orders so they can see order numbers - listAll() method in order data
                        choice = input.nextInt();
                        //print the getters for the info for this order
                        //what would you like to change?  choice will delete entry....\
                        //delete that entry with 'set' if it's an array list or something else if hash'
                        // menu to select
                        //boolean as above for modification finished?
                    }

                    case 3 -> { // this one is list all orders...iterate through hash or arraylist - just listAll from orderData
                        Cls.cls();
                        //goes iterates through hashmap get and printing info for each item


                    }
                    case 4 -> { // this one is delete order - listAll(), then orderData.remove<orderNum, order>
                    }
                    case 5 -> { // this one is return to main menu
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);

                }
            }while (restart);
        }
}


                    //default -> out.println("Invalid choice.");

                        //remember to put the assignNum() method in the constructor at the end of 'create ordery/n'