package k20;

public interface ISearchAlgo {
	public Node executeRoot(Node root, String goal);// find the path from root node to the goal node
	public Node executeRoot(Node root, String goal,int limit);// find the path from root node to the goal node with limit depth

	public Node executeStartNode(Node root, String start, String goal); // find the path from start node to the goal node
	public Node executeStartNode(Node root, String start, String goal,int limit); // find the path from start node to the goal node with limit depth

}
