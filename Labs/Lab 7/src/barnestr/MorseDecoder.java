/*
 * CS 2852
 * Fall 2018
 * Lab 7 - Morse Code Decoder
 * Name: Trevor Barnes
 * Created: 10/15/18
 */
package barnestr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The main class that builds a morse tree and decodes an encoded file.
 * This decoded data is then written out to another file
 *
 * @author Trevor Barnes
 * @version 2.0
 */
public class MorseDecoder {

    private static MorseTree<Character> tree = new MorseTree<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        loadDecoder(new File("morsecode.txt"));
        System.out.println("Enter an input filename:");
        String inputFile = in.nextLine();
        System.out.println("Enter an output filename:");
        String outputFile = in.nextLine();
        decodeFile(inputFile, outputFile);
        System.out.println("Encoded file saved in project directory");
    }

    /**
     * Loads in a file with morse code data and builds a Morse Code decoding tree.
     * @param file the Morse Code "key" file
     */
    public static void loadDecoder(File file) {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String code = line.substring(2, Math.max(line.lastIndexOf('.'),
                        line.lastIndexOf('-')) + 1);
                tree.add(line.charAt(0), code);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning! File not found!");
        }
    }

    /**
     * Accepts the file location of an encoded file then decodes the data in a file
     * @param inputFileName the name of the file to be decoded
     *                      (assuming it is located in the project directory)
     * @param outputFileName the name given name for the encoded output file
     */
    public static void decodeFile(String inputFileName, String outputFileName) {
        File encodedFile = new File(inputFileName);
        File decodedFile = new File(outputFileName);
        try (Scanner sc = new Scanner(encodedFile)) {
            try (PrintWriter pw = new PrintWriter(decodedFile)) {
                while (sc.hasNextLine()) {
                    StringTokenizer line = new StringTokenizer(sc.nextLine());
                    while (line.hasMoreTokens()) {
                        String token = line.nextToken();
                        if (token.equals("|")) {
                            pw.append(" ");
                        } else {
                            try {
                                char symbol = tree.decode(token);
                                pw.append(symbol);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Warning! Skipping: " + token);
                            }
                        }
                    }
                    pw.append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning! File not found!");
        }
    }
}
