package com.leetcode.datastructure;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.Queue;

//146. LRU Cache
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
	}

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
