class NodeAvl<T> {
    T data;
    NodeAvl<T> right;
    NodeAvl<T> left;
    int balanceFactor;

    NodeAvl(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
        this.balanceFactor = 0;
    }
}
