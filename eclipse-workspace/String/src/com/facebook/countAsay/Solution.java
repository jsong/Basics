package com.facebook.countAsay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	//TODO Revisit
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		String result = sl.countAndSay(3);
		System.out.println(result);
	}
//443. String Compression
	 public int compress(char[] chars) {
		
		 return 0;   
	    }
//	38. Count and Say
	public String countAndSay(int n) {
	    if(n<=0)
	        return "";
	    String curRes = "1";
	    int start = 1;//从1开始算
	    while(start < n){
	        StringBuilder res = new StringBuilder();
	        int count = 1;
	        for(int j=1;j<curRes.length();j++){
	            if(curRes.charAt(j)==curRes.charAt(j-1))
	                count++;
	            else{
	                res.append(count);
	                res.append(curRes.charAt(j-1));
	                count = 1;
	            }
	        }
	        res.append(count);
	        res.append(curRes.charAt(curRes.length()-1));
	        curRes = res.toString();
	        start++;
	    }
	    return curRes;
	}
}
