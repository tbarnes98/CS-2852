package barnestr;

import edu.msoe.taylor.audio.Note;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Driver program for Data Structures lab assignment
 *
 * @author t a y l o r@msoe.edu
 * @version 2018.03.26
 */
public class Lab5 {

    /**
     * Program that reads in notes from a text file and plays a song
     * using the Guitar class to generate the sounds which are then
     * played by a SimpleAudio object.
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file to be played: ");
        Guitar guitar = new Guitar();
        try (Scanner sc = new Scanner(new File(in.nextLine()))) {
            int lineCount = 0;
            while (sc.hasNextLine()) {
                lineCount++;
                try {
                    guitar.addNote(parseNote(sc.nextLine()));
                } catch (NumberFormatException e) {
                    sc.nextLine();
                    System.out.println("Warning: IncorrectNumberFormat on line " + lineCount + ". Line skipped.");
                }

            }
            guitar.play();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException Thrown");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException Thrown");
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException Thrown");
        } catch (IOException e) {
            System.out.println("IOException Thrown");
        }

    }

    /**
     * Returns a new Note initialized to the value represented by the specified String
     *
     * @param line Description of a note with scientific pitch followed by duration in milliseconds.
     * @return Note represented by the String passed in.  Returns null if it is unable to parse
     * the note data correctly.
     */
    private static Note parseNote(String line) {
        Scanner input = new Scanner(line);
        return new Note(input.next(), Float.parseFloat(input.next()));
    }
}
