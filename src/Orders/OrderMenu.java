package Orders;

//check for when you've referenced 'orderData' (hashmap containing integer key and order object)
// and 'orderItems'  Array list containing the food items user has entered in order...
import Helpers.Cls;
import Helpers.YesNo;
import java.util.*;

import static java.lang.System.*;
public class OrderMenu {
    package Orders;

    public class OrderMenu {
        public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            OrderData orderData = new OrderData();  //going to try to change my arraylist conception to match
            int choice = 0;                         //the other hash stuff
            int orderNum = 1;

            String noSeat = "No seat";//I might have caused an issue by using 'choice' as a var inside loops..maybe not though
            String noTable = "No table";

            Boolean continueEntry = true;
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
                            boolean restart;
                            do {
                                Cls.cls();
                                out.println("Enter the customer's last name.");
                                out.println("[last name]: ");
                                String custName = input.nextLine();
                                Cls.cls();
                                out.println("Is this order for (1) Dine-in, (2) Delivery, or (3) Pick up?");
                                out.println("[order type]: ";
                                String orderType;
                               // orderChoice takes the number to avoid confusion on my part...
                                int orderChoice = input.nextInt();
                                if (orderChoice == 1) {
                                    orderType = "Dine-in"}
                                else if (orderChoice == 2) {
                                    orderType = "Delivery"}
                                else {
                                    orderType = "Pick up"}  // could add a section here to assign table/seat if dine-in later
                                Cls.cls();

                                boolean restart;
                                 do {
                                  int i = 1


                                    out.println("Please enter menu item.");
                                    out.println("[menu item(" + i + ")]: ");
                                    OrderData.addOrderItem(input.nextLine());
                                    Cls.cls();
                                    out.println("Would you like add another item?");
                                    out.print("[(Y)es/(N)o]: ");
                                    ///wait I got confused here...need to use function to pass all order items to orderItem list or hashmap
                                    // then create object, then pass it to the hashmap
                                    String orderList = OrderData.orderItems(); //telling me to make something static here
                                     //this *should* add order object to the existing order hashmap
                                     orderData.addOrder(Order); ////wtf??
                                    i++; //augments the menu entry item that precedes [menu item(#)
                                    orderNum++; //advances orderNum - this variable is outside the loop and should keep advancing
                                    restart = YesNo.yesNo(input.nextLine());

                                } while (restart);

                        case 2 -> {
                                  boolean restart;
                                  Cls.cls();
                                  out.println("Enter the number of the order to modify: ")
                                            //need option to list orders so they can see order numbers - listAll() method in order data
                                    choice = input.nextInt();
                                    //print the getters for the info for this order
                                    //what would you like to change?  choice will delete entry....\
                                    //delete that entry with 'set' if it's an array list or something else if hash'
                                    // menu to select
                            boolean as above for modification finished?
                        }

                        case 3 -> { // this one is list all orders...iterate through hash or arraylist - just listAll from orderData
                                    boolean restart;
                                    Cls.cls();
                                    //goes iterates through hashmap get and printing info for each item


                        }
                        case 4 -> { // this one is delete order - listAll(), then orderData.remove<orderNum, order>

                            {
                        case 5 -> { // this one is return to main menu

                        }
                            }
                        }



                                }


                                }
                                }
                                }
                            }
                        }

                    //default -> out.println("Invalid choice.");

                        //remember to put the assignNum() method in the constructor at the end of 'create ordery/n'