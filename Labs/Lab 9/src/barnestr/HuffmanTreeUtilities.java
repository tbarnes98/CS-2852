package barnestr;

import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTreeUtilities implements Traverse {

    private static final int ASCII_VALUES = 256;
    private static final HashMap<Character, String> CODE_TABLE = new HashMap<>();

    //TODO
    public static int[] calculateFrequencies(String input) {
        int[] freqArray = new int[ASCII_VALUES];

        for (int i = 0; i < freqArray.length; i++) {
            char c = input.charAt(i);
            freqArray[c]++;
        }
        return freqArray;
    }

    //TODO
    public static HuffmanTree buildTree(int[] characterFrequencies) {
        PriorityQueue<HuffmanTree> pq = new PriorityQueue<>();
        for (int i = 0; i < characterFrequencies.length; i++) {
            if (characterFrequencies[i] > 0) {
                pq.offer(new HuffmanLeaf(characterFrequencies[i], (char)i));
            }
        }
        while (pq.size() > 1) {
            HuffmanTree left = pq.poll();
            HuffmanTree right = pq.poll();
            pq.offer(new HuffmanNode(left, right));
        }
        return pq.poll();
    }

    //TODO
    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;

            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    //TODO
    public static void buildTable(HuffmanTree tree, StringBuffer prefix) {

    }

    //TODO
    public static void compress(HuffmanTree tree, String filename, String input) {

    }

    //TODO
    public static String decompress(HuffmanTree tree, File file) {
        return "";
    }

    @Override
    public void process(HuffmanLeaf leaf, StringBuffer sb) {

    }

}
