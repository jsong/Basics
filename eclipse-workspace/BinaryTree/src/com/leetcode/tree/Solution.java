package com.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
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

		List<TreeNode> lists = sl.generateTrees(5);
		int[] nums = {1, 3, 5, 7, 9, 11};
		SegmentTreeNode seg = sl.buildTree(nums, 0, 6);
		System.out.println(seg.sum);
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
	// which the depth of the two subtrees of every node never differ by more than
	// 1.
	// NOTE: Recursive concept, so original solution won't work.
	// Solution: 1. Recursive solution, check left and right, return the depth + 1;
	// 2.
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

	// 114. Flatten Binary Tree to Linked List
	// Company: Facebook # Bloomberg Apple Nutanix Amazon Microsoft
	// Description: Given a binary tree, flatten it to a linked list in-place.
	// Solution: 1. Iterative solution. 2. Recursion. Draw
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();

			if (cur.right != null) {
				stack.push(cur.right);
			}

			if (cur.left != null) {
				stack.push(cur.left);
			}

			if (!stack.empty()) {
				TreeNode top = stack.peek();
				cur.right = top;
			}

			cur.left = null;
		}
	}

	TreeNode previous = null;

	public void flatten2(TreeNode root) {
		if (root == null) {
			return;
		}
		flatten2(root.right);
		flatten2(root.left);

		root.right = previous;
		previous = root;
		root.left = null;
	}

	// 117. Populating Next Right Pointers in Each Node II
	// Company: Microsoft Amazon # Bloomberg Facebook
	// Description: Given a binary tree. Populate each next pointer to point to its
	// next right node. If there is no next right node, the next pointer should be
	// set to NULL.
	// Solution: 1. BFS, keep the previous pointer, each time move the pointer, and
	// connect it.
	TreeLinkNode pre = null;

	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeLinkNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeLinkNode node = queue.poll();
				if (pre == null) {
					pre = node;
				} else {
					pre.next = node;
					pre = node; // pre should move to node.
				}

				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			pre = null; // reaches next level.
		}
	}

	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

	// 105. Construct Binary Tree from Preorder and Inorder Traversal
	// Company: Bloomberg Facebook Microsoft # Amazon Google
	// Description: Given preorder and inorder traversal of a tree, construct the
	// binary tree.
	// Solution: Recursion build the tree. Preorder: root -> left -> right, Inorder:
	// left -> root -> right; Make sure get the correct index.
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		return generateNode(preorder, 0, preorder.length, inorder, 0, inorder.length);
	}

	private TreeNode generateNode(int[] preorder, int begin1, int end1, int[] inorder, int begin2, int end2) {
		if (begin1 == end1) {
			return null;
		}

		if (begin2 == end2) {
			return null;
		}

		int rootValue = preorder[begin1];
		TreeNode root = new TreeNode(rootValue);
		int rootIndex = findRoot(begin2, end2, rootValue, inorder);
		int leftsize = rootIndex - begin2;

		root.left = generateNode(preorder, begin1 + 1, begin1 + leftsize + 1, inorder, begin2, begin2 + leftsize);
		root.right = generateNode(preorder, begin1 + leftsize + 1, end1, inorder, begin2 + leftsize + 1, end2);

		return root;
	}

	// inorder left | root | right
	private int findRoot(int begin2, int end2, int val, int[] inorder) {
		// no duplicates allowed.
		for (int i = begin2; i < end2; i++) {
			if (inorder[i] == val) {
				return i;
			}
		}

		return -1;
	}

	// 106. Construct Binary Tree from Inorder and Postorder Traversal
	// Company: Microsoft # Facebook Amazon
	// Description: Given inorder and postorder traversal of a tree, construct the
	// binary tree.
	// Solution: Same idea as 105.
	public TreeNode buildTree2(int[] inorder, int[] postorder) {
		return constructTree(0, inorder.length, inorder, 0, postorder.length, postorder);
	}

	private TreeNode constructTree(int begin1, int end1, int[] inorder, int begin2, int end2, int[] postorder) {
		if (begin1 == end1) {
			return null;
		}

		if (begin2 == end2) {
			return null;
		}

		int rootVal = postorder[end2 - 1];
		TreeNode root = new TreeNode(rootVal);

		int rootPos = findRoot(begin1, end1, rootVal, inorder);
		int leftsize = rootPos - begin1;

		root.left = constructTree(begin1, begin1 + leftsize, inorder, begin2, begin2 + leftsize, postorder);
		root.right = constructTree(begin1 + leftsize + 1, end1, inorder, begin2 + leftsize, end2 - 1, postorder);

		return root;
	}

	// 96. Unique Binary Search Trees
	// Company: Amazon # Google Adobe VMWare Snapchat Yahoo
	// Description: Given n, how many structurally unique BST's (binary search
	// trees) that store values 1 ... n?
	// Solution: DP solution, f(i) means [1, i] how many unique binary search tree
	// could be generated.
	// for eg, f[3] = f[1 as root] + f[2 as root] + f[3 as root]; when i as root,
	// then left tree [1, i- 1]; right tree [i + 1, n];
	// left * right = nums of possible solutions.
	public int numTrees(int n) {
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 1;

		for (int i = 2; i <= n; i++) {
			for (int k = 1; k <= i; k++) {
				f[i] += f[k - 1] * f[i - k];
			}
		}

		return f[n];
	}

	// 95. Unique Binary Search Trees II
	// Company: Bloomberg # Google Alibaba Adobe
	// Description: Given an integer n, generate all structurally unique BST's
	// (binary search trees) that store values 1 ... n.
	// Solution: Use Recursion, left nodes starts from (start, k - 1), right nodes
	// starts from (k + 1, end);
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<>();
		}

		return generateTreeNodes(1, n);
	}

	private List<TreeNode> generateTreeNodes(int start, int end) {
		List<TreeNode> subTree = new ArrayList<>();

		if (start > end) {
			subTree.add(null);
			return subTree;
		}

		for (int k = start; k <= end; k++) {
			List<TreeNode> leftNodes = generateTreeNodes(start, k - 1);
			List<TreeNode> rightNodes = generateTreeNodes(k + 1, end);

			// for arbitrary left node, combine right nodes, left * right
			for (TreeNode i : leftNodes) {
				for (TreeNode j : rightNodes) {
					TreeNode node = new TreeNode(k);
					node.left = i;
					node.right = j;
					subTree.add(node);
				}
			}
		}

		return subTree;
	}

	// 98. Validate Binary Search Tree
	// Company: Facebook Amazon Bloomberg Microsoft Google Qualtrics TripAdvisor #
	// Capital One Apple LinkedIn Square Snapchat VMWare
	// Description: Given a binary tree, determine if it is a valid binary search
	// tree (BST).
	// Solution: Recursion, define lower and upper make sure left and right all
	// valid BST.
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, null, null);
	}

	private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
		if (node == null) {
			return true;
		}

		if (lower != null && node.val <= lower)
			return false;
		if (upper != null && node.val >= upper)
			return false;

		return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
	}

	// 108. Convert Sorted Array to Binary Search Tree
	// Company: Google Apple # VMWare Microsoft Baidu Bloomberg Facebook
	// Description: Given an array where elements are sorted in ascending order,
	// convert it to a height balanced BST.
	// Solution: Recursion find the mid element as current 'root' and find the left
	// and right nodes accordingly.
	public TreeNode sortedArrayToBST(int[] nums) {
		return generateBST(nums, 0, nums.length - 1);
	}

	private TreeNode generateBST(int[] nums, int begin, int end) {
		if (begin > end) {
			return null;
		}

		int mid = begin + (end - begin) / 2;

		TreeNode node = new TreeNode(nums[mid]);
		node.left = generateBST(nums, begin, mid - 1);
		node.right = generateBST(nums, mid + 1, end);

		return node;
	}

	// 109. Convert Sorted List to Binary Search Tree
	// Company: Google Facebook # Amazon Adobe Oracle Uber Zenefits
	// Description: Given a singly linked list where elements are sorted in
	// ascending order, convert it to a height balanced BST.
	// Solution: Runner method find the mid each time, and recursive to add left and
	// right node.

	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		if (head.next == null) {
			return new TreeNode(head.val);
		}
		ListNode mid = cutInMid(head);
		TreeNode root = new TreeNode(mid.val);
		root.left = sortedListToBST(head);
		root.right = sortedListToBST(mid.next);

		return root;
	}

	// return the mid node
	private ListNode cutInMid(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode fast = head;
		ListNode slow = head;
		ListNode pre_slow = head;

		while (fast != null && fast.next != null) {
			pre_slow = slow;
			fast = fast.next.next;
			slow = slow.next;
		}

		pre_slow.next = null; // cut in mid;
		return slow;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	// 235. Lowest Common Ancestor of a Binary Search Tree
	// Company: Microsoft Amazon Tencent LinkedIn Bloomberg # Google BlackRock
	// Facebook
	// Description: Given a binary search tree (BST), find the lowest common
	// ancestor (LCA) of two given nodes in the BST.
	// Solution: 1. Recursive solution. 2. Iterative solution.
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		if (Math.max(p.val, q.val) < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (Math.min(p.val, q.val) > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return root;
		}
	}

	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		while (root != null) {
			if (Math.max(p.val, q.val) < root.val) {
				root = root.left;
			} else if (Math.min(p.val, q.val) > root.val) {
				root = root.right;
			} else {
				return root;
			}
		}

		return null;
	}

	// 230. Kth Smallest Element in a BST
	// Company: Amazon Walmart Labs TripleByte # Facebook Microsoft Bloomberg Google
	// Description: Given a binary search tree, write a function kthSmallest to find
	// the kth smallest element in it.
	// Solution: Inorder traverse will have the ascending.
	private int kth = 0;
	private int kthValue = 0;

	public int kthSmallest(TreeNode root, int k) {
		kth = k;
		inorder2(root);
		return kthValue;
	}

	private void inorder2(TreeNode node) {
		if (node == null) {
			return;
		}

		inorder2(node.left);
		kth--;
		if (kth == 0) {
			kthValue = node.val;
		}
		inorder2(node.right);
	}

	// 111. Minimum Depth of Binary Tree
	// Company: Facebook Amazon # Google Adobe Alibaba Goldman Sachs
	// Description: Given a binary tree, find its minimum depth. The minimum depth
	// is the number of nodes along the shortest path from the root node down to the
	// nearest leaf node.
	// Solution: 1. BFS check leaf node 2. DFS check leaf node.
	public int minDepth(TreeNode root) {
		int depth = 1;
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		outerloop: while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				// if node is leaf;
				if (node.left == null && node.right == null) {
					break outerloop;
				}

				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}

			depth++;
		}

		return depth;
	}

	public int minDepth2(TreeNode root) {
		return minDepth(root, root == null);
	}

	private int minDepth(TreeNode node, boolean isLeaf) {
		if (node == null) {
			return isLeaf ? 0 : Integer.MAX_VALUE;
		}

		return 1 + Math.min(minDepth(node.left, node.right == null), minDepth(node.right, node.left == null));
	}

	// 104. Maximum Depth of Binary Tree
	// Company: LinkedIn Amazon Google # Alibaba Bloomberg Tencent Facebook
	// Description: Given a binary tree, find its maximum depth. The maximum depth
	// is the number of nodes along the longest path from the root node down to the
	// farthest leaf node.
	// Solution: 1. BFS traverse until there are no further levels. 2. DFS left
	// right recursively.
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int depth = 0;
		Queue<TreeNode> queue = new LinkedList<>();
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
			}

			depth++;
		}

		return depth;
	}

	public int maxDepth2(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return 1 + Math.max(maxDepth2(root.left), maxDepth2(root.right));
	}

	// 112. Path Sum
	// Company: Amazon # LinkedIn Google
	// Description: Given a binary tree and a sum, determine if the tree has a
	// root-to-leaf path such that adding up all the values along the path equals
	// the given sum.
	// Solution: Recursive solution, check when reach leaf, whether the sum reaches
	// 0;
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		// leaf
		if (root.left == null && root.right == null) {
			return root.val == sum;
		}

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	// 113. Path Sum II
	// Company: Amazon Facebook # Zillow Adobe Bloomberg Microsoft Yahoo
	// Description: Given a binary tree and a sum, find all root-to-leaf paths where
	// each path's sum equals the given sum.
	// Solution: Extra path to track the path, backtrack to previous one, if reachs
	// end.
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> paths = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		if (root == null) {
			return paths;
		}

		pathSumHelper(root, sum, path, paths);
		return paths;
	}

	private void pathSumHelper(TreeNode node, int sum, List<Integer> path, List<List<Integer>> paths) {
		if (node == null) {
			return;
		}
		path.add(node.val);

		if (node.left == null && node.right == null) {
			if (sum == node.val) {
				paths.add(new ArrayList<>(path));
			}
		}

		pathSumHelper(node.left, sum - node.val, path, paths);
		pathSumHelper(node.right, sum - node.val, path, paths);
		path.remove(path.size() - 1);
	}

	// 437. Path Sum III
	// Company: Facebook Quroa # Alibaba Microsoft.
	// Description: You are given a binary tree in which each node contains an integer value. Find the number of paths that sum to a given value. Not nessariliy from root to leaf.
	// Solution: 1. Brutal force, 2. Use HashMap to store the previous count;
	public int pathSumIII(TreeNode root, int sum) {
			HashMap<Integer, Integer> map = new HashMap<>();
			map.put(0, 1); // make sure the root->leaf also count;
			return pathSum3(root, sum, 0, map);
	}
	
	private int pathSum3(TreeNode root, int curSum, int sum, HashMap<Integer, Integer> map) {
			if (root == null) {
				return 0;
			}

			curSum += root.val;
			int res = 0;
			if (map.containsKey(curSum - sum)) {
					res = map.get(curSum - sum);
			}

			if (!map.containsKey(curSum)) {
				map.put(curSum, 1);
			} else {
				map.put(curSum, map.get(curSum) + 1);
			}

			res += pathSum3(root.left, curSum, sum, map) + pathSum3(root.right, curSum, sum, map);
			map.put(curSum, map.get(curSum) - 1);
			return res;
	}

	// 124. Binary Tree Maximum Path Sum
	// Company: Facebook Google Microsoft Amazon # Pocket Gems Intuit Alibaba
	// Bloomberg
	// Description: Given a non-empty binary tree, find the maximum path sum. For
	// this problem, a path is defined as any sequence of nodes from some starting
	// node to any node in the tree along the parent-child connections. The path
	// must contain at least one node and does not need to go through the root.
	// Solution: Recursion, calculate left / right use global max_value to track the
	// current
	// maximum value.
	private int max_value = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		dfs(root);
		return max_value;
	}

	private int dfs(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int l = dfs(node.left);
		int r = dfs(node.right);
		int sum = node.val;
		if (l > 0) {
			sum += l;
		}

		if (r > 0) {
			sum += r;
		}

		max_value = Math.max(max_value, sum);

		return Math.max(l, r) > 0 ? Math.max(l, r) + node.val : node.val;
	}

	// 116. Populating Next Right Pointers in Each Node
	// Company: Google Apple # Bloomberg Microsoft Facebook.
	// Description: Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL. Initially, all next pointers are set to NULL.
	// Solution: Constant space. Use next for next level of root, pre to record previous node.

	public void connect2(TreeLinkNode root) {
		TreeLinkNode next = null;
		TreeLinkNode pre = null;

		while (root != null) {
			next = null;
			pre = null;
			for (; root != null; root = root.next) {
				if (next == null) {
					next = root.left != null ? root.left: root.right;
				}

				if (root.left != null) {
					if (pre != null) {
						pre.next = root.left;
					}

					pre = root.left;
				}

				if (root.right != null) {
					if (pre != null) {
						pre.next = root.right;
					}

					pre = root.right;
				}
			}

			root = next; 	// next level;
		}
	}

	// 129. Sum Root to Leaf Numbers
	// Company: Facebook # Amazon
	// Description: Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. An example is the root-to-leaf path 1->2->3 which represents the number 123. Find the total sum of all root-to-leaf numbers.
	// Solution: 1. Like the 113. Path Sum II, record each path, use dfs to traverse all paths from root -> leaf.
	// 2. DFS, start from root, sums up left and right, if meet leaf node, then add value and multiply 10.
	public int sumNumbers2(TreeNode root) {
		return sumhelper(root, 0);
	}

	private int sumhelper(TreeNode node, int sum) {
		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null) {
			return node.val + sum * 10;
		}

		return sumhelper(node.left, sum * 10 + node.val) + sumhelper(node.right, sum * 10 + node.val);
	}

	public int sumNumbers(TreeNode root) {
		List<List<Integer>> paths = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		dfsPath(root, paths, path);

		int sum = 0;
		for (List<Integer> list: paths) {
			// root to leaf;
			int i = 0;
			for (int num: list) {
				i = i * 10 + num;
			}

			sum += i;
		}

		return sum;
    }

	private void dfsPath(TreeNode node, List<List<Integer>> paths, List<Integer> path) {
		if (node == null) {
			return;
		}

		path.add(node.val);

		if (node.left == null && node.right == null) {
			paths.add(new ArrayList<>(path));
		}


		dfsPath(node.left, paths, path);
		dfsPath(node.right, paths, path);

		path.remove(path.size() - 1);
	}


	// 236. Lowest Common Ancestor of a Binary Tree
	// Company: Facebook Amazon Microsoft LinkedIn Apple Tencent Bloomberg Zillow # Google Yahoo Uber Lyft Alibaba Oracle Tableau Goldman Sachs
	// Description: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
	// Solution: Recursion find whether left and right has valid, if both has valid, then return root, otherwise return not null side.
	public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor3(root.left, p, q);
		TreeNode right = lowestCommonAncestor3(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		return left == null? right: left;
    }

	// ##### Segment tree
	// 307. Range Sum Query - Mutable
	// Company: Google # Pocket Gems Facebook
	// Description: Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive. The update(i, val) function modifies nums by updating the element at index i to val.
	// Solution: Use Segment Tree which has log(n) update and log(n) sum time.

	class NumArray {
		SegmentTreeNode root;
	    public NumArray(int[] nums) {
	        root = buildTree(nums, 0, nums.length);
	    }

	    public void update(int i, int val) {
	        updateHelper(this.root, i, val);
	    }

	    public int sumRange(int i, int j) {
	        return sumRangeHelper(this.root, i, j);
	    }
	}

	private int sumRangeHelper(SegmentTreeNode node, int begin, int end) {
		if (node == null || begin >= node.end || end <= node.begin) {
			return 0;
		}

		if (begin <= node.begin && end >= node.end) {
			return node.sum;
		}

		int mid = node.begin + (node.end - node.begin) / 2;
		return sumRangeHelper(node.left, begin, Math.min(end, mid)) + sumRangeHelper(node.right, Math.max(begin, mid), end);
	}

	private void updateHelper(SegmentTreeNode node, int i, int val) {
		// leaf node.
		if (node.begin == i && node.begin == node.end - 1) {
			node.sum = val;
			return;
		}

		int mid = node.begin + (node.end - node.begin) / 2;

		if (i > mid) {
			updateHelper(node.right, i, val);
		} else {
			updateHelper(node.left, i, val);
		}

		node.sum = node.left.sum + node.right.sum;
	}

	private SegmentTreeNode buildTree(int[] nums, int begin, int end) {
       if (nums == null || nums.length == 0 || begin >= end) {
    	   return null;
       }

       if (begin == end - 1) { // only one element, leaf node.
    	   return new SegmentTreeNode(begin, end, nums[begin]);
       }

       SegmentTreeNode root = new SegmentTreeNode(begin, end);
       int mid = begin + (end - begin) / 2;
       root.left = buildTree(nums, begin, mid);
       root.right = buildTree(nums, mid, end);
       root.sum = root.left.sum + root.right.sum;

       return root;
    }

	static class SegmentTreeNode {
        private int begin;
        private int end;
        private int sum;
        private SegmentTreeNode left;
        private SegmentTreeNode right;

        public SegmentTreeNode(int begin, int end, int sum) {
            this.begin = begin;
            this.end = end;
            this.sum = sum;
        }
        public SegmentTreeNode(int begin, int end) {
            this(begin, end, 0);
        }
    }

	// 298. Binary Tree Longest Consecutive Sequence
	// Company: Google # Facebook Amazon
	// Description: Given a binary tree, find the length of the longest consecutive sequence path.
	// The longest consecutive path need to be from parent to child (cannot be the reverse).
	// Solution: Per each node, calculate its left and right longest and compare with the global maximum.
	private int longestSeq = 0;
	public int longestConsecutive(TreeNode root) {
			consecutiveSeq(root);
			return longestSeq;
  }

	private int consecutiveSeq(TreeNode root) {
			if (root == null) {
					return 0;
			}

			int left = consecutiveSeq(root.left);
			int right = consecutiveSeq(root.right);
			int longest = 1;

			if (root.left != null && root.val == root.left.val - 1) {
					longest = Math.max(longest, left + 1);
			}

			if (root.right != null && root.val == root.right.val - 1) {
					longest = Math.max(longest, right + 1);
			}

			if (longest > longestSeq) {
		       longestSeq = longest;
		  }

			return longest;
	}


	//
	// Company:
	// Description:
	// Solution:
}
