package com.leetcode.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TrieNode {
	TrieNode[] children;
	boolean isWord;
	String word;

	public TrieNode() {
		// a - z
		children = new TrieNode[26];
		isWord = false;
		word = "";
	}
}

// 211. Add and Search Word - Data structure design
// Company: Facebook
// Description:
// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad") -> false
// search("bad") -> true
// search(".ad") -> true
// search("b..") -> true
// Solution:

class WordDictionary {
	TrieNode root;

	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int cVal = c - 'a';
			if (node.children[cVal] == null) {
				node.children[cVal] = new TrieNode();
			}

			node = node.children[cVal];
		}
		node.isWord = true;
		node.word = word;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return isMatch(word, 0, root);
	}

	private boolean isMatch(String word, int index, TrieNode node) {
		// find until end;
		if (node == null) {
			return false;
		}

		if (index >= word.length()) {
			return node.isWord;
		}

		char c = word.charAt(index);
		if (c == '.') {
			// find all possible child
			for (int i = 0; i < 26; i++) {
				// return isMatch(word, index + 1, node.children[i]);
				if (isMatch(word, index + 1, node.children[i])) {
					return true;
				}
			}
		} else {
			int num = word.charAt(index) - 'a';
			if (node.children[num] == null) {
				return false;
			} else {
				return isMatch(word, index + 1, node.children[num]);
			}
		}

		return false;
	}
}

// 208. Implement Trie (Prefix Tree)
// Company: Google facebook microsoft Uber Twitter Bloomberg
// Description: Implement a trie with insert, search, and startsWith methods.
// Solution:

class Trie {

	/** Initialize your data structure here. */
	TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int cVal = c - 'a';
			if (node.children[cVal] == null) {
				node.children[cVal] = new TrieNode();
			}

			node = node.children[cVal];
		}

		node.isWord = true;
		node.word = word;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		// return isMatch(word, 0, root);
		// non recursion version.
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int cVal = c - 'a';
			if (node.children[cVal] == null) {
				return false;
			}
			node = node.children[cVal];
		}

		return node.isWord;
	}

