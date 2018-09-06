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
	// Description: Given a binary tree, return the "bottom-up" level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
	// Solution: Same as 102, except when adding the res, always insert the result at "0".
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

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

}
