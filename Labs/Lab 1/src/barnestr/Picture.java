/*
 * CS2852
 * Fall 2018
 * Lab 3 - Connect the Dots Generator Revisited
 * Name: Trevor Barnes
 * Created: 9/11/18
 */
package barnestr;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * A class named Picture that represents the entire image drawn on a JavaFX Canvas.
 * This class has an ArrayList of dots loaded in from a .dot file.
 * Dots and lines can also be drawn with the methods in this class.
 *
 * @author Trevor Barnes
 * @version 3.0
 */
public class Picture {

    private static final int CANVAS_SIZE = 720;
    private static final int DOT_SIZE = 8;

    private List<Dot> dots;
    private Picture original;

    /**
     * The constructor for Picture that initializes the dots list
     *
     * @param emptyList the list of dots passed in
     */
    public Picture(List<Dot> emptyList) {
        this.dots = emptyList;
    }

    /**
     * The constructor for Picture that initializes the dots list and original Picture object
     *
     * @param original  the original Picture with the original dots
     * @param emptyList the list of dots passed in
     */
    public Picture(Picture original, List<Dot> emptyList) {
        this.dots = emptyList;
        this.original = original;
    }

    /**
     * Loads in a .dot file's data into the ArrayList of Dots.
     *
     * @param file the .dot file with dot coordinates
     * @throws FileNotFoundException if file is not found
     */
    public void load(File file) throws FileNotFoundException {

        try (Scanner in = new Scanner(file)) {
            do {
                String coord = in.nextLine();
                double x = Double.parseDouble(coord.substring(0, coord.indexOf(","))) *
                        CANVAS_SIZE;
                double y = Math.abs((Double.parseDouble(coord.
                        substring(coord.indexOf(",") + 1)) * CANVAS_SIZE) - CANVAS_SIZE);
                dots.add(new Dot(x, y));
                original.dots.add(new Dot(x, y));
            } while (in.hasNext());
        }
    }

