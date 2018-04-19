package com.leetcode.tinyURL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	
	public static void main(String[] args) {
		Solution sl = new Solution();
		String tiny = sl.encode("http://since.yesterday.com");
		System.out.println(tiny);
		String longurl = "http://quitealongurl.com";
		System.out.println("hash:"+longurl.hashCode());
		String shorturl = sl.decode("http://tinyurl.com/-10514417");
		System.out.println(shorturl);
		
		String word = "eat";
        char[] characters = word.toCharArray(); 
		Arrays.sort(characters);
		String revisedWord = String.valueOf(characters);
		System.out.println(revisedWord);
		int result = sl.testBreakWhile();
		int sum = 5;
        sum /= 10;
        System.out.println("value:"+sum);
        long N = 10;
        for (long i = N; i > 0; i /= 2) {
        		System.out.println("N:"+i);
        }
        
	}


	    Map<Integer, String> map = new HashMap<>();

	    public String encode(String longUrl) {
	        map.put(longUrl.hashCode(), longUrl);
	        return "http://tinyurl.com/" + longUrl.hashCode();
	    }

	    public String decode(String shortUrl) {
	        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	    }
	    
	    public int testBreakWhile(){
	    		int testVar = 9;
	    		int returnValue = 0;
	    		String breakCondition = "string";
	    		while (testVar-- > 0) {
	    			System.out.println("int:"+testVar);
	    			returnValue = testVar;
	    			if(testVar ==3 && breakCondition.equalsIgnoreCase("string")) {	    				
	    				break;
	    			}
	    		}
	    		System.out.println("value:"+ returnValue);
	    		return returnValue;
	    }
}

