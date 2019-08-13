package barnestr;

public class HuffmanLeaf extends HuffmanTree {

    public char value;

    /**
     * Constructor that takes a starting frequency
     *
     * @param frequency The default starting frequency of a node (usually 0 or 1)
     */
    HuffmanLeaf(int frequency, char value) {
        super(frequency);
        this.value = value;
    }
}
