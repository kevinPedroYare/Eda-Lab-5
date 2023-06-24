public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(8);
        tree.insert(12);
        tree.insert(20);

        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        System.out.print("Inorder: ");
        tree.inorder();
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder();
        System.out.println();

	//GraphStreamTree<Integer> graphTree = new GraphStreamTree<>(tree);
	//graphTree.visualizeTree();
    }

    public GraphStream(){
    
	System.setProperty("org.graphstream.ui", "swing");

	Graph graph = new SingleGraph("GraphStream");

	Viewer viewer = graph.display();
	viewer.disableAutoLayout();

	graph.setAttribute("ui.stylesheet", styleSheet);
	for(Node<T> node: tree){
		graph.addNode(node.key.toString());
		graph.getNode
	}
    }

}

