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
	// Description: Given a binary tree, return the preorder traversal of its nodes' values.
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
}