//	private boolean isMatch(String word, int index, TrieNode node) {
//		if (node == null) {
//			return false;
//		}
//
//		if (index >= word.length()) {
//			return node.isWord;
//		}
//
//		char c = word.charAt(index);
//		int cVal = c - 'a';
//		TrieNode child = node.children[cVal];
//		if (isMatch(word, index + 1, child)) {
//			return true;
//		}
//
//		return false;
//	}

	private boolean startsWith(String word, int index, TrieNode node) {
		if (node == null) {
			return false;
		}

		if (index >= word.length()) {
			return true;
		}

		char c = word.charAt(index);
		int cVal = c - 'a';
		TrieNode child = node.children[cVal];
		if (startsWith(word, index + 1, child)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		return startsWith(prefix, 0, root);
	}
}

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] nums = { 1, 2, 3 };
		List<List<Integer>> per = sl.permute(nums);

		System.out.println("Per:" + per);

		// sl.swap(nums, 0, 1);
		// sl.reverse(nums, 0, nums.length - 1);
		sl.nextPermutation(nums);
		System.out.println(nums);

		String res = sl.getPermutation(3, 3);
		System.out.println("Kth" + res);
		int[] A = { 4, 2, 1 };
		long index = sl.permutationIndex(A);
		System.out.println("index: " + index);

		List<List<String>> queens = sl.solveNQueens(4);
		System.out.println(queens);

		List<String> list = sl.letterCombinations("23");
		System.out.println("list" + list);

		String format = sl.nextClosestTime("19:34");
		System.out.println("Format:" + format);

		boolean isMatch = sl.wordPattern("abba", "cat dog dog cat");

		boolean isMatch2 = sl.wordPatternMatch("abab", "redblueredblue");

		// "hit"
		// "cog"
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		// "red"
		// "tax"
		wordList = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
		// "leet"
		// "code"
		wordList = Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost");

		List<List<String>> ladder = sl.findLadders("leet", "code", wordList);

		System.out.println(ladder);
		// Reference, No need to put it back.
		// HashMap<String, List<String>> map = new HashMap<>();
		// map.put("key", new ArrayList<>());
		//
		// List<String> value = map.get("key");
		// value.add("value");

		WordDictionary wd = new WordDictionary();
		wd.addWord("aa");
		// wd.addWord("a");
		boolean found = wd.search("a");
		System.out.println("found:" + found);

		Trie trie = new Trie();
		trie.insert("ab");
		boolean start = trie.startsWith("a");
		System.out.println("Starts:" + start);

		// [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
		// ["oath","pea","eat","rain"]
		char[][] board = { { 'a', 'b' }, { 'c', 'd' } };
		// { {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i',
		// 'f', 'l', 'v'}};
		// String[] words = {"oath", "pea", "eat", "rain"};
		String[] words =
				// { "acdb" };
				{ "ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb" };
		List<String> searchRes = sl.findWords(board, words);
		System.out.println("Res:" + searchRes);
	}

	// 212. Word Search II
	// Company: Google Airbnb Microsoft
	// Description: Given a list of words, and a char[][] find all the words in the
	// char[][]
	// Input:
	// words = ["oath","pea","eat","rain"] and board =
	// [
	// ['o','a','a','n'],
	// ['e','t','a','e'],
	// ['i','h','k','r'],
	// ['i','f','l','v']
	// ]
	//
	// Output: ["eat","oath"]
	// Solution: 1. Build Trie first. 2. Use '#' to mark that character has been visited before.
	// 3. DFS 
	public List<String> findWords(char[][] board, String[] words) {
		// build trie for each word.
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0) {
			return res;
		}

		if (words.length == 1 && board.length == 1) {
			if (words[0].length() > board[0].length) {
				return res;
			}
		}

		TrieNode root = new TrieNode();
		buildTrie(words, root);

		// search character within the board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.println("i: j: " + i + j + board[i][j]);
				searchTrie(root, res, board, i, j);
			}
		}
		return res;
	}

	private void searchTrie(TrieNode node, List<String> list, char[][] board, int i, int j) {
		// search board within the trie node.
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
			return;
		}

		char c = board[i][j];
		int cVal = c - 'a';
		if (c == '#' || node.children[cVal] == null) {
			return;
		}

		TrieNode cNode = node.children[cVal];
		board[i][j] = '#';
		if (cNode.word != null) {
			list.add(cNode.word);
			cNode.word = null;
		}

		searchTrie(cNode, list, board, i, j + 1);
		searchTrie(cNode, list, board, i, j - 1);
		searchTrie(cNode, list, board, i + 1, j);
		searchTrie(cNode, list, board, i - 1, j);
		board[i][j] = c;
	}

	// char c = board[i][j];
	// int cVal = c - 'a';
	// if (node.children[cVal] == null) {
	// if (node.isWord) {
	// list.add(node.word);
	// return true;
	// }
	// }
	//
	// boolean res = searchTrie(node.children[cVal], list, board, i + 1, j) ||
	// searchTrie(node.children[cVal], list, board, i - 1, j) ||
	// searchTrie(node.children[cVal], list, board, i, j + 1) ||
	// searchTrie(node.children[cVal], list, board, i, j - 1);
	//
	// return res;

	private void buildTrie(String[] words, TrieNode root) {
		TrieNode node = root;
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				int cVal = c - 'a';
				if (node.children[cVal] == null) {
					node.children[cVal] = new TrieNode();
				}

				node = node.children[cVal];
			}
			node.isWord = true;
			node.word = word;
			node = root;
		}
	}


	// 79. Word Search
	// Company: Facebook Microsoft Bloomberg
	// Description:
	// board =
	// [
	// ['A','B','C','E'],
	// ['S','F','C','S'],
	// ['A','D','E','E']
	// ]
	//
	// Given word = "ABCCED", return true.
	// Given word = "SEE", return true.
	// Given word = "ABCB", return false.
	// Solution:
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (wordsearch(board, word, i, j, 0)) {
					return true;
				}
				// return wordsearch(board, word, i, j, 0);
			}
		}

		return false;
	}

	private boolean wordsearch(char[][] board, String word, int i, int j, int start) {
		if (start >= word.length()) {
			return true;
		}

		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
			return false;
		}

		if (word.charAt(start++) == board[i][j]) {
			// mark as visited;
			char c = board[i][j];
			board[i][j] = '#';
			boolean res = wordsearch(board, word, i + 1, j, start) || wordsearch(board, word, i - 1, j, start)
					|| wordsearch(board, word, i, j + 1, start) || wordsearch(board, word, i, j - 1, start);

			board[i][j] = c;
			return res;
		}

		return false;
	}

	// 126. Word Ladder II
	// Company: Amazon, Yelp
	// Description: from beginword until endword, find the shortest path to it.
	// Solution:
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		if (wordList == null || wordList.size() == 0) {
			return res;
		}

		HashSet<String> dict = new HashSet<>(wordList);
		if (!dict.contains(endWord)) {
			return res;
		}
		dict.remove(beginWord);
		dict.remove(endWord);
		// How many steps to take to the word
		HashMap<String, Integer> step = new HashMap<>();
		step.put(beginWord, 1);
		// Graph
		HashMap<String, List<String>> graph = new HashMap<>();
		boolean found = false;
		// used to construct the graph
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int curStep = 0;
		// BFS to construct the graph
		while (!queue.isEmpty() && !found) {
			curStep++;
			// String word = queue.poll();
			// if (word.equalsIgnoreCase("dog") || word.equalsIgnoreCase("log")) {
			// System.out.println(word);
			// }
			for (int size = queue.size(); size > 0; size--) {
				// char c = word.charAt(i);
				String word = queue.poll();
				// find an replace
				for (int i = 0; i < word.length(); i++) {
					char c = word.charAt(i);
					for (char j = 'a'; j <= 'z'; j++) {
						if (j == c) {
							continue;
						}
						// check whether word has changed or not.
						String newWord = new String(word.substring(0, i) + j + word.substring(i + 1));
						System.out.println("new:" + newWord);
						if (newWord.equalsIgnoreCase(endWord)) {
							List<String> parents = graph.get(newWord);
							if (parents == null) {
								parents = new ArrayList<>();
								graph.put(newWord, parents);
							}
							parents.add(word);
							found = true;
						} else {
							// transform from another word with less steps.
							// q --- t
							// p -- /
							if (step.containsKey(newWord) && curStep < step.get(newWord)) {
								List<String> parents = graph.get(newWord);
								if (parents == null) {
									parents = new ArrayList<>();
									graph.put(newWord, parents);
								}
								parents.add(word);
							}
						}

						if (!dict.contains(newWord)) {
							continue;
						}

						dict.remove(newWord);
						queue.offer(newWord);
						// append newWord's parents
						List<String> parents = graph.get(newWord);
						if (parents == null) {
							parents = new ArrayList<>();
							graph.put(newWord, parents);
						}
						parents.add(word);
						step.put(newWord, step.get(word) + 1);
					}
				}
			}
		}

		if (found) {
			// graph already been constructed.
			// DFS to construct the word from parents graph.
			List<String> cur = new ArrayList<String>();
			cur.add(endWord);
			getPath(endWord, beginWord, res, cur, graph);
		}

		return res;
	}

	private void getPath(String word, String beginWord, List<List<String>> res, List<String> cur,
			HashMap<String, List<String>> parents) {
		if (word.equalsIgnoreCase(beginWord)) {
			// need copy
			// Collections.reverse(cur);
			res.add(new ArrayList(cur));
			return;
		}

		for (String p : parents.get(word)) {
			System.out.println("adding..." + p);
			cur.add(0, p);
			getPath(p, beginWord, res, cur, parents);

			cur.remove(0);
		}
	}

	// 291. Word Pattern II
	// Company: Uber, Dropbox
	// Description: pattern = "abab", str = "redblueredblue" should return true.
	// pattern = "aaaa", str = "asdasdasdasd" should return true.
	// pattern = "aabb", str = "xyzabcxzyabc" should return false.
	// Solution:
	public boolean wordPatternMatch(String pattern, String str) {
		HashMap<Character, String> map = new HashMap<>();
		HashSet<String> set = new HashSet<>();
		boolean isMatch = isMatch(pattern, 0, str, 0, map, set);

		return isMatch;
	}

	private boolean isMatch(String p, int pIndex, String s, int sIndex, HashMap<Character, String> map,
			HashSet<String> set) {
		if (pIndex == p.length() && sIndex == s.length()) {
			return true;
		}

		// only one reaches end.
		if (pIndex == p.length() || sIndex == s.length()) {
			return false;
		}

		char c = p.charAt(pIndex);

		if (map.containsKey(c)) {
			String value = map.get(c);
			if (!s.startsWith(value, sIndex)) {
				return false;
			}
			System.out.println("p:" + (pIndex + 1) + "value: " + value + "s:" + sIndex);
			return isMatch(p, pIndex + 1, s, sIndex + value.length(), map, set);
		}

		for (int k = sIndex; k < s.length(); k++) {
			String sub = s.substring(sIndex, k + 1);
			System.out.println("sub:" + sub);
			if (set.contains(sub)) {
				continue;
			}

			map.put(c, sub);
			System.out.println("adding...: k:" + c + " v:" + sub);
			set.add(sub);
			if (isMatch(p, pIndex + 1, s, k + 1, map, set)) {
				return true;
			}
			map.remove(c);
			set.remove(sub);
		}

		return false;

	}

	// 290. Word Pattern
	// Company: Uber Dropbox
	// Description: Given a pattern, and a string. "abba", "dog cat cat dog"
	// should return true
	// Solution: Use HashMap to map string to char.
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		// no match
		if (pattern.length() != words.length) {
			return false;
		}
		HashMap<String, Character> pair = new HashMap<>();

		// for (int i = 0; i < pattern.length(); i++) {
		// if (!pair.containsKey(words[i])) {
		// // don't have that word, however has that key already.
		// // abba = > "cat dog dog fish"
		// if (pair.containsValue(pattern.charAt(i))) {
		// return false;
		// }
		//
		// pair.put(words[i], pattern.charAt(i));
		// } else {
		// Character value = pair.get(words[i]);
		// if (value == pattern.charAt(i)) {
		// continue;
		// } else {
		// return false;
		// }
		// }
		// }

		HashMap<Character, String> map = new HashMap<>();

		for (int i = 0; i < pattern.length(); i++) {
			if (!map.containsKey(pattern.charAt(i))) {
				map.put(pattern.charAt(i), words[i]);
			} else {
				String value = map.get(pattern.charAt(i));

				if (value.equalsIgnoreCase(words[i])) {
					continue;
				} else {
					return false;
				}
			}
		}

		return true;
	}

	// 681. Next Closest Time
	// Company: Google
	// Description: Given a string, 1934, return 19:39 which is 5 minutes later.
	// Solution: DFS or Brutal force.
	// DFS consideration, try to fill each digit all possible values, check whether
	// it's legal
	// if legal, then record whether it's the best value.

	private int best = Integer.MAX_VALUE;

	public String nextClosestTime(String time) {
		// convert time to nums.
		// String[] times = time.split(":"); //19:05 or 05:19
		int[] nums = new int[time.length() - 1]; // Remove ':'.
		char[] chars = time.toCharArray();
		int i = 0;
		for (char s : chars) {
			if (s == ':') {
				continue;
			}
			nums[i++] = s - '0';
		}
		int h = nums[0] * 10 + nums[1];
		int m = nums[2] * 10 + nums[3];
		// permutations of valid numbers duplicate is allowed.
		int[] cur = new int[4];
		best = hrToMinute(h, m);

		dfsNextTime(0, nums, cur, hrToMinute(h, m));

		StringBuffer sb = new StringBuffer();
		String hr = best / 60 < 10 ? "0" + best / 60 : "" + best / 60;
		String min = best % 60 < 10 ? "0" + best % 60 : "" + best % 60;
		sb.append(hr + ":" + min);

		return sb.toString();
	}

	private void dfsNextTime(int dep, int[] nums, int[] curr, int time) {
		if (dep == 4) {
			int hr = curr[0] * 10 + curr[1];
			int min = curr[2] * 10 + curr[3];
			if (hr > 23 || min > 59) {
				return;
			}

			int curr_time = hrToMinute(hr, min);
			if (timeDiff(time, curr_time) < timeDiff(time, best)) {
				best = curr_time;
			}

			return;
		}

		// make sure each digit will be used, including duplicate as well.
		for (int digit : nums) {
			curr[dep] = digit;
			// System.out.println("Dep:" + dep + "\n");
			// System.out.println("" + curr[0] + curr[1] + curr[2] + curr[3]);
			dfsNextTime(dep + 1, nums, curr, time);
		}
	}

	private int timeDiff(int t1, int t2) {
		if (t1 == t2) {
			return Integer.MAX_VALUE;
		}

		// t1 19:34, t2 19:33
		return (t2 - t1 + 24 * 60) % (24 * 60);
	}

	private int hrToMinute(int hr, int minute) {
		int res = hr * 60 + minute;

		return res;
	}
	// 17. Letter Combinations of a Phone Number
	// Company: Google Facebook Amazon
	// Description: Given a T9 mapping table, and a input number
	// Return all the possible string combinations.
	// Solution: 1. DFS, 2. use LinkedList(Queue)

	private String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();

		if (digits == null || digits.length() == 0) {
			return res;
		}

		dfsLetter(res, digits, 0, "");
		return res;
	}

	private void dfsLetter(List<String> res, String digits, int index, String string) {
		if (index == digits.length()) {
			res.add(string);
			return;
		}

		// abc or def
		String s = mapping[digits.charAt(index) - '0'];
		for (int i = 0; i < s.length(); i++) {
			dfsLetter(res, digits, index + 1, string + s.charAt(i));
		}
	}

	// 197. Permutation Index, LintCode
	// Company: N/A
	// Description: Given a int array, [1, 2, 4], find its index in the full
	// permutations.
	// In above case which is 1; NOTE: No duplicate numbers.
	// Solution: During iteration check how many numbers are smaller than me, if 2
	// which means, there are 2 full permutations ahead of me. same as follows.

	public long permutationIndex(int[] A) {
		long ans = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[j] < A[i]) {
					ans += fac(A.length - i - 1);
				}
			}
		}

		return ans + 1;
	}

	public long fac(int n) {
		long ans = 1;

		for (int i = 1; i <= n; i++) {
			ans *= (long) i;
		}

		return ans;
	}

	// 51. N-Queens
	// Company: N/A
	// Description: Queen can not be in same column, nor it can be in the same
	// diagonal.
	// Solution:
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();

		if (n == 0) {
			return res;
		}

		// queens [1,2,3,4] stores column position.
		int[] queens = new int[n];
		queenHelper(res, queens, 0);

		return res;
	}

	private void queenHelper(List<List<String>> res, int[] queens, int pos) {
		if (queens.length == pos) {
			addSolution(res, queens);
			return;
		}

		// pos is row number, i is column where we put it.
		// for eg, queens[2] = 1; means on the row 3, Q is at column 2.
		// queens[1] = 2; means on the row 2, Q is at column 3.
		for (int i = 0; i < queens.length; i++) {
			queens[pos] = i;
			if (isValid(queens, pos)) {
				queenHelper(res, queens, pos + 1);
			}
		}
	}

	private boolean isValid(int[] queens, int pos) {
		for (int i = 0; i < pos; i++) {
			if (queens[pos] == queens[i]) { // same column;
				return false;
			}

			if (Math.abs(queens[pos] - queens[i]) == Math.abs(pos - i)) {
				return false;
			}
		}

		return true;
	}

	private void addSolution(List<List<String>> res, int[] queens) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < queens.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < queens.length; j++) {
				if (queens[i] == j) {
					sb.append("Q");
				} else {
					sb.append(".");
				}
			}

			list.add(sb.toString());
		}

		res.add(list);
	}

	// 60. Permutation Sequence
	// Company: Twitter
	// Description: Given n, find all permutations, get the kth of it.
	// Solution:
	public String getPermutation(int n, int k) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			res.add(i);
		}
		// factorial
		int[] fact = new int[n];
		fact[0] = 1;

		for (int i = 1; i < n; i++) {
			fact[i] = fact[i - 1] * i;
		}

		StringBuilder sb = new StringBuilder();
		k = k - 1;
		for (int i = n; i > 0; i--) {
			int index = k / fact[i - 1];
			k = k % fact[i - 1];
			sb.append(res.get(index));
			res.remove(index);
		}

		return sb.toString();
	}

	// 31. Next Permutation
	// Company: Google
	// Description: [1,2,3] - > [1,3,2]; [3,2,1] -> [1,2,3]
	// Solution:
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int firstSmall = -1;

		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				firstSmall = i;
				break;
			}
		}
		// not found, like 3,2,1 case
		if (firstSmall == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}

		int firstLarge = -1;
		for (int i = nums.length - 1; i > firstSmall; i--) {
			if (nums[i] > nums[firstSmall]) {
				firstLarge = i;
				break;
			}
		}

		swap(nums, firstSmall, firstLarge);
		reverse(nums, firstSmall + 1, nums.length - 1);
		return;
	}

	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// Lint Code. String permutation II
	// http://www.lintcode.com/en/problem/string-permutation-ii/
	// Given a string, find all permutations of it without duplicates.
	// Example
	// Given “abb”, return [“abb”, “bab”, “bba”].
	// Given “aabb”, return [“aabb”, “abab”, “baba”, “bbaa”, “abba”, “baab”].
	// Same as Permutation II.

	// 47. Permutations II
	// Company: Microsoft, LinkedIn
	// Description: Same as 46, except don't allow duplicates.
	// Solution: Skip the duplicates previous value.
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		Arrays.sort(nums);
		dfs2(nums, res, new boolean[nums.length], new ArrayList<Integer>());
		return res;
	}

	private void dfs2(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> permutation) {
		if (permutation.size() == nums.length) {
			res.add(new ArrayList<Integer>(permutation));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			// skip the case [1, 1, 2] current index = 1.
			// since later 1 is equal to first 1, and we should skip the first.
			// it's based on the array has been sorted, all the same value is adjacent.
			if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
				continue;
			}

			permutation.add(nums[i]);
			visited[i] = true;
			dfs2(nums, res, visited, permutation);
			visited[i] = false;
			permutation.remove(permutation.size() - 1);
		}
	}

	// Shift + Ctrl + O
	// 46. Permutations
	// Company: Microsoft, LinkedIn
	// Description: Given a list of integers, return all possible sequences.
	// Permutations
	// [1, 2, 3] = > [1, 3, 2], [2, 1, 3], etc..
	// Solution: Use DFS & Backtracking. Use visited array to record which
	// index(number) been visited before.
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		dfs(nums, res, new boolean[nums.length], new ArrayList<Integer>());
		return res;
	}

	private void dfs(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> permutation) {
		if (permutation.size() == nums.length) {
			res.add(new ArrayList<Integer>(permutation));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			permutation.add(nums[i]);
			visited[i] = true;
			dfs(nums, res, visited, permutation);
			visited[i] = false;
			permutation.remove(permutation.size() - 1);
		}
	}
}
