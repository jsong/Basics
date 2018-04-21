package com.general.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.PriorityQueue;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solution sl = new Solution();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		// root.left.left = new TreeNode(4);
		// root.left.right = new TreeNode(0);
		// root.right.left = new TreeNode(1);
		// root.right.right = new TreeNode(7);

		sl.verticalOrder(root);
		String encodedTree = sl.serialize(root);
		TreeNode decodeTreeNode = sl.deserialize(encodedTree);
		// app for validBST
		boolean valid = sl.isValidBST(root);
		// app for construct binary tree from string.
		// String tree = "4(2(3)(1))(6(5))";
		String tree = "-4(2(3)(1))(6(5))";
		String tree2 = "123(456)";
		TreeNode node = sl.str2tree(tree);
		System.out.println(encodedTree);
		sl.stackQueueCompare();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		// new ArrayList<String>("hot","dot","dog","lot","log","cog");
		int length = sl.ladderLength(beginWord, endWord, wordList);
		System.out.println("Length: " + length);
		
		String[] strings = {"z", "x", "z"};
			//{"za","zb","ca","cb"}; 
			//{"wrt","wrf","er","ett","rftt"};
		String res = sl.alienOrder(strings);
		System.out.println("alien:" + res);
	}

	public void stackQueueCompare() {
		Stack<Integer> stack = new Stack();
		Queue<Integer> queue = new LinkedList();
		int[] nums = new int[] { 1, 2, 3 };
		for (int i = 0; i < nums.length; i++) {
			stack.push(nums[i]);
			queue.add(nums[i]);
		}
		while (!stack.isEmpty()) {
			System.out.println("stack:" + stack.pop());
		}

		System.out.println("===================");

		while (!queue.isEmpty()) {
			System.out.println("queue:" + queue.poll());
		}
	}

	// 269. Alien Dictionary
	// Company: Google Facebook Twitter SnapChat.
	// Description: According to the alien sequence in dictionary derive or infer
	// whether input
	// is valid, if not just return "", otherwise return any words should be fine.
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}

		// construct graph
		HashMap<Character, HashSet<Character>> graph = constructGraph(words);

		// construct indegree
		HashMap<Character, Integer> indegree = indegree(graph);

		// topological sort
		String res = topSort(graph, indegree);
		return res;
	}

	private HashMap<Character, HashSet<Character>> constructGraph(String[] words) {
		HashMap<Character, HashSet<Character>> graph = new HashMap<>();

		// find all nodes.
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				Character c = words[i].charAt(j);
				if (!graph.containsKey(c)) {
					graph.put(c, new HashSet<Character>());
				}
			}
		}

		// connect all edges
		for (int i = 0; i < words.length - 1; i++) {
			int length = Math.min(words[i].length(), words[i + 1].length());
			for (int j = 0; j < length; j++) {
				Character c1 = words[i].charAt(j);
				Character c2 = words[i + 1].charAt(j);
				// here will have issue, like za -> zb, ca -> cb. The a -> b order 
				// is hard to tell.
				// Solution if it has been added, then just break;
				if (c1 != c2) {
//					if (!graph.get(c2).contains(c1)) {
						graph.get(c1).add(c2);
						break;
//					}
				}
			}
		}

		return graph;
	}

	private HashMap<Character, Integer> indegree(HashMap<Character, HashSet<Character>> graph) {
		HashMap<Character, Integer> indegree = new HashMap<>();
		Set<Character> keys = graph.keySet();
		for (Character c : keys) {
			HashSet<Character> set = graph.get(c);
			if (!indegree.containsKey(c)) {
				indegree.put(c, 1);
			} else {
				indegree.put(c, indegree.get(c) + 1);
			}

			for (Character s : set) {
				if (indegree.containsKey(s)) {
					indegree.put(s, indegree.get(s) + 1);
				} else {
					indegree.put(s, 1);
				}
			}
		}

		return indegree;
	}

	private String topSort(HashMap<Character, HashSet<Character>> graph, HashMap<Character, Integer> indegree) {
		StringBuilder builder = new StringBuilder();
		Queue<Character> queue = new PriorityQueue<>();

		for (Character c : indegree.keySet()) {
			if (indegree.get(c) == 1) {
				queue.offer(c);
			}
		}

		while (!queue.isEmpty()) {
			Character c = queue.poll();
			builder.append(c);
			HashSet<Character> neighbors = graph.get(c);
			for (Character s: neighbors) {
				if (indegree.containsKey(s)) {
					indegree.put(s, indegree.get(s) - 1);
					
					if (indegree.get(s) == 1) {
						queue.offer(s);
					}
				}
			}
		}
		System.out.println("builder:" + builder.length() + "\nindegree:" + indegree.size());
		if (builder.length() != indegree.size()) {
			return "";
		}
		
		return builder.toString();
	}

	// 127 Topological sorting. LintCode
	// Company: basic concept.
	// Description: Return ArrayList which contains at least one topological order
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		// write your code here
		ArrayList<DirectedGraphNode> res = new ArrayList<>();
		if (graph == null) {
			return res;
		}

		// find all nodes with indegree.
		Map<DirectedGraphNode, Integer> indegree = getNodes(graph);

		// getStartNodes with 0 indegree as starters.
		ArrayList<DirectedGraphNode> list = getStarterNodes(graph, indegree);
		// bfs
		Queue<DirectedGraphNode> queue = new LinkedList<>();
		for (DirectedGraphNode node : list) {
			queue.offer(node);
		}

		while (!queue.isEmpty()) {
			DirectedGraphNode cur = queue.poll();
			res.add(cur);
			for (DirectedGraphNode neighbor : cur.neighbors) {
				indegree.put(neighbor, indegree.get(neighbor) - 1);
				if (indegree.get(neighbor) == 0) {
					queue.offer(neighbor);
				}
			}
		}

		return res;
	}

	//
	private ArrayList<DirectedGraphNode> getStarterNodes(ArrayList<DirectedGraphNode> graph,
			Map<DirectedGraphNode, Integer> indegree) {
		ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
		for (DirectedGraphNode node : graph) {
			if (indegree.get(node) == 0) {
				list.add(node);
			}
		}

		return list;
	}

	// TODO: why graph is presented as ArrayList?
	private HashMap getNodes(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();
		for (DirectedGraphNode node : graph) {
			if (!indegree.containsKey(node)) {
				indegree.put(node, 0);
			}

			for (DirectedGraphNode cur : node.neighbors) {
				if (!indegree.containsKey(cur)) {
					indegree.put(cur, 1);
				} else {
					indegree.put(cur, indegree.get(cur) + 1);
				}
			}
		}

		return indegree;
	}

	// 200. Number of Islands
	// Company: Facebook Google Amazon
	// Description:
	// Solution: 1. DFS recursion 2. Non - Recursion

	// Class for storing the point.
	class Coordinate {
		int x, y;

		public Coordinate(int i, int j) {
			x = i;
			y = j;
		}
	}

	public int numsIslands2(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int islands = 0;
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					bfs(grid, i, j);
					islands++;
				}
			}
		}

		return islands;
	}

	private void bfs(char[][] grid, int x, int y) {
		int[] deltaX = { -1, 0, 1, 0 };
		int[] deltaY = { 0, -1, 0, 1 };

		// bfs
		Queue<Coordinate> queue = new LinkedList<>();
		Coordinate coor = new Coordinate(x, y);
		queue.offer(coor);

		while (!queue.isEmpty()) {
			Coordinate coordinate = queue.poll();
			for (int i = 0; i < 4; i++) {
				Coordinate newCoor = new Coordinate(coordinate.x + deltaX[i], coordinate.y + deltaY[i]);

				if (!inBound(grid, newCoor.x, newCoor.y)) {
					continue;
				}

				if (grid[newCoor.x][newCoor.y] == '1') {
					grid[newCoor.x][newCoor.y] = '0';
					queue.offer(newCoor);
				}
			}
		}
	}

	private boolean inBound(char[][] grid, int x, int y) {
		int m = grid.length;
		int n = grid[0].length;

		return x >= 0 && x < m && y >= 0 && y < n;
	}

	private int m; // row
	private int n; // column

	public int numIslands(char[][] grid) {
		int res = 0;
		m = grid.length;
		if (m == 0) {
			return res;
		}

		n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					dfsIslands(grid, i, j);
					res++;
				}
			}
		}
		return res;
	}

	private void dfsIslands(char[][] grid, int i, int j) {
		if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0')
			return;
		// mark all surrounding area as '0';
		grid[i][j] = '0';
		dfsIslands(grid, i, j + 1);
		dfsIslands(grid, i, j - 1);
		dfsIslands(grid, i + 1, j);
		dfsIslands(grid, i - 1, j);
	}

	// 207. Course Schedule
	// Company: Uber Apple Yelp
	// Description:
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int res = numCourses;
		int[] indegree = new int[numCourses];
		// pair like [1,0] 0 -> 1
		for (int[] pair : prerequisites) {
			indegree[pair[0]]++;
		}

		// Prepare all the node with indegree 0;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		// BFS
		while (!queue.isEmpty()) {
			int pre = queue.poll();
			res--;
			for (int[] pair : prerequisites) {
				//
				if (pair[1] == pre) {
					indegree[pair[0]]--;
					if (indegree[pair[0]] == 0) {
						queue.offer(indegree[pair[0]]);
					}
				}
			}
		}

		return res == 0;
	}

	// 127. Word Ladder
	// Company: Facebook Google
	// Description:
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> set = new HashSet<>(wordList);
		if (set.contains(beginWord)) {
			set.remove(beginWord);
		}

		Queue<String> queue = new LinkedList<String>();
		// word and level
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		queue.offer(beginWord);
		map.put(beginWord, 1);
		while (!queue.isEmpty()) {
			String curWord = queue.poll();
			Integer curlevel = map.get(curWord);
			for (int i = 0; i < curWord.length(); i++) {
				for (char j = 'a'; j <= 'z'; j++) {
					char[] curChar = curWord.toCharArray();
					curChar[i] = j;
					String temp = new String(curChar);
					if (set.contains(temp)) {
						if (temp.equals(endWord)) {
							return curlevel + 1;
						}
						map.put(temp, curlevel + 1);
						queue.offer(temp);
						set.remove(temp);
					}
				}
			}
		}

		return 0;
	}

	// 133. Clone Graph
	// Company: Facebook Google
	// Description:
	// 1 Solution is recursion, 2 Solution is BFS.
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}

		HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		// find all nodes;
		ArrayList<UndirectedGraphNode> nodes = getNodes(node);

		// mapping old nodes to new nodes;
		for (UndirectedGraphNode cur : nodes) {
			UndirectedGraphNode dupNode = new UndirectedGraphNode(cur.label);
			mapping.put(cur, dupNode);
		}

		// connect new nodes relationship based on old nodes.
		for (UndirectedGraphNode cur : nodes) {
			UndirectedGraphNode curDup = mapping.get(cur);
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				UndirectedGraphNode dupNeighbor = mapping.get(neighbor);
				curDup.neighbors.add(dupNeighbor);
			}
		}

		return mapping.get(node);
	}

	private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode root) {
		// ArrayList<UndirectedGraphNode> res = new ArrayList<UndirectedGraphNode>();

		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> hash = new HashSet<UndirectedGraphNode>();
		// res.add(root);

		queue.offer(root);
		hash.add(root);
		// BFS to get all the nodes.
		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			for (UndirectedGraphNode node : cur.neighbors) {
				if (!hash.contains(node)) {
					hash.add(node);
					queue.offer(node);
					// res.add(node);
				}
			}
		}

		return new ArrayList<UndirectedGraphNode>(hash);
	}

	private HashMap<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

		return cloneGraphHelper(node);
	}

	private UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}

		if (nodeMap.containsKey(node)) {
			return nodeMap.get(node);
		}
		UndirectedGraphNode dup = new UndirectedGraphNode(node.label);
		// clone old node's label into new one.
		nodeMap.put(node, dup);

		// find all neighbors and connect them.
		for (UndirectedGraphNode n : node.neighbors) {
			UndirectedGraphNode clonedNode = cloneGraphHelper(n);
			dup.neighbors.add(clonedNode);
		}

		return dup;
	}

	// 145. Binary Tree Postorder Traversal
	// Description: left -> right -> root
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		postorderHelper(root, res);

		return res;
	}

	private void postorderHelper(TreeNode node, List<Integer> res) {
		if (node == null)
			return;
		postorderHelper(node.left, res);
		postorderHelper(node.right, res);
		res.add(node.val);
	}

	// 783. Minimum Distance Between BST Nodes
	// Description: return minimum distance between any of two nodes.
	// Company: Google
	private Integer pre;
	private Integer minVal;

	public int minDiffInBST(TreeNode root) {
		minVal = Integer.MAX_VALUE;
		minDiffHelper(root);
		return minVal;
	}

	private void minDiffHelper(TreeNode root) {
		if (root == null) {
			return;
		}

		minDiffHelper(root.left);
		if (pre != null) {
			minVal = Math.min(minVal, root.val - pre);
		}
		pre = root.val;
		minDiffHelper(root.right);
	}

	// 114. Flatten Binary Tree to Linked List
	// Description:
	// Company: Microsoft. TODO:

	private TreeNode prev; // record previous tree node;

	public void flatten(TreeNode root) {
		if (root == null)
			return;
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}

	// 285. Inorder Successor in BST
	// Description: Inorder successor is the sequence which tree has been traversal.
	// Company: Facebook
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		TreeNode res = null;
		while (root != null) {
			if (root.val <= p.val) {
				root = root.right;
			} else {
				res = root;
				root = root.left;
			}
		}

		return res;
	}

	// 94. Binary Tree Inorder Traversal
	// Description: inorder left -> root -> right
	// Company: Microsoft
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		inorderHelper(root, res);
		return res;
	}

	private void inorderHelper(TreeNode node, List<Integer> list) {
		if (node == null)
			return;
		inorderHelper(node.left, list);
		list.add(node.val);
		inorderHelper(node.right, list);
	}

	// non-recursion
	public List<Integer> inorderTraveral2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}

		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;

		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			res.add(cur.val);
			cur = cur.right;
		}
		return res;
	}

	// 144. Binary Tree Preorder Traversal
	// Description, preorder: root -> left -> right
	// Company:
	// Recursion and normal
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		Stack<TreeNode> stack = new Stack();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}

		return res;
	}

	// recursion solution
	public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		preoderHelper(res, root);
		return res;
	}

	public void preoderHelper(List<Integer> list, TreeNode node) {
		if (node == null)
			return;
		list.add(node.val);
		preoderHelper(list, node.left);
		preoderHelper(list, node.right);

	}

	// 111. Minimum Depth of Binary Tree
	// Description: The minimum depth is the number of nodes along the shortest path
	// from the root node down to the nearest leaf node.
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null || root.right == null) {
			return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
		}

		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}

	// 104. Maximum Depth of Binary Tree
	// Company: Uber Linkedin Apple
	// Description:
	// Solution 1. normal. Solution 2. recursion.
	public int maxDepth(TreeNode root) {
		int counter = 0;
		if (root == null)
			return counter;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			++counter;
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
		}

		return counter;
	}

	public int maxDepth2(TreeNode root) {
		if (root == null)
			return 0;
		int l = maxDepth2(root.left) + 1;
		int r = maxDepth2(root.right) + 1;
		return Math.max(l, r);
	}

	// 226. Invert Binary Tree
	// Company: Google
	// Description:
	// Solution 1. Solution 2 is a recursive.
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return null;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				TreeNode temp = cur.left;
				cur.left = cur.right;
				cur.right = temp;
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
		}

		return root;
	}

	public TreeNode invertTree2(TreeNode root) {
		if (root == null)
			return root;
		TreeNode left = invertTree2(root.left);
		TreeNode right = invertTree2(root.right);
		root.left = right;
		root.right = left;
		return root;
	}

	// 103. Binary Tree Zigzag Level Order Traversal
	// Company: Microsoft LinkedIn
	// Description:
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<List<Integer>> res = new ArrayList();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		boolean reverse = false;

		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (reverse) {
					list.add(0, cur.val);
				} else {
					list.add(cur.val);
				}
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			res.add(list);
			reverse = reverse ? false : true;
		}

		return res;
	}

	// 606. Construct String from Binary Tree
	// Company: Amazon.
	// Description: Construct String from Binary Tree.
	// Complexity:
	public String tree2str(TreeNode t) {
		if (t == null)
			return "";
		String res = t.val + "";
		String left = tree2str(t.left);
		String right = tree2str(t.right);
		if (left == "" && right == "")
			return res;
		if (left == "")
			return res + "()" + "(" + right + ")";
		if (right == "")
			// no need to keep the right empty (), just leave the () on the left node.
			return res + "(" + left + ")";
		// + "()";
		return res + "(" + left + ")" + "(" + right + ")";
	}

	// 536. Construct Binary Tree from String
	// Company: Amazon.
	// Description: Construct a Tree from string.
	// Complexity:
	public TreeNode str2tree(String s) {
		Stack<TreeNode> stack = new Stack<>();
		for (int i = 0, j = i; i < s.length(); i++, j = i) {
			char c = s.charAt(i);
			if (c == ')')
				stack.pop();
			else if (c >= '0' && c <= '9' || c == '-') {
				// what's this logic been used for?
				// check -4 case, whether sign is followed by a number digit.
				while (i + 1 < s.length()) {
					char n = s.charAt(i + 1);
					if (n <= '9' && n >= '0') {
						i++;
					} else {
						break;
					}
					// System.out.println("integer:" + i + "char:" + n);
				}

				TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
				if (!stack.isEmpty()) {
					TreeNode parent = stack.peek();
					if (parent.left != null)
						parent.right = currentNode;
					else
						parent.left = currentNode;
				}
				stack.push(currentNode);
			}
		}
		return stack.isEmpty() ? null : stack.peek();
	}

	// 98. Validate Binary Search Tree
	// Company: Facebook, Amazon
	// Description validate whether a tree is a BST.
	// BST concept, cur node should be larger than any's of its' left child nodes'
	// value
	// and should be smaller than its' right child nodes' value.
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}

		return validBST(root, null, null);
	}

	private boolean validBST(TreeNode node, Integer min, Integer max) {
		if (node == null)
			return true;
		if (min != null && node.val <= min)
			return false;
		if (max != null && node.val >= max)
			return false;
		return validBST(node.left, min, node.val) && validBST(node.right, node.val, max);
	}

	// 297. Serialize and Deserialize Binary Tree
	// Company: Facebook, Amazon, LinkedIn
	// Description: serialize the TreeNode into String and then deserialize the data
	// into TreeNode

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null)
			return "";
		StringBuilder sb = new StringBuilder();
		// Check the Data Structure book on implementation of using
		// LinkedList to implement the queue.
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur == null) {
				sb.append("null ");
				continue;
			}
			sb.append(cur.val + " ");
			queue.offer(cur.left);
			queue.offer(cur.right);
		}

		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == "")
			return null;
		String[] arr = data.split(" ");
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
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

	// 103. Binary Tree Zigzag Level Order Traversal

	// 314. Binary Tree Vertical Order Traversal
	// Company: Facebook SnapChat, Google.
	// Description:
	//
	private int min = 0;
	private int max = 0;

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null)
			return res;
		helper(root, 0);
		for (int i = min; i <= max; i++) {
			res.add(new ArrayList());
		}

		Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
		Queue<Integer> idxQueue = new LinkedList<Integer>();
		queueNode.offer(root);
		idxQueue.offer(-min);
		while (!queueNode.isEmpty()) {
			TreeNode node = queueNode.poll();
			int idx = idxQueue.poll();
			res.get(idx).add(node.val);
			if (node.left != null) {
				queueNode.offer(node.left);
				idxQueue.offer(idx - 1);
			}

			if (node.right != null) {
				queueNode.offer(node.right);
				idxQueue.offer(idx + 1);
			}
		}

		return res;
	}

	// DFS helper method to get the most left index and most right index;
	//
	private void helper(TreeNode node, int idx) {
		if (node == null)
			return;
		min = Math.min(min, idx);
		max = Math.max(max, idx);
		helper(node.left, idx - 1);
		helper(node.right, idx + 1);
	}

	// 107. Binary Tree Level Order Traversal II
	// Description: return the bottom up level traversal order.
	// Company: non
	// Still use BFS, however when add the level result, just insert it index 0;
	//
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List result = new ArrayList();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			ArrayList level = new ArrayList<Integer>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode head = queue.poll();
				level.add(head.val);
				if (head.left != null) {
					queue.offer(head.left);
				}
				if (head.right != null) {
					queue.offer(head.right);
				}
			}
			result.add(0, level);
		}

		return result;
	}

	// 637. Average of Levels in Binary Tree
	// Description: Calculate the average value of the levels'.
	// Company: Facebook
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<Double>();
		if (root == null)
			return res;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			double sum = 0; // sum should NOT be INT, otherwise it will overflow.
			for (int i = 0; i < size; i++) {
				TreeNode head = queue.poll();
				sum += head.val;
				if (head.left != null) {
					queue.offer(head.left);
				}
				if (head.right != null) {
					queue.offer(head.right);
				}
			}
			Double doubleRes = new Double(sum / size);
			res.add(doubleRes);
		}

		return res;
	}

	// Problem: 102. Binary Tree Level Order Traversal
	// Company: Facebook, Apple
	// BFS
	public List<List<Integer>> levelOrder(TreeNode root) {
		List result = new ArrayList();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			ArrayList level = new ArrayList<Integer>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode head = queue.poll();
				level.add(head.val);
				if (head.left != null) {
					queue.offer(head.left);
				}
				if (head.right != null) {
					queue.offer(head.right);
				}
			}
			result.add(level);
		}

		return result;
	}
}
