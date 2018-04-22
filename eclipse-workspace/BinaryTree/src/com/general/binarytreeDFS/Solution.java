package com.general.binarytreeDFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.general.binarytree.TreeNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 108. Convert Sorted Array to Binary Search Tree
	// Company: airbnb
	// Description:
	public TreeNode sortedArrayToBST(int[] nums) {

	}

	// 250. Count Univalue Subtrees
	// Company: N/A
	// Description: count how much subnodes has the same value.
	// find the subtree and do the count
	int count;

	public int countUnivalSubtrees(TreeNode root) {
		count = 0;
		uniHelper(root);
		return count;
	}

	private boolean uniHelper(TreeNode node) {
		if (node == null) {
			return true;
		}
		boolean left = uniHelper(node.left);
		boolean right = uniHelper(node.right);

		if (left && right) {
			if (node.left != null && node.left.val != node.val) {
				return false;
			}

			if (node.right != null && node.right.val != node.val) {
				return false;
			}
			count++;
			return true;
		}

		return false;
	}

	// 236. Lowest Common Ancestor of a Binary Tree
	// Company: Facebook, Amazon, LinkedIn, Apple
	// Description: Given a binary tree, find the lowest common ancestor (LCA) of
	// two given nodes in the tree.
	// Solution Recursion, find the leaf first,
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		return left == null ? right : left;
	}

	// 235. Lowest Common Ancestor of a Binary Search Tree
	// Company: Facebook Amazon Twitter Microsoft
	// Description: Given a BST find the common parent node. Common node can be
	// itself.
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		// lowest ancestor on the left.
		if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor2(root.left, p, q);
		} else if (root.val < p.val && root.val < q.val) { // lowest ancestor on the right.
			return lowestCommonAncestor2(root.right, p, q);
		} else {
			return root;
		}
		// else if (root.val > p.val && root.val < q.val || root.val < p.val && root.val
		// > q.val) {
		// return root;
		// } else {
		// return null;
		// }
	}

	// 112. Path Sum
	// Company: Microsoft
	// Description: Find whether there are path from root to leaf which can sum up
	// to given value.
	// Solution 1. Recursion 2. Iteration using Stack
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		// reach the leaf of node.
		if (root.left == null && root.right == null) {
			return sum == root.val;
		}

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public boolean hasPathSum2(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			// reach the leaf.
			if (cur.left == null && cur.right == null) {
				if (cur.val == sum) {
					return true;
				}
			}

			if (cur.right != null) {
				stack.push(cur.right);
				cur.right.val += cur.val;
			}

			if (cur.left != null) {
				stack.push(cur.left);
				cur.left.val += cur.val;
			}
		}

		return false;
	}

	// 113. Path Sum II
	// Company: Bloomberg
	// Description: find all root-to-leaf paths where each path's sum equals the
	// given sum.
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		pathSumHelper(root, sum, res, new ArrayList<Integer>());

		return res;
	}

	private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				res.add(new ArrayList<Integer>(list));
			}
		}

		if (root.left != null) {
			pathSumHelper(root.left, sum - root.val, res, list);
		}

		if (root.right != null) {
			pathSumHelper(root.right, sum - root.val, res, list);
		}

		list.remove(res.size() - 1);
	}

	// 257. Binary Tree Paths.
	// Company: Google Facebook Apple
	// Description: Return all the paths which contains
	// root-to-leaf paths
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<String>();

		if (root == null) {
			return res;
		}

		pathHelper(root, String.valueOf(root.val), res);

		return res;
	}

	private void pathHelper(TreeNode root, String path, List<String> res) {
		if (root == null) {
			return;
		}

		// make sure it's the end of leaf.
		if (root.right == null && root.left == null) {
			res.add(path);
			return;
		}

		if (root.left != null) {
			pathHelper(root.left, path + "->" + root.left.val, res);
		}

		if (root.right != null) {
			pathHelper(root.right, path + "->" + root.right.val, res);
		}

	}

	private TreeNode subtree = null;
	private int subTreeSum = Integer.MAX_VALUE;

	// LintCode warm up.
	// Problem: 596 minimum subtree.
	// Description: Find out the minimum value for the subtrees.
	public TreeNode findSubtree(TreeNode root) {
		helper(root);
		return subtree;
	}

	private int helper(TreeNode root) {
		if (root == null)
			return 0;
		int sum = helper(root.left) + helper(root.right) + root.val;

		if (sum < subTreeSum) {
			subTreeSum = sum;
			subtree = root;
		}

		return sum;
	}
}
