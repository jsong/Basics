package com.general.binarytree;

import java.util.Stack;

public class BSTIterator {
	private Stack<TreeNode> stack;
	private TreeNode cur;

	public static void main(String[] args) {

	}

	// 270. Closest Binary Search Tree Value
	// Company: Microsoft Snapchat Google
	// Description: Find the value in BST which is closet to the target. Non empty BST.
	// Solution: Iteration to find out which nodes is closet to target value. And return that node value.
	public int closestValue(TreeNode root, double target) {
		int res = root.val;
		while (root != null) {
			if (Math.abs(target - root.val) < Math.abs(target - res)) {
				res = root.val;
			}
			
			root = root.val > target ? root.left: root.right;
		}
		
		return res;
	}
	
	// 173. Binary Tree Iterator
	// Company: Facebook LinkedIn
	// Description: Use in-order for BST tree, which will output the sorted numbers.
	// Solution: Inorder traversal to find the next smallest value.

	public BSTIterator(TreeNode root) {
		cur = root;
		stack = new Stack<>();
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		if (!stack.isEmpty() || cur != null)
			return true;
		return false;
	}

	/** @return the next smallest number */
	public int next() {
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
		cur = stack.pop();
		int val = cur.val;
		cur = cur.right;
		return val;
	}

}
