package Helpers;

/**
 * A class with a static method to clear the console screen
 */
public class Cls {
    /**
     * Clears the console screen
     */
    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
