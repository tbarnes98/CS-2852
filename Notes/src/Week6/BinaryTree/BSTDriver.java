package Week6.BinaryTree;

import Week6.BinaryTree.BinarySearchTree;
import Week6.BinaryTree.BinaryTree;

public class BSTDriver {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(13);
        tree.add(5);
        tree.add(18);
        tree.add(9);
        tree.add(1);
        tree.add(17);
        tree.add(8);
        tree.add(12);
        tree.add(3);
        tree.add(7);

        tree.preOrderTraverse((e, f) -> System.out.println(e));
//        Week6.BinaryTree<Integer> tree2 = new Week6.BinaryTree(13,
//                new Week6.BinaryTree(new Week6.BinaryTree.Node(5)),
//                new Week6.BinaryTree(new Week6.BinaryTree.Node(18)));

//        tree.delete(9);
//        System.out.println(tree);
//        System.out.println(tree.toList());
//        System.out.println(tree.size());
//        System.out.println(tree.contains(9));
//        System.out.println(tree.min());
//        System.out.println(tree.max());

//        System.out.println(tree.minValue());
//        System.out.println(tree2.minValue());
    }
}
