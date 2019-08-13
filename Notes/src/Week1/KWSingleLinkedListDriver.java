/*
 * CS2852
 * Spring 2018
 * KWSingleLinkedList Driver
 * Name: Sean Jones
 * Created: 03/07/2018
 */

package Week1;

public class KWSingleLinkedListDriver {
    public static void main(String[] args) {
        KWSingleLinkedList<String> names = new KWSingleLinkedList<>();
        names.addFirst("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("Diane");
        names.add("Elaine");
        names.add("Fido");

        System.out.println(names);
        System.out.println(names.get(3));
        System.out.println(names.set(5, "Frank"));
        names.add(4, "Ernie");
        System.out.println(names);
        names.remove(5);
        System.out.println(names.size());
        System.out.println(names.indexOf("Diane"));
        System.out.println(names);
    }
}
