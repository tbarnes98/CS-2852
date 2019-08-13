/*
 * CS2852
 * Fall 2018
 * Lab 3 - Connect the Dots Generator Revisited
 * Name: Trevor Barnes
 * Created: 9/11/18
 */
package barnestr;

/**
 * A class named Dot that holds all of the data and
 * methods for a dot to be drawn on a JavaFX canvas.
 *
 * @author Trevor Barnes
 * @version 1.0
 */
public class Dot {

    private double x;
    private double y;

    /**
     * The constructor for a Dot which requires an X and Y coordinate
     *
     * @param x the X coordinate of the Dot
     * @param y the Y coordinate of the Dot
     */
    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Returns the critical value of a dot by calculating the distances between the
     * previous and next dot in the List.
     *
     * @param previous the previous Dot object in the List
     * @param next     the next Dot object in the List
     * @return the calculated critical value as a double
     */
    public double calculateCriticalValue(Dot previous, Dot next) {
        double p = Math.sqrt(Math.pow((previous.getX() - getX()), 2) +
                Math.pow((previous.getY() - getY()), 2));
        double n = Math.sqrt(Math.pow((next.getX() - getX()), 2) +
                Math.pow((next.getY() - getY()), 2));
        double pn = Math.sqrt(Math.pow((previous.getX() - next.getX()), 2) +
                Math.pow((previous.getY() - next.getY()), 2));
        return p + n - pn;

    }
}
