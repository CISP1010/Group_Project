package Helpers;

public class Round {
    /**
     * Rounds the passed value to the specified number of decimals.
     *
     * @param value    The value to round.
     * @param decimal    The number of figures after decimal point.
     * @return The rounded value.
     */
    public static double round(double value, int decimal) {
        if (decimal <= 0) //If decimal is less than or equal to 0
            return value; //return the value
        double p = Math.pow(10, decimal); //p is 10 to the power of decimal
        value = value * p; //value is value times p
        return Math.round(value) / p; //return the rounded value divided by p
    }
}
