package barnestr;

public class HuffmanNode extends HuffmanTree {

    public HuffmanTree left;
    public HuffmanTree right;
    /**
     * Constructor that takes a starting frequency
     *
     * @param 
     */
    HuffmanNode(HuffmanTree left, HuffmanTree right) {
        super(left.frequency + right.frequency);
        this.left = left;
        this.right = right;
    }
}
