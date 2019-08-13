/*
 * CS2852
 * Spring 2018
 * KWLinkedList
 * Name: Sean Jones
 * Created: 03/07/2018
 */
package Week1;

/**
 * This class implements some of the methods of the Java ArrayList class. It
 * does not implement the List interface.
 * @param <E> Generic type that will be declared by the user
 */
public class KWSingleLinkedList<E> {
    private Node<E> head = null;
    private int size = 0;

    /**
     * Returns the element at the specified index (not LinkedList behavior)
     *
     * Throws an ArrayIndexOutOfBoundsException on invalid index
     *
     * @param index The index of the element to be returned
     * @return The element stored in the specified index
     * @throws ArrayIndexOutOfBoundsException thrown when index is invalid
     */
    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Sets a specified value at a specified index in the list (not LinkedList behavior)
     *
     * @param index The index where the new value will be stored
     * @param newValue The value to be stored in the specified index
     * @return The element previously stored at the specified index
     * @throws ArrayIndexOutOfBoundsException thrown when index is invalid
     */
    public E set(int index, E newValue) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }

    /**
     * Special add method for adding the first Node to the KWSIngleLinkedList.
     *
     * @param item The element to be added as the head of the list
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    /**
     * Adds a new Node to a specified index of the KWSingleLinkedList (not LinkedList behavior)
     * @param index The index where the element will be added
     * @param item The element to be added
     * @throws ArrayIndexOutOfBoundsException thrown when index is invalid
     */
    public void add(int index, E item) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if(index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }




    }

    /**
     * Adds a new Node at the end of the list
     *
     * @param item The element to be added to the list
     * @return true if the element was successfully added, false otherwise
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /**
     * Removes a Node from the specified index
     * @param index The index of the Node to remove
     * @return The element removed from the list
     * @throws ArrayIndexOutOfBoundsException thrown when index is invalid
     */
    public E remove(int index) throws ArrayIndexOutOfBoundsException {
        //TODO
        return removeAfter(getNode(index-1));
    }

    /**
     * Returns the size (number of elements) in the list
     * @return The number of elements in the list
     */
    public int size() {
        int s = 0;
        while (getNode(s) != null) {
            s++;
        }
        return s;
    }

    /**
     * Returns the index of first instance of a specified element
     * @param item The specified element
     * @return The index of the first instance of the specified, -1 if not found
     */
    public int indexOf(E item) {
        // TODO
        int index = 0;
        while (item != get(index) && index < size()-1) {
            index++;
        }
        if (item == get(index)) {
            return index;
        } else {
            return -1;
        }
    }

    /**
     * Returns a String representation of the list, showing the next relationships with
     * a "==>"
     * @return The String representation of the list.
     */
    @Override
    public String toString() {
        Node<E> node = head;
        StringBuilder result = new StringBuilder();
        while(node != null) {
            result.append(node.data);
            if(node.next != null) {
                result.append(" ==> ");
            }
            node = node.next;
        }
        return result.toString();
    }

    /*
     * Helper method that finds and returns the Node at the specified index
     */
    private Node<E> getNode(int index) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node<E> node = head;
        for(int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /*
     * Helper method that adds an element after the specified Node. A new Node is
     * created, the element is assigned to the new Node, and that Node is set as
     * the next Node of the specified Node. The size is then incremented to reflect
     * the added Node.
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /*
     * Helper method that removes the Node that follow (is the next of) a specified
     * Node. The specified Node's next is set to it's next Node's next. The size is
     * then decremented to reflect the removed Node.
     *
     * The removed Node is then returned.
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /*
     * Helper method that removes the head of the list. The head's next Node is
     * set as the new head of the list. The size is then decremented to reflect
     * the removed Node.
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /*
     * A private class that wraps data in a Node object to be referenced by the list. Notice that
     * all methods, including constructors are private. Since this is a private class and only
     * exists inside the containing class, all private fields and methods are accessible by the
     * containing class.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E dataItem) {
            this.data = dataItem;
            this.next = null;
        }

        private Node(E dataItem, Node<E> next) {
            this.data = dataItem;
            this.next = next;
        }
    }
}

