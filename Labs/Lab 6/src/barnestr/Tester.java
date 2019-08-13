/*
 * CS2852
 * Spring 2017
 * Lab 6 - Recursion
 * Name: 
 * Created: 3/30/2017
 */
package barnestr;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Tests the ArrayList and LinkedList implementations for the
 * CS2852 Lab 6 assignment.
 */
public class Tester {
    private final static String[] EMPTY_STRINGS = {};
    private final static String[] STRINGS1 = {"this"};
    private final static String[] STRINGS2 = {"this", "will"};
    private final static String[] STRINGS3 = {"this", "will", "be"};
    private final static String[] STRINGS4 = {"this", "will", "be", "interesting"};
    private final static String[] STRINGS5 = {"this", null, "will", "be", "interesting"};

    private final static int[] EMPTY_INTS = {};
    private final static int[] INTS1 = {-1};
    private final static int[] INTS2 = {-1, 2};
    private final static int[] INTS3 = {-1, 2, 8};
    private final static int[] INTS4 = {-1, 2, 8, Integer.MAX_VALUE};

    /**
     * Calls all of the test methods
     * @param ignored Not used
     */
    public static void main(String[] ignored) {
        String description = "ArrayList constructor collection of Strings of size: ";
        test(description + 0, stringConstructor(EMPTY_STRINGS));
        test(description + 1, stringConstructor(STRINGS1));
        test(description + 2, stringConstructor(STRINGS2));
        test(description + 3, stringConstructor(STRINGS3));
        test(description + 4, stringConstructor(STRINGS4));
        test(description + 5, stringConstructor(STRINGS5));

        description = "ArrayList constructor collection of Integers of size: ";
        test(description + 0, intConstructor(EMPTY_INTS));
        test(description + 1, intConstructor(INTS1));
        test(description + 2, intConstructor(INTS2));
        test(description + 3, intConstructor(INTS3));
        test(description + 4, intConstructor(INTS4));

        description = "ArrayList contains method for ArrayList of Integers of size: ";
        test(description + 0, arrayListContains(EMPTY_INTS));
        test(description + 1, arrayListContains(INTS1));
        test(description + 2, arrayListContains(INTS2));
        test(description + 3, arrayListContains(INTS3));
        test(description + 4, arrayListContains(INTS4));

        description = "ArrayList indexOf method for ArrayList of Integers of size:  ";
        test(description + 0, arrayListIndexOf(EMPTY_INTS));
        test(description + 1, arrayListIndexOf(INTS1));
        test(description + 2, arrayListIndexOf(INTS2));
        test(description + 3, arrayListIndexOf(INTS3));
        test(description + 4, arrayListIndexOf(INTS4));

        description = "LinkedList size method for LinkedList of Integers of size: ";
        test(description + 0, linkedListSize(EMPTY_INTS));
        test(description + 1, linkedListSize(INTS1));
        test(description + 2, linkedListSize(INTS2));
        test(description + 3, linkedListSize(INTS3));
        test(description + 4, linkedListSize(INTS4));

        description = "LinkedList contains method for LinkedList of Integers of size: ";
        test(description + 0, linkedListContains(EMPTY_INTS));
        test(description + 1, linkedListContains(INTS1));
        test(description + 2, linkedListContains(INTS2));
        test(description + 3, linkedListContains(INTS3));
        test(description + 4, linkedListContains(INTS4));

        description = "LinkedList indexOf method for LinkedList of Integers of size: ";
        test(description + 0, linkedListIndexOf(EMPTY_INTS));
        test(description + 1, linkedListIndexOf(INTS1));
        test(description + 2, linkedListIndexOf(INTS2));
        test(description + 3, linkedListIndexOf(INTS3));
        test(description + 4, linkedListIndexOf(INTS4));

        description = "LinkedList get method method for LinkedList of Integers of size: ";
        test(description + 0, linkedListGet(EMPTY_INTS));
        test(description + 1, linkedListGet(INTS1));
        test(description + 2, linkedListGet(INTS2));
        test(description + 3, linkedListGet(INTS3));
        test(description + 4, linkedListGet(INTS4));

    }

    /**
     * Displays an error if the test fails
     * @param description Description of the test
     * @param passed true if the test passed
     */
    private static void test(String description, boolean passed) {
        if(!passed) {
            System.out.println("Test failed: " + description);
        }
    }

    /**
     * Tests the constructor that accepts a collection of elements.
     * After the ArrayList is instantiated, the size is compared with
     * the size of the collection.
     * @param strings An array of Strings to be added to the ArrayList
     * @return true if test passed
     */
    private static boolean stringConstructor(String[] strings) {
        Collection<String> input = new java.util.ArrayList<>();
        for(String string : strings) {
            input.add(string);
        }
        List<String> list = new ArrayList<>(input);
        return list.size()==input.size();
    }

    /**
     * Tests the constructor that accepts a collection of elements.
     * After the ArrayList is instantiated, the size is compared with
     * the size of the collection.
     * @param numbers An array of ints to be added to the ArrayList
     * @return true if test passed
     */
    private static boolean intConstructor(int[] numbers) {
        Collection<Integer> input = new java.util.ArrayList<>();
        for(int number : numbers) {
            input.add(number);
        }
        List<Integer> list = new ArrayList<>(input);
        return list.size()==input.size();
    }

    private static boolean arrayListContains(int[] numbers) {
        List<Integer> input = new java.util.ArrayList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new ArrayList<>(input);
        for (int i = 0; i < list.size(); i++) {
            if (!(input.contains(input.get(i)) ||
                    list.contains(list.get(i)))) {
                return false;
            }
        }
        return true;
    }

    private static boolean arrayListIndexOf(int[] numbers) {
        List<Integer> input = new java.util.ArrayList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new barnestr.ArrayList<>(input);
        for (int i = 0; i < list.size(); i++) {
            return list.indexOf(list.get(i)) == input.indexOf(input.get(i));
        }
        return false;
    }

    private static boolean linkedListSize(int[] numbers) {
        Collection<Integer> input = new java.util.LinkedList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new LinkedList<>(input);
        return list.size() == input.size();
    }

    private static boolean linkedListContains(int[] numbers) {
        List<Integer> input = new java.util.LinkedList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new LinkedList<>(input);
        for (int i = 0; i < list.size(); i++) {
            if (!(input.contains(input.get(i)) ||
                    list.contains(list.get(i)))) {
                return false;
            }
        }
        return true;
    }

    private static boolean linkedListIndexOf(int[] numbers) {
        List<Integer> input = new java.util.LinkedList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new LinkedList<>(input);
        for (int i = 0; i < list.size(); i++) {
            return list.indexOf(list.get(i)) == input.indexOf(input.get(i));
        }
        return false;
    }

    private static boolean linkedListGet(int[] numbers) {
        java.util.LinkedList<Integer> input = new java.util.LinkedList<>();
        for (int num : numbers) {
            input.add(num);
        }
        List<Integer> list = new LinkedList<>(input);
        for (int i = 0; i < list.size(); i++) {
            if (input.get(i) != list.get(i)) {
                return false;
            }
        }
        return false;
    }


}
