package Week5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < 10; i++) {
            list.add((int)Math.random()* 10 + 1);
        }
        // size
        System.out.println(recursiveSize(it,0));
        // search

        // greatest common divisor
    }
    public static int recursiveSize(Iterator<Integer> it, int count) {
        // base case
        if (!it.hasNext()) {
            return count;
        }
        // recursive call
        count++;
        it.next();
        return recursiveSize(it,count);
    }
}
