package linkedlist;

import java.util.Arrays;

public class LinkedList {

	private Node head;
	private Node tail;
	private int length;

	class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}
	}

	public LinkedList(int value) {
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
			tail = newNode;
		}
		length++;
	}

	public Node removeLast() {
		Node removedNode = null;
		if (length == 0) {
			return null;
		} else if (length == 1) {
			removedNode = tail;
			makeEmpty();
			return removedNode;
		} else {
			Node temp = head;
			while (temp.next != tail) {
				temp = temp.next;
			}
			removedNode = temp.next;
			temp.next = null;
			tail = temp;
			length--;
			return removedNode;
		}
	}

	public void prepend(int value) {
		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		length++;
	}

	public Node removeFirst() {
		Node removedNode = null;
		if (length == 0) {
			return null;
		} else if (length == 1) {
			removedNode = head;
			makeEmpty();
			return removedNode;
		} else {
			removedNode = head;
			head = head.next;
			removedNode.next = null;
			length--;
			return removedNode;
		}
	}

	public Node get(int index) {
		Node temp = head;
		if (index < 0 || index >= length)
			return null;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public boolean set(int index, int value) {
		if (index < 0 || index >= length)
			return false;
		Node temp = get(index);
		temp.value = value;
		return true;

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
		Node inserted = new Node(value);
		Node temp = get(index - 1);
		inserted.next = temp.next;
		temp.next = inserted;
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

		Node prev = get(index - 1);
		// Node temp = get(index); O(n)
		Node temp = prev.next;
		prev.next = temp.next;
		temp.next = null;
		length--;

		return temp;
	}

	public void reverse() {
		if (length == 0)
			return;

		Node temp = head;
		head = tail;
		tail = temp;

		Node prev = null;
		Node curr = tail;
		Node next;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
	}

	public Node findMiddleNode() {
		if (head == null)
			return null;
		;
		Node slowPointer = head;
		Node fastPointer = head;
		Node middle = null;
		Node tmp = null;
		while (fastPointer != null) {
			middle = slowPointer;
			slowPointer = slowPointer.next;
			tmp = fastPointer.next;
			fastPointer = tmp == null ? null : tmp.next;
		}
		return tmp == null ? middle : middle.next;

	}

	/*
	 * public Node findMiddleNode() { Node slow = head; Node fast = head;
	 * 
	 * while (fast != null && fast.next != null) { slow = slow.next; fast =
	 * fast.next.next; }
	 * 
	 * return slow; }
	 */

	public boolean hasLoop() {
		if (head == null)
			return false;
		Node slowPointer = head;
		Node fastPointer = head.next;

		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			if (fastPointer == slowPointer)
				return true;
		}
		return false;
	}

	public Node findKthFromEnd(int k) {
		Node slow = head;
		Node fast = head;

		for (int i = 0; i < k; i++) {
			if (fast == null)
				return null;
			fast = fast.next;
		}

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	public void removeKthFromEnd(int k) {
		Node slow = head;
		Node fast = head;

		for (int i = 0; i < k; i++) {
			if (fast == null)
				return;
			fast = fast.next;
		}

		Node prev = null;

		while (fast != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next;
		}

		if (slow == head) {
			head = head.next;
			slow.next = null;
		} else {
			prev.next = slow.next;
			slow.next = null;
		}
		length--;

	}

	public void reverseBetween(int m, int n) {
		if (head == null || m == n)
			return;

		Node dummy = new Node(0);
		dummy.next = head;
		Node prev = dummy;

		for (int i = 0; i < m; i++) {
			prev = prev.next;
		}

		Node curr = prev.next;

		for (int i = 0; i < n - m; i++) {
			Node temp = curr.next;
			curr.next = temp.next;
			temp.next = prev.next;
			prev.next = temp;
		}
		head = dummy.next;

	}

	public void removeDuplicates() {
		Node current = head;
		while (current != null) {
			Node runner = current;
			while (runner.next != null) {
				if (runner.next.value == current.value) {
					runner.next = runner.next.next;
					length -= 1;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	public void swapNodes(int k) {
		if (head == null)
			return;
		Node firstNode = head;
		int first = -1;
		int last = -1;
		for (int i = 0; i < k - 1; i++) {
			firstNode = firstNode.next;
		}
		first = firstNode.value;

		Node lastNode = head;

		for (int i = 0; i < length - k; i++) {
			lastNode = lastNode.next;
		}
		last = lastNode.value;

		firstNode.value = last;
		lastNode.value = first;
	}

	public boolean isPalindrome() {
		int[] frontNodes = new int[length / 2];
		int[] endNodes = new int[length / 2];
		Node temp = head;
		for (int i = 0; i < frontNodes.length; i++) {
			frontNodes[i] = temp.value;
			temp = temp.next;
		}
		temp = length % 2 == 0 ? temp : temp.next;
		for (int i = endNodes.length - 1; i >= 0; i--) {
			endNodes[i] = temp.value;
			temp = temp.next;
		}

		return Arrays.equals(frontNodes, endNodes);

	}

	public void partitionList(int x) {
		if (head == null)
			return;
		Node leftHead = new Node(0);
		Node rightHead = new Node(0);
		Node leftTail = leftHead;
		Node rightTail = rightHead;

		while (head != null) {
			if (head.value < x) {
				leftTail.next = head;
				leftTail = leftTail.next;
			} else {
				rightTail.next = head;
				rightTail = rightTail.next;
			}
			head = head.next;
		}
		leftTail.next = rightHead.next;
		rightTail.next = null;

		head = leftHead.next;
		tail = rightTail;
	}
	
	public void swapPairs() {
		Node dummy = new Node(0);
		dummy.next = head;
		Node prev = dummy;
		Node curr = head;
		for (int i = 0; i <= length / 2 - 1; i++) {
			Node next = curr.next;
			curr.next = next.next;
			next.next = curr;
			prev.next = next;
			curr = curr.next;
			prev = prev.next.next;
		}
		head = dummy.next;
	}

	

}
