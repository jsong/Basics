package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.general.binarytree.TreeNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(8);
		
		root.left = new TreeNode(6);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(2);
		Solution sl = new Solution();
		boolean balanced = sl.isBalanced(root);
	}

	// 144. Binary Tree Preorder Traversal
	// Company: Facebook # Alibaba
	// Description: Given a binary tree, return the preorder traversal of its nodes'
	// values.
	// Solution: 1. DFS 2. BFS
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> nodes = new ArrayList<>();
		preorder(root, nodes);

		return nodes;
	}

	private void preorder(TreeNode node, List<Integer> nodes) {
		if (node == null) {
			return;
		}
		nodes.add(node.val);
		preorder(node.left, nodes);
		preorder(node.right, nodes);
	}

	// 2 BFS
	public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();

		if (root != null) {
			stack.push(root);
		}

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();

			res.add(node.val);

			if (node.right != null) {
				stack.add(node.right);
			}

			if (node.left != null) {
				stack.add(node.left);
			}
		}

		return res;
	}

	// 94. Binary Tree Inorder Traversal
	// Company: Adobe Amazon # Facebook Microsoft Palantir Tech
	// Description: Given a binary tree, return the inorder traversal of its nodes'
	// values.
	// Solution: 1. DFS 2. BFS 3. Still BFS without modify the original tree.
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		inorder(root, res);
		return res;
	}

	private void inorder(TreeNode node, List<Integer> res) {
		if (node == null) {
			return;
		}

		inorder(node.left, res);
		res.add(node.val);
		inorder(node.right, res);
	}

	public List<Integer> inorderTraversal3(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> res = new ArrayList<>();

		TreeNode p = root;

		while (p != null || !stack.isEmpty()) {
			while (p != null) {
				stack.push(p);
				p = p.left;
			}

			p = stack.pop();
			res.add(p.val);
			p = p.right;
		}

		return res;
	}

	public List<Integer> inorderTraversal2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode node = stack.peek();
			if (node.left != null) {
				stack.push(node.left);
			} else {
				TreeNode top = stack.pop();
				res.add(top.val);

				if (!stack.isEmpty()) {
					TreeNode newTop = stack.peek();
					newTop.left = null;
				}

				if (top.right != null) {
					stack.push(top.right);
				}
			}
		}

		return res;
	}

	// 145. Binary Tree Postorder Traversal
	// Company: Facebook # Google
	// Description: Given a binary tree, return the postorder traversal of its
	// nodes' values.
	// Solution: 1. DFS
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		postorder(root, res);
		return res;
	}

	private void postorder(TreeNode node, List<Integer> res) {
		if (node == null) {
			return;
		}

		postorder(node.left, res);
		postorder(node.right, res);
		res.add(node.val);
	}

	// 102. Binary Tree Level Order Traversal
	// Company: Amazon Apple Google Microsoft Bloomberg Facebook LinkedIn eBay #
	// Paypal Adobe TripAdvisor
	// Description: Given a binary tree, return the level order traversal of its
	// nodes' values. (ie, from left to right, level by level).
	// Solution: Use queue to track each level nodes.
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);

				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			res.add(level);
		}

		return res;
	}

	// 107. Binary Tree Level Order Traversal II
	// Company: Microsoft # Google Airbnb
	// Description: Given a binary tree, return the "bottom-up" level order
	// traversal of its nodes' values. (ie, from left to right, level by level from
	// leaf to root).
	// Solution: Same as 102, except when adding the res, always insert the result
	// at "0".
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}

		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);

				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			res.add(0, level);
		}

		return res;
	}

	// 199. Binary Tree Right Side View
	// Company: Amazon Facebook Apple # Mathworks Adobe Zenefits
	// Description: Given a binary tree, imagine yourself standing on the right side
	// of it, return the values of the nodes you can see ordered from top to bottom.
	// Solution: BFS level traversal, record the right most element.
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();

		if (root == null) {
			return res;
		}

		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
				if (i == size - 1) { // right most
					res.add(node.val);
				}
			}
		}

		return res;
	}

	// 226. Invert Binary Tree
	// Company: Google Uber # Bloomberg Baidu
	// Description: Invert a binary tree.
	// Solution: 1. Use BFS level traverse and swap the left and right node. 2. Use
	// recursion. 2. Recursion
	public TreeNode invertTree2(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();

		if (root == null) {
			return root;
		}

		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				// swap
				TreeNode left = node.left;
				TreeNode right = node.right;
				node.left = right;
				node.right = left;
				//
				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}

		return root;
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		TreeNode tmp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(tmp);

		return root;
	}

	// 103. Binary Tree Zigzag Level Order Traversal
	// Company: Microsoft Amazon Facebook eBay Uber Tencent Google Apple Alibaba #
	// Baidu Pinterest
	// Description: Given a binary tree, return the zigzag level order traversal of
	// its nodes' values. (ie, from left to right, then right to left for the next
	// level and alternate between).
	// Solution: Use counter to track whether left -> right or right -> left. BFS.
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();

		if (root == null) {
			return res;
		}

		queue.offer(root);
		int counter = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			counter++;
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if (counter % 2 == 1) {
					level.add(node.val); // left -> right;
				} else {
					level.add(0, node.val); // right -> left;
				}

				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			res.add(level);
		}

		return res;
	}

	// 173. Binary Search Tree Iterator
	// Company: Facebook Microsoft Oracle Amazon Qualtrics # Cloudera Google
	// Bloomberg Uber LinkedIn
	// Description: Implement an iterator over a binary search tree (BST). Your
	// iterator will be initialized with the root node of a BST.Calling next() will
	// return the next smallest number in the BST. Note: next() and hasNext() should
	// run in average O(1) time and uses O(h) memory, where h is the height of the
	// tree.
	// Solution: 1. BST inorder traverse will form a sorted array. By using the
	// sorted array, will get the expected output. 2.
	public class BSTIterator {
		int curIndex = 0;
		List<Integer> res = new ArrayList<>();

		public BSTIterator(TreeNode root) {
			Stack<TreeNode> stack = new Stack<>();
			TreeNode p = root;

			while (!stack.isEmpty() || p != null) {
				while (p != null) {
					stack.push(p);
					p = p.left;
				}

				p = stack.pop();
				res.add(p.val);
				p = p.right;
			}
		}

		/** @return whether we have a next smallest number */
		public boolean hasNext() {
			return curIndex < res.size();
		}

		/** @return the next smallest number */
		public int next() {
			if (curIndex < res.size()) {
				return res.get(curIndex++);
			}

			return -1;
		}
	}

	// Solution 2:
	public class BSTIterator2 {
		Stack<TreeNode> stack;

		public BSTIterator2(TreeNode root) {
			stack = new Stack<>();
			TreeNode p = root;
			while (p != null) {
				stack.push(p);
				p = p.left;
			}
		}

		/** @return whether we have a next smallest number */
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		/** @return the next smallest number */
		public int next() {
			TreeNode p = stack.pop();
			if (p.right != null) {
				TreeNode node = p.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}

			return p.val;
		}
	}

	// 99. Recover Binary Search Tree
	// Company: Facebook Amazon # Uber Google Microsoft
	// Description: Two elements of a binary search tree (BST) are swapped by
	// mistake. Recover the tree without changing its structure.
	// Solution: Use in order traverse, re
	TreeNode prev; // check whether it's in the correct order
	TreeNode p1; // store the wrong swapped nodes.
	TreeNode p2;

	public void recoverTree(TreeNode root) {
		inorder(root);
		if (p1 != null && p2 != null) {
			int tmp = p1.val;
			p1.val = p2.val;
			p2.val = tmp;
		}
	}

	// in order prints the tree in ascending order
	private void inorder(TreeNode node) {
		if (node == null) {
			return;
		}

		inorder(node.left);

		if (prev != null && prev.val > node.val) {
			if (p1 == null) {
				p1 = prev;
				p2 = node;
			} else {
				p2 = node;
			}
		}

		prev = node;
		inorder(node.right);
	}

	// 100. Same Tree
	// Company: Amazon # Facebook LinkedIn
	// Description:Given two binary trees, write a function to check if they are the
	// same or not. Two binary trees are considered the same if they are
	// structurally identical and the nodes have the same value.
	// Solution: 1. Origin version which runs more time. 2. Pruning based on 1.
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q != null) {
			return false;
		}

		if (p != null && q == null) {
			return false;
		}

		if (p == null && q == null) {
			return true;
		}

		if (p.val != q.val) {
			return false;
		}

		boolean sameLeft = isSameTree(p.left, q.left);

		if (sameLeft) {
			boolean sameRight = isSameTree(p.right, q.right);

			return sameLeft && sameRight;
		}

		return false;
	}

	public boolean isSameTree2(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if (p == null || q == null) {
			return false;
		}

		// if (p.val == q.val) {
		// if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
		// return true;
		// }
		// }
		//
		// return false;
		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	// 101. Symmetric Tree
	// Company: LinkedIn Amazon Facebook Apple # Microsoft Uber Google
	// Description: Given a binary tree, check whether it is a mirror of itself (ie,
	// symmetric around its center).
	// Solution: 1. Recursion left should equal to right. Similar to isSame Tree.
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return isMirror(root.left, root.right);
	}

	private boolean isMirror(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if (p == null || q == null) {
			return false;
		}

		return (p.val == q.val) && isMirror(p.left, q.right) && isMirror(p.right, q.left);
	}

	// 110. Balanced Binary Tree
	// Company: Amazon Facebook Google # Paypal
	// Description: Given a binary tree, determine if it is height-balanced. For
	// this problem, a height-balanced binary tree is defined as: a binary tree in
	// which the depth of the two subtrees of every node never differ by more than 1.
	// NOTE: Recursive concept, so original solution won't work.
	// Solution: 1. Recursive solution, check left and right, return the depth + 1; 2. 
	public boolean isBalanced(TreeNode root) {
		
		return treeHeight(root) >= 0;
	}
	
	private int treeHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int left = treeHeight(node.left);
		int right = treeHeight(node.right);
		
		// Pruning
		if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
			return -1;
		}
		
		return Math.max(left, right) + 1;
 	}

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:
}