    /**
     * Saves the data of the dots ArrayList in a given file location as a .dot file
     *
     * @param file the file to be saved to
     * @throws FileNotFoundException if file is not found
     */
    public void save(File file) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i = 0; i < dots.size(); i++) {
                pw.append(String.valueOf((dots.get(i).getX()) / CANVAS_SIZE));
                pw.append(", ");
                pw.append(String.valueOf(Math.abs(dots.get(i).getY() - CANVAS_SIZE) /
                        CANVAS_SIZE));
                pw.append("\r\n");
            }
        }
    }

    /**
     * Draws the dots using the Canvas's GraphicsContext
     *
     * @param canvas the canvas to be drawn on
     */
    public void drawDots(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);

        for (int i = dots.size() - 1; i > 0; i--) {
            gc.fillOval(dots.get(i).getX() - (DOT_SIZE / 2),
                    dots.get(i).getY() - (DOT_SIZE / 2), DOT_SIZE, DOT_SIZE);
        }
    }

    /**
     * Sets the number of dots on the image by each dots determined critical values by
     * using indexed methods. The dot with the lowest critical value is removed until the number of
     * desired dots is reached.
     *
     * @param numberDesired the number of dots requested by the user
     * @return the amount of time, in nanoseconds, the operation took to complete
     */
    public long removeDots(int numberDesired) {
        long initialTime = System.nanoTime();
        dots.clear();
        for (Dot dot : original.dots) {
            dots.add(dot);
        }
        if (numberDesired > 3) {
            int removedCount = dots.size() - numberDesired;

            //recalculates critical values until the removed count has been reached
            for (int i = 0; i < removedCount; i++) {
                //creates a double array with critical values with the new dots list size
                double[] cv = new double[dots.size()];
                //setting critical values for dots at each index
                for (int j = 0; j < dots.size(); j++) {
                    //first dot
                    if (j == 0) {
                        cv[j] = dots.get(j).calculateCriticalValue(dots
                                .get(dots.size() - 1), dots.get(j + 1));
                        //last dot
                    } else if (j == dots.size() - 1) {
                        cv[j] = dots.get(j).calculateCriticalValue(dots
                                .get(j - 1), dots.get(0));
                        //all dots in between
                    } else {
                        cv[j] = dots.get(j).calculateCriticalValue(dots
                                .get(j - 1), dots.get(j + 1));
                    }
                }
                int index = 0;
                //sets the first dot as the "default" lowest critical value
                double minCValue = cv[index];
                //for the length of the array, the index is set to the lowest critical value
                for (int k = 0; k < cv.length; k++) {
                    if (cv[k] < minCValue) {
                        minCValue = cv[k];
                        index = k;
                    }
                }
                //dot with lowest CV in the list is removed at the same index as the CV in
                // the CV array
                dots.remove(index);
            }
        }
        return System.nanoTime() - initialTime;
    }

    /**
     * Sets the number of dots on the image by each dots determined critical values by
     * using iterators. The dot with the lowest critical value is removed until the number of
     * desired dots is reached.
     *
     * @param numberDesired the number of dots requested by the user
     * @return the amount of time, in nanoseconds, the operation took to complete
     */
    public long removeDots2(int numberDesired) {
        long initialTime = System.nanoTime();
        dots.clear();
        for (Dot dot : original.dots) {
            dots.add(dot);
        }

        if (numberDesired >= 3) {
            int removedCount = dots.size() - numberDesired;
            for (int i = 0; i < removedCount; i++) {
                Iterator<Dot> iterCurrent = dots.iterator();
                Iterator<Dot> iterPrev = dots.iterator();
                Iterator<Dot> iterNext = dots.iterator();
                Collection<Double> cv = new ArrayList<>();
                iterNext.next();
                for (int j = 0; iterCurrent.hasNext(); j++) {
                    if (j == 0) {
                        Iterator<Dot> iterLast = dots.iterator();
                        Dot lastDot = new Dot(0, 0);
                        while (iterLast.hasNext()) {
                            lastDot = iterLast.next();
                        }
                        cv.add(iterCurrent.next()
                                .calculateCriticalValue(lastDot, iterNext.next()));
                    } else if (!iterNext.hasNext()) {
                        Iterator<Dot> first = dots.iterator();
                        cv.add(iterCurrent.next()
                                .calculateCriticalValue(iterPrev.next(), first.next()));
                    } else {
                        cv.add(iterCurrent.next()
                                .calculateCriticalValue(iterPrev.next(), iterNext.next()));
                    }
                }
                Iterator<Double> iterCV = cv.iterator();
                int location = 0;
                double minCValue = Integer.MAX_VALUE;
                for (int k = 0; iterCV.hasNext(); k++) {
                    double current = iterCV.next();
                    if (current < minCValue) {
                        minCValue = current;
                        location = k;
                    }
                }
                Iterator<Dot> iterRemove = dots.iterator();
                iterRemove.next();
                for (int j = 0; j < location; j++) {
                    iterRemove.next();
                }
                iterRemove.remove();
            }
        }
        return System.nanoTime() - initialTime;
    }

    /**
     * Draws the lines of one dot to the next in the ArrayList using the Canvas's GraphicsContext.
     *
     * @param canvas the canvas to be drawn on
     */
    public void drawLines(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.beginPath();

        for (int i = dots.size() - 1; i > 0; i--) {
            gc.beginPath();
            gc.moveTo(dots.get(i).getX(), dots.get(i).getY());
            if (i + 1 < dots.size()) {
                gc.lineTo(dots.get(i + 1).getX(), dots.get(i + 1).getY());
            } else {
                gc.lineTo(dots.get(1).getX(), dots.get(1).getY());
            }
            gc.closePath();
            gc.stroke();
        }
    }

    /**
     * Clears the canvas by using the clearReact method for GraphicsContext.
     *
     * @param canvas the canvas to be cleared
     */
    public void clearCanvas(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
    }

}
