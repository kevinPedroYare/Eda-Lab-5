public class AVLTree<T extends Comparable<T>> {
    NodeAvl<T> root;

    int getHeight(NodeAvl<T> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NodeAvl<T> createNode(T key) {
        return new NodeAvl<>(key);
    }

    NodeAvl<T> rotateRight(NodeAvl<T> y) {
        NodeAvl<T> x = y.left;
        NodeAvl<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        y.balanceFactor = getHeight(y.left) - getHeight(y.right);
        x.balanceFactor = getHeight(x.left) - getHeight(x.right);

        return x;
    }

    NodeAvl<T> rotateLeft(NodeAvl<T> x) {
        NodeAvl<T> y = x.right;
        NodeAvl<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        x.balanceFactor = getHeight(x.left) - getHeight(x.right);
        y.balanceFactor = getHeight(y.left) - getHeight(y.right);

        return y;
    }

    int getBalance(NodeAvl<T> node) {
        if (node == null)
            return 0;
        return node.balanceFactor;
    }

    NodeAvl<T> insertNode(NodeAvl<T> node, T key) {
        if (node == null)
            return createNode(key);

        if (key.compareTo(node.key) < 0)
            node.left = insertNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insertNode(node.right, key);
        else
            return node;

        node.height = 1 + max(getHeight(node.left), getHeight(node.right));
        node.balanceFactor = getHeight(node.left) - getHeight(node.right);

        if (node.balanceFactor > 1 && key.compareTo(node.left.key) < 0)
            return rotateRight(node);

        if (node.balanceFactor < -1 && key.compareTo(node.right.key) > 0)
            return rotateLeft(node);

        if (node.balanceFactor > 1 && key.compareTo(node.left.key) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (node.balanceFactor < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void printInOrder(NodeAvl<T> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }
}