public class AVLTree<T extends Comparable<T>> {
    NodeAvl<T> root;

    AVLTree() {
        this.root = null;
    }

    void insert(T data) {
        root = insertNode(root, data);
    }

    private NodeAvl<T> insertNode(NodeAvl<T> node, T data) {
        if (node == null) {
            return new NodeAvl<>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // Duplicados no se permiten en AVL, no se realiza la inserción
        }

        node.balanceFactor = calculateBalanceFactor(node);

        // Balanceo del árbol después de la inserción
        if (node.balanceFactor < -1) {
            if (data.compareTo(node.left.data) < 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (node.balanceFactor > 1) {
            if (data.compareTo(node.right.data) > 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    void delete(T data) {
        root = deleteNode(root, data);
    }

    private NodeAvl<T> deleteNode(NodeAvl<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = deleteNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                NodeAvl<T> successor = findMinimum(node.right);
                node.data = successor.data;
                node.right = deleteNode(node.right, successor.data);
            }
        }

        if (node != null) {
            node.balanceFactor = calculateBalanceFactor(node);

            // Balanceo del árbol después de la eliminación
            if (node.balanceFactor < -1) {
                if (calculateBalanceFactor(node.left) <= 0) {
                    return rotateRight(node);
                } else {
                    node.left = rotateLeft(node.left);
                    return rotateRight(node);
                }
            }

            if (node.balanceFactor > 1) {
                if (calculateBalanceFactor(node.right) >= 0) {
                    return rotateLeft(node);
                } else {
                    node.right = rotateRight(node.right);
                    return rotateLeft(node);
                }
            }
        }

        return node;
    }

    private NodeAvl<T> findMinimum(NodeAvl<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int calculateBalanceFactor(NodeAvl<T> node) {
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }

    private int height(NodeAvl<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private NodeAvl<T> rotateRight(NodeAvl<T> node) {
        NodeAvl<T> pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        node.balanceFactor = calculateBalanceFactor(node);
        pivot.balanceFactor = calculateBalanceFactor(pivot);
        return pivot;
    }

    private NodeAvl<T> rotateLeft(NodeAvl<T> node) {
        NodeAvl<T> pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        node.balanceFactor = calculateBalanceFactor(node);
        pivot.balanceFactor = calculateBalanceFactor(pivot);
        return pivot;
    }

       void preorder() {
        preorderTraversal(root);
    }

    private void preorderTraversal(NodeAvl<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    void inorder() {
        inorderTraversal(root);
    }

    private void inorderTraversal(NodeAvl<T> node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    void postorder() {
        postorderTraversal(root);
    }

    private void postorderTraversal(NodeAvl<T> node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    public T getMin() {
        if (root == null) {
            return null;
        }

        NodeAvl<T> current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    public T getMax() {
        if (root == null) {
            return null;
        }

        NodeAvl<T> current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    public NodeAvl<T> parent(NodeAvl<T> node) {
        if (node == null || node == root) {
            return null;
        }

        return findParent(root, node);
    }

    private NodeAvl<T> findParent(NodeAvl<T> current, NodeAvl<T> node) {
        if (current == null) {
            return null;
        }

        if ((current.left != null && current.left == node) || (current.right != null && current.right == node)) {
            return current;
        }

        NodeAvl<T> parent = findParent(current.left, node);
        if (parent == null) {
            parent = findParent(current.right, node);
        }

        return parent;
    }
    
    public NodeAvl<T> son(NodeAvl<T> parent, boolean isLeft) {
        if (parent == null) {
            return null;
        }

        if (isLeft) {
            return parent.left;
        } else {
            return parent.right;
        }
    }
}

