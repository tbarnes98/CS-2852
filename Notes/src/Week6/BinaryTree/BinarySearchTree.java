package Week6.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a binary search tree.
 *
 * @param <E> The element type
 * @author Koffman and Wolfgang
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
        implements SearchTree<E> {
    // Data Fields

    /**
     * Return value from the public add method.
     */
    protected boolean addReturn;
    /**
     * Return value from the public delete method.
     */
    protected E deleteReturn;

    private int size;

    /**
     * Starter method find.
     *
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    @Override
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     *
     * @param localRoot The local subtree�s root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target) {
        if(localRoot == null) {
            return null;
        }
        int result = target.compareTo(localRoot.data);

        if(result == 0) {
            return localRoot.data;
        } else if (result < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /**
     * Starter method add.
     *
     * @param item The object being inserted
     * @return true if the object is inserted, false if the object already
     * exists in the tree
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * Recursive add method.
     *
     * @post The data field addReturn is set true if the item is added to the
     * tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if(localRoot == null) {
            addReturn = true;
            return new Node<>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Starter method delete.
     *
     * @post The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree or null if the object was not in
     * the tree
     */
    @Override
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     *
     * @post The item is not in the tree; deleteReturn is equal to the deleted
     * item as it was stored in the tree or null if the item was not found.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if(localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }
        int result = item.compareTo(localRoot.data);
        if(result < 0) {
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (result > 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            deleteReturn = localRoot.data;
            if(localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                // 2 children
                if(localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
         }
    }

    /**
     * Find the node that is the in-order predecessor and replace it with its
     * left child (if any).
     *
     * @post The in-order predecessor is removed from the tree.
     * @param parent The parent of possible in-order predecessor (ip)
     * @return The data in the ip
     */
    private E findLargestChild(Node<E> parent) {
        if(parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * Removes target from tree.
     *
     * @param target Item to be removed
     * @return true if the object was in the tree, false otherwise
     * @post target is not in the tree
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /**
     * Determine if an item is in the tree
     *
     * @param target Item being sought in tree
     * @return true If the item is in the tree, false otherwise
     * @throws ClassCastException if target is not Comparable
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /**
     * Find the node that is the in-order successor and replace it with its
     * right child (if any).
     *
     * @post The in-order successor is removed from the tree.
     * @param parent The parent of possible in-order successor (is)
     * @return The data in the is
     */
    private E findSmallestChild(Node<E> parent) {
        if(parent.left.left == null) {
            E returnValue = parent.left.data;
            parent.left = parent.left.right;
            return returnValue;
        } else {
            return findSmallestChild(parent.left);
        }
    }

    /**
     * Returns the size of the tree.
     * @return the number of nodes with non-null data in the tree
     */
    public int size() {
        size = 0;
        inOrderTraverse((e, d) -> { if (e != null) {
            ++size;
        }
        });
        return size;
    }

    public E minValue() {
        return minimum(root);
    }

    private E minimum(Node<E> node) {
        if(node == null) {
            return null;
        }
        if(node.left == null) {
            return node.data;
        }
        return minimum(node.left);
    }

    /**
     * Returns the smallest data value contained in the tree
     * @return The smallest value
     */
    public E min() {
        return findSmallestChild(root);
    }

    /**
     * Returns the largest data value contained in the tree
     * @return The largest value
     */
    public E max() {
        return findLargestChild(root);
    }

    /**
     * Return the contents of the BinarySearchTree as a List of items in
     * ascending order. (Note the exercise suggests returning a string of item
     * separated by newline characters, but a List is more general and useful
     * for the testing performed by the exercise. Also, the toString method of
     * the Week6.BinaryTree class would be hidden and it is useful to verify some of
     * the tests.
     *
     * @return A list containing the contents of the binary search tree
     */
    @Override
    public List<E> toList() {
        List<E> result = new ArrayList<>();
        inOrderTraverse((e, d)-> {
            if(e != null) {
                result.add(e);
            }
        });
        return result;
    }

    /**
     * Empty this BinarySearchTree
     */
    public void clear() {
        root = null;
    }

}
