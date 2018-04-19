package com.amazon.firstuniqchar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		//most appearance showing first.
		String result = sl.frequencySort("bcaaa");
		System.out.println(result);
		int[] nums = {1,1,1,3,3,2,2,2,4,4,4};
		List<Integer> most = sl.topKFrequent(nums, 2);
		System.out.println(most);

	}
	//387. First Unique Character in a String
		public int firstUniqChar(String s) {
	        int freq [] = new int[26];
	        for(int i = 0; i < s.length(); i ++)
	            freq [s.charAt(i) - 'a'] ++;
	        for(int i = 0; i < s.length(); i ++)
	            if(freq [s.charAt(i) - 'a'] == 1)
	                return i;
	        return -1;
	    }
		
		//follow up 451. Sort Characters By Frequency
		//hastable & heap
		public String frequencySort(String s) {
		    if (s == null) {
		        return null;
		    }
		    Map<Character, Integer> map = new HashMap();
		    char[] charArray = s.toCharArray();
		    int max = 0;
		    for (Character c : charArray) {
		        if (!map.containsKey(c)) {
		            map.put(c, 1);
		        } else {
		        		map.put(c, map.get(c) + 1);
		        }
		        
		        max = Math.max(max, map.get(c));
		    }

		    List<Character>[] array = buildArray(map, max);

		    return buildString(array);
		}

		private List<Character>[] buildArray(Map<Character, Integer> map, int maxCount) {
		    List<Character>[] array = new List[maxCount + 1];
		    for (Character c : map.keySet()) {
		        int count = map.get(c);
		        if (array[count] == null) {
		            array[count] = new ArrayList();
		        }
		        array[count].add(c); //same count multiple characters.
		    }
		    return array;
		}

		private String buildString(List<Character>[] array) {
		    StringBuilder sb = new StringBuilder();
		    int length = array.length;
		    for (int i = array.length - 1; i > 0; i--) {
		        List<Character> list = array[i];
		        if (list != null) {
		            for (Character c : list) {
		                for (int j = 0; j < i; j++) {
		                    sb.append(c);
		                }
		            }
		        }
		    }
		    return sb.toString();
		}
		
//		347. Top K Frequent Elements
		public List<Integer> topKFrequent(int[] nums, int k) {  	
			
			List<Integer>[] bucket = new List[nums.length + 1];
			Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

			for (int n : nums) {
				frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
			}

			for (int key : frequencyMap.keySet()) {
				int frequency = frequencyMap.get(key);
				if (bucket[frequency] == null) {
					bucket[frequency] = new ArrayList<>();
				}
				bucket[frequency].add(key);
			}

			List<Integer> res = new ArrayList<>();

			for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
				if (bucket[pos] != null) {
					res.addAll(bucket[pos]);
				}
			}
			return res.subList(0, k);
//			return res;
	    }
		

}
