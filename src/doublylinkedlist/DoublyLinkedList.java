package doublylinkedlist;

public class DoublyLinkedList {

	private Node head;
	private Node tail;
	private int length;

	class Node {
		int value;
		Node next;
		Node prev;

		Node(int value) {
			this.value = value;
		}
	}

	public DoublyLinkedList(int value) {
		Node newNode = new Node(value);
		head = newNode;
		tail = newNode;
		length = 1;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	public int getLength() {
		return length;
	}

	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}

	public void printAll() {
		if (length == 0) {
			System.out.println("Head: null");
			System.out.println("Tail: null");
		} else {
			System.out.println("Head: " + head.value);
			System.out.println("Tail: " + tail.value);
		}
		System.out.println("Length:" + length);
		System.out.println("\nLinked List:");
		if (length == 0) {
			System.out.println("empty");
		} else {
			printList();
		}
	}

	public void makeEmpty() {
		head = null;
		tail = null;
		length = 0;
	}

	public void append(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		length++;
	}

	public Node removeLast() {
		Node temp = tail;
		if (length == 0)
			return null;

		if (length == 1) {
			makeEmpty();
		} else {
			tail = tail.prev;
			tail.next = null;
			tail.prev = null;
			length--;
		}
		return temp;
	}

	public void prepend(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		length++;
	}

	public Node removeFirst() {
		Node temp = head;
		if (length == 0) {
			return null;
		} else if (length == 1) {
			makeEmpty();
			return temp;
		} else {
			head = head.next;
			temp.next = null;
			head.prev = null;
			length--;
			return temp;
		}
	}

	public Node get(int index) {
		Node temp = head;
		if (index < 0 || index >= length)
			return null;
		if (index < length / 2) {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		} else {
			temp = tail;
			for (int i = length - 1; i > index; i--) {
				temp = temp.prev;
			}
		}

		return temp;
	}

	public boolean set(int index, int value) {
		Node temp = get(index);
		if (temp != null) {
			temp.value = value;
			return true;
		}
		return false;

	}

	public boolean insert(int index, int value) {
		if (index < 0 || index > length)
			return false;
		if (index == 0) {
			prepend(value);
			return true;
		}
		if (index == length) {
			append(value);
			return true;
		}
		Node newNode = new Node(value);
		Node prev = get(index - 1);
		Node next = prev.next;
		newNode.next = next;
		newNode.prev = prev;
		prev.next = newNode;
		next.prev = newNode;

		length++;

		return true;
	}

	public Node remove(int index) {
		if (index < 0 || index >= length)
			return null;
		if (index == 0)
			return removeFirst();
		if (index == length - 1)
			return removeLast();

		Node temp = get(index);
		temp.next.prev = temp.prev;
		temp.prev.next = temp.next;
		temp.next = null;
		temp.prev = null;
		length--;

		return temp;
	}

	public void reverse() {
		Node current = head;
		Node temp = null;

		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}

		temp = head;
		head = tail;
		tail = temp;

	}

	public void swapFirstLast() {
		if (length < 2)
			return;
		int temp = head.value;
		head.value = tail.value;
		tail.value = temp;
	}

	public boolean isPalindrome() {
		Node tempF = head;
		Node tempB = tail;
		while (tempF != tempB && tempB.next != tempF) {
			if (tempF.value != tempB.value)
				return false;
			tempF = tempF.next;
			tempB = tempB.prev;
		}
		return true;
	}

	public void swapPairs() {
	    if (head == null || head.next == null)
	        return; 
	    Node dummy = new Node(0);
	    dummy.next = head;
	    Node prev = dummy;
	    Node curr = head;
	    Node last = null;
	    while (curr != null && curr.next != null) {
	        Node next = curr.next;
	        curr.next = next.next;
	        if (next.next != null)
	            next.next.prev = curr;
	        next.next = curr;
	        curr.prev = next;
	        next.prev = prev;
	        prev.next = next;
	        last = curr;
	        prev = curr;
	        curr = curr.next;
	    }
	    head = dummy.next;
	    tail = last;
	}


}
