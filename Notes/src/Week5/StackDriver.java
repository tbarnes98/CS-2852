package Week5;

public class StackDriver {
    public static void main(String[] args) {
        KMStack<Integer> stack = new KMStack<>();
        // test if empty
        System.out.println(stack.empty());
        // test add
        System.out.println(stack.push(5));
        System.out.println(stack.empty());
        System.out.println(stack.peek());
        stack.push(7);
        System.out.println(stack.peek());
        // test remove
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
    

}
