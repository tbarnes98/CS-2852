package barnestr;

//import edu.msoe.jones.huffmantree.HuffmanTree;
//import edu.msoe.jones.huffmantree.HuffmanTreeUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Driver class for the Huffman Encoding/Decoding Lab
 */
public class HuffmanCode {


    private static long origFileSize = 0L;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = loadFile(in);

        // build the Huffman Tree
        HuffmanTree tree = HuffmanTreeUtilities.
                buildTree(HuffmanTreeUtilities.calculateFrequencies(input));

        // Print out the resulting Huffman Code
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        HuffmanTreeUtilities.printCodes(tree, new StringBuffer());

        // Get the output filename from the user
        String filename = saveFile(in);
        // Compress the file
        HuffmanTreeUtilities.compress(tree, filename, input);

        // Report compression savings
        // TODO

        // Uncompress the file and print it to the screen
        System.out.println(HuffmanTreeUtilities.decompress(tree, new File(filename)));

    }

    /**
     * Prompts the user for a file to read and verifies it is a .txt file. This method will
     * call the readFile method to read the contents of the text file to a String.
     * @param in Scanner for user input
     * @return A String containing the contents of the file read in.
     */
    private static String loadFile(Scanner in) {
		// TODO
        String fileString = "";
        System.out.println("Enter a .txt file to be compressed (without .txt extension): ");
        try {
            File origFile = new File(in.nextLine() + ".txt");
            origFileSize = origFile.length();
            fileString = readFile(new Scanner(origFile));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileString;
    }

    /**
     * Reads a file, line by line, adding each line to a String a
     * @param fileIn A Scanner that is connected to the file to read
     * @return A String containing tbe contents of the given file.
     */
    private static String readFile(Scanner fileIn) {
        StringBuilder text = new StringBuilder();
        while (fileIn.hasNextLine()) {
            text.append(fileIn.nextLine());
            text.append("\n");
        }
        return text.toString();
    }

    /**
     * Prompts the user for a filename to save the compressed text file to.
     * @param in Scanner for user input
     * @return A String containing the filename. The extension .bin will be attached
     */
    private static String saveFile(Scanner in) {
        //TODO
        return null;

    }
}