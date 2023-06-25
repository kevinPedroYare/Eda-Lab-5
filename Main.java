public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        AVLTreeVisualizer<Integer> visualizer = new AVLTreeVisualizer<>();

        visualizer.visualizeTree(tree);

        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        System.out.print("Inorder: ");
        tree.inorder();
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder();
        System.out.println();
    }
}