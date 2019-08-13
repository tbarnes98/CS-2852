/*
 * CS 2852
 * Fall 2018
 * Lab 7 - Morse Code Decoder
 * Name: Trevor Barnes
 * Created: 10/15/18
 */
package barnestr;

/**
 * A class that represents a BinaryTree used to decode Morse Code files
 *
 * @param <E> the generic value used to construct the tree
 */
public class MorseTree<E> {

    private static class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        private Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            if (data == null) {
                return null;
            } else {
                return data.toString();
            }
        }
    }

    private Node<E> root;

    /**
     * The constructor for a MorseTree that sets the root node's data to null
     */
    public MorseTree() {
        this.root = new Node<>(null);
    }

    /**
     * A method that sets the passed in root to whatever result is given from
     * the call to the recursive add method.
     *
     * @param symbol the symbol to be set at the given code location
     * @param code   the code that determines where a symbol will be placed in the tree
     */
    public void add(E symbol, String code) {
        root = addRecursive(root, symbol, code);
    }

    private Node<E> addRecursive(Node<E> localRoot, E symbol, String code) {

        if (code.isEmpty()) {
            // If the code string is empty, the symbol's location has been reached.
            // Returns the new Node with the symbol as its data.
            localRoot.data = symbol;
            return localRoot;
        }

        if (code.charAt(0) == '.') {
            // If the branch does not exist, add a Node with null data
            if (localRoot.left == null) {
                localRoot.left = new Node<>(null);
            }
            // Add in the left half of the localRoot's subtree
            localRoot.left = addRecursive(localRoot.left, symbol, code.substring(1));

        } else if (code.charAt(0) == '-') {
            // If the branch does not exist, add a Node with null data
            if (localRoot.right == null) {
                localRoot.right = new Node<>(null);
            }
            // Add in the right half of the localRoot's subtree
            localRoot.right = addRecursive(localRoot.right, symbol, code.substring(1));

        }
        // Return the localRoot Node
        return localRoot;
    }

    /**
     * A method retrieves a symbol by traversing the tree with the given
     * code by calling a recursive decode method
     *
     * @param code the code that signifies the location of a value in the tree
     * @return a generic with whatever value was decoded
     * @throws IllegalArgumentException thrown when anything other than a
     *                                  dot or line is passed to it
     */
    public E decode(String code) throws IllegalArgumentException {
        Node<E> data = decodeRecursive(root, code);
        return data.data;
    }

    private Node<E> decodeRecursive(Node<E> localRoot, String code) {
        if (!code.startsWith(".") && !code.startsWith("-") && !code.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (code.startsWith(".")) {
            return decodeRecursive(localRoot.left, code.substring(1));
        } else if (code.startsWith("-")) {
            return decodeRecursive(localRoot.right, code.substring(1));
        }

        return localRoot;
    }

}
