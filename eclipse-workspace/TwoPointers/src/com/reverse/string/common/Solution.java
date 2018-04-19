package com.reverse.string.common;
//344. Reverse String
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String reverseString(String s) {
		  if(s == null || s.length()==0) return s;
		    char[] chars = s.toCharArray();
		    int start = 0;
		    int end = s.length()-1;
		    while(start<end){
		        char temp = chars[start];
		        chars[start] = chars[end];
		        chars[end] = temp;
		        start++;
		        end--;
		    }
		    return new String(chars);	        
    }

}
