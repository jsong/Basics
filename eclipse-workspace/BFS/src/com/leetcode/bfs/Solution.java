package com.leetcode.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

public static void main(String[] args) {
								// TODO Auto-generated method stub
								int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
								Solution sl = new Solution();
								sl.validTree(5, edges);

}

// 261. Graph Valid Tree
// Description: Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
// Company: LinkedIn Facebook Amazon
// Solution: Valid Graph must comply: 1. nodes size = edges.length - 1; 2. Visit one of the starter node, we could tranverse all the other nodes. size == n;
public boolean validTree(int n, int[][] edges) {
								// Graph 1. nodes size = edges size - 1
								// Graph 2. visted nodes should equals to n.
								if (n == 0) {
																return false;
								}

								if (n - 1 != edges.length) {
																return false;
								}

								HashMap<Integer, List<Integer> > map = buildTree(edges);
								Queue<Integer> queue = new LinkedList<>();
								Set<Integer> set = new HashSet<>();

								queue.offer(0);
								set.add(0);

								while (!queue.isEmpty()) {
																int node = queue.poll();
																List<Integer> neighbours = map.get(node);
																if (neighbours == null || neighbours.isEmpty()) continue;
																for (int v: neighbours) {
																								if (set.contains(v)) continue;
																								queue.offer(v);
																								set.add(v);
																}
								}

								return set.size() == n;
}

private HashMap<Integer, List<Integer> > buildTree(int[][] edges) {
								HashMap<Integer, List<Integer> > tree = new HashMap<>();
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

// 127. Word Ladder
// Description:
// Company:
// Solution:
public int ladderLength(String beginWord, String endWord, List<String> wordList) {

}
}
