package binarysearchtree;

public class BinarySearchTree {
	private Node root;

	class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
		}
	}
	
	public Node getRoot() {
		return root;
	}

	public boolean insert(int value) {
		Node newNode = new Node(value);

		if (root == null)
			root = newNode;

		Node temp = root;
		while (true) {
			if (newNode.value == temp.value)
				return false;
			if (newNode.value > temp.value) {
				if (temp.right == null) {
					temp.right = newNode;
					return true;
				}
				temp = temp.right;
			} else {
				if (temp.left == null) {
					temp.left = newNode;
					return true;
				}
				temp = temp.left;
			}
		}
	}

	public boolean contains(int value) {
		Node temp = root;
		while (temp != null) {
			if (value < temp.value)
				temp = temp.left;
			else if (value > temp.value)
				temp = temp.right;
			else
				return true;
		}
		return false;
	}
	
	private boolean rContains(Node currentNode, int value) {
        if (currentNode == null) return false;
 
        if (currentNode.value == value) return true;
 
        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }
	
	public boolean rContains(int value) {
        return rContains(root, value);
    }
	
	private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        } else {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode;
    } 
    
    public void rInsert(int value) {
        if (root == null) root = new Node(value);
        rInsert(root, value);
    }
}
