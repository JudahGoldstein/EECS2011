package model;

import tests.Node;

/*
 * Name:		Judah Goldstein
 * Student#:	217988841
 * Date:		2022-02-28
 */
public class ListUtilities<T> {
	public Node<String> task(Node<String> n1, int n) {
		/* 
		 * if rotating zero then return input. not strictly needed 
		 * (would work with this commented out) but is best practice. 
		 */
		if (n == 0) { 
			return n1;
		}
		Node<String> current = n1; //current node as not to lose the head of the singly linked list
		Node<String> head = n1; //head that well be returned
		for (int i = 0; i < n; i++) { //each loop iteration will rotate the list once to the right
			//traverse to the node before the last node (which will become the new first node)
			while (current.getNext().getNext() != null) {
				current = current.getNext();
			}
			current.getNext().setNext(head); //connect the last node to the head of the list
			head = current.getNext(); //move the head to the new head of the list
			current.setNext(null); //disconnect the second to last node from the last making it the new last node
			current = head; //reset the current node to the new head to prepare for next iteration
		}
		return head; //return the new head
	}
}
