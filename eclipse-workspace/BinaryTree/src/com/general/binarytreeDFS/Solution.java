package com.general.binarytreeDFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.general.binarytree.TreeNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
