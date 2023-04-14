package Helpers;

/**
 * A class with a static method to check if a given string is "Yes" or "Y"
 */
public class YesNo {
    /**
     * Checks if a given string is "Yes" or "Y" (case-insensitive)
     *
     * @param yn the string to be checked
     * @return true if the string is "Yes" or "Y", false otherwise
     */
    public static boolean yesNo(String yn) {
        return yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("Yes");
    }
}
