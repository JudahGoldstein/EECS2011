package model;

import tests.SLLNode;
import tests.TreeNode;

public class TreeUtilities {

	public SLLNode<Integer> getElementsOfRanks(TreeNode<Integer> n, int i, int j) {
		SLLNode<TreeNode<Integer>> que = new SLLNode<TreeNode<Integer>>(n, null);
		SLLNode<TreeNode<Integer>> tail = que;
		SLLNode<Integer> result = null;
		while (que != null) {
			result = addSorted(result, que.getElement().getElement());
			tail.setNext(que.getElement().getChildren());
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			que = que.getNext();
		}
		SLLNode<Integer> current = result;
		for (int z = 1; z < j; z++) {
			current = current.getNext();
		}
		current.setNext(null);
		for (int z = 1; z < i; z++) {
			result = result.getNext();
		}
		return result;
	}

	private SLLNode<Integer> addSorted(SLLNode<Integer> n, int i) {
		if (n == null) {
			n = new SLLNode<Integer>(i, null);
			return n;
		}
		if (n.getElement() >= i) {
			SLLNode<Integer> head = new SLLNode<Integer>(i, n);
			return head;
		}
		SLLNode<Integer> current = n;
		while (current.getNext() != null) {
			if (current.getNext().getElement() >= i) {
				SLLNode<Integer> newNode = new SLLNode<Integer>(i, current.getNext());
				current.setNext(newNode);
				return n;
			}
			current = current.getNext();
		}

		current.setNext(new SLLNode<Integer>(i, null));
		return n;
	}

	public TreeNode<String> getStats(TreeNode<Integer> n) {
		return clone(new TreeNode<String>(null), n);
	}
	
	private TreeNode<String> clone(TreeNode<String> nn, TreeNode<Integer> n) {
		SLLNode<Integer> stats = stats(n);
		nn.setElement("Number of descendants: "+stats.getElement()+"; Sum of descendants: "+stats.getNext().getElement());
		SLLNode<TreeNode<Integer>> children = n.getChildren();
		while(children!=null) {
			TreeNode<String> nc = new TreeNode<String>(null);
			clone(nc, children.getElement());
			nc.setParent(nn);
			nn.addChild(nc);
			children = children.getNext();
		}
		return nn;
	}

	public SLLNode<Integer> stats(TreeNode<Integer> n) {
		SLLNode<TreeNode<Integer>> que = new SLLNode<TreeNode<Integer>>(n, null);
		SLLNode<TreeNode<Integer>> tail = que;
		SLLNode<Integer> result = new SLLNode<Integer>(0, new SLLNode<Integer>(0, null));
		while (que != null) {
			result.setElement(result.getElement() + 1);
			result.getNext().setElement(result.getNext().getElement() + que.getElement().getElement());
			tail.setNext(que.getElement().getChildren());
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			que = que.getNext();
		}
		return result;
	}
}
