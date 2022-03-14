package model;

import java.util.concurrent.ConcurrentHashMap;

import tests.Node;

public class ListUtilities {

	public Node<String> getAllPrefixes(Node<Integer> input, int i, int j) {
		Node<String> output = new Node<String>(null, null);
		Node<String> currento = output;
		int count = i;
		while (count <= j) {
			String out = "[";
			Node<Integer> head = input;
			for (int z = 1; z <= count; z++) {
				out = out += head.getElement();
				if (z == count) {
					out += "]";
				} else {
					out += ", ";
					head = head.getNext();
				}

			}
			currento.setElement(out);
			if (count < j) {
				currento.setNext(new Node<String>(null, null));
				currento = currento.getNext();
			}
			count++;
		}

		return output;
	}

	public Node<Integer> getMergedChain(Node<Integer> leftChain, Node<Integer> rightChain) {
		if (leftChain == null && rightChain == null) {
			return null;
		}
		if (rightChain == null) {
			return hardCopy(leftChain);
		}
		if (leftChain == null) {
			return hardCopy(rightChain);
		}
		Node<Integer> left = hardCopy(leftChain);
		Node<Integer> right = hardCopy(rightChain);
		Node<Integer> output = new Node<Integer>(0, null);
		Node<Integer> current = output;
		while (true) {
			if (left.getElement() <= right.getElement()) {
				current.setNext(left);
				current = current.getNext();
				if (current.getNext() == null) {
					current.setNext(right);
					return output.getNext();
				}
				left = left.getNext();
			} else if (left.getElement() > right.getElement()) {
				current.setNext(right);
				current = current.getNext();
				if (current.getNext() == null) {
					current.setNext(left);
					return output.getNext();
				}
				right = right.getNext();
			}
		}
	}

	private Node<Integer> hardCopy(Node<Integer> input) {
		Node<Integer> output = new Node<Integer>(null, null);
		Node<Integer> head = output;
		Node<Integer> current = input;
		while (current.getNext() != null) {
			head.setElement(current.getElement());
			head.setNext(new Node<Integer>(null, null));
			head = head.getNext();
			current = current.getNext();
		}
		head.setElement(current.getElement());
		return output;
	}

	public Node<Integer> getInterleavedArithmeticFibonacciSequences(int i, int j, int k, int l) {
		int fibCount = 0;
		int ariCount = 0;
		Node<Integer> output = new Node<Integer>(null, null);
		Node<Integer> current = output;
		while (fibCount < l || ariCount < k) {
			if (ariCount < k) {
				current.setNext(new Node<Integer>(null, null));
				current = current.getNext();
				ariCount++;
				current.setElement(i + (ariCount - 1) * j);
			}
			if (fibCount < l) {
				current.setNext(new Node<Integer>(null, null));
				current = current.getNext();
				fibCount++;
				current.setElement(fib(fibCount));
			}
		}
		return output.getNext();
	}

	private int fib(int i) {
		if (i == 1) {
			return 1;
		} else if (i == 2) {
			return 1;
		} else {
			return fib(i - 1) + fib(i - 2);
		}
	}

	public Node<String> getGroupedStrings(Node<String> input, int m, int n) {
		if (input == null) {
			return null;
		}
		Node<String> ltm = new Node<String>(null, null);
		Node<String> ltmCurrent = ltm;
		Node<String> geqmltn = new Node<String>(null, null);
		Node<String> geqmltnCurrent = geqmltn;
		Node<String> geqn = new Node<String>(null, null);
		Node<String> geqnCurrent = geqn;
		while (true) {
			if (input.getElement().length() < m) {
				ltmCurrent.setNext(new Node<String>(null, null));
				ltmCurrent = ltmCurrent.getNext();
				ltmCurrent.setElement(input.getElement());
			} else if (input.getElement().length() >= m && input.getElement().length() < n) {
				geqmltnCurrent.setNext(new Node<String>(null, null));
				geqmltnCurrent = geqmltnCurrent.getNext();
				geqmltnCurrent.setElement(input.getElement());
			} else {
				geqnCurrent.setNext(new Node<String>(null, null));
				geqnCurrent = geqnCurrent.getNext();
				geqnCurrent.setElement(input.getElement());
			}
			if (input.getNext() == null) {
				Node<String> output = new Node<String>(null, null);
				Node<String> current = output;
				if (ltm.getNext() != null) {
					current.setNext(ltm.getNext());
				}
				while (current.getNext() != null) {
					current = current.getNext();
				}
				if (geqmltn.getNext() != null) {
					current.setNext(geqmltn.getNext());
				}
				while (current.getNext() != null) {
					current = current.getNext();
				}
				if (geqn.getNext() != null) {
					current.setNext(geqn.getNext());
				}
				return output.getNext();
			}
			input = input.getNext();
		}
	}

}
