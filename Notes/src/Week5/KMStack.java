package Week5;

import java.util.ArrayList;
import java.util.List;

public class KMStack<E> {
    // Backing Structure
    List<E> data;

    // Constructor
    public KMStack() {
        data = new ArrayList();
    }
    // empty
    public boolean empty() {
        return data.isEmpty();
    }
    // peek
    public E peek() {
        return data.get(data.size() - 1);
    }
    // pop
    public E pop() {
        E temp = data.get(data.size()-1);
        data.remove(data.size()-1);
        return temp;
    }
    //push
    public boolean push(E element) {
        return data.add(element);
    }
}
