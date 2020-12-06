


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
		/*
		 * Given a value that is stored in the BST, it returns the corresponding Node that holds
		 * it. If the value does not exist in this BST, this method should return null.
		 */
		return findNode(root, val);
	}
	
	protected Node findNode(Node n, E val)  {
		if (val == null) return null;
		
		int comparison = val.compareTo(n.value);
		// Check to the left
		if (comparison < 0) return n.leftChild != null ? findNode(n.leftChild, val) : null;
		// Check to the right
		if (comparison > 0) return n.rightChild != null ? findNode(n.rightChild, val) : null;
		// If we get here then it must be the node we're on 
		return n;
	}
	
	// Method #2.
	protected int depth(E val) {
		/*
		 * Given a value, this method should return the “depth” of its Node, which is the 
		 * number of ancestors between that node and the root, including the root but not the 
		 * node itself. The depth of the root is defined to be 0; the depth of its two children 
		 * (if any) is defined to be 1; the depth of the root’s grand children (if any) is 
		 * defined to be 2; and so on. If the value is null or does not exist in this BST, this 
		 * method should return -1.
		 */
		
		return depth(root, val, 0);
	}
	
	protected int depth(Node n, E val, int d) {
		if (val == null) return -1;
		
		int comparison = val.compareTo(n.value);
		++d;
		// Check to the left
		if (comparison < 0) return n.leftChild != null ? depth(n.leftChild, val, d) : -1;
		// Check to the right
		if (comparison > 0) return n.rightChild != null ? depth(n.rightChild, val, d) : -1;
		// If we get here then it must be the node we're on 
		return --d;
	}
	
	// Method #3.
	protected int height(E val) {
		/*
		 * Given a value, this method should return the “height” of its Node, which is the greatest 
		 * number of nodes between that node and any descendant node that is a leaf, including the 
		 * leaf but not the node itself. The height of a leaf node (i.e., one which has no children) 
		 * is defined to be 0. If the input value is null or does not exist in this BST, this method 
		 * should return -1.
		 */
		
		// Handle base cases: val == null || node == null
		Node node = findNode(val);
		if (node == null) return -1;
		return height(node, 0);

	}
	
	protected int height(Node n, int h) {
		if (n.leftChild == null && n.rightChild == null) return h;
		// Find the maxHeight of each side of the Node
		++h;
		int leftHeight = n.leftChild != null ? height(n.leftChild, h) : 0;
		int rightHeight = n.rightChild != null ? height(n.rightChild, h) : 0;
		// Return the max of the two heights
		return Math.max(leftHeight, rightHeight); 
	}


	// Method #4.
	protected boolean isBalanced(Node n) {
		/* 
		 * Given a Node, return true if the absolute value of the difference in heights of its 
		 * left and right children is 0 or 1, and return false otherwise. If the Node is null or 
		 * does not exist in this BST, this method should return false.
		 * 
		 * Note that if a Node's child is null, then the height of that child should be considered as -1. 
		 */
		
		// Handle base cases: n == null || n is not present in BST
		Node node = n != null ? findNode(n.value) : null;
		if (node == null) return false;
		// Return abs(difference in children heights)
		int lh = n.leftChild == null ? -1 : height(n.leftChild, 0);
		int rh = n.rightChild == null ? -1 : height(n.rightChild, 0);
		return Math.abs(lh - rh) < 2;

	}
	
	// Method #5. .
	public boolean isBalanced() {		
		// Recursively test each node in the tree
		return isTreeBalanced(root);
	}
	
	protected boolean isTreeBalanced(Node n) {
		if (!isBalanced(n)) return false;
		return isBalanced(n.leftChild) && isBalanced(n.rightChild);
	} 
}
