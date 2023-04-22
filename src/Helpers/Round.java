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
        if (decimal <= 0)
            return value;
        double p = Math.pow(10, decimal);
        value = value * p;
        return Math.round(value) / p;
    }
}
