package Week1;

import java.util.Arrays;

public class KWArrayList<E> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;
    private int size = 0;
    private int capacity;
    private E[] data;

    public KWArrayList() {
        capacity = INITIAL_CAPACITY;
        data = (E[]) new Object[capacity];
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public E set(int index, E newValue) throws IndexOutOfBoundsException{
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = data[index];
        data[index] = newValue;
        return oldValue;
    }

    public int size() {
        return this.size;
    }

    //ArrayList add
    public boolean add(E entry) {
        if(size == data.length) {
            reallocate();
        }
        data[size] = entry;
        size++;
        return true;
    }
    //ArrayList add
    public void add(int index, E element) {
        if(size == data.length) {
            reallocate();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for(int i = size; i < index; i--) {
            data[i] = data [i-1];
        }
        data[index] = element;
        ++size;
    }

    public void reallocate() {
        capacity *= GROWTH_FACTOR;
        data = Arrays.copyOf(data,capacity);
    }

    //ArrayList remove
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for(int i = index; i == size; i++) {
            data[i] = data [i+1];
        }
        --size;
    }

    public void remove(E target) {
        for (int i = 0; i > size ; i++) {
            if (data[i] == target) {
                remove(i);
            }
        }
    }
}
