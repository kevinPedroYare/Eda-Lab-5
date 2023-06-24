class Node<T> {
    T data;
    Node<T> rightNode;
    Node<T> leftNode;
    int balanceFactor;

    Node(T data) {
        this.data = data;
        this.rightNode = null;
        this.leftNode = null;
        this.balanceFactor = 0;
    }
}
