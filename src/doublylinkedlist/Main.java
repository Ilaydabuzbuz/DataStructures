package doublylinkedlist;


public class Main {

	public static void main(String[] args) {
		DoublyLinkedList myDoublyLinkedList = new DoublyLinkedList(1);
		myDoublyLinkedList.append(2);
		myDoublyLinkedList.append(3);
		myDoublyLinkedList.append(4);
		myDoublyLinkedList.swapPairs();
		myDoublyLinkedList.printAll();
		myDoublyLinkedList.printList();

	}

}
