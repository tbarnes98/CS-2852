package barnestr;

/**
 * A Functional Interface that implements a specific action taken when doing
 * a traversal of a Huffman Tree and visiting a leaf node.
 */
@FunctionalInterface
interface Traverse {
    void process(HuffmanLeaf leaf, StringBuffer sb);
}
