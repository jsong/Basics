package com.leetcode.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } };
		Solution sl = new Solution();
		sl.validTree(5, edges);

	}

	// 261. Graph Valid Tree
	// Description: Given n nodes labeled from 0 to n-1 and a list of undirected
	// edges (each edge is a pair of nodes), write a function to check whether these
	// edges make up a valid tree.
	// Company: LinkedIn Facebook Amazon # Google
	// Solution: Valid Graph must comply: 1. nodes size = edges.length - 1; 2. Visit
	// one of the starter node, we could tranverse all the other nodes. size == n;
	public boolean validTree(int n, int[][] edges) {
		// Graph 1. nodes size = edges size - 1
		// Graph 2. visted nodes should equals to n.
		if (n == 0) {
			return false;
		}

		if (n - 1 != edges.length) {
			return false;
		}

		HashMap<Integer, List<Integer>> map = buildTree(edges, n);
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> set = new HashSet<>();

		queue.offer(0);
		set.add(0);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			List<Integer> neighbours = map.get(node);
			if (neighbours == null || neighbours.isEmpty())
				continue;
			for (int v : neighbours) {
				if (set.contains(v))
					continue;
				queue.offer(v);
				set.add(v);
			}
		}

		return set.size() == n;
	}

	private HashMap<Integer, List<Integer>> buildTree(int[][] edges, int n) {
		HashMap<Integer, List<Integer>> tree = new HashMap<>();
		for (int i = 0; i < n; i++) {
			tree.put(i, new ArrayList<Integer>());
		}

		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			tree.get(u).add(v);
			tree.get(v).add(u);
		}

		return tree;
	}

	// 297. Serialize and Deserialize Binary Tree
	// Description: Serialize and Deserialize Binary Tree
	// Company: Facebook Amazon LinkedIn Google Quora Microsoft Uber # Intuit Oracle VMWare Apple Yahoo
	// Solution: BSF traverse all the nodes, append " " to seperate all the nodes. Use queue to restore the tree based on String.

	// Encodes a tree to a single string.
  public String serialize(TreeNode root) {
		StringBuffer sb = new StringBuffer();
		if (root == null) {
			return sb.toString();
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				if (node == null) {
					sb.append("null ");
					continue;
				}

				sb.append(node.val + " ");
				queue.offer(node.left);
				queue.offer(node.right);
		}

		return sb.toString();
  }

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}

		// Get all the nodes;
		String[] arr = data.split(" ");
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
		queue.offer(root);

		for (int i = 1; i < arr.length; i++) {
			TreeNode cur = queue.poll();
			if (!arr[i].equals("null")) {
				cur.left = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(cur.left);
			}

			if (!arr[++i].equals("null")) {
				cur.right = new TreeNode(Integer.parseInt(arr[i]));
				queue.offer(cur.right);
			}
		}

		return root;
	}

	// 127. Word Ladder
	// Description:
	// Company:
	// Solution:
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		return 0;
	}
}
