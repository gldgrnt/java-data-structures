

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;
		}
		if (start.getElement().equals(elementToFind)) {
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	
	/*
	 * bfs but counting the edges
	 */
	public int bfsEdgeCount(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
			return -1;
		}
		
		int count = 0;
		if (start.getElement().equals(elementToFind)) {
			return count;
		}		
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			boolean countAdded = false;
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					// Increment count when at least 1 neighbour has been added to the marked list
					if (!countAdded) {
						count++;
						countAdded = true;
					}
					// Break once element is found
					if (neighbor.getElement().equals(elementToFind)) {
						return count;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		// If the code reaches this point, the nodes are not connected and we return -1 
		return -1;
	}
	
	/*
	 * bfs but for counting up to a max number of neighbours
	 */
	public Set<String> bfsMaxDistance(Node start, int maxDistance) {
		if (!graph.containsNode(start) || maxDistance < 1) {
			return null;
		}
		HashSet<String> result = new HashSet<String>();
		int distance = 0;
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			// Check how many edges we've gone down
			if (distance >= maxDistance) {
				break;
			}
			Node current = toExplore.remove();
			boolean countAdded = false;
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					// Increment count when at least 1 neighbour has been added to the marked list
					if (!countAdded) {
						distance++;
						countAdded = true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		// Map nodes in marked hashSet to a hashSet of strings
		marked.remove(start);
		for(Node element : marked) {
			result.add(element.getElement());
		}
		return result;
	}
}
