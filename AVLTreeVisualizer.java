import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class AVLTreeVisualizer<T extends Comparable<T>> {
    private Graph graph;

    public AVLTreeVisualizer() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("AVL Tree");
        graph.setAttribute("ui.stylesheet", styleSheet);
    }

    public void visualizeTree(AVLTree<T> tree) {
        addNodesToGraph(tree.root);
        addEdgesToGraph(tree.root);

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
    }

    private void addNodesToGraph(NodeAvl<T> node) {
        if (node != null) {
            Node graphNode = graph.addNode(node.data.toString());
            graphNode.setAttribute("data", node.data.toString());
            graphNode.setAttribute("ui.label", node.data.toString());
            graphNode.setAttribute("ui.class", "node");

            addNodesToGraph(node.left);
            addNodesToGraph(node.right);
        }
    }

    private void addEdgesToGraph(NodeAvl<T> node) {
        if (node != null) {
            if (node.left != null) {
                graph.addEdge(node.data.toString() + "-" + node.left.data.toString(),
                        node.data.toString(), node.left.data.toString());
            }

            if (node.right != null) {
                graph.addEdge(node.data.toString() + "-" + node.right.data.toString(),
                        node.data.toString(), node.right.data.toString());
            }

            addEdgesToGraph(node.left);
            addEdgesToGraph(node.right);
        }
    }

    private String styleSheet =
        "node {" +
        "   shape: circle;" +
        "   size: 40px;" +
        "   fill-mode: plain;" +
        "   fill-color: skyblue;" +
        "   stroke-mode: plain;" +
        "   stroke-color: black;" +
        "   stroke-width: 1px;" +
        "   text-alignment: center;" +
        "   text-size: 15px;" +
        "}";
}