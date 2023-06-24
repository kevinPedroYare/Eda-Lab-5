public class NodeAvl<T> {
    T key;
    NodeAvl<T> left;
    NodeAvl<T> right;
    int height;
    int balanceFactor;

    public NodeAvl(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.balanceFactor = 0;
    }
}
