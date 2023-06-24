public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.root = tree.insertNode(tree.root, 10);
        tree.root = tree.insertNode(tree.root, 20);
        tree.root = tree.insertNode(tree.root, 30);
        tree.root = tree.insertNode(tree.root, 40);
        tree.root = tree.insertNode(tree.root, 50);
        tree.root = tree.insertNode(tree.root, 25);

        System.out.println("Árbol AVL en orden:");
        tree.printInOrder(tree.root);

        GraphStream graphStream = new GraphStream();
        graphStream.visualizeTree(tree.root);
    }
}
