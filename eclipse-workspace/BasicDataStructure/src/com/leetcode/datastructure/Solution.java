package com.leetcode.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

//Definition for an interval.
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

class Point {
	public Point(int i, int j) {
		// TODO Auto-generated constructor stub
		x = i;
		y = j;
	}

	int x;
	int y;
}

// 146. LRU Cache
// Company: ALL
// Description:
// Solution: Hashmap combined with LinkedList, swap the most used item to the
// back of the LinkedList. O(1): get use HashMap, when put, we should get first,
// and then update/ move to tail.
class LRUCache {
	private class Node {
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int k, int v) {
			key = k;
			value = v;
			next = null;
			prev = null;
		}
	}

	private HashMap<Integer, Node> map = new HashMap<>();
	private int capacity;
	private Node head = new Node(-1, -1);
	private Node tail = new Node(-1, -1);

	public LRUCache(int capacity) {
		this.capacity = capacity;
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}

		Node cur = map.get(key);
		// relocate cur to tail.
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;

		move_to_tail(cur);

		return cur.value;
	}

	public void put(int key, int value) {
		// check whether need to update the existing item
		// or need to remove least used unit due to the
		// capacity limitation.
		if (get(key) != -1) {
			// means we have a match.
			map.get(key).value = value;
			return;
		}

		if (map.size() == capacity) {
			map.remove(head.next.key);
			// remove the node from list.
			head.next = head.next.next;
			head.next.prev = head;
		}
		// append node.
		Node node = new Node(key, value);
		map.put(key, node);
		move_to_tail(node);
	}

	private void move_to_tail(Node node) {
		tail.prev.next = node;
		node.prev = tail.prev;
		node.next = tail;
		tail.prev = node;
	}
}

// 380. Insert Delete GetRandom O(1)
// Company: Google Amazon Facebook Twitter Uber
// Description: Design a data structure that supports all following operations
// in average O(1) time.
// 1. insert(val): Inserts an item val to the set if not already present.
// 2. remove(val): Removes an item val from the set if present.
// 3. getRandom: Returns a random element from current set of elements. Each
// element must have the same probability of being returned.

// Solution:
class RandomizedSet {

	/** Initialize your data structure here. */
	HashMap<Integer, Integer> cache;
	Integer index = 0; // array index
	ArrayList<Integer> array;
	Random random;

	public RandomizedSet() {
		array = new ArrayList<>();
		cache = new HashMap<>();
		random = new Random();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		if (cache.containsKey(val)) {
			return false;
		}
		array.add(val);
		cache.put(val, index++);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (!cache.containsKey(val)) {
			return false;
		}

		int idx = cache.get(val);
		cache.put(array.get(array.size() - 1), idx);
		cache.remove(val);
		// swap(idx, array.size() - 1);
		array.set(idx, array.get(array.size() - 1));
		array.remove(array.size() - 1);
		index--;
		return true;
	}

	private void swap(int i, int j) {
		int temp1 = array.get(j);
		int temp2 = array.get(i);
		array.set(i, temp1);
		array.set(j, temp2);
	}

	/** Get a random element from the set. */
	public int getRandom() {
		int idx = random.nextInt(index);
		return array.get(idx);
	}
}

// 460. LFU Cache
// Company: Google Amazon.
// Description: Least Frequency Used. If there is a tie, then the LRU item will
// be removed.
// Solution: Use HashMap and LinkedHashSet, the linkedhashset can be used to
// track the least recent used item. Since it has been added with sequence.

class LFUCache {
	HashMap<Integer, Integer> cache;
	HashMap<Integer, Integer> frequency;
	HashMap<Integer, LinkedHashSet<Integer>> freq_list;
	int capacity;
	int min_freq; // for current min freq.

	public LFUCache(int capacity) {
		min_freq = -1;
		cache = new HashMap<>(capacity);
		frequency = new HashMap<>();
		this.capacity = capacity;
		freq_list = new HashMap<>();
		freq_list.put(1, new LinkedHashSet<>());
	}

	public int get(int key) {
		if (!cache.containsKey(key)) {
			return -1;
		}

		int count = frequency.get(key);
		frequency.put(key, count + 1);
		freq_list.get(count).remove(key);
		// count equal to min_freq && current frequency list does not
		// contains any item.
		if (count == min_freq && freq_list.get(min_freq).size() == 0) {
			min_freq++;
		}

		if (!freq_list.containsKey(count + 1)) {
			freq_list.put(count + 1, new LinkedHashSet<>());
		}

		freq_list.get(count + 1).add(key);

		return cache.get(key);
	}

	public void put(int key, int value) {
		// capacity reached
		if (this.capacity == 0) {
			return;
		}

		// we have a match and we need to update the frequency as well.
		if (cache.containsKey(key)) {
			cache.put(key, value);
			get(key);
			return;
		}

		// reaches capacity.
		if (cache.size() == this.capacity) {
			// evict
			int evictKey = freq_list.get(min_freq).iterator().next();
			freq_list.get(min_freq).remove(evictKey);
			cache.remove(evictKey);
			// frequency.remove(evictKey);
		}
		// newly add key.
		cache.put(key, value);
		frequency.put(key, 1);
		min_freq = 1;
		freq_list.get(1).add(key);
	}
}

// To be used in Merge K sorted List
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

