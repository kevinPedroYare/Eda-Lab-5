public class AVLTree<T extends Comparable<T>> {
    Node<T> root;

    AVLTree() {
        this.root = null;
    }

    void insert(T data) {
        root = insertNode(root, data);
    }

    private Node<T> insertNode(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.leftNode = insertNode(node.leftNode, data);
        } else if (data.compareTo(node.data) > 0) {
            node.rightNode = insertNode(node.rightNode, data);
        } else {
            return node; // Duplicados no se permiten en AVL, no se realiza la inserción
        }

        node.balanceFactor = calculateBalanceFactor(node);

        // Balanceo del árbol después de la inserción
        if (node.balanceFactor < -1) {
            if (data.compareTo(node.leftNode.data) < 0) {
                return rotateRight(node);
            } else {
                node.leftNode = rotateLeft(node.leftNode);
                return rotateRight(node);
            }
        }

        if (node.balanceFactor > 1) {
            if (data.compareTo(node.rightNode.data) > 0) {
                return rotateLeft(node);
            } else {
                node.rightNode = rotateRight(node.rightNode);
                return rotateLeft(node);
            }
        }

        return node;
    }

    void delete(T data) {
        root = deleteNode(root, data);
    }

    private Node<T> deleteNode(Node<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.leftNode = deleteNode(node.leftNode, data);
        } else if (data.compareTo(node.data) > 0) {
            node.rightNode = deleteNode(node.rightNode, data);
        } else {
            if (node.leftNode == null || node.rightNode == null) {
                node = (node.leftNode != null) ? node.leftNode : node.rightNode;
            } else {
                Node<T> successor = findMinimum(node.rightNode);
                node.data = successor.data;
                node.rightNode = deleteNode(node.rightNode, successor.data);
            }
        }

        if (node != null) {
            node.balanceFactor = calculateBalanceFactor(node);

            // Balanceo del árbol después de la eliminación
            if (node.balanceFactor < -1) {
                if (calculateBalanceFactor(node.leftNode) <= 0) {
                    return rotateRight(node);
                } else {
                    node.leftNode = rotateLeft(node.leftNode);
                    return rotateRight(node);
                }
            }

            if (node.balanceFactor > 1) {
                if (calculateBalanceFactor(node.rightNode) >= 0) {
                    return rotateLeft(node);
                } else {
                    node.rightNode = rotateRight(node.rightNode);
                    return rotateLeft(node);
                }
            }
        }

        return node;
    }

    private Node<T> findMinimum(Node<T> node) {
        while (node.leftNode != null) {
            node = node.leftNode;
        }
        return node;
    }

    private int calculateBalanceFactor(Node<T> node) {
        return (node == null) ? 0 : height(node.rightNode) - height(node.leftNode);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.leftNode), height(node.rightNode));
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> pivot = node.leftNode;
        node.leftNode = pivot.rightNode;
        pivot.rightNode = node;
        node.balanceFactor = calculateBalanceFactor(node);
        pivot.balanceFactor = calculateBalanceFactor(pivot);
        return pivot;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> pivot = node.rightNode;
        node.rightNode = pivot.leftNode;
        pivot.leftNode = node;
        node.balanceFactor = calculateBalanceFactor(node);
        pivot.balanceFactor = calculateBalanceFactor(pivot);
        return pivot;
    }

       void preorder() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.leftNode);
            preorderTraversal(node.rightNode);
        }
    }

    void inorder() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<T> node) {
        if (node != null) {
            inorderTraversal(node.leftNode);
            System.out.print(node.data + " ");
            inorderTraversal(node.rightNode);
        }
    }

    void postorder() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<T> node) {
        if (node != null) {
            postorderTraversal(node.leftNode);
            postorderTraversal(node.rightNode);
            System.out.print(node.data + " ");
        }
    }
}

