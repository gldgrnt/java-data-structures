

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	/*
	 * Given a Graph, this method returns the shortest distance (in terms of number of edges) 
	 * from the node labelled src to the node labelled dest. The method should return -1 for any 
	 * invalid inputs, including: null values for the Graph, src, or dest; there is no node 
	 * labelled src or dest in the graph; there is no path from src to dest. Keep in mind that 
	 * this method does not just return the distance of any path from src to dest, it must be 
	 * the shortest path.
	 */
	public static int minDistance(Graph graph, String src, String dest) {
		int distance = -1;
		// Handle base cases
		if (graph == null || src == null || dest == null) return distance; 
		if (!graph.containsElement(src) || !graph.containsElement(dest)) return distance; 
		/*
		 * Run edited bfs algo that increments a count when at least 1 neighbour is added to the
		 * marked hashSet from the current Node 
		 */
		BreadthFirstSearch search = new BreadthFirstSearch(graph);
		return search.bfsEdgeCount(graph.getNode(src), dest); 
	}
	

	/*
	 * Given a Graph, this method returns a Set of the values of all nodes within the 
	 * specified distance (in terms of number of edges) of the node labeled src, i.e. for 
	 * which the minimum number of edges from src to that node is less than or equal to the 
	 * specified distance. The value of the node itself should not be in the Set, even if there 
	 * is an edge from the node to itself. The method should return null for any invalid inputs, 
	 * including: null values for the Graph or src; there is no node labeled src in the graph; 
	 * distance less than 1. However, if distance is greater than or equal to 1 and there are no 
	 * nodes within that distance (meaning: src is the only node in the graph), the method should 
	 * return an empty Set.
	 */
	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		// Handle base cases
		if (graph == null || src == null || !graph.containsElement(src) || distance < 1) return null;
		/*
		 * Run edited bfs algo that runs normally along a maximum amount of edges returning the marked
		 * hashSet
		 */
		BreadthFirstSearch search = new BreadthFirstSearch(graph);
		return search.bfsMaxDistance(graph.getNode(src), distance);
	}


	/*
	 * Given a Graph, this method indicates whether the List of node values represents a Hamiltonian 
	 * Path. A Hamiltonian Path is a valid path through the graph in which every node in the graph is 
	 * visited exactly once, except for the start and end nodes, which are the same, so that it is a 
	 * cycle. If the values in the input List represent a Hamiltonian Path, the method should return 
	 * true, but the method should return false otherwise, e.g. if the path is not a cycle, if some 
	 * nodes are not visited, if some nodes are visited more than once, if some values do not have 
	 * corresponding nodes in the graph, if the input is not a valid path (i.e., there is a sequence 
	 * of nodes in the List that are not connected by an edge), etc. The method should also return 
	 * false if the input Graph or List is null.
	 */
	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		// Handle base cases + HP characteristics
		if (g == null || values == null || values.isEmpty()) return false;
		if (values.get(0) != values.get(values.size() - 1)) return false;
		if (values.size() != g.numNodes + 1) return false;
		// Check the path via following the list of values
		int currIndex = 0;
		Set<Node> visited = new HashSet<Node>();
		while(currIndex + 1 < values.size()) {
			BreadthFirstSearch search = new BreadthFirstSearch(g);
			// Handle base cases
			if (!g.containsElement(values.get(currIndex))) return false;
			if (!g.containsElement(values.get(currIndex + 1))) return false;
			// Create nodes
			Node node1 = g.getNode(values.get(currIndex));
			Node node2 = g.getNode(values.get(currIndex + 1));
			// If next value isn't along an edge, then path is not Hamiltonian
			boolean edgeExistsBetweenNodes = search.bfsMaxDistance(node1, 1).contains(node2.getElement());
			if (!edgeExistsBetweenNodes) return false;
			// Add node2 to visited list
			visited.add(node2); // (we can miss the first as it's the same as the last)
			currIndex++;
		}
		// Check that all nodes were visited
		return visited.containsAll(g.getAllNodes());
	}
	
}