// 284. Peeking Iterator
// Company: Apple, Google, Yahoo
// Description: Design a iterator which supports peek().
// Solution: Use advance method to advance the iterator, meanwhile use boolean
// flag to track
// whether we still have available element.
class PeekingIterator implements Iterator<Integer> {
	boolean noSuchElement = false;
	Iterator<Integer> curIterator;
	Integer next;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		curIterator = iterator;
		advanceIterator();
	}

	private void advanceIterator() {
		if (curIterator.hasNext()) {
			next = curIterator.next();
		} else {
			noSuchElement = true;
		}
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (noSuchElement) {
			throw new NoSuchElementException();
		}

		int res = next;
		advanceIterator();
		return res;
	}

	@Override
	public boolean hasNext() {
		return !noSuchElement;
	}
}

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// LRUCache cache = new LRUCache(1);
		Solution sl = new Solution();
		LRUCache lcache = new LRUCache(2);
		lcache.get(2);
		lcache.put(2, 6);
		lcache.get(1);
		lcache.put(1, 5);
		lcache.put(1, 2);
		lcache.get(1);
		lcache.get(2);

		int res = lcache.get(2);
		System.out.println("1:" + res);
		lcache.put(3, 2);
		res = lcache.get(2);
		System.out.println("2:" + res);
		res = lcache.get(3);
		System.out.println("3:" + res);

		HashMap<Integer, Integer> map = new HashMap<>();
		Integer v = map.get(1);
		System.out.println("value:" + v);
		String abc = "abc";
		int index = 0;
		while (index < abc.length()) {
			System.out.println("c:" + abc.charAt(index++));
		}

		System.out.println("=============");
		LFUCache cache = new LFUCache(2);
		cache.put(1, 1);
		int value = cache.get(1);
		cache.put(2, 2);
		cache.get(1);
		cache.get(2);
		// ["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
		// [[],[1],[2],[2],[],[1],[2],[]]
		RandomizedSet set = new RandomizedSet();
		set.insert(1);
		set.remove(2);
		set.insert(2);
		int r1 = set.getRandom();
		set.remove(1);
		set.insert(2);
		int r2 = set.getRandom();
		Queue<Integer> heap = new PriorityQueue<Integer>(4, new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2; // Default. If return o2 - o1 then the larger will be the root.
			}
		});

		// Default heap on JAVA is MinHeap.
		// o2 - o1 will be MaxHeap. Or use reverseOrder from Collection.
		// heap = new PriorityQueue<Integer>();

		heap.offer(5);
		heap.offer(10);
		heap.offer(6);

		int head = heap.peek();
		// heap.poll();
		heap.offer(8);

		Point[] points = new Point[3];
		Random rand = new Random();
		for (int i = 0; i < 3; i++) {
			points[i] = new Point(rand.nextInt(5), rand.nextInt(5));
		}
		Point[] resP = sl.kClosest(points, new Point(0, 0), 2);

		System.out.println("Res:" + resP);

		String[] strings = new String[] { "i", "love", "leetcode", "i", "love", "coding" };
		sl.topKFrequent(strings, 2);
		int x = 1534236469;
		int resInt = sl.reverse(x);
		System.out.println(resInt);

		String note = "bjaajgea";
		String mag = "affhiiicabhbdchbidghccijjbfjfhjeddgggbajhidhjchiedhdibgeaecffbbbefiabjdhggihccec";
		sl.canConstruct(note, mag);
		int n = 10;
		int nAndBit = n & 1;
		int nOrBit = n | 1;
		int nRshift = n >> 1; // consider/2
		int nLshift = n << 2;

		System.out.println("Bit manipulation");
		int reverseRes = sl.reverseBits(43261596);

		for (int i = 2; i < 3; i++) {
			System.out.println("Not suppposed");
		}

		int[] fn = new int[2]; // fn[0], fn[1];
		System.out.println("Array size:" + fn.length);

		String s1 = "1";
		String s2 = "1.1";
		int com = sl.compareVersion(s1, s2);
		System.out.println("Res:" + com);

		String rStr = "This is test";
		rStr = " start game";
		// int endIndex = rStr.length();
		// int beginIndex = rStr.lastIndexOf(' ', endIndex - 1);
		// String sub = rStr.substring(beginIndex + 1, endIndex);

		// " a b ";
		// " 1";
		String resStr = sl.reverseWords(rStr);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					if (i == 1 && j == 1 && k == 1) {
						System.out.println("loop reached");
						break;
					}

					System.out.println("loop break i:" + i + "j:" + j + "k:" + k);
				}
				System.out.println("loop in j");
			}
			System.out.println("loop in i");
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (i == 0 || j == 0) {
					System.out.println("i == 0 or j == 0");
				} else if (i == 0) {
					System.out.println("i == 0");
				} else if (j == 0) {
					System.out.println("j == 0");
				} else {
					System.out.println("i = " + i + "j = " + j);
				}
			}
		}

		List<List<Integer>> pas = sl.generate(3);

		System.out.println("Pas: " + pas);

		int pos = sl.strStr("tartarget", "target");

		ListNode headNode = new ListNode(1);
		headNode.next = new ListNode(2);
		headNode.next.next = new ListNode(3);

		ListNode rNode = sl.reverseList(headNode);
		System.out.println("Reverse: " + rNode);

		int[] w = new int[] { 2, 3, 1, 5 };
		int r = sl.trap(w);
		System.out.println("R:" + r);

		String rr1 = "aa";
		String rr2 = "ab";

		sl.canConstruct2(rr1, rr2);
		sl.priorityQueueTester();

		int dnum = sl.numDecodings("01");

		String sRes = sl.decodeString("3[a]2[bc]");
		System.out.println("Decode:" + sRes);

		int[] nums = { 1, 2, 0, 1 }; // [1,2,0,1]
		int lRes = sl.longestConsecutive(nums);
		System.out.println(lRes);

		int a = 2;
		// int b = 2;
		int c = 4;

		System.out.println("a ^ a" + (a ^ a) + "a ^ c ^ a" + (a ^ c ^ a));

		int[] subnums = { 1, 2, 5, 7 };

		int subRes = sl.subsetSum(subnums, 8);
		System.out.println("Res:" + subRes);
		int[] partitionKSum = { 1, 4, 5, 2, 3 };
		//
		partitionKSum = new int[] { 2, 5, 3 };
		boolean canP = sl.canPartitionKSubsets(partitionKSum, 2);
		System.out.println("Res " + canP);
		// int[] cel = {0, 1, 2};
		int cel = 3;
		sl.findCelebrity(cel);
		List<List<Integer>> resFactors = sl.getFactors(12);
		
		System.out.println("Factor:" + resFactors);
	}

	// 254. Factor Combinations
	// Company: LinkedIn Uber
	// Description: Write a function that takes an integer n and return all possible
	// combinations of its factors. e.g 8 = 2 * 2 * 2 and 8 = 2 * 4
	// Solution:
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		factorHelper(2, n, new ArrayList<Integer>(), res);
		return res;
	}
	
	private void factorHelper(int start, int n, List<Integer> back_list, List<List<Integer>> res) {
		if (n == 1) {  // base condition.
			// 1 => [] instead of [[]]
			if (back_list.size() > 1) {
				res.add(new ArrayList<Integer>(back_list));
			}
		} else {
			for (int i = start; i <= n; i++) {
				if (n % i == 0) {
					back_list.add(i);
					factorHelper(i, n/i, back_list, res);
					back_list.remove(back_list.size() - 1);
				}
			}
		}
	}

	// 277. Find the Celebrity
	// Company: Facebook LinkedIn
	// Description: Condition, c doesn't know any of them, and every other people
	// knows c.
	// Solution: First we need to find the seeds celebrity, starting from 0, if 0
	// knows 1...i, then
	// those (1...i) will be seeds, so does if (1...i) knows 0, then 0 also needs to
	// be seed.
	// Second is, iterate those seeds, for each seed, we need to loop whether all
	// the other element knows it, if not
	// then put 0 into seeds, also seed should not know any of other either, if not
	// then mark it not seed and break.
	// Last, check whether seed array have the valid seed, if not then return -1;

	// place holder method inherited from parent.
	private boolean knows(int a, int b) {
		return false;
	}

	public int findCelebrity(int n) {
		int[] possible = new int[n];
		for (int i = 1; i < n; i++) {
			if (knows(0, i)) {
				possible[i] = 1;
			} else if (knows(i, 0)) {
				possible[0] = 1;
			}
		}

		for (int i = 0; i < n; i++) {
			if (possible[i] == 1) {
				for (int j = 0; j < n; j++) {
					if (j == i) {
						continue;
					} else {
						if (knows(i, j)) {
							possible[i] = 0; // not seeds anymore
							break;
						} else if (!knows(j, i)) {
							possible[i] = 0;
							break;
						}
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (possible[i] == 1) {
				return i;
			}
		}

		return -1;
	}

	// 698. Partition to K Equal Sum Subsets
	// Company: Linkedin
	// Description: Given array, check whether it could be partition into k subsets.
	// Solution: Visited array could keep us tracking whether the element has been
	// used before, so in the next dfs, we will know we should not use the element
	// anymore.
	// The idea is backtracking, also the early return make sure the loop exit
	// earlier.
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int n : nums) {
			sum += n;
		}

		if (sum % k != 0) { //
			return false;
		}
		int pSum = sum / k;
		boolean[] visited = new boolean[nums.length];
		boolean res = dfsPartition(nums, 0, 0, pSum, k, visited); // how many partitions we have.

		return res;
	}

	private boolean dfsPartition(int[] nums, int start, int curSum, int target, int pair, boolean[] visited) {
		if (pair == 1) {
			return true;
		}

		if (target == curSum) {
			return dfsPartition(nums, 0, 0, target, pair - 1, visited);
		} else if (target < curSum) {
			return false;
		}

		for (int i = start; i < nums.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (dfsPartition(nums, i + 1, nums[i] + curSum, target, pair, visited)) {
					return true;
				}
				visited[i] = false;
			}
		}

		return false;
	}

	private boolean dfsPartition2(int[] nums, int start, int curSum, int target, int pair) {
		if (pair == 0) {
			return true;
		}

		if (curSum == target) { // start from 0?
			return dfsPartition2(nums, 0, 0, target, pair - 1);
		}

		for (int i = start; i < nums.length; i++) {
			int n = nums[i];
			nums[i] = 0;
			System.out.println("start:" + i + "arr:" + Arrays.toString(nums) + "-> sum:" + n + curSum);
			if (dfsPartition2(nums, i + 1, n + curSum, target, pair)) {
				return true;
			}
			nums[i] = n;
			System.out.println("set i:" + i + "-> n:" + n + "arr:" + Arrays.toString(nums));
		}

		return false;
	}

	public int subsetSum(int[] nums, int s) {
		int dp[] = new int[s + 1];
		dp[0] = 1;
		for (int n : nums) {
			for (int i = s; i >= n; i--) {
				System.out.println("i:" + i + " " + dp[i]);
				dp[i] += dp[i - n];
			}
		}
		return dp[s];
	}

	// 416. Partition Equal Subset Sum
	// Company: Ebay
	// Description: Find whether two subsets sum could be equal to each other.
	// Solution: use dp, if dp[target] = dp[value] + dp[value - i] which means truth
	// of value = truth of value include + truth of value not include.
	// subset sum.
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int n : nums) {
			sum += n;
		}

		if (sum % 2 != 0) { // must be even.
			return false;
		}

		int pSum = sum / 2; // if subsets could sum up to pSum, then there will be solution.

		int[] dp = new int[pSum + 1];
		dp[0] = 1;
		for (int n : nums) {
			for (int i = pSum; i >= n; i--) {
				dp[i] += dp[i - n];
			}
		}

		return dp[pSum] != 0; // initial value 0;
	}

	public void priorityQueueTester() {
		PriorityQueue<Integer> queue1 = new PriorityQueue<>();
		PriorityQueue<Integer> queue2 = new PriorityQueue<>(Collections.reverseOrder());

		queue1.offer(1);
		// queue2.offer(queue1.poll());
		queue1.offer(2);
		// queue2.offer(queue1.poll());''
		queue1.offer(3);

		queue1.offer(4);
		System.out.println("PQ size" + queue1.poll() + queue1.peek());
		int[] coins = new int[] { 2 };
		int count = coinChange(coins, 3);
	}

	// 477. Total Hamming Distance
	// Company: Facebook.
	// Description: 4, 14, 2, each 0100, 1110, 0010, 4 -> 14 = 2, 4 -> 2 = 2, 14 ->
	// 2 = 2, Total is 2 + 2 + 2 = 6
	// Solution: 1. Brutal force, time limit exceed. 2.For all 32 bit, check each
	// number, how many total 1s, the other are 0s, multiple them, will be the
	// corresponding total distance on each bit.

	public int totalHammingDistance(int[] nums) {
		int res = 0;
		int length = nums.length;
		int counter = 0; // how many 1s we have.
		for (int i = 0; i < 32; i++) { // per each digit.
			for (int num : nums) {
				if (((num >> i) & 1) == 1) { // for each of the num, calculate how many 1s on the corresponding bit.
					counter++;
				}
			}

			res += counter * (length - counter); // More explanation, if we have 3 1s and 2 0s, total 5 num, then total
													// distance is 3 * 2, which is 6.
			counter = 0;
		}

		return res;
	}

	public int totalHammingDistance2(int[] nums) {
		int res = 0;

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				res += hammingDistance(nums[i], nums[j]);
			}
		}

		return res;
	}

	// 461. Hamming Distance
	// Company: Facebook
	// Description: Find the hamming distance between two integers. Hamming distance
	// is the total different between 1 -> 0 when binary representation.
	// Solution: after the XOR, shit till number becomes 0, counting total zeros.
	public int hammingDistance(int x, int y) {
		int res = x ^ y;
		int counter = 0;
		while (res != 0) {
			if ((res & 1) == 1) {
				counter++;
			}

			res = res >> 1;
		}

		return counter;
	}

	// 394. Decode String
	// Company: Facebook Google Yelp Coupang
	// Description: The encoding rule is: k[encoded_string], where the
	// encoded_string inside the square brackets is being repeated exactly k times.
	// Note that k is guaranteed to be a positive integer.
	// For eg, s = "3[a2[c]]", return "accaccacc".
	// Solution: 1. Recursion, consider the s has a pattern which is number +
	// [string], string could also be
	// number + [string].
	public String decodeString(String s) {
		StringBuffer res = new StringBuffer();
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				num = 10 * num + s.charAt(i) - '0';
			} else if (s.charAt(i) == '[') { // find the last ']'
				int j = i + 1;
				int pair = 1; // already pass first '['
				for (; j < s.length(); j++) {
					if (s.charAt(j) == '[') {
						pair++;
					} else if (s.charAt(j) == ']') {
						pair--;
					}

					if (pair == 0) { // find the last ']'
						break;
					}
				}
				System.out.println("i:" + i + " j:" + j);

				String temp = decodeString(s.substring(i + 1, j));
				i = j;
				for (int k = 0; k < num; k++) {
					res.append(temp);
				}
				num = 0;
			} else { // character.
				res.append(s.charAt(i));
			}

		}

		return res.toString();
	}

	// 674. Longest Continuous Increasing Subsequence
	// Company: Facebook
	// Description: Given a unsorted array of integers, find the length
	// of longest continuous increasing subsequence
	// Solution:
	public int findLengthOfLCIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int longest = Integer.MIN_VALUE;
		int counter = 1;

		if (nums.length == 1) {
			return counter;
		}

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] > nums[i]) {
				counter++;
				longest = Math.max(longest, counter);
			} else {
				counter = 1;
				longest = Math.max(longest, counter);
			}
		}

		return longest;
	}

	// 494. Target Sum
	// Company: Facebook Google
	// Description: Given int array, and target value, find all the possibilities
	// that will add up to the target.
	// Solution: 1. Use

	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length;
		return nums(S - nums[nums.length - 1], n - 1, nums) + nums(S + nums[nums.length - 1], n - 1, nums);
	}

	private int nums(int target, int position, int[] nums) { // how many combinations on poistion for target

	}

	// 784. Letter Case Permutation
	// Company: Facebook Yelp
	// Description: Given a string "a1b2" return all possible strings we could
	// create
	// "A1b2" "A1B2" "a1B2" "a1b2"
	// Solution:
	public List<String> letterCasePermutation(String S) {

	}

	// 824. Goat Latin
	// Company: Facebook
	// Description: if word begins with (a, e, i, o, u) append 'ma', if not then
	// remove first letter
	// and put it to the end, and then append 'ma', add 'a' to end of each word per
	// its word index first
	// word get 'a' second 'aa'
	// Solution: Need to consider the space not adding to the last word, also the
	// Cap or normal case

	public String toGoatLatin(String S) {
		String[] strings = S.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			String s = strings[i];
			char first = s.charAt(0);
			StringBuffer sub = new StringBuffer();
			if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u' || first == 'A'
					|| first == 'E' || first == 'I' || first == 'O' || first == 'U') {
				sub.append(s);
			} else {
				sub.append(s.substring(1, s.length()) + first);
			}

			sub.append("ma");

			for (int j = 0; j <= i; j++) {
				sub.append('a');
			}

			sb.append(sub);

			if (i != strings.length - 1) {
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	// 136. Single Number
	// Company: Airbnb, Palatinr.
	// Description: Given a array, every element appears twice except for one, find
	// that element.
	// Solution: (a ^ a) ^ b = b;
	public int singleNumber(int[] nums) {
		int res = 0;
		for (int num : nums) {
			res ^= num;
		}

		return res;
	}

	// 128. Longest Consecutive Sequence
	// Description:
	// Solution:
	public int longestConsecutive(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return nums.length;
		}

		Arrays.sort(nums);
		int counter = 1;
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] + 1 == nums[i + 1]) {
				counter++;
				res = Math.max(counter, res);
			} else if (nums[i] == nums[i + 1]) {
				res = Math.max(counter, res);
				continue;
			} else {
				res = Math.max(counter, res);
				counter = 1;
			}
		}
		return res;
	}

	// 91. Decode Ways
	// Description: A - Z conresponding 1, 26. Given a number, 226, how many
	// combinations can it have.
	// for eg, 2, 26; 22, 6; 2, 2, 6 another kind.
	// Solution: Take '1234' as example, the result must come from 123 + 4 and 12 +
	// 34,
	// however 12 + 34 is not legal on 34, so it can not be added on. Iterative the
	// process until we
	// get the answer.
	public int numDecodings(String s) {
		if (s == null || s.length() == 0 || s.equalsIgnoreCase("0")) {
			return 0;
		}

		int w1 = 1; // dp[i - 1]
		int w2 = 1; // dp[i - 2]

		for (int i = 1; i < s.length(); i++) {
			int w = 0;
			if (!isValid(s.charAt(i)) && !isValid(s.charAt(i - 1), s.charAt(i))) {
				return 0;
			}

			if (isValid(s.charAt(i))) {
				w = w1;
			}

			if (isValid(s.charAt(i - 1), s.charAt(i))) {
				w += w2;
			}

			w2 = w1;
			w1 = w;
		}

		return w1;
	}

	private boolean isValid(char a) {
		return a - '0' == 0 ? false : true;
	}

	private boolean isValid(char a, char b) {
		int v = (a - '0') * 10 + (b - '0');
		if (10 <= v && v <= 26) {
			return true;
		}

		return false;
	}

	// 322. Coin Change
	// Description: Given a array and a target, find out whether the coin could be
	// added up to target value. Return the minimum coins required.
	// Solution: DP[i] means for the target i, minimum coins required.
	// Also could be achived by using DFS.
	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		for (int i = 1; i < amount + 1; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for (int coin : coins) {
			System.out.println("coin:" + coin);
			for (int i = coin; i <= amount; i++) {
				if (dp[i - coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], Math.abs(dp[i - coin] + 1));
				}
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	// 22. Generate Parentheses
	// Company: Google Uber, Zenefits
	// Description: Given an integer, generate all the possible combinations of
	// parenthesis.
	// Solution:
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfshelper(0, 0, n, "", res);
		return res;
	}

	private void dfshelper(int l, int r, int n, String p, List<String> res) {
		if (l == n && r == n) {
			res.add(p);
			return;
		}

		if (l < n) {
			dfshelper(l + 1, r, n, p + "(", res);
		}

		if (r < l) {
			dfshelper(l, r + 1, n, p + ")", res);
		}
	}

	// 56. Merge Intervals
	// Company: Google Facebook Microsoft Bloomberg LinkedIn Twitter Yelp
	// Description: Given a collection of intervals, merge all overlapping
	// intervals.
	// Solution: Sort the input intervals, when adding back to res, try to see
	// whether
	// it could be merged or not.

	public List<Interval> merge(List<Interval> intervals) {
		Interval[] interval = new Interval[intervals.size()];
		for (int i = 0; i < intervals.size(); i++) {
			interval[i] = intervals.get(i);
		}

		Arrays.sort(interval, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		});

		List<Interval> res = new ArrayList<>();

		for (int i = 0; i < interval.length; i++) {
			Interval cur = interval[i];
			if (res.isEmpty()) {
				res.add(cur);
			} else {
				Interval last = res.get(res.size() - 1);
				if (cur.start <= last.end) {
					if (cur.end > last.end) {
						// before merge the new one, remove the previous
						res.remove(res.size() - 1);
						res.add(new Interval(last.start, cur.end));
					}
				} else {
					res.add(cur);
				}
			}
		}

		return res;
	}

	// 252. Meeting Rooms
	// Company: Facebook
	// Description: Given an list of intervals, find whether a person could attend
	// all the
	// meetings.
	// Solution: consider the whole intervals as a big array,
	// if any of the elements are overlap, then the big array element's next value
	// will be overlapping with the previous value.
	public boolean canAttendMeetings(Interval[] intervals) {
		int[] array = new int[intervals.length * 2];

		for (int i = 0; i < intervals.length; i++) {
			array[2 * i] = intervals[i].start;
			array[2 * i + 1] = intervals[i].end;
		}

		Arrays.sort(array);
		Arrays.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}

		});

		for (int i = 0; i < intervals.length; i++) {
			if (array[2 * i] == intervals[i].start && array[2 * i + 1] == intervals[i].end) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	// 206. Reverse Linked List
	// Company: ALL
	// Description: Reverse Linked List
	// Solution:
	public ListNode reverseList(ListNode head) {
		ListNode preNode = null;
		ListNode curNode = head;

		while (curNode != null) {
			ListNode nextNode = curNode.next;
			curNode.next = preNode;
			preNode = curNode;
			curNode = nextNode;
		}

		return preNode;
	}

	// 21. Merge Two Sorted Lists
	// Company: Microsoft, Amazon, LinkedIn, Apple.
	// Description: Merge two sorted lists into one.
	// Solution: Pick up head, iterate the node. If found null already means reach
	// the end.
	//
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode l = new ListNode(0);
		ListNode p = l;

		while (l1 != null || l2 != null) {
			if (l1 == null) {
				p.next = l2;
				break;
			}

			if (l2 == null) {
				p.next = l1;
				break;
			}

			if (l1.val > l2.val) {
				p.next = l2;
				l2 = l2.next;
			} else {
				p.next = l1;
				l1 = l1.next;
			}

			p = p.next;
		}

		return l.next;
	}

	// 28. Implement strStr()
	// Company: Facebook Microsoft Apple Pocket Gems
	// Description: Return the index of the first occurrence of needle in haystack,
	// or -1 if needle is not part of haystack.
	// Solution:
	public int strStr(String source, String target) {
		return 0;
	}
	// public int strStr2(String source, String target) { This answer is wrong.
	// if (target.length() > source.length()) {
	// return -1;
	// }
	// int j = 0;
	// int i = 0;
	// boolean stepback = false;
	//
	// for (; i < source.length(); i++) {
	// if (j == target.length()) {
	// break;
	// }
	//
	// if (source.charAt(i) == target.charAt(j)) {
	// j++;
	// stepback = true;
	// continue;
	// } else {
	// j = 0;
	// if (stepback) {
	// i = i - 1;
	// }
	// }
	//
	// System.out.println("i:" + i + "j:" + j);
	// }
	//
	// if (j == target.length()) {
	// return i - j;
	// }
	//
	// return -1;
	// }

	// 149. Max Points on a Line
	// Company: LinkedIn, Apple, Twitter
	// Description: Given n points on a 2D plane, find the maximum number of points
	// that
	// lie on the same straight line.
	// Solution:
	public int maxPoints(Point[] points) {
		if (points == null) {
			return 0;
		}

		int size = points.length;
		if (size < 3) {
			return size;
		}

		int result = 0;
		HashMap<Integer, HashMap<Integer, Integer>> slopes = new HashMap<>();
		for (int i = 0; i < size; i++) {
			int samePoints = 0;
			int max = 0;
			slopes.clear();
			for (int j = i + 1; j < size; j++) {
				int dx = points[j].x - points[i].x;
				int dy = points[j].y - points[i].y;

				if (dx == 0 && dy == 0) {
					samePoints++;
					continue;
				}

				int m_gcd = gcd(dx, dy);

				dx /= m_gcd;
				dy /= m_gcd;

				if (slopes.containsKey(dx)) {
					if (slopes.get(dx).containsKey(dy)) {
						slopes.get(dx).put(dy, slopes.get(dx).get(dy) + 1);
					} else {
						slopes.get(dx).put(dy, 1);
					}
				} else {
					HashMap<Integer, Integer> map = new HashMap<>();
					map.put(dy, 1);
					slopes.put(dx, map);
				}

				max = Math.max(max, slopes.get(dx).get(dy));
			}

			result = Math.max(result, max + samePoints + 1);
		}

		return result;
	}

	private int gcd(int m, int n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	// 221. Maximal Square
	// Company: Facebook, Apple, Airbnb.
	// Description: return the area of largest square in a matrix.
	// Input:
	//
	// 1 0 1 0 0
	// 1 0 1 1 1
	// 1 1 1 1 1
	// 1 0 0 1 0
	//
	// Output: 4
	//
	// Solution: 1. DP which also applies to Rectangle, need to optimize for the
	// square.

	public int maximalSquare(char[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int ret = 0;

		int[][] size = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				size[i][j] = matrix[i][j] - '0';
				if (size[i][j] == 0) {
					continue;
				}
				if (i != 0 && j != 0) {
					size[i][j] = Math.min(size[i - 1][j - 1], Math.min(size[i - 1][j], size[i][j - 1])) + 1;
				}
				ret = Math.max(ret, size[i][j] * size[i][j]);
			}
		}

		return ret;
	}

	public int maximalSquare2(char[][] matrix) {
		int m = matrix.length;

		if (m == 0) {
			return 0;
		}

		int n = matrix[0].length;
		int[][] sum = new int[m + 1][n + 1]; // Why + 1?

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1] - '0';
			}
		}

		int res = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = Math.min(m - i + 1, n - j + 1); k > 0; k--) {
					int sumRect = sum[i + k - 1][j + k - 1] - sum[i - 1][j + k - 1] - sum[i + k - 1][j - 1]
							+ sum[i - 1][j - 1];
					if (sumRect == k * k) {
						res = Math.max(sumRect, res);
						break;
					}
				}
			}
		}

		return res;
	}

	// 240. Search a 2D Matrix II
	// Company: Amazon, Apple, Google
	// Description: [
	// [1, 4, 7, 11, 15],
	// [2, 5, 8, 12, 19],
	// [3, 6, 9, 16, 22],
	// [10, 13, 14, 17, 24],
	// [18, 21, 23, 26, 30]
	// ]
	// Solution: Starting from the left bottom corner, element row-- are smaller,
	// and element col++ are larger
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int i = matrix.length - 1;
		int j = 0;

		while (true) {
			if (matrix[i][j] > target) {
				i--; // up
			} else if (matrix[i][j] < target) {
				j++; // right
			} else {
				return true; // equal
			}

			if (i < 0 || j >= matrix[0].length) {
				break;
			}
		}

		return false;
	}

	// 42. Trapping Rain Water
	// Company: Google, Apple, Amazon, Airbnb, Twitter, Bloomberg
	// Description: Given n non-negative integers representing an elevation map
	// where the width of each bar is 1, compute how much water it is able to trap
	// after raining.Input: [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6
	// Solution:
	public int trap(int[] height) {
		int left = 0, right = height.length - 1;
		int res = 0;
		int leftMax = 0, rightMax = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				leftMax = Math.max(height[left], leftMax);
				res += leftMax - height[left];
				left++;
			} else {
				rightMax = Math.max(height[right], rightMax);
				res += rightMax - height[right];
				right--;
			}
		}

		return res;
	}

	// 151. Reverse Words in a String
	// Company: Apple Microsoft Bloomberg Snapchat Yelp
	// Description: Given an input string, reverse the string word by word.
	// Input: "the sky is blue",
	// Output: "blue is sky the".
	// Solution: Find the last word before the space, and append that word with
	// space, until there is no more spaces, end index equals to zero.
	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		int endIndex = s.length();
		int beginIndex;
		while ((beginIndex = s.lastIndexOf(' ', endIndex - 1)) != -1) {
			String str = s.substring(beginIndex + 1, endIndex);
			if (!str.isEmpty()) {
				sb.append(str).append(' ');
			}
			endIndex = beginIndex;
		}
		// int length = sb.length();
		// endIndex means leading zero from original string.
		if (endIndex == 0 && sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		} else {
			sb.append(s.substring(0, endIndex));
		}

		return sb.toString();
	}

	public String reverseWords2(String s) {
		if (s.trim().length() == 0) {
			return s.trim();
		}

		s = s.trim();

		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();

		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i].length() == 0) { // while space skip.
				continue;
			}
			sb.append(arr[i] + " ");
		}

		if (arr.length >= 0) {
			sb.append(arr[0]);
		}

		return sb.toString();
	}

	// 36. Valid Sudoku
	// Company: Apple, Uber, Snapchat.
	// Description: Check whether it's valid. row, col and cube does not allow
	// duplicate numbers.
	// Solution: For each row, check i, j, col check j, i, cube check rowIndex,
	// colIndex.
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			HashSet<Character> row = new HashSet<>();
			HashSet<Character> col = new HashSet<>();
			HashSet<Character> cube = new HashSet<>();

			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.' && !row.add(board[i][j])) {
					return false;
				}

				if (board[j][i] != '.' && !col.add(board[j][i])) {
					return false;
				}

				int rowIndex = 3 * (i / 3);
				int colIndex = 3 * (i % 3);

				if (board[rowIndex + j / 3][colIndex + j % 3] != '.'
						&& !cube.add(board[rowIndex + j / 3][colIndex + j % 3])) {
					return false;
				}
			}
		}
		return true;
	}

	// 565. Array Nesting
	// Company: Apple
	// Description: Find the longest path in the array. A[A[A[i]]];
	// Solution: Loop, mark visited as -1, need to find the longest loop.
	public int arrayNesting(int[] nums) {
		int ret = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= 0) {
				int longest = 0;
				for (int j = i; nums[j] >= 0;) {
					int tmp = nums[j];
					nums[j] = -1; // mark as visited.
					longest++;
					ret = Math.max(ret, longest);
					j = tmp;
				}
			}
		}
		return ret;
	}

	// 203. Remove Linked List Elements
	// Company: N/A
	// Description:
	// Solution:
	// TODO: 237 Follow up

	// 237. Delete Node in a Linked List
	// Company: Microsoft, Apple, Adobe
	// Description: Given only access to the node.
	// Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node
	// with value 3, the linked list should become 1 -> 2 -> 4 after calling your
	// function.
	// Solution: We use 4's value to replace 3's value and delete 4 actually.
	public void deleteNode(ListNode node) {
		// give next node val to it.
		node.val = node.next.val;
		node.next = node.next.next;
	}

	// 165. Compare Version Numbers
	// Company: Microsoft, Apple
	// Description: Compare two version, v1 > v2 => 1, v1 < v2 => -1, otherwise 0.
	// also 1.3 < 1.10
	// Solution: loop through numeric parts until encounter the '.', if equal then
	// reset the numeric parts.
	// NOTE: leading 0 needs to be considered.
	public int compareVersion(String version1, String version2) {
		int num1 = 0;
		int num2 = 0;

		for (int i = 0, j = 0; (i < version1.length() || j < version2.length());) {
			while (i < version1.length() && version1.charAt(i) != '.') {
				num1 = num1 * 10 + version1.charAt(i) - '0';
				i++;
			}

			while (j < version2.length() && version2.charAt(j) != '.') {
				num2 = num2 * 10 + version2.charAt(j) - '0';
				j++;
			}

			if (num1 > num2) {
				return 1;
			} else if (num1 < num2) {
				return -1;
			}
			// equal reset
			num1 = 0;
			num2 = 0;
			i++;
			j++;
		}

		return 0;
	}

	public int compareVersion2(String version1, String version2) {
		// if (version1.indexOf('.') == -1 && version2.indexOf('.') == -1) {
		// // normal value
		// int v1 = Integer.parseInt(version1);
		// int v2 = Integer.parseInt(version2);
		// if (v1 == v2) {
		// return 0;
		// } else if (v1 > v2) {
		// return 1;
		// } else {
		// return -1;
		// }
		// }
		String[] nums1 = version1.split("\\.");
		String[] nums2 = version2.split("\\.");

		int l1 = nums1.length;
		int l2 = nums2.length;

		if (l1 == l2 && l1 == 0) {
			int v1 = Integer.parseInt(version1);
			int v2 = Integer.parseInt(version2);
			if (v1 == v2) {
				return 0;
			} else if (v1 > v2) {
				return 1;
			} else {
				return -1;
			}
		}

		int length = l1 > l2 ? l2 : l1; // min

		for (int i = 0; i < length; i++) {
			if (Integer.parseInt(nums1[i]) > Integer.parseInt(nums2[i])) {
				return 1;
			} else if (Integer.parseInt(nums1[i]) < Integer.parseInt(nums2[i])) {
				return -1;
			}
		}

		if (l1 == l2) {
			return 0;
		}

		if (length == l1) { // l2 is longer
			while (Integer.parseInt(nums2[length++]) == 0) {
				if (length == l2) {
					return 0;
				}
			}
			return -1;
		} else {
			while (Integer.parseInt(nums1[length++]) == 0) {
				if (length == l1) {
					return 0;
				}
			}
			return 1;
		}
	}

	// 70. Climbing Stairs
	// Company: Apple Adobe
	// Description: You are climbing a stair case. It takes n steps to reach to the
	// top.
	// Each time you can either climb 1 or 2 steps. In how many distinct ways can
	// you climb to the top?
	// Note: Given n will be a positive integer.
	// Solution: DP problem.
	public int climbStairs(int n) {
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 1;

		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];
	}

	// 48. Rotate Image
	// Company: Apple Amazon Microsoft
	// Description:
	// You are given an n x n 2D matrix representing an image.
	// Rotate the image by 90 degrees (clockwise). in-place.
	// Solution: n * n, fold diagonal first, then fold in the middle.
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = tmp;
			}
		}
	}

	// 190. Reverse Bits
	// Company: Apple, Airbnb
	// Description: Reverse bits of a given 32 bits unsigned integer
	// Solution:
	// Follow up If this function is called many times, how would you optimize it?
	public int reverseBits(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res = res << 1;
			if ((n & 1) == 1) {
				res++;
			}
			n = n >> 1;
		}
		return res;
	}

	// 1. Two Sum
	// Company: ALL
	// Description: Given an array, and target, return the indices of two integer if
	// there
	// are any.
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				res[0] = map.get(target - nums[i]);
				res[1] = i;
			}
			map.put(nums[i], i);
		}

		return res;
	}

	// 191. Number of 1 Bits
	// Company: Apple Microsoft
	// Description: Write a function that takes an unsigned integer and returns the
	// number of '1' bits it has (also known as the Hamming weight).
	// Solution: 1. Slow solution. Just count 1s from string.
	// 2. Fast solution: By shift bits.
	public int hammingWeight(int n) {
		String s = Integer.toBinaryString(n);
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				count++;
			}
		}

		return count;
	}

	public int hammingWeight2(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 1) {
				count++;
			}

			n = n >>> 1;
		}
		return count;
	}

	// 383. Ransom Note
	// Company: Apple
	// Description://"bg"
	// "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"
	// Solution: 1. Use hashtable as storage, 2. use array.
	// Idea is to check whether we has the character in the ransomNote which never
	// appears in the magazine.
	public boolean canConstruct2(String ransomNote, String magazine) {
		int[] alpha = new int[26];
		for (int i = 0; i < magazine.length(); i++) {
			alpha[magazine.charAt(i) - 'a']++;
		}

		for (int i = 0; i < ransomNote.length(); i++) {
			if (--alpha[ransomNote.charAt(i) - 'a'] < 0) {
				return false;
			}
		}

		return true;
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		// int j = 0;
		// for (int i = 0; i < magazine.length() && j < ransomNote.length(); i++) {
		// if (magazine.charAt(i) == ransomNote.charAt(j)) {
		// j++;
		// }
		// }
		//
		// System.out.println("j:" + j + "c:" + ransomNote.charAt(j));
		// return j == ransomNote.length();
		// aa, ab = > false;
		// no need to use hashtable, just array should be enough cause there is
		// only 26 character.
		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < magazine.length(); i++) {
			char c = magazine.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		for (int i = 0; i < ransomNote.length(); i++) {
			if (!map.containsKey(ransomNote.charAt(i))) {
				return false;
			} else {
				int count = map.get(ransomNote.charAt(i));
				count = count - 1;
				if (count == 0) {
					map.remove(ransomNote.charAt(i));
				} else {
					map.put(ransomNote.charAt(i), count);
				}
			}
		}

		return true;
	}

	// 69. Sqrt(x)
	// Company: Facebook Bloomberg Apple
	// Description: Return the integer of square root of x.
	// Solution: 1. NewTon method. 2. Binary search method.
	public int mySqrt(int x) {
		long res = x;

		while (res * res > x) {
			res = (res + x / res) / 2;
		}

		return (int) res;
	}

	// 7. Reverse Integer
	// Company: Apple, Bloomberg
	// Description: Given a 32-bit signed integer, reverse digits of an integer.
	// When overflow it should return 0; for eg, 123 -> 321, 2147483647 after
	// reverse will overflow.
	// Solution: 1. Consider boundary, Java int from -2^32 ~ 2^32 - 1;
	// so if exceeding we should return 0;
	// 2. Performance is bad. But the solution itself is straight forward.
	public int reverse(int x) {
		long res = 0;

		while (x != 0) {
			res = 10 * res + x % 10;
			x /= 10;
			if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
				return 0;
			}
		}

		return (int) res;
	}

	public int reverse2(int x) {
		boolean reverse = false;
		if (x < 0) {
			x = -x;
			reverse = true;
		}
		String str = String.valueOf(x);
		StringBuilder sb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		int res = 0;
		try {
			res = Integer.parseInt(sb.toString());
		} catch (Exception e) {
			res = 0;
		}

		if (reverse) {
			res = -res;
		}
		return res;
	}

	// 238. Product of Array Except Self
	// Company: Facebook Microsoft Amazon LinkedIn Apple
	// Description: Given an array nums of n integers where n > 1, return an array
	// output such that output[i] is equal to the product of all the elements of
	// nums except nums[i].
	// Solution: product = left * right
	// Requirement: space O(1), complexity O(n);
	public int[] productExceptSelf(int[] nums) {
		int[] res = new int[nums.length];
		res[nums.length - 1] = 1;

		for (int i = nums.length - 2; i >= 0; i--) {
			res[i] = res[i + 1] * nums[i + 1];
		}

		int left = 1;
		for (int i = 0; i < nums.length; i++) {
			res[i] = left * res[i];
			left = left * nums[i];
		}

		return res;
	}

	// Time Complexity: O(n2)
	private int product(int[] nums, int skip) {
		int res = 1;
		for (int i = 0; i < nums.length; i++) {
			if (i == skip) {
				continue;
			}
			res = nums[i] * res;
		}

		return res;
	}

	// 118. Pascal's Triangle
	// Company: Apple, Twitter
	// Description: sums up the
	// Solution:
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		ArrayList<Integer> row = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			row.add(0, 1);
			for (int j = 1; j < row.size() - 1; j++) {
				row.set(j, row.get(j) + row.get(j + 1));
			}
			res.add(new ArrayList<>(row));
		}

		return res;
	}

	// 4. Median of Two Sorted Arrays
	// Company: Google Microsoft Apple Zenefits Yahoo Adobe Dropbox
	// Description: There are two sorted arrays nums1 and nums2 of size m and n
	// respectively.
	// Find the median of the two sorted arrays. The overall run time complexity
	// should be O(log (m+n)).
	// Solution:
	// TODO: Revisit
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		if (n1 > n2) { // make sure n1 <= n2;
			return findMedianSortedArrays(nums2, nums1);
		}

		int k = (n1 + n2 + 1) / 2; // merged array, median must be in k elements.

		int l = 0;
		int r = n1;
		int m1 = 0;
		int m2 = 0;
		while (l < r) {
			m1 = l + (r - l) / 2;
			m2 = k - m1;
			if (nums1[m1] < nums2[m2 - 1]) { // we need more elements from nums1.
				l = m1 + 1;
			} else {
				r = m1;
			}
		}

		m1 = l;
		m2 = k - l;

		int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
		if ((n1 + n2) % 2 == 1) {
			return c1;
		}

		int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1], m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

		return (c1 + c2) * 0.5;
	}

	// 612. K closest points
	// Company: N/A
	// Description: Given some points and a point origin in two dimensional space,
	// find k points out of the some points which are nearest to origin.
	// Return these points sorted by distance, if they are same with distance,
	// sorted by x-axis, otherwise sorted by y-axis.
	// Solution: PriorityQueue
	public Point[] kClosest(Point[] points, Point origin, int k) {
		// write your code here
		Point[] res = new Point[k];
		PriorityQueue<Point> queue = new PriorityQueue<Point>(k, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				System.out.println("comparing....: o2" + o2.x + o2.y + "o1" + o1.x + o1.y);
				// System.out.println("\n");
				int diff = distance(o2, origin) - distance(o1, origin);
				if (diff == 0) {
					return o2.x - o1.x;
				}

				if (diff == 0) {
					return o2.y - o1.y;
				}

				return diff;
			}
		});

		for (Point p : points) {
			System.out.println("adding... " + p.x + p.y);
			// System.out.println("\n");
			queue.offer(p);
			if (queue.size() > k) {
				Point pr = queue.poll();
				System.out.println("remove..." + pr.x + pr.y);
			}
		}

		while (!queue.isEmpty()) {
			res[--k] = queue.poll();
		}

		return res;
	}

	private int distance(Point a, Point b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}

	// 692. Top K Frequent Words
	// Company: Amazon Uber Yelp
	// Description: Given a non-empty list of words, return the k most frequent
	// elements. Alphabet order
	// Solution:
	public List<String> topKFrequent(String[] words, int k) {
		HashMap<String, Integer> map = new HashMap<>();
		Queue<Entry<String, Integer>> queue = new PriorityQueue(k, new EntryComparator());

		for (String word : words) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}

		for (Entry<String, Integer> entry : map.entrySet()) {
			queue.offer(entry);
			if (queue.size() > k) {
				queue.poll();
			}
		}

		List<String> res = new ArrayList(k);
		while (!queue.isEmpty()) {
			res.add(0, queue.poll().getKey());
		}

		return res;
	}

	public class EntryComparator implements Comparator<Entry<String, Integer>> {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			int diff = o1.getValue() - o2.getValue();
			if (diff == 0) {
				diff = o2.getKey().compareTo(o1.getKey());
			}

			return diff;
		}
	}

	// 545. Top k Largest Numbers II
	// Company: N/A
	// Description: Size k container, add(num) to it, find top k elements. max heap.
	// Solution:
	private Queue<Integer> heap = new PriorityQueue<>();
	private int size; // initialize during constructor

	public void add(int num) {
		if (heap.size() < size) {
			heap.offer(num);
			return;
		}

		if (num > heap.peek()) {
			heap.poll();
			heap.add(num);
		}
	}

	public List<Integer> topK() {
		List<Integer> res = new ArrayList<Integer>();
		Iterator<Integer> iterator = heap.iterator();
		while (iterator.hasNext()) {
			res.add(iterator.next().intValue());
		}
		Collections.sort(res, Collections.reverseOrder());

		return res;

	}

	// 23. Merge k Sorted Lists
	// Company: ALL
	// Description:
	// Input:
	// [
	// 1->4->5,
	// 1->3->4,
	// 2->6
	// ]
	// Output: 1->1->2->3->4->4->5->6
	// Soultion: 1. Using Heap(Priority Queue)
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		int size = lists.length;
		Queue<ListNode> queue = new PriorityQueue(size, new ListNodeComparator());

		for (int i = 0; i < size; i++) {
			queue.offer(lists[i]);
		}

		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;

		if (!queue.isEmpty()) {
			ListNode n = queue.poll();
			cur.next = n;
			cur = n;
			while (cur.next != null) {
				queue.offer(cur.next);
			}
		}

		return dummy.next;
	}

	public class ListNodeComparator implements Comparator<ListNode> {
		public int compare(ListNode o1, ListNode o2) {
			assert o1 != null && o2 != null;
			return o1.val - o2.val;
		}
	}

	// 264. Ugly Number II
	// Company: N/A
	// Description: Find the nth ugly number
	// Solution: Use priority Queue to maintain the order of generated ugly numbers
	public int nthUglyNumber(int n) {
		//
		Queue<Long> queue = new PriorityQueue<>();
		HashSet<Long> set = new HashSet<>();

		Long[] primes = new Long[3];
		primes[0] = Long.valueOf(2);
		primes[1] = Long.valueOf(3);
		primes[2] = Long.valueOf(5);

		for (int i = 0; i < primes.length; i++) {
			queue.add(primes[i]);
			set.add(primes[i]);
		}

		Long number = Long.valueOf(1);
		for (int i = 1; i < n; i++) {
			number = queue.poll();
			for (int j = 0; j < primes.length; j++) {
				if (!set.contains(number * primes[j])) {
					queue.add(number * primes[j]);
					set.add(number * primes[j]);
				}
			}
		}

		return number.intValue();
	}

	// 263. Ugly Number
	// Company: N/A
	// Description:
	// Write a program to check whether a given number is an ugly number.
	// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
	// Solution:
	public boolean isUgly(int num) {
		if (num == 0) {
			return false;
		}
		int[] uglyPrimes = { 2, 3, 5 };

		for (int i : uglyPrimes) {
			while (num % i == 0) {
				num = num / i;
			}
		}

		return num == 1;
	}

	// 387. First Unique Character in a String
	// Company: Google Microsoft Amazon Bloomberg
	// Description: Return the index of first unique character in a String
	// Solution: Use Array[26] to store the corresponding character, ++ its value
	// if encountered more than once.

	// 239. Sliding Window Maximum
	// Company: Google Amazon
	// Description:
	// Solution:

	// 346. Moving Average from Data Stream
	// Company: Google
	// Description:
	// Solution:
	class MovingAverage {
		private Queue<Integer> queue;
		private int capacity;

		/** Initialize your data structure here. */
		public MovingAverage(int size) {
			queue = new LinkedList<>();
			capacity = size;
		}

		public double next(int val) {
			int size = queue.size();
			if (size >= capacity) {
				queue.poll();
			}

			queue.offer(val);
			// int sum = 0; will lose precision
			double sum = 0;
			for (Integer i : queue) {
				sum += i;
			}

			return sum / queue.size();
		}
	}

}