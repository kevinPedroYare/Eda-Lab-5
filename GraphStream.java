import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphStream {
    void visualizeTree(NodeAvl<?> root) {
        Graph graph = new SingleGraph("Árbol AVL");

        addNodesToGraph(root, graph);
        addEdgesToGraph(root, graph);

        graph.addAttribute("ui.stylesheet", "node { size: 20px; text-size: 15px; text-alignment: center; }");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        graph.display();
    }

    void addNodesToGraph(NodeAvl<?> node, Graph graph) {
        if (node != null) {
            org.graphstream.graph.Node graphNode = graph.addNode(node.key.toString());
            graphNode.addAttribute("ui.label", node.key.toString());

            addNodesToGraph(node.left, graph);
            addNodesToGraph(node.right, graph);
        }
    }

    void addEdgesToGraph(NodeAvl<?> node, Graph graph) {
        if (node != null) {
            if (node.left != null) {
                graph.addEdge(node.key.toString() + "-" + node.left.key.toString(),
                        node.key.toString(), node.left.key.toString());
            }

            if (node.right != null) {
                graph.addEdge(node.key.toString() + "-" + node.right.key.toString(),
                        node.key.toString(), node.right.key.toString());
            }

            addEdgesToGraph(node.left, graph);
            addEdgesToGraph(node.right, graph);
        }
    }
}

